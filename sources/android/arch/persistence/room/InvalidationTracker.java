package android.arch.persistence.room;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class InvalidationTracker {
    @VisibleForTesting
    static final String CLEANUP_SQL = "DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)";
    private static final String CREATE_VERSION_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)";
    @VisibleForTesting
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private static final String VERSION_COLUMN_NAME = "version";
    /* access modifiers changed from: private */
    public volatile SupportSQLiteStatement mCleanupStatement;
    /* access modifiers changed from: private */
    public final RoomDatabase mDatabase;
    private volatile boolean mInitialized;
    /* access modifiers changed from: private */
    public long mMaxVersion = 0;
    private ObservedTableTracker mObservedTableTracker;
    @VisibleForTesting
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap;
    AtomicBoolean mPendingRefresh;
    /* access modifiers changed from: private */
    public Object[] mQueryArgs = new Object[1];
    @VisibleForTesting
    Runnable mRefreshRunnable;
    @VisibleForTesting
    @NonNull
    ArrayMap<String, Integer> mTableIdLookup;
    private String[] mTableNames;
    @VisibleForTesting
    @NonNull
    long[] mTableVersions;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public InvalidationTracker(RoomDatabase database, String... tableNames) {
        this.mPendingRefresh = new AtomicBoolean(false);
        this.mInitialized = false;
        this.mObserverMap = new SafeIterableMap<>();
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                SupportSQLiteDatabase db;
                Lock closeLock = InvalidationTracker.this.mDatabase.getCloseLock();
                boolean hasUpdatedTable = false;
                try {
                    closeLock.lock();
                    if (!InvalidationTracker.this.ensureInitialization()) {
                        closeLock.unlock();
                    } else if (!InvalidationTracker.this.mPendingRefresh.compareAndSet(true, false)) {
                        closeLock.unlock();
                    } else if (InvalidationTracker.this.mDatabase.inTransaction()) {
                        closeLock.unlock();
                    } else {
                        InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                        InvalidationTracker.this.mQueryArgs[0] = Long.valueOf(InvalidationTracker.this.mMaxVersion);
                        if (InvalidationTracker.this.mDatabase.mWriteAheadLoggingEnabled) {
                            db = InvalidationTracker.this.mDatabase.getOpenHelper().getWritableDatabase();
                            db.beginTransaction();
                            hasUpdatedTable = checkUpdatedTable();
                            db.setTransactionSuccessful();
                            db.endTransaction();
                        } else {
                            hasUpdatedTable = checkUpdatedTable();
                        }
                        closeLock.unlock();
                        if (hasUpdatedTable) {
                            synchronized (InvalidationTracker.this.mObserverMap) {
                                Iterator<Map.Entry<Observer, ObserverWrapper>> it = InvalidationTracker.this.mObserverMap.iterator();
                                while (it.hasNext()) {
                                    it.next().getValue().checkForInvalidation(InvalidationTracker.this.mTableVersions);
                                }
                            }
                        }
                    }
                } catch (SQLiteException | IllegalStateException exception) {
                    try {
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                    } catch (Throwable th) {
                        closeLock.unlock();
                        throw th;
                    }
                } catch (Throwable th2) {
                    db.endTransaction();
                    throw th2;
                }
            }

            private boolean checkUpdatedTable() {
                boolean hasUpdatedTable = false;
                Cursor cursor = InvalidationTracker.this.mDatabase.query(InvalidationTracker.SELECT_UPDATED_TABLES_SQL, InvalidationTracker.this.mQueryArgs);
                while (cursor.moveToNext()) {
                    try {
                        long version = cursor.getLong(0);
                        InvalidationTracker.this.mTableVersions[cursor.getInt(1)] = version;
                        hasUpdatedTable = true;
                        long unused = InvalidationTracker.this.mMaxVersion = version;
                    } finally {
                        cursor.close();
                    }
                }
                return hasUpdatedTable;
            }
        };
        this.mDatabase = database;
        this.mObservedTableTracker = new ObservedTableTracker(tableNames.length);
        this.mTableIdLookup = new ArrayMap<>();
        int size = tableNames.length;
        this.mTableNames = new String[size];
        for (int id = 0; id < size; id++) {
            String tableName = tableNames[id].toLowerCase(Locale.US);
            this.mTableIdLookup.put(tableName, Integer.valueOf(id));
            this.mTableNames[id] = tableName;
        }
        this.mTableVersions = new long[tableNames.length];
        Arrays.fill(this.mTableVersions, 0);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void internalInit(SupportSQLiteDatabase database) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            database.beginTransaction();
            try {
                database.execSQL("PRAGMA temp_store = MEMORY;");
                database.execSQL("PRAGMA recursive_triggers='ON';");
                database.execSQL(CREATE_VERSION_TABLE_SQL);
                database.setTransactionSuccessful();
                database.endTransaction();
                syncTriggers(database);
                this.mCleanupStatement = database.compileStatement(CLEANUP_SQL);
                this.mInitialized = true;
            } catch (Throwable th) {
                database.endTransaction();
                throw th;
            }
        }
    }

    private static void appendTriggerName(StringBuilder builder, String tableName, String triggerType) {
        builder.append("`");
        builder.append("room_table_modification_trigger_");
        builder.append(tableName);
        builder.append("_");
        builder.append(triggerType);
        builder.append("`");
    }

    private void stopTrackingTable(SupportSQLiteDatabase writableDb, int tableId) {
        String tableName = this.mTableNames[tableId];
        StringBuilder stringBuilder = new StringBuilder();
        for (String trigger : TRIGGERS) {
            stringBuilder.setLength(0);
            stringBuilder.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(stringBuilder, tableName, trigger);
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    private void startTrackingTable(SupportSQLiteDatabase writableDb, int tableId) {
        String tableName = this.mTableNames[tableId];
        StringBuilder stringBuilder = new StringBuilder();
        for (String trigger : TRIGGERS) {
            stringBuilder.setLength(0);
            stringBuilder.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(stringBuilder, tableName, trigger);
            stringBuilder.append(" AFTER ");
            stringBuilder.append(trigger);
            stringBuilder.append(" ON `");
            stringBuilder.append(tableName);
            stringBuilder.append("` BEGIN INSERT OR REPLACE INTO ");
            stringBuilder.append(UPDATE_TABLE_NAME);
            stringBuilder.append(" VALUES(null, ");
            stringBuilder.append(tableId);
            stringBuilder.append("); END");
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    @WorkerThread
    public void addObserver(@NonNull Observer observer) {
        ObserverWrapper currentObserver;
        String[] tableNames = observer.mTables;
        int[] tableIds = new int[tableNames.length];
        int size = tableNames.length;
        long[] versions = new long[tableNames.length];
        int i = 0;
        while (i < size) {
            Integer tableId = this.mTableIdLookup.get(tableNames[i].toLowerCase(Locale.US));
            if (tableId != null) {
                tableIds[i] = tableId.intValue();
                versions[i] = this.mMaxVersion;
                i++;
            } else {
                throw new IllegalArgumentException("There is no table with name " + tableNames[i]);
            }
        }
        ObserverWrapper wrapper = new ObserverWrapper(observer, tableIds, tableNames, versions);
        synchronized (this.mObserverMap) {
            currentObserver = this.mObserverMap.putIfAbsent(observer, wrapper);
        }
        if (currentObserver == null && this.mObservedTableTracker.onAdded(tableIds)) {
            syncTriggers();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    @WorkerThread
    public void removeObserver(@NonNull Observer observer) {
        ObserverWrapper wrapper;
        synchronized (this.mObserverMap) {
            wrapper = this.mObserverMap.remove(observer);
        }
        if (wrapper != null && this.mObservedTableTracker.onRemoved(wrapper.mTableIds)) {
            syncTriggers();
        }
    }

    /* access modifiers changed from: private */
    public boolean ensureInitialization() {
        if (!this.mDatabase.isOpen()) {
            return false;
        }
        if (!this.mInitialized) {
            this.mDatabase.getOpenHelper().getWritableDatabase();
        }
        if (this.mInitialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(this.mRefreshRunnable);
        }
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void refreshVersionsSync() {
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    /* access modifiers changed from: package-private */
    public void syncTriggers(SupportSQLiteDatabase database) {
        if (!database.inTransaction()) {
            while (true) {
                try {
                    Lock closeLock = this.mDatabase.getCloseLock();
                    closeLock.lock();
                    try {
                        int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                        if (tablesToSync == null) {
                            closeLock.unlock();
                            return;
                        }
                        int limit = tablesToSync.length;
                        database.beginTransaction();
                        for (int tableId = 0; tableId < limit; tableId++) {
                            switch (tablesToSync[tableId]) {
                                case 1:
                                    startTrackingTable(database, tableId);
                                    break;
                                case 2:
                                    stopTrackingTable(database, tableId);
                                    break;
                            }
                        }
                        database.setTransactionSuccessful();
                        database.endTransaction();
                        this.mObservedTableTracker.onSyncCompleted();
                        closeLock.unlock();
                    } catch (Throwable th) {
                        closeLock.unlock();
                        throw th;
                    }
                } catch (SQLiteException | IllegalStateException exception) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void syncTriggers() {
        if (this.mDatabase.isOpen()) {
            syncTriggers(this.mDatabase.getOpenHelper().getWritableDatabase());
        }
    }

    static class ObserverWrapper {
        final Observer mObserver;
        private final Set<String> mSingleTableSet;
        final int[] mTableIds;
        private final String[] mTableNames;
        private final long[] mVersions;

        ObserverWrapper(Observer observer, int[] tableIds, String[] tableNames, long[] versions) {
            this.mObserver = observer;
            this.mTableIds = tableIds;
            this.mTableNames = tableNames;
            this.mVersions = versions;
            if (tableIds.length == 1) {
                ArraySet<String> set = new ArraySet<>();
                set.add(this.mTableNames[0]);
                this.mSingleTableSet = Collections.unmodifiableSet(set);
                return;
            }
            this.mSingleTableSet = null;
        }

        /* access modifiers changed from: package-private */
        public void checkForInvalidation(long[] versions) {
            Set<String> invalidatedTables = null;
            int size = this.mTableIds.length;
            for (int index = 0; index < size; index++) {
                long newVersion = versions[this.mTableIds[index]];
                if (this.mVersions[index] < newVersion) {
                    this.mVersions[index] = newVersion;
                    if (size == 1) {
                        invalidatedTables = this.mSingleTableSet;
                    } else {
                        if (invalidatedTables == null) {
                            invalidatedTables = new ArraySet<>(size);
                        }
                        invalidatedTables.add(this.mTableNames[index]);
                    }
                }
            }
            if (invalidatedTables != null) {
                this.mObserver.onInvalidated(invalidatedTables);
            }
        }
    }

    public static abstract class Observer {
        final String[] mTables;

        public abstract void onInvalidated(@NonNull Set<String> set);

        protected Observer(@NonNull String firstTable, String... rest) {
            this.mTables = (String[]) Arrays.copyOf(rest, rest.length + 1);
            this.mTables[rest.length] = firstTable;
        }

        public Observer(@NonNull String[] tables) {
            this.mTables = (String[]) Arrays.copyOf(tables, tables.length);
        }
    }

    static class ObservedTableTracker {
        static final int ADD = 1;
        static final int NO_OP = 0;
        static final int REMOVE = 2;
        boolean mNeedsSync;
        boolean mPendingSync;
        final long[] mTableObservers;
        final int[] mTriggerStateChanges;
        final boolean[] mTriggerStates;

        ObservedTableTracker(int tableCount) {
            this.mTableObservers = new long[tableCount];
            this.mTriggerStates = new boolean[tableCount];
            this.mTriggerStateChanges = new int[tableCount];
            Arrays.fill(this.mTableObservers, 0);
            Arrays.fill(this.mTriggerStates, false);
        }

        /* access modifiers changed from: package-private */
        public boolean onAdded(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long prevObserverCount = this.mTableObservers[tableId];
                    this.mTableObservers[tableId] = 1 + prevObserverCount;
                    if (prevObserverCount == 0) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        /* access modifiers changed from: package-private */
        public boolean onRemoved(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long prevObserverCount = this.mTableObservers[tableId];
                    this.mTableObservers[tableId] = prevObserverCount - 1;
                    if (prevObserverCount == 1) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public int[] getTablesToSync() {
            synchronized (this) {
                if (this.mNeedsSync) {
                    if (!this.mPendingSync) {
                        int tableCount = this.mTableObservers.length;
                        int i = 0;
                        while (true) {
                            int i2 = 1;
                            if (i < tableCount) {
                                boolean newState = this.mTableObservers[i] > 0;
                                if (newState != this.mTriggerStates[i]) {
                                    int[] iArr = this.mTriggerStateChanges;
                                    if (!newState) {
                                        i2 = 2;
                                    }
                                    iArr[i] = i2;
                                } else {
                                    this.mTriggerStateChanges[i] = 0;
                                }
                                this.mTriggerStates[i] = newState;
                                i++;
                            } else {
                                this.mPendingSync = true;
                                this.mNeedsSync = false;
                                int[] iArr2 = this.mTriggerStateChanges;
                                return iArr2;
                            }
                        }
                    }
                }
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void onSyncCompleted() {
            synchronized (this) {
                this.mPendingSync = false;
            }
        }
    }

    static class WeakObserver extends Observer {
        final WeakReference<Observer> mDelegateRef;
        final InvalidationTracker mTracker;

        WeakObserver(InvalidationTracker tracker, Observer delegate) {
            super(delegate.mTables);
            this.mTracker = tracker;
            this.mDelegateRef = new WeakReference<>(delegate);
        }

        public void onInvalidated(@NonNull Set<String> tables) {
            Observer observer = (Observer) this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(tables);
            }
        }
    }
}

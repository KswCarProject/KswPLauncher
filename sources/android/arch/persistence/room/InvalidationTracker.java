package android.arch.persistence.room;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.persistence.p000db.SupportSQLiteDatabase;
import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.p001v4.util.ArrayMap;
import android.support.p001v4.util.ArraySet;
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

/* loaded from: classes.dex */
public class InvalidationTracker {
    static final String CLEANUP_SQL = "DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)";
    private static final String CREATE_VERSION_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)";
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private static final String VERSION_COLUMN_NAME = "version";
    private volatile SupportSQLiteStatement mCleanupStatement;
    private final RoomDatabase mDatabase;
    private ObservedTableTracker mObservedTableTracker;
    private String[] mTableNames;
    long[] mTableVersions;
    private Object[] mQueryArgs = new Object[1];
    private long mMaxVersion = 0;
    AtomicBoolean mPendingRefresh = new AtomicBoolean(false);
    private volatile boolean mInitialized = false;
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap = new SafeIterableMap<>();
    Runnable mRefreshRunnable = new Runnable() { // from class: android.arch.persistence.room.InvalidationTracker.1
        @Override // java.lang.Runnable
        public void run() {
            Lock closeLock = InvalidationTracker.this.mDatabase.getCloseLock();
            boolean hasUpdatedTable = false;
            try {
                try {
                    closeLock.lock();
                } catch (SQLiteException | IllegalStateException exception) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                }
                if (InvalidationTracker.this.ensureInitialization()) {
                    if (InvalidationTracker.this.mPendingRefresh.compareAndSet(true, false)) {
                        if (InvalidationTracker.this.mDatabase.inTransaction()) {
                            return;
                        }
                        InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                        InvalidationTracker.this.mQueryArgs[0] = Long.valueOf(InvalidationTracker.this.mMaxVersion);
                        if (InvalidationTracker.this.mDatabase.mWriteAheadLoggingEnabled) {
                            SupportSQLiteDatabase db = InvalidationTracker.this.mDatabase.getOpenHelper().getWritableDatabase();
                            try {
                                db.beginTransaction();
                                hasUpdatedTable = checkUpdatedTable();
                                db.setTransactionSuccessful();
                                db.endTransaction();
                            } catch (Throwable th) {
                                db.endTransaction();
                                throw th;
                            }
                        } else {
                            hasUpdatedTable = checkUpdatedTable();
                        }
                        if (hasUpdatedTable) {
                            synchronized (InvalidationTracker.this.mObserverMap) {
                                Iterator<Map.Entry<Observer, ObserverWrapper>> it = InvalidationTracker.this.mObserverMap.iterator();
                                while (it.hasNext()) {
                                    Map.Entry<Observer, ObserverWrapper> entry = it.next();
                                    entry.getValue().checkForInvalidation(InvalidationTracker.this.mTableVersions);
                                }
                            }
                        }
                    }
                }
            } finally {
                closeLock.unlock();
            }
        }

        private boolean checkUpdatedTable() {
            boolean hasUpdatedTable = false;
            Cursor cursor = InvalidationTracker.this.mDatabase.query(InvalidationTracker.SELECT_UPDATED_TABLES_SQL, InvalidationTracker.this.mQueryArgs);
            while (cursor.moveToNext()) {
                try {
                    long version = cursor.getLong(0);
                    int tableId = cursor.getInt(1);
                    InvalidationTracker.this.mTableVersions[tableId] = version;
                    hasUpdatedTable = true;
                    InvalidationTracker.this.mMaxVersion = version;
                } finally {
                    cursor.close();
                }
            }
            return hasUpdatedTable;
        }
    };
    ArrayMap<String, Integer> mTableIdLookup = new ArrayMap<>();

    public InvalidationTracker(RoomDatabase database, String... tableNames) {
        this.mDatabase = database;
        this.mObservedTableTracker = new ObservedTableTracker(tableNames.length);
        int size = tableNames.length;
        this.mTableNames = new String[size];
        for (int id = 0; id < size; id++) {
            String tableName = tableNames[id].toLowerCase(Locale.US);
            this.mTableIdLookup.put(tableName, Integer.valueOf(id));
            this.mTableNames[id] = tableName;
        }
        int id2 = tableNames.length;
        long[] jArr = new long[id2];
        this.mTableVersions = jArr;
        Arrays.fill(jArr, 0L);
    }

    void internalInit(SupportSQLiteDatabase database) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            database.beginTransaction();
            database.execSQL("PRAGMA temp_store = MEMORY;");
            database.execSQL("PRAGMA recursive_triggers='ON';");
            database.execSQL(CREATE_VERSION_TABLE_SQL);
            database.setTransactionSuccessful();
            database.endTransaction();
            syncTriggers(database);
            this.mCleanupStatement = database.compileStatement(CLEANUP_SQL);
            this.mInitialized = true;
        }
    }

    private static void appendTriggerName(StringBuilder builder, String tableName, String triggerType) {
        builder.append("`").append("room_table_modification_trigger_").append(tableName).append("_").append(triggerType).append("`");
    }

    private void stopTrackingTable(SupportSQLiteDatabase writableDb, int tableId) {
        String[] strArr;
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
        String[] strArr;
        String tableName = this.mTableNames[tableId];
        StringBuilder stringBuilder = new StringBuilder();
        for (String trigger : TRIGGERS) {
            stringBuilder.setLength(0);
            stringBuilder.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(stringBuilder, tableName, trigger);
            stringBuilder.append(" AFTER ").append(trigger).append(" ON `").append(tableName).append("` BEGIN INSERT OR REPLACE INTO ").append(UPDATE_TABLE_NAME).append(" VALUES(null, ").append(tableId).append("); END");
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    public void addObserver(Observer observer) {
        String[] tableNames = observer.mTables;
        int[] tableIds = new int[tableNames.length];
        int size = tableNames.length;
        long[] versions = new long[tableNames.length];
        for (int i = 0; i < size; i++) {
            Integer tableId = this.mTableIdLookup.get(tableNames[i].toLowerCase(Locale.US));
            if (tableId == null) {
                throw new IllegalArgumentException("There is no table with name " + tableNames[i]);
            }
            tableIds[i] = tableId.intValue();
            versions[i] = this.mMaxVersion;
        }
        ObserverWrapper wrapper = new ObserverWrapper(observer, tableIds, tableNames, versions);
        synchronized (this.mObserverMap) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                ObserverWrapper currentObserver = this.mObserverMap.putIfAbsent(observer, wrapper);
                if (currentObserver == null && this.mObservedTableTracker.onAdded(tableIds)) {
                    syncTriggers();
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    public void removeObserver(Observer observer) {
        synchronized (this.mObserverMap) {
            try {
                try {
                    ObserverWrapper wrapper = this.mObserverMap.remove(observer);
                    if (wrapper != null && this.mObservedTableTracker.onRemoved(wrapper.mTableIds)) {
                        syncTriggers();
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureInitialization() {
        if (this.mDatabase.isOpen()) {
            if (!this.mInitialized) {
                this.mDatabase.getOpenHelper().getWritableDatabase();
            }
            if (!this.mInitialized) {
                Log.e("ROOM", "database is not initialized even though it is open");
                return false;
            }
            return true;
        }
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(this.mRefreshRunnable);
        }
    }

    public void refreshVersionsSync() {
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    void syncTriggers(SupportSQLiteDatabase database) {
        if (database.inTransaction()) {
            return;
        }
        while (true) {
            try {
                Lock closeLock = this.mDatabase.getCloseLock();
                closeLock.lock();
                try {
                    int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                    if (tablesToSync == null) {
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
                } finally {
                    closeLock.unlock();
                }
            } catch (SQLiteException | IllegalStateException exception) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                return;
            }
        }
    }

    void syncTriggers() {
        if (!this.mDatabase.isOpen()) {
            return;
        }
        syncTriggers(this.mDatabase.getOpenHelper().getWritableDatabase());
    }

    /* loaded from: classes.dex */
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
                set.add(tableNames[0]);
                this.mSingleTableSet = Collections.unmodifiableSet(set);
                return;
            }
            this.mSingleTableSet = null;
        }

        void checkForInvalidation(long[] versions) {
            Set<String> invalidatedTables = null;
            int size = this.mTableIds.length;
            for (int index = 0; index < size; index++) {
                int tableId = this.mTableIds[index];
                long newVersion = versions[tableId];
                long[] jArr = this.mVersions;
                long currentVersion = jArr[index];
                if (currentVersion < newVersion) {
                    jArr[index] = newVersion;
                    if (size == 1) {
                        invalidatedTables = this.mSingleTableSet;
                    } else {
                        if (invalidatedTables == null) {
                            invalidatedTables = new ArraySet(size);
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

    /* loaded from: classes.dex */
    public static abstract class Observer {
        final String[] mTables;

        public abstract void onInvalidated(Set<String> set);

        protected Observer(String firstTable, String... rest) {
            String[] strArr = (String[]) Arrays.copyOf(rest, rest.length + 1);
            this.mTables = strArr;
            strArr[rest.length] = firstTable;
        }

        public Observer(String[] tables) {
            this.mTables = (String[]) Arrays.copyOf(tables, tables.length);
        }
    }

    /* loaded from: classes.dex */
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
            long[] jArr = new long[tableCount];
            this.mTableObservers = jArr;
            boolean[] zArr = new boolean[tableCount];
            this.mTriggerStates = zArr;
            this.mTriggerStateChanges = new int[tableCount];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        boolean onAdded(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long[] jArr = this.mTableObservers;
                    long prevObserverCount = jArr[tableId];
                    jArr[tableId] = 1 + prevObserverCount;
                    if (prevObserverCount == 0) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        boolean onRemoved(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long[] jArr = this.mTableObservers;
                    long prevObserverCount = jArr[tableId];
                    jArr[tableId] = prevObserverCount - 1;
                    if (prevObserverCount == 1) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        int[] getTablesToSync() {
            synchronized (this) {
                if (this.mNeedsSync && !this.mPendingSync) {
                    int tableCount = this.mTableObservers.length;
                    int i = 0;
                    while (true) {
                        int i2 = 1;
                        if (i < tableCount) {
                            boolean newState = this.mTableObservers[i] > 0;
                            boolean[] zArr = this.mTriggerStates;
                            if (newState != zArr[i]) {
                                int[] iArr = this.mTriggerStateChanges;
                                if (!newState) {
                                    i2 = 2;
                                }
                                iArr[i] = i2;
                            } else {
                                this.mTriggerStateChanges[i] = 0;
                            }
                            zArr[i] = newState;
                            i++;
                        } else {
                            this.mPendingSync = true;
                            this.mNeedsSync = false;
                            return this.mTriggerStateChanges;
                        }
                    }
                }
                return null;
            }
        }

        void onSyncCompleted() {
            synchronized (this) {
                this.mPendingSync = false;
            }
        }
    }

    /* loaded from: classes.dex */
    static class WeakObserver extends Observer {
        final WeakReference<Observer> mDelegateRef;
        final InvalidationTracker mTracker;

        WeakObserver(InvalidationTracker tracker, Observer delegate) {
            super(delegate.mTables);
            this.mTracker = tracker;
            this.mDelegateRef = new WeakReference<>(delegate);
        }

        @Override // android.arch.persistence.room.InvalidationTracker.Observer
        public void onInvalidated(Set<String> tables) {
            Observer observer = this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(tables);
            }
        }
    }
}

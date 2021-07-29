package android.arch.persistence.room;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
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
    static final String CLEANUP_SQL = "DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)";
    private static final String CREATE_VERSION_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)";
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private static final String VERSION_COLUMN_NAME = "version";
    /* access modifiers changed from: private */
    public volatile SupportSQLiteStatement mCleanupStatement;
    /* access modifiers changed from: private */
    public final RoomDatabase mDatabase;
    private volatile boolean mInitialized = false;
    /* access modifiers changed from: private */
    public long mMaxVersion = 0;
    private ObservedTableTracker mObservedTableTracker;
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap = new SafeIterableMap<>();
    AtomicBoolean mPendingRefresh = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Object[] mQueryArgs = new Object[1];
    Runnable mRefreshRunnable = new Runnable() {
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
    ArrayMap<String, Integer> mTableIdLookup;
    private String[] mTableNames;
    long[] mTableVersions;

    public InvalidationTracker(RoomDatabase database, String... tableNames) {
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
        long[] jArr = new long[tableNames.length];
        this.mTableVersions = jArr;
        Arrays.fill(jArr, 0);
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
        builder.append("`").append("room_table_modification_trigger_").append(tableName).append("_").append(triggerType).append("`");
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
            stringBuilder.append(" AFTER ").append(trigger).append(" ON `").append(tableName).append("` BEGIN INSERT OR REPLACE INTO ").append(UPDATE_TABLE_NAME).append(" VALUES(null, ").append(tableId).append("); END");
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        if (r5 != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        if (r9.mObservedTableTracker.onAdded(r1) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        syncTriggers();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addObserver(android.arch.persistence.room.InvalidationTracker.Observer r10) {
        /*
            r9 = this;
            java.lang.String[] r0 = r10.mTables
            int r1 = r0.length
            int[] r1 = new int[r1]
            int r2 = r0.length
            int r3 = r0.length
            long[] r3 = new long[r3]
            r4 = 0
            r5 = 0
        L_0x000b:
            if (r4 >= r2) goto L_0x0047
            android.support.v4.util.ArrayMap<java.lang.String, java.lang.Integer> r5 = r9.mTableIdLookup
            r6 = r0[r4]
            java.util.Locale r7 = java.util.Locale.US
            java.lang.String r6 = r6.toLowerCase(r7)
            java.lang.Object r5 = r5.get(r6)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L_0x002c
            int r6 = r5.intValue()
            r1[r4] = r6
            long r6 = r9.mMaxVersion
            r3[r4] = r6
            int r4 = r4 + 1
            goto L_0x000b
        L_0x002c:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "There is no table with name "
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r0[r4]
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0047:
            android.arch.persistence.room.InvalidationTracker$ObserverWrapper r4 = new android.arch.persistence.room.InvalidationTracker$ObserverWrapper
            r4.<init>(r10, r1, r0, r3)
            android.arch.core.internal.SafeIterableMap<android.arch.persistence.room.InvalidationTracker$Observer, android.arch.persistence.room.InvalidationTracker$ObserverWrapper> r6 = r9.mObserverMap
            monitor-enter(r6)
            android.arch.core.internal.SafeIterableMap<android.arch.persistence.room.InvalidationTracker$Observer, android.arch.persistence.room.InvalidationTracker$ObserverWrapper> r7 = r9.mObserverMap     // Catch:{ all -> 0x0067 }
            java.lang.Object r7 = r7.putIfAbsent(r10, r4)     // Catch:{ all -> 0x0067 }
            android.arch.persistence.room.InvalidationTracker$ObserverWrapper r7 = (android.arch.persistence.room.InvalidationTracker.ObserverWrapper) r7     // Catch:{ all -> 0x0067 }
            r5 = r7
            monitor-exit(r6)     // Catch:{ all -> 0x006a }
            if (r5 != 0) goto L_0x0066
            android.arch.persistence.room.InvalidationTracker$ObservedTableTracker r6 = r9.mObservedTableTracker
            boolean r6 = r6.onAdded(r1)
            if (r6 == 0) goto L_0x0066
            r9.syncTriggers()
        L_0x0066:
            return
        L_0x0067:
            r7 = move-exception
        L_0x0068:
            monitor-exit(r6)     // Catch:{ all -> 0x006a }
            throw r7
        L_0x006a:
            r7 = move-exception
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.InvalidationTracker.addObserver(android.arch.persistence.room.InvalidationTracker$Observer):void");
    }

    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r3.mObservedTableTracker.onRemoved(r1.mTableIds) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        syncTriggers();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r1 == null) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeObserver(android.arch.persistence.room.InvalidationTracker.Observer r4) {
        /*
            r3 = this;
            android.arch.core.internal.SafeIterableMap<android.arch.persistence.room.InvalidationTracker$Observer, android.arch.persistence.room.InvalidationTracker$ObserverWrapper> r0 = r3.mObserverMap
            monitor-enter(r0)
            r1 = 0
            android.arch.core.internal.SafeIterableMap<android.arch.persistence.room.InvalidationTracker$Observer, android.arch.persistence.room.InvalidationTracker$ObserverWrapper> r2 = r3.mObserverMap     // Catch:{ all -> 0x001e }
            java.lang.Object r2 = r2.remove(r4)     // Catch:{ all -> 0x001e }
            android.arch.persistence.room.InvalidationTracker$ObserverWrapper r2 = (android.arch.persistence.room.InvalidationTracker.ObserverWrapper) r2     // Catch:{ all -> 0x001e }
            r1 = r2
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x001d
            android.arch.persistence.room.InvalidationTracker$ObservedTableTracker r0 = r3.mObservedTableTracker
            int[] r2 = r1.mTableIds
            boolean r0 = r0.onRemoved(r2)
            if (r0 == 0) goto L_0x001d
            r3.syncTriggers()
        L_0x001d:
            return
        L_0x001e:
            r2 = move-exception
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r2
        L_0x0021:
            r2 = move-exception
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.InvalidationTracker.removeObserver(android.arch.persistence.room.InvalidationTracker$Observer):void");
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
                set.add(tableNames[0]);
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
                long[] jArr = this.mVersions;
                if (jArr[index] < newVersion) {
                    jArr[index] = newVersion;
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
            Arrays.fill(jArr, 0);
            Arrays.fill(zArr, false);
        }

        /* access modifiers changed from: package-private */
        public boolean onAdded(int... tableIds) {
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

        /* access modifiers changed from: package-private */
        public boolean onRemoved(int... tableIds) {
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

        /* access modifiers changed from: package-private */
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

        public void onInvalidated(Set<String> tables) {
            Observer observer = (Observer) this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(tables);
            }
        }
    }
}

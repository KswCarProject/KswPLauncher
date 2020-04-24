package android.arch.persistence.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.arch.core.executor.ArchTaskExecutor;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    @Nullable
    protected List<Callback> mCallbacks;
    private final ReentrantLock mCloseLock = new ReentrantLock();
    protected volatile SupportSQLiteDatabase mDatabase;
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();
    private SupportSQLiteOpenHelper mOpenHelper;
    boolean mWriteAheadLoggingEnabled;

    @WorkerThread
    public abstract void clearAllTables();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract InvalidationTracker createInvalidationTracker();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    /* access modifiers changed from: package-private */
    public Lock getCloseLock() {
        return this.mCloseLock;
    }

    @CallSuper
    public void init(@NonNull DatabaseConfiguration configuration) {
        this.mOpenHelper = createOpenHelper(configuration);
        boolean wal = false;
        if (Build.VERSION.SDK_INT >= 16) {
            wal = configuration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
            this.mOpenHelper.setWriteAheadLoggingEnabled(wal);
        }
        this.mCallbacks = configuration.callbacks;
        this.mAllowMainThreadQueries = configuration.allowMainThreadQueries;
        this.mWriteAheadLoggingEnabled = wal;
    }

    @NonNull
    public SupportSQLiteOpenHelper getOpenHelper() {
        return this.mOpenHelper;
    }

    public boolean isOpen() {
        SupportSQLiteDatabase db = this.mDatabase;
        return db != null && db.isOpen();
    }

    public void close() {
        if (isOpen()) {
            try {
                this.mCloseLock.lock();
                this.mOpenHelper.close();
            } finally {
                this.mCloseLock.unlock();
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor query(String query, @Nullable Object[] args) {
        return this.mOpenHelper.getWritableDatabase().query((SupportSQLiteQuery) new SimpleSQLiteQuery(query, args));
    }

    public Cursor query(SupportSQLiteQuery query) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().query(query);
    }

    public SupportSQLiteStatement compileStatement(@NonNull String sql) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().compileStatement(sql);
    }

    public void beginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase database = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(database);
        database.beginTransaction();
    }

    public void endTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            this.mInvalidationTracker.refreshVersionsAsync();
        }
    }

    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(@NonNull Runnable body) {
        beginTransaction();
        try {
            body.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(@NonNull Callable<V> body) {
        beginTransaction();
        try {
            V result = body.call();
            setTransactionSuccessful();
            endTransaction();
            return result;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception in transaction", e2);
        } catch (Throwable result2) {
            endTransaction();
            throw result2;
        }
    }

    /* access modifiers changed from: protected */
    public void internalInitInvalidationTracker(@NonNull SupportSQLiteDatabase db) {
        this.mInvalidationTracker.internalInit(db);
    }

    @NonNull
    public InvalidationTracker getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public JournalMode resolve(Context context) {
            ActivityManager manager;
            if (this != AUTOMATIC) {
                return this;
            }
            if (Build.VERSION.SDK_INT < 16 || (manager = (ActivityManager) context.getSystemService("activity")) == null || ActivityManagerCompat.isLowRamDevice(manager)) {
                return TRUNCATE;
            }
            return WRITE_AHEAD_LOGGING;
        }
    }

    public static class Builder<T extends RoomDatabase> {
        private boolean mAllowMainThreadQueries;
        private ArrayList<Callback> mCallbacks;
        private final Context mContext;
        private final Class<T> mDatabaseClass;
        private SupportSQLiteOpenHelper.Factory mFactory;
        private JournalMode mJournalMode = JournalMode.AUTOMATIC;
        private final MigrationContainer mMigrationContainer = new MigrationContainer();
        private Set<Integer> mMigrationStartAndEndVersions;
        private Set<Integer> mMigrationsNotRequiredFrom;
        private final String mName;
        private boolean mRequireMigration = true;

        Builder(@NonNull Context context, @NonNull Class<T> klass, @Nullable String name) {
            this.mContext = context;
            this.mDatabaseClass = klass;
            this.mName = name;
        }

        @NonNull
        public Builder<T> openHelperFactory(@Nullable SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        @NonNull
        public Builder<T> addMigrations(@NonNull Migration... migrations) {
            if (this.mMigrationStartAndEndVersions == null) {
                this.mMigrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrations) {
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            this.mMigrationContainer.addMigrations(migrations);
            return this;
        }

        @NonNull
        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        @NonNull
        public Builder<T> setJournalMode(@NonNull JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        @NonNull
        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            return this;
        }

        @NonNull
        public Builder<T> fallbackToDestructiveMigrationFrom(int... startVersions) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(startVersions.length);
            }
            for (int startVersion : startVersions) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(startVersion));
            }
            return this;
        }

        @NonNull
        public Builder<T> addCallback(@NonNull Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        @NonNull
        public T build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.mDatabaseClass != null) {
                if (!(this.mMigrationStartAndEndVersions == null || this.mMigrationsNotRequiredFrom == null)) {
                    for (Integer version : this.mMigrationStartAndEndVersions) {
                        if (this.mMigrationsNotRequiredFrom.contains(version)) {
                            throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + version);
                        }
                    }
                }
                if (this.mFactory == null) {
                    this.mFactory = new FrameworkSQLiteOpenHelperFactory();
                }
                T db = new DatabaseConfiguration(this.mContext, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(this.mContext), this.mRequireMigration, this.mMigrationsNotRequiredFrom);
                T db2 = (RoomDatabase) Room.getGeneratedImplementation(this.mDatabaseClass, RoomDatabase.DB_IMPL_SUFFIX);
                db2.init(db);
                return db2;
            } else {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
        }
    }

    public static class MigrationContainer {
        private SparseArrayCompat<SparseArrayCompat<Migration>> mMigrations = new SparseArrayCompat<>();

        public void addMigrations(@NonNull Migration... migrations) {
            for (Migration migration : migrations) {
                addMigration(migration);
            }
        }

        private void addMigration(Migration migration) {
            int start = migration.startVersion;
            int end = migration.endVersion;
            SparseArrayCompat<Migration> targetMap = this.mMigrations.get(start);
            if (targetMap == null) {
                targetMap = new SparseArrayCompat<>();
                this.mMigrations.put(start, targetMap);
            }
            Migration existing = targetMap.get(end);
            if (existing != null) {
                Log.w("ROOM", "Overriding migration " + existing + " with " + migration);
            }
            targetMap.append(end, migration);
        }

        @Nullable
        public List<Migration> findMigrationPath(int start, int end) {
            if (start == end) {
                return Collections.emptyList();
            }
            return findUpMigrationPath(new ArrayList<>(), end > start, start, end);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0020 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.util.List<android.arch.persistence.room.migration.Migration> findUpMigrationPath(java.util.List<android.arch.persistence.room.migration.Migration> r16, boolean r17, int r18, int r19) {
            /*
                r15 = this;
                r0 = r16
                r1 = r19
                if (r17 == 0) goto L_0x0008
                r3 = -1
                goto L_0x0009
            L_0x0008:
                r3 = 1
            L_0x0009:
                r4 = r18
            L_0x000b:
                if (r17 == 0) goto L_0x0012
                if (r4 >= r1) goto L_0x0010
                goto L_0x0014
            L_0x0010:
                r5 = r15
                goto L_0x0059
            L_0x0012:
                if (r4 <= r1) goto L_0x0010
            L_0x0014:
                r5 = r15
                android.support.v4.util.SparseArrayCompat<android.support.v4.util.SparseArrayCompat<android.arch.persistence.room.migration.Migration>> r6 = r5.mMigrations
                java.lang.Object r6 = r6.get(r4)
                android.support.v4.util.SparseArrayCompat r6 = (android.support.v4.util.SparseArrayCompat) r6
                r7 = 0
                if (r6 != 0) goto L_0x0021
                return r7
            L_0x0021:
                int r8 = r6.size()
                if (r17 == 0) goto L_0x002b
                int r9 = r8 + -1
                r10 = -1
                goto L_0x002d
            L_0x002b:
                r9 = 0
                r10 = r8
            L_0x002d:
                r11 = 0
                r12 = r9
            L_0x002f:
                if (r12 == r10) goto L_0x0055
                int r13 = r6.keyAt(r12)
                r14 = 0
                if (r17 == 0) goto L_0x003f
                if (r13 > r1) goto L_0x003e
                if (r13 <= r4) goto L_0x003e
                r14 = 1
            L_0x003e:
                goto L_0x0045
            L_0x003f:
                if (r13 < r1) goto L_0x0045
                if (r13 >= r4) goto L_0x0045
                r14 = 1
            L_0x0045:
                if (r14 == 0) goto L_0x0053
                java.lang.Object r2 = r6.valueAt(r12)
                r0.add(r2)
                r2 = r13
                r11 = 1
                r4 = r2
                goto L_0x0055
            L_0x0053:
                int r12 = r12 + r3
                goto L_0x002f
            L_0x0055:
                if (r11 != 0) goto L_0x0058
                return r7
            L_0x0058:
                goto L_0x000b
            L_0x0059:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.RoomDatabase.MigrationContainer.findUpMigrationPath(java.util.List, boolean, int, int):java.util.List");
        }
    }

    public static abstract class Callback {
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
        }

        public void onOpen(@NonNull SupportSQLiteDatabase db) {
        }
    }
}

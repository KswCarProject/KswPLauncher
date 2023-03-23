package android.arch.persistence.room;

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
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    /* access modifiers changed from: protected */
    public List<Callback> mCallbacks;
    private final ReentrantLock mCloseLock = new ReentrantLock();
    /* access modifiers changed from: protected */
    public volatile SupportSQLiteDatabase mDatabase;
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();
    private SupportSQLiteOpenHelper mOpenHelper;
    boolean mWriteAheadLoggingEnabled;

    public abstract void clearAllTables();

    /* access modifiers changed from: protected */
    public abstract InvalidationTracker createInvalidationTracker();

    /* access modifiers changed from: protected */
    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    /* access modifiers changed from: package-private */
    public Lock getCloseLock() {
        return this.mCloseLock;
    }

    public void init(DatabaseConfiguration configuration) {
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

    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor query(String query, Object[] args) {
        return this.mOpenHelper.getWritableDatabase().query((SupportSQLiteQuery) new SimpleSQLiteQuery(query, args));
    }

    public Cursor query(SupportSQLiteQuery query) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().query(query);
    }

    public SupportSQLiteStatement compileStatement(String sql) {
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

    public void runInTransaction(Runnable body) {
        beginTransaction();
        try {
            body.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(Callable<V> body) {
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
    public void internalInitInvalidationTracker(SupportSQLiteDatabase db) {
        this.mInvalidationTracker.internalInit(db);
    }

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

        Builder(Context context, Class<T> klass, String name) {
            this.mContext = context;
            this.mDatabaseClass = klass;
            this.mName = name;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        public Builder<T> addMigrations(Migration... migrations) {
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

        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(int... startVersions) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(startVersions.length);
            }
            for (int startVersion : startVersions) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(startVersion));
            }
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        public T build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.mDatabaseClass != null) {
                Set<Integer> set = this.mMigrationStartAndEndVersions;
                if (!(set == null || this.mMigrationsNotRequiredFrom == null)) {
                    for (Integer version : set) {
                        if (this.mMigrationsNotRequiredFrom.contains(version)) {
                            throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + version);
                        }
                    }
                }
                if (this.mFactory == null) {
                    this.mFactory = new FrameworkSQLiteOpenHelperFactory();
                }
                Context context = this.mContext;
                T db = new DatabaseConfiguration(context, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(context), this.mRequireMigration, this.mMigrationsNotRequiredFrom);
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

        public void addMigrations(Migration... migrations) {
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

        public List<Migration> findMigrationPath(int start, int end) {
            if (start == end) {
                return Collections.emptyList();
            }
            return findUpMigrationPath(new ArrayList<>(), end > start, start, end);
        }

        private List<Migration> findUpMigrationPath(List<Migration> result, boolean upgrade, int start, int end) {
            int lastIndex;
            int firstIndex;
            boolean found;
            List<Migration> list = result;
            int i = end;
            int searchDirection = upgrade ? -1 : 1;
            int start2 = start;
            do {
                if (upgrade) {
                    if (start2 >= i) {
                        return list;
                    }
                } else if (start2 <= i) {
                    return list;
                }
                SparseArrayCompat<Migration> targetNodes = this.mMigrations.get(start2);
                if (targetNodes != null) {
                    int size = targetNodes.size();
                    if (upgrade) {
                        firstIndex = size - 1;
                        lastIndex = -1;
                    } else {
                        firstIndex = 0;
                        lastIndex = size;
                    }
                    found = false;
                    int i2 = firstIndex;
                    while (true) {
                        if (i2 == lastIndex) {
                            break;
                        }
                        int targetVersion = targetNodes.keyAt(i2);
                        boolean shouldAddToPath = false;
                        if (upgrade) {
                            if (targetVersion <= i && targetVersion > start2) {
                                shouldAddToPath = true;
                            }
                        } else if (targetVersion >= i && targetVersion < start2) {
                            shouldAddToPath = true;
                        }
                        if (shouldAddToPath) {
                            list.add(targetNodes.valueAt(i2));
                            start2 = targetVersion;
                            found = true;
                            continue;
                            break;
                        }
                        i2 += searchDirection;
                    }
                } else {
                    return null;
                }
            } while (found);
            return null;
        }
    }

    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase db) {
        }

        public void onOpen(SupportSQLiteDatabase db) {
        }
    }
}

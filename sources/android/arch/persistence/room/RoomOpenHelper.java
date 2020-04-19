package android.arch.persistence.room;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    @Nullable
    private DatabaseConfiguration mConfiguration;
    @NonNull
    private final Delegate mDelegate;
    @NonNull
    private final String mIdentityHash;
    @NonNull
    private final String mLegacyHash;

    public RoomOpenHelper(@NonNull DatabaseConfiguration configuration, @NonNull Delegate delegate, @NonNull String identityHash, @NonNull String legacyHash) {
        super(delegate.version);
        this.mConfiguration = configuration;
        this.mDelegate = delegate;
        this.mIdentityHash = identityHash;
        this.mLegacyHash = legacyHash;
    }

    public RoomOpenHelper(@NonNull DatabaseConfiguration configuration, @NonNull Delegate delegate, @NonNull String legacyHash) {
        this(configuration, delegate, "", legacyHash);
    }

    public void onConfigure(SupportSQLiteDatabase db) {
        super.onConfigure(db);
    }

    public void onCreate(SupportSQLiteDatabase db) {
        updateIdentity(db);
        this.mDelegate.createAllTables(db);
        this.mDelegate.onCreate(db);
    }

    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        List<Migration> migrations;
        boolean migrated = false;
        if (!(this.mConfiguration == null || (migrations = this.mConfiguration.migrationContainer.findMigrationPath(oldVersion, newVersion)) == null)) {
            for (Migration migration : migrations) {
                migration.migrate(db);
            }
            this.mDelegate.validateMigration(db);
            updateIdentity(db);
            migrated = true;
        }
        if (migrated) {
            return;
        }
        if (this.mConfiguration == null || this.mConfiguration.isMigrationRequiredFrom(oldVersion)) {
            throw new IllegalStateException("A migration from " + oldVersion + " to " + newVersion + " was required but not found. Please provide the " + "necessary Migration path via " + "RoomDatabase.Builder.addMigration(Migration ...) or allow for " + "destructive migrations via one of the " + "RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
        }
        this.mDelegate.dropAllTables(db);
        this.mDelegate.createAllTables(db);
    }

    public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onOpen(SupportSQLiteDatabase db) {
        super.onOpen(db);
        checkIdentity(db);
        this.mDelegate.onOpen(db);
        this.mConfiguration = null;
    }

    private void checkIdentity(SupportSQLiteDatabase db) {
        String identityHash = null;
        if (hasRoomMasterTable(db)) {
            Cursor cursor = db.query((SupportSQLiteQuery) new SimpleSQLiteQuery(RoomMasterTable.READ_QUERY));
            try {
                if (cursor.moveToFirst()) {
                    identityHash = cursor.getString(0);
                }
            } finally {
                cursor.close();
            }
        }
        if (!this.mIdentityHash.equals(identityHash) && !this.mLegacyHash.equals(identityHash)) {
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
        }
    }

    private void updateIdentity(SupportSQLiteDatabase db) {
        createMasterTableIfNotExists(db);
        db.execSQL(RoomMasterTable.createInsertQuery(this.mIdentityHash));
    }

    private void createMasterTableIfNotExists(SupportSQLiteDatabase db) {
        db.execSQL(RoomMasterTable.CREATE_QUERY);
    }

    private static boolean hasRoomMasterTable(SupportSQLiteDatabase db) {
        Cursor cursor = db.query("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
        try {
            boolean z = false;
            if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            cursor.close();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static abstract class Delegate {
        public final int version;

        /* access modifiers changed from: protected */
        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        /* access modifiers changed from: protected */
        public abstract void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase);

        public Delegate(int version2) {
            this.version = version2;
        }
    }
}

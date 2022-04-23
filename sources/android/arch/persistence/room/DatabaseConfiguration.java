package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import java.util.List;
import java.util.Set;

public class DatabaseConfiguration {
    public final boolean allowMainThreadQueries;
    public final List<RoomDatabase.Callback> callbacks;
    public final Context context;
    public final RoomDatabase.JournalMode journalMode;
    private final Set<Integer> mMigrationNotRequiredFrom;
    public final RoomDatabase.MigrationContainer migrationContainer;
    public final String name;
    public final boolean requireMigration;
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;

    public DatabaseConfiguration(Context context2, String name2, SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory2, RoomDatabase.MigrationContainer migrationContainer2, List<RoomDatabase.Callback> callbacks2, boolean allowMainThreadQueries2, RoomDatabase.JournalMode journalMode2, boolean requireMigration2, Set<Integer> migrationNotRequiredFrom) {
        this.sqliteOpenHelperFactory = sqliteOpenHelperFactory2;
        this.context = context2;
        this.name = name2;
        this.migrationContainer = migrationContainer2;
        this.callbacks = callbacks2;
        this.allowMainThreadQueries = allowMainThreadQueries2;
        this.journalMode = journalMode2;
        this.requireMigration = requireMigration2;
        this.mMigrationNotRequiredFrom = migrationNotRequiredFrom;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.mMigrationNotRequiredFrom;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isMigrationRequiredFrom(int r3) {
        /*
            r2 = this;
            boolean r0 = r2.requireMigration
            if (r0 == 0) goto L_0x0014
            java.util.Set<java.lang.Integer> r0 = r2.mMigrationNotRequiredFrom
            if (r0 == 0) goto L_0x0012
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x0014
        L_0x0012:
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.DatabaseConfiguration.isMigrationRequiredFrom(int):boolean");
    }
}

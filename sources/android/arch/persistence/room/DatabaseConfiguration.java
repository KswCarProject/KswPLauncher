package android.arch.persistence.room;

import android.arch.persistence.p000db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
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

    public DatabaseConfiguration(Context context, String name, SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory, RoomDatabase.MigrationContainer migrationContainer, List<RoomDatabase.Callback> callbacks, boolean allowMainThreadQueries, RoomDatabase.JournalMode journalMode, boolean requireMigration, Set<Integer> migrationNotRequiredFrom) {
        this.sqliteOpenHelperFactory = sqliteOpenHelperFactory;
        this.context = context;
        this.name = name;
        this.migrationContainer = migrationContainer;
        this.callbacks = callbacks;
        this.allowMainThreadQueries = allowMainThreadQueries;
        this.journalMode = journalMode;
        this.requireMigration = requireMigration;
        this.mMigrationNotRequiredFrom = migrationNotRequiredFrom;
    }

    public boolean isMigrationRequiredFrom(int version) {
        Set<Integer> set;
        return this.requireMigration && ((set = this.mMigrationNotRequiredFrom) == null || !set.contains(Integer.valueOf(version)));
    }
}

package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.List;
import java.util.Set;

public class DatabaseConfiguration {
    public final boolean allowMainThreadQueries;
    @Nullable
    public final List<RoomDatabase.Callback> callbacks;
    @NonNull
    public final Context context;
    public final RoomDatabase.JournalMode journalMode;
    private final Set<Integer> mMigrationNotRequiredFrom;
    @NonNull
    public final RoomDatabase.MigrationContainer migrationContainer;
    @Nullable
    public final String name;
    public final boolean requireMigration;
    @NonNull
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public DatabaseConfiguration(@NonNull Context context2, @Nullable String name2, @NonNull SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory2, @NonNull RoomDatabase.MigrationContainer migrationContainer2, @Nullable List<RoomDatabase.Callback> callbacks2, boolean allowMainThreadQueries2, RoomDatabase.JournalMode journalMode2, boolean requireMigration2, @Nullable Set<Integer> migrationNotRequiredFrom) {
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

    public boolean isMigrationRequiredFrom(int version) {
        return this.requireMigration && (this.mMigrationNotRequiredFrom == null || !this.mMigrationNotRequiredFrom.contains(Integer.valueOf(version)));
    }
}

package android.arch.persistence.room.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;

public abstract class Migration {
    public final int endVersion;
    public final int startVersion;

    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);

    public Migration(int startVersion2, int endVersion2) {
        this.startVersion = startVersion2;
        this.endVersion = endVersion2;
    }
}

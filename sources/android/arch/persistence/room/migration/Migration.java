package android.arch.persistence.room.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.support.annotation.NonNull;

public abstract class Migration {
    public final int endVersion;
    public final int startVersion;

    public abstract void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase);

    public Migration(int startVersion2, int endVersion2) {
        this.startVersion = startVersion2;
        this.endVersion = endVersion2;
    }
}

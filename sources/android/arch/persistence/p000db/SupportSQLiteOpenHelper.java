package android.arch.persistence.p000db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* renamed from: android.arch.persistence.db.SupportSQLiteOpenHelper */
/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper {

    /* renamed from: android.arch.persistence.db.SupportSQLiteOpenHelper$Factory */
    /* loaded from: classes.dex */
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);

    /* renamed from: android.arch.persistence.db.SupportSQLiteOpenHelper$Callback */
    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public Callback(int version) {
            this.version = version;
        }

        public void onConfigure(SupportSQLiteDatabase db) {
        }

        public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
            throw new SQLiteException("Can't downgrade database from version " + oldVersion + " to " + newVersion);
        }

        public void onOpen(SupportSQLiteDatabase db) {
        }

        public void onCorruption(SupportSQLiteDatabase db) {
            Log.e(TAG, "Corruption reported by sqlite on database: " + db.getPath());
            if (!db.isOpen()) {
                deleteDatabaseFile(db.getPath());
                return;
            }
            List<Pair<String, String>> attachedDbs = null;
            try {
                try {
                    attachedDbs = db.getAttachedDbs();
                } catch (SQLiteException e) {
                }
                try {
                    db.close();
                } catch (IOException e2) {
                }
            } finally {
                if (attachedDbs != null) {
                    for (Pair<String, String> p : attachedDbs) {
                        deleteDatabaseFile((String) p.second);
                    }
                } else {
                    deleteDatabaseFile(db.getPath());
                }
            }
        }

        private void deleteDatabaseFile(String fileName) {
            if (fileName.equalsIgnoreCase(":memory:") || fileName.trim().length() == 0) {
                return;
            }
            Log.w(TAG, "deleting the database file: " + fileName);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    SQLiteDatabase.deleteDatabase(new File(fileName));
                } else {
                    try {
                        boolean deleted = new File(fileName).delete();
                        if (!deleted) {
                            Log.e(TAG, "Could not delete the database file " + fileName);
                        }
                    } catch (Exception error) {
                        Log.e(TAG, "error while deleting corrupted database file", error);
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, "delete failed: ", e);
            }
        }
    }

    /* renamed from: android.arch.persistence.db.SupportSQLiteOpenHelper$Configuration */
    /* loaded from: classes.dex */
    public static class Configuration {
        public final Callback callback;
        public final Context context;
        public final String name;

        Configuration(Context context, String name, Callback callback) {
            this.context = context;
            this.name = name;
            this.callback = callback;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        /* renamed from: android.arch.persistence.db.SupportSQLiteOpenHelper$Configuration$Builder */
        /* loaded from: classes.dex */
        public static class Builder {
            Callback mCallback;
            Context mContext;
            String mName;

            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                if (this.mContext == null) {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
                return new Configuration(this.mContext, this.mName, this.mCallback);
            }

            Builder(Context context) {
                this.mContext = context;
            }

            public Builder name(String name) {
                this.mName = name;
                return this;
            }

            public Builder callback(Callback callback) {
                this.mCallback = callback;
                return this;
            }
        }
    }
}

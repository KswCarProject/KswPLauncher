package android.arch.persistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.File;

public interface SupportSQLiteOpenHelper {

    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    @RequiresApi(api = 16)
    void setWriteAheadLoggingEnabled(boolean z);

    public static abstract class Callback {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public Callback(int version2) {
            this.version = version2;
        }

        public void onConfigure(SupportSQLiteDatabase db) {
        }

        public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
            throw new SQLiteException("Can't downgrade database from version " + oldVersion + " to " + newVersion);
        }

        public void onOpen(SupportSQLiteDatabase db) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
            if (r0 != null) goto L_0x0038;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
            r2 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
            if (r2.hasNext() != false) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
            deleteDatabaseFile((java.lang.String) r2.next().second);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
            deleteDatabaseFile(r6.getPath());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002f A[ExcHandler: all (r1v6 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r0 
          PHI: (r0v4 'attachedDbs' java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) = (r0v2 'attachedDbs' java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v3 'attachedDbs' java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v3 'attachedDbs' java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) binds: [B:5:0x0029, B:10:0x0032, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0029] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCorruption(android.arch.persistence.db.SupportSQLiteDatabase r6) {
            /*
                r5 = this;
                java.lang.String r0 = "SupportSQLite"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Corruption reported by sqlite on database: "
                r1.append(r2)
                java.lang.String r2 = r6.getPath()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                android.util.Log.e(r0, r1)
                boolean r0 = r6.isOpen()
                if (r0 != 0) goto L_0x0028
                java.lang.String r0 = r6.getPath()
                r5.deleteDatabaseFile(r0)
                return
            L_0x0028:
                r0 = 0
                java.util.List r1 = r6.getAttachedDbs()     // Catch:{ SQLiteException -> 0x0031, all -> 0x002f }
                r0 = r1
                goto L_0x0032
            L_0x002f:
                r1 = move-exception
                goto L_0x0036
            L_0x0031:
                r1 = move-exception
            L_0x0032:
                r6.close()     // Catch:{ IOException -> 0x0058, all -> 0x002f }
                goto L_0x0059
            L_0x0036:
                if (r0 == 0) goto L_0x0050
                java.util.Iterator r2 = r0.iterator()
            L_0x003c:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0057
                java.lang.Object r3 = r2.next()
                android.util.Pair r3 = (android.util.Pair) r3
                java.lang.Object r4 = r3.second
                java.lang.String r4 = (java.lang.String) r4
                r5.deleteDatabaseFile(r4)
                goto L_0x003c
            L_0x0050:
                java.lang.String r2 = r6.getPath()
                r5.deleteDatabaseFile(r2)
            L_0x0057:
                throw r1
            L_0x0058:
                r1 = move-exception
            L_0x0059:
                if (r0 == 0) goto L_0x0073
                java.util.Iterator r1 = r0.iterator()
            L_0x005f:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x007b
                java.lang.Object r2 = r1.next()
                android.util.Pair r2 = (android.util.Pair) r2
                java.lang.Object r3 = r2.second
                java.lang.String r3 = (java.lang.String) r3
                r5.deleteDatabaseFile(r3)
                goto L_0x005f
            L_0x0073:
                java.lang.String r1 = r6.getPath()
                r5.deleteDatabaseFile(r1)
            L_0x007b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.db.SupportSQLiteOpenHelper.Callback.onCorruption(android.arch.persistence.db.SupportSQLiteDatabase):void");
        }

        private void deleteDatabaseFile(String fileName) {
            if (!fileName.equalsIgnoreCase(":memory:") && fileName.trim().length() != 0) {
                Log.w(TAG, "deleting the database file: " + fileName);
                try {
                    if (Build.VERSION.SDK_INT >= 16) {
                        SQLiteDatabase.deleteDatabase(new File(fileName));
                        return;
                    }
                    try {
                        if (!new File(fileName).delete()) {
                            Log.e(TAG, "Could not delete the database file " + fileName);
                        }
                    } catch (Exception error) {
                        Log.e(TAG, "error while deleting corrupted database file", error);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "delete failed: ", e);
                }
            }
        }
    }

    public static class Configuration {
        @NonNull
        public final Callback callback;
        @NonNull
        public final Context context;
        @Nullable
        public final String name;

        Configuration(@NonNull Context context2, @Nullable String name2, @NonNull Callback callback2) {
            this.context = context2;
            this.name = name2;
            this.callback = callback2;
        }

        public static Builder builder(Context context2) {
            return new Builder(context2);
        }

        public static class Builder {
            Callback mCallback;
            Context mContext;
            String mName;

            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (this.mContext != null) {
                    return new Configuration(this.mContext, this.mName, this.mCallback);
                } else {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
            }

            Builder(@NonNull Context context) {
                this.mContext = context;
            }

            public Builder name(@Nullable String name) {
                this.mName = name;
                return this;
            }

            public Builder callback(@NonNull Callback callback) {
                this.mCallback = callback;
                return this;
            }
        }
    }
}

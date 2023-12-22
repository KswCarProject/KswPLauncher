package android.support.p001v4.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;

/* renamed from: android.support.v4.database.sqlite.SQLiteCursorCompat */
/* loaded from: classes.dex */
public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor cursor, boolean fillWindowForwardOnly) {
        if (Build.VERSION.SDK_INT >= 28) {
            cursor.setFillWindowForwardOnly(fillWindowForwardOnly);
        }
    }
}

package android.support.p001v4.p003os;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* renamed from: android.support.v4.os.UserManagerCompat */
/* loaded from: classes.dex */
public class UserManagerCompat {
    private UserManagerCompat() {
    }

    public static boolean isUserUnlocked(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
        }
        return true;
    }
}

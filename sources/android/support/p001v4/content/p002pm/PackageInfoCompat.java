package android.support.p001v4.content.p002pm;

import android.content.pm.PackageInfo;
import android.os.Build;

/* renamed from: android.support.v4.content.pm.PackageInfoCompat */
/* loaded from: classes.dex */
public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= 28) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    private PackageInfoCompat() {
    }
}

package skin.support.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes.dex */
public class SkinFileUtils {
    public static String getSkinDir(Context context) {
        File skinDir = new File(getCacheDir(context), SkinConstants.SKIN_DEPLOY_PATH);
        if (!skinDir.exists()) {
            skinDir.mkdirs();
        }
        return skinDir.getAbsolutePath();
    }

    private static String getCacheDir(Context context) {
        File cacheDir;
        if (Environment.getExternalStorageState().equals("mounted") && (cacheDir = context.getExternalCacheDir()) != null && (cacheDir.exists() || cacheDir.mkdirs())) {
            return cacheDir.getAbsolutePath();
        }
        return context.getCacheDir().getAbsolutePath();
    }

    public static boolean isFileExists(String path) {
        return !TextUtils.isEmpty(path) && new File(path).exists();
    }
}

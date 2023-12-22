package skin.support.utils;

import android.util.Log;
import com.ibm.icu.text.PluralRules;

/* loaded from: classes.dex */
public class Slog {
    public static boolean DEBUG = false;
    private static final String TAG = "skin-support";

    /* renamed from: i */
    public static void m3i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    /* renamed from: i */
    public static void m2i(String subtag, String msg) {
        if (DEBUG) {
            Log.i(TAG, subtag + PluralRules.KEYWORD_RULE_SEPARATOR + msg);
        }
    }

    /* renamed from: r */
    public static void m1r(String msg) {
        Log.i(TAG, msg);
    }

    /* renamed from: r */
    public static void m0r(String subtag, String msg) {
        Log.i(TAG, subtag + PluralRules.KEYWORD_RULE_SEPARATOR + msg);
    }
}

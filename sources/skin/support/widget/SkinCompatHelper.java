package skin.support.widget;

/* loaded from: classes.dex */
public abstract class SkinCompatHelper {
    public static final int INVALID_ID = 0;
    protected static final String SYSTEM_ID_PREFIX = "1";

    public abstract void applySkin();

    public static final int checkResourceId(int resId) {
        String hexResId = Integer.toHexString(resId);
        if (hexResId.startsWith("1")) {
            return 0;
        }
        return resId;
    }
}

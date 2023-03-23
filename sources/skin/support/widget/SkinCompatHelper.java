package skin.support.widget;

public abstract class SkinCompatHelper {
    public static final int INVALID_ID = 0;
    protected static final String SYSTEM_ID_PREFIX = "1";

    public abstract void applySkin();

    public static final int checkResourceId(int resId) {
        if (Integer.toHexString(resId).startsWith("1")) {
            return 0;
        }
        return resId;
    }
}

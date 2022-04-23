package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.TypedValue;

public class SkinCompatThemeUtils {
    static final int[] ACCELERATED_STATE_SET = {16843547};
    static final int[] ACTIVATED_STATE_SET = {16843518};
    private static final int[] APPCOMPAT_COLOR_ACCENT_ATTRS = {R.attr.colorAccent};
    private static final int[] APPCOMPAT_COLOR_PRIMARY_ATTRS = {R.attr.colorPrimary};
    private static final int[] APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = {R.attr.colorPrimaryDark};
    static final int[] CHECKED_STATE_SET = {16842912};
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] DRAG_CAN_ACCEPT_STATE_SET = {16843624};
    static final int[] DRAG_HOVERED_STATE_SET = {16843625};
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] ENABLED_STATE_SET = {16842910};
    static final int[] FOCUSED_STATE_SET = {16842908};
    static final int[] HOVERED_STATE_SET = {16843623};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] PRESSED_STATE_SET = {16842919};
    static final int[] SELECTED_STATE_SET = {16842913};
    private static final int[] TEMP_ARRAY = new int[1];
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    static final int[] WINDOW_FOCUSED_STATE_SET = {16842909};

    public static int getColorPrimaryResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_PRIMARY_ATTRS);
    }

    public static int getColorPrimaryDarkResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS);
    }

    public static int getColorAccentResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_ACCENT_ATTRS);
    }

    public static int getTextColorPrimaryResId(Context context) {
        return getResId(context, new int[]{16842806});
    }

    public static int getStatusBarColorResId(Context context) {
        return getResId(context, new int[]{16843857});
    }

    public static int getWindowBackgroundResId(Context context) {
        return getResId(context, new int[]{16842836});
    }

    private static int getResId(Context context, int[] attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs);
        int resId = a.getResourceId(0, 0);
        a.recycle();
        return resId;
    }

    public static int getThemeAttrColor(Context context, int attr) {
        int[] iArr = TEMP_ARRAY;
        iArr[0] = attr;
        TypedArray a = context.obtainStyledAttributes((AttributeSet) null, iArr);
        try {
            int resId = a.getResourceId(0, 0);
            if (resId != 0) {
                return SkinCompatResources.getColor(context, resId);
            }
            a.recycle();
            return 0;
        } finally {
            a.recycle();
        }
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int attr) {
        int[] iArr = TEMP_ARRAY;
        iArr[0] = attr;
        TypedArray a = context.obtainStyledAttributes((AttributeSet) null, iArr);
        try {
            int resId = a.getResourceId(0, 0);
            if (resId != 0) {
                return SkinCompatResources.getColorStateList(context, resId);
            }
            a.recycle();
            return null;
        } finally {
            a.recycle();
        }
    }

    public static int getDisabledThemeAttrColor(Context context, int attr) {
        ColorStateList csl = getThemeAttrColorStateList(context, attr);
        if (csl != null && csl.isStateful()) {
            return csl.getColorForState(DISABLED_STATE_SET, csl.getDefaultColor());
        }
        TypedValue tv = getTypedValue();
        context.getTheme().resolveAttribute(16842803, tv, true);
        return getThemeAttrColor(context, attr, tv.getFloat());
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = TL_TYPED_VALUE;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    static int getThemeAttrColor(Context context, int attr, float alpha) {
        int color = getThemeAttrColor(context, attr);
        return ColorUtils.setAlphaComponent(color, Math.round(((float) Color.alpha(color)) * alpha));
    }
}

package android.support.v7.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

public class DrawableUtils {
    public static final Rect INSETS_NONE = new Rect();
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
            }
        }
    }

    private DrawableUtils() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect getOpticalBounds(android.graphics.drawable.Drawable r11) {
        /*
            java.lang.Class<?> r0 = sInsetsClazz
            if (r0 == 0) goto L_0x0091
            android.graphics.drawable.Drawable r0 = android.support.v4.graphics.drawable.DrawableCompat.unwrap(r11)     // Catch:{ Exception -> 0x0089 }
            r11 = r0
            java.lang.Class r0 = r11.getClass()     // Catch:{ Exception -> 0x0089 }
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0089 }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch:{ Exception -> 0x0089 }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0089 }
            java.lang.Object r1 = r0.invoke(r11, r1)     // Catch:{ Exception -> 0x0089 }
            if (r1 == 0) goto L_0x0088
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ Exception -> 0x0089 }
            r3.<init>()     // Catch:{ Exception -> 0x0089 }
            java.lang.Class<?> r4 = sInsetsClazz     // Catch:{ Exception -> 0x0089 }
            java.lang.reflect.Field[] r4 = r4.getFields()     // Catch:{ Exception -> 0x0089 }
            int r5 = r4.length     // Catch:{ Exception -> 0x0089 }
            r6 = r2
        L_0x002b:
            if (r6 >= r5) goto L_0x0087
            r7 = r4[r6]     // Catch:{ Exception -> 0x0089 }
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x0089 }
            r9 = -1
            int r10 = r8.hashCode()     // Catch:{ Exception -> 0x0089 }
            switch(r10) {
                case -1383228885: goto L_0x005b;
                case 115029: goto L_0x0050;
                case 3317767: goto L_0x0046;
                case 108511772: goto L_0x003c;
                default: goto L_0x003b;
            }     // Catch:{ Exception -> 0x0089 }
        L_0x003b:
            goto L_0x0064
        L_0x003c:
            java.lang.String r10 = "right"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x0089 }
            if (r8 == 0) goto L_0x003b
            r9 = 2
            goto L_0x0064
        L_0x0046:
            java.lang.String r10 = "left"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x0089 }
            if (r8 == 0) goto L_0x003b
            r9 = r2
            goto L_0x0064
        L_0x0050:
            java.lang.String r10 = "top"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x0089 }
            if (r8 == 0) goto L_0x003b
            r9 = 1
            goto L_0x0064
        L_0x005b:
            java.lang.String r10 = "bottom"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x0089 }
            if (r8 == 0) goto L_0x003b
            r9 = 3
        L_0x0064:
            switch(r9) {
                case 0: goto L_0x007d;
                case 1: goto L_0x0076;
                case 2: goto L_0x006f;
                case 3: goto L_0x0068;
                default: goto L_0x0067;
            }     // Catch:{ Exception -> 0x0089 }
        L_0x0067:
            goto L_0x0084
        L_0x0068:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x0089 }
            r3.bottom = r8     // Catch:{ Exception -> 0x0089 }
            goto L_0x0084
        L_0x006f:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x0089 }
            r3.right = r8     // Catch:{ Exception -> 0x0089 }
            goto L_0x0084
        L_0x0076:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x0089 }
            r3.top = r8     // Catch:{ Exception -> 0x0089 }
            goto L_0x0084
        L_0x007d:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x0089 }
            r3.left = r8     // Catch:{ Exception -> 0x0089 }
        L_0x0084:
            int r6 = r6 + 1
            goto L_0x002b
        L_0x0087:
            return r3
        L_0x0088:
            goto L_0x0091
        L_0x0089:
            r0 = move-exception
            java.lang.String r1 = "DrawableUtils"
            java.lang.String r2 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r1, r2)
        L_0x0091:
            android.graphics.Rect r0 = INSETS_NONE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DrawableUtils.getOpticalBounds(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    static void fixDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    public static boolean canSafelyMutateDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState state = drawable.getConstantState();
            if (!(state instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable child : ((DrawableContainer.DrawableContainerState) state).getChildren()) {
                if (!canSafelyMutateDrawable(child)) {
                    return false;
                }
            }
            return true;
        } else if (drawable instanceof WrappedDrawable) {
            return canSafelyMutateDrawable(((WrappedDrawable) drawable).getWrappedDrawable());
        } else {
            if (drawable instanceof DrawableWrapper) {
                return canSafelyMutateDrawable(((DrawableWrapper) drawable).getWrappedDrawable());
            }
            if (drawable instanceof ScaleDrawable) {
                return canSafelyMutateDrawable(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        int[] originalState = drawable.getState();
        if (originalState == null || originalState.length == 0) {
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        } else {
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        drawable.setState(originalState);
    }

    public static PorterDuff.Mode parseTintMode(int value, PorterDuff.Mode defaultMode) {
        switch (value) {
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return defaultMode;
        }
    }
}

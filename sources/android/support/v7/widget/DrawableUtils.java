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
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
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

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079 A[Catch:{ Exception -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007a A[Catch:{ Exception -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081 A[Catch:{ Exception -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088 A[Catch:{ Exception -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f A[Catch:{ Exception -> 0x009b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect getOpticalBounds(android.graphics.drawable.Drawable r12) {
        /*
            java.lang.Class<?> r0 = sInsetsClazz
            if (r0 == 0) goto L_0x00a3
            android.graphics.drawable.Drawable r0 = android.support.v4.graphics.drawable.DrawableCompat.unwrap(r12)     // Catch:{ Exception -> 0x009b }
            r12 = r0
            java.lang.Class r0 = r12.getClass()     // Catch:{ Exception -> 0x009b }
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x009b }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch:{ Exception -> 0x009b }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x009b }
            java.lang.Object r1 = r0.invoke(r12, r1)     // Catch:{ Exception -> 0x009b }
            if (r1 == 0) goto L_0x009a
            android.graphics.Rect r3 = new android.graphics.Rect     // Catch:{ Exception -> 0x009b }
            r3.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.Class<?> r4 = sInsetsClazz     // Catch:{ Exception -> 0x009b }
            java.lang.reflect.Field[] r4 = r4.getFields()     // Catch:{ Exception -> 0x009b }
            int r5 = r4.length     // Catch:{ Exception -> 0x009b }
            r6 = r2
        L_0x002b:
            if (r6 >= r5) goto L_0x0099
            r7 = r4[r6]     // Catch:{ Exception -> 0x009b }
            java.lang.String r8 = r7.getName()     // Catch:{ Exception -> 0x009b }
            r9 = -1
            int r10 = r8.hashCode()     // Catch:{ Exception -> 0x009b }
            r11 = -1383228885(0xffffffffad8d9a2b, float:-1.6098308E-11)
            if (r10 == r11) goto L_0x006b
            r11 = 115029(0x1c155, float:1.6119E-40)
            if (r10 == r11) goto L_0x0061
            r11 = 3317767(0x32a007, float:4.649182E-39)
            if (r10 == r11) goto L_0x0057
            r11 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r10 == r11) goto L_0x004d
            goto L_0x0075
        L_0x004d:
            java.lang.String r10 = "right"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x0075
            r8 = 2
            goto L_0x0076
        L_0x0057:
            java.lang.String r10 = "left"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x0075
            r8 = r2
            goto L_0x0076
        L_0x0061:
            java.lang.String r10 = "top"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x0075
            r8 = 1
            goto L_0x0076
        L_0x006b:
            java.lang.String r10 = "bottom"
            boolean r8 = r8.equals(r10)     // Catch:{ Exception -> 0x009b }
            if (r8 == 0) goto L_0x0075
            r8 = 3
            goto L_0x0076
        L_0x0075:
            r8 = r9
        L_0x0076:
            switch(r8) {
                case 0: goto L_0x008f;
                case 1: goto L_0x0088;
                case 2: goto L_0x0081;
                case 3: goto L_0x007a;
                default: goto L_0x0079;
            }     // Catch:{ Exception -> 0x009b }
        L_0x0079:
            goto L_0x0096
        L_0x007a:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x009b }
            r3.bottom = r8     // Catch:{ Exception -> 0x009b }
            goto L_0x0096
        L_0x0081:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x009b }
            r3.right = r8     // Catch:{ Exception -> 0x009b }
            goto L_0x0096
        L_0x0088:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x009b }
            r3.top = r8     // Catch:{ Exception -> 0x009b }
            goto L_0x0096
        L_0x008f:
            int r8 = r7.getInt(r1)     // Catch:{ Exception -> 0x009b }
            r3.left = r8     // Catch:{ Exception -> 0x009b }
        L_0x0096:
            int r6 = r6 + 1
            goto L_0x002b
        L_0x0099:
            return r3
        L_0x009a:
            goto L_0x00a3
        L_0x009b:
            r0 = move-exception
            java.lang.String r1 = "DrawableUtils"
            java.lang.String r2 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r1, r2)
        L_0x00a3:
            android.graphics.Rect r0 = INSETS_NONE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DrawableUtils.getOpticalBounds(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    static void fixDrawable(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
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
        if (value == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (value == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (value == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (value) {
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

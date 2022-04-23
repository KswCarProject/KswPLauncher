package skin.support.utils;

import android.graphics.drawable.Drawable;
import java.lang.reflect.Method;

public final class SkinCompatVersionUtils {
    private static final String TAG = "SkinCompatUtils";
    private static Class<?> sV4DrawableWrapperClass;
    private static Method sV4DrawableWrapperGetM;
    private static Method sV4DrawableWrapperSetM;
    private static Class<?> sV4WrappedDrawableClass;
    private static Method sV4WrappedDrawableGetM;
    private static Method sV4WrappedDrawableSetM;

    static {
        try {
            sV4WrappedDrawableClass = Class.forName("android.support.v4.graphics.drawable.WrappedDrawable");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasWrappedDrawable = false");
            }
        }
        try {
            sV4DrawableWrapperClass = Class.forName("android.support.v4.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException e2) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasDrawableWrapper = false");
            }
        }
    }

    public static boolean hasV4WrappedDrawable() {
        return sV4WrappedDrawableClass != null;
    }

    public static boolean isV4WrappedDrawable(Drawable drawable) {
        Class<?> cls = sV4WrappedDrawableClass;
        return cls != null && cls.isAssignableFrom(drawable.getClass());
    }

    public static Drawable getV4WrappedDrawableWrappedDrawable(Drawable drawable) {
        Class<?> cls = sV4WrappedDrawableClass;
        if (cls != null) {
            if (sV4WrappedDrawableGetM == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("getWrappedDrawable", new Class[0]);
                    sV4WrappedDrawableGetM = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4WrappedDrawableWrappedDrawable No Such Method");
                    }
                }
            }
            Method method = sV4WrappedDrawableGetM;
            if (method != null) {
                try {
                    return (Drawable) method.invoke(drawable, new Object[0]);
                } catch (Exception e2) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4WrappedDrawableWrappedDrawable invoke error: " + e2);
                    }
                }
            }
        }
        return drawable;
    }

    public static void setV4WrappedDrawableWrappedDrawable(Drawable drawable, Drawable inner) {
        Class<?> cls = sV4WrappedDrawableClass;
        if (cls != null) {
            if (sV4WrappedDrawableSetM == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("setWrappedDrawable", new Class[]{Drawable.class});
                    sV4WrappedDrawableSetM = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4WrappedDrawableWrappedDrawable No Such Method");
                    }
                }
            }
            Method method = sV4WrappedDrawableSetM;
            if (method != null) {
                try {
                    method.invoke(drawable, new Object[]{inner});
                } catch (Exception e2) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4WrappedDrawableWrappedDrawable invoke error: " + e2);
                    }
                }
            }
        }
    }

    public static boolean hasV4DrawableWrapper() {
        return sV4DrawableWrapperClass != null;
    }

    public static boolean isV4DrawableWrapper(Drawable drawable) {
        Class<?> cls = sV4DrawableWrapperClass;
        return cls != null && cls.isAssignableFrom(drawable.getClass());
    }

    public static Drawable getV4DrawableWrapperWrappedDrawable(Drawable drawable) {
        Class<?> cls = sV4DrawableWrapperClass;
        if (cls != null) {
            if (sV4DrawableWrapperGetM == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("getWrappedDrawable", new Class[0]);
                    sV4DrawableWrapperGetM = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            Method method = sV4DrawableWrapperGetM;
            if (method != null) {
                try {
                    return (Drawable) method.invoke(drawable, new Object[0]);
                } catch (Exception e2) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4DrawableWrapperWrappedDrawable invoke error: " + e2);
                    }
                }
            }
        }
        return drawable;
    }

    public static void setV4DrawableWrapperWrappedDrawable(Drawable drawable, Drawable inner) {
        Class<?> cls = sV4DrawableWrapperClass;
        if (cls != null) {
            if (sV4DrawableWrapperSetM == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("setWrappedDrawable", new Class[]{Drawable.class});
                    sV4DrawableWrapperSetM = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            Method method = sV4DrawableWrapperSetM;
            if (method != null) {
                try {
                    method.invoke(drawable, new Object[]{inner});
                } catch (Exception e2) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4DrawableWrapperWrappedDrawable invoke error: " + e2);
                    }
                }
            }
        }
    }
}

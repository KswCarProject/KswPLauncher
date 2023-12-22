package butterknife;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.util.Property;
import android.view.View;
import butterknife.internal.ButterKnifeProcessor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class ButterKnife {
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;
    static final Map<Class<?>, Method> INJECTORS = new LinkedHashMap();
    static final Map<Class<?>, Method> RESETTERS = new LinkedHashMap();
    static final Method NO_OP = null;

    /* loaded from: classes.dex */
    public interface Action<T extends View> {
        void apply(T t, int i);
    }

    /* loaded from: classes.dex */
    public interface Setter<T extends View, V> {
        void set(T t, V v, int i);
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    /* loaded from: classes.dex */
    public enum Finder {
        VIEW { // from class: butterknife.ButterKnife.Finder.1
            @Override // butterknife.ButterKnife.Finder
            public View findOptionalView(Object source, int id) {
                return ((View) source).findViewById(id);
            }
        },
        ACTIVITY { // from class: butterknife.ButterKnife.Finder.2
            @Override // butterknife.ButterKnife.Finder
            public View findOptionalView(Object source, int id) {
                return ((Activity) source).findViewById(id);
            }
        },
        DIALOG { // from class: butterknife.ButterKnife.Finder.3
            @Override // butterknife.ButterKnife.Finder
            public View findOptionalView(Object source, int id) {
                return ((Dialog) source).findViewById(id);
            }
        };

        public abstract View findOptionalView(Object obj, int i);

        public static <T extends View> T[] arrayOf(T... views) {
            return views;
        }

        public static <T extends View> List<T> listOf(T... views) {
            return new ImmutableViewList(views);
        }

        public View findRequiredView(Object source, int id, String who) {
            View view = findOptionalView(source, id);
            if (view == null) {
                throw new IllegalStateException("Required view with id '" + id + "' for " + who + " was not found. If this view is optional add '@Optional' annotation.");
            }
            return view;
        }
    }

    public static void setDebug(boolean debug2) {
        debug = debug2;
    }

    public static void inject(Activity target) {
        inject(target, target, Finder.ACTIVITY);
    }

    public static void inject(View target) {
        inject(target, target, Finder.VIEW);
    }

    public static void inject(Dialog target) {
        inject(target, target, Finder.DIALOG);
    }

    public static void inject(Object target, Activity source) {
        inject(target, source, Finder.ACTIVITY);
    }

    public static void inject(Object target, View source) {
        inject(target, source, Finder.VIEW);
    }

    public static void inject(Object target, Dialog source) {
        inject(target, source, Finder.DIALOG);
    }

    public static void reset(Object target) {
        Class<?> targetClass = target.getClass();
        try {
            if (debug) {
                Log.d(TAG, "Looking up view injector for " + targetClass.getName());
            }
            Method reset = findResettersForClass(targetClass);
            if (reset != null) {
                reset.invoke(null, target);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            Throwable t = e2;
            if (t instanceof InvocationTargetException) {
                t = t.getCause();
            }
            throw new RuntimeException("Unable to reset views for " + target, t);
        }
    }

    static void inject(Object target, Object source, Finder finder) {
        Class<?> targetClass = target.getClass();
        try {
            if (debug) {
                Log.d(TAG, "Looking up view injector for " + targetClass.getName());
            }
            Method inject = findInjectorForClass(targetClass);
            if (inject != null) {
                inject.invoke(null, finder, target, source);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            Throwable t = e2;
            if (t instanceof InvocationTargetException) {
                t = t.getCause();
            }
            throw new RuntimeException("Unable to inject views for " + target, t);
        }
    }

    private static Method findInjectorForClass(Class<?> cls) throws NoSuchMethodException {
        Method inject;
        Method inject2 = INJECTORS.get(cls);
        if (inject2 != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in injector map.");
            }
            return inject2;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return NO_OP;
        }
        try {
            Class<?> injector = Class.forName(clsName + ButterKnifeProcessor.SUFFIX);
            inject = injector.getMethod("inject", Finder.class, cls, Object.class);
            if (debug) {
                Log.d(TAG, "HIT: Class loaded injection class.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            inject = findInjectorForClass(cls.getSuperclass());
        }
        INJECTORS.put(cls, inject);
        return inject;
    }

    private static Method findResettersForClass(Class<?> cls) throws NoSuchMethodException {
        Method inject;
        Method inject2 = RESETTERS.get(cls);
        if (inject2 != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in injector map.");
            }
            return inject2;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return NO_OP;
        }
        try {
            Class<?> injector = Class.forName(clsName + ButterKnifeProcessor.SUFFIX);
            inject = injector.getMethod("reset", cls);
            if (debug) {
                Log.d(TAG, "HIT: Class loaded injection class.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            inject = findResettersForClass(cls.getSuperclass());
        }
        RESETTERS.put(cls, inject);
        return inject;
    }

    public static <T extends View> void apply(List<T> list, Action<? super T> action) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            action.apply(list.get(i), i);
        }
    }

    public static <T extends View, V> void apply(List<T> list, Setter<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set(list.get(i), value, i);
        }
    }

    public static <T extends View, V> void apply(List<T> list, Property<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set(list.get(i), value);
        }
    }

    public static <T extends View> T findById(View view, int id) {
        return (T) view.findViewById(id);
    }

    public static <T extends View> T findById(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }
}

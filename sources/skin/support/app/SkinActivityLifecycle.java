package skin.support.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import skin.support.SkinCompatManager;
import skin.support.annotation.Skinable;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.utils.Slog;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "SkinActivityLifecycle";
    private static volatile SkinActivityLifecycle sInstance = null;
    /* access modifiers changed from: private */
    public WeakReference<Activity> mCurActivityRef;
    private WeakHashMap<Context, SkinCompatDelegate> mSkinDelegateMap;
    private WeakHashMap<Context, LazySkinObserver> mSkinObserverMap;

    public static SkinActivityLifecycle init(Application application) {
        if (sInstance == null) {
            synchronized (SkinActivityLifecycle.class) {
                if (sInstance == null) {
                    sInstance = new SkinActivityLifecycle(application);
                }
            }
        }
        return sInstance;
    }

    private SkinActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(this);
        installLayoutFactory(application);
        SkinCompatManager.getInstance().addObserver(getObserver(application));
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (isContextSkinEnable(activity)) {
            installLayoutFactory(activity);
            updateStatusBarColor(activity);
            updateWindowBackground(activity);
            if (activity instanceof SkinCompatSupportable) {
                ((SkinCompatSupportable) activity).applySkin();
            }
        }
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.mCurActivityRef = new WeakReference<>(activity);
        if (isContextSkinEnable(activity)) {
            LazySkinObserver observer = getObserver(activity);
            SkinCompatManager.getInstance().addObserver(observer);
            observer.updateSkinIfNeeded();
        }
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
        if (isContextSkinEnable(activity)) {
            SkinCompatManager.getInstance().deleteObserver(getObserver(activity));
            this.mSkinObserverMap.remove(activity);
            this.mSkinDelegateMap.remove(activity);
        }
    }

    private void installLayoutFactory(Context context) {
        try {
            LayoutInflaterCompat.setFactory(LayoutInflater.from(context), getSkinDelegate(context));
        } catch (Exception e) {
            Slog.i("SkinActivity", "A factory has already been set on this LayoutInflater");
        }
    }

    /* access modifiers changed from: private */
    public SkinCompatDelegate getSkinDelegate(Context context) {
        if (this.mSkinDelegateMap == null) {
            this.mSkinDelegateMap = new WeakHashMap<>();
        }
        SkinCompatDelegate mSkinDelegate = this.mSkinDelegateMap.get(context);
        if (mSkinDelegate != null) {
            return mSkinDelegate;
        }
        SkinCompatDelegate mSkinDelegate2 = SkinCompatDelegate.create(context);
        this.mSkinDelegateMap.put(context, mSkinDelegate2);
        return mSkinDelegate2;
    }

    private LazySkinObserver getObserver(Context context) {
        if (this.mSkinObserverMap == null) {
            this.mSkinObserverMap = new WeakHashMap<>();
        }
        LazySkinObserver observer = this.mSkinObserverMap.get(context);
        if (observer != null) {
            return observer;
        }
        LazySkinObserver observer2 = new LazySkinObserver(context);
        this.mSkinObserverMap.put(context, observer2);
        return observer2;
    }

    /* access modifiers changed from: private */
    public void updateStatusBarColor(Activity activity) {
        if (SkinCompatManager.getInstance().isSkinStatusBarColorEnable() && Build.VERSION.SDK_INT >= 21) {
            int statusBarColorResId = SkinCompatThemeUtils.getStatusBarColorResId(activity);
            int colorPrimaryDarkResId = SkinCompatThemeUtils.getColorPrimaryDarkResId(activity);
            if (SkinCompatHelper.checkResourceId(statusBarColorResId) != 0) {
                activity.getWindow().setStatusBarColor(SkinCompatResources.getColor(activity, statusBarColorResId));
            } else if (SkinCompatHelper.checkResourceId(colorPrimaryDarkResId) != 0) {
                activity.getWindow().setStatusBarColor(SkinCompatResources.getColor(activity, colorPrimaryDarkResId));
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateWindowBackground(Activity activity) {
        Drawable drawable;
        if (SkinCompatManager.getInstance().isSkinWindowBackgroundEnable()) {
            int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(activity);
            if (SkinCompatHelper.checkResourceId(windowBackgroundResId) != 0 && (drawable = SkinCompatResources.getDrawableCompat(activity, windowBackgroundResId)) != null) {
                activity.getWindow().setBackgroundDrawable(drawable);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isContextSkinEnable(Context context) {
        return SkinCompatManager.getInstance().isSkinAllActivityEnable() || context.getClass().getAnnotation(Skinable.class) != null || (context instanceof SkinCompatSupportable);
    }

    private class LazySkinObserver implements SkinObserver {
        private final Context mContext;
        private boolean mMarkNeedUpdate = false;

        LazySkinObserver(Context context) {
            this.mContext = context;
        }

        public void updateSkin(SkinObservable observable, Object o) {
            if (SkinActivityLifecycle.this.mCurActivityRef == null || this.mContext == SkinActivityLifecycle.this.mCurActivityRef.get() || !(this.mContext instanceof Activity)) {
                updateSkinForce();
            } else {
                this.mMarkNeedUpdate = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void updateSkinIfNeeded() {
            if (this.mMarkNeedUpdate) {
                updateSkinForce();
            }
        }

        /* access modifiers changed from: package-private */
        public void updateSkinForce() {
            if (Slog.DEBUG) {
                Slog.i(SkinActivityLifecycle.TAG, "Context: " + this.mContext + " updateSkinForce");
            }
            Context context = this.mContext;
            if (context != null) {
                if ((context instanceof Activity) && SkinActivityLifecycle.this.isContextSkinEnable(context)) {
                    SkinActivityLifecycle.this.updateStatusBarColor((Activity) this.mContext);
                    SkinActivityLifecycle.this.updateWindowBackground((Activity) this.mContext);
                }
                SkinActivityLifecycle.this.getSkinDelegate(this.mContext).applySkin();
                Context context2 = this.mContext;
                if (context2 instanceof SkinCompatSupportable) {
                    ((SkinCompatSupportable) context2).applySkin();
                }
                this.mMarkNeedUpdate = false;
            }
        }
    }
}

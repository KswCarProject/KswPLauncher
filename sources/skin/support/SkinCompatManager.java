package skin.support;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import skin.support.app.SkinActivityLifecycle;
import skin.support.app.SkinLayoutInflater;
import skin.support.content.res.SkinCompatResources;
import skin.support.load.SkinAssetsLoader;
import skin.support.load.SkinBuildInLoader;
import skin.support.load.SkinNoneLoader;
import skin.support.load.SkinPrefixBuildInLoader;
import skin.support.observe.SkinObservable;
import skin.support.utils.SkinPreference;

public class SkinCompatManager extends SkinObservable {
    public static final int SKIN_LOADER_STRATEGY_ASSETS = 0;
    public static final int SKIN_LOADER_STRATEGY_BUILD_IN = 1;
    public static final int SKIN_LOADER_STRATEGY_NONE = -1;
    public static final int SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN = 2;
    private static volatile SkinCompatManager sInstance;
    /* access modifiers changed from: private */
    public final Context mAppContext;
    private List<SkinLayoutInflater> mHookInflaters = new ArrayList();
    private List<SkinLayoutInflater> mInflaters = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mLoading = false;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private boolean mSkinAllActivityEnable = true;
    private boolean mSkinStatusBarColorEnable = false;
    private boolean mSkinWindowBackgroundColorEnable = true;
    private SparseArray<SkinLoaderStrategy> mStrategyMap = new SparseArray<>();

    public interface SkinLoaderListener {
        void onFailed(String str);

        void onStart();

        void onSuccess();
    }

    public interface SkinLoaderStrategy {
        ColorStateList getColor(Context context, String str, int i);

        ColorStateList getColorStateList(Context context, String str, int i);

        Drawable getDrawable(Context context, String str, int i);

        String getTargetResourceEntryName(Context context, String str, int i);

        int getType();

        String loadSkinInBackground(Context context, String str);
    }

    public static SkinCompatManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinCompatManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatManager(context);
                }
            }
        }
        SkinPreference.init(context);
        return sInstance;
    }

    public static SkinCompatManager getInstance() {
        return sInstance;
    }

    public static SkinCompatManager withoutActivity(Application application) {
        init(application);
        SkinActivityLifecycle.init(application);
        return sInstance;
    }

    private SkinCompatManager(Context context) {
        this.mAppContext = context.getApplicationContext();
        initLoaderStrategy();
    }

    private void initLoaderStrategy() {
        this.mStrategyMap.put(-1, new SkinNoneLoader());
        this.mStrategyMap.put(0, new SkinAssetsLoader());
        this.mStrategyMap.put(1, new SkinBuildInLoader());
        this.mStrategyMap.put(2, new SkinPrefixBuildInLoader());
    }

    public Context getContext() {
        return this.mAppContext;
    }

    public SkinCompatManager addStrategy(SkinLoaderStrategy strategy) {
        this.mStrategyMap.put(strategy.getType(), strategy);
        return this;
    }

    public SparseArray<SkinLoaderStrategy> getStrategies() {
        return this.mStrategyMap;
    }

    public SkinCompatManager addInflater(SkinLayoutInflater inflater) {
        this.mInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getInflaters() {
        return this.mInflaters;
    }

    public SkinCompatManager addHookInflater(SkinLayoutInflater inflater) {
        this.mHookInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getHookInflaters() {
        return this.mHookInflaters;
    }

    @Deprecated
    public String getCurSkinName() {
        return SkinPreference.getInstance().getSkinName();
    }

    public void restoreDefaultTheme() {
        loadSkin("", -1);
    }

    public SkinCompatManager setSkinAllActivityEnable(boolean enable) {
        this.mSkinAllActivityEnable = enable;
        return this;
    }

    public boolean isSkinAllActivityEnable() {
        return this.mSkinAllActivityEnable;
    }

    public SkinCompatManager setSkinStatusBarColorEnable(boolean enable) {
        this.mSkinStatusBarColorEnable = enable;
        return this;
    }

    public boolean isSkinStatusBarColorEnable() {
        return this.mSkinStatusBarColorEnable;
    }

    public SkinCompatManager setSkinWindowBackgroundEnable(boolean enable) {
        this.mSkinWindowBackgroundColorEnable = enable;
        return this;
    }

    public boolean isSkinWindowBackgroundEnable() {
        return this.mSkinWindowBackgroundColorEnable;
    }

    public AsyncTask loadSkin() {
        String skin2 = SkinPreference.getInstance().getSkinName();
        int strategy = SkinPreference.getInstance().getSkinStrategy();
        if (TextUtils.isEmpty(skin2) || strategy == -1) {
            return null;
        }
        return loadSkin(skin2, (SkinLoaderListener) null, strategy);
    }

    public AsyncTask loadSkin(SkinLoaderListener listener) {
        String skin2 = SkinPreference.getInstance().getSkinName();
        int strategy = SkinPreference.getInstance().getSkinStrategy();
        if (TextUtils.isEmpty(skin2) || strategy == -1) {
            return null;
        }
        return loadSkin(skin2, listener, strategy);
    }

    @Deprecated
    public AsyncTask loadSkin(String skinName) {
        return loadSkin(skinName, (SkinLoaderListener) null);
    }

    @Deprecated
    public AsyncTask loadSkin(String skinName, SkinLoaderListener listener) {
        return loadSkin(skinName, listener, 0);
    }

    public AsyncTask loadSkin(String skinName, int strategy) {
        return loadSkin(skinName, (SkinLoaderListener) null, strategy);
    }

    public AsyncTask loadSkin(String skinName, SkinLoaderListener listener, int strategy) {
        SkinLoaderStrategy loaderStrategy = this.mStrategyMap.get(strategy);
        if (loaderStrategy == null) {
            return null;
        }
        return new SkinLoadTask(listener, loaderStrategy).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{skinName});
    }

    private class SkinLoadTask extends AsyncTask<String, Void, String> {
        private final SkinLoaderListener mListener;
        private final SkinLoaderStrategy mStrategy;

        SkinLoadTask(SkinLoaderListener listener, SkinLoaderStrategy strategy) {
            this.mListener = listener;
            this.mStrategy = strategy;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            SkinLoaderListener skinLoaderListener = this.mListener;
            if (skinLoaderListener != null) {
                skinLoaderListener.onStart();
            }
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... params) {
            synchronized (SkinCompatManager.this.mLock) {
                while (SkinCompatManager.this.mLoading) {
                    try {
                        SkinCompatManager.this.mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                boolean unused = SkinCompatManager.this.mLoading = true;
            }
            try {
                if (params.length == 1) {
                    if (TextUtils.isEmpty(this.mStrategy.loadSkinInBackground(SkinCompatManager.this.mAppContext, params[0]))) {
                        SkinCompatResources.getInstance().reset(this.mStrategy);
                    }
                    return params[0];
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            SkinCompatResources.getInstance().reset();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String skinName) {
            synchronized (SkinCompatManager.this.mLock) {
                if (skinName != null) {
                    SkinPreference.getInstance().setSkinName(skinName).setSkinStrategy(this.mStrategy.getType()).commitEditor();
                    SkinCompatManager.this.notifyUpdateSkin();
                    SkinLoaderListener skinLoaderListener = this.mListener;
                    if (skinLoaderListener != null) {
                        skinLoaderListener.onSuccess();
                    }
                } else {
                    SkinPreference.getInstance().setSkinName("").setSkinStrategy(-1).commitEditor();
                    SkinLoaderListener skinLoaderListener2 = this.mListener;
                    if (skinLoaderListener2 != null) {
                        skinLoaderListener2.onFailed("皮肤资源获取失败");
                    }
                }
                boolean unused = SkinCompatManager.this.mLoading = false;
                SkinCompatManager.this.mLock.notifyAll();
            }
        }
    }

    public String getSkinPackageName(String skinPkgPath) {
        return this.mAppContext.getPackageManager().getPackageArchiveInfo(skinPkgPath, 1).packageName;
    }

    public Resources getSkinResources(String skinPkgPath) {
        try {
            PackageInfo packageInfo = this.mAppContext.getPackageManager().getPackageArchiveInfo(skinPkgPath, 0);
            packageInfo.applicationInfo.sourceDir = skinPkgPath;
            packageInfo.applicationInfo.publicSourceDir = skinPkgPath;
            Resources res = this.mAppContext.getPackageManager().getResourcesForApplication(packageInfo.applicationInfo);
            Resources superRes = this.mAppContext.getResources();
            return new Resources(res.getAssets(), superRes.getDisplayMetrics(), superRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

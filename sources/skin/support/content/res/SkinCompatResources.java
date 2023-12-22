package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.p004v7.app.AppCompatDelegate;
import android.support.p004v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.TypedValue;
import skin.support.SkinCompatManager;

/* loaded from: classes.dex */
public class SkinCompatResources {
    private static volatile SkinCompatResources sInstance;
    private Resources mResources;
    private SkinCompatManager.SkinLoaderStrategy mStrategy;
    private String mSkinPkgName = "";
    private String mSkinName = "";
    private boolean isDefaultSkin = true;

    private SkinCompatResources() {
    }

    public static SkinCompatResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatResources();
                }
            }
        }
        return sInstance;
    }

    public void reset() {
        reset(SkinCompatManager.getInstance().getStrategies().get(-1));
    }

    public void reset(SkinCompatManager.SkinLoaderStrategy strategy) {
        this.mResources = SkinCompatManager.getInstance().getContext().getResources();
        this.mSkinPkgName = "";
        this.mSkinName = "";
        this.mStrategy = strategy;
        this.isDefaultSkin = true;
        SkinCompatUserThemeManager.get().clearCaches();
        SkinCompatDrawableManager.get().clearCaches();
    }

    public void setupSkin(Resources resources, String pkgName, String skinName, SkinCompatManager.SkinLoaderStrategy strategy) {
        if (resources == null || TextUtils.isEmpty(pkgName) || TextUtils.isEmpty(skinName)) {
            reset(strategy);
            return;
        }
        this.mResources = resources;
        this.mSkinPkgName = pkgName;
        this.mSkinName = skinName;
        this.mStrategy = strategy;
        this.isDefaultSkin = false;
        SkinCompatUserThemeManager.get().clearCaches();
        SkinCompatDrawableManager.get().clearCaches();
    }

    public Resources getSkinResources() {
        return this.mResources;
    }

    public String getSkinPkgName() {
        return this.mSkinPkgName;
    }

    public boolean isDefaultSkin() {
        return this.isDefaultSkin;
    }

    @Deprecated
    public int getColor(int resId) {
        return getColor(SkinCompatManager.getInstance().getContext(), resId);
    }

    @Deprecated
    public Drawable getDrawable(int resId) {
        return getDrawable(SkinCompatManager.getInstance().getContext(), resId);
    }

    @Deprecated
    public ColorStateList getColorStateList(int resId) {
        return getColorStateList(SkinCompatManager.getInstance().getContext(), resId);
    }

    private int getTargetResId(Context context, int resId) {
        String resName = null;
        try {
            SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
            if (skinLoaderStrategy != null) {
                resName = skinLoaderStrategy.getTargetResourceEntryName(context, this.mSkinName, resId);
            }
            if (TextUtils.isEmpty(resName)) {
                resName = context.getResources().getResourceEntryName(resId);
            }
            String type = context.getResources().getResourceTypeName(resId);
            return this.mResources.getIdentifier(resName, type, this.mSkinPkgName);
        } catch (Exception e) {
            return 0;
        }
    }

    private int getSkinColor(Context context, int resId) {
        int targetResId;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList2 = SkinCompatUserThemeManager.get().getColorStateList(resId)) != null) {
            return colorStateList2.getDefaultColor();
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (colorStateList = skinLoaderStrategy.getColor(context, this.mSkinName, resId)) != null) {
            return colorStateList.getDefaultColor();
        }
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, resId)) != 0) {
            return this.mResources.getColor(targetResId);
        }
        return context.getResources().getColor(resId);
    }

    private ColorStateList getSkinColorStateList(Context context, int resId) {
        int targetResId;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList2 = SkinCompatUserThemeManager.get().getColorStateList(resId)) != null) {
            return colorStateList2;
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (colorStateList = skinLoaderStrategy.getColorStateList(context, this.mSkinName, resId)) != null) {
            return colorStateList;
        }
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, resId)) != 0) {
            return this.mResources.getColorStateList(targetResId);
        }
        return context.getResources().getColorStateList(resId);
    }

    private Drawable getSkinDrawable(Context context, int resId) {
        int targetResId;
        Drawable drawable;
        Drawable drawable2;
        ColorStateList colorStateList;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList = SkinCompatUserThemeManager.get().getColorStateList(resId)) != null) {
            return new ColorDrawable(colorStateList.getDefaultColor());
        }
        if (!SkinCompatUserThemeManager.get().isDrawableEmpty() && (drawable2 = SkinCompatUserThemeManager.get().getDrawable(resId)) != null) {
            return drawable2;
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (drawable = skinLoaderStrategy.getDrawable(context, this.mSkinName, resId)) != null) {
            return drawable;
        }
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, resId)) != 0) {
            return this.mResources.getDrawable(targetResId);
        }
        return context.getResources().getDrawable(resId);
    }

    private Drawable getSkinDrawableCompat(Context context, int resId) {
        Drawable drawable;
        Drawable drawable2;
        ColorStateList colorStateList;
        if (AppCompatDelegate.isCompatVectorFromResourcesEnabled()) {
            if (!this.isDefaultSkin) {
                try {
                    return SkinCompatDrawableManager.get().getDrawable(context, resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList = SkinCompatUserThemeManager.get().getColorStateList(resId)) != null) {
                return new ColorDrawable(colorStateList.getDefaultColor());
            }
            if (!SkinCompatUserThemeManager.get().isDrawableEmpty() && (drawable2 = SkinCompatUserThemeManager.get().getDrawable(resId)) != null) {
                return drawable2;
            }
            SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
            if (skinLoaderStrategy != null && (drawable = skinLoaderStrategy.getDrawable(context, this.mSkinName, resId)) != null) {
                return drawable;
            }
            return AppCompatResources.getDrawable(context, resId);
        }
        return getSkinDrawable(context, resId);
    }

    private XmlResourceParser getSkinXml(Context context, int resId) {
        int targetResId;
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, resId)) != 0) {
            return this.mResources.getXml(targetResId);
        }
        return context.getResources().getXml(resId);
    }

    private void getSkinValue(Context context, int resId, TypedValue outValue, boolean resolveRefs) {
        int targetResId;
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, resId)) != 0) {
            this.mResources.getValue(targetResId, outValue, resolveRefs);
        } else {
            context.getResources().getValue(resId, outValue, resolveRefs);
        }
    }

    public static int getColor(Context context, int resId) {
        return getInstance().getSkinColor(context, resId);
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        return getInstance().getSkinColorStateList(context, resId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return getInstance().getSkinDrawable(context, resId);
    }

    public static Drawable getDrawableCompat(Context context, int resId) {
        return getInstance().getSkinDrawableCompat(context, resId);
    }

    public static XmlResourceParser getXml(Context context, int resId) {
        return getInstance().getSkinXml(context, resId);
    }

    public static void getValue(Context context, int resId, TypedValue outValue, boolean resolveRefs) {
        getInstance().getSkinValue(context, resId, outValue, resolveRefs);
    }
}

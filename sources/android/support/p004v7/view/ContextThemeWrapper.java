package android.support.p004v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.p004v7.appcompat.C0365R;
import android.view.LayoutInflater;

/* renamed from: android.support.v7.view.ContextThemeWrapper */
/* loaded from: classes.dex */
public class ContextThemeWrapper extends ContextWrapper {
    private LayoutInflater mInflater;
    private Configuration mOverrideConfiguration;
    private Resources mResources;
    private Resources.Theme mTheme;
    private int mThemeResource;

    public ContextThemeWrapper() {
        super(null);
    }

    public ContextThemeWrapper(Context base, int themeResId) {
        super(base);
        this.mThemeResource = themeResId;
    }

    public ContextThemeWrapper(Context base, Resources.Theme theme) {
        super(base);
        this.mTheme = theme;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (this.mResources != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.mOverrideConfiguration != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.mOverrideConfiguration = new Configuration(overrideConfiguration);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return getResourcesInternal();
    }

    private Resources getResourcesInternal() {
        if (this.mResources == null) {
            if (this.mOverrideConfiguration == null) {
                this.mResources = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                Context resContext = createConfigurationContext(this.mOverrideConfiguration);
                this.mResources = resContext.getResources();
            }
        }
        return this.mResources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int resid) {
        if (this.mThemeResource != resid) {
            this.mThemeResource = resid;
            initializeTheme();
        }
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.mTheme;
        if (theme != null) {
            return theme;
        }
        if (this.mThemeResource == 0) {
            this.mThemeResource = C0365R.style.Theme_AppCompat_Light;
        }
        initializeTheme();
        return this.mTheme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        if ("layout_inflater".equals(name)) {
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.mInflater;
        }
        return getBaseContext().getSystemService(name);
    }

    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        theme.applyStyle(resid, true);
    }

    private void initializeTheme() {
        boolean first = this.mTheme == null;
        if (first) {
            this.mTheme = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.mTheme.setTo(theme);
            }
        }
        onApplyThemeResource(this.mTheme, this.mThemeResource, first);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }
}

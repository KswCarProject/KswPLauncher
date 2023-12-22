package skin.support.app;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.p001v4.view.LayoutInflaterCompat;
import android.support.p004v7.app.AppCompatActivity;
import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatHelper;

@Deprecated
/* loaded from: classes.dex */
public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {
    private SkinCompatDelegate mSkinDelegate;

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
        updateStatusBarColor();
        updateWindowBackground();
    }

    public SkinCompatDelegate getSkinDelegate() {
        if (this.mSkinDelegate == null) {
            this.mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return this.mSkinDelegate;
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    protected boolean skinStatusBarColorEnable() {
        return true;
    }

    protected void updateStatusBarColor() {
        if (skinStatusBarColorEnable() && Build.VERSION.SDK_INT >= 21) {
            int statusBarColorResId = SkinCompatThemeUtils.getStatusBarColorResId(this);
            int colorPrimaryDarkResId = SkinCompatThemeUtils.getColorPrimaryDarkResId(this);
            if (SkinCompatHelper.checkResourceId(statusBarColorResId) != 0) {
                getWindow().setStatusBarColor(SkinCompatResources.getColor(this, statusBarColorResId));
            } else if (SkinCompatHelper.checkResourceId(colorPrimaryDarkResId) != 0) {
                getWindow().setStatusBarColor(SkinCompatResources.getColor(this, colorPrimaryDarkResId));
            }
        }
    }

    protected void updateWindowBackground() {
        Drawable drawable;
        int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(this);
        if (SkinCompatHelper.checkResourceId(windowBackgroundResId) != 0 && (drawable = SkinCompatResources.getDrawableCompat(this, windowBackgroundResId)) != null) {
            getWindow().setBackgroundDrawable(drawable);
        }
    }

    @Override // skin.support.observe.SkinObserver
    public void updateSkin(SkinObservable observable, Object o) {
        updateStatusBarColor();
        updateWindowBackground();
        getSkinDelegate().applySkin();
    }
}

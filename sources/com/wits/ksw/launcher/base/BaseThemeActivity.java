package com.wits.ksw.launcher.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public abstract class BaseThemeActivity extends AppCompatActivity {
    private static final String TAG = ("KSWLauncher." + BaseThemeActivity.class.getSimpleName());

    /* access modifiers changed from: protected */
    public abstract void initAlsId7UI();

    /* access modifiers changed from: protected */
    public abstract void initAlsView();

    /* access modifiers changed from: protected */
    public abstract void initAudiView();

    /* access modifiers changed from: protected */
    public abstract void initBcUiView();

    /* access modifiers changed from: protected */
    public abstract void initBenzGSView();

    /* access modifiers changed from: protected */
    public abstract void initBenzMBUXView();

    /* access modifiers changed from: protected */
    public abstract void initBenzNTG5View();

    /* access modifiers changed from: protected */
    public abstract void initBmwEvoId6GS();

    /* access modifiers changed from: protected */
    public abstract void initBmwid5UiView();

    /* access modifiers changed from: protected */
    public abstract void initBmwid6UiView();

    /* access modifiers changed from: protected */
    public abstract void initBmwid7UiView();

    /* access modifiers changed from: protected */
    public abstract void initBwmID7Hicar();

    /* access modifiers changed from: protected */
    public abstract void initBwmNbt();

    /* access modifiers changed from: protected */
    public abstract void initCommonUIGSUG1024View();

    /* access modifiers changed from: protected */
    public abstract void initCommonUIGSUGView();

    /* access modifiers changed from: protected */
    public abstract void initGSUiView();

    /* access modifiers changed from: protected */
    public abstract void initLandRover();

    /* access modifiers changed from: protected */
    public abstract void initLexus();

    /* access modifiers changed from: protected */
    public abstract void initRomeo();

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (UiThemeUtils.isBenz_NTG6(this)) {
            initBcUiView();
        } else if (UiThemeUtils.isBMW_EVO_ID7(this)) {
            initBmwid7UiView();
        } else if (UiThemeUtils.isBMW_EVO_ID6(this)) {
            initBmwid6UiView();
        } else if (UiThemeUtils.isBMW_EVO_ID6_GS(this)) {
            initBmwEvoId6GS();
        } else if (UiThemeUtils.isBMW_EVO_ID5(this)) {
            initBmwid5UiView();
        } else if (UiThemeUtils.isCommon_UI_GS(this)) {
            initGSUiView();
        } else if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            initCommonUIGSUGView();
        } else if (UiThemeUtils.isBenz_MBUX(this)) {
            initBenzMBUXView();
        } else if (UiThemeUtils.isBenz_GS(this)) {
            initBenzGSView();
        } else if (UiThemeUtils.isAudi_MMI_4G(this)) {
            initAudiView();
        } else if (UiThemeUtils.isBenz_NTG5(this)) {
            initBenzNTG5View();
        } else if (UiThemeUtils.isALS_ID6(this)) {
            initAlsView();
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            initBwmNbt();
        } else if (UiThemeUtils.isLEXUS_UI(this)) {
            initLexus();
        } else if (UiThemeUtils.isROMEO_UI(this)) {
            initRomeo();
        } else if (UiThemeUtils.isLAND_ROVER(this)) {
            setActivityFull();
            initLandRover();
        } else if (UiThemeUtils.isCommon_UI_GS_UG_1024(this)) {
            initCommonUIGSUG1024View();
        } else if (!UiThemeUtils.isID7_ALS(this)) {
            initBmwid7UiView();
        } else if (ClientManager.getInstance().isAls6208Client()) {
            initAlsId7UI();
        } else {
            initBmwid7UiView();
        }
        super.onCreate(savedInstanceState);
    }

    public void setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public void setFull() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
        }
    }

    public void setActivityFull() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }
}

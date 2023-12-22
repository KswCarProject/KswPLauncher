package com.wits.ksw.launcher.base;

import android.os.Build;
import android.os.Bundle;
import android.support.p004v7.app.AppCompatActivity;
import android.support.p004v7.app.AppCompatDelegate;
import android.support.p004v7.app.SkinAppCompatDelegateImpl;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

/* loaded from: classes14.dex */
public abstract class BaseThemeActivity extends AppCompatActivity {
    private static final String TAG = "KswApplication." + BaseThemeActivity.class.getSimpleName();

    protected abstract void initAlsId7UI();

    protected abstract void initAlsId7UI_V2();

    protected abstract void initAlsId7UiView();

    protected abstract void initAlsView();

    protected abstract void initAudiMbi3View();

    protected abstract void initAudiMbi3ViewV2();

    protected abstract void initAudiMib3Ty();

    protected abstract void initAudiView();

    protected abstract void initAudi_mib3_FyUiView();

    protected abstract void initAudi_mib3_Fy_V2_UiView();

    protected abstract void initBcUiView();

    protected abstract void initBenzGSView();

    protected abstract void initBenzMBUXView();

    protected abstract void initBenzNTG5View();

    protected abstract void initBenz_MBUX_2021_KSW_View();

    protected abstract void initBenz_MBUX_2021_KSW_View_V2();

    protected abstract void initBenz_MBUX_2021_KSW_View_new();

    protected abstract void initBenz_MBUX_2021_View();

    protected abstract void initBenz_MBUX_2021_View2();

    protected abstract void initBenz_NTG6_FY_View();

    protected abstract void initBenz_NTG6_FY_View_new();

    protected abstract void initBmwEvoId6GS();

    protected abstract void initBmwId8GsUiView();

    protected abstract void initBmwId8PempUiView();

    protected abstract void initBmwId8UiView();

    protected abstract void initBmwid5UiView();

    protected abstract void initBmwid6CuspUiView();

    protected abstract void initBmwid6UiView();

    protected abstract void initBmwid7UiView();

    protected abstract void initBmwid7V2UiView();

    protected abstract void initBwmID7Hicar();

    protected abstract void initBwmNbt();

    protected abstract void initCommonUIGSUG1024View();

    protected abstract void initCommonUIGSUGView();

    protected abstract void initCommonUIKSWMBUX1024View();

    protected abstract void initGSUiView();

    protected abstract void initLandRover();

    protected abstract void initLexus();

    protected abstract void initLexusLs();

    protected abstract void initLexusLsDrag();

    protected abstract void initLexusLsDragV2();

    protected abstract void initRomeo();

    protected abstract void initRomeo_V2();

    protected abstract void initUIKSWID7View();

    protected abstract void initUI_NTG6_FY_ViewV2();

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        if (UiThemeUtils.isBenz_NTG6(this)) {
            initBcUiView();
        } else if (UiThemeUtils.isBMW_EVO_ID7(this)) {
            initBmwid7UiView();
        } else if (UiThemeUtils.isBMW_EVO_ID6(this)) {
            initBmwid6UiView();
        } else if (UiThemeUtils.isBMW_EVO_ID6_CUSP(this) && ClientManager.getInstance().isCUSP_210407()) {
            initBmwid6CuspUiView();
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
        } else if (UiThemeUtils.isBenz_MBUX_2021(this)) {
            initBenz_MBUX_2021_View2();
        } else if (UiThemeUtils.isBenz_MBUX_2021_KSW(this) || UiThemeUtils.isUI_MBUX_2021_KSW_1024(this)) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                initBenz_MBUX_2021_KSW_View();
            } else {
                initBenz_MBUX_2021_KSW_View_new();
            }
        } else if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this) || UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(this)) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                initBenz_MBUX_2021_KSW_View_V2();
            } else {
                initBenz_MBUX_2021_KSW_View_new();
            }
        } else if (UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                initBenz_NTG6_FY_View();
            } else {
                initBenz_NTG6_FY_View_new();
            }
        } else if (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                initUI_NTG6_FY_ViewV2();
            } else {
                initBenz_NTG6_FY_View_new();
            }
        } else if (UiThemeUtils.isBenz_GS(this)) {
            initBenzGSView();
        } else if (UiThemeUtils.isAudi_MMI_4G(this)) {
            initAudiView();
        } else if (UiThemeUtils.isAudi_mib3(this)) {
            initAudiMbi3View();
        } else if (UiThemeUtils.isUI_mib3_V2(this)) {
            initAudiMbi3ViewV2();
        } else if (UiThemeUtils.isBenz_NTG5(this)) {
            initBenzNTG5View();
        } else if (UiThemeUtils.isALS_ID6(this)) {
            initAlsView();
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            initBwmNbt();
        } else if (UiThemeUtils.isLEXUS_UI(this)) {
            initLexus();
        } else if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            initLexusLsDrag();
        } else if (UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            initLexusLsDragV2();
        } else if (UiThemeUtils.isROMEO_UI(this)) {
            initRomeo();
        } else if (UiThemeUtils.isROMEO_UI_V2(this)) {
            initRomeo_V2();
        } else if (UiThemeUtils.isLAND_ROVER(this)) {
            setActivityFull();
            initLandRover();
        } else if (UiThemeUtils.isCommon_UI_GS_UG_1024(this)) {
            initCommonUIGSUG1024View();
        } else if (UiThemeUtils.isUI_KSW_MBUX_1024(this)) {
            initCommonUIKSWMBUX1024View();
        } else if (UiThemeUtils.isID7_ALS(this)) {
            if (ClientManager.getInstance().isAls6208Client()) {
                initAlsId7UI();
            } else {
                initBmwid7UiView();
            }
        } else if (UiThemeUtils.isID7_ALS_V2(this)) {
            if (ClientManager.getInstance().isAls6208Client()) {
                initAlsId7UI_V2();
            } else {
                initBmwid7UiView();
            }
        } else if (UiThemeUtils.isALS_ID7_UI(this)) {
            initAlsId7UiView();
        } else if (UiThemeUtils.isAudi_mib3_FY(this) && ClientManager.getInstance().isAls6208Client()) {
            initAudi_mib3_FyUiView();
        } else if (UiThemeUtils.isAudi_mib3_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
            initAudi_mib3_Fy_V2_UiView();
        } else if (UiThemeUtils.isAudi_mib3_ty(this)) {
            initAudiMib3Ty();
        } else if (UiThemeUtils.isUI_KSW_ID7(this)) {
            initUIKSWID7View();
        } else if (UiThemeUtils.isBMW_ID8_UI(this)) {
            initBmwId8UiView();
        } else if (UiThemeUtils.isUI_GS_ID8(this)) {
            initBmwId8GsUiView();
        } else if (UiThemeUtils.isUI_PEMP_ID8(this)) {
            initBmwId8PempUiView();
        } else if (UiThemeUtils.isBMW_EVO_ID7_V2(this)) {
            initBmwid7V2UiView();
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

    public void setFullActivity(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= 1024;
            getWindow().setAttributes(lp);
            getWindow().addFlags(512);
            return;
        }
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        attr.flags &= -1025;
        getWindow().setAttributes(attr);
        getWindow().clearFlags(512);
    }

    @Override // android.support.p004v7.app.AppCompatActivity
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}

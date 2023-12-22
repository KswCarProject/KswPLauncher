package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.ActivityAlsId7AppsBinding;
import com.wits.ksw.databinding.ActivityId7AppsBinding;
import com.wits.ksw.databinding.ActivityId8AppsBinding;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;

/* loaded from: classes16.dex */
public final class AppsActivity extends BaseThemeActivity {
    private ActivityAlsId7AppsBinding alsBinding;
    private ActivityId7AppsBinding binding;
    private ActivityId8AppsBinding id8Binding;
    String screenFile = "";
    private Handler screenHandler = new Handler() { // from class: com.wits.ksw.launcher.view.AppsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 666:
                    AppsActivity appsActivity = AppsActivity.this;
                    appsActivity.screenFile = MainActivity.screenShotByShell(appsActivity);
                    Log.d("liuhao", AppsActivity.this.screenFile);
                    return;
                default:
                    return;
            }
        }
    };
    private AppViewModel viewModel;

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUIKSWID7View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid5UiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid6UiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid6CuspUiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwEvoId6GS() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid7UiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    private void initBenzMbux2021UiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id7_apps);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        if (width == 1024 || (width == 1280 && height == 720)) {
            this.binding.appGridView.setNumColumns(5);
        } else {
            this.binding.appGridView.setNumColumns(7);
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBcUiView() {
        initBenzMbux2021UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzGSView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initGSUiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUGView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzMBUXView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_new() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_V2() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View2() {
        initBenzMbux2021UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View() {
        initBenzMbux2021UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUI_NTG6_FY_ViewV2() {
        initBenzMbux2021UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View_new() {
        initBenzMbux2021UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3View() {
        initAudiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3ViewV2() {
        initAudiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzNTG5View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmNbt() {
        Log.d("zgy", "---initBwmNbt()--AAA--");
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexus() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLs() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDrag() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDragV2() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmID7Hicar() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo_V2() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUG1024View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIKSWMBUX1024View() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.alsBinding = (ActivityAlsId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_als_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.alsBinding.appGridView.setNumColumns(5);
            } else {
                this.alsBinding.appGridView.setNumColumns(6);
            }
        }
        this.alsBinding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI_V2() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.alsBinding = (ActivityAlsId7AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_als_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width = dm.widthPixels;
            if (width == 1024) {
                this.alsBinding.appGridView.setNumColumns(5);
            } else {
                this.alsBinding.appGridView.setNumColumns(8);
            }
        }
        this.alsBinding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLandRover() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_FyUiView() {
        initAudiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_Fy_V2_UiView() {
        initAudiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMib3Ty() {
        initAudiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8UiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        ActivityId8AppsBinding activityId8AppsBinding = (ActivityId8AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_apps);
        this.id8Binding = activityId8AppsBinding;
        activityId8AppsBinding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8PempUiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        ActivityId8AppsBinding activityId8AppsBinding = (ActivityId8AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_apps);
        this.id8Binding = activityId8AppsBinding;
        activityId8AppsBinding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8GsUiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.m59of(this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        ActivityId8AppsBinding activityId8AppsBinding = (ActivityId8AppsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_apps);
        this.id8Binding = activityId8AppsBinding;
        activityId8AppsBinding.setAppViewModel(this.viewModel);
        this.viewModel.refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid7V2UiView() {
        initBmwid7UiView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        int drawableId;
        super.onResume();
        Log.w("AppsActivity", "onresume");
        if (UiThemeUtils.isLAND_ROVER(this)) {
            Log.w("AppsActivity", "onresume isLAND_ROVER");
            setFullActivity(false);
        }
        if (Settings.System.getInt(getContentResolver(), "multi_window", -1) == 1) {
            Intent intent = new Intent("com.wits.on_multi_window_mode").putExtra("multi_window", false);
            sendBroadcastAsUser(intent, Process.myUserHandle());
        }
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            this.screenHandler.sendEmptyMessageDelayed(666, 1800L);
        }
        if (UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this)) {
            String skinName = ID8LauncherConstants.loadCurrentSkin();
            if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
                drawableId = C0899R.C0900drawable.ksw_id8_btn_selector_red;
            } else if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
                drawableId = C0899R.C0900drawable.ksw_id8_btn_selector_blue;
            } else {
                drawableId = C0899R.C0900drawable.ksw_id8_btn_selector_yellow;
            }
            this.id8Binding.appGridView.setSelector(drawableId);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            this.screenHandler.removeMessages(666);
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("AppsActivity", "onKeyDown: " + keyCode);
        ActivityId7AppsBinding activityId7AppsBinding = this.binding;
        if (activityId7AppsBinding != null) {
            if (keyCode == 20 || keyCode == 22) {
                return activityId7AppsBinding.appGridView.onKeyUp(22, event);
            }
            if (keyCode == 19 || keyCode == 21) {
                return activityId7AppsBinding.appGridView.onKeyUp(21, event);
            }
            if (keyCode == 66) {
                return activityId7AppsBinding.appGridView.onKeyUp(66, event);
            }
        }
        ActivityAlsId7AppsBinding activityAlsId7AppsBinding = this.alsBinding;
        if (activityAlsId7AppsBinding != null) {
            if (keyCode == 20 || keyCode == 22) {
                return activityAlsId7AppsBinding.appGridView.onKeyUp(22, event);
            }
            if (keyCode == 19 || keyCode == 21) {
                return activityAlsId7AppsBinding.appGridView.onKeyUp(21, event);
            }
            if (keyCode == 66) {
                return activityAlsId7AppsBinding.appGridView.onKeyUp(66, event);
            }
        }
        ActivityId8AppsBinding activityId8AppsBinding = this.id8Binding;
        if (activityId8AppsBinding != null) {
            if (keyCode == 20 || keyCode == 22) {
                return activityId8AppsBinding.appGridView.onKeyUp(22, event);
            }
            if (keyCode == 19 || keyCode == 21) {
                return activityId8AppsBinding.appGridView.onKeyUp(21, event);
            }
            if (keyCode == 66) {
                return activityId8AppsBinding.appGridView.onKeyUp(66, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

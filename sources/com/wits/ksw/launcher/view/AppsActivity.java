package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAlsId7AppsBinding;
import com.wits.ksw.databinding.ActivityId7AppsBinding;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public final class AppsActivity extends BaseThemeActivity {
    private ActivityAlsId7AppsBinding alsBinding;
    private ActivityId7AppsBinding binding;
    String screenFile = "";
    private Handler screenHandler = new Handler() {
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

    /* access modifiers changed from: protected */
    public void initBmwid5UiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwid6UiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwid6CuspUiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwEvoId6GS() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwid7UiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, R.layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            if (getResources().getDisplayMetrics().widthPixels == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.queryApps();
    }

    /* access modifiers changed from: protected */
    public void initBcUiView() {
        initBenzMbux2021UiView();
    }

    /* access modifiers changed from: protected */
    public void initBenzGSView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initGSUiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUGView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBenzMBUXView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBenz_MBUX_2021_View() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBenz_MBUX_2021_View2() {
        initBenzMbux2021UiView();
    }

    private void initBenzMbux2021UiView() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, R.layout.activity_id7_apps);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        if (width == 1024 || (width == 1280 && height == 720)) {
            this.binding.appGridView.setNumColumns(5);
        } else {
            this.binding.appGridView.setNumColumns(7);
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.queryApps();
    }

    /* access modifiers changed from: protected */
    public void initBenz_NTG6_FY_View() {
        initBenzMbux2021UiView();
    }

    /* access modifiers changed from: protected */
    public void initAudiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initAudiMbi3View() {
        initAudiView();
    }

    /* access modifiers changed from: protected */
    public void initBenzNTG5View() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initAlsView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBwmNbt() {
        Log.d("zgy", "---initBwmNbt()--AAA--");
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initLexus() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initLexusLs() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, R.layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            if (getResources().getDisplayMetrics().widthPixels == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.queryApps();
    }

    /* access modifiers changed from: protected */
    public void initLexusLsDrag() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.binding = (ActivityId7AppsBinding) DataBindingUtil.setContentView(this, R.layout.activity_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            if (getResources().getDisplayMetrics().widthPixels == 1024) {
                this.binding.appGridView.setNumColumns(5);
            } else {
                this.binding.appGridView.setNumColumns(6);
            }
        }
        this.binding.setAppViewModel(this.viewModel);
        this.viewModel.queryApps();
    }

    /* access modifiers changed from: protected */
    public void initBwmID7Hicar() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initRomeo() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUG1024View() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UI() {
        AppViewModel appViewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel = appViewModel;
        appViewModel.setActivity(this);
        this.alsBinding = (ActivityAlsId7AppsBinding) DataBindingUtil.setContentView(this, R.layout.activity_als_id7_apps);
        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
            if (getResources().getDisplayMetrics().widthPixels == 1024) {
                this.alsBinding.appGridView.setNumColumns(5);
            } else {
                this.alsBinding.appGridView.setNumColumns(6);
            }
        }
        this.alsBinding.setAppViewModel(this.viewModel);
        this.viewModel.queryApps();
    }

    /* access modifiers changed from: protected */
    public void initLandRover() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initAudi_mib3_FyUiView() {
        initAudiView();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.w("AppsActivity", "onresume");
        if (UiThemeUtils.isLAND_ROVER(this)) {
            Log.w("AppsActivity", "onresume isLAND_ROVER");
            setFullActivity(false);
        }
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            this.screenHandler.sendEmptyMessageDelayed(666, 1800);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            this.screenHandler.removeMessages(666);
        }
    }

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
        return super.onKeyDown(keyCode, event);
    }
}

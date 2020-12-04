package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityId7AppsBinding;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;

public final class AppsActivity extends BaseThemeActivity {
    private ActivityId7AppsBinding binding;
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
    public void initBmwEvoId6GS() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwid7UiView() {
        this.viewModel = (AppViewModel) ViewModelProviders.of((FragmentActivity) this).get(AppViewModel.class);
        this.viewModel.setActivity(this);
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
        initBmwid7UiView();
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
    public void initAudiView() {
        initBmwid7UiView();
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("AppsActivity", "onKeyDown: " + keyCode);
        if (keyCode == 20 || keyCode == 22) {
            return this.binding.appGridView.onKeyUp(22, event);
        }
        if (keyCode == 19 || keyCode == 21) {
            return this.binding.appGridView.onKeyUp(21, event);
        }
        if (keyCode == 66) {
            return this.binding.appGridView.onKeyUp(66, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}

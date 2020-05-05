package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ALSDasoardBind;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBinding;
import com.wits.ksw.databinding.DasoardBind;
import com.wits.ksw.databinding.SevenDasoardBind;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.utils.KeyUtils;

public class DashboardActivity extends BaseThemeActivity {
    private static final String TAG = "ID7仪表盘";
    /* access modifiers changed from: private */
    public DashboardViewModel viewMode;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.viewMode = (DashboardViewModel) ViewModelProviders.of((FragmentActivity) this).get(DashboardViewModel.class);
        this.viewMode.initData();
        KeyUtils.pressKey(391);
        super.onCreate(savedInstanceState);
    }

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
        initBmwid6UiView();
    }

    /* access modifiers changed from: protected */
    public void initBmwid7UiView() {
        setActivityFull();
        if (this.viewMode.isAlsModel()) {
            initAlsDashboard();
        } else if (this.viewMode.isSevenModel()) {
            initSevenDasoard();
        } else {
            ((DasoardBind) DataBindingUtil.setContentView(this, R.layout.activity_dash_board)).setViewModel(this.viewMode);
        }
    }

    /* access modifiers changed from: protected */
    public void initBcUiView() {
        if (this.viewMode.isAlsModel()) {
            setActivityFull();
            initAlsDashboard();
        } else if (this.viewMode.isSevenModel()) {
            setActivityFull();
            initSevenDasoard();
        } else {
            setFull();
            ((ActivityNtg6DashBoardBinding) DataBindingUtil.setContentView(this, R.layout.activity_ntg6_dash_board)).setViewModel(this.viewMode);
        }
        this.viewMode.hideOil.set(true);
    }

    /* access modifiers changed from: protected */
    public void initBenzGSView() {
        initBcUiView();
    }

    private void initSevenDasoard() {
        SevenDasoardBind binding = (SevenDasoardBind) DataBindingUtil.setContentView(this, R.layout.activity_dash_board_seven);
        DashboardViewModel dashboardViewModel = this.viewMode;
        DashboardViewModel.carInfo.sevenmode.set(this.viewMode.getSevenModel());
        binding.setViewModel(this.viewMode);
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    DashboardActivity.this.viewMode.showSevenMenu.set(event.getX() < 252.0f);
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initGSUiView() {
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUGView() {
        initBmwid6UiView();
    }

    /* access modifiers changed from: protected */
    public void initBenzMBUXView() {
        initBcUiView();
    }

    /* access modifiers changed from: protected */
    public void initAudiView() {
        setActivityFull();
        initSevenDasoard();
        this.viewMode.hideOil.set(false);
    }

    /* access modifiers changed from: protected */
    public void initBenzNTG5View() {
        setActivityFull();
        initBcUiView();
    }

    private void initAlsDashboard() {
        final ALSDasoardBind binding = (ALSDasoardBind) DataBindingUtil.setContentView(this, R.layout.activity_dash_board_als);
        this.viewMode.setAlsModel(this.viewMode.getAlsModel());
        binding.setViewModel(this.viewMode);
        if (this.viewMode.getAlsModel() == 0) {
            binding.alsRadioGroup.check(binding.alsRadioButton1.getId());
        } else if (this.viewMode.getAlsModel() == 1) {
            binding.alsRadioGroup.check(binding.alsRadioButton2.getId());
        } else if (this.viewMode.getAlsModel() == 2) {
            binding.alsRadioGroup.check(binding.alsRadioButton3.getId());
        }
        binding.alsCloseView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DashboardActivity.this.viewMode.showAls.set(false);
            }
        });
        binding.alsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.alsRadioButton1.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(0);
                } else if (checkedId == binding.alsRadioButton2.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(1);
                } else if (checkedId == binding.alsRadioButton3.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(2);
                }
                DashboardActivity.this.viewMode.showAls.set(false);
            }
        });
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0 && DashboardActivity.this.viewMode != null && DashboardActivity.this.viewMode.isAlsModel()) {
                    DashboardActivity.this.viewMode.showAls.set(event.getX() < 398.0f);
                }
                return false;
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 66) {
            if (this.viewMode != null && this.viewMode.isAlsModel()) {
                this.viewMode.showAls.set(true);
            } else if (this.viewMode.isSevenModel()) {
                this.viewMode.showSevenMenu.set(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.ALSDasoardBind;
import com.wits.ksw.databinding.ActivityDashBoardAudiBinding;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3Binding;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3FyBinding;
import com.wits.ksw.databinding.ActivityDashBoardLcBinding;
import com.wits.ksw.databinding.ActivityDashBoardLexusBinding;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBinding;
import com.wits.ksw.databinding.BmwId8DashboardLayoutNewBinding;
import com.wits.ksw.databinding.DasoardBind;
import com.wits.ksw.databinding.SevenDasoardBind;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes16.dex */
public class DashboardActivity extends BaseThemeActivity {
    private static final String TAG = DashboardActivity.class.getSimpleName();
    private int carManufacturer;
    private BmwId8DashboardLayoutNewBinding mBmwId8NewBinding;
    String screenFile = "";
    private Handler screenHandler = new Handler() { // from class: com.wits.ksw.launcher.view.DashboardActivity.6
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 666:
                    DashboardActivity dashboardActivity = DashboardActivity.this;
                    dashboardActivity.screenFile = MainActivity.screenShotByShell(dashboardActivity);
                    Log.d(DashboardActivity.TAG, DashboardActivity.this.screenFile);
                    return;
                default:
                    return;
            }
        }
    };
    private DashboardViewModel viewMode;

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        Log.d(TAG, "onCreate: width = " + width + "height=" + height);
        DashboardViewModel dashboardViewModel = (DashboardViewModel) ViewModelProviders.m59of(this).get(DashboardViewModel.class);
        this.viewMode = dashboardViewModel;
        dashboardViewModel.initData();
        initCarManufacturer();
        KeyUtils.pressKey(391);
        super.onCreate(savedInstanceState);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUIKSWID7View() {
        initBmwid7UiView();
    }

    private void initCarManufacturer() {
        this.carManufacturer = 0;
        try {
            this.carManufacturer = PowerManagerApp.getSettingsInt(KeyConfig.CarManufacturer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        int i = this.carManufacturer;
        if (i == 0 || i > 3) {
            this.carManufacturer = UiThemeUtils.getCarManufacturer(this);
        }
        Log.d(TAG, "initCarManufacturer: " + this.carManufacturer);
        int i2 = this.carManufacturer;
        if (i2 != 2 && i2 != 3) {
            this.viewMode.hideOil.set(false);
        } else {
            this.viewMode.hideOil.set(true);
        }
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
        initBmwid6UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid7UiView() {
        initUI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBcUiView() {
        initUI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzGSView() {
        initBcUiView();
    }

    private void initSevenDashBoard() {
        SevenDasoardBind binding = (SevenDasoardBind) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_seven);
        DashboardViewModel.carInfo.sevenmode.set(this.viewMode.getSevenModel());
        binding.setViewModel(this.viewMode);
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.launcher.view.DashboardActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    DashboardActivity.this.viewMode.showSevenMenu.set(event.getX() < 252.0f);
                }
                return false;
            }
        });
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initGSUiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUGView() {
        initBmwid6UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzMBUXView() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_new() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_V2() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View2() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUI_NTG6_FY_ViewV2() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View_new() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiView() {
        initUI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3View() {
        initAudiMib3UI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3ViewV2() {
        initAudiMib3UI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzNTG5View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmNbt() {
        Log.d("zgy", "---initBwmNbt()--BBB--");
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexus() {
        ActivityDashBoardLexusBinding binding = (ActivityDashBoardLexusBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_lexus);
        binding.setViewModel(this.viewMode);
        AnimationDrawable animationDrawableL = (AnimationDrawable) binding.lexusIvLeft.getBackground();
        AnimationDrawable animationDrawableR = (AnimationDrawable) binding.lexusIvRight.getBackground();
        if (!animationDrawableL.isRunning()) {
            animationDrawableL.setOneShot(true);
            animationDrawableL.start();
        }
        if (!animationDrawableR.isRunning()) {
            animationDrawableR.setOneShot(true);
            animationDrawableR.start();
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLs() {
        initLexus();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDrag() {
        initLexus();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDragV2() {
        initLexus();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmID7Hicar() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo() {
        initSevenDashBoard();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo_V2() {
        initSevenDashBoard();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUG1024View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIKSWMBUX1024View() {
        initBcUiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI_V2() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLandRover() {
        initBmwid7UiView();
        View tempAndoil = findViewById(C0899R.C0901id.constraintLayout);
        if (tempAndoil != null) {
            tempAndoil.setVisibility(8);
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UiView() {
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_FyUiView() {
        initAudiMib3FyUI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_Fy_V2_UiView() {
        initAudiMib3FyUI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMib3Ty() {
        initAudiMib3UI();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8UiView() {
        setActivityFull();
        BmwId8DashboardLayoutNewBinding bmwId8DashboardLayoutNewBinding = (BmwId8DashboardLayoutNewBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_dashboard_layout_new);
        this.mBmwId8NewBinding = bmwId8DashboardLayoutNewBinding;
        bmwId8DashboardLayoutNewBinding.setViewModel(this.viewMode);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8GsUiView() {
        setActivityFull();
        BmwId8DashboardLayoutNewBinding bmwId8DashboardLayoutNewBinding = (BmwId8DashboardLayoutNewBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_dashboard_layout_new);
        this.mBmwId8NewBinding = bmwId8DashboardLayoutNewBinding;
        bmwId8DashboardLayoutNewBinding.setViewModel(this.viewMode);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8PempUiView() {
        setActivityFull();
        BmwId8DashboardLayoutNewBinding bmwId8DashboardLayoutNewBinding = (BmwId8DashboardLayoutNewBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_dashboard_layout_new);
        this.mBmwId8NewBinding = bmwId8DashboardLayoutNewBinding;
        bmwId8DashboardLayoutNewBinding.setViewModel(this.viewMode);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid7V2UiView() {
        initBmwid7UiView();
    }

    private void initAudiMib3FyUI() {
        if (this.viewMode.isSevenModel()) {
            setActivityFull();
            initSevenDashBoard();
        } else if (this.viewMode.isLCModel()) {
            setFull();
            ActivityDashBoardLcBinding binding = (ActivityDashBoardLcBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_lc);
            binding.setViewModel(this.viewMode);
        } else {
            setFull();
            initAudiMib3FyDashBoard();
        }
    }

    private void initAudiMib3FyDashBoard() {
        final ActivityDashBoardAudiMib3FyBinding binding = (ActivityDashBoardAudiMib3FyBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_audi_mib3_fy);
        DashboardViewModel dashboardViewModel = this.viewMode;
        dashboardViewModel.setAlsModel(dashboardViewModel.getAlsModel());
        binding.setViewModel(this.viewMode);
        if (this.viewMode.getAlsModel() == 0) {
            binding.modeRg.check(binding.energyConservationRb.getId());
            setAudiMib3FyRes(0, binding);
        } else if (this.viewMode.getAlsModel() == 1) {
            binding.modeRg.check(binding.comfortableRb.getId());
            setAudiMib3FyRes(1, binding);
        } else if (this.viewMode.getAlsModel() == 2) {
            binding.modeRg.check(binding.motionRb.getId());
            setAudiMib3FyRes(2, binding);
        }
        binding.showModeIv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$DashboardActivity$Bi0r8X10Riu0JqVRyOMuUdbrCmw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.this.lambda$initAudiMib3FyDashBoard$0$DashboardActivity(view);
            }
        });
        binding.modeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.view.DashboardActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.energyConservationRb.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(0);
                    DashboardActivity.this.setAudiMib3FyRes(0, binding);
                } else if (checkedId == binding.comfortableRb.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(1);
                    DashboardActivity.this.setAudiMib3FyRes(1, binding);
                } else if (checkedId == binding.motionRb.getId()) {
                    DashboardActivity.this.viewMode.setAlsModel(2);
                    DashboardActivity.this.setAudiMib3FyRes(2, binding);
                }
                DashboardActivity.this.viewMode.showAls.set(false);
            }
        });
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.launcher.view.DashboardActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0 && DashboardActivity.this.viewMode != null && DashboardActivity.this.viewMode.isAlsModel() && DashboardActivity.this.viewMode.showAls.get()) {
                    DashboardActivity.this.viewMode.showAls.set(false);
                }
                return false;
            }
        });
    }

    public /* synthetic */ void lambda$initAudiMib3FyDashBoard$0$DashboardActivity(View v) {
        this.viewMode.showAls.set(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudiMib3FyRes(int mode, ActivityDashBoardAudiMib3FyBinding binding) {
        if (binding != null) {
            switch (mode) {
                case 0:
                    binding.linearLayout2.setBackgroundResource(C0899R.C0900drawable.ec_audi_mib3_dashboard_bk);
                    binding.speedometerImageView.setImageResource(C0899R.C0900drawable.ec_audi_mib3_fy_speed_wtach_level);
                    binding.tachometerImageView.setImageResource(C0899R.C0900drawable.ec_audi_mib3_dashboard_8);
                    return;
                case 1:
                    binding.linearLayout2.setBackgroundResource(C0899R.C0900drawable.comfortable_audi_mib3_dashboard_bk);
                    binding.speedometerImageView.setImageResource(C0899R.C0900drawable.comfortable_audi_mib3_fy_speed_wtach_level);
                    binding.tachometerImageView.setImageResource(C0899R.C0900drawable.comfortable_audi_mib3_dashboard_8);
                    return;
                case 2:
                    binding.linearLayout2.setBackgroundResource(C0899R.C0900drawable.motion_audi_mib3_dashboard_bk);
                    binding.speedometerImageView.setImageResource(C0899R.C0900drawable.motion_audi_mib3_fy_speed_wtach_level);
                    binding.tachometerImageView.setImageResource(C0899R.C0900drawable.motion_audi_mib3_dashboard_8);
                    return;
                default:
                    return;
            }
        }
    }

    private void initAlsDashboard() {
        final ALSDasoardBind binding = (ALSDasoardBind) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_als);
        DashboardViewModel dashboardViewModel = this.viewMode;
        dashboardViewModel.setAlsModel(dashboardViewModel.getAlsModel());
        binding.setViewModel(this.viewMode);
        if (this.viewMode.getAlsModel() == 0) {
            binding.alsRadioGroup.check(binding.alsRadioButton1.getId());
        } else if (this.viewMode.getAlsModel() == 1) {
            binding.alsRadioGroup.check(binding.alsRadioButton2.getId());
        } else if (this.viewMode.getAlsModel() == 2) {
            binding.alsRadioGroup.check(binding.alsRadioButton3.getId());
        }
        binding.alsCloseView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$DashboardActivity$V64oXRIPCz5HLFOg660VSxTx0Hg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.this.lambda$initAlsDashboard$1$DashboardActivity(view);
            }
        });
        binding.alsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.view.DashboardActivity.4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
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
        binding.getRoot().setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.launcher.view.DashboardActivity.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0 && DashboardActivity.this.viewMode != null && DashboardActivity.this.viewMode.isAlsModel()) {
                    DashboardActivity.this.viewMode.showAls.set(event.getX() < 235.0f);
                }
                return false;
            }
        });
    }

    public /* synthetic */ void lambda$initAlsDashboard$1$DashboardActivity(View v) {
        this.viewMode.showAls.set(false);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 66) {
            DashboardViewModel dashboardViewModel = this.viewMode;
            if (dashboardViewModel != null && dashboardViewModel.isAlsModel()) {
                this.viewMode.showAls.set(true);
            } else if (this.viewMode.isSevenModel()) {
                this.viewMode.showSevenMenu.set(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initUI() {
        int i = this.carManufacturer;
        if (i == 1) {
            initBwmUI();
        } else if (i == 2) {
            initBzUI();
        } else if (i == 3) {
            initAudiUI();
        } else {
            initBwmUI();
        }
    }

    private void initAudiMib3UI() {
        if (this.viewMode.isSevenModel()) {
            setActivityFull();
            initSevenDashBoard();
        } else if (this.viewMode.isLCModel()) {
            setFull();
            ActivityDashBoardLcBinding binding = (ActivityDashBoardLcBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_lc);
            binding.setViewModel(this.viewMode);
        } else {
            setFull();
            setStatusBarTranslucent();
            ActivityDashBoardAudiMib3Binding binding2 = (ActivityDashBoardAudiMib3Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_audi_mib3);
            binding2.setViewModel(this.viewMode);
        }
    }

    private void initBwmUI() {
        setActivityFull();
        if (this.viewMode.isAlsModel()) {
            initAlsDashboard();
        } else if (this.viewMode.isSevenModel()) {
            initSevenDashBoard();
        } else {
            DasoardBind binding = (DasoardBind) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board);
            binding.setViewModel(this.viewMode);
        }
    }

    private void initBzUI() {
        if (this.viewMode.isAlsModel()) {
            setActivityFull();
            setFull();
            setStatusBarTranslucent();
            initAlsDashboard();
        } else if (this.viewMode.isSevenModel()) {
            setActivityFull();
            setFull();
            setStatusBarTranslucent();
            initSevenDashBoard();
        } else {
            setFull();
            setStatusBarTranslucent();
            ActivityNtg6DashBoardBinding ntg6DasoardBind = (ActivityNtg6DashBoardBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_ntg6_dash_board);
            ntg6DasoardBind.setViewModel(this.viewMode);
        }
        this.viewMode.hideOil.set(true);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
                this.screenHandler.sendEmptyMessageDelayed(666, 1800L);
            }
            if (UiThemeUtils.isLEXUS_UI(this)) {
                McuImpl.getInstance().getCarInfo().unitEnImg.set(Boolean.valueOf(KswUtils.ismph()));
            }
            if (UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this)) {
                BmwId8DashboardLayoutNewBinding bmwId8DashboardLayoutNewBinding = this.mBmwId8NewBinding;
                if (bmwId8DashboardLayoutNewBinding != null) {
                    bmwId8DashboardLayoutNewBinding.bmwId8DashboardLay.setBackground(SkinCompatResources.getDrawable(this, C0899R.C0900drawable.id8_dashboard_bg_new));
                    this.mBmwId8NewBinding.bmwId8DashboardMusicLay.bmwId8DashboardMusicName.setTextColor(SkinCompatResources.getColor(this, C0899R.color.id8_main_style_color));
                    this.mBmwId8NewBinding.bmwId8DashboardLeftBg.setBackground(SkinCompatResources.getDrawable(this, C0899R.C0900drawable.id8_dashboard_bg_l_new));
                    this.mBmwId8NewBinding.bmwId8DashboardRightBg.setBackground(SkinCompatResources.getDrawable(this, C0899R.C0900drawable.id8_dashboard_bg_r_new));
                }
                boolean isPlay = PowerManagerApp.getManager().getStatusBoolean("play");
                int lastMode = PowerManagerApp.getStatusInt("lastMode");
                Log.e(TAG, " isPlay " + isPlay + " lastMode " + lastMode);
                if (lastMode == 1 && isPlay) {
                    this.viewMode.dashBoardMusicShow.set(Boolean.valueOf(isPlay));
                }
            }
            if (KswUtils.ismph()) {
                McuImpl.getInstance().getCarInfo().speedUnit.set("mph");
            } else {
                McuImpl.getInstance().getCarInfo().speedUnit.set("km/h");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            this.screenHandler.removeMessages(666);
        }
    }

    private void initAudiUI() {
        if (this.viewMode.isSevenModel()) {
            setActivityFull();
            initSevenDashBoard();
        } else if (this.viewMode.isLCModel()) {
            setFull();
            ActivityDashBoardLcBinding binding = (ActivityDashBoardLcBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_lc);
            binding.setViewModel(this.viewMode);
        } else {
            setFull();
            ActivityDashBoardAudiBinding binding2 = (ActivityDashBoardAudiBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_dash_board_audi);
            binding2.setViewModel(this.viewMode);
        }
    }
}

package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsSystemLayoutBinding;
import com.wits.ksw.launcher.utils.AnimationUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemBrightnessLay;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemCameraLay;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemFuelLay;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemMusicLay;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemTempLay;
import com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemVideoLay;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsSystemActivity extends BaseSkinActivity implements View.OnClickListener {
    private final String TAG = "BmwId8SettingsSystemActivity";
    private int[] isSecondViewId = {R.id.bmw_id8_settings_system_camera, R.id.bmw_id8_settings_system_brightness, R.id.bmw_id8_settings_system_temp, R.id.bmw_id8_settings_system_fuel, R.id.bmw_id8_settings_system_music, R.id.bmw_id8_settings_system_video};
    /* access modifiers changed from: private */
    public BmwId8SettingsSystemLayoutBinding mBinding;
    private BmwId8SettingsSystemBrightnessLay mBrightnessLay;
    private BmwId8SettingsSystemCameraLay mCameraLay;
    private CountDownTimer mCountDownTimer;
    private BmwId8SettingsSystemFuelLay mFuelLay;
    private BmwId8SettingsSystemMusicLay mMusicLay;
    /* access modifiers changed from: private */
    public int mOffset = ScreenUtil.dip2px(4.0f);
    private BmwId8SettingsSystemTempLay mTempLay;
    private BmwId8SettingsSystemVideoLay mVideoLay;
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId = {R.id.bmw_id8_settings_system_mirror, R.id.bmw_id8_settings_system_motion, R.id.bmw_id8_settings_system_lines, R.id.bmw_id8_settings_system_radar, R.id.bmw_id8_settings_system_mute, R.id.bmw_id8_settings_system_camera, R.id.bmw_id8_settings_system_brightness, R.id.bmw_id8_settings_system_temp, R.id.bmw_id8_settings_system_fuel, R.id.bmw_id8_settings_system_music, R.id.bmw_id8_settings_system_video};
    /* access modifiers changed from: private */
    public int scrollActualHeight;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsSystemActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsSystemLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_system_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        initView();
    }

    private void initView() {
        try {
            this.mCameraLay = new BmwId8SettingsSystemCameraLay(this);
            this.mBrightnessLay = new BmwId8SettingsSystemBrightnessLay(this);
            this.mTempLay = new BmwId8SettingsSystemTempLay(this);
            this.mFuelLay = new BmwId8SettingsSystemFuelLay(this);
            this.mMusicLay = new BmwId8SettingsSystemMusicLay(this);
            this.mVideoLay = new BmwId8SettingsSystemVideoLay(this);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i >= iArr.length) {
                    break;
                }
                findViewById(iArr[i]).setOnClickListener(this);
                findViewById(this.relativeLayoutId[i]).setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.i("BmwId8SettingsSystemActivity", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                        if (event.getAction() != 1 || v.isFocused()) {
                            return false;
                        }
                        v.requestFocus();
                        return false;
                    }
                });
                i++;
            }
            boolean z = false;
            this.mViewModel.rearMirror.set(PowerManagerApp.getSettingsInt(KeyConfig.HOU_SHI_SX) != 0);
            this.mViewModel.disableVideo.set(PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP) != 0);
            this.mViewModel.parkLines.set(PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_GJ) != 0);
            this.mViewModel.parkRadar.set(PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_LD) != 0);
            ObservableBoolean observableBoolean = this.mViewModel.parkMute;
            if (PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_JY) != 0) {
                z = true;
            }
            observableBoolean.set(z);
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.i("BmwId8SettingsSystemActivity", "onGlobalFocusChanged: " + BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.hasFocus());
                    if (BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.hasFocus()) {
                        BmwId8SettingsSystemActivity.this.mViewModel.systemBgShow.set(true);
                    }
                    if (newFocus != null && BmwId8SettingsSystemActivity.this.isSecondId(newFocus.getId())) {
                        newFocus.setFocusableInTouchMode(true);
                    }
                    if (oldFocus != null && BmwId8SettingsSystemActivity.this.isSecondId(oldFocus.getId())) {
                        oldFocus.setFocusableInTouchMode(false);
                    }
                }
            });
            this.mViewModel.systemBgShow.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                public void onPropertyChanged(Observable sender, int propertyId) {
                    Log.i("BmwId8SettingsSystemActivity", " onPropertyChanged systemBgShow " + BmwId8SettingsSystemActivity.this.mViewModel.systemBgShow.get());
                    if (BmwId8SettingsSystemActivity.this.mViewModel.systemBgShow.get()) {
                        BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemRightArrow.clearAnimation();
                        AnimationUtils.playAnimation(BmwId8SettingsSystemActivity.this.getApplicationContext(), BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemLeftArrow, false);
                        return;
                    }
                    BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemLeftArrow.clearAnimation();
                    AnimationUtils.playAnimation(BmwId8SettingsSystemActivity.this.getApplicationContext(), BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemRightArrow, false);
                }
            });
            this.mBinding.bmwId8SettingsSystemScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    Log.i("BmwId8SettingsSystemActivity", " onScrollChange oldScrollY " + oldScrollY + " scrollY " + scrollY);
                    BmwId8SettingsSystemActivity.this.startCount();
                }
            });
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsSystemActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsSystemActivity", " onResume ");
        initData();
    }

    private void initData() {
        try {
            selectLayout((View) null, 0);
            this.mViewModel.systemBgShow.set(true);
            this.mBinding.bmwId8SettingsSystemScroll.scrollTo(0, 0);
            if (getCurrentFocus() == null) {
                this.mBinding.bmwId8SettingsSystemMirror.requestFocus();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmw_id8_settings_system_brightness /*2131296595*/:
                selectLayout(this.mBrightnessLay, v.getId());
                this.mBrightnessLay.requestFocus();
                return;
            case R.id.bmw_id8_settings_system_camera /*2131296596*/:
                selectLayout(this.mCameraLay, v.getId());
                this.mCameraLay.requestFocus();
                return;
            case R.id.bmw_id8_settings_system_fuel /*2131296602*/:
                selectLayout(this.mFuelLay, v.getId());
                this.mFuelLay.requestFocus();
                return;
            case R.id.bmw_id8_settings_system_lines /*2131296609*/:
                this.mViewModel.parkLines.set(!this.mViewModel.parkLines.get());
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, this.mViewModel.parkLines.get());
                selectLayout((View) null, 0);
                return;
            case R.id.bmw_id8_settings_system_mirror /*2131296610*/:
                this.mViewModel.rearMirror.set(!this.mViewModel.rearMirror.get());
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, this.mViewModel.rearMirror.get());
                selectLayout((View) null, 0);
                return;
            case R.id.bmw_id8_settings_system_motion /*2131296611*/:
                this.mViewModel.disableVideo.set(!this.mViewModel.disableVideo.get());
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, this.mViewModel.disableVideo.get());
                selectLayout((View) null, 0);
                return;
            case R.id.bmw_id8_settings_system_music /*2131296612*/:
                selectLayout(this.mMusicLay, R.id.bmw_id8_settings_system_music);
                this.mMusicLay.requestFocus();
                return;
            case R.id.bmw_id8_settings_system_mute /*2131296615*/:
                this.mViewModel.parkMute.set(!this.mViewModel.parkMute.get());
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, this.mViewModel.parkMute.get());
                selectLayout((View) null, 0);
                return;
            case R.id.bmw_id8_settings_system_radar /*2131296616*/:
                this.mViewModel.parkRadar.set(!this.mViewModel.parkRadar.get());
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, this.mViewModel.parkRadar.get());
                selectLayout((View) null, 0);
                return;
            case R.id.bmw_id8_settings_system_temp /*2131296619*/:
                selectLayout(this.mTempLay, v.getId());
                this.mTempLay.requestFocus();
                return;
            case R.id.bmw_id8_settings_system_video /*2131296623*/:
                selectLayout(this.mVideoLay, v.getId());
                this.mVideoLay.requestFocus();
                return;
            default:
                return;
        }
    }

    private void selectLayout(View view, int viewId) {
        try {
            Log.i("BmwId8SettingsSystemActivity", "selectLayout view " + view + " viewId " + viewId);
            this.mBinding.bmwId8SettingsSystemFramelay.removeAllViews();
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i >= iArr.length) {
                    break;
                }
                if (viewId == iArr[i]) {
                    findViewById(iArr[i]).setSelected(true);
                } else {
                    findViewById(iArr[i]).setSelected(false);
                }
                i++;
            }
            if (view != null) {
                this.mBinding.bmwId8SettingsSystemFramelay.addView(view);
                this.mViewModel.systemIconShow.set(false);
                return;
            }
            this.mViewModel.systemIconShow.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();
        View view = getCurrentFocus();
        Log.i("BmwId8SettingsSystemActivity", "dispatchKeyEvent keyCode " + keyCode + " action " + action);
        switch (action) {
            case 0:
                if (event.getKeyCode() == 21 && view != null && this.mBinding.bmwId8SettingsSystemScroll.hasFocus()) {
                    Log.i("BmwId8SettingsSystemActivity", "dispatchKeyEvent ACTION_DOWN focusView " + view.toString() + " view.isFocused() " + view.isFocused());
                    KswUtils.sendKeyDownUpSync(4);
                    return true;
                }
        }
        return super.dispatchKeyEvent(event);
    }

    /* access modifiers changed from: private */
    public boolean isSecondId(int viewId) {
        int i = 0;
        while (true) {
            try {
                int[] iArr = this.isSecondViewId;
                if (i >= iArr.length) {
                    return false;
                }
                if (viewId == iArr[i]) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public CountDownTimer getCountDownTimer() {
        try {
            if (this.mCountDownTimer == null) {
                this.mCountDownTimer = new CountDownTimer(100, 50) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        int scrolly = BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.getScrollY();
                        if (BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.hasFocus()) {
                            int focusIndex = ((LinearLayout) BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.getChildAt(0)).indexOfChild(BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.findFocus());
                            Log.e("BmwId8SettingsSystemActivity", "onFinish: scrolly " + scrolly + " focusIndex " + focusIndex);
                            if (focusIndex >= 0) {
                                int itemHeight = BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemMirror.getHeight() - BmwId8SettingsSystemActivity.this.mOffset;
                                int scrollHeight = BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.getHeight() + scrolly;
                                BmwId8SettingsSystemActivity bmwId8SettingsSystemActivity = BmwId8SettingsSystemActivity.this;
                                int unused = bmwId8SettingsSystemActivity.scrollActualHeight = ((LinearLayout) bmwId8SettingsSystemActivity.mBinding.bmwId8SettingsSystemScroll.getChildAt(0)).getBottom();
                                int focusViewHeight = (focusIndex + 1) * itemHeight;
                                int focusViewHeight2 = focusViewHeight > BmwId8SettingsSystemActivity.this.scrollActualHeight ? BmwId8SettingsSystemActivity.this.scrollActualHeight : focusViewHeight;
                                Log.e("BmwId8SettingsSystemActivity", "onFinish: focusViewHeight " + focusViewHeight2 + " itemHeight " + itemHeight + " scrollHeight " + scrollHeight);
                                if (focusViewHeight2 < scrolly || (focusViewHeight2 - itemHeight) + BmwId8SettingsSystemActivity.this.mOffset > scrollHeight) {
                                    int refresIndex = (int) Math.ceil((double) ((((float) scrolly) * 1.0f) / ((float) BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemMirror.getHeight())));
                                    Log.e("BmwId8SettingsSystemActivity", "onFinish: refresIndex " + refresIndex);
                                    View focusView = ((LinearLayout) BmwId8SettingsSystemActivity.this.mBinding.bmwId8SettingsSystemScroll.getChildAt(0)).getChildAt(refresIndex);
                                    focusView.setFocusableInTouchMode(true);
                                    focusView.requestFocus();
                                }
                            }
                        }
                    }
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mCountDownTimer;
    }

    public void startCount() {
        getCountDownTimer().cancel();
        getCountDownTimer().start();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i("BmwId8SettingsSystemActivity", " onPause ");
        getCountDownTimer().cancel();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsSystemActivity", " onStop ");
        getCountDownTimer().cancel();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsSystemActivity", " onDestroy ");
    }
}

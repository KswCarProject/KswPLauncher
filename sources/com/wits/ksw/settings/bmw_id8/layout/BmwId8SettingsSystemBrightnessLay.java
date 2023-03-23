package com.wits.ksw.settings.bmw_id8.layout;

import android.content.Context;
import android.database.ContentObserver;
import android.databinding.DataBindingUtil;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsSystemBrightnessLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import java.text.NumberFormat;

public class BmwId8SettingsSystemBrightnessLay extends RelativeLayout implements View.OnClickListener, ID8ProgressBar.OnValueChangeListener, ID8ProgressBar.OnTouchChangeListener {
    private final String TAG = "BmwId8SettingsSystemBrightnessLay";
    private int beiguangValue;
    /* access modifiers changed from: private */
    public Context context;
    private int[] imageButtonId = {R.id.bmw_id8_settings_brightness_sub_btn, R.id.bmw_id8_settings_brightness_add_btn};
    /* access modifiers changed from: private */
    public Handler mBackgroundHandler;
    /* access modifiers changed from: private */
    public BmwId8SettingsSystemBrightnessLayoutBinding mBinding;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness = 255;
    private int mMinBrightness = 10;
    /* access modifiers changed from: private */
    public final Runnable mUpdateSliderRunnable = new Runnable() {
        public void run() {
            int brightness = Settings.System.getInt(BmwId8SettingsSystemBrightnessLay.this.context.getContentResolver(), "screen_brightness", 255);
            BmwId8SettingsSystemBrightnessLay.this.setProgressText(brightness);
            BmwId8SettingsSystemBrightnessLay.this.setProgress(brightness);
        }
    };
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsSystemBrightnessLay(Context context2) {
        super(context2);
        this.context = context2;
        this.mBinding = (BmwId8SettingsSystemBrightnessLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context2), R.layout.bmw_id8_settings_system_brightness_layout, (ViewGroup) null, false);
        this.mBinding.getRoot().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        addView(this.mBinding.getRoot());
        initView();
        initData();
    }

    private void initView() {
        try {
            this.mBinding.bmwId8SettingsBrightnessSeekbar.setOnValueChangeListener(this);
            this.mBinding.bmwId8SettingsBrightnessSeekbar.setOnTouchChangeListener(this);
            int i = 0;
            while (true) {
                int[] iArr = this.imageButtonId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnClickListener(this);
                    findViewById(this.imageButtonId[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemBrightnessLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() != 1 || v.isFocused()) {
                                return false;
                            }
                            v.requestFocus();
                            return false;
                        }
                    });
                    i++;
                } else {
                    this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                            Log.i("BmwId8SettingsSystemBrightnessLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemBrightnessLay.this.mBinding.bmwId8SettingsBrightnessLay.hasFocus());
                            if (BmwId8SettingsSystemBrightnessLay.this.mBinding.bmwId8SettingsBrightnessLay.hasFocus()) {
                                BmwId8SettingsSystemBrightnessLay.this.mViewModel.systemBgShow.set(false);
                            }
                        }
                    });
                    return;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initData() {
        try {
            this.mBackgroundHandler = new Handler(Looper.getMainLooper());
            BrightnessObserver brightnessObserver = new BrightnessObserver(new Handler());
            this.mBrightnessObserver = brightnessObserver;
            brightnessObserver.startObserving();
            int i = Settings.System.getInt(this.context.getContentResolver(), "screen_brightness", 255);
            this.beiguangValue = i;
            setProgress(i);
            setProgressText(this.beiguangValue);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void setSystemBrightness(int brightness) {
        Log.i("BmwId8SettingsSystemBrightnessLay", " setSystemBrightness=" + brightness);
        Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", brightness);
    }

    public void setBrightnessValueBg(Context context2, int key) {
        try {
            Class.forName("android.hardware.display.DisplayManager").getMethod("setTemporaryBrightness", new Class[]{Integer.TYPE}).invoke((DisplayManager) context2.getSystemService("display"), new Object[]{Integer.valueOf(key)});
            Log.i("BmwId8SettingsSystemBrightnessLay", "setBrightnessValueBg: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmw_id8_settings_brightness_add_btn /*2131296549*/:
                this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(this.mBinding.bmwId8SettingsBrightnessSeekbar.getValue() + 1);
                return;
            case R.id.bmw_id8_settings_brightness_sub_btn /*2131296552*/:
                this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(this.mBinding.bmwId8SettingsBrightnessSeekbar.getValue() - 1);
                return;
            default:
                return;
        }
    }

    public void onValueChange(ID8ProgressBar progressBar, int value, float progress) {
        int temp = BrightnessUtils.convertGammaToLinearBmwId8(value, 10, 255);
        Log.i("BmwId8SettingsSystemBrightnessLay", " value " + temp);
        setBrightnessValueBg(this.context, temp);
        setSystemBrightness(temp);
    }

    public void onStartTrackingTouch(ID8ProgressBar progressBar) {
        this.mViewModel.systemBgShow.set(false);
        progressBar.requestFocus();
    }

    public void onStopTrackingTouch(ID8ProgressBar progressBar) {
    }

    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");

        public BrightnessObserver(Handler handler) {
            super(handler);
        }

        public void startObserving() {
            BmwId8SettingsSystemBrightnessLay.this.context.getContentResolver().unregisterContentObserver(this);
            BmwId8SettingsSystemBrightnessLay.this.context.getContentResolver().registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("BmwId8SettingsSystemBrightnessLay", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            BmwId8SettingsSystemBrightnessLay.this.context.getContentResolver().unregisterContentObserver(this);
            Log.i("BmwId8SettingsSystemBrightnessLay", "stopObserving: unregisterContentObserver");
        }

        public void onChange(boolean selfChange) {
            onChange(selfChange, (Uri) null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("BmwId8SettingsSystemBrightnessLay", "onChange: " + uri);
                BmwId8SettingsSystemBrightnessLay.this.mBackgroundHandler.post(BmwId8SettingsSystemBrightnessLay.this.mUpdateSliderRunnable);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGammaBmwId8(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, 100);
        Log.i("BmwId8SettingsSystemBrightnessLay", "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.mBinding.bmwId8SettingsBrightnessText.setText("" + ((int) Math.round(100.0d * b)));
    }

    /* access modifiers changed from: private */
    public void setProgress(int brightness) {
        int value = BrightnessUtils.convertLinearToGammaBmwId8(brightness, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, 100);
        Log.i("BmwId8SettingsSystemBrightnessLay", "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(value);
    }
}

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
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsSystemBrightnessLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import java.lang.reflect.Method;
import java.text.NumberFormat;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemBrightnessLay extends RelativeLayout implements View.OnClickListener, ID8ProgressBar.OnValueChangeListener, ID8ProgressBar.OnTouchChangeListener {
    private final String TAG;
    private int beiguangValue;
    private Context context;
    private int[] imageButtonId;
    private Handler mBackgroundHandler;
    private BmwId8SettingsSystemBrightnessLayoutBinding mBinding;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness;
    private int mMinBrightness;
    private final Runnable mUpdateSliderRunnable;
    private BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsSystemBrightnessLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemBrightnessLay";
        this.imageButtonId = new int[]{C0899R.C0901id.bmw_id8_settings_brightness_sub_btn, C0899R.C0901id.bmw_id8_settings_brightness_add_btn};
        this.mUpdateSliderRunnable = new Runnable() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemBrightnessLay.3
            @Override // java.lang.Runnable
            public void run() {
                int brightness = Settings.System.getInt(BmwId8SettingsSystemBrightnessLay.this.context.getContentResolver(), "screen_brightness", 255);
                BmwId8SettingsSystemBrightnessLay.this.setProgressText(brightness);
                BmwId8SettingsSystemBrightnessLay.this.setProgress(brightness);
            }
        };
        this.mMinBrightness = 10;
        this.mMaxBrightness = 255;
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemBrightnessLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_brightness_layout, null, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mBinding.getRoot().setLayoutParams(layoutParams);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
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
                    findViewById(this.imageButtonId[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemBrightnessLay.1
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemBrightnessLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() == 1 && !v.isFocused()) {
                                v.requestFocus();
                                return false;
                            }
                            return false;
                        }
                    });
                    i++;
                } else {
                    this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemBrightnessLay.2
                        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
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

    public void setBrightnessValueBg(Context context, int key) {
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Class<?> dmclass = Class.forName("android.hardware.display.DisplayManager");
            Method set = dmclass.getMethod("setTemporaryBrightness", Integer.TYPE);
            set.invoke(displayManager, Integer.valueOf(key));
            Log.i("BmwId8SettingsSystemBrightnessLay", "setBrightnessValueBg: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.bmw_id8_settings_brightness_add_btn /* 2131296574 */:
                this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(this.mBinding.bmwId8SettingsBrightnessSeekbar.getValue() + 1);
                return;
            case C0899R.C0901id.bmw_id8_settings_brightness_sub_btn /* 2131296577 */:
                this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(this.mBinding.bmwId8SettingsBrightnessSeekbar.getValue() - 1);
                return;
            default:
                return;
        }
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnValueChangeListener
    public void onValueChange(ID8ProgressBar progressBar, int value, float progress) {
        int temp = BrightnessUtils.convertGammaToLinearBmwId8(value, 10, 255);
        Log.i("BmwId8SettingsSystemBrightnessLay", " value " + temp);
        setBrightnessValueBg(this.context, temp);
        setSystemBrightness(temp);
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnTouchChangeListener
    public void onStartTrackingTouch(ID8ProgressBar progressBar) {
        this.mViewModel.systemBgShow.set(false);
        progressBar.requestFocus();
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnTouchChangeListener
    public void onStopTrackingTouch(ID8ProgressBar progressBar) {
    }

    /* loaded from: classes8.dex */
    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
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

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("BmwId8SettingsSystemBrightnessLay", "onChange: " + uri);
                BmwId8SettingsSystemBrightnessLay.this.mBackgroundHandler.post(BmwId8SettingsSystemBrightnessLay.this.mUpdateSliderRunnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGammaBmwId8(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage(value, 0, 100);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("BmwId8SettingsSystemBrightnessLay", "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.mBinding.bmwId8SettingsBrightnessText.setText("" + ((int) Math.round(100.0d * b)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(int brightness) {
        int value = BrightnessUtils.convertLinearToGammaBmwId8(brightness, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage(value, 0, 100);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("BmwId8SettingsSystemBrightnessLay", "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.mBinding.bmwId8SettingsBrightnessSeekbar.setValue(value);
    }
}

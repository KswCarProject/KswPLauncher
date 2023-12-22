package com.wits.ksw.settings.audi_mib3.p008vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.lang.reflect.Method;
import java.text.NumberFormat;

/* renamed from: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel */
/* loaded from: classes5.dex */
public class AudiMib3SystemViewModel extends AndroidViewModel {
    private static final String TAG = "KswApplication." + AudiMib3SystemViewModel.class.getSimpleName();
    public ObservableBoolean autoBrightness;
    public SeekBar.OnSeekBarChangeListener aux1ChangeListener;
    public ObservableInt aux1Progress;
    public SeekBar.OnSeekBarChangeListener aux2ChangeListener;
    public ObservableInt aux2Progress;
    private int beiguangValue;
    private ContentResolver contentResolver;
    private Context context;
    public ObservableInt dayBrightness;
    public SeekBar.OnSeekBarChangeListener dayBrightnessChangeListener;
    public ObservableField<String> dayBrightnessStr;
    public ObservableBoolean drivingVideo;
    private Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness;
    private int mMinBrightness;
    private final Runnable mUpdateSliderRunnable;
    public ObservableInt nightBrightness;
    public SeekBar.OnSeekBarChangeListener nightBrightnessChangeListener;
    public ObservableField<String> nightBrightnessStr;
    public CompoundButton.OnCheckedChangeListener onAudoBrightnessChangeListener;
    public RadioGroup.OnCheckedChangeListener onReverCameraCheckedChangeListener;
    public CompoundButton.OnCheckedChangeListener onReverMuteChangeListener;
    public CompoundButton.OnCheckedChangeListener onReverRadarkChangeListener;
    public CompoundButton.OnCheckedChangeListener onReverTrackChangeListener;
    public CompoundButton.OnCheckedChangeListener onReverViewChangeListener;
    public RadioGroup.OnCheckedChangeListener onSpedUnitChangeListener;
    public RadioGroup.OnCheckedChangeListener onTempUnitChangeListener;
    public CompoundButton.OnCheckedChangeListener ondrivingVideoChangeListener;
    public ObservableInt reverCamera;
    public ObservableBoolean reverMute;
    public ObservableBoolean reverRadar;
    public ObservableBoolean reverTrack;
    public ObservableBoolean reverView;
    public ObservableBoolean showAuxPostion;
    public ObservableInt speedUnit;
    public ObservableInt tempUnit;

    public AudiMib3SystemViewModel(Application application) {
        super(application);
        this.reverView = new ObservableBoolean(false);
        this.drivingVideo = new ObservableBoolean(false);
        this.reverTrack = new ObservableBoolean(false);
        this.reverRadar = new ObservableBoolean(false);
        this.reverMute = new ObservableBoolean(false);
        this.reverCamera = new ObservableInt(0);
        this.tempUnit = new ObservableInt(0);
        this.autoBrightness = new ObservableBoolean(false);
        this.nightBrightness = new ObservableInt();
        this.nightBrightnessStr = new ObservableField<>();
        this.dayBrightness = new ObservableInt();
        this.dayBrightnessStr = new ObservableField<>();
        this.showAuxPostion = new ObservableBoolean();
        this.aux1Progress = new ObservableInt();
        this.aux2Progress = new ObservableInt();
        this.speedUnit = new ObservableInt(0);
        this.onReverViewChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "save reverView: " + (isChecked ? 1 : 0));
            }
        };
        this.ondrivingVideoChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "save drivingVideo: " + (isChecked ? 1 : 0));
            }
        };
        this.onReverTrackChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "save reverTrack: " + (isChecked ? 1 : 0));
            }
        };
        this.onReverRadarkChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "save reverRadar: " + (isChecked ? 1 : 0));
            }
        };
        this.onReverMuteChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "save reverMute: " + (isChecked ? 1 : 0));
            }
        };
        this.onSpedUnitChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.6
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                int i = 0;
                while (i < count) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, i == 2 ? i - 1 : i);
                        Log.i(AudiMib3SystemViewModel.TAG, "save speed unit : " + i);
                        AudiMib3SystemViewModel.this.speedUnit.set(i == 2 ? i - 1 : i);
                    }
                    group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
                    i++;
                }
            }
        };
        this.onReverCameraCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.7
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        int data = i;
                        switch (i) {
                            case 2:
                                data = 1;
                                break;
                            case 4:
                                data = 2;
                                break;
                            case 6:
                                data = 3;
                                break;
                        }
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, data);
                        if (data == 3) {
                            SystemProperties.set("persist.sys.camera.preview.size", "1920x4344");
                        }
                        Log.i(AudiMib3SystemViewModel.TAG, "save rever Camera : " + data);
                        AudiMib3SystemViewModel.this.reverCamera.set(data);
                    }
                    group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
                }
            }
        };
        this.onTempUnitChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.8
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                int i = 0;
                while (i < count) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.TempUnit, i == 2 ? i - 1 : i);
                        Log.i(AudiMib3SystemViewModel.TAG, "save tempUnit  : " + i);
                        AudiMib3SystemViewModel.this.tempUnit.set(i == 2 ? i - 1 : i);
                    }
                    group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
                    i++;
                }
            }
        };
        this.onAudoBrightnessChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.BACKLIGHT, !isChecked ? 1 : 0);
                AudiMib3SystemViewModel.this.autoBrightness.set(isChecked);
                Log.i(AudiMib3SystemViewModel.TAG, "Backlight_auto_set : Backlight_auto_set" + (!isChecked ? 1 : 0));
            }
        };
        this.aux1ChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.10
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    AudiMib3SystemViewModel.this.aux1Progress.set(progress);
                    FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        this.aux2ChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.11
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AudiMib3SystemViewModel.this.aux2Progress.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        this.dayBrightnessChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.12
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, AudiMib3SystemViewModel.this.mMinBrightness, AudiMib3SystemViewModel.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    AudiMib3SystemViewModel audiMib3SystemViewModel = AudiMib3SystemViewModel.this;
                    audiMib3SystemViewModel.setBrightnessValueBg(audiMib3SystemViewModel.context, val);
                    AudiMib3SystemViewModel.this.setSystemBrightness(val);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        this.nightBrightnessChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.13
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        this.mUpdateSliderRunnable = new Runnable() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel.14
            @Override // java.lang.Runnable
            public void run() {
                int brightness = Settings.System.getInt(AudiMib3SystemViewModel.this.contentResolver, "screen_brightness", 255);
                AudiMib3SystemViewModel.this.setProgressText(brightness);
                AudiMib3SystemViewModel.this.setProgress(brightness);
            }
        };
        Context applicationContext = application.getApplicationContext();
        this.context = applicationContext;
        this.contentResolver = applicationContext.getContentResolver();
        try {
            int housi = PowerManagerApp.getSettingsInt(KeyConfig.HOU_SHI_SX);
            int cheVideo = PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP);
            int dcgj = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_GJ);
            int dcld = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_LD);
            int dcjy = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_JY);
            int nbauxsw = PowerManagerApp.getSettingsInt(KeyConfig.NBT_AUX_SW);
            int danwei = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
            int dcsxt = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_SXT);
            int bgkz = PowerManagerApp.getSettingsInt(KeyConfig.BACKLIGHT);
            int aux1 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX1);
            int aux2 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX2);
            int wd = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            Log.i(TAG, "AudiSystemViewModel: \nhousi:" + housi + "\ncheVideo:" + cheVideo + "\ndcgj:" + dcgj + "\ndcld:" + dcld + "\ndcjy:" + dcjy + "\nnbauxsw:" + nbauxsw + "\nspeed uint:" + danwei + "\ndcsxt:" + dcsxt + "\nbgkz:" + bgkz + "\naux1:" + aux1 + "\naux2:" + aux2 + "\ntempUnit:" + wd);
            boolean z = true;
            this.reverView.set(housi == 1);
            this.drivingVideo.set(cheVideo == 1);
            this.reverTrack.set(dcgj == 1);
            this.reverRadar.set(dcld == 1);
            this.reverMute.set(dcjy == 1);
            this.speedUnit.set(danwei);
            this.reverCamera.set(dcsxt);
            this.autoBrightness.set(bgkz == 0);
            ObservableBoolean observableBoolean = this.showAuxPostion;
            if (nbauxsw != 3) {
                z = false;
            }
            observableBoolean.set(z);
            this.aux1Progress.set(aux1);
            this.aux2Progress.set(aux2);
            this.tempUnit.set(wd);
        } catch (Exception e) {
            e.getStackTrace();
        }
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = getMaximumScreenBrightnessSetting();
        int i = Settings.System.getInt(this.contentResolver, "screen_brightness", 255);
        this.beiguangValue = i;
        setProgress(i);
        setProgressText(this.beiguangValue);
        this.mBackgroundHandler = new Handler(Looper.getMainLooper());
        BrightnessObserver brightnessObserver = new BrightnessObserver(new Handler());
        this.mBrightnessObserver = brightnessObserver;
        brightnessObserver.startObserving();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(int brightness) {
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        int maximumScreenBrightnessSetting = getMaximumScreenBrightnessSetting();
        this.mMaxBrightness = maximumScreenBrightnessSetting;
        int value = BrightnessUtils.convertLinearToGamma(brightness, this.mMinBrightness, maximumScreenBrightnessSetting);
        double b = BrightnessUtils.getPercentage(value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i(TAG, "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.dayBrightness.set(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGamma(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage(value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i(TAG, "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.dayBrightnessStr.set("" + ((int) Math.round(100.0d * b)));
    }

    public void setBrightnessValueBg(Context context, int key) {
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Class<?> dmclass = Class.forName("android.hardware.display.DisplayManager");
            Method set = dmclass.getMethod("setTemporaryBrightness", Integer.TYPE);
            set.invoke(displayManager, Integer.valueOf(key));
            Log.i(TAG, "setBrightness: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemBrightness(int brightness) {
        Log.i(TAG, " setSystemBrightness:" + brightness);
        Settings.System.putInt(this.contentResolver, "screen_brightness", brightness);
    }

    public int getMinimumScreenBrightnessSetting() {
        return 10;
    }

    public int getMaximumScreenBrightnessSetting() {
        return 255;
    }

    /* renamed from: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel$BrightnessObserver */
    /* loaded from: classes5.dex */
    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
        }

        public void startObserving() {
            AudiMib3SystemViewModel.this.contentResolver.unregisterContentObserver(this);
            AudiMib3SystemViewModel.this.contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("SetSystemTwo", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            AudiMib3SystemViewModel.this.contentResolver.unregisterContentObserver(this);
            Log.i("SetSystemTwo", "stopObserving: unregisterContentObserver");
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("SetSystemTwo", "onChange: " + uri);
                AudiMib3SystemViewModel.this.mBackgroundHandler.post(AudiMib3SystemViewModel.this.mUpdateSliderRunnable);
            }
        }
    }
}

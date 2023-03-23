package com.wits.ksw.settings.audi_mib3.vm;

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
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.NumberFormat;

public class AudiMib3SystemViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public static final String TAG = ("KswApplication." + AudiMib3SystemViewModel.class.getSimpleName());
    public ObservableBoolean autoBrightness = new ObservableBoolean(false);
    public SeekBar.OnSeekBarChangeListener aux1ChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                AudiMib3SystemViewModel.this.aux1Progress.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    public ObservableInt aux1Progress = new ObservableInt();
    public SeekBar.OnSeekBarChangeListener aux2ChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            AudiMib3SystemViewModel.this.aux2Progress.set(progress);
            FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    public ObservableInt aux2Progress = new ObservableInt();
    private int beiguangValue;
    /* access modifiers changed from: private */
    public ContentResolver contentResolver;
    /* access modifiers changed from: private */
    public Context context;
    public ObservableInt dayBrightness = new ObservableInt();
    public SeekBar.OnSeekBarChangeListener dayBrightnessChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                int val = BrightnessUtils.convertGammaToLinear(progress, AudiMib3SystemViewModel.this.mMinBrightness, AudiMib3SystemViewModel.this.mMaxBrightness);
                Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                AudiMib3SystemViewModel audiMib3SystemViewModel = AudiMib3SystemViewModel.this;
                audiMib3SystemViewModel.setBrightnessValueBg(audiMib3SystemViewModel.context, val);
                AudiMib3SystemViewModel.this.setSystemBrightness(val);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    public ObservableField<String> dayBrightnessStr = new ObservableField<>();
    public ObservableBoolean drivingVideo = new ObservableBoolean(false);
    /* access modifiers changed from: private */
    public Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    /* access modifiers changed from: private */
    public int mMaxBrightness;
    /* access modifiers changed from: private */
    public int mMinBrightness;
    /* access modifiers changed from: private */
    public final Runnable mUpdateSliderRunnable = new Runnable() {
        public void run() {
            int brightness = Settings.System.getInt(AudiMib3SystemViewModel.this.contentResolver, "screen_brightness", 255);
            AudiMib3SystemViewModel.this.setProgressText(brightness);
            AudiMib3SystemViewModel.this.setProgress(brightness);
        }
    };
    public ObservableInt nightBrightness = new ObservableInt();
    public SeekBar.OnSeekBarChangeListener nightBrightnessChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    public ObservableField<String> nightBrightnessStr = new ObservableField<>();
    public CompoundButton.OnCheckedChangeListener onAudoBrightnessChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaIntData(KeyConfig.BACKLIGHT, isChecked ^ true ? 1 : 0);
            AudiMib3SystemViewModel.this.autoBrightness.set(isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "Backlight_auto_set : Backlight_auto_set" + (isChecked ^ true ? 1 : 0));
        }
    };
    public RadioGroup.OnCheckedChangeListener onReverCameraCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
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
                    Log.i(AudiMib3SystemViewModel.TAG, "save rever Camera : " + data);
                    AudiMib3SystemViewModel.this.reverCamera.set(data);
                }
                group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
            }
        }
    };
    public CompoundButton.OnCheckedChangeListener onReverMuteChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "save reverMute: " + (isChecked));
        }
    };
    public CompoundButton.OnCheckedChangeListener onReverRadarkChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "save reverRadar: " + (isChecked));
        }
    };
    public CompoundButton.OnCheckedChangeListener onReverTrackChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "save reverTrack: " + (isChecked));
        }
    };
    public CompoundButton.OnCheckedChangeListener onReverViewChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "save reverView: " + (isChecked));
        }
    };
    public RadioGroup.OnCheckedChangeListener onSpedUnitChangeListener = new RadioGroup.OnCheckedChangeListener() {
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
    public RadioGroup.OnCheckedChangeListener onTempUnitChangeListener = new RadioGroup.OnCheckedChangeListener() {
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
    public CompoundButton.OnCheckedChangeListener ondrivingVideoChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
            Log.i(AudiMib3SystemViewModel.TAG, "save drivingVideo: " + (isChecked));
        }
    };
    public ObservableInt reverCamera = new ObservableInt(0);
    public ObservableBoolean reverMute = new ObservableBoolean(false);
    public ObservableBoolean reverRadar = new ObservableBoolean(false);
    public ObservableBoolean reverTrack = new ObservableBoolean(false);
    public ObservableBoolean reverView = new ObservableBoolean(false);
    public ObservableBoolean showAuxPostion = new ObservableBoolean();
    public ObservableInt speedUnit = new ObservableInt(0);
    public ObservableInt tempUnit = new ObservableInt(0);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AudiMib3SystemViewModel(Application application) {
        super(application);
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

    /* access modifiers changed from: private */
    public void setProgress(int brightness) {
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        int maximumScreenBrightnessSetting = getMaximumScreenBrightnessSetting();
        this.mMaxBrightness = maximumScreenBrightnessSetting;
        int value = BrightnessUtils.convertLinearToGamma(brightness, this.mMinBrightness, maximumScreenBrightnessSetting);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        Log.i(TAG, "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.dayBrightness.set(value);
    }

    /* access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGamma(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        Log.i(TAG, "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.dayBrightnessStr.set("" + ((int) Math.round(100.0d * b)));
    }

    public void setBrightnessValueBg(Context context2, int key) {
        try {
            Class.forName("android.hardware.display.DisplayManager").getMethod("setTemporaryBrightness", new Class[]{Integer.TYPE}).invoke((DisplayManager) context2.getSystemService("display"), new Object[]{Integer.valueOf(key)});
            Log.i(TAG, "setBrightness: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
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

    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");

        public BrightnessObserver(Handler handler) {
            super(handler);
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

        public void onChange(boolean selfChange) {
            onChange(selfChange, (Uri) null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("SetSystemTwo", "onChange: " + uri);
                AudiMib3SystemViewModel.this.mBackgroundHandler.post(AudiMib3SystemViewModel.this.mUpdateSliderRunnable);
            }
        }
    }
}

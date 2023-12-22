package com.wits.ksw.settings.audi.p007vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.widget.SeekBar;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* renamed from: com.wits.ksw.settings.audi.vm.VolumeViewModel */
/* loaded from: classes7.dex */
public class VolumeViewModel extends AndroidViewModel {
    public ObservableInt carCallVolume;
    public ObservableInt carNaviVolume;
    public ObservableInt hzMediaVolume;
    public ObservableInt hzcallVolume;

    public VolumeViewModel(Application application) {
        super(application);
        this.hzMediaVolume = new ObservableInt(26);
        this.hzcallVolume = new ObservableInt(26);
        this.carCallVolume = new ObservableInt(26);
        this.carNaviVolume = new ObservableInt(26);
        try {
            int hz_mediaVolume = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL);
            int hz_callVolume = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_PHONE_VOL);
            int car_callVolume = PowerManagerApp.getSettingsInt(KeyConfig.CAR_PHONE_VOL);
            int car_naviVolume = PowerManagerApp.getSettingsInt(KeyConfig.CAR_NAVI_VOL);
            this.hzMediaVolume.set(hz_mediaVolume);
            this.hzcallVolume.set(hz_callVolume);
            this.carCallVolume.set(car_callVolume);
            this.carNaviVolume.set(car_naviVolume);
        } catch (Exception e) {
            e.getStackTrace();
        }
        ContentResolver contentResolver = application.getApplicationContext().getContentResolver();
        contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_MEDIA_VOL), true, new ContentObserver(new Handler()) { // from class: com.wits.ksw.settings.audi.vm.VolumeViewModel.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                try {
                    int hz_mediaVolume2 = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL);
                    VolumeViewModel.this.hzMediaVolume.set(hz_mediaVolume2);
                } catch (RemoteException e2) {
                }
            }
        });
    }

    public static void setOnSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi.vm.VolumeViewModel.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    ObservableInt.this.set(progress);
                    FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                ObservableInt.this.set(progress);
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
            }
        });
    }

    public static void setHzCallSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi.vm.VolumeViewModel.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    ObservableInt.this.set(progress);
                    FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                ObservableInt.this.set(progress);
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
            }
        });
    }

    public static void setCarCallSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi.vm.VolumeViewModel.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    ObservableInt.this.set(progress);
                    FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                ObservableInt.this.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
            }
        });
    }

    public static void setCarNaviSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi.vm.VolumeViewModel.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    ObservableInt.this.set(progress);
                    FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                ObservableInt.this.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
            }
        });
    }
}

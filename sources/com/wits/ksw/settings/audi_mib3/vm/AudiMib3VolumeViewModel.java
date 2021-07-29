package com.wits.ksw.settings.audi_mib3.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.database.ContentObserver;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.widget.SeekBar;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AudiMib3VolumeViewModel extends AndroidViewModel {
    public ObservableInt carCallVolume = new ObservableInt(26);
    public ObservableInt carNaviVolume = new ObservableInt(26);
    public ObservableInt hzMediaVolume = new ObservableInt(26);
    public ObservableInt hzcallVolume = new ObservableInt(26);

    public AudiMib3VolumeViewModel(Application application) {
        super(application);
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
        application.getApplicationContext().getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_MEDIA_VOL), true, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                try {
                    AudiMib3VolumeViewModel.this.hzMediaVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
                } catch (RemoteException e) {
                }
            }
        });
    }

    public static void setOnSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    observableInt.set(progress);
                    FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                observableInt.set(progress);
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
            }
        });
    }

    public static void setHzCallSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    observableInt.set(progress);
                    FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                observableInt.set(progress);
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
            }
        });
    }

    public static void setCarCallSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    observableInt.set(progress);
                    FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                observableInt.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
            }
        });
    }

    public static void setCarNaviSeekBarChangeListener(SeekBar seekBar, final ObservableInt observableInt) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    observableInt.set(progress);
                    FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                observableInt.set(progress);
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
            }
        });
    }
}

package com.wits.ksw.settings.audi.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.database.ContentObserver;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class VolumeViewModel extends AndroidViewModel {
    public ObservableInt carCallVolume = new ObservableInt(26);
    public ObservableInt carNaviVolume = new ObservableInt(26);
    public ObservableInt hzMediaVolume = new ObservableInt(26);
    public ObservableInt hzcallVolume = new ObservableInt(26);

    public VolumeViewModel(@NonNull Application application) {
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
                    VolumeViewModel.this.hzMediaVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
                } catch (RemoteException e) {
                }
            }
        });
    }

    @BindingAdapter({"setHzMediaSeekBarChangeListener"})
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

    @BindingAdapter({"setHzCallSeekBarChangeListener"})
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

    @BindingAdapter({"setCarCallSeekBarChangeListener"})
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

    @BindingAdapter({"setCarNaviSeekBarChangeListener"})
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

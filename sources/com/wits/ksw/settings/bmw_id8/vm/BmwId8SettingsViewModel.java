package com.wits.ksw.settings.bmw_id8.vm;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;

public class BmwId8SettingsViewModel extends ViewModel {
    private static volatile BmwId8SettingsViewModel mBmwId8SettingsViewModel = null;
    public ObservableInt androidCallVolume = new ObservableInt(0);
    public ObservableInt androidMediaVolume = new ObservableInt(0);
    public ObservableInt androidOemVolumeMax = new ObservableInt(40);
    public ObservableField<String> appVersionStr = new ObservableField<>();
    public ObservableBoolean audioBgShow = new ObservableBoolean(true);
    public ObservableBoolean audioIconShow = new ObservableBoolean(true);
    public ObservableInt bassVolume = new ObservableInt(0);
    public ObservableField<String> bassVolumeStr = new ObservableField<>(TxzMessage.TXZ_DISMISS);
    public ObservableInt brightness = new ObservableInt(0);
    public ObservableBoolean disableVideo = new ObservableBoolean();
    public ObservableInt eqType = new ObservableInt(0);
    public ObservableInt fuelUnit = new ObservableInt(0);
    public ObservableField<String> mcuVersionStr = new ObservableField<>();
    public ObservableInt midVolume = new ObservableInt(0);
    public ObservableField<String> midVolumeStr = new ObservableField<>(TxzMessage.TXZ_DISMISS);
    public ObservableInt oemCallVolume = new ObservableInt(0);
    public ObservableInt oemNaviVolume = new ObservableInt(0);
    public ObservableBoolean parkLines = new ObservableBoolean();
    public ObservableBoolean parkMute = new ObservableBoolean();
    public ObservableBoolean parkRadar = new ObservableBoolean();
    public ObservableField<String> ramStr = new ObservableField<>();
    public ObservableInt rearCameraType = new ObservableInt(0);
    public ObservableBoolean rearMirror = new ObservableBoolean();
    public ObservableField<String> storageStr = new ObservableField<>();
    public ObservableBoolean systemBgShow = new ObservableBoolean(true);
    public ObservableBoolean systemIconShow = new ObservableBoolean(true);
    public ObservableField<String> systemVersionStr = new ObservableField<>();
    public ObservableInt tempUnit = new ObservableInt(0);
    public ObservableBoolean timeFormatShow = new ObservableBoolean();
    public ObservableInt timeFormatState = new ObservableInt(1);
    public ObservableBoolean timeSyncShow = new ObservableBoolean();
    public ObservableInt timeSyncState = new ObservableInt(1);
    public ObservableInt treVolume = new ObservableInt(0);
    public ObservableField<String> treVolumeStr = new ObservableField<>(TxzMessage.TXZ_DISMISS);
    public ObservableBoolean userTypeShow = new ObservableBoolean();

    public static BmwId8SettingsViewModel getInstance() {
        if (mBmwId8SettingsViewModel == null) {
            synchronized (BmwId8SettingsViewModel.class) {
                if (mBmwId8SettingsViewModel == null) {
                    mBmwId8SettingsViewModel = new BmwId8SettingsViewModel();
                }
            }
        }
        return mBmwId8SettingsViewModel;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmw_id8_settings_android_call_add_btn /*2131296524*/:
                this.audioBgShow.set(false);
                calcAddVolume(this.androidCallVolume, this.androidOemVolumeMax.get(), KeyConfig.ANDROID_PHONE_VOL);
                return;
            case R.id.bmw_id8_settings_android_call_sub_btn /*2131296526*/:
                this.audioBgShow.set(false);
                calcSubVolume(this.androidCallVolume, 0, KeyConfig.ANDROID_PHONE_VOL);
                return;
            case R.id.bmw_id8_settings_meida_add_btn /*2131296579*/:
                this.audioBgShow.set(false);
                calcAddVolume(this.androidMediaVolume, this.androidOemVolumeMax.get(), KeyConfig.ANDROID_MEDIA_VOL);
                return;
            case R.id.bmw_id8_settings_meida_sub_btn /*2131296581*/:
                this.audioBgShow.set(false);
                calcSubVolume(this.androidMediaVolume, 0, KeyConfig.ANDROID_MEDIA_VOL);
                return;
            case R.id.bmw_id8_settings_navi_add_btn /*2131296585*/:
                this.audioBgShow.set(false);
                calcAddVolume(this.oemNaviVolume, this.androidOemVolumeMax.get(), KeyConfig.CAR_NAVI_VOL);
                return;
            case R.id.bmw_id8_settings_navi_sub_btn /*2131296591*/:
                this.audioBgShow.set(false);
                calcSubVolume(this.oemNaviVolume, 0, KeyConfig.CAR_NAVI_VOL);
                return;
            case R.id.bmw_id8_settings_oem_call_add_btn /*2131296592*/:
                this.audioBgShow.set(false);
                calcAddVolume(this.oemCallVolume, this.androidOemVolumeMax.get(), KeyConfig.CAR_PHONE_VOL);
                return;
            case R.id.bmw_id8_settings_oem_call_sub_btn /*2131296594*/:
                this.audioBgShow.set(false);
                calcSubVolume(this.oemCallVolume, 0, KeyConfig.CAR_PHONE_VOL);
                return;
            case R.id.bmw_id8_settings_system_camera_360 /*2131296597*/:
                this.systemBgShow.set(false);
                this.rearCameraType.set(2);
                FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 2);
                return;
            case R.id.bmw_id8_settings_system_camera_after /*2131296598*/:
                this.systemBgShow.set(false);
                this.rearCameraType.set(0);
                FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 0);
                return;
            case R.id.bmw_id8_settings_system_camera_oem /*2131296600*/:
                this.systemBgShow.set(false);
                this.rearCameraType.set(1);
                FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 1);
                return;
            case R.id.bmw_id8_settings_system_fuel_l /*2131296603*/:
                this.systemBgShow.set(false);
                this.fuelUnit.set(0);
                FileUtils.savaIntData(KeyConfig.FUEL_UNIT, 0);
                return;
            case R.id.bmw_id8_settings_system_fuel_uk /*2131296605*/:
                this.systemBgShow.set(false);
                this.fuelUnit.set(2);
                FileUtils.savaIntData(KeyConfig.FUEL_UNIT, 2);
                return;
            case R.id.bmw_id8_settings_system_fuel_us /*2131296606*/:
                this.systemBgShow.set(false);
                this.fuelUnit.set(1);
                FileUtils.savaIntData(KeyConfig.FUEL_UNIT, 1);
                return;
            case R.id.bmw_id8_settings_system_temp_c /*2131296620*/:
                this.systemBgShow.set(false);
                this.tempUnit.set(0);
                FileUtils.savaIntData(KeyConfig.TempUnit, 0);
                return;
            case R.id.bmw_id8_settings_system_temp_f /*2131296621*/:
                this.systemBgShow.set(false);
                this.tempUnit.set(1);
                FileUtils.savaIntData(KeyConfig.TempUnit, 1);
                return;
            case R.id.bmw_id8_settings_time_12 /*2131296626*/:
                if (this.timeFormatState.get() != 1) {
                    this.timeFormatState.set(1);
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                    return;
                }
                return;
            case R.id.bmw_id8_settings_time_24 /*2131296627*/:
                if (this.timeFormatState.get() != 0) {
                    this.timeFormatState.set(0);
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                    return;
                }
                return;
            case R.id.bmw_id8_settings_time_android /*2131296628*/:
                if (this.timeSyncState.get() != 0) {
                    this.timeSyncState.set(0);
                    FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                    return;
                }
                return;
            case R.id.bmw_id8_settings_time_car /*2131296630*/:
                if (this.timeSyncState.get() != 1) {
                    this.timeSyncState.set(1);
                    FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                    return;
                }
                return;
            case R.id.bmw_id8_settings_time_format /*2131296631*/:
                ObservableBoolean observableBoolean = this.timeFormatShow;
                observableBoolean.set(!observableBoolean.get());
                return;
            case R.id.bmw_id8_settings_time_sync /*2131296633*/:
                ObservableBoolean observableBoolean2 = this.timeSyncShow;
                observableBoolean2.set(!observableBoolean2.get());
                return;
            default:
                return;
        }
    }

    private void calcSubVolume(ObservableInt observableInt, int min, String key) {
        try {
            int value = observableInt.get() - 1;
            if (value >= min) {
                FileUtils.savaIntData(key, value);
                observableInt.set(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcAddVolume(ObservableInt observableInt, int max, String key) {
        try {
            int value = observableInt.get() + 1;
            if (value <= max) {
                FileUtils.savaIntData(key, value);
                observableInt.set(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVolumeStr(ObservableInt observableInt) {
        if (observableInt.get() > 0) {
            return "+" + observableInt.get();
        }
        return String.valueOf(observableInt.get());
    }
}

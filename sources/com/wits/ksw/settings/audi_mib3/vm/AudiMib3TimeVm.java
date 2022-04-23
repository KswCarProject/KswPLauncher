package com.wits.ksw.settings.audi_mib3.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.RemoteException;
import android.util.Log;
import android.widget.CompoundButton;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AudiMib3TimeVm extends AndroidViewModel {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + AudiMib3TimeVm.class.getSimpleName());
    public CompoundButton.OnCheckedChangeListener androiTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: androiTimeCheckedChangeListener");
            FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
            AudiMib3TimeVm.this.updataTime();
        }
    };
    public CompoundButton.OnCheckedChangeListener carTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: carTimeCheckedChangeListener");
            FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
            AudiMib3TimeVm.this.updataTime();
        }
    };
    private Context context = getApplication();
    public ObservableBoolean is24Hour = new ObservableBoolean();
    public ObservableBoolean isCarTime = new ObservableBoolean();
    public CompoundButton.OnCheckedChangeListener on12HourChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: on12HourChangeListener12");
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                AudiMib3TimeVm.this.updata24HourFormat();
            }
        }
    };
    public CompoundButton.OnCheckedChangeListener on24HourChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(AudiMib3TimeVm.TAG, "onCheckedChanged: " + isChecked);
            if (isChecked) {
                Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: on24HourChangeListener24");
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                AudiMib3TimeVm.this.updata24HourFormat();
            }
        }
    };

    public AudiMib3TimeVm(Application application) {
        super(application);
        updata24HourFormat();
        updataTime();
    }

    /* access modifiers changed from: private */
    public void updataTime() {
        try {
            int timeSync = PowerManagerApp.getSettingsInt(KeyConfig.TIME_SOURCE);
            ObservableBoolean observableBoolean = this.isCarTime;
            boolean z = true;
            if (timeSync != 1) {
                z = false;
            }
            observableBoolean.set(z);
            Log.i(TAG, "updataTime: " + timeSync);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updata24HourFormat() {
        try {
            int timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
            ObservableBoolean observableBoolean = this.is24Hour;
            boolean z = true;
            if (timeZhis != 1) {
                z = false;
            }
            observableBoolean.set(z);
            Log.i(TAG, "updata24HourFormat: " + timeZhis);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

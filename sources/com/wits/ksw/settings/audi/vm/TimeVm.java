package com.wits.ksw.settings.audi.vm;

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

public class TimeVm extends AndroidViewModel {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + TimeVm.class.getSimpleName());
    public CompoundButton.OnCheckedChangeListener androiTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
            TimeVm.this.updataTime();
        }
    };
    public CompoundButton.OnCheckedChangeListener carTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
            TimeVm.this.updataTime();
        }
    };
    private Context context = getApplication();
    public ObservableBoolean is24Hour = new ObservableBoolean();
    public ObservableBoolean isCarTime = new ObservableBoolean();
    public CompoundButton.OnCheckedChangeListener on12HourChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                TimeVm.this.updata24HourFormat();
            }
        }
    };
    public CompoundButton.OnCheckedChangeListener on24HourChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(TimeVm.TAG, "onCheckedChanged: " + isChecked);
            if (isChecked) {
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                TimeVm.this.updata24HourFormat();
            }
        }
    };

    public TimeVm(Application application) {
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

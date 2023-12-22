package com.wits.ksw.settings.audi.p007vm;

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

/* renamed from: com.wits.ksw.settings.audi.vm.TimeVm */
/* loaded from: classes7.dex */
public class TimeVm extends AndroidViewModel {
    private static final String TAG = "KswApplication." + TimeVm.class.getSimpleName();
    public CompoundButton.OnCheckedChangeListener androiTimeCheckedChangeListener;
    public CompoundButton.OnCheckedChangeListener carTimeCheckedChangeListener;
    private Context context;
    public ObservableBoolean is24Hour;
    public ObservableBoolean isCarTime;
    public CompoundButton.OnCheckedChangeListener on12HourChangeListener;
    public CompoundButton.OnCheckedChangeListener on24HourChangeListener;

    public TimeVm(Application application) {
        super(application);
        this.is24Hour = new ObservableBoolean();
        this.isCarTime = new ObservableBoolean();
        this.on24HourChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi.vm.TimeVm.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TimeVm.TAG, "onCheckedChanged: " + isChecked);
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                    TimeVm.this.updata24HourFormat();
                }
            }
        };
        this.on12HourChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi.vm.TimeVm.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                    TimeVm.this.updata24HourFormat();
                }
            }
        };
        this.androiTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi.vm.TimeVm.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                TimeVm.this.updataTime();
            }
        };
        this.carTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi.vm.TimeVm.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                TimeVm.this.updataTime();
            }
        };
        this.context = getApplication();
        updata24HourFormat();
        updataTime();
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
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

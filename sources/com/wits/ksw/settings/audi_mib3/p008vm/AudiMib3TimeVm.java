package com.wits.ksw.settings.audi_mib3.p008vm;

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

/* renamed from: com.wits.ksw.settings.audi_mib3.vm.AudiMib3TimeVm */
/* loaded from: classes5.dex */
public class AudiMib3TimeVm extends AndroidViewModel {
    private static final String TAG = "KswApplication." + AudiMib3TimeVm.class.getSimpleName();
    public CompoundButton.OnCheckedChangeListener androiTimeCheckedChangeListener;
    public CompoundButton.OnCheckedChangeListener carTimeCheckedChangeListener;
    private Context context;
    public ObservableBoolean is24Hour;
    public ObservableBoolean isCarTime;
    public CompoundButton.OnCheckedChangeListener on12HourChangeListener;
    public CompoundButton.OnCheckedChangeListener on24HourChangeListener;

    public AudiMib3TimeVm(Application application) {
        super(application);
        this.is24Hour = new ObservableBoolean();
        this.isCarTime = new ObservableBoolean();
        this.on24HourChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3TimeVm.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(AudiMib3TimeVm.TAG, "onCheckedChanged: " + isChecked);
                if (isChecked) {
                    Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: on24HourChangeListener24");
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                    AudiMib3TimeVm.this.updata24HourFormat();
                }
            }
        };
        this.on12HourChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3TimeVm.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: on12HourChangeListener12");
                    FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                    AudiMib3TimeVm.this.updata24HourFormat();
                }
            }
        };
        this.androiTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3TimeVm.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: androiTimeCheckedChangeListener");
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                AudiMib3TimeVm.this.updataTime();
            }
        };
        this.carTimeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3TimeVm.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(AudiMib3TimeVm.TAG, "onCheckedChanged: carTimeCheckedChangeListener");
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                AudiMib3TimeVm.this.updataTime();
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

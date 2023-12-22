package com.wits.ksw.settings.audi_mib3.p008vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.ksw.settings.utlis_view.TimeUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* renamed from: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel */
/* loaded from: classes5.dex */
public class AudiMib3SettingViewModel extends AndroidViewModel {
    private static final String TAG = "KswApplication." + AudiMib3SettingViewModel.class.getSimpleName();
    public ObservableField<String> appVer;
    private Context context;
    public ObservableBoolean is24Hour;
    public ObservableBoolean isCarTime;
    public ObservableField<String> keyBuffer;
    public ObservableField<String> mcuVer;
    public ObservableField<String> nandflash;
    public RadioGroup.OnCheckedChangeListener on24HourChangeListener;
    public RadioGroup.OnCheckedChangeListener onTimeMoedlChangeListener;
    private StringBuffer passwordBuffer;
    public ObservableField<String> ramVer;
    public ObservableField<String> systemVersion;

    public AudiMib3SettingViewModel(Application application) {
        super(application);
        this.appVer = new ObservableField<>();
        this.systemVersion = new ObservableField<>();
        this.nandflash = new ObservableField<>();
        this.ramVer = new ObservableField<>();
        this.mcuVer = new ObservableField<>();
        this.is24Hour = new ObservableBoolean();
        this.isCarTime = new ObservableBoolean();
        this.keyBuffer = new ObservableField<>();
        this.passwordBuffer = new StringBuffer();
        this.on24HourChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case C0899R.C0901id.audi_mib3_12_time /* 2131296397 */:
                        Log.d(AudiMib3SettingViewModel.TAG, "onCheckedChanged: 12\u5c0f\u65f6\u5236");
                        FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                        break;
                    case C0899R.C0901id.audi_mib3_24_time /* 2131296398 */:
                        Log.d(AudiMib3SettingViewModel.TAG, "onCheckedChanged: 24\u5c0f\u65f6\u5236");
                        FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                        break;
                }
                AudiMib3SettingViewModel.this.updata24HourFormat();
            }
        };
        this.onTimeMoedlChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case C0899R.C0901id.audi_mib3_android_time /* 2131296399 */:
                        Log.d(AudiMib3SettingViewModel.TAG, "onCheckedChanged: android\u65f6\u95f4");
                        FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                        break;
                    case C0899R.C0901id.audi_mib3_car_time /* 2131296400 */:
                        Log.d(AudiMib3SettingViewModel.TAG, "onCheckedChanged: \u539f\u8f66\u65f6\u95f4");
                        FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                        break;
                }
                AudiMib3SettingViewModel.this.updataTime();
            }
        };
        Context applicationContext = application.getApplicationContext();
        this.context = applicationContext;
        String appVerStr = applicationContext.getString(C0899R.string.audi_set_sys_info_app_ver, getAppVersion());
        String mcuVerStr = this.context.getString(C0899R.string.audi_set_sys_info_mcu_ver, McuUtil.getMcuVersion());
        Context context = this.context;
        String ramStr = context.getString(C0899R.string.audi_set_sys_info_ram, UtilsInfo.getRAMSize(context));
        String flashStr = this.context.getString(C0899R.string.audi_set_sys_info_nanndflash, UtilsInfo.getROMSize());
        this.nandflash.set(flashStr);
        this.ramVer.set(ramStr);
        this.appVer.set(appVerStr);
        this.mcuVer.set(mcuVerStr);
        systemVersion();
        Log.i(TAG, " \nflashStr : " + flashStr + " \nramVer : " + ramStr + " \nis24Hour : " + TimeUtil.is24HourFormat(this.context) + " \nappVer : " + appVerStr + " \nmcuVer : " + mcuVerStr);
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel.1
            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                new Handler().post(new Runnable() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AudiMib3SettingViewModel.this.updatMcuVersion();
                    }
                });
            }
        });
        updataTime();
        updata24HourFormat();
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
            this.is24Hour.set(timeZhis == 0);
            Log.i(TAG, "updata24HourFormat: " + timeZhis);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void systemVersion() {
        this.systemVersion.set(UtilsInfo.getSettingsVer(this.context) + "-" + UtilsInfo.getIMEI());
    }

    private String getAppVersion() {
        try {
            PackageManager manager = this.context.getPackageManager();
            manager.getPackageInfo(this.context.getPackageName(), 0);
            String version = "Witstek-" + Build.DISPLAY;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "\u627e\u4e0d\u5230\u7248\u672c\u53f7";
        }
    }

    private String getMcuVersion() {
        try {
            String mvformat = PowerManagerApp.getStatusString("mcuVerison");
            return mvformat;
        } catch (Exception e) {
            e.getStackTrace();
            return "";
        }
    }

    public static String getBaseband_Ver() {
        String Version = SystemProperties.get("gsm.version.baseband");
        return Version;
    }

    public void updatMcuVersion() {
        String mcuVerStr = this.context.getString(C0899R.string.audi_set_sys_info_mcu_ver, McuUtil.getMcuVersion());
        this.mcuVer.set(mcuVerStr);
    }

    public void onKeyClick(View view, int key) {
        this.passwordBuffer.append(key);
        this.keyBuffer.set(this.passwordBuffer.toString());
    }

    public void onDeleteClick(View view) {
        int lenght = this.passwordBuffer.length();
        if (lenght > 0) {
            this.passwordBuffer.deleteCharAt(lenght - 1);
        }
        this.keyBuffer.set(this.passwordBuffer.toString());
    }

    public boolean onDeleteLongClick(View view) {
        int lenght = this.passwordBuffer.length();
        if (lenght > 0) {
            this.passwordBuffer.delete(0, lenght);
        }
        this.keyBuffer.set(this.passwordBuffer.toString());
        return true;
    }

    private String getFactoryPassword() {
        String str = "factoryPassword\uff1a";
        String factoryPassword = "1314";
        try {
            try {
                factoryPassword = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
                str = "factoryPassword\uff1a" + factoryPassword;
                Log.e(TAG, str);
                return factoryPassword;
            } catch (Exception e) {
                e.printStackTrace();
                String str2 = TAG;
                Log.e(str2, "Exception: \u83b7\u53d6\u5de5\u5382\u914d\u7f6e\u5bc6\u7801\u5931\u8d25");
                Log.e(str2, "factoryPassword\uff1a1314");
                return "1314";
            }
        } catch (Throwable th) {
            Log.e(TAG, str + factoryPassword);
            throw th;
        }
    }

    private String getInputPassword() {
        if (this.passwordBuffer.length() > 0) {
            return this.passwordBuffer.toString();
        }
        return "";
    }

    public boolean isPasswordCorrect() {
        boolean isCorrect = TextUtils.equals(getFactoryPassword(), getInputPassword());
        onDeleteLongClick(null);
        return isCorrect;
    }
}

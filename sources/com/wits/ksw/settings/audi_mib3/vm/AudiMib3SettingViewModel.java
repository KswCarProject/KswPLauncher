package com.wits.ksw.settings.audi_mib3.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.ksw.settings.utlis_view.TimeUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AudiMib3SettingViewModel extends AndroidViewModel {
    private static final String TAG = ("KSWLauncher." + AudiMib3SettingViewModel.class.getSimpleName());
    public ObservableField<String> appVer = new ObservableField<>();
    private Context context;
    public ObservableBoolean is24Hour = new ObservableBoolean();
    public ObservableBoolean isCarTime = new ObservableBoolean();
    public ObservableField<String> keyBuffer = new ObservableField<>();
    public ObservableField<String> mcuVer = new ObservableField<>();
    public ObservableField<String> nandflash = new ObservableField<>();
    public RadioGroup.OnCheckedChangeListener on24HourChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                if (checkedId == group.getChildAt(i).getId()) {
                    switch (i) {
                        case 0:
                            FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                            break;
                        case 2:
                            FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                            break;
                    }
                    AudiMib3SettingViewModel.this.updata24HourFormat();
                }
            }
        }
    };
    public RadioGroup.OnCheckedChangeListener onTimeMoedlChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                if (checkedId == group.getChildAt(i).getId()) {
                    switch (i) {
                        case 0:
                            FileUtils.savaIntData(KeyConfig.TIME_SOURCE, i);
                            break;
                        case 2:
                            break;
                    }
                    FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                    AudiMib3SettingViewModel.this.updataTime();
                    continue;
                }
            }
        }
    };
    private StringBuffer passwordBuffer = new StringBuffer();
    public ObservableField<String> ramVer = new ObservableField<>();
    public ObservableField<String> systemVersion = new ObservableField<>();

    public AudiMib3SettingViewModel(Application application) {
        super(application);
        Context applicationContext = application.getApplicationContext();
        this.context = applicationContext;
        String appVerStr = applicationContext.getString(R.string.audi_set_sys_info_app_ver, new Object[]{getAppVersion()});
        String mcuVerStr = this.context.getString(R.string.audi_set_sys_info_mcu_ver, new Object[]{McuUtil.getMcuVersion()});
        Context context2 = this.context;
        String ramStr = context2.getString(R.string.audi_set_sys_info_ram, new Object[]{UtilsInfo.getRAMSize(context2)});
        String flashStr = this.context.getString(R.string.audi_set_sys_info_nanndflash, new Object[]{UtilsInfo.getROMSize()});
        this.nandflash.set(flashStr);
        this.ramVer.set(ramStr);
        this.appVer.set(appVerStr);
        this.mcuVer.set(mcuVerStr);
        systemVersion();
        Log.i(TAG, " \nflashStr : " + flashStr + " \nramVer : " + ramStr + " \nis24Hour : " + TimeUtil.is24HourFormat(this.context) + " \nappVer : " + appVerStr + " \nmcuVer : " + mcuVerStr);
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                new Handler().post(new Runnable() {
                    public void run() {
                        AudiMib3SettingViewModel.this.updatMcuVersion();
                    }
                });
            }
        });
        updataTime();
        updata24HourFormat();
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
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            return Build.DISPLAY;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    private String getMcuVersion() {
        try {
            return PowerManagerApp.getStatusString("mcuVerison");
        } catch (Exception e) {
            e.getStackTrace();
            return "";
        }
    }

    public static String getBaseband_Ver() {
        return SystemProperties.get("gsm.version.baseband");
    }

    public void updatMcuVersion() {
        this.mcuVer.set(this.context.getString(R.string.audi_set_sys_info_mcu_ver, new Object[]{McuUtil.getMcuVersion()}));
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
        try {
            String factoryPassword = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            Log.e(TAG, "factoryPassword：" + factoryPassword);
            return factoryPassword;
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "Exception: 获取工厂配置密码失败");
            Log.e(str, "factoryPassword：" + "1314");
            return "1314";
        } catch (Throwable th) {
            Log.e(TAG, "factoryPassword：" + "1314");
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
        onDeleteLongClick((View) null);
        return isCorrect;
    }
}

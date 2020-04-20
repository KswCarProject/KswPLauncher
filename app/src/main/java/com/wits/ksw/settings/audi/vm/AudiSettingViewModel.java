package com.wits.ksw.settings.audi.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.ksw.settings.utlis_view.TimeUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

@BindingMethods({@BindingMethod(attribute = "OnCheckedChangeListener", method = "setOnCheckedChangeListener", type = TimeVm.class)})
public class AudiSettingViewModel extends AndroidViewModel {
    private static final String TAG = ("KSWLauncher." + AudiSettingViewModel.class.getSimpleName());
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
                        case 1:
                            FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                            break;
                    }
                    AudiSettingViewModel.this.updata24HourFormat();
                }
            }
        }
    };
    public RadioGroup.OnCheckedChangeListener onTimeMoedlChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                if (checkedId == group.getChildAt(i).getId()) {
                    FileUtils.savaIntData(KeyConfig.TIME_SOURCE, i);
                    AudiSettingViewModel.this.updataTime();
                }
            }
        }
    };
    private StringBuffer passwordBuffer = new StringBuffer();
    public ObservableField<String> ramVer = new ObservableField<>();
    public ObservableField<String> systemVersion = new ObservableField<>();

    public AudiSettingViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        String appVerStr = this.context.getString(R.string.audi_set_sys_info_app_ver, new Object[]{getAppVersion()});
        String mcuVerStr = this.context.getString(R.string.audi_set_sys_info_mcu_ver, new Object[]{getMcuVersion()});
        String ramStr = this.context.getString(R.string.audi_set_sys_info_ram, new Object[]{UtilsInfo.getRAMSize(this.context)});
        String flashStr = this.context.getString(R.string.audi_set_sys_info_nanndflash, new Object[]{UtilsInfo.getROMSize()});
        this.nandflash.set(flashStr);
        this.ramVer.set(ramStr);
        this.appVer.set(appVerStr);
        this.mcuVer.set(mcuVerStr);
        systemVersion();
        String str = TAG;
        Log.i(str, " \nflashStr : " + flashStr + " \nramVer : " + ramStr + " \nis24Hour : " + TimeUtil.is24HourFormat(this.context) + " \nappVer : " + appVerStr + " \nmcuVer : " + mcuVerStr);
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                new Handler().post(new Runnable() {
                    public void run() {
                        AudiSettingViewModel.this.updatMcuVersion();
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
            String str = TAG;
            Log.i(str, "updataTime: " + timeSync);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updata24HourFormat() {
        try {
            int timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
            this.is24Hour.set(timeZhis == 0);
            String str = TAG;
            Log.i(str, "updata24HourFormat: " + timeZhis);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void systemVersion() {
        String pingtai;
        if (!TextUtils.isEmpty(UtilsInfo.getBaseband_Ver())) {
            String substring = UtilsInfo.getBaseband_Ver().substring(0, 4);
            int index_NA = UtilsInfo.getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = UtilsInfo.getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = UtilsInfo.getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = UtilsInfo.getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = UtilsInfo.getBaseband_Ver().indexOf("8937");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_M501 > -1) {
                pingtai = "M501";
            } else if (index_platform_8953 > -1) {
                pingtai = "8953";
            } else if (index_platform_8937 > -1) {
                pingtai = "8937";
            } else {
                pingtai = "8953";
            }
            ObservableField<String> observableField = this.systemVersion;
            Context context2 = this.context;
            observableField.set(context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai}));
            if (index_NA > -1) {
                ObservableField<String> observableField2 = this.systemVersion;
                Context context3 = this.context;
                observableField2.set(context3.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "NA"}));
                return;
            }
            ObservableField<String> observableField3 = this.systemVersion;
            Context context4 = this.context;
            observableField3.set(context4.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "EA"}));
            return;
        }
        this.systemVersion.set(this.context.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE}));
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
        this.mcuVer.set(this.context.getString(R.string.audi_set_sys_info_mcu_ver, new Object[]{getMcuVersion()}));
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
        String str;
        StringBuilder sb;
        String factoryPassword = "1314";
        try {
            factoryPassword = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            str = TAG;
            sb = new StringBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception: 获取工厂配置密码失败");
            str = TAG;
            sb = new StringBuilder();
        } catch (Throwable th) {
            String str2 = TAG;
            Log.e(str2, "factoryPassword：" + factoryPassword);
            throw th;
        }
        sb.append("factoryPassword：");
        sb.append(factoryPassword);
        Log.e(str, sb.toString());
        return factoryPassword;
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

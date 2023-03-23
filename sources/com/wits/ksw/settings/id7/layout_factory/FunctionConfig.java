package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.id7.bean.DevBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanDevList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class FunctionConfig extends FrameLayout implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "SettingFunction";
    private int ahdsele = 0;
    private int bgkz = 0;
    private int bootMode;
    private RadioGroup bootModeRadiogroup;
    private int bt;
    private int btType = 0;
    private int cam360 = 0;
    private CheckBox cbox_bt;
    private CheckBox cbox_eq_app;
    private CheckBox cbox_frontView;
    private CheckBox cbox_function1;
    private CheckBox cbox_function3;
    private CheckBox cbox_function4;
    private CheckBox cbox_function5;
    private CheckBox cbox_function6;
    private CheckBox cbox_function7;
    private CheckBox cbox_function_google_off;
    private CheckBox cbox_hicar;
    private CheckBox cbox_screencast;
    private CheckBox cbox_sysXcjz;
    private CheckBox cbox_touch_send;
    private int cheVideo = 0;
    private CheckBox chox_function_fcam;
    private int danwei = 0;
    private String defDev = "";
    private String defPlayApp = "";
    /* access modifiers changed from: private */
    public List<DevBean> devBanList;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    private int eqapp = 0;
    private int fcamType;
    private int frontViewMirrorSetting;
    private int funtion1;
    private int funtion3;
    private int funtion4;
    private int funtion5;
    private int funtion6;
    private int funtion7;
    private int gongfang = 0;
    private int googleOff;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private int hicar = 0;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    /* access modifiers changed from: private */
    public List<DevBean> playAppList;
    private RadioGroup rdg_360sx;
    private RadioGroup rdg_Ahd;
    private RadioGroup rdg_bgkz;
    private RadioGroup rdg_dawei;
    private RadioGroup rdg_funbt;
    private RadioGroup rdg_fungf;
    private RadioGroup rdg_funxcjl;
    private int screencast = 0;
    private int touch_send;
    private TextView tv_seleAPk;
    private TextView tv_selePlayAPk;
    private int usbHost = 0;
    private View view;
    private int xingcjl = 0;

    public FunctionConfig(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.layout_function_config, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initUsbHost();
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initUsbHost() {
        if (KswApplication.factoryUsbHost) {
            FileUtils.savaIntData(KeyConfig.USB_HOST, 1);
            KswApplication.factoryUsbHost = false;
        }
    }

    private void initData() {
        String str;
        try {
            String string = Settings.System.getString(this.m_con.getContentResolver(), "defPlayApp");
            this.defPlayApp = string;
            boolean exits = KswUtils.isAppInstalled(string);
            if (TextUtils.isEmpty(this.defPlayApp) || !exits) {
                this.defPlayApp = "com.wits.ksw.media";
            }
            this.cheVideo = PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP);
            this.funtion1 = PowerManagerApp.getSettingsInt(KeyConfig.USB_HOST);
            this.funtion3 = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
            this.funtion4 = PowerManagerApp.getSettingsInt("AUX_Type");
            this.funtion5 = PowerManagerApp.getSettingsInt("DTV_Type");
            this.funtion6 = PowerManagerApp.getSettingsInt(KeyConfig.DISH_BOARD);
            this.funtion7 = PowerManagerApp.getSettingsInt(KeyConfig.TXZ);
            this.cam360 = PowerManagerApp.getSettingsInt(KeyConfig.CAM360);
            this.bgkz = PowerManagerApp.getSettingsInt(KeyConfig.BACKLIGHT);
            this.danwei = PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT);
            this.ahdsele = PowerManagerApp.getSettingsInt(KeyConfig.AHD_Select);
            this.xingcjl = PowerManagerApp.getSettingsInt("DVR_Type");
            this.btType = PowerManagerApp.getSettingsInt(KeyConfig.BT_TYPE);
            this.gongfang = PowerManagerApp.getSettingsInt(KeyConfig.AMP_TYPE);
            this.bootMode = PowerManagerApp.getSettingsInt(KeyConfig.DEFAULT_POWER_BOOT);
            this.bt = PowerManagerApp.getSettingsInt(KeyConfig.Android_Bt_Switch);
            this.touch_send = PowerManagerApp.getSettingsInt(KeyConfig.TOUCH_CONTINUOUS_SEND);
            this.fcamType = PowerManagerApp.getSettingsInt("Front_view_camera");
            this.googleOff = PowerManagerApp.getSettingsInt(KeyConfig.ZLINK_AUTO_START);
            this.hicar = PowerManagerApp.getSettingsInt(KeyConfig.HiCar);
            this.screencast = PowerManagerApp.getSettingsInt(KeyConfig.SCAREEN_CAST);
            this.eqapp = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
            this.frontViewMirrorSetting = PowerManagerApp.getSettingsInt(KeyConfig.FRONT_VIEW_MIRROR_SETTING);
            Log.w(TAG, "initData frontViewMirrorSetting: " + this.frontViewMirrorSetting);
            Log.d(TAG, "initData get data==\tUSBHost:" + this.funtion1 + "\tgoogapp:" + this.funtion3 + "\taux:" + this.funtion4 + "\tdtv:" + this.funtion5 + "\tdishboard:" + this.funtion6 + "\ttxz:" + this.funtion7 + "\tbootMode:" + this.bootMode + "\tscreencast:" + this.screencast + "\tbt:" + this.bt + "\tfcamType:" + this.fcamType + "\tgoogleOff:" + this.googleOff);
            this.defDev = PowerManagerApp.getSettingsString(KeyConfig.DEF_DVRAPK);
            Log.d(TAG, "defDvrApk:" + this.defDev);
            List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this.m_con);
            this.devBanList = new ArrayList();
            for (LexusLsAppSelBean bean : listMusic) {
                if (!"com.suding.speedplay.ui.MainActivity".equals(bean.getAppMainAty())) {
                    DevBean devBean = new DevBean();
                    devBean.setPackageName(bean.getAppPkg());
                    devBean.setAppicon(bean.getAppIcon());
                    devBean.setName(bean.getAppName());
                    if (!"".equals(this.defDev) && (str = this.defDev) != null && str.equals(bean.getAppPkg())) {
                        devBean.setCheck(true);
                    }
                    this.devBanList.add(devBean);
                }
            }
            this.playAppList = ScanDevList.getInstance().playAppList(this.m_con, this.defPlayApp);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        TextView textView = (TextView) this.view.findViewById(R.id.tv_seleAPk);
        this.tv_seleAPk = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FunctionConfig.this.dialogViews.listChoseApk(FunctionConfig.this.devBanList);
            }
        });
        this.cbox_bt = (CheckBox) this.view.findViewById(R.id.cbox_bt);
        this.cbox_touch_send = (CheckBox) this.view.findViewById(R.id.cbox_touch_send);
        CheckBox checkBox = (CheckBox) this.view.findViewById(R.id.cbox_function1);
        this.cbox_function1 = checkBox;
        boolean z = false;
        checkBox.setChecked(this.funtion1 != 0);
        CheckBox checkBox2 = (CheckBox) this.view.findViewById(R.id.cbox_function3);
        this.cbox_function3 = checkBox2;
        checkBox2.setChecked(this.funtion3 != 0);
        CheckBox checkBox3 = (CheckBox) this.view.findViewById(R.id.cbox_function4);
        this.cbox_function4 = checkBox3;
        checkBox3.setChecked(this.funtion4 != 0);
        CheckBox checkBox4 = (CheckBox) this.view.findViewById(R.id.cbox_function5);
        this.cbox_function5 = checkBox4;
        checkBox4.setChecked(this.funtion5 != 0);
        CheckBox checkBox5 = (CheckBox) this.view.findViewById(R.id.cbox_function6);
        this.cbox_function6 = checkBox5;
        checkBox5.setChecked(this.funtion6 != 0);
        CheckBox checkBox6 = (CheckBox) this.view.findViewById(R.id.cbox_function7);
        this.cbox_function7 = checkBox6;
        checkBox6.setChecked(this.funtion7 != 0);
        CheckBox checkBox7 = (CheckBox) this.view.findViewById(R.id.cbox_sysXcjz);
        this.cbox_sysXcjz = checkBox7;
        checkBox7.setChecked(this.cheVideo != 0);
        CheckBox checkBox8 = (CheckBox) this.view.findViewById(R.id.cbox_front_view);
        this.cbox_frontView = checkBox8;
        checkBox8.setChecked(this.frontViewMirrorSetting == 1);
        CheckBox checkBox9 = (CheckBox) this.view.findViewById(R.id.cbox_function_fcam);
        this.chox_function_fcam = checkBox9;
        checkBox9.setChecked(this.fcamType == 1);
        CheckBox checkBox10 = (CheckBox) this.view.findViewById(R.id.cbox_sreencast);
        this.cbox_screencast = checkBox10;
        checkBox10.setChecked(this.screencast == 1);
        CheckBox checkBox11 = (CheckBox) this.view.findViewById(R.id.cbox_eq_app);
        this.cbox_eq_app = checkBox11;
        checkBox11.setChecked(this.eqapp == 1);
        CheckBox checkBox12 = (CheckBox) this.view.findViewById(R.id.cbox_function_google_off);
        this.cbox_function_google_off = checkBox12;
        checkBox12.setChecked(this.googleOff == 1);
        if (UiThemeUtils.isBMW_EVO_ID6(this.m_con) || UiThemeUtils.isBMW_EVO_ID5(this.m_con) || UiThemeUtils.isBMW_EVO_ID6_GS(this.m_con) || UiThemeUtils.isCommon_UI_GS(this.m_con) || UiThemeUtils.isCommon_UI_GS_UG(this.m_con) || UiThemeUtils.isCommon_UI_GS_UG_1024(this.m_con) || UiThemeUtils.isUI_KSW_MBUX_1024(this.m_con) || UiThemeUtils.isALS_ID6(this.m_con) || (UiThemeUtils.isBMW_EVO_ID6_CUSP(this.m_con) && ClientManager.getInstance().isCUSP_210407())) {
            this.cbox_sysXcjz.setVisibility(0);
        } else {
            this.cbox_sysXcjz.setVisibility(8);
        }
        RadioGroup radioGroup = (RadioGroup) this.view.findViewById(R.id.rdg_funxcjl);
        this.rdg_funxcjl = radioGroup;
        switch (this.xingcjl) {
            case 0:
                radioGroup.check(R.id.rdb_funxcjl1);
                this.tv_seleAPk.setVisibility(8);
                break;
            case 1:
                radioGroup.check(R.id.rdb_funxcjl2);
                this.tv_seleAPk.setVisibility(8);
                break;
            case 2:
                radioGroup.check(R.id.rdb_funxcjl3);
                this.tv_seleAPk.setVisibility(0);
                break;
        }
        RadioGroup radioGroup2 = (RadioGroup) this.view.findViewById(R.id.rdg_funbt);
        this.rdg_funbt = radioGroup2;
        switch (this.btType) {
            case 0:
                radioGroup2.check(R.id.rdb_funbt2);
                break;
            case 1:
                radioGroup2.check(R.id.rdb_funbt1);
                break;
        }
        RadioGroup radioGroup3 = (RadioGroup) this.view.findViewById(R.id.rdg_fungf);
        this.rdg_fungf = radioGroup3;
        switch (this.gongfang) {
            case 0:
                radioGroup3.check(R.id.rdg_fungf1);
                break;
            case 1:
                radioGroup3.check(R.id.rdg_fungf2);
                break;
        }
        RadioGroup radioGroup4 = (RadioGroup) this.view.findViewById(R.id.rdg_360sx);
        this.rdg_360sx = radioGroup4;
        switch (this.cam360) {
            case 0:
                radioGroup4.check(R.id.rdg_360sx1);
                break;
            case 1:
                radioGroup4.check(R.id.rdg_360sx2);
                break;
        }
        RadioGroup radioGroup5 = (RadioGroup) this.view.findViewById(R.id.rdg_bgkz);
        this.rdg_bgkz = radioGroup5;
        switch (this.bgkz) {
            case 0:
                radioGroup5.check(R.id.rdg_bgkz1);
                break;
            case 1:
                radioGroup5.check(R.id.rdg_bgkz2);
                break;
        }
        RadioGroup radioGroup6 = (RadioGroup) this.view.findViewById(R.id.rdg_dawei);
        this.rdg_dawei = radioGroup6;
        switch (this.danwei) {
            case 0:
                radioGroup6.check(R.id.rdg_dawei1);
                break;
            case 1:
                radioGroup6.check(R.id.rdg_dawei2);
                break;
        }
        RadioGroup radioGroup7 = (RadioGroup) this.view.findViewById(R.id.rdg_Ahd);
        this.rdg_Ahd = radioGroup7;
        switch (this.ahdsele) {
            case 0:
                radioGroup7.check(R.id.rdg_ahd0);
                break;
            case 1:
                radioGroup7.check(R.id.rdg_ahd1);
                break;
            case 2:
                radioGroup7.check(R.id.rdg_ahd2);
                break;
            case 3:
                radioGroup7.check(R.id.rdg_ahd3);
                break;
            case 4:
                radioGroup7.check(R.id.rdg_ahd4);
                break;
            case 5:
                radioGroup7.check(R.id.rdg_ahd5);
                break;
            case 6:
                radioGroup7.check(R.id.rdg_ahd6);
                break;
            case 7:
                radioGroup7.check(R.id.rdg_ahd7);
                break;
        }
        RadioGroup radioGroup8 = (RadioGroup) this.view.findViewById(R.id.boot_record_mode_radiogroup);
        this.bootModeRadiogroup = radioGroup8;
        switch (this.bootMode) {
            case 0:
                radioGroup8.check(R.id.boot_record_mode_radio1);
                break;
            case 1:
                radioGroup8.check(R.id.boot_record_mode_radio2);
                break;
            case 2:
                radioGroup8.check(R.id.boot_record_mode_radio3);
                break;
        }
        this.cbox_bt.setOnCheckedChangeListener(this);
        this.cbox_screencast.setOnCheckedChangeListener(this);
        this.cbox_eq_app.setOnCheckedChangeListener(this);
        this.cbox_touch_send.setOnCheckedChangeListener(this);
        this.cbox_sysXcjz.setOnCheckedChangeListener(this);
        this.cbox_frontView.setOnCheckedChangeListener(this);
        this.cbox_function1.setOnCheckedChangeListener(this);
        this.cbox_function3.setOnCheckedChangeListener(this);
        this.cbox_function4.setOnCheckedChangeListener(this);
        this.cbox_function5.setOnCheckedChangeListener(this);
        this.cbox_function6.setOnCheckedChangeListener(this);
        this.cbox_function7.setOnCheckedChangeListener(this);
        this.chox_function_fcam.setOnCheckedChangeListener(this);
        this.cbox_function_google_off.setOnCheckedChangeListener(this);
        this.rdg_funxcjl.setOnCheckedChangeListener(this);
        this.rdg_funbt.setOnCheckedChangeListener(this);
        this.rdg_fungf.setOnCheckedChangeListener(this);
        this.rdg_360sx.setOnCheckedChangeListener(this);
        this.rdg_bgkz.setOnCheckedChangeListener(this);
        this.rdg_dawei.setOnCheckedChangeListener(this);
        this.rdg_Ahd.setOnCheckedChangeListener(this);
        this.bootModeRadiogroup.setOnCheckedChangeListener(this);
        CheckBox checkBox13 = (CheckBox) this.view.findViewById(R.id.cbox_hicar);
        this.cbox_hicar = checkBox13;
        checkBox13.setChecked(this.hicar == 1);
        this.cbox_hicar.setOnCheckedChangeListener(this);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tv_selePlayAPk);
        this.tv_selePlayAPk = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FunctionConfig.this.dialogViews.listPlayApk(FunctionConfig.this.playAppList);
            }
        });
        this.cbox_bt.setChecked(this.bt == 1);
        CheckBox checkBox14 = this.cbox_touch_send;
        if (this.touch_send == 1) {
            z = true;
        }
        checkBox14.setChecked(z);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbox_bt:
                FileUtils.savaData(KeyConfig.Android_Bt_Switch, isChecked);
                return;
            case R.id.cbox_eq_app:
                FileUtils.savaIntData(KeyConfig.EQ_APP, isChecked);
                return;
            case R.id.cbox_front_view:
                FileUtils.savaData(KeyConfig.FRONT_VIEW_MIRROR_SETTING, isChecked);
                return;
            case R.id.cbox_function1:
                FileUtils.savaData(KeyConfig.USB_HOST, isChecked);
                return;
            case R.id.cbox_function3:
                FileUtils.savaData(KeyConfig.GOOGLE_APP, isChecked);
                if (UiThemeUtils.isUI_GS_ID8(this.m_con)) {
                    if (IconUtils.GOOGLE_MAP.equals(Settings.System.getString(this.m_con.getContentResolver(), "wits_freedom_pkg")) && !isChecked) {
                        Settings.System.putString(this.m_con.getContentResolver(), "wits_freedom_pkg", "");
                        return;
                    }
                    return;
                }
                return;
            case R.id.cbox_function4:
                FileUtils.savaData("AUX_Type", isChecked);
                return;
            case R.id.cbox_function5:
                FileUtils.savaData("DTV_Type", isChecked);
                return;
            case R.id.cbox_function6:
                FileUtils.savaData(KeyConfig.DISH_BOARD, isChecked);
                return;
            case R.id.cbox_function7:
                FileUtils.savaData(KeyConfig.TXZ, isChecked);
                return;
            case R.id.cbox_function_fcam:
                FileUtils.savaData("Front_view_camera", isChecked);
                return;
            case R.id.cbox_function_google_off:
                FileUtils.savaData(KeyConfig.ZLINK_AUTO_START, isChecked);
                return;
            case R.id.cbox_hicar:
                FileUtils.savaIntData(KeyConfig.HiCar, isChecked);
                return;
            case R.id.cbox_sreencast:
                FileUtils.savaIntData(KeyConfig.SCAREEN_CAST, isChecked);
                return;
            case R.id.cbox_sysXcjz:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            case R.id.cbox_touch_send:
                FileUtils.savaData(KeyConfig.TOUCH_CONTINUOUS_SEND, isChecked);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.boot_record_mode_radio1:
                Log.i(TAG, "onCheckedChanged: set booMdode: 0");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 0);
                return;
            case R.id.boot_record_mode_radio2:
                Log.i(TAG, "onCheckedChanged: set booMdode: 1");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 1);
                return;
            case R.id.boot_record_mode_radio3:
                Log.i(TAG, "onCheckedChanged: set booMdode: 2");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 2);
                return;
            case R.id.rdb_funbt1:
                FileUtils.savaIntData(KeyConfig.BT_TYPE, 1);
                return;
            case R.id.rdb_funbt2:
                FileUtils.savaIntData(KeyConfig.BT_TYPE, 0);
                return;
            case R.id.rdb_funxcjl1:
                FileUtils.savaIntData("DVR_Type", 0);
                this.tv_seleAPk.setVisibility(8);
                return;
            case R.id.rdb_funxcjl2:
                FileUtils.savaIntData("DVR_Type", 1);
                this.tv_seleAPk.setVisibility(8);
                return;
            case R.id.rdb_funxcjl3:
                FileUtils.savaIntData("DVR_Type", 2);
                this.tv_seleAPk.setVisibility(0);
                return;
            case R.id.rdg_360sx1:
                FileUtils.savaIntData(KeyConfig.CAM360, 0);
                return;
            case R.id.rdg_360sx2:
                FileUtils.savaIntData(KeyConfig.CAM360, 1);
                return;
            case R.id.rdg_ahd0:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 0);
                return;
            case R.id.rdg_ahd1:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 1);
                return;
            case R.id.rdg_ahd2:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 2);
                return;
            case R.id.rdg_ahd3:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 3);
                return;
            case R.id.rdg_ahd4:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 4);
                return;
            case R.id.rdg_ahd5:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 5);
                return;
            case R.id.rdg_ahd6:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 6);
                return;
            case R.id.rdg_ahd7:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 7);
                return;
            case R.id.rdg_bgkz1:
                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 0);
                return;
            case R.id.rdg_bgkz2:
                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 1);
                return;
            case R.id.rdg_dawei1:
                FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 0);
                return;
            case R.id.rdg_dawei2:
                FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 1);
                return;
            case R.id.rdg_fungf1:
                FileUtils.savaIntData(KeyConfig.AMP_TYPE, 0);
                return;
            case R.id.rdg_fungf2:
                FileUtils.savaIntData(KeyConfig.AMP_TYPE, 1);
                return;
            default:
                return;
        }
    }
}

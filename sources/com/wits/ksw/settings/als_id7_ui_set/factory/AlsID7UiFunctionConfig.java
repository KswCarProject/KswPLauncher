package com.wits.ksw.settings.als_id7_ui_set.factory;

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
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.id7.bean.DevBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanDevList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class AlsID7UiFunctionConfig extends FrameLayout implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "AlsID7UiFunctionConfig";
    private int ahdsele = 0;
    private int bgkz = 0;
    private int bootMode;
    private RadioGroup bootModeRadiogroup;
    private int bt;
    private int btType = 0;
    private int cam360 = 0;
    private CheckBox cbox_bt;
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
    private int fcamType;
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

    public AlsID7UiFunctionConfig(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.als_id7_ui_layout_function_config, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
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
            Log.d(TAG, "initData get data==\tUSBHost:" + this.funtion1 + "\tgoogapp:" + this.funtion3 + "\taux:" + this.funtion4 + "\tdtv:" + this.funtion5 + "\tdishboard:" + this.funtion6 + "\ttxz:" + this.funtion7 + "\tbootMode:" + this.bootMode + "\tscreencast:" + this.screencast + "\tbt:" + this.bt + "\tfcamType:" + this.fcamType + "\tgoogleOff:" + this.googleOff);
            this.defDev = PowerManagerApp.getSettingsString(KeyConfig.DEF_DVRAPK);
            Log.d(TAG, "defDvrApk:" + this.defDev);
            List<String> devList = PowerManagerApp.getDataListFromJsonKey(KeyConfig.DVRAPKLIST);
            if (devList != null && devList.size() > 0) {
                this.devBanList = ScanDevList.getInstance().scanList(devList, this.defDev, this.m_con);
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
                AlsID7UiFunctionConfig.this.dialogViews.listChoseApk(AlsID7UiFunctionConfig.this.devBanList);
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
        CheckBox checkBox8 = (CheckBox) this.view.findViewById(R.id.cbox_function_fcam);
        this.chox_function_fcam = checkBox8;
        checkBox8.setChecked(this.fcamType == 1);
        CheckBox checkBox9 = (CheckBox) this.view.findViewById(R.id.cbox_sreencast);
        this.cbox_screencast = checkBox9;
        checkBox9.setChecked(this.screencast == 1);
        this.cbox_screencast.setVisibility(0);
        CheckBox checkBox10 = (CheckBox) this.view.findViewById(R.id.cbox_function_google_off);
        this.cbox_function_google_off = checkBox10;
        checkBox10.setChecked(this.googleOff == 1);
        if (UiThemeUtils.isBMW_EVO_ID6(this.m_con) || UiThemeUtils.isBMW_EVO_ID5(this.m_con) || UiThemeUtils.isCommon_UI_GS(this.m_con) || (UiThemeUtils.isBMW_EVO_ID6_CUSP(this.m_con) && ClientManager.getInstance().isCUSP_210407())) {
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
        this.cbox_touch_send.setOnCheckedChangeListener(this);
        this.cbox_sysXcjz.setOnCheckedChangeListener(this);
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
        CheckBox checkBox11 = (CheckBox) this.view.findViewById(R.id.cbox_hicar);
        this.cbox_hicar = checkBox11;
        checkBox11.setChecked(this.hicar == 1);
        this.cbox_hicar.setOnCheckedChangeListener(this);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tv_selePlayAPk);
        this.tv_selePlayAPk = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlsID7UiFunctionConfig.this.dialogViews.listPlayApk(AlsID7UiFunctionConfig.this.playAppList);
            }
        });
        this.cbox_bt.setChecked(this.bt == 1);
        CheckBox checkBox12 = this.cbox_touch_send;
        if (this.touch_send == 1) {
            z = true;
        }
        checkBox12.setChecked(z);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbox_bt /*2131296557*/:
                FileUtils.savaData(KeyConfig.Android_Bt_Switch, isChecked);
                return;
            case R.id.cbox_function1 /*2131296560*/:
                FileUtils.savaData(KeyConfig.USB_HOST, isChecked);
                return;
            case R.id.cbox_function3 /*2131296561*/:
                FileUtils.savaData(KeyConfig.GOOGLE_APP, isChecked);
                return;
            case R.id.cbox_function4 /*2131296562*/:
                FileUtils.savaData("AUX_Type", isChecked);
                return;
            case R.id.cbox_function5 /*2131296563*/:
                FileUtils.savaData("DTV_Type", isChecked);
                return;
            case R.id.cbox_function6 /*2131296564*/:
                FileUtils.savaData(KeyConfig.DISH_BOARD, isChecked);
                return;
            case R.id.cbox_function7 /*2131296565*/:
                FileUtils.savaData(KeyConfig.TXZ, isChecked);
                return;
            case R.id.cbox_function_fcam /*2131296566*/:
                FileUtils.savaData("Front_view_camera", isChecked);
                return;
            case R.id.cbox_function_google_off /*2131296567*/:
                FileUtils.savaData(KeyConfig.ZLINK_AUTO_START, isChecked);
                return;
            case R.id.cbox_hicar /*2131296568*/:
                FileUtils.savaIntData(KeyConfig.HiCar, isChecked);
                return;
            case R.id.cbox_sreencast /*2131296570*/:
                FileUtils.savaIntData(KeyConfig.SCAREEN_CAST, isChecked);
                return;
            case R.id.cbox_sysXcjz /*2131296575*/:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            case R.id.cbox_touch_send /*2131296576*/:
                FileUtils.savaData(KeyConfig.TOUCH_CONTINUOUS_SEND, isChecked);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.boot_record_mode_radio1 /*2131296489*/:
                Log.i(TAG, "onCheckedChanged: set booMdode: 0");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 0);
                return;
            case R.id.boot_record_mode_radio2 /*2131296490*/:
                Log.i(TAG, "onCheckedChanged: set booMdode: 1");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 1);
                return;
            case R.id.boot_record_mode_radio3 /*2131296491*/:
                Log.i(TAG, "onCheckedChanged: set booMdode: 2");
                FileUtils.savaIntData(KeyConfig.DEFAULT_POWER_BOOT, 2);
                return;
            case R.id.rdb_funbt1 /*2131297181*/:
                FileUtils.savaIntData(KeyConfig.BT_TYPE, 1);
                return;
            case R.id.rdb_funbt2 /*2131297182*/:
                FileUtils.savaIntData(KeyConfig.BT_TYPE, 0);
                return;
            case R.id.rdb_funxcjl1 /*2131297183*/:
                FileUtils.savaIntData("DVR_Type", 0);
                this.tv_seleAPk.setVisibility(8);
                return;
            case R.id.rdb_funxcjl2 /*2131297184*/:
                FileUtils.savaIntData("DVR_Type", 1);
                this.tv_seleAPk.setVisibility(8);
                return;
            case R.id.rdb_funxcjl3 /*2131297185*/:
                FileUtils.savaIntData("DVR_Type", 2);
                this.tv_seleAPk.setVisibility(0);
                return;
            case R.id.rdg_360sx1 /*2131297206*/:
                FileUtils.savaIntData(KeyConfig.CAM360, 0);
                return;
            case R.id.rdg_360sx2 /*2131297207*/:
                FileUtils.savaIntData(KeyConfig.CAM360, 1);
                return;
            case R.id.rdg_ahd0 /*2131297210*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 0);
                return;
            case R.id.rdg_ahd1 /*2131297211*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 1);
                return;
            case R.id.rdg_ahd2 /*2131297212*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 2);
                return;
            case R.id.rdg_ahd3 /*2131297213*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 3);
                return;
            case R.id.rdg_ahd4 /*2131297214*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 4);
                return;
            case R.id.rdg_ahd5 /*2131297215*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 5);
                return;
            case R.id.rdg_ahd6 /*2131297216*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 6);
                return;
            case R.id.rdg_ahd7 /*2131297217*/:
                FileUtils.savaIntData(KeyConfig.AHD_Select, 7);
                return;
            case R.id.rdg_bgkz1 /*2131297223*/:
                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 0);
                return;
            case R.id.rdg_bgkz2 /*2131297224*/:
                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 1);
                return;
            case R.id.rdg_dawei1 /*2131297234*/:
                FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 0);
                return;
            case R.id.rdg_dawei2 /*2131297235*/:
                FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 1);
                return;
            case R.id.rdg_fungf1 /*2131297267*/:
                FileUtils.savaIntData(KeyConfig.AMP_TYPE, 0);
                return;
            case R.id.rdg_fungf2 /*2131297268*/:
                FileUtils.savaIntData(KeyConfig.AMP_TYPE, 1);
                return;
            default:
                return;
        }
    }
}

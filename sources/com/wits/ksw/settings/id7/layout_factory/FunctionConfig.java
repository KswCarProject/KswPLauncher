package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
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
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.id7.bean.DevBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanDevList;
import com.wits.pms.statuscontrol.PowerManagerApp;
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
    private CheckBox cbox_function1;
    private CheckBox cbox_function3;
    private CheckBox cbox_function4;
    private CheckBox cbox_function5;
    private CheckBox cbox_function6;
    private CheckBox cbox_function7;
    private CheckBox cbox_function_google_off;
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
    private int touch_send;
    private TextView tv_seleAPk;
    private TextView tv_selePlayAPk;
    private int usbHost = 0;
    private View view;
    private int xingcjl = 0;

    public FunctionConfig(@NonNull Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(this.m_con).inflate(R.layout.layout_function_config, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.defPlayApp = Settings.System.getString(this.m_con.getContentResolver(), "defPlayApp");
            boolean exits = KswUtils.isAppInstalled(this.defPlayApp);
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
            Log.d(TAG, "initData get data==\tUSBHost:" + this.funtion1 + "\tgoogapp:" + this.funtion3 + "\taux:" + this.funtion4 + "\tdtv:" + this.funtion5 + "\tdishboard:" + this.funtion6 + "\ttxz:" + this.funtion7 + "\tbootMode:" + this.bootMode + "\tbt:" + this.bt + "\tfcamType:" + this.fcamType + "\tgoogleOff:" + this.googleOff);
            this.defDev = PowerManagerApp.getSettingsString(KeyConfig.DEF_DVRAPK);
            StringBuilder sb = new StringBuilder();
            sb.append("defDvrApk:");
            sb.append(this.defDev);
            Log.d(TAG, sb.toString());
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
        this.tv_seleAPk = (TextView) this.view.findViewById(R.id.tv_seleAPk);
        this.tv_seleAPk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FunctionConfig.this.dialogViews.listChoseApk(FunctionConfig.this.devBanList);
            }
        });
        this.cbox_bt = (CheckBox) this.view.findViewById(R.id.cbox_bt);
        this.cbox_touch_send = (CheckBox) this.view.findViewById(R.id.cbox_touch_send);
        this.cbox_function1 = (CheckBox) this.view.findViewById(R.id.cbox_function1);
        boolean z = false;
        this.cbox_function1.setChecked(this.funtion1 != 0);
        this.cbox_function3 = (CheckBox) this.view.findViewById(R.id.cbox_function3);
        this.cbox_function3.setChecked(this.funtion3 != 0);
        this.cbox_function4 = (CheckBox) this.view.findViewById(R.id.cbox_function4);
        this.cbox_function4.setChecked(this.funtion4 != 0);
        this.cbox_function5 = (CheckBox) this.view.findViewById(R.id.cbox_function5);
        this.cbox_function5.setChecked(this.funtion5 != 0);
        this.cbox_function6 = (CheckBox) this.view.findViewById(R.id.cbox_function6);
        this.cbox_function6.setChecked(this.funtion6 != 0);
        this.cbox_function7 = (CheckBox) this.view.findViewById(R.id.cbox_function7);
        this.cbox_function7.setChecked(this.funtion7 != 0);
        this.cbox_sysXcjz = (CheckBox) this.view.findViewById(R.id.cbox_sysXcjz);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.chox_function_fcam = (CheckBox) this.view.findViewById(R.id.cbox_function_fcam);
        this.chox_function_fcam.setChecked(this.fcamType == 1);
        this.cbox_function_google_off = (CheckBox) this.view.findViewById(R.id.cbox_function_google_off);
        this.cbox_function_google_off.setChecked(this.googleOff == 1);
        if (UiThemeUtils.isBMW_EVO_ID6(this.m_con) || UiThemeUtils.isBMW_EVO_ID5(this.m_con) || UiThemeUtils.isCommon_UI_GS(this.m_con)) {
            this.cbox_sysXcjz.setVisibility(0);
        } else {
            this.cbox_sysXcjz.setVisibility(8);
        }
        this.rdg_funxcjl = (RadioGroup) this.view.findViewById(R.id.rdg_funxcjl);
        switch (this.xingcjl) {
            case 0:
                this.rdg_funxcjl.check(R.id.rdb_funxcjl1);
                this.tv_seleAPk.setVisibility(8);
                break;
            case 1:
                this.rdg_funxcjl.check(R.id.rdb_funxcjl2);
                this.tv_seleAPk.setVisibility(8);
                break;
            case 2:
                this.rdg_funxcjl.check(R.id.rdb_funxcjl3);
                this.tv_seleAPk.setVisibility(0);
                break;
        }
        this.rdg_funbt = (RadioGroup) this.view.findViewById(R.id.rdg_funbt);
        switch (this.btType) {
            case 0:
                this.rdg_funbt.check(R.id.rdb_funbt2);
                break;
            case 1:
                this.rdg_funbt.check(R.id.rdb_funbt1);
                break;
        }
        this.rdg_fungf = (RadioGroup) this.view.findViewById(R.id.rdg_fungf);
        switch (this.gongfang) {
            case 0:
                this.rdg_fungf.check(R.id.rdg_fungf1);
                break;
            case 1:
                this.rdg_fungf.check(R.id.rdg_fungf2);
                break;
        }
        this.rdg_360sx = (RadioGroup) this.view.findViewById(R.id.rdg_360sx);
        switch (this.cam360) {
            case 0:
                this.rdg_360sx.check(R.id.rdg_360sx1);
                break;
            case 1:
                this.rdg_360sx.check(R.id.rdg_360sx2);
                break;
        }
        this.rdg_bgkz = (RadioGroup) this.view.findViewById(R.id.rdg_bgkz);
        switch (this.bgkz) {
            case 0:
                this.rdg_bgkz.check(R.id.rdg_bgkz1);
                break;
            case 1:
                this.rdg_bgkz.check(R.id.rdg_bgkz2);
                break;
        }
        this.rdg_dawei = (RadioGroup) this.view.findViewById(R.id.rdg_dawei);
        switch (this.danwei) {
            case 0:
                this.rdg_dawei.check(R.id.rdg_dawei1);
                break;
            case 1:
                this.rdg_dawei.check(R.id.rdg_dawei2);
                break;
        }
        this.rdg_Ahd = (RadioGroup) this.view.findViewById(R.id.rdg_Ahd);
        switch (this.ahdsele) {
            case 0:
                this.rdg_Ahd.check(R.id.rdg_ahd0);
                break;
            case 1:
                this.rdg_Ahd.check(R.id.rdg_ahd1);
                break;
            case 2:
                this.rdg_Ahd.check(R.id.rdg_ahd2);
                break;
            case 3:
                this.rdg_Ahd.check(R.id.rdg_ahd3);
                break;
            case 4:
                this.rdg_Ahd.check(R.id.rdg_ahd4);
                break;
            case 5:
                this.rdg_Ahd.check(R.id.rdg_ahd5);
                break;
            case 6:
                this.rdg_Ahd.check(R.id.rdg_ahd6);
                break;
            case 7:
                this.rdg_Ahd.check(R.id.rdg_ahd7);
                break;
        }
        this.bootModeRadiogroup = (RadioGroup) this.view.findViewById(R.id.boot_record_mode_radiogroup);
        switch (this.bootMode) {
            case 0:
                this.bootModeRadiogroup.check(R.id.boot_record_mode_radio1);
                break;
            case 1:
                this.bootModeRadiogroup.check(R.id.boot_record_mode_radio2);
                break;
            case 2:
                this.bootModeRadiogroup.check(R.id.boot_record_mode_radio3);
                break;
        }
        this.cbox_bt.setOnCheckedChangeListener(this);
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
        this.tv_selePlayAPk = (TextView) this.view.findViewById(R.id.tv_selePlayAPk);
        this.tv_selePlayAPk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FunctionConfig.this.dialogViews.listPlayApk(FunctionConfig.this.playAppList);
            }
        });
        this.cbox_bt.setChecked(this.bt == 1);
        CheckBox checkBox = this.cbox_touch_send;
        if (this.touch_send == 1) {
            z = true;
        }
        checkBox.setChecked(z);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id != R.id.cbox_bt) {
            switch (id) {
                case R.id.cbox_function1:
                    FileUtils.savaData(KeyConfig.USB_HOST, isChecked);
                    return;
                case R.id.cbox_function3:
                    FileUtils.savaData(KeyConfig.GOOGLE_APP, isChecked);
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
                default:
                    switch (id) {
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
        } else {
            FileUtils.savaData(KeyConfig.Android_Bt_Switch, isChecked);
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
            default:
                switch (checkedId) {
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
                    default:
                        switch (checkedId) {
                            case R.id.rdg_360sx1:
                                FileUtils.savaIntData(KeyConfig.CAM360, 0);
                                return;
                            case R.id.rdg_360sx2:
                                FileUtils.savaIntData(KeyConfig.CAM360, 1);
                                return;
                            default:
                                switch (checkedId) {
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
                                    default:
                                        switch (checkedId) {
                                            case R.id.rdg_bgkz1:
                                                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 0);
                                                return;
                                            case R.id.rdg_bgkz2:
                                                FileUtils.savaIntData(KeyConfig.BACKLIGHT, 1);
                                                return;
                                            default:
                                                switch (checkedId) {
                                                    case R.id.rdg_dawei1:
                                                        FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 0);
                                                        return;
                                                    case R.id.rdg_dawei2:
                                                        FileUtils.savaIntData(KeyConfig.DASHBOARDUNIT, 1);
                                                        return;
                                                    default:
                                                        switch (checkedId) {
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
                                }
                        }
                }
        }
    }
}

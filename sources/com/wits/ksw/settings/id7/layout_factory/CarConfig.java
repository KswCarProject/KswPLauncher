package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class CarConfig extends FrameLayout implements RadioGroup.OnCheckedChangeListener {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + CarConfig.class.getSimpleName());
    /* access modifiers changed from: private */
    public RadioGroup audiHomeLeftRadioGroup;
    /* access modifiers changed from: private */
    public RadioGroup audiHomeRightRadioGroup;
    private int audiLeftLogoIndex;
    private int audiRightWidgetIndex;
    private TextView audi_home_left_widget_textview;
    private TextView audi_home_right_widget_textview;
    private int auxsw = 0;
    private int benzpane = 0;
    private int canS = 0;
    private int carManufacturer = 0;
    private int cardoor = 0;
    private int carseep = 0;
    private CheckBox cbox_ac_control;
    private CheckBox cbox_air_con;
    private CheckBox cbox_bencAux;
    private CheckBox cbox_bencPank;
    /* access modifiers changed from: private */
    public RadioGroup cbox_bencPank_rgd;
    private View cbox_bencPank_root;
    private CheckBox cbox_canBus;
    private CheckBox cbox_dcld;
    private CheckBox cbox_oem_fm;
    private TextView ccciDTextView;
    private int cccid = 0;
    private int danwei = 0;
    private int doornumb = 0;
    private int isOldBmwx = 0;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int mapkey = 0;
    private int modekey = 0;
    private int nbauxsw = 0;
    private RadioGroup rdg_Nbtauxsw;
    private RadioGroup rdg_auxsw;
    /* access modifiers changed from: private */
    public RadioGroup rdg_can;
    private RadioGroup rdg_cardoor;
    private RadioGroup rdg_ccciD;
    private RadioGroup rdg_factory_carseep;
    private RadioGroup rdg_factory_danwei;
    private RadioGroup rdg_factory_mapkey;
    private RadioGroup rdg_factory_modekey;
    private RadioGroup rdg_factory_yuyinkey;
    private RadioGroup rdg_numdoor;
    private RadioGroup rdg_track;
    private int track = 0;
    private View view;
    private int yuyinkey = 0;

    public CarConfig(@NonNull Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(this.m_con).inflate(R.layout.factory_car_config, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.isOldBmwx = PowerManagerApp.getSettingsInt(KeyConfig.BAO_MA_DISPLAY);
            this.auxsw = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_SW);
            this.nbauxsw = PowerManagerApp.getSettingsInt(KeyConfig.NBT_AUX_SW);
            this.cccid = PowerManagerApp.getSettingsInt(KeyConfig.CCC_ID);
            this.cardoor = PowerManagerApp.getSettingsInt(KeyConfig.CAR_DOOR_SELECT);
            this.carseep = PowerManagerApp.getSettingsInt(KeyConfig.DASH_MAX_SPEED);
            this.danwei = PowerManagerApp.getSettingsInt(KeyConfig.HAND_SET_AUTOMATIC);
            this.doornumb = PowerManagerApp.getSettingsInt(KeyConfig.CAR_DOOR_NUM);
            this.yuyinkey = PowerManagerApp.getSettingsInt(KeyConfig.VOICE_KEY);
            this.mapkey = PowerManagerApp.getSettingsInt(KeyConfig.MAP_KEY);
            this.modekey = PowerManagerApp.getSettingsInt(KeyConfig.MODE_KEY);
            this.benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
            this.audiLeftLogoIndex = PowerManagerApp.getSettingsInt(KeyConfig.AUDI_UI_LEFT_ID);
            this.audiRightWidgetIndex = PowerManagerApp.getSettingsInt(KeyConfig.AUDI_UI_RIGHT_ID);
            this.carManufacturer = PowerManagerApp.getSettingsInt(KeyConfig.CarManufacturer);
            this.canS = PowerManagerApp.getSettingsInt(KeyConfig.CAN_BUS_TYPE);
            this.track = PowerManagerApp.getSettingsInt(KeyConfig.DRIVE_TRACK);
            String str = TAG;
            Log.d(str, "initData: " + this.carManufacturer + " " + this.canS + " " + this.track);
            if (this.carManufacturer == 0) {
                this.carManufacturer = UiThemeUtils.getCarManufacturer(this.m_con);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        int airCon;
        this.cbox_dcld = (CheckBox) this.view.findViewById(R.id.cbox_dcld);
        this.cbox_canBus = (CheckBox) this.view.findViewById(R.id.cbox_canBus);
        this.cbox_bencAux = (CheckBox) this.view.findViewById(R.id.cbox_bencAux);
        this.cbox_air_con = (CheckBox) this.view.findViewById(R.id.cbox_air_con);
        this.cbox_ac_control = (CheckBox) this.view.findViewById(R.id.cbox_ac_control);
        this.cbox_oem_fm = (CheckBox) this.view.findViewById(R.id.cbox_oem_fm);
        int i = 0;
        this.cbox_canBus.setChecked(false);
        boolean z = true;
        this.cbox_dcld.setChecked(this.isOldBmwx == 0);
        this.cbox_dcld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.BAO_MA_DISPLAY, 0);
                } else {
                    FileUtils.savaIntData(KeyConfig.BAO_MA_DISPLAY, 1);
                }
            }
        });
        this.cbox_bencAux.setChecked(false);
        try {
            this.cbox_bencAux.setChecked(PowerManagerApp.getSettingsInt("benz_aux_switch") == 1);
        } catch (RemoteException e) {
        }
        this.cbox_bencAux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData("benz_aux_switch", isChecked);
            }
        });
        if (this.carManufacturer == 1 || this.carManufacturer == 3) {
            airCon = Settings.System.getInt(this.m_con.getContentResolver(), "air_conditioner", 0);
        } else {
            airCon = Settings.System.getInt(this.m_con.getContentResolver(), "air_conditioner", 1);
        }
        int acControl = Settings.System.getInt(this.m_con.getContentResolver(), KeyConfig.AC_CONTROL, 0);
        int oemFM = Settings.System.getInt(this.m_con.getContentResolver(), KeyConfig.OEM_FM, 0);
        this.cbox_air_con.setChecked(airCon == 1);
        this.cbox_air_con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData("air_conditioner", isChecked);
            }
        });
        this.cbox_ac_control.setChecked(acControl == 1);
        this.cbox_ac_control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.AC_CONTROL, isChecked);
            }
        });
        this.cbox_oem_fm.setChecked(oemFM == 1);
        this.cbox_oem_fm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.OEM_FM, isChecked);
            }
        });
        this.cbox_bencPank = (CheckBox) this.view.findViewById(R.id.cbox_bencPank);
        this.cbox_bencPank.setChecked(this.benzpane != 0);
        this.cbox_bencPank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.BENZPANE, 1);
                } else {
                    FileUtils.savaIntData(KeyConfig.BENZPANE, 0);
                }
            }
        });
        this.cbox_bencPank_root = this.view.findViewById(R.id.cbox_bencPank_root);
        this.cbox_bencPank_rgd = (RadioGroup) this.view.findViewById(R.id.cbox_bencPank_rgd);
        int count = this.cbox_bencPank_rgd.getChildCount();
        int i2 = 0;
        while (i2 < count) {
            ((RadioButton) this.cbox_bencPank_rgd.getChildAt(i2)).setChecked(this.benzpane == i2);
            i2++;
        }
        this.cbox_bencPank_rgd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = CarConfig.this.cbox_bencPank_rgd.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == CarConfig.this.cbox_bencPank_rgd.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.BENZPANE, i);
                        String access$100 = CarConfig.TAG;
                        Log.i(access$100, "save BenzPanelEnable : " + i);
                    }
                }
            }
        });
        try {
            this.cbox_canBus.setChecked(PowerManagerApp.getSettingsInt("can_bus_switch") == 1);
        } catch (RemoteException e2) {
        }
        this.cbox_canBus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    PowerManagerApp.setSettingsInt("can_bus_switch", isChecked);
                    if (isChecked) {
                        CarConfig.this.rdg_can.setEnabled(false);
                        CarConfig.this.rdg_can.findViewById(R.id.rdg_can1).setEnabled(false);
                        CarConfig.this.rdg_can.findViewById(R.id.rdg_can2).setEnabled(false);
                        return;
                    }
                    CarConfig.this.rdg_can.setEnabled(true);
                    CarConfig.this.rdg_can.findViewById(R.id.rdg_can1).setEnabled(true);
                    CarConfig.this.rdg_can.findViewById(R.id.rdg_can2).setEnabled(true);
                } catch (RemoteException e) {
                }
            }
        });
        this.rdg_auxsw = (RadioGroup) this.view.findViewById(R.id.rdg_auxsw);
        switch (this.auxsw) {
            case 0:
                this.rdg_auxsw.check(R.id.rdb_auxsw1);
                break;
            case 1:
                this.rdg_auxsw.check(R.id.rdb_auxsw2);
                break;
        }
        this.rdg_Nbtauxsw = (RadioGroup) this.view.findViewById(R.id.rdg_Nbtauxsw);
        switch (this.nbauxsw) {
            case 1:
                this.rdg_Nbtauxsw.check(R.id.rdb_Nbtauxsw1);
                break;
            case 2:
                this.rdg_Nbtauxsw.check(R.id.rdb_Nbtauxsw2);
                break;
            case 3:
                this.rdg_Nbtauxsw.check(R.id.rdb_Nbtauxsw3);
                break;
        }
        this.rdg_ccciD = (RadioGroup) this.view.findViewById(R.id.rdg_ccciD);
        switch (this.cccid) {
            case 0:
                this.rdg_ccciD.check(R.id.rdb_ccciD1);
                break;
            case 1:
                this.rdg_ccciD.check(R.id.rdb_ccciD2);
                break;
        }
        this.rdg_cardoor = (RadioGroup) this.view.findViewById(R.id.rdg_cardoor);
        switch (this.cardoor) {
            case 0:
            case 2:
                this.rdg_cardoor.check(R.id.rdg_cardoor1);
                break;
            case 1:
                this.rdg_cardoor.check(R.id.rdg_cardoor2);
                break;
        }
        this.rdg_factory_carseep = (RadioGroup) this.view.findViewById(R.id.rdg_factory_carseep);
        switch (this.carseep) {
            case 0:
                this.rdg_factory_carseep.check(R.id.rdg_factory_carseep1);
                break;
            case 1:
                this.rdg_factory_carseep.check(R.id.rdg_factory_carseep2);
                break;
            case 2:
                this.rdg_factory_carseep.check(R.id.rdg_factory_carseep3);
                break;
            case 3:
                this.rdg_factory_carseep.check(R.id.rdg_factory_carseep4);
                break;
        }
        this.rdg_factory_danwei = (RadioGroup) this.view.findViewById(R.id.rdg_factory_danwei);
        switch (this.danwei) {
            case 0:
                this.rdg_factory_danwei.check(R.id.rdg_factory_danwei1);
                break;
            case 1:
                this.rdg_factory_danwei.check(R.id.rdg_factory_danwei2);
                break;
        }
        this.rdg_numdoor = (RadioGroup) this.view.findViewById(R.id.rdg_numdoor);
        switch (this.doornumb) {
            case 0:
                this.rdg_numdoor.check(R.id.rdg_numdoor1);
                break;
            case 1:
                this.rdg_numdoor.check(R.id.rdg_numdoor2);
                break;
            case 2:
                this.rdg_numdoor.check(R.id.rdg_cardoor3);
                break;
        }
        this.rdg_factory_mapkey = (RadioGroup) this.view.findViewById(R.id.rdg_factory_mapkey);
        Log.d("CarConfig", "mapkey====get====>" + this.mapkey);
        switch (this.mapkey) {
            case 0:
                this.rdg_factory_mapkey.check(R.id.rdg_factory_mapkey1);
                break;
            case 1:
                this.rdg_factory_mapkey.check(R.id.rdg_factory_mapkey2);
                break;
        }
        this.rdg_factory_modekey = (RadioGroup) this.view.findViewById(R.id.rdg_factory_modekey);
        switch (this.modekey) {
            case 0:
                this.rdg_factory_modekey.check(R.id.rdg_factory_modekey1);
                break;
            case 1:
                this.rdg_factory_modekey.check(R.id.rdg_factory_modekey2);
                break;
        }
        this.rdg_factory_yuyinkey = (RadioGroup) this.view.findViewById(R.id.rdg_factory_yuyinkey);
        switch (this.yuyinkey) {
            case 0:
                this.rdg_factory_yuyinkey.check(R.id.rdg_factory_yuyinkey1);
                break;
            case 1:
                this.rdg_factory_yuyinkey.check(R.id.rdg_factory_yuyinkey2);
                break;
            case 2:
                this.rdg_factory_yuyinkey.check(R.id.rdg_factory_yuyinkey3);
                break;
            case 3:
                this.rdg_factory_yuyinkey.check(R.id.rdg_factory_yuyinkey4);
                break;
        }
        this.rdg_can = (RadioGroup) this.view.findViewById(R.id.rdg_can);
        switch (this.canS) {
            case 1:
                this.rdg_can.check(R.id.rdg_can1);
                break;
            case 2:
                this.rdg_can.check(R.id.rdg_can2);
                break;
        }
        if (this.cbox_canBus.isChecked()) {
            this.rdg_can.setEnabled(false);
            this.rdg_can.findViewById(R.id.rdg_can1).setEnabled(false);
            this.rdg_can.findViewById(R.id.rdg_can2).setEnabled(false);
        }
        this.rdg_track = (RadioGroup) this.view.findViewById(R.id.rdg_track);
        switch (this.track) {
            case 0:
                this.rdg_track.check(R.id.rdg_track1);
                break;
            case 1:
                this.rdg_track.check(R.id.rdg_track2);
                break;
        }
        this.rdg_can.setOnCheckedChangeListener(this);
        this.rdg_track.setOnCheckedChangeListener(this);
        this.rdg_auxsw.setOnCheckedChangeListener(this);
        this.rdg_Nbtauxsw.setOnCheckedChangeListener(this);
        this.rdg_ccciD.setOnCheckedChangeListener(this);
        this.rdg_cardoor.setOnCheckedChangeListener(this);
        this.rdg_numdoor.setOnCheckedChangeListener(this);
        this.rdg_factory_carseep.setOnCheckedChangeListener(this);
        this.rdg_factory_danwei.setOnCheckedChangeListener(this);
        this.rdg_factory_yuyinkey.setOnCheckedChangeListener(this);
        this.rdg_factory_mapkey.setOnCheckedChangeListener(this);
        this.rdg_factory_modekey.setOnCheckedChangeListener(this);
        this.ccciDTextView = (TextView) this.view.findViewById(R.id.ccciDTextView);
        boolean isBmw = UiThemeUtils.isBMW_EVO_ID5(this.m_con) || UiThemeUtils.isBMW_EVO_ID6(this.m_con) || UiThemeUtils.isBMW_EVO_ID6_GS(this.m_con) || UiThemeUtils.isBMW_EVO_ID7(this.m_con) || UiThemeUtils.isCommon_UI_GS_UG(this.m_con) || UiThemeUtils.isCommon_UI_GS(this.m_con) || UiThemeUtils.isBenz_MBUX(this.m_con) || UiThemeUtils.isBenz_NTG6(this.m_con) || UiThemeUtils.isBenz_GS(this.m_con);
        this.ccciDTextView.setVisibility(isBmw ? 0 : 8);
        this.rdg_ccciD.setVisibility(isBmw ? 0 : 8);
        boolean enableAux = this.carManufacturer == 2;
        boolean enableAirCondition = this.carManufacturer == 4;
        this.cbox_bencAux.setVisibility(enableAux ? 0 : 8);
        this.cbox_bencPank.setVisibility(enableAux ? 0 : 8);
        this.cbox_bencPank_root.setVisibility(enableAux ? 0 : 8);
        this.cbox_air_con.setVisibility(enableAux ? 0 : 8);
        this.cbox_ac_control.setVisibility(enableAirCondition ? 0 : 8);
        this.cbox_oem_fm.setVisibility(enableAirCondition ? 0 : 8);
        this.audi_home_left_widget_textview = (TextView) this.view.findViewById(R.id.audi_home_left_widget_textview);
        this.audi_home_right_widget_textview = (TextView) this.view.findViewById(R.id.audi_home_right_widget_textview);
        this.audiHomeLeftRadioGroup = (RadioGroup) this.view.findViewById(R.id.audiHomeLeftRadioGroup);
        this.audiHomeRightRadioGroup = (RadioGroup) this.view.findViewById(R.id.audiHomeRightRadioGroup);
        Log.i(TAG, "audiLeftLogoIndex: " + this.audiLeftLogoIndex);
        Log.i(TAG, "audiRightWidgetIndex: " + this.audiRightWidgetIndex);
        int i3 = 0;
        while (i3 < this.audiHomeLeftRadioGroup.getChildCount()) {
            ((RadioButton) this.audiHomeLeftRadioGroup.getChildAt(i3)).setChecked(this.audiLeftLogoIndex == i3);
            i3++;
        }
        int i4 = 0;
        while (i4 < this.audiHomeRightRadioGroup.getChildCount()) {
            ((RadioButton) this.audiHomeRightRadioGroup.getChildAt(i4)).setChecked(this.audiRightWidgetIndex == i4);
            i4++;
        }
        this.audiHomeLeftRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = CarConfig.this.audiHomeLeftRadioGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == CarConfig.this.audiHomeLeftRadioGroup.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.AUDI_UI_LEFT_ID, i);
                        String access$100 = CarConfig.TAG;
                        Log.i(access$100, "save Audi_Logo_Left : " + i);
                    }
                }
            }
        });
        this.audiHomeRightRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = CarConfig.this.audiHomeRightRadioGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == CarConfig.this.audiHomeRightRadioGroup.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.AUDI_UI_RIGHT_ID, i);
                        String access$100 = CarConfig.TAG;
                        Log.i(access$100, "save Audi_Logo_Right : " + i);
                    }
                }
            }
        });
        if (this.carManufacturer != 3) {
            z = false;
        }
        boolean isAudi = z;
        this.audi_home_left_widget_textview.setVisibility(isAudi ? 0 : 8);
        this.audi_home_right_widget_textview.setVisibility(isAudi ? 0 : 8);
        this.audiHomeLeftRadioGroup.setVisibility(isAudi ? 0 : 8);
        RadioGroup radioGroup = this.audiHomeRightRadioGroup;
        if (!isAudi) {
            i = 8;
        }
        radioGroup.setVisibility(i);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rdb_Nbtauxsw1:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 1);
                return;
            case R.id.rdb_Nbtauxsw2:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 2);
                return;
            case R.id.rdb_Nbtauxsw3:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 3);
                return;
            case R.id.rdb_auxsw1:
                FileUtils.savaIntData(KeyConfig.CAR_AUX_SW, 0);
                return;
            case R.id.rdb_auxsw2:
                FileUtils.savaIntData(KeyConfig.CAR_AUX_SW, 1);
                return;
            case R.id.rdb_ccciD1:
                FileUtils.savaIntData(KeyConfig.CCC_ID, 0);
                return;
            case R.id.rdb_ccciD2:
                FileUtils.savaIntData(KeyConfig.CCC_ID, 1);
                return;
            case R.id.rdg_can1:
                FileUtils.savaIntData(KeyConfig.CAN_BUS_TYPE, 1);
                return;
            case R.id.rdg_can2:
                FileUtils.savaIntData(KeyConfig.CAN_BUS_TYPE, 2);
                return;
            case R.id.rdg_cardoor1:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_SELECT, 0);
                return;
            case R.id.rdg_cardoor2:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_SELECT, 1);
                return;
            case R.id.rdg_cardoor3:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 2);
                return;
            case R.id.rdg_factory_carseep1:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 0);
                return;
            case R.id.rdg_factory_carseep2:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 1);
                return;
            case R.id.rdg_factory_carseep3:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 2);
                return;
            case R.id.rdg_factory_carseep4:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 3);
                return;
            case R.id.rdg_factory_danwei1:
                FileUtils.savaIntData(KeyConfig.HAND_SET_AUTOMATIC, 0);
                return;
            case R.id.rdg_factory_danwei2:
                FileUtils.savaIntData(KeyConfig.HAND_SET_AUTOMATIC, 1);
                return;
            case R.id.rdg_factory_mapkey1:
                FileUtils.savaIntData(KeyConfig.MAP_KEY, 0);
                Log.d("CarConfig", "mapkey====sava==0000===>");
                return;
            case R.id.rdg_factory_mapkey2:
                FileUtils.savaIntData(KeyConfig.MAP_KEY, 1);
                Log.d("CarConfig", "mapkey====sava===1111===>");
                return;
            case R.id.rdg_factory_modekey1:
                FileUtils.savaIntData(KeyConfig.MODE_KEY, 0);
                return;
            case R.id.rdg_factory_modekey2:
                FileUtils.savaIntData(KeyConfig.MODE_KEY, 1);
                return;
            case R.id.rdg_factory_yuyinkey1:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 0);
                return;
            case R.id.rdg_factory_yuyinkey2:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 1);
                return;
            case R.id.rdg_factory_yuyinkey3:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 2);
                return;
            case R.id.rdg_factory_yuyinkey4:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 3);
                return;
            case R.id.rdg_numdoor1:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 0);
                return;
            case R.id.rdg_numdoor2:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 1);
                return;
            case R.id.rdg_track1:
                FileUtils.savaIntData(KeyConfig.DRIVE_TRACK, 0);
                return;
            case R.id.rdg_track2:
                FileUtils.savaIntData(KeyConfig.DRIVE_TRACK, 1);
                return;
            default:
                return;
        }
    }
}

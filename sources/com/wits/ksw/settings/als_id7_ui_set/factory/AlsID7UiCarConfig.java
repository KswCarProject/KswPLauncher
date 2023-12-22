package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.content.Context;
import android.os.RemoteException;
import android.provider.Settings;
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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes5.dex */
public class AlsID7UiCarConfig extends FrameLayout implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "KswApplication." + AlsID7UiCarConfig.class.getSimpleName();
    private RadioGroup audiHomeLeftRadioGroup;
    private RadioGroup audiHomeRightRadioGroup;
    private int audiLeftLogoIndex;
    private int audiRightWidgetIndex;
    private TextView audi_home_left_widget_textview;
    private TextView audi_home_right_widget_textview;
    private int auxsw;
    private int benzpane;
    private int canS;
    private int carManufacturer;
    private int cardoor;
    private int carseep;
    private CheckBox cbox_ac_control;
    private CheckBox cbox_air_con;
    private CheckBox cbox_bencAux;
    private CheckBox cbox_bencPank;
    private RadioGroup cbox_bencPank_rgd;
    private View cbox_bencPank_root;
    private CheckBox cbox_canBus;
    private CheckBox cbox_dcld;
    private CheckBox cbox_oem_fm;
    private TextView ccciDTextView;
    private int cccid;
    private int doornumb;
    private int gears;
    private int isOldBmwx;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int mapkey;
    private int modekey;
    private int nbauxsw;
    private RadioGroup rdg_Nbtauxsw;
    private RadioGroup rdg_auxsw;
    private RadioGroup rdg_can;
    private RadioGroup rdg_cardoor;
    private RadioGroup rdg_ccciD;
    private RadioGroup rdg_factory_carseep;
    private RadioGroup rdg_factory_gears;
    private RadioGroup rdg_factory_mapkey;
    private RadioGroup rdg_factory_modekey;
    private RadioGroup rdg_factory_yuyinkey;
    private RadioGroup rdg_numdoor;
    private RadioGroup rdg_track;
    private int track;
    private View view;
    private int yuyinkey;

    public AlsID7UiCarConfig(Context context) {
        super(context);
        this.isOldBmwx = 0;
        this.auxsw = 0;
        this.nbauxsw = 0;
        this.cccid = 0;
        this.cardoor = 0;
        this.carseep = 0;
        this.gears = 0;
        this.doornumb = 0;
        this.yuyinkey = 0;
        this.mapkey = 0;
        this.modekey = 0;
        this.benzpane = 0;
        this.canS = 0;
        this.track = 0;
        this.carManufacturer = 0;
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_factory_car_config, (ViewGroup) null);
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
            this.gears = PowerManagerApp.getSettingsInt(KeyConfig.HAND_SET_AUTOMATIC);
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
            Log.d(TAG, "initData: " + this.carManufacturer + " " + this.canS + " " + this.track);
            if (this.carManufacturer == 0) {
                this.carManufacturer = UiThemeUtils.getCarManufacturer(this.m_con);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        int airCon;
        this.cbox_dcld = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_dcld);
        this.cbox_canBus = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_canBus);
        this.cbox_bencAux = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_bencAux);
        this.cbox_air_con = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_air_con);
        this.cbox_ac_control = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_ac_control);
        this.cbox_oem_fm = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_oem_fm);
        this.cbox_canBus.setChecked(false);
        this.cbox_dcld.setChecked(this.isOldBmwx == 0);
        this.cbox_dcld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
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
        this.cbox_bencAux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData("benz_aux_switch", isChecked ? 1 : 0);
            }
        });
        int i = this.carManufacturer;
        if (i == 1 || i == 3) {
            airCon = Settings.System.getInt(this.m_con.getContentResolver(), "air_conditioner", 0);
        } else {
            airCon = Settings.System.getInt(this.m_con.getContentResolver(), "air_conditioner", 1);
        }
        int acControl = Settings.System.getInt(this.m_con.getContentResolver(), KeyConfig.AC_CONTROL, 0);
        int oemFM = Settings.System.getInt(this.m_con.getContentResolver(), KeyConfig.OEM_FM, 0);
        this.cbox_air_con.setChecked(airCon == 1);
        this.cbox_air_con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData("air_conditioner", isChecked ? 1 : 0);
            }
        });
        this.cbox_ac_control.setChecked(acControl == 1);
        this.cbox_ac_control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.AC_CONTROL, isChecked ? 1 : 0);
            }
        });
        this.cbox_oem_fm.setChecked(oemFM == 1);
        this.cbox_oem_fm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FileUtils.savaIntData(KeyConfig.OEM_FM, isChecked ? 1 : 0);
            }
        });
        CheckBox checkBox = (CheckBox) this.view.findViewById(C0899R.C0901id.cbox_bencPank);
        this.cbox_bencPank = checkBox;
        checkBox.setChecked(this.benzpane != 0);
        this.cbox_bencPank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.BENZPANE, 1);
                } else {
                    FileUtils.savaIntData(KeyConfig.BENZPANE, 0);
                }
            }
        });
        this.cbox_bencPank_root = this.view.findViewById(C0899R.C0901id.cbox_bencPank_root);
        RadioGroup radioGroup = (RadioGroup) this.view.findViewById(C0899R.C0901id.cbox_bencPank_rgd);
        this.cbox_bencPank_rgd = radioGroup;
        int count = radioGroup.getChildCount();
        int i2 = 0;
        while (i2 < count) {
            ((RadioButton) this.cbox_bencPank_rgd.getChildAt(i2)).setChecked(this.benzpane == i2);
            i2++;
        }
        this.cbox_bencPank_rgd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.7
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count2 = AlsID7UiCarConfig.this.cbox_bencPank_rgd.getChildCount();
                for (int i3 = 0; i3 < count2; i3++) {
                    if (checkedId == AlsID7UiCarConfig.this.cbox_bencPank_rgd.getChildAt(i3).getId()) {
                        FileUtils.savaIntData(KeyConfig.BENZPANE, i3);
                        Log.i(AlsID7UiCarConfig.TAG, "save BenzPanelEnable : " + i3);
                    }
                }
            }
        });
        try {
            this.cbox_canBus.setChecked(PowerManagerApp.getSettingsInt("can_bus_switch") == 1);
        } catch (RemoteException e2) {
        }
        this.cbox_canBus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    PowerManagerApp.setSettingsInt("can_bus_switch", isChecked ? 1 : 0);
                    if (isChecked) {
                        AlsID7UiCarConfig.this.rdg_can.setEnabled(false);
                        AlsID7UiCarConfig.this.rdg_can.findViewById(C0899R.C0901id.rdg_can1).setEnabled(false);
                        AlsID7UiCarConfig.this.rdg_can.findViewById(C0899R.C0901id.rdg_can2).setEnabled(false);
                    } else {
                        AlsID7UiCarConfig.this.rdg_can.setEnabled(true);
                        AlsID7UiCarConfig.this.rdg_can.findViewById(C0899R.C0901id.rdg_can1).setEnabled(true);
                        AlsID7UiCarConfig.this.rdg_can.findViewById(C0899R.C0901id.rdg_can2).setEnabled(true);
                    }
                } catch (RemoteException e3) {
                }
            }
        });
        RadioGroup radioGroup2 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_auxsw);
        this.rdg_auxsw = radioGroup2;
        switch (this.auxsw) {
            case 0:
                radioGroup2.check(C0899R.C0901id.rdb_auxsw1);
                break;
            case 1:
                radioGroup2.check(C0899R.C0901id.rdb_auxsw2);
                break;
        }
        RadioGroup radioGroup3 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_Nbtauxsw);
        this.rdg_Nbtauxsw = radioGroup3;
        switch (this.nbauxsw) {
            case 1:
                radioGroup3.check(C0899R.C0901id.rdb_Nbtauxsw1);
                break;
            case 2:
                radioGroup3.check(C0899R.C0901id.rdb_Nbtauxsw2);
                break;
            case 3:
                radioGroup3.check(C0899R.C0901id.rdb_Nbtauxsw3);
                break;
        }
        RadioGroup radioGroup4 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_ccciD);
        this.rdg_ccciD = radioGroup4;
        switch (this.cccid) {
            case 0:
                radioGroup4.check(C0899R.C0901id.rdb_ccciD1);
                break;
            case 1:
                radioGroup4.check(C0899R.C0901id.rdb_ccciD2);
                break;
        }
        RadioGroup radioGroup5 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_cardoor);
        this.rdg_cardoor = radioGroup5;
        switch (this.cardoor) {
            case 0:
            case 2:
                radioGroup5.check(C0899R.C0901id.rdg_cardoor1);
                break;
            case 1:
                radioGroup5.check(C0899R.C0901id.rdg_cardoor2);
                break;
        }
        RadioGroup radioGroup6 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_factory_carseep);
        this.rdg_factory_carseep = radioGroup6;
        switch (this.carseep) {
            case 0:
                radioGroup6.check(C0899R.C0901id.rdg_factory_carseep1);
                break;
            case 1:
                radioGroup6.check(C0899R.C0901id.rdg_factory_carseep2);
                break;
            case 2:
                radioGroup6.check(C0899R.C0901id.rdg_factory_carseep3);
                break;
            case 3:
                radioGroup6.check(C0899R.C0901id.rdg_factory_carseep4);
                break;
        }
        RadioGroup radioGroup7 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_factory_gear);
        this.rdg_factory_gears = radioGroup7;
        switch (this.gears) {
            case 0:
                radioGroup7.check(C0899R.C0901id.rdg_factory_gear1);
                break;
            case 1:
                radioGroup7.check(C0899R.C0901id.rdg_factory_gear2);
                break;
            case 2:
                radioGroup7.check(C0899R.C0901id.rdg_factory_gear3);
                break;
        }
        RadioGroup radioGroup8 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_numdoor);
        this.rdg_numdoor = radioGroup8;
        switch (this.doornumb) {
            case 0:
                radioGroup8.check(C0899R.C0901id.rdg_numdoor1);
                break;
            case 1:
                radioGroup8.check(C0899R.C0901id.rdg_numdoor2);
                break;
            case 2:
                radioGroup8.check(C0899R.C0901id.rdg_cardoor3);
                break;
        }
        this.rdg_factory_mapkey = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_factory_mapkey);
        Log.d("CarConfig", "mapkey====get====>" + this.mapkey);
        switch (this.mapkey) {
            case 0:
                this.rdg_factory_mapkey.check(C0899R.C0901id.rdg_factory_mapkey1);
                break;
            case 1:
                this.rdg_factory_mapkey.check(C0899R.C0901id.rdg_factory_mapkey2);
                break;
        }
        RadioGroup radioGroup9 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_factory_modekey);
        this.rdg_factory_modekey = radioGroup9;
        switch (this.modekey) {
            case 0:
                radioGroup9.check(C0899R.C0901id.rdg_factory_modekey1);
                break;
            case 1:
                radioGroup9.check(C0899R.C0901id.rdg_factory_modekey2);
                break;
        }
        RadioGroup radioGroup10 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_factory_yuyinkey);
        this.rdg_factory_yuyinkey = radioGroup10;
        switch (this.yuyinkey) {
            case 0:
                radioGroup10.check(C0899R.C0901id.rdg_factory_yuyinkey1);
                break;
            case 1:
                radioGroup10.check(C0899R.C0901id.rdg_factory_yuyinkey2);
                break;
            case 2:
                radioGroup10.check(C0899R.C0901id.rdg_factory_yuyinkey3);
                break;
            case 3:
                radioGroup10.check(C0899R.C0901id.rdg_factory_yuyinkey4);
                break;
        }
        RadioGroup radioGroup11 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_can);
        this.rdg_can = radioGroup11;
        switch (this.canS) {
            case 1:
                radioGroup11.check(C0899R.C0901id.rdg_can1);
                break;
            case 2:
                radioGroup11.check(C0899R.C0901id.rdg_can2);
                break;
        }
        if (this.cbox_canBus.isChecked()) {
            this.rdg_can.setEnabled(false);
            this.rdg_can.findViewById(C0899R.C0901id.rdg_can1).setEnabled(false);
            this.rdg_can.findViewById(C0899R.C0901id.rdg_can2).setEnabled(false);
        }
        RadioGroup radioGroup12 = (RadioGroup) this.view.findViewById(C0899R.C0901id.rdg_track);
        this.rdg_track = radioGroup12;
        switch (this.track) {
            case 0:
                radioGroup12.check(C0899R.C0901id.rdg_track1);
                break;
            case 1:
                radioGroup12.check(C0899R.C0901id.rdg_track2);
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
        this.rdg_factory_gears.setOnCheckedChangeListener(this);
        this.rdg_factory_yuyinkey.setOnCheckedChangeListener(this);
        this.rdg_factory_mapkey.setOnCheckedChangeListener(this);
        this.rdg_factory_modekey.setOnCheckedChangeListener(this);
        this.ccciDTextView = (TextView) this.view.findViewById(C0899R.C0901id.ccciDTextView);
        int i3 = this.carManufacturer;
        boolean enableAux = i3 == 2;
        boolean enableAirCondition = i3 == 4;
        this.cbox_bencAux.setVisibility(enableAux ? 0 : 8);
        this.cbox_bencPank.setVisibility(enableAux ? 0 : 8);
        this.cbox_bencPank_root.setVisibility(enableAux ? 0 : 8);
        this.cbox_air_con.setVisibility((enableAux || enableAirCondition) ? 0 : 8);
        this.cbox_ac_control.setVisibility(enableAirCondition ? 0 : 8);
        this.cbox_oem_fm.setVisibility(enableAirCondition ? 0 : 8);
        this.audi_home_left_widget_textview = (TextView) this.view.findViewById(C0899R.C0901id.audi_home_left_widget_textview);
        this.audi_home_right_widget_textview = (TextView) this.view.findViewById(C0899R.C0901id.audi_home_right_widget_textview);
        this.audiHomeLeftRadioGroup = (RadioGroup) this.view.findViewById(C0899R.C0901id.audiHomeLeftRadioGroup);
        this.audiHomeRightRadioGroup = (RadioGroup) this.view.findViewById(C0899R.C0901id.audiHomeRightRadioGroup);
        String str = TAG;
        Log.i(str, "audiLeftLogoIndex: " + this.audiLeftLogoIndex);
        Log.i(str, "audiRightWidgetIndex: " + this.audiRightWidgetIndex);
        int i4 = 0;
        while (i4 < this.audiHomeLeftRadioGroup.getChildCount()) {
            ((RadioButton) this.audiHomeLeftRadioGroup.getChildAt(i4)).setChecked(this.audiLeftLogoIndex == i4);
            i4++;
        }
        int i5 = 0;
        while (i5 < this.audiHomeRightRadioGroup.getChildCount()) {
            ((RadioButton) this.audiHomeRightRadioGroup.getChildAt(i5)).setChecked(this.audiRightWidgetIndex == i5);
            i5++;
        }
        this.audiHomeLeftRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.9
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count2 = AlsID7UiCarConfig.this.audiHomeLeftRadioGroup.getChildCount();
                for (int i6 = 0; i6 < count2; i6++) {
                    if (checkedId == AlsID7UiCarConfig.this.audiHomeLeftRadioGroup.getChildAt(i6).getId()) {
                        FileUtils.savaIntData(KeyConfig.AUDI_UI_LEFT_ID, i6);
                        Log.i(AlsID7UiCarConfig.TAG, "save Audi_Logo_Left : " + i6);
                    }
                }
            }
        });
        this.audiHomeRightRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig.10
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count2 = AlsID7UiCarConfig.this.audiHomeRightRadioGroup.getChildCount();
                for (int i6 = 0; i6 < count2; i6++) {
                    if (checkedId == AlsID7UiCarConfig.this.audiHomeRightRadioGroup.getChildAt(i6).getId()) {
                        FileUtils.savaIntData(KeyConfig.AUDI_UI_RIGHT_ID, i6);
                        Log.i(AlsID7UiCarConfig.TAG, "save Audi_Logo_Right : " + i6);
                    }
                }
            }
        });
        boolean isAudi = this.carManufacturer == 3;
        this.audi_home_left_widget_textview.setVisibility(isAudi ? 0 : 8);
        this.audi_home_right_widget_textview.setVisibility(isAudi ? 0 : 8);
        this.audiHomeLeftRadioGroup.setVisibility(isAudi ? 0 : 8);
        this.audiHomeRightRadioGroup.setVisibility(isAudi ? 0 : 8);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case C0899R.C0901id.rdb_Nbtauxsw1 /* 2131297473 */:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 1);
                return;
            case C0899R.C0901id.rdb_Nbtauxsw2 /* 2131297474 */:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 2);
                return;
            case C0899R.C0901id.rdb_Nbtauxsw3 /* 2131297475 */:
                FileUtils.savaIntData(KeyConfig.NBT_AUX_SW, 3);
                return;
            case C0899R.C0901id.rdb_auxsw1 /* 2131297476 */:
                FileUtils.savaIntData(KeyConfig.CAR_AUX_SW, 0);
                return;
            case C0899R.C0901id.rdb_auxsw2 /* 2131297477 */:
                FileUtils.savaIntData(KeyConfig.CAR_AUX_SW, 1);
                return;
            case C0899R.C0901id.rdb_ccciD1 /* 2131297478 */:
                FileUtils.savaIntData(KeyConfig.CCC_ID, 0);
                return;
            case C0899R.C0901id.rdb_ccciD2 /* 2131297479 */:
                FileUtils.savaIntData(KeyConfig.CCC_ID, 1);
                return;
            case C0899R.C0901id.rdg_can1 /* 2131297527 */:
                FileUtils.savaIntData(KeyConfig.CAN_BUS_TYPE, 1);
                return;
            case C0899R.C0901id.rdg_can2 /* 2131297528 */:
                FileUtils.savaIntData(KeyConfig.CAN_BUS_TYPE, 2);
                return;
            case C0899R.C0901id.rdg_cardoor1 /* 2131297530 */:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_SELECT, 0);
                return;
            case C0899R.C0901id.rdg_cardoor2 /* 2131297531 */:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_SELECT, 1);
                return;
            case C0899R.C0901id.rdg_cardoor3 /* 2131297532 */:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 2);
                return;
            case C0899R.C0901id.rdg_factory_carseep1 /* 2131297538 */:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 0);
                return;
            case C0899R.C0901id.rdg_factory_carseep2 /* 2131297539 */:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 1);
                return;
            case C0899R.C0901id.rdg_factory_carseep3 /* 2131297540 */:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 2);
                return;
            case C0899R.C0901id.rdg_factory_carseep4 /* 2131297541 */:
                FileUtils.savaIntData(KeyConfig.DASH_MAX_SPEED, 3);
                return;
            case C0899R.C0901id.rdg_factory_gear1 /* 2131297546 */:
                FileUtils.savaIntData(KeyConfig.HAND_SET_AUTOMATIC, 0);
                return;
            case C0899R.C0901id.rdg_factory_gear2 /* 2131297547 */:
                FileUtils.savaIntData(KeyConfig.HAND_SET_AUTOMATIC, 1);
                return;
            case C0899R.C0901id.rdg_factory_gear3 /* 2131297548 */:
                FileUtils.savaIntData(KeyConfig.HAND_SET_AUTOMATIC, 2);
                return;
            case C0899R.C0901id.rdg_factory_mapkey1 /* 2131297550 */:
                FileUtils.savaIntData(KeyConfig.MAP_KEY, 0);
                Log.d("CarConfig", "mapkey====sava==0000===>");
                return;
            case C0899R.C0901id.rdg_factory_mapkey2 /* 2131297551 */:
                FileUtils.savaIntData(KeyConfig.MAP_KEY, 1);
                Log.d("CarConfig", "mapkey====sava===1111===>");
                return;
            case C0899R.C0901id.rdg_factory_modekey1 /* 2131297553 */:
                FileUtils.savaIntData(KeyConfig.MODE_KEY, 0);
                return;
            case C0899R.C0901id.rdg_factory_modekey2 /* 2131297554 */:
                FileUtils.savaIntData(KeyConfig.MODE_KEY, 1);
                return;
            case C0899R.C0901id.rdg_factory_yuyinkey1 /* 2131297563 */:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 0);
                return;
            case C0899R.C0901id.rdg_factory_yuyinkey2 /* 2131297564 */:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 1);
                return;
            case C0899R.C0901id.rdg_factory_yuyinkey3 /* 2131297565 */:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 2);
                return;
            case C0899R.C0901id.rdg_factory_yuyinkey4 /* 2131297566 */:
                FileUtils.savaIntData(KeyConfig.VOICE_KEY, 3);
                return;
            case C0899R.C0901id.rdg_numdoor1 /* 2131297587 */:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 0);
                return;
            case C0899R.C0901id.rdg_numdoor2 /* 2131297588 */:
                FileUtils.savaIntData(KeyConfig.CAR_DOOR_NUM, 1);
                return;
            case C0899R.C0901id.rdg_track1 /* 2131297605 */:
                FileUtils.savaIntData(KeyConfig.DRIVE_TRACK, 0);
                return;
            case C0899R.C0901id.rdg_track2 /* 2131297606 */:
                FileUtils.savaIntData(KeyConfig.DRIVE_TRACK, 1);
                return;
            default:
                return;
        }
    }
}

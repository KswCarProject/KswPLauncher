package com.wits.ksw.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.als_id7_ui_set.AlsId7UiSettingsActivity;
import com.wits.ksw.settings.alsid7.AlsID7SettingsActivity;
import com.wits.ksw.settings.audi.AudiSettingMainActivity;
import com.wits.ksw.settings.audi_mib3.AudiMib3SettingMainActivity;
import com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity;
import com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsAudioActivity;
import com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsMainActivity;
import com.wits.ksw.settings.id6.ID6SettingsActivity;
import com.wits.ksw.settings.id7.ID7SettingsActivity;
import com.wits.ksw.settings.land_rover.LandroverSettingsActivity;
import com.wits.ksw.settings.lexus.LexusSettingsActivity;
import com.wits.ksw.settings.ntg6.Ntg6SettingsActivity;
import com.wits.ksw.settings.romeo.RomeoSettingsActivity;

public class SettingsActivity extends BaseActivity {
    private boolean alsid7UIshow = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Intent intentSend;
        super.onCreate(savedInstanceState);
        Log.d("startAction", "===action====:" + getIntent().getAction());
        if (UiThemeUtils.isBenz_NTG6(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isBenz_GS(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID7(this) || UiThemeUtils.isUI_KSW_ID7(this)) {
            intentSend = new Intent(this, ID7SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID6(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID6_GS(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID5(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isCommon_UI_GS(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isBenz_MBUX(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isAudi_MMI_4G(this)) {
            intentSend = new Intent(this, AudiSettingMainActivity.class);
        } else if (UiThemeUtils.isAudi_mib3(this) || UiThemeUtils.isUI_mib3_V2(this) || UiThemeUtils.isAudi_mib3_FY(this) || UiThemeUtils.isAudi_mib3_FY_V2(this) || UiThemeUtils.isAudi_mib3_ty(this)) {
            intentSend = new Intent(this, AudiMib3SettingMainActivity.class);
        } else if (UiThemeUtils.isBenz_NTG5(this)) {
            intentSend = new Intent(this, AudiSettingMainActivity.class);
        } else if (UiThemeUtils.isALS_ID6(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isBenz_MBUX_2021(this) || UiThemeUtils.isBenz_MBUX_2021_KSW(this) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if ((UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client())) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isLEXUS_UI(this)) {
            intentSend = new Intent(this, LexusSettingsActivity.class);
        } else if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            intentSend = new Intent(this, LexusSettingsActivity.class);
        } else if (UiThemeUtils.isROMEO_UI(this)) {
            intentSend = new Intent(this, RomeoSettingsActivity.class);
        } else if (UiThemeUtils.isCommon_UI_GS_UG_1024(this) || UiThemeUtils.isUI_KSW_MBUX_1024(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID6_CUSP(this) && ClientManager.getInstance().isCUSP_210407()) {
            intentSend = new Intent(this, ID6SettingsActivity.class);
        } else if (UiThemeUtils.isID7_ALS(this) || UiThemeUtils.isID7_ALS_V2(this)) {
            if (ClientManager.getInstance().isAls6208Client()) {
                intentSend = new Intent(this, AlsID7SettingsActivity.class);
            } else {
                intentSend = new Intent(this, ID7SettingsActivity.class);
            }
        } else if (UiThemeUtils.isLAND_ROVER(this)) {
            intentSend = new Intent(this, LandroverSettingsActivity.class);
        } else if (UiThemeUtils.isALS_ID7_UI(this)) {
            intentSend = new Intent(this, AlsId7UiSettingsActivity.class);
        } else if (UiThemeUtils.isBMW_ID8_UI(this)) {
            if (TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice") || TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice.function")) {
                intentSend = new Intent(this, BmwId8SettingsAudioActivity.class);
            } else {
                intentSend = new Intent(this, BmwId8SettingsMainActivity.class);
            }
        } else if (!UiThemeUtils.isUI_GS_ID8(this)) {
            intentSend = new Intent(this, ID7SettingsActivity.class);
        } else if (TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice") || TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice.function")) {
            intentSend = new Intent(this, BmwId8SettingsAudioActivity.class);
        } else {
            intentSend = new Intent(this, BmwId8GsSettingsMainActivity.class);
        }
        if (TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice")) {
            intentSend.putExtra("voiceData", "voic");
        } else if (TextUtils.equals(getIntent().getAction(), "com.on.systemUi.start.voice.function")) {
            intentSend.putExtra("voiceData", "voicFun");
        }
        startActivity(intentSend);
        finish();
    }
}

package com.wits.ksw.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.audi.AudiSettingMainActivity;
import com.wits.ksw.settings.id6.ID6SettingsActivity;
import com.wits.ksw.settings.id7.ID7SettingsActivity;
import com.wits.ksw.settings.ntg6.Ntg6SettingsActivity;

public class SettingsActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    @RequiresApi(api = 23)
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intentSend;
        super.onCreate(savedInstanceState);
        Log.d("startAction", "===action====:" + getIntent().getAction());
        if (UiThemeUtils.isBenz_NTG6(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isBenz_GS(this)) {
            intentSend = new Intent(this, Ntg6SettingsActivity.class);
        } else if (UiThemeUtils.isBMW_EVO_ID7(this)) {
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
        } else if (UiThemeUtils.isBenz_NTG5(this)) {
            intentSend = new Intent(this, AudiSettingMainActivity.class);
        } else {
            intentSend = new Intent(this, ID7SettingsActivity.class);
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

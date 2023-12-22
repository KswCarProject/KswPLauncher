package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityAudiMib3Binding;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes15.dex */
public class AudiMib3SettingMainActivity extends AudiMib3SubActivity implements View.OnClickListener {
    private static final String TAG = "KswApplication." + AudiMib3SettingMainActivity.class.getSimpleName();
    public static AudiMib3SettingMainActivity getInstance;
    private ActivityAudiMib3Binding binding;
    public AudiMib3SystemViewModel viewModel;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInstance = this;
        ActivityAudiMib3Binding activityAudiMib3Binding = (ActivityAudiMib3Binding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi_mib3, null, false);
        this.binding = activityAudiMib3Binding;
        View root = activityAudiMib3Binding.getRoot();
        this.contentLayout.addView(root, -1, -1);
        this.viewModel = (AudiMib3SystemViewModel) ViewModelProviders.m59of(this).get(AudiMib3SystemViewModel.class);
        int count = this.binding.audiHomeParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            this.binding.audiHomeParentPanel.getChildAt(i).setOnClickListener(this);
        }
        String voiceData = getIntent().getStringExtra("voiceData");
        if (TextUtils.equals("voicFun", voiceData)) {
            AudiMib3StartUtil.AudiEqActivity(this);
            finish();
        } else if (TextUtils.equals("voic", voiceData)) {
            AudiMib3StartUtil.AudiSoundActivity(this);
            finish();
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String voiceData = intent.getStringExtra("voiceData");
        Log.d("ntg55startAction", "onNewIntent:" + voiceData);
        if (TextUtils.equals("voic", voiceData)) {
            AudiMib3StartUtil.AudiSoundActivity(this);
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        this.binding.audiHomeParentPanel.setSeleted(v);
        switch (v.getId()) {
            case C0899R.C0901id.au_set_eq_item /* 2131296360 */:
                AudiMib3StartUtil.AudiEqActivity(this);
                return;
            case C0899R.C0901id.au_set_fac_item /* 2131296361 */:
                AudiMib3StartUtil.AudiPasswordActivity(this);
                return;
            case C0899R.C0901id.au_set_lag_item /* 2131296362 */:
                AudiMib3StartUtil.AudiLanguageActivity(this);
                return;
            case C0899R.C0901id.au_set_more_item /* 2131296363 */:
                AudiMib3StartUtil.SettingsActivity(this);
                return;
            case C0899R.C0901id.au_set_navi_item /* 2131296364 */:
                AudiMib3StartUtil.AudiNaviActivity(this);
                return;
            case C0899R.C0901id.au_set_sound_item /* 2131296365 */:
                AudiMib3StartUtil.AudiSoundActivity(this);
                return;
            case C0899R.C0901id.au_set_sys_item /* 2131296366 */:
                AudiMib3StartUtil.AudioSystemActivity(this);
                return;
            case C0899R.C0901id.au_set_sysinfo_item /* 2131296367 */:
                AudiMib3StartUtil.AudiSysinfoActivity(this);
                return;
            case C0899R.C0901id.au_set_time_item /* 2131296368 */:
                AudiMib3StartUtil.AudiTimeActivity(this);
                return;
            default:
                return;
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21) {
            finish();
        }
        return super.dispatchKeyEvent(event);
    }
}

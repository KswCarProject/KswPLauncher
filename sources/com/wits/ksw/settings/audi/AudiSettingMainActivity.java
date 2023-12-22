package com.wits.ksw.settings.audi;

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
import com.wits.ksw.databinding.ActivityAudiBinding;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes13.dex */
public class AudiSettingMainActivity extends AudiSubActivity implements View.OnClickListener {
    private static final String TAG = "KswApplication." + AudiSettingMainActivity.class.getSimpleName();
    public static AudiSettingMainActivity getInstance;
    private ActivityAudiBinding binding;
    public AudiSystemViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInstance = this;
        ActivityAudiBinding activityAudiBinding = (ActivityAudiBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi, null, false);
        this.binding = activityAudiBinding;
        View root = activityAudiBinding.getRoot();
        this.contentLayout.addView(root, -1, -1);
        this.viewModel = (AudiSystemViewModel) ViewModelProviders.m59of(this).get(AudiSystemViewModel.class);
        int count = this.binding.audiHomeParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            this.binding.audiHomeParentPanel.getChildAt(i).setOnClickListener(this);
        }
        String voiceData = getIntent().getStringExtra("voiceData");
        if (TextUtils.equals("voicFun", voiceData)) {
            StartUtil.AudiEqActivity(this);
            finish();
        } else if (TextUtils.equals("voic", voiceData)) {
            StartUtil.AudiSoundActivity(this);
            finish();
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String voiceData = intent.getStringExtra("voiceData");
        Log.d("ntg55startAction", "onNewIntent:" + voiceData);
        if (TextUtils.equals("voic", voiceData)) {
            StartUtil.AudiSoundActivity(this);
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        this.binding.audiHomeParentPanel.setSeleted(v);
        switch (v.getId()) {
            case C0899R.C0901id.au_set_eq_item /* 2131296360 */:
                StartUtil.AudiEqActivity(this);
                return;
            case C0899R.C0901id.au_set_fac_item /* 2131296361 */:
                StartUtil.AudiPasswordActivity(this);
                return;
            case C0899R.C0901id.au_set_lag_item /* 2131296362 */:
                StartUtil.AudiLanguageActivity(this);
                return;
            case C0899R.C0901id.au_set_more_item /* 2131296363 */:
                StartUtil.SettingsActivity(this);
                return;
            case C0899R.C0901id.au_set_navi_item /* 2131296364 */:
                StartUtil.AudiNaviActivity(this);
                return;
            case C0899R.C0901id.au_set_sound_item /* 2131296365 */:
                StartUtil.AudiSoundActivity(this);
                return;
            case C0899R.C0901id.au_set_sys_item /* 2131296366 */:
                StartUtil.AudioSystemActivity(this);
                return;
            case C0899R.C0901id.au_set_sysinfo_item /* 2131296367 */:
                StartUtil.AudiSysinfoActivity(this);
                return;
            case C0899R.C0901id.au_set_time_item /* 2131296368 */:
                StartUtil.AudiTimeActivity(this);
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

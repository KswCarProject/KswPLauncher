package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiBinding;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiSettingMainActivity extends AudiSubActivity implements View.OnClickListener {
    private static final String TAG = ("KswApplication." + AudiSettingMainActivity.class.getSimpleName());
    public static AudiSettingMainActivity getInstance;
    private ActivityAudiBinding binding;
    public AudiSystemViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInstance = this;
        ActivityAudiBinding activityAudiBinding = (ActivityAudiBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi, (ViewGroup) null, false);
        this.binding = activityAudiBinding;
        this.contentLayout.addView(activityAudiBinding.getRoot(), -1, -1);
        this.viewModel = (AudiSystemViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiSystemViewModel.class);
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

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String voiceData = intent.getStringExtra("voiceData");
        Log.d("ntg55startAction", "onNewIntent:" + voiceData);
        if (TextUtils.equals("voic", voiceData)) {
            StartUtil.AudiSoundActivity(this);
            finish();
        }
    }

    public void onClick(View v) {
        this.binding.audiHomeParentPanel.setSeleted(v);
        switch (v.getId()) {
            case R.id.au_set_eq_item /*2131296357*/:
                StartUtil.AudiEqActivity(this);
                return;
            case R.id.au_set_fac_item /*2131296358*/:
                StartUtil.AudiPasswordActivity(this);
                return;
            case R.id.au_set_lag_item /*2131296359*/:
                StartUtil.AudiLanguageActivity(this);
                return;
            case R.id.au_set_more_item /*2131296360*/:
                StartUtil.SettingsActivity(this);
                return;
            case R.id.au_set_navi_item /*2131296361*/:
                StartUtil.AudiNaviActivity(this);
                return;
            case R.id.au_set_sound_item /*2131296362*/:
                StartUtil.AudiSoundActivity(this);
                return;
            case R.id.au_set_sys_item /*2131296363*/:
                StartUtil.AudioSystemActivity(this);
                return;
            case R.id.au_set_sysinfo_item /*2131296364*/:
                StartUtil.AudiSysinfoActivity(this);
                return;
            case R.id.au_set_time_item /*2131296365*/:
                StartUtil.AudiTimeActivity(this);
                return;
            default:
                return;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21) {
            finish();
        }
        return super.dispatchKeyEvent(event);
    }
}

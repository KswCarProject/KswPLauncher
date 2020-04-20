package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiBinding;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiSettingMainActivity extends AudiSubActivity implements View.OnClickListener {
    private static final String TAG = ("KSWLauncher." + AudiSettingMainActivity.class.getSimpleName());
    public static AudiSettingMainActivity getInstance;
    private ActivityAudiBinding binding;
    public AudiSystemViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInstance = this;
        this.binding = (ActivityAudiBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
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

    public void onClick(View v) {
        this.binding.audiHomeParentPanel.setSeleted(v);
        switch (v.getId()) {
            case R.id.au_set_eq_item:
                StartUtil.AudiEqActivity(this);
                return;
            case R.id.au_set_fac_item:
                StartUtil.AudiPasswordActivity(this);
                return;
            case R.id.au_set_lag_item:
                StartUtil.AudiLanguageActivity(this);
                return;
            case R.id.au_set_more_item:
                StartUtil.SettingsActivity(this);
                return;
            case R.id.au_set_navi_item:
                StartUtil.AudiNaviActivity(this);
                return;
            case R.id.au_set_sound_item:
                StartUtil.AudiSoundActivity(this);
                return;
            case R.id.au_set_sys_item:
                StartUtil.AudioSystemActivity(this);
                return;
            case R.id.au_set_sysinfo_item:
                StartUtil.AudiSysinfoActivity(this);
                return;
            case R.id.au_set_time_item:
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

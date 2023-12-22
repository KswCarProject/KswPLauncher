package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityAudiMib3TimeBinding;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes15.dex */
public class AudiMib3TimeActivity extends AudiMib3SubActivity {
    private ActivityAudiMib3TimeBinding binding;
    private AudiMib3SettingViewModel viewModel;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiMib3TimeBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi_mib3_time, null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        AudiMib3SettingViewModel audiMib3SettingViewModel = (AudiMib3SettingViewModel) ViewModelProviders.m59of(this).get(AudiMib3SettingViewModel.class);
        this.viewModel = audiMib3SettingViewModel;
        this.binding.setVm(audiMib3SettingViewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.item6));
    }
}

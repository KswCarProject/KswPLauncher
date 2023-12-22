package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityAudiTimeBinding;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;

/* loaded from: classes13.dex */
public class AudiTimeActivity extends AudiSubActivity {
    private ActivityAudiTimeBinding binding;
    private AudiSettingViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiTimeBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi_time, null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        AudiSettingViewModel audiSettingViewModel = (AudiSettingViewModel) ViewModelProviders.m59of(this).get(AudiSettingViewModel.class);
        this.viewModel = audiSettingViewModel;
        this.binding.setVm(audiSettingViewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.item6));
    }
}

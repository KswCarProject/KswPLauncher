package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityAudiSoundBinding;
import com.wits.ksw.settings.audi.p007vm.VolumeViewModel;

/* loaded from: classes13.dex */
public class AudiSoundActivity extends AudiSubActivity {
    private ActivityAudiSoundBinding binding;
    private VolumeViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiSoundBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi_sound, null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        VolumeViewModel volumeViewModel = (VolumeViewModel) ViewModelProviders.m59of(this).get(VolumeViewModel.class);
        this.viewModel = volumeViewModel;
        this.binding.setVm(volumeViewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.item3));
    }
}

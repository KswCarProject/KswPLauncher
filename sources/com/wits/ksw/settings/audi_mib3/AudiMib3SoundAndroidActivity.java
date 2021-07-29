package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiMib3SoundAndroidBinding;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public class AudiMib3SoundAndroidActivity extends AudiMib3SubActivity {
    private ActivityAudiMib3SoundAndroidBinding binding;
    private AudiMib3VolumeViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiMib3SoundAndroidBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi_mib3_sound_android, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        AudiMib3VolumeViewModel audiMib3VolumeViewModel = (AudiMib3VolumeViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiMib3VolumeViewModel.class);
        this.viewModel = audiMib3VolumeViewModel;
        this.binding.setVm(audiMib3VolumeViewModel);
    }
}

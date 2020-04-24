package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiSoundBinding;
import com.wits.ksw.settings.audi.vm.VolumeViewModel;

public class AudiSoundActivity extends AudiSubActivity {
    private ActivityAudiSoundBinding binding;
    private VolumeViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiSoundBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi_sound, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        this.viewModel = (VolumeViewModel) ViewModelProviders.of((FragmentActivity) this).get(VolumeViewModel.class);
        this.binding.setVm(this.viewModel);
    }
}

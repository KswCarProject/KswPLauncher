package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiTimeBinding;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;

public class AudiTimeActivity extends AudiSubActivity {
    private ActivityAudiTimeBinding binding;
    private AudiSettingViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiTimeBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi_time, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        this.viewModel = (AudiSettingViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiSettingViewModel.class);
        this.binding.setVm(this.viewModel);
    }
}

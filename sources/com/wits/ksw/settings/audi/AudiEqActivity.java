package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiEqViewBinding;
import com.wits.ksw.settings.audi.vm.EQViewModel;

public class AudiEqActivity extends AudiSubActivity {
    private AudiEqViewBinding binding;
    private EQViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (AudiEqViewBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_eq_view, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        this.viewModel = (EQViewModel) ViewModelProviders.of((FragmentActivity) this).get(EQViewModel.class);
        this.binding.setVm(this.viewModel);
    }
}

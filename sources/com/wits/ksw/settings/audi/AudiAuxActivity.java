package com.wits.ksw.settings.audi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiAuxBinding;

public class AudiAuxActivity extends AudiSubActivity {
    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiAuxBinding binding = (AudiAuxBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_aux, (ViewGroup) null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        binding.setVm(AudiSettingMainActivity.getInstance.viewModel);
    }
}

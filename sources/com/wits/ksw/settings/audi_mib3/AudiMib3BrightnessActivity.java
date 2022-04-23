package com.wits.ksw.settings.audi_mib3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3BrightnessBinding;

public class AudiMib3BrightnessActivity extends AudiMib3SubActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3BrightnessBinding binding = (AudiMib3BrightnessBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_mib3_brightness, (ViewGroup) null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        binding.setVm(AudiMib3SettingMainActivity.getInstance.viewModel);
        this.tv_title_set.setText(getResources().getString(R.string.text_13));
    }
}

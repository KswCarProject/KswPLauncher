package com.wits.ksw.settings.audi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiSpeedUnitBinding;

/* loaded from: classes13.dex */
public class AudiSpeedUnitActivity extends AudiSubActivity {
    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiSpeedUnitBinding binding = (AudiSpeedUnitBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_speed_unit, null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        binding.setVm(AudiSettingMainActivity.getInstance.viewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.function_text24));
    }
}

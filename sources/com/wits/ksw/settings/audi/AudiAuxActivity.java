package com.wits.ksw.settings.audi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiAuxBinding;

/* loaded from: classes13.dex */
public class AudiAuxActivity extends AudiSubActivity {
    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiAuxBinding binding = (AudiAuxBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_aux, null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        binding.setVm(AudiSettingMainActivity.getInstance.viewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.set_caraux));
    }
}

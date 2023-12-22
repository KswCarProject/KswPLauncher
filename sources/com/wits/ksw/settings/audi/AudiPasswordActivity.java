package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiPasswordBinding;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.utlis_view.ToastUtils;

/* loaded from: classes13.dex */
public class AudiPasswordActivity extends BaseActivity {
    private AudiSettingViewModel viewModel;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiPasswordBinding binding = (AudiPasswordBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_password);
        AudiSettingViewModel audiSettingViewModel = (AudiSettingViewModel) ViewModelProviders.m59of(this).get(AudiSettingViewModel.class);
        this.viewModel = audiSettingViewModel;
        binding.setVm(audiSettingViewModel);
        binding.audiKeyOk.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi.-$$Lambda$AudiPasswordActivity$7lFtVRrtPk1dOhhjo-hvTJdSMKA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudiPasswordActivity.this.lambda$onCreate$0$AudiPasswordActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$AudiPasswordActivity(View v) {
        if (this.viewModel.isPasswordCorrect()) {
            StartUtil.FactoryActivity(this);
            finish();
            return;
        }
        ToastUtils.showToast(this, getString(C0899R.string.lb_password_error), 0);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AudiSettingViewModel audiSettingViewModel = this.viewModel;
        if (audiSettingViewModel != null) {
            audiSettingViewModel.onDeleteLongClick(null);
        }
        super.onDestroy();
    }
}

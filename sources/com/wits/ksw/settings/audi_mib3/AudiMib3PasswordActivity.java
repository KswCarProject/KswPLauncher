package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3PasswordBinding;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;
import com.wits.ksw.settings.utlis_view.ToastUtils;

/* loaded from: classes15.dex */
public class AudiMib3PasswordActivity extends BaseActivity {
    private AudiMib3SettingViewModel viewModel;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3PasswordBinding binding = (AudiMib3PasswordBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_password);
        AudiMib3SettingViewModel audiMib3SettingViewModel = (AudiMib3SettingViewModel) ViewModelProviders.m59of(this).get(AudiMib3SettingViewModel.class);
        this.viewModel = audiMib3SettingViewModel;
        binding.setVm(audiMib3SettingViewModel);
        binding.audiKeyOk.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi_mib3.-$$Lambda$AudiMib3PasswordActivity$bo7bbOL4xcmbWX3npz5vgQBGYwY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudiMib3PasswordActivity.this.lambda$onCreate$0$AudiMib3PasswordActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$AudiMib3PasswordActivity(View v) {
        if (this.viewModel.isPasswordCorrect()) {
            AudiMib3StartUtil.FactoryActivity(this);
            finish();
            return;
        }
        ToastUtils.showToast(this, getString(C0899R.string.lb_password_error), 0);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AudiMib3SettingViewModel audiMib3SettingViewModel = this.viewModel;
        if (audiMib3SettingViewModel != null) {
            audiMib3SettingViewModel.onDeleteLongClick(null);
        }
        super.onDestroy();
    }
}

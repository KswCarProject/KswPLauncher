package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiPasswordBinding;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.utlis_view.ToastUtils;

public class AudiPasswordActivity extends BaseActivity {
    private AudiSettingViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiPasswordBinding binding = (AudiPasswordBinding) DataBindingUtil.setContentView(this, R.layout.audi_password);
        AudiSettingViewModel audiSettingViewModel = (AudiSettingViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiSettingViewModel.class);
        this.viewModel = audiSettingViewModel;
        binding.setVm(audiSettingViewModel);
        binding.audiKeyOk.setOnClickListener(new View.OnClickListener() {
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
        ToastUtils.showToast(this, getString(R.string.lb_password_error), 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AudiSettingViewModel audiSettingViewModel = this.viewModel;
        if (audiSettingViewModel != null) {
            audiSettingViewModel.onDeleteLongClick((View) null);
        }
        super.onDestroy();
    }
}

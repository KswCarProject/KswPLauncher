package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiPasswordBinding binding = (AudiPasswordBinding) DataBindingUtil.setContentView(this, R.layout.audi_password);
        this.viewModel = (AudiSettingViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiSettingViewModel.class);
        binding.setVm(this.viewModel);
        binding.audiKeyOk.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudiPasswordActivity.lambda$onCreate$0(AudiPasswordActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$onCreate$0(AudiPasswordActivity audiPasswordActivity, View v) {
        if (audiPasswordActivity.viewModel.isPasswordCorrect()) {
            StartUtil.FactoryActivity(audiPasswordActivity);
            audiPasswordActivity.finish();
            return;
        }
        ToastUtils.showToast(audiPasswordActivity, audiPasswordActivity.getString(R.string.lb_password_error), 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.viewModel != null) {
            this.viewModel.onDeleteLongClick((View) null);
        }
        super.onDestroy();
    }
}

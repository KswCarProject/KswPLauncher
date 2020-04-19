package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiSysinfoBinding;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;

public class AudiSystemInfoActivity extends AudiSubActivity {
    private AudiSysinfoBinding binding;
    private DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (AudiSystemInfoActivity.this.viewModel != null) {
                    AudiSystemInfoActivity.this.viewModel.updatMcuVersion();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                AudiSystemInfoActivity.this.sendBroadcast(resetIntent);
            }
        }
    };
    /* access modifiers changed from: private */
    public AudiSettingViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (AudiSysinfoBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_sysinfo, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        this.viewModel = (AudiSettingViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiSettingViewModel.class);
        this.binding.setVm(this.viewModel);
        this.dialogViews = new DialogViews(this);
        int count = this.binding.audiSysInfParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = this.binding.audiSysInfParentPanel.getChildAt(i);
            if (childView instanceof TextView) {
                childView.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        AudiSystemInfoActivity.this.onClick(view);
                    }
                });
            }
        }
    }

    public void onClick(View v) {
        this.binding.audiSysInfParentPanel.setSeleted(v);
        int id = v.getId();
        if (id == R.id.audioSysInfoMcuUpdata) {
            this.dialogViews.updateMcu(getString(R.string.update_mcu_file));
        } else if (id == R.id.audioSysInfoRestoreFactory) {
            this.dialogViews.isQuestView(getString(R.string.update_reset_all), this.handler);
        }
    }
}

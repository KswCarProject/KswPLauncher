package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3SysinfoBinding;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.UtilsInfo;

public class AudiMib3SystemInfoActivity extends AudiMib3SubActivity {
    private AudiMib3SysinfoBinding binding;
    private DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (AudiMib3SystemInfoActivity.this.viewModel != null) {
                    AudiMib3SystemInfoActivity.this.viewModel.updatMcuVersion();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                AudiMib3SystemInfoActivity.this.sendBroadcast(resetIntent);
            }
        }
    };
    /* access modifiers changed from: private */
    public AudiMib3SettingViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3SysinfoBinding audiMib3SysinfoBinding = (AudiMib3SysinfoBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_mib3_sysinfo, (ViewGroup) null, false);
        this.binding = audiMib3SysinfoBinding;
        this.contentLayout.addView(audiMib3SysinfoBinding.getRoot(), -1, -1);
        AudiMib3SettingViewModel audiMib3SettingViewModel = (AudiMib3SettingViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiMib3SettingViewModel.class);
        this.viewModel = audiMib3SettingViewModel;
        this.binding.setVm(audiMib3SettingViewModel);
        this.dialogViews = new DialogViews(this);
        int count = this.binding.audiSysInfParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = this.binding.audiSysInfParentPanel.getChildAt(i);
            if (childView instanceof TextView) {
                childView.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        AudiMib3SystemInfoActivity.this.onClick(view);
                    }
                });
            }
        }
        this.tv_title_set.setText(getResources().getString(R.string.item7));
    }

    public void onClick(View v) {
        this.binding.audiSysInfParentPanel.setSeleted(v);
        switch (v.getId()) {
            case R.id.audiSysInfoSysVer:
                UtilsInfo.showQRCode(this);
                return;
            case R.id.audioSysInfoMcuUpdata:
                this.dialogViews.updateMcu(getString(R.string.update_mcu_file));
                return;
            case R.id.audioSysInfoRestoreFactory:
                this.dialogViews.isQuestView(getString(R.string.update_reset_all), this.handler);
                return;
            default:
                return;
        }
    }
}

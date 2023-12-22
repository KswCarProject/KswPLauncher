package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3SysinfoBinding;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.UtilsInfo;

/* loaded from: classes15.dex */
public class AudiMib3SystemInfoActivity extends AudiMib3SubActivity {
    private AudiMib3SysinfoBinding binding;
    private DialogViews dialogViews;
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.audi_mib3.AudiMib3SystemInfoActivity.1
        @Override // android.os.Handler
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
    private AudiMib3SettingViewModel viewModel;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3SysinfoBinding audiMib3SysinfoBinding = (AudiMib3SysinfoBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_mib3_sysinfo, null, false);
        this.binding = audiMib3SysinfoBinding;
        View view = audiMib3SysinfoBinding.getRoot();
        this.contentLayout.addView(view, -1, -1);
        AudiMib3SettingViewModel audiMib3SettingViewModel = (AudiMib3SettingViewModel) ViewModelProviders.m59of(this).get(AudiMib3SettingViewModel.class);
        this.viewModel = audiMib3SettingViewModel;
        this.binding.setVm(audiMib3SettingViewModel);
        this.dialogViews = new DialogViews(this);
        int count = this.binding.audiSysInfParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = this.binding.audiSysInfParentPanel.getChildAt(i);
            if (childView instanceof TextView) {
                childView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi_mib3.-$$Lambda$M-13UadNXyC12D25TOeIjWf64iQ
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        AudiMib3SystemInfoActivity.this.onClick(view2);
                    }
                });
            }
        }
        this.tv_title_set.setText(getResources().getString(C0899R.string.item7));
    }

    public void onClick(View v) {
        this.binding.audiSysInfParentPanel.setSeleted(v);
        switch (v.getId()) {
            case C0899R.C0901id.audiSysInfoSysVer /* 2131296377 */:
                UtilsInfo.showQRCode(this);
                return;
            case C0899R.C0901id.audioSysInfoMcuUpdata /* 2131296418 */:
                this.dialogViews.updateMcu(getString(C0899R.string.update_mcu_file));
                return;
            case C0899R.C0901id.audioSysInfoRestoreFactory /* 2131296420 */:
                this.dialogViews.isQuestView(getString(C0899R.string.update_reset_all), this.handler);
                return;
            case C0899R.C0901id.audioSysInfoUpDateFactory /* 2131296421 */:
                TxzMessage txzMessage = new TxzMessage(2070, "system.ota.check", null);
                txzMessage.sendBroadCast(this);
                return;
            default:
                return;
        }
    }
}

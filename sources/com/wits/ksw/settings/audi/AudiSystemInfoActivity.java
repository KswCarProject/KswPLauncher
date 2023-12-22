package com.wits.ksw.settings.audi;

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
import com.wits.ksw.databinding.AudiSysinfoBinding;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.UtilsInfo;

/* loaded from: classes13.dex */
public class AudiSystemInfoActivity extends AudiSubActivity {
    private AudiSysinfoBinding binding;
    private DialogViews dialogViews;
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.audi.AudiSystemInfoActivity.1
        @Override // android.os.Handler
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
    private AudiSettingViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiSysinfoBinding audiSysinfoBinding = (AudiSysinfoBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_sysinfo, null, false);
        this.binding = audiSysinfoBinding;
        View view = audiSysinfoBinding.getRoot();
        this.contentLayout.addView(view, -1, -1);
        AudiSettingViewModel audiSettingViewModel = (AudiSettingViewModel) ViewModelProviders.m59of(this).get(AudiSettingViewModel.class);
        this.viewModel = audiSettingViewModel;
        this.binding.setVm(audiSettingViewModel);
        this.dialogViews = new DialogViews(this);
        int count = this.binding.audiSysInfParentPanel.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = this.binding.audiSysInfParentPanel.getChildAt(i);
            if (childView instanceof TextView) {
                childView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi.-$$Lambda$QMfPLHNrIFj5KFMgiBKHHiAYGRY
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        AudiSystemInfoActivity.this.onClick(view2);
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

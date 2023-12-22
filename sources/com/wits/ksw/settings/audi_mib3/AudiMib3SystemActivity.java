package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3SystemSetBinding;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes15.dex */
public class AudiMib3SystemActivity extends AudiMib3SubActivity {
    private AudiMib3SystemSetBinding binding;
    private AudiMib3SystemViewModel viewModel;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3SystemSetBinding audiMib3SystemSetBinding = (AudiMib3SystemSetBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_mib3_system_set, null, false);
        this.binding = audiMib3SystemSetBinding;
        View view = audiMib3SystemSetBinding.getRoot();
        this.contentLayout.addView(view, -1, -1);
        AudiMib3SystemViewModel audiMib3SystemViewModel = (AudiMib3SystemViewModel) ViewModelProviders.m59of(AudiMib3SettingMainActivity.getInstance).get(AudiMib3SystemViewModel.class);
        this.viewModel = audiMib3SystemViewModel;
        this.binding.setVm(audiMib3SystemViewModel);
        int childView = this.binding.audiSystemSetParentPanel.getChildCount();
        for (int i = 0; i < childView; i++) {
            this.binding.audiSystemSetParentPanel.getChildAt(i).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi_mib3.-$$Lambda$AudiMib3SystemActivity$HjuFyJbeJEL4KOJQTlXhtbwqWXg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AudiMib3SystemActivity.this.onClick(view2);
                }
            });
        }
        this.tv_title_set.setText(getResources().getString(C0899R.string.item1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClick(View view) {
        this.binding.audiSystemSetParentPanel.setSeleted(view);
        switch (view.getId()) {
            case C0899R.C0901id.audi_system_aux_postion /* 2131296402 */:
                AudiMib3StartUtil.AudiAuxActivity(this);
                return;
            case C0899R.C0901id.audi_system_brightness /* 2131296403 */:
                AudiMib3StartUtil.AudiBrightnessActivity(this);
                return;
            case C0899R.C0901id.audi_system_rever_camera /* 2131296405 */:
                AudiMib3StartUtil.AudiReverCameraActivity(this);
                return;
            case C0899R.C0901id.audi_system_speed_unit /* 2131296410 */:
                AudiMib3StartUtil.AudiSpeedUnitActivity(this);
                return;
            case C0899R.C0901id.audi_system_temp_unit /* 2131296411 */:
                AudiMib3StartUtil.AudiTempActivity(this);
                return;
            case C0899R.C0901id.tv_music_app /* 2131297952 */:
                AudiMib3StartUtil.toAudiSelMusicApp(this);
                return;
            case C0899R.C0901id.tv_video_app /* 2131298003 */:
                AudiMib3StartUtil.toAudiSelVideoApp(this);
                return;
            default:
                return;
        }
    }
}

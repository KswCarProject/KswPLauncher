package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiSystemSetBinding;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes13.dex */
public class AudiSystemActivity extends AudiSubActivity {
    private AudiSystemSetBinding binding;
    private AudiSystemViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiSystemSetBinding audiSystemSetBinding = (AudiSystemSetBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_system_set, null, false);
        this.binding = audiSystemSetBinding;
        View view = audiSystemSetBinding.getRoot();
        this.contentLayout.addView(view, -1, -1);
        AudiSystemViewModel audiSystemViewModel = (AudiSystemViewModel) ViewModelProviders.m59of(AudiSettingMainActivity.getInstance).get(AudiSystemViewModel.class);
        this.viewModel = audiSystemViewModel;
        this.binding.setVm(audiSystemViewModel);
        int childView = this.binding.audiSystemSetParentPanel.getChildCount();
        for (int i = 0; i < childView; i++) {
            this.binding.audiSystemSetParentPanel.getChildAt(i).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi.-$$Lambda$AudiSystemActivity$2W3iY3WAmuZY5AXaTzdQ8wVNwEU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AudiSystemActivity.this.onClick(view2);
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
                StartUtil.AudiAuxActivity(this);
                return;
            case C0899R.C0901id.audi_system_brightness /* 2131296403 */:
                StartUtil.AudiBrightnessActivity(this);
                return;
            case C0899R.C0901id.audi_system_rever_camera /* 2131296405 */:
                StartUtil.AudiReverCameraActivity(this);
                return;
            case C0899R.C0901id.audi_system_speed_unit /* 2131296410 */:
                StartUtil.AudiSpeedUnitActivity(this);
                return;
            case C0899R.C0901id.audi_system_temp_unit /* 2131296411 */:
                StartUtil.AudiTempActivity(this);
                return;
            case C0899R.C0901id.tv_music_app /* 2131297952 */:
                StartUtil.toAudiSelMusicApp(this);
                return;
            case C0899R.C0901id.tv_video_app /* 2131298003 */:
                StartUtil.toAudiSelVideoApp(this);
                return;
            default:
                return;
        }
    }
}

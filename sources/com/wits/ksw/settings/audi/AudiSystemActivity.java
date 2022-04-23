package com.wits.ksw.settings.audi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiSystemSetBinding;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiSystemActivity extends AudiSubActivity {
    private AudiSystemSetBinding binding;
    private AudiSystemViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiSystemSetBinding audiSystemSetBinding = (AudiSystemSetBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_system_set, (ViewGroup) null, false);
        this.binding = audiSystemSetBinding;
        this.contentLayout.addView(audiSystemSetBinding.getRoot(), -1, -1);
        AudiSystemViewModel audiSystemViewModel = (AudiSystemViewModel) ViewModelProviders.of((FragmentActivity) AudiSettingMainActivity.getInstance).get(AudiSystemViewModel.class);
        this.viewModel = audiSystemViewModel;
        this.binding.setVm(audiSystemViewModel);
        int childView = this.binding.audiSystemSetParentPanel.getChildCount();
        for (int i = 0; i < childView; i++) {
            this.binding.audiSystemSetParentPanel.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AudiSystemActivity.this.onClick(view);
                }
            });
        }
        this.tv_title_set.setText(getResources().getString(R.string.item1));
    }

    /* access modifiers changed from: private */
    public void onClick(View view) {
        this.binding.audiSystemSetParentPanel.setSeleted(view);
        switch (view.getId()) {
            case R.id.audi_system_aux_postion:
                StartUtil.AudiAuxActivity(this);
                return;
            case R.id.audi_system_brightness:
                StartUtil.AudiBrightnessActivity(this);
                return;
            case R.id.audi_system_rever_camera:
                StartUtil.AudiReverCameraActivity(this);
                return;
            case R.id.audi_system_speed_unit:
                StartUtil.AudiSpeedUnitActivity(this);
                return;
            case R.id.audi_system_temp_unit:
                StartUtil.AudiTempActivity(this);
                return;
            case R.id.tv_music_app:
                StartUtil.toAudiSelMusicApp(this);
                return;
            case R.id.tv_video_app:
                StartUtil.toAudiSelVideoApp(this);
                return;
            default:
                return;
        }
    }
}

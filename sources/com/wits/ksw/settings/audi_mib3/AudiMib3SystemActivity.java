package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3SystemSetBinding;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public class AudiMib3SystemActivity extends AudiMib3SubActivity {
    private AudiMib3SystemSetBinding binding;
    private AudiMib3SystemViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3SystemSetBinding audiMib3SystemSetBinding = (AudiMib3SystemSetBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_mib3_system_set, (ViewGroup) null, false);
        this.binding = audiMib3SystemSetBinding;
        this.contentLayout.addView(audiMib3SystemSetBinding.getRoot(), -1, -1);
        AudiMib3SystemViewModel audiMib3SystemViewModel = (AudiMib3SystemViewModel) ViewModelProviders.of((FragmentActivity) AudiMib3SettingMainActivity.getInstance).get(AudiMib3SystemViewModel.class);
        this.viewModel = audiMib3SystemViewModel;
        this.binding.setVm(audiMib3SystemViewModel);
        int childView = this.binding.audiSystemSetParentPanel.getChildCount();
        for (int i = 0; i < childView; i++) {
            this.binding.audiSystemSetParentPanel.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AudiMib3SystemActivity.this.onClick(view);
                }
            });
        }
        this.tv_title_set.setText(getResources().getString(R.string.item1));
    }

    /* access modifiers changed from: private */
    public void onClick(View view) {
        this.binding.audiSystemSetParentPanel.setSeleted(view);
        switch (view.getId()) {
            case R.id.audi_system_aux_postion /*2131296399*/:
                AudiMib3StartUtil.AudiAuxActivity(this);
                return;
            case R.id.audi_system_brightness /*2131296400*/:
                AudiMib3StartUtil.AudiBrightnessActivity(this);
                return;
            case R.id.audi_system_rever_camera /*2131296402*/:
                AudiMib3StartUtil.AudiReverCameraActivity(this);
                return;
            case R.id.audi_system_speed_unit /*2131296407*/:
                AudiMib3StartUtil.AudiSpeedUnitActivity(this);
                return;
            case R.id.audi_system_temp_unit /*2131296408*/:
                AudiMib3StartUtil.AudiTempActivity(this);
                return;
            case R.id.tv_music_app /*2131297900*/:
                AudiMib3StartUtil.toAudiSelMusicApp(this);
                return;
            case R.id.tv_video_app /*2131297951*/:
                AudiMib3StartUtil.toAudiSelVideoApp(this);
                return;
            default:
                return;
        }
    }
}

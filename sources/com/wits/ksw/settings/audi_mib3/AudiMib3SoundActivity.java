package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityAudiMib3SoundBinding;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public class AudiMib3SoundActivity extends AudiMib3SubActivity {
    private ActivityAudiMib3SoundBinding binding;
    private AudiMib3VolumeViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiMib3SoundBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_audi_mib3_sound, (ViewGroup) null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        AudiMib3VolumeViewModel audiMib3VolumeViewModel = (AudiMib3VolumeViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiMib3VolumeViewModel.class);
        this.viewModel = audiMib3VolumeViewModel;
        this.binding.setVm(audiMib3VolumeViewModel);
        this.tv_title_set.setText(getResources().getString(R.string.item3));
        initEvent();
    }

    private void initEvent() {
        this.binding.hzTextView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudiMib3SoundActivity.this.lambda$initEvent$0$AudiMib3SoundActivity(view);
            }
        });
        this.binding.carVolumeTextView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudiMib3SoundActivity.this.lambda$initEvent$1$AudiMib3SoundActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$AudiMib3SoundActivity(View v) {
        startActivity(new Intent(this, AudiMib3SoundAndroidActivity.class));
    }

    public /* synthetic */ void lambda$initEvent$1$AudiMib3SoundActivity(View v) {
        startActivity(new Intent(this, AudiMib3SoundOemActivity.class));
    }
}

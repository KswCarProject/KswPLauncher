package com.wits.ksw.settings.audi_mib3;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityAudiMib3SoundBinding;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3VolumeViewModel;

/* loaded from: classes15.dex */
public class AudiMib3SoundActivity extends AudiMib3SubActivity {
    private ActivityAudiMib3SoundBinding binding;
    private AudiMib3VolumeViewModel viewModel;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityAudiMib3SoundBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.activity_audi_mib3_sound, null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        AudiMib3VolumeViewModel audiMib3VolumeViewModel = (AudiMib3VolumeViewModel) ViewModelProviders.m59of(this).get(AudiMib3VolumeViewModel.class);
        this.viewModel = audiMib3VolumeViewModel;
        this.binding.setVm(audiMib3VolumeViewModel);
        this.tv_title_set.setText(getResources().getString(C0899R.string.item3));
        initEvent();
    }

    private void initEvent() {
        this.binding.hzTextView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi_mib3.-$$Lambda$AudiMib3SoundActivity$OST9pfgt8uCNH6AVEtQGgSS7wwI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudiMib3SoundActivity.this.lambda$initEvent$0$AudiMib3SoundActivity(view);
            }
        });
        this.binding.carVolumeTextView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.audi_mib3.-$$Lambda$AudiMib3SoundActivity$emd0fvjpUxVuC0ymyhAdgos-w9I
            @Override // android.view.View.OnClickListener
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

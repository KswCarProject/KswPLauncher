package com.wits.ksw.settings.bmw_id8.layout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsAudioSoundLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BmwId8SettingsAudioSoundLay extends RelativeLayout implements View.OnClickListener, ID8AudioProgressBar.OnTouchChangeListener, ID8AudioProgressBar.OnValueChangeListener {
    private final String TAG;
    private int bass;
    private Context context;
    private int eqModel;
    private BmwId8SettingsAudioSoundLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private int mid;
    private int[] relativeLayoutId;
    private int tre;

    public BmwId8SettingsAudioSoundLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsAudioSoundLay";
        this.bass = 12;
        this.mid = 12;
        this.tre = 12;
        this.eqModel = 0;
        this.relativeLayoutId = new int[]{C0899R.C0901id.bmw_id8_settings_audio_sound_user, C0899R.C0901id.bmw_id8_settings_audio_sound_pop, C0899R.C0901id.bmw_id8_settings_audio_sound_class, C0899R.C0901id.bmw_id8_settings_audio_sound_rock, C0899R.C0901id.bmw_id8_settings_audio_sound_jazz, C0899R.C0901id.bmw_id8_settings_audio_sound_dance, C0899R.C0901id.bmw_id8_settings_bass_sub_btn, C0899R.C0901id.bmw_id8_settings_bass_add_btn, C0899R.C0901id.bmw_id8_settings_mid_sub_btn, C0899R.C0901id.bmw_id8_settings_mid_add_btn, C0899R.C0901id.bmw_id8_settings_tre_sub_btn, C0899R.C0901id.bmw_id8_settings_tre_add_btn};
        this.context = context;
        this.mBinding = (BmwId8SettingsAudioSoundLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_audio_sound_layout, null, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mBinding.getRoot().setLayoutParams(layoutParams);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        addView(this.mBinding.getRoot());
        initView();
        initData();
    }

    private void initView() {
        int i = 0;
        while (true) {
            try {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnClickListener(this);
                    findViewById(this.relativeLayoutId[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsAudioSoundLay.1
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsAudioSoundLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() == 1 && !v.isFocused()) {
                                v.setFocusableInTouchMode(true);
                                v.requestFocus();
                                return false;
                            }
                            return false;
                        }
                    });
                    i++;
                } else {
                    this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsAudioSoundLay.2
                        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                            Log.i("BmwId8SettingsAudioSoundLay", "onGlobalFocusChanged: " + BmwId8SettingsAudioSoundLay.this.mBinding.bmwId8SettingsAudioSoundLay.hasFocus());
                            if (BmwId8SettingsAudioSoundLay.this.mBinding.bmwId8SettingsAudioSoundLay.hasFocus()) {
                                BmwId8SettingsAudioSoundLay.this.mViewModel.audioBgShow.set(false);
                            }
                        }
                    });
                    this.mBinding.bmwId8SettingsAudioLow.setOnTouchChangeListener(this);
                    this.mBinding.bmwId8SettingsAudioLow.setOnValueChangeListener(this);
                    this.mBinding.bmwId8SettingsAudioMid.setOnTouchChangeListener(this);
                    this.mBinding.bmwId8SettingsAudioMid.setOnValueChangeListener(this);
                    this.mBinding.bmwId8SettingsAudioTre.setOnTouchChangeListener(this);
                    this.mBinding.bmwId8SettingsAudioTre.setOnValueChangeListener(this);
                    return;
                }
            } catch (Exception e) {
                e.getStackTrace();
                return;
            }
        }
    }

    private void initData() {
        try {
            this.bass = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.mid = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.tre = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.i("BmwId8SettingsAudioSoundLay", "initData: bass " + this.bass + " mid " + this.mid + " tre " + this.tre + " eqModel " + this.eqModel);
            this.mViewModel.eqType.set(this.eqModel);
            this.mViewModel.bassVolume.set(this.bass);
            this.mViewModel.bassVolumeStr.set(getVolumeStr(this.mViewModel.bassVolume));
            this.mViewModel.midVolume.set(this.mid);
            this.mViewModel.midVolumeStr.set(getVolumeStr(this.mViewModel.midVolume));
            this.mViewModel.treVolume.set(this.tre);
            this.mViewModel.treVolumeStr.set(getVolumeStr(this.mViewModel.treVolume));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.bmw_id8_settings_audio_sound_class /* 2131296563 */:
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 2);
                this.mViewModel.eqType.set(2);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_sound_dance /* 2131296564 */:
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 5);
                this.mViewModel.eqType.set(5);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_sound_jazz /* 2131296566 */:
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 4);
                this.mViewModel.eqType.set(4);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_sound_pop /* 2131296568 */:
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 1);
                this.mViewModel.eqType.set(1);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_sound_rock /* 2131296569 */:
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 3);
                this.mViewModel.eqType.set(3);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_sound_user /* 2131296570 */:
                this.mViewModel.userTypeShow.set(!this.mViewModel.userTypeShow.get());
                FileUtils.savaIntData(KeyConfig.EQ_MODE, 0);
                this.mViewModel.eqType.set(0);
                return;
            case C0899R.C0901id.bmw_id8_settings_bass_add_btn /* 2131296572 */:
                calcAddVolume(this.mViewModel.bassVolume, 24, KeyConfig.EQ_BASS);
                this.mViewModel.bassVolumeStr.set(getVolumeStr(this.mViewModel.bassVolume));
                return;
            case C0899R.C0901id.bmw_id8_settings_bass_sub_btn /* 2131296573 */:
                calcSubVolume(this.mViewModel.bassVolume, 0, KeyConfig.EQ_BASS);
                this.mViewModel.bassVolumeStr.set(getVolumeStr(this.mViewModel.bassVolume));
                return;
            case C0899R.C0901id.bmw_id8_settings_mid_add_btn /* 2131296607 */:
                calcAddVolume(this.mViewModel.midVolume, 24, KeyConfig.EQ_MIDDLE);
                this.mViewModel.midVolumeStr.set(getVolumeStr(this.mViewModel.midVolume));
                return;
            case C0899R.C0901id.bmw_id8_settings_mid_sub_btn /* 2131296608 */:
                calcSubVolume(this.mViewModel.midVolume, 0, KeyConfig.EQ_MIDDLE);
                this.mViewModel.midVolumeStr.set(getVolumeStr(this.mViewModel.midVolume));
                return;
            case C0899R.C0901id.bmw_id8_settings_tre_add_btn /* 2131296660 */:
                calcAddVolume(this.mViewModel.treVolume, 24, KeyConfig.EQ_TREBLE);
                this.mViewModel.treVolumeStr.set(getVolumeStr(this.mViewModel.treVolume));
                return;
            case C0899R.C0901id.bmw_id8_settings_tre_sub_btn /* 2131296661 */:
                calcSubVolume(this.mViewModel.treVolume, 0, KeyConfig.EQ_TREBLE);
                this.mViewModel.treVolumeStr.set(getVolumeStr(this.mViewModel.treVolume));
                return;
            default:
                return;
        }
    }

    private void calcSubVolume(ObservableInt observableInt, int min, String key) {
        try {
            int value = observableInt.get() - 1;
            if (value < min) {
                return;
            }
            FileUtils.savaIntData(key, value);
            observableInt.set(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcAddVolume(ObservableInt observableInt, int max, String key) {
        try {
            int value = observableInt.get() + 1;
            if (value > max) {
                return;
            }
            FileUtils.savaIntData(key, value);
            observableInt.set(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVolumeStr(ObservableInt observableInt) {
        int value = observableInt.get() - 12;
        if (value > 0) {
            String str = "+" + value;
            return str;
        }
        String str2 = String.valueOf(value);
        return str2;
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar.OnValueChangeListener
    public void onValueChange(ID8AudioProgressBar progressBar, int value) {
        switch (progressBar.getId()) {
            case C0899R.C0901id.bmw_id8_settings_audio_low /* 2131296558 */:
                this.mViewModel.bassVolume.set(value);
                this.mViewModel.bassVolumeStr.set(getVolumeStr(this.mViewModel.bassVolume));
                FileUtils.savaIntData(KeyConfig.EQ_BASS, value);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_mid /* 2131296559 */:
                this.mViewModel.midVolume.set(value);
                this.mViewModel.midVolumeStr.set(getVolumeStr(this.mViewModel.midVolume));
                FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, value);
                return;
            case C0899R.C0901id.bmw_id8_settings_audio_tre /* 2131296571 */:
                this.mViewModel.treVolume.set(value);
                this.mViewModel.treVolumeStr.set(getVolumeStr(this.mViewModel.treVolume));
                FileUtils.savaIntData(KeyConfig.EQ_TREBLE, value);
                return;
            default:
                return;
        }
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar.OnTouchChangeListener
    public void onStartTrackingTouch(ID8AudioProgressBar progressBar) {
        this.mViewModel.audioBgShow.set(false);
        progressBar.setFocusableInTouchMode(true);
        progressBar.requestFocus();
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar.OnTouchChangeListener
    public void onStopTrackingTouch(ID8AudioProgressBar progressBar) {
    }
}

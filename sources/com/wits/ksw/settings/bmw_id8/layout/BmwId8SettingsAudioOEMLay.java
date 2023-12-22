package com.wits.ksw.settings.bmw_id8.layout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsAudioOemLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BmwId8SettingsAudioOEMLay extends RelativeLayout implements ID8ProgressBar.OnValueChangeListener, ID8ProgressBar.OnTouchChangeListener {
    private final String TAG;
    private Context context;
    private int[] imageButtonId;
    private BmwId8SettingsAudioOemLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsAudioOEMLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsAudioOEMLay";
        this.imageButtonId = new int[]{C0899R.C0901id.bmw_id8_settings_oem_call_sub_btn, C0899R.C0901id.bmw_id8_settings_oem_call_add_btn, C0899R.C0901id.bmw_id8_settings_navi_sub_btn, C0899R.C0901id.bmw_id8_settings_navi_add_btn};
        this.context = context;
        this.mBinding = (BmwId8SettingsAudioOemLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_audio_oem_layout, null, false);
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
        try {
            this.mBinding.bmwId8SettingsOemCallSeekbar.setOnValueChangeListener(this);
            this.mBinding.bmwId8SettingsNaviSeekbar.setOnValueChangeListener(this);
            this.mBinding.bmwId8SettingsOemCallSeekbar.setOnTouchChangeListener(this);
            this.mBinding.bmwId8SettingsNaviSeekbar.setOnTouchChangeListener(this);
            int i = 0;
            while (true) {
                int[] iArr = this.imageButtonId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsAudioOEMLay.1
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsAudioOEMLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() == 1 && !v.isFocused()) {
                                v.requestFocus();
                                return false;
                            }
                            return false;
                        }
                    });
                    i++;
                } else {
                    this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsAudioOEMLay.2
                        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                            Log.i("BmwId8SettingsAudioOEMLay", "onGlobalFocusChanged: " + BmwId8SettingsAudioOEMLay.this.mBinding.bmwId8SettingsAudioOemLay.hasFocus());
                            if (BmwId8SettingsAudioOEMLay.this.mBinding.bmwId8SettingsAudioOemLay.hasFocus()) {
                                BmwId8SettingsAudioOEMLay.this.mViewModel.audioBgShow.set(false);
                            }
                        }
                    });
                    this.mViewModel.oemCallVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.CAR_PHONE_VOL));
                    this.mViewModel.oemNaviVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.CAR_NAVI_VOL));
                    return;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initData() {
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnValueChangeListener
    public void onValueChange(ID8ProgressBar progressBar, int value, float progress) {
        switch (progressBar.getId()) {
            case C0899R.C0901id.bmw_id8_settings_navi_seekbar /* 2131296615 */:
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, value);
                this.mViewModel.oemNaviVolume.set(value);
                return;
            case C0899R.C0901id.bmw_id8_settings_oem_call_seekbar /* 2131296618 */:
                FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, value);
                this.mViewModel.oemCallVolume.set(value);
                return;
            default:
                return;
        }
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnTouchChangeListener
    public void onStartTrackingTouch(ID8ProgressBar progressBar) {
        this.mViewModel.audioBgShow.set(false);
        progressBar.requestFocus();
    }

    @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.OnTouchChangeListener
    public void onStopTrackingTouch(ID8ProgressBar progressBar) {
    }
}

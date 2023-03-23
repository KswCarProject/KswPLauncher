package com.wits.ksw.settings.bmw_id8.layout;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsAudioAndroidLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsAudioAndroidLay extends RelativeLayout implements ID8ProgressBar.OnValueChangeListener, ID8ProgressBar.OnTouchChangeListener {
    private final String TAG = "BmwId8SettingsAudioAndroidLay";
    private Context context;
    private int[] imageButtonId = {R.id.bmw_id8_settings_meida_sub_btn, R.id.bmw_id8_settings_meida_add_btn, R.id.bmw_id8_settings_android_call_sub_btn, R.id.bmw_id8_settings_android_call_add_btn};
    /* access modifiers changed from: private */
    public BmwId8SettingsAudioAndroidLayoutBinding mBinding;
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsAudioAndroidLay(Context context2) {
        super(context2);
        this.context = context2;
        this.mBinding = (BmwId8SettingsAudioAndroidLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context2), R.layout.bmw_id8_settings_audio_android_layout, (ViewGroup) null, false);
        this.mBinding.getRoot().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        addView(this.mBinding.getRoot());
        initView();
        initData();
    }

    private void initView() {
        try {
            this.mBinding.bmwId8SettingsMeidaSeekbar.setOnValueChangeListener(this);
            this.mBinding.bmwId8SettingsAndroidCallSeekbar.setOnValueChangeListener(this);
            this.mBinding.bmwId8SettingsMeidaSeekbar.setOnTouchChangeListener(this);
            this.mBinding.bmwId8SettingsAndroidCallSeekbar.setOnTouchChangeListener(this);
            int i = 0;
            while (true) {
                int[] iArr = this.imageButtonId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsAudioAndroidLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() != 1 || v.isFocused()) {
                                return false;
                            }
                            v.requestFocus();
                            return false;
                        }
                    });
                    i++;
                } else {
                    this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                            Log.i("BmwId8SettingsAudioAndroidLay", "onGlobalFocusChanged: " + BmwId8SettingsAudioAndroidLay.this.mBinding.bmwId8SettingsAudioAndroidLay.hasFocus());
                            if (BmwId8SettingsAudioAndroidLay.this.mBinding.bmwId8SettingsAudioAndroidLay.hasFocus()) {
                                BmwId8SettingsAudioAndroidLay.this.mViewModel.audioBgShow.set(false);
                            }
                        }
                    });
                    return;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initData() {
        try {
            this.mViewModel.androidMediaVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
            this.mViewModel.androidCallVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_PHONE_VOL));
            ContentResolver contentResolver = getContext().getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_MEDIA_VOL), true, new ContentObserver(new Handler()) {
                public void onChange(boolean selfChange) {
                    Log.i("BmwId8SettingsAudioAndroidLay", "KeyConfig.ANDROID_MEDIA_VOL onChange ");
                    try {
                        BmwId8SettingsAudioAndroidLay.this.mViewModel.androidMediaVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
                    } catch (RemoteException e) {
                    }
                }
            });
            contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_PHONE_VOL), true, new ContentObserver(new Handler()) {
                public void onChange(boolean selfChange) {
                    Log.i("BmwId8SettingsAudioAndroidLay", "KeyConfig.ANDROID_PHONE_VOL onChange ");
                    try {
                        BmwId8SettingsAudioAndroidLay.this.mViewModel.androidCallVolume.set(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_PHONE_VOL));
                    } catch (RemoteException e) {
                    }
                }
            });
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void onValueChange(ID8ProgressBar progressBar, int value, float progress) {
        switch (progressBar.getId()) {
            case R.id.bmw_id8_settings_android_call_seekbar /*2131296525*/:
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, value);
                this.mViewModel.androidCallVolume.set(value);
                return;
            case R.id.bmw_id8_settings_meida_seekbar /*2131296580*/:
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, value);
                this.mViewModel.androidMediaVolume.set(value);
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(ID8ProgressBar progressBar) {
        this.mViewModel.audioBgShow.set(false);
        progressBar.requestFocus();
    }

    public void onStopTrackingTouch(ID8ProgressBar progressBar) {
    }
}

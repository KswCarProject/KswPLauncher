package com.wits.ksw.settings.bmw_id8.layout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsSystemCameraLayoutBinding;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsSystemCameraLay extends RelativeLayout {
    private final String TAG = "BmwId8SettingsSystemCameraLay";
    private Context context;
    /* access modifiers changed from: private */
    public BmwId8SettingsSystemCameraLayoutBinding mBinding;
    private int mCameraType = 0;
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId = {R.id.bmw_id8_settings_system_camera_after, R.id.bmw_id8_settings_system_camera_oem, R.id.bmw_id8_settings_system_camera_360};

    public BmwId8SettingsSystemCameraLay(Context context2) {
        super(context2);
        this.context = context2;
        this.mBinding = (BmwId8SettingsSystemCameraLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context2), R.layout.bmw_id8_settings_system_camera_layout, (ViewGroup) null, false);
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
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.i("BmwId8SettingsSystemCameraLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemCameraLay.this.mBinding.bmwId8SettingsSystemCameraLay.hasFocus());
                    if (BmwId8SettingsSystemCameraLay.this.mBinding.bmwId8SettingsSystemCameraLay.hasFocus()) {
                        BmwId8SettingsSystemCameraLay.this.mViewModel.systemBgShow.set(false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            this.mCameraType = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_SXT);
            this.mViewModel.rearCameraType.set(this.mCameraType);
            Log.i("BmwId8SettingsSystemCameraLay", " mCameraType " + this.mCameraType);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemCameraLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() != 1 || v.isFocused()) {
                                return false;
                            }
                            v.requestFocus();
                            return false;
                        }
                    });
                    i++;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

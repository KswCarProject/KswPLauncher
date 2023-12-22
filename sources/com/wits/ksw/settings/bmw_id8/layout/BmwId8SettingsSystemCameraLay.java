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
import com.wits.ksw.databinding.BmwId8SettingsSystemCameraLayoutBinding;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemCameraLay extends RelativeLayout {
    private final String TAG;
    private Context context;
    private BmwId8SettingsSystemCameraLayoutBinding mBinding;
    private int mCameraType;
    private BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId;

    public BmwId8SettingsSystemCameraLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemCameraLay";
        this.mCameraType = 0;
        this.relativeLayoutId = new int[]{C0899R.C0901id.bmw_id8_settings_system_camera_after, C0899R.C0901id.bmw_id8_settings_system_camera_oem, C0899R.C0901id.bmw_id8_settings_system_camera_360, C0899R.C0901id.bmw_id8_settings_system_camera_360_built};
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemCameraLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_camera_layout, null, false);
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
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemCameraLay.1
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
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
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemCameraLay.2
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemCameraLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                            if (event.getAction() == 1 && !v.isFocused()) {
                                v.requestFocus();
                                return false;
                            }
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

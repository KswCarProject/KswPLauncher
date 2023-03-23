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
import com.wits.ksw.databinding.BmwId8SettingsSystemTempLayoutBinding;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsSystemTempLay extends RelativeLayout {
    private final String TAG = "BmwId8SettingsSystemTempLay";
    private Context context;
    /* access modifiers changed from: private */
    public BmwId8SettingsSystemTempLayoutBinding mBinding;
    private int mTempUnit = 0;
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId = {R.id.bmw_id8_settings_system_temp_c, R.id.bmw_id8_settings_system_temp_f};

    public BmwId8SettingsSystemTempLay(Context context2) {
        super(context2);
        this.context = context2;
        this.mBinding = (BmwId8SettingsSystemTempLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context2), R.layout.bmw_id8_settings_system_temp_layout, (ViewGroup) null, false);
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
                    Log.i("BmwId8SettingsSystemTempLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemTempLay.this.mBinding.bmwId8SettingsSystemTempLay.hasFocus());
                    if (BmwId8SettingsSystemTempLay.this.mBinding.bmwId8SettingsSystemTempLay.hasFocus()) {
                        BmwId8SettingsSystemTempLay.this.mViewModel.systemBgShow.set(false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            this.mTempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            this.mViewModel.tempUnit.set(this.mTempUnit);
            Log.i("BmwId8SettingsSystemTempLay", " mTempUnit " + this.mTempUnit);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemTempLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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

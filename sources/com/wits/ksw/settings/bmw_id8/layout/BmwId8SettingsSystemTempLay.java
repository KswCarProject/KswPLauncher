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
import com.wits.ksw.databinding.BmwId8SettingsSystemTempLayoutBinding;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemTempLay extends RelativeLayout {
    private final String TAG;
    private Context context;
    private BmwId8SettingsSystemTempLayoutBinding mBinding;
    private int mTempUnit;
    private BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId;

    public BmwId8SettingsSystemTempLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemTempLay";
        this.relativeLayoutId = new int[]{C0899R.C0901id.bmw_id8_settings_system_temp_c, C0899R.C0901id.bmw_id8_settings_system_temp_f};
        this.mTempUnit = 0;
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemTempLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_temp_layout, null, false);
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
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemTempLay.1
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
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
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemTempLay.2
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemTempLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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

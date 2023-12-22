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
import com.wits.ksw.databinding.BmwId8SettingsSystemFuelLayoutBinding;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemFuelLay extends RelativeLayout {
    private final String TAG;
    private Context context;
    private BmwId8SettingsSystemFuelLayoutBinding mBinding;
    private int mFuelUnit;
    private BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId;

    public BmwId8SettingsSystemFuelLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemFuelLay";
        this.relativeLayoutId = new int[]{C0899R.C0901id.bmw_id8_settings_system_fuel_l, C0899R.C0901id.bmw_id8_settings_system_fuel_us, C0899R.C0901id.bmw_id8_settings_system_fuel_uk};
        this.mFuelUnit = 0;
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemFuelLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_fuel_layout, null, false);
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
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemFuelLay.1
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.i("BmwId8SettingsSystemFuelLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemFuelLay.this.mBinding.bmwId8SettingsSystemFuelLay.hasFocus());
                    if (BmwId8SettingsSystemFuelLay.this.mBinding.bmwId8SettingsSystemFuelLay.hasFocus()) {
                        BmwId8SettingsSystemFuelLay.this.mViewModel.systemBgShow.set(false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            this.mFuelUnit = PowerManagerApp.getSettingsInt(KeyConfig.FUEL_UNIT);
            this.mViewModel.fuelUnit.set(this.mFuelUnit);
            Log.i("BmwId8SettingsSystemFuelLay", " mFuelUnit " + this.mFuelUnit);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemFuelLay.2
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsSystemFuelLay", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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

package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsTimeLayoutBinding;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsTimeActivity extends BaseSkinActivity {
    private final String TAG = "BmwId8SettingsTimeActivity";
    private BmwId8SettingsTimeLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId = {R.id.bmw_id8_settings_time_sync, R.id.bmw_id8_settings_time_car, R.id.bmw_id8_settings_time_android, R.id.bmw_id8_settings_time_format, R.id.bmw_id8_settings_time_12, R.id.bmw_id8_settings_time_24};
    private int timeFormat;
    private int timeSync;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsTimeActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsTimeLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_time_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        initView();
    }

    private void initView() {
        int i = 0;
        while (true) {
            try {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsTimeActivity", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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
            } catch (Exception e) {
                e.getStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsTimeActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsTimeActivity", " onResume ");
        initData();
    }

    private void initData() {
        try {
            this.mViewModel.timeSyncShow.set(false);
            this.mViewModel.timeFormatShow.set(false);
            this.mViewModel.timeSyncState.set(PowerManagerApp.getSettingsInt(KeyConfig.TIME_SOURCE));
            this.mViewModel.timeFormatState.set(PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT));
            if (getCurrentFocus() == null) {
                this.mBinding.bmwId8SettingsTimeSync.requestFocus();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();
        View view = getCurrentFocus();
        Log.i("BmwId8SettingsTimeActivity", "dispatchKeyEvent keyCode " + keyCode + " action " + action);
        switch (action) {
            case 0:
                if (event.getKeyCode() == 21 && view != null && this.mBinding.bmwId8SettingsTimeLay.hasFocus()) {
                    Log.i("BmwId8SettingsTimeActivity", " focusView " + view.toString() + " view.isFocused() " + view.isFocused());
                    KswUtils.sendKeyDownUpSync(4);
                    break;
                }
        }
        return super.dispatchKeyEvent(event);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsTimeActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsTimeActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

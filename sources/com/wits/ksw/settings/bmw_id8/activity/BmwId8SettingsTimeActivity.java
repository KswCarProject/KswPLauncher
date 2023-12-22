package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsTimeLayoutBinding;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class BmwId8SettingsTimeActivity extends BaseSkinActivity {
    private BmwId8SettingsTimeLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private int timeFormat;
    private int timeSync;
    private final String TAG = "BmwId8SettingsTimeActivity";
    private int[] relativeLayoutId = {C0899R.C0901id.bmw_id8_settings_time_sync, C0899R.C0901id.bmw_id8_settings_time_car, C0899R.C0901id.bmw_id8_settings_time_android, C0899R.C0901id.bmw_id8_settings_time_format, C0899R.C0901id.bmw_id8_settings_time_12, C0899R.C0901id.bmw_id8_settings_time_24};

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsTimeActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsTimeLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_settings_time_layout);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        initView();
    }

    private void initView() {
        int i = 0;
        while (true) {
            try {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsTimeActivity.1
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsTimeActivity", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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
            } catch (Exception e) {
                e.getStackTrace();
                return;
            }
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsTimeActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
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

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
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
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsTimeActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsTimeActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

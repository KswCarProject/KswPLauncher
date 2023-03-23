package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsInfoLayoutBinding;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsInfoActivity extends BaseSkinActivity implements View.OnClickListener {
    private final String TAG = "BmwId8SettingsInfoActivity";
    private DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    BmwId8SettingsInfoActivity.this.mViewModel.mcuVersionStr.set(McuUtil.getMcuVersion());
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                BmwId8SettingsInfoActivity.this.sendBroadcast(resetIntent);
            }
        }
    };
    private BmwId8SettingsInfoLayoutBinding mBinding;
    /* access modifiers changed from: private */
    public BmwId8SettingsViewModel mViewModel;
    private int[] relativeLayoutId = {R.id.bmw_id8_info_mcu_upgrade, R.id.bmw_id8_info_system_recovery, R.id.bmw_id8_info_system_update};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsInfoActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsInfoLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_info_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        this.dialogViews = new DialogViews(this);
        initData();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsInfoActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsInfoActivity", " onResume ");
        try {
            if (getCurrentFocus() == null) {
                this.mBinding.bmwId8InfoMcuUpgrade.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
                public void onChange() throws RemoteException {
                    BmwId8SettingsInfoActivity.this.handler.sendEmptyMessage(0);
                }
            });
            this.mViewModel.mcuVersionStr.set(McuUtil.getMcuVersion());
            this.mViewModel.appVersionStr.set(Build.DISPLAY);
            this.mViewModel.systemVersionStr.set(UtilsInfo.getSettingsVer(this) + "-" + UtilsInfo.getIMEI());
            this.mViewModel.storageStr.set(UtilsInfo.getROMSize());
            this.mViewModel.ramStr.set(UtilsInfo.getRAMSize(this));
            this.mBinding.bmwId8InfoSystemVersion.setOnClickListener(this);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnClickListener(this);
                    findViewById(this.relativeLayoutId[i]).setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsInfoActivity", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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
            e.getStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmw_id8_info_mcu_upgrade /*2131296516*/:
                this.dialogViews.updateMcu(getString(R.string.update_mcu_file));
                return;
            case R.id.bmw_id8_info_system_recovery /*2131296521*/:
                this.dialogViews.isQuestView(getString(R.string.update_reset_all), this.handler);
                return;
            case R.id.bmw_id8_info_system_update /*2131296522*/:
                new TxzMessage(2070, "system.ota.check", (Bundle) null).sendBroadCast(this);
                return;
            case R.id.bmw_id8_info_system_version /*2131296523*/:
                UtilsInfo.showQRCode(this);
                return;
            default:
                return;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();
        View view = getCurrentFocus();
        Log.i("BmwId8SettingsInfoActivity", "dispatchKeyEvent keyCode " + keyCode + " action " + action);
        switch (action) {
            case 0:
                if (event.getKeyCode() == 21 && view != null && this.mBinding.bmwId8SettingsInfoLay.hasFocus()) {
                    Log.i("BmwId8SettingsInfoActivity", " focusView " + view.toString() + " view.isFocused() " + view.isFocused());
                    KswUtils.sendKeyDownUpSync(4);
                    break;
                }
        }
        return super.dispatchKeyEvent(event);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsInfoActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsInfoActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

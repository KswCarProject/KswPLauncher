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
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsInfoLayoutBinding;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class BmwId8SettingsInfoActivity extends BaseSkinActivity implements View.OnClickListener {
    private DialogViews dialogViews;
    private BmwId8SettingsInfoLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private final String TAG = "BmwId8SettingsInfoActivity";
    private int[] relativeLayoutId = {C0899R.C0901id.bmw_id8_info_mcu_upgrade, C0899R.C0901id.bmw_id8_info_system_recovery, C0899R.C0901id.bmw_id8_info_system_update};
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsInfoActivity.1
        @Override // android.os.Handler
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

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsInfoActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsInfoLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_settings_info_layout);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        this.dialogViews = new DialogViews(this);
        initData();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsInfoActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
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
            PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsInfoActivity.2
                @Override // com.wits.pms.IContentObserver
                public void onChange() throws RemoteException {
                    BmwId8SettingsInfoActivity.this.handler.sendEmptyMessage(0);
                }
            });
            this.mViewModel.mcuVersionStr.set(McuUtil.getMcuVersion());
            this.mViewModel.appVersionStr.set("Witstek-" + Build.DISPLAY);
            this.mViewModel.systemVersionStr.set(UtilsInfo.getSettingsVer(this) + "-" + UtilsInfo.getIMEI());
            this.mViewModel.storageStr.set(UtilsInfo.getROMSize());
            this.mViewModel.ramStr.set(UtilsInfo.getRAMSize(this));
            this.mBinding.bmwId8InfoSystemVersion.setOnClickListener(this);
            int i = 0;
            while (true) {
                int[] iArr = this.relativeLayoutId;
                if (i < iArr.length) {
                    findViewById(iArr[i]).setOnClickListener(this);
                    findViewById(this.relativeLayoutId[i]).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsInfoActivity.3
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("BmwId8SettingsInfoActivity", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
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
            e.getStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.bmw_id8_info_mcu_upgrade /* 2131296540 */:
                this.dialogViews.updateMcu(getString(C0899R.string.update_mcu_file));
                return;
            case C0899R.C0901id.bmw_id8_info_system_recovery /* 2131296545 */:
                this.dialogViews.isQuestView(getString(C0899R.string.update_reset_all), this.handler);
                return;
            case C0899R.C0901id.bmw_id8_info_system_update /* 2131296546 */:
                TxzMessage txzMessage = new TxzMessage(2070, "system.ota.check", null);
                txzMessage.sendBroadCast(this);
                return;
            case C0899R.C0901id.bmw_id8_info_system_version /* 2131296547 */:
                UtilsInfo.showQRCode(this);
                return;
            default:
                return;
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
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
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsInfoActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsInfoActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

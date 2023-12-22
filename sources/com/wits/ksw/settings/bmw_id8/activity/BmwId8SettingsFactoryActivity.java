package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsFactoryLayoutBinding;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class BmwId8SettingsFactoryActivity extends BaseSkinActivity implements View.OnClickListener {
    private final String TAG = "BmwId8SettingsFactoryActivity";
    private String defPwd = "1314";
    private int[] imageViewId = {C0899R.C0901id.bmw_id8_settings_factory_1_btn, C0899R.C0901id.bmw_id8_settings_factory_2_btn, C0899R.C0901id.bmw_id8_settings_factory_3_btn, C0899R.C0901id.bmw_id8_settings_factory_4_btn, C0899R.C0901id.bmw_id8_settings_factory_5_btn, C0899R.C0901id.bmw_id8_settings_factory_6_btn, C0899R.C0901id.bmw_id8_settings_factory_7_btn, C0899R.C0901id.bmw_id8_settings_factory_8_btn, C0899R.C0901id.bmw_id8_settings_factory_9_btn, C0899R.C0901id.bmw_id8_settings_factory_0_btn, C0899R.C0901id.bmw_id8_settings_factory_del_btn, C0899R.C0901id.bmw_id8_settings_factory_enter_btn};
    private BmwId8SettingsFactoryLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private StringBuffer pwdInput;

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsFactoryActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsFactoryLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_settings_factory_layout);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        initView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsFactoryActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsFactoryActivity", " onResume ");
        initData();
    }

    private void initView() {
        this.pwdInput = new StringBuffer();
        int i = 0;
        while (true) {
            int[] iArr = this.imageViewId;
            if (i < iArr.length) {
                findViewById(iArr[i]).setOnClickListener(this);
                i++;
            } else {
                this.mBinding.bmwId8SettingsFactoryDelBtn.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsFactoryActivity.1
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        BmwId8SettingsFactoryActivity.this.deletePwdText();
                        return true;
                    }
                });
                return;
            }
        }
    }

    private void initData() {
        try {
            deletePwdText();
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            Log.d("BmwId8SettingsFactoryActivity", "===pwd====:" + this.defPwd);
            if (TextUtils.isEmpty(this.defPwd)) {
                this.defPwd = "1314";
            }
        } catch (Exception e) {
            e.getStackTrace();
            this.defPwd = "1314";
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.bmw_id8_settings_factory_0_btn /* 2131296579 */:
                inputPwdText(TxzMessage.TXZ_DISMISS);
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_1_btn /* 2131296580 */:
                inputPwdText(TxzMessage.TXZ_SHOW);
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_2_btn /* 2131296581 */:
                inputPwdText("2");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_3_btn /* 2131296582 */:
                inputPwdText("3");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_4_btn /* 2131296583 */:
                inputPwdText("4");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_5_btn /* 2131296584 */:
                inputPwdText("5");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_6_btn /* 2131296585 */:
                inputPwdText("6");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_7_btn /* 2131296586 */:
                inputPwdText("7");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_8_btn /* 2131296587 */:
                inputPwdText("8");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_9_btn /* 2131296588 */:
                inputPwdText("9");
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_del_btn /* 2131296589 */:
                if (TextUtils.equals(this.mBinding.bmwId8SettingsFactoryPsw.getText().toString(), getResources().getString(C0899R.string.lb_password_error))) {
                    deletePwdText();
                }
                if (this.pwdInput.length() > 0) {
                    StringBuffer stringBuffer = this.pwdInput;
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    this.mBinding.bmwId8SettingsFactoryPsw.setText(this.pwdInput.toString());
                    return;
                }
                return;
            case C0899R.C0901id.bmw_id8_settings_factory_enter_btn /* 2131296590 */:
                if (TextUtils.equals(this.defPwd, this.pwdInput.toString())) {
                    deletePwdText();
                    startActivity(new Intent(this, FactoryActivity.class));
                    finish();
                    return;
                }
                this.mBinding.bmwId8SettingsFactoryPsw.setText(getResources().getString(C0899R.string.lb_password_error));
                return;
            default:
                return;
        }
    }

    private void inputPwdText(String str) {
        try {
            if (TextUtils.equals(this.mBinding.bmwId8SettingsFactoryPsw.getText().toString(), getResources().getString(C0899R.string.lb_password_error))) {
                deletePwdText();
            }
            if (this.pwdInput.length() <= 8) {
                this.pwdInput.append(str);
                this.mBinding.bmwId8SettingsFactoryPsw.setText(this.pwdInput.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deletePwdText() {
        if (this.pwdInput.length() > 0) {
            StringBuffer stringBuffer = this.pwdInput;
            stringBuffer.delete(0, stringBuffer.length());
            this.mBinding.bmwId8SettingsFactoryPsw.setText(this.pwdInput.toString());
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsFactoryActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsFactoryActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

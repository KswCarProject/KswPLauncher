package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsFactoryLayoutBinding;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BmwId8SettingsFactoryActivity extends BaseSkinActivity implements View.OnClickListener {
    private final String TAG = "BmwId8SettingsFactoryActivity";
    private String defPwd = "1314";
    private int[] imageViewId = {R.id.bmw_id8_settings_factory_1_btn, R.id.bmw_id8_settings_factory_2_btn, R.id.bmw_id8_settings_factory_3_btn, R.id.bmw_id8_settings_factory_4_btn, R.id.bmw_id8_settings_factory_5_btn, R.id.bmw_id8_settings_factory_6_btn, R.id.bmw_id8_settings_factory_7_btn, R.id.bmw_id8_settings_factory_8_btn, R.id.bmw_id8_settings_factory_9_btn, R.id.bmw_id8_settings_factory_0_btn, R.id.bmw_id8_settings_factory_del_btn, R.id.bmw_id8_settings_factory_enter_btn};
    private BmwId8SettingsFactoryLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private StringBuffer pwdInput;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsFactoryActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsFactoryLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_factory_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsFactoryActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
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
                this.mBinding.bmwId8SettingsFactoryDelBtn.setOnLongClickListener(new View.OnLongClickListener() {
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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmw_id8_settings_factory_0_btn /*2131296554*/:
                inputPwdText(TxzMessage.TXZ_DISMISS);
                return;
            case R.id.bmw_id8_settings_factory_1_btn /*2131296555*/:
                inputPwdText(TxzMessage.TXZ_SHOW);
                return;
            case R.id.bmw_id8_settings_factory_2_btn /*2131296556*/:
                inputPwdText("2");
                return;
            case R.id.bmw_id8_settings_factory_3_btn /*2131296557*/:
                inputPwdText("3");
                return;
            case R.id.bmw_id8_settings_factory_4_btn /*2131296558*/:
                inputPwdText("4");
                return;
            case R.id.bmw_id8_settings_factory_5_btn /*2131296559*/:
                inputPwdText("5");
                return;
            case R.id.bmw_id8_settings_factory_6_btn /*2131296560*/:
                inputPwdText("6");
                return;
            case R.id.bmw_id8_settings_factory_7_btn /*2131296561*/:
                inputPwdText("7");
                return;
            case R.id.bmw_id8_settings_factory_8_btn /*2131296562*/:
                inputPwdText("8");
                return;
            case R.id.bmw_id8_settings_factory_9_btn /*2131296563*/:
                inputPwdText("9");
                return;
            case R.id.bmw_id8_settings_factory_del_btn /*2131296564*/:
                if (TextUtils.equals(this.mBinding.bmwId8SettingsFactoryPsw.getText().toString(), getResources().getString(R.string.lb_password_error))) {
                    deletePwdText();
                }
                if (this.pwdInput.length() > 0) {
                    StringBuffer stringBuffer = this.pwdInput;
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    this.mBinding.bmwId8SettingsFactoryPsw.setText(this.pwdInput.toString());
                    return;
                }
                return;
            case R.id.bmw_id8_settings_factory_enter_btn /*2131296565*/:
                if (TextUtils.equals(this.defPwd, this.pwdInput.toString())) {
                    deletePwdText();
                    startActivity(new Intent(this, FactoryActivity.class));
                    finish();
                    return;
                }
                this.mBinding.bmwId8SettingsFactoryPsw.setText(getResources().getString(R.string.lb_password_error));
                return;
            default:
                return;
        }
    }

    private void inputPwdText(String str) {
        try {
            if (TextUtils.equals(this.mBinding.bmwId8SettingsFactoryPsw.getText().toString(), getResources().getString(R.string.lb_password_error))) {
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

    /* access modifiers changed from: private */
    public void deletePwdText() {
        if (this.pwdInput.length() > 0) {
            StringBuffer stringBuffer = this.pwdInput;
            stringBuffer.delete(0, stringBuffer.length());
            this.mBinding.bmwId8SettingsFactoryPsw.setText(this.pwdInput.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsFactoryActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsFactoryActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}

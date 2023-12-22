package com.wits.ksw.settings.audi_mib3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3ReverCameraBinding;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes15.dex */
public class AudiMib3ReverCameraActivity extends AudiMib3SubActivity implements View.OnKeyListener {
    private AudiMib3SystemViewModel viewModel;
    private View[] views = new View[4];

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMib3ReverCameraBinding binding = (AudiMib3ReverCameraBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_mib3_rever_camera, null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        this.tv_title_set.setText(getResources().getString(C0899R.string.set_text5));
        AudiMib3SystemViewModel audiMib3SystemViewModel = AudiMib3SettingMainActivity.getInstance.viewModel;
        this.viewModel = audiMib3SystemViewModel;
        binding.setVm(audiMib3SystemViewModel);
        this.views[0] = binding.RadioButton1;
        this.views[1] = binding.RadioButton2;
        this.views[2] = binding.RadioButton3;
        this.views[3] = binding.RadioButton4;
        setOnKeyListener();
        setSelected(this.views[this.viewModel.reverCamera.get()]);
    }

    private void setOnKeyListener() {
        View[] viewArr;
        for (View mView : this.views) {
            mView.setOnKeyListener(this);
        }
    }

    public void setSelected(View view) {
        View[] viewArr = this.views;
        int length = viewArr.length;
        for (int i = 0; i < length; i++) {
            View mView = viewArr[i];
            mView.setSelected(mView == view);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        setSelected(v);
        return false;
    }
}

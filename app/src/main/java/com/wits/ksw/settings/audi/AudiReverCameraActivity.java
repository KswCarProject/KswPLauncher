package com.wits.ksw.settings.audi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiReverCameraBinding;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiReverCameraActivity extends AudiSubActivity implements View.OnKeyListener {
    private AudiSystemViewModel viewModel;
    private View[] views = new View[3];

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiReverCameraBinding binding = (AudiReverCameraBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_rever_camera, (ViewGroup) null, false);
        this.contentLayout.addView(binding.getRoot(), -1, -1);
        this.viewModel = AudiSettingMainActivity.getInstance.viewModel;
        binding.setVm(this.viewModel);
        this.views[0] = binding.RadioButton1;
        this.views[1] = binding.RadioButton2;
        this.views[2] = binding.RadioButton3;
        setOnKeyListener();
        setSelected(this.views[this.viewModel.reverCamera.get()]);
    }

    private void setOnKeyListener() {
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

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        setSelected(v);
        return false;
    }
}

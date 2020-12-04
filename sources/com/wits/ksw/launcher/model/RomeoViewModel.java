package com.wits.ksw.launcher.model;

import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityRomeoBinding;

public class RomeoViewModel extends LauncherViewModel implements View.OnFocusChangeListener {
    private ActivityRomeoBinding romeoBinding;

    public void setRomeoBinding(ActivityRomeoBinding romeoBinding2) {
        this.romeoBinding = romeoBinding2;
        romeoBinding2.romeoNavi.setOnFocusChangeListener(this);
        romeoBinding2.romeoMusic.setOnFocusChangeListener(this);
        romeoBinding2.romeoVideo.setOnFocusChangeListener(this);
        romeoBinding2.romeoPhone.setOnFocusChangeListener(this);
        romeoBinding2.romeoSetting.setOnFocusChangeListener(this);
        romeoBinding2.romeoApp.setOnFocusChangeListener(this);
    }

    private void changeIndicatorFocus(int type) {
        this.romeoBinding.romeoIndicator1.setVisibility(8);
        this.romeoBinding.romeoIndicator2.setVisibility(8);
        this.romeoBinding.romeoIndicator3.setVisibility(8);
        this.romeoBinding.romeoIndicator4.setVisibility(8);
        this.romeoBinding.romeoIndicator5.setVisibility(8);
        this.romeoBinding.romeoIndicator6.setVisibility(8);
        switch (type) {
            case 1:
                this.romeoBinding.romeoIndicator1.setVisibility(0);
                return;
            case 2:
                this.romeoBinding.romeoIndicator2.setVisibility(0);
                return;
            case 3:
                this.romeoBinding.romeoIndicator3.setVisibility(0);
                return;
            case 4:
                this.romeoBinding.romeoIndicator4.setVisibility(0);
                return;
            case 5:
                this.romeoBinding.romeoIndicator5.setVisibility(0);
                return;
            case 6:
                this.romeoBinding.romeoIndicator6.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        if (id == R.id.romeo_app) {
            changeIndicatorFocus(5);
        } else if (id != R.id.romeo_video) {
            switch (id) {
                case R.id.romeo_music:
                    changeIndicatorFocus(2);
                    return;
                case R.id.romeo_navi:
                    changeIndicatorFocus(1);
                    return;
                case R.id.romeo_phone:
                    changeIndicatorFocus(4);
                    return;
                case R.id.romeo_setting:
                    changeIndicatorFocus(6);
                    return;
                default:
                    return;
            }
        } else {
            changeIndicatorFocus(3);
        }
    }
}

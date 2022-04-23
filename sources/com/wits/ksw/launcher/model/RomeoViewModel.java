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
        switch (v.getId()) {
            case R.id.romeo_app /*2131297336*/:
                changeIndicatorFocus(5);
                return;
            case R.id.romeo_music /*2131297355*/:
                changeIndicatorFocus(2);
                return;
            case R.id.romeo_navi /*2131297356*/:
                changeIndicatorFocus(1);
                return;
            case R.id.romeo_phone /*2131297357*/:
                changeIndicatorFocus(4);
                return;
            case R.id.romeo_setting /*2131297358*/:
                changeIndicatorFocus(6);
                return;
            case R.id.romeo_video /*2131297374*/:
                changeIndicatorFocus(3);
                return;
            default:
                return;
        }
    }
}

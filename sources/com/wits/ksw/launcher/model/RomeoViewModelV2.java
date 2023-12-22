package com.wits.ksw.launcher.model;

import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityRomeoV2Binding;

/* loaded from: classes9.dex */
public class RomeoViewModelV2 extends LauncherViewModel implements View.OnFocusChangeListener {
    private ActivityRomeoV2Binding romeoBinding;

    public void setRomeoBinding(ActivityRomeoV2Binding romeoBinding) {
        this.romeoBinding = romeoBinding;
        romeoBinding.romeoNavi.setOnFocusChangeListener(this);
        romeoBinding.romeoMusic.setOnFocusChangeListener(this);
        romeoBinding.romeoVideo.setOnFocusChangeListener(this);
        romeoBinding.romeoPhone.setOnFocusChangeListener(this);
        romeoBinding.romeoSetting.setOnFocusChangeListener(this);
        romeoBinding.romeoApp.setOnFocusChangeListener(this);
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

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case C0899R.C0901id.romeo_app /* 2131297664 */:
                changeIndicatorFocus(5);
                return;
            case C0899R.C0901id.romeo_music /* 2131297683 */:
                changeIndicatorFocus(2);
                return;
            case C0899R.C0901id.romeo_navi /* 2131297684 */:
                changeIndicatorFocus(1);
                return;
            case C0899R.C0901id.romeo_phone /* 2131297685 */:
                changeIndicatorFocus(4);
                return;
            case C0899R.C0901id.romeo_setting /* 2131297686 */:
                changeIndicatorFocus(6);
                return;
            case C0899R.C0901id.romeo_video /* 2131297702 */:
                changeIndicatorFocus(3);
                return;
            default:
                return;
        }
    }
}

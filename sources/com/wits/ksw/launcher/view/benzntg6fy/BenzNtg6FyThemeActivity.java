package com.wits.ksw.launcher.view.benzntg6fy;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.ksw.settings.TxzMessage;

/* loaded from: classes8.dex */
public class BenzNtg6FyThemeActivity extends Activity {
    private RadioButton rb_benz_fy_bg1;
    private RadioButton rb_benz_fy_bg2;
    private RadioButton rb_benz_fy_bg3;
    private RadioButton rb_benz_fy_bg4;
    private RadioButton rb_benz_fy_bg5;
    private RadioButton rb_benz_fy_bg6;
    private RadioButton rb_benz_fy_bg7;
    private RadioButton rb_benz_fy_bg8;
    private TextView tv_bg1;
    private TextView tv_bg2;
    private TextView tv_bg3;
    private TextView tv_bg4;
    private TextView tv_bg5;
    private TextView tv_bg6;
    private TextView tv_bg7;
    private TextView tv_bg8;
    private String styleIndex = TxzMessage.TXZ_SHOW;
    private String bgIndex = "8";
    private View.OnClickListener mThemeItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyThemeActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            LOGE.m43E("v.getId() = " + v.getId());
            switch (v.getId()) {
                case C0899R.C0901id.rb_benz_fy_bg1 /* 2131297454 */:
                    BenzNtg6FyConfigs.bg_sel = 1;
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 1);
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg2 /* 2131297455 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 2);
                    BenzNtg6FyConfigs.bg_sel = 2;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg3 /* 2131297456 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 3);
                    BenzNtg6FyConfigs.bg_sel = 3;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg4 /* 2131297457 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 4);
                    BenzNtg6FyConfigs.bg_sel = 4;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg5 /* 2131297458 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 5);
                    BenzNtg6FyConfigs.bg_sel = 5;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg6 /* 2131297459 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 6);
                    BenzNtg6FyConfigs.bg_sel = 6;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg7 /* 2131297460 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 7);
                    BenzNtg6FyConfigs.bg_sel = 7;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                case C0899R.C0901id.rb_benz_fy_bg8 /* 2131297461 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_SAVE, 8);
                    BenzNtg6FyConfigs.bg_sel = 8;
                    MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGE.m44D("BenzNtg6FyThemeActivity00000000000");
        setContentView(C0899R.C0902layout.benz_ntg6_fy_theme_set_layout);
        initViews();
        getWindow().setGravity(80);
    }

    private void initViews() {
        this.tv_bg1 = (TextView) findViewById(C0899R.C0901id.tv_bg1);
        this.tv_bg2 = (TextView) findViewById(C0899R.C0901id.tv_bg2);
        this.tv_bg3 = (TextView) findViewById(C0899R.C0901id.tv_bg3);
        this.tv_bg4 = (TextView) findViewById(C0899R.C0901id.tv_bg4);
        this.tv_bg5 = (TextView) findViewById(C0899R.C0901id.tv_bg5);
        this.tv_bg6 = (TextView) findViewById(C0899R.C0901id.tv_bg6);
        this.tv_bg7 = (TextView) findViewById(C0899R.C0901id.tv_bg7);
        this.tv_bg8 = (TextView) findViewById(C0899R.C0901id.tv_bg8);
        this.rb_benz_fy_bg1 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg1);
        this.rb_benz_fy_bg2 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg2);
        this.rb_benz_fy_bg3 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg3);
        this.rb_benz_fy_bg4 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg4);
        this.rb_benz_fy_bg5 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg5);
        this.rb_benz_fy_bg6 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg6);
        this.rb_benz_fy_bg7 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg7);
        this.rb_benz_fy_bg8 = (RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg8);
        this.tv_bg1.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg2.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg3.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg4.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg5.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg6.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg7.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg8.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg1.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg2.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg3.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg4.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg5.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg6.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg7.setOnClickListener(this.mThemeItemClickListener);
        this.rb_benz_fy_bg8.setOnClickListener(this.mThemeItemClickListener);
        this.tv_bg1.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 1");
        this.tv_bg2.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 2");
        this.tv_bg3.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 3");
        this.tv_bg4.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 4");
        this.tv_bg5.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 5");
        this.tv_bg6.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 6");
        this.tv_bg7.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 7");
        this.tv_bg8.setText(getResources().getString(C0899R.string.benz_ntg6_fy_theme_settings) + " 8");
        String string = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgIndex = "8";
        }
        int iBgIndex = Integer.parseInt(this.bgIndex);
        LOGE.m44D("Benz2021DialogThemeActivity  iBgIndex = " + iBgIndex);
        switch (iBgIndex) {
            case 1:
                BenzNtg6FyConfigs.bg_sel = 1;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg1)).setChecked(true);
                return;
            case 2:
                BenzNtg6FyConfigs.bg_sel = 2;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg2)).setChecked(true);
                return;
            case 3:
                BenzNtg6FyConfigs.bg_sel = 3;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg3)).setChecked(true);
                return;
            case 4:
                BenzNtg6FyConfigs.bg_sel = 4;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg4)).setChecked(true);
                return;
            case 5:
                BenzNtg6FyConfigs.bg_sel = 5;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg5)).setChecked(true);
                return;
            case 6:
                BenzNtg6FyConfigs.bg_sel = 6;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg6)).setChecked(true);
                return;
            case 7:
                BenzNtg6FyConfigs.bg_sel = 7;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg7)).setChecked(true);
                return;
            case 8:
                BenzNtg6FyConfigs.bg_sel = 8;
                MainActivity.mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[BenzNtg6FyConfigs.bg_sel - 1]);
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_fy_bg8)).setChecked(true);
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void exit() {
        finish();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        BenzNtg6FyConfigs.saveStyleData(this);
    }
}

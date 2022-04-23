package com.wits.ksw.launcher.view.benzmbux2021;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;

public class Benz2021DialogThemeActivity extends Activity {
    private String bgIndex = "1";
    private View.OnClickListener mThemeItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            Object obj7;
            LOGE.E("v.getId() = " + v.getId());
            switch (v.getId()) {
                case R.id.rb_benz_mbux_2021_coat_bg_1 /*2131297163*/:
                    BenzMbux2021Configs.bg_sel = 1;
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 1);
                    BcVieModel bcVieModel = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel2 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel3 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel4 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel5 = MainActivity.mBcVieModel;
                        obj = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo.setPicBg((BitmapDrawable) obj);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel6 = MainActivity.mBcVieModel;
                        imageView.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView2 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel7 = MainActivity.mBcVieModel;
                        imageView2.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView3 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel8 = MainActivity.mBcVieModel;
                        imageView3.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_2 /*2131297164*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 2);
                    BenzMbux2021Configs.bg_sel = 2;
                    BcVieModel bcVieModel9 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel10 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel11 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo2 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel12 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj2 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel13 = MainActivity.mBcVieModel;
                        obj2 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo2.setPicBg((BitmapDrawable) obj2);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView4 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel14 = MainActivity.mBcVieModel;
                        imageView4.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView5 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel15 = MainActivity.mBcVieModel;
                        imageView5.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView6 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel16 = MainActivity.mBcVieModel;
                        imageView6.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_3 /*2131297165*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 3);
                    BenzMbux2021Configs.bg_sel = 3;
                    BcVieModel bcVieModel17 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel18 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel19 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo3 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel20 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj3 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel21 = MainActivity.mBcVieModel;
                        obj3 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo3.setPicBg((BitmapDrawable) obj3);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView7 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel22 = MainActivity.mBcVieModel;
                        imageView7.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView8 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel23 = MainActivity.mBcVieModel;
                        imageView8.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView9 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel24 = MainActivity.mBcVieModel;
                        imageView9.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_4 /*2131297166*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 4);
                    BenzMbux2021Configs.bg_sel = 4;
                    BcVieModel bcVieModel25 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel26 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel27 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo4 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel28 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj4 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel29 = MainActivity.mBcVieModel;
                        obj4 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo4.setPicBg((BitmapDrawable) obj4);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView10 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel30 = MainActivity.mBcVieModel;
                        imageView10.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView11 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel31 = MainActivity.mBcVieModel;
                        imageView11.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView12 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel32 = MainActivity.mBcVieModel;
                        imageView12.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_5 /*2131297167*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 5);
                    BenzMbux2021Configs.bg_sel = 5;
                    BcVieModel bcVieModel33 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel34 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel35 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo5 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel36 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj5 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel37 = MainActivity.mBcVieModel;
                        obj5 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo5.setPicBg((BitmapDrawable) obj5);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView13 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel38 = MainActivity.mBcVieModel;
                        imageView13.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView14 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel39 = MainActivity.mBcVieModel;
                        imageView14.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView15 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel40 = MainActivity.mBcVieModel;
                        imageView15.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_6 /*2131297168*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 6);
                    BenzMbux2021Configs.bg_sel = 6;
                    BcVieModel bcVieModel41 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel42 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel43 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo6 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel44 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj6 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel45 = MainActivity.mBcVieModel;
                        obj6 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo6.setPicBg((BitmapDrawable) obj6);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView16 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel46 = MainActivity.mBcVieModel;
                        imageView16.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView17 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel47 = MainActivity.mBcVieModel;
                        imageView17.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView18 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel48 = MainActivity.mBcVieModel;
                        imageView18.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_benz_mbux_2021_coat_bg_7 /*2131297169*/:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 7);
                    BenzMbux2021Configs.bg_sel = 7;
                    BcVieModel bcVieModel49 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel50 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                    BcVieModel bcVieModel51 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo7 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel52 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        obj7 = Benz2021DialogThemeActivity.this.getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel53 = MainActivity.mBcVieModel;
                        obj7 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo7.setPicBg((BitmapDrawable) obj7);
                    if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView19 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel54 = MainActivity.mBcVieModel;
                        imageView19.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView20 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel55 = MainActivity.mBcVieModel;
                        imageView20.setBackground(BcVieModel.mediaInfo.picBg.get());
                        return;
                    } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                        ImageView imageView21 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                        BcVieModel bcVieModel56 = MainActivity.mBcVieModel;
                        imageView21.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case R.id.rb_icon_sel_classical /*2131297170*/:
                    BenzMbux2021Configs.style_sel = 1;
                    Benz2021DialogThemeActivity.this.tv_icon_sel_modern.setTextColor(Benz2021DialogThemeActivity.this.getColor(R.color.color1));
                    Benz2021DialogThemeActivity.this.tv_icon_sel_classical.setTextColor(Benz2021DialogThemeActivity.this.getColor(R.color.rb_text_color));
                    Benz2021DialogThemeActivity.this.rb_icon_sel_modern.setSelected(false);
                    Benz2021DialogThemeActivity.this.rb_icon_sel_classical.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(true);
                    return;
                case R.id.rb_icon_sel_modern /*2131297171*/:
                    BenzMbux2021Configs.style_sel = 2;
                    Benz2021DialogThemeActivity.this.tv_icon_sel_modern.setTextColor(Benz2021DialogThemeActivity.this.getColor(R.color.rb_text_color));
                    Benz2021DialogThemeActivity.this.tv_icon_sel_classical.setTextColor(Benz2021DialogThemeActivity.this.getColor(R.color.color1));
                    Benz2021DialogThemeActivity.this.rb_icon_sel_classical.setSelected(false);
                    Benz2021DialogThemeActivity.this.rb_icon_sel_modern.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(false);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public RadioButton rb_icon_sel_classical;
    /* access modifiers changed from: private */
    public RadioButton rb_icon_sel_modern;
    private String styleIndex = "1";
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_classical;
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_modern;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        super.onCreate(savedInstanceState);
        LOGE.D("Benz2021DialogThemeActivity00000000000");
        setContentView(R.layout.dialog_benz_mbux_2021_theme_layout);
        this.tv_icon_sel_classical = (TextView) findViewById(R.id.tv_icon_sel_classical);
        this.tv_icon_sel_modern = (TextView) findViewById(R.id.tv_icon_sel_modern);
        this.rb_icon_sel_classical = (RadioButton) findViewById(R.id.rb_icon_sel_classical);
        RadioButton radioButton = (RadioButton) findViewById(R.id.rb_icon_sel_modern);
        this.rb_icon_sel_modern = radioButton;
        radioButton.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setSelected(!this.rb_icon_sel_modern.isSelected());
        this.tv_icon_sel_classical.setTextColor(this.rb_icon_sel_classical.isSelected() ? getColor(R.color.rb_text_color) : getColor(R.color.color1));
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_1).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_2).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_3).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_4).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_5).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_6).setOnClickListener(this.mThemeItemClickListener);
        findViewById(R.id.rb_benz_mbux_2021_coat_bg_7).setOnClickListener(this.mThemeItemClickListener);
        this.styleIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.STYLE_INDEX);
        this.bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_INDEX);
        if (TextUtils.isEmpty(this.styleIndex)) {
            this.styleIndex = "1";
        }
        if (TextUtils.isEmpty(this.bgIndex)) {
            this.bgIndex = "1";
        }
        int iStyleIndex = Integer.parseInt(this.styleIndex);
        int iBgIndex = Integer.parseInt(this.bgIndex);
        LOGE.D("Benz2021DialogThemeActivity styleIndex = " + this.styleIndex + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            BenzMbux2021Configs.style_sel = 1;
            this.tv_icon_sel_modern.setTextColor(getColor(R.color.color1));
            this.tv_icon_sel_classical.setTextColor(getColor(R.color.rb_text_color));
            this.rb_icon_sel_modern.setSelected(false);
            this.rb_icon_sel_classical.setSelected(true);
            MainActivity.mBcVieModel.itemIconClassical.set(true);
        } else {
            BenzMbux2021Configs.style_sel = 2;
            this.tv_icon_sel_modern.setTextColor(getColor(R.color.rb_text_color));
            this.tv_icon_sel_classical.setTextColor(getColor(R.color.color1));
            this.rb_icon_sel_classical.setSelected(false);
            this.rb_icon_sel_modern.setSelected(true);
            MainActivity.mBcVieModel.itemIconClassical.set(false);
        }
        switch (iBgIndex) {
            case 1:
                BenzMbux2021Configs.bg_sel = 1;
                BcVieModel bcVieModel = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel2 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel3 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo = BcVieModel.mediaInfo;
                BcVieModel bcVieModel4 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel5 = MainActivity.mBcVieModel;
                    obj = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo.setPicBg((BitmapDrawable) obj);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel6 = MainActivity.mBcVieModel;
                    imageView.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------1 ");
                    ImageView imageView2 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel7 = MainActivity.mBcVieModel;
                    imageView2.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView3 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel8 = MainActivity.mBcVieModel;
                    imageView3.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_1)).setChecked(true);
                return;
            case 2:
                BenzMbux2021Configs.bg_sel = 2;
                BcVieModel bcVieModel9 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel10 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel11 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo2 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel12 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj2 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel13 = MainActivity.mBcVieModel;
                    obj2 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo2.setPicBg((BitmapDrawable) obj2);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView4 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel14 = MainActivity.mBcVieModel;
                    imageView4.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------2 ");
                    ImageView imageView5 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel15 = MainActivity.mBcVieModel;
                    imageView5.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView6 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel16 = MainActivity.mBcVieModel;
                    imageView6.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_2)).setChecked(true);
                return;
            case 3:
                BenzMbux2021Configs.bg_sel = 3;
                BcVieModel bcVieModel17 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel18 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel19 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo3 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel20 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj3 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel21 = MainActivity.mBcVieModel;
                    obj3 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo3.setPicBg((BitmapDrawable) obj3);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView7 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel22 = MainActivity.mBcVieModel;
                    imageView7.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------3 ");
                    ImageView imageView8 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel23 = MainActivity.mBcVieModel;
                    imageView8.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView9 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel24 = MainActivity.mBcVieModel;
                    imageView9.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_3)).setChecked(true);
                return;
            case 4:
                BenzMbux2021Configs.bg_sel = 4;
                BcVieModel bcVieModel25 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel26 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel27 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo4 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel28 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj4 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel29 = MainActivity.mBcVieModel;
                    obj4 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo4.setPicBg((BitmapDrawable) obj4);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView10 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel30 = MainActivity.mBcVieModel;
                    imageView10.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------4 ");
                    ImageView imageView11 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel31 = MainActivity.mBcVieModel;
                    imageView11.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView12 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel32 = MainActivity.mBcVieModel;
                    imageView12.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_4)).setChecked(true);
                return;
            case 5:
                BenzMbux2021Configs.bg_sel = 5;
                BcVieModel bcVieModel33 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel34 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel35 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo5 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel36 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj5 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel37 = MainActivity.mBcVieModel;
                    obj5 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo5.setPicBg((BitmapDrawable) obj5);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView13 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel38 = MainActivity.mBcVieModel;
                    imageView13.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------5 ");
                    ImageView imageView14 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel39 = MainActivity.mBcVieModel;
                    imageView14.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView15 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel40 = MainActivity.mBcVieModel;
                    imageView15.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_5)).setChecked(true);
                return;
            case 6:
                BenzMbux2021Configs.bg_sel = 6;
                BcVieModel bcVieModel41 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel42 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel43 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo6 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel44 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj6 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel45 = MainActivity.mBcVieModel;
                    obj6 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo6.setPicBg((BitmapDrawable) obj6);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView16 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel46 = MainActivity.mBcVieModel;
                    imageView16.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------6 ");
                    ImageView imageView17 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel47 = MainActivity.mBcVieModel;
                    imageView17.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView18 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel48 = MainActivity.mBcVieModel;
                    imageView18.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_6)).setChecked(true);
                return;
            case 7:
                BenzMbux2021Configs.bg_sel = 7;
                BcVieModel bcVieModel49 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel50 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]));
                BcVieModel bcVieModel51 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo7 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel52 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj7 = getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel53 = MainActivity.mBcVieModel;
                    obj7 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo7.setPicBg((BitmapDrawable) obj7);
                if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView19 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel54 = MainActivity.mBcVieModel;
                    imageView19.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    LOGE.D("liuhaoMedia benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem()==1---------7 ");
                    ImageView imageView20 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel55 = MainActivity.mBcVieModel;
                    imageView20.setBackground(BcVieModel.mediaInfo.picBg.get());
                } else if (MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 2) {
                    ImageView imageView21 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel56 = MainActivity.mBcVieModel;
                    imageView21.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(R.id.rb_benz_mbux_2021_coat_bg_7)).setChecked(true);
                return;
            default:
                return;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void exit() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        BenzMbux2021Configs.saveStyleData(this);
    }
}

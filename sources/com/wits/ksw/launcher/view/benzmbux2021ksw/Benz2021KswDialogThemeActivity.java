package com.wits.ksw.launcher.view.benzmbux2021ksw;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswConfigs;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.ksw.settings.TxzMessage;

/* loaded from: classes9.dex */
public class Benz2021KswDialogThemeActivity extends Activity {
    int height;
    private RadioButton rb_icon_sel_classical;
    private RadioButton rb_icon_sel_modern;
    private TextView tv_icon_sel_classical;
    private TextView tv_icon_sel_modern;
    int width;
    private String styleIndex = TxzMessage.TXZ_SHOW;
    private String bgIndex = TxzMessage.TXZ_SHOW;
    private View.OnClickListener mThemeItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021ksw.Benz2021KswDialogThemeActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Drawable drawable;
            Drawable drawable2;
            Drawable drawable3;
            Drawable drawable4;
            Drawable drawable5;
            Drawable drawable6;
            Drawable drawable7;
            LOGE.m43E("v.getId() = " + v.getId());
            switch (v.getId()) {
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_1 /* 2131297462 */:
                    BenzMbux2021KswConfigs.bg_sel = 1;
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 1);
                    BcVieModel bcVieModel = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel2 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel3 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel4 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel5 = MainActivity.mBcVieModel;
                        drawable = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo.setPicBg((BitmapDrawable) drawable);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel6 = MainActivity.mBcVieModel;
                            imageView.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView2 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel7 = MainActivity.mBcVieModel;
                            imageView2.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView3 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel8 = MainActivity.mBcVieModel;
                        imageView3.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView4 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel9 = MainActivity.mBcVieModel;
                        imageView4.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_2 /* 2131297463 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 2);
                    BenzMbux2021KswConfigs.bg_sel = 2;
                    BcVieModel bcVieModel10 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel11 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel12 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo2 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel13 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable2 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel14 = MainActivity.mBcVieModel;
                        drawable2 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo2.setPicBg((BitmapDrawable) drawable2);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView5 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel15 = MainActivity.mBcVieModel;
                            imageView5.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView6 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel16 = MainActivity.mBcVieModel;
                            imageView6.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView7 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel17 = MainActivity.mBcVieModel;
                        imageView7.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView8 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel18 = MainActivity.mBcVieModel;
                        imageView8.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_3 /* 2131297464 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 3);
                    BenzMbux2021KswConfigs.bg_sel = 3;
                    BcVieModel bcVieModel19 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel20 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel21 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo3 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel22 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable3 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel23 = MainActivity.mBcVieModel;
                        drawable3 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo3.setPicBg((BitmapDrawable) drawable3);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView9 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel24 = MainActivity.mBcVieModel;
                            imageView9.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView10 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel25 = MainActivity.mBcVieModel;
                            imageView10.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView11 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel26 = MainActivity.mBcVieModel;
                        imageView11.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView12 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel27 = MainActivity.mBcVieModel;
                        imageView12.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_4 /* 2131297465 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 4);
                    BenzMbux2021KswConfigs.bg_sel = 4;
                    BcVieModel bcVieModel28 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel29 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel30 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo4 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel31 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable4 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel32 = MainActivity.mBcVieModel;
                        drawable4 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo4.setPicBg((BitmapDrawable) drawable4);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView13 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel33 = MainActivity.mBcVieModel;
                            imageView13.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView14 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel34 = MainActivity.mBcVieModel;
                            imageView14.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView15 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel35 = MainActivity.mBcVieModel;
                        imageView15.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView16 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel36 = MainActivity.mBcVieModel;
                        imageView16.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_5 /* 2131297466 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 5);
                    BenzMbux2021KswConfigs.bg_sel = 5;
                    BcVieModel bcVieModel37 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel38 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel39 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo5 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel40 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable5 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel41 = MainActivity.mBcVieModel;
                        drawable5 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo5.setPicBg((BitmapDrawable) drawable5);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView17 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel42 = MainActivity.mBcVieModel;
                            imageView17.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView18 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel43 = MainActivity.mBcVieModel;
                            imageView18.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView19 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel44 = MainActivity.mBcVieModel;
                        imageView19.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView20 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel45 = MainActivity.mBcVieModel;
                        imageView20.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_6 /* 2131297467 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 6);
                    BenzMbux2021KswConfigs.bg_sel = 6;
                    BcVieModel bcVieModel46 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel47 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel48 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo6 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel49 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable6 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel50 = MainActivity.mBcVieModel;
                        drawable6 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo6.setPicBg((BitmapDrawable) drawable6);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView21 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel51 = MainActivity.mBcVieModel;
                            imageView21.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView22 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel52 = MainActivity.mBcVieModel;
                            imageView22.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView23 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel53 = MainActivity.mBcVieModel;
                        imageView23.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView24 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel54 = MainActivity.mBcVieModel;
                        imageView24.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_benz_mbux_2021_coat_bg_7 /* 2131297468 */:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 7);
                    BenzMbux2021KswConfigs.bg_sel = 7;
                    BcVieModel bcVieModel55 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOne((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel56 = MainActivity.mBcVieModel;
                    BcVieModel.mediaInfo.setPicOther((BitmapDrawable) Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                    BcVieModel bcVieModel57 = MainActivity.mBcVieModel;
                    MediaInfo mediaInfo7 = BcVieModel.mediaInfo;
                    BcVieModel bcVieModel58 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() == null) {
                        drawable7 = Benz2021KswDialogThemeActivity.this.getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                    } else {
                        BcVieModel bcVieModel59 = MainActivity.mBcVieModel;
                        drawable7 = BcVieModel.mediaInfo.picBg.get();
                    }
                    mediaInfo7.setPicBg((BitmapDrawable) drawable7);
                    if ((Benz2021KswDialogThemeActivity.this.width == 1280 && Benz2021KswDialogThemeActivity.this.height == 720) || (Benz2021KswDialogThemeActivity.this.width == 1024 && Benz2021KswDialogThemeActivity.this.height == 600)) {
                        if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                            ImageView imageView25 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel60 = MainActivity.mBcVieModel;
                            imageView25.setBackground(BcVieModel.mediaInfo.picOne.get());
                            return;
                        } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                            ImageView imageView26 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                            BcVieModel bcVieModel61 = MainActivity.mBcVieModel;
                            imageView26.setBackground(BcVieModel.mediaInfo.picOther.get());
                            return;
                        } else {
                            return;
                        }
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView27 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel62 = MainActivity.mBcVieModel;
                        imageView27.setBackground(BcVieModel.mediaInfo.picOne.get());
                        return;
                    } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView28 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                        BcVieModel bcVieModel63 = MainActivity.mBcVieModel;
                        imageView28.setBackground(BcVieModel.mediaInfo.picOther.get());
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.rb_icon_sel_classical /* 2131297469 */:
                    BenzMbux2021KswConfigs.style_sel = 1;
                    Benz2021KswDialogThemeActivity.this.tv_icon_sel_modern.setTextColor(Benz2021KswDialogThemeActivity.this.getColor(C0899R.color.color1));
                    Benz2021KswDialogThemeActivity.this.tv_icon_sel_classical.setTextColor(Benz2021KswDialogThemeActivity.this.getColor(C0899R.color.rb_text_color));
                    Benz2021KswDialogThemeActivity.this.rb_icon_sel_modern.setSelected(false);
                    Benz2021KswDialogThemeActivity.this.rb_icon_sel_classical.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(true);
                    return;
                case C0899R.C0901id.rb_icon_sel_modern /* 2131297470 */:
                    BenzMbux2021KswConfigs.style_sel = 2;
                    Benz2021KswDialogThemeActivity.this.tv_icon_sel_modern.setTextColor(Benz2021KswDialogThemeActivity.this.getColor(C0899R.color.rb_text_color));
                    Benz2021KswDialogThemeActivity.this.tv_icon_sel_classical.setTextColor(Benz2021KswDialogThemeActivity.this.getColor(C0899R.color.color1));
                    Benz2021KswDialogThemeActivity.this.rb_icon_sel_classical.setSelected(false);
                    Benz2021KswDialogThemeActivity.this.rb_icon_sel_modern.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(false);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        super.onCreate(savedInstanceState);
        setContentView(C0899R.C0902layout.activity_benz2021_ksw_dialog_theme);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.width = dm.widthPixels;
        this.height = dm.heightPixels;
        this.tv_icon_sel_classical = (TextView) findViewById(C0899R.C0901id.tv_icon_sel_classical);
        this.tv_icon_sel_modern = (TextView) findViewById(C0899R.C0901id.tv_icon_sel_modern);
        this.rb_icon_sel_classical = (RadioButton) findViewById(C0899R.C0901id.rb_icon_sel_classical);
        RadioButton radioButton = (RadioButton) findViewById(C0899R.C0901id.rb_icon_sel_modern);
        this.rb_icon_sel_modern = radioButton;
        radioButton.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setSelected(!this.rb_icon_sel_modern.isSelected());
        this.tv_icon_sel_classical.setTextColor(this.rb_icon_sel_classical.isSelected() ? getColor(C0899R.color.rb_text_color) : getColor(C0899R.color.color1));
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_1).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_2).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_3).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_4).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_5).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_6).setOnClickListener(this.mThemeItemClickListener);
        findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_7).setOnClickListener(this.mThemeItemClickListener);
        this.styleIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "STYLE_INDEX");
        this.bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "BG_INDEX");
        if (TextUtils.isEmpty(this.styleIndex)) {
            this.styleIndex = TxzMessage.TXZ_SHOW;
        }
        if (TextUtils.isEmpty(this.bgIndex)) {
            this.bgIndex = TxzMessage.TXZ_SHOW;
        }
        int iStyleIndex = Integer.parseInt(this.styleIndex);
        int iBgIndex = Integer.parseInt(this.bgIndex);
        LOGE.m44D("Benz2021DialogThemeActivity styleIndex = " + this.styleIndex + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            BenzMbux2021KswConfigs.style_sel = 1;
            this.tv_icon_sel_modern.setTextColor(getColor(C0899R.color.color1));
            this.tv_icon_sel_classical.setTextColor(getColor(C0899R.color.rb_text_color));
            this.rb_icon_sel_modern.setSelected(false);
            this.rb_icon_sel_classical.setSelected(true);
            MainActivity.mBcVieModel.itemIconClassical.set(true);
        } else {
            BenzMbux2021KswConfigs.style_sel = 2;
            this.tv_icon_sel_modern.setTextColor(getColor(C0899R.color.rb_text_color));
            this.tv_icon_sel_classical.setTextColor(getColor(C0899R.color.color1));
            this.rb_icon_sel_classical.setSelected(false);
            this.rb_icon_sel_modern.setSelected(true);
            MainActivity.mBcVieModel.itemIconClassical.set(false);
        }
        switch (iBgIndex) {
            case 1:
                BenzMbux2021KswConfigs.bg_sel = 1;
                BcVieModel bcVieModel = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel2 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel3 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo = BcVieModel.mediaInfo;
                BcVieModel bcVieModel4 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel5 = MainActivity.mBcVieModel;
                    obj = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo.setPicBg((BitmapDrawable) obj);
                int i = this.width;
                if ((i == 1280 && this.height == 720) || (i == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel6 = MainActivity.mBcVieModel;
                        imageView.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView2 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel7 = MainActivity.mBcVieModel;
                        imageView2.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView3 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel8 = MainActivity.mBcVieModel;
                    imageView3.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView4 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel9 = MainActivity.mBcVieModel;
                    imageView4.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_1)).setChecked(true);
                return;
            case 2:
                BenzMbux2021KswConfigs.bg_sel = 2;
                BcVieModel bcVieModel10 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel11 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel12 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo2 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel13 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj2 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel14 = MainActivity.mBcVieModel;
                    obj2 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo2.setPicBg((BitmapDrawable) obj2);
                int i2 = this.width;
                if ((i2 == 1280 && this.height == 720) || (i2 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView5 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel15 = MainActivity.mBcVieModel;
                        imageView5.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView6 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel16 = MainActivity.mBcVieModel;
                        imageView6.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView7 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel17 = MainActivity.mBcVieModel;
                    imageView7.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView8 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel18 = MainActivity.mBcVieModel;
                    imageView8.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_2)).setChecked(true);
                return;
            case 3:
                BenzMbux2021KswConfigs.bg_sel = 3;
                BcVieModel bcVieModel19 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel20 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel21 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo3 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel22 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj3 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel23 = MainActivity.mBcVieModel;
                    obj3 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo3.setPicBg((BitmapDrawable) obj3);
                int i3 = this.width;
                if ((i3 == 1280 && this.height == 720) || (i3 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView9 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel24 = MainActivity.mBcVieModel;
                        imageView9.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView10 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel25 = MainActivity.mBcVieModel;
                        imageView10.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView11 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel26 = MainActivity.mBcVieModel;
                    imageView11.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView12 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel27 = MainActivity.mBcVieModel;
                    imageView12.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_3)).setChecked(true);
                return;
            case 4:
                BenzMbux2021KswConfigs.bg_sel = 4;
                BcVieModel bcVieModel28 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel29 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel30 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo4 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel31 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj4 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel32 = MainActivity.mBcVieModel;
                    obj4 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo4.setPicBg((BitmapDrawable) obj4);
                int i4 = this.width;
                if ((i4 == 1280 && this.height == 720) || (i4 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView13 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel33 = MainActivity.mBcVieModel;
                        imageView13.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView14 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel34 = MainActivity.mBcVieModel;
                        imageView14.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView15 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel35 = MainActivity.mBcVieModel;
                    imageView15.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView16 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel36 = MainActivity.mBcVieModel;
                    imageView16.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_4)).setChecked(true);
                return;
            case 5:
                BenzMbux2021KswConfigs.bg_sel = 5;
                BcVieModel bcVieModel37 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel38 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel39 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo5 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel40 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj5 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel41 = MainActivity.mBcVieModel;
                    obj5 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo5.setPicBg((BitmapDrawable) obj5);
                int i5 = this.width;
                if ((i5 == 1280 && this.height == 720) || (i5 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView17 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel42 = MainActivity.mBcVieModel;
                        imageView17.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView18 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel43 = MainActivity.mBcVieModel;
                        imageView18.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView19 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel44 = MainActivity.mBcVieModel;
                    imageView19.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView20 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel45 = MainActivity.mBcVieModel;
                    imageView20.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_5)).setChecked(true);
                return;
            case 6:
                BenzMbux2021KswConfigs.bg_sel = 6;
                BcVieModel bcVieModel46 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel47 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel48 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo6 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel49 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj6 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel50 = MainActivity.mBcVieModel;
                    obj6 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo6.setPicBg((BitmapDrawable) obj6);
                int i6 = this.width;
                if ((i6 == 1280 && this.height == 720) || (i6 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView21 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel51 = MainActivity.mBcVieModel;
                        imageView21.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView22 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel52 = MainActivity.mBcVieModel;
                        imageView22.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView23 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel53 = MainActivity.mBcVieModel;
                    imageView23.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView24 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel54 = MainActivity.mBcVieModel;
                    imageView24.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_6)).setChecked(true);
                return;
            case 7:
                BenzMbux2021KswConfigs.bg_sel = 7;
                BcVieModel bcVieModel55 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_ONE[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel56 = MainActivity.mBcVieModel;
                BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]));
                BcVieModel bcVieModel57 = MainActivity.mBcVieModel;
                MediaInfo mediaInfo7 = BcVieModel.mediaInfo;
                BcVieModel bcVieModel58 = MainActivity.mBcVieModel;
                if (BcVieModel.mediaInfo.picBg.get() == null) {
                    obj7 = getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[BenzMbux2021KswConfigs.bg_sel - 1]);
                } else {
                    BcVieModel bcVieModel59 = MainActivity.mBcVieModel;
                    obj7 = BcVieModel.mediaInfo.picBg.get();
                }
                mediaInfo7.setPicBg((BitmapDrawable) obj7);
                int i7 = this.width;
                if ((i7 == 1280 && this.height == 720) || (i7 == 1024 && this.height == 600)) {
                    if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                        ImageView imageView25 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel60 = MainActivity.mBcVieModel;
                        imageView25.setBackground(BcVieModel.mediaInfo.picOne.get());
                    } else if (MainActivity.benzMbux2021KswActivityBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                        ImageView imageView26 = MainActivity.benzMbux2021KswActivityBinding.layoutMain2021;
                        BcVieModel bcVieModel61 = MainActivity.mBcVieModel;
                        imageView26.setBackground(BcVieModel.mediaInfo.picOther.get());
                    }
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 0) {
                    ImageView imageView27 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel62 = MainActivity.mBcVieModel;
                    imageView27.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (MainActivity.mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.getCurrentItem() == 1) {
                    ImageView imageView28 = MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021;
                    BcVieModel bcVieModel63 = MainActivity.mBcVieModel;
                    imageView28.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
                ((RadioButton) findViewById(C0899R.C0901id.rb_benz_mbux_2021_coat_bg_7)).setChecked(true);
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
        BenzMbux2021KswConfigs.saveStyleData(this);
    }
}

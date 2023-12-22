package com.wits.ksw.launcher.model;

import android.databinding.ObservableInt;
import android.provider.Settings;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityBmwNbtBinding;

/* loaded from: classes9.dex */
public class BwmNbtModel extends LauncherViewModel implements View.OnFocusChangeListener {
    private ActivityBmwNbtBinding mainnbtBinging;
    private int[] leftBG = {C0899R.C0900drawable.nbt_left_bg_app, C0899R.C0900drawable.nbt_left_bg_navi, C0899R.C0900drawable.nbt_left_bg_music, C0899R.C0900drawable.nbt_left_bg_video, C0899R.C0900drawable.nbt_left_bg_bt, C0899R.C0900drawable.nbt_left_bg_carinfo, C0899R.C0900drawable.nbt_left_bg_dashboard, C0899R.C0900drawable.nbt_left_bg_settings};
    private int[] itemLogo = {C0899R.C0900drawable.nbt_src_icon_app, C0899R.C0900drawable.nbt_src_icon_navi, C0899R.C0900drawable.nbt_src_icon_music, C0899R.C0900drawable.nbt_src_icon_video, C0899R.C0900drawable.nbt_src_icon_bt, C0899R.C0900drawable.nbt_src_icon_carinfo, C0899R.C0900drawable.nbt_src_icon_dashboard, C0899R.C0900drawable.nbt_src_icon_settings};
    private int[] circleBg = {C0899R.C0900drawable.nbt_circle_n_00, C0899R.C0900drawable.nbt_circle_n_01, C0899R.C0900drawable.nbt_circle_n_02, C0899R.C0900drawable.nbt_circle_n_03, C0899R.C0900drawable.nbt_circle_n_04, C0899R.C0900drawable.nbt_circle_n_05, C0899R.C0900drawable.nbt_circle_n_06, C0899R.C0900drawable.nbt_circle_n_07};
    private int[] indicatorBg = {C0899R.C0900drawable.nbt_focus_indicator_line_0, C0899R.C0900drawable.nbt_focus_indicator_line_1, C0899R.C0900drawable.nbt_focus_indicator_line_2, C0899R.C0900drawable.nbt_focus_indicator_line_3, C0899R.C0900drawable.nbt_focus_indicator_line_4, C0899R.C0900drawable.nbt_focus_indicator_line_5, C0899R.C0900drawable.nbt_focus_indicator_line_6, C0899R.C0900drawable.nbt_focus_indicator_line_7};
    public ObservableInt leftBGId = new ObservableInt();
    public ObservableInt itemLogoId = new ObservableInt();
    public ObservableInt circleBgId = new ObservableInt();
    public ObservableInt indicatorBgId = new ObservableInt();

    public void setMainnbtBinging(ActivityBmwNbtBinding mainnbtBinging) {
        this.mainnbtBinging = mainnbtBinging;
        mainnbtBinging.appLl.setOnFocusChangeListener(this);
        mainnbtBinging.naviLl.setOnFocusChangeListener(this);
        mainnbtBinging.musicLl.setOnFocusChangeListener(this);
        mainnbtBinging.videoLl.setOnFocusChangeListener(this);
        mainnbtBinging.phoneLl.setOnFocusChangeListener(this);
        mainnbtBinging.rlCar.setOnFocusChangeListener(this);
        mainnbtBinging.dashbroadLl.setOnFocusChangeListener(this);
        mainnbtBinging.rlSettings.setOnFocusChangeListener(this);
    }

    private void refrshBg(int index) {
        this.leftBGId.set(this.leftBG[index]);
        this.itemLogoId.set(this.itemLogo[index]);
        this.indicatorBgId.set(this.indicatorBg[index]);
        this.circleBgId.set(this.circleBg[index]);
        btnSelect(index);
    }

    public void refrshInit() {
        refrshBg(readIndex());
    }

    private void saveIndex(int index) {
        Settings.System.putInt(this.context.getContentResolver(), "BMW_NBT_Index", index);
    }

    private int readIndex() {
        return Settings.System.getInt(this.context.getContentResolver(), "BMW_NBT_Index", 0);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        focusChange(v, hasFocus);
    }

    private void btnSelect(int index) {
        ActivityBmwNbtBinding activityBmwNbtBinding = this.mainnbtBinging;
        if (activityBmwNbtBinding != null) {
            activityBmwNbtBinding.appLl.setSelected(index == 0);
            this.mainnbtBinging.naviLl.setSelected(index == 1);
            this.mainnbtBinging.musicLl.setSelected(index == 2);
            this.mainnbtBinging.videoLl.setSelected(index == 3);
            this.mainnbtBinging.phoneLl.setSelected(index == 4);
            this.mainnbtBinging.rlCar.setSelected(index == 5);
            this.mainnbtBinging.dashbroadLl.setSelected(index == 6);
            this.mainnbtBinging.rlSettings.setSelected(index == 7);
        }
    }

    private void focusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case C0899R.C0901id.app_ll /* 2131296349 */:
                if (hasFocus) {
                    refrshBg(0);
                    return;
                }
                return;
            case C0899R.C0901id.dashbroad_ll /* 2131296812 */:
                if (hasFocus) {
                    refrshBg(6);
                    return;
                }
                return;
            case C0899R.C0901id.music_ll /* 2131297370 */:
                if (hasFocus) {
                    refrshBg(2);
                    return;
                }
                return;
            case C0899R.C0901id.navi_ll /* 2131297394 */:
                if (hasFocus) {
                    refrshBg(1);
                    return;
                }
                return;
            case C0899R.C0901id.phone_ll /* 2131297434 */:
                if (hasFocus) {
                    refrshBg(4);
                    return;
                }
                return;
            case C0899R.C0901id.rl_car /* 2131297654 */:
                refrshBg(5);
                return;
            case C0899R.C0901id.rl_settings /* 2131297662 */:
                if (hasFocus) {
                    refrshBg(7);
                    return;
                }
                return;
            case C0899R.C0901id.video_ll /* 2131298055 */:
                if (hasFocus) {
                    refrshBg(3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onNbtClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.app_ll /* 2131296349 */:
                refrshBg(0);
                openApps(v);
                return;
            case C0899R.C0901id.dashbroad_ll /* 2131296812 */:
                refrshBg(6);
                openDashboard(v);
                return;
            case C0899R.C0901id.music_ll /* 2131297370 */:
                refrshBg(2);
                openMusicMulti(v);
                return;
            case C0899R.C0901id.navi_ll /* 2131297394 */:
                refrshBg(1);
                openNaviApp(v);
                return;
            case C0899R.C0901id.phone_ll /* 2131297434 */:
                refrshBg(4);
                openBtApp(v);
                return;
            case C0899R.C0901id.rl_car /* 2131297654 */:
                refrshBg(5);
                openCar(v);
                return;
            case C0899R.C0901id.rl_settings /* 2131297662 */:
                refrshBg(7);
                openSettings(v);
                return;
            case C0899R.C0901id.video_ll /* 2131298055 */:
                refrshBg(3);
                openVideoMulti(v);
                return;
            default:
                return;
        }
    }
}

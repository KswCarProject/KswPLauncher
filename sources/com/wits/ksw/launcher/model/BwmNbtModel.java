package com.wits.ksw.launcher.model;

import android.databinding.ObservableInt;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityBmwNbtBinding;

public class BwmNbtModel extends LauncherViewModel implements View.OnFocusChangeListener {
    private int[] circleBg = {R.drawable.nbt_circle_n_00, R.drawable.nbt_circle_n_01, R.drawable.nbt_circle_n_02, R.drawable.nbt_circle_n_03, R.drawable.nbt_circle_n_04, R.drawable.nbt_circle_n_05, R.drawable.nbt_circle_n_06, R.drawable.nbt_circle_n_07};
    public ObservableInt circleBgId = new ObservableInt();
    private int[] indicatorBg = {R.drawable.nbt_focus_indicator_line_0, R.drawable.nbt_focus_indicator_line_1, R.drawable.nbt_focus_indicator_line_2, R.drawable.nbt_focus_indicator_line_3, R.drawable.nbt_focus_indicator_line_4, R.drawable.nbt_focus_indicator_line_5, R.drawable.nbt_focus_indicator_line_6, R.drawable.nbt_focus_indicator_line_7};
    public ObservableInt indicatorBgId = new ObservableInt();
    private int[] itemLogo = {R.drawable.nbt_src_icon_app, R.drawable.nbt_src_icon_navi, R.drawable.nbt_src_icon_music, R.drawable.nbt_src_icon_video, R.drawable.nbt_src_icon_bt, R.drawable.nbt_src_icon_carinfo, R.drawable.nbt_src_icon_dashboard, R.drawable.nbt_src_icon_settings};
    public ObservableInt itemLogoId = new ObservableInt();
    private int[] leftBG = {R.drawable.nbt_left_bg_app, R.drawable.nbt_left_bg_navi, R.drawable.nbt_left_bg_music, R.drawable.nbt_left_bg_video, R.drawable.nbt_left_bg_bt, R.drawable.nbt_left_bg_carinfo, R.drawable.nbt_left_bg_dashboard, R.drawable.nbt_left_bg_settings};
    public ObservableInt leftBGId = new ObservableInt();
    private ActivityBmwNbtBinding mainnbtBinging;

    public void setMainnbtBinging(ActivityBmwNbtBinding mainnbtBinging2) {
        this.mainnbtBinging = mainnbtBinging2;
        mainnbtBinging2.appLl.setOnFocusChangeListener(this);
        mainnbtBinging2.naviLl.setOnFocusChangeListener(this);
        mainnbtBinging2.musicLl.setOnFocusChangeListener(this);
        mainnbtBinging2.videoLl.setOnFocusChangeListener(this);
        mainnbtBinging2.phoneLl.setOnFocusChangeListener(this);
        mainnbtBinging2.rlCar.setOnFocusChangeListener(this);
        mainnbtBinging2.dashbroadLl.setOnFocusChangeListener(this);
        mainnbtBinging2.rlSettings.setOnFocusChangeListener(this);
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

    public void onFocusChange(View v, boolean hasFocus) {
        focusChange(v, hasFocus);
    }

    private void btnSelect(int index) {
        if (this.mainnbtBinging != null) {
            boolean z = false;
            this.mainnbtBinging.appLl.setSelected(index == 0);
            this.mainnbtBinging.naviLl.setSelected(index == 1);
            this.mainnbtBinging.musicLl.setSelected(index == 2);
            this.mainnbtBinging.videoLl.setSelected(index == 3);
            this.mainnbtBinging.phoneLl.setSelected(index == 4);
            this.mainnbtBinging.rlCar.setSelected(index == 5);
            this.mainnbtBinging.dashbroadLl.setSelected(index == 6);
            Button button = this.mainnbtBinging.rlSettings;
            if (index == 7) {
                z = true;
            }
            button.setSelected(z);
        }
    }

    private void focusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.app_ll:
                if (hasFocus) {
                    refrshBg(0);
                    return;
                }
                return;
            case R.id.dashbroad_ll:
                if (hasFocus) {
                    refrshBg(6);
                    return;
                }
                return;
            case R.id.music_ll:
                if (hasFocus) {
                    refrshBg(2);
                    return;
                }
                return;
            case R.id.navi_ll:
                if (hasFocus) {
                    refrshBg(1);
                    return;
                }
                return;
            case R.id.phone_ll:
                if (hasFocus) {
                    refrshBg(4);
                    return;
                }
                return;
            case R.id.rl_car:
                refrshBg(5);
                return;
            case R.id.rl_settings:
                if (hasFocus) {
                    refrshBg(7);
                    return;
                }
                return;
            case R.id.video_ll:
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
            case R.id.app_ll:
                refrshBg(0);
                openApps(v);
                return;
            case R.id.dashbroad_ll:
                refrshBg(6);
                openDashboard(v);
                return;
            case R.id.music_ll:
                refrshBg(2);
                openMusic(v);
                return;
            case R.id.navi_ll:
                refrshBg(1);
                openNaviApp(v);
                return;
            case R.id.phone_ll:
                refrshBg(4);
                openBtApp(v);
                return;
            case R.id.rl_car:
                refrshBg(5);
                openCar(v);
                return;
            case R.id.rl_settings:
                refrshBg(7);
                openSettings(v);
                return;
            case R.id.video_ll:
                refrshBg(3);
                openVideo(v);
                return;
            default:
                return;
        }
    }
}

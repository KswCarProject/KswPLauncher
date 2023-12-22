package com.wits.ksw.launcher.bmw_id8_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8PempModusLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.BaseSkinActivity;

/* loaded from: classes5.dex */
public class ID8PempModusActivity extends BaseSkinActivity {
    private final String TAG = "ID8PempModusActivity";
    private LauncherViewModel bmwId8PempViewModel;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    private String id8CacheLeftIcon5;
    private BmwId8PempModusLayoutBinding mBinding;

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ID8PempModusActivity", " onCreate ");
        setFull();
        setStatusBarTranslucent();
        this.mBinding = (BmwId8PempModusLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_pemp_modus_layout);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.bmwId8PempViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.mBinding.setLauncherViewModel(this.bmwId8PempViewModel);
        OnID8SkinChangeListener onPempID8SkinChangeListener = new OnID8SkinChangeListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.1
            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeLeftBar(int drawableId) {
                if (ID8PempModusActivity.this.mBinding != null) {
                    ID8PempModusActivity.this.mBinding.bmwId8PempModusMainLeftBar.llLeft4.setBackground(ID8PempModusActivity.this.getDrawable(drawableId));
                    ID8PempModusActivity.this.mBinding.bmwId8PempModusMainLeftBar.llLeft1.setBackground(ID8PempModusActivity.this.getDrawable(drawableId));
                    ID8PempModusActivity.this.mBinding.bmwId8PempModusMainLeftBar.llLeft2.setBackground(ID8PempModusActivity.this.getDrawable(drawableId));
                    ID8PempModusActivity.this.mBinding.bmwId8PempModusMainLeftBar.llLeft3.setBackground(ID8PempModusActivity.this.getDrawable(drawableId));
                    ID8PempModusActivity.this.mBinding.bmwId8PempModusMainLeftBar.llLeft5.setBackground(ID8PempModusActivity.this.getDrawable(drawableId));
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeCardBGSelector(String skinName) {
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeMusicAlbum(int drawableId) {
            }
        };
        this.bmwId8PempViewModel.initSkinData(onPempID8SkinChangeListener);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("ID8PempModusActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("ID8PempModusActivity", " onResume ");
        beforeRefreshLeftBarIcon();
        BmwId8PempModusLayoutBinding bmwId8PempModusLayoutBinding = this.mBinding;
        if (bmwId8PempModusLayoutBinding != null) {
            bmwId8PempModusLayoutBinding.bmwId8PempModusMainLeftBar.llLeft1.setFocusedByDefault(true);
        }
    }

    private void beforeRefreshLeftBarIcon() {
        Log.w("ID8PempModusActivity", "beforeRefreshLeftBarIcon: ");
        if (checkLeftIconHasChange()) {
            refreshLeftBarIcon();
        }
    }

    private boolean checkLeftIconHasChange() {
        return (PempID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) && PempID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4) && PempID8LauncherConstants.leftIcon5.equals(this.id8CacheLeftIcon5)) ? false : true;
    }

    private void refreshLeftBarIcon() {
        this.id8CacheLeftIcon3 = PempID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = PempID8LauncherConstants.leftIcon4;
        this.id8CacheLeftIcon5 = PempID8LauncherConstants.leftIcon5;
        initLeftIcon(this.mBinding.bmwId8PempModusMainLeftBar.llLeft3, this.mBinding.bmwId8PempModusMainLeftBar.ivLeft3, this.mBinding.bmwId8PempModusMainLeftBar.tvLeft3, this.id8CacheLeftIcon3);
        initLeftIcon(this.mBinding.bmwId8PempModusMainLeftBar.llLeft4, this.mBinding.bmwId8PempModusMainLeftBar.ivLeft4, this.mBinding.bmwId8PempModusMainLeftBar.tvLeft4, this.id8CacheLeftIcon4);
        initLeftIcon(this.mBinding.bmwId8PempModusMainLeftBar.llLeft5, this.mBinding.bmwId8PempModusMainLeftBar.ivLeft5, this.mBinding.bmwId8PempModusMainLeftBar.tvLeft5, this.id8CacheLeftIcon5);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initLeftIcon(LinearLayout linearLayout, ImageView iv, TextView tv, String name) {
        char c;
        int iconRes = -1;
        int nameRes = -1;
        switch (name.hashCode()) {
            case -1591043536:
                if (name.equals("SETTING")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case -1409845903:
                if (name.equals("NAVIGATE")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 73532672:
                if (name.equals("MODUS")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 73725445:
                if (name.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 76105038:
                if (name.equals("PHONE")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 81665115:
                if (name.equals("VIDEO")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 741767578:
                if (name.equals("CAR INFO")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 1738734196:
                if (name.equals("DASHBOARD")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 1941423060:
                if (name.equals("WEATHER")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openNaviApp();
                    }
                });
                break;
            case 1:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openMusicMulti(view);
                    }
                });
                break;
            case 2:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_car;
                nameRes = C0899R.string.ksw_id7_car;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openCar(view);
                    }
                });
                break;
            case 3:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openBtApp(view);
                    }
                });
                break;
            case 4:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openWeatherApp(view);
                    }
                });
                break;
            case 5:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.enterPempChangeModus(view);
                    }
                });
                break;
            case 6:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openDashboard(view);
                    }
                });
                break;
            case 7:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.9
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openSettings(view);
                    }
                });
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity.10
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8PempModusActivity.this.bmwId8PempViewModel.openVideoMulti(view);
                    }
                });
                break;
        }
        if (iconRes == -1) {
            return;
        }
        tv.setText(getString(nameRes));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        iv.setImageBitmap(bitmap);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.i("ID8PempModusActivity", " onPause ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("ID8PempModusActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ID8PempModusActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("ID8PempModusActivity", " dispatchKeyEvent ");
        return super.dispatchKeyEvent(event);
    }
}

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
import com.wits.ksw.databinding.BmwId8ModusLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.BaseSkinActivity;

/* loaded from: classes5.dex */
public class ID8ModusActivity extends BaseSkinActivity {
    private final String TAG = "ID8ModusActivity";
    private LauncherViewModel bmwId8ViewModel;
    private String id8CacheLeftIcon2;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    private BmwId8ModusLayoutBinding mBinding;

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ID8ModusActivity", " onCreate ");
        setFull();
        setStatusBarTranslucent();
        this.mBinding = (BmwId8ModusLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_modus_layout);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.mBinding.setLauncherViewModel(this.bmwId8ViewModel);
        OnID8SkinChangeListener onID8SkinChangeListener = new OnID8SkinChangeListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.1
            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeLeftBar(int drawableId) {
                if (ID8ModusActivity.this.mBinding != null) {
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft1.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft2.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft3.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft4.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeCardBGSelector(String skinName) {
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeMusicAlbum(int drawableId) {
            }
        };
        this.bmwId8ViewModel.initSkinData(onID8SkinChangeListener);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("ID8ModusActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("ID8ModusActivity", " onResume ");
        beforeRefreshLeftBarIcon();
        BmwId8ModusLayoutBinding bmwId8ModusLayoutBinding = this.mBinding;
        if (bmwId8ModusLayoutBinding != null) {
            bmwId8ModusLayoutBinding.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    private void beforeRefreshLeftBarIcon() {
        Log.w("ID8ModusActivity", "beforeRefreshLeftBarIcon: ");
        if (checkLeftIconHasChange()) {
            refreshLeftBarIcon();
        }
    }

    private boolean checkLeftIconHasChange() {
        return (ID8LauncherConstants.leftIcon2.equals(this.id8CacheLeftIcon2) && ID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) && ID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4)) ? false : true;
    }

    private void refreshLeftBarIcon() {
        this.id8CacheLeftIcon2 = ID8LauncherConstants.leftIcon2;
        this.id8CacheLeftIcon3 = ID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = ID8LauncherConstants.leftIcon4;
        initLeftIcon(this.mBinding.llLeftContainer.llLeft2, this.mBinding.llLeftContainer.ivLeft2, this.mBinding.llLeftContainer.tvLeft2, this.id8CacheLeftIcon2);
        initLeftIcon(this.mBinding.llLeftContainer.llLeft3, this.mBinding.llLeftContainer.ivLeft3, this.mBinding.llLeftContainer.tvLeft3, this.id8CacheLeftIcon3);
        initLeftIcon(this.mBinding.llLeftContainer.llLeft4, this.mBinding.llLeftContainer.ivLeft4, this.mBinding.llLeftContainer.tvLeft4, this.id8CacheLeftIcon4);
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
                iconRes = C0899R.C0900drawable.id8_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openNaviApp(view);
                    }
                });
                break;
            case 1:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openMusicMulti(view);
                    }
                });
                break;
            case 2:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_car;
                nameRes = C0899R.string.ksw_id8_abbr_car_info;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openCar(view);
                    }
                });
                break;
            case 3:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openBtApp(view);
                    }
                });
                break;
            case 4:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openWeatherApp(view);
                    }
                });
                break;
            case 5:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.enterChangeModus(view);
                    }
                });
                break;
            case 6:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openDashboard(view);
                    }
                });
                break;
            case 7:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.9
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openSettings(view);
                    }
                });
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.10
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ID8ModusActivity.this.bmwId8ViewModel.openVideoMulti(view);
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
        Log.i("ID8ModusActivity", " onPause ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("ID8ModusActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ID8ModusActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("ID8ModusActivity", " dispatchKeyEvent ");
        return super.dispatchKeyEvent(event);
    }
}

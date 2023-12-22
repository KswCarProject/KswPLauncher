package com.wits.ksw.launcher.view.audimib3v2;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3OneDataClsV2;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes12.dex */
public class AudiMib3FragmentOneV2 extends AudiMib3BaseFragmentV2 implements View.OnKeyListener {
    public static final String TAG = "AudiMib3FragmentOne - One";
    public static AudiMib3OneDataClsV2 bindingOne;
    private AnimationDrawable animDrawBt;
    private AnimationDrawable animDrawCar;
    private AnimationDrawable animDrawMusic;
    private AnimationDrawable animDrawNavi;
    private AnimationDrawable animDrawPhonelink;
    private AnimationDrawable animDrawSet;
    private AnimationDrawable animDrawVideo;
    private AnimationDrawable animDrawWeather;
    private View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentOneV2.2
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            AudiMib3FragmentOneV2.this.viewModel.clearLastSel();
            switch (v.getId()) {
                case C0899R.C0901id.bt_itemview /* 2131296685 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawBt.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawBt.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawBt.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawBt.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.car_itemview /* 2131296719 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawCar.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawCar.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawCar.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawCar.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.music_itemview /* 2131297366 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawMusic.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawMusic.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawMusic.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawMusic.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.navi_itemview /* 2131297390 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawNavi.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawNavi.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawNavi.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawNavi.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.phonelink_itemview /* 2131297437 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawPhonelink.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawPhonelink.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawPhonelink.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawPhonelink.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.set_itemview /* 2131297748 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawSet.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawSet.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawSet.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawSet.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.video_itemview /* 2131298051 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawVideo.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawVideo.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawVideo.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawVideo.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.weather_itemview /* 2131298073 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOneV2.this.animDrawWeather.isRunning()) {
                            AudiMib3FragmentOneV2.this.animDrawWeather.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOneV2.this.animDrawWeather.isRunning()) {
                        AudiMib3FragmentOneV2.this.animDrawWeather.stop();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentOneV2.3
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.bt_itemview /* 2131296685 */:
                    BcVieModel bcVieModel = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.btItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.btItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openBtApp(v);
                    return;
                case C0899R.C0901id.car_itemview /* 2131296719 */:
                    BcVieModel bcVieModel2 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.carItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.carItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openCar(v);
                    return;
                case C0899R.C0901id.music_itemview /* 2131297366 */:
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.musicItemview);
                    BcVieModel bcVieModel3 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.musicItemview;
                    AudiMib3FragmentOneV2.this.viewModel.openMusicMulti(v);
                    return;
                case C0899R.C0901id.navi_itemview /* 2131297390 */:
                    BcVieModel bcVieModel4 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.naviItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.naviItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openNaviApp(v);
                    return;
                case C0899R.C0901id.phonelink_itemview /* 2131297437 */:
                    BcVieModel bcVieModel5 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.phonelinkItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.phonelinkItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openPhoneLink2021(v);
                    return;
                case C0899R.C0901id.set_itemview /* 2131297748 */:
                    BcVieModel bcVieModel6 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.setItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.setItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openSettings(v);
                    return;
                case C0899R.C0901id.video_itemview /* 2131298051 */:
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.videoItemview);
                    BcVieModel bcVieModel7 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.videoItemview;
                    AudiMib3FragmentOneV2.this.viewModel.openVideoMulti(v);
                    return;
                case C0899R.C0901id.weather_itemview /* 2131298073 */:
                    BcVieModel bcVieModel8 = AudiMib3FragmentOneV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOneV2.bindingOne.weatherItemview;
                    AudiMib3FragmentOneV2.setItemSelected(AudiMib3FragmentOneV2.bindingOne.weatherItemview);
                    AudiMib3FragmentOneV2.this.viewModel.openWeatherApp(v);
                    return;
                default:
                    return;
            }
        }
    };
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentOneV2.4
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i("AudiMib3FragmentOne - One", "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override // com.wits.ksw.launcher.view.audimib3v2.AudiMib3BaseFragmentV2, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3OneDataClsV2 audiMib3OneDataClsV2 = (AudiMib3OneDataClsV2) DataBindingUtil.inflate(inflater, C0899R.C0902layout.audi_mib3_fragment_one_v2, null, false);
        bindingOne = audiMib3OneDataClsV2;
        return audiMib3OneDataClsV2.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("AudiMib3FragmentOne", "onResume()  1111111111111");
        this.viewModel.refreshLastSel();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingOne.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.e("liuhao", "isSmooth = " + isSmooth);
        bindingOne.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentOneV2.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhao bindingOne.setItemview", "isSmooth 0000000000 hasFocus = " + hasFocus);
                if (hasFocus && AudiMib3FragmentOneV2.this.mainActivity.viewpagerAudiMib3 != null && AudiMib3FragmentOneV2.this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 0) {
                    AudiMib3FragmentOneV2.this.mainActivity.viewpagerAudiMib3.setCurrentItem(0);
                }
            }
        });
        bindingOne.videoItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.videoIv.setOnClickListener(this.mItemClickListener);
        bindingOne.videoTv.setOnClickListener(this.mItemClickListener);
        bindingOne.musicItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.musicIv.setOnClickListener(this.mItemClickListener);
        bindingOne.musicTv.setOnClickListener(this.mItemClickListener);
        bindingOne.btItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.btTv.setOnClickListener(this.mItemClickListener);
        bindingOne.btIv.setOnClickListener(this.mItemClickListener);
        bindingOne.naviItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.naviTv.setOnClickListener(this.mItemClickListener);
        bindingOne.naviIv.setOnClickListener(this.mItemClickListener);
        bindingOne.phonelinkItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.phonelinkIv.setOnClickListener(this.mItemClickListener);
        bindingOne.phonelinkTv.setOnClickListener(this.mItemClickListener);
        bindingOne.carItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.carTv.setOnClickListener(this.mItemClickListener);
        bindingOne.carIv.setOnClickListener(this.mItemClickListener);
        bindingOne.weatherItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.weatherIv.setOnClickListener(this.mItemClickListener);
        bindingOne.weatherTv.setOnClickListener(this.mItemClickListener);
        bindingOne.setItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.setIv.setOnClickListener(this.mItemClickListener);
        bindingOne.setTv.setOnClickListener(this.mItemClickListener);
        bindingOne.videoItemview.setOnKeyListener(this);
        bindingOne.musicItemview.setOnKeyListener(this);
        bindingOne.btItemview.setOnKeyListener(this);
        bindingOne.naviItemview.setOnKeyListener(this);
        bindingOne.phonelinkItemview.setOnKeyListener(this);
        bindingOne.carItemview.setOnKeyListener(this);
        bindingOne.weatherItemview.setOnKeyListener(this);
        bindingOne.setItemview.setOnKeyListener(this);
        this.animDrawVideo = (AnimationDrawable) bindingOne.videoItemview.getDrawable();
        this.animDrawMusic = (AnimationDrawable) bindingOne.musicItemview.getDrawable();
        this.animDrawBt = (AnimationDrawable) bindingOne.btItemview.getDrawable();
        this.animDrawNavi = (AnimationDrawable) bindingOne.naviItemview.getDrawable();
        this.animDrawPhonelink = (AnimationDrawable) bindingOne.phonelinkItemview.getDrawable();
        this.animDrawCar = (AnimationDrawable) bindingOne.carItemview.getDrawable();
        this.animDrawWeather = (AnimationDrawable) bindingOne.weatherItemview.getDrawable();
        this.animDrawSet = (AnimationDrawable) bindingOne.setItemview.getDrawable();
        bindingOne.videoItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.musicItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.btItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.naviItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.phonelinkItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.carItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.weatherItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.setItemview.setOnFocusChangeListener(this.mFocusChangeListener);
    }

    public static void setItemSelected(View view) {
        bindingOne.videoItemview.setSelected(bindingOne.videoItemview == view);
        bindingOne.musicItemview.setSelected(bindingOne.musicItemview == view);
        bindingOne.btItemview.setSelected(bindingOne.btItemview == view);
        bindingOne.naviItemview.setSelected(bindingOne.naviItemview == view);
        bindingOne.phonelinkItemview.setSelected(bindingOne.phonelinkItemview == view);
        bindingOne.carItemview.setSelected(bindingOne.carItemview == view);
        bindingOne.weatherItemview.setSelected(bindingOne.weatherItemview == view);
        bindingOne.setItemview.setSelected(bindingOne.setItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(bindingOne.videoItemview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("AudiMib3FragmentOne - One", "Audimib3 onKey: " + keyCode);
            if (keyCode == 22) {
                if (v == bindingOne.setItemview && this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 1) {
                    this.mainActivity.viewpagerAudiMib3.setCurrentItem(1);
                    return true;
                } else if (v == bindingOne.naviItemview) {
                    Log.e("liuhao", "isAudi_mib3 22222222222");
                    KeyUtils.pressKey(20);
                    return true;
                } else {
                    return false;
                }
            } else if (keyCode == 20 && v == bindingOne.setItemview && this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 1) {
                this.mainActivity.viewpagerAudiMib3.setCurrentItem(1);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void refreshLastSel(View view) {
        setItemSelected(view);
    }
}

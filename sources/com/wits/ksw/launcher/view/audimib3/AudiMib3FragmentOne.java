package com.wits.ksw.launcher.view.audimib3;

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
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3OneDataCls;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AudiMib3FragmentOne extends AudiMib3BaseFragment implements View.OnKeyListener {
    public static final String TAG = "AudiMib3FragmentOne - One";
    public static AudiMib3OneDataCls bindingOne;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawApps;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawBt;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawCar;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawMusic;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawNavi;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawPhonelink;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawSet;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawVideo;
    private View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.app_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawApps.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawApps.start();
                            AudiMib3FragmentOne.bindingOne.appItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_app_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawApps.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawApps.stop();
                        AudiMib3FragmentOne.bindingOne.appItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_apps));
                        return;
                    } else {
                        return;
                    }
                case R.id.bt_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawBt.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawBt.start();
                            AudiMib3FragmentOne.bindingOne.btItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_bt_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawBt.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawBt.stop();
                        AudiMib3FragmentOne.bindingOne.btItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_bt));
                        return;
                    } else {
                        return;
                    }
                case R.id.car_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawCar.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawCar.start();
                            AudiMib3FragmentOne.bindingOne.carItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_car_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawCar.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawCar.stop();
                        AudiMib3FragmentOne.bindingOne.carItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_car));
                        return;
                    } else {
                        return;
                    }
                case R.id.music_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawMusic.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawMusic.start();
                            AudiMib3FragmentOne.bindingOne.musicItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_music_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawMusic.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawMusic.stop();
                        AudiMib3FragmentOne.bindingOne.musicItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_music));
                        return;
                    } else {
                        return;
                    }
                case R.id.navi_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawNavi.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawNavi.start();
                            AudiMib3FragmentOne.bindingOne.naviItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_gps_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawNavi.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawNavi.stop();
                        AudiMib3FragmentOne.bindingOne.naviItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_navi));
                        return;
                    } else {
                        return;
                    }
                case R.id.phonelink_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawPhonelink.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawPhonelink.start();
                            AudiMib3FragmentOne.bindingOne.phonelinkItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_phonelink_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawPhonelink.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawPhonelink.stop();
                        AudiMib3FragmentOne.bindingOne.phonelinkItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_phonelink));
                        return;
                    } else {
                        return;
                    }
                case R.id.set_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawSet.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawSet.start();
                            AudiMib3FragmentOne.bindingOne.setItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_set_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawSet.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawSet.stop();
                        AudiMib3FragmentOne.bindingOne.setItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_set));
                        return;
                    } else {
                        return;
                    }
                case R.id.video_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawVideo.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawVideo.start();
                            AudiMib3FragmentOne.bindingOne.videoItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_video_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawVideo.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawVideo.stop();
                        AudiMib3FragmentOne.bindingOne.videoItemview.setBackground(AudiMib3FragmentOne.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_video));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.app_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.appItemview);
                    AudiMib3FragmentOne.this.viewModel.openApps(v);
                    return;
                case R.id.bt_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.btItemview);
                    AudiMib3FragmentOne.this.viewModel.openBtApp(v);
                    return;
                case R.id.car_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.carItemview);
                    AudiMib3FragmentOne.this.viewModel.openCar(v);
                    return;
                case R.id.music_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.musicItemview);
                    AudiMib3FragmentOne.this.viewModel.openMusic(v);
                    return;
                case R.id.navi_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.naviItemview);
                    AudiMib3FragmentOne.this.viewModel.openNaviApp(v);
                    return;
                case R.id.phonelink_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.phonelinkItemview);
                    AudiMib3FragmentOne.this.viewModel.openPhoneLink2021(v);
                    return;
                case R.id.set_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.setItemview);
                    AudiMib3FragmentOne.this.viewModel.openSettings(v);
                    return;
                case R.id.video_itemview:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.videoItemview);
                    AudiMib3FragmentOne.this.viewModel.openVideo(v);
                    return;
                default:
                    return;
            }
        }
    };
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(AudiMib3FragmentOne.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3OneDataCls audiMib3OneDataCls = (AudiMib3OneDataCls) DataBindingUtil.inflate(inflater, R.layout.audi_mib3_fragment_one, (ViewGroup) null, false);
        bindingOne = audiMib3OneDataCls;
        return audiMib3OneDataCls.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingOne.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.e("liuhao", "isSmooth = " + isSmooth);
        bindingOne.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhao", "isSmooth 0000000000 hasFocus = " + hasFocus);
                if (hasFocus && AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3 != null && AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 0) {
                    AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3.setCurrentItem(0);
                    AudiMib3FragmentOne.setItemSelected(v);
                    AudiMib3FragmentOne.bindingOne.setItemview.setSelected(true);
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
        bindingOne.appItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.appIv.setOnClickListener(this.mItemClickListener);
        bindingOne.appIv.setOnClickListener(this.mItemClickListener);
        bindingOne.setItemview.setOnClickListener(this.mItemClickListener);
        bindingOne.setIv.setOnClickListener(this.mItemClickListener);
        bindingOne.setTv.setOnClickListener(this.mItemClickListener);
        bindingOne.videoItemview.setOnKeyListener(this);
        bindingOne.musicItemview.setOnKeyListener(this);
        bindingOne.btItemview.setOnKeyListener(this);
        bindingOne.naviItemview.setOnKeyListener(this);
        bindingOne.phonelinkItemview.setOnKeyListener(this);
        bindingOne.carItemview.setOnKeyListener(this);
        bindingOne.appItemview.setOnKeyListener(this);
        bindingOne.setItemview.setOnKeyListener(this);
        this.animDrawVideo = (AnimationDrawable) bindingOne.videoItemview.getDrawable();
        this.animDrawMusic = (AnimationDrawable) bindingOne.musicItemview.getDrawable();
        this.animDrawBt = (AnimationDrawable) bindingOne.btItemview.getDrawable();
        this.animDrawNavi = (AnimationDrawable) bindingOne.naviItemview.getDrawable();
        this.animDrawPhonelink = (AnimationDrawable) bindingOne.phonelinkItemview.getDrawable();
        this.animDrawCar = (AnimationDrawable) bindingOne.carItemview.getDrawable();
        this.animDrawApps = (AnimationDrawable) bindingOne.appItemview.getDrawable();
        this.animDrawSet = (AnimationDrawable) bindingOne.setItemview.getDrawable();
        bindingOne.videoItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.musicItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.btItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.naviItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.phonelinkItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.carItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.appItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingOne.setItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        setDefaultSelected();
    }

    public static void setItemSelected(View view) {
        boolean z = true;
        bindingOne.videoItemview.setSelected(bindingOne.videoItemview == view);
        bindingOne.musicItemview.setSelected(bindingOne.musicItemview == view);
        bindingOne.btItemview.setSelected(bindingOne.btItemview == view);
        bindingOne.naviItemview.setSelected(bindingOne.naviItemview == view);
        bindingOne.phonelinkItemview.setSelected(bindingOne.phonelinkItemview == view);
        bindingOne.carItemview.setSelected(bindingOne.carItemview == view);
        bindingOne.appItemview.setSelected(bindingOne.appItemview == view);
        BenzMbuxItemView benzMbuxItemView = bindingOne.setItemview;
        if (bindingOne.setItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(bindingOne.videoItemview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        Log.i(TAG, "Audimib3 onKey: " + keyCode);
        if (keyCode == 20) {
            if (v == bindingOne.videoItemview) {
                setItemSelected(bindingOne.musicItemview);
                return false;
            } else if (v == bindingOne.musicItemview) {
                setItemSelected(bindingOne.btItemview);
                return false;
            } else if (v == bindingOne.btItemview) {
                setItemSelected(bindingOne.naviItemview);
                return false;
            } else if (v == bindingOne.naviItemview) {
                Log.e("liuhao", "isAudi_mib3 0000000");
                setItemSelected(bindingOne.phonelinkItemview);
                return false;
            } else if (v == bindingOne.phonelinkItemview) {
                setItemSelected(bindingOne.carItemview);
                return false;
            } else if (v == bindingOne.carItemview) {
                setItemSelected(bindingOne.appItemview);
                return false;
            } else if (v == bindingOne.appItemview) {
                setItemSelected(bindingOne.setItemview);
                return false;
            } else if (v != bindingOne.setItemview || this.mainActivity.viewpagerAudiMib3.getCurrentItem() == 1) {
                return false;
            } else {
                this.mainActivity.viewpagerAudiMib3.setCurrentItem(1);
                return true;
            }
        } else if (keyCode != 19) {
            return false;
        } else {
            if (v == bindingOne.setItemview) {
                setItemSelected(bindingOne.appItemview);
                return false;
            } else if (v == bindingOne.appItemview) {
                setItemSelected(bindingOne.carItemview);
                return false;
            } else if (v == bindingOne.carItemview) {
                setItemSelected(bindingOne.phonelinkItemview);
                return false;
            } else if (v == bindingOne.phonelinkItemview) {
                setItemSelected(bindingOne.naviItemview);
                return false;
            } else if (v == bindingOne.naviItemview) {
                setItemSelected(bindingOne.btItemview);
                return false;
            } else if (v == bindingOne.btItemview) {
                setItemSelected(bindingOne.musicItemview);
                return false;
            } else if (v == bindingOne.musicItemview) {
                setItemSelected(bindingOne.videoItemview);
                return false;
            } else if (v != bindingOne.videoItemview) {
                return false;
            } else {
                setItemSelected(bindingOne.videoItemview);
                return true;
            }
        }
    }
}

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
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.utils.KeyUtils;
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
            AudiMib3FragmentOne.this.viewModel.clearLastSel();
            switch (v.getId()) {
                case R.id.app_itemview /*2131296342*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawApps.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawApps.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawApps.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawApps.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.bt_itemview /*2131296659*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawBt.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawBt.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawBt.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawBt.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.car_itemview /*2131296693*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawCar.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawCar.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawCar.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawCar.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.music_itemview /*2131297337*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawMusic.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawMusic.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawMusic.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawMusic.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.navi_itemview /*2131297361*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawNavi.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawNavi.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawNavi.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawNavi.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.phonelink_itemview /*2131297405*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawPhonelink.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawPhonelink.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawPhonelink.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawPhonelink.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.set_itemview /*2131297701*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawSet.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawSet.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawSet.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawSet.stop();
                        return;
                    } else {
                        return;
                    }
                case R.id.video_itemview /*2131297999*/:
                    if (hasFocus) {
                        if (!AudiMib3FragmentOne.this.animDrawVideo.isRunning()) {
                            AudiMib3FragmentOne.this.animDrawVideo.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentOne.this.animDrawVideo.isRunning()) {
                        AudiMib3FragmentOne.this.animDrawVideo.stop();
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
                case R.id.app_itemview /*2131296342*/:
                    BcVieModel bcVieModel = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.appItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.appItemview);
                    AudiMib3FragmentOne.this.viewModel.openApps(v);
                    return;
                case R.id.bt_itemview /*2131296659*/:
                    BcVieModel bcVieModel2 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.btItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.btItemview);
                    AudiMib3FragmentOne.this.viewModel.openBtApp(v);
                    return;
                case R.id.car_itemview /*2131296693*/:
                    BcVieModel bcVieModel3 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.carItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.carItemview);
                    AudiMib3FragmentOne.this.viewModel.openCar(v);
                    return;
                case R.id.music_itemview /*2131297337*/:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.musicItemview);
                    BcVieModel bcVieModel4 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.musicItemview;
                    AudiMib3FragmentOne.this.viewModel.openMusicMulti(v);
                    return;
                case R.id.navi_itemview /*2131297361*/:
                    BcVieModel bcVieModel5 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.naviItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.naviItemview);
                    AudiMib3FragmentOne.this.viewModel.openNaviApp(v);
                    return;
                case R.id.phonelink_itemview /*2131297405*/:
                    BcVieModel bcVieModel6 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.phonelinkItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.phonelinkItemview);
                    AudiMib3FragmentOne.this.viewModel.openPhoneLink2021(v);
                    return;
                case R.id.set_itemview /*2131297701*/:
                    BcVieModel bcVieModel7 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.setItemview;
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.setItemview);
                    AudiMib3FragmentOne.this.viewModel.openSettings(v);
                    return;
                case R.id.video_itemview /*2131297999*/:
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.videoItemview);
                    BcVieModel bcVieModel8 = AudiMib3FragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentOne.bindingOne.videoItemview;
                    AudiMib3FragmentOne.this.viewModel.openVideoMulti(v);
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
                Log.i("AudiMib3FragmentOne - One", "onChange: topApp=" + topApp);
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

    public void onResume() {
        super.onResume();
        Log.d("AudiMib3FragmentOne", "onResume()  1111111111111");
        this.viewModel.refreshLastSel();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingOne.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.e("liuhao", "isSmooth = " + isSmooth);
        bindingOne.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhao bindingOne.setItemview", "isSmooth 0000000000 hasFocus = " + hasFocus);
                if (hasFocus && AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3 != null && AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 0) {
                    AudiMib3FragmentOne.this.mainActivity.viewpagerAudiMib3.setCurrentItem(0);
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
        Log.i("AudiMib3FragmentOne - One", "Audimib3 onKey: " + keyCode);
        if (keyCode == 22) {
            if (v == bindingOne.setItemview && this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 1) {
                this.mainActivity.viewpagerAudiMib3.setCurrentItem(1);
                return true;
            } else if (v != bindingOne.naviItemview) {
                return false;
            } else {
                Log.e("liuhao", "isAudi_mib3 22222222222");
                KeyUtils.pressKey(20);
                return true;
            }
        } else if (keyCode != 20 || v != bindingOne.setItemview || this.mainActivity.viewpagerAudiMib3.getCurrentItem() == 1) {
            return false;
        } else {
            this.mainActivity.viewpagerAudiMib3.setCurrentItem(1);
            return true;
        }
    }

    public void refreshLastSel(View view) {
        setItemSelected(view);
    }
}

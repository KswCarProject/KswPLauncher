package com.wits.ksw.launcher.view.audimib3ty;

import android.databinding.DataBindingUtil;
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
import com.wits.ksw.databinding.AudiMib3TyOneBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AudiMib3TyFragmentOne extends AudiMib3TyBaseFragment implements View.OnKeyListener {
    public static final String TAG = "AudiMib3TyFragmentOne - One";
    public static AudiMib3TyOneBinding bindingOne;
    private View.OnClickListener mItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_itemview /*2131296659*/:
                    BcVieModel bcVieModel = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.btItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.btItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openBtApp(v);
                    return;
                case R.id.music_itemview /*2131297337*/:
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.musicItemview);
                    BcVieModel bcVieModel2 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.musicItemview;
                    AudiMib3TyFragmentOne.this.viewModel.openMusicMulti(v);
                    return;
                case R.id.navi_itemview /*2131297361*/:
                    BcVieModel bcVieModel3 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.naviItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.naviItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openNaviApp(v);
                    return;
                case R.id.phonelink_itemview /*2131297405*/:
                    BcVieModel bcVieModel4 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.phonelinkItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.phonelinkItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openPhoneLink2021(v);
                    return;
                case R.id.video_itemview /*2131297999*/:
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.videoItemview);
                    BcVieModel bcVieModel5 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.videoItemview;
                    AudiMib3TyFragmentOne.this.viewModel.openVideoMulti(v);
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
                Log.i(AudiMib3TyFragmentOne.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3TyOneBinding audiMib3TyOneBinding = (AudiMib3TyOneBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_audi_mib3_ty_one, (ViewGroup) null, false);
        bindingOne = audiMib3TyOneBinding;
        return audiMib3TyOneBinding.getRoot();
    }

    public void onResume() {
        super.onResume();
        Log.d("AudiMib3TyFragmentOne", "onResume()  1111111111111");
        this.viewModel.refreshLastSel();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingOne.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.e("liuhao", "isSmooth = " + isSmooth);
        bindingOne.phonelinkItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhao bindingOne.setItemview", "isSmooth 0000000000 hasFocus = " + hasFocus);
                if (hasFocus && AudiMib3TyFragmentOne.this.mainActivity.audiMib3TyViewPager != null && AudiMib3TyFragmentOne.this.mainActivity.audiMib3TyViewPager.getCurrentItem() != 0) {
                    AudiMib3TyFragmentOne.this.mainActivity.audiMib3TyViewPager.setCurrentItem(0);
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
        bindingOne.videoItemview.setOnKeyListener(this);
        bindingOne.musicItemview.setOnKeyListener(this);
        bindingOne.btItemview.setOnKeyListener(this);
        bindingOne.naviItemview.setOnKeyListener(this);
        bindingOne.phonelinkItemview.setOnKeyListener(this);
    }

    public static void setItemSelected(View view) {
        boolean z = true;
        bindingOne.videoItemview.setSelected(bindingOne.videoItemview == view);
        bindingOne.musicItemview.setSelected(bindingOne.musicItemview == view);
        bindingOne.btItemview.setSelected(bindingOne.btItemview == view);
        bindingOne.naviItemview.setSelected(bindingOne.naviItemview == view);
        BenzMbuxItemView benzMbuxItemView = bindingOne.phonelinkItemview;
        if (bindingOne.phonelinkItemview != view) {
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
        if (keyCode == 22) {
            if (v != bindingOne.phonelinkItemview || this.mainActivity.audiMib3TyViewPager.getCurrentItem() == 1) {
                return false;
            }
            this.mainActivity.audiMib3TyViewPager.setCurrentItem(1);
            return true;
        } else if (keyCode != 20 || v != bindingOne.phonelinkItemview || this.mainActivity.audiMib3TyViewPager.getCurrentItem() == 1) {
            return false;
        } else {
            this.mainActivity.audiMib3TyViewPager.setCurrentItem(1);
            return true;
        }
    }

    public void refreshLastSel(View view) {
        setItemSelected(view);
    }
}

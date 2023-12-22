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
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3TyOneBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes6.dex */
public class AudiMib3TyFragmentOne extends AudiMib3TyBaseFragment implements View.OnKeyListener {
    public static final String TAG = "AudiMib3TyFragmentOne - One";
    public static AudiMib3TyOneBinding bindingOne;
    private View.OnClickListener mItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyFragmentOne.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.bt_itemview /* 2131296685 */:
                    BcVieModel bcVieModel = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.btItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.btItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openBtApp(v);
                    return;
                case C0899R.C0901id.music_itemview /* 2131297366 */:
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.musicItemview);
                    BcVieModel bcVieModel2 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.musicItemview;
                    AudiMib3TyFragmentOne.this.viewModel.openMusicMulti(v);
                    return;
                case C0899R.C0901id.navi_itemview /* 2131297390 */:
                    BcVieModel bcVieModel3 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.naviItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.naviItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openNaviApp(v);
                    return;
                case C0899R.C0901id.phonelink_itemview /* 2131297437 */:
                    BcVieModel bcVieModel4 = AudiMib3TyFragmentOne.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentOne.bindingOne.phonelinkItemview;
                    AudiMib3TyFragmentOne.setItemSelected(AudiMib3TyFragmentOne.bindingOne.phonelinkItemview);
                    AudiMib3TyFragmentOne.this.viewModel.openPhoneLink2021(v);
                    return;
                case C0899R.C0901id.video_itemview /* 2131298051 */:
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
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyFragmentOne.3
        @Override // com.wits.pms.IContentObserver
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

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3TyOneBinding audiMib3TyOneBinding = (AudiMib3TyOneBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_audi_mib3_ty_one, null, false);
        bindingOne = audiMib3TyOneBinding;
        return audiMib3TyOneBinding.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("AudiMib3TyFragmentOne", "onResume()  1111111111111");
        this.viewModel.refreshLastSel();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingOne.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.e("liuhao", "isSmooth = " + isSmooth);
        bindingOne.phonelinkItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyFragmentOne.1
            @Override // android.view.View.OnFocusChangeListener
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
        bindingOne.videoItemview.setSelected(bindingOne.videoItemview == view);
        bindingOne.musicItemview.setSelected(bindingOne.musicItemview == view);
        bindingOne.btItemview.setSelected(bindingOne.btItemview == view);
        bindingOne.naviItemview.setSelected(bindingOne.naviItemview == view);
        bindingOne.phonelinkItemview.setSelected(bindingOne.phonelinkItemview == view);
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
            Log.i(TAG, "Audimib3 onKey: " + keyCode);
            if (keyCode == 22) {
                if (v == bindingOne.phonelinkItemview && this.mainActivity.audiMib3TyViewPager.getCurrentItem() != 1) {
                    this.mainActivity.audiMib3TyViewPager.setCurrentItem(1);
                    return true;
                }
                return false;
            } else if (keyCode == 20 && v == bindingOne.phonelinkItemview && this.mainActivity.audiMib3TyViewPager.getCurrentItem() != 1) {
                this.mainActivity.audiMib3TyViewPager.setCurrentItem(1);
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

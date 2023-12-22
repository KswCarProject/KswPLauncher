package com.wits.ksw.launcher.view.audimib3v2;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3TwoDataClsV2;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes12.dex */
public class AudiMib3FragmentTwoV2 extends AudiMib3BaseFragmentV2 implements View.OnKeyListener {
    public static final String TAG = "AudiMib3FragmentTwo - Two";
    public static AudiMib3TwoDataClsV2 bindingTwo;
    private AnimationDrawable animDrawApp;
    private AnimationDrawable animDrawBrowser;
    private AnimationDrawable animDrawDashboard;
    private AnimationDrawable animDrawDvr;
    private AnimationDrawable animDrawFile;
    private View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentTwoV2.1
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            AudiMib3FragmentTwoV2.this.viewModel.clearLastSel();
            switch (v.getId()) {
                case C0899R.C0901id.app_itemview /* 2131296344 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwoV2.this.animDrawApp.isRunning()) {
                            AudiMib3FragmentTwoV2.this.animDrawApp.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwoV2.this.animDrawApp.isRunning()) {
                        AudiMib3FragmentTwoV2.this.animDrawApp.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.browser_itemview /* 2131296677 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwoV2.this.animDrawBrowser.isRunning()) {
                            AudiMib3FragmentTwoV2.this.animDrawBrowser.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwoV2.this.animDrawBrowser.isRunning()) {
                        AudiMib3FragmentTwoV2.this.animDrawBrowser.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwoV2.this.animDrawDashboard.isRunning()) {
                            AudiMib3FragmentTwoV2.this.animDrawDashboard.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwoV2.this.animDrawDashboard.isRunning()) {
                        AudiMib3FragmentTwoV2.this.animDrawDashboard.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.dvr_itemview /* 2131296851 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwoV2.this.animDrawDvr.isRunning()) {
                            AudiMib3FragmentTwoV2.this.animDrawDvr.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwoV2.this.animDrawDvr.isRunning()) {
                        AudiMib3FragmentTwoV2.this.animDrawDvr.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.file_itemview /* 2131296874 */:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwoV2.this.animDrawFile.isRunning()) {
                            AudiMib3FragmentTwoV2.this.animDrawFile.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwoV2.this.animDrawFile.isRunning()) {
                        AudiMib3FragmentTwoV2.this.animDrawFile.stop();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentTwoV2.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.app_itemview /* 2131296344 */:
                    BcVieModel bcVieModel = AudiMib3FragmentTwoV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentTwoV2.bindingTwo.appItemview;
                    AudiMib3FragmentTwoV2.setItemSelected(AudiMib3FragmentTwoV2.bindingTwo.appItemview);
                    AudiMib3FragmentTwoV2.this.viewModel.openApps(v);
                    return;
                case C0899R.C0901id.browser_itemview /* 2131296677 */:
                    BcVieModel bcVieModel2 = AudiMib3FragmentTwoV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentTwoV2.bindingTwo.browserItemview;
                    AudiMib3FragmentTwoV2.setItemSelected(AudiMib3FragmentTwoV2.bindingTwo.browserItemview);
                    AudiMib3FragmentTwoV2.this.viewModel.openBrowser(v);
                    return;
                case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
                    BcVieModel bcVieModel3 = AudiMib3FragmentTwoV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentTwoV2.bindingTwo.dashboardItemview;
                    AudiMib3FragmentTwoV2.setItemSelected(AudiMib3FragmentTwoV2.bindingTwo.dashboardItemview);
                    AudiMib3FragmentTwoV2.this.viewModel.openDashboard(v);
                    return;
                case C0899R.C0901id.dvr_itemview /* 2131296851 */:
                    BcVieModel bcVieModel4 = AudiMib3FragmentTwoV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentTwoV2.bindingTwo.dvrItemview;
                    AudiMib3FragmentTwoV2.setItemSelected(AudiMib3FragmentTwoV2.bindingTwo.dvrItemview);
                    AudiMib3FragmentTwoV2.this.viewModel.openDvr(v);
                    return;
                case C0899R.C0901id.file_itemview /* 2131296874 */:
                    BcVieModel bcVieModel5 = AudiMib3FragmentTwoV2.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FragmentTwoV2.bindingTwo.fileItemview;
                    AudiMib3FragmentTwoV2.setItemSelected(AudiMib3FragmentTwoV2.bindingTwo.fileItemview);
                    AudiMib3FragmentTwoV2.this.viewModel.openFileManager(v);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.wits.ksw.launcher.view.audimib3v2.AudiMib3BaseFragmentV2, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3TwoDataClsV2 audiMib3TwoDataClsV2 = (AudiMib3TwoDataClsV2) DataBindingUtil.inflate(inflater, C0899R.C0902layout.audi_mib3_fragment_two_v2, null, false);
        bindingTwo = audiMib3TwoDataClsV2;
        return audiMib3TwoDataClsV2.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingTwo.setViewModel(this.viewModel);
        bindingTwo.dvrItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.fileItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.fileIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.fileTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.browserItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.browserIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.browserTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.appItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.appIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.appTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrItemview.setOnKeyListener(this);
        bindingTwo.dashboardItemview.setOnKeyListener(this);
        bindingTwo.fileItemview.setOnKeyListener(this);
        bindingTwo.browserItemview.setOnKeyListener(this);
        bindingTwo.appItemview.setOnKeyListener(this);
        this.animDrawDvr = (AnimationDrawable) bindingTwo.dvrItemview.getDrawable();
        this.animDrawDashboard = (AnimationDrawable) bindingTwo.dashboardItemview.getDrawable();
        this.animDrawFile = (AnimationDrawable) bindingTwo.fileItemview.getDrawable();
        this.animDrawBrowser = (AnimationDrawable) bindingTwo.browserItemview.getDrawable();
        this.animDrawApp = (AnimationDrawable) bindingTwo.appItemview.getDrawable();
        bindingTwo.dvrItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.dashboardItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.fileItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.browserItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.appItemview.setOnFocusChangeListener(this.mFocusChangeListener);
    }

    public static void setItemSelected(View view) {
        bindingTwo.dvrItemview.setSelected(bindingTwo.dvrItemview == view);
        bindingTwo.dashboardItemview.setSelected(bindingTwo.dashboardItemview == view);
        bindingTwo.fileItemview.setSelected(bindingTwo.fileItemview == view);
        bindingTwo.browserItemview.setSelected(bindingTwo.browserItemview == view);
        bindingTwo.appItemview.setSelected(bindingTwo.appItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(bindingTwo.dvrItemview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("AudiMib3FragmentOne", "onResume() 2222222222 ");
        this.viewModel.refreshLastSel();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("AudiMib3FragmentTwo - Two", "onKey: " + keyCode);
            if (keyCode == 20 || keyCode == 22) {
                if (v == bindingTwo.dvrItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.fileItemview);
                } else if (v == bindingTwo.fileItemview) {
                    setItemSelected(bindingTwo.browserItemview);
                } else if (v == bindingTwo.browserItemview) {
                    setItemSelected(bindingTwo.appItemview);
                } else if (v == bindingTwo.appItemview) {
                    setItemSelected(bindingTwo.appItemview);
                    return true;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == bindingTwo.appItemview) {
                    Log.i("AudiMib3FragmentTwo - Two", "onKey: \u8fdb\u5165app\u5224\u65ad");
                    setItemSelected(bindingTwo.browserItemview);
                    bindingTwo.browserItemview.requestFocus();
                    return true;
                } else if (v == bindingTwo.browserItemview) {
                    setItemSelected(bindingTwo.fileItemview);
                } else if (v == bindingTwo.fileItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.dvrItemview);
                } else if (v == bindingTwo.dvrItemview && this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 0) {
                    this.mainActivity.viewpagerAudiMib3.setCurrentItem(0);
                    AudiMib3FragmentOneV2.bindingOne.setItemview.requestFocus();
                    isSmooth = true;
                    return true;
                }
            }
        }
        return false;
    }
}

package com.wits.ksw.launcher.view.audimib3fyv2;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMib3FyV2TwoDataCls;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.audimib3fy.AudiMib3FyBaseFragment;

/* loaded from: classes15.dex */
public class AudiMib3FyV2FragmentTwo extends AudiMib3FyBaseFragment implements View.OnKeyListener {
    public static final String TAG = AudiMib3FyV2FragmentTwo.class.getSimpleName();
    public static AudiMib3FyV2TwoDataCls bindingTwo;
    private AnimationDrawable animDrawApps;
    private AnimationDrawable animDrawBrowser;
    private AnimationDrawable animDrawDashboard;
    private AnimationDrawable animDrawDvr;
    private AnimationDrawable animDrawFile;
    private View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.audimib3fyv2.AudiMib3FyV2FragmentTwo.1
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            AudiMib3FyV2FragmentTwo.this.viewModel.clearLastSel();
            switch (v.getId()) {
                case C0899R.C0901id.app_itemview /* 2131296344 */:
                    if (hasFocus) {
                        if (!AudiMib3FyV2FragmentTwo.this.animDrawApps.isRunning()) {
                            AudiMib3FyV2FragmentTwo.this.animDrawApps.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FyV2FragmentTwo.this.animDrawApps.isRunning()) {
                        AudiMib3FyV2FragmentTwo.this.animDrawApps.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.browser_itemview /* 2131296677 */:
                    if (hasFocus) {
                        if (!AudiMib3FyV2FragmentTwo.this.animDrawBrowser.isRunning()) {
                            AudiMib3FyV2FragmentTwo.this.animDrawBrowser.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FyV2FragmentTwo.this.animDrawBrowser.isRunning()) {
                        AudiMib3FyV2FragmentTwo.this.animDrawBrowser.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
                    if (hasFocus) {
                        if (!AudiMib3FyV2FragmentTwo.this.animDrawDashboard.isRunning()) {
                            AudiMib3FyV2FragmentTwo.this.animDrawDashboard.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FyV2FragmentTwo.this.animDrawDashboard.isRunning()) {
                        AudiMib3FyV2FragmentTwo.this.animDrawDashboard.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.dvr_itemview /* 2131296851 */:
                    if (hasFocus) {
                        if (!AudiMib3FyV2FragmentTwo.this.animDrawDvr.isRunning()) {
                            AudiMib3FyV2FragmentTwo.this.animDrawDvr.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FyV2FragmentTwo.this.animDrawDvr.isRunning()) {
                        AudiMib3FyV2FragmentTwo.this.animDrawDvr.stop();
                        return;
                    } else {
                        return;
                    }
                case C0899R.C0901id.file_itemview /* 2131296874 */:
                    if (hasFocus) {
                        if (!AudiMib3FyV2FragmentTwo.this.animDrawFile.isRunning()) {
                            AudiMib3FyV2FragmentTwo.this.animDrawFile.start();
                            return;
                        }
                        return;
                    } else if (AudiMib3FyV2FragmentTwo.this.animDrawFile.isRunning()) {
                        AudiMib3FyV2FragmentTwo.this.animDrawFile.stop();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.audimib3fyv2.AudiMib3FyV2FragmentTwo.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.app_itemview /* 2131296344 */:
                    BcVieModel unused = AudiMib3FyV2FragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FyV2FragmentTwo.bindingTwo.appItemview;
                    AudiMib3FyV2FragmentTwo.setItemSelected(AudiMib3FyV2FragmentTwo.bindingTwo.appItemview);
                    AudiMib3FyV2FragmentTwo.this.viewModel.openApps(v);
                    return;
                case C0899R.C0901id.browser_itemview /* 2131296677 */:
                    BcVieModel unused2 = AudiMib3FyV2FragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FyV2FragmentTwo.bindingTwo.browserItemview;
                    AudiMib3FyV2FragmentTwo.setItemSelected(AudiMib3FyV2FragmentTwo.bindingTwo.browserItemview);
                    AudiMib3FyV2FragmentTwo.this.viewModel.openBrowser(v);
                    return;
                case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
                    BcVieModel unused3 = AudiMib3FyV2FragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FyV2FragmentTwo.bindingTwo.dashboardItemview;
                    AudiMib3FyV2FragmentTwo.setItemSelected(AudiMib3FyV2FragmentTwo.bindingTwo.dashboardItemview);
                    AudiMib3FyV2FragmentTwo.this.viewModel.openDashboard(v);
                    return;
                case C0899R.C0901id.dvr_itemview /* 2131296851 */:
                    BcVieModel unused4 = AudiMib3FyV2FragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FyV2FragmentTwo.bindingTwo.dvrItemview;
                    AudiMib3FyV2FragmentTwo.setItemSelected(AudiMib3FyV2FragmentTwo.bindingTwo.dvrItemview);
                    AudiMib3FyV2FragmentTwo.this.viewModel.openDvr(v);
                    return;
                case C0899R.C0901id.file_itemview /* 2131296874 */:
                    BcVieModel unused5 = AudiMib3FyV2FragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3FyV2FragmentTwo.bindingTwo.fileItemview;
                    AudiMib3FyV2FragmentTwo.setItemSelected(AudiMib3FyV2FragmentTwo.bindingTwo.fileItemview);
                    AudiMib3FyV2FragmentTwo.this.viewModel.openFileManager(v);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.wits.ksw.launcher.view.audimib3fy.AudiMib3FyBaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3FyV2TwoDataCls audiMib3FyV2TwoDataCls = (AudiMib3FyV2TwoDataCls) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_audi_mib3_fy_v2_two, null, false);
        bindingTwo = audiMib3FyV2TwoDataCls;
        return audiMib3FyV2TwoDataCls.getRoot();
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
        this.animDrawApps = (AnimationDrawable) bindingTwo.appItemview.getDrawable();
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
        Log.d(TAG, "onResume() 2222222222 ");
        this.viewModel.refreshLastSel();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i(TAG, "onKey: " + keyCode);
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
                    setItemSelected(bindingTwo.browserItemview);
                    bindingTwo.browserItemview.requestFocus();
                    return true;
                } else if (v == bindingTwo.browserItemview) {
                    setItemSelected(bindingTwo.fileItemview);
                } else if (v == bindingTwo.fileItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.dvrItemview);
                } else if (v == bindingTwo.dvrItemview && this.mainActivity.viewpagerAudiMib3Fy.getCurrentItem() != 0) {
                    this.mainActivity.viewpagerAudiMib3Fy.setCurrentItem(0);
                    AudiMib3FyV2FragmentOne.bindingOne.setItemview.requestFocus();
                    isSmooth = true;
                    return true;
                }
            }
        }
        return false;
    }
}

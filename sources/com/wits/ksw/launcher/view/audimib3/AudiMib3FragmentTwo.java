package com.wits.ksw.launcher.view.audimib3;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3TwoDataCls;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class AudiMib3FragmentTwo extends AudiMib3BaseFragment implements View.OnKeyListener {
    public static final String TAG = "AudiMib3FragmentTwo - Two";
    public static AudiMib3TwoDataCls bindingTwo;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawBrowser;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawDashboard;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawDvr;
    /* access modifiers changed from: private */
    public AnimationDrawable animDrawFile;
    private View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.browser_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwo.this.animDrawBrowser.isRunning()) {
                            AudiMib3FragmentTwo.this.animDrawBrowser.start();
                            AudiMib3FragmentTwo.bindingTwo.browserItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_browser_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwo.this.animDrawBrowser.isRunning()) {
                        AudiMib3FragmentTwo.this.animDrawBrowser.stop();
                        AudiMib3FragmentTwo.bindingTwo.browserItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_browser));
                        return;
                    } else {
                        return;
                    }
                case R.id.dashboard_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwo.this.animDrawDashboard.isRunning()) {
                            AudiMib3FragmentTwo.this.animDrawDashboard.start();
                            AudiMib3FragmentTwo.bindingTwo.dashboardItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_dashboard_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwo.this.animDrawDashboard.isRunning()) {
                        AudiMib3FragmentTwo.this.animDrawDashboard.stop();
                        AudiMib3FragmentTwo.bindingTwo.dashboardItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_dashboard));
                        return;
                    } else {
                        return;
                    }
                case R.id.dvr_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwo.this.animDrawDvr.isRunning()) {
                            AudiMib3FragmentTwo.this.animDrawDvr.start();
                            AudiMib3FragmentTwo.bindingTwo.dvrItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_dvr_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwo.this.animDrawDvr.isRunning()) {
                        AudiMib3FragmentTwo.this.animDrawDvr.stop();
                        AudiMib3FragmentTwo.bindingTwo.dvrItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_dvr));
                        return;
                    } else {
                        return;
                    }
                case R.id.file_itemview:
                    if (hasFocus) {
                        if (!AudiMib3FragmentTwo.this.animDrawFile.isRunning()) {
                            AudiMib3FragmentTwo.this.animDrawFile.start();
                            AudiMib3FragmentTwo.bindingTwo.fileItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_main_icon_file_n));
                            return;
                        }
                        return;
                    } else if (AudiMib3FragmentTwo.this.animDrawFile.isRunning()) {
                        AudiMib3FragmentTwo.this.animDrawFile.stop();
                        AudiMib3FragmentTwo.bindingTwo.fileItemview.setBackground(AudiMib3FragmentTwo.this.getResources().getDrawable(R.drawable.audi_mib3_ripple_file));
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
                case R.id.browser_itemview:
                    AudiMib3FragmentTwo.setItemSelected(AudiMib3FragmentTwo.bindingTwo.browserItemview);
                    AudiMib3FragmentTwo.this.viewModel.openBrowser(v);
                    return;
                case R.id.dashboard_itemview:
                    AudiMib3FragmentTwo.setItemSelected(AudiMib3FragmentTwo.bindingTwo.dashboardItemview);
                    AudiMib3FragmentTwo.this.viewModel.openDashboard(v);
                    return;
                case R.id.dvr_itemview:
                    AudiMib3FragmentTwo.setItemSelected(AudiMib3FragmentTwo.bindingTwo.dvrItemview);
                    AudiMib3FragmentTwo.this.viewModel.openDvr(v);
                    return;
                case R.id.file_itemview:
                    AudiMib3FragmentTwo.setItemSelected(AudiMib3FragmentTwo.bindingTwo.fileItemview);
                    AudiMib3FragmentTwo.this.viewModel.openFileManager(v);
                    return;
                default:
                    return;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3TwoDataCls audiMib3TwoDataCls = (AudiMib3TwoDataCls) DataBindingUtil.inflate(inflater, R.layout.audi_mib3_fragment_two, (ViewGroup) null, false);
        bindingTwo = audiMib3TwoDataCls;
        return audiMib3TwoDataCls.getRoot();
    }

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
        bindingTwo.dvrItemview.setOnKeyListener(this);
        bindingTwo.dashboardItemview.setOnKeyListener(this);
        bindingTwo.fileItemview.setOnKeyListener(this);
        bindingTwo.browserItemview.setOnKeyListener(this);
        this.animDrawDvr = (AnimationDrawable) bindingTwo.dvrItemview.getDrawable();
        this.animDrawDashboard = (AnimationDrawable) bindingTwo.dashboardItemview.getDrawable();
        this.animDrawFile = (AnimationDrawable) bindingTwo.fileItemview.getDrawable();
        this.animDrawBrowser = (AnimationDrawable) bindingTwo.browserItemview.getDrawable();
        bindingTwo.dvrItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.dashboardItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.fileItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        bindingTwo.browserItemview.setOnFocusChangeListener(this.mFocusChangeListener);
        setDefaultSelected();
    }

    public static void setItemSelected(View view) {
        boolean z = true;
        bindingTwo.dvrItemview.setSelected(bindingTwo.dvrItemview == view);
        bindingTwo.dashboardItemview.setSelected(bindingTwo.dashboardItemview == view);
        bindingTwo.fileItemview.setSelected(bindingTwo.fileItemview == view);
        BenzMbuxItemView benzMbuxItemView = bindingTwo.browserItemview;
        if (bindingTwo.browserItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(bindingTwo.dvrItemview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i(TAG, "benz2021 onKey: " + keyCode);
            if (keyCode == 20 || keyCode == 22) {
                if (v == bindingTwo.dvrItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.fileItemview);
                } else if (v == bindingTwo.fileItemview) {
                    setItemSelected(bindingTwo.browserItemview);
                } else if (v == bindingTwo.browserItemview) {
                    setItemSelected(bindingTwo.browserItemview);
                    return true;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == bindingTwo.browserItemview) {
                    setItemSelected(bindingTwo.fileItemview);
                } else if (v == bindingTwo.fileItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.dvrItemview);
                } else if (v == bindingTwo.dvrItemview && this.mainActivity.viewpagerAudiMib3.getCurrentItem() != 0) {
                    this.mainActivity.viewpagerAudiMib3.setCurrentItem(0);
                    AudiMib3FragmentOne.bindingOne.setItemview.requestFocus();
                    AudiMib3FragmentOne.setItemSelected(AudiMib3FragmentOne.bindingOne.setItemview);
                    isSmooth = true;
                    return true;
                }
            }
        }
        return false;
    }
}

package com.wits.ksw.launcher.view.benzntg6fyv2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsV2;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment;

public class BenzNtg6FyFragmentTwoV2 extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzNtg6FyFragmentTwoV2 - Two";
    public BenzNtg6FyFragmentTwoClsV2 binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BenzNtg6FyFragmentTwoClsV2 benzNtg6FyFragmentTwoClsV2 = (BenzNtg6FyFragmentTwoClsV2) DataBindingUtil.inflate(inflater, R.layout.benz_ntg6_fy_fragment_two_v2, (ViewGroup) null, false);
        this.binding = benzNtg6FyFragmentTwoClsV2;
        return benzNtg6FyFragmentTwoClsV2.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzNtg6FyFragmentTwoV2.this.mainActivity.viewpagerBenzNtg6Fy != null && BenzNtg6FyFragmentTwoV2.this.mainActivity.viewpagerBenzNtg6Fy.getCurrentItem() != 1) {
                    BenzNtg6FyFragmentTwoV2.this.mainActivity.viewpagerBenzNtg6Fy.setCurrentItem(1);
                    BenzNtg6FyFragmentTwoV2.this.setItemSelected(v);
                }
            }
        });
        this.binding.videoItemview.setOnClickListener(this);
        this.binding.videoIv1.setOnClickListener(this);
        this.binding.videoIv2.setOnClickListener(this);
        this.binding.appItemview.setOnClickListener(this);
        this.binding.appIv1.setOnClickListener(this);
        this.binding.appIv2.setOnClickListener(this);
        this.binding.setItemview.setOnClickListener(this);
        this.binding.setIv1.setOnClickListener(this);
        this.binding.setIv2.setOnClickListener(this);
        this.binding.dashboardItemview.setOnClickListener(this);
        this.binding.dashboardIv1.setOnClickListener(this);
        this.binding.dashboardIv2.setOnClickListener(this);
        this.binding.phonelinkIv1.setOnClickListener(this);
        this.binding.phonelinkIv2.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnClickListener(this);
        this.binding.videoItemview.setOnKeyListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.setItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
        this.binding.setItemview.setSelected(this.binding.setItemview == view);
        this.binding.dashboardItemview.setSelected(this.binding.dashboardItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.phonelinkItemview;
        if (this.binding.phonelinkItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.videoItemview);
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
        Log.i(TAG, "benz2021 onKey: " + keyCode);
        if (keyCode == 20 || keyCode == 22) {
            if (v == this.binding.setItemview) {
                setItemSelected(this.binding.videoItemview);
                return false;
            } else if (v == this.binding.videoItemview) {
                setItemSelected(this.binding.appItemview);
                return false;
            } else if (v == this.binding.appItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                this.binding.phonelinkItemview.requestFocus();
                return true;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.dashboardItemview);
                return false;
            } else if (v != this.binding.dashboardItemview) {
                return false;
            } else {
                setItemSelected(this.binding.dashboardItemview);
                return false;
            }
        } else if (keyCode != 19 && keyCode != 21) {
            return false;
        } else {
            if (v == this.binding.dashboardItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return false;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.appItemview);
                this.binding.appItemview.requestFocus();
                return true;
            } else if (v == this.binding.appItemview) {
                setItemSelected(this.binding.videoItemview);
                return false;
            } else if (v != this.binding.videoItemview) {
                return false;
            } else {
                setItemSelected(this.binding.setItemview);
                return false;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_itemview /*2131296342*/:
            case R.id.app_iv1 /*2131296344*/:
            case R.id.app_iv2 /*2131296345*/:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case R.id.dashboard_itemview /*2131296776*/:
            case R.id.dashboard_iv1 /*2131296778*/:
            case R.id.dashboard_iv2 /*2131296779*/:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case R.id.phonelink_itemview /*2131297405*/:
            case R.id.phonelink_iv1 /*2131297407*/:
            case R.id.phonelink_iv2 /*2131297408*/:
                this.viewModel.openShouJiHuLian(v);
                setItemSelected(this.binding.phonelinkItemview);
                return;
            case R.id.set_itemview /*2131297701*/:
            case R.id.set_iv1 /*2131297703*/:
            case R.id.set_iv2 /*2131297704*/:
                this.viewModel.openSettings(v);
                setItemSelected(this.binding.setItemview);
                return;
            case R.id.video_itemview /*2131297999*/:
            case R.id.video_iv1 /*2131298001*/:
            case R.id.video_iv2 /*2131298002*/:
                this.viewModel.openVideoMulti(v);
                setItemSelected(this.binding.videoItemview);
                return;
            default:
                return;
        }
    }
}

package com.wits.ksw.launcher.view.benzntg6fy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment;

public class BenzNtg6FyFragmentTwo extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzNtg6FyFragmentTwo - Two";
    public BenzNtg6FyFragmentTwoCls binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BenzNtg6FyFragmentTwoCls benzNtg6FyFragmentTwoCls = (BenzNtg6FyFragmentTwoCls) DataBindingUtil.inflate(inflater, R.layout.benz_ntg6_fy_fragment_two, (ViewGroup) null, false);
        this.binding = benzNtg6FyFragmentTwoCls;
        return benzNtg6FyFragmentTwoCls.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.videoItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzNtg6FyFragmentTwo.this.mainActivity.viewpagerBenzNtg6Fy != null && BenzNtg6FyFragmentTwo.this.mainActivity.viewpagerBenzNtg6Fy.getCurrentItem() != 1) {
                    BenzNtg6FyFragmentTwo.this.mainActivity.viewpagerBenzNtg6Fy.setCurrentItem(1);
                    BenzNtg6FyFragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.videoItemview.setOnClickListener(this);
        this.binding.videoIv1.setOnClickListener(this);
        this.binding.videoIv2.setOnClickListener(this);
        this.binding.appItemview.setOnClickListener(this);
        this.binding.appIv1.setOnClickListener(this);
        this.binding.appIv2.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnClickListener(this);
        this.binding.phoneIv1.setOnClickListener(this);
        this.binding.phoneIv2.setOnClickListener(this);
        this.binding.dashboardItemview.setOnClickListener(this);
        this.binding.dashboardIv1.setOnClickListener(this);
        this.binding.dashboardIv2.setOnClickListener(this);
        this.binding.drvIv1.setOnClickListener(this);
        this.binding.drvIv2.setOnClickListener(this);
        this.binding.dvrItemview.setOnClickListener(this);
        this.binding.videoItemview.setOnKeyListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        this.binding.dvrItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
        this.binding.phonelinkItemview.setSelected(this.binding.phonelinkItemview == view);
        this.binding.dashboardItemview.setSelected(this.binding.dashboardItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.dvrItemview;
        if (this.binding.dvrItemview != view) {
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
            if (v == this.binding.videoItemview) {
                setItemSelected(this.binding.appItemview);
                return false;
            } else if (v == this.binding.appItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return false;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.dashboardItemview);
                return false;
            } else if (v != this.binding.dashboardItemview) {
                return false;
            } else {
                setItemSelected(this.binding.dvrItemview);
                return false;
            }
        } else if (keyCode != 19 && keyCode != 21) {
            return false;
        } else {
            if (v == this.binding.dvrItemview) {
                setItemSelected(this.binding.dashboardItemview);
                return false;
            } else if (v == this.binding.dashboardItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return false;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.appItemview);
                return false;
            } else if (v != this.binding.appItemview) {
                return false;
            } else {
                setItemSelected(this.binding.videoItemview);
                return false;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_itemview /*2131296335*/:
            case R.id.app_iv1 /*2131296337*/:
            case R.id.app_iv2 /*2131296338*/:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case R.id.dashboard_itemview /*2131296616*/:
            case R.id.dashboard_iv1 /*2131296618*/:
            case R.id.dashboard_iv2 /*2131296619*/:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case R.id.drv_iv1 /*2131296656*/:
            case R.id.drv_iv2 /*2131296657*/:
            case R.id.dvr_itemview /*2131296658*/:
                this.viewModel.openDvr(v);
                setItemSelected(this.binding.dvrItemview);
                return;
            case R.id.phone_iv1 /*2131297138*/:
            case R.id.phone_iv2 /*2131297139*/:
            case R.id.phonelink_itemview /*2131297141*/:
                this.viewModel.openPhoneLink2021(v);
                setItemSelected(this.binding.phonelinkItemview);
                return;
            case R.id.video_itemview /*2131297672*/:
            case R.id.video_iv1 /*2131297674*/:
            case R.id.video_iv2 /*2131297675*/:
                this.viewModel.openVideoMulti(v);
                setItemSelected(this.binding.videoItemview);
                return;
            default:
                return;
        }
    }
}

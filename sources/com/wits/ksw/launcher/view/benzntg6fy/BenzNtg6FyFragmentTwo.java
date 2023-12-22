package com.wits.ksw.launcher.view.benzntg6fy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment;

/* loaded from: classes8.dex */
public class BenzNtg6FyFragmentTwo extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzNtg6FyFragmentTwo - Two";
    public BenzNtg6FyFragmentTwoCls binding;

    @Override // com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BenzNtg6FyFragmentTwoCls benzNtg6FyFragmentTwoCls = (BenzNtg6FyFragmentTwoCls) DataBindingUtil.inflate(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_two, null, false);
        this.binding = benzNtg6FyFragmentTwoCls;
        return benzNtg6FyFragmentTwoCls.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.videoItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyFragmentTwo.1
            @Override // android.view.View.OnFocusChangeListener
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
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
        this.binding.phonelinkItemview.setSelected(this.binding.phonelinkItemview == view);
        this.binding.dashboardItemview.setSelected(this.binding.dashboardItemview == view);
        this.binding.dvrItemview.setSelected(this.binding.dvrItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.videoItemview);
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
                } else if (v == this.binding.dashboardItemview) {
                    setItemSelected(this.binding.dvrItemview);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == this.binding.dvrItemview) {
                    setItemSelected(this.binding.dashboardItemview);
                    return false;
                } else if (v == this.binding.dashboardItemview) {
                    setItemSelected(this.binding.phonelinkItemview);
                    return false;
                } else if (v == this.binding.phonelinkItemview) {
                    setItemSelected(this.binding.appItemview);
                    return false;
                } else if (v == this.binding.appItemview) {
                    setItemSelected(this.binding.videoItemview);
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.app_itemview /* 2131296344 */:
            case C0899R.C0901id.app_iv1 /* 2131296347 */:
            case C0899R.C0901id.app_iv2 /* 2131296348 */:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
            case C0899R.C0901id.dashboard_iv1 /* 2131296807 */:
            case C0899R.C0901id.dashboard_iv2 /* 2131296808 */:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case C0899R.C0901id.drv_iv1 /* 2131296848 */:
            case C0899R.C0901id.drv_iv2 /* 2131296849 */:
            case C0899R.C0901id.dvr_itemview /* 2131296851 */:
                this.viewModel.openDvr(v);
                setItemSelected(this.binding.dvrItemview);
                return;
            case C0899R.C0901id.phone_iv1 /* 2131297432 */:
            case C0899R.C0901id.phone_iv2 /* 2131297433 */:
            case C0899R.C0901id.phonelink_itemview /* 2131297437 */:
                this.viewModel.openPhoneLink2021(v);
                setItemSelected(this.binding.phonelinkItemview);
                return;
            case C0899R.C0901id.video_itemview /* 2131298051 */:
            case C0899R.C0901id.video_iv1 /* 2131298053 */:
            case C0899R.C0901id.video_iv2 /* 2131298054 */:
                this.viewModel.openVideoMulti(v);
                setItemSelected(this.binding.videoItemview);
                return;
            default:
                return;
        }
    }
}

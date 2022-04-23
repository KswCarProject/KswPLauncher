package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.Benz2021FragmentThree;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class BenzMbux2021FragmentThree extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzMbux2021BaseFragment - Three";
    public Benz2021FragmentThree binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021FragmentThree benz2021FragmentThree = (Benz2021FragmentThree) DataBindingUtil.inflate(inflater, R.layout.benz_mbux_2021_fragment_three, (ViewGroup) null, false);
        this.binding = benz2021FragmentThree;
        return benz2021FragmentThree.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.phonelinkItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(2);
                    BenzMbux2021FragmentThree.this.setItemSelected(v);
                }
            }
        });
        this.binding.dashboardItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(2);
                    BenzMbux2021FragmentThree.this.setItemSelected(v);
                }
            }
        });
        this.binding.ivApps1.setOnClickListener(this);
        this.binding.ivApps2.setOnClickListener(this);
        this.binding.ivDash1.setOnClickListener(this);
        this.binding.ivDash2.setOnClickListener(this);
        this.binding.ivPhone1.setOnClickListener(this);
        this.binding.ivPhone2.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnClickListener(this);
        this.binding.appItemview.setOnClickListener(this);
        this.binding.dashboardItemview.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.phonelinkItemview.setSelected(this.binding.phonelinkItemview == view);
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.dashboardItemview;
        if (this.binding.dashboardItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.phonelinkItemview);
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
            if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.appItemview);
                return false;
            } else if (v != this.binding.appItemview) {
                return false;
            } else {
                setItemSelected(this.binding.dashboardItemview);
                return false;
            }
        } else if (keyCode != 19 && keyCode != 21) {
            return false;
        } else {
            if (v == this.binding.dashboardItemview) {
                setItemSelected(this.binding.appItemview);
                return false;
            } else if (v != this.binding.appItemview) {
                return false;
            } else {
                setItemSelected(this.binding.phonelinkItemview);
                return false;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_itemview /*2131296335*/:
            case R.id.iv_apps1 /*2131296883*/:
            case R.id.iv_apps2 /*2131296884*/:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case R.id.dashboard_itemview /*2131296616*/:
            case R.id.iv_dash1 /*2131296903*/:
            case R.id.iv_dash2 /*2131296904*/:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case R.id.iv_phone1 /*2131296941*/:
            case R.id.iv_phone2 /*2131296942*/:
            case R.id.phonelink_itemview /*2131297141*/:
                if (!Build.DISPLAY.contains("8937")) {
                    this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                } else if (Settings.System.getInt(this.mainActivity.getContentResolver(), "speed_play_switch", 1) == 2) {
                    this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                } else {
                    this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                }
                setItemSelected(this.binding.phonelinkItemview);
                return;
            default:
                return;
        }
    }
}

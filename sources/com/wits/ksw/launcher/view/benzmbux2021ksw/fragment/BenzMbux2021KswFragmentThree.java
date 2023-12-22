package com.wits.ksw.launcher.view.benzmbux2021ksw.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.Benz2021KswFragmentThree;
import com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment;

/* loaded from: classes14.dex */
public class BenzMbux2021KswFragmentThree extends BenzMbux2021KswBaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzMbux2021KswFragmentTwo - Two";
    public Benz2021KswFragmentThree binding;

    @Override // com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021KswFragmentThree benz2021KswFragmentThree = (Benz2021KswFragmentThree) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_three, null, false);
        this.binding = benz2021KswFragmentThree;
        return benz2021KswFragmentThree.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.appItemview.setOnClickListener(this);
        this.binding.ivApps1.setOnClickListener(this);
        this.binding.ivApps2.setOnClickListener(this);
        this.binding.ivPhone1.setOnClickListener(this);
        this.binding.ivPhone2.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnClickListener(this);
        this.binding.ivDash1.setOnClickListener(this);
        this.binding.ivDash2.setOnClickListener(this);
        this.binding.dashboardItemview.setOnClickListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.appItemview);
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
            Log.i("BenzMbux2021KswFragmentTwo - Two", "benz2021 onKey: " + keyCode);
            if (keyCode == 20 || keyCode == 22) {
                if (v == this.binding.appItemview) {
                    setItemSelected(this.binding.phonelinkItemview);
                    return false;
                } else if (v == this.binding.phonelinkItemview) {
                    setItemSelected(this.binding.dashboardItemview);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == this.binding.dashboardItemview) {
                    setItemSelected(this.binding.phonelinkItemview);
                    return false;
                } else if (v == this.binding.phonelinkItemview) {
                    setItemSelected(this.binding.appItemview);
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (keyCode != 66) {
            if (v == this.binding.appItemview) {
                setItemSelected(this.binding.appItemview);
                return true;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return true;
            } else if (v == this.binding.dashboardItemview) {
                setItemSelected(this.binding.dashboardItemview);
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.app_itemview /* 2131296344 */:
            case C0899R.C0901id.iv_apps1 /* 2131297100 */:
            case C0899R.C0901id.iv_apps2 /* 2131297101 */:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
            case C0899R.C0901id.iv_dash1 /* 2131297124 */:
            case C0899R.C0901id.iv_dash2 /* 2131297125 */:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case C0899R.C0901id.iv_phone1 /* 2131297175 */:
            case C0899R.C0901id.iv_phone2 /* 2131297176 */:
            case C0899R.C0901id.phonelink_itemview /* 2131297437 */:
                boolean flag = Build.DISPLAY.contains("8937");
                if (!flag) {
                    this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                } else {
                    int speed_play_switch = Settings.System.getInt(this.mainActivity.getContentResolver(), "speed_play_switch", 1);
                    if (speed_play_switch == 2) {
                        this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    } else {
                        this.viewModel.openApp(this.mainActivity.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    }
                }
                setItemSelected(this.binding.phonelinkItemview);
                return;
            default:
                return;
        }
    }
}

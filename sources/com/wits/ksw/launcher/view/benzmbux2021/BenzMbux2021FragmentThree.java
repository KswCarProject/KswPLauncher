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
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.Benz2021FragmentThree;

/* loaded from: classes4.dex */
public class BenzMbux2021FragmentThree extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzMbux2021BaseFragment - Three";
    public Benz2021FragmentThree binding;

    @Override // com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021FragmentThree benz2021FragmentThree = (Benz2021FragmentThree) DataBindingUtil.inflate(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_three, null, false);
        this.binding = benz2021FragmentThree;
        return benz2021FragmentThree.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.phonelinkItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentThree.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentThree.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(2);
                    BenzMbux2021FragmentThree.this.setItemSelected(v);
                }
            }
        });
        this.binding.dashboardItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentThree.2
            @Override // android.view.View.OnFocusChangeListener
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
        this.binding.phonelinkItemview.setSelected(this.binding.phonelinkItemview == view);
        this.binding.appItemview.setSelected(this.binding.appItemview == view);
        this.binding.dashboardItemview.setSelected(this.binding.dashboardItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.phonelinkItemview);
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
                if (v == this.binding.phonelinkItemview) {
                    setItemSelected(this.binding.appItemview);
                    return false;
                } else if (v == this.binding.appItemview) {
                    setItemSelected(this.binding.dashboardItemview);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == this.binding.dashboardItemview) {
                    setItemSelected(this.binding.appItemview);
                    return false;
                } else if (v == this.binding.appItemview) {
                    setItemSelected(this.binding.phonelinkItemview);
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (keyCode != 66) {
            Log.i(TAG, "benz2021 onKey: " + keyCode);
            if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return true;
            } else if (v == this.binding.appItemview) {
                setItemSelected(this.binding.appItemview);
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

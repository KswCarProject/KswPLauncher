package com.wits.ksw.launcher.view.benzmbux2021ksw.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.Benz2021KswFragmentTwo;
import com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment;

/* loaded from: classes14.dex */
public class BenzMbux2021KswFragmentTwo extends BenzMbux2021KswBaseFragment implements View.OnKeyListener, View.OnClickListener, View.OnFocusChangeListener {
    public static final String TAG = "BenzMbux2021KswFragmentTwo - Two";
    public Benz2021KswFragmentTwo binding;
    private int height;
    private int width;

    @Override // com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021KswFragmentTwo benz2021KswFragmentTwo = (Benz2021KswFragmentTwo) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_two, null, false);
        this.binding = benz2021KswFragmentTwo;
        return benz2021KswFragmentTwo.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.width = dm.widthPixels;
        this.height = dm.heightPixels;
        this.binding.videoItemview.setOnFocusChangeListener(this);
        this.binding.dvrItemview.setOnFocusChangeListener(this);
        this.binding.appItemview.setOnClickListener(this);
        this.binding.ivApps1.setOnClickListener(this);
        this.binding.ivApps2.setOnClickListener(this);
        this.binding.ivPhone1.setOnClickListener(this);
        this.binding.ivPhone2.setOnClickListener(this);
        this.binding.phonelinkItemview.setOnClickListener(this);
        this.binding.ivDash1.setOnClickListener(this);
        this.binding.ivDash2.setOnClickListener(this);
        this.binding.dashboardItemview.setOnClickListener(this);
        this.binding.dvrItemview.setOnClickListener(this);
        this.binding.ivDvr1.setOnClickListener(this);
        this.binding.ivDvr2.setOnClickListener(this);
        this.binding.videoItemview.setOnClickListener(this);
        this.binding.ivVideo1.setOnClickListener(this);
        this.binding.ivVideo2.setOnClickListener(this);
        this.binding.carItemview.setOnClickListener(this);
        this.binding.ivCar1.setOnClickListener(this);
        this.binding.ivCar2.setOnClickListener(this);
        this.binding.setItemview.setOnClickListener(this);
        this.binding.ivSet1.setOnClickListener(this);
        this.binding.ivSet2.setOnClickListener(this);
        this.binding.carItemview.setOnKeyListener(this);
        this.binding.setItemview.setOnKeyListener(this);
        this.binding.videoItemview.setOnKeyListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        this.binding.dvrItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        if (this.width == 1280 && this.height == 720) {
            this.binding.carItemview.setSelected(this.binding.carItemview == view);
        } else {
            this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        }
    }

    public void setDefaultSelected() {
        try {
            if (this.width == 1280 && this.height == 720) {
                setItemSelected(this.binding.carItemview);
            } else {
                setItemSelected(this.binding.videoItemview);
            }
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
                if (this.width == 1280 && this.height == 720) {
                    if (v == this.binding.carItemview) {
                        setItemSelected(this.binding.setItemview);
                        return false;
                    } else if (v == this.binding.setItemview) {
                        setItemSelected(this.binding.videoItemview);
                        return false;
                    } else {
                        return false;
                    }
                } else if (v == this.binding.videoItemview) {
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
                if (this.width == 1280 && this.height == 720) {
                    if (v == this.binding.videoItemview) {
                        setItemSelected(this.binding.setItemview);
                        return false;
                    } else if (v == this.binding.setItemview) {
                        setItemSelected(this.binding.carItemview);
                        return false;
                    } else {
                        return false;
                    }
                } else if (v == this.binding.dvrItemview) {
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
        } else if (keyCode != 66) {
            if (this.width == 1280 && this.height == 720) {
                if (v == this.binding.carItemview) {
                    setItemSelected(this.binding.carItemview);
                    return true;
                } else if (v == this.binding.setItemview) {
                    setItemSelected(this.binding.setItemview);
                    return true;
                } else if (v == this.binding.videoItemview) {
                    setItemSelected(this.binding.videoItemview);
                    return true;
                } else {
                    return true;
                }
            } else if (v == this.binding.videoItemview) {
                setItemSelected(this.binding.videoItemview);
                return true;
            } else if (v == this.binding.appItemview) {
                setItemSelected(this.binding.appItemview);
                return true;
            } else if (v == this.binding.phonelinkItemview) {
                setItemSelected(this.binding.phonelinkItemview);
                return true;
            } else if (v == this.binding.dashboardItemview) {
                setItemSelected(this.binding.dashboardItemview);
                return true;
            } else if (v == this.binding.dvrItemview) {
                setItemSelected(this.binding.dvrItemview);
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
            case C0899R.C0901id.car_itemview /* 2131296719 */:
            case C0899R.C0901id.iv_car1 /* 2131297118 */:
            case C0899R.C0901id.iv_car2 /* 2131297119 */:
                this.viewModel.openCar(v);
                setItemSelected(this.binding.carItemview);
                return;
            case C0899R.C0901id.dashboard_itemview /* 2131296804 */:
            case C0899R.C0901id.iv_dash1 /* 2131297124 */:
            case C0899R.C0901id.iv_dash2 /* 2131297125 */:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case C0899R.C0901id.dvr_itemview /* 2131296851 */:
            case C0899R.C0901id.iv_dvr1 /* 2131297129 */:
            case C0899R.C0901id.iv_dvr2 /* 2131297130 */:
                this.viewModel.openDvr(v);
                setItemSelected(this.binding.dvrItemview);
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
            case C0899R.C0901id.iv_set1 /* 2131297177 */:
            case C0899R.C0901id.iv_set2 /* 2131297178 */:
            case C0899R.C0901id.set_itemview /* 2131297748 */:
                this.viewModel.openSettings(v);
                setItemSelected(this.binding.setItemview);
                return;
            case C0899R.C0901id.iv_video1 /* 2131297187 */:
            case C0899R.C0901id.iv_video2 /* 2131297188 */:
            case C0899R.C0901id.video_itemview /* 2131298051 */:
                this.viewModel.openVideoMulti(v);
                setItemSelected(this.binding.videoItemview);
                return;
            default:
                return;
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case C0899R.C0901id.dvr_itemview /* 2131296851 */:
            case C0899R.C0901id.set_itemview /* 2131297748 */:
            case C0899R.C0901id.video_itemview /* 2131298051 */:
                if (hasFocus && this.mainActivity.viewPagerBenzMbux2021Ksw != null && this.mainActivity.viewPagerBenzMbux2021Ksw.getCurrentItem() != 1) {
                    this.mainActivity.viewPagerBenzMbux2021Ksw.setCurrentItem(1);
                    setItemSelected(v);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

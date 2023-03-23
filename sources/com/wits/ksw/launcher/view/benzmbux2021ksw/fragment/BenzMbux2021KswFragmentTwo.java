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
import com.wits.ksw.R;
import com.wits.ksw.databinding.Benz2021KswFragmentTwo;
import com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment;

public class BenzMbux2021KswFragmentTwo extends BenzMbux2021KswBaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzMbux2021KswFragmentTwo - Two";
    public Benz2021KswFragmentTwo binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021KswFragmentTwo benz2021KswFragmentTwo = (Benz2021KswFragmentTwo) DataBindingUtil.inflate(inflater, R.layout.fragment_benz_mbux2021_ksw_two, (ViewGroup) null, false);
        this.binding = benz2021KswFragmentTwo;
        return benz2021KswFragmentTwo.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.videoItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw != null && BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw.getCurrentItem() != 1) {
                    BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw.setCurrentItem(1);
                    BenzMbux2021KswFragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.dvrItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw != null && BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw.getCurrentItem() != 1) {
                    BenzMbux2021KswFragmentTwo.this.mainActivity.viewPagerBenzMbux2021Ksw.setCurrentItem(1);
                    BenzMbux2021KswFragmentTwo.this.setItemSelected(v);
                }
            }
        });
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
        this.binding.videoItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswFragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021KswFragmentTwo benzMbux2021KswFragmentTwo = BenzMbux2021KswFragmentTwo.this;
                benzMbux2021KswFragmentTwo.setItemSelected(benzMbux2021KswFragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswFragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021KswFragmentTwo benzMbux2021KswFragmentTwo = BenzMbux2021KswFragmentTwo.this;
                benzMbux2021KswFragmentTwo.setItemSelected(benzMbux2021KswFragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswFragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021KswFragmentTwo benzMbux2021KswFragmentTwo = BenzMbux2021KswFragmentTwo.this;
                benzMbux2021KswFragmentTwo.setItemSelected(benzMbux2021KswFragmentTwo.binding.videoItemview);
            }
        });
        this.binding.videoItemview.setOnKeyListener(this);
        this.binding.appItemview.setOnKeyListener(this);
        this.binding.phonelinkItemview.setOnKeyListener(this);
        this.binding.dashboardItemview.setOnKeyListener(this);
        this.binding.dvrItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
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
        } else if (keyCode == 66) {
            return false;
        } else {
            if (v == this.binding.videoItemview) {
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
            } else if (v != this.binding.dvrItemview) {
                return true;
            } else {
                setItemSelected(this.binding.dvrItemview);
                return true;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_itemview /*2131296342*/:
            case R.id.iv_apps1 /*2131297068*/:
            case R.id.iv_apps2 /*2131297069*/:
                this.viewModel.openApps(v);
                setItemSelected(this.binding.appItemview);
                return;
            case R.id.dashboard_itemview /*2131296776*/:
            case R.id.iv_dash1 /*2131297092*/:
            case R.id.iv_dash2 /*2131297093*/:
                this.viewModel.openDashboard(v);
                setItemSelected(this.binding.dashboardItemview);
                return;
            case R.id.dvr_itemview /*2131296820*/:
            case R.id.iv_dvr1 /*2131297097*/:
            case R.id.iv_dvr2 /*2131297098*/:
                this.viewModel.openDvr(v);
                setItemSelected(this.binding.dvrItemview);
                return;
            case R.id.iv_phone1 /*2131297143*/:
            case R.id.iv_phone2 /*2131297144*/:
            case R.id.phonelink_itemview /*2131297405*/:
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

package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.R;
import com.wits.ksw.databinding.Benz2021FragmentOne;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BenzMbux2021FragmentOne extends BenzMbux2021BaseFragment implements View.OnKeyListener {
    public static final String TAG = "BenzMbux2021BaseFragment - One";
    public Benz2021FragmentOne binding;
    private BenzMbuxItemView itemClicked = null;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(BenzMbux2021FragmentOne.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021FragmentOne benz2021FragmentOne = (Benz2021FragmentOne) DataBindingUtil.inflate(inflater, R.layout.benz_mbux_2021_fragment_one, (ViewGroup) null, false);
        this.binding = benz2021FragmentOne;
        return benz2021FragmentOne.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.carItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentOne.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentOne.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 0) {
                    BenzMbux2021FragmentOne.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(0);
                    BenzMbux2021FragmentOne.this.setItemSelected(v);
                    BenzMbux2021FragmentOne.this.binding.carItemview.setSelected(true);
                }
            }
        });
        this.binding.btItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.btItemview);
                BenzMbux2021FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.ivBt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.btItemview);
                BenzMbux2021FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.ivBt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.btItemview);
                BenzMbux2021FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.naviItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.naviItemview);
                BenzMbux2021FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.ivNavi1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.naviItemview);
                BenzMbux2021FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.ivNavi2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.naviItemview);
                BenzMbux2021FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.carItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.carItemview);
                BenzMbux2021FragmentOne.this.viewModel.openCar(v);
            }
        });
        this.binding.ivCar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.carItemview);
                BenzMbux2021FragmentOne.this.viewModel.openCar(v);
            }
        });
        this.binding.ivCar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentOne benzMbux2021FragmentOne = BenzMbux2021FragmentOne.this;
                benzMbux2021FragmentOne.setItemSelected(benzMbux2021FragmentOne.binding.carItemview);
                BenzMbux2021FragmentOne.this.viewModel.openCar(v);
            }
        });
        this.binding.btItemview.setOnKeyListener(this);
        this.binding.naviItemview.setOnKeyListener(this);
        this.binding.carItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.btItemview.setSelected(this.binding.btItemview == view);
        this.binding.naviItemview.setSelected(this.binding.naviItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.carItemview;
        if (this.binding.carItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.btItemview);
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
                if (v == this.binding.btItemview) {
                    setItemSelected(this.binding.naviItemview);
                    return false;
                } else if (v != this.binding.naviItemview) {
                    return false;
                } else {
                    setItemSelected(this.binding.carItemview);
                    return false;
                }
            } else if (keyCode != 19 && keyCode != 21) {
                return false;
            } else {
                if (v == this.binding.carItemview) {
                    setItemSelected(this.binding.naviItemview);
                    return false;
                } else if (v != this.binding.naviItemview) {
                    return false;
                } else {
                    setItemSelected(this.binding.btItemview);
                    return false;
                }
            }
        } else if (keyCode == 66) {
            return false;
        } else {
            Log.i(TAG, "benz2021 onKey: " + keyCode);
            if (v == this.binding.btItemview) {
                setItemSelected(this.binding.btItemview);
                return true;
            } else if (v == this.binding.naviItemview) {
                setItemSelected(this.binding.naviItemview);
                return true;
            } else if (v != this.binding.carItemview) {
                return true;
            } else {
                setItemSelected(this.binding.carItemview);
                return true;
            }
        }
    }
}

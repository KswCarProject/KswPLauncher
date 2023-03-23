package com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment;

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
import com.wits.ksw.databinding.Benz2021KswV2FragmentOne;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021ksw.BenzMbux2021KswBaseFragment;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BenzMbux2021KswV2FragmentOne extends BenzMbux2021KswBaseFragment implements View.OnKeyListener {
    public static final String TAG = BenzMbux2021KswV2FragmentOne.class.getSimpleName();
    public Benz2021KswV2FragmentOne binding;
    private BenzMbuxItemView itemClicked = null;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(BenzMbux2021KswV2FragmentOne.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021KswV2FragmentOne benz2021KswV2FragmentOne = (Benz2021KswV2FragmentOne) DataBindingUtil.inflate(inflater, R.layout.fragment_benz_mbux2021_ksw_v2_one, (ViewGroup) null, false);
        this.binding = benz2021KswV2FragmentOne;
        return benz2021KswV2FragmentOne.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021KswV2FragmentOne.this.mainActivity.viewPagerBenzMbux2021Ksw != null && BenzMbux2021KswV2FragmentOne.this.mainActivity.viewPagerBenzMbux2021Ksw.getCurrentItem() != 0) {
                    BenzMbux2021KswV2FragmentOne.this.mainActivity.viewPagerBenzMbux2021Ksw.setCurrentItem(0);
                    BenzMbux2021KswV2FragmentOne.this.setItemSelected(v);
                    BenzMbux2021KswV2FragmentOne.this.binding.setItemview.setSelected(true);
                }
            }
        });
        this.binding.btItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.btItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.ivBt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.btItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.ivBt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.btItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openBtApp();
            }
        });
        this.binding.naviItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.naviItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.ivNavi1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.naviItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.ivNavi2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.naviItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openNaviApp(v);
            }
        });
        this.binding.weatherItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.weatherItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openWeatherApp(v);
            }
        });
        this.binding.ivWeather1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.weatherItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openWeatherApp(v);
            }
        });
        this.binding.ivWeather2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.weatherItemview);
                BenzMbux2021KswV2FragmentOne.this.viewModel.openWeatherApp(v);
            }
        });
        this.binding.musicItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.openMusicMulti(v);
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.musicItemview);
            }
        });
        this.binding.ivMusic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.btnMusicPrev();
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.musicItemview);
            }
        });
        this.binding.ivMusic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.btnMusicNext();
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.musicItemview);
            }
        });
        this.binding.setItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.openSettings(v);
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.setItemview);
            }
        });
        this.binding.ivSet1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.openSettings(v);
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.setItemview);
            }
        });
        this.binding.ivSet2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021KswV2FragmentOne.this.viewModel.openSettings(v);
                BenzMbux2021KswV2FragmentOne benzMbux2021KswV2FragmentOne = BenzMbux2021KswV2FragmentOne.this;
                benzMbux2021KswV2FragmentOne.setItemSelected(benzMbux2021KswV2FragmentOne.binding.setItemview);
            }
        });
        this.binding.btItemview.setOnKeyListener(this);
        this.binding.naviItemview.setOnKeyListener(this);
        this.binding.weatherItemview.setOnKeyListener(this);
        this.binding.musicItemview.setOnKeyListener(this);
        this.binding.setItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.btItemview.setSelected(this.binding.btItemview == view);
        this.binding.naviItemview.setSelected(this.binding.naviItemview == view);
        this.binding.weatherItemview.setSelected(this.binding.weatherItemview == view);
        this.binding.musicItemview.setSelected(this.binding.musicItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.setItemview;
        if (this.binding.setItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            Log.d(TAG, "setDefaultSelected: 进入第一页");
            setItemSelected(this.binding.naviItemview);
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
                if (v == this.binding.naviItemview) {
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v == this.binding.musicItemview) {
                    setItemSelected(this.binding.btItemview);
                    return false;
                } else if (v == this.binding.btItemview) {
                    setItemSelected(this.binding.weatherItemview);
                    return false;
                } else if (v != this.binding.weatherItemview) {
                    return false;
                } else {
                    setItemSelected(this.binding.setItemview);
                    return false;
                }
            } else if (keyCode != 19 && keyCode != 21) {
                return false;
            } else {
                if (v == this.binding.setItemview) {
                    setItemSelected(this.binding.weatherItemview);
                    return false;
                } else if (v == this.binding.weatherItemview) {
                    setItemSelected(this.binding.btItemview);
                    return false;
                } else if (v == this.binding.btItemview) {
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v != this.binding.musicItemview) {
                    return false;
                } else {
                    setItemSelected(this.binding.naviItemview);
                    return false;
                }
            }
        } else if (keyCode == 66) {
            return false;
        } else {
            if (v == this.binding.naviItemview) {
                setItemSelected(this.binding.naviItemview);
                return true;
            } else if (v == this.binding.musicItemview) {
                setItemSelected(this.binding.musicItemview);
                return true;
            } else if (v == this.binding.btItemview) {
                setItemSelected(this.binding.btItemview);
                return true;
            } else if (v == this.binding.weatherItemview) {
                setItemSelected(this.binding.weatherItemview);
                return true;
            } else if (v != this.binding.setItemview) {
                return true;
            } else {
                setItemSelected(this.binding.setItemview);
                return true;
            }
        }
    }
}

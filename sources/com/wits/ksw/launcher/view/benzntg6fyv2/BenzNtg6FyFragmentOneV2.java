package com.wits.ksw.launcher.view.benzntg6fyv2;

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
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsV2;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class BenzNtg6FyFragmentOneV2 extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzNtg6FyFragmentOneV2 - One";
    public BenzNtg6FyFragmentOneClsV2 binding;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(BenzNtg6FyFragmentOneV2.TAG, "onChange: topApp=" + topApp);
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
        BenzNtg6FyFragmentOneClsV2 benzNtg6FyFragmentOneClsV2 = (BenzNtg6FyFragmentOneClsV2) DataBindingUtil.inflate(inflater, R.layout.benz_ntg6_fy_fragment_one_v2, (ViewGroup) null, false);
        this.binding = benzNtg6FyFragmentOneClsV2;
        return benzNtg6FyFragmentOneClsV2.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.carItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzNtg6FyFragmentOneV2.this.mainActivity.viewpagerBenzNtg6Fy != null && BenzNtg6FyFragmentOneV2.this.mainActivity.viewpagerBenzNtg6Fy.getCurrentItem() != 0) {
                    BenzNtg6FyFragmentOneV2.this.mainActivity.viewpagerBenzNtg6Fy.setCurrentItem(0);
                    BenzNtg6FyFragmentOneV2.this.setItemSelected(v);
                }
            }
        });
        this.binding.naviItemview.setOnClickListener(this);
        this.binding.musicItemview.setOnClickListener(this);
        this.binding.btItemview.setOnClickListener(this);
        this.binding.weatherItemview.setOnClickListener(this);
        this.binding.carItemview.setOnClickListener(this);
        this.binding.naviIv1.setOnClickListener(this);
        this.binding.naviIv2.setOnClickListener(this);
        this.binding.musicIv1.setOnClickListener(this);
        this.binding.musicIv2.setOnClickListener(this);
        this.binding.btIv1.setOnClickListener(this);
        this.binding.btIv2.setOnClickListener(this);
        this.binding.carIv1.setOnClickListener(this);
        this.binding.carIv2.setOnClickListener(this);
        this.binding.weatherIv1.setOnClickListener(this);
        this.binding.weatherIv2.setOnClickListener(this);
        this.binding.btItemview.setOnKeyListener(this);
        this.binding.naviItemview.setOnKeyListener(this);
        this.binding.carItemview.setOnKeyListener(this);
        this.binding.musicItemview.setOnKeyListener(this);
        this.binding.weatherItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.btItemview.setSelected(this.binding.btItemview == view);
        this.binding.naviItemview.setSelected(this.binding.naviItemview == view);
        this.binding.carItemview.setSelected(this.binding.carItemview == view);
        this.binding.musicItemview.setSelected(this.binding.musicItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.weatherItemview;
        if (this.binding.weatherItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.naviItemview);
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
        Log.i(TAG, "benzNtg6Fy onKey: " + keyCode);
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
                setItemSelected(this.binding.carItemview);
                return false;
            }
        } else if (keyCode != 19 && keyCode != 21) {
            return false;
        } else {
            if (v == this.binding.carItemview) {
                setItemSelected(this.binding.weatherItemview);
                return false;
            } else if (v == this.binding.weatherItemview) {
                setItemSelected(this.binding.btItemview);
                return false;
            } else if (v == this.binding.btItemview) {
                setItemSelected(this.binding.musicItemview);
                return false;
            } else if (v == this.binding.musicItemview) {
                setItemSelected(this.binding.naviItemview);
                return false;
            } else if (v != this.binding.naviItemview) {
                return false;
            } else {
                setItemSelected(this.binding.naviItemview);
                return false;
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_itemview /*2131296659*/:
            case R.id.bt_iv1 /*2131296661*/:
            case R.id.bt_iv2 /*2131296662*/:
                this.viewModel.openBtApp();
                setItemSelected(this.binding.btItemview);
                return;
            case R.id.car_itemview /*2131296693*/:
            case R.id.car_iv1 /*2131296695*/:
            case R.id.car_iv2 /*2131296696*/:
                this.viewModel.openCar(v);
                setItemSelected(this.binding.carItemview);
                return;
            case R.id.music_itemview /*2131297337*/:
                this.viewModel.openMusicMulti(v);
                setItemSelected(this.binding.musicItemview);
                return;
            case R.id.music_iv1 /*2131297339*/:
                this.viewModel.btnMusicNext();
                setItemSelected(this.binding.musicItemview);
                return;
            case R.id.music_iv2 /*2131297340*/:
                this.viewModel.btnMusicPrev();
                setItemSelected(this.binding.musicItemview);
                return;
            case R.id.navi_itemview /*2131297361*/:
            case R.id.navi_iv1 /*2131297363*/:
            case R.id.navi_iv2 /*2131297364*/:
                this.viewModel.openNaviApp(v);
                setItemSelected(this.binding.naviItemview);
                return;
            case R.id.weather_itemview /*2131298021*/:
            case R.id.weather_iv1 /*2131298023*/:
            case R.id.weather_iv2 /*2131298024*/:
                this.viewModel.openWeatherApp(v);
                setItemSelected(this.binding.weatherItemview);
                return;
            default:
                return;
        }
    }
}

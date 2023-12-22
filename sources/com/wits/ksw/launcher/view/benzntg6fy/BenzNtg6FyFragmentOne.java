package com.wits.ksw.launcher.view.benzntg6fy;

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
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneCls;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class BenzNtg6FyFragmentOne extends BenzMbux2021BaseFragment implements View.OnKeyListener, View.OnClickListener {
    public static final String TAG = "BenzNtg6FyFragmentOne - One";
    public BenzNtg6FyFragmentOneCls binding;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyFragmentOne.2
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(BenzNtg6FyFragmentOne.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override // com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BenzNtg6FyFragmentOneCls benzNtg6FyFragmentOneCls = (BenzNtg6FyFragmentOneCls) DataBindingUtil.inflate(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_one, null, false);
        this.binding = benzNtg6FyFragmentOneCls;
        return benzNtg6FyFragmentOneCls.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyFragmentOne.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzNtg6FyFragmentOne.this.mainActivity.viewpagerBenzNtg6Fy != null && BenzNtg6FyFragmentOne.this.mainActivity.viewpagerBenzNtg6Fy.getCurrentItem() != 0) {
                    BenzNtg6FyFragmentOne.this.mainActivity.viewpagerBenzNtg6Fy.setCurrentItem(0);
                    BenzNtg6FyFragmentOne.this.setItemSelected(v);
                }
            }
        });
        this.binding.naviItemview.setOnClickListener(this);
        this.binding.musicItemview.setOnClickListener(this);
        this.binding.btItemview.setOnClickListener(this);
        this.binding.carItemview.setOnClickListener(this);
        this.binding.setItemview.setOnClickListener(this);
        this.binding.naviIv1.setOnClickListener(this);
        this.binding.naviIv2.setOnClickListener(this);
        this.binding.musicIv1.setOnClickListener(this);
        this.binding.musicIv2.setOnClickListener(this);
        this.binding.btIv1.setOnClickListener(this);
        this.binding.btIv2.setOnClickListener(this);
        this.binding.carIv1.setOnClickListener(this);
        this.binding.carIv2.setOnClickListener(this);
        this.binding.setIv1.setOnClickListener(this);
        this.binding.setIv2.setOnClickListener(this);
        this.binding.btItemview.setOnKeyListener(this);
        this.binding.naviItemview.setOnKeyListener(this);
        this.binding.carItemview.setOnKeyListener(this);
        this.binding.musicItemview.setOnKeyListener(this);
        this.binding.setItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.btItemview.setSelected(this.binding.btItemview == view);
        this.binding.naviItemview.setSelected(this.binding.naviItemview == view);
        this.binding.carItemview.setSelected(this.binding.carItemview == view);
        this.binding.musicItemview.setSelected(this.binding.musicItemview == view);
        this.binding.setItemview.setSelected(this.binding.setItemview == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.naviItemview);
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
            Log.i(TAG, "benzNtg6Fy onKey: " + keyCode);
            if (keyCode == 20 || keyCode == 22) {
                if (v == this.binding.naviItemview) {
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v == this.binding.musicItemview) {
                    setItemSelected(this.binding.btItemview);
                    return false;
                } else if (v == this.binding.btItemview) {
                    setItemSelected(this.binding.carItemview);
                    return false;
                } else if (v == this.binding.carItemview) {
                    setItemSelected(this.binding.setItemview);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == this.binding.setItemview) {
                    setItemSelected(this.binding.carItemview);
                    return false;
                } else if (v == this.binding.carItemview) {
                    setItemSelected(this.binding.btItemview);
                    return false;
                } else if (v == this.binding.btItemview) {
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v == this.binding.musicItemview) {
                    setItemSelected(this.binding.naviItemview);
                    return false;
                } else if (v == this.binding.naviItemview) {
                    setItemSelected(this.binding.naviItemview);
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
            case C0899R.C0901id.bt_itemview /* 2131296685 */:
            case C0899R.C0901id.bt_iv1 /* 2131296687 */:
            case C0899R.C0901id.bt_iv2 /* 2131296688 */:
                this.viewModel.openBtApp();
                setItemSelected(this.binding.btItemview);
                return;
            case C0899R.C0901id.car_itemview /* 2131296719 */:
            case C0899R.C0901id.car_iv1 /* 2131296721 */:
            case C0899R.C0901id.car_iv2 /* 2131296722 */:
                this.viewModel.openCar(v);
                setItemSelected(this.binding.carItemview);
                return;
            case C0899R.C0901id.music_itemview /* 2131297366 */:
                this.viewModel.openMusicMulti(v);
                setItemSelected(this.binding.musicItemview);
                return;
            case C0899R.C0901id.music_iv1 /* 2131297368 */:
                this.viewModel.btnMusicNext();
                setItemSelected(this.binding.musicItemview);
                return;
            case C0899R.C0901id.music_iv2 /* 2131297369 */:
                this.viewModel.btnMusicPrev();
                setItemSelected(this.binding.musicItemview);
                return;
            case C0899R.C0901id.navi_itemview /* 2131297390 */:
            case C0899R.C0901id.navi_iv1 /* 2131297392 */:
            case C0899R.C0901id.navi_iv2 /* 2131297393 */:
                this.viewModel.openNaviApp(v);
                setItemSelected(this.binding.naviItemview);
                return;
            case C0899R.C0901id.set_itemview /* 2131297748 */:
            case C0899R.C0901id.set_iv1 /* 2131297750 */:
            case C0899R.C0901id.set_iv2 /* 2131297751 */:
                this.viewModel.openSettings(v);
                setItemSelected(this.binding.setItemview);
                return;
            default:
                return;
        }
    }
}

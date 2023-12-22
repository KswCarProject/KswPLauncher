package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.Benz2021FragmentTwo;

/* loaded from: classes4.dex */
public class BenzMbux2021FragmentTwo extends BenzMbux2021BaseFragment implements View.OnKeyListener {
    public static final String TAG = "BenzMbux2021BaseFragment - Two";
    public Benz2021FragmentTwo binding;

    @Override // com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021BaseFragment, android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021FragmentTwo benz2021FragmentTwo = (Benz2021FragmentTwo) DataBindingUtil.inflate(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_two, null, false);
        this.binding = benz2021FragmentTwo;
        return benz2021FragmentTwo.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.videoItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(1);
                    BenzMbux2021FragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(1);
                    BenzMbux2021FragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.videoItemview.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo1.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.musicItemview.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openMusicMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.ivMusic1.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.btnMusicPrev();
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.ivMusic2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.btnMusicNext();
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.setItemview.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openSettings(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.setItemview);
            }
        });
        this.binding.ivSet1.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.10
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openSettings(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.setItemview);
            }
        });
        this.binding.ivSet2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openSettings(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.setItemview);
            }
        });
        this.binding.videoItemview.setOnKeyListener(this);
        this.binding.musicItemview.setOnKeyListener(this);
        this.binding.setItemview.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        this.binding.musicItemview.setSelected(this.binding.musicItemview == view);
        this.binding.setItemview.setSelected(this.binding.setItemview == view);
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
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v == this.binding.musicItemview) {
                    setItemSelected(this.binding.setItemview);
                    return false;
                } else if (v == this.binding.setItemview) {
                    this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(2);
                    return true;
                } else {
                    return false;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == this.binding.setItemview) {
                    setItemSelected(this.binding.musicItemview);
                    return false;
                } else if (v == this.binding.musicItemview) {
                    setItemSelected(this.binding.videoItemview);
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (keyCode != 66) {
            Log.i(TAG, "benz2021 onKey: " + keyCode);
            if (v == this.binding.videoItemview) {
                setItemSelected(this.binding.videoItemview);
            } else if (v == this.binding.musicItemview) {
                setItemSelected(this.binding.musicItemview);
            } else if (v == this.binding.setItemview) {
                setItemSelected(this.binding.setItemview);
            }
            return true;
        } else {
            return false;
        }
    }
}

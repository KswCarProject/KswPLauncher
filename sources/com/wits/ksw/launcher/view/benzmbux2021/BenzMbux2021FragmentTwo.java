package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.Benz2021FragmentTwo;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class BenzMbux2021FragmentTwo extends BenzMbux2021BaseFragment implements View.OnKeyListener {
    public static final String TAG = "BenzMbux2021BaseFragment - Two";
    public Benz2021FragmentTwo binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Benz2021FragmentTwo benz2021FragmentTwo = (Benz2021FragmentTwo) DataBindingUtil.inflate(inflater, R.layout.benz_mbux_2021_fragment_two, (ViewGroup) null, false);
        this.binding = benz2021FragmentTwo;
        return benz2021FragmentTwo.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.videoItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(1);
                    BenzMbux2021FragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.setItemview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021 != null && BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.getCurrentItem() != 1) {
                    BenzMbux2021FragmentTwo.this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(1);
                    BenzMbux2021FragmentTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.videoItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.ivVideo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openVideoMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.videoItemview);
            }
        });
        this.binding.musicItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openMusicMulti(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.ivMusic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.btnMusicPrev();
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.ivMusic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.btnMusicNext();
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.musicItemview);
            }
        });
        this.binding.setItemview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openSettings(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.setItemview);
            }
        });
        this.binding.ivSet1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BenzMbux2021FragmentTwo.this.viewModel.openSettings(v);
                BenzMbux2021FragmentTwo benzMbux2021FragmentTwo = BenzMbux2021FragmentTwo.this;
                benzMbux2021FragmentTwo.setItemSelected(benzMbux2021FragmentTwo.binding.setItemview);
            }
        });
        this.binding.ivSet2.setOnClickListener(new View.OnClickListener() {
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
        boolean z = true;
        this.binding.videoItemview.setSelected(this.binding.videoItemview == view);
        this.binding.musicItemview.setSelected(this.binding.musicItemview == view);
        BenzMbuxItemView benzMbuxItemView = this.binding.setItemview;
        if (this.binding.setItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
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
        if (event.getAction() != 0) {
            return false;
        }
        Log.i(TAG, "benz2021 onKey: " + keyCode);
        if (keyCode == 20 || keyCode == 22) {
            if (v == this.binding.videoItemview) {
                setItemSelected(this.binding.musicItemview);
                return false;
            } else if (v == this.binding.musicItemview) {
                setItemSelected(this.binding.setItemview);
                return false;
            } else if (v != this.binding.setItemview) {
                return false;
            } else {
                this.mainActivity.viewPagerBenzMbux2021.setCurrentItem(2);
                return true;
            }
        } else if (keyCode != 19 && keyCode != 21) {
            return false;
        } else {
            if (v == this.binding.setItemview) {
                setItemSelected(this.binding.musicItemview);
                return false;
            } else if (v != this.binding.musicItemview) {
                return false;
            } else {
                setItemSelected(this.binding.videoItemview);
                return false;
            }
        }
    }
}

package com.wits.ksw.launcher.view.kswmbux;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.KswmbuxHomeTwoBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgPager;
import com.wits.ksw.launcher.view.ug.WiewFocusUtils;

public class KswMbuxHomeFragmentTwo extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = ("KswApplication." + KswMbuxHomeFragmentTwo.class.getSimpleName());
    /* access modifiers changed from: private */
    public KswmbuxHomeTwoBindingImpl binding;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KswmbuxHomeTwoBindingImpl kswmbuxHomeTwoBindingImpl = (KswmbuxHomeTwoBindingImpl) DataBindingUtil.inflate(inflater, R.layout.kswmbux_home_two, (ViewGroup) null, false);
        this.binding = kswmbuxHomeTwoBindingImpl;
        return kswmbuxHomeTwoBindingImpl.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.kswmbuxHomeHdvideoVaiw.setOnFocusChangeListener(this);
        this.binding.kswmbuxHomeSettingVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
            public void onChanged(UgPager ugPager) {
                if (ugPager.index != 1) {
                    return;
                }
                if (ugPager.left) {
                    WiewFocusUtils.setViewRequestFocus(KswMbuxHomeFragmentTwo.this.binding.kswmbuxHomeHdvideoVaiw);
                } else if (ugPager.right) {
                    WiewFocusUtils.setViewRequestFocus(KswMbuxHomeFragmentTwo.this.binding.kswmbuxHomeSettingVaiw);
                }
            }
        });
    }

    public void setCurrentItem(int item) {
        try {
            if (MainActivity.mainActivity.kswmbuxBinding.ugViewPage.getCurrentItem() != item) {
                MainActivity.mainActivity.kswmbuxBinding.ugViewPage.setCurrentItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setCurrentItem(1);
        }
    }
}

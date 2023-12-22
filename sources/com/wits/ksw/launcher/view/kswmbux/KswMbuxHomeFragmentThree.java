package com.wits.ksw.launcher.view.kswmbux;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.KswmbuxHomeThreeBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgPager;
import com.wits.ksw.launcher.view.p006ug.WiewFocusUtils;

/* loaded from: classes15.dex */
public class KswMbuxHomeFragmentThree extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = "KswApplication." + KswMbuxHomeFragmentThree.class.getSimpleName();
    private KswmbuxHomeThreeBindingImpl binding;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KswmbuxHomeThreeBindingImpl kswmbuxHomeThreeBindingImpl = (KswmbuxHomeThreeBindingImpl) DataBindingUtil.inflate(inflater, C0899R.C0902layout.kswmbux_home_three, null, false);
        this.binding = kswmbuxHomeThreeBindingImpl;
        return kswmbuxHomeThreeBindingImpl.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.kswmbuxHomeDashboradVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() { // from class: com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentThree.1
            @Override // android.arch.lifecycle.Observer
            public void onChanged(UgPager ugPager) {
                if (ugPager.index == 2 && ugPager.left) {
                    WiewFocusUtils.setViewRequestFocus(KswMbuxHomeFragmentThree.this.binding.kswmbuxHomeDashboradVaiw);
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

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setCurrentItem(2);
        }
    }
}

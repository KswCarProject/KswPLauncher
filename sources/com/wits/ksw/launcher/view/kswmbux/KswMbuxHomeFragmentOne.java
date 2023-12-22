package com.wits.ksw.launcher.view.kswmbux;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.KswmbuxHomeOneBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgPager;
import com.wits.ksw.launcher.view.p006ug.WiewFocusUtils;

/* loaded from: classes15.dex */
public class KswMbuxHomeFragmentOne extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = "KswApplication," + KswMbuxHomeFragmentOne.class.getSimpleName();
    private KswmbuxHomeOneBindingImpl binding;
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
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KswmbuxHomeOneBindingImpl kswmbuxHomeOneBindingImpl = (KswmbuxHomeOneBindingImpl) DataBindingUtil.inflate(inflater, C0899R.C0902layout.kswmbux_home_one, null, false);
        this.binding = kswmbuxHomeOneBindingImpl;
        return kswmbuxHomeOneBindingImpl.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.kswmbuxHomeBtVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() { // from class: com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentOne.1
            @Override // android.arch.lifecycle.Observer
            public void onChanged(UgPager ugPager) {
                if (ugPager.index == 0) {
                    WiewFocusUtils.setViewRequestFocus(KswMbuxHomeFragmentOne.this.binding.kswmbuxHomeBtVaiw);
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
        Log.i(TAG, "onFocusChange: ");
        if (hasFocus) {
            setCurrentItem(0);
        }
    }
}

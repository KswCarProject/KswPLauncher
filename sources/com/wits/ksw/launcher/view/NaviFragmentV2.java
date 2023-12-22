package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.NaviFragmentBindingV2;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes16.dex */
public class NaviFragmentV2 extends Fragment {
    private NaviFragmentBindingV2 binding;
    private LauncherViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        NaviFragmentBindingV2 naviFragmentBindingV2 = (NaviFragmentBindingV2) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id7_v2_fragment_navi, null, false);
        this.binding = naviFragmentBindingV2;
        return naviFragmentBindingV2.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NaviFragmentBindingV2 naviFragmentBindingV2 = this.binding;
        if (naviFragmentBindingV2 != null) {
            naviFragmentBindingV2.setNaviViewModel(this.viewModel);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}

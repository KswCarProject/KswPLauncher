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
import com.wits.ksw.databinding.HicarNaviFragment;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.UiThemeUtils;

/* loaded from: classes16.dex */
public class NaviFragment extends Fragment {
    private com.wits.ksw.databinding.NaviFragment binding;
    private HicarNaviFragment hicarBinding;
    private LauncherViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isBMW_EVO_ID7_HiCar(getContext())) {
            this.viewModel.hicar.set(true);
            HicarNaviFragment hicarNaviFragment = (HicarNaviFragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id7_fragment_navi_hicar, null, false);
            this.hicarBinding = hicarNaviFragment;
            return hicarNaviFragment.getRoot();
        }
        this.viewModel.hicar.set(false);
        com.wits.ksw.databinding.NaviFragment naviFragment = (com.wits.ksw.databinding.NaviFragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id7_fragment_navi, null, false);
        this.binding = naviFragment;
        return naviFragment.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.NaviFragment naviFragment = this.binding;
        if (naviFragment != null) {
            naviFragment.setNaviViewModel(this.viewModel);
        }
        HicarNaviFragment hicarNaviFragment = this.hicarBinding;
        if (hicarNaviFragment != null) {
            hicarNaviFragment.setNaviViewModel(this.viewModel);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.HicarNaviFragment;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class NaviFragment extends Fragment {
    private com.wits.ksw.databinding.NaviFragment binding;
    private HicarNaviFragment hicarBinding;
    private LauncherViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isBMW_EVO_ID7_HiCar(getContext())) {
            this.viewModel.hicar.set(true);
            HicarNaviFragment hicarNaviFragment = (HicarNaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi_hicar, (ViewGroup) null, false);
            this.hicarBinding = hicarNaviFragment;
            return hicarNaviFragment.getRoot();
        }
        this.viewModel.hicar.set(false);
        com.wits.ksw.databinding.NaviFragment naviFragment = (com.wits.ksw.databinding.NaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi, (ViewGroup) null, false);
        this.binding = naviFragment;
        return naviFragment.getRoot();
    }

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

    public void onDestroy() {
        super.onDestroy();
    }
}

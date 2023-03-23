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
import com.wits.ksw.databinding.NaviFragmentBindingV2;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class NaviFragmentV2 extends Fragment {
    private NaviFragmentBindingV2 binding;
    private LauncherViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        NaviFragmentBindingV2 naviFragmentBindingV2 = (NaviFragmentBindingV2) DataBindingUtil.inflate(inflater, R.layout.id7_v2_fragment_navi, (ViewGroup) null, false);
        this.binding = naviFragmentBindingV2;
        return naviFragmentBindingV2.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NaviFragmentBindingV2 naviFragmentBindingV2 = this.binding;
        if (naviFragmentBindingV2 != null) {
            naviFragmentBindingV2.setNaviViewModel(this.viewModel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

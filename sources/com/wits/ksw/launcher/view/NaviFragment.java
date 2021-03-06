package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (UiThemeUtils.isBMW_EVO_ID7_HiCar(getContext())) {
            this.viewModel.hicar.set(true);
            this.hicarBinding = (HicarNaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi_hicar, (ViewGroup) null, false);
            return this.hicarBinding.getRoot();
        }
        this.viewModel.hicar.set(false);
        this.binding = (com.wits.ksw.databinding.NaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.binding != null) {
            this.binding.setNaviViewModel(this.viewModel);
        }
        if (this.hicarBinding != null) {
            this.hicarBinding.setNaviViewModel(this.viewModel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

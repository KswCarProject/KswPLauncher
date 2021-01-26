package com.wits.ksw.launcher.land_rover.fragment;

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
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LandroverOneFragment extends Fragment {
    private com.wits.ksw.databinding.LandroverOneFragment binding;
    private LauncherViewModel viewModel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarFragment", "onCreate: CarFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        this.binding = (com.wits.ksw.databinding.LandroverOneFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_one, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.binding != null) {
            this.binding.setViewModel(this.viewModel);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

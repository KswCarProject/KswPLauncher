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
import com.wits.ksw.databinding.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.McuImpl;

public class CarFragment extends Fragment {
    private CarInfo binding;
    private LauncherViewModel viewModel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: CarFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (CarInfo) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_car, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setCarViewModel(this.viewModel);
    }

    public void onResume() {
        super.onResume();
        McuImpl.getInstance().setUint();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

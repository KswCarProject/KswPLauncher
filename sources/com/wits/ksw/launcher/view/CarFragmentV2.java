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
import com.wits.ksw.databinding.CarInfoV2;
import com.wits.ksw.databinding.HicarCarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.McuImpl;

public class CarFragmentV2 extends Fragment {
    private CarInfoV2 binding;
    private HicarCarInfo hicarBinding;
    private LauncherViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarFragment", "onCreate: CarFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        CarInfoV2 carInfoV2 = (CarInfoV2) DataBindingUtil.inflate(inflater, R.layout.id7_v2_fragment_car, (ViewGroup) null, false);
        this.binding = carInfoV2;
        return carInfoV2.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CarInfoV2 carInfoV2 = this.binding;
        if (carInfoV2 != null) {
            carInfoV2.setCarViewModel(this.viewModel);
        }
        HicarCarInfo hicarCarInfo = this.hicarBinding;
        if (hicarCarInfo != null) {
            hicarCarInfo.setCarViewModel(this.viewModel);
        }
    }

    public void onResume() {
        super.onResume();
        McuImpl.getInstance().setUint();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

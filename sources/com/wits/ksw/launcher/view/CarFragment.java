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
import com.wits.ksw.databinding.CarInfo;
import com.wits.ksw.databinding.HicarCarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class CarFragment extends Fragment {
    private CarInfo binding;
    private HicarCarInfo hicarBinding;
    private LauncherViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarFragment", "onCreate: CarFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isBMW_EVO_ID7_HiCar(getContext())) {
            this.viewModel.hicar.set(true);
            HicarCarInfo hicarCarInfo = (HicarCarInfo) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_car_hicar, (ViewGroup) null, false);
            this.hicarBinding = hicarCarInfo;
            return hicarCarInfo.getRoot();
        }
        this.viewModel.hicar.set(false);
        CarInfo carInfo = (CarInfo) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_car, (ViewGroup) null, false);
        this.binding = carInfo;
        return carInfo.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CarInfo carInfo = this.binding;
        if (carInfo != null) {
            carInfo.setCarViewModel(this.viewModel);
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

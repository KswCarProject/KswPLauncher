package com.wits.ksw.launcher.als_id7_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AlsId7UiCarInfoBinding;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.McuImpl;

/* loaded from: classes11.dex */
public class AlsId7UiCarFragment extends Fragment {
    private CarInfo carInfo;
    private LauncherViewModel launcherViewModel;
    private AlsId7UiCarInfoBinding uiCarInfoBinding;

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AlsId7UiCarFragment", "onCreate: CarFragment");
        this.launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.launcherViewModel.hicar.set(false);
        AlsId7UiCarInfoBinding alsId7UiCarInfoBinding = (AlsId7UiCarInfoBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_als_id7_ui_car, null, false);
        this.uiCarInfoBinding = alsId7UiCarInfoBinding;
        return alsId7UiCarInfoBinding.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AlsId7UiCarInfoBinding alsId7UiCarInfoBinding = this.uiCarInfoBinding;
        if (alsId7UiCarInfoBinding != null) {
            alsId7UiCarInfoBinding.setCarViewModel(this.launcherViewModel);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        McuImpl.getInstance().setUint();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}

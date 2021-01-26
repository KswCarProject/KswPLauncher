package com.wits.ksw.launcher.als_id7.fragment;

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
import com.wits.ksw.databinding.HicarCarInfo;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.model.McuImpl;

public class DashVideoFragment extends Fragment {
    private com.wits.ksw.databinding.DashVideoFragment binding;
    private HicarCarInfo hicarBinding;
    private AlsID7ViewModel viewModel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarFragment", "onCreate: CarFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.of(getActivity()).get(AlsID7ViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        this.binding = (com.wits.ksw.databinding.DashVideoFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_dash_video, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.binding != null) {
            this.binding.setDashVideoViewModel(this.viewModel);
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

package com.wits.ksw.launcher.als_id7.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.DashVideoFragment_V2;
import com.wits.ksw.databinding.HicarCarInfo;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class DashVideoFragment extends Fragment {
    private com.wits.ksw.databinding.DashVideoFragment binding;
    private DashVideoFragment_V2 binding_v2;
    private HicarCarInfo hicarBinding;
    private AlsID7ViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CarFragment", "onCreate: CarFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.of(getActivity()).get(AlsID7ViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        if (UiThemeUtils.isID7_ALS_V2(getContext())) {
            DashVideoFragment_V2 dashVideoFragment_V2 = (DashVideoFragment_V2) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_dash_video_v2, (ViewGroup) null, false);
            this.binding_v2 = dashVideoFragment_V2;
            return dashVideoFragment_V2.getRoot();
        }
        com.wits.ksw.databinding.DashVideoFragment dashVideoFragment = (com.wits.ksw.databinding.DashVideoFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_dash_video, (ViewGroup) null, false);
        this.binding = dashVideoFragment;
        return dashVideoFragment.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.DashVideoFragment dashVideoFragment = this.binding;
        if (dashVideoFragment != null) {
            dashVideoFragment.setDashVideoViewModel(this.viewModel);
        }
        DashVideoFragment_V2 dashVideoFragment_V2 = this.binding_v2;
        if (dashVideoFragment_V2 != null) {
            dashVideoFragment_V2.setDashVideoViewModel(this.viewModel);
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

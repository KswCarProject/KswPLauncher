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
import com.wits.ksw.databinding.HicarNaviFragment;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class NaviCarFragment extends Fragment {
    private com.wits.ksw.databinding.NaviCarFragment binding;
    private HicarNaviFragment hicarBinding;
    private AlsID7ViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.of(getActivity()).get(AlsID7ViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        com.wits.ksw.databinding.NaviCarFragment naviCarFragment = (com.wits.ksw.databinding.NaviCarFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_navi_car, (ViewGroup) null, false);
        this.binding = naviCarFragment;
        return naviCarFragment.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.NaviCarFragment naviCarFragment = this.binding;
        if (naviCarFragment != null) {
            naviCarFragment.setNaviCarViewModel(this.viewModel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

package com.wits.ksw.launcher.als_id7.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.HicarNaviFragment;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

/* loaded from: classes6.dex */
public class NaviCarFragment extends Fragment {
    private com.wits.ksw.databinding.NaviCarFragment binding;
    private HicarNaviFragment hicarBinding;
    private AlsID7ViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: NaviFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.m59of(getActivity()).get(AlsID7ViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel.hicar.set(false);
        com.wits.ksw.databinding.NaviCarFragment naviCarFragment = (com.wits.ksw.databinding.NaviCarFragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.als_id7_fragment_navi_car, null, false);
        this.binding = naviCarFragment;
        return naviCarFragment.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.NaviCarFragment naviCarFragment = this.binding;
        if (naviCarFragment != null) {
            naviCarFragment.setNaviCarViewModel(this.viewModel);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}

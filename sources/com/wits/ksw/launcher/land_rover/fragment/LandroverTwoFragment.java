package com.wits.ksw.launcher.land_rover.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes11.dex */
public class LandroverTwoFragment extends Fragment {
    private static final String TAG = "KswApplication";
    private com.wits.ksw.databinding.LandroverTwoFragment binding;
    private MainActivity mainActivity;
    private LandroverViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LandroverTwoFragment", "onCreate: LandroverTwoFragment");
        this.viewModel = (LandroverViewModel) ViewModelProviders.m59of(getActivity()).get(LandroverViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.wits.ksw.databinding.LandroverTwoFragment landroverTwoFragment = (com.wits.ksw.databinding.LandroverTwoFragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.landrover_main_fragment_two, null, false);
        this.binding = landroverTwoFragment;
        return landroverTwoFragment.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.LandroverTwoFragment landroverTwoFragment = this.binding;
        if (landroverTwoFragment != null) {
            landroverTwoFragment.setViewModel(this.viewModel);
        }
        Log.i("KswApplication", "onActivityCreated: registerIContentObserver topApp ");
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.i("KswApplication", "onDestroy: unRegisterIContentObserver topApp");
    }
}

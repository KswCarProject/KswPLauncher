package com.wits.ksw.launcher.land_rover.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public class LandroverTwoFragment extends Fragment {
    private static final String TAG = "KswApplication";
    private com.wits.ksw.databinding.LandroverTwoFragment binding;
    private MainActivity mainActivity;
    private LandroverViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LandroverTwoFragment", "onCreate: LandroverTwoFragment");
        this.viewModel = (LandroverViewModel) ViewModelProviders.of(getActivity()).get(LandroverViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.wits.ksw.databinding.LandroverTwoFragment landroverTwoFragment = (com.wits.ksw.databinding.LandroverTwoFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_two, (ViewGroup) null, false);
        this.binding = landroverTwoFragment;
        return landroverTwoFragment.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com.wits.ksw.databinding.LandroverTwoFragment landroverTwoFragment = this.binding;
        if (landroverTwoFragment != null) {
            landroverTwoFragment.setViewModel(this.viewModel);
        }
        Log.i("KswApplication", "onActivityCreated: registerIContentObserver topApp ");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("KswApplication", "onDestroy: unRegisterIContentObserver topApp");
    }
}

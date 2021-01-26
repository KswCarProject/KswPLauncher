package com.wits.ksw.launcher.land_rover.fragment;

import android.app.Activity;
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
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LandroverTwoFragment extends Fragment {
    private static final String TAG = "KSWLauncher";
    private com.wits.ksw.databinding.LandroverTwoFragment binding;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LandroverTwoFragment", "onCreate: LandroverTwoFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (com.wits.ksw.databinding.LandroverTwoFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_two, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.binding != null) {
            this.binding.setViewModel(this.viewModel);
        }
        Log.i("KSWLauncher", "onActivityCreated: registerIContentObserver topApp ");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("KSWLauncher", "onDestroy: unRegisterIContentObserver topApp");
    }
}

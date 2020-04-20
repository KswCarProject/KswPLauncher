package com.wits.ksw.launcher.view.id6;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID6BaseFragment extends Fragment {
    protected static final String TAG = "KSWLauncher";
    protected MainActivity mainActivity;
    protected LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }
}

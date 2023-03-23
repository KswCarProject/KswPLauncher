package com.wits.ksw.launcher.view.lexusls;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LexusLsBaseBottomFragment extends Fragment {
    protected static final String TAG = "KswApplication";
    protected MainActivity mainActivity;
    protected LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }
}

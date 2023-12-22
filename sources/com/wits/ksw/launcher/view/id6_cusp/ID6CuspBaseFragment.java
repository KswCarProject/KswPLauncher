package com.wits.ksw.launcher.view.id6_cusp;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class ID6CuspBaseFragment extends Fragment {
    protected static final String TAG = "KswApplication";
    protected MainActivity mainActivity;
    protected LauncherViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }
}

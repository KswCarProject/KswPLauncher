package com.wits.ksw.launcher.view.audimib3;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes9.dex */
public class AudiMib3BaseFragment extends Fragment {
    protected static final String TAG = AudiMib3BaseFragment.class.getSimpleName();
    protected static boolean isSmooth = false;
    protected MainActivity mainActivity;
    protected BcVieModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (BcVieModel) ViewModelProviders.m59of(getActivity()).get(BcVieModel.class);
    }
}

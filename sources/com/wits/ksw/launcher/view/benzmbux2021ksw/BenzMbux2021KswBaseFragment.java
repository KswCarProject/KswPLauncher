package com.wits.ksw.launcher.view.benzmbux2021ksw;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.BcVieModel;

public class BenzMbux2021KswBaseFragment extends Fragment {
    protected static final String TAG = "KswApplication";
    /* access modifiers changed from: protected */
    public MainActivity mainActivity;
    /* access modifiers changed from: protected */
    public BcVieModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (BcVieModel) ViewModelProviders.of(getActivity()).get(BcVieModel.class);
    }
}

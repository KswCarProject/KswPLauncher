package com.wits.ksw.launcher.view.audimib3fy;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.BcVieModel;

public class AudiMib3FyBaseFragment extends Fragment {
    protected static final String TAG = AudiMib3FyBaseFragment.class.getSimpleName();
    protected static boolean isSmooth = false;
    protected MainActivity mainActivity;
    protected BcVieModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (BcVieModel) ViewModelProviders.of(getActivity()).get(BcVieModel.class);
    }
}

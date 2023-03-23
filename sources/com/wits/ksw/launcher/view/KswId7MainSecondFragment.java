package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.KswId7MainPage2Fragment;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class KswId7MainSecondFragment extends Fragment {
    private KswId7MainPage2Fragment binding;
    private LauncherViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KswId7MainPage2Fragment kswId7MainPage2Fragment = (KswId7MainPage2Fragment) DataBindingUtil.inflate(inflater, R.layout.ksw_id7_main_page2, (ViewGroup) null, false);
        this.binding = kswId7MainPage2Fragment;
        return kswId7MainPage2Fragment.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setCarViewModel(this.viewModel);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void hideArrow() {
        KswId7MainPage2Fragment kswId7MainPage2Fragment = this.binding;
        if (kswId7MainPage2Fragment != null) {
            kswId7MainPage2Fragment.ivArrow.setVisibility(4);
        }
    }

    public void showArrow() {
        KswId7MainPage2Fragment kswId7MainPage2Fragment = this.binding;
        if (kswId7MainPage2Fragment != null) {
            kswId7MainPage2Fragment.ivArrow.setVisibility(0);
        }
    }
}

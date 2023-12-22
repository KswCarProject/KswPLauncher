package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.KswId7MainPage2Fragment;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes16.dex */
public class KswId7MainSecondFragment extends Fragment {
    private KswId7MainPage2Fragment binding;
    private LauncherViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KswId7MainPage2Fragment kswId7MainPage2Fragment = (KswId7MainPage2Fragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.ksw_id7_main_page2, null, false);
        this.binding = kswId7MainPage2Fragment;
        return kswId7MainPage2Fragment.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setCarViewModel(this.viewModel);
    }

    @Override // android.support.p001v4.app.Fragment
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

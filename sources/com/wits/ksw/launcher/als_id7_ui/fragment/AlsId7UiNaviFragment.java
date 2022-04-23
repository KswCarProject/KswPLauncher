package com.wits.ksw.launcher.als_id7_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AlsId7UiNaviBinding;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class AlsId7UiNaviFragment extends Fragment {
    private LauncherViewModel launcherViewModel;
    private AlsId7UiNaviBinding naviSubViewBinding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: AlsId7UiNaviFragment");
        this.launcherViewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.launcherViewModel.hicar.set(false);
        AlsId7UiNaviBinding alsId7UiNaviBinding = (AlsId7UiNaviBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_als_id7_ui_navi, (ViewGroup) null, false);
        this.naviSubViewBinding = alsId7UiNaviBinding;
        return alsId7UiNaviBinding.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AlsId7UiNaviBinding alsId7UiNaviBinding = this.naviSubViewBinding;
        if (alsId7UiNaviBinding != null) {
            alsId7UiNaviBinding.setNaviViewModel(this.launcherViewModel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}

package com.wits.ksw.launcher.view.ug;

import android.app.Activity;
import android.arch.lifecycle.Observer;
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
import com.wits.ksw.databinding.UgHomeOneBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class UgHomeFragmentOne extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = ("KSWLauncher," + UgHomeFragmentOne.class.getSimpleName());
    /* access modifiers changed from: private */
    public UgHomeOneBindingImpl binding;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (UgHomeOneBindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_one, (ViewGroup) null, false);
        return this.binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.ugHomeMusicVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
            public void onChanged(@Nullable UgPager ugPager) {
                if (ugPager.index == 0) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentOne.this.binding.ugHomeMusicVaiw);
                }
            }
        });
    }

    public void setCurrentItem(int item) {
        try {
            if (MainActivity.mainActivity.ugBinding.ugViewPage.getCurrentItem() != item) {
                MainActivity.mainActivity.ugBinding.ugViewPage.setCurrentItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        Log.i(TAG, "onFocusChange: ");
        if (hasFocus) {
            setCurrentItem(0);
        }
    }
}

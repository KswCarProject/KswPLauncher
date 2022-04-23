package com.wits.ksw.launcher.view.ug;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.UgHomeFour2BindingImpl;
import com.wits.ksw.databinding.UgHomeFourBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class UgHomeFragmentFour extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = ("KSWLauncher." + UgHomeFragmentFour.class.getSimpleName());
    /* access modifiers changed from: private */
    public UgHomeFourBindingImpl binding;
    /* access modifiers changed from: private */
    public UgHomeFour2BindingImpl binding2;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;
    private int width = 0;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        this.width = getResources().getDisplayMetrics().widthPixels;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            UgHomeFour2BindingImpl ugHomeFour2BindingImpl = (UgHomeFour2BindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_four2, (ViewGroup) null, false);
            this.binding2 = ugHomeFour2BindingImpl;
            return ugHomeFour2BindingImpl.getRoot();
        }
        UgHomeFourBindingImpl ugHomeFourBindingImpl = (UgHomeFourBindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_four, (ViewGroup) null, false);
        this.binding = ugHomeFourBindingImpl;
        setGSClientIcon(ugHomeFourBindingImpl.ugHomeBrowserVaiw, this.binding.ugHomeDvrVaiw, this.binding.ugHomeFileVaiw, this.width);
        return this.binding.getRoot();
    }

    public void setGSClientIcon(UgHomeImageView imageView1, UgHomeImageView imageView2, UgHomeImageView imageView3, int width2) {
        if (ClientManager.getInstance().isGSClient() && width2 == 1920) {
            imageView1.setImageResource(R.drawable.ug_home_broswer_gs_selector);
            imageView2.setImageResource(R.drawable.ug_home_dvr_gs_selector);
            imageView3.setImageResource(R.drawable.ug_home_file_gs_selector);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            this.binding2.setViewModel(this.viewModel);
            this.binding2.ugHomeBrowserVaiw.setOnFocusChangeListener(this);
            this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
                public void onChanged(UgPager ugPager) {
                    if (ugPager.index == 3 && ugPager.left) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentFour.this.binding2.ugHomeBrowserVaiw);
                    }
                }
            });
            return;
        }
        this.binding.setViewModel(this.viewModel);
        this.binding.ugHomeBrowserVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
            public void onChanged(UgPager ugPager) {
                if (ugPager.index == 3 && ugPager.left) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentFour.this.binding.ugHomeBrowserVaiw);
                }
            }
        });
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setCurrentItem(3);
        }
    }

    public void setCurrentItem(int item) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            try {
                if (MainActivity.mainActivity.gsug2Binding.ugViewPage.getCurrentItem() != item) {
                    MainActivity.mainActivity.gsug2Binding.ugViewPage.setCurrentItem(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (MainActivity.mainActivity.ugBinding.ugViewPage.getCurrentItem() != item) {
                    MainActivity.mainActivity.ugBinding.ugViewPage.setCurrentItem(item);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

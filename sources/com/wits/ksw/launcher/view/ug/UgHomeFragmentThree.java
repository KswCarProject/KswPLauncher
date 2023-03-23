package com.wits.ksw.launcher.view.ug;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.UgHomeThree2BindingImpl;
import com.wits.ksw.databinding.UgHomeThreeBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class UgHomeFragmentThree extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = ("KswApplication." + UgHomeFragmentThree.class.getSimpleName());
    /* access modifiers changed from: private */
    public UgHomeThreeBindingImpl binding;
    /* access modifiers changed from: private */
    public UgHomeThree2BindingImpl binding2;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;
    private int width = 0;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        this.width = getResources().getDisplayMetrics().widthPixels;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            UgHomeThree2BindingImpl ugHomeThree2BindingImpl = (UgHomeThree2BindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_three2, (ViewGroup) null, false);
            this.binding2 = ugHomeThree2BindingImpl;
            setGSClientIcon(ugHomeThree2BindingImpl.ugHomeCarVaiw, this.binding2.ugHomeDashboradVaiw, this.binding2.ugHomeSettingVaiw, this.width);
            return this.binding2.getRoot();
        }
        UgHomeThreeBindingImpl ugHomeThreeBindingImpl = (UgHomeThreeBindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_three, (ViewGroup) null, false);
        this.binding = ugHomeThreeBindingImpl;
        setGSClientIcon(ugHomeThreeBindingImpl.ugHomeCarVaiw, this.binding.ugHomeDashboradVaiw, this.binding.ugHomeSettingVaiw, this.width);
        return this.binding.getRoot();
    }

    public void setGSClientIcon(UgHomeImageView imageView1, UgHomeImageView imageView2, UgHomeImageView imageView3, int width2) {
        if (!ClientManager.getInstance().isGSClient()) {
            return;
        }
        if (width2 == 1920) {
            imageView1.setImageResource(R.drawable.ug_home_car_gs_selector);
            imageView2.setImageResource(R.drawable.ug_home_dashboard_gs_selector);
            imageView3.setImageResource(R.drawable.ug_home_setting_gs_selector);
            return;
        }
        imageView1.setImageResource(R.drawable.ug_home_car_gs_selector);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            this.binding2.setViewModel(this.viewModel);
            this.binding2.ugHomeCarVaiw.setOnFocusChangeListener(this);
            this.binding2.ugHomeSettingVaiw.setOnFocusChangeListener(this);
            this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
                public void onChanged(UgPager ugPager) {
                    if (ugPager.index != 2) {
                        return;
                    }
                    if (ugPager.left) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentThree.this.binding2.ugHomeCarVaiw);
                    } else if (ugPager.right) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentThree.this.binding2.ugHomeSettingVaiw);
                    }
                }
            });
            return;
        }
        this.binding.setViewModel(this.viewModel);
        this.binding.ugHomeCarVaiw.setOnFocusChangeListener(this);
        this.binding.ugHomeSettingVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
            public void onChanged(UgPager ugPager) {
                if (ugPager.index != 2) {
                    return;
                }
                if (ugPager.left) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentThree.this.binding.ugHomeCarVaiw);
                } else if (ugPager.right) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentThree.this.binding.ugHomeSettingVaiw);
                }
            }
        });
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

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setCurrentItem(2);
        }
    }
}

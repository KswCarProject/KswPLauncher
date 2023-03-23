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
import com.wits.ksw.databinding.UgHomeTwo2BindingImpl;
import com.wits.ksw.databinding.UgHomeTwoBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

public class UgHomeFragmentTwo extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = ("KswApplication." + UgHomeFragmentTwo.class.getSimpleName());
    /* access modifiers changed from: private */
    public UgHomeTwoBindingImpl binding;
    /* access modifiers changed from: private */
    public UgHomeTwo2BindingImpl binding2;
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
        this.width = getResources().getDisplayMetrics().widthPixels;
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            UgHomeTwo2BindingImpl ugHomeTwo2BindingImpl = (UgHomeTwo2BindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_two2, (ViewGroup) null, false);
            this.binding2 = ugHomeTwo2BindingImpl;
            setGSClientIcon(ugHomeTwo2BindingImpl.ugHomeHdvideoVaiw, this.binding2.ugHomeEasyVaiw, this.binding2.ugHomeAppVaiw, this.width);
            return this.binding2.getRoot();
        }
        UgHomeTwoBindingImpl ugHomeTwoBindingImpl = (UgHomeTwoBindingImpl) DataBindingUtil.inflate(inflater, R.layout.ug_home_two, (ViewGroup) null, false);
        this.binding = ugHomeTwoBindingImpl;
        setGSClientIcon(ugHomeTwoBindingImpl.ugHomeHdvideoVaiw, this.binding.ugHomeEasyVaiw, this.binding.ugHomeAppVaiw, this.width);
        return this.binding.getRoot();
    }

    public void setGSClientIcon(UgHomeImageView imageView1, UgHomeImageView imageView2, UgHomeImageView imageView3, int width2) {
        if (ClientManager.getInstance().isGSClient() && (width2 == 1024 || width2 == 1280)) {
            imageView2.setImageResource(R.drawable.ug_home_easy_gs_selector);
            imageView3.setImageResource(R.drawable.ug_home_app_gs_selector);
        } else if (ClientManager.getInstance().isGSClient() && width2 == 1920) {
            imageView1.setImageResource(R.drawable.ug_home_hdvideo_gs_selector);
            imageView2.setImageResource(R.drawable.ug_home_easy_gs_selector);
            imageView3.setImageResource(R.drawable.ug_home_app_gs_selector);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            this.binding2.setViewModel(this.viewModel);
            this.binding2.ugHomeHdvideoVaiw.setOnFocusChangeListener(this);
            this.binding2.ugHomeAppVaiw.setOnFocusChangeListener(this);
            this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
                public void onChanged(UgPager ugPager) {
                    if (ugPager.index != 1) {
                        return;
                    }
                    if (ugPager.left) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding2.ugHomeHdvideoVaiw);
                    } else if (ugPager.right) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding2.ugHomeAppVaiw);
                    }
                }
            });
            return;
        }
        this.binding.setViewModel(this.viewModel);
        this.binding.ugHomeHdvideoVaiw.setOnFocusChangeListener(this);
        this.binding.ugHomeEasyVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() {
            public void onChanged(UgPager ugPager) {
                if (ugPager.index != 1) {
                    return;
                }
                if (ugPager.left) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding.ugHomeHdvideoVaiw);
                } else if (ugPager.right) {
                    WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding.ugHomeEasyVaiw);
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
            setCurrentItem(1);
        }
    }
}

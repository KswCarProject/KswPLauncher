package com.wits.ksw.launcher.view.p006ug;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.UgHomeTwo2BindingImpl;
import com.wits.ksw.databinding.UgHomeTwoBindingImpl;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.UiThemeUtils;

/* renamed from: com.wits.ksw.launcher.view.ug.UgHomeFragmentTwo */
/* loaded from: classes9.dex */
public class UgHomeFragmentTwo extends Fragment implements View.OnFocusChangeListener {
    private static final String TAG = "KswApplication." + UgHomeFragmentTwo.class.getSimpleName();
    private UgHomeTwoBindingImpl binding;
    private UgHomeTwo2BindingImpl binding2;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;
    private int width = 0;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.width = dm.widthPixels;
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            UgHomeTwo2BindingImpl ugHomeTwo2BindingImpl = (UgHomeTwo2BindingImpl) DataBindingUtil.inflate(inflater, C0899R.C0902layout.ug_home_two2, null, false);
            this.binding2 = ugHomeTwo2BindingImpl;
            setGSClientIcon(ugHomeTwo2BindingImpl.ugHomeHdvideoVaiw, this.binding2.ugHomeEasyVaiw, this.binding2.ugHomeAppVaiw, this.width);
            return this.binding2.getRoot();
        }
        UgHomeTwoBindingImpl ugHomeTwoBindingImpl = (UgHomeTwoBindingImpl) DataBindingUtil.inflate(inflater, C0899R.C0902layout.ug_home_two, null, false);
        this.binding = ugHomeTwoBindingImpl;
        setGSClientIcon(ugHomeTwoBindingImpl.ugHomeHdvideoVaiw, this.binding.ugHomeEasyVaiw, this.binding.ugHomeAppVaiw, this.width);
        return this.binding.getRoot();
    }

    public void setGSClientIcon(UgHomeImageView imageView1, UgHomeImageView imageView2, UgHomeImageView imageView3, int width) {
        if (ClientManager.getInstance().isGSClient() && (width == 1024 || width == 1280)) {
            imageView2.setImageResource(C0899R.C0900drawable.ug_home_easy_gs_selector);
            imageView3.setImageResource(C0899R.C0900drawable.ug_home_app_gs_selector);
        } else if (ClientManager.getInstance().isGSClient() && width == 1920) {
            imageView1.setImageResource(C0899R.C0900drawable.ug_home_hdvideo_gs_selector);
            imageView2.setImageResource(C0899R.C0900drawable.ug_home_easy_gs_selector);
            imageView3.setImageResource(C0899R.C0900drawable.ug_home_app_gs_selector);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            this.binding2.setViewModel(this.viewModel);
            this.binding2.ugHomeHdvideoVaiw.setOnFocusChangeListener(this);
            this.binding2.ugHomeAppVaiw.setOnFocusChangeListener(this);
            this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() { // from class: com.wits.ksw.launcher.view.ug.UgHomeFragmentTwo.1
                @Override // android.arch.lifecycle.Observer
                public void onChanged(UgPager ugPager) {
                    if (ugPager.index == 1) {
                        if (ugPager.left) {
                            WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding2.ugHomeHdvideoVaiw);
                        } else if (ugPager.right) {
                            WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding2.ugHomeAppVaiw);
                        }
                    }
                }
            });
            return;
        }
        this.binding.setViewModel(this.viewModel);
        this.binding.ugHomeHdvideoVaiw.setOnFocusChangeListener(this);
        this.binding.ugHomeEasyVaiw.setOnFocusChangeListener(this);
        this.mainActivity.select.observe(getActivity(), new Observer<UgPager>() { // from class: com.wits.ksw.launcher.view.ug.UgHomeFragmentTwo.2
            @Override // android.arch.lifecycle.Observer
            public void onChanged(UgPager ugPager) {
                if (ugPager.index == 1) {
                    if (ugPager.left) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding.ugHomeHdvideoVaiw);
                    } else if (ugPager.right) {
                        WiewFocusUtils.setViewRequestFocus(UgHomeFragmentTwo.this.binding.ugHomeEasyVaiw);
                    }
                }
            }
        });
    }

    public void setCurrentItem(int item) {
        if (UiThemeUtils.isCommon_UI_GS_UG_1024(getActivity())) {
            try {
                if (MainActivity.mainActivity.gsug2Binding.ugViewPage.getCurrentItem() != item) {
                    MainActivity.mainActivity.gsug2Binding.ugViewPage.setCurrentItem(item);
                    return;
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            if (MainActivity.mainActivity.ugBinding.ugViewPage.getCurrentItem() != item) {
                MainActivity.mainActivity.ugBinding.ugViewPage.setCurrentItem(item);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setCurrentItem(1);
        }
    }
}
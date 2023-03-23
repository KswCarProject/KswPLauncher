package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.FragmentDashboardEditBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID8FragmentDashboard extends Fragment {
    private static final String TAG = "ID8FragmentDashboard";
    public static final int iconResId = 2131233428;
    public static final String name = "DASHBOARD";
    public static final int nameResId = 2131558787;
    private FragmentDashboardEditBinding mEditBinding;
    private LauncherViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.of((Fragment) this).get(LauncherViewModel.class);
        FragmentDashboardEditBinding fragmentDashboardEditBinding = (FragmentDashboardEditBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_edit, container, false);
        this.mEditBinding = fragmentDashboardEditBinding;
        fragmentDashboardEditBinding.setDashboardViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "DASHBOARD", R.drawable.id8_main_left_icon_dashboard, R.string.ksw_id8_dashboard));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("DASHBOARD");
        return view;
    }
}

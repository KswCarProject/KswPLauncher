package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.FragmentDashboardgsGsEditBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID8GsFragmentDashboard extends Fragment {
    private static final String TAG = "ID8FragmentDashboard";
    public static final int iconResId = 2131233428;
    public static final String name = "DASHBOARD";
    public static final int nameResId = 2131558787;
    private FragmentDashboardgsGsEditBinding mEditBinding;
    private LauncherViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.of((Fragment) this).get(LauncherViewModel.class);
        FragmentDashboardgsGsEditBinding fragmentDashboardgsGsEditBinding = (FragmentDashboardgsGsEditBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_gs_dashboardgs_edit, container, false);
        this.mEditBinding = fragmentDashboardgsGsEditBinding;
        fragmentDashboardgsGsEditBinding.setDashboardGsViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "DASHBOARD", R.drawable.id8_main_left_icon_dashboard, R.string.ksw_id8_dashboard));
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        view.setTag("DASHBOARD");
        return view;
    }
}

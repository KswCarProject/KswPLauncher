package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.CustomizeSeekBar;

/* loaded from: classes7.dex */
public abstract class BmwId8DashboardMusicLayoutBinding extends ViewDataBinding {
    public final TextView bmwId8DashboardMusicName;
    public final CustomizeSeekBar bmwId8DashboardMusicSeekbar;
    @Bindable
    protected DashboardViewModel mViewModel;

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected BmwId8DashboardMusicLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView bmwId8DashboardMusicName, CustomizeSeekBar bmwId8DashboardMusicSeekbar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8DashboardMusicName = bmwId8DashboardMusicName;
        this.bmwId8DashboardMusicSeekbar = bmwId8DashboardMusicSeekbar;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardMusicLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardMusicLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardMusicLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_music_layout, root, attachToRoot, component);
    }

    public static BmwId8DashboardMusicLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardMusicLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardMusicLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_music_layout, null, false, component);
    }

    public static BmwId8DashboardMusicLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardMusicLayoutBinding bind(View view, Object component) {
        return (BmwId8DashboardMusicLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_dashboard_music_layout);
    }
}

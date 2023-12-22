package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8SettingsNaviLayoutBinding extends ViewDataBinding {
    public final ImageView bmwId8SettingsNaviBack;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;
    public final RecyclerView naviRecycle;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsNaviLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bmwId8SettingsNaviBack, RecyclerView naviRecycle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsNaviBack = bmwId8SettingsNaviBack;
        this.naviRecycle = naviRecycle;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_navi_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_navi_layout, null, false, component);
    }

    public static BmwId8SettingsNaviLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_navi_layout);
    }
}

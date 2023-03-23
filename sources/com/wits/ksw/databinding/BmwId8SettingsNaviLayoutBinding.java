package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsNaviLayoutBinding extends ViewDataBinding {
    public final ImageView bmwId8SettingsNaviBack;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;
    public final RecyclerView naviRecycle;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsNaviLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bmwId8SettingsNaviBack2, RecyclerView naviRecycle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsNaviBack = bmwId8SettingsNaviBack2;
        this.naviRecycle = naviRecycle2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_navi_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_navi_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsNaviLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsNaviLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsNaviLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_navi_layout);
    }
}

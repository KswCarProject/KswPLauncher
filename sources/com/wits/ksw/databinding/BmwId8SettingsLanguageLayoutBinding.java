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

public abstract class BmwId8SettingsLanguageLayoutBinding extends ViewDataBinding {
    public final ImageView bmwId8SettingsLanguageBack;
    public final RecyclerView languageRecycle;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsLanguageLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bmwId8SettingsLanguageBack2, RecyclerView languageRecycle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsLanguageBack = bmwId8SettingsLanguageBack2;
        this.languageRecycle = languageRecycle2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsLanguageLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsLanguageLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsLanguageLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_language_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsLanguageLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsLanguageLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsLanguageLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_language_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsLanguageLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsLanguageLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsLanguageLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_language_layout);
    }
}

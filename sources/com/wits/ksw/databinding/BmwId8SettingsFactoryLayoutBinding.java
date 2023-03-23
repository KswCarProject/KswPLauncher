package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsFactoryLayoutBinding extends ViewDataBinding {
    public final ImageView bmwId8SettingsFactory0Btn;
    public final ImageView bmwId8SettingsFactory1Btn;
    public final ImageView bmwId8SettingsFactory2Btn;
    public final ImageView bmwId8SettingsFactory3Btn;
    public final ImageView bmwId8SettingsFactory4Btn;
    public final ImageView bmwId8SettingsFactory5Btn;
    public final ImageView bmwId8SettingsFactory6Btn;
    public final ImageView bmwId8SettingsFactory7Btn;
    public final ImageView bmwId8SettingsFactory8Btn;
    public final ImageView bmwId8SettingsFactory9Btn;
    public final ImageView bmwId8SettingsFactoryDelBtn;
    public final ImageView bmwId8SettingsFactoryEnterBtn;
    public final TextView bmwId8SettingsFactoryPsw;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BmwId8SettingsFactoryLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView bmwId8SettingsFactory0Btn2, ImageView bmwId8SettingsFactory1Btn2, ImageView bmwId8SettingsFactory2Btn2, ImageView bmwId8SettingsFactory3Btn2, ImageView bmwId8SettingsFactory4Btn2, ImageView bmwId8SettingsFactory5Btn2, ImageView bmwId8SettingsFactory6Btn2, ImageView bmwId8SettingsFactory7Btn2, ImageView bmwId8SettingsFactory8Btn2, ImageView bmwId8SettingsFactory9Btn2, ImageView bmwId8SettingsFactoryDelBtn2, ImageView bmwId8SettingsFactoryEnterBtn2, TextView bmwId8SettingsFactoryPsw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsFactory0Btn = bmwId8SettingsFactory0Btn2;
        this.bmwId8SettingsFactory1Btn = bmwId8SettingsFactory1Btn2;
        this.bmwId8SettingsFactory2Btn = bmwId8SettingsFactory2Btn2;
        this.bmwId8SettingsFactory3Btn = bmwId8SettingsFactory3Btn2;
        this.bmwId8SettingsFactory4Btn = bmwId8SettingsFactory4Btn2;
        this.bmwId8SettingsFactory5Btn = bmwId8SettingsFactory5Btn2;
        this.bmwId8SettingsFactory6Btn = bmwId8SettingsFactory6Btn2;
        this.bmwId8SettingsFactory7Btn = bmwId8SettingsFactory7Btn2;
        this.bmwId8SettingsFactory8Btn = bmwId8SettingsFactory8Btn2;
        this.bmwId8SettingsFactory9Btn = bmwId8SettingsFactory9Btn2;
        this.bmwId8SettingsFactoryDelBtn = bmwId8SettingsFactoryDelBtn2;
        this.bmwId8SettingsFactoryEnterBtn = bmwId8SettingsFactoryEnterBtn2;
        this.bmwId8SettingsFactoryPsw = bmwId8SettingsFactoryPsw2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsFactoryLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsFactoryLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsFactoryLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_factory_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsFactoryLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsFactoryLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsFactoryLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_factory_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsFactoryLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsFactoryLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsFactoryLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_factory_layout);
    }
}

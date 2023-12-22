package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

/* loaded from: classes7.dex */
public abstract class ZlinkWeatherFragment extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mZlinkWeatherViewModel;

    public abstract void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel);

    protected ZlinkWeatherFragment(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AlsID7ViewModel getZlinkWeatherViewModel() {
        return this.mZlinkWeatherViewModel;
    }

    public static ZlinkWeatherFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ZlinkWeatherFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ZlinkWeatherFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_zlink_weather, root, attachToRoot, component);
    }

    public static ZlinkWeatherFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ZlinkWeatherFragment inflate(LayoutInflater inflater, Object component) {
        return (ZlinkWeatherFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_zlink_weather, null, false, component);
    }

    public static ZlinkWeatherFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ZlinkWeatherFragment bind(View view, Object component) {
        return (ZlinkWeatherFragment) bind(component, view, C0899R.C0902layout.als_id7_fragment_zlink_weather);
    }
}

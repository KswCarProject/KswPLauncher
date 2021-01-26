package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public abstract class NaviCarFragment extends ViewDataBinding {
    @NonNull
    public final AlsId7SubCarViewBinding carinfoLayout;
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    @NonNull
    public final AlsId7SubNaviViewBinding naviLayout;

    public abstract void setNaviCarViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected NaviCarFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, AlsId7SubCarViewBinding carinfoLayout2, AlsId7SubNaviViewBinding naviLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carinfoLayout = carinfoLayout2;
        setContainedBinding(this.carinfoLayout);
        this.naviLayout = naviLayout2;
        setContainedBinding(this.naviLayout);
    }

    @Nullable
    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    @NonNull
    public static NaviCarFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NaviCarFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (NaviCarFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_navi_car, root, attachToRoot, component);
    }

    @NonNull
    public static NaviCarFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NaviCarFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (NaviCarFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_navi_car, (ViewGroup) null, false, component);
    }

    public static NaviCarFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static NaviCarFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (NaviCarFragment) bind(component, view, R.layout.als_id7_fragment_navi_car);
    }
}

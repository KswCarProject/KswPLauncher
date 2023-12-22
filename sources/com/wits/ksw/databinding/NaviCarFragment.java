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
public abstract class NaviCarFragment extends ViewDataBinding {
    public final AlsId7SubCarViewBinding carinfoLayout;
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    public final AlsId7SubNaviViewBinding naviLayout;

    public abstract void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel);

    protected NaviCarFragment(Object _bindingComponent, View _root, int _localFieldCount, AlsId7SubCarViewBinding carinfoLayout, AlsId7SubNaviViewBinding naviLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carinfoLayout = carinfoLayout;
        this.naviLayout = naviLayout;
    }

    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    public static NaviCarFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviCarFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NaviCarFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_navi_car, root, attachToRoot, component);
    }

    public static NaviCarFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviCarFragment inflate(LayoutInflater inflater, Object component) {
        return (NaviCarFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_navi_car, null, false, component);
    }

    public static NaviCarFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviCarFragment bind(View view, Object component) {
        return (NaviCarFragment) bind(component, view, C0899R.C0902layout.als_id7_fragment_navi_car);
    }
}

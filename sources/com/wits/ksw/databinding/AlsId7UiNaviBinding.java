package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class AlsId7UiNaviBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final AlsId7UiSubNaviViewBinding naviLayout;
    public final AlsId7UiSubPhoneViewBinding phoneLayout;

    public abstract void setNaviViewModel(LauncherViewModel NaviViewModel);

    protected AlsId7UiNaviBinding(Object _bindingComponent, View _root, int _localFieldCount, AlsId7UiSubNaviViewBinding naviLayout, AlsId7UiSubPhoneViewBinding phoneLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviLayout = naviLayout;
        this.phoneLayout = phoneLayout;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static AlsId7UiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_als_id7_ui_navi, root, attachToRoot, component);
    }

    public static AlsId7UiNaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_als_id7_ui_navi, null, false, component);
    }

    public static AlsId7UiNaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviBinding bind(View view, Object component) {
        return (AlsId7UiNaviBinding) bind(component, view, C0899R.C0902layout.fragment_als_id7_ui_navi);
    }
}

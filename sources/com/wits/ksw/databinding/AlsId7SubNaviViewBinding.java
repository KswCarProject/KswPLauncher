package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7SubNaviViewBinding extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    public final ConstraintLayout naviConstraintLayout;
    public final CustomBmwImageView naviImageView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel);

    protected AlsId7SubNaviViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout naviConstraintLayout, CustomBmwImageView naviImageView, TextView textView2, TextView textView3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviConstraintLayout = naviConstraintLayout;
        this.naviImageView = naviImageView;
        this.textView2 = textView2;
        this.textView3 = textView3;
    }

    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_navi_view, root, attachToRoot, component);
    }

    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_navi_view, null, false, component);
    }

    public static AlsId7SubNaviViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding bind(View view, Object component) {
        return (AlsId7SubNaviViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_navi_view);
    }
}

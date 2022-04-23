package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class AlsId7SubNaviViewBinding extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    public final ConstraintLayout naviConstraintLayout;
    public final CustomBmwImageView naviImageView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviCarViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubNaviViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout naviConstraintLayout2, CustomBmwImageView naviImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviConstraintLayout = naviConstraintLayout2;
        this.naviImageView = naviImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_navi_view, root, attachToRoot, component);
    }

    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_navi_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubNaviViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubNaviViewBinding bind(View view, Object component) {
        return (AlsId7SubNaviViewBinding) bind(component, view, R.layout.als_id7_sub_navi_view);
    }
}

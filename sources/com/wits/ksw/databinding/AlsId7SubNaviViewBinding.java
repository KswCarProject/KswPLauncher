package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    public final ConstraintLayout naviConstraintLayout;
    @NonNull
    public final CustomBmwImageView naviImageView;
    @NonNull
    public final TextView textView2;
    @NonNull
    public final TextView textView3;

    public abstract void setNaviCarViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubNaviViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout naviConstraintLayout2, CustomBmwImageView naviImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviConstraintLayout = naviConstraintLayout2;
        this.naviImageView = naviImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    @Nullable
    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    @NonNull
    public static AlsId7SubNaviViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubNaviViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AlsId7SubNaviViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_navi_view, root, attachToRoot, component);
    }

    @NonNull
    public static AlsId7SubNaviViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubNaviViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AlsId7SubNaviViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_navi_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubNaviViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AlsId7SubNaviViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AlsId7SubNaviViewBinding) bind(component, view, R.layout.als_id7_sub_navi_view);
    }
}

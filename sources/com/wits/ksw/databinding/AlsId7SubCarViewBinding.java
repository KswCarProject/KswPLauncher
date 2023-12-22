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
public abstract class AlsId7SubCarViewBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomBmwImageView carImageView;
    public final TextView dayTextView;
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    public final TextView monthTextView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel);

    protected AlsId7SubCarViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, CustomBmwImageView carImageView, TextView dayTextView, TextView monthTextView, TextView textView2, TextView textView3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.carImageView = carImageView;
        this.dayTextView = dayTextView;
        this.monthTextView = monthTextView;
        this.textView2 = textView2;
        this.textView3 = textView3;
    }

    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_car_view, root, attachToRoot, component);
    }

    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_car_view, null, false, component);
    }

    public static AlsId7SubCarViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding bind(View view, Object component) {
        return (AlsId7SubCarViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_car_view);
    }
}

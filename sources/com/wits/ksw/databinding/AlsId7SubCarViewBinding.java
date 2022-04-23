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

public abstract class AlsId7SubCarViewBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomBmwImageView carImageView;
    public final TextView dayTextView;
    @Bindable
    protected AlsID7ViewModel mNaviCarViewModel;
    public final TextView monthTextView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviCarViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubCarViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, CustomBmwImageView carImageView2, TextView dayTextView2, TextView monthTextView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.carImageView = carImageView2;
        this.dayTextView = dayTextView2;
        this.monthTextView = monthTextView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    public AlsID7ViewModel getNaviCarViewModel() {
        return this.mNaviCarViewModel;
    }

    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_car_view, root, attachToRoot, component);
    }

    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_car_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubCarViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubCarViewBinding bind(View view, Object component) {
        return (AlsId7SubCarViewBinding) bind(component, view, R.layout.als_id7_sub_car_view);
    }
}

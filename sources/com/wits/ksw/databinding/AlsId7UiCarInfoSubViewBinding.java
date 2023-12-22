package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7UiCarInfoSubViewBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomSkinImageView carImageView;
    public final ImageView carInfoImageView;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setCarViewModel(LauncherViewModel CarViewModel);

    protected AlsId7UiCarInfoSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, CustomSkinImageView carImageView, ImageView carInfoImageView, TextView textView2, TextView textView3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.carImageView = carImageView;
        this.carInfoImageView = carInfoImageView;
        this.textView2 = textView2;
        this.textView3 = textView3;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static AlsId7UiCarInfoSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiCarInfoSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_car_view, root, attachToRoot, component);
    }

    public static AlsId7UiCarInfoSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiCarInfoSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_car_view, null, false, component);
    }

    public static AlsId7UiCarInfoSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoSubViewBinding bind(View view, Object component) {
        return (AlsId7UiCarInfoSubViewBinding) bind(component, view, C0899R.C0902layout.als_id7_ui_sub_car_view);
    }
}

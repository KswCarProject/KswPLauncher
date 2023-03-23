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

public abstract class AlsId7SubZlinkViewBinding extends ViewDataBinding {
    public final ConstraintLayout dashboardConstraintLayout;
    @Bindable
    protected AlsID7ViewModel mZlinkWeatherViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final CustomBmwImageView zlinkImageView;

    public abstract void setZlinkWeatherViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubZlinkViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout dashboardConstraintLayout2, TextView textView22, TextView textView32, CustomBmwImageView zlinkImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dashboardConstraintLayout = dashboardConstraintLayout2;
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.zlinkImageView = zlinkImageView2;
    }

    public AlsID7ViewModel getZlinkWeatherViewModel() {
        return this.mZlinkWeatherViewModel;
    }

    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubZlinkViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_zlink_view, root, attachToRoot, component);
    }

    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubZlinkViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_zlink_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubZlinkViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding bind(View view, Object component) {
        return (AlsId7SubZlinkViewBinding) bind(component, view, R.layout.als_id7_sub_zlink_view);
    }
}

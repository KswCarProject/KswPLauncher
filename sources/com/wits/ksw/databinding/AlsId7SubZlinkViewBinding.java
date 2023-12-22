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
public abstract class AlsId7SubZlinkViewBinding extends ViewDataBinding {
    public final ConstraintLayout dashboardConstraintLayout;
    @Bindable
    protected AlsID7ViewModel mZlinkWeatherViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final CustomBmwImageView zlinkImageView;

    public abstract void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel);

    protected AlsId7SubZlinkViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout dashboardConstraintLayout, TextView textView2, TextView textView3, CustomBmwImageView zlinkImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dashboardConstraintLayout = dashboardConstraintLayout;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.zlinkImageView = zlinkImageView;
    }

    public AlsID7ViewModel getZlinkWeatherViewModel() {
        return this.mZlinkWeatherViewModel;
    }

    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubZlinkViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_zlink_view, root, attachToRoot, component);
    }

    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubZlinkViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_zlink_view, null, false, component);
    }

    public static AlsId7SubZlinkViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubZlinkViewBinding bind(View view, Object component) {
        return (AlsId7SubZlinkViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_zlink_view);
    }
}

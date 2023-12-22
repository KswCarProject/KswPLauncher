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
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class Id7SubPhoneViewBinding extends ViewDataBinding {
    public final TextView dayTextView;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final TextView monthTextView;
    public final TextView phoneConnectionTextView;
    public final ConstraintLayout phoneConstraintLayout;
    public final CustomBmwImageView phoneImageView;
    public final TextView textView2;

    public abstract void setNaviViewModel(LauncherViewModel NaviViewModel);

    protected Id7SubPhoneViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView dayTextView, TextView monthTextView, TextView phoneConnectionTextView, ConstraintLayout phoneConstraintLayout, CustomBmwImageView phoneImageView, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dayTextView = dayTextView;
        this.monthTextView = monthTextView;
        this.phoneConnectionTextView = phoneConnectionTextView;
        this.phoneConstraintLayout = phoneConstraintLayout;
        this.phoneImageView = phoneImageView;
        this.textView2 = textView2;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static Id7SubPhoneViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubPhoneViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_phone_view, root, attachToRoot, component);
    }

    public static Id7SubPhoneViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubPhoneViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_phone_view, null, false, component);
    }

    public static Id7SubPhoneViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewBinding bind(View view, Object component) {
        return (Id7SubPhoneViewBinding) bind(component, view, C0899R.C0902layout.id7_sub_phone_view);
    }
}

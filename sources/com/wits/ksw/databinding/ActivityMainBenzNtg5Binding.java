package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainBenzNtg5Binding extends ViewDataBinding {
    public final ImageView controlBtn;
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcNTG5ViewModel mMBcVieModel;
    public final RecyclerView recyclerView2;

    public abstract void setMBcVieModel(BcNTG5ViewModel mBcVieModel);

    protected ActivityMainBenzNtg5Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn, ConstraintLayout linearLayout3, RecyclerView recyclerView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn;
        this.linearLayout3 = linearLayout3;
        this.recyclerView2 = recyclerView2;
    }

    public BcNTG5ViewModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static ActivityMainBenzNtg5Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzNtg5Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBenzNtg5Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_ntg5, root, attachToRoot, component);
    }

    public static ActivityMainBenzNtg5Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzNtg5Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBenzNtg5Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_ntg5, null, false, component);
    }

    public static ActivityMainBenzNtg5Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzNtg5Binding bind(View view, Object component) {
        return (ActivityMainBenzNtg5Binding) bind(component, view, C0899R.C0902layout.activity_main_benz_ntg5);
    }
}

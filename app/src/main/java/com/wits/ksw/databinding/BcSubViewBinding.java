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
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.BcItem;

public abstract class BcSubViewBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout BcItemConstraintLayout;
    @Bindable
    protected BcItem mListItem;

    public abstract void setListItem(@Nullable BcItem bcItem);

    protected BcSubViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout BcItemConstraintLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.BcItemConstraintLayout = BcItemConstraintLayout2;
    }

    @Nullable
    public BcItem getListItem() {
        return this.mListItem;
    }

    @NonNull
    public static BcSubViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BcSubViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (BcSubViewBinding) DataBindingUtil.inflate(inflater, R.layout.bc_sub_view, root, attachToRoot, component);
    }

    @NonNull
    public static BcSubViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BcSubViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (BcSubViewBinding) DataBindingUtil.inflate(inflater, R.layout.bc_sub_view, (ViewGroup) null, false, component);
    }

    public static BcSubViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BcSubViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (BcSubViewBinding) bind(component, view, R.layout.bc_sub_view);
    }
}

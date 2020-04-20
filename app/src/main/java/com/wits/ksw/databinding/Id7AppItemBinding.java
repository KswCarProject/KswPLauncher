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
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.AppInfo;

public abstract class Id7AppItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout BcItemConstraintLayout;
    @Bindable
    protected AppInfo mListItem;
    @NonNull
    public final ImageView nameImageView;
    @NonNull
    public final TextView textView;

    public abstract void setListItem(@Nullable AppInfo appInfo);

    protected Id7AppItemBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout BcItemConstraintLayout2, ImageView nameImageView2, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.BcItemConstraintLayout = BcItemConstraintLayout2;
        this.nameImageView = nameImageView2;
        this.textView = textView2;
    }

    @Nullable
    public AppInfo getListItem() {
        return this.mListItem;
    }

    @NonNull
    public static Id7AppItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7AppItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7AppItemBinding) DataBindingUtil.inflate(inflater, R.layout.id7_app_item, root, attachToRoot, component);
    }

    @NonNull
    public static Id7AppItemBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7AppItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7AppItemBinding) DataBindingUtil.inflate(inflater, R.layout.id7_app_item, (ViewGroup) null, false, component);
    }

    public static Id7AppItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7AppItemBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7AppItemBinding) bind(component, view, R.layout.id7_app_item);
    }
}

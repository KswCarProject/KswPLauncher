package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.AppInfo;

/* loaded from: classes7.dex */
public abstract class Id7AppItemBinding extends ViewDataBinding {
    public final ConstraintLayout BcItemConstraintLayout;
    public final FrameLayout appIconView;
    public final TextView badgeNumber;
    @Bindable
    protected AppInfo mListItem;
    public final ImageView nameImageView;
    public final TextView textView;

    public abstract void setListItem(AppInfo listItem);

    protected Id7AppItemBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout BcItemConstraintLayout, FrameLayout appIconView, TextView badgeNumber, ImageView nameImageView, TextView textView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.BcItemConstraintLayout = BcItemConstraintLayout;
        this.appIconView = appIconView;
        this.badgeNumber = badgeNumber;
        this.nameImageView = nameImageView;
        this.textView = textView;
    }

    public AppInfo getListItem() {
        return this.mListItem;
    }

    public static Id7AppItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7AppItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7AppItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_app_item, root, attachToRoot, component);
    }

    public static Id7AppItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7AppItemBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7AppItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_app_item, null, false, component);
    }

    public static Id7AppItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7AppItemBinding bind(View view, Object component) {
        return (Id7AppItemBinding) bind(component, view, C0899R.C0902layout.id7_app_item);
    }
}

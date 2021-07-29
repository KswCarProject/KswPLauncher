package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.BcItem;

public abstract class BcSubViewBinding extends ViewDataBinding {
    public final ConstraintLayout BcItemConstraintLayout;
    @Bindable
    protected BcItem mListItem;

    public abstract void setListItem(BcItem bcItem);

    protected BcSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout BcItemConstraintLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.BcItemConstraintLayout = BcItemConstraintLayout2;
    }

    public BcItem getListItem() {
        return this.mListItem;
    }

    public static BcSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BcSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bc_sub_view, root, attachToRoot, component);
    }

    public static BcSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (BcSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bc_sub_view, (ViewGroup) null, false, component);
    }

    public static BcSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding bind(View view, Object component) {
        return (BcSubViewBinding) bind(component, view, R.layout.bc_sub_view);
    }
}

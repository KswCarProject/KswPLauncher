package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.BcItem;

/* loaded from: classes7.dex */
public abstract class BcSubViewBinding extends ViewDataBinding {
    public final ConstraintLayout BcItemConstraintLayout;
    @Bindable
    protected BcItem mListItem;

    public abstract void setListItem(BcItem listItem);

    protected BcSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout BcItemConstraintLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.BcItemConstraintLayout = BcItemConstraintLayout;
    }

    public BcItem getListItem() {
        return this.mListItem;
    }

    public static BcSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BcSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bc_sub_view, root, attachToRoot, component);
    }

    public static BcSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (BcSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bc_sub_view, null, false, component);
    }

    public static BcSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcSubViewBinding bind(View view, Object component) {
        return (BcSubViewBinding) bind(component, view, C0899R.C0902layout.bc_sub_view);
    }
}

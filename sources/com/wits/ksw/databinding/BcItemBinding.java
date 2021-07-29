package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class BcItemBinding extends ViewDataBinding {
    @Bindable
    protected BcItem mListItem;
    @Bindable
    protected BcVieModel mMBcVieModel;
    public final LinearLayout naviCusLinearLayout;

    public abstract void setListItem(BcItem bcItem);

    public abstract void setMBcVieModel(BcVieModel bcVieModel);

    protected BcItemBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout naviCusLinearLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviCusLinearLayout = naviCusLinearLayout2;
    }

    public BcItem getListItem() {
        return this.mListItem;
    }

    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static BcItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BcItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bc_item, root, attachToRoot, component);
    }

    public static BcItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcItemBinding inflate(LayoutInflater inflater, Object component) {
        return (BcItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bc_item, (ViewGroup) null, false, component);
    }

    public static BcItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcItemBinding bind(View view, Object component) {
        return (BcItemBinding) bind(component, view, R.layout.bc_item);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public abstract class BenzMbuxItemBinding extends ViewDataBinding {
    public final BenzMbuxItemView benzMbuxImageView;
    public final TextView benzMbuxTextView;
    @Bindable
    protected BenzMbuxBean mListItem;
    @Bindable
    protected BcVieModel mVieModel;
    public final RelativeLayout naviCusLinearLayout;
    public final View space;

    public abstract void setListItem(BenzMbuxBean benzMbuxBean);

    public abstract void setVieModel(BcVieModel bcVieModel);

    protected BenzMbuxItemBinding(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView benzMbuxImageView2, TextView benzMbuxTextView2, RelativeLayout naviCusLinearLayout2, View space2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxImageView = benzMbuxImageView2;
        this.benzMbuxTextView = benzMbuxTextView2;
        this.naviCusLinearLayout = naviCusLinearLayout2;
        this.space = space2;
    }

    public BenzMbuxBean getListItem() {
        return this.mListItem;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static BenzMbuxItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbuxItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_item, root, attachToRoot, component);
    }

    public static BenzMbuxItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxItemBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbuxItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_item, (ViewGroup) null, false, component);
    }

    public static BenzMbuxItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxItemBinding bind(View view, Object component) {
        return (BenzMbuxItemBinding) bind(component, view, R.layout.benz_mbux_item);
    }
}

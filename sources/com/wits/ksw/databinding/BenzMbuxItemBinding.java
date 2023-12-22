package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class BenzMbuxItemBinding extends ViewDataBinding {
    public final BenzMbuxItemView benzMbuxImageView;
    public final TextView benzMbuxTextView;
    @Bindable
    protected BenzMbuxBean mListItem;
    @Bindable
    protected BcVieModel mVieModel;
    public final RelativeLayout naviCusLinearLayout;
    public final View space;

    public abstract void setListItem(BenzMbuxBean listItem);

    public abstract void setVieModel(BcVieModel vieModel);

    protected BenzMbuxItemBinding(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView benzMbuxImageView, TextView benzMbuxTextView, RelativeLayout naviCusLinearLayout, View space) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxImageView = benzMbuxImageView;
        this.benzMbuxTextView = benzMbuxTextView;
        this.naviCusLinearLayout = naviCusLinearLayout;
        this.space = space;
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
        return (BenzMbuxItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_item, root, attachToRoot, component);
    }

    public static BenzMbuxItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxItemBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbuxItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_item, null, false, component);
    }

    public static BenzMbuxItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxItemBinding bind(View view, Object component) {
        return (BenzMbuxItemBinding) bind(component, view, C0899R.C0902layout.benz_mbux_item);
    }
}

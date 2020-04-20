package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    public final BenzMbuxItemView benzMbuxImageView;
    @NonNull
    public final TextView benzMbuxTextView;
    @Bindable
    protected BenzMbuxBean mListItem;
    @Bindable
    protected BcVieModel mVieModel;
    @NonNull
    public final RelativeLayout naviCusLinearLayout;
    @NonNull
    public final View space;

    public abstract void setListItem(@Nullable BenzMbuxBean benzMbuxBean);

    public abstract void setVieModel(@Nullable BcVieModel bcVieModel);

    protected BenzMbuxItemBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView benzMbuxImageView2, TextView benzMbuxTextView2, RelativeLayout naviCusLinearLayout2, View space2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxImageView = benzMbuxImageView2;
        this.benzMbuxTextView = benzMbuxTextView2;
        this.naviCusLinearLayout = naviCusLinearLayout2;
        this.space = space2;
    }

    @Nullable
    public BenzMbuxBean getListItem() {
        return this.mListItem;
    }

    @Nullable
    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    @NonNull
    public static BenzMbuxItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BenzMbuxItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (BenzMbuxItemBinding) DataBindingUtil.inflate(inflater, R.layout.benz_mbux_item, root, attachToRoot, component);
    }

    @NonNull
    public static BenzMbuxItemBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BenzMbuxItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (BenzMbuxItemBinding) DataBindingUtil.inflate(inflater, R.layout.benz_mbux_item, (ViewGroup) null, false, component);
    }

    public static BenzMbuxItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BenzMbuxItemBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (BenzMbuxItemBinding) bind(component, view, R.layout.benz_mbux_item);
    }
}

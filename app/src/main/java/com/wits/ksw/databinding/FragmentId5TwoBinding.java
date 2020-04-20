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
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class FragmentId5TwoBinding extends ViewDataBinding {
    @Bindable
    protected BcItem mListItem;
    @Bindable
    protected BcVieModel mMBcVieModel;
    @NonNull
    public final LinearLayout naviCusLinearLayout;

    public abstract void setListItem(@Nullable BcItem bcItem);

    public abstract void setMBcVieModel(@Nullable BcVieModel bcVieModel);

    protected FragmentId5TwoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, LinearLayout naviCusLinearLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviCusLinearLayout = naviCusLinearLayout2;
    }

    @Nullable
    public BcItem getListItem() {
        return this.mListItem;
    }

    @Nullable
    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static FragmentId5TwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentId5TwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FragmentId5TwoBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_id5_two, root, attachToRoot, component);
    }

    @NonNull
    public static FragmentId5TwoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentId5TwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FragmentId5TwoBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_id5_two, (ViewGroup) null, false, component);
    }

    public static FragmentId5TwoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentId5TwoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FragmentId5TwoBinding) bind(component, view, R.layout.fragment_id5_two);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class FragmentId5TwoBinding extends ViewDataBinding {
    @Bindable
    protected BcItem mListItem;
    @Bindable
    protected BcVieModel mMBcVieModel;
    public final LinearLayout naviCusLinearLayout;

    public abstract void setListItem(BcItem listItem);

    public abstract void setMBcVieModel(BcVieModel mBcVieModel);

    protected FragmentId5TwoBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout naviCusLinearLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviCusLinearLayout = naviCusLinearLayout;
    }

    public BcItem getListItem() {
        return this.mListItem;
    }

    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static FragmentId5TwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentId5TwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentId5TwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_id5_two, root, attachToRoot, component);
    }

    public static FragmentId5TwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentId5TwoBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentId5TwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_id5_two, null, false, component);
    }

    public static FragmentId5TwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentId5TwoBinding bind(View view, Object component) {
        return (FragmentId5TwoBinding) bind(component, view, C0899R.C0902layout.fragment_id5_two);
    }
}

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
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Bean;

/* loaded from: classes7.dex */
public abstract class BenzMbux2021ItemBinding extends ViewDataBinding {
    public final BenzMbuxItemView benzMbux2021ImageView;
    public final TextView benzMbux2021TextView;
    public final TextView benzMbux2021Tip;
    @Bindable
    protected BenzMbux2021Bean mListItem;
    @Bindable
    protected BcVieModel mVieModel;
    public final RelativeLayout rlContain;
    public final View space;

    public abstract void setListItem(BenzMbux2021Bean listItem);

    public abstract void setVieModel(BcVieModel vieModel);

    protected BenzMbux2021ItemBinding(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView benzMbux2021ImageView, TextView benzMbux2021TextView, TextView benzMbux2021Tip, RelativeLayout rlContain, View space) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbux2021ImageView = benzMbux2021ImageView;
        this.benzMbux2021TextView = benzMbux2021TextView;
        this.benzMbux2021Tip = benzMbux2021Tip;
        this.rlContain = rlContain;
        this.space = space;
    }

    public BenzMbux2021Bean getListItem() {
        return this.mListItem;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static BenzMbux2021ItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbux2021ItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_item, root, attachToRoot, component);
    }

    public static BenzMbux2021ItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ItemBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbux2021ItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_item, null, false, component);
    }

    public static BenzMbux2021ItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ItemBinding bind(View view, Object component) {
        return (BenzMbux2021ItemBinding) bind(component, view, C0899R.C0902layout.benz_mbux_2021_item);
    }
}

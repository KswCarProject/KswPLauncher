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
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.view.ntg5.NTG5ImageView;

/* loaded from: classes7.dex */
public abstract class BcNtg5ItemBinding extends ViewDataBinding {
    public final NTG5ImageView appIcon;
    public final TextView appName;
    @Bindable
    protected BcItem mListItem;
    @Bindable
    protected BcNTG5ViewModel mMBcVieModel;
    public final RelativeLayout naviCusLinearLayout;

    public abstract void setListItem(BcItem listItem);

    public abstract void setMBcVieModel(BcNTG5ViewModel mBcVieModel);

    protected BcNtg5ItemBinding(Object _bindingComponent, View _root, int _localFieldCount, NTG5ImageView appIcon, TextView appName, RelativeLayout naviCusLinearLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appIcon = appIcon;
        this.appName = appName;
        this.naviCusLinearLayout = naviCusLinearLayout;
    }

    public BcItem getListItem() {
        return this.mListItem;
    }

    public BcNTG5ViewModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static BcNtg5ItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcNtg5ItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BcNtg5ItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bc_ntg5_item, root, attachToRoot, component);
    }

    public static BcNtg5ItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcNtg5ItemBinding inflate(LayoutInflater inflater, Object component) {
        return (BcNtg5ItemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bc_ntg5_item, null, false, component);
    }

    public static BcNtg5ItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BcNtg5ItemBinding bind(View view, Object component) {
        return (BcNtg5ItemBinding) bind(component, view, C0899R.C0902layout.bc_ntg5_item);
    }
}

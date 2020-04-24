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
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.view.ntg5.NTG5ImageView;

public abstract class BcNtg5ItemBinding extends ViewDataBinding {
    @NonNull
    public final NTG5ImageView appIcon;
    @NonNull
    public final TextView appName;
    @Bindable
    protected BcItem mListItem;
    @Bindable
    protected BcNTG5ViewModel mMBcVieModel;
    @NonNull
    public final RelativeLayout naviCusLinearLayout;

    public abstract void setListItem(@Nullable BcItem bcItem);

    public abstract void setMBcVieModel(@Nullable BcNTG5ViewModel bcNTG5ViewModel);

    protected BcNtg5ItemBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, NTG5ImageView appIcon2, TextView appName2, RelativeLayout naviCusLinearLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appIcon = appIcon2;
        this.appName = appName2;
        this.naviCusLinearLayout = naviCusLinearLayout2;
    }

    @Nullable
    public BcItem getListItem() {
        return this.mListItem;
    }

    @Nullable
    public BcNTG5ViewModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static BcNtg5ItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BcNtg5ItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (BcNtg5ItemBinding) DataBindingUtil.inflate(inflater, R.layout.bc_ntg5_item, root, attachToRoot, component);
    }

    @NonNull
    public static BcNtg5ItemBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BcNtg5ItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (BcNtg5ItemBinding) DataBindingUtil.inflate(inflater, R.layout.bc_ntg5_item, (ViewGroup) null, false, component);
    }

    public static BcNtg5ItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BcNtg5ItemBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (BcNtg5ItemBinding) bind(component, view, R.layout.bc_ntg5_item);
    }
}

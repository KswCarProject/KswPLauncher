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
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiRightLogoBinding extends ViewDataBinding {
    @NonNull
    public final View KSWA4LRightShowLogo;
    @NonNull
    public final ImageView KSWYouAudi;
    @Bindable
    protected AudiViewModel mVm;

    public abstract void setVm(@Nullable AudiViewModel audiViewModel);

    protected AudiRightLogoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, View KSWA4LRightShowLogo2, ImageView KSWYouAudi2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.KSWA4LRightShowLogo = KSWA4LRightShowLogo2;
        this.KSWYouAudi = KSWYouAudi2;
    }

    @Nullable
    public AudiViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiRightLogoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightLogoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiRightLogoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_logo, root, attachToRoot, component);
    }

    @NonNull
    public static AudiRightLogoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightLogoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiRightLogoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_logo, (ViewGroup) null, false, component);
    }

    public static AudiRightLogoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiRightLogoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiRightLogoBinding) bind(component, view, R.layout.audi_right_logo);
    }
}

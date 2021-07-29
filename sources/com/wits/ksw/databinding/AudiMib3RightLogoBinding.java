package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiMib3RightLogoBinding extends ViewDataBinding {
    public final RelativeLayout KSWA4LRightShowLogo;
    public final ImageView KSWYouAudi;
    @Bindable
    protected AudiViewModel mVm;

    public abstract void setVm(AudiViewModel audiViewModel);

    protected AudiMib3RightLogoBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout KSWA4LRightShowLogo2, ImageView KSWYouAudi2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.KSWA4LRightShowLogo = KSWA4LRightShowLogo2;
        this.KSWYouAudi = KSWYouAudi2;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3RightLogoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_right_logo, root, attachToRoot, component);
    }

    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3RightLogoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_right_logo, (ViewGroup) null, false, component);
    }

    public static AudiMib3RightLogoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding bind(View view, Object component) {
        return (AudiMib3RightLogoBinding) bind(component, view, R.layout.audi_mib3_right_logo);
    }
}

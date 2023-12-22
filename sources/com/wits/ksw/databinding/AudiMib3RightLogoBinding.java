package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3RightLogoBinding extends ViewDataBinding {
    public final RelativeLayout KSWA4LRightShowLogo;
    public final ImageView KSWYouAudi;
    @Bindable
    protected AudiViewModel mVm;

    public abstract void setVm(AudiViewModel vm);

    protected AudiMib3RightLogoBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout KSWA4LRightShowLogo, ImageView KSWYouAudi) {
        super(_bindingComponent, _root, _localFieldCount);
        this.KSWA4LRightShowLogo = KSWA4LRightShowLogo;
        this.KSWYouAudi = KSWYouAudi;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3RightLogoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_logo, root, attachToRoot, component);
    }

    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3RightLogoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_logo, null, false, component);
    }

    public static AudiMib3RightLogoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightLogoBinding bind(View view, Object component) {
        return (AudiMib3RightLogoBinding) bind(component, view, C0899R.C0902layout.audi_mib3_right_logo);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3TyMainLayoutBinding extends ViewDataBinding {
    public final AudiMib3TyMainLeftBinding include;
    public final LinearLayout includeLl;
    public final LinearLayout indicatorAudiMib3Ty;
    @Bindable
    protected BcVieModel mViewModel;
    public final ViewPager viewPageAudiMib3Ty;

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3TyMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiMib3TyMainLeftBinding include, LinearLayout includeLl, LinearLayout indicatorAudiMib3Ty, ViewPager viewPageAudiMib3Ty) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include = include;
        this.includeLl = includeLl;
        this.indicatorAudiMib3Ty = indicatorAudiMib3Ty;
        this.viewPageAudiMib3Ty = viewPageAudiMib3Ty;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_ty_main_layout, root, attachToRoot, component);
    }

    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_ty_main_layout, null, false, component);
    }

    public static AudiMib3TyMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding bind(View view, Object component) {
        return (AudiMib3TyMainLayoutBinding) bind(component, view, C0899R.C0902layout.audi_mib3_ty_main_layout);
    }
}

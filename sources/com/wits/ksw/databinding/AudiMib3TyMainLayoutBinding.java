package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class AudiMib3TyMainLayoutBinding extends ViewDataBinding {
    public final AudiMib3TyMainLeftBinding include;
    public final LinearLayout includeLl;
    public final LinearLayout indicatorAudiMib3Ty;
    @Bindable
    protected BcVieModel mViewModel;
    public final ViewPager viewPageAudiMib3Ty;

    public abstract void setViewModel(BcVieModel bcVieModel);

    protected AudiMib3TyMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiMib3TyMainLeftBinding include2, LinearLayout includeLl2, LinearLayout indicatorAudiMib3Ty2, ViewPager viewPageAudiMib3Ty2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include = include2;
        this.includeLl = includeLl2;
        this.indicatorAudiMib3Ty = indicatorAudiMib3Ty2;
        this.viewPageAudiMib3Ty = viewPageAudiMib3Ty2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_ty_main_layout, root, attachToRoot, component);
    }

    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_ty_main_layout, (ViewGroup) null, false, component);
    }

    public static AudiMib3TyMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyMainLayoutBinding bind(View view, Object component) {
        return (AudiMib3TyMainLayoutBinding) bind(component, view, R.layout.audi_mib3_ty_main_layout);
    }
}

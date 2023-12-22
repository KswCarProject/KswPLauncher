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
public abstract class AudiMib3FyMainLayoutBinding extends ViewDataBinding {
    public final AudiMib3FyMainLeftBinding include;
    public final LinearLayout includeLl;
    public final LinearLayout indicatorAudimib3;
    @Bindable
    protected BcVieModel mViewModel;
    public final ViewPager viewPageAudiMib3;

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3FyMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiMib3FyMainLeftBinding include, LinearLayout includeLl, LinearLayout indicatorAudimib3, ViewPager viewPageAudiMib3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include = include;
        this.includeLl = includeLl;
        this.indicatorAudimib3 = indicatorAudimib3;
        this.viewPageAudiMib3 = viewPageAudiMib3;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3FyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3FyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fy_main_layout, root, attachToRoot, component);
    }

    public static AudiMib3FyMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3FyMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fy_main_layout, null, false, component);
    }

    public static AudiMib3FyMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLayoutBinding bind(View view, Object component) {
        return (AudiMib3FyMainLayoutBinding) bind(component, view, C0899R.C0902layout.audi_mib3_fy_main_layout);
    }
}

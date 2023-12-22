package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

/* loaded from: classes7.dex */
public abstract class FraBmwEvoId6GsThreeBinding extends ViewDataBinding {
    public final TextView bmwEvoId6GsHmoeAppsHintTextview;
    public final ConstraintLayout bmwEvoId6GsHmoeDashBtn;
    public final TextView bmwEvoId6GsHmoeOliHintTextview;
    public final TextView bmwEvoId6GsHmoePlinkHintTextview;
    public final TextView bmwEvoId6GsHmoeSetHintTextview;
    public final TextView bmwEvoId6GsHmoeSpeedTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(BmwId6gsViewMode vm);

    protected FraBmwEvoId6GsThreeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeAppsHintTextview, ConstraintLayout bmwEvoId6GsHmoeDashBtn, TextView bmwEvoId6GsHmoeOliHintTextview, TextView bmwEvoId6GsHmoePlinkHintTextview, TextView bmwEvoId6GsHmoeSetHintTextview, TextView bmwEvoId6GsHmoeSpeedTextview) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeAppsHintTextview = bmwEvoId6GsHmoeAppsHintTextview;
        this.bmwEvoId6GsHmoeDashBtn = bmwEvoId6GsHmoeDashBtn;
        this.bmwEvoId6GsHmoeOliHintTextview = bmwEvoId6GsHmoeOliHintTextview;
        this.bmwEvoId6GsHmoePlinkHintTextview = bmwEvoId6GsHmoePlinkHintTextview;
        this.bmwEvoId6GsHmoeSetHintTextview = bmwEvoId6GsHmoeSetHintTextview;
        this.bmwEvoId6GsHmoeSpeedTextview = bmwEvoId6GsHmoeSpeedTextview;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBmwEvoId6GsThreeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_three, root, attachToRoot, component);
    }

    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBmwEvoId6GsThreeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_three, null, false, component);
    }

    public static FraBmwEvoId6GsThreeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding bind(View view, Object component) {
        return (FraBmwEvoId6GsThreeBinding) bind(component, view, C0899R.C0902layout.fra_bmw_evo_id6_gs_three);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public abstract class FraBmwEvoId6GsThreeBinding extends ViewDataBinding {
    public final TextView bmwEvoId6GsHmoeAppsHintTextview;
    public final ConstraintLayout bmwEvoId6GsHmoeDashBtn;
    public final TextView bmwEvoId6GsHmoeOliHintTextview;
    public final TextView bmwEvoId6GsHmoePlinkHintTextview;
    public final TextView bmwEvoId6GsHmoeSetHintTextview;
    public final TextView bmwEvoId6GsHmoeSpeedTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(BmwId6gsViewMode bmwId6gsViewMode);

    protected FraBmwEvoId6GsThreeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeAppsHintTextview2, ConstraintLayout bmwEvoId6GsHmoeDashBtn2, TextView bmwEvoId6GsHmoeOliHintTextview2, TextView bmwEvoId6GsHmoePlinkHintTextview2, TextView bmwEvoId6GsHmoeSetHintTextview2, TextView bmwEvoId6GsHmoeSpeedTextview2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeAppsHintTextview = bmwEvoId6GsHmoeAppsHintTextview2;
        this.bmwEvoId6GsHmoeDashBtn = bmwEvoId6GsHmoeDashBtn2;
        this.bmwEvoId6GsHmoeOliHintTextview = bmwEvoId6GsHmoeOliHintTextview2;
        this.bmwEvoId6GsHmoePlinkHintTextview = bmwEvoId6GsHmoePlinkHintTextview2;
        this.bmwEvoId6GsHmoeSetHintTextview = bmwEvoId6GsHmoeSetHintTextview2;
        this.bmwEvoId6GsHmoeSpeedTextview = bmwEvoId6GsHmoeSpeedTextview2;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBmwEvoId6GsThreeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_bmw_evo_id6_gs_three, root, attachToRoot, component);
    }

    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBmwEvoId6GsThreeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_bmw_evo_id6_gs_three, (ViewGroup) null, false, component);
    }

    public static FraBmwEvoId6GsThreeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsThreeBinding bind(View view, Object component) {
        return (FraBmwEvoId6GsThreeBinding) bind(component, view, R.layout.fra_bmw_evo_id6_gs_three);
    }
}

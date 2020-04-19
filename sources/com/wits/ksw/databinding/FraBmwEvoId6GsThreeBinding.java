package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public abstract class FraBmwEvoId6GsThreeBinding extends ViewDataBinding {
    @NonNull
    public final TextView bmwEvoId6GsHmoeAppsHintTextview;
    @NonNull
    public final ConstraintLayout bmwEvoId6GsHmoeDashBtn;
    @NonNull
    public final TextView bmwEvoId6GsHmoeOliHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoePlinkHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeSetHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeSpeedTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(@Nullable BmwId6gsViewMode bmwId6gsViewMode);

    protected FraBmwEvoId6GsThreeBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeAppsHintTextview2, ConstraintLayout bmwEvoId6GsHmoeDashBtn2, TextView bmwEvoId6GsHmoeOliHintTextview2, TextView bmwEvoId6GsHmoePlinkHintTextview2, TextView bmwEvoId6GsHmoeSetHintTextview2, TextView bmwEvoId6GsHmoeSpeedTextview2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeAppsHintTextview = bmwEvoId6GsHmoeAppsHintTextview2;
        this.bmwEvoId6GsHmoeDashBtn = bmwEvoId6GsHmoeDashBtn2;
        this.bmwEvoId6GsHmoeOliHintTextview = bmwEvoId6GsHmoeOliHintTextview2;
        this.bmwEvoId6GsHmoePlinkHintTextview = bmwEvoId6GsHmoePlinkHintTextview2;
        this.bmwEvoId6GsHmoeSetHintTextview = bmwEvoId6GsHmoeSetHintTextview2;
        this.bmwEvoId6GsHmoeSpeedTextview = bmwEvoId6GsHmoeSpeedTextview2;
    }

    @Nullable
    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    @NonNull
    public static FraBmwEvoId6GsThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsThreeBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_three, root, attachToRoot, component);
    }

    @NonNull
    public static FraBmwEvoId6GsThreeBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsThreeBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_three, (ViewGroup) null, false, component);
    }

    public static FraBmwEvoId6GsThreeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FraBmwEvoId6GsThreeBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsThreeBinding) bind(component, view, R.layout.fra_bmw_evo_id6_gs_three);
    }
}

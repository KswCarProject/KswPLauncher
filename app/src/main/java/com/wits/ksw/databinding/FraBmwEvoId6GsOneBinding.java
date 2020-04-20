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

public abstract class FraBmwEvoId6GsOneBinding extends ViewDataBinding {
    @NonNull
    public final TextView bmwEvoId6GsHmoeMusicHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeMusicNameTextview;
    @NonNull
    public final ConstraintLayout bmwEvoId6GsHmoeNaviBtn;
    @NonNull
    public final TextView bmwEvoId6GsHmoeNaviHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeVideoHintTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;
    @NonNull
    public final TextView textView17;

    public abstract void setVm(@Nullable BmwId6gsViewMode bmwId6gsViewMode);

    protected FraBmwEvoId6GsOneBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeMusicHintTextview2, TextView bmwEvoId6GsHmoeMusicNameTextview2, ConstraintLayout bmwEvoId6GsHmoeNaviBtn2, TextView bmwEvoId6GsHmoeNaviHintTextview2, TextView bmwEvoId6GsHmoeVideoHintTextview2, TextView textView172) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeMusicHintTextview = bmwEvoId6GsHmoeMusicHintTextview2;
        this.bmwEvoId6GsHmoeMusicNameTextview = bmwEvoId6GsHmoeMusicNameTextview2;
        this.bmwEvoId6GsHmoeNaviBtn = bmwEvoId6GsHmoeNaviBtn2;
        this.bmwEvoId6GsHmoeNaviHintTextview = bmwEvoId6GsHmoeNaviHintTextview2;
        this.bmwEvoId6GsHmoeVideoHintTextview = bmwEvoId6GsHmoeVideoHintTextview2;
        this.textView17 = textView172;
    }

    @Nullable
    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    @NonNull
    public static FraBmwEvoId6GsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsOneBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_one, root, attachToRoot, component);
    }

    @NonNull
    public static FraBmwEvoId6GsOneBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsOneBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_one, (ViewGroup) null, false, component);
    }

    public static FraBmwEvoId6GsOneBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FraBmwEvoId6GsOneBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsOneBinding) bind(component, view, R.layout.fra_bmw_evo_id6_gs_one);
    }
}

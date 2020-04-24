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

public abstract class FraBmwEvoId6GsTwoBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout bmwEvoId6GsHmoeCarBtn;
    @NonNull
    public final TextView bmwEvoId6GsHmoeCarHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeFileHintTextview;
    @NonNull
    public final TextView bmwEvoId6GsHmoeVideoHintTextview;
    @NonNull
    public final TextView id6BrowserMessTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(@Nullable BmwId6gsViewMode bmwId6gsViewMode);

    protected FraBmwEvoId6GsTwoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout bmwEvoId6GsHmoeCarBtn2, TextView bmwEvoId6GsHmoeCarHintTextview2, TextView bmwEvoId6GsHmoeFileHintTextview2, TextView bmwEvoId6GsHmoeVideoHintTextview2, TextView id6BrowserMessTextview2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeCarBtn = bmwEvoId6GsHmoeCarBtn2;
        this.bmwEvoId6GsHmoeCarHintTextview = bmwEvoId6GsHmoeCarHintTextview2;
        this.bmwEvoId6GsHmoeFileHintTextview = bmwEvoId6GsHmoeFileHintTextview2;
        this.bmwEvoId6GsHmoeVideoHintTextview = bmwEvoId6GsHmoeVideoHintTextview2;
        this.id6BrowserMessTextview = id6BrowserMessTextview2;
    }

    @Nullable
    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    @NonNull
    public static FraBmwEvoId6GsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_two, root, attachToRoot, component);
    }

    @NonNull
    public static FraBmwEvoId6GsTwoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBmwEvoId6GsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_two, (ViewGroup) null, false, component);
    }

    public static FraBmwEvoId6GsTwoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FraBmwEvoId6GsTwoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FraBmwEvoId6GsTwoBinding) bind(component, view, R.layout.fra_bmw_evo_id6_gs_two);
    }
}

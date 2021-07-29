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

public abstract class FraBmwEvoId6GsOneBinding extends ViewDataBinding {
    public final TextView bmwEvoId6GsHmoeMusicHintTextview;
    public final TextView bmwEvoId6GsHmoeMusicNameTextview;
    public final ConstraintLayout bmwEvoId6GsHmoeNaviBtn;
    public final TextView bmwEvoId6GsHmoeNaviHintTextview;
    public final TextView bmwEvoId6GsHmoeVideoHintTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;
    public final TextView textView17;

    public abstract void setVm(BmwId6gsViewMode bmwId6gsViewMode);

    protected FraBmwEvoId6GsOneBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeMusicHintTextview2, TextView bmwEvoId6GsHmoeMusicNameTextview2, ConstraintLayout bmwEvoId6GsHmoeNaviBtn2, TextView bmwEvoId6GsHmoeNaviHintTextview2, TextView bmwEvoId6GsHmoeVideoHintTextview2, TextView textView172) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeMusicHintTextview = bmwEvoId6GsHmoeMusicHintTextview2;
        this.bmwEvoId6GsHmoeMusicNameTextview = bmwEvoId6GsHmoeMusicNameTextview2;
        this.bmwEvoId6GsHmoeNaviBtn = bmwEvoId6GsHmoeNaviBtn2;
        this.bmwEvoId6GsHmoeNaviHintTextview = bmwEvoId6GsHmoeNaviHintTextview2;
        this.bmwEvoId6GsHmoeVideoHintTextview = bmwEvoId6GsHmoeVideoHintTextview2;
        this.textView17 = textView172;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBmwEvoId6GsOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_bmw_evo_id6_gs_one, root, attachToRoot, component);
    }

    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBmwEvoId6GsOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_bmw_evo_id6_gs_one, (ViewGroup) null, false, component);
    }

    public static FraBmwEvoId6GsOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding bind(View view, Object component) {
        return (FraBmwEvoId6GsOneBinding) bind(component, view, R.layout.fra_bmw_evo_id6_gs_one);
    }
}

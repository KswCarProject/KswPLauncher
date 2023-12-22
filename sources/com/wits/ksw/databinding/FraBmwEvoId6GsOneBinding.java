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
public abstract class FraBmwEvoId6GsOneBinding extends ViewDataBinding {
    public final TextView bmwEvoId6GsHmoeMusicHintTextview;
    public final TextView bmwEvoId6GsHmoeMusicNameTextview;
    public final ConstraintLayout bmwEvoId6GsHmoeNaviBtn;
    public final TextView bmwEvoId6GsHmoeNaviHintTextview;
    public final TextView bmwEvoId6GsHmoeVideoHintTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;
    public final TextView textView17;

    public abstract void setVm(BmwId6gsViewMode vm);

    protected FraBmwEvoId6GsOneBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView bmwEvoId6GsHmoeMusicHintTextview, TextView bmwEvoId6GsHmoeMusicNameTextview, ConstraintLayout bmwEvoId6GsHmoeNaviBtn, TextView bmwEvoId6GsHmoeNaviHintTextview, TextView bmwEvoId6GsHmoeVideoHintTextview, TextView textView17) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeMusicHintTextview = bmwEvoId6GsHmoeMusicHintTextview;
        this.bmwEvoId6GsHmoeMusicNameTextview = bmwEvoId6GsHmoeMusicNameTextview;
        this.bmwEvoId6GsHmoeNaviBtn = bmwEvoId6GsHmoeNaviBtn;
        this.bmwEvoId6GsHmoeNaviHintTextview = bmwEvoId6GsHmoeNaviHintTextview;
        this.bmwEvoId6GsHmoeVideoHintTextview = bmwEvoId6GsHmoeVideoHintTextview;
        this.textView17 = textView17;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBmwEvoId6GsOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_one, root, attachToRoot, component);
    }

    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBmwEvoId6GsOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_one, null, false, component);
    }

    public static FraBmwEvoId6GsOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsOneBinding bind(View view, Object component) {
        return (FraBmwEvoId6GsOneBinding) bind(component, view, C0899R.C0902layout.fra_bmw_evo_id6_gs_one);
    }
}

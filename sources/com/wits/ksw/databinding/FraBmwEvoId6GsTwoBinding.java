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
public abstract class FraBmwEvoId6GsTwoBinding extends ViewDataBinding {
    public final ConstraintLayout bmwEvoId6GsHmoeCarBtn;
    public final TextView bmwEvoId6GsHmoeCarHintTextview;
    public final TextView bmwEvoId6GsHmoeFileHintTextview;
    public final TextView bmwEvoId6GsHmoeVideoHintTextview;
    public final TextView id6BrowserMessTextview;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(BmwId6gsViewMode vm);

    protected FraBmwEvoId6GsTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout bmwEvoId6GsHmoeCarBtn, TextView bmwEvoId6GsHmoeCarHintTextview, TextView bmwEvoId6GsHmoeFileHintTextview, TextView bmwEvoId6GsHmoeVideoHintTextview, TextView id6BrowserMessTextview) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwEvoId6GsHmoeCarBtn = bmwEvoId6GsHmoeCarBtn;
        this.bmwEvoId6GsHmoeCarHintTextview = bmwEvoId6GsHmoeCarHintTextview;
        this.bmwEvoId6GsHmoeFileHintTextview = bmwEvoId6GsHmoeFileHintTextview;
        this.bmwEvoId6GsHmoeVideoHintTextview = bmwEvoId6GsHmoeVideoHintTextview;
        this.id6BrowserMessTextview = id6BrowserMessTextview;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static FraBmwEvoId6GsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBmwEvoId6GsTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_two, root, attachToRoot, component);
    }

    public static FraBmwEvoId6GsTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBmwEvoId6GsTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_two, null, false, component);
    }

    public static FraBmwEvoId6GsTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBmwEvoId6GsTwoBinding bind(View view, Object component) {
        return (FraBmwEvoId6GsTwoBinding) bind(component, view, C0899R.C0902layout.fra_bmw_evo_id6_gs_two);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BwmNbtModel;

/* loaded from: classes7.dex */
public abstract class ActivityBmwNbtBinding extends ViewDataBinding {
    public final Button appLl;
    public final Button dashbroadLl;
    @Bindable
    protected BwmNbtModel mNbtModel;
    public final Button musicLl;
    public final Button naviLl;
    public final Button phoneLl;
    public final Button rlCar;
    public final Button rlSettings;
    public final Button videoLl;

    public abstract void setNbtModel(BwmNbtModel nbtModel);

    protected ActivityBmwNbtBinding(Object _bindingComponent, View _root, int _localFieldCount, Button appLl, Button dashbroadLl, Button musicLl, Button naviLl, Button phoneLl, Button rlCar, Button rlSettings, Button videoLl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLl = appLl;
        this.dashbroadLl = dashbroadLl;
        this.musicLl = musicLl;
        this.naviLl = naviLl;
        this.phoneLl = phoneLl;
        this.rlCar = rlCar;
        this.rlSettings = rlSettings;
        this.videoLl = videoLl;
    }

    public BwmNbtModel getNbtModel() {
        return this.mNbtModel;
    }

    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBmwNbtBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_bmw_nbt, root, attachToRoot, component);
    }

    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBmwNbtBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_bmw_nbt, null, false, component);
    }

    public static ActivityBmwNbtBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding bind(View view, Object component) {
        return (ActivityBmwNbtBinding) bind(component, view, C0899R.C0902layout.activity_bmw_nbt);
    }
}

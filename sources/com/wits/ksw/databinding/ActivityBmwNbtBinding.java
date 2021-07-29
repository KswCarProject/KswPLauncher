package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BwmNbtModel;

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

    public abstract void setNbtModel(BwmNbtModel bwmNbtModel);

    protected ActivityBmwNbtBinding(Object _bindingComponent, View _root, int _localFieldCount, Button appLl2, Button dashbroadLl2, Button musicLl2, Button naviLl2, Button phoneLl2, Button rlCar2, Button rlSettings2, Button videoLl2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLl = appLl2;
        this.dashbroadLl = dashbroadLl2;
        this.musicLl = musicLl2;
        this.naviLl = naviLl2;
        this.phoneLl = phoneLl2;
        this.rlCar = rlCar2;
        this.rlSettings = rlSettings2;
        this.videoLl = videoLl2;
    }

    public BwmNbtModel getNbtModel() {
        return this.mNbtModel;
    }

    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBmwNbtBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bmw_nbt, root, attachToRoot, component);
    }

    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBmwNbtBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bmw_nbt, (ViewGroup) null, false, component);
    }

    public static ActivityBmwNbtBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBmwNbtBinding bind(View view, Object component) {
        return (ActivityBmwNbtBinding) bind(component, view, R.layout.activity_bmw_nbt);
    }
}

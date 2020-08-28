package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BwmNbtModel;

public abstract class ActivityBmwNbtBinding extends ViewDataBinding {
    @NonNull
    public final Button appLl;
    @NonNull
    public final Button dashbroadLl;
    @Bindable
    protected BwmNbtModel mNbtModel;
    @NonNull
    public final Button musicLl;
    @NonNull
    public final Button naviLl;
    @NonNull
    public final Button phoneLl;
    @NonNull
    public final Button rlCar;
    @NonNull
    public final Button rlSettings;
    @NonNull
    public final Button videoLl;

    public abstract void setNbtModel(@Nullable BwmNbtModel bwmNbtModel);

    protected ActivityBmwNbtBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Button appLl2, Button dashbroadLl2, Button musicLl2, Button naviLl2, Button phoneLl2, Button rlCar2, Button rlSettings2, Button videoLl2) {
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

    @Nullable
    public BwmNbtModel getNbtModel() {
        return this.mNbtModel;
    }

    @NonNull
    public static ActivityBmwNbtBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBmwNbtBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityBmwNbtBinding) DataBindingUtil.inflate(inflater, R.layout.activity_bmw_nbt, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityBmwNbtBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBmwNbtBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityBmwNbtBinding) DataBindingUtil.inflate(inflater, R.layout.activity_bmw_nbt, (ViewGroup) null, false, component);
    }

    public static ActivityBmwNbtBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityBmwNbtBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityBmwNbtBinding) bind(component, view, R.layout.activity_bmw_nbt);
    }
}

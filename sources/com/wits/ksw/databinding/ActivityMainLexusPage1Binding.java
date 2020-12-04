package com.wits.ksw.databinding;

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

public abstract class ActivityMainLexusPage1Binding extends ViewDataBinding {
    @NonNull
    public final Button lexusBtCar;
    @NonNull
    public final Button lexusBtNavi;
    @NonNull
    public final Button lexusBtPhone;
    @NonNull
    public final Button lexusBtSet;

    protected ActivityMainLexusPage1Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Button lexusBtCar2, Button lexusBtNavi2, Button lexusBtPhone2, Button lexusBtSet2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtCar = lexusBtCar2;
        this.lexusBtNavi = lexusBtNavi2;
        this.lexusBtPhone = lexusBtPhone2;
        this.lexusBtSet = lexusBtSet2;
    }

    @NonNull
    public static ActivityMainLexusPage1Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage1Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage1Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page1, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainLexusPage1Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage1Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage1Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page1, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusPage1Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainLexusPage1Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage1Binding) bind(component, view, R.layout.activity_main_lexus_page1);
    }
}

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

public abstract class ActivityMainLexusPage3Binding extends ViewDataBinding {
    @NonNull
    public final Button lexusBtApp;
    @NonNull
    public final Button lexusBtDvr;
    @NonNull
    public final Button lexusBtLink;

    protected ActivityMainLexusPage3Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Button lexusBtApp2, Button lexusBtDvr2, Button lexusBtLink2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtApp = lexusBtApp2;
        this.lexusBtDvr = lexusBtDvr2;
        this.lexusBtLink = lexusBtLink2;
    }

    @NonNull
    public static ActivityMainLexusPage3Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage3Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage3Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page3, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainLexusPage3Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage3Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage3Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page3, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusPage3Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainLexusPage3Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage3Binding) bind(component, view, R.layout.activity_main_lexus_page3);
    }
}

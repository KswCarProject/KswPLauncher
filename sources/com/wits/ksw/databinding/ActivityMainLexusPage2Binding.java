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

public abstract class ActivityMainLexusPage2Binding extends ViewDataBinding {
    @NonNull
    public final Button lexusBtDash;
    @NonNull
    public final Button lexusBtFile;
    @NonNull
    public final Button lexusBtMusic;
    @NonNull
    public final Button lexusBtVedio;

    protected ActivityMainLexusPage2Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Button lexusBtDash2, Button lexusBtFile2, Button lexusBtMusic2, Button lexusBtVedio2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtDash = lexusBtDash2;
        this.lexusBtFile = lexusBtFile2;
        this.lexusBtMusic = lexusBtMusic2;
        this.lexusBtVedio = lexusBtVedio2;
    }

    @NonNull
    public static ActivityMainLexusPage2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage2Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page2, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainLexusPage2Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusPage2Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage2Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus_page2, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusPage2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainLexusPage2Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusPage2Binding) bind(component, view, R.layout.activity_main_lexus_page2);
    }
}

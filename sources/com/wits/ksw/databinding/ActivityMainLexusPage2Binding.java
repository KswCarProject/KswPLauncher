package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.R;

public abstract class ActivityMainLexusPage2Binding extends ViewDataBinding {
    public final Button lexusBtDash;
    public final Button lexusBtFile;
    public final Button lexusBtMusic;
    public final Button lexusBtVedio;

    protected ActivityMainLexusPage2Binding(Object _bindingComponent, View _root, int _localFieldCount, Button lexusBtDash2, Button lexusBtFile2, Button lexusBtMusic2, Button lexusBtVedio2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtDash = lexusBtDash2;
        this.lexusBtFile = lexusBtFile2;
        this.lexusBtMusic = lexusBtMusic2;
        this.lexusBtVedio = lexusBtVedio2;
    }

    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusPage2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus_page2, root, attachToRoot, component);
    }

    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusPage2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus_page2, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusPage2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding bind(View view, Object component) {
        return (ActivityMainLexusPage2Binding) bind(component, view, R.layout.activity_main_lexus_page2);
    }
}

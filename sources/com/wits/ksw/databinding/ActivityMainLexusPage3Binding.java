package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.R;

public abstract class ActivityMainLexusPage3Binding extends ViewDataBinding {
    public final Button lexusBtApp;
    public final Button lexusBtDvr;
    public final Button lexusBtLink;

    protected ActivityMainLexusPage3Binding(Object _bindingComponent, View _root, int _localFieldCount, Button lexusBtApp2, Button lexusBtDvr2, Button lexusBtLink2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtApp = lexusBtApp2;
        this.lexusBtDvr = lexusBtDvr2;
        this.lexusBtLink = lexusBtLink2;
    }

    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusPage3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus_page3, root, attachToRoot, component);
    }

    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusPage3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus_page3, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusPage3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding bind(View view, Object component) {
        return (ActivityMainLexusPage3Binding) bind(component, view, R.layout.activity_main_lexus_page3);
    }
}

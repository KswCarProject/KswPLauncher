package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class ActivityMainLexusPage3Binding extends ViewDataBinding {
    public final Button lexusBtApp;
    public final Button lexusBtDvr;
    public final Button lexusBtLink;

    protected ActivityMainLexusPage3Binding(Object _bindingComponent, View _root, int _localFieldCount, Button lexusBtApp, Button lexusBtDvr, Button lexusBtLink) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtApp = lexusBtApp;
        this.lexusBtDvr = lexusBtDvr;
        this.lexusBtLink = lexusBtLink;
    }

    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusPage3Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page3, root, attachToRoot, component);
    }

    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusPage3Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page3, null, false, component);
    }

    public static ActivityMainLexusPage3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage3Binding bind(View view, Object component) {
        return (ActivityMainLexusPage3Binding) bind(component, view, C0899R.C0902layout.activity_main_lexus_page3);
    }
}

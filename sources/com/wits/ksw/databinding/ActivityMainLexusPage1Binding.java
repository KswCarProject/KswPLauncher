package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class ActivityMainLexusPage1Binding extends ViewDataBinding {
    public final Button lexusBtCar;
    public final Button lexusBtNavi;
    public final Button lexusBtPhone;
    public final Button lexusBtSet;

    protected ActivityMainLexusPage1Binding(Object _bindingComponent, View _root, int _localFieldCount, Button lexusBtCar, Button lexusBtNavi, Button lexusBtPhone, Button lexusBtSet) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtCar = lexusBtCar;
        this.lexusBtNavi = lexusBtNavi;
        this.lexusBtPhone = lexusBtPhone;
        this.lexusBtSet = lexusBtSet;
    }

    public static ActivityMainLexusPage1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusPage1Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page1, root, attachToRoot, component);
    }

    public static ActivityMainLexusPage1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage1Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusPage1Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page1, null, false, component);
    }

    public static ActivityMainLexusPage1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage1Binding bind(View view, Object component) {
        return (ActivityMainLexusPage1Binding) bind(component, view, C0899R.C0902layout.activity_main_lexus_page1);
    }
}

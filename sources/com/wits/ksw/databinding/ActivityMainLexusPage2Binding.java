package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class ActivityMainLexusPage2Binding extends ViewDataBinding {
    public final Button lexusBtDash;
    public final Button lexusBtFile;
    public final Button lexusBtMusic;
    public final Button lexusBtVedio;

    protected ActivityMainLexusPage2Binding(Object _bindingComponent, View _root, int _localFieldCount, Button lexusBtDash, Button lexusBtFile, Button lexusBtMusic, Button lexusBtVedio) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lexusBtDash = lexusBtDash;
        this.lexusBtFile = lexusBtFile;
        this.lexusBtMusic = lexusBtMusic;
        this.lexusBtVedio = lexusBtVedio;
    }

    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusPage2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page2, root, attachToRoot, component);
    }

    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusPage2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus_page2, null, false, component);
    }

    public static ActivityMainLexusPage2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusPage2Binding bind(View view, Object component) {
        return (ActivityMainLexusPage2Binding) bind(component, view, C0899R.C0902layout.activity_main_lexus_page2);
    }
}

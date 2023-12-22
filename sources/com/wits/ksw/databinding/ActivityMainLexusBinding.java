package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainLexusBinding extends ViewDataBinding {
    public final LinearLayout idGallery;
    public final Button lexusAir;
    public final Button lexusBtApp;
    public final Button lexusBtCar;
    public final Button lexusBtDash;
    public final Button lexusBtDvr;
    public final Button lexusBtFile;
    public final Button lexusBtLink;
    public final Button lexusBtMusic;
    public final Button lexusBtNavi;
    public final Button lexusBtPhone;
    public final Button lexusBtSet;
    public final Button lexusBtVedio;
    public final HorizontalScrollView lexusMainSv;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final LinearLayout mainMenu;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ActivityMainLexusBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout idGallery, Button lexusAir, Button lexusBtApp, Button lexusBtCar, Button lexusBtDash, Button lexusBtDvr, Button lexusBtFile, Button lexusBtLink, Button lexusBtMusic, Button lexusBtNavi, Button lexusBtPhone, Button lexusBtSet, Button lexusBtVedio, HorizontalScrollView lexusMainSv, LinearLayout mainMenu) {
        super(_bindingComponent, _root, _localFieldCount);
        this.idGallery = idGallery;
        this.lexusAir = lexusAir;
        this.lexusBtApp = lexusBtApp;
        this.lexusBtCar = lexusBtCar;
        this.lexusBtDash = lexusBtDash;
        this.lexusBtDvr = lexusBtDvr;
        this.lexusBtFile = lexusBtFile;
        this.lexusBtLink = lexusBtLink;
        this.lexusBtMusic = lexusBtMusic;
        this.lexusBtNavi = lexusBtNavi;
        this.lexusBtPhone = lexusBtPhone;
        this.lexusBtSet = lexusBtSet;
        this.lexusBtVedio = lexusBtVedio;
        this.lexusMainSv = lexusMainSv;
        this.mainMenu = mainMenu;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus, root, attachToRoot, component);
    }

    public static ActivityMainLexusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_lexus, null, false, component);
    }

    public static ActivityMainLexusBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding bind(View view, Object component) {
        return (ActivityMainLexusBinding) bind(component, view, C0899R.C0902layout.activity_main_lexus);
    }
}

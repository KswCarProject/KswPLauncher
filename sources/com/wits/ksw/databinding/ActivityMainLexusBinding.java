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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMainLexusBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout idGallery2, Button lexusAir2, Button lexusBtApp2, Button lexusBtCar2, Button lexusBtDash2, Button lexusBtDvr2, Button lexusBtFile2, Button lexusBtLink2, Button lexusBtMusic2, Button lexusBtNavi2, Button lexusBtPhone2, Button lexusBtSet2, Button lexusBtVedio2, HorizontalScrollView lexusMainSv2, LinearLayout mainMenu2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.idGallery = idGallery2;
        this.lexusAir = lexusAir2;
        this.lexusBtApp = lexusBtApp2;
        this.lexusBtCar = lexusBtCar2;
        this.lexusBtDash = lexusBtDash2;
        this.lexusBtDvr = lexusBtDvr2;
        this.lexusBtFile = lexusBtFile2;
        this.lexusBtLink = lexusBtLink2;
        this.lexusBtMusic = lexusBtMusic2;
        this.lexusBtNavi = lexusBtNavi2;
        this.lexusBtPhone = lexusBtPhone2;
        this.lexusBtSet = lexusBtSet2;
        this.lexusBtVedio = lexusBtVedio2;
        this.lexusMainSv = lexusMainSv2;
        this.mainMenu = mainMenu2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLexusBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus, root, attachToRoot, component);
    }

    public static ActivityMainLexusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLexusBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_lexus, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLexusBinding bind(View view, Object component) {
        return (ActivityMainLexusBinding) bind(component, view, R.layout.activity_main_lexus);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainLexusBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout idGallery;
    @NonNull
    public final Button lexusAir;
    @NonNull
    public final Button lexusBtApp;
    @NonNull
    public final Button lexusBtCar;
    @NonNull
    public final Button lexusBtDash;
    @NonNull
    public final Button lexusBtDvr;
    @NonNull
    public final Button lexusBtFile;
    @NonNull
    public final Button lexusBtLink;
    @NonNull
    public final Button lexusBtMusic;
    @NonNull
    public final Button lexusBtNavi;
    @NonNull
    public final Button lexusBtPhone;
    @NonNull
    public final Button lexusBtSet;
    @NonNull
    public final Button lexusBtVedio;
    @NonNull
    public final HorizontalScrollView lexusMainSv;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final LinearLayout mainMenu;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMainLexusBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, LinearLayout idGallery2, Button lexusAir2, Button lexusBtApp2, Button lexusBtCar2, Button lexusBtDash2, Button lexusBtDvr2, Button lexusBtFile2, Button lexusBtLink2, Button lexusBtMusic2, Button lexusBtNavi2, Button lexusBtPhone2, Button lexusBtSet2, Button lexusBtVedio2, HorizontalScrollView lexusMainSv2, LinearLayout mainMenu2) {
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

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ActivityMainLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainLexusBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_lexus, (ViewGroup) null, false, component);
    }

    public static ActivityMainLexusBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainLexusBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainLexusBinding) bind(component, view, R.layout.activity_main_lexus);
    }
}

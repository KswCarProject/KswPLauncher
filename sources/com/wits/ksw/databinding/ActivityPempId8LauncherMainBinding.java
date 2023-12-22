package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityPempId8LauncherMainBinding extends ViewDataBinding {
    public final LinearLayout llContainer;
    public final Id8PempLauncherLeftBarBinding llLeftContainer;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final HorizontalScrollView scrollView;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected ActivityPempId8LauncherMainBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llContainer, Id8PempLauncherLeftBarBinding llLeftContainer, HorizontalScrollView scrollView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llContainer = llContainer;
        this.llLeftContainer = llLeftContainer;
        this.scrollView = scrollView;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityPempId8LauncherMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPempId8LauncherMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPempId8LauncherMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_pemp_id8_launcher_main, root, attachToRoot, component);
    }

    public static ActivityPempId8LauncherMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPempId8LauncherMainBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPempId8LauncherMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_pemp_id8_launcher_main, null, false, component);
    }

    public static ActivityPempId8LauncherMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPempId8LauncherMainBinding bind(View view, Object component) {
        return (ActivityPempId8LauncherMainBinding) bind(component, view, C0899R.C0902layout.activity_pemp_id8_launcher_main);
    }
}

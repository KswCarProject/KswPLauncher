package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8HorizontalScrollView;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityGsId8LauncherMainBinding extends ViewDataBinding {
    public final ID8HorizontalScrollView gsScrollView;
    public final LinearLayout llContaine;
    public final Id8GsLauncherLeftBarBinding llLeftContainer;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final RelativeLayout rlModusContainer;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected ActivityGsId8LauncherMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ID8HorizontalScrollView gsScrollView, LinearLayout llContaine, Id8GsLauncherLeftBarBinding llLeftContainer, RelativeLayout rlModusContainer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsScrollView = gsScrollView;
        this.llContaine = llContaine;
        this.llLeftContainer = llLeftContainer;
        this.rlModusContainer = rlModusContainer;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityGsId8LauncherMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGsId8LauncherMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGsId8LauncherMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_gs_id8_launcher_main, root, attachToRoot, component);
    }

    public static ActivityGsId8LauncherMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGsId8LauncherMainBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGsId8LauncherMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_gs_id8_launcher_main, null, false, component);
    }

    public static ActivityGsId8LauncherMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGsId8LauncherMainBinding bind(View view, Object component) {
        return (ActivityGsId8LauncherMainBinding) bind(component, view, C0899R.C0902layout.activity_gs_id8_launcher_main);
    }
}

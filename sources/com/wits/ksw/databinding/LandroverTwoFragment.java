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
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class LandroverTwoFragment extends ViewDataBinding {
    @NonNull
    public final ImageView landroverMainIconApp;
    @NonNull
    public final ImageView landroverMainIconBrowser;
    @NonNull
    public final ImageView landroverMainIconBt;
    @NonNull
    public final ImageView landroverMainIconDashboard;
    @NonNull
    public final ImageView landroverMainIconFile;
    @NonNull
    public final ImageView landroverMainIconPhonelink;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected LandroverTwoFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainIconApp2, ImageView landroverMainIconBrowser2, ImageView landroverMainIconBt2, ImageView landroverMainIconDashboard2, ImageView landroverMainIconFile2, ImageView landroverMainIconPhonelink2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainIconApp = landroverMainIconApp2;
        this.landroverMainIconBrowser = landroverMainIconBrowser2;
        this.landroverMainIconBt = landroverMainIconBt2;
        this.landroverMainIconDashboard = landroverMainIconDashboard2;
        this.landroverMainIconFile = landroverMainIconFile2;
        this.landroverMainIconPhonelink = landroverMainIconPhonelink2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static LandroverTwoFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverTwoFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (LandroverTwoFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_two, root, attachToRoot, component);
    }

    @NonNull
    public static LandroverTwoFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverTwoFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (LandroverTwoFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_two, (ViewGroup) null, false, component);
    }

    public static LandroverTwoFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LandroverTwoFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (LandroverTwoFragment) bind(component, view, R.layout.landrover_main_fragment_two);
    }
}

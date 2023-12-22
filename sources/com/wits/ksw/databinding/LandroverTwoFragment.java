package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
public abstract class LandroverTwoFragment extends ViewDataBinding {
    public final ImageView landroverMainIconApp;
    public final ImageView landroverMainIconBrowser;
    public final ImageView landroverMainIconBt;
    public final ImageView landroverMainIconDashboard;
    public final ImageView landroverMainIconFile;
    public final ImageView landroverMainIconPhonelink;
    @Bindable
    protected LandroverViewModel mViewModel;

    public abstract void setViewModel(LandroverViewModel ViewModel);

    protected LandroverTwoFragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainIconApp, ImageView landroverMainIconBrowser, ImageView landroverMainIconBt, ImageView landroverMainIconDashboard, ImageView landroverMainIconFile, ImageView landroverMainIconPhonelink) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainIconApp = landroverMainIconApp;
        this.landroverMainIconBrowser = landroverMainIconBrowser;
        this.landroverMainIconBt = landroverMainIconBt;
        this.landroverMainIconDashboard = landroverMainIconDashboard;
        this.landroverMainIconFile = landroverMainIconFile;
        this.landroverMainIconPhonelink = landroverMainIconPhonelink;
    }

    public LandroverViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LandroverTwoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverTwoFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main_fragment_two, root, attachToRoot, component);
    }

    public static LandroverTwoFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment inflate(LayoutInflater inflater, Object component) {
        return (LandroverTwoFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main_fragment_two, null, false, component);
    }

    public static LandroverTwoFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment bind(View view, Object component) {
        return (LandroverTwoFragment) bind(component, view, C0899R.C0902layout.landrover_main_fragment_two);
    }
}

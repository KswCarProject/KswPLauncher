package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public abstract class LandroverTwoFragment extends ViewDataBinding {
    public final ImageView landroverMainIconApp;
    public final ImageView landroverMainIconBrowser;
    public final ImageView landroverMainIconBt;
    public final ImageView landroverMainIconDashboard;
    public final ImageView landroverMainIconFile;
    public final ImageView landroverMainIconPhonelink;
    @Bindable
    protected LandroverViewModel mViewModel;

    public abstract void setViewModel(LandroverViewModel landroverViewModel);

    protected LandroverTwoFragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainIconApp2, ImageView landroverMainIconBrowser2, ImageView landroverMainIconBt2, ImageView landroverMainIconDashboard2, ImageView landroverMainIconFile2, ImageView landroverMainIconPhonelink2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainIconApp = landroverMainIconApp2;
        this.landroverMainIconBrowser = landroverMainIconBrowser2;
        this.landroverMainIconBt = landroverMainIconBt2;
        this.landroverMainIconDashboard = landroverMainIconDashboard2;
        this.landroverMainIconFile = landroverMainIconFile2;
        this.landroverMainIconPhonelink = landroverMainIconPhonelink2;
    }

    public LandroverViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LandroverTwoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverTwoFragment) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_fragment_two, root, attachToRoot, component);
    }

    public static LandroverTwoFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment inflate(LayoutInflater inflater, Object component) {
        return (LandroverTwoFragment) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_fragment_two, (ViewGroup) null, false, component);
    }

    public static LandroverTwoFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverTwoFragment bind(View view, Object component) {
        return (LandroverTwoFragment) bind(component, view, R.layout.landrover_main_fragment_two);
    }
}

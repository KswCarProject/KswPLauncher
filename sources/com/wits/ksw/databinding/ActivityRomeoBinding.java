package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.RomeoViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityRomeoBinding extends ViewDataBinding {
    @Bindable
    protected RomeoViewModel mViewModel;
    public final ImageView pageIndicator1;
    public final ImageView pageIndicator2;
    public final ImageView romeoApp;
    public final ImageView romeoIndicator1;
    public final ImageView romeoIndicator2;
    public final ImageView romeoIndicator3;
    public final ImageView romeoIndicator4;
    public final ImageView romeoIndicator5;
    public final ImageView romeoIndicator6;
    public final RecyclerView romeoMainRv;
    public final ImageView romeoMusic;
    public final ImageView romeoNavi;
    public final ImageView romeoPhone;
    public final ImageView romeoSetting;
    public final ImageView romeoVideo;

    public abstract void setViewModel(RomeoViewModel viewModel);

    protected ActivityRomeoBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView pageIndicator1, ImageView pageIndicator2, ImageView romeoApp, ImageView romeoIndicator1, ImageView romeoIndicator2, ImageView romeoIndicator3, ImageView romeoIndicator4, ImageView romeoIndicator5, ImageView romeoIndicator6, RecyclerView romeoMainRv, ImageView romeoMusic, ImageView romeoNavi, ImageView romeoPhone, ImageView romeoSetting, ImageView romeoVideo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.pageIndicator1 = pageIndicator1;
        this.pageIndicator2 = pageIndicator2;
        this.romeoApp = romeoApp;
        this.romeoIndicator1 = romeoIndicator1;
        this.romeoIndicator2 = romeoIndicator2;
        this.romeoIndicator3 = romeoIndicator3;
        this.romeoIndicator4 = romeoIndicator4;
        this.romeoIndicator5 = romeoIndicator5;
        this.romeoIndicator6 = romeoIndicator6;
        this.romeoMainRv = romeoMainRv;
        this.romeoMusic = romeoMusic;
        this.romeoNavi = romeoNavi;
        this.romeoPhone = romeoPhone;
        this.romeoSetting = romeoSetting;
        this.romeoVideo = romeoVideo;
    }

    public RomeoViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityRomeoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRomeoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_romeo, root, attachToRoot, component);
    }

    public static ActivityRomeoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRomeoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_romeo, null, false, component);
    }

    public static ActivityRomeoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding bind(View view, Object component) {
        return (ActivityRomeoBinding) bind(component, view, C0899R.C0902layout.activity_romeo);
    }
}

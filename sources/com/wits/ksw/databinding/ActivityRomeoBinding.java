package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.RomeoViewModel;

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

    public abstract void setViewModel(RomeoViewModel romeoViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityRomeoBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView pageIndicator12, ImageView pageIndicator22, ImageView romeoApp2, ImageView romeoIndicator12, ImageView romeoIndicator22, ImageView romeoIndicator32, ImageView romeoIndicator42, ImageView romeoIndicator52, ImageView romeoIndicator62, RecyclerView romeoMainRv2, ImageView romeoMusic2, ImageView romeoNavi2, ImageView romeoPhone2, ImageView romeoSetting2, ImageView romeoVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.pageIndicator1 = pageIndicator12;
        this.pageIndicator2 = pageIndicator22;
        this.romeoApp = romeoApp2;
        this.romeoIndicator1 = romeoIndicator12;
        this.romeoIndicator2 = romeoIndicator22;
        this.romeoIndicator3 = romeoIndicator32;
        this.romeoIndicator4 = romeoIndicator42;
        this.romeoIndicator5 = romeoIndicator52;
        this.romeoIndicator6 = romeoIndicator62;
        this.romeoMainRv = romeoMainRv2;
        this.romeoMusic = romeoMusic2;
        this.romeoNavi = romeoNavi2;
        this.romeoPhone = romeoPhone2;
        this.romeoSetting = romeoSetting2;
        this.romeoVideo = romeoVideo2;
    }

    public RomeoViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityRomeoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRomeoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_romeo, root, attachToRoot, component);
    }

    public static ActivityRomeoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRomeoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_romeo, (ViewGroup) null, false, component);
    }

    public static ActivityRomeoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoBinding bind(View view, Object component) {
        return (ActivityRomeoBinding) bind(component, view, R.layout.activity_romeo);
    }
}

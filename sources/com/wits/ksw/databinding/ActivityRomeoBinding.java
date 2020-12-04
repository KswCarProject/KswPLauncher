package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    public final ImageView pageIndicator1;
    @NonNull
    public final ImageView pageIndicator2;
    @NonNull
    public final ImageView romeoApp;
    @NonNull
    public final ImageView romeoIndicator1;
    @NonNull
    public final ImageView romeoIndicator2;
    @NonNull
    public final ImageView romeoIndicator3;
    @NonNull
    public final ImageView romeoIndicator4;
    @NonNull
    public final ImageView romeoIndicator5;
    @NonNull
    public final ImageView romeoIndicator6;
    @NonNull
    public final RecyclerView romeoMainRv;
    @NonNull
    public final ImageView romeoMusic;
    @NonNull
    public final ImageView romeoNavi;
    @NonNull
    public final ImageView romeoPhone;
    @NonNull
    public final ImageView romeoSetting;
    @NonNull
    public final ImageView romeoVideo;

    public abstract void setViewModel(@Nullable RomeoViewModel romeoViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityRomeoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView pageIndicator12, ImageView pageIndicator22, ImageView romeoApp2, ImageView romeoIndicator12, ImageView romeoIndicator22, ImageView romeoIndicator32, ImageView romeoIndicator42, ImageView romeoIndicator52, ImageView romeoIndicator62, RecyclerView romeoMainRv2, ImageView romeoMusic2, ImageView romeoNavi2, ImageView romeoPhone2, ImageView romeoSetting2, ImageView romeoVideo2) {
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

    @Nullable
    public RomeoViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ActivityRomeoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRomeoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityRomeoBinding) DataBindingUtil.inflate(inflater, R.layout.activity_romeo, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityRomeoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRomeoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityRomeoBinding) DataBindingUtil.inflate(inflater, R.layout.activity_romeo, (ViewGroup) null, false, component);
    }

    public static ActivityRomeoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityRomeoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityRomeoBinding) bind(component, view, R.layout.activity_romeo);
    }
}

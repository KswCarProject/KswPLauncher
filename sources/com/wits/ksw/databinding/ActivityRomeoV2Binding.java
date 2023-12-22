package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.RomeoViewModelV2;

/* loaded from: classes7.dex */
public abstract class ActivityRomeoV2Binding extends ViewDataBinding {
    public final TextView dateTv;
    public final ImageView ivIcon;
    @Bindable
    protected RomeoViewModelV2 mViewModel;
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
    public final TextView temperatureTv;
    public final TextView unitWeather;
    public final ConstraintLayout videoConstraintLayout;
    public final ImageView weatherImageView;

    public abstract void setViewModel(RomeoViewModelV2 viewModel);

    protected ActivityRomeoV2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView dateTv, ImageView ivIcon, ImageView pageIndicator1, ImageView pageIndicator2, ImageView romeoApp, ImageView romeoIndicator1, ImageView romeoIndicator2, ImageView romeoIndicator3, ImageView romeoIndicator4, ImageView romeoIndicator5, ImageView romeoIndicator6, RecyclerView romeoMainRv, ImageView romeoMusic, ImageView romeoNavi, ImageView romeoPhone, ImageView romeoSetting, ImageView romeoVideo, TextView temperatureTv, TextView unitWeather, ConstraintLayout videoConstraintLayout, ImageView weatherImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dateTv = dateTv;
        this.ivIcon = ivIcon;
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
        this.temperatureTv = temperatureTv;
        this.unitWeather = unitWeather;
        this.videoConstraintLayout = videoConstraintLayout;
        this.weatherImageView = weatherImageView;
    }

    public RomeoViewModelV2 getViewModel() {
        return this.mViewModel;
    }

    public static ActivityRomeoV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRomeoV2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_romeo_v2, root, attachToRoot, component);
    }

    public static ActivityRomeoV2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoV2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRomeoV2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_romeo_v2, null, false, component);
    }

    public static ActivityRomeoV2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRomeoV2Binding bind(View view, Object component) {
        return (ActivityRomeoV2Binding) bind(component, view, C0899R.C0902layout.activity_romeo_v2);
    }
}

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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainAlsId6Binding extends ViewDataBinding {
    @NonNull
    public final LinearLayout appLl;
    @NonNull
    public final LinearLayout dashbroadLl;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final LinearLayout musicLl;
    @NonNull
    public final LinearLayout naviLl;
    @NonNull
    public final LinearLayout phoneLl;
    @NonNull
    public final ImageView pointerImageView;
    @NonNull
    public final View sView;
    @NonNull
    public final ImageView seatBeltBtn;
    @NonNull
    public final ImageView seatBtn;
    @NonNull
    public final LinearLayout seatLl;
    @NonNull
    public final SeekBar seekBar;
    @NonNull
    public final LinearLayout videoLl;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ActivityMainAlsId6Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, LinearLayout appLl2, LinearLayout dashbroadLl2, LinearLayout musicLl2, LinearLayout naviLl2, LinearLayout phoneLl2, ImageView pointerImageView2, View sView2, ImageView seatBeltBtn2, ImageView seatBtn2, LinearLayout seatLl2, SeekBar seekBar2, LinearLayout videoLl2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLl = appLl2;
        this.dashbroadLl = dashbroadLl2;
        this.musicLl = musicLl2;
        this.naviLl = naviLl2;
        this.phoneLl = phoneLl2;
        this.pointerImageView = pointerImageView2;
        this.sView = sView2;
        this.seatBeltBtn = seatBeltBtn2;
        this.seatBtn = seatBtn2;
        this.seatLl = seatLl2;
        this.seekBar = seekBar2;
        this.videoLl = videoLl2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ActivityMainAlsId6Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAlsId6Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId6Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_als_id6, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainAlsId6Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAlsId6Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId6Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_als_id6, (ViewGroup) null, false, component);
    }

    public static ActivityMainAlsId6Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainAlsId6Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId6Binding) bind(component, view, R.layout.activity_main_als_id6);
    }
}

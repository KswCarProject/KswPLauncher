package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainAlsId6Binding extends ViewDataBinding {
    public final LinearLayout appLl;
    public final LinearLayout dashbroadLl;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final LinearLayout musicLl;
    public final LinearLayout naviLl;
    public final LinearLayout phoneLl;
    public final ImageView pointerImageView;
    public final View sView;
    public final ImageView seatBeltBtn;
    public final ImageView seatBtn;
    public final LinearLayout seatLl;
    public final SeekBar seekBar;
    public final LinearLayout videoLl;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ActivityMainAlsId6Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout appLl, LinearLayout dashbroadLl, LinearLayout musicLl, LinearLayout naviLl, LinearLayout phoneLl, ImageView pointerImageView, View sView, ImageView seatBeltBtn, ImageView seatBtn, LinearLayout seatLl, SeekBar seekBar, LinearLayout videoLl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLl = appLl;
        this.dashbroadLl = dashbroadLl;
        this.musicLl = musicLl;
        this.naviLl = naviLl;
        this.phoneLl = phoneLl;
        this.pointerImageView = pointerImageView;
        this.sView = sView;
        this.seatBeltBtn = seatBeltBtn;
        this.seatBtn = seatBtn;
        this.seatLl = seatLl;
        this.seekBar = seekBar;
        this.videoLl = videoLl;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAlsId6Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id6, root, attachToRoot, component);
    }

    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAlsId6Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id6, null, false, component);
    }

    public static ActivityMainAlsId6Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding bind(View view, Object component) {
        return (ActivityMainAlsId6Binding) bind(component, view, C0899R.C0902layout.activity_main_als_id6);
    }
}

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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ActivityMainAlsId6Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout appLl2, LinearLayout dashbroadLl2, LinearLayout musicLl2, LinearLayout naviLl2, LinearLayout phoneLl2, ImageView pointerImageView2, View sView2, ImageView seatBeltBtn2, ImageView seatBtn2, LinearLayout seatLl2, SeekBar seekBar2, LinearLayout videoLl2) {
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

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAlsId6Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id6, root, attachToRoot, component);
    }

    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAlsId6Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id6, (ViewGroup) null, false, component);
    }

    public static ActivityMainAlsId6Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId6Binding bind(View view, Object component) {
        return (ActivityMainAlsId6Binding) bind(component, view, R.layout.activity_main_als_id6);
    }
}

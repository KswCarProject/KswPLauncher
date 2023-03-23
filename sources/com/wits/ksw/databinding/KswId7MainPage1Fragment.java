package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class KswId7MainPage1Fragment extends ViewDataBinding {
    public final ImageView ivArrow;
    public final LinearLayout llAppCard;
    public final LinearLayout llMusicCard;
    public final LinearLayout llNaviCard;
    public final LinearLayout llPhoneCard;
    public final LinearLayout llSetCard;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView tvMusic;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected KswId7MainPage1Fragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivArrow2, LinearLayout llAppCard2, LinearLayout llMusicCard2, LinearLayout llNaviCard2, LinearLayout llPhoneCard2, LinearLayout llSetCard2, TextView tvMusic2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivArrow = ivArrow2;
        this.llAppCard = llAppCard2;
        this.llMusicCard = llMusicCard2;
        this.llNaviCard = llNaviCard2;
        this.llPhoneCard = llPhoneCard2;
        this.llSetCard = llSetCard2;
        this.tvMusic = tvMusic2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswId7MainPage1Fragment) ViewDataBinding.inflateInternal(inflater, R.layout.ksw_id7_main_page1, root, attachToRoot, component);
    }

    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, Object component) {
        return (KswId7MainPage1Fragment) ViewDataBinding.inflateInternal(inflater, R.layout.ksw_id7_main_page1, (ViewGroup) null, false, component);
    }

    public static KswId7MainPage1Fragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment bind(View view, Object component) {
        return (KswId7MainPage1Fragment) bind(component, view, R.layout.ksw_id7_main_page1);
    }
}

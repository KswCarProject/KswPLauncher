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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected KswId7MainPage1Fragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivArrow, LinearLayout llAppCard, LinearLayout llMusicCard, LinearLayout llNaviCard, LinearLayout llPhoneCard, LinearLayout llSetCard, TextView tvMusic) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivArrow = ivArrow;
        this.llAppCard = llAppCard;
        this.llMusicCard = llMusicCard;
        this.llNaviCard = llNaviCard;
        this.llPhoneCard = llPhoneCard;
        this.llSetCard = llSetCard;
        this.tvMusic = tvMusic;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswId7MainPage1Fragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ksw_id7_main_page1, root, attachToRoot, component);
    }

    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment inflate(LayoutInflater inflater, Object component) {
        return (KswId7MainPage1Fragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ksw_id7_main_page1, null, false, component);
    }

    public static KswId7MainPage1Fragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage1Fragment bind(View view, Object component) {
        return (KswId7MainPage1Fragment) bind(component, view, C0899R.C0902layout.ksw_id7_main_page1);
    }
}

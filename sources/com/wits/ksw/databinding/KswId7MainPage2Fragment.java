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

public abstract class KswId7MainPage2Fragment extends ViewDataBinding {
    public final ImageView ivArrow;
    public final LinearLayout llCarCard;
    public final LinearLayout llComputerCard;
    public final LinearLayout llFileCard;
    public final LinearLayout llLinkCard;
    public final LinearLayout llVideoCard;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView tvVideo;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected KswId7MainPage2Fragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivArrow2, LinearLayout llCarCard2, LinearLayout llComputerCard2, LinearLayout llFileCard2, LinearLayout llLinkCard2, LinearLayout llVideoCard2, TextView tvVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivArrow = ivArrow2;
        this.llCarCard = llCarCard2;
        this.llComputerCard = llComputerCard2;
        this.llFileCard = llFileCard2;
        this.llLinkCard = llLinkCard2;
        this.llVideoCard = llVideoCard2;
        this.tvVideo = tvVideo2;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static KswId7MainPage2Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage2Fragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswId7MainPage2Fragment) ViewDataBinding.inflateInternal(inflater, R.layout.ksw_id7_main_page2, root, attachToRoot, component);
    }

    public static KswId7MainPage2Fragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage2Fragment inflate(LayoutInflater inflater, Object component) {
        return (KswId7MainPage2Fragment) ViewDataBinding.inflateInternal(inflater, R.layout.ksw_id7_main_page2, (ViewGroup) null, false, component);
    }

    public static KswId7MainPage2Fragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswId7MainPage2Fragment bind(View view, Object component) {
        return (KswId7MainPage2Fragment) bind(component, view, R.layout.ksw_id7_main_page2);
    }
}

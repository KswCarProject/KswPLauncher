package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7UiSubNaviViewBinding extends ViewDataBinding {
    public final ImageView alsId7MainNaviImg;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final ConstraintLayout naviConstraintLayout;
    public final CustomSkinImageView naviImageView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviViewModel(LauncherViewModel NaviViewModel);

    protected AlsId7UiSubNaviViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView alsId7MainNaviImg, ConstraintLayout naviConstraintLayout, CustomSkinImageView naviImageView, TextView textView2, TextView textView3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsId7MainNaviImg = alsId7MainNaviImg;
        this.naviConstraintLayout = naviConstraintLayout;
        this.naviImageView = naviImageView;
        this.textView2 = textView2;
        this.textView3 = textView3;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static AlsId7UiSubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubNaviViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiSubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_navi_view, root, attachToRoot, component);
    }

    public static AlsId7UiSubNaviViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubNaviViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiSubNaviViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_navi_view, null, false, component);
    }

    public static AlsId7UiSubNaviViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubNaviViewBinding bind(View view, Object component) {
        return (AlsId7UiSubNaviViewBinding) bind(component, view, C0899R.C0902layout.als_id7_ui_sub_navi_view);
    }
}

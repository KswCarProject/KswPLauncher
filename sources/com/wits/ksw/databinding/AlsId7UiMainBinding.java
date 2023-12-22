package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7_ui.view.CustomViewpager;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class AlsId7UiMainBinding extends ViewDataBinding {
    public final ConstraintLayout alsRoot;
    public final ImageView blueIv;
    public final ImageView btn1Iv;
    public final ImageView btn2Iv;
    public final ImageView btn3Iv;
    public final ImageView btn4Iv;
    public final ImageView btn5Iv;
    public final Guideline guideline;
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final ImageView itemViewSkinDefault;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final RelativeLayout menuButton1;
    public final RelativeLayout menuButton2;
    public final RelativeLayout menuButton3;
    public final RelativeLayout menuButton4;
    public final RelativeLayout menuButton5;
    public final LinearLayout menuConstraintLayout;
    public final ImageView redIv;
    public final RelativeLayout skinLl;
    public final CustomViewpager viewPage;
    public final ImageView yellowIv;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected AlsId7UiMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout alsRoot, ImageView blueIv, ImageView btn1Iv, ImageView btn2Iv, ImageView btn3Iv, ImageView btn4Iv, ImageView btn5Iv, Guideline guideline, ImageView imageView1, ImageView imageView3, ImageView imageView4, ImageView itemViewSkinDefault, RelativeLayout menuButton1, RelativeLayout menuButton2, RelativeLayout menuButton3, RelativeLayout menuButton4, RelativeLayout menuButton5, LinearLayout menuConstraintLayout, ImageView redIv, RelativeLayout skinLl, CustomViewpager viewPage, ImageView yellowIv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsRoot = alsRoot;
        this.blueIv = blueIv;
        this.btn1Iv = btn1Iv;
        this.btn2Iv = btn2Iv;
        this.btn3Iv = btn3Iv;
        this.btn4Iv = btn4Iv;
        this.btn5Iv = btn5Iv;
        this.guideline = guideline;
        this.imageView1 = imageView1;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.itemViewSkinDefault = itemViewSkinDefault;
        this.menuButton1 = menuButton1;
        this.menuButton2 = menuButton2;
        this.menuButton3 = menuButton3;
        this.menuButton4 = menuButton4;
        this.menuButton5 = menuButton5;
        this.menuConstraintLayout = menuConstraintLayout;
        this.redIv = redIv;
        this.skinLl = skinLl;
        this.viewPage = viewPage;
        this.yellowIv = yellowIv;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id7_ui, root, attachToRoot, component);
    }

    public static AlsId7UiMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id7_ui, null, false, component);
    }

    public static AlsId7UiMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding bind(View view, Object component) {
        return (AlsId7UiMainBinding) bind(component, view, C0899R.C0902layout.activity_main_als_id7_ui);
    }
}

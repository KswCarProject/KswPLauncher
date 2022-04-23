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
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7_ui.view.CustomViewpager;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AlsId7UiMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout alsRoot2, ImageView blueIv2, ImageView btn1Iv2, ImageView btn2Iv2, ImageView btn3Iv2, ImageView btn4Iv2, ImageView btn5Iv2, Guideline guideline2, ImageView imageView12, ImageView imageView32, ImageView imageView42, ImageView itemViewSkinDefault2, RelativeLayout menuButton12, RelativeLayout menuButton22, RelativeLayout menuButton32, RelativeLayout menuButton42, RelativeLayout menuButton52, LinearLayout menuConstraintLayout2, ImageView redIv2, RelativeLayout skinLl2, CustomViewpager viewPage2, ImageView yellowIv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsRoot = alsRoot2;
        this.blueIv = blueIv2;
        this.btn1Iv = btn1Iv2;
        this.btn2Iv = btn2Iv2;
        this.btn3Iv = btn3Iv2;
        this.btn4Iv = btn4Iv2;
        this.btn5Iv = btn5Iv2;
        this.guideline = guideline2;
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.itemViewSkinDefault = itemViewSkinDefault2;
        this.menuButton1 = menuButton12;
        this.menuButton2 = menuButton22;
        this.menuButton3 = menuButton32;
        this.menuButton4 = menuButton42;
        this.menuButton5 = menuButton52;
        this.menuConstraintLayout = menuConstraintLayout2;
        this.redIv = redIv2;
        this.skinLl = skinLl2;
        this.viewPage = viewPage2;
        this.yellowIv = yellowIv2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id7_ui, root, attachToRoot, component);
    }

    public static AlsId7UiMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id7_ui, (ViewGroup) null, false, component);
    }

    public static AlsId7UiMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMainBinding bind(View view, Object component) {
        return (AlsId7UiMainBinding) bind(component, view, R.layout.activity_main_als_id7_ui);
    }
}

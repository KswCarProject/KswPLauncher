package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public abstract class FraBenzgsOneBinding extends ViewDataBinding {
    public final CustomFontTextView benzGsHomeBt;
    public final RelativeLayout benzGsHomeBtBtn;
    public final CustomFontTextView benzGsHomeBtHint;
    public final CustomFontTextView benzGsHomeCar;
    public final RelativeLayout benzGsHomeCarBtn;
    public final CustomFontTextView benzGsHomeCarHint;
    public final CustomFontTextView benzGsHomeMusic;
    public final RelativeLayout benzGsHomeMusicBtn;
    public final CustomFontTextView benzGsHomeMusicHint;
    public final CustomFontTextView benzGsHomeNavi;
    public final RelativeLayout benzGsHomeNaviBtn;
    public final CustomFontTextView benzGsHomeNaviHint;
    public final CustomFontTextView benzGsHomeSet;
    public final RelativeLayout benzGsHomeSetBtn;
    public final CustomFontTextView benzGsHomeSetHint;
    public final LinearLayout benzgsHomeOne;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(BenzGsViewMoel benzGsViewMoel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FraBenzgsOneBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBt2, RelativeLayout benzGsHomeBtBtn2, CustomFontTextView benzGsHomeBtHint2, CustomFontTextView benzGsHomeCar2, RelativeLayout benzGsHomeCarBtn2, CustomFontTextView benzGsHomeCarHint2, CustomFontTextView benzGsHomeMusic2, RelativeLayout benzGsHomeMusicBtn2, CustomFontTextView benzGsHomeMusicHint2, CustomFontTextView benzGsHomeNavi2, RelativeLayout benzGsHomeNaviBtn2, CustomFontTextView benzGsHomeNaviHint2, CustomFontTextView benzGsHomeSet2, RelativeLayout benzGsHomeSetBtn2, CustomFontTextView benzGsHomeSetHint2, LinearLayout benzgsHomeOne2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzGsHomeBt = benzGsHomeBt2;
        this.benzGsHomeBtBtn = benzGsHomeBtBtn2;
        this.benzGsHomeBtHint = benzGsHomeBtHint2;
        this.benzGsHomeCar = benzGsHomeCar2;
        this.benzGsHomeCarBtn = benzGsHomeCarBtn2;
        this.benzGsHomeCarHint = benzGsHomeCarHint2;
        this.benzGsHomeMusic = benzGsHomeMusic2;
        this.benzGsHomeMusicBtn = benzGsHomeMusicBtn2;
        this.benzGsHomeMusicHint = benzGsHomeMusicHint2;
        this.benzGsHomeNavi = benzGsHomeNavi2;
        this.benzGsHomeNaviBtn = benzGsHomeNaviBtn2;
        this.benzGsHomeNaviHint = benzGsHomeNaviHint2;
        this.benzGsHomeSet = benzGsHomeSet2;
        this.benzGsHomeSetBtn = benzGsHomeSetBtn2;
        this.benzGsHomeSetHint = benzGsHomeSetHint2;
        this.benzgsHomeOne = benzgsHomeOne2;
    }

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBenzgsOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_benzgs_one, root, attachToRoot, component);
    }

    public static FraBenzgsOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBenzgsOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_benzgs_one, (ViewGroup) null, false, component);
    }

    public static FraBenzgsOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding bind(View view, Object component) {
        return (FraBenzgsOneBinding) bind(component, view, R.layout.fra_benzgs_one);
    }
}

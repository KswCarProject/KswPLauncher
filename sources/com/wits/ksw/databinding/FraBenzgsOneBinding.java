package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(BenzGsViewMoel vm);

    protected FraBenzgsOneBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBt, RelativeLayout benzGsHomeBtBtn, CustomFontTextView benzGsHomeBtHint, CustomFontTextView benzGsHomeCar, RelativeLayout benzGsHomeCarBtn, CustomFontTextView benzGsHomeCarHint, CustomFontTextView benzGsHomeMusic, RelativeLayout benzGsHomeMusicBtn, CustomFontTextView benzGsHomeMusicHint, CustomFontTextView benzGsHomeNavi, RelativeLayout benzGsHomeNaviBtn, CustomFontTextView benzGsHomeNaviHint, CustomFontTextView benzGsHomeSet, RelativeLayout benzGsHomeSetBtn, CustomFontTextView benzGsHomeSetHint, LinearLayout benzgsHomeOne) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzGsHomeBt = benzGsHomeBt;
        this.benzGsHomeBtBtn = benzGsHomeBtBtn;
        this.benzGsHomeBtHint = benzGsHomeBtHint;
        this.benzGsHomeCar = benzGsHomeCar;
        this.benzGsHomeCarBtn = benzGsHomeCarBtn;
        this.benzGsHomeCarHint = benzGsHomeCarHint;
        this.benzGsHomeMusic = benzGsHomeMusic;
        this.benzGsHomeMusicBtn = benzGsHomeMusicBtn;
        this.benzGsHomeMusicHint = benzGsHomeMusicHint;
        this.benzGsHomeNavi = benzGsHomeNavi;
        this.benzGsHomeNaviBtn = benzGsHomeNaviBtn;
        this.benzGsHomeNaviHint = benzGsHomeNaviHint;
        this.benzGsHomeSet = benzGsHomeSet;
        this.benzGsHomeSetBtn = benzGsHomeSetBtn;
        this.benzGsHomeSetHint = benzGsHomeSetHint;
        this.benzgsHomeOne = benzgsHomeOne;
    }

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBenzgsOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_benzgs_one, root, attachToRoot, component);
    }

    public static FraBenzgsOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBenzgsOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_benzgs_one, null, false, component);
    }

    public static FraBenzgsOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsOneBinding bind(View view, Object component) {
        return (FraBenzgsOneBinding) bind(component, view, C0899R.C0902layout.fra_benzgs_one);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public abstract class FraBenzgsOneBinding extends ViewDataBinding {
    @NonNull
    public final CustomFontTextView benzGsHomeBt;
    @NonNull
    public final RelativeLayout benzGsHomeBtBtn;
    @NonNull
    public final CustomFontTextView benzGsHomeBtHint;
    @NonNull
    public final CustomFontTextView benzGsHomeCar;
    @NonNull
    public final RelativeLayout benzGsHomeCarBtn;
    @NonNull
    public final CustomFontTextView benzGsHomeCarHint;
    @NonNull
    public final CustomFontTextView benzGsHomeMusic;
    @NonNull
    public final RelativeLayout benzGsHomeMusicBtn;
    @NonNull
    public final CustomFontTextView benzGsHomeMusicHint;
    @NonNull
    public final CustomFontTextView benzGsHomeNavi;
    @NonNull
    public final RelativeLayout benzGsHomeNaviBtn;
    @NonNull
    public final CustomFontTextView benzGsHomeNaviHint;
    @NonNull
    public final CustomFontTextView benzGsHomeSet;
    @NonNull
    public final RelativeLayout benzGsHomeSetBtn;
    @NonNull
    public final CustomFontTextView benzGsHomeSetHint;
    @NonNull
    public final LinearLayout benzgsHomeOne;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(@Nullable BenzGsViewMoel benzGsViewMoel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FraBenzgsOneBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBt2, RelativeLayout benzGsHomeBtBtn2, CustomFontTextView benzGsHomeBtHint2, CustomFontTextView benzGsHomeCar2, RelativeLayout benzGsHomeCarBtn2, CustomFontTextView benzGsHomeCarHint2, CustomFontTextView benzGsHomeMusic2, RelativeLayout benzGsHomeMusicBtn2, CustomFontTextView benzGsHomeMusicHint2, CustomFontTextView benzGsHomeNavi2, RelativeLayout benzGsHomeNaviBtn2, CustomFontTextView benzGsHomeNaviHint2, CustomFontTextView benzGsHomeSet2, RelativeLayout benzGsHomeSetBtn2, CustomFontTextView benzGsHomeSetHint2, LinearLayout benzgsHomeOne2) {
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

    @Nullable
    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    @NonNull
    public static FraBenzgsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBenzgsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FraBenzgsOneBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_one, root, attachToRoot, component);
    }

    @NonNull
    public static FraBenzgsOneBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBenzgsOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FraBenzgsOneBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_one, (ViewGroup) null, false, component);
    }

    public static FraBenzgsOneBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FraBenzgsOneBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FraBenzgsOneBinding) bind(component, view, R.layout.fra_benzgs_one);
    }
}

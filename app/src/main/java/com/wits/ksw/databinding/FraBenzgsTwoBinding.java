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
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public abstract class FraBenzgsTwoBinding extends ViewDataBinding {
    @NonNull
    public final CustomFontTextView benzGsHomeBrowser;
    @NonNull
    public final CustomFontTextView benzGsHomeBrowserHint;
    @NonNull
    public final CustomFontTextView benzGsHomeDash;
    @NonNull
    public final CustomFontTextView benzGsHomeDashHint;
    @NonNull
    public final CustomFontTextView benzGsHomeFilema;
    @NonNull
    public final CustomFontTextView benzGsHomeFilemaHint;
    @NonNull
    public final CustomFontTextView benzGsHomePlink;
    @NonNull
    public final CustomFontTextView benzGsHomePlinkHint;
    @NonNull
    public final CustomFontTextView benzGsHomeVide;
    @NonNull
    public final CustomFontTextView benzGsHomeVideHint;
    @NonNull
    public final LinearLayout benzgsHomeTwo;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(@Nullable BenzGsViewMoel benzGsViewMoel);

    protected FraBenzgsTwoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBrowser2, CustomFontTextView benzGsHomeBrowserHint2, CustomFontTextView benzGsHomeDash2, CustomFontTextView benzGsHomeDashHint2, CustomFontTextView benzGsHomeFilema2, CustomFontTextView benzGsHomeFilemaHint2, CustomFontTextView benzGsHomePlink2, CustomFontTextView benzGsHomePlinkHint2, CustomFontTextView benzGsHomeVide2, CustomFontTextView benzGsHomeVideHint2, LinearLayout benzgsHomeTwo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzGsHomeBrowser = benzGsHomeBrowser2;
        this.benzGsHomeBrowserHint = benzGsHomeBrowserHint2;
        this.benzGsHomeDash = benzGsHomeDash2;
        this.benzGsHomeDashHint = benzGsHomeDashHint2;
        this.benzGsHomeFilema = benzGsHomeFilema2;
        this.benzGsHomeFilemaHint = benzGsHomeFilemaHint2;
        this.benzGsHomePlink = benzGsHomePlink2;
        this.benzGsHomePlinkHint = benzGsHomePlinkHint2;
        this.benzGsHomeVide = benzGsHomeVide2;
        this.benzGsHomeVideHint = benzGsHomeVideHint2;
        this.benzgsHomeTwo = benzgsHomeTwo2;
    }

    @Nullable
    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    @NonNull
    public static FraBenzgsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBenzgsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (FraBenzgsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_two, root, attachToRoot, component);
    }

    @NonNull
    public static FraBenzgsTwoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FraBenzgsTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (FraBenzgsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_two, (ViewGroup) null, false, component);
    }

    public static FraBenzgsTwoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FraBenzgsTwoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (FraBenzgsTwoBinding) bind(component, view, R.layout.fra_benzgs_two);
    }
}

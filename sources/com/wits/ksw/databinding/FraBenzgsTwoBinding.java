package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public abstract class FraBenzgsTwoBinding extends ViewDataBinding {
    public final CustomFontTextView benzGsHomeBrowser;
    public final CustomFontTextView benzGsHomeBrowserHint;
    public final CustomFontTextView benzGsHomeDash;
    public final CustomFontTextView benzGsHomeDashHint;
    public final CustomFontTextView benzGsHomeFilema;
    public final CustomFontTextView benzGsHomeFilemaHint;
    public final CustomFontTextView benzGsHomePlink;
    public final CustomFontTextView benzGsHomePlinkHint;
    public final CustomFontTextView benzGsHomeVide;
    public final CustomFontTextView benzGsHomeVideHint;
    public final LinearLayout benzgsHomeTwo;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(BenzGsViewMoel benzGsViewMoel);

    protected FraBenzgsTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBrowser2, CustomFontTextView benzGsHomeBrowserHint2, CustomFontTextView benzGsHomeDash2, CustomFontTextView benzGsHomeDashHint2, CustomFontTextView benzGsHomeFilema2, CustomFontTextView benzGsHomeFilemaHint2, CustomFontTextView benzGsHomePlink2, CustomFontTextView benzGsHomePlinkHint2, CustomFontTextView benzGsHomeVide2, CustomFontTextView benzGsHomeVideHint2, LinearLayout benzgsHomeTwo2) {
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

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBenzgsTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_benzgs_two, root, attachToRoot, component);
    }

    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBenzgsTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fra_benzgs_two, (ViewGroup) null, false, component);
    }

    public static FraBenzgsTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding bind(View view, Object component) {
        return (FraBenzgsTwoBinding) bind(component, view, R.layout.fra_benzgs_two);
    }
}

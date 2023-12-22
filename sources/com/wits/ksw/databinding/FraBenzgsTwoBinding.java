package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(BenzGsViewMoel vm);

    protected FraBenzgsTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomFontTextView benzGsHomeBrowser, CustomFontTextView benzGsHomeBrowserHint, CustomFontTextView benzGsHomeDash, CustomFontTextView benzGsHomeDashHint, CustomFontTextView benzGsHomeFilema, CustomFontTextView benzGsHomeFilemaHint, CustomFontTextView benzGsHomePlink, CustomFontTextView benzGsHomePlinkHint, CustomFontTextView benzGsHomeVide, CustomFontTextView benzGsHomeVideHint, LinearLayout benzgsHomeTwo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzGsHomeBrowser = benzGsHomeBrowser;
        this.benzGsHomeBrowserHint = benzGsHomeBrowserHint;
        this.benzGsHomeDash = benzGsHomeDash;
        this.benzGsHomeDashHint = benzGsHomeDashHint;
        this.benzGsHomeFilema = benzGsHomeFilema;
        this.benzGsHomeFilemaHint = benzGsHomeFilemaHint;
        this.benzGsHomePlink = benzGsHomePlink;
        this.benzGsHomePlinkHint = benzGsHomePlinkHint;
        this.benzGsHomeVide = benzGsHomeVide;
        this.benzGsHomeVideHint = benzGsHomeVideHint;
        this.benzgsHomeTwo = benzgsHomeTwo;
    }

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FraBenzgsTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_benzgs_two, root, attachToRoot, component);
    }

    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (FraBenzgsTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fra_benzgs_two, null, false, component);
    }

    public static FraBenzgsTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FraBenzgsTwoBinding bind(View view, Object component) {
        return (FraBenzgsTwoBinding) bind(component, view, C0899R.C0902layout.fra_benzgs_two);
    }
}

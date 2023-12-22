package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class LexusLsBottomFragmentTwo_V2 extends ViewDataBinding {
    public final ImageView ivLexusLsAdd;
    public final ImageView ivLexusLsDashboard;
    public final ImageView ivLexusLsDvr;
    public final ImageView ivLexusLsFile;
    public final ImageView ivLexusLsPhonelink;
    public final ImageView ivLexusLsWeather;
    public final LinearLayout lexusLsLlBottomFragTwo;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected LexusLsBottomFragmentTwo_V2(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsAdd, ImageView ivLexusLsDashboard, ImageView ivLexusLsDvr, ImageView ivLexusLsFile, ImageView ivLexusLsPhonelink, ImageView ivLexusLsWeather, LinearLayout lexusLsLlBottomFragTwo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsAdd = ivLexusLsAdd;
        this.ivLexusLsDashboard = ivLexusLsDashboard;
        this.ivLexusLsDvr = ivLexusLsDvr;
        this.ivLexusLsFile = ivLexusLsFile;
        this.ivLexusLsPhonelink = ivLexusLsPhonelink;
        this.ivLexusLsWeather = ivLexusLsWeather;
        this.lexusLsLlBottomFragTwo = lexusLsLlBottomFragTwo;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsBottomFragmentTwo_V2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_two_v2, root, attachToRoot, component);
    }

    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, Object component) {
        return (LexusLsBottomFragmentTwo_V2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_two_v2, null, false, component);
    }

    public static LexusLsBottomFragmentTwo_V2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 bind(View view, Object component) {
        return (LexusLsBottomFragmentTwo_V2) bind(component, view, C0899R.C0902layout.lexus_ls_bottom_fragment_two_v2);
    }
}

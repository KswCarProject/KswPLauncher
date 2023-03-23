package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected LexusLsBottomFragmentTwo_V2(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsAdd2, ImageView ivLexusLsDashboard2, ImageView ivLexusLsDvr2, ImageView ivLexusLsFile2, ImageView ivLexusLsPhonelink2, ImageView ivLexusLsWeather2, LinearLayout lexusLsLlBottomFragTwo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsAdd = ivLexusLsAdd2;
        this.ivLexusLsDashboard = ivLexusLsDashboard2;
        this.ivLexusLsDvr = ivLexusLsDvr2;
        this.ivLexusLsFile = ivLexusLsFile2;
        this.ivLexusLsPhonelink = ivLexusLsPhonelink2;
        this.ivLexusLsWeather = ivLexusLsWeather2;
        this.lexusLsLlBottomFragTwo = lexusLsLlBottomFragTwo2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsBottomFragmentTwo_V2) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_bottom_fragment_two_v2, root, attachToRoot, component);
    }

    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 inflate(LayoutInflater inflater, Object component) {
        return (LexusLsBottomFragmentTwo_V2) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_bottom_fragment_two_v2, (ViewGroup) null, false, component);
    }

    public static LexusLsBottomFragmentTwo_V2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentTwo_V2 bind(View view, Object component) {
        return (LexusLsBottomFragmentTwo_V2) bind(component, view, R.layout.lexus_ls_bottom_fragment_two_v2);
    }
}

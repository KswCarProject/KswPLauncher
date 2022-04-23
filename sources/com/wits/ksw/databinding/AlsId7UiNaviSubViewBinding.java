package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinImageView;

public abstract class AlsId7UiNaviSubViewBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final ConstraintLayout naviConstraintLayout;
    public final CustomSkinImageView naviImageView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviViewModel(LauncherViewModel launcherViewModel);

    protected AlsId7UiNaviSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout naviConstraintLayout2, CustomSkinImageView naviImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviConstraintLayout = naviConstraintLayout2;
        this.naviImageView = naviImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static AlsId7UiNaviSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiNaviSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_ui_sub_navi_view, root, attachToRoot, component);
    }

    public static AlsId7UiNaviSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiNaviSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_ui_sub_navi_view, (ViewGroup) null, false, component);
    }

    public static AlsId7UiNaviSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiNaviSubViewBinding bind(View view, Object component) {
        return (AlsId7UiNaviSubViewBinding) bind(component, view, R.layout.als_id7_ui_sub_navi_view);
    }
}

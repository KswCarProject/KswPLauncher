package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7UiSubPhoneViewBinding extends ViewDataBinding {
    public final ImageView alsId7MainPhoneImg;
    public final TextView dayTextView;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final TextView monthTextView;
    public final TextView phoneConnectionTextView;
    public final ConstraintLayout phoneConstraintLayout;
    public final CustomSkinImageView phoneImageView;
    public final TextView textView2;

    public abstract void setNaviViewModel(LauncherViewModel NaviViewModel);

    protected AlsId7UiSubPhoneViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView alsId7MainPhoneImg, TextView dayTextView, TextView monthTextView, TextView phoneConnectionTextView, ConstraintLayout phoneConstraintLayout, CustomSkinImageView phoneImageView, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsId7MainPhoneImg = alsId7MainPhoneImg;
        this.dayTextView = dayTextView;
        this.monthTextView = monthTextView;
        this.phoneConnectionTextView = phoneConnectionTextView;
        this.phoneConstraintLayout = phoneConstraintLayout;
        this.phoneImageView = phoneImageView;
        this.textView2 = textView2;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static AlsId7UiSubPhoneViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubPhoneViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiSubPhoneViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_phone_view, root, attachToRoot, component);
    }

    public static AlsId7UiSubPhoneViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubPhoneViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiSubPhoneViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_phone_view, null, false, component);
    }

    public static AlsId7UiSubPhoneViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiSubPhoneViewBinding bind(View view, Object component) {
        return (AlsId7UiSubPhoneViewBinding) bind(component, view, C0899R.C0902layout.als_id7_ui_sub_phone_view);
    }
}

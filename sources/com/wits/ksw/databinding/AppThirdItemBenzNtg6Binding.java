package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.settings.utlis_view.RtlNaviRadioButton;

/* loaded from: classes7.dex */
public abstract class AppThirdItemBenzNtg6Binding extends ViewDataBinding {
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final ImageView nameImageView;
    public final RtlNaviRadioButton rbtApps;
    public final TextView textView;

    public abstract void setListItem(LexusLsAppSelBean listItem);

    protected AppThirdItemBenzNtg6Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView nameImageView, RtlNaviRadioButton rbtApps, TextView textView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.nameImageView = nameImageView;
        this.rbtApps = rbtApps;
        this.textView = textView;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemBenzNtg6Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_benz_ntg6, root, attachToRoot, component);
    }

    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemBenzNtg6Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_benz_ntg6, null, false, component);
    }

    public static AppThirdItemBenzNtg6Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding bind(View view, Object component) {
        return (AppThirdItemBenzNtg6Binding) bind(component, view, C0899R.C0902layout.app_third_item_benz_ntg6);
    }
}

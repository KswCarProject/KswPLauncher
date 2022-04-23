package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.settings.utlis_view.RtlNaviRadioButton;

public abstract class AppThirdItemBenzNtg6Binding extends ViewDataBinding {
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final ImageView nameImageView;
    public final RtlNaviRadioButton rbtApps;
    public final TextView textView;

    public abstract void setListItem(LexusLsAppSelBean lexusLsAppSelBean);

    protected AppThirdItemBenzNtg6Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView nameImageView2, RtlNaviRadioButton rbtApps2, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.nameImageView = nameImageView2;
        this.rbtApps = rbtApps2;
        this.textView = textView2;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemBenzNtg6Binding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_benz_ntg6, root, attachToRoot, component);
    }

    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemBenzNtg6Binding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_benz_ntg6, (ViewGroup) null, false, component);
    }

    public static AppThirdItemBenzNtg6Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemBenzNtg6Binding bind(View view, Object component) {
        return (AppThirdItemBenzNtg6Binding) bind(component, view, R.layout.app_third_item_benz_ntg6);
    }
}

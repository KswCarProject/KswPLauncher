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
import com.wits.ksw.settings.utlis_view.CustomRadioButton;

public abstract class AppThirdItemAlsId7UiBinding extends ViewDataBinding {
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final ImageView nameImageView;
    public final CustomRadioButton rbtApps;
    public final TextView textView;

    public abstract void setListItem(LexusLsAppSelBean lexusLsAppSelBean);

    protected AppThirdItemAlsId7UiBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView nameImageView2, CustomRadioButton rbtApps2, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.nameImageView = nameImageView2;
        this.rbtApps = rbtApps2;
        this.textView = textView2;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemAlsId7UiBinding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_als_id7_ui, root, attachToRoot, component);
    }

    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemAlsId7UiBinding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_als_id7_ui, (ViewGroup) null, false, component);
    }

    public static AppThirdItemAlsId7UiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding bind(View view, Object component) {
        return (AppThirdItemAlsId7UiBinding) bind(component, view, R.layout.app_third_item_als_id7_ui);
    }
}

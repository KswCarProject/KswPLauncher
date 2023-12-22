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
import com.wits.ksw.settings.utlis_view.CustomRadioButton;

/* loaded from: classes7.dex */
public abstract class AppThirdItemAlsId7UiBinding extends ViewDataBinding {
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final ImageView nameImageView;
    public final CustomRadioButton rbtApps;
    public final TextView textView;

    public abstract void setListItem(LexusLsAppSelBean listItem);

    protected AppThirdItemAlsId7UiBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView nameImageView, CustomRadioButton rbtApps, TextView textView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.nameImageView = nameImageView;
        this.rbtApps = rbtApps;
        this.textView = textView;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemAlsId7UiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_als_id7_ui, root, attachToRoot, component);
    }

    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemAlsId7UiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_als_id7_ui, null, false, component);
    }

    public static AppThirdItemAlsId7UiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAlsId7UiBinding bind(View view, Object component) {
        return (AppThirdItemAlsId7UiBinding) bind(component, view, C0899R.C0902layout.app_third_item_als_id7_ui);
    }
}

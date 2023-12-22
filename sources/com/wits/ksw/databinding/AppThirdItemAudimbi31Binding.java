package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;

/* loaded from: classes7.dex */
public abstract class AppThirdItemAudimbi31Binding extends ViewDataBinding {
    public final ImageView ivAppIcon;
    public final ImageView ivCheck;
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final LinearLayout rbtApps;
    public final TextView tvAppName;

    public abstract void setListItem(LexusLsAppSelBean listItem);

    protected AppThirdItemAudimbi31Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAppIcon, ImageView ivCheck, LinearLayout rbtApps, TextView tvAppName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAppIcon = ivAppIcon;
        this.ivCheck = ivCheck;
        this.rbtApps = rbtApps;
        this.tvAppName = tvAppName;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemAudimbi31Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_audimbi3_1, root, attachToRoot, component);
    }

    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemAudimbi31Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.app_third_item_audimbi3_1, null, false, component);
    }

    public static AppThirdItemAudimbi31Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding bind(View view, Object component) {
        return (AppThirdItemAudimbi31Binding) bind(component, view, C0899R.C0902layout.app_third_item_audimbi3_1);
    }
}

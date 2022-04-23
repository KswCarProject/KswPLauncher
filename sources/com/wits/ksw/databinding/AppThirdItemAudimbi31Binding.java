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
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;

public abstract class AppThirdItemAudimbi31Binding extends ViewDataBinding {
    public final ImageView ivAppIcon;
    public final ImageView ivCheck;
    @Bindable
    protected LexusLsAppSelBean mListItem;
    public final LinearLayout rbtApps;
    public final TextView tvAppName;

    public abstract void setListItem(LexusLsAppSelBean lexusLsAppSelBean);

    protected AppThirdItemAudimbi31Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAppIcon2, ImageView ivCheck2, LinearLayout rbtApps2, TextView tvAppName2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAppIcon = ivAppIcon2;
        this.ivCheck = ivCheck2;
        this.rbtApps = rbtApps2;
        this.tvAppName = tvAppName2;
    }

    public LexusLsAppSelBean getListItem() {
        return this.mListItem;
    }

    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AppThirdItemAudimbi31Binding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_audimbi3_1, root, attachToRoot, component);
    }

    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding inflate(LayoutInflater inflater, Object component) {
        return (AppThirdItemAudimbi31Binding) ViewDataBinding.inflateInternal(inflater, R.layout.app_third_item_audimbi3_1, (ViewGroup) null, false, component);
    }

    public static AppThirdItemAudimbi31Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AppThirdItemAudimbi31Binding bind(View view, Object component) {
        return (AppThirdItemAudimbi31Binding) bind(component, view, R.layout.app_third_item_audimbi3_1);
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.launcher.bean.AppInfo;

public class Id7AppItemBindingSw600dpLandImpl extends Id7AppItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public Id7AppItemBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private Id7AppItemBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0], bindings[1], bindings[2]);
        this.mDirtyFlags = -1;
        this.BcItemConstraintLayout.setTag((Object) null);
        this.nameImageView.setTag((Object) null);
        this.textView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        if (11 != variableId) {
            return false;
        }
        setListItem((AppInfo) variable);
        return true;
    }

    public void setListItem(AppInfo ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AppInfo listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        String listItemAppLable = null;
        if (!((dirtyFlags & 3) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
        }
        if ((3 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.nameImageView, listItemAppIcon);
            TextViewBindingAdapter.setText(this.textView, listItemAppLable);
        }
    }
}

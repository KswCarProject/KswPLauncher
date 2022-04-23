package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;

public class BcNtg5ItemBindingImpl extends BcNtg5ItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback92;
    private long mDirtyFlags;

    public BcNtg5ItemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private BcNtg5ItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[2], bindings[0]);
        this.mDirtyFlags = -1;
        this.appIcon.setTag((Object) null);
        this.appName.setTag((Object) null);
        this.naviCusLinearLayout.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback92 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (11 == variableId) {
            setListItem((BcItem) variable);
            return true;
        } else if (12 != variableId) {
            return false;
        } else {
            setMBcVieModel((BcNTG5ViewModel) variable);
            return true;
        }
    }

    public void setListItem(BcItem ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    public void setMBcVieModel(BcNTG5ViewModel MBcVieModel) {
        this.mMBcVieModel = MBcVieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(12);
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
        BcItem listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        BcNTG5ViewModel bcNTG5ViewModel = this.mMBcVieModel;
        String listItemAppLable = null;
        if (!((dirtyFlags & 5) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appIcon, listItemAppIcon);
            TextViewBindingAdapter.setText(this.appName, listItemAppLable);
        }
        if ((4 & dirtyFlags) != 0) {
            this.appIcon.setOnClickListener(this.mCallback92);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        BcItem listItem = this.mListItem;
        BcNTG5ViewModel mBcVieModel = this.mMBcVieModel;
        if (mBcVieModel != null) {
            mBcVieModel.onItemClick(callbackArg_0, listItem);
        }
    }
}

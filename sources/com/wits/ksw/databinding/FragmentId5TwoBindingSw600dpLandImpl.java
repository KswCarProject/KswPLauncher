package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.CustomBcImageView;

public class FragmentId5TwoBindingSw600dpLandImpl extends FragmentId5TwoBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback316;
    private long mDirtyFlags;
    private final CustomBcImageView mboundView1;
    private final TextView mboundView2;

    public FragmentId5TwoBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private FragmentId5TwoBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0]);
        this.mDirtyFlags = -1;
        CustomBcImageView customBcImageView = bindings[1];
        this.mboundView1 = customBcImageView;
        customBcImageView.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        this.naviCusLinearLayout.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback316 = new OnClickListener(this, 1);
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
        if (20 == variableId) {
            setListItem((BcItem) variable);
            return true;
        } else if (21 != variableId) {
            return false;
        } else {
            setMBcVieModel((BcVieModel) variable);
            return true;
        }
    }

    public void setListItem(BcItem ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(20);
        super.requestRebind();
    }

    public void setMBcVieModel(BcVieModel MBcVieModel) {
        this.mMBcVieModel = MBcVieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(21);
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
        BcVieModel bcVieModel = this.mMBcVieModel;
        String listItemAppLable = null;
        if (!((dirtyFlags & 5) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView1, listItemAppIcon);
            TextViewBindingAdapter.setText(this.mboundView2, listItemAppLable);
        }
        if ((4 & dirtyFlags) != 0) {
            this.mboundView1.setOnClickListener(this.mCallback316);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        BcItem listItem = this.mListItem;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        if (mBcVieModel != null) {
            mBcVieModel.onItemClick(callbackArg_0, listItem);
        }
    }
}

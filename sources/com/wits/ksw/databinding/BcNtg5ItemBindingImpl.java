package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.view.ntg5.NTG5ImageView;

/* loaded from: classes7.dex */
public class BcNtg5ItemBindingImpl extends BcNtg5ItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback88;
    private long mDirtyFlags;

    public BcNtg5ItemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private BcNtg5ItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (NTG5ImageView) bindings[1], (TextView) bindings[2], (RelativeLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.appIcon.setTag(null);
        this.appName.setTag(null);
        this.naviCusLinearLayout.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback88 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (20 == variableId) {
            setListItem((BcItem) variable);
            return true;
        } else if (21 == variableId) {
            setMBcVieModel((BcNTG5ViewModel) variable);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.wits.ksw.databinding.BcNtg5ItemBinding
    public void setListItem(BcItem ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(20);
        super.requestRebind();
    }

    @Override // com.wits.ksw.databinding.BcNtg5ItemBinding
    public void setMBcVieModel(BcNTG5ViewModel MBcVieModel) {
        this.mMBcVieModel = MBcVieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(21);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BcItem listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        BcNTG5ViewModel bcNTG5ViewModel = this.mMBcVieModel;
        String listItemAppLable = null;
        if ((dirtyFlags & 5) != 0 && listItem != null) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.appIcon, listItemAppIcon);
            TextViewBindingAdapter.setText(this.appName, listItemAppLable);
        }
        if ((4 & dirtyFlags) != 0) {
            this.appIcon.setOnClickListener(this.mCallback88);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        BcItem listItem = this.mListItem;
        BcNTG5ViewModel mBcVieModel = this.mMBcVieModel;
        boolean mBcVieModelJavaLangObjectNull = mBcVieModel != null;
        if (mBcVieModelJavaLangObjectNull) {
            mBcVieModel.onItemClick(callbackArg_0, listItem);
        }
    }
}

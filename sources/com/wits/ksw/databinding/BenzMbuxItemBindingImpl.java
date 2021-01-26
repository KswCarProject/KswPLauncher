package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;

public class BenzMbuxItemBindingImpl extends BenzMbuxItemBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback154;
    @Nullable
    private final View.OnClickListener mCallback155;
    @Nullable
    private final View.OnClickListener mCallback156;
    private long mDirtyFlags;
    @NonNull
    private final ImageView mboundView3;
    @NonNull
    private final ImageView mboundView4;

    static {
        sViewsWithIds.put(R.id.space, 5);
    }

    public BenzMbuxItemBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private BenzMbuxItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[2], bindings[0], bindings[5]);
        this.mDirtyFlags = -1;
        this.benzMbuxImageView.setTag((Object) null);
        this.benzMbuxTextView.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.naviCusLinearLayout.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback156 = new OnClickListener(this, 3);
        this.mCallback154 = new OnClickListener(this, 1);
        this.mCallback155 = new OnClickListener(this, 2);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (1 == variableId) {
            setListItem((BenzMbuxBean) variable);
            return true;
        } else if (14 != variableId) {
            return false;
        } else {
            setVieModel((BcVieModel) variable);
            return true;
        }
    }

    public void setListItem(@Nullable BenzMbuxBean ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    public void setVieModel(@Nullable BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
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
        BenzMbuxBean listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        BcVieModel bcVieModel = this.mVieModel;
        Drawable listItemSubIcon1 = null;
        String listItemAppLable = null;
        Drawable listItemSubIcon2 = null;
        if (!((dirtyFlags & 5) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemSubIcon1 = listItem.getSubIcon1();
            listItemAppLable = listItem.getAppLable();
            listItemSubIcon2 = listItem.getSubIcon2();
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbuxImageView.setOnClickListener(this.mCallback154);
            this.mboundView3.setOnClickListener(this.mCallback155);
            this.mboundView4.setOnClickListener(this.mCallback156);
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.benzMbuxImageView, listItemAppIcon);
            TextViewBindingAdapter.setText(this.benzMbuxTextView, listItemAppLable);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, listItemSubIcon1);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, listItemSubIcon2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vieModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                BenzMbuxBean listItem = this.mListItem;
                BcVieModel vieModel = this.mVieModel;
                if (vieModel != null) {
                    vieModelJavaLangObjectNull = true;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel.onMbuxHomeItemClick(callbackArg_0, listItem);
                    return;
                }
                return;
            case 2:
                BenzMbuxBean listItem2 = this.mListItem;
                BcVieModel vieModel2 = this.mVieModel;
                if (vieModel2 != null) {
                    vieModelJavaLangObjectNull = true;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel2.onMbuxHomeItemClick(this.benzMbuxImageView, listItem2);
                    return;
                }
                return;
            case 3:
                BenzMbuxBean listItem3 = this.mListItem;
                BcVieModel vieModel3 = this.mVieModel;
                if (vieModel3 != null) {
                    vieModelJavaLangObjectNull = true;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel3.onMbuxHomeItemClick(this.benzMbuxImageView, listItem3);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

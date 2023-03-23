package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;

public class BenzMbuxItemBindingImpl extends BenzMbuxItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback117;
    private final View.OnClickListener mCallback118;
    private final View.OnClickListener mCallback119;
    private long mDirtyFlags;
    private final ImageView mboundView3;
    private final ImageView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.space, 5);
    }

    public BenzMbuxItemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private BenzMbuxItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[2], bindings[0], bindings[5]);
        this.mDirtyFlags = -1;
        this.benzMbuxImageView.setTag((Object) null);
        this.benzMbuxTextView.setTag((Object) null);
        ImageView imageView = bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag((Object) null);
        this.naviCusLinearLayout.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback117 = new OnClickListener(this, 1);
        this.mCallback118 = new OnClickListener(this, 2);
        this.mCallback119 = new OnClickListener(this, 3);
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
            setListItem((BenzMbuxBean) variable);
            return true;
        } else if (24 != variableId) {
            return false;
        } else {
            setVieModel((BcVieModel) variable);
            return true;
        }
    }

    public void setListItem(BenzMbuxBean ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(20);
        super.requestRebind();
    }

    public void setVieModel(BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(24);
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
        String listItemAppLable = null;
        Drawable listItemSubIcon1 = null;
        Drawable listItemSubIcon2 = null;
        if (!((dirtyFlags & 5) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
            listItemSubIcon1 = listItem.getSubIcon1();
            listItemSubIcon2 = listItem.getSubIcon2();
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbuxImageView.setOnClickListener(this.mCallback117);
            this.mboundView3.setOnClickListener(this.mCallback118);
            this.mboundView4.setOnClickListener(this.mCallback119);
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.benzMbuxImageView, listItemAppIcon);
            TextViewBindingAdapter.setText(this.benzMbuxTextView, listItemAppLable);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, listItemSubIcon1);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, listItemSubIcon2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vieModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                BenzMbuxBean listItem = this.mListItem;
                BcVieModel vieModel = this.mVieModel;
                if (vieModel == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel.onMbuxHomeItemClick(callbackArg_0, listItem);
                    return;
                }
                return;
            case 2:
                BenzMbuxBean listItem2 = this.mListItem;
                BcVieModel vieModel2 = this.mVieModel;
                if (vieModel2 == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel2.onMbuxHomeItemClick(this.benzMbuxImageView, listItem2);
                    return;
                }
                return;
            case 3:
                BenzMbuxBean listItem3 = this.mListItem;
                BcVieModel vieModel3 = this.mVieModel;
                if (vieModel3 == null) {
                    vieModelJavaLangObjectNull = false;
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

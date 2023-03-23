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
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Bean;

public class BenzMbux2021ItemBindingHdpi1920x720Impl extends BenzMbux2021ItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback329;
    private final View.OnClickListener mCallback330;
    private final View.OnClickListener mCallback331;
    private final View.OnClickListener mCallback332;
    private long mDirtyFlags;
    private final ImageView mboundView4;
    private final ImageView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.space, 6);
    }

    public BenzMbux2021ItemBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private BenzMbux2021ItemBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[2], bindings[3], bindings[0], bindings[6]);
        this.mDirtyFlags = -1;
        this.benzMbux2021ImageView.setTag((Object) null);
        this.benzMbux2021TextView.setTag((Object) null);
        this.benzMbux2021Tip.setTag((Object) null);
        ImageView imageView = bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[5];
        this.mboundView5 = imageView2;
        imageView2.setTag((Object) null);
        this.rlContain.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback332 = new OnClickListener(this, 4);
        this.mCallback331 = new OnClickListener(this, 3);
        this.mCallback330 = new OnClickListener(this, 2);
        this.mCallback329 = new OnClickListener(this, 1);
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
            setListItem((BenzMbux2021Bean) variable);
            return true;
        } else if (24 != variableId) {
            return false;
        } else {
            setVieModel((BcVieModel) variable);
            return true;
        }
    }

    public void setListItem(BenzMbux2021Bean ListItem) {
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
        BenzMbux2021Bean listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        BcVieModel bcVieModel = this.mVieModel;
        String listItemAppLable = null;
        Drawable listItemSubIcon1 = null;
        Drawable listItemSubIcon2 = null;
        String listItemAppTip = null;
        if (!((dirtyFlags & 5) == 0 || listItem == null)) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
            listItemSubIcon1 = listItem.getSubIcon1();
            listItemSubIcon2 = listItem.getSubIcon2();
            listItemAppTip = listItem.getAppTip();
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbux2021ImageView.setOnClickListener(this.mCallback329);
            this.benzMbux2021Tip.setOnClickListener(this.mCallback330);
            this.mboundView4.setOnClickListener(this.mCallback331);
            this.mboundView5.setOnClickListener(this.mCallback332);
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.benzMbux2021ImageView, listItemAppIcon);
            TextViewBindingAdapter.setText(this.benzMbux2021TextView, listItemAppLable);
            TextViewBindingAdapter.setText(this.benzMbux2021Tip, listItemAppTip);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, listItemSubIcon1);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, listItemSubIcon2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vieModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                BenzMbux2021Bean listItem = this.mListItem;
                BcVieModel vieModel = this.mVieModel;
                if (vieModel == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel.onMbux2021HomeItemClick(callbackArg_0, listItem);
                    return;
                }
                return;
            case 2:
                BenzMbux2021Bean listItem2 = this.mListItem;
                BcVieModel vieModel2 = this.mVieModel;
                if (vieModel2 == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel2.onMbux2021HomeItemClick(this.benzMbux2021ImageView, listItem2);
                    return;
                }
                return;
            case 3:
                BenzMbux2021Bean listItem3 = this.mListItem;
                BcVieModel vieModel3 = this.mVieModel;
                if (vieModel3 == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel3.onMbux2021HomeItemClick(this.benzMbux2021ImageView, listItem3);
                    return;
                }
                return;
            case 4:
                BenzMbux2021Bean listItem4 = this.mListItem;
                BcVieModel vieModel4 = this.mVieModel;
                if (vieModel4 == null) {
                    vieModelJavaLangObjectNull = false;
                }
                if (vieModelJavaLangObjectNull) {
                    vieModel4.onMbux2021HomeItemClick(this.benzMbux2021ImageView, listItem4);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

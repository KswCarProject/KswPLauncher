package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswBean;

/* loaded from: classes7.dex */
public class BenzMbux2021KswItemBindingImpl extends BenzMbux2021KswItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback492;
    private final View.OnClickListener mCallback493;
    private final View.OnClickListener mCallback494;
    private final View.OnClickListener mCallback495;
    private long mDirtyFlags;
    private final ImageView mboundView4;
    private final ImageView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.space, 6);
    }

    public BenzMbux2021KswItemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private BenzMbux2021KswItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (BenzMbuxItemView) bindings[1], (TextView) bindings[2], (TextView) bindings[3], (RelativeLayout) bindings[0], (View) bindings[6]);
        this.mDirtyFlags = -1L;
        this.benzMbux2021ImageView.setTag(null);
        this.benzMbux2021TextView.setTag(null);
        this.benzMbux2021Tip.setTag(null);
        ImageView imageView = (ImageView) bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[5];
        this.mboundView5 = imageView2;
        imageView2.setTag(null);
        this.rlContain.setTag("naviCusLinearLayout");
        setRootTag(root);
        this.mCallback493 = new OnClickListener(this, 2);
        this.mCallback492 = new OnClickListener(this, 1);
        this.mCallback495 = new OnClickListener(this, 4);
        this.mCallback494 = new OnClickListener(this, 3);
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
            setListItem((BenzMbux2021KswBean) variable);
            return true;
        } else if (24 == variableId) {
            setVieModel((BcVieModel) variable);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.wits.ksw.databinding.BenzMbux2021KswItemBinding
    public void setListItem(BenzMbux2021KswBean ListItem) {
        this.mListItem = ListItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(20);
        super.requestRebind();
    }

    @Override // com.wits.ksw.databinding.BenzMbux2021KswItemBinding
    public void setVieModel(BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(24);
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
        BenzMbux2021KswBean listItem = this.mListItem;
        Drawable listItemAppIcon = null;
        BcVieModel bcVieModel = this.mVieModel;
        String listItemAppLable = null;
        Drawable listItemSubIcon1 = null;
        Drawable listItemSubIcon2 = null;
        String listItemAppTip = null;
        if ((dirtyFlags & 5) != 0 && listItem != null) {
            listItemAppIcon = listItem.getAppIcon();
            listItemAppLable = listItem.getAppLable();
            listItemSubIcon1 = listItem.getSubIcon1();
            listItemSubIcon2 = listItem.getSubIcon2();
            listItemAppTip = listItem.getAppTip();
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbux2021ImageView.setOnClickListener(this.mCallback492);
            this.benzMbux2021Tip.setOnClickListener(this.mCallback493);
            this.mboundView4.setOnClickListener(this.mCallback494);
            this.mboundView5.setOnClickListener(this.mCallback495);
        }
        if ((5 & dirtyFlags) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.benzMbux2021ImageView, listItemAppIcon);
            TextViewBindingAdapter.setText(this.benzMbux2021TextView, listItemAppLable);
            TextViewBindingAdapter.setText(this.benzMbux2021Tip, listItemAppTip);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, listItemSubIcon1);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, listItemSubIcon2);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vieModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                BenzMbux2021KswBean listItem = this.mListItem;
                BcVieModel vieModel = this.mVieModel;
                vieModelJavaLangObjectNull = vieModel != null;
                if (vieModelJavaLangObjectNull) {
                    vieModel.onMbux2021KswHomeItemClick(callbackArg_0, listItem);
                    return;
                }
                return;
            case 2:
                BenzMbux2021KswBean listItem2 = this.mListItem;
                BcVieModel vieModel2 = this.mVieModel;
                vieModelJavaLangObjectNull = vieModel2 != null;
                if (vieModelJavaLangObjectNull) {
                    vieModel2.onMbux2021KswHomeItemClick(this.benzMbux2021ImageView, listItem2);
                    return;
                }
                return;
            case 3:
                BenzMbux2021KswBean listItem3 = this.mListItem;
                BcVieModel vieModel3 = this.mVieModel;
                vieModelJavaLangObjectNull = vieModel3 != null;
                if (vieModelJavaLangObjectNull) {
                    vieModel3.onMbux2021KswHomeItemClick(this.benzMbux2021ImageView, listItem3);
                    return;
                }
                return;
            case 4:
                BenzMbux2021KswBean listItem4 = this.mListItem;
                BcVieModel vieModel4 = this.mVieModel;
                vieModelJavaLangObjectNull = vieModel4 != null;
                if (vieModelJavaLangObjectNull) {
                    vieModel4.onMbux2021KswHomeItemClick(this.benzMbux2021ImageView, listItem4);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

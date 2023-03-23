package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id8LauncherLeftBarBindingImpl extends Id8LauncherLeftBarBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback194;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_left_1, 2);
        sparseIntArray.put(R.id.tv_left_1, 3);
        sparseIntArray.put(R.id.ll_left_2, 4);
        sparseIntArray.put(R.id.iv_left_2, 5);
        sparseIntArray.put(R.id.tv_left_2, 6);
        sparseIntArray.put(R.id.ll_left_3, 7);
        sparseIntArray.put(R.id.iv_left_3, 8);
        sparseIntArray.put(R.id.tv_left_3, 9);
        sparseIntArray.put(R.id.ll_left_4, 10);
        sparseIntArray.put(R.id.iv_left_4, 11);
        sparseIntArray.put(R.id.tv_left_4, 12);
    }

    public Id8LauncherLeftBarBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private Id8LauncherLeftBarBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[2], bindings[5], bindings[8], bindings[11], bindings[1], bindings[4], bindings[7], bindings[10], bindings[0], bindings[3], bindings[6], bindings[9], bindings[12]);
        this.mDirtyFlags = -1;
        this.llLeft1.setTag((Object) null);
        this.llLeftBarContainer.setTag((Object) null);
        setRootTag(root);
        this.mCallback194 = new OnClickListener(this, 1);
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
        if (8 != variableId) {
            return false;
        }
        setLeftViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLeftViewModel(LauncherViewModel LeftViewModel) {
        this.mLeftViewModel = LeftViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(8);
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
        LauncherViewModel launcherViewModel = this.mLeftViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.llLeft1.setOnClickListener(this.mCallback194);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel leftViewModel = this.mLeftViewModel;
        if (leftViewModel != null) {
            leftViewModel.openApps(callbackArg_0);
        }
    }
}

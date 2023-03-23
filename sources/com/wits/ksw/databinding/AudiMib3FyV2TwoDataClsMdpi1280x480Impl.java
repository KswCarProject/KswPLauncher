package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class AudiMib3FyV2TwoDataClsMdpi1280x480Impl extends AudiMib3FyV2TwoDataCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.dvr_itemview, 1);
        sparseIntArray.put(R.id.dvr_iv, 2);
        sparseIntArray.put(R.id.dvr_tv, 3);
        sparseIntArray.put(R.id.dashboard_itemview, 4);
        sparseIntArray.put(R.id.dashboard_iv, 5);
        sparseIntArray.put(R.id.dashboard_tv, 6);
        sparseIntArray.put(R.id.file_itemview, 7);
        sparseIntArray.put(R.id.file_iv, 8);
        sparseIntArray.put(R.id.file_tv, 9);
        sparseIntArray.put(R.id.browser_itemview, 10);
        sparseIntArray.put(R.id.browser_iv, 11);
        sparseIntArray.put(R.id.browser_tv, 12);
        sparseIntArray.put(R.id.app_itemview, 13);
        sparseIntArray.put(R.id.app_iv, 14);
        sparseIntArray.put(R.id.app_tv, 15);
    }

    public AudiMib3FyV2TwoDataClsMdpi1280x480Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiMib3FyV2TwoDataClsMdpi1280x480Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[13], bindings[14], bindings[15], bindings[10], bindings[11], bindings[12], bindings[4], bindings[5], bindings[6], bindings[1], bindings[2], bindings[3], bindings[7], bindings[8], bindings[9], bindings[0]);
        this.mDirtyFlags = -1;
        this.fragmentTwoLl.setTag((Object) null);
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}

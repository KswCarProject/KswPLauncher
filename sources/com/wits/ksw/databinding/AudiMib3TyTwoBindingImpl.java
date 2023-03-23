package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class AudiMib3TyTwoBindingImpl extends AudiMib3TyTwoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.car_itemview, 1);
        sparseIntArray.put(R.id.car_content_ll, 2);
        sparseIntArray.put(R.id.car_iv, 3);
        sparseIntArray.put(R.id.car_tv, 4);
        sparseIntArray.put(R.id.dashboard_itemview, 5);
        sparseIntArray.put(R.id.dashboard_content, 6);
        sparseIntArray.put(R.id.dashboard_content_ll, 7);
        sparseIntArray.put(R.id.dashboard_iv, 8);
        sparseIntArray.put(R.id.dashboard_tv, 9);
        sparseIntArray.put(R.id.app_itemview, 10);
        sparseIntArray.put(R.id.app_content_ll, 11);
        sparseIntArray.put(R.id.app_iv, 12);
        sparseIntArray.put(R.id.app_tv, 13);
        sparseIntArray.put(R.id.dvr_itemview, 14);
        sparseIntArray.put(R.id.dvr_content_ll, 15);
        sparseIntArray.put(R.id.dvr_iv, 16);
        sparseIntArray.put(R.id.dvr_tv, 17);
        sparseIntArray.put(R.id.set_itemview, 18);
        sparseIntArray.put(R.id.set_content_ll, 19);
        sparseIntArray.put(R.id.set_iv, 20);
        sparseIntArray.put(R.id.set_tv, 21);
    }

    public AudiMib3TyTwoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private AudiMib3TyTwoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[11], bindings[10], bindings[12], bindings[13], bindings[2], bindings[1], bindings[3], bindings[4], bindings[6], bindings[7], bindings[5], bindings[8], bindings[9], bindings[15], bindings[14], bindings[16], bindings[17], bindings[0], bindings[19], bindings[18], bindings[20], bindings[21]);
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

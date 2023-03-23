package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class BenzNtg6FyFragmentTwoClsHdpi1280x720Impl extends BenzNtg6FyFragmentTwoCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.video_rl, 1);
        sparseIntArray.put(R.id.video_itemview, 2);
        sparseIntArray.put(R.id.video_tv, 3);
        sparseIntArray.put(R.id.video_tip, 4);
        sparseIntArray.put(R.id.video_iv1, 5);
        sparseIntArray.put(R.id.space1, 6);
        sparseIntArray.put(R.id.video_iv2, 7);
        sparseIntArray.put(R.id.app_rl, 8);
        sparseIntArray.put(R.id.app_itemview, 9);
        sparseIntArray.put(R.id.app_tv, 10);
        sparseIntArray.put(R.id.app_tip, 11);
        sparseIntArray.put(R.id.app_iv1, 12);
        sparseIntArray.put(R.id.space2, 13);
        sparseIntArray.put(R.id.app_iv2, 14);
        sparseIntArray.put(R.id.phonelink_rl, 15);
        sparseIntArray.put(R.id.phonelink_itemview, 16);
        sparseIntArray.put(R.id.phonelink_tv, 17);
        sparseIntArray.put(R.id.phonelink_tip, 18);
        sparseIntArray.put(R.id.phone_iv1, 19);
        sparseIntArray.put(R.id.space3, 20);
        sparseIntArray.put(R.id.phone_iv2, 21);
        sparseIntArray.put(R.id.dashboard_rl, 22);
        sparseIntArray.put(R.id.dashboard_itemview, 23);
        sparseIntArray.put(R.id.dashboard_tv, 24);
        sparseIntArray.put(R.id.dashboard_tip, 25);
        sparseIntArray.put(R.id.dashboard_iv1, 26);
        sparseIntArray.put(R.id.space4, 27);
        sparseIntArray.put(R.id.dashboard_iv2, 28);
        sparseIntArray.put(R.id.dvr_rl, 29);
        sparseIntArray.put(R.id.dvr_itemview, 30);
        sparseIntArray.put(R.id.dvr_tv, 31);
        sparseIntArray.put(R.id.dvr_tip, 32);
        sparseIntArray.put(R.id.drv_iv1, 33);
        sparseIntArray.put(R.id.space5, 34);
        sparseIntArray.put(R.id.drv_iv2, 35);
    }

    public BenzNtg6FyFragmentTwoClsHdpi1280x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private BenzNtg6FyFragmentTwoClsHdpi1280x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[9], bindings[12], bindings[14], bindings[8], bindings[11], bindings[10], bindings[23], bindings[26], bindings[28], bindings[22], bindings[25], bindings[24], bindings[33], bindings[35], bindings[30], bindings[29], bindings[32], bindings[31], bindings[0], bindings[19], bindings[21], bindings[16], bindings[15], bindings[18], bindings[17], bindings[6], bindings[13], bindings[20], bindings[27], bindings[34], bindings[2], bindings[5], bindings[7], bindings[1], bindings[4], bindings[3]);
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

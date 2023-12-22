package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class AudiMib3TwoDataClsV2Impl extends AudiMib3TwoDataClsV2 {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.dvr_itemview, 1);
        sparseIntArray.put(C0899R.C0901id.dvr_iv, 2);
        sparseIntArray.put(C0899R.C0901id.dvr_tv, 3);
        sparseIntArray.put(C0899R.C0901id.dashboard_itemview, 4);
        sparseIntArray.put(C0899R.C0901id.dashboard_iv, 5);
        sparseIntArray.put(C0899R.C0901id.dashboard_tv, 6);
        sparseIntArray.put(C0899R.C0901id.file_itemview, 7);
        sparseIntArray.put(C0899R.C0901id.file_iv, 8);
        sparseIntArray.put(C0899R.C0901id.file_tv, 9);
        sparseIntArray.put(C0899R.C0901id.browser_itemview, 10);
        sparseIntArray.put(C0899R.C0901id.browser_iv, 11);
        sparseIntArray.put(C0899R.C0901id.browser_tv, 12);
        sparseIntArray.put(C0899R.C0901id.app_itemview, 13);
        sparseIntArray.put(C0899R.C0901id.app_iv, 14);
        sparseIntArray.put(C0899R.C0901id.app_tv, 15);
    }

    public AudiMib3TwoDataClsV2Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiMib3TwoDataClsV2Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (BenzMbuxItemView) bindings[13], (ImageView) bindings[14], (TextView) bindings[15], (BenzMbuxItemView) bindings[10], (ImageView) bindings[11], (TextView) bindings[12], (BenzMbuxItemView) bindings[4], (ImageView) bindings[5], (TextView) bindings[6], (BenzMbuxItemView) bindings[1], (ImageView) bindings[2], (TextView) bindings[3], (BenzMbuxItemView) bindings[7], (ImageView) bindings[8], (TextView) bindings[9], (LinearLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.fragmentTwoLl.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (25 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3TwoDataClsV2
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
    }
}

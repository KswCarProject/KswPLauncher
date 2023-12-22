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
public class AudiMib3TyTwoBindingMdpi1280x480Impl extends AudiMib3TyTwoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.car_itemview, 1);
        sparseIntArray.put(C0899R.C0901id.car_content_ll, 2);
        sparseIntArray.put(C0899R.C0901id.car_iv, 3);
        sparseIntArray.put(C0899R.C0901id.car_tv, 4);
        sparseIntArray.put(C0899R.C0901id.dashboard_itemview, 5);
        sparseIntArray.put(C0899R.C0901id.dashboard_content, 6);
        sparseIntArray.put(C0899R.C0901id.dashboard_content_ll, 7);
        sparseIntArray.put(C0899R.C0901id.dashboard_iv, 8);
        sparseIntArray.put(C0899R.C0901id.dashboard_tv, 9);
        sparseIntArray.put(C0899R.C0901id.app_itemview, 10);
        sparseIntArray.put(C0899R.C0901id.app_content_ll, 11);
        sparseIntArray.put(C0899R.C0901id.app_iv, 12);
        sparseIntArray.put(C0899R.C0901id.app_tv, 13);
        sparseIntArray.put(C0899R.C0901id.dvr_itemview, 14);
        sparseIntArray.put(C0899R.C0901id.dvr_content_ll, 15);
        sparseIntArray.put(C0899R.C0901id.dvr_iv, 16);
        sparseIntArray.put(C0899R.C0901id.dvr_tv, 17);
        sparseIntArray.put(C0899R.C0901id.set_itemview, 18);
        sparseIntArray.put(C0899R.C0901id.set_content_ll, 19);
        sparseIntArray.put(C0899R.C0901id.set_iv, 20);
        sparseIntArray.put(C0899R.C0901id.set_tv, 21);
    }

    public AudiMib3TyTwoBindingMdpi1280x480Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private AudiMib3TyTwoBindingMdpi1280x480Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[11], (BenzMbuxItemView) bindings[10], (ImageView) bindings[12], (TextView) bindings[13], (LinearLayout) bindings[2], (BenzMbuxItemView) bindings[1], (ImageView) bindings[3], (TextView) bindings[4], (TextView) bindings[6], (LinearLayout) bindings[7], (BenzMbuxItemView) bindings[5], (ImageView) bindings[8], (TextView) bindings[9], (LinearLayout) bindings[15], (BenzMbuxItemView) bindings[14], (ImageView) bindings[16], (TextView) bindings[17], (LinearLayout) bindings[0], (LinearLayout) bindings[19], (BenzMbuxItemView) bindings[18], (ImageView) bindings[20], (TextView) bindings[21]);
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

    @Override // com.wits.ksw.databinding.AudiMib3TyTwoBinding
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

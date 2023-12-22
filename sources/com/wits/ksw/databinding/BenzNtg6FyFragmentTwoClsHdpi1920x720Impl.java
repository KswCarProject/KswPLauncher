package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class BenzNtg6FyFragmentTwoClsHdpi1920x720Impl extends BenzNtg6FyFragmentTwoCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.video_rl, 1);
        sparseIntArray.put(C0899R.C0901id.video_itemview, 2);
        sparseIntArray.put(C0899R.C0901id.video_tv, 3);
        sparseIntArray.put(C0899R.C0901id.video_tip, 4);
        sparseIntArray.put(C0899R.C0901id.video_iv1, 5);
        sparseIntArray.put(C0899R.C0901id.space1, 6);
        sparseIntArray.put(C0899R.C0901id.video_iv2, 7);
        sparseIntArray.put(C0899R.C0901id.app_rl, 8);
        sparseIntArray.put(C0899R.C0901id.app_itemview, 9);
        sparseIntArray.put(C0899R.C0901id.app_tv, 10);
        sparseIntArray.put(C0899R.C0901id.app_tip, 11);
        sparseIntArray.put(C0899R.C0901id.app_iv1, 12);
        sparseIntArray.put(C0899R.C0901id.space2, 13);
        sparseIntArray.put(C0899R.C0901id.app_iv2, 14);
        sparseIntArray.put(C0899R.C0901id.phonelink_rl, 15);
        sparseIntArray.put(C0899R.C0901id.phonelink_itemview, 16);
        sparseIntArray.put(C0899R.C0901id.phonelink_tv, 17);
        sparseIntArray.put(C0899R.C0901id.phonelink_tip, 18);
        sparseIntArray.put(C0899R.C0901id.phone_iv1, 19);
        sparseIntArray.put(C0899R.C0901id.space3, 20);
        sparseIntArray.put(C0899R.C0901id.phone_iv2, 21);
        sparseIntArray.put(C0899R.C0901id.dashboard_rl, 22);
        sparseIntArray.put(C0899R.C0901id.dashboard_itemview, 23);
        sparseIntArray.put(C0899R.C0901id.dashboard_tv, 24);
        sparseIntArray.put(C0899R.C0901id.dashboard_tip, 25);
        sparseIntArray.put(C0899R.C0901id.dashboard_iv1, 26);
        sparseIntArray.put(C0899R.C0901id.space4, 27);
        sparseIntArray.put(C0899R.C0901id.dashboard_iv2, 28);
        sparseIntArray.put(C0899R.C0901id.dvr_rl, 29);
        sparseIntArray.put(C0899R.C0901id.dvr_itemview, 30);
        sparseIntArray.put(C0899R.C0901id.dvr_tv, 31);
        sparseIntArray.put(C0899R.C0901id.dvr_tip, 32);
        sparseIntArray.put(C0899R.C0901id.drv_iv1, 33);
        sparseIntArray.put(C0899R.C0901id.space5, 34);
        sparseIntArray.put(C0899R.C0901id.drv_iv2, 35);
    }

    public BenzNtg6FyFragmentTwoClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private BenzNtg6FyFragmentTwoClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (BenzMbuxItemView) bindings[9], (ImageView) bindings[12], (ImageView) bindings[14], (RelativeLayout) bindings[8], (TextView) bindings[11], (TextView) bindings[10], (BenzMbuxItemView) bindings[23], (ImageView) bindings[26], (ImageView) bindings[28], (RelativeLayout) bindings[22], (TextView) bindings[25], (TextView) bindings[24], (ImageView) bindings[33], (ImageView) bindings[35], (BenzMbuxItemView) bindings[30], (RelativeLayout) bindings[29], (TextView) bindings[32], (TextView) bindings[31], (LinearLayout) bindings[0], (ImageView) bindings[19], (ImageView) bindings[21], (BenzMbuxItemView) bindings[16], (RelativeLayout) bindings[15], (TextView) bindings[18], (TextView) bindings[17], (View) bindings[6], (View) bindings[13], (View) bindings[20], (View) bindings[27], (View) bindings[34], (BenzMbuxItemView) bindings[2], (ImageView) bindings[5], (ImageView) bindings[7], (RelativeLayout) bindings[1], (TextView) bindings[4], (TextView) bindings[3]);
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

    @Override // com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls
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

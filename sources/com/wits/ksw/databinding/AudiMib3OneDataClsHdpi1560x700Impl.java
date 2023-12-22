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
public class AudiMib3OneDataClsHdpi1560x700Impl extends AudiMib3OneDataCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.video_itemview, 1);
        sparseIntArray.put(C0899R.C0901id.video_iv, 2);
        sparseIntArray.put(C0899R.C0901id.video_tv, 3);
        sparseIntArray.put(C0899R.C0901id.music_itemview, 4);
        sparseIntArray.put(C0899R.C0901id.music_iv, 5);
        sparseIntArray.put(C0899R.C0901id.music_tv, 6);
        sparseIntArray.put(C0899R.C0901id.bt_itemview, 7);
        sparseIntArray.put(C0899R.C0901id.bt_iv, 8);
        sparseIntArray.put(C0899R.C0901id.bt_tv, 9);
        sparseIntArray.put(C0899R.C0901id.navi_itemview, 10);
        sparseIntArray.put(C0899R.C0901id.navi_iv, 11);
        sparseIntArray.put(C0899R.C0901id.navi_tv, 12);
        sparseIntArray.put(C0899R.C0901id.phonelink_itemview, 13);
        sparseIntArray.put(C0899R.C0901id.phonelink_iv, 14);
        sparseIntArray.put(C0899R.C0901id.phonelink_tv, 15);
        sparseIntArray.put(C0899R.C0901id.car_itemview, 16);
        sparseIntArray.put(C0899R.C0901id.car_iv, 17);
        sparseIntArray.put(C0899R.C0901id.car_tv, 18);
        sparseIntArray.put(C0899R.C0901id.app_itemview, 19);
        sparseIntArray.put(C0899R.C0901id.app_iv, 20);
        sparseIntArray.put(C0899R.C0901id.app_tv, 21);
        sparseIntArray.put(C0899R.C0901id.set_itemview, 22);
        sparseIntArray.put(C0899R.C0901id.set_iv, 23);
        sparseIntArray.put(C0899R.C0901id.set_tv, 24);
    }

    public AudiMib3OneDataClsHdpi1560x700Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }

    private AudiMib3OneDataClsHdpi1560x700Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (BenzMbuxItemView) bindings[19], (ImageView) bindings[20], (TextView) bindings[21], (BenzMbuxItemView) bindings[7], (ImageView) bindings[8], (TextView) bindings[9], (BenzMbuxItemView) bindings[16], (ImageView) bindings[17], (TextView) bindings[18], (LinearLayout) bindings[0], (BenzMbuxItemView) bindings[4], (ImageView) bindings[5], (TextView) bindings[6], (BenzMbuxItemView) bindings[10], (ImageView) bindings[11], (TextView) bindings[12], (BenzMbuxItemView) bindings[13], (ImageView) bindings[14], (TextView) bindings[15], (BenzMbuxItemView) bindings[22], (ImageView) bindings[23], (TextView) bindings[24], (BenzMbuxItemView) bindings[1], (ImageView) bindings[2], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.fragmentOneLl.setTag(null);
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

    @Override // com.wits.ksw.databinding.AudiMib3OneDataCls
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

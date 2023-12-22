package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public class AudiMbi3SelThirdClsHdpi1920x720Impl extends AudiMbi3SelThirdCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 1);
        sparseIntArray.put(C0899R.C0901id.iv_title, 2);
        sparseIntArray.put(C0899R.C0901id.tv_title, 3);
        sparseIntArray.put(C0899R.C0901id.title_divider, 4);
        sparseIntArray.put(C0899R.C0901id.ll_selapp, 5);
        sparseIntArray.put(C0899R.C0901id.listview_music, 6);
        sparseIntArray.put(C0899R.C0901id.listview_video, 7);
        sparseIntArray.put(C0899R.C0901id.v_divider, 8);
        sparseIntArray.put(C0899R.C0901id.iv_show, 9);
    }

    public AudiMbi3SelThirdClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private AudiMbi3SelThirdClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[9], (ImageView) bindings[2], (RecyclerView) bindings[6], (RecyclerView) bindings[7], (LinearLayout) bindings[5], (RelativeLayout) bindings[1], (View) bindings[4], (TextView) bindings[3], (View) bindings[8]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
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
        if (26 == variableId) {
            setVm((AudiMib3SystemViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMbi3SelThirdCls
    public void setVm(AudiMib3SystemViewModel Vm) {
        this.mVm = Vm;
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

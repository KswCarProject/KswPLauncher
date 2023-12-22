package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.lexusls.drag.DeleteZone;
import com.wits.ksw.launcher.view.lexusls.drag.DragLayer;

/* loaded from: classes7.dex */
public class LexusLsDragMainLayoutBindingImpl extends LexusLsDragMainLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_drag_desktop, 1);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_drag_mb, 2);
        sparseIntArray.put(C0899R.C0901id.ll_lexus_ls_drag_bottom, 3);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_drag_left_btn, 4);
        sparseIntArray.put(C0899R.C0901id.demo_draglayer, 5);
        sparseIntArray.put(C0899R.C0901id.demo_del_zone, 6);
        sparseIntArray.put(C0899R.C0901id.deleteImg, 7);
        sparseIntArray.put(C0899R.C0901id.mInstallTxt, 8);
        sparseIntArray.put(C0899R.C0901id.recyclerview_drag, 9);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_drag_right_btn, 10);
    }

    public LexusLsDragMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private LexusLsDragMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[7], (DeleteZone) bindings[6], (DragLayer) bindings[5], (ImageView) bindings[1], (ImageView) bindings[4], (ImageView) bindings[2], (ImageView) bindings[10], (RelativeLayout) bindings[0], (RelativeLayout) bindings[3], (TextView) bindings[8], (RecyclerView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.lexusLsDragMainLl.setTag(null);
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
        if (9 == variableId) {
            setLexusLsViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.LexusLsDragMainLayoutBinding
    public void setLexusLsViewModel(LauncherViewModel LexusLsViewModel) {
        this.mLexusLsViewModel = LexusLsViewModel;
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

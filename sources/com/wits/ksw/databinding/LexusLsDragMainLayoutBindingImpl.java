package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LexusLsDragMainLayoutBindingImpl extends LexusLsDragMainLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_lexus_ls_drag_desktop, 1);
        sparseIntArray.put(R.id.iv_lexus_ls_drag_mb, 2);
        sparseIntArray.put(R.id.ll_lexus_ls_drag_bottom, 3);
        sparseIntArray.put(R.id.iv_lexus_ls_drag_left_btn, 4);
        sparseIntArray.put(R.id.demo_draglayer, 5);
        sparseIntArray.put(R.id.demo_del_zone, 6);
        sparseIntArray.put(R.id.deleteImg, 7);
        sparseIntArray.put(R.id.mInstallTxt, 8);
        sparseIntArray.put(R.id.recyclerview_drag, 9);
        sparseIntArray.put(R.id.iv_lexus_ls_drag_right_btn, 10);
    }

    public LexusLsDragMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LexusLsDragMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[7], bindings[6], bindings[5], bindings[1], bindings[4], bindings[2], bindings[10], bindings[0], bindings[3], bindings[8], bindings[9]);
        this.mDirtyFlags = -1;
        this.lexusLsDragMainLl.setTag((Object) null);
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
        if (5 != variableId) {
            return false;
        }
        setLexusLsViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLexusLsViewModel(LauncherViewModel LexusLsViewModel) {
        this.mLexusLsViewModel = LexusLsViewModel;
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

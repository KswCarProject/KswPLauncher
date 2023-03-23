package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LexusLsMainLayoutBindingImpl extends LexusLsMainLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.lexus_ls_recycleview_desktop, 1);
        sparseIntArray.put(R.id.iv_lexus_ls_bg_app_layerlist, 2);
        sparseIntArray.put(R.id.iv_lexusls_left_btn, 3);
        sparseIntArray.put(R.id.lexus_ls_viewpager_bottom, 4);
        sparseIntArray.put(R.id.iv_lexusls_right_btn, 5);
    }

    public LexusLsMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private LexusLsMainLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[2], bindings[3], bindings[5], bindings[0], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.lexusLsMainLl.setTag((Object) null);
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
        if (9 != variableId) {
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

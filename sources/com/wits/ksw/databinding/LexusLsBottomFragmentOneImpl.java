package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LexusLsBottomFragmentOneImpl extends LexusLsBottomFragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_lexus_ls_navi, 1);
        sparseIntArray.put(R.id.iv_lexus_ls_music, 2);
        sparseIntArray.put(R.id.iv_lexus_ls_bt, 3);
        sparseIntArray.put(R.id.iv_lexus_ls_app, 4);
        sparseIntArray.put(R.id.iv_lexus_ls_video, 5);
        sparseIntArray.put(R.id.iv_lexus_ls_car, 6);
        sparseIntArray.put(R.id.iv_lexus_ls_set, 7);
        sparseIntArray.put(R.id.iv_lexus_ls_air, 8);
    }

    public LexusLsBottomFragmentOneImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LexusLsBottomFragmentOneImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[8], bindings[4], bindings[3], bindings[6], bindings[2], bindings[1], bindings[7], bindings[5], bindings[0]);
        this.mDirtyFlags = -1;
        this.lexusLsLlBottomFragOne.setTag((Object) null);
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
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
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

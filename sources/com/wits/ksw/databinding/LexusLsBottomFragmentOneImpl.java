package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class LexusLsBottomFragmentOneImpl extends LexusLsBottomFragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_navi, 1);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_music, 2);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_bt, 3);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_app, 4);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_video, 5);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_car, 6);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_set, 7);
        sparseIntArray.put(C0899R.C0901id.iv_lexus_ls_air, 8);
    }

    public LexusLsBottomFragmentOneImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private LexusLsBottomFragmentOneImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[8], (ImageView) bindings[4], (ImageView) bindings[3], (ImageView) bindings[6], (ImageView) bindings[2], (ImageView) bindings[1], (ImageView) bindings[7], (ImageView) bindings[5], (LinearLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.lexusLsLlBottomFragOne.setTag(null);
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
            setViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.LexusLsBottomFragmentOne
    public void setViewModel(LauncherViewModel ViewModel) {
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

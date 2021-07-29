package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import com.wits.ksw.R;

public class ActivityAudiBindingImpl extends ActivityAudiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.audiHomeParentPanel, 1);
        sparseIntArray.put(R.id.au_set_sys_item, 2);
        sparseIntArray.put(R.id.au_set_navi_item, 3);
        sparseIntArray.put(R.id.au_set_sound_item, 4);
        sparseIntArray.put(R.id.au_set_eq_item, 5);
        sparseIntArray.put(R.id.au_set_lag_item, 6);
        sparseIntArray.put(R.id.au_set_time_item, 7);
        sparseIntArray.put(R.id.au_set_sysinfo_item, 8);
        sparseIntArray.put(R.id.au_set_more_item, 9);
        sparseIntArray.put(R.id.au_set_fac_item, 10);
    }

    public ActivityAudiBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityAudiBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[5], bindings[10], bindings[6], bindings[9], bindings[3], bindings[4], bindings[2], bindings[8], bindings[7], bindings[1]);
        this.mDirtyFlags = -1;
        ScrollView scrollView = bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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
        return true;
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

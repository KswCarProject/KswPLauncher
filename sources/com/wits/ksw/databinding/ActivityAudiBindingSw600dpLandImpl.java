package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public class ActivityAudiBindingSw600dpLandImpl extends ActivityAudiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.audiHomeParentPanel, 1);
        sparseIntArray.put(C0899R.C0901id.au_set_sys_item, 2);
        sparseIntArray.put(C0899R.C0901id.au_set_navi_item, 3);
        sparseIntArray.put(C0899R.C0901id.au_set_sound_item, 4);
        sparseIntArray.put(C0899R.C0901id.au_set_eq_item, 5);
        sparseIntArray.put(C0899R.C0901id.au_set_lag_item, 6);
        sparseIntArray.put(C0899R.C0901id.au_set_time_item, 7);
        sparseIntArray.put(C0899R.C0901id.au_set_sysinfo_item, 8);
        sparseIntArray.put(C0899R.C0901id.au_set_more_item, 9);
        sparseIntArray.put(C0899R.C0901id.au_set_fac_item, 10);
    }

    public ActivityAudiBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ActivityAudiBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[5], (TextView) bindings[10], (TextView) bindings[6], (TextView) bindings[9], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[2], (TextView) bindings[8], (TextView) bindings[7], (AudiConstraintLayout) bindings[1]);
        this.mDirtyFlags = -1L;
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
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
        return true;
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

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.RomeoViewModel;

/* loaded from: classes7.dex */
public class ActivityRomeoBindingImpl extends ActivityRomeoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.romeo_navi, 1);
        sparseIntArray.put(C0899R.C0901id.romeo_music, 2);
        sparseIntArray.put(C0899R.C0901id.romeo_video, 3);
        sparseIntArray.put(C0899R.C0901id.romeo_phone, 4);
        sparseIntArray.put(C0899R.C0901id.romeo_app, 5);
        sparseIntArray.put(C0899R.C0901id.romeo_setting, 6);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_1, 7);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_2, 8);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_3, 9);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_4, 10);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_5, 11);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_6, 12);
        sparseIntArray.put(C0899R.C0901id.page_indicator1, 13);
        sparseIntArray.put(C0899R.C0901id.page_indicator2, 14);
        sparseIntArray.put(C0899R.C0901id.romeo_main_rv, 15);
    }

    public ActivityRomeoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityRomeoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[13], (ImageView) bindings[14], (ImageView) bindings[5], (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[9], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[12], (RecyclerView) bindings[15], (ImageView) bindings[2], (ImageView) bindings[1], (ImageView) bindings[4], (ImageView) bindings[6], (ImageView) bindings[3]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
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
            setViewModel((RomeoViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityRomeoBinding
    public void setViewModel(RomeoViewModel ViewModel) {
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

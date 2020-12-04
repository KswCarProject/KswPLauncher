package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.RomeoViewModel;

public class ActivityRomeoBindingImpl extends ActivityRomeoBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.romeo_navi, 1);
        sViewsWithIds.put(R.id.romeo_music, 2);
        sViewsWithIds.put(R.id.romeo_video, 3);
        sViewsWithIds.put(R.id.romeo_phone, 4);
        sViewsWithIds.put(R.id.romeo_app, 5);
        sViewsWithIds.put(R.id.romeo_setting, 6);
        sViewsWithIds.put(R.id.romeo_indicator_1, 7);
        sViewsWithIds.put(R.id.romeo_indicator_2, 8);
        sViewsWithIds.put(R.id.romeo_indicator_3, 9);
        sViewsWithIds.put(R.id.romeo_indicator_4, 10);
        sViewsWithIds.put(R.id.romeo_indicator_5, 11);
        sViewsWithIds.put(R.id.romeo_indicator_6, 12);
        sViewsWithIds.put(R.id.page_indicator1, 13);
        sViewsWithIds.put(R.id.page_indicator2, 14);
        sViewsWithIds.put(R.id.romeo_main_rv, 15);
    }

    public ActivityRomeoBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityRomeoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[13], bindings[14], bindings[5], bindings[7], bindings[8], bindings[9], bindings[10], bindings[11], bindings[12], bindings[15], bindings[2], bindings[1], bindings[4], bindings[6], bindings[3]);
        this.mDirtyFlags = -1;
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (13 != variableId) {
            return false;
        }
        setViewModel((RomeoViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable RomeoViewModel ViewModel) {
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

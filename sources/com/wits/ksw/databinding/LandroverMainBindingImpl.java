package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LandroverMainBindingImpl extends LandroverMainBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.viewPager, 1);
        sViewsWithIds.put(R.id.icon_left, 2);
        sViewsWithIds.put(R.id.icon_right, 3);
        sViewsWithIds.put(R.id.indicato1, 4);
        sViewsWithIds.put(R.id.indicato2, 5);
    }

    public LandroverMainBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private LandroverMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[2], bindings[3], bindings[4], bindings[5], bindings[1]);
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
        if (18 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(@Nullable LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
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

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID6FragmentFourSw600dpLandImpl extends ID6FragmentFour {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.id6_phone_iamge_view, 1);
        sViewsWithIds.put(R.id.id6_phone_mess, 2);
        sViewsWithIds.put(R.id.id6_apps_image_view, 3);
        sViewsWithIds.put(R.id.id6_apps_mess, 4);
        sViewsWithIds.put(R.id.id6_setting_image_view, 5);
        sViewsWithIds.put(R.id.id6_setting_mess, 6);
    }

    public ID6FragmentFourSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ID6FragmentFourSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[3], bindings[4], bindings[1], bindings[2], bindings[5], bindings[6]);
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
        if (11 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable LauncherViewModel ViewModel) {
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

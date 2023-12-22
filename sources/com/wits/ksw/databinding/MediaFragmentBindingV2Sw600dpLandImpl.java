package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class MediaFragmentBindingV2Sw600dpLandImpl extends MediaFragmentBindingV2 {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final Id7SubMusicViewBinding mboundView0;
    private final ConstraintLayout mboundView01;
    private final Id7SubPhoneViewBinding mboundView02;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"id7_sub_music_view", "id7_sub_phone_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.id7_sub_music_view, C0899R.C0902layout.id7_sub_phone_view});
        sViewsWithIds = null;
    }

    public MediaFragmentBindingV2Sw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private MediaFragmentBindingV2Sw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        Id7SubMusicViewBinding id7SubMusicViewBinding = (Id7SubMusicViewBinding) bindings[1];
        this.mboundView0 = id7SubMusicViewBinding;
        setContainedBinding(id7SubMusicViewBinding);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView01 = constraintLayout;
        constraintLayout.setTag(null);
        Id7SubPhoneViewBinding id7SubPhoneViewBinding = (Id7SubPhoneViewBinding) bindings[2];
        this.mboundView02 = id7SubPhoneViewBinding;
        setContainedBinding(id7SubPhoneViewBinding);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView0.invalidateAll();
        this.mboundView02.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.mboundView02.hasPendingBindings();
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (10 == variableId) {
            setMediaViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.MediaFragmentBindingV2
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.mboundView02.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if ((3 & dirtyFlags) != 0) {
            this.mboundView0.setMediaViewModel(mediaViewModel);
            this.mboundView02.setNaviViewModel(mediaViewModel);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.mboundView02);
    }
}

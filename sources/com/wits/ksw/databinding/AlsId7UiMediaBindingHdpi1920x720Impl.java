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
public class AlsId7UiMediaBindingHdpi1920x720Impl extends AlsId7UiMediaBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final AlsId7UiVideoSubViewBinding mboundView01;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(3);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"als_id7_ui_sub_music_view", "als_id7_ui_sub_video_view"}, new int[]{1, 2}, new int[]{C0899R.C0902layout.als_id7_ui_sub_music_view, C0899R.C0902layout.als_id7_ui_sub_video_view});
        sViewsWithIds = null;
    }

    public AlsId7UiMediaBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private AlsId7UiMediaBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (AlsId7UiMusicSubViewBinding) bindings[1]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        AlsId7UiVideoSubViewBinding alsId7UiVideoSubViewBinding = (AlsId7UiVideoSubViewBinding) bindings[2];
        this.mboundView01 = alsId7UiVideoSubViewBinding;
        setContainedBinding(alsId7UiVideoSubViewBinding);
        setContainedBinding(this.musicInclude);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.musicInclude.invalidateAll();
        this.mboundView01.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.musicInclude.hasPendingBindings() || this.mboundView01.hasPendingBindings();
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

    @Override // com.wits.ksw.databinding.AlsId7UiMediaBinding
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.musicInclude.setLifecycleOwner(lifecycleOwner);
        this.mboundView01.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMusicInclude((AlsId7UiMusicSubViewBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicInclude(AlsId7UiMusicSubViewBinding MusicInclude, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
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
        if ((6 & dirtyFlags) != 0) {
            this.mboundView01.setMediaViewModel(mediaViewModel);
            this.musicInclude.setMediaViewModel(mediaViewModel);
        }
        executeBindingsOn(this.musicInclude);
        executeBindingsOn(this.mboundView01);
    }
}

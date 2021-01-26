package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubVideoViewBindingSw600dpLandImpl extends Id7SubVideoViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback128;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 2);
        sViewsWithIds.put(R.id.textView3, 3);
    }

    public Id7SubVideoViewBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private Id7SubVideoViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[2], bindings[3], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.videoConstraintLayout.setTag((Object) null);
        this.videoImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback128 = new OnClickListener(this, 1);
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
        if (22 != variableId) {
            return false;
        }
        setMediaViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setMediaViewModel(@Nullable LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(22);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        View.OnFocusChangeListener mediaViewModelVideoViewFocusChangeListener = null;
        if (!((dirtyFlags & 3) == 0 || mediaViewModel == null)) {
            mediaViewModelVideoViewFocusChangeListener = mediaViewModel.videoViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.videoImageView.setOnClickListener(this.mCallback128);
        }
        if ((3 & dirtyFlags) != 0) {
            this.videoImageView.setOnFocusChangeListener(mediaViewModelVideoViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if (mediaViewModel != null) {
            mediaViewModel.openVideo(callbackArg_0);
        }
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class Id7V2SubVideoViewBindingImpl extends Id7V2SubVideoViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback465;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.textView2, 2);
        sparseIntArray.put(C0899R.C0901id.textView3, 3);
    }

    public Id7V2SubVideoViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private Id7V2SubVideoViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[2], (TextView) bindings[3], (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.videoConstraintLayout.setTag(null);
        this.videoImageView.setTag(null);
        setRootTag(root);
        this.mCallback465 = new OnClickListener(this, 1);
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
        if (10 == variableId) {
            setMediaViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Id7V2SubVideoViewBinding
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
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
        View.OnFocusChangeListener mediaViewModelVideoViewFocusChangeListenerv2 = null;
        if ((dirtyFlags & 3) != 0 && mediaViewModel != null) {
            mediaViewModelVideoViewFocusChangeListenerv2 = mediaViewModel.videoViewFocusChangeListenerv2;
        }
        if ((2 & dirtyFlags) != 0) {
            this.videoImageView.setOnClickListener(this.mCallback465);
        }
        if ((3 & dirtyFlags) != 0) {
            this.videoImageView.setOnFocusChangeListener(mediaViewModelVideoViewFocusChangeListenerv2);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        boolean mediaViewModelJavaLangObjectNull = mediaViewModel != null;
        if (mediaViewModelJavaLangObjectNull) {
            mediaViewModel.openVideoMulti(callbackArg_0);
        }
    }
}
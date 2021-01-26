package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubVideoViewBindingImpl extends AlsId7SubVideoViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback118;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 2);
        sViewsWithIds.put(R.id.textView3, 3);
    }

    public AlsId7SubVideoViewBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private AlsId7SubVideoViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[2], bindings[3], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.videoConstraintLayout.setTag((Object) null);
        this.videoImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback118 = new OnClickListener(this, 1);
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
        if (15 != variableId) {
            return false;
        }
        setDashVideoViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setDashVideoViewModel(@Nullable AlsID7ViewModel DashVideoViewModel) {
        this.mDashVideoViewModel = DashVideoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(15);
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
        AlsID7ViewModel alsID7ViewModel = this.mDashVideoViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.videoImageView.setOnClickListener(this.mCallback118);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        if (dashVideoViewModel != null) {
            dashVideoViewModel.openVideo(callbackArg_0);
        }
    }
}

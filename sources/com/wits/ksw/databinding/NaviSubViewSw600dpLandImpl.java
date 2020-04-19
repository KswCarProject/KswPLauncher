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

public class NaviSubViewSw600dpLandImpl extends NaviSubView implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback34;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 2);
        sViewsWithIds.put(R.id.textView3, 3);
    }

    public NaviSubViewSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private NaviSubViewSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0], bindings[1], bindings[2], bindings[3]);
        this.mDirtyFlags = -1;
        this.naviConstraintLayout.setTag((Object) null);
        this.naviImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback34 = new OnClickListener(this, 1);
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
        if (4 != variableId) {
            return false;
        }
        setNaviViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setNaviViewModel(@Nullable LauncherViewModel NaviViewModel) {
        this.mNaviViewModel = NaviViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(4);
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
        LauncherViewModel launcherViewModel = this.mNaviViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.naviImageView.setOnClickListener(this.mCallback34);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        if (naviViewModel != null) {
            naviViewModel.openNaviApp(callbackArg_0);
        }
    }
}

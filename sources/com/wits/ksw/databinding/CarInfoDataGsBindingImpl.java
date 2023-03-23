package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class CarInfoDataGsBindingImpl extends CarInfoDataGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback304;
    private long mDirtyFlags;

    public CarInfoDataGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private CarInfoDataGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[0]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.llContainerGs.setTag((Object) null);
        setRootTag(root);
        this.mCallback304 = new OnClickListener(this, 1);
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

    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setCarViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setCarViewModel(LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
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
        View.OnFocusChangeListener carViewModelCarViewFocusChangeListener = null;
        LauncherViewModel carViewModel = this.mCarViewModel;
        if (!((dirtyFlags & 3) == 0 || carViewModel == null)) {
            carViewModelCarViewFocusChangeListener = carViewModel.carViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.ivMask.setOnClickListener(this.mCallback304);
        }
        if ((3 & dirtyFlags) != 0) {
            this.ivMask.setOnFocusChangeListener(carViewModelCarViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel carViewModel = this.mCarViewModel;
        if (carViewModel != null) {
            carViewModel.openCar(callbackArg_0);
        }
    }
}

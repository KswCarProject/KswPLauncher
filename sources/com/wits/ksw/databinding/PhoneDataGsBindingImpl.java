package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class PhoneDataGsBindingImpl extends PhoneDataGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback42;
    private long mDirtyFlags;

    public PhoneDataGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private PhoneDataGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[1], bindings[0]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.llContainerGs.setTag((Object) null);
        setRootTag(root);
        this.mCallback42 = new OnClickListener(this, 1);
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
        if (2 != variableId) {
            return false;
        }
        setBtViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setBtViewModel(LauncherViewModel BtViewModel) {
        this.mBtViewModel = BtViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
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
        LauncherViewModel btViewModel = this.mBtViewModel;
        View.OnFocusChangeListener btViewModelPhoneViewFocusChangeListener = null;
        if (!((dirtyFlags & 3) == 0 || btViewModel == null)) {
            btViewModelPhoneViewFocusChangeListener = btViewModel.phoneViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.ivMask.setOnClickListener(this.mCallback42);
        }
        if ((3 & dirtyFlags) != 0) {
            this.ivMask.setOnFocusChangeListener(btViewModelPhoneViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel btViewModel = this.mBtViewModel;
        if (btViewModel != null) {
            btViewModel.openBtApp(callbackArg_0);
        }
    }
}

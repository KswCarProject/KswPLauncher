package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class AlsId7UiCarInfoSubViewBindingImpl extends AlsId7UiCarInfoSubViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback241;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.carInfoImageView, 2);
        sparseIntArray.put(R.id.textView2, 3);
        sparseIntArray.put(R.id.textView3, 4);
    }

    public AlsId7UiCarInfoSubViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private AlsId7UiCarInfoSubViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0], bindings[1], bindings[2], bindings[3], bindings[4]);
        this.mDirtyFlags = -1;
        this.carConstraintLayout.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback241 = new OnClickListener(this, 1);
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
        setCarViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setCarViewModel(LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
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
        View.OnFocusChangeListener carViewModelCarViewFocusChangeListener = null;
        LauncherViewModel carViewModel = this.mCarViewModel;
        if (!((dirtyFlags & 3) == 0 || carViewModel == null)) {
            carViewModelCarViewFocusChangeListener = carViewModel.carViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.carImageView.setOnClickListener(this.mCallback241);
        }
        if ((3 & dirtyFlags) != 0) {
            this.carImageView.setOnFocusChangeListener(carViewModelCarViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel carViewModel = this.mCarViewModel;
        if (carViewModel != null) {
            carViewModel.openCar(callbackArg_0);
        }
    }
}

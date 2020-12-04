package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubPhoneViewHicarBindingImpl extends Id7SubPhoneViewHicarBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback49;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 3);
    }

    public Id7SubPhoneViewHicarBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private Id7SubPhoneViewHicarBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[0], bindings[1], bindings[2], bindings[3]);
        this.mDirtyFlags = -1;
        this.carConstraintLayout.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.phoneConnectionTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback49 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (5 != variableId) {
            return false;
        }
        setNaviViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setNaviViewModel(@Nullable LauncherViewModel NaviViewModel) {
        this.mNaviViewModel = NaviViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(5);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeNaviViewModelBtState((ObservableField) object, fieldId);
    }

    private boolean onChangeNaviViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<String> naviViewModelBtState = null;
        String naviViewModelBtStateGet = null;
        View.OnFocusChangeListener naviViewModelCarViewFocusChangeListener = null;
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        if ((dirtyFlags & 7) != 0) {
            if (naviViewModel != null) {
                naviViewModelBtState = naviViewModel.btState;
            }
            updateRegistration(0, (Observable) naviViewModelBtState);
            if (naviViewModelBtState != null) {
                naviViewModelBtStateGet = naviViewModelBtState.get();
            }
            if (!((dirtyFlags & 6) == 0 || naviViewModel == null)) {
                naviViewModelCarViewFocusChangeListener = naviViewModel.carViewFocusChangeListener;
            }
        }
        if ((4 & dirtyFlags) != 0) {
            this.carImageView.setOnClickListener(this.mCallback49);
        }
        if ((dirtyFlags & 6) != 0) {
            this.carImageView.setOnFocusChangeListener(naviViewModelCarViewFocusChangeListener);
        }
        if ((7 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.phoneConnectionTextView, naviViewModelBtStateGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        if (naviViewModel != null) {
            naviViewModel.openBtApp(callbackArg_0);
        }
    }
}

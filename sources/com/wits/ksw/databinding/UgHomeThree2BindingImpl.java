package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class UgHomeThree2BindingImpl extends UgHomeThree2Binding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback11;
    @Nullable
    private final View.OnClickListener mCallback12;
    @Nullable
    private final View.OnClickListener mCallback13;
    private long mDirtyFlags;

    public UgHomeThree2BindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private UgHomeThree2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0], bindings[1], bindings[2], bindings[3]);
        this.mDirtyFlags = -1;
        this.carConstraintLayout.setTag((Object) null);
        this.ugHomeCarVaiw.setTag((Object) null);
        this.ugHomeDashboradVaiw.setTag((Object) null);
        this.ugHomeSettingVaiw.setTag((Object) null);
        setRootTag(root);
        this.mCallback13 = new OnClickListener(this, 3);
        this.mCallback11 = new OnClickListener(this, 1);
        this.mCallback12 = new OnClickListener(this, 2);
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
        if (19 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(19);
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
        LauncherViewModel launcherViewModel = this.mViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.ugHomeCarVaiw.setOnClickListener(this.mCallback11);
            this.ugHomeDashboradVaiw.setOnClickListener(this.mCallback12);
            this.ugHomeSettingVaiw.setOnClickListener(this.mCallback13);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openCar(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

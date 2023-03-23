package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainGsugBindingHdpi1920x720Impl extends ActivityMainGsugBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback144;
    private final View.OnClickListener mCallback145;
    private final View.OnClickListener mCallback146;
    private final View.OnClickListener mCallback147;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ugViewPage, 5);
    }

    public ActivityMainGsugBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainGsugBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[4], bindings[1], bindings[2], bindings[3], bindings[5]);
        this.mDirtyFlags = -1;
        this.appsButton.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.musicButton.setTag((Object) null);
        this.naviButton.setTag((Object) null);
        this.settingButton.setTag((Object) null);
        setRootTag(root);
        this.mCallback146 = new OnClickListener(this, 3);
        this.mCallback147 = new OnClickListener(this, 4);
        this.mCallback144 = new OnClickListener(this, 1);
        this.mCallback145 = new OnClickListener(this, 2);
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(25);
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
            this.appsButton.setOnClickListener(this.mCallback147);
            this.musicButton.setOnClickListener(this.mCallback144);
            this.naviButton.setOnClickListener(this.mCallback145);
            this.settingButton.setOnClickListener(this.mCallback146);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openChoseMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openApps(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

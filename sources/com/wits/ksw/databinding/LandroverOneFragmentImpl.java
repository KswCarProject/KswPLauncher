package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class LandroverOneFragmentImpl extends LandroverOneFragment implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback100;
    @Nullable
    private final View.OnClickListener mCallback101;
    @Nullable
    private final View.OnClickListener mCallback96;
    @Nullable
    private final View.OnClickListener mCallback97;
    @Nullable
    private final View.OnClickListener mCallback98;
    @Nullable
    private final View.OnClickListener mCallback99;
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;

    public LandroverOneFragmentImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LandroverOneFragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[4], bindings[5], bindings[1], bindings[3], bindings[6], bindings[2]);
        this.mDirtyFlags = -1;
        this.landroverMainIconCar.setTag((Object) null);
        this.landroverMainIconDvr.setTag((Object) null);
        this.landroverMainIconGps.setTag((Object) null);
        this.landroverMainIconMusic.setTag((Object) null);
        this.landroverMainIconSettings.setTag((Object) null);
        this.landroverMainIconVideo.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        setRootTag(root);
        this.mCallback100 = new OnClickListener(this, 5);
        this.mCallback101 = new OnClickListener(this, 6);
        this.mCallback99 = new OnClickListener(this, 4);
        this.mCallback96 = new OnClickListener(this, 1);
        this.mCallback98 = new OnClickListener(this, 3);
        this.mCallback97 = new OnClickListener(this, 2);
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
        if (9 != variableId) {
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
        notifyPropertyChanged(9);
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
            this.landroverMainIconCar.setOnClickListener(this.mCallback99);
            this.landroverMainIconDvr.setOnClickListener(this.mCallback100);
            this.landroverMainIconGps.setOnClickListener(this.mCallback96);
            this.landroverMainIconMusic.setOnClickListener(this.mCallback98);
            this.landroverMainIconSettings.setOnClickListener(this.mCallback101);
            this.landroverMainIconVideo.setOnClickListener(this.mCallback97);
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
                    viewModel.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openVideo(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                if (viewModel5 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                if (viewModel6 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openSettings(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

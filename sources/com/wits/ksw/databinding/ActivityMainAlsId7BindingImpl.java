package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainAlsId7BindingImpl extends ActivityMainAlsId7Binding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback162;
    @Nullable
    private final View.OnClickListener mCallback163;
    @Nullable
    private final View.OnClickListener mCallback164;
    @Nullable
    private final View.OnClickListener mCallback165;
    @Nullable
    private final View.OnClickListener mCallback166;
    @Nullable
    private final View.OnClickListener mCallback167;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    private final Button mboundView4;
    @NonNull
    private final Button mboundView5;
    @NonNull
    private final Button mboundView6;

    static {
        sViewsWithIds.put(R.id.guideline, 7);
        sViewsWithIds.put(R.id.imageView1, 8);
        sViewsWithIds.put(R.id.imageView4, 9);
        sViewsWithIds.put(R.id.imageView3, 10);
        sViewsWithIds.put(R.id.viewPage, 11);
        sViewsWithIds.put(R.id.menuConstraintLayout, 12);
    }

    public ActivityMainAlsId7BindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainAlsId7BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[7], bindings[8], bindings[10], bindings[9], bindings[1], bindings[2], bindings[3], bindings[12], bindings[11]);
        this.mDirtyFlags = -1;
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        this.menuButton1.setTag((Object) null);
        this.menuButton2.setTag((Object) null);
        this.menuButton3.setTag((Object) null);
        setRootTag(root);
        this.mCallback164 = new OnClickListener(this, 3);
        this.mCallback165 = new OnClickListener(this, 4);
        this.mCallback162 = new OnClickListener(this, 1);
        this.mCallback163 = new OnClickListener(this, 2);
        this.mCallback166 = new OnClickListener(this, 5);
        this.mCallback167 = new OnClickListener(this, 6);
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
        if (18 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(@Nullable LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(18);
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
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.mboundView4.setOnClickListener(this.mCallback165);
            this.mboundView5.setOnClickListener(this.mCallback166);
            this.mboundView6.setOnClickListener(this.mCallback167);
            this.menuButton1.setOnClickListener(this.mCallback162);
            this.menuButton2.setOnClickListener(this.mCallback163);
            this.menuButton3.setOnClickListener(this.mCallback164);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                LauncherViewModel launcherViewModel = this.mLauncherViewModel;
                if (launcherViewModel != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.openBrowser(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                if (launcherViewModel2 != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.openApps(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                if (launcherViewModel3 != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel launcherViewModel4 = this.mLauncherViewModel;
                if (launcherViewModel4 != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel launcherViewModel5 = this.mLauncherViewModel;
                if (launcherViewModel5 != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel5.openApps(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel launcherViewModel6 = this.mLauncherViewModel;
                if (launcherViewModel6 != null) {
                    launcherViewModelJavaLangObjectNull = true;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel6.openSettings(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

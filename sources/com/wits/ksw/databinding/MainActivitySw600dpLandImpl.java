package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class MainActivitySw600dpLandImpl extends MainActivity implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback323;
    private final View.OnClickListener mCallback324;
    private final View.OnClickListener mCallback325;
    private final View.OnClickListener mCallback326;
    private final View.OnClickListener mCallback327;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline, 6);
        sparseIntArray.put(R.id.imageView1, 7);
        sparseIntArray.put(R.id.imageView4, 8);
        sparseIntArray.put(R.id.imageView3, 9);
        sparseIntArray.put(R.id.viewPage, 10);
        sparseIntArray.put(R.id.menuConstraintLayout, 11);
    }

    public MainActivitySw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private MainActivitySw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[6], bindings[7], bindings[9], bindings[8], bindings[3], bindings[2], bindings[1], bindings[4], bindings[5], bindings[11], bindings[10]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.menuButton1.setTag((Object) null);
        this.menuButton2.setTag((Object) null);
        this.menuButton3.setTag((Object) null);
        this.menuButton4.setTag((Object) null);
        this.menuButton5.setTag((Object) null);
        setRootTag(root);
        this.mCallback325 = new OnClickListener(this, 3);
        this.mCallback324 = new OnClickListener(this, 2);
        this.mCallback327 = new OnClickListener(this, 5);
        this.mCallback326 = new OnClickListener(this, 4);
        this.mCallback323 = new OnClickListener(this, 1);
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
        if (7 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
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
            this.menuButton1.setOnClickListener(this.mCallback325);
            this.menuButton2.setOnClickListener(this.mCallback324);
            this.menuButton3.setOnClickListener(this.mCallback323);
            this.menuButton4.setOnClickListener(this.mCallback326);
            this.menuButton5.setOnClickListener(this.mCallback327);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel launcherViewModel = this.mLauncherViewModel;
                if (launcherViewModel == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                if (launcherViewModel2 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                if (launcherViewModel3 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openChoseMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel launcherViewModel4 = this.mLauncherViewModel;
                if (launcherViewModel4 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel launcherViewModel5 = this.mLauncherViewModel;
                if (launcherViewModel5 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel5.openApps(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

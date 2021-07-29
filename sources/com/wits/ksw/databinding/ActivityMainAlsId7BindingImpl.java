package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainAlsId7BindingImpl extends ActivityMainAlsId7Binding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback65;
    private final View.OnClickListener mCallback66;
    private final View.OnClickListener mCallback67;
    private final View.OnClickListener mCallback68;
    private final View.OnClickListener mCallback69;
    private final View.OnClickListener mCallback70;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final Button mboundView4;
    private final Button mboundView5;
    private final Button mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline, 7);
        sparseIntArray.put(R.id.imageView1, 8);
        sparseIntArray.put(R.id.imageView4, 9);
        sparseIntArray.put(R.id.imageView3, 10);
        sparseIntArray.put(R.id.viewPage, 11);
        sparseIntArray.put(R.id.menuConstraintLayout, 12);
    }

    public ActivityMainAlsId7BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainAlsId7BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[7], bindings[8], bindings[10], bindings[9], bindings[1], bindings[2], bindings[3], bindings[12], bindings[11]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        Button button = bindings[4];
        this.mboundView4 = button;
        button.setTag((Object) null);
        Button button2 = bindings[5];
        this.mboundView5 = button2;
        button2.setTag((Object) null);
        Button button3 = bindings[6];
        this.mboundView6 = button3;
        button3.setTag((Object) null);
        this.menuButton1.setTag((Object) null);
        this.menuButton2.setTag((Object) null);
        this.menuButton3.setTag((Object) null);
        setRootTag(root);
        this.mCallback69 = new OnClickListener(this, 5);
        this.mCallback67 = new OnClickListener(this, 3);
        this.mCallback68 = new OnClickListener(this, 4);
        this.mCallback65 = new OnClickListener(this, 1);
        this.mCallback66 = new OnClickListener(this, 2);
        this.mCallback70 = new OnClickListener(this, 6);
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
        if (4 != variableId) {
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
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.mboundView4.setOnClickListener(this.mCallback68);
            this.mboundView5.setOnClickListener(this.mCallback69);
            this.mboundView6.setOnClickListener(this.mCallback70);
            this.menuButton1.setOnClickListener(this.mCallback65);
            this.menuButton2.setOnClickListener(this.mCallback66);
            this.menuButton3.setOnClickListener(this.mCallback67);
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
                    launcherViewModel.openBrowser(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                if (launcherViewModel2 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.openApps(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                if (launcherViewModel3 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openSettings(callbackArg_0);
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
            case 6:
                LauncherViewModel launcherViewModel6 = this.mLauncherViewModel;
                if (launcherViewModel6 == null) {
                    launcherViewModelJavaLangObjectNull = false;
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

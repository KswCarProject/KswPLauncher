package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.p001v4.view.ViewPager;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class MainActivityImpl extends MainActivity implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback15;
    private final View.OnClickListener mCallback16;
    private final View.OnClickListener mCallback17;
    private final View.OnClickListener mCallback18;
    private final View.OnClickListener mCallback19;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.guideline, 6);
        sparseIntArray.put(C0899R.C0901id.imageView1, 7);
        sparseIntArray.put(C0899R.C0901id.imageView4, 8);
        sparseIntArray.put(C0899R.C0901id.imageView3, 9);
        sparseIntArray.put(C0899R.C0901id.viewPage, 10);
        sparseIntArray.put(C0899R.C0901id.menuConstraintLayout, 11);
    }

    public MainActivityImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private MainActivityImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Guideline) bindings[6], (ImageView) bindings[7], (ImageView) bindings[9], (ImageView) bindings[8], (Button) bindings[3], (Button) bindings[2], (Button) bindings[1], (Button) bindings[4], (Button) bindings[5], (ConstraintLayout) bindings[11], (ViewPager) bindings[10]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.menuButton1.setTag(null);
        this.menuButton2.setTag(null);
        this.menuButton3.setTag(null);
        this.menuButton4.setTag(null);
        this.menuButton5.setTag(null);
        setRootTag(root);
        this.mCallback16 = new OnClickListener(this, 2);
        this.mCallback17 = new OnClickListener(this, 3);
        this.mCallback15 = new OnClickListener(this, 1);
        this.mCallback18 = new OnClickListener(this, 4);
        this.mCallback19 = new OnClickListener(this, 5);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (7 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.MainActivity
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.menuButton1.setOnClickListener(this.mCallback17);
            this.menuButton2.setOnClickListener(this.mCallback16);
            this.menuButton3.setOnClickListener(this.mCallback15);
            this.menuButton4.setOnClickListener(this.mCallback18);
            this.menuButton5.setOnClickListener(this.mCallback19);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel launcherViewModel = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel2 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel3 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openChoseMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel launcherViewModel4 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel4 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel launcherViewModel5 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel5 != null;
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

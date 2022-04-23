package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public class LandroverMainBottomLayBindingImpl extends LandroverMainBottomLayBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback242;
    private final View.OnClickListener mCallback243;
    private final View.OnClickListener mCallback244;
    private final View.OnClickListener mCallback245;
    private final View.OnClickListener mCallback246;
    private final View.OnClickListener mCallback247;
    private final View.OnClickListener mCallback248;
    private final View.OnClickListener mCallback249;
    private final View.OnClickListener mCallback250;
    private final View.OnClickListener mCallback251;
    private final View.OnClickListener mCallback252;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverMainBottomLayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LandroverMainBottomLayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[8], bindings[6], bindings[4], bindings[5], bindings[2], bindings[11], bindings[10], bindings[9], bindings[1], bindings[3], bindings[7]);
        this.mDirtyFlags = -1;
        this.landroverMainBottomAirBtn.setTag((Object) null);
        this.landroverMainBottomBtBtn.setTag((Object) null);
        this.landroverMainBottomDvrBtn.setTag((Object) null);
        this.landroverMainBottomGpsBtn.setTag((Object) null);
        this.landroverMainBottomMenuBtn.setTag((Object) null);
        this.landroverMainBottomOffBtn.setTag((Object) null);
        this.landroverMainBottomParkassistBtn.setTag((Object) null);
        this.landroverMainBottomRadarBtn.setTag((Object) null);
        this.landroverMainBottomReturnBtn.setTag((Object) null);
        this.landroverMainBottomSetupBtn.setTag((Object) null);
        this.landroverMainBottomVideoBtn.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback249 = new OnClickListener(this, 8);
        this.mCallback247 = new OnClickListener(this, 6);
        this.mCallback245 = new OnClickListener(this, 4);
        this.mCallback243 = new OnClickListener(this, 2);
        this.mCallback251 = new OnClickListener(this, 10);
        this.mCallback248 = new OnClickListener(this, 7);
        this.mCallback246 = new OnClickListener(this, 5);
        this.mCallback244 = new OnClickListener(this, 3);
        this.mCallback252 = new OnClickListener(this, 11);
        this.mCallback242 = new OnClickListener(this, 1);
        this.mCallback250 = new OnClickListener(this, 9);
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
        setLauncherViewModel((LandroverViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LandroverViewModel LauncherViewModel) {
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
        LandroverViewModel landroverViewModel = this.mLauncherViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.landroverMainBottomAirBtn.setOnClickListener(this.mCallback249);
            this.landroverMainBottomBtBtn.setOnClickListener(this.mCallback247);
            this.landroverMainBottomDvrBtn.setOnClickListener(this.mCallback245);
            this.landroverMainBottomGpsBtn.setOnClickListener(this.mCallback246);
            this.landroverMainBottomMenuBtn.setOnClickListener(this.mCallback243);
            this.landroverMainBottomOffBtn.setOnClickListener(this.mCallback252);
            this.landroverMainBottomParkassistBtn.setOnClickListener(this.mCallback251);
            this.landroverMainBottomRadarBtn.setOnClickListener(this.mCallback250);
            this.landroverMainBottomReturnBtn.setOnClickListener(this.mCallback242);
            this.landroverMainBottomSetupBtn.setOnClickListener(this.mCallback244);
            this.landroverMainBottomVideoBtn.setOnClickListener(this.mCallback248);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LandroverViewModel launcherViewModel = this.mLauncherViewModel;
                if (launcherViewModel == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.backKeyClick();
                    return;
                }
                return;
            case 2:
                LandroverViewModel launcherViewModel2 = this.mLauncherViewModel;
                if (launcherViewModel2 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.homeKeyClick();
                    return;
                }
                return;
            case 3:
                LandroverViewModel launcherViewModel3 = this.mLauncherViewModel;
                if (launcherViewModel3 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LandroverViewModel launcherViewModel4 = this.mLauncherViewModel;
                if (launcherViewModel4 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel4.openDvr();
                    return;
                }
                return;
            case 5:
                LandroverViewModel launcherViewModel5 = this.mLauncherViewModel;
                if (launcherViewModel5 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel5.openNaviApp();
                    return;
                }
                return;
            case 6:
                LandroverViewModel launcherViewModel6 = this.mLauncherViewModel;
                if (launcherViewModel6 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel6.openBtApp();
                    return;
                }
                return;
            case 7:
                LandroverViewModel launcherViewModel7 = this.mLauncherViewModel;
                if (launcherViewModel7 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel7.openVideo();
                    return;
                }
                return;
            case 8:
                LandroverViewModel launcherViewModel8 = this.mLauncherViewModel;
                if (launcherViewModel8 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel8.airClick();
                    return;
                }
                return;
            case 9:
                LandroverViewModel launcherViewModel9 = this.mLauncherViewModel;
                if (launcherViewModel9 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel9.radarClick();
                    return;
                }
                return;
            case 10:
                LandroverViewModel launcherViewModel10 = this.mLauncherViewModel;
                if (launcherViewModel10 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel10.parkClick();
                    return;
                }
                return;
            case 11:
                LandroverViewModel launcherViewModel11 = this.mLauncherViewModel;
                if (launcherViewModel11 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel11.screenOff();
                    return;
                }
                return;
            default:
                return;
        }
    }
}

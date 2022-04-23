package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public class LandroverMainBottomLayBinding1920x660Impl extends LandroverMainBottomLayBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback57;
    private final View.OnClickListener mCallback58;
    private final View.OnClickListener mCallback59;
    private final View.OnClickListener mCallback60;
    private final View.OnClickListener mCallback61;
    private final View.OnClickListener mCallback62;
    private final View.OnClickListener mCallback63;
    private final View.OnClickListener mCallback64;
    private final View.OnClickListener mCallback65;
    private final View.OnClickListener mCallback66;
    private final View.OnClickListener mCallback67;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverMainBottomLayBinding1920x660Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LandroverMainBottomLayBinding1920x660Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        this.mCallback59 = new OnClickListener(this, 3);
        this.mCallback67 = new OnClickListener(this, 11);
        this.mCallback57 = new OnClickListener(this, 1);
        this.mCallback65 = new OnClickListener(this, 9);
        this.mCallback64 = new OnClickListener(this, 8);
        this.mCallback62 = new OnClickListener(this, 6);
        this.mCallback60 = new OnClickListener(this, 4);
        this.mCallback58 = new OnClickListener(this, 2);
        this.mCallback66 = new OnClickListener(this, 10);
        this.mCallback63 = new OnClickListener(this, 7);
        this.mCallback61 = new OnClickListener(this, 5);
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
            this.landroverMainBottomAirBtn.setOnClickListener(this.mCallback64);
            this.landroverMainBottomBtBtn.setOnClickListener(this.mCallback62);
            this.landroverMainBottomDvrBtn.setOnClickListener(this.mCallback60);
            this.landroverMainBottomGpsBtn.setOnClickListener(this.mCallback61);
            this.landroverMainBottomMenuBtn.setOnClickListener(this.mCallback58);
            this.landroverMainBottomOffBtn.setOnClickListener(this.mCallback67);
            this.landroverMainBottomParkassistBtn.setOnClickListener(this.mCallback66);
            this.landroverMainBottomRadarBtn.setOnClickListener(this.mCallback65);
            this.landroverMainBottomReturnBtn.setOnClickListener(this.mCallback57);
            this.landroverMainBottomSetupBtn.setOnClickListener(this.mCallback59);
            this.landroverMainBottomVideoBtn.setOnClickListener(this.mCallback63);
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

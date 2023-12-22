package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
public class LandroverMainBottomLayBinding1920x660Impl extends LandroverMainBottomLayBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback330;
    private final View.OnClickListener mCallback331;
    private final View.OnClickListener mCallback332;
    private final View.OnClickListener mCallback333;
    private final View.OnClickListener mCallback334;
    private final View.OnClickListener mCallback335;
    private final View.OnClickListener mCallback336;
    private final View.OnClickListener mCallback337;
    private final View.OnClickListener mCallback338;
    private final View.OnClickListener mCallback339;
    private final View.OnClickListener mCallback340;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverMainBottomLayBinding1920x660Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private LandroverMainBottomLayBinding1920x660Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[8], (ImageView) bindings[6], (ImageView) bindings[4], (ImageView) bindings[5], (ImageView) bindings[2], (ImageView) bindings[11], (ImageView) bindings[10], (ImageView) bindings[9], (ImageView) bindings[1], (ImageView) bindings[3], (ImageView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.landroverMainBottomAirBtn.setTag(null);
        this.landroverMainBottomBtBtn.setTag(null);
        this.landroverMainBottomDvrBtn.setTag(null);
        this.landroverMainBottomGpsBtn.setTag(null);
        this.landroverMainBottomMenuBtn.setTag(null);
        this.landroverMainBottomOffBtn.setTag(null);
        this.landroverMainBottomParkassistBtn.setTag(null);
        this.landroverMainBottomRadarBtn.setTag(null);
        this.landroverMainBottomReturnBtn.setTag(null);
        this.landroverMainBottomSetupBtn.setTag(null);
        this.landroverMainBottomVideoBtn.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        this.mCallback336 = new OnClickListener(this, 7);
        this.mCallback338 = new OnClickListener(this, 9);
        this.mCallback332 = new OnClickListener(this, 3);
        this.mCallback334 = new OnClickListener(this, 5);
        this.mCallback330 = new OnClickListener(this, 1);
        this.mCallback340 = new OnClickListener(this, 11);
        this.mCallback335 = new OnClickListener(this, 6);
        this.mCallback337 = new OnClickListener(this, 8);
        this.mCallback331 = new OnClickListener(this, 2);
        this.mCallback333 = new OnClickListener(this, 4);
        this.mCallback339 = new OnClickListener(this, 10);
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
            setLauncherViewModel((LandroverViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.LandroverMainBottomLayBinding
    public void setLauncherViewModel(LandroverViewModel LauncherViewModel) {
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
        LandroverViewModel landroverViewModel = this.mLauncherViewModel;
        if ((2 & dirtyFlags) != 0) {
            this.landroverMainBottomAirBtn.setOnClickListener(this.mCallback337);
            this.landroverMainBottomBtBtn.setOnClickListener(this.mCallback335);
            this.landroverMainBottomDvrBtn.setOnClickListener(this.mCallback333);
            this.landroverMainBottomGpsBtn.setOnClickListener(this.mCallback334);
            this.landroverMainBottomMenuBtn.setOnClickListener(this.mCallback331);
            this.landroverMainBottomOffBtn.setOnClickListener(this.mCallback340);
            this.landroverMainBottomParkassistBtn.setOnClickListener(this.mCallback339);
            this.landroverMainBottomRadarBtn.setOnClickListener(this.mCallback338);
            this.landroverMainBottomReturnBtn.setOnClickListener(this.mCallback330);
            this.landroverMainBottomSetupBtn.setOnClickListener(this.mCallback332);
            this.landroverMainBottomVideoBtn.setOnClickListener(this.mCallback336);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LandroverViewModel launcherViewModel = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.backKeyClick();
                    return;
                }
                return;
            case 2:
                LandroverViewModel launcherViewModel2 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel2 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.homeKeyClick();
                    return;
                }
                return;
            case 3:
                LandroverViewModel launcherViewModel3 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel3 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LandroverViewModel launcherViewModel4 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel4 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel4.openDvr();
                    return;
                }
                return;
            case 5:
                LandroverViewModel launcherViewModel5 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel5 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel5.openNaviApp();
                    return;
                }
                return;
            case 6:
                LandroverViewModel launcherViewModel6 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel6 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel6.openBtApp();
                    return;
                }
                return;
            case 7:
                LandroverViewModel launcherViewModel7 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel7 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel7.openVideo();
                    return;
                }
                return;
            case 8:
                LandroverViewModel launcherViewModel8 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel8 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel8.airClick();
                    return;
                }
                return;
            case 9:
                LandroverViewModel launcherViewModel9 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel9 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel9.radarClick();
                    return;
                }
                return;
            case 10:
                LandroverViewModel launcherViewModel10 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel10 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel10.parkClick();
                    return;
                }
                return;
            case 11:
                LandroverViewModel launcherViewModel11 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel11 != null;
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

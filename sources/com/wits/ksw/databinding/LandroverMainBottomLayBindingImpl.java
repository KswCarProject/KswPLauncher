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
public class LandroverMainBottomLayBindingImpl extends LandroverMainBottomLayBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback239;
    private final View.OnClickListener mCallback240;
    private final View.OnClickListener mCallback241;
    private final View.OnClickListener mCallback242;
    private final View.OnClickListener mCallback243;
    private final View.OnClickListener mCallback244;
    private final View.OnClickListener mCallback245;
    private final View.OnClickListener mCallback246;
    private final View.OnClickListener mCallback247;
    private final View.OnClickListener mCallback248;
    private final View.OnClickListener mCallback249;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverMainBottomLayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private LandroverMainBottomLayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        this.mCallback249 = new OnClickListener(this, 11);
        this.mCallback239 = new OnClickListener(this, 1);
        this.mCallback247 = new OnClickListener(this, 9);
        this.mCallback245 = new OnClickListener(this, 7);
        this.mCallback243 = new OnClickListener(this, 5);
        this.mCallback241 = new OnClickListener(this, 3);
        this.mCallback248 = new OnClickListener(this, 10);
        this.mCallback246 = new OnClickListener(this, 8);
        this.mCallback244 = new OnClickListener(this, 6);
        this.mCallback242 = new OnClickListener(this, 4);
        this.mCallback240 = new OnClickListener(this, 2);
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
            this.landroverMainBottomAirBtn.setOnClickListener(this.mCallback246);
            this.landroverMainBottomBtBtn.setOnClickListener(this.mCallback244);
            this.landroverMainBottomDvrBtn.setOnClickListener(this.mCallback242);
            this.landroverMainBottomGpsBtn.setOnClickListener(this.mCallback243);
            this.landroverMainBottomMenuBtn.setOnClickListener(this.mCallback240);
            this.landroverMainBottomOffBtn.setOnClickListener(this.mCallback249);
            this.landroverMainBottomParkassistBtn.setOnClickListener(this.mCallback248);
            this.landroverMainBottomRadarBtn.setOnClickListener(this.mCallback247);
            this.landroverMainBottomReturnBtn.setOnClickListener(this.mCallback239);
            this.landroverMainBottomSetupBtn.setOnClickListener(this.mCallback241);
            this.landroverMainBottomVideoBtn.setOnClickListener(this.mCallback245);
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

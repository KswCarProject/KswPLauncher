package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class ActivityMainLexusBindingImpl extends ActivityMainLexusBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback392;
    private final View.OnClickListener mCallback393;
    private final View.OnClickListener mCallback394;
    private final View.OnClickListener mCallback395;
    private final View.OnClickListener mCallback396;
    private final View.OnClickListener mCallback397;
    private final View.OnClickListener mCallback398;
    private final View.OnClickListener mCallback399;
    private final View.OnClickListener mCallback400;
    private final View.OnClickListener mCallback401;
    private final View.OnClickListener mCallback402;
    private final View.OnClickListener mCallback403;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.lexus_main_sv, 13);
        sparseIntArray.put(C0899R.C0901id.id_gallery, 14);
        sparseIntArray.put(C0899R.C0901id.main_menu, 15);
    }

    public ActivityMainLexusBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityMainLexusBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[14], (Button) bindings[12], (Button) bindings[11], (Button) bindings[10], (Button) bindings[4], (Button) bindings[5], (Button) bindings[9], (Button) bindings[6], (Button) bindings[3], (Button) bindings[1], (Button) bindings[2], (Button) bindings[7], (Button) bindings[8], (HorizontalScrollView) bindings[13], (LinearLayout) bindings[15]);
        this.mDirtyFlags = -1L;
        this.lexusAir.setTag(null);
        this.lexusBtApp.setTag(null);
        this.lexusBtCar.setTag(null);
        this.lexusBtDash.setTag(null);
        this.lexusBtDvr.setTag(null);
        this.lexusBtFile.setTag(null);
        this.lexusBtLink.setTag(null);
        this.lexusBtMusic.setTag(null);
        this.lexusBtNavi.setTag(null);
        this.lexusBtPhone.setTag(null);
        this.lexusBtSet.setTag(null);
        this.lexusBtVedio.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        setRootTag(root);
        this.mCallback401 = new OnClickListener(this, 10);
        this.mCallback403 = new OnClickListener(this, 12);
        this.mCallback398 = new OnClickListener(this, 7);
        this.mCallback394 = new OnClickListener(this, 3);
        this.mCallback396 = new OnClickListener(this, 5);
        this.mCallback392 = new OnClickListener(this, 1);
        this.mCallback402 = new OnClickListener(this, 11);
        this.mCallback397 = new OnClickListener(this, 6);
        this.mCallback400 = new OnClickListener(this, 9);
        this.mCallback399 = new OnClickListener(this, 8);
        this.mCallback393 = new OnClickListener(this, 2);
        this.mCallback395 = new OnClickListener(this, 4);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (25 == variableId) {
            setViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityMainLexusBinding
    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAcControl((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAcControl(ObservableBoolean ViewModelAcControl, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean viewModelAcControlGet = false;
        int viewModelAcControlViewVISIBLEViewGONE = 0;
        ObservableBoolean viewModelAcControl = null;
        LauncherViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 7) != 0) {
            if (viewModel != null) {
                viewModelAcControl = viewModel.acControl;
            }
            updateRegistration(0, viewModelAcControl);
            if (viewModelAcControl != null) {
                viewModelAcControlGet = viewModelAcControl.get();
            }
            if ((dirtyFlags & 7) != 0) {
                if (viewModelAcControlGet) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            viewModelAcControlViewVISIBLEViewGONE = viewModelAcControlGet ? 0 : 8;
        }
        if ((7 & dirtyFlags) != 0) {
            this.lexusAir.setVisibility(viewModelAcControlViewVISIBLEViewGONE);
        }
        if ((4 & dirtyFlags) != 0) {
            this.lexusAir.setOnClickListener(this.mCallback403);
            this.lexusBtApp.setOnClickListener(this.mCallback402);
            this.lexusBtCar.setOnClickListener(this.mCallback401);
            this.lexusBtDash.setOnClickListener(this.mCallback395);
            this.lexusBtDvr.setOnClickListener(this.mCallback396);
            this.lexusBtFile.setOnClickListener(this.mCallback400);
            this.lexusBtLink.setOnClickListener(this.mCallback397);
            this.lexusBtMusic.setOnClickListener(this.mCallback394);
            this.lexusBtNavi.setOnClickListener(this.mCallback392);
            this.lexusBtPhone.setOnClickListener(this.mCallback393);
            this.lexusBtSet.setOnClickListener(this.mCallback398);
            this.lexusBtVedio.setOnClickListener(this.mCallback399);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel4 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel5 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel6 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel7 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel8 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel9 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openFileManager(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel10 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel10.openLexusCar(callbackArg_0);
                    return;
                }
                return;
            case 11:
                LauncherViewModel viewModel11 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel11 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel11.openApps(callbackArg_0);
                    return;
                }
                return;
            case 12:
                LauncherViewModel viewModel12 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel12 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel12.openAirControl(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

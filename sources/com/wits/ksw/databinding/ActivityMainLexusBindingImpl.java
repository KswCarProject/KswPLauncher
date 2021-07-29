package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainLexusBindingImpl extends ActivityMainLexusBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback113;
    private final View.OnClickListener mCallback114;
    private final View.OnClickListener mCallback115;
    private final View.OnClickListener mCallback116;
    private final View.OnClickListener mCallback117;
    private final View.OnClickListener mCallback118;
    private final View.OnClickListener mCallback119;
    private final View.OnClickListener mCallback120;
    private final View.OnClickListener mCallback121;
    private final View.OnClickListener mCallback122;
    private final View.OnClickListener mCallback123;
    private final View.OnClickListener mCallback124;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.lexus_main_sv, 13);
        sparseIntArray.put(R.id.id_gallery, 14);
        sparseIntArray.put(R.id.main_menu, 15);
    }

    public ActivityMainLexusBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityMainLexusBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[14], bindings[12], bindings[11], bindings[10], bindings[4], bindings[5], bindings[9], bindings[6], bindings[3], bindings[1], bindings[2], bindings[7], bindings[8], bindings[13], bindings[15]);
        this.mDirtyFlags = -1;
        this.lexusAir.setTag((Object) null);
        this.lexusBtApp.setTag((Object) null);
        this.lexusBtCar.setTag((Object) null);
        this.lexusBtDash.setTag((Object) null);
        this.lexusBtDvr.setTag((Object) null);
        this.lexusBtFile.setTag((Object) null);
        this.lexusBtLink.setTag((Object) null);
        this.lexusBtMusic.setTag((Object) null);
        this.lexusBtNavi.setTag((Object) null);
        this.lexusBtPhone.setTag((Object) null);
        this.lexusBtSet.setTag((Object) null);
        this.lexusBtVedio.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback118 = new OnClickListener(this, 6);
        this.mCallback116 = new OnClickListener(this, 4);
        this.mCallback124 = new OnClickListener(this, 12);
        this.mCallback114 = new OnClickListener(this, 2);
        this.mCallback122 = new OnClickListener(this, 10);
        this.mCallback120 = new OnClickListener(this, 8);
        this.mCallback117 = new OnClickListener(this, 5);
        this.mCallback115 = new OnClickListener(this, 3);
        this.mCallback113 = new OnClickListener(this, 1);
        this.mCallback123 = new OnClickListener(this, 11);
        this.mCallback121 = new OnClickListener(this, 9);
        this.mCallback119 = new OnClickListener(this, 7);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAcControl((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAcControl(ObservableBoolean ViewModelAcControl, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean viewModelAcControlGet = false;
        int viewModelAcControlViewVISIBLEViewGONE = 0;
        ObservableBoolean viewModelAcControl = null;
        LauncherViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 7) != 0) {
            if (viewModel != null) {
                viewModelAcControl = viewModel.acControl;
            }
            int i = 0;
            updateRegistration(0, (Observable) viewModelAcControl);
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
            if (!viewModelAcControlGet) {
                i = 8;
            }
            viewModelAcControlViewVISIBLEViewGONE = i;
        }
        if ((7 & dirtyFlags) != 0) {
            this.lexusAir.setVisibility(viewModelAcControlViewVISIBLEViewGONE);
        }
        if ((4 & dirtyFlags) != 0) {
            this.lexusAir.setOnClickListener(this.mCallback124);
            this.lexusBtApp.setOnClickListener(this.mCallback123);
            this.lexusBtCar.setOnClickListener(this.mCallback122);
            this.lexusBtDash.setOnClickListener(this.mCallback116);
            this.lexusBtDvr.setOnClickListener(this.mCallback117);
            this.lexusBtFile.setOnClickListener(this.mCallback121);
            this.lexusBtLink.setOnClickListener(this.mCallback118);
            this.lexusBtMusic.setOnClickListener(this.mCallback115);
            this.lexusBtNavi.setOnClickListener(this.mCallback113);
            this.lexusBtPhone.setOnClickListener(this.mCallback114);
            this.lexusBtSet.setOnClickListener(this.mCallback119);
            this.lexusBtVedio.setOnClickListener(this.mCallback120);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                if (viewModel5 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                if (viewModel6 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                if (viewModel7 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                if (viewModel8 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openVideo(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                if (viewModel9 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openFileManager(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                if (viewModel10 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel10.openLexusCar(callbackArg_0);
                    return;
                }
                return;
            case 11:
                LauncherViewModel viewModel11 = this.mViewModel;
                if (viewModel11 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel11.openApps(callbackArg_0);
                    return;
                }
                return;
            case 12:
                LauncherViewModel viewModel12 = this.mViewModel;
                if (viewModel12 == null) {
                    viewModelJavaLangObjectNull = false;
                }
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

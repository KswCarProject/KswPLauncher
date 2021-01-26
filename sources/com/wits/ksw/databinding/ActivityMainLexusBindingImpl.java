package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainLexusBindingImpl extends ActivityMainLexusBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback104;
    @Nullable
    private final View.OnClickListener mCallback105;
    @Nullable
    private final View.OnClickListener mCallback106;
    @Nullable
    private final View.OnClickListener mCallback107;
    @Nullable
    private final View.OnClickListener mCallback108;
    @Nullable
    private final View.OnClickListener mCallback109;
    @Nullable
    private final View.OnClickListener mCallback110;
    @Nullable
    private final View.OnClickListener mCallback111;
    @Nullable
    private final View.OnClickListener mCallback112;
    @Nullable
    private final View.OnClickListener mCallback113;
    @Nullable
    private final View.OnClickListener mCallback114;
    @Nullable
    private final View.OnClickListener mCallback115;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.lexus_main_sv, 13);
        sViewsWithIds.put(R.id.id_gallery, 14);
        sViewsWithIds.put(R.id.main_menu, 15);
    }

    public ActivityMainLexusBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
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
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        setRootTag(root);
        this.mCallback112 = new OnClickListener(this, 9);
        this.mCallback108 = new OnClickListener(this, 5);
        this.mCallback113 = new OnClickListener(this, 10);
        this.mCallback109 = new OnClickListener(this, 6);
        this.mCallback106 = new OnClickListener(this, 3);
        this.mCallback110 = new OnClickListener(this, 7);
        this.mCallback107 = new OnClickListener(this, 4);
        this.mCallback111 = new OnClickListener(this, 8);
        this.mCallback104 = new OnClickListener(this, 1);
        this.mCallback105 = new OnClickListener(this, 2);
        this.mCallback114 = new OnClickListener(this, 11);
        this.mCallback115 = new OnClickListener(this, 12);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (19 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeViewModelAcControl((ObservableBoolean) object, fieldId);
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
            this.lexusAir.setOnClickListener(this.mCallback115);
            this.lexusBtApp.setOnClickListener(this.mCallback114);
            this.lexusBtCar.setOnClickListener(this.mCallback113);
            this.lexusBtDash.setOnClickListener(this.mCallback107);
            this.lexusBtDvr.setOnClickListener(this.mCallback108);
            this.lexusBtFile.setOnClickListener(this.mCallback112);
            this.lexusBtLink.setOnClickListener(this.mCallback109);
            this.lexusBtMusic.setOnClickListener(this.mCallback106);
            this.lexusBtNavi.setOnClickListener(this.mCallback104);
            this.lexusBtPhone.setOnClickListener(this.mCallback105);
            this.lexusBtSet.setOnClickListener(this.mCallback110);
            this.lexusBtVedio.setOnClickListener(this.mCallback111);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                if (viewModel5 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                if (viewModel6 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                if (viewModel7 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                if (viewModel8 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openVideo(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                if (viewModel9 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openFileManager(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                if (viewModel10 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel10.openLexusCar(callbackArg_0);
                    return;
                }
                return;
            case 11:
                LauncherViewModel viewModel11 = this.mViewModel;
                if (viewModel11 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel11.openApps(callbackArg_0);
                    return;
                }
                return;
            case 12:
                LauncherViewModel viewModel12 = this.mViewModel;
                if (viewModel12 != null) {
                    viewModelJavaLangObjectNull = true;
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

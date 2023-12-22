package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class BmwId8ModusLayoutBindingImpl extends BmwId8ModusLayoutBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback46;
    private final View.OnClickListener mCallback47;
    private final View.OnClickListener mCallback48;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final ImageView mboundView5;
    private final TextView mboundView6;
    private final ImageView mboundView8;
    private final TextView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(16);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"id8_launcher_left_bar"}, new int[]{10}, new int[]{C0899R.C0902layout.id8_launcher_left_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.rl_modus_container, 11);
        sparseIntArray.put(C0899R.C0901id.tv_change_modus_title, 12);
        sparseIntArray.put(C0899R.C0901id.ll_modus_personal, 13);
        sparseIntArray.put(C0899R.C0901id.ll_modus_sport, 14);
        sparseIntArray.put(C0899R.C0901id.ll_modus_efficient, 15);
    }

    public BmwId8ModusLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private BmwId8ModusLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (Id8LauncherLeftBarBinding) bindings[10], (RelativeLayout) bindings[15], (ImageView) bindings[7], (RelativeLayout) bindings[13], (ImageView) bindings[1], (RelativeLayout) bindings[14], (ImageView) bindings[4], (RelativeLayout) bindings[11], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.llLeftContainer);
        this.llModusEfficientImg.setTag(null);
        this.llModusPersonalImg.setTag(null);
        this.llModusSportImg.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[5];
        this.mboundView5 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[8];
        this.mboundView8 = imageView3;
        imageView3.setTag(null);
        TextView textView3 = (TextView) bindings[9];
        this.mboundView9 = textView3;
        textView3.setTag(null);
        setRootTag(root);
        this.mCallback47 = new OnClickListener(this, 2);
        this.mCallback48 = new OnClickListener(this, 3);
        this.mCallback46 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
        }
        this.llLeftContainer.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.llLeftContainer.hasPendingBindings();
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

    @Override // com.wits.ksw.databinding.BmwId8ModusLayoutBinding
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.llLeftContainer.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelIsEfficientModus((ObservableField) object, fieldId);
            case 1:
                return onChangeLauncherViewModelIsPersonalModus((ObservableField) object, fieldId);
            case 2:
                return onChangeLauncherViewModelIsSportModus((ObservableField) object, fieldId);
            case 3:
                return onChangeLlLeftContainer((Id8LauncherLeftBarBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelIsEfficientModus(ObservableField<Boolean> LauncherViewModelIsEfficientModus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelIsPersonalModus(ObservableField<Boolean> LauncherViewModelIsPersonalModus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelIsSportModus(ObservableField<Boolean> LauncherViewModelIsSportModus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLlLeftContainer(Id8LauncherLeftBarBinding LlLeftContainer, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int launcherViewModelIsSportModusViewVISIBLEViewGONE;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Boolean launcherViewModelIsEfficientModusGet = null;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        Boolean launcherViewModelIsPersonalModusGet = null;
        int launcherViewModelIsEfficientModusViewVISIBLEViewGONE = 0;
        int launcherViewModelIsPersonalModusViewVISIBLEViewGONE = 0;
        ObservableField<Boolean> launcherViewModelIsEfficientModus = null;
        ObservableField<Boolean> launcherViewModelIsPersonalModus = null;
        ObservableField<Boolean> launcherViewModelIsSportModus = null;
        Boolean launcherViewModelIsSportModusGet = null;
        if ((dirtyFlags & 55) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelIsEfficientModus = launcherViewModel.isEfficientModus;
                }
                updateRegistration(0, launcherViewModelIsEfficientModus);
                if (launcherViewModelIsEfficientModus != null) {
                    launcherViewModelIsEfficientModusGet = launcherViewModelIsEfficientModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsEfficientModusGet);
                if ((dirtyFlags & 49) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                launcherViewModelIsEfficientModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet ? 0 : 8;
            }
            if ((dirtyFlags & 50) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelIsPersonalModus = launcherViewModel.isPersonalModus;
                }
                updateRegistration(1, launcherViewModelIsPersonalModus);
                if (launcherViewModelIsPersonalModus != null) {
                    Boolean launcherViewModelIsPersonalModusGet2 = launcherViewModelIsPersonalModus.get();
                    launcherViewModelIsPersonalModusGet = launcherViewModelIsPersonalModusGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsPersonalModusGet);
                if ((dirtyFlags & 50) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                launcherViewModelIsPersonalModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet ? 0 : 8;
            }
            if ((dirtyFlags & 52) == 0) {
                launcherViewModelIsSportModusViewVISIBLEViewGONE = 0;
            } else {
                if (launcherViewModel != null) {
                    launcherViewModelIsSportModus = launcherViewModel.isSportModus;
                }
                updateRegistration(2, launcherViewModelIsSportModus);
                if (launcherViewModelIsSportModus != null) {
                    Boolean launcherViewModelIsSportModusGet2 = launcherViewModelIsSportModus.get();
                    launcherViewModelIsSportModusGet = launcherViewModelIsSportModusGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsSportModusGet);
                if ((dirtyFlags & 52) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                int launcherViewModelIsSportModusViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet ? 0 : 8;
                launcherViewModelIsSportModusViewVISIBLEViewGONE = launcherViewModelIsSportModusViewVISIBLEViewGONE2;
            }
        } else {
            launcherViewModelIsSportModusViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 48) != 0) {
            this.llLeftContainer.setLeftViewModel(launcherViewModel);
        }
        if ((dirtyFlags & 32) != 0) {
            this.llModusEfficientImg.setOnClickListener(this.mCallback48);
            this.llModusPersonalImg.setOnClickListener(this.mCallback46);
            this.llModusSportImg.setOnClickListener(this.mCallback47);
        }
        if ((dirtyFlags & 50) != 0) {
            this.mboundView2.setVisibility(launcherViewModelIsPersonalModusViewVISIBLEViewGONE);
            this.mboundView3.setVisibility(launcherViewModelIsPersonalModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 52) != 0) {
            this.mboundView5.setVisibility(launcherViewModelIsSportModusViewVISIBLEViewGONE);
            this.mboundView6.setVisibility(launcherViewModelIsSportModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 49) != 0) {
            this.mboundView8.setVisibility(launcherViewModelIsEfficientModusViewVISIBLEViewGONE);
            this.mboundView9.setVisibility(launcherViewModelIsEfficientModusViewVISIBLEViewGONE);
        }
        executeBindingsOn(this.llLeftContainer);
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel launcherViewModel = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.changeModusToPersonal(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel2 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.changeModusToSport(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                launcherViewModelJavaLangObjectNull = launcherViewModel3 != null;
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel3.changeModusToEfficient(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

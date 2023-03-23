package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ModusDataBindingImpl extends ModusDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback305;
    private long mDirtyFlags;
    private final ImageView mboundView1;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    public ModusDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ModusDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[2], bindings[0]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.llContainer.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        TextView textView = bindings[3];
        this.mboundView3 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag((Object) null);
        setRootTag(root);
        this.mCallback305 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (11 != variableId) {
            return false;
        }
        setModusViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setModusViewModel(LauncherViewModel ModusViewModel) {
        this.mModusViewModel = ModusViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeModusViewModelIsEfficientModus((ObservableField) object, fieldId);
            case 1:
                return onChangeModusViewModelIsPersonalModus((ObservableField) object, fieldId);
            case 2:
                return onChangeModusViewModelId8ModusDrawable((ObservableField) object, fieldId);
            case 3:
                return onChangeModusViewModelIsSportModus((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeModusViewModelIsEfficientModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModusViewModelIsPersonalModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModusViewModelId8ModusDrawable(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModusViewModelIsSportModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int modusViewModelIsEfficientModusViewVISIBLEViewGONE;
        Boolean modusViewModelIsEfficientModusGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int modusViewModelIsPersonalModusViewVISIBLEViewGONE = 0;
        ObservableField<Boolean> modusViewModelIsEfficientModus = null;
        ObservableField<Boolean> modusViewModelIsPersonalModus = null;
        ObservableField<BitmapDrawable> modusViewModelId8ModusDrawable = null;
        ObservableField<Boolean> modusViewModelIsSportModus = null;
        int modusViewModelIsSportModusViewVISIBLEViewGONE = 0;
        BitmapDrawable modusViewModelId8ModusDrawableGet = null;
        Boolean modusViewModelIsPersonalModusGet = null;
        LauncherViewModel modusViewModel = this.mModusViewModel;
        Boolean modusViewModelIsSportModusGet = null;
        int modusViewModelIsEfficientModusViewVISIBLEViewGONE2 = 0;
        if ((dirtyFlags & 63) != 0) {
            int i = 8;
            if ((dirtyFlags & 49) != 0) {
                if (modusViewModel != null) {
                    modusViewModelIsEfficientModus = modusViewModel.isEfficientModus;
                }
                updateRegistration(0, (Observable) modusViewModelIsEfficientModus);
                if (modusViewModelIsEfficientModus != null) {
                    modusViewModelIsEfficientModusGet = modusViewModelIsEfficientModus.get();
                } else {
                    modusViewModelIsEfficientModusGet = null;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxModusViewModelIsEfficientModusGet = ViewDataBinding.safeUnbox(modusViewModelIsEfficientModusGet);
                if ((dirtyFlags & 49) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxModusViewModelIsEfficientModusGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                modusViewModelIsEfficientModusViewVISIBLEViewGONE2 = androidDatabindingViewDataBindingSafeUnboxModusViewModelIsEfficientModusGet ? 0 : 8;
            } else {
                modusViewModelIsEfficientModusGet = null;
            }
            if ((dirtyFlags & 50) != 0) {
                if (modusViewModel != null) {
                    modusViewModelIsPersonalModus = modusViewModel.isPersonalModus;
                }
                updateRegistration(1, (Observable) modusViewModelIsPersonalModus);
                if (modusViewModelIsPersonalModus != null) {
                    modusViewModelIsPersonalModusGet = modusViewModelIsPersonalModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxModusViewModelIsPersonalModusGet = ViewDataBinding.safeUnbox(modusViewModelIsPersonalModusGet);
                if ((dirtyFlags & 50) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxModusViewModelIsPersonalModusGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                modusViewModelIsPersonalModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxModusViewModelIsPersonalModusGet ? 0 : 8;
            }
            if ((dirtyFlags & 52) != 0) {
                if (modusViewModel != null) {
                    modusViewModelId8ModusDrawable = modusViewModel.id8ModusDrawable;
                }
                updateRegistration(2, (Observable) modusViewModelId8ModusDrawable);
                if (modusViewModelId8ModusDrawable != null) {
                    modusViewModelId8ModusDrawableGet = modusViewModelId8ModusDrawable.get();
                }
            }
            if ((dirtyFlags & 56) != 0) {
                if (modusViewModel != null) {
                    modusViewModelIsSportModus = modusViewModel.isSportModus;
                }
                updateRegistration(3, (Observable) modusViewModelIsSportModus);
                if (modusViewModelIsSportModus != null) {
                    modusViewModelIsSportModusGet = modusViewModelIsSportModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxModusViewModelIsSportModusGet = ViewDataBinding.safeUnbox(modusViewModelIsSportModusGet);
                if ((dirtyFlags & 56) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxModusViewModelIsSportModusGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxModusViewModelIsSportModusGet) {
                    i = 0;
                }
                modusViewModelIsSportModusViewVISIBLEViewGONE = i;
                modusViewModelIsEfficientModusViewVISIBLEViewGONE = modusViewModelIsEfficientModusViewVISIBLEViewGONE2;
                Boolean bool = modusViewModelIsEfficientModusGet;
            } else {
                modusViewModelIsEfficientModusViewVISIBLEViewGONE = modusViewModelIsEfficientModusViewVISIBLEViewGONE2;
                Boolean bool2 = modusViewModelIsEfficientModusGet;
            }
        } else {
            modusViewModelIsEfficientModusViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 32) != 0) {
            LauncherViewModel launcherViewModel = modusViewModel;
            Boolean bool3 = modusViewModelIsSportModusGet;
            this.ivMask.setOnClickListener(this.mCallback305);
        } else {
            Boolean bool4 = modusViewModelIsSportModusGet;
        }
        if ((dirtyFlags & 52) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, modusViewModelId8ModusDrawableGet);
        }
        if ((dirtyFlags & 50) != 0) {
            this.mboundView3.setVisibility(modusViewModelIsPersonalModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 56) != 0) {
            this.mboundView4.setVisibility(modusViewModelIsSportModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 49) != 0) {
            this.mboundView5.setVisibility(modusViewModelIsEfficientModusViewVISIBLEViewGONE);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel modusViewModel = this.mModusViewModel;
        if (modusViewModel != null) {
            modusViewModel.enterChangeModus(callbackArg_0);
        }
    }
}

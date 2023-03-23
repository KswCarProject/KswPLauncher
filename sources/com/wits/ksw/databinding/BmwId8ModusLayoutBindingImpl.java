package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class BmwId8ModusLayoutBindingImpl extends BmwId8ModusLayoutBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback37;
    private final View.OnClickListener mCallback38;
    private final View.OnClickListener mCallback39;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView10;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(16);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"id8_launcher_left_bar"}, new int[]{11}, new int[]{R.layout.id8_launcher_left_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rl_modus_container, 12);
        sparseIntArray.put(R.id.ll_modus_personal, 13);
        sparseIntArray.put(R.id.ll_modus_sport, 14);
        sparseIntArray.put(R.id.ll_modus_efficient, 15);
    }

    public BmwId8ModusLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BmwId8ModusLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[11], bindings[15], bindings[8], bindings[13], bindings[2], bindings[14], bindings[5], bindings[12], bindings[1]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.llLeftContainer);
        this.llModusEfficientImg.setTag((Object) null);
        this.llModusPersonalImg.setTag((Object) null);
        this.llModusSportImg.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = bindings[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        ImageView imageView = bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
        TextView textView2 = bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        ImageView imageView2 = bindings[6];
        this.mboundView6 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView3 = bindings[7];
        this.mboundView7 = textView3;
        textView3.setTag((Object) null);
        ImageView imageView3 = bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag((Object) null);
        this.tvChangeModusTitle.setTag((Object) null);
        setRootTag(root);
        this.mCallback39 = new OnClickListener(this, 3);
        this.mCallback37 = new OnClickListener(this, 1);
        this.mCallback38 = new OnClickListener(this, 2);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
        }
        this.llLeftContainer.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.llLeftContainer.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.wits.ksw.databinding.Id8LauncherLeftBarBinding r0 = r4.llLeftContainer
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8ModusLayoutBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, Object variable) {
        if (7 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.llLeftContainer.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelId8TextColor((ObservableField) object, fieldId);
            case 1:
                return onChangeLauncherViewModelIsEfficientModus((ObservableField) object, fieldId);
            case 2:
                return onChangeLauncherViewModelIsPersonalModus((ObservableField) object, fieldId);
            case 3:
                return onChangeLauncherViewModelIsSportModus((ObservableField) object, fieldId);
            case 4:
                return onChangeLlLeftContainer((Id8LauncherLeftBarBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelId8TextColor(ObservableField<Integer> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelIsEfficientModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelIsPersonalModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelIsSportModus(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeLlLeftContainer(Id8LauncherLeftBarBinding LlLeftContainer, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int launcherViewModelIsSportModusViewVISIBLEViewGONE;
        ObservableField<Boolean> launcherViewModelIsSportModus;
        ObservableField<Boolean> launcherViewModelIsPersonalModus;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<Integer> launcherViewModelId8TextColor = null;
        Boolean launcherViewModelIsEfficientModusGet = null;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        Boolean launcherViewModelIsPersonalModusGet = null;
        int launcherViewModelIsEfficientModusViewVISIBLEViewGONE = 0;
        int launcherViewModelIsPersonalModusViewVISIBLEViewGONE = 0;
        ObservableField<Boolean> launcherViewModelIsEfficientModus = null;
        int androidDatabindingViewDataBindingSafeUnboxLauncherViewModelId8TextColorGet = 0;
        Integer launcherViewModelId8TextColorGet = null;
        Boolean launcherViewModelIsSportModusGet = null;
        if ((dirtyFlags & 111) != 0) {
            if ((dirtyFlags & 97) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelId8TextColor = launcherViewModel.id8TextColor;
                }
                updateRegistration(0, (Observable) launcherViewModelId8TextColor);
                if (launcherViewModelId8TextColor != null) {
                    launcherViewModelId8TextColorGet = launcherViewModelId8TextColor.get();
                }
                androidDatabindingViewDataBindingSafeUnboxLauncherViewModelId8TextColorGet = ViewDataBinding.safeUnbox(launcherViewModelId8TextColorGet);
            }
            if ((dirtyFlags & 98) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelIsEfficientModus = launcherViewModel.isEfficientModus;
                }
                updateRegistration(1, (Observable) launcherViewModelIsEfficientModus);
                if (launcherViewModelIsEfficientModus != null) {
                    launcherViewModelIsEfficientModusGet = launcherViewModelIsEfficientModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsEfficientModusGet);
                if ((dirtyFlags & 98) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                launcherViewModelIsEfficientModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsEfficientModusGet ? 0 : 8;
            }
            if ((dirtyFlags & 100) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelIsPersonalModus = launcherViewModel.isPersonalModus;
                } else {
                    launcherViewModelIsPersonalModus = null;
                }
                updateRegistration(2, (Observable) launcherViewModelIsPersonalModus);
                if (launcherViewModelIsPersonalModus != null) {
                    launcherViewModelIsPersonalModusGet = launcherViewModelIsPersonalModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsPersonalModusGet);
                if ((dirtyFlags & 100) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                launcherViewModelIsPersonalModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsPersonalModusGet ? 0 : 8;
                ObservableField<Boolean> observableField = launcherViewModelIsPersonalModus;
            }
            if ((dirtyFlags & 104) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelIsSportModus = launcherViewModel.isSportModus;
                } else {
                    launcherViewModelIsSportModus = null;
                }
                updateRegistration(3, (Observable) launcherViewModelIsSportModus);
                if (launcherViewModelIsSportModus != null) {
                    launcherViewModelIsSportModusGet = launcherViewModelIsSportModus.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet = ViewDataBinding.safeUnbox(launcherViewModelIsSportModusGet);
                if ((dirtyFlags & 104) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                ObservableField<Boolean> observableField2 = launcherViewModelIsSportModus;
                launcherViewModelIsSportModusViewVISIBLEViewGONE = androidDatabindingViewDataBindingSafeUnboxLauncherViewModelIsSportModusGet ? 0 : 8;
            } else {
                launcherViewModelIsSportModusViewVISIBLEViewGONE = 0;
            }
        } else {
            launcherViewModelIsSportModusViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 96) != 0) {
            this.llLeftContainer.setLeftViewModel(launcherViewModel);
        }
        if ((dirtyFlags & 64) != 0) {
            ObservableField<Integer> observableField3 = launcherViewModelId8TextColor;
            this.llModusEfficientImg.setOnClickListener(this.mCallback39);
            this.llModusPersonalImg.setOnClickListener(this.mCallback37);
            this.llModusSportImg.setOnClickListener(this.mCallback38);
        }
        if ((dirtyFlags & 98) != 0) {
            this.mboundView10.setVisibility(launcherViewModelIsEfficientModusViewVISIBLEViewGONE);
            this.mboundView9.setVisibility(launcherViewModelIsEfficientModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 100) != 0) {
            this.mboundView3.setVisibility(launcherViewModelIsPersonalModusViewVISIBLEViewGONE);
            this.mboundView4.setVisibility(launcherViewModelIsPersonalModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 104) != 0) {
            this.mboundView6.setVisibility(launcherViewModelIsSportModusViewVISIBLEViewGONE);
            this.mboundView7.setVisibility(launcherViewModelIsSportModusViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 97) != 0) {
            this.tvChangeModusTitle.setTextColor(androidDatabindingViewDataBindingSafeUnboxLauncherViewModelId8TextColorGet);
        }
        executeBindingsOn(this.llLeftContainer);
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean launcherViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel launcherViewModel = this.mLauncherViewModel;
                if (launcherViewModel == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel.changeModusToPersonal(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel launcherViewModel2 = this.mLauncherViewModel;
                if (launcherViewModel2 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
                if (launcherViewModelJavaLangObjectNull) {
                    launcherViewModel2.changeModusToSport(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel launcherViewModel3 = this.mLauncherViewModel;
                if (launcherViewModel3 == null) {
                    launcherViewModelJavaLangObjectNull = false;
                }
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

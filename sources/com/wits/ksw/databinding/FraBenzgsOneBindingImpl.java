package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public class FraBenzgsOneBindingImpl extends FraBenzgsOneBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.benz_gs_home_navi_hint, 8);
        sViewsWithIds.put(R.id.benz_gs_home_navi, 9);
        sViewsWithIds.put(R.id.benz_gs_home_music, 10);
        sViewsWithIds.put(R.id.benz_gs_home_bt, 11);
        sViewsWithIds.put(R.id.benz_gs_home_car_hint, 12);
        sViewsWithIds.put(R.id.benz_gs_home_car, 13);
        sViewsWithIds.put(R.id.benz_gs_home_set_hint, 14);
        sViewsWithIds.put(R.id.benz_gs_home_set, 15);
    }

    public FraBenzgsOneBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private FraBenzgsOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[11], bindings[4], bindings[5], bindings[13], bindings[6], bindings[12], bindings[10], bindings[2], bindings[3], bindings[9], bindings[1], bindings[8], bindings[15], bindings[7], bindings[14], bindings[0]);
        this.mDirtyFlags = -1;
        this.benzGsHomeBtBtn.setTag((Object) null);
        this.benzGsHomeBtHint.setTag((Object) null);
        this.benzGsHomeCarBtn.setTag((Object) null);
        this.benzGsHomeMusicBtn.setTag((Object) null);
        this.benzGsHomeMusicHint.setTag((Object) null);
        this.benzGsHomeNaviBtn.setTag((Object) null);
        this.benzGsHomeSetBtn.setTag((Object) null);
        this.benzgsHomeOne.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (10 != variableId) {
            return false;
        }
        setVm((BenzGsViewMoel) variable);
        return true;
    }

    public void setVm(@Nullable BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmPhoneConState((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmMediaInfoMusicName((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIndex(ObservableInt VmIndex, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmPhoneConState(ObservableInt VmPhoneConState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String str;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean vmIndexInt2 = false;
        ObservableInt vmIndex = null;
        BenzGsViewMoel vm = this.mVm;
        ObservableInt vmPhoneConState = null;
        ObservableField<String> vmMediaInfoMusicName = null;
        String vmMediaInfoMusicNameGet = null;
        int vmPhoneConStateGet = 0;
        boolean vmIndexInt3 = false;
        boolean vmIndexInt1 = false;
        String vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName = null;
        int vmIndexGet = 0;
        boolean vmIndexInt12 = false;
        boolean vmMediaInfoMusicNameJavaLangObjectNull = false;
        boolean vmIndexInt4 = false;
        String vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone = null;
        if ((dirtyFlags & 27) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (vm != null) {
                    vmIndex = vm.index;
                }
                updateRegistration(0, (Observable) vmIndex);
                if (vmIndex != null) {
                    vmIndexGet = vmIndex.get();
                }
                int vmIndexGet2 = vmIndexGet;
                boolean vmIndexInt22 = vmIndexGet2 == 2;
                boolean vmIndexInt0 = vmIndexGet2 == 0;
                boolean vmIndexInt32 = vmIndexGet2 == 3;
                boolean vmIndexInt13 = vmIndexGet2 == 1;
                boolean vmIndexInt23 = vmIndexInt22;
                vmIndexInt4 = vmIndexGet2 == 4;
                int i = vmIndexGet2;
                vmIndexInt12 = vmIndexInt13;
                vmIndexInt2 = vmIndexInt23;
                vmIndexInt1 = vmIndexInt32;
                vmIndexInt3 = vmIndexInt0;
            }
            if ((dirtyFlags & 26) != 0) {
                if (vm != null) {
                    vmPhoneConState = vm.phoneConState;
                }
                updateRegistration(1, (Observable) vmPhoneConState);
                if (vmPhoneConState != null) {
                    vmPhoneConStateGet = vmPhoneConState.get();
                }
                boolean vmPhoneConStateInt0 = vmPhoneConStateGet == 0;
                if ((dirtyFlags & 26) != 0) {
                    if (vmPhoneConStateInt0) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone = vmPhoneConStateInt0 ? this.benzGsHomeBtHint.getResources().getString(R.string.ksw_id7_not_connected_phone) : this.benzGsHomeBtHint.getResources().getString(R.string.ksw_id7_connected_phone);
            }
        }
        boolean vmIndexInt33 = vmIndexInt1;
        boolean vmIndexInt42 = vmIndexInt4;
        String vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone2 = vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone;
        boolean vmIndexInt02 = vmIndexInt3;
        boolean vmIndexInt24 = vmIndexInt2;
        boolean vmIndexInt14 = vmIndexInt12;
        if ((dirtyFlags & 20) != 0) {
            BenzGsViewMoel benzGsViewMoel = vm;
            MediaInfo vmMediaInfo = BenzGsViewMoel.mediaInfo;
            if (vmMediaInfo != null) {
                vmMediaInfoMusicName = vmMediaInfo.musicName;
            }
            MediaInfo mediaInfo = vmMediaInfo;
            updateRegistration(2, (Observable) vmMediaInfoMusicName);
            if (vmMediaInfoMusicName != null) {
                vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
            }
            vmMediaInfoMusicNameJavaLangObjectNull = vmMediaInfoMusicNameGet == null;
            if ((dirtyFlags & 20) != 0) {
                if (vmMediaInfoMusicNameJavaLangObjectNull) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
        }
        if ((dirtyFlags & 20) != 0) {
            if (vmMediaInfoMusicNameJavaLangObjectNull) {
                ObservableInt observableInt = vmPhoneConState;
                str = this.benzGsHomeMusicHint.getResources().getString(R.string.benz_gs_home_music_hint);
            } else {
                str = vmMediaInfoMusicNameGet;
            }
            vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName = str;
        }
        if ((dirtyFlags & 25) != 0) {
            this.benzGsHomeBtBtn.setSelected(vmIndexInt24);
            this.benzGsHomeCarBtn.setSelected(vmIndexInt33);
            this.benzGsHomeMusicBtn.setSelected(vmIndexInt14);
            this.benzGsHomeNaviBtn.setSelected(vmIndexInt02);
            this.benzGsHomeSetBtn.setSelected(vmIndexInt42);
        }
        if ((dirtyFlags & 26) != 0) {
            TextViewBindingAdapter.setText(this.benzGsHomeBtHint, vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone2);
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.benzGsHomeMusicHint, vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName);
        }
    }
}

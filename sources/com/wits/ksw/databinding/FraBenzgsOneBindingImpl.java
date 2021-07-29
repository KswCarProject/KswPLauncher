package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public class FraBenzgsOneBindingImpl extends FraBenzgsOneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.benz_gs_home_navi_hint, 8);
        sparseIntArray.put(R.id.benz_gs_home_navi, 9);
        sparseIntArray.put(R.id.benz_gs_home_music, 10);
        sparseIntArray.put(R.id.benz_gs_home_bt, 11);
        sparseIntArray.put(R.id.benz_gs_home_car_hint, 12);
        sparseIntArray.put(R.id.benz_gs_home_car, 13);
        sparseIntArray.put(R.id.benz_gs_home_set_hint, 14);
        sparseIntArray.put(R.id.benz_gs_home_set, 15);
    }

    public FraBenzgsOneBindingImpl(DataBindingComponent bindingComponent, View root) {
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

    public boolean setVariable(int variableId, Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((BenzGsViewMoel) variable);
        return true;
    }

    public void setVm(BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(17);
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
        boolean vmIndexInt1;
        String vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName;
        String vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName2;
        ObservableField<String> vmMediaInfoMusicName;
        int vmIndexGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean vmIndexInt2 = false;
        boolean vmIndexInt0 = false;
        String vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone = null;
        ObservableInt vmIndex = null;
        String vmMediaInfoMusicNameGet = null;
        BenzGsViewMoel vm = this.mVm;
        int vmPhoneConStateGet = 0;
        ObservableInt vmPhoneConState = null;
        boolean vmIndexInt3 = false;
        boolean vmIndexInt32 = false;
        boolean vmIndexInt12 = false;
        boolean vmMediaInfoMusicNameJavaLangObjectNull = false;
        if ((dirtyFlags & 27) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (vm != null) {
                    vmIndex = vm.index;
                }
                updateRegistration(0, (Observable) vmIndex);
                if (vmIndex != null) {
                    vmIndexGet = vmIndex.get();
                } else {
                    vmIndexGet = 0;
                }
                vmIndexInt2 = vmIndexGet == 2;
                boolean vmIndexInt4 = vmIndexGet == 4;
                boolean vmIndexInt02 = vmIndexGet == 0;
                boolean vmIndexInt33 = vmIndexGet == 3;
                int i = vmIndexGet;
                vmIndexInt12 = vmIndexGet == 1;
                vmIndexInt32 = vmIndexInt33;
                vmIndexInt3 = vmIndexInt02;
                vmIndexInt0 = vmIndexInt4;
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
                vmIndexInt1 = vmIndexInt12;
            } else {
                vmIndexInt1 = vmIndexInt12;
            }
        } else {
            vmIndexInt1 = false;
        }
        if ((dirtyFlags & 20) != 0) {
            MediaInfo vmMediaInfo = BenzGsViewMoel.mediaInfo;
            if (vmMediaInfo != null) {
                vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName = null;
                vmMediaInfoMusicName = vmMediaInfo.musicName;
            } else {
                vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName = null;
                vmMediaInfoMusicName = null;
            }
            MediaInfo mediaInfo = vmMediaInfo;
            updateRegistration(2, (Observable) vmMediaInfoMusicName);
            if (vmMediaInfoMusicName != null) {
                vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
            }
            vmMediaInfoMusicNameJavaLangObjectNull = vmMediaInfoMusicNameGet == null;
            if ((dirtyFlags & 20) == 0) {
                ObservableField<String> observableField = vmMediaInfoMusicName;
            } else if (vmMediaInfoMusicNameJavaLangObjectNull) {
                dirtyFlags |= 64;
                ObservableField<String> observableField2 = vmMediaInfoMusicName;
            } else {
                dirtyFlags |= 32;
                ObservableField<String> observableField3 = vmMediaInfoMusicName;
            }
        } else {
            vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName = null;
        }
        if ((dirtyFlags & 20) != 0) {
            vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName2 = vmMediaInfoMusicNameJavaLangObjectNull ? this.benzGsHomeMusicHint.getResources().getString(R.string.benz_gs_home_music_hint) : vmMediaInfoMusicNameGet;
        } else {
            vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName2 = vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName;
        }
        if ((dirtyFlags & 25) != 0) {
            this.benzGsHomeBtBtn.setSelected(vmIndexInt2);
            this.benzGsHomeCarBtn.setSelected(vmIndexInt32);
            this.benzGsHomeMusicBtn.setSelected(vmIndexInt1);
            this.benzGsHomeNaviBtn.setSelected(vmIndexInt3);
            this.benzGsHomeSetBtn.setSelected(vmIndexInt0);
        }
        if ((dirtyFlags & 26) != 0) {
            TextViewBindingAdapter.setText(this.benzGsHomeBtHint, vmPhoneConStateInt0BenzGsHomeBtHintAndroidStringKswId7NotConnectedPhoneBenzGsHomeBtHintAndroidStringKswId7ConnectedPhone);
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.benzGsHomeMusicHint, vmMediaInfoMusicNameJavaLangObjectNullBenzGsHomeMusicHintAndroidStringBenzGsHomeMusicHintVmMediaInfoMusicName2);
        }
    }
}

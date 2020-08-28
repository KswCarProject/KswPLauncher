package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.VolumeViewModel;

public class ActivityAudiSoundBindingSw600dpLandImpl extends ActivityAudiSoundBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final SeekBar mboundView3;
    @NonNull
    private final TextView mboundView4;
    @NonNull
    private final SeekBar mboundView5;
    @NonNull
    private final TextView mboundView6;
    @NonNull
    private final SeekBar mboundView7;
    @NonNull
    private final TextView mboundView8;

    static {
        sViewsWithIds.put(R.id.hzTextView, 9);
        sViewsWithIds.put(R.id.hzMediaLinearLayout, 10);
        sViewsWithIds.put(R.id.audio_seekbar_title, 11);
        sViewsWithIds.put(R.id.hzCallLinearLayout, 12);
        sViewsWithIds.put(R.id.carVolumeTextView, 13);
        sViewsWithIds.put(R.id.carCallLinearLayout, 14);
        sViewsWithIds.put(R.id.carNaviLinearLayout, 15);
    }

    public ActivityAudiSoundBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityAudiSoundBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[1], bindings[2], bindings[11], bindings[14], bindings[15], bindings[13], bindings[12], bindings[10], bindings[9], bindings[0]);
        this.mDirtyFlags = -1;
        this.audioSeekbar.setTag((Object) null);
        this.audioSeekbarRightText.setTag((Object) null);
        this.linearLayout4.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
        this.mboundView7 = bindings[7];
        this.mboundView7.setTag((Object) null);
        this.mboundView8 = bindings[8];
        this.mboundView8.setTag((Object) null);
        setRootTag(root);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setVm((VolumeViewModel) variable);
        return true;
    }

    public void setVm(@Nullable VolumeViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmCarNaviVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmCarCallVolume((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmHzMediaVolume((ObservableInt) object, fieldId);
            case 3:
                return onChangeVmHzcallVolume((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarNaviVolume(ObservableInt VmCarNaviVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmCarCallVolume(ObservableInt VmCarCallVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmHzMediaVolume(ObservableInt VmHzMediaVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmHzcallVolume(ObservableInt VmHzcallVolume, int fieldId) {
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
        String javaLangStringVmCarCallVolume;
        int vmHzcallVolumeGet;
        int vmCarCallVolumeGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String javaLangStringVmCarCallVolume2 = null;
        VolumeViewModel vm = this.mVm;
        String javaLangStringVmHzcallVolume = null;
        ObservableInt vmCarNaviVolume = null;
        ObservableInt vmCarCallVolume = null;
        int vmCarNaviVolumeGet = 0;
        String javaLangStringVmHzMediaVolume = null;
        ObservableInt vmHzMediaVolume = null;
        int vmHzMediaVolumeGet = 0;
        String javaLangStringVmCarNaviVolume = null;
        ObservableInt vmHzcallVolume = null;
        int vmCarCallVolumeGet2 = 0;
        int vmHzcallVolumeGet2 = 0;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (vm != null) {
                    vmCarNaviVolume = vm.carNaviVolume;
                }
                updateRegistration(0, (Observable) vmCarNaviVolume);
                if (vmCarNaviVolume != null) {
                    vmCarNaviVolumeGet = vmCarNaviVolume.get();
                }
                javaLangStringVmCarNaviVolume = "" + vmCarNaviVolumeGet;
            }
            if ((dirtyFlags & 50) != 0) {
                if (vm != null) {
                    vmCarCallVolume = vm.carCallVolume;
                }
                updateRegistration(1, (Observable) vmCarCallVolume);
                if (vmCarCallVolume != null) {
                    vmCarCallVolumeGet2 = vmCarCallVolume.get();
                }
                int vmCarCallVolumeGet3 = vmCarCallVolumeGet2;
                javaLangStringVmCarCallVolume2 = "" + vmCarCallVolumeGet3;
                vmCarCallVolumeGet2 = vmCarCallVolumeGet3;
            }
            if ((dirtyFlags & 52) != 0) {
                if (vm != null) {
                    vmHzMediaVolume = vm.hzMediaVolume;
                }
                updateRegistration(2, (Observable) vmHzMediaVolume);
                if (vmHzMediaVolume != null) {
                    vmHzMediaVolumeGet = vmHzMediaVolume.get();
                }
                javaLangStringVmHzMediaVolume = "" + vmHzMediaVolumeGet;
            }
            if ((dirtyFlags & 56) != 0) {
                if (vm != null) {
                    vmHzcallVolume = vm.hzcallVolume;
                }
                updateRegistration(3, (Observable) vmHzcallVolume);
                if (vmHzcallVolume != null) {
                    vmHzcallVolumeGet2 = vmHzcallVolume.get();
                }
                vmHzcallVolumeGet = vmHzcallVolumeGet2;
                StringBuilder sb = new StringBuilder();
                String javaLangStringVmCarCallVolume3 = javaLangStringVmCarCallVolume2;
                sb.append("");
                sb.append(vmHzcallVolumeGet);
                javaLangStringVmHzcallVolume = sb.toString();
                vmCarCallVolumeGet = vmCarCallVolumeGet2;
                javaLangStringVmCarCallVolume = javaLangStringVmCarCallVolume3;
            } else {
                String javaLangStringVmCarCallVolume4 = javaLangStringVmCarCallVolume2;
                vmCarCallVolumeGet = vmCarCallVolumeGet2;
                vmHzcallVolumeGet = 0;
                javaLangStringVmCarCallVolume = javaLangStringVmCarCallVolume4;
            }
        } else {
            vmCarCallVolumeGet = 0;
            vmHzcallVolumeGet = 0;
            javaLangStringVmCarCallVolume = null;
        }
        if ((dirtyFlags & 52) != 0) {
            VolumeViewModel volumeViewModel = vm;
            SeekBarBindingAdapter.setProgress(this.audioSeekbar, vmHzMediaVolumeGet);
            VolumeViewModel.setOnSeekBarChangeListener(this.audioSeekbar, vmHzMediaVolume);
            TextViewBindingAdapter.setText(this.audioSeekbarRightText, javaLangStringVmHzMediaVolume);
        }
        if ((dirtyFlags & 56) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmHzcallVolumeGet);
            VolumeViewModel.setHzCallSeekBarChangeListener(this.mboundView3, vmHzcallVolume);
            TextViewBindingAdapter.setText(this.mboundView4, javaLangStringVmHzcallVolume);
        }
        if ((dirtyFlags & 50) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView5, vmCarCallVolumeGet);
            VolumeViewModel.setCarCallSeekBarChangeListener(this.mboundView5, vmCarCallVolume);
            TextViewBindingAdapter.setText(this.mboundView6, javaLangStringVmCarCallVolume);
        }
        if ((dirtyFlags & 49) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView7, vmCarNaviVolumeGet);
            VolumeViewModel.setCarNaviSeekBarChangeListener(this.mboundView7, vmCarNaviVolume);
            TextViewBindingAdapter.setText(this.mboundView8, javaLangStringVmCarNaviVolume);
        }
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public class ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl extends ActivityAudiMib3SoundAndroidBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final SeekBar mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 5);
        sparseIntArray.put(R.id.title_divider, 6);
        sparseIntArray.put(R.id.sv_sound, 7);
        sparseIntArray.put(R.id.linearLayout4, 8);
        sparseIntArray.put(R.id.hzMediaLinearLayout, 9);
        sparseIntArray.put(R.id.audio_seekbar_title, 10);
        sparseIntArray.put(R.id.sound_icon, 11);
        sparseIntArray.put(R.id.hzCallLinearLayout, 12);
        sparseIntArray.put(R.id.sound_tv, 13);
        sparseIntArray.put(R.id.sound_set_iv, 14);
        sparseIntArray.put(R.id.v_divider, 15);
    }

    public ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[2], bindings[10], bindings[12], bindings[9], bindings[8], bindings[11], bindings[14], bindings[13], bindings[4], bindings[7], bindings[5], bindings[6], bindings[15]);
        this.mDirtyFlags = -1;
        this.audioSeekbar.setTag((Object) null);
        this.audioSeekbarRightText.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        SeekBar seekBar = bindings[3];
        this.mboundView3 = seekBar;
        seekBar.setTag((Object) null);
        this.soundVolumTv.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (26 != variableId) {
            return false;
        }
        setVm((AudiMib3VolumeViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3VolumeViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmHzcallVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmHzMediaVolume((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmHzcallVolume(ObservableInt VmHzcallVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmHzMediaVolume(ObservableInt VmHzMediaVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        int vmHzMediaVolumeGet = 0;
        ObservableInt vmHzcallVolume = null;
        String javaLangStringVmHzcallVolume = null;
        ObservableInt vmHzMediaVolume = null;
        AudiMib3VolumeViewModel vm = this.mVm;
        String javaLangStringVmHzMediaVolume = null;
        int vmHzcallVolumeGet = 0;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmHzcallVolume = vm.hzcallVolume;
                }
                updateRegistration(0, (Observable) vmHzcallVolume);
                if (vmHzcallVolume != null) {
                    vmHzcallVolumeGet = vmHzcallVolume.get();
                }
                javaLangStringVmHzcallVolume = "" + vmHzcallVolumeGet;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    vmHzMediaVolume = vm.hzMediaVolume;
                }
                updateRegistration(1, (Observable) vmHzMediaVolume);
                if (vmHzMediaVolume != null) {
                    vmHzMediaVolumeGet = vmHzMediaVolume.get();
                }
                javaLangStringVmHzMediaVolume = "" + vmHzMediaVolumeGet;
            }
        }
        if ((dirtyFlags & 14) != 0) {
            SeekBarBindingAdapter.setProgress(this.audioSeekbar, vmHzMediaVolumeGet);
            AudiMib3VolumeViewModel.setOnSeekBarChangeListener(this.audioSeekbar, vmHzMediaVolume);
            TextViewBindingAdapter.setText(this.audioSeekbarRightText, javaLangStringVmHzMediaVolume);
        }
        if ((13 & dirtyFlags) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmHzcallVolumeGet);
            AudiMib3VolumeViewModel.setHzCallSeekBarChangeListener(this.mboundView3, vmHzcallVolume);
            TextViewBindingAdapter.setText(this.soundVolumTv, javaLangStringVmHzcallVolume);
        }
    }
}

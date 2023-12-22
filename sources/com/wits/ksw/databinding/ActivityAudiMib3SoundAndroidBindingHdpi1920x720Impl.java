package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3VolumeViewModel;

/* loaded from: classes7.dex */
public class ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl extends ActivityAudiMib3SoundAndroidBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final SeekBar mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 5);
        sparseIntArray.put(C0899R.C0901id.title_divider, 6);
        sparseIntArray.put(C0899R.C0901id.sv_sound, 7);
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 8);
        sparseIntArray.put(C0899R.C0901id.hzMediaLinearLayout, 9);
        sparseIntArray.put(C0899R.C0901id.audio_seekbar_title, 10);
        sparseIntArray.put(C0899R.C0901id.sound_icon, 11);
        sparseIntArray.put(C0899R.C0901id.hzCallLinearLayout, 12);
        sparseIntArray.put(C0899R.C0901id.sound_tv, 13);
        sparseIntArray.put(C0899R.C0901id.sound_set_iv, 14);
        sparseIntArray.put(C0899R.C0901id.v_divider, 15);
    }

    public ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (SeekBar) bindings[1], (TextView) bindings[2], (TextView) bindings[10], (View) bindings[12], (View) bindings[9], (ConstraintLayout) bindings[8], (ImageView) bindings[11], (ImageView) bindings[14], (TextView) bindings[13], (TextView) bindings[4], (ScrollView) bindings[7], (AppCompatTextView) bindings[5], (View) bindings[6], (View) bindings[15]);
        this.mDirtyFlags = -1L;
        this.audioSeekbar.setTag(null);
        this.audioSeekbarRightText.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        SeekBar seekBar = (SeekBar) bindings[3];
        this.mboundView3 = seekBar;
        seekBar.setTag(null);
        this.soundVolumTv.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (26 == variableId) {
            setVm((AudiMib3VolumeViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityAudiMib3SoundAndroidBinding
    public void setVm(AudiMib3VolumeViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmHzMediaVolume(ObservableInt VmHzMediaVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
                updateRegistration(0, vmHzcallVolume);
                if (vmHzcallVolume != null) {
                    vmHzcallVolumeGet = vmHzcallVolume.get();
                }
                javaLangStringVmHzcallVolume = "" + vmHzcallVolumeGet;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    vmHzMediaVolume = vm.hzMediaVolume;
                }
                updateRegistration(1, vmHzMediaVolume);
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

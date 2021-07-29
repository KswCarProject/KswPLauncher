package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.VolumeViewModel;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public class ActivityAudiSoundBindingImpl extends ActivityAudiSoundBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final SeekBar mboundView3;
    private final TextView mboundView4;
    private final SeekBar mboundView5;
    private final TextView mboundView6;
    private final SeekBar mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.hzTextView, 9);
        sparseIntArray.put(R.id.hzMediaLinearLayout, 10);
        sparseIntArray.put(R.id.audio_seekbar_title, 11);
        sparseIntArray.put(R.id.hzCallLinearLayout, 12);
        sparseIntArray.put(R.id.carVolumeTextView, 13);
        sparseIntArray.put(R.id.carCallLinearLayout, 14);
        sparseIntArray.put(R.id.carNaviLinearLayout, 15);
    }

    public ActivityAudiSoundBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityAudiSoundBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[1], bindings[2], bindings[11], bindings[14], bindings[15], bindings[13], bindings[12], bindings[10], bindings[9], bindings[0]);
        this.mDirtyFlags = -1;
        this.audioSeekbar.setTag((Object) null);
        this.audioSeekbarRightText.setTag((Object) null);
        this.linearLayout4.setTag((Object) null);
        SeekBar seekBar = bindings[3];
        this.mboundView3 = seekBar;
        seekBar.setTag((Object) null);
        TextView textView = bindings[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
        SeekBar seekBar2 = bindings[5];
        this.mboundView5 = seekBar2;
        seekBar2.setTag((Object) null);
        TextView textView2 = bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag((Object) null);
        SeekBar seekBar3 = bindings[7];
        this.mboundView7 = seekBar3;
        seekBar3.setTag((Object) null);
        TextView textView3 = bindings[8];
        this.mboundView8 = textView3;
        textView3.setTag((Object) null);
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

    public boolean setVariable(int variableId, Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((VolumeViewModel) variable);
        return true;
    }

    public void setVm(VolumeViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmCarCallVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmHzcallVolume((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmHzMediaVolume((ObservableInt) object, fieldId);
            case 3:
                return onChangeVmCarNaviVolume((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarCallVolume(ObservableInt VmCarCallVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmHzcallVolume(ObservableInt VmHzcallVolume, int fieldId) {
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

    private boolean onChangeVmCarNaviVolume(ObservableInt VmCarNaviVolume, int fieldId) {
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
        int vmHzcallVolumeGet;
        ObservableInt vmCarNaviVolume;
        int vmHzMediaVolumeGet;
        int vmHzcallVolumeGet2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int vmHzMediaVolumeGet2 = 0;
        ObservableInt vmCarCallVolume = null;
        ObservableInt vmHzcallVolume = null;
        String javaLangStringVmCarCallVolume = null;
        VolumeViewModel vm = this.mVm;
        int vmCarNaviVolumeGet = 0;
        String javaLangStringVmCarNaviVolume = null;
        String javaLangStringVmHzMediaVolume = null;
        int vmCarCallVolumeGet = 0;
        String javaLangStringVmHzcallVolume = null;
        ObservableInt vmHzMediaVolume = null;
        int vmHzcallVolumeGet3 = 0;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (vm != null) {
                    vmCarCallVolume = vm.carCallVolume;
                }
                updateRegistration(0, (Observable) vmCarCallVolume);
                if (vmCarCallVolume != null) {
                    vmCarCallVolumeGet = vmCarCallVolume.get();
                }
                javaLangStringVmCarCallVolume = "" + vmCarCallVolumeGet;
            }
            if ((dirtyFlags & 50) != 0) {
                if (vm != null) {
                    vmHzcallVolume = vm.hzcallVolume;
                }
                updateRegistration(1, (Observable) vmHzcallVolume);
                if (vmHzcallVolume != null) {
                    vmHzcallVolumeGet2 = vmHzcallVolume.get();
                } else {
                    vmHzcallVolumeGet2 = 0;
                }
                vmHzMediaVolumeGet = 0;
                javaLangStringVmHzcallVolume = "" + vmHzcallVolumeGet2;
                vmHzcallVolumeGet3 = vmHzcallVolumeGet2;
            } else {
                vmHzMediaVolumeGet = 0;
            }
            if ((dirtyFlags & 52) != 0) {
                if (vm != null) {
                    vmHzMediaVolume = vm.hzMediaVolume;
                }
                updateRegistration(2, (Observable) vmHzMediaVolume);
                if (vmHzMediaVolume != null) {
                    vmHzMediaVolumeGet2 = vmHzMediaVolume.get();
                } else {
                    vmHzMediaVolumeGet2 = vmHzMediaVolumeGet;
                }
                javaLangStringVmHzMediaVolume = "" + vmHzMediaVolumeGet2;
            } else {
                vmHzMediaVolumeGet2 = vmHzMediaVolumeGet;
            }
            if ((dirtyFlags & 56) != 0) {
                if (vm != null) {
                    vmCarNaviVolume = vm.carNaviVolume;
                } else {
                    vmCarNaviVolume = null;
                }
                updateRegistration(3, (Observable) vmCarNaviVolume);
                if (vmCarNaviVolume != null) {
                    vmCarNaviVolumeGet = vmCarNaviVolume.get();
                }
                javaLangStringVmCarNaviVolume = "" + vmCarNaviVolumeGet;
                vmHzcallVolumeGet = vmHzcallVolumeGet3;
                vmHzMediaVolumeGet2 = vmHzMediaVolumeGet2;
            } else {
                vmCarNaviVolume = null;
                vmHzcallVolumeGet = vmHzcallVolumeGet3;
            }
        } else {
            vmCarNaviVolume = null;
            vmHzcallVolumeGet = 0;
        }
        if ((dirtyFlags & 52) != 0) {
            VolumeViewModel volumeViewModel = vm;
            SeekBarBindingAdapter.setProgress(this.audioSeekbar, vmHzMediaVolumeGet2);
            AudiMib3VolumeViewModel.setOnSeekBarChangeListener(this.audioSeekbar, vmHzMediaVolume);
            TextViewBindingAdapter.setText(this.audioSeekbarRightText, javaLangStringVmHzMediaVolume);
        }
        if ((dirtyFlags & 50) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmHzcallVolumeGet);
            AudiMib3VolumeViewModel.setHzCallSeekBarChangeListener(this.mboundView3, vmHzcallVolume);
            TextViewBindingAdapter.setText(this.mboundView4, javaLangStringVmHzcallVolume);
        }
        if ((dirtyFlags & 49) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView5, vmCarCallVolumeGet);
            AudiMib3VolumeViewModel.setCarCallSeekBarChangeListener(this.mboundView5, vmCarCallVolume);
            TextViewBindingAdapter.setText(this.mboundView6, javaLangStringVmCarCallVolume);
        }
        if ((dirtyFlags & 56) != 0) {
            SeekBarBindingAdapter.setProgress(this.mboundView7, vmCarNaviVolumeGet);
            AudiMib3VolumeViewModel.setCarNaviSeekBarChangeListener(this.mboundView7, vmCarNaviVolume);
            TextViewBindingAdapter.setText(this.mboundView8, javaLangStringVmCarNaviVolume);
        }
    }
}

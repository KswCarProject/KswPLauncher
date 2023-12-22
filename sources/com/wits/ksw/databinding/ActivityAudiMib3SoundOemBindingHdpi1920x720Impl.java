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
public class ActivityAudiMib3SoundOemBindingHdpi1920x720Impl extends ActivityAudiMib3SoundOemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final SeekBar mboundView1;
    private final SeekBar mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 5);
        sparseIntArray.put(C0899R.C0901id.title_divider, 6);
        sparseIntArray.put(C0899R.C0901id.sv_sound, 7);
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 8);
        sparseIntArray.put(C0899R.C0901id.carCallLinearLayout, 9);
        sparseIntArray.put(C0899R.C0901id.voic_tv, 10);
        sparseIntArray.put(C0899R.C0901id.voic_iv, 11);
        sparseIntArray.put(C0899R.C0901id.carNaviLinearLayout, 12);
        sparseIntArray.put(C0899R.C0901id.voic_5_tv, 13);
        sparseIntArray.put(C0899R.C0901id.voic_5_iv, 14);
        sparseIntArray.put(C0899R.C0901id.v_divider, 15);
    }

    public ActivityAudiMib3SoundOemBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityAudiMib3SoundOemBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (View) bindings[9], (TextView) bindings[2], (TextView) bindings[4], (View) bindings[12], (ConstraintLayout) bindings[8], (ScrollView) bindings[7], (AppCompatTextView) bindings[5], (View) bindings[6], (View) bindings[15], (ImageView) bindings[14], (TextView) bindings[13], (ImageView) bindings[11], (TextView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.carCallTv.setTag(null);
        this.carNaviIv.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        SeekBar seekBar = (SeekBar) bindings[1];
        this.mboundView1 = seekBar;
        seekBar.setTag(null);
        SeekBar seekBar2 = (SeekBar) bindings[3];
        this.mboundView3 = seekBar2;
        seekBar2.setTag(null);
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

    @Override // com.wits.ksw.databinding.ActivityAudiMib3SoundOemBinding
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
                return onChangeVmCarCallVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmCarNaviVolume((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarCallVolume(ObservableInt VmCarCallVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmCarNaviVolume(ObservableInt VmCarNaviVolume, int fieldId) {
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
        ObservableInt vmCarCallVolume = null;
        int vmCarCallVolumeGet = 0;
        ObservableInt vmCarNaviVolume = null;
        String javaLangStringVmCarCallVolume = null;
        AudiMib3VolumeViewModel vm = this.mVm;
        int vmCarNaviVolumeGet = 0;
        String javaLangStringVmCarNaviVolume = null;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmCarCallVolume = vm.carCallVolume;
                }
                updateRegistration(0, vmCarCallVolume);
                if (vmCarCallVolume != null) {
                    vmCarCallVolumeGet = vmCarCallVolume.get();
                }
                javaLangStringVmCarCallVolume = "" + vmCarCallVolumeGet;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    vmCarNaviVolume = vm.carNaviVolume;
                }
                updateRegistration(1, vmCarNaviVolume);
                if (vmCarNaviVolume != null) {
                    vmCarNaviVolumeGet = vmCarNaviVolume.get();
                }
                javaLangStringVmCarNaviVolume = "" + vmCarNaviVolumeGet;
            }
        }
        if ((13 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.carCallTv, javaLangStringVmCarCallVolume);
            SeekBarBindingAdapter.setProgress(this.mboundView1, vmCarCallVolumeGet);
            AudiMib3VolumeViewModel.setCarCallSeekBarChangeListener(this.mboundView1, vmCarCallVolume);
        }
        if ((dirtyFlags & 14) != 0) {
            TextViewBindingAdapter.setText(this.carNaviIv, javaLangStringVmCarNaviVolume);
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmCarNaviVolumeGet);
            AudiMib3VolumeViewModel.setCarNaviSeekBarChangeListener(this.mboundView3, vmCarNaviVolume);
        }
    }
}

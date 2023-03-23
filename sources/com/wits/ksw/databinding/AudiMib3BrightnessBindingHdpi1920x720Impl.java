package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public class AudiMib3BrightnessBindingHdpi1920x720Impl extends AudiMib3BrightnessBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final SeekBar mboundView3;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 6);
        sparseIntArray.put(R.id.title_divider, 7);
        sparseIntArray.put(R.id.linearLayout4, 8);
        sparseIntArray.put(R.id.hzMediaLinearLayout, 9);
        sparseIntArray.put(R.id.audio_seekbar_title, 10);
        sparseIntArray.put(R.id.hzCallLinearLayout, 11);
        sparseIntArray.put(R.id.v_divider, 12);
    }

    public AudiMib3BrightnessBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiMib3BrightnessBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[5], bindings[1], bindings[2], bindings[10], bindings[11], bindings[9], bindings[8], bindings[6], bindings[7], bindings[12]);
        this.mDirtyFlags = -1;
        this.audiSystemReverCamera.setTag((Object) null);
        this.audioSeekbar.setTag((Object) null);
        this.audioSeekbarRightText.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        SeekBar seekBar = bindings[3];
        this.mboundView3 = seekBar;
        seekBar.setTag((Object) null);
        TextView textView = bindings[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
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
        if (26 != variableId) {
            return false;
        }
        setVm((AudiMib3SystemViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3SystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmDayBrightnessStr((ObservableField) object, fieldId);
            case 1:
                return onChangeVmDayBrightness((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmAutoBrightness((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmDayBrightnessStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmDayBrightness(ObservableInt VmDayBrightness, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmAutoBrightness(ObservableBoolean VmAutoBrightness, int fieldId) {
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<String> vmDayBrightnessStr = null;
        String vmDayBrightnessStrGet = null;
        boolean vmAutoBrightnessGet = false;
        ObservableInt vmDayBrightness = null;
        ObservableBoolean vmAutoBrightness = null;
        AudiMib3SystemViewModel vm = this.mVm;
        int vmDayBrightnessGet = 0;
        SeekBar.OnSeekBarChangeListener vmDayBrightnessChangeListener = null;
        CompoundButton.OnCheckedChangeListener vmOnAudoBrightnessChangeListener = null;
        if ((31 & dirtyFlags) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (vm != null) {
                    vmDayBrightnessStr = vm.dayBrightnessStr;
                }
                updateRegistration(0, (Observable) vmDayBrightnessStr);
                if (vmDayBrightnessStr != null) {
                    vmDayBrightnessStrGet = vmDayBrightnessStr.get();
                }
            }
            if ((dirtyFlags & 26) != 0) {
                if (vm != null) {
                    vmDayBrightness = vm.dayBrightness;
                }
                updateRegistration(1, (Observable) vmDayBrightness);
                if (vmDayBrightness != null) {
                    vmDayBrightnessGet = vmDayBrightness.get();
                }
            }
            if ((dirtyFlags & 28) != 0) {
                if (vm != null) {
                    vmAutoBrightness = vm.autoBrightness;
                }
                updateRegistration(2, (Observable) vmAutoBrightness);
                if (vmAutoBrightness != null) {
                    vmAutoBrightnessGet = vmAutoBrightness.get();
                }
            }
            if (!((dirtyFlags & 24) == 0 || vm == null)) {
                vmDayBrightnessChangeListener = vm.dayBrightnessChangeListener;
                vmOnAudoBrightnessChangeListener = vm.onAudoBrightnessChangeListener;
            }
        }
        if ((dirtyFlags & 28) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemReverCamera, vmAutoBrightnessGet);
        }
        if ((dirtyFlags & 24) != 0) {
            this.audiSystemReverCamera.setOnCheckedChangeListener(vmOnAudoBrightnessChangeListener);
            this.audioSeekbar.setOnSeekBarChangeListener(vmDayBrightnessChangeListener);
            this.mboundView3.setOnSeekBarChangeListener(vmDayBrightnessChangeListener);
        }
        if ((dirtyFlags & 26) != 0) {
            SeekBarBindingAdapter.setProgress(this.audioSeekbar, vmDayBrightnessGet);
            SeekBarBindingAdapter.setProgress(this.mboundView3, vmDayBrightnessGet);
        }
        if ((dirtyFlags & 25) != 0) {
            TextViewBindingAdapter.setText(this.audioSeekbarRightText, vmDayBrightnessStrGet);
            TextViewBindingAdapter.setText(this.mboundView4, vmDayBrightnessStrGet);
        }
    }
}

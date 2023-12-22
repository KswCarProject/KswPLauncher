package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public class AudiMib3BrightnessBindingImpl extends AudiMib3BrightnessBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final SeekBar mboundView3;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 6);
        sparseIntArray.put(C0899R.C0901id.title_divider, 7);
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 8);
        sparseIntArray.put(C0899R.C0901id.hzMediaLinearLayout, 9);
        sparseIntArray.put(C0899R.C0901id.audio_seekbar_title, 10);
        sparseIntArray.put(C0899R.C0901id.hzCallLinearLayout, 11);
        sparseIntArray.put(C0899R.C0901id.v_divider, 12);
    }

    public AudiMib3BrightnessBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private AudiMib3BrightnessBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (CheckBox) bindings[5], (SeekBar) bindings[1], (TextView) bindings[2], (TextView) bindings[10], (LinearLayout) bindings[11], (LinearLayout) bindings[9], (AudiConstraintLayout) bindings[8], (AppCompatTextView) bindings[6], (View) bindings[7], (View) bindings[12]);
        this.mDirtyFlags = -1L;
        this.audiSystemReverCamera.setTag(null);
        this.audioSeekbar.setTag(null);
        this.audioSeekbarRightText.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        SeekBar seekBar = (SeekBar) bindings[3];
        this.mboundView3 = seekBar;
        seekBar.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setVm((AudiMib3SystemViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3BrightnessBinding
    public void setVm(AudiMib3SystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeVmDayBrightnessStr(ObservableField<String> VmDayBrightnessStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmDayBrightness(ObservableInt VmDayBrightness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmAutoBrightness(ObservableBoolean VmAutoBrightness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
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
                updateRegistration(0, vmDayBrightnessStr);
                if (vmDayBrightnessStr != null) {
                    vmDayBrightnessStrGet = vmDayBrightnessStr.get();
                }
            }
            if ((dirtyFlags & 26) != 0) {
                if (vm != null) {
                    vmDayBrightness = vm.dayBrightness;
                }
                updateRegistration(1, vmDayBrightness);
                if (vmDayBrightness != null) {
                    vmDayBrightnessGet = vmDayBrightness.get();
                }
            }
            if ((dirtyFlags & 28) != 0) {
                if (vm != null) {
                    vmAutoBrightness = vm.autoBrightness;
                }
                updateRegistration(2, vmAutoBrightness);
                if (vmAutoBrightness != null) {
                    vmAutoBrightnessGet = vmAutoBrightness.get();
                }
            }
            if ((dirtyFlags & 24) != 0 && vm != null) {
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

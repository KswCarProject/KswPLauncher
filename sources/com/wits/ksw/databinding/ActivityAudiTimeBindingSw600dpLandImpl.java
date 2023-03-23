package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;

public class ActivityAudiTimeBindingSw600dpLandImpl extends ActivityAudiTimeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RadioButton mboundView2;
    private final RadioButton mboundView3;
    private final RadioGroup mboundView4;
    private final RadioButton mboundView5;
    private final RadioButton mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.audi_sync_time, 7);
        sparseIntArray.put(R.id.audi_time_zhishi, 8);
    }

    public ActivityAudiTimeBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityAudiTimeBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[7], bindings[8], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.linearLayout4.setTag((Object) null);
        RadioButton radioButton = bindings[2];
        this.mboundView2 = radioButton;
        radioButton.setTag((Object) null);
        RadioButton radioButton2 = bindings[3];
        this.mboundView3 = radioButton2;
        radioButton2.setTag((Object) null);
        RadioGroup radioGroup = bindings[4];
        this.mboundView4 = radioGroup;
        radioGroup.setTag((Object) null);
        RadioButton radioButton3 = bindings[5];
        this.mboundView5 = radioButton3;
        radioButton3.setTag((Object) null);
        RadioButton radioButton4 = bindings[6];
        this.mboundView6 = radioButton4;
        radioButton4.setTag((Object) null);
        this.timeRadioGroup.setTag((Object) null);
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
        setVm((AudiSettingViewModel) variable);
        return true;
    }

    public void setVm(AudiSettingViewModel Vm) {
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
                return onChangeVmIsCarTime((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVmIs24Hour((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIsCarTime(ObservableBoolean VmIsCarTime, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmIs24Hour(ObservableBoolean VmIs24Hour, int fieldId) {
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
        RadioGroup.OnCheckedChangeListener vmOnTimeMoedlChangeListener = null;
        ObservableBoolean vmIsCarTime = null;
        boolean VmIsCarTime1 = false;
        boolean vmIs24HourGet = false;
        AudiSettingViewModel vm = this.mVm;
        boolean vmIs24Hour = false;
        RadioGroup.OnCheckedChangeListener vmOn24HourChangeListener = null;
        boolean vmIsCarTimeGet = false;
        ObservableBoolean VmIs24Hour1 = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((dirtyFlags & 12) == 0 || vm == null)) {
                vmOnTimeMoedlChangeListener = vm.onTimeMoedlChangeListener;
                vmOn24HourChangeListener = vm.on24HourChangeListener;
            }
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmIsCarTime = vm.isCarTime;
                }
                updateRegistration(0, (Observable) vmIsCarTime);
                if (vmIsCarTime != null) {
                    vmIsCarTimeGet = vmIsCarTime.get();
                }
                VmIsCarTime1 = !vmIsCarTimeGet;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    VmIs24Hour1 = vm.is24Hour;
                }
                updateRegistration(1, (Observable) VmIs24Hour1);
                if (VmIs24Hour1 != null) {
                    vmIs24HourGet = VmIs24Hour1.get();
                }
                vmIs24Hour = !vmIs24HourGet;
            }
        }
        if ((dirtyFlags & 13) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.mboundView2, VmIsCarTime1);
            CompoundButtonBindingAdapter.setChecked(this.mboundView3, vmIsCarTimeGet);
        }
        if ((dirtyFlags & 12) != 0) {
            this.mboundView4.setOnCheckedChangeListener(vmOn24HourChangeListener);
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnTimeMoedlChangeListener);
        }
        if ((dirtyFlags & 14) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.mboundView5, vmIs24Hour);
            CompoundButtonBindingAdapter.setChecked(this.mboundView6, vmIs24HourGet);
        }
    }
}

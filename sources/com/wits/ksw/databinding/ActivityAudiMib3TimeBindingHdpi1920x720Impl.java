package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes7.dex */
public class ActivityAudiMib3TimeBindingHdpi1920x720Impl extends ActivityAudiMib3TimeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final RadioGroup mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.title, 7);
        sparseIntArray.put(C0899R.C0901id.title_divider, 8);
        sparseIntArray.put(C0899R.C0901id.sv_time, 9);
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 10);
        sparseIntArray.put(C0899R.C0901id.audi_sync_time, 11);
        sparseIntArray.put(C0899R.C0901id.audi_time_zhishi, 12);
        sparseIntArray.put(C0899R.C0901id.v_divider, 13);
    }

    public ActivityAudiMib3TimeBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityAudiMib3TimeBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (RadioButton) bindings[5], (RadioButton) bindings[6], (RadioButton) bindings[2], (RadioButton) bindings[3], (TextView) bindings[11], (TextView) bindings[12], (AudiConstraintLayout) bindings[10], (ScrollView) bindings[9], (RadioGroup) bindings[1], (AppCompatTextView) bindings[7], (View) bindings[8], (View) bindings[13]);
        this.mDirtyFlags = -1L;
        this.audiMib312Time.setTag(null);
        this.audiMib324Time.setTag(null);
        this.audiMib3AndroidTime.setTag(null);
        this.audiMib3CarTime.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        RadioGroup radioGroup = (RadioGroup) bindings[4];
        this.mboundView4 = radioGroup;
        radioGroup.setTag(null);
        this.timeRadioGroup.setTag(null);
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
            setVm((AudiMib3SettingViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityAudiMib3TimeBinding
    public void setVm(AudiMib3SettingViewModel Vm) {
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
                return onChangeVmIsCarTime((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVmIs24Hour((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIsCarTime(ObservableBoolean VmIsCarTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmIs24Hour(ObservableBoolean VmIs24Hour, int fieldId) {
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
        RadioGroup.OnCheckedChangeListener vmOnTimeMoedlChangeListener = null;
        ObservableBoolean vmIsCarTime = null;
        boolean VmIsCarTime1 = false;
        boolean vmIs24HourGet = false;
        AudiMib3SettingViewModel vm = this.mVm;
        boolean vmIs24Hour = false;
        RadioGroup.OnCheckedChangeListener vmOn24HourChangeListener = null;
        boolean vmIsCarTimeGet = false;
        ObservableBoolean VmIs24Hour1 = null;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 12) != 0 && vm != null) {
                vmOnTimeMoedlChangeListener = vm.onTimeMoedlChangeListener;
                vmOn24HourChangeListener = vm.on24HourChangeListener;
            }
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmIsCarTime = vm.isCarTime;
                }
                updateRegistration(0, vmIsCarTime);
                if (vmIsCarTime != null) {
                    vmIsCarTimeGet = vmIsCarTime.get();
                }
                VmIsCarTime1 = !vmIsCarTimeGet;
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    VmIs24Hour1 = vm.is24Hour;
                }
                updateRegistration(1, VmIs24Hour1);
                if (VmIs24Hour1 != null) {
                    vmIs24HourGet = VmIs24Hour1.get();
                }
                vmIs24Hour = !vmIs24HourGet;
            }
        }
        if ((dirtyFlags & 14) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiMib312Time, vmIs24Hour);
            CompoundButtonBindingAdapter.setChecked(this.audiMib324Time, vmIs24HourGet);
        }
        if ((dirtyFlags & 13) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiMib3AndroidTime, VmIsCarTime1);
            CompoundButtonBindingAdapter.setChecked(this.audiMib3CarTime, vmIsCarTimeGet);
        }
        if ((dirtyFlags & 12) != 0) {
            this.mboundView4.setOnCheckedChangeListener(vmOn24HourChangeListener);
            this.timeRadioGroup.setOnCheckedChangeListener(vmOnTimeMoedlChangeListener);
        }
    }
}

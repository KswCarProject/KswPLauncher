package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;

public class ActivityAudiTimeBindingImpl extends ActivityAudiTimeBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RadioButton mboundView2;
    @NonNull
    private final RadioButton mboundView3;
    @NonNull
    private final RadioGroup mboundView4;
    @NonNull
    private final RadioButton mboundView5;
    @NonNull
    private final RadioButton mboundView6;

    static {
        sViewsWithIds.put(R.id.audi_sync_time, 7);
        sViewsWithIds.put(R.id.audi_time_zhishi, 8);
    }

    public ActivityAudiTimeBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityAudiTimeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[7], bindings[8], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.linearLayout4.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (9 != variableId) {
            return false;
        }
        setVm((AudiSettingViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiSettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(9);
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
        AudiSettingViewModel vm = this.mVm;
        RadioGroup.OnCheckedChangeListener vmOnTimeMoedlChangeListener = null;
        ObservableBoolean vmIsCarTime = null;
        boolean vmIs24Hour = false;
        boolean vmIsCarTimeGet = false;
        boolean VmIsCarTime1 = false;
        boolean vmIs24HourGet = false;
        ObservableBoolean VmIs24Hour1 = null;
        RadioGroup.OnCheckedChangeListener vmOn24HourChangeListener = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((dirtyFlags & 12) == 0 || vm == null)) {
                vmOnTimeMoedlChangeListener = vm.onTimeMoedlChangeListener;
                vmOn24HourChangeListener = vm.on24HourChangeListener;
            }
            boolean z = true;
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
                if (vmIs24HourGet) {
                    z = false;
                }
                vmIs24Hour = z;
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

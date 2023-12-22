package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.p004v7.widget.AppCompatTextView;
import android.support.p004v7.widget.LinearLayoutCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes7.dex */
public class AudiMib3PasswordBindingHdpi1920x720Impl extends AudiMib3PasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback140;
    private final View.OnClickListener mCallback141;
    private final View.OnClickListener mCallback142;
    private final View.OnClickListener mCallback143;
    private final View.OnClickListener mCallback144;
    private final View.OnClickListener mCallback145;
    private final View.OnClickListener mCallback146;
    private final View.OnClickListener mCallback147;
    private final View.OnClickListener mCallback148;
    private final View.OnClickListener mCallback149;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnDeleteClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
    private final RelativeLayout mboundView0;
    private final AppCompatTextView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.linearLayout4, 13);
        sparseIntArray.put(C0899R.C0901id.audioViewPager, 14);
        sparseIntArray.put(C0899R.C0901id.audi_key_ok, 15);
    }

    public AudiMib3PasswordBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiMib3PasswordBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[12], (ImageView) bindings[3], (ImageView) bindings[4], (ImageView) bindings[5], (ImageView) bindings[6], (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[9], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[2], (ImageView) bindings[15], (GridLayout) bindings[14], (LinearLayoutCompat) bindings[13]);
        this.mDirtyFlags = -1L;
        this.audiKey0.setTag(null);
        this.audiKey1.setTag(null);
        this.audiKey2.setTag(null);
        this.audiKey3.setTag(null);
        this.audiKey4.setTag(null);
        this.audiKey5.setTag(null);
        this.audiKey6.setTag(null);
        this.audiKey7.setTag(null);
        this.audiKey8.setTag(null);
        this.audiKey9.setTag(null);
        this.audiKeyDelete.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        AppCompatTextView appCompatTextView = (AppCompatTextView) bindings[1];
        this.mboundView1 = appCompatTextView;
        appCompatTextView.setTag(null);
        setRootTag(root);
        this.mCallback148 = new OnClickListener(this, 9);
        this.mCallback146 = new OnClickListener(this, 7);
        this.mCallback144 = new OnClickListener(this, 5);
        this.mCallback142 = new OnClickListener(this, 3);
        this.mCallback140 = new OnClickListener(this, 1);
        this.mCallback149 = new OnClickListener(this, 10);
        this.mCallback147 = new OnClickListener(this, 8);
        this.mCallback145 = new OnClickListener(this, 6);
        this.mCallback143 = new OnClickListener(this, 4);
        this.mCallback141 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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

    @Override // com.wits.ksw.databinding.AudiMib3PasswordBinding
    public void setVm(AudiMib3SettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmKeyBuffer((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmKeyBuffer(ObservableField<String> VmKeyBuffer, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
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
        String vmKeyBufferGet = null;
        AudiMib3SettingViewModel vm = this.mVm;
        View.OnLongClickListener vmOnDeleteLongClickAndroidViewViewOnLongClickListener = null;
        ObservableField<String> vmKeyBuffer = null;
        View.OnClickListener vmOnDeleteClickAndroidViewViewOnClickListener = null;
        if ((dirtyFlags & 7) != 0) {
            if ((dirtyFlags & 6) != 0 && vm != null) {
                OnLongClickListenerImpl onLongClickListenerImpl = this.mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
                if (onLongClickListenerImpl == null) {
                    onLongClickListenerImpl = new OnLongClickListenerImpl();
                    this.mVmOnDeleteLongClickAndroidViewViewOnLongClickListener = onLongClickListenerImpl;
                }
                vmOnDeleteLongClickAndroidViewViewOnLongClickListener = onLongClickListenerImpl.setValue(vm);
                OnClickListenerImpl onClickListenerImpl = this.mVmOnDeleteClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVmOnDeleteClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                vmOnDeleteClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vm);
            }
            if (vm != null) {
                vmKeyBuffer = vm.keyBuffer;
            }
            updateRegistration(0, vmKeyBuffer);
            if (vmKeyBuffer != null) {
                vmKeyBufferGet = vmKeyBuffer.get();
            }
        }
        if ((4 & dirtyFlags) != 0) {
            this.audiKey0.setOnClickListener(this.mCallback149);
            this.audiKey1.setOnClickListener(this.mCallback140);
            this.audiKey2.setOnClickListener(this.mCallback141);
            this.audiKey3.setOnClickListener(this.mCallback142);
            this.audiKey4.setOnClickListener(this.mCallback143);
            this.audiKey5.setOnClickListener(this.mCallback144);
            this.audiKey6.setOnClickListener(this.mCallback145);
            this.audiKey7.setOnClickListener(this.mCallback146);
            this.audiKey8.setOnClickListener(this.mCallback147);
            this.audiKey9.setOnClickListener(this.mCallback148);
        }
        if ((dirtyFlags & 6) != 0) {
            this.audiKeyDelete.setOnClickListener(vmOnDeleteClickAndroidViewViewOnClickListener);
            this.audiKeyDelete.setOnLongClickListener(vmOnDeleteLongClickAndroidViewViewOnLongClickListener);
        }
        if ((7 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, vmKeyBufferGet);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudiMib3SettingViewModel value;

        public OnLongClickListenerImpl setValue(AudiMib3SettingViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View arg0) {
            return this.value.onDeleteLongClick(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudiMib3SettingViewModel value;

        public OnClickListenerImpl setValue(AudiMib3SettingViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onDeleteClick(arg0);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                AudiMib3SettingViewModel vm = this.mVm;
                if (vm != null) {
                    vm.onKeyClick(callbackArg_0, 1);
                    return;
                }
                return;
            case 2:
                AudiMib3SettingViewModel vm2 = this.mVm;
                vmJavaLangObjectNull = vm2 != null;
                if (vmJavaLangObjectNull) {
                    vm2.onKeyClick(callbackArg_0, 2);
                    return;
                }
                return;
            case 3:
                AudiMib3SettingViewModel vm3 = this.mVm;
                vmJavaLangObjectNull = vm3 != null;
                if (vmJavaLangObjectNull) {
                    vm3.onKeyClick(callbackArg_0, 3);
                    return;
                }
                return;
            case 4:
                AudiMib3SettingViewModel vm4 = this.mVm;
                vmJavaLangObjectNull = vm4 != null;
                if (vmJavaLangObjectNull) {
                    vm4.onKeyClick(callbackArg_0, 4);
                    return;
                }
                return;
            case 5:
                AudiMib3SettingViewModel vm5 = this.mVm;
                vmJavaLangObjectNull = vm5 != null;
                if (vmJavaLangObjectNull) {
                    vm5.onKeyClick(callbackArg_0, 5);
                    return;
                }
                return;
            case 6:
                AudiMib3SettingViewModel vm6 = this.mVm;
                vmJavaLangObjectNull = vm6 != null;
                if (vmJavaLangObjectNull) {
                    vm6.onKeyClick(callbackArg_0, 6);
                    return;
                }
                return;
            case 7:
                AudiMib3SettingViewModel vm7 = this.mVm;
                vmJavaLangObjectNull = vm7 != null;
                if (vmJavaLangObjectNull) {
                    vm7.onKeyClick(callbackArg_0, 7);
                    return;
                }
                return;
            case 8:
                AudiMib3SettingViewModel vm8 = this.mVm;
                vmJavaLangObjectNull = vm8 != null;
                if (vmJavaLangObjectNull) {
                    vm8.onKeyClick(callbackArg_0, 8);
                    return;
                }
                return;
            case 9:
                AudiMib3SettingViewModel vm9 = this.mVm;
                vmJavaLangObjectNull = vm9 != null;
                if (vmJavaLangObjectNull) {
                    vm9.onKeyClick(callbackArg_0, 9);
                    return;
                }
                return;
            case 10:
                AudiMib3SettingViewModel vm10 = this.mVm;
                vmJavaLangObjectNull = vm10 != null;
                if (vmJavaLangObjectNull) {
                    vm10.onKeyClick(callbackArg_0, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;

public class AudiMib3PasswordBindingImpl extends AudiMib3PasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback10;
    private final View.OnClickListener mCallback11;
    private final View.OnClickListener mCallback12;
    private final View.OnClickListener mCallback3;
    private final View.OnClickListener mCallback4;
    private final View.OnClickListener mCallback5;
    private final View.OnClickListener mCallback6;
    private final View.OnClickListener mCallback7;
    private final View.OnClickListener mCallback8;
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnDeleteClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
    private final RelativeLayout mboundView0;
    private final AppCompatTextView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.linearLayout4, 13);
        sparseIntArray.put(R.id.audioViewPager, 14);
        sparseIntArray.put(R.id.audi_key_ok, 15);
    }

    public AudiMib3PasswordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiMib3PasswordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[12], bindings[3], bindings[4], bindings[5], bindings[6], bindings[7], bindings[8], bindings[9], bindings[10], bindings[11], bindings[2], bindings[15], bindings[14], bindings[13]);
        this.mDirtyFlags = -1;
        this.audiKey0.setTag((Object) null);
        this.audiKey1.setTag((Object) null);
        this.audiKey2.setTag((Object) null);
        this.audiKey3.setTag((Object) null);
        this.audiKey4.setTag((Object) null);
        this.audiKey5.setTag((Object) null);
        this.audiKey6.setTag((Object) null);
        this.audiKey7.setTag((Object) null);
        this.audiKey8.setTag((Object) null);
        this.audiKey9.setTag((Object) null);
        this.audiKeyDelete.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        AppCompatTextView appCompatTextView = bindings[1];
        this.mboundView1 = appCompatTextView;
        appCompatTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback11 = new OnClickListener(this, 9);
        this.mCallback8 = new OnClickListener(this, 6);
        this.mCallback6 = new OnClickListener(this, 4);
        this.mCallback4 = new OnClickListener(this, 2);
        this.mCallback12 = new OnClickListener(this, 10);
        this.mCallback10 = new OnClickListener(this, 8);
        this.mCallback9 = new OnClickListener(this, 7);
        this.mCallback7 = new OnClickListener(this, 5);
        this.mCallback5 = new OnClickListener(this, 3);
        this.mCallback3 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        setVm((AudiMib3SettingViewModel) variable);
        return true;
    }

    public void setVm(AudiMib3SettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmKeyBuffer((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmKeyBuffer(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
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
        String vmKeyBufferGet = null;
        AudiMib3SettingViewModel vm = this.mVm;
        View.OnLongClickListener vmOnDeleteLongClickAndroidViewViewOnLongClickListener = null;
        ObservableField<String> vmKeyBuffer = null;
        View.OnClickListener vmOnDeleteClickAndroidViewViewOnClickListener = null;
        if ((dirtyFlags & 7) != 0) {
            if (!((dirtyFlags & 6) == 0 || vm == null)) {
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
            updateRegistration(0, (Observable) vmKeyBuffer);
            if (vmKeyBuffer != null) {
                vmKeyBufferGet = vmKeyBuffer.get();
            }
        }
        if ((4 & dirtyFlags) != 0) {
            this.audiKey0.setOnClickListener(this.mCallback12);
            this.audiKey1.setOnClickListener(this.mCallback3);
            this.audiKey2.setOnClickListener(this.mCallback4);
            this.audiKey3.setOnClickListener(this.mCallback5);
            this.audiKey4.setOnClickListener(this.mCallback6);
            this.audiKey5.setOnClickListener(this.mCallback7);
            this.audiKey6.setOnClickListener(this.mCallback8);
            this.audiKey7.setOnClickListener(this.mCallback9);
            this.audiKey8.setOnClickListener(this.mCallback10);
            this.audiKey9.setOnClickListener(this.mCallback11);
        }
        if ((dirtyFlags & 6) != 0) {
            this.audiKeyDelete.setOnClickListener(vmOnDeleteClickAndroidViewViewOnClickListener);
            this.audiKeyDelete.setOnLongClickListener(vmOnDeleteLongClickAndroidViewViewOnLongClickListener);
        }
        if ((7 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, vmKeyBufferGet);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudiMib3SettingViewModel value;

        public OnLongClickListenerImpl setValue(AudiMib3SettingViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View arg0) {
            return this.value.onDeleteLongClick(arg0);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudiMib3SettingViewModel value;

        public OnClickListenerImpl setValue(AudiMib3SettingViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onDeleteClick(arg0);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull = true;
        boolean vmJavaLangObjectNull2 = false;
        switch (sourceId) {
            case 1:
                AudiMib3SettingViewModel vm = this.mVm;
                if (vm != null) {
                    vmJavaLangObjectNull2 = true;
                }
                if (vmJavaLangObjectNull2) {
                    vm.onKeyClick(callbackArg_0, 1);
                    return;
                }
                return;
            case 2:
                AudiMib3SettingViewModel vm2 = this.mVm;
                if (vm2 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm2.onKeyClick(callbackArg_0, 2);
                    return;
                }
                return;
            case 3:
                AudiMib3SettingViewModel vm3 = this.mVm;
                if (vm3 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm3.onKeyClick(callbackArg_0, 3);
                    return;
                }
                return;
            case 4:
                AudiMib3SettingViewModel vm4 = this.mVm;
                if (vm4 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm4.onKeyClick(callbackArg_0, 4);
                    return;
                }
                return;
            case 5:
                AudiMib3SettingViewModel vm5 = this.mVm;
                if (vm5 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm5.onKeyClick(callbackArg_0, 5);
                    return;
                }
                return;
            case 6:
                AudiMib3SettingViewModel vm6 = this.mVm;
                if (vm6 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm6.onKeyClick(callbackArg_0, 6);
                    return;
                }
                return;
            case 7:
                AudiMib3SettingViewModel vm7 = this.mVm;
                if (vm7 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm7.onKeyClick(callbackArg_0, 7);
                    return;
                }
                return;
            case 8:
                AudiMib3SettingViewModel vm8 = this.mVm;
                if (vm8 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm8.onKeyClick(callbackArg_0, 8);
                    return;
                }
                return;
            case 9:
                AudiMib3SettingViewModel vm9 = this.mVm;
                if (vm9 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm9.onKeyClick(callbackArg_0, 9);
                    return;
                }
                return;
            case 10:
                AudiMib3SettingViewModel vm10 = this.mVm;
                if (vm10 == null) {
                    vmJavaLangObjectNull = false;
                }
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

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;

public class AudiPasswordBindingImpl extends AudiPasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback100;
    private final View.OnClickListener mCallback101;
    private final View.OnClickListener mCallback102;
    private final View.OnClickListener mCallback103;
    private final View.OnClickListener mCallback94;
    private final View.OnClickListener mCallback95;
    private final View.OnClickListener mCallback96;
    private final View.OnClickListener mCallback97;
    private final View.OnClickListener mCallback98;
    private final View.OnClickListener mCallback99;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnDeleteClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
    private final TextView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.audioViewPager, 13);
        sparseIntArray.put(R.id.audi_key_ok, 14);
        sparseIntArray.put(R.id.bottomFrameLayout, 15);
    }

    public AudiPasswordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiPasswordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[3], bindings[4], bindings[5], bindings[6], bindings[7], bindings[8], bindings[9], bindings[10], bindings[11], bindings[12], bindings[2], bindings[14], bindings[13], bindings[15], bindings[0]);
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
        this.linearLayout4.setTag((Object) null);
        TextView textView = bindings[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
        setRootTag(root);
        this.mCallback102 = new OnClickListener(this, 9);
        this.mCallback100 = new OnClickListener(this, 7);
        this.mCallback99 = new OnClickListener(this, 6);
        this.mCallback96 = new OnClickListener(this, 3);
        this.mCallback94 = new OnClickListener(this, 1);
        this.mCallback103 = new OnClickListener(this, 10);
        this.mCallback98 = new OnClickListener(this, 5);
        this.mCallback101 = new OnClickListener(this, 8);
        this.mCallback97 = new OnClickListener(this, 4);
        this.mCallback95 = new OnClickListener(this, 2);
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
        setVm((AudiSettingViewModel) variable);
        return true;
    }

    public void setVm(AudiSettingViewModel Vm) {
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
        AudiSettingViewModel vm = this.mVm;
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
            this.audiKey0.setOnClickListener(this.mCallback94);
            this.audiKey1.setOnClickListener(this.mCallback95);
            this.audiKey2.setOnClickListener(this.mCallback96);
            this.audiKey3.setOnClickListener(this.mCallback97);
            this.audiKey4.setOnClickListener(this.mCallback98);
            this.audiKey5.setOnClickListener(this.mCallback99);
            this.audiKey6.setOnClickListener(this.mCallback100);
            this.audiKey7.setOnClickListener(this.mCallback101);
            this.audiKey8.setOnClickListener(this.mCallback102);
            this.audiKey9.setOnClickListener(this.mCallback103);
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
        private AudiSettingViewModel value;

        public OnLongClickListenerImpl setValue(AudiSettingViewModel value2) {
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
        private AudiSettingViewModel value;

        public OnClickListenerImpl setValue(AudiSettingViewModel value2) {
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
                AudiSettingViewModel vm = this.mVm;
                if (vm == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm.onKeyClick(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                AudiSettingViewModel vm2 = this.mVm;
                if (vm2 != null) {
                    vmJavaLangObjectNull2 = true;
                }
                if (vmJavaLangObjectNull2) {
                    vm2.onKeyClick(callbackArg_0, 1);
                    return;
                }
                return;
            case 3:
                AudiSettingViewModel vm3 = this.mVm;
                if (vm3 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm3.onKeyClick(callbackArg_0, 2);
                    return;
                }
                return;
            case 4:
                AudiSettingViewModel vm4 = this.mVm;
                if (vm4 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm4.onKeyClick(callbackArg_0, 3);
                    return;
                }
                return;
            case 5:
                AudiSettingViewModel vm5 = this.mVm;
                if (vm5 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm5.onKeyClick(callbackArg_0, 4);
                    return;
                }
                return;
            case 6:
                AudiSettingViewModel vm6 = this.mVm;
                if (vm6 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm6.onKeyClick(callbackArg_0, 5);
                    return;
                }
                return;
            case 7:
                AudiSettingViewModel vm7 = this.mVm;
                if (vm7 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm7.onKeyClick(callbackArg_0, 6);
                    return;
                }
                return;
            case 8:
                AudiSettingViewModel vm8 = this.mVm;
                if (vm8 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm8.onKeyClick(callbackArg_0, 7);
                    return;
                }
                return;
            case 9:
                AudiSettingViewModel vm9 = this.mVm;
                if (vm9 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm9.onKeyClick(callbackArg_0, 8);
                    return;
                }
                return;
            case 10:
                AudiSettingViewModel vm10 = this.mVm;
                if (vm10 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm10.onKeyClick(callbackArg_0, 9);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

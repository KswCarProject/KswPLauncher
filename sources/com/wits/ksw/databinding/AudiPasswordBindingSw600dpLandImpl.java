package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;

public class AudiPasswordBindingSw600dpLandImpl extends AudiPasswordBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback14;
    @Nullable
    private final View.OnClickListener mCallback15;
    @Nullable
    private final View.OnClickListener mCallback16;
    @Nullable
    private final View.OnClickListener mCallback17;
    @Nullable
    private final View.OnClickListener mCallback18;
    @Nullable
    private final View.OnClickListener mCallback19;
    @Nullable
    private final View.OnClickListener mCallback20;
    @Nullable
    private final View.OnClickListener mCallback21;
    @Nullable
    private final View.OnClickListener mCallback22;
    @Nullable
    private final View.OnClickListener mCallback23;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnDeleteClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
    @NonNull
    private final TextView mboundView1;

    static {
        sViewsWithIds.put(R.id.audioViewPager, 13);
        sViewsWithIds.put(R.id.audi_key_ok, 14);
        sViewsWithIds.put(R.id.bottomFrameLayout, 15);
    }

    public AudiPasswordBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiPasswordBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        setRootTag(root);
        this.mCallback15 = new OnClickListener(this, 2);
        this.mCallback16 = new OnClickListener(this, 3);
        this.mCallback14 = new OnClickListener(this, 1);
        this.mCallback23 = new OnClickListener(this, 10);
        this.mCallback19 = new OnClickListener(this, 6);
        this.mCallback17 = new OnClickListener(this, 4);
        this.mCallback21 = new OnClickListener(this, 8);
        this.mCallback20 = new OnClickListener(this, 7);
        this.mCallback22 = new OnClickListener(this, 9);
        this.mCallback18 = new OnClickListener(this, 5);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (17 != variableId) {
            return false;
        }
        setVm((AudiSettingViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiSettingViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeVmKeyBuffer((ObservableField) object, fieldId);
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
        OnLongClickListenerImpl onLongClickListenerImpl;
        OnClickListenerImpl onClickListenerImpl;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AudiSettingViewModel vm = this.mVm;
        View.OnLongClickListener vmOnDeleteLongClickAndroidViewViewOnLongClickListener = null;
        View.OnClickListener vmOnDeleteClickAndroidViewViewOnClickListener = null;
        String vmKeyBufferGet = null;
        ObservableField<String> vmKeyBuffer = null;
        if ((dirtyFlags & 7) != 0) {
            if (!((dirtyFlags & 6) == 0 || vm == null)) {
                if (this.mVmOnDeleteLongClickAndroidViewViewOnLongClickListener == null) {
                    onLongClickListenerImpl = new OnLongClickListenerImpl();
                    this.mVmOnDeleteLongClickAndroidViewViewOnLongClickListener = onLongClickListenerImpl;
                } else {
                    onLongClickListenerImpl = this.mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
                }
                vmOnDeleteLongClickAndroidViewViewOnLongClickListener = onLongClickListenerImpl.setValue(vm);
                if (this.mVmOnDeleteClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVmOnDeleteClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mVmOnDeleteClickAndroidViewViewOnClickListener;
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
            this.audiKey0.setOnClickListener(this.mCallback14);
            this.audiKey1.setOnClickListener(this.mCallback15);
            this.audiKey2.setOnClickListener(this.mCallback16);
            this.audiKey3.setOnClickListener(this.mCallback17);
            this.audiKey4.setOnClickListener(this.mCallback18);
            this.audiKey5.setOnClickListener(this.mCallback19);
            this.audiKey6.setOnClickListener(this.mCallback20);
            this.audiKey7.setOnClickListener(this.mCallback21);
            this.audiKey8.setOnClickListener(this.mCallback22);
            this.audiKey9.setOnClickListener(this.mCallback23);
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
        boolean vmJavaLangObjectNull = false;
        boolean vmJavaLangObjectNull2 = true;
        switch (sourceId) {
            case 1:
                AudiSettingViewModel vm = this.mVm;
                if (vm == null) {
                    vmJavaLangObjectNull2 = false;
                }
                if (vmJavaLangObjectNull2) {
                    vm.onKeyClick(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                AudiSettingViewModel vm2 = this.mVm;
                if (vm2 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm2.onKeyClick(callbackArg_0, 1);
                    return;
                }
                return;
            case 3:
                AudiSettingViewModel vm3 = this.mVm;
                if (vm3 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm3.onKeyClick(callbackArg_0, 2);
                    return;
                }
                return;
            case 4:
                AudiSettingViewModel vm4 = this.mVm;
                if (vm4 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm4.onKeyClick(callbackArg_0, 3);
                    return;
                }
                return;
            case 5:
                AudiSettingViewModel vm5 = this.mVm;
                if (vm5 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm5.onKeyClick(callbackArg_0, 4);
                    return;
                }
                return;
            case 6:
                AudiSettingViewModel vm6 = this.mVm;
                if (vm6 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm6.onKeyClick(callbackArg_0, 5);
                    return;
                }
                return;
            case 7:
                AudiSettingViewModel vm7 = this.mVm;
                if (vm7 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm7.onKeyClick(callbackArg_0, 6);
                    return;
                }
                return;
            case 8:
                AudiSettingViewModel vm8 = this.mVm;
                if (vm8 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm8.onKeyClick(callbackArg_0, 7);
                    return;
                }
                return;
            case 9:
                AudiSettingViewModel vm9 = this.mVm;
                if (vm9 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm9.onKeyClick(callbackArg_0, 8);
                    return;
                }
                return;
            case 10:
                AudiSettingViewModel vm10 = this.mVm;
                if (vm10 != null) {
                    vmJavaLangObjectNull = true;
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

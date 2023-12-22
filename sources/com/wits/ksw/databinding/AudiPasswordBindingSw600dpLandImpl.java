package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.DateView;

/* loaded from: classes7.dex */
public class AudiPasswordBindingSw600dpLandImpl extends AudiPasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback270;
    private final View.OnClickListener mCallback271;
    private final View.OnClickListener mCallback272;
    private final View.OnClickListener mCallback273;
    private final View.OnClickListener mCallback274;
    private final View.OnClickListener mCallback275;
    private final View.OnClickListener mCallback276;
    private final View.OnClickListener mCallback277;
    private final View.OnClickListener mCallback278;
    private final View.OnClickListener mCallback279;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnDeleteClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mVmOnDeleteLongClickAndroidViewViewOnLongClickListener;
    private final TextView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.audioViewPager, 13);
        sparseIntArray.put(C0899R.C0901id.audi_key_ok, 14);
        sparseIntArray.put(C0899R.C0901id.bottomFrameLayout, 15);
    }

    public AudiPasswordBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiPasswordBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[3], (ImageView) bindings[4], (ImageView) bindings[5], (ImageView) bindings[6], (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[9], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[12], (ImageView) bindings[2], (ImageView) bindings[14], (GridLayout) bindings[13], (DateView) bindings[15], (ConstraintLayout) bindings[0]);
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
        this.linearLayout4.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        setRootTag(root);
        this.mCallback279 = new OnClickListener(this, 10);
        this.mCallback277 = new OnClickListener(this, 8);
        this.mCallback275 = new OnClickListener(this, 6);
        this.mCallback273 = new OnClickListener(this, 4);
        this.mCallback271 = new OnClickListener(this, 2);
        this.mCallback278 = new OnClickListener(this, 9);
        this.mCallback276 = new OnClickListener(this, 7);
        this.mCallback274 = new OnClickListener(this, 5);
        this.mCallback272 = new OnClickListener(this, 3);
        this.mCallback270 = new OnClickListener(this, 1);
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
            setVm((AudiSettingViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiPasswordBinding
    public void setVm(AudiSettingViewModel Vm) {
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
        AudiSettingViewModel vm = this.mVm;
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
            this.audiKey0.setOnClickListener(this.mCallback270);
            this.audiKey1.setOnClickListener(this.mCallback271);
            this.audiKey2.setOnClickListener(this.mCallback272);
            this.audiKey3.setOnClickListener(this.mCallback273);
            this.audiKey4.setOnClickListener(this.mCallback274);
            this.audiKey5.setOnClickListener(this.mCallback275);
            this.audiKey6.setOnClickListener(this.mCallback276);
            this.audiKey7.setOnClickListener(this.mCallback277);
            this.audiKey8.setOnClickListener(this.mCallback278);
            this.audiKey9.setOnClickListener(this.mCallback279);
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
        private AudiSettingViewModel value;

        public OnLongClickListenerImpl setValue(AudiSettingViewModel value) {
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
        private AudiSettingViewModel value;

        public OnClickListenerImpl setValue(AudiSettingViewModel value) {
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
                AudiSettingViewModel vm = this.mVm;
                vmJavaLangObjectNull = vm != null;
                if (vmJavaLangObjectNull) {
                    vm.onKeyClick(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                AudiSettingViewModel vm2 = this.mVm;
                if (vm2 != null) {
                    vm2.onKeyClick(callbackArg_0, 1);
                    return;
                }
                return;
            case 3:
                AudiSettingViewModel vm3 = this.mVm;
                vmJavaLangObjectNull = vm3 != null;
                if (vmJavaLangObjectNull) {
                    vm3.onKeyClick(callbackArg_0, 2);
                    return;
                }
                return;
            case 4:
                AudiSettingViewModel vm4 = this.mVm;
                vmJavaLangObjectNull = vm4 != null;
                if (vmJavaLangObjectNull) {
                    vm4.onKeyClick(callbackArg_0, 3);
                    return;
                }
                return;
            case 5:
                AudiSettingViewModel vm5 = this.mVm;
                vmJavaLangObjectNull = vm5 != null;
                if (vmJavaLangObjectNull) {
                    vm5.onKeyClick(callbackArg_0, 4);
                    return;
                }
                return;
            case 6:
                AudiSettingViewModel vm6 = this.mVm;
                vmJavaLangObjectNull = vm6 != null;
                if (vmJavaLangObjectNull) {
                    vm6.onKeyClick(callbackArg_0, 5);
                    return;
                }
                return;
            case 7:
                AudiSettingViewModel vm7 = this.mVm;
                vmJavaLangObjectNull = vm7 != null;
                if (vmJavaLangObjectNull) {
                    vm7.onKeyClick(callbackArg_0, 6);
                    return;
                }
                return;
            case 8:
                AudiSettingViewModel vm8 = this.mVm;
                vmJavaLangObjectNull = vm8 != null;
                if (vmJavaLangObjectNull) {
                    vm8.onKeyClick(callbackArg_0, 7);
                    return;
                }
                return;
            case 9:
                AudiSettingViewModel vm9 = this.mVm;
                vmJavaLangObjectNull = vm9 != null;
                if (vmJavaLangObjectNull) {
                    vm9.onKeyClick(callbackArg_0, 8);
                    return;
                }
                return;
            case 10:
                AudiSettingViewModel vm10 = this.mVm;
                vmJavaLangObjectNull = vm10 != null;
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

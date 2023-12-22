package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsViewPager;

/* loaded from: classes7.dex */
public class ActivityMainId6GsBindingImpl extends ActivityMainId6GsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback515;
    private final View.OnClickListener mCallback516;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ActivityMainId6GsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ActivityMainId6GsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[2], (Bmwid6gsViewPager) bindings[1], (ImageView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.id6GsLeftBtn.setTag(null);
        this.id6GsMainViewPager.setTag(null);
        this.id6GsRightBtn.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(root);
        this.mCallback516 = new OnClickListener(this, 2);
        this.mCallback515 = new OnClickListener(this, 1);
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
            setVm((BmwId6gsViewMode) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityMainId6GsBinding
    public void setVm(BmwId6gsViewMode Vm) {
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
                return onChangeVmPageIndex((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmPageIndex(ObservableInt VmPageIndex, int fieldId) {
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
        boolean z;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int vmPageIndexInt2ViewINVISIBLEViewVISIBLE = 0;
        ObservableInt vmPageIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        int vmPageIndexGet = 0;
        int vmPageIndexInt0ViewINVISIBLEViewVISIBLE = 0;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmPageIndex = vm.pageIndex;
            }
            updateRegistration(0, vmPageIndex);
            if (vmPageIndex != null) {
                vmPageIndexGet = vmPageIndex.get();
            }
            if (vmPageIndexGet != 0) {
                z = false;
            } else {
                z = true;
            }
            boolean vmPageIndexInt0 = z;
            boolean vmPageIndexInt2 = vmPageIndexGet == 2;
            if ((dirtyFlags & 7) != 0) {
                if (vmPageIndexInt0) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
            if ((dirtyFlags & 7) != 0) {
                if (vmPageIndexInt2) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            vmPageIndexInt0ViewINVISIBLEViewVISIBLE = vmPageIndexInt0 ? 4 : 0;
            vmPageIndexInt2ViewINVISIBLEViewVISIBLE = vmPageIndexInt2 ? 4 : 0;
        }
        if ((4 & dirtyFlags) != 0) {
            this.id6GsLeftBtn.setOnClickListener(this.mCallback515);
            this.id6GsRightBtn.setOnClickListener(this.mCallback516);
        }
        if ((7 & dirtyFlags) != 0) {
            this.id6GsLeftBtn.setVisibility(vmPageIndexInt0ViewINVISIBLEViewVISIBLE);
            this.id6GsMainViewPager.setCurrentItem(vmPageIndexGet);
            this.id6GsRightBtn.setVisibility(vmPageIndexInt2ViewINVISIBLEViewVISIBLE);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmPageIndexJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                BmwId6gsViewMode vm = this.mVm;
                boolean vmJavaLangObjectNull = vm != null;
                if (vmJavaLangObjectNull) {
                    ObservableInt vmPageIndex = vm.pageIndex;
                    vmPageIndexJavaLangObjectNull = vmPageIndex != null;
                    if (vmPageIndexJavaLangObjectNull) {
                        int vmPageIndexGet = vmPageIndex.get();
                        vm.setCurrentItem(callbackArg_0, vmPageIndexGet - 1);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                BmwId6gsViewMode vm2 = this.mVm;
                boolean vmJavaLangObjectNull2 = vm2 != null;
                if (vmJavaLangObjectNull2) {
                    ObservableInt vmPageIndex2 = vm2.pageIndex;
                    vmPageIndexJavaLangObjectNull = vmPageIndex2 != null;
                    if (vmPageIndexJavaLangObjectNull) {
                        int vmPageIndexGet2 = vmPageIndex2.get();
                        vm2.setCurrentItem(callbackArg_0, vmPageIndexGet2 + 1);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }
}

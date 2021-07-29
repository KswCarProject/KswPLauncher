package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public class ActivityMainId6GsBindingImpl extends ActivityMainId6GsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback251;
    private final View.OnClickListener mCallback252;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ActivityMainId6GsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ActivityMainId6GsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[2], bindings[1], bindings[3]);
        this.mDirtyFlags = -1;
        this.id6GsLeftBtn.setTag((Object) null);
        this.id6GsMainViewPager.setTag((Object) null);
        this.id6GsRightBtn.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback252 = new OnClickListener(this, 2);
        this.mCallback251 = new OnClickListener(this, 1);
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
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(BmwId6gsViewMode Vm) {
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
                return onChangeVmPageIndex((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmPageIndex(ObservableInt VmPageIndex, int fieldId) {
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
        int vmPageIndexInt2ViewINVISIBLEViewVISIBLE = 0;
        ObservableInt vmPageIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        int vmPageIndexGet = 0;
        int vmPageIndexInt0ViewINVISIBLEViewVISIBLE = 0;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmPageIndex = vm.pageIndex;
            }
            updateRegistration(0, (Observable) vmPageIndex);
            if (vmPageIndex != null) {
                vmPageIndexGet = vmPageIndex.get();
            }
            boolean z = true;
            boolean vmPageIndexInt0 = vmPageIndexGet == 0;
            if (vmPageIndexGet != 2) {
                z = false;
            }
            boolean vmPageIndexInt2 = z;
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
            int i = 4;
            vmPageIndexInt0ViewINVISIBLEViewVISIBLE = vmPageIndexInt0 ? 4 : 0;
            if (!vmPageIndexInt2) {
                i = 0;
            }
            vmPageIndexInt2ViewINVISIBLEViewVISIBLE = i;
        }
        if ((4 & dirtyFlags) != 0) {
            this.id6GsLeftBtn.setOnClickListener(this.mCallback251);
            this.id6GsRightBtn.setOnClickListener(this.mCallback252);
        }
        if ((7 & dirtyFlags) != 0) {
            this.id6GsLeftBtn.setVisibility(vmPageIndexInt0ViewINVISIBLEViewVISIBLE);
            this.id6GsMainViewPager.setCurrentItem(vmPageIndexGet);
            this.id6GsRightBtn.setVisibility(vmPageIndexInt2ViewINVISIBLEViewVISIBLE);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmPageIndexJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                BmwId6gsViewMode vm = this.mVm;
                if (vm != null) {
                    ObservableInt vmPageIndex = vm.pageIndex;
                    if (vmPageIndex != null) {
                        vmPageIndexJavaLangObjectNull = true;
                    }
                    if (vmPageIndexJavaLangObjectNull) {
                        vm.setCurrentItem(callbackArg_0, vmPageIndex.get() - 1);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                BmwId6gsViewMode vm2 = this.mVm;
                if (vm2 != null) {
                    ObservableInt vmPageIndex2 = vm2.pageIndex;
                    if (vmPageIndex2 != null) {
                        vmPageIndexJavaLangObjectNull = true;
                    }
                    if (vmPageIndexJavaLangObjectNull) {
                        vm2.setCurrentItem(callbackArg_0, vmPageIndex2.get() + 1);
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

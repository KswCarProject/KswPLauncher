package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public class ActivityMainId6GsBindingImpl extends ActivityMainId6GsBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback47;
    @Nullable
    private final View.OnClickListener mCallback48;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    public ActivityMainId6GsBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ActivityMainId6GsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[2], bindings[1], bindings[3]);
        this.mDirtyFlags = -1;
        this.id6GsLeftBtn.setTag((Object) null);
        this.id6GsMainViewPager.setTag((Object) null);
        this.id6GsRightBtn.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        setRootTag(root);
        this.mCallback47 = new OnClickListener(this, 1);
        this.mCallback48 = new OnClickListener(this, 2);
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
        if (10 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(@Nullable BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeVmPageIndex((ObservableInt) object, fieldId);
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
        BmwId6gsViewMode vm = this.mVm;
        int vmPageIndexInt2ViewINVISIBLEViewVISIBLE = 0;
        ObservableInt vmPageIndex = null;
        int vmPageIndexInt0ViewINVISIBLEViewVISIBLE = 0;
        int vmPageIndexGet = 0;
        if ((dirtyFlags & 7) != 0) {
            if (vm != null) {
                vmPageIndex = vm.pageIndex;
            }
            updateRegistration(0, (Observable) vmPageIndex);
            if (vmPageIndex != null) {
                vmPageIndexGet = vmPageIndex.get();
            }
            boolean z = true;
            boolean vmPageIndexInt2 = vmPageIndexGet == 2;
            if (vmPageIndexGet != 0) {
                z = false;
            }
            boolean vmPageIndexInt0 = z;
            if ((dirtyFlags & 7) != 0) {
                if (vmPageIndexInt2) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            if ((dirtyFlags & 7) != 0) {
                if (vmPageIndexInt0) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
            int i = 4;
            vmPageIndexInt2ViewINVISIBLEViewVISIBLE = vmPageIndexInt2 ? 4 : 0;
            if (!vmPageIndexInt0) {
                i = 0;
            }
            vmPageIndexInt0ViewINVISIBLEViewVISIBLE = i;
        }
        if ((4 & dirtyFlags) != 0) {
            this.id6GsLeftBtn.setOnClickListener(this.mCallback47);
            this.id6GsRightBtn.setOnClickListener(this.mCallback48);
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

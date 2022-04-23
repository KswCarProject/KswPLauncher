package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public class ActivityMainBenzGsBindingImpl extends ActivityMainBenzGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback176;
    private final View.OnClickListener mCallback177;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnControlClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;

    public ActivityMainBenzGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityMainBenzGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[2], bindings[3], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.benzgsHomeLeftBtn.setTag((Object) null);
        this.benzgsHomeRightBtn.setTag((Object) null);
        this.benzgsViewpage.setTag((Object) null);
        this.controlBtn.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback176 = new OnClickListener(this, 1);
        this.mCallback177 = new OnClickListener(this, 2);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        setVm((BenzGsViewMoel) variable);
        return true;
    }

    public void setVm(BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVmPageIndex((ObservableInt) object, fieldId);
            case 2:
                return onChangeVmControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmControlBeanBenzControlPanelState(ObservableBoolean VmControlBeanBenzControlPanelState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmPageIndex(ObservableInt VmPageIndex, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmControlBeanControlPanelClose(ObservableBoolean VmControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int vmPageIndexGet;
        int vmPageIndexInt1ViewVISIBLEViewINVISIBLE;
        int vmPageIndexInt0ViewVISIBLEViewINVISIBLE;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ControlBean vmControlBean = null;
        BenzGsViewMoel vm = this.mVm;
        int vmControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        boolean vmControlBeanBenzControlPanelStateGet = false;
        View.OnClickListener vmOnControlClickAndroidViewViewOnClickListener = null;
        ObservableBoolean vmControlBeanBenzControlPanelState = null;
        Drawable vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        ObservableInt vmPageIndex = null;
        ObservableBoolean vmControlBeanControlPanelClose = null;
        boolean vmControlBeanControlPanelCloseGet = false;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 29) != 0) {
                if (vm != null) {
                    vmControlBean = vm.controlBean;
                }
                if ((dirtyFlags & 25) != 0) {
                    if (vmControlBean != null) {
                        vmControlBeanBenzControlPanelState = vmControlBean.benzControlPanelState;
                    }
                    updateRegistration(0, (Observable) vmControlBeanBenzControlPanelState);
                    if (vmControlBeanBenzControlPanelState != null) {
                        vmControlBeanBenzControlPanelStateGet = vmControlBeanBenzControlPanelState.get();
                    }
                    if ((dirtyFlags & 25) != 0) {
                        if (vmControlBeanBenzControlPanelStateGet) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = vmControlBeanBenzControlPanelStateGet ? AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_down_selector) : AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_up_selector);
                }
                if ((dirtyFlags & 28) != 0) {
                    if (vmControlBean != null) {
                        vmControlBeanControlPanelClose = vmControlBean.controlPanelClose;
                    }
                    updateRegistration(2, (Observable) vmControlBeanControlPanelClose);
                    if (vmControlBeanControlPanelClose != null) {
                        vmControlBeanControlPanelCloseGet = vmControlBeanControlPanelClose.get();
                    }
                    if ((dirtyFlags & 28) != 0) {
                        if (vmControlBeanControlPanelCloseGet) {
                            dirtyFlags |= 64;
                        } else {
                            dirtyFlags |= 32;
                        }
                    }
                    vmControlBeanControlPanelCloseViewGONEViewVISIBLE = vmControlBeanControlPanelCloseGet ? 8 : 0;
                }
            }
            if (!((dirtyFlags & 24) == 0 || vm == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mVmOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                vmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vm);
            }
            if ((dirtyFlags & 26) != 0) {
                if (vm != null) {
                    vmPageIndex = vm.pageIndex;
                }
                boolean z = true;
                updateRegistration(1, (Observable) vmPageIndex);
                if (vmPageIndex != null) {
                    vmPageIndexGet = vmPageIndex.get();
                } else {
                    vmPageIndexGet = 0;
                }
                boolean vmPageIndexInt0 = vmPageIndexGet == 0;
                if (vmPageIndexGet != 1) {
                    z = false;
                }
                boolean vmPageIndexInt1 = z;
                if ((dirtyFlags & 26) != 0) {
                    if (vmPageIndexInt0) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if ((dirtyFlags & 26) != 0) {
                    if (vmPageIndexInt1) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 4;
                int vmPageIndexInt0ViewVISIBLEViewINVISIBLE2 = vmPageIndexInt0 ? 0 : 4;
                if (vmPageIndexInt1) {
                    vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
                }
                ControlBean controlBean = vmControlBean;
                vmPageIndexInt0ViewVISIBLEViewINVISIBLE = vmPageIndexInt0ViewVISIBLEViewINVISIBLE2;
            } else {
                vmPageIndexGet = 0;
                vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
                ControlBean controlBean2 = vmControlBean;
                vmPageIndexInt0ViewVISIBLEViewINVISIBLE = 0;
            }
        } else {
            vmPageIndexGet = 0;
            vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
            vmPageIndexInt0ViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 16) != 0) {
            BenzGsViewMoel benzGsViewMoel = vm;
            boolean z2 = vmControlBeanBenzControlPanelStateGet;
            this.benzgsHomeLeftBtn.setOnClickListener(this.mCallback176);
            this.benzgsHomeRightBtn.setOnClickListener(this.mCallback177);
        } else {
            boolean z3 = vmControlBeanBenzControlPanelStateGet;
        }
        if ((dirtyFlags & 26) != 0) {
            this.benzgsHomeLeftBtn.setVisibility(vmPageIndexInt1ViewVISIBLEViewINVISIBLE);
            this.benzgsHomeRightBtn.setVisibility(vmPageIndexInt0ViewVISIBLEViewINVISIBLE);
            this.benzgsViewpage.setCurrentItem(vmPageIndexGet);
        }
        if ((dirtyFlags & 28) != 0) {
            this.controlBtn.setVisibility(vmControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 24) != 0) {
            this.controlBtn.setOnClickListener(vmOnControlClickAndroidViewViewOnClickListener);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BenzGsViewMoel value;

        public OnClickListenerImpl setValue(BenzGsViewMoel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onControlClick(arg0);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull = true;
        boolean vmJavaLangObjectNull2 = false;
        switch (sourceId) {
            case 1:
                BenzGsViewMoel vm = this.mVm;
                if (vm == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm.setCurrentItem(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                BenzGsViewMoel vm2 = this.mVm;
                if (vm2 != null) {
                    vmJavaLangObjectNull2 = true;
                }
                if (vmJavaLangObjectNull2) {
                    vm2.setCurrentItem(callbackArg_0, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

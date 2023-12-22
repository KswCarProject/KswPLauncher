package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewPage;

/* loaded from: classes7.dex */
public class ActivityMainBenzGsBindingImpl extends ActivityMainBenzGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback448;
    private final View.OnClickListener mCallback449;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnControlClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;

    public ActivityMainBenzGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityMainBenzGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ImageView) bindings[2], (ImageView) bindings[3], (BenzGsViewPage) bindings[1], (ImageView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.benzgsHomeLeftBtn.setTag(null);
        this.benzgsHomeRightBtn.setTag(null);
        this.benzgsViewpage.setTag(null);
        this.controlBtn.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        this.mCallback449 = new OnClickListener(this, 2);
        this.mCallback448 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setVm((BenzGsViewMoel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityMainBenzGsBinding
    public void setVm(BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmPageIndex(ObservableInt VmPageIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmControlBeanControlPanelClose(ObservableBoolean VmControlBeanControlPanelClose, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int vmPageIndexGet;
        int vmPageIndexInt1ViewVISIBLEViewINVISIBLE;
        int vmPageIndexInt0ViewVISIBLEViewINVISIBLE;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
                    updateRegistration(0, vmControlBeanBenzControlPanelState);
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
                    vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = vmControlBeanBenzControlPanelStateGet ? AppCompatResources.getDrawable(this.controlBtn.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_down_selector) : AppCompatResources.getDrawable(this.controlBtn.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_up_selector);
                }
                if ((dirtyFlags & 28) != 0) {
                    if (vmControlBean != null) {
                        vmControlBeanControlPanelClose = vmControlBean.controlPanelClose;
                    }
                    updateRegistration(2, vmControlBeanControlPanelClose);
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
            if ((dirtyFlags & 24) != 0 && vm != null) {
                OnClickListenerImpl onClickListenerImpl = this.mVmOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                vmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vm);
            }
            if ((dirtyFlags & 26) == 0) {
                vmPageIndexGet = 0;
                vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
                vmPageIndexInt0ViewVISIBLEViewINVISIBLE = 0;
            } else {
                if (vm != null) {
                    vmPageIndex = vm.pageIndex;
                }
                updateRegistration(1, vmPageIndex);
                if (vmPageIndex == null) {
                    vmPageIndexGet = 0;
                } else {
                    int vmPageIndexGet2 = vmPageIndex.get();
                    vmPageIndexGet = vmPageIndexGet2;
                }
                boolean vmPageIndexInt0 = vmPageIndexGet == 0;
                boolean vmPageIndexInt1 = vmPageIndexGet == 1;
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
                int vmPageIndexInt0ViewVISIBLEViewINVISIBLE2 = vmPageIndexInt0 ? 0 : 4;
                vmPageIndexInt1ViewVISIBLEViewINVISIBLE = vmPageIndexInt1 ? 0 : 4;
                vmPageIndexInt0ViewVISIBLEViewINVISIBLE = vmPageIndexInt0ViewVISIBLEViewINVISIBLE2;
            }
        } else {
            vmPageIndexGet = 0;
            vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
            vmPageIndexInt0ViewVISIBLEViewINVISIBLE = 0;
        }
        if ((dirtyFlags & 16) != 0) {
            this.benzgsHomeLeftBtn.setOnClickListener(this.mCallback448);
            this.benzgsHomeRightBtn.setOnClickListener(this.mCallback449);
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

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private BenzGsViewMoel value;

        public OnClickListenerImpl setValue(BenzGsViewMoel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onControlClick(arg0);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        switch (sourceId) {
            case 1:
                BenzGsViewMoel vm = this.mVm;
                boolean vmJavaLangObjectNull = vm != null;
                if (vmJavaLangObjectNull) {
                    vm.setCurrentItem(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                BenzGsViewMoel vm2 = this.mVm;
                boolean vmJavaLangObjectNull2 = vm2 != null;
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

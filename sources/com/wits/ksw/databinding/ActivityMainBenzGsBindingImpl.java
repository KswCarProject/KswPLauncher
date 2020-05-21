package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;

public class ActivityMainBenzGsBindingImpl extends ActivityMainBenzGsBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback78;
    @Nullable
    private final View.OnClickListener mCallback79;
    private long mDirtyFlags;
    private OnClickListenerImpl mVmOnControlClickAndroidViewViewOnClickListener;
    @NonNull
    private final RelativeLayout mboundView0;

    public ActivityMainBenzGsBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityMainBenzGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[2], bindings[3], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.benzgsHomeLeftBtn.setTag((Object) null);
        this.benzgsHomeRightBtn.setTag((Object) null);
        this.benzgsViewpage.setTag((Object) null);
        this.controlBtn.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        setRootTag(root);
        this.mCallback79 = new OnClickListener(this, 2);
        this.mCallback78 = new OnClickListener(this, 1);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (9 != variableId) {
            return false;
        }
        setVm((BenzGsViewMoel) variable);
        return true;
    }

    public void setVm(@Nullable BenzGsViewMoel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmPageIndex((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeVmControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
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

    private boolean onChangeVmControlBeanControlPanelClose(ObservableBoolean VmControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmControlBeanBenzControlPanelState(ObservableBoolean VmControlBeanBenzControlPanelState, int fieldId) {
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
        OnClickListenerImpl onClickListenerImpl;
        ImageView imageView;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        BenzGsViewMoel vm = this.mVm;
        boolean vmControlBeanControlPanelCloseGet = false;
        int vmPageIndexInt0ViewVISIBLEViewINVISIBLE = 0;
        ObservableInt vmPageIndex = null;
        ObservableBoolean vmControlBeanControlPanelClose = null;
        boolean vmControlBeanBenzControlPanelStateGet = false;
        ObservableBoolean vmControlBeanBenzControlPanelState = null;
        boolean vmPageIndexInt1 = false;
        ControlBean vmControlBean = null;
        int vmControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        View.OnClickListener vmOnControlClickAndroidViewViewOnClickListener = null;
        Drawable vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        int vmPageIndexGet = 0;
        int vmPageIndexInt1ViewVISIBLEViewINVISIBLE = 0;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (vm != null) {
                    vmPageIndex = vm.pageIndex;
                }
                updateRegistration(0, (Observable) vmPageIndex);
                if (vmPageIndex != null) {
                    vmPageIndexGet = vmPageIndex.get();
                }
                int vmPageIndexGet2 = vmPageIndexGet;
                vmPageIndexInt1 = vmPageIndexGet2 == 1;
                boolean vmPageIndexInt0 = vmPageIndexGet2 == 0;
                if ((dirtyFlags & 25) != 0) {
                    if (vmPageIndexInt1) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if ((dirtyFlags & 25) != 0) {
                    if (vmPageIndexInt0) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
                int i2 = 4;
                vmPageIndexInt1ViewVISIBLEViewINVISIBLE = vmPageIndexInt1 ? 0 : 4;
                if (vmPageIndexInt0) {
                    i2 = 0;
                }
                vmPageIndexInt0ViewVISIBLEViewINVISIBLE = i2;
                vmPageIndexGet = vmPageIndexGet2;
            }
            if ((dirtyFlags & 30) != 0) {
                if (vm != null) {
                    vmControlBean = vm.controlBean;
                }
                if ((dirtyFlags & 26) != 0) {
                    if (vmControlBean != null) {
                        vmControlBeanControlPanelClose = vmControlBean.controlPanelClose;
                    }
                    updateRegistration(1, (Observable) vmControlBeanControlPanelClose);
                    if (vmControlBeanControlPanelClose != null) {
                        vmControlBeanControlPanelCloseGet = vmControlBeanControlPanelClose.get();
                    }
                    if ((dirtyFlags & 26) != 0) {
                        if (vmControlBeanControlPanelCloseGet) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    vmControlBeanControlPanelCloseViewGONEViewVISIBLE = vmControlBeanControlPanelCloseGet ? 8 : 0;
                }
                if ((dirtyFlags & 28) != 0) {
                    if (vmControlBean != null) {
                        vmControlBeanBenzControlPanelState = vmControlBean.benzControlPanelState;
                    }
                    updateRegistration(2, (Observable) vmControlBeanBenzControlPanelState);
                    if (vmControlBeanBenzControlPanelState != null) {
                        vmControlBeanBenzControlPanelStateGet = vmControlBeanBenzControlPanelState.get();
                    }
                    if ((dirtyFlags & 28) != 0) {
                        if (vmControlBeanBenzControlPanelStateGet) {
                            dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        } else {
                            dirtyFlags |= 512;
                        }
                    }
                    if (vmControlBeanBenzControlPanelStateGet) {
                        imageView = this.controlBtn;
                        i = R.drawable.ntg55_ctrlpanel_down_selector;
                    } else {
                        imageView = this.controlBtn;
                        i = R.drawable.ntg55_ctrlpanel_up_selector;
                    }
                    vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = getDrawableFromResource(imageView, i);
                }
            }
            if (!((dirtyFlags & 24) == 0 || vm == null)) {
                if (this.mVmOnControlClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mVmOnControlClickAndroidViewViewOnClickListener;
                }
                vmOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vm);
            }
        }
        int vmPageIndexGet3 = vmPageIndexGet;
        int vmPageIndexInt1ViewVISIBLEViewINVISIBLE2 = vmPageIndexInt1ViewVISIBLEViewINVISIBLE;
        boolean z = vmControlBeanBenzControlPanelStateGet;
        Drawable vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector2 = vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector;
        boolean vmControlBeanBenzControlPanelStateGet2 = z;
        if ((dirtyFlags & 16) != 0) {
            BenzGsViewMoel benzGsViewMoel = vm;
            ObservableInt observableInt = vmPageIndex;
            this.benzgsHomeLeftBtn.setOnClickListener(this.mCallback78);
            this.benzgsHomeRightBtn.setOnClickListener(this.mCallback79);
        } else {
            ObservableInt observableInt2 = vmPageIndex;
        }
        if ((dirtyFlags & 25) != 0) {
            this.benzgsHomeLeftBtn.setVisibility(vmPageIndexInt1ViewVISIBLEViewINVISIBLE2);
            this.benzgsHomeRightBtn.setVisibility(vmPageIndexInt0ViewVISIBLEViewINVISIBLE);
            this.benzgsViewpage.setCurrentItem(vmPageIndexGet3);
        }
        if ((dirtyFlags & 26) != 0) {
            this.controlBtn.setVisibility(vmControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, vmControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector2);
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
        boolean vmJavaLangObjectNull = false;
        boolean vmJavaLangObjectNull2 = true;
        switch (sourceId) {
            case 1:
                BenzGsViewMoel vm = this.mVm;
                if (vm == null) {
                    vmJavaLangObjectNull2 = false;
                }
                if (vmJavaLangObjectNull2) {
                    vm.setCurrentItem(callbackArg_0, 0);
                    return;
                }
                return;
            case 2:
                BenzGsViewMoel vm2 = this.mVm;
                if (vm2 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm2.setCurrentItem(callbackArg_0, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

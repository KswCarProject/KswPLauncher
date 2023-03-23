package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainKswmbuxBindingImpl extends ActivityMainKswmbuxBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnControlClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.top, 2);
        sparseIntArray.put(R.id.left_button, 3);
        sparseIntArray.put(R.id.right_button, 4);
        sparseIntArray.put(R.id.ugViewPage, 5);
        sparseIntArray.put(R.id.down, 6);
        sparseIntArray.put(R.id.imageView1, 7);
        sparseIntArray.put(R.id.imageView2, 8);
        sparseIntArray.put(R.id.imageView3, 9);
    }

    public ActivityMainKswmbuxBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainKswmbuxBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[6], bindings[7], bindings[8], bindings[9], bindings[3], bindings[4], bindings[2], bindings[5]);
        this.mDirtyFlags = -1;
        this.controlBtn.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelControlBeanBenzControlPanelState(ObservableBoolean ViewModelControlBeanBenzControlPanelState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelControlBeanControlPanelClose(ObservableBoolean ViewModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        int viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        boolean viewModelControlBeanBenzControlPanelStateGet = false;
        boolean viewModelControlBeanControlPanelCloseGet = false;
        ObservableBoolean viewModelControlBeanBenzControlPanelState = null;
        Drawable viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        ObservableBoolean viewModelControlBeanControlPanelClose = null;
        ControlBean viewModelControlBean = null;
        LauncherViewModel viewModel = this.mViewModel;
        View.OnClickListener viewModelOnControlClickAndroidViewViewOnClickListener = null;
        if ((15 & dirtyFlags) != 0) {
            if (viewModel != null) {
                viewModelControlBean = viewModel.controlBean;
            }
            if ((dirtyFlags & 13) != 0) {
                if (viewModelControlBean != null) {
                    viewModelControlBeanBenzControlPanelState = viewModelControlBean.benzControlPanelState;
                }
                updateRegistration(0, (Observable) viewModelControlBeanBenzControlPanelState);
                if (viewModelControlBeanBenzControlPanelState != null) {
                    viewModelControlBeanBenzControlPanelStateGet = viewModelControlBeanBenzControlPanelState.get();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (viewModelControlBeanBenzControlPanelStateGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = AppCompatResources.getDrawable(this.controlBtn.getContext(), viewModelControlBeanBenzControlPanelStateGet ? R.drawable.ntg55_ctrlpanel_down_selector : R.drawable.ntg55_ctrlpanel_up_selector);
            }
            if ((dirtyFlags & 14) != 0) {
                if (viewModelControlBean != null) {
                    viewModelControlBeanControlPanelClose = viewModelControlBean.controlPanelClose;
                }
                updateRegistration(1, (Observable) viewModelControlBeanControlPanelClose);
                if (viewModelControlBeanControlPanelClose != null) {
                    viewModelControlBeanControlPanelCloseGet = viewModelControlBeanControlPanelClose.get();
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelControlBeanControlPanelCloseGet) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = viewModelControlBeanControlPanelCloseGet ? 8 : 0;
            }
            if (!((dirtyFlags & 12) == 0 || viewModel == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                viewModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(viewModel);
            }
        }
        if ((dirtyFlags & 14) != 0) {
            this.controlBtn.setVisibility(viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 13) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((12 & dirtyFlags) != 0) {
            this.controlBtn.setOnClickListener(viewModelOnControlClickAndroidViewViewOnClickListener);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl setValue(LauncherViewModel value2) {
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
}

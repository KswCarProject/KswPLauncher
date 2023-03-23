package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class BenzNtg6FyBindCls1024x600Impl extends BenzNtg6FyBindCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnControlClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_main_ntg_fy, 2);
        sparseIntArray.put(R.id.benzNtg6FyViewpager, 3);
        sparseIntArray.put(R.id.indicator_benz_ntg6_fy, 4);
        sparseIntArray.put(R.id.layout_coat_benz_fy, 5);
        sparseIntArray.put(R.id.iv_coat_fy, 6);
        sparseIntArray.put(R.id.tv_coat_fy_tip, 7);
    }

    public BenzNtg6FyBindCls1024x600Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private BenzNtg6FyBindCls1024x600Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[3], bindings[1], bindings[4], bindings[6], bindings[5], bindings[2], bindings[7]);
        this.mDirtyFlags = -1;
        this.controlBtn.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
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
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
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
                return onChangeViewModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelControlBeanControlPanelClose(ObservableBoolean ViewModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelControlBeanBenzControlPanelState(ObservableBoolean ViewModelControlBeanBenzControlPanelState, int fieldId) {
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
        Drawable viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        int viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        boolean viewModelControlBeanBenzControlPanelStateGet = false;
        ObservableBoolean viewModelControlBeanControlPanelClose = null;
        boolean viewModelControlBeanControlPanelCloseGet = false;
        ControlBean viewModelControlBean = null;
        BcVieModel viewModel = this.mViewModel;
        ObservableBoolean viewModelControlBeanBenzControlPanelState = null;
        View.OnClickListener viewModelOnControlClickAndroidViewViewOnClickListener = null;
        if ((15 & dirtyFlags) != 0) {
            if (viewModel != null) {
                viewModelControlBean = viewModel.controlBean;
            }
            if ((dirtyFlags & 13) != 0) {
                if (viewModelControlBean != null) {
                    viewModelControlBeanControlPanelClose = viewModelControlBean.controlPanelClose;
                }
                int i = 0;
                updateRegistration(0, (Observable) viewModelControlBeanControlPanelClose);
                if (viewModelControlBeanControlPanelClose != null) {
                    viewModelControlBeanControlPanelCloseGet = viewModelControlBeanControlPanelClose.get();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (viewModelControlBeanControlPanelCloseGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                if (viewModelControlBeanControlPanelCloseGet) {
                    i = 8;
                }
                viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = i;
            }
            if ((dirtyFlags & 14) != 0) {
                if (viewModelControlBean != null) {
                    viewModelControlBeanBenzControlPanelState = viewModelControlBean.benzControlPanelState;
                }
                updateRegistration(1, (Observable) viewModelControlBeanBenzControlPanelState);
                if (viewModelControlBeanBenzControlPanelState != null) {
                    viewModelControlBeanBenzControlPanelStateGet = viewModelControlBeanBenzControlPanelState.get();
                }
                if ((dirtyFlags & 14) != 0) {
                    if (viewModelControlBeanBenzControlPanelStateGet) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = viewModelControlBeanBenzControlPanelStateGet ? AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_down_selector) : AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_up_selector);
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
        if ((dirtyFlags & 13) != 0) {
            this.controlBtn.setVisibility(viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 14) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 12) != 0) {
            this.controlBtn.setOnClickListener(viewModelOnControlClickAndroidViewViewOnClickListener);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl setValue(BcVieModel value2) {
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

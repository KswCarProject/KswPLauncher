package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class MainKswID7BindingImpl extends MainKswID7Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mLauncherViewModelOnControlClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.iv_top_line, 2);
        sparseIntArray.put(C0899R.C0901id.iv_bottom_line, 3);
        sparseIntArray.put(C0899R.C0901id.viewPage, 4);
        sparseIntArray.put(C0899R.C0901id.iv_bottom_home, 5);
        sparseIntArray.put(C0899R.C0901id.iv_bottom_point, 6);
    }

    public MainKswID7BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private MainKswID7BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[1], (ImageView) bindings[5], (ImageView) bindings[3], (ImageView) bindings[6], (ImageView) bindings[2], (ViewPager) bindings[4]);
        this.mDirtyFlags = -1L;
        this.controlBtn.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (7 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.MainKswID7Binding
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeLauncherViewModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelControlBeanControlPanelClose(ObservableBoolean LauncherViewModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanBenzControlPanelState(ObservableBoolean LauncherViewModelControlBeanBenzControlPanelState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        ObservableBoolean launcherViewModelControlBeanControlPanelClose = null;
        View.OnClickListener launcherViewModelOnControlClickAndroidViewViewOnClickListener = null;
        ObservableBoolean launcherViewModelControlBeanBenzControlPanelState = null;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        int launcherViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        ControlBean launcherViewModelControlBean = null;
        boolean launcherViewModelControlBeanControlPanelCloseGet = false;
        boolean launcherViewModelControlBeanBenzControlPanelStateGet = false;
        Drawable launcherViewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 12) != 0 && launcherViewModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mLauncherViewModelOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mLauncherViewModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                launcherViewModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(launcherViewModel);
            }
            if (launcherViewModel != null) {
                launcherViewModelControlBean = launcherViewModel.controlBean;
            }
            if ((dirtyFlags & 13) != 0) {
                if (launcherViewModelControlBean != null) {
                    launcherViewModelControlBeanControlPanelClose = launcherViewModelControlBean.controlPanelClose;
                }
                updateRegistration(0, launcherViewModelControlBeanControlPanelClose);
                if (launcherViewModelControlBeanControlPanelClose != null) {
                    launcherViewModelControlBeanControlPanelCloseGet = launcherViewModelControlBeanControlPanelClose.get();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (launcherViewModelControlBeanControlPanelCloseGet) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                launcherViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = launcherViewModelControlBeanControlPanelCloseGet ? 8 : 0;
            }
            if ((dirtyFlags & 14) != 0) {
                if (launcherViewModelControlBean != null) {
                    launcherViewModelControlBeanBenzControlPanelState = launcherViewModelControlBean.benzControlPanelState;
                }
                updateRegistration(1, launcherViewModelControlBeanBenzControlPanelState);
                if (launcherViewModelControlBeanBenzControlPanelState != null) {
                    launcherViewModelControlBeanBenzControlPanelStateGet = launcherViewModelControlBeanBenzControlPanelState.get();
                }
                if ((dirtyFlags & 14) != 0) {
                    if (launcherViewModelControlBeanBenzControlPanelStateGet) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                launcherViewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = AppCompatResources.getDrawable(this.controlBtn.getContext(), launcherViewModelControlBeanBenzControlPanelStateGet ? C0899R.C0900drawable.ntg55_ctrlpanel_down_selector : C0899R.C0900drawable.ntg55_ctrlpanel_up_selector);
            }
        }
        if ((dirtyFlags & 13) != 0) {
            this.controlBtn.setVisibility(launcherViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 14) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, launcherViewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((12 & dirtyFlags) != 0) {
            this.controlBtn.setOnClickListener(launcherViewModelOnControlClickAndroidViewViewOnClickListener);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl setValue(LauncherViewModel value) {
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
}

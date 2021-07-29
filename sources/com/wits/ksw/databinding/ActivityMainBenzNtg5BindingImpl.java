package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.model.ControlBean;

public class ActivityMainBenzNtg5BindingImpl extends ActivityMainBenzNtg5Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mMBcVieModelOnControlClickAndroidViewViewOnClickListener;

    public ActivityMainBenzNtg5BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ActivityMainBenzNtg5BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[2], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.controlBtn.setTag((Object) null);
        this.linearLayout3.setTag((Object) null);
        this.recyclerView2.setTag((Object) null);
        setRootTag(root);
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
        if (12 != variableId) {
            return false;
        }
        setMBcVieModel((BcNTG5ViewModel) variable);
        return true;
    }

    public void setMBcVieModel(BcNTG5ViewModel MBcVieModel) {
        this.mMBcVieModel = MBcVieModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMBcVieModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeMBcVieModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeMBcVieModelBcPagePosition((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMBcVieModelControlBeanBenzControlPanelState(ObservableBoolean MBcVieModelControlBeanBenzControlPanelState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanControlPanelClose(ObservableBoolean MBcVieModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMBcVieModelBcPagePosition(ObservableInt MBcVieModelBcPagePosition, int fieldId) {
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean mBcVieModelControlBeanBenzControlPanelStateGet = false;
        Drawable mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        ObservableBoolean mBcVieModelControlBeanBenzControlPanelState = null;
        ControlBean mBcVieModelControlBean = null;
        int mBcVieModelBcPagePositionGet = 0;
        ObservableBoolean mBcVieModelControlBeanControlPanelClose = null;
        ObservableInt mBcVieModelBcPagePosition = null;
        int mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        View.OnClickListener mBcVieModelOnControlClickAndroidViewViewOnClickListener = null;
        BcNTG5ViewModel mBcVieModel = this.mMBcVieModel;
        boolean mBcVieModelControlBeanControlPanelCloseGet = false;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 27) != 0) {
                if (mBcVieModel != null) {
                    mBcVieModelControlBean = mBcVieModel.controlBean;
                }
                if ((dirtyFlags & 25) != 0) {
                    if (mBcVieModelControlBean != null) {
                        mBcVieModelControlBeanBenzControlPanelState = mBcVieModelControlBean.benzControlPanelState;
                    }
                    updateRegistration(0, (Observable) mBcVieModelControlBeanBenzControlPanelState);
                    if (mBcVieModelControlBeanBenzControlPanelState != null) {
                        mBcVieModelControlBeanBenzControlPanelStateGet = mBcVieModelControlBeanBenzControlPanelState.get();
                    }
                    if ((dirtyFlags & 25) != 0) {
                        if (mBcVieModelControlBeanBenzControlPanelStateGet) {
                            dirtyFlags |= 64;
                        } else {
                            dirtyFlags |= 32;
                        }
                    }
                    mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = mBcVieModelControlBeanBenzControlPanelStateGet ? AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_down_selector) : AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_up_selector);
                }
                if ((dirtyFlags & 26) != 0) {
                    if (mBcVieModelControlBean != null) {
                        mBcVieModelControlBeanControlPanelClose = mBcVieModelControlBean.controlPanelClose;
                    }
                    updateRegistration(1, (Observable) mBcVieModelControlBeanControlPanelClose);
                    if (mBcVieModelControlBeanControlPanelClose != null) {
                        mBcVieModelControlBeanControlPanelCloseGet = mBcVieModelControlBeanControlPanelClose.get();
                    }
                    if ((dirtyFlags & 26) != 0) {
                        if (mBcVieModelControlBeanControlPanelCloseGet) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = mBcVieModelControlBeanControlPanelCloseGet ? 8 : 0;
                }
            }
            if ((dirtyFlags & 28) != 0) {
                if (mBcVieModel != null) {
                    mBcVieModelBcPagePosition = mBcVieModel.bcPagePosition;
                }
                updateRegistration(2, (Observable) mBcVieModelBcPagePosition);
                if (mBcVieModelBcPagePosition != null) {
                    mBcVieModelBcPagePositionGet = mBcVieModelBcPagePosition.get();
                }
            }
            if (!((dirtyFlags & 24) == 0 || mBcVieModel == null)) {
                OnClickListenerImpl onClickListenerImpl = this.mMBcVieModelOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                mBcVieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(mBcVieModel);
            }
        }
        if ((dirtyFlags & 26) != 0) {
            this.controlBtn.setVisibility(mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 24) != 0) {
            this.controlBtn.setOnClickListener(mBcVieModelOnControlClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 28) != 0) {
            this.recyclerView2.smoothScrollToPosition(mBcVieModelBcPagePositionGet);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BcNTG5ViewModel value;

        public OnClickListenerImpl setValue(BcNTG5ViewModel value2) {
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

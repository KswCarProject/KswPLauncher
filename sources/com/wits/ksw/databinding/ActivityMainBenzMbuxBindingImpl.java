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
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class ActivityMainBenzMbuxBindingImpl extends ActivityMainBenzMbuxBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mVieModelOnControlClickAndroidViewViewOnClickListener;

    public ActivityMainBenzMbuxBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ActivityMainBenzMbuxBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[1], bindings[2], bindings[0]);
        this.mDirtyFlags = -1;
        this.benzMbuxRecyclerView.setTag((Object) null);
        this.controlBtn.setTag((Object) null);
        this.linearLayout3.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (8 != variableId) {
            return false;
        }
        setVieModel((BcVieModel) variable);
        return true;
    }

    public void setVieModel(@Nullable BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVieModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVieModelBcPagePosition((ObservableInt) object, fieldId);
            case 2:
                return onChangeVieModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVieModelControlBeanControlPanelClose(ObservableBoolean VieModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVieModelBcPagePosition(ObservableInt VieModelBcPagePosition, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVieModelControlBeanBenzControlPanelState(ObservableBoolean VieModelControlBeanBenzControlPanelState, int fieldId) {
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
        ImageView imageView;
        int i;
        OnClickListenerImpl onClickListenerImpl;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableBoolean vieModelControlBeanControlPanelClose = null;
        View.OnClickListener vieModelOnControlClickAndroidViewViewOnClickListener = null;
        ObservableInt vieModelBcPagePosition = null;
        boolean vieModelControlBeanControlPanelCloseGet = false;
        ControlBean vieModelControlBean = null;
        int vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        BcVieModel vieModel = this.mVieModel;
        ObservableBoolean vieModelControlBeanBenzControlPanelState = null;
        int vieModelBcPagePositionGet = 0;
        Drawable vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        boolean vieModelControlBeanBenzControlPanelStateGet = false;
        if ((dirtyFlags & 31) != 0) {
            if (!((dirtyFlags & 24) == 0 || vieModel == null)) {
                if (this.mVieModelOnControlClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mVieModelOnControlClickAndroidViewViewOnClickListener;
                }
                vieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vieModel);
            }
            if ((dirtyFlags & 26) != 0) {
                if (vieModel != null) {
                    vieModelBcPagePosition = vieModel.bcPagePosition;
                }
                updateRegistration(1, (Observable) vieModelBcPagePosition);
                if (vieModelBcPagePosition != null) {
                    vieModelBcPagePositionGet = vieModelBcPagePosition.get();
                }
            }
            if ((29 & dirtyFlags) != 0) {
                if (vieModel != null) {
                    vieModelControlBean = vieModel.controlBean;
                }
                if ((dirtyFlags & 25) != 0) {
                    if (vieModelControlBean != null) {
                        vieModelControlBeanControlPanelClose = vieModelControlBean.controlPanelClose;
                    }
                    int i2 = 0;
                    updateRegistration(0, (Observable) vieModelControlBeanControlPanelClose);
                    if (vieModelControlBeanControlPanelClose != null) {
                        vieModelControlBeanControlPanelCloseGet = vieModelControlBeanControlPanelClose.get();
                    }
                    if ((dirtyFlags & 25) != 0) {
                        if (vieModelControlBeanControlPanelCloseGet) {
                            dirtyFlags |= 64;
                        } else {
                            dirtyFlags |= 32;
                        }
                    }
                    if (vieModelControlBeanControlPanelCloseGet) {
                        i2 = 8;
                    }
                    vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = i2;
                }
                if ((dirtyFlags & 28) != 0) {
                    if (vieModelControlBean != null) {
                        vieModelControlBeanBenzControlPanelState = vieModelControlBean.benzControlPanelState;
                    }
                    updateRegistration(2, (Observable) vieModelControlBeanBenzControlPanelState);
                    if (vieModelControlBeanBenzControlPanelState != null) {
                        vieModelControlBeanBenzControlPanelStateGet = vieModelControlBeanBenzControlPanelState.get();
                    }
                    if ((dirtyFlags & 28) != 0) {
                        if (vieModelControlBeanBenzControlPanelStateGet) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    if (vieModelControlBeanBenzControlPanelStateGet) {
                        imageView = this.controlBtn;
                        i = R.drawable.ntg55_ctrlpanel_down_selector;
                    } else {
                        imageView = this.controlBtn;
                        i = R.drawable.ntg55_ctrlpanel_up_selector;
                    }
                    vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = getDrawableFromResource(imageView, i);
                }
            }
        }
        if ((dirtyFlags & 26) != 0) {
            this.benzMbuxRecyclerView.smoothScrollToPosition(vieModelBcPagePositionGet);
        }
        if ((dirtyFlags & 25) != 0) {
            this.controlBtn.setVisibility(vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 24) != 0) {
            this.controlBtn.setOnClickListener(vieModelOnControlClickAndroidViewViewOnClickListener);
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

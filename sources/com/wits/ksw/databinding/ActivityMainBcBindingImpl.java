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
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class ActivityMainBcBindingImpl extends ActivityMainBcBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback70;
    @Nullable
    private final View.OnClickListener mCallback71;
    private long mDirtyFlags;
    private OnClickListenerImpl mMBcVieModelOnControlClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mMBcVieModelOpenAppsAndroidViewViewOnClickListener;

    static {
        sViewsWithIds.put(R.id.constraintLayout2, 6);
        sViewsWithIds.put(R.id.CustomBcItemBgImageView, 7);
    }

    public ActivityMainBcBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainBcBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[7], bindings[2], bindings[3], bindings[4], bindings[6], bindings[5], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.appsBtn.setTag((Object) null);
        this.bcArrowLeftButton.setTag((Object) null);
        this.bcArrowRightButton.setTag((Object) null);
        this.controlBtn.setTag((Object) null);
        this.linearLayout3.setTag((Object) null);
        this.recyclerView2.setTag((Object) null);
        setRootTag(root);
        this.mCallback70 = new OnClickListener(this, 1);
        this.mCallback71 = new OnClickListener(this, 2);
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
        if (2 != variableId) {
            return false;
        }
        setMBcVieModel((BcVieModel) variable);
        return true;
    }

    public void setMBcVieModel(@Nullable BcVieModel MBcVieModel) {
        this.mMBcVieModel = MBcVieModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMBcVieModelBcPagePosition((ObservableInt) object, fieldId);
            case 1:
                return onChangeMBcVieModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeMBcVieModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMBcVieModelBcPagePosition(ObservableInt MBcVieModelBcPagePosition, int fieldId) {
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

    private boolean onChangeMBcVieModelControlBeanBenzControlPanelState(ObservableBoolean MBcVieModelControlBeanBenzControlPanelState, int fieldId) {
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
        ControlBean mBcVieModelControlBean;
        boolean mBcVieModelControlBeanBenzControlPanelStateGet;
        Drawable drawable;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl1 onClickListenerImpl1;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean mBcVieModelControlBeanBenzControlPanelStateGet2 = false;
        ObservableInt mBcVieModelBcPagePosition = null;
        int mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        View.OnClickListener mBcVieModelOnControlClickAndroidViewViewOnClickListener = null;
        ObservableBoolean mBcVieModelControlBeanControlPanelClose = null;
        Drawable mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        boolean mBcVieModelControlBeanControlPanelCloseGet = false;
        View.OnClickListener mBcVieModelOpenAppsAndroidViewViewOnClickListener = null;
        ObservableBoolean mBcVieModelControlBeanBenzControlPanelState = null;
        int mBcVieModelBcPagePositionGet = 0;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (mBcVieModel != null) {
                    mBcVieModelBcPagePosition = mBcVieModel.bcPagePosition;
                }
                updateRegistration(0, (Observable) mBcVieModelBcPagePosition);
                if (mBcVieModelBcPagePosition != null) {
                    mBcVieModelBcPagePositionGet = mBcVieModelBcPagePosition.get();
                }
            }
            if (!((dirtyFlags & 24) == 0 || mBcVieModel == null)) {
                if (this.mMBcVieModelOnControlClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mMBcVieModelOnControlClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnControlClickAndroidViewViewOnClickListener2 = onClickListenerImpl.setValue(mBcVieModel);
                if (this.mMBcVieModelOpenAppsAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mMBcVieModelOpenAppsAndroidViewViewOnClickListener = onClickListenerImpl1;
                } else {
                    onClickListenerImpl1 = this.mMBcVieModelOpenAppsAndroidViewViewOnClickListener;
                }
                mBcVieModelOpenAppsAndroidViewViewOnClickListener = onClickListenerImpl1.setValue(mBcVieModel);
                mBcVieModelOnControlClickAndroidViewViewOnClickListener = mBcVieModelOnControlClickAndroidViewViewOnClickListener2;
            }
            if ((dirtyFlags & 30) != 0) {
                if (mBcVieModel != null) {
                    mBcVieModelControlBean = mBcVieModel.controlBean;
                } else {
                    mBcVieModelControlBean = null;
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
                            dirtyFlags |= 64;
                        } else {
                            dirtyFlags |= 32;
                        }
                    }
                    mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = mBcVieModelControlBeanControlPanelCloseGet ? 8 : 0;
                }
                if ((dirtyFlags & 28) != 0) {
                    if (mBcVieModelControlBean != null) {
                        mBcVieModelControlBeanBenzControlPanelState = mBcVieModelControlBean.benzControlPanelState;
                    }
                    updateRegistration(2, (Observable) mBcVieModelControlBeanBenzControlPanelState);
                    if (mBcVieModelControlBeanBenzControlPanelState != null) {
                        mBcVieModelControlBeanBenzControlPanelStateGet2 = mBcVieModelControlBeanBenzControlPanelState.get();
                    }
                    if ((dirtyFlags & 28) != 0) {
                        if (mBcVieModelControlBeanBenzControlPanelStateGet2) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    if (mBcVieModelControlBeanBenzControlPanelStateGet2) {
                        mBcVieModelControlBeanBenzControlPanelStateGet = mBcVieModelControlBeanBenzControlPanelStateGet2;
                        drawable = getDrawableFromResource(this.controlBtn, R.drawable.ntg55_ctrlpanel_down_selector);
                    } else {
                        mBcVieModelControlBeanBenzControlPanelStateGet = mBcVieModelControlBeanBenzControlPanelStateGet2;
                        drawable = getDrawableFromResource(this.controlBtn, R.drawable.ntg55_ctrlpanel_up_selector);
                    }
                    mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = drawable;
                    ControlBean controlBean = mBcVieModelControlBean;
                    boolean z = mBcVieModelControlBeanBenzControlPanelStateGet;
                } else {
                    ControlBean controlBean2 = mBcVieModelControlBean;
                }
            }
        }
        if ((dirtyFlags & 24) != 0) {
            this.appsBtn.setOnClickListener(mBcVieModelOpenAppsAndroidViewViewOnClickListener);
            this.controlBtn.setOnClickListener(mBcVieModelOnControlClickAndroidViewViewOnClickListener);
        }
        if ((16 & dirtyFlags) != 0) {
            this.bcArrowLeftButton.setOnClickListener(this.mCallback70);
            this.bcArrowRightButton.setOnClickListener(this.mCallback71);
        }
        if ((dirtyFlags & 26) != 0) {
            this.controlBtn.setVisibility(mBcVieModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, mBcVieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 25) != 0) {
            this.recyclerView2.smoothScrollToPosition(mBcVieModelBcPagePositionGet);
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

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl1 setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.openApps(arg0);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean mBcVieModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                BcVieModel mBcVieModel = this.mMBcVieModel;
                if (mBcVieModel != null) {
                    mBcVieModelJavaLangObjectNull = true;
                }
                if (mBcVieModelJavaLangObjectNull) {
                    mBcVieModel.switchToFirstView();
                    return;
                }
                return;
            case 2:
                BcVieModel mBcVieModel2 = this.mMBcVieModel;
                if (mBcVieModel2 != null) {
                    mBcVieModelJavaLangObjectNull = true;
                }
                if (mBcVieModelJavaLangObjectNull) {
                    mBcVieModel2.switchToSecondView();
                    return;
                }
                return;
            default:
                return;
        }
    }
}

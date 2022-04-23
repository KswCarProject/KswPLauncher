package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class Ntg630ControlPopupBindingImpl extends Ntg630ControlPopupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl1 mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mMBcVieModelOnEspClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;

    public Ntg630ControlPopupBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Ntg630ControlPopupBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[7], bindings[8], bindings[2], bindings[3], bindings[4], bindings[5], bindings[6], bindings[1], bindings[0]);
        this.mDirtyFlags = -1;
        this.brightnessBtnLeft.setTag((Object) null);
        this.brightnessBtnRight.setTag((Object) null);
        this.controlBtn1.setTag((Object) null);
        this.controlBtn2.setTag((Object) null);
        this.controlBtn3.setTag((Object) null);
        this.foldLeftBtn.setTag((Object) null);
        this.foldRightBtn.setTag((Object) null);
        this.imageView.setTag((Object) null);
        this.linearLayout.setTag((Object) null);
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
        setMBcVieModel((BcVieModel) variable);
        return true;
    }

    public void setMBcVieModel(BcVieModel MBcVieModel) {
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
                return onChangeMBcVieModelControlBeanChassis((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeMBcVieModelControlBeanPassairbar((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeMBcVieModelControlBeanRdarAssistance((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMBcVieModelControlBeanChassis(ObservableBoolean MBcVieModelControlBeanChassis, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanPassairbar(ObservableBoolean MBcVieModelControlBeanPassairbar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanRdarAssistance(ObservableBoolean MBcVieModelControlBeanRdarAssistance, int fieldId) {
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
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
        ObservableBoolean mBcVieModelControlBeanChassis;
        ObservableBoolean mBcVieModelControlBeanChassis2;
        ObservableBoolean mBcVieModelControlBeanChassis3;
        ObservableBoolean mBcVieModelControlBeanRdarAssistance;
        Drawable drawable;
        Drawable drawable2;
        ObservableBoolean mBcVieModelControlBeanChassis4;
        ObservableBoolean mBcVieModelControlBeanChassis5;
        Drawable drawable3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
        View.OnClickListener mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener = null;
        ObservableBoolean mBcVieModelControlBeanPassairbar = null;
        View.OnClickListener mBcVieModelOnEspClickAndroidViewViewOnClickListener = null;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
        boolean mBcVieModelControlBeanChassisGet = false;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = null;
        boolean mBcVieModelControlBeanRdarAssistanceGet = false;
        ControlBean mBcVieModelControlBean = null;
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2 = null;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = null;
        boolean mBcVieModelControlBeanPassairbarGet = false;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 24) == 0) {
                mBcVieModelControlBeanChassis = null;
            } else if (mBcVieModel != null) {
                mBcVieModelControlBeanChassis = null;
                OnClickListenerImpl onClickListenerImpl = this.mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener mBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(mBcVieModel);
                OnClickListenerImpl1 onClickListenerImpl1 = this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl1 == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl1;
                }
                View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = onClickListenerImpl1.setValue(mBcVieModel);
                OnClickListenerImpl2 onClickListenerImpl2 = this.mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                View.OnClickListener mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener2 = onClickListenerImpl2.setValue(mBcVieModel);
                OnClickListenerImpl3 onClickListenerImpl3 = this.mMBcVieModelOnEspClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.mMBcVieModelOnEspClickAndroidViewViewOnClickListener = onClickListenerImpl3;
                }
                View.OnClickListener mBcVieModelOnEspClickAndroidViewViewOnClickListener2 = onClickListenerImpl3.setValue(mBcVieModel);
                OnClickListenerImpl4 onClickListenerImpl4 = this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl4 == null) {
                    onClickListenerImpl4 = new OnClickListenerImpl4();
                    this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl4;
                }
                View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = onClickListenerImpl4.setValue(mBcVieModel);
                OnClickListenerImpl5 onClickListenerImpl5 = this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                if (onClickListenerImpl5 == null) {
                    onClickListenerImpl5 = new OnClickListenerImpl5();
                    this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl5;
                }
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = onClickListenerImpl5.setValue(mBcVieModel);
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = mBcVieModelOnEspClickAndroidViewViewOnClickListener2;
                mBcVieModelOnEspClickAndroidViewViewOnClickListener = mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener2;
                mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2;
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = mBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
            } else {
                mBcVieModelControlBeanChassis = null;
            }
            if (mBcVieModel != null) {
                mBcVieModelControlBean = mBcVieModel.controlBean;
            }
            BcVieModel bcVieModel = mBcVieModel;
            if ((dirtyFlags & 25) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanChassis4 = mBcVieModelControlBean.chassis;
                } else {
                    mBcVieModelControlBeanChassis4 = mBcVieModelControlBeanChassis;
                }
                updateRegistration(0, (Observable) mBcVieModelControlBeanChassis4);
                if (mBcVieModelControlBeanChassis4 != null) {
                    mBcVieModelControlBeanChassisGet = mBcVieModelControlBeanChassis4.get();
                }
                if ((dirtyFlags & 25) != 0) {
                    if (mBcVieModelControlBeanChassisGet) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
                if (mBcVieModelControlBeanChassisGet) {
                    mBcVieModelControlBeanChassis5 = mBcVieModelControlBeanChassis4;
                    drawable3 = AppCompatResources.getDrawable(this.controlBtn1.getContext(), R.drawable.ntg55_ctrlpanel_ntg30_btn_on);
                } else {
                    mBcVieModelControlBeanChassis5 = mBcVieModelControlBeanChassis4;
                    drawable3 = AppCompatResources.getDrawable(this.controlBtn1.getContext(), R.drawable.ntg55_ctrlpanel_ntg30_btn_off_n);
                }
                mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = drawable3;
                mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis5;
            } else {
                mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis;
            }
            if ((dirtyFlags & 26) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanPassairbar = mBcVieModelControlBean.passairbar;
                }
                updateRegistration(1, (Observable) mBcVieModelControlBeanPassairbar);
                if (mBcVieModelControlBeanPassairbar != null) {
                    mBcVieModelControlBeanPassairbarGet = mBcVieModelControlBeanPassairbar.get();
                }
                if ((dirtyFlags & 26) != 0) {
                    if (mBcVieModelControlBeanPassairbarGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (mBcVieModelControlBeanPassairbarGet) {
                    mBcVieModelControlBeanChassis3 = mBcVieModelControlBeanChassis2;
                    drawable2 = AppCompatResources.getDrawable(this.imageView.getContext(), R.drawable.ntg55_ctrlpanel_airbag_on);
                } else {
                    mBcVieModelControlBeanChassis3 = mBcVieModelControlBeanChassis2;
                    drawable2 = AppCompatResources.getDrawable(this.imageView.getContext(), R.drawable.ntg55_ctrlpanel_airbag_off);
                }
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2 = drawable2;
            } else {
                mBcVieModelControlBeanChassis3 = mBcVieModelControlBeanChassis2;
            }
            if ((dirtyFlags & 28) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRdarAssistance = mBcVieModelControlBean.rdarAssistance;
                } else {
                    mBcVieModelControlBeanRdarAssistance = null;
                }
                updateRegistration(2, (Observable) mBcVieModelControlBeanRdarAssistance);
                if (mBcVieModelControlBeanRdarAssistance != null) {
                    mBcVieModelControlBeanRdarAssistanceGet = mBcVieModelControlBeanRdarAssistance.get();
                }
                if ((dirtyFlags & 28) != 0) {
                    if (mBcVieModelControlBeanRdarAssistanceGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (mBcVieModelControlBeanRdarAssistanceGet) {
                    ObservableBoolean observableBoolean = mBcVieModelControlBeanRdarAssistance;
                    drawable = AppCompatResources.getDrawable(this.controlBtn3.getContext(), R.drawable.ntg55_ctrlpanel_ntg30_btn_on);
                } else {
                    drawable = AppCompatResources.getDrawable(this.controlBtn3.getContext(), R.drawable.ntg55_ctrlpanel_ntg30_btn_off_n);
                }
                mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = drawable;
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2;
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2;
                ObservableBoolean observableBoolean2 = mBcVieModelControlBeanChassis3;
            } else {
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2;
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2;
                ObservableBoolean observableBoolean3 = mBcVieModelControlBeanChassis3;
            }
        } else {
            BcVieModel bcVieModel2 = mBcVieModel;
            mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
            mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = null;
        }
        if ((dirtyFlags & 24) != 0) {
            ObservableBoolean observableBoolean4 = mBcVieModelControlBeanPassairbar;
            this.brightnessBtnLeft.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.brightnessBtnRight.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.controlBtn1.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2);
            this.controlBtn2.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.controlBtn3.setOnClickListener(mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener);
            this.foldLeftBtn.setOnClickListener(mBcVieModelOnEspClickAndroidViewViewOnClickListener);
            this.foldRightBtn.setOnClickListener(mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN);
        }
        if ((dirtyFlags & 26) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imageView, mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff);
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
            this.value.onFoldRigtClick(arg0);
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
            this.value.onAuxiliaryRadarClick(arg0);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl2 setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onFoldLeftClick(arg0);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl3 setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onEspClick(arg0);
        }
    }

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl4 setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onHighChasssisClick(arg0);
        }
    }

    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl5 setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.showBrightnessDialog(arg0);
        }
    }
}

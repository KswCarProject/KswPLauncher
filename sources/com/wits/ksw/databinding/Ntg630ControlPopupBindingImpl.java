package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class Ntg630ControlPopupBindingImpl extends Ntg630ControlPopupBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl4 mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mMBcVieModelOnEspClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;

    public Ntg630ControlPopupBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Ntg630ControlPopupBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[7], bindings[2], bindings[3], bindings[4], bindings[5], bindings[6], bindings[1], bindings[0]);
        this.mDirtyFlags = -1;
        this.brightnessBtn.setTag((Object) null);
        this.controlBtn1.setTag((Object) null);
        this.controlBtn2.setTag((Object) null);
        this.controlBtn3.setTag((Object) null);
        this.foldLeftBtn.setTag((Object) null);
        this.foldRightBtn.setTag((Object) null);
        this.imageView.setTag((Object) null);
        this.linearLayout.setTag((Object) null);
        View view = root;
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
                return onChangeMBcVieModelControlBeanPassairbar((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeMBcVieModelControlBeanRdarAssistance((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeMBcVieModelControlBeanChassis((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMBcVieModelControlBeanPassairbar(ObservableBoolean MBcVieModelControlBeanPassairbar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanRdarAssistance(ObservableBoolean MBcVieModelControlBeanRdarAssistance, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanChassis(ObservableBoolean MBcVieModelControlBeanChassis, int fieldId) {
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
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2;
        ControlBean mBcVieModelControlBean;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
        ObservableBoolean mBcVieModelControlBeanChassis;
        ObservableBoolean mBcVieModelControlBeanChassis2;
        Drawable drawable;
        ImageView imageView;
        Drawable drawable2;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl2 onClickListenerImpl2;
        OnClickListenerImpl3 onClickListenerImpl3;
        OnClickListenerImpl4 onClickListenerImpl4;
        OnClickListenerImpl5 onClickListenerImpl5;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4 = null;
        boolean mBcVieModelControlBeanRdarAssistanceGet = false;
        boolean mBcVieModelControlBeanPassairbarGet = false;
        ObservableBoolean mBcVieModelControlBeanPassairbar = null;
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
        ObservableBoolean mBcVieModelControlBeanRdarAssistance = null;
        View.OnClickListener mBcVieModelOnEspClickAndroidViewViewOnClickListener = null;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = null;
        View.OnClickListener mBcVieModelOnEspClickAndroidViewViewOnClickListener2 = null;
        boolean mBcVieModelControlBeanChassisGet = false;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3 = null;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = null;
        if ((dirtyFlags & 31) != 0) {
            if (!((dirtyFlags & 24) == 0 || mBcVieModel == null)) {
                if (this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
                }
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4 = onClickListenerImpl.setValue(mBcVieModel);
                if (this.mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener = onClickListenerImpl1;
                } else {
                    onClickListenerImpl1 = this.mMBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener = onClickListenerImpl1.setValue(mBcVieModel);
                if (this.mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                } else {
                    onClickListenerImpl2 = this.mMBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener = onClickListenerImpl2.setValue(mBcVieModel);
                if (this.mMBcVieModelOnEspClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.mMBcVieModelOnEspClickAndroidViewViewOnClickListener = onClickListenerImpl3;
                } else {
                    onClickListenerImpl3 = this.mMBcVieModelOnEspClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnEspClickAndroidViewViewOnClickListener3 = onClickListenerImpl3.setValue(mBcVieModel);
                if (this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl4 = new OnClickListenerImpl4();
                    this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl4;
                } else {
                    onClickListenerImpl4 = this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener4 = onClickListenerImpl4.setValue(mBcVieModel);
                if (this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl5 = new OnClickListenerImpl5();
                    this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl5;
                } else {
                    onClickListenerImpl5 = this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                }
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3 = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener4;
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl5.setValue(mBcVieModel);
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = mBcVieModelOnFoldLeftClickAndroidViewViewOnClickListener;
                mBcVieModelOnEspClickAndroidViewViewOnClickListener2 = mBcVieModelOnEspClickAndroidViewViewOnClickListener3;
                mBcVieModelOnEspClickAndroidViewViewOnClickListener = mBcVieModelOnFoldRigtClickAndroidViewViewOnClickListener;
            }
            if (mBcVieModel != null) {
                mBcVieModelControlBean = mBcVieModel.controlBean;
            } else {
                mBcVieModelControlBean = null;
            }
            if ((dirtyFlags & 25) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanPassairbar = mBcVieModelControlBean.passairbar;
                }
                updateRegistration(0, (Observable) mBcVieModelControlBeanPassairbar);
                if (mBcVieModelControlBeanPassairbar != null) {
                    mBcVieModelControlBeanPassairbarGet = mBcVieModelControlBeanPassairbar.get();
                }
                if ((dirtyFlags & 25) != 0) {
                    if (mBcVieModelControlBeanPassairbarGet) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
                if (mBcVieModelControlBeanPassairbarGet) {
                    mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4;
                    drawable2 = getDrawableFromResource(this.imageView, R.drawable.ntg55_ctrlpanel_airbag_on);
                } else {
                    mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4;
                    drawable2 = getDrawableFromResource(this.imageView, R.drawable.ntg55_ctrlpanel_airbag_off);
                }
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = drawable2;
            } else {
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4;
            }
            int i = ((dirtyFlags & 26) > 0 ? 1 : ((dirtyFlags & 26) == 0 ? 0 : -1));
            int i2 = R.drawable.ntg55_ctrlpanel_ntg30_btn_on;
            if (i != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRdarAssistance = mBcVieModelControlBean.rdarAssistance;
                }
                updateRegistration(1, (Observable) mBcVieModelControlBeanRdarAssistance);
                if (mBcVieModelControlBeanRdarAssistance != null) {
                    mBcVieModelControlBeanRdarAssistanceGet = mBcVieModelControlBeanRdarAssistance.get();
                }
                if ((dirtyFlags & 26) != 0) {
                    if (mBcVieModelControlBeanRdarAssistanceGet) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                if (mBcVieModelControlBeanRdarAssistanceGet) {
                    imageView = this.controlBtn3;
                } else {
                    imageView = this.controlBtn3;
                    i2 = R.drawable.ntg55_ctrlpanel_ntg30_btn_off_n;
                }
                mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = getDrawableFromResource(imageView, i2);
            }
            if ((dirtyFlags & 28) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanChassis = mBcVieModelControlBean.chassis;
                } else {
                    mBcVieModelControlBeanChassis = null;
                }
                updateRegistration(2, (Observable) mBcVieModelControlBeanChassis);
                if (mBcVieModelControlBeanChassis != null) {
                    mBcVieModelControlBeanChassisGet = mBcVieModelControlBeanChassis.get();
                }
                if ((dirtyFlags & 28) != 0) {
                    if (mBcVieModelControlBeanChassisGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (mBcVieModelControlBeanChassisGet) {
                    mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis;
                    drawable = getDrawableFromResource(this.controlBtn1, R.drawable.ntg55_ctrlpanel_ntg30_btn_on);
                } else {
                    mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis;
                    drawable = getDrawableFromResource(this.controlBtn1, R.drawable.ntg55_ctrlpanel_ntg30_btn_off_n);
                }
                mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = drawable;
                ControlBean controlBean = mBcVieModelControlBean;
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3;
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
                ObservableBoolean observableBoolean = mBcVieModelControlBeanChassis2;
            } else {
                ControlBean controlBean2 = mBcVieModelControlBean;
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3;
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
            }
        } else {
            mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
            mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
            mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = null;
        }
        boolean z = mBcVieModelControlBeanChassisGet;
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN2 = mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN;
        boolean mBcVieModelControlBeanChassisGet2 = z;
        Drawable drawable3 = mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN;
        Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN2 = drawable3;
        if ((dirtyFlags & 24) != 0) {
            ObservableBoolean observableBoolean2 = mBcVieModelControlBeanPassairbar;
            this.brightnessBtn.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2);
            this.controlBtn1.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.controlBtn2.setOnClickListener(mBcVieModelOnEspClickAndroidViewViewOnClickListener2);
            this.controlBtn3.setOnClickListener(mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
            this.foldLeftBtn.setOnClickListener(mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2);
            this.foldRightBtn.setOnClickListener(mBcVieModelOnEspClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN2);
        }
        if ((dirtyFlags & 26) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN2);
        }
        if ((dirtyFlags & 25) != 0) {
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
            this.value.onHighChasssisClick(arg0);
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
            this.value.onFoldRigtClick(arg0);
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
            this.value.onAuxiliaryRadarClick(arg0);
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

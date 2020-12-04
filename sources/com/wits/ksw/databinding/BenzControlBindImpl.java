package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class BenzControlBindImpl extends BenzControlBind {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private OnClickListenerImpl1 mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mMBcVieModelOnSportClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;

    static {
        sViewsWithIds.put(R.id.netgControlImageview, 9);
    }

    public BenzControlBindImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BenzControlBindImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (ImageView) null, bindings[6], bindings[5], bindings[7], bindings[8], bindings[2], bindings[3], bindings[4], bindings[1], bindings[0], bindings[9]);
        this.mDirtyFlags = -1;
        this.brightnessBtnLeft.setTag((Object) null);
        this.brightnessBtnRight.setTag((Object) null);
        this.checkBox.setTag((Object) null);
        this.checkBox2.setTag((Object) null);
        this.controlBtn1.setTag((Object) null);
        this.controlBtn2.setTag((Object) null);
        this.controlBtn3.setTag((Object) null);
        this.imageView.setTag((Object) null);
        this.linearLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
            this.mDirtyFlags |= 64;
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
            case 3:
                return onChangeMBcVieModelControlBeanLeftBrightnessAdjus((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeMBcVieModelControlBeanSport((ObservableBoolean) object, fieldId);
            case 5:
                return onChangeMBcVieModelControlBeanRightBrightnessAdjus((ObservableBoolean) object, fieldId);
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

    private boolean onChangeMBcVieModelControlBeanLeftBrightnessAdjus(ObservableBoolean MBcVieModelControlBeanLeftBrightnessAdjus, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanSport(ObservableBoolean MBcVieModelControlBeanSport, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanRightBrightnessAdjus(ObservableBoolean MBcVieModelControlBeanRightBrightnessAdjus, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        ObservableBoolean mBcVieModelControlBeanPassairbar;
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff;
        boolean mBcVieModelControlBeanRightBrightnessAdjusGet;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
        View.OnClickListener mBcVieModelOnSportClickAndroidViewViewOnClickListener;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
        CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
        ControlBean mBcVieModelControlBean;
        ObservableBoolean mBcVieModelControlBeanChassis;
        ObservableBoolean mBcVieModelControlBeanRightBrightnessAdjus;
        ObservableBoolean mBcVieModelControlBeanSport;
        long dirtyFlags2;
        Drawable drawable;
        ObservableBoolean mBcVieModelControlBeanLeftBrightnessAdjus;
        long dirtyFlags3;
        Drawable drawable2;
        ImageView imageView;
        long dirtyFlags4;
        Drawable drawable3;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl2 onClickListenerImpl2;
        OnClickListenerImpl3 onClickListenerImpl3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean mBcVieModelControlBeanRdarAssistanceGet = false;
        CompoundButton.OnCheckedChangeListener mBcVieModelLeftOnCheckedChangeListener = null;
        boolean mBcVieModelControlBeanPassairbarGet = false;
        ObservableBoolean mBcVieModelControlBeanPassairbar2 = null;
        boolean mBcVieModelControlBeanSportGet = false;
        boolean mBcVieModelControlBeanLeftBrightnessAdjusGet = false;
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
        Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = null;
        ObservableBoolean mBcVieModelControlBeanRdarAssistance = null;
        Drawable mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = null;
        boolean mBcVieModelControlBeanChassisGet = false;
        boolean mBcVieModelControlBeanRightBrightnessAdjusGet2 = false;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff2 = null;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = null;
        View.OnClickListener mBcVieModelOnSportClickAndroidViewViewOnClickListener2 = null;
        CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener2 = null;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = null;
        if ((dirtyFlags & 255) != 0) {
            if ((dirtyFlags & 192) == 0 || mBcVieModel == null) {
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = null;
            } else {
                if (this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4 = onClickListenerImpl.setValue(mBcVieModel);
                mBcVieModelLeftOnCheckedChangeListener = mBcVieModel.leftOnCheckedChangeListener;
                View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener5 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener4;
                if (this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl1;
                } else {
                    onClickListenerImpl1 = this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3 = onClickListenerImpl1.setValue(mBcVieModel);
                if (this.mMBcVieModelOnSportClickAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.mMBcVieModelOnSportClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                } else {
                    onClickListenerImpl2 = this.mMBcVieModelOnSportClickAndroidViewViewOnClickListener;
                }
                View.OnClickListener mBcVieModelOnSportClickAndroidViewViewOnClickListener3 = onClickListenerImpl2.setValue(mBcVieModel);
                CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener3 = mBcVieModel.rightOnCheckedChangeListener;
                if (this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl3;
                } else {
                    onClickListenerImpl3 = this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                }
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = onClickListenerImpl3.setValue(mBcVieModel);
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener5;
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener3;
                mBcVieModelOnSportClickAndroidViewViewOnClickListener2 = mBcVieModelOnSportClickAndroidViewViewOnClickListener3;
                mBcVieModelRightOnCheckedChangeListener2 = mBcVieModelRightOnCheckedChangeListener3;
            }
            if (mBcVieModel != null) {
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2;
                mBcVieModelControlBean = mBcVieModel.controlBean;
            } else {
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3 = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener2;
                mBcVieModelControlBean = null;
            }
            if ((dirtyFlags & 193) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanPassairbar2 = mBcVieModelControlBean.passairbar;
                }
                BcVieModel bcVieModel = mBcVieModel;
                updateRegistration(0, (Observable) mBcVieModelControlBeanPassairbar2);
                if (mBcVieModelControlBeanPassairbar2 != null) {
                    mBcVieModelControlBeanPassairbarGet = mBcVieModelControlBeanPassairbar2.get();
                }
                if ((dirtyFlags & 193) != 0) {
                    if (mBcVieModelControlBeanPassairbarGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                if (mBcVieModelControlBeanPassairbarGet) {
                    dirtyFlags4 = dirtyFlags;
                    drawable3 = getDrawableFromResource(this.imageView, R.drawable.ntg55_ctrlpanel_airbag_on);
                } else {
                    dirtyFlags4 = dirtyFlags;
                    drawable3 = getDrawableFromResource(this.imageView, R.drawable.ntg55_ctrlpanel_airbag_off);
                }
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = drawable3;
                dirtyFlags = dirtyFlags4;
            }
            int i = ((dirtyFlags & 194) > 0 ? 1 : ((dirtyFlags & 194) == 0 ? 0 : -1));
            int i2 = R.drawable.ntg55_ctrlpanel_btn_on;
            if (i != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRdarAssistance = mBcVieModelControlBean.rdarAssistance;
                }
                updateRegistration(1, (Observable) mBcVieModelControlBeanRdarAssistance);
                if (mBcVieModelControlBeanRdarAssistance != null) {
                    mBcVieModelControlBeanRdarAssistanceGet = mBcVieModelControlBeanRdarAssistance.get();
                }
                if ((dirtyFlags & 194) != 0) {
                    if (mBcVieModelControlBeanRdarAssistanceGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (mBcVieModelControlBeanRdarAssistanceGet) {
                    imageView = this.controlBtn3;
                } else {
                    imageView = this.controlBtn3;
                    i2 = R.drawable.ntg55_ctrlpanel_btn_off;
                }
                mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = getDrawableFromResource(imageView, i2);
            }
            if ((dirtyFlags & 196) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanChassis = mBcVieModelControlBean.chassis;
                } else {
                    mBcVieModelControlBeanChassis = null;
                }
                updateRegistration(2, (Observable) mBcVieModelControlBeanChassis);
                if (mBcVieModelControlBeanChassis != null) {
                    mBcVieModelControlBeanChassisGet = mBcVieModelControlBeanChassis.get();
                }
                if ((dirtyFlags & 196) != 0) {
                    if (mBcVieModelControlBeanChassisGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                if (mBcVieModelControlBeanChassisGet) {
                    dirtyFlags3 = dirtyFlags;
                    drawable2 = getDrawableFromResource(this.controlBtn1, R.drawable.ntg55_ctrlpanel_btn_on);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable2 = getDrawableFromResource(this.controlBtn1, R.drawable.ntg55_ctrlpanel_btn_off);
                }
                mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff2 = drawable2;
                dirtyFlags = dirtyFlags3;
            } else {
                mBcVieModelControlBeanChassis = null;
            }
            if ((dirtyFlags & 200) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanLeftBrightnessAdjus = mBcVieModelControlBean.leftBrightnessAdjus;
                } else {
                    mBcVieModelControlBeanLeftBrightnessAdjus = null;
                }
                ObservableBoolean observableBoolean = mBcVieModelControlBeanChassis;
                updateRegistration(3, (Observable) mBcVieModelControlBeanLeftBrightnessAdjus);
                if (mBcVieModelControlBeanLeftBrightnessAdjus != null) {
                    ObservableBoolean observableBoolean2 = mBcVieModelControlBeanLeftBrightnessAdjus;
                    mBcVieModelControlBeanLeftBrightnessAdjusGet = mBcVieModelControlBeanLeftBrightnessAdjus.get();
                } else {
                    ObservableBoolean observableBoolean3 = mBcVieModelControlBeanLeftBrightnessAdjus;
                }
            }
            if ((dirtyFlags & 208) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanSport = mBcVieModelControlBean.sport;
                } else {
                    mBcVieModelControlBeanSport = null;
                }
                updateRegistration(4, (Observable) mBcVieModelControlBeanSport);
                if (mBcVieModelControlBeanSport != null) {
                    mBcVieModelControlBeanSportGet = mBcVieModelControlBeanSport.get();
                }
                if ((dirtyFlags & 208) != 0) {
                    if (mBcVieModelControlBeanSportGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                if (mBcVieModelControlBeanSportGet) {
                    dirtyFlags2 = dirtyFlags;
                    drawable = getDrawableFromResource(this.controlBtn2, R.drawable.ntg55_ctrlpanel_btn_on);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    drawable = getDrawableFromResource(this.controlBtn2, R.drawable.ntg55_ctrlpanel_btn_off);
                }
                mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = drawable;
                ObservableBoolean observableBoolean4 = mBcVieModelControlBeanSport;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 224) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRightBrightnessAdjus = mBcVieModelControlBean.rightBrightnessAdjus;
                } else {
                    mBcVieModelControlBeanRightBrightnessAdjus = null;
                }
                updateRegistration(5, (Observable) mBcVieModelControlBeanRightBrightnessAdjus);
                if (mBcVieModelControlBeanRightBrightnessAdjus != null) {
                    mBcVieModelControlBeanRightBrightnessAdjusGet2 = mBcVieModelControlBeanRightBrightnessAdjus.get();
                }
            }
            mBcVieModelRightOnCheckedChangeListener = mBcVieModelRightOnCheckedChangeListener2;
            mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2;
            mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener3;
            boolean z = mBcVieModelControlBeanRightBrightnessAdjusGet2;
            mBcVieModelOnSportClickAndroidViewViewOnClickListener = mBcVieModelOnSportClickAndroidViewViewOnClickListener2;
            mBcVieModelControlBeanPassairbar = mBcVieModelControlBeanPassairbar2;
            mBcVieModelControlBeanRightBrightnessAdjusGet = z;
            Drawable drawable4 = mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff2;
            mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2;
            mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = drawable4;
        } else {
            BcVieModel bcVieModel2 = mBcVieModel;
            mBcVieModelRightOnCheckedChangeListener = null;
            mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = null;
            mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
            mBcVieModelOnSportClickAndroidViewViewOnClickListener = null;
            mBcVieModelControlBeanPassairbar = null;
            mBcVieModelControlBeanRightBrightnessAdjusGet = false;
            mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
            mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = null;
        }
        if ((dirtyFlags & 192) != 0) {
            ObservableBoolean observableBoolean5 = mBcVieModelControlBeanPassairbar;
            this.brightnessBtnLeft.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.brightnessBtnRight.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.checkBox.setOnCheckedChangeListener(mBcVieModelLeftOnCheckedChangeListener);
            this.checkBox2.setOnCheckedChangeListener(mBcVieModelRightOnCheckedChangeListener);
            this.controlBtn1.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.controlBtn2.setOnClickListener(mBcVieModelOnSportClickAndroidViewViewOnClickListener);
            this.controlBtn3.setOnClickListener(mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 200) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox, mBcVieModelControlBeanLeftBrightnessAdjusGet);
        }
        if ((dirtyFlags & 224) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox2, mBcVieModelControlBeanRightBrightnessAdjusGet);
        }
        if ((dirtyFlags & 196) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 208) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn2, mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 194) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 193) != 0) {
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
            this.value.onSportClick(arg0);
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
            this.value.showBrightnessDialog(arg0);
        }
    }
}

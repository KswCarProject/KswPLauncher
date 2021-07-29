package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class BenzControlBindImpl extends BenzControlBind {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mMBcVieModelOnSportClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.netgControlImageview, 9);
    }

    public BenzControlBindImpl(DataBindingComponent bindingComponent, View root) {
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
            this.mDirtyFlags |= 64;
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
                return onChangeMBcVieModelControlBeanLeftBrightnessAdjus((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeMBcVieModelControlBeanSport((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeMBcVieModelControlBeanRightBrightnessAdjus((ObservableBoolean) object, fieldId);
            case 5:
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

    private boolean onChangeMBcVieModelControlBeanLeftBrightnessAdjus(ObservableBoolean MBcVieModelControlBeanLeftBrightnessAdjus, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanSport(ObservableBoolean MBcVieModelControlBeanSport, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanRightBrightnessAdjus(ObservableBoolean MBcVieModelControlBeanRightBrightnessAdjus, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMBcVieModelControlBeanRdarAssistance(ObservableBoolean MBcVieModelControlBeanRdarAssistance, int fieldId) {
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
        Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff;
        CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener;
        boolean mBcVieModelControlBeanLeftBrightnessAdjusGet;
        CompoundButton.OnCheckedChangeListener mBcVieModelLeftOnCheckedChangeListener;
        boolean mBcVieModelControlBeanRightBrightnessAdjusGet;
        ObservableBoolean mBcVieModelControlBeanChassis;
        ControlBean mBcVieModelControlBean;
        ObservableBoolean mBcVieModelControlBeanChassis2;
        ObservableBoolean mBcVieModelControlBeanChassis3;
        ObservableBoolean mBcVieModelControlBeanRdarAssistance;
        ControlBean mBcVieModelControlBean2;
        Drawable drawable;
        ObservableBoolean mBcVieModelControlBeanRightBrightnessAdjus;
        ObservableBoolean mBcVieModelControlBeanSport;
        long dirtyFlags2;
        Drawable drawable2;
        long dirtyFlags3;
        Drawable drawable3;
        long dirtyFlags4;
        Drawable drawable4;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = null;
        Drawable mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = null;
        View.OnClickListener mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
        View.OnClickListener mBcVieModelOnSportClickAndroidViewViewOnClickListener = null;
        ObservableBoolean mBcVieModelControlBeanPassairbar = null;
        boolean mBcVieModelControlBeanSportGet = false;
        boolean mBcVieModelControlBeanChassisGet = false;
        View.OnClickListener mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
        boolean mBcVieModelControlBeanRdarAssistanceGet = false;
        ObservableBoolean mBcVieModelControlBeanLeftBrightnessAdjus = null;
        CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener2 = null;
        boolean mBcVieModelControlBeanLeftBrightnessAdjusGet2 = false;
        boolean mBcVieModelControlBeanRightBrightnessAdjusGet2 = false;
        CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener3 = null;
        Drawable mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2 = null;
        View.OnClickListener mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = null;
        boolean mBcVieModelControlBeanPassairbarGet = false;
        BcVieModel mBcVieModel = this.mMBcVieModel;
        if ((dirtyFlags & 255) != 0) {
            if ((dirtyFlags & 192) == 0) {
                mBcVieModelControlBeanChassis = null;
            } else if (mBcVieModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mMBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(mBcVieModel);
                OnClickListenerImpl1 onClickListenerImpl1 = this.mMBcVieModelOnSportClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl1 == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.mMBcVieModelOnSportClickAndroidViewViewOnClickListener = onClickListenerImpl1;
                }
                mBcVieModelOnSportClickAndroidViewViewOnClickListener = onClickListenerImpl1.setValue(mBcVieModel);
                OnClickListenerImpl2 onClickListenerImpl2 = this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.mMBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl2.setValue(mBcVieModel);
                CompoundButton.OnCheckedChangeListener mBcVieModelLeftOnCheckedChangeListener2 = mBcVieModel.leftOnCheckedChangeListener;
                mBcVieModelControlBeanChassis = null;
                CompoundButton.OnCheckedChangeListener mBcVieModelRightOnCheckedChangeListener4 = mBcVieModel.rightOnCheckedChangeListener;
                OnClickListenerImpl3 onClickListenerImpl3 = this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.mMBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl3;
                }
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2 = onClickListenerImpl3.setValue(mBcVieModel);
                mBcVieModelRightOnCheckedChangeListener3 = mBcVieModelRightOnCheckedChangeListener4;
                mBcVieModelRightOnCheckedChangeListener2 = mBcVieModelLeftOnCheckedChangeListener2;
            } else {
                mBcVieModelControlBeanChassis = null;
            }
            if (mBcVieModel != null) {
                mBcVieModelControlBean = mBcVieModel.controlBean;
            } else {
                mBcVieModelControlBean = null;
            }
            BcVieModel bcVieModel = mBcVieModel;
            if ((dirtyFlags & 193) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanChassis2 = mBcVieModelControlBean.chassis;
                } else {
                    mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis;
                }
                updateRegistration(0, (Observable) mBcVieModelControlBeanChassis2);
                if (mBcVieModelControlBeanChassis2 != null) {
                    mBcVieModelControlBeanChassisGet = mBcVieModelControlBeanChassis2.get();
                }
                if ((dirtyFlags & 193) != 0) {
                    if (mBcVieModelControlBeanChassisGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (mBcVieModelControlBeanChassisGet) {
                    dirtyFlags4 = dirtyFlags;
                    drawable4 = AppCompatResources.getDrawable(this.controlBtn1.getContext(), R.drawable.ntg55_ctrlpanel_btn_on);
                } else {
                    dirtyFlags4 = dirtyFlags;
                    drawable4 = AppCompatResources.getDrawable(this.controlBtn1.getContext(), R.drawable.ntg55_ctrlpanel_btn_off);
                }
                mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = drawable4;
                dirtyFlags = dirtyFlags4;
            } else {
                mBcVieModelControlBeanChassis2 = mBcVieModelControlBeanChassis;
            }
            if ((dirtyFlags & 194) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanPassairbar = mBcVieModelControlBean.passairbar;
                }
                updateRegistration(1, (Observable) mBcVieModelControlBeanPassairbar);
                if (mBcVieModelControlBeanPassairbar != null) {
                    mBcVieModelControlBeanPassairbarGet = mBcVieModelControlBeanPassairbar.get();
                }
                if ((dirtyFlags & 194) != 0) {
                    if (mBcVieModelControlBeanPassairbarGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                if (mBcVieModelControlBeanPassairbarGet) {
                    dirtyFlags3 = dirtyFlags;
                    drawable3 = AppCompatResources.getDrawable(this.imageView.getContext(), R.drawable.ntg55_ctrlpanel_airbag_on);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable3 = AppCompatResources.getDrawable(this.imageView.getContext(), R.drawable.ntg55_ctrlpanel_airbag_off);
                }
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2 = drawable3;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 196) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanLeftBrightnessAdjus = mBcVieModelControlBean.leftBrightnessAdjus;
                }
                updateRegistration(2, (Observable) mBcVieModelControlBeanLeftBrightnessAdjus);
                if (mBcVieModelControlBeanLeftBrightnessAdjus != null) {
                    mBcVieModelControlBeanLeftBrightnessAdjusGet2 = mBcVieModelControlBeanLeftBrightnessAdjus.get();
                }
            }
            if ((dirtyFlags & 200) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanSport = mBcVieModelControlBean.sport;
                } else {
                    mBcVieModelControlBeanSport = null;
                }
                mBcVieModelControlBeanChassis3 = mBcVieModelControlBeanChassis2;
                updateRegistration(3, (Observable) mBcVieModelControlBeanSport);
                if (mBcVieModelControlBeanSport != null) {
                    mBcVieModelControlBeanSportGet = mBcVieModelControlBeanSport.get();
                }
                if ((dirtyFlags & 200) != 0) {
                    if (mBcVieModelControlBeanSportGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                if (mBcVieModelControlBeanSportGet) {
                    dirtyFlags2 = dirtyFlags;
                    drawable2 = AppCompatResources.getDrawable(this.controlBtn2.getContext(), R.drawable.ntg55_ctrlpanel_btn_on);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    drawable2 = AppCompatResources.getDrawable(this.controlBtn2.getContext(), R.drawable.ntg55_ctrlpanel_btn_off);
                }
                mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = drawable2;
                ObservableBoolean observableBoolean = mBcVieModelControlBeanSport;
                dirtyFlags = dirtyFlags2;
            } else {
                mBcVieModelControlBeanChassis3 = mBcVieModelControlBeanChassis2;
            }
            if ((dirtyFlags & 208) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRightBrightnessAdjus = mBcVieModelControlBean.rightBrightnessAdjus;
                } else {
                    mBcVieModelControlBeanRightBrightnessAdjus = null;
                }
                updateRegistration(4, (Observable) mBcVieModelControlBeanRightBrightnessAdjus);
                if (mBcVieModelControlBeanRightBrightnessAdjus != null) {
                    mBcVieModelControlBeanRightBrightnessAdjusGet2 = mBcVieModelControlBeanRightBrightnessAdjus.get();
                    ObservableBoolean observableBoolean2 = mBcVieModelControlBeanRightBrightnessAdjus;
                } else {
                    ObservableBoolean observableBoolean3 = mBcVieModelControlBeanRightBrightnessAdjus;
                }
            }
            if ((dirtyFlags & 224) != 0) {
                if (mBcVieModelControlBean != null) {
                    mBcVieModelControlBeanRdarAssistance = mBcVieModelControlBean.rdarAssistance;
                } else {
                    mBcVieModelControlBeanRdarAssistance = null;
                }
                updateRegistration(5, (Observable) mBcVieModelControlBeanRdarAssistance);
                if (mBcVieModelControlBeanRdarAssistance != null) {
                    mBcVieModelControlBeanRdarAssistanceGet = mBcVieModelControlBeanRdarAssistance.get();
                }
                if ((dirtyFlags & 224) != 0) {
                    if (mBcVieModelControlBeanRdarAssistanceGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                if (mBcVieModelControlBeanRdarAssistanceGet) {
                    mBcVieModelControlBean2 = mBcVieModelControlBean;
                    drawable = AppCompatResources.getDrawable(this.controlBtn3.getContext(), R.drawable.ntg55_ctrlpanel_btn_on);
                } else {
                    mBcVieModelControlBean2 = mBcVieModelControlBean;
                    drawable = AppCompatResources.getDrawable(this.controlBtn3.getContext(), R.drawable.ntg55_ctrlpanel_btn_off);
                }
                Drawable mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2 = drawable;
                ObservableBoolean observableBoolean4 = mBcVieModelControlBeanRdarAssistance;
                mBcVieModelLeftOnCheckedChangeListener = mBcVieModelRightOnCheckedChangeListener2;
                mBcVieModelControlBeanLeftBrightnessAdjusGet = mBcVieModelControlBeanLeftBrightnessAdjusGet2;
                mBcVieModelControlBeanRightBrightnessAdjusGet = mBcVieModelControlBeanRightBrightnessAdjusGet2;
                ObservableBoolean observableBoolean5 = mBcVieModelControlBeanChassis3;
                ControlBean controlBean = mBcVieModelControlBean2;
                ObservableBoolean observableBoolean6 = mBcVieModelControlBeanPassairbar;
                boolean mBcVieModelControlBeanRightBrightnessAdjusGet3 = mBcVieModelControlBeanSportGet;
                mBcVieModelRightOnCheckedChangeListener = mBcVieModelRightOnCheckedChangeListener3;
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2;
                boolean z = mBcVieModelControlBeanChassisGet;
                boolean z2 = mBcVieModelControlBeanRdarAssistanceGet;
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2;
                mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2;
            } else {
                ControlBean mBcVieModelControlBean3 = mBcVieModelControlBean;
                mBcVieModelLeftOnCheckedChangeListener = mBcVieModelRightOnCheckedChangeListener2;
                mBcVieModelControlBeanLeftBrightnessAdjusGet = mBcVieModelControlBeanLeftBrightnessAdjusGet2;
                mBcVieModelControlBeanRightBrightnessAdjusGet = mBcVieModelControlBeanRightBrightnessAdjusGet2;
                ObservableBoolean observableBoolean7 = mBcVieModelControlBeanChassis3;
                ControlBean controlBean2 = mBcVieModelControlBean3;
                ObservableBoolean observableBoolean8 = mBcVieModelControlBeanPassairbar;
                boolean mBcVieModelControlBeanRightBrightnessAdjusGet4 = mBcVieModelControlBeanSportGet;
                mBcVieModelRightOnCheckedChangeListener = mBcVieModelRightOnCheckedChangeListener3;
                mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff2;
                boolean z3 = mBcVieModelControlBeanChassisGet;
                mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener2;
                mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = null;
            }
        } else {
            BcVieModel bcVieModel2 = mBcVieModel;
            mBcVieModelLeftOnCheckedChangeListener = null;
            mBcVieModelControlBeanLeftBrightnessAdjusGet = false;
            mBcVieModelControlBeanRightBrightnessAdjusGet = false;
            mBcVieModelRightOnCheckedChangeListener = null;
            mBcVieModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
            mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener = null;
            mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = null;
        }
        if ((dirtyFlags & 192) != 0) {
            ObservableBoolean observableBoolean9 = mBcVieModelControlBeanLeftBrightnessAdjus;
            this.brightnessBtnLeft.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.brightnessBtnRight.setOnClickListener(mBcVieModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.checkBox.setOnCheckedChangeListener(mBcVieModelLeftOnCheckedChangeListener);
            this.checkBox2.setOnCheckedChangeListener(mBcVieModelRightOnCheckedChangeListener);
            this.controlBtn1.setOnClickListener(mBcVieModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.controlBtn2.setOnClickListener(mBcVieModelOnSportClickAndroidViewViewOnClickListener);
            this.controlBtn3.setOnClickListener(mBcVieModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 196) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox, mBcVieModelControlBeanLeftBrightnessAdjusGet);
        }
        if ((dirtyFlags & 208) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox2, mBcVieModelControlBeanRightBrightnessAdjusGet);
        }
        if ((dirtyFlags & 193) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, mBcVieModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 200) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn2, mBcVieModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 224) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, mBcVieModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 194) != 0) {
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
            this.value.onAuxiliaryRadarClick(arg0);
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
            this.value.onSportClick(arg0);
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
            this.value.onHighChasssisClick(arg0);
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

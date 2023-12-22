package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class BenzControlBindImpl extends BenzControlBind {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* renamed from: mLauncherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener */
    private OnClickListenerImpl2 f178x91270436;

    /* renamed from: mLauncherViewModelOnHighChasssisClickAndroidViewViewOnClickListener */
    private OnClickListenerImpl3 f179xf249ca93;
    private OnClickListenerImpl mLauncherViewModelOnSportClickAndroidViewViewOnClickListener;

    /* renamed from: mLauncherViewModelShowBrightnessDialogAndroidViewViewOnClickListener */
    private OnClickListenerImpl1 f180xc17a85f1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.netgControlImageview, 9);
    }

    public BenzControlBindImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private BenzControlBindImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, null, (ImageView) bindings[6], (ImageView) bindings[5], (CheckBox) bindings[7], (CheckBox) bindings[8], (ImageView) bindings[2], (ImageView) bindings[3], (ImageView) bindings[4], (ImageView) bindings[1], (ConstraintLayout) bindings[0], (ImageView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.brightnessBtnLeft.setTag(null);
        this.brightnessBtnRight.setTag(null);
        this.checkBox.setTag(null);
        this.checkBox2.setTag(null);
        this.controlBtn1.setTag(null);
        this.controlBtn2.setTag(null);
        this.controlBtn3.setTag(null);
        this.imageView.setTag(null);
        this.linearLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        if (19 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BenzControlBind
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelControlBeanRightBrightnessAdjus((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeLauncherViewModelControlBeanSport((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeLauncherViewModelControlBeanRdarAssistance((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeLauncherViewModelControlBeanLeftBrightnessAdjus((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeLauncherViewModelControlBeanPassairbar((ObservableBoolean) object, fieldId);
            case 5:
                return onChangeLauncherViewModelControlBeanChassis((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelControlBeanRightBrightnessAdjus(ObservableBoolean LauncherViewModelControlBeanRightBrightnessAdjus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanSport(ObservableBoolean LauncherViewModelControlBeanSport, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanRdarAssistance(ObservableBoolean LauncherViewModelControlBeanRdarAssistance, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanLeftBrightnessAdjus(ObservableBoolean LauncherViewModelControlBeanLeftBrightnessAdjus, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanPassairbar(ObservableBoolean LauncherViewModelControlBeanPassairbar, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanChassis(ObservableBoolean LauncherViewModelControlBeanChassis, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        boolean launcherViewModelControlBeanRightBrightnessAdjusGet;
        Drawable launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff;
        Drawable launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff;
        CompoundButton.OnCheckedChangeListener launcherViewModelRightOnCheckedChangeListener;
        ObservableBoolean launcherViewModelControlBeanRightBrightnessAdjus;
        ControlBean launcherViewModelControlBean;
        ObservableBoolean launcherViewModelControlBeanPassairbar;
        ObservableBoolean launcherViewModelControlBeanRightBrightnessAdjus2;
        ObservableBoolean launcherViewModelControlBeanPassairbar2;
        ObservableBoolean launcherViewModelControlBeanChassis;
        long dirtyFlags2;
        Context context;
        int i;
        long dirtyFlags3;
        Drawable drawable;
        ObservableBoolean launcherViewModelControlBeanLeftBrightnessAdjus;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Drawable launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = null;
        ObservableBoolean launcherViewModelControlBeanSport = null;
        View.OnClickListener launcherViewModelOnSportClickAndroidViewViewOnClickListener = null;
        View.OnClickListener launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener = null;
        boolean launcherViewModelControlBeanLeftBrightnessAdjusGet = false;
        CompoundButton.OnCheckedChangeListener launcherViewModelLeftOnCheckedChangeListener = null;
        Drawable launcherViewModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
        View.OnClickListener launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
        ObservableBoolean launcherViewModelControlBeanRdarAssistance = null;
        View.OnClickListener launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
        boolean launcherViewModelControlBeanRightBrightnessAdjusGet2 = false;
        boolean launcherViewModelControlBeanSportGet = false;
        Drawable launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2 = null;
        boolean launcherViewModelControlBeanRdarAssistanceGet = false;
        Drawable launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff2 = null;
        boolean launcherViewModelControlBeanPassairbarGet = false;
        CompoundButton.OnCheckedChangeListener launcherViewModelRightOnCheckedChangeListener2 = null;
        boolean launcherViewModelControlBeanChassisGet = false;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        if ((dirtyFlags & 255) == 0) {
            launcherViewModelControlBeanRightBrightnessAdjusGet = false;
            launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = null;
            launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = null;
            launcherViewModelRightOnCheckedChangeListener = null;
        } else {
            if ((dirtyFlags & 192) == 0) {
                launcherViewModelControlBeanRightBrightnessAdjus = null;
            } else if (launcherViewModel == null) {
                launcherViewModelControlBeanRightBrightnessAdjus = null;
            } else {
                launcherViewModelControlBeanRightBrightnessAdjus = null;
                OnClickListenerImpl onClickListenerImpl = this.mLauncherViewModelOnSportClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mLauncherViewModelOnSportClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                launcherViewModelOnSportClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(launcherViewModel);
                OnClickListenerImpl1 onClickListenerImpl1 = this.f180xc17a85f1;
                if (onClickListenerImpl1 == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.f180xc17a85f1 = onClickListenerImpl1;
                }
                launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener = onClickListenerImpl1.setValue(launcherViewModel);
                launcherViewModelLeftOnCheckedChangeListener = launcherViewModel.leftOnCheckedChangeListener;
                OnClickListenerImpl2 onClickListenerImpl2 = this.f178x91270436;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.f178x91270436 = onClickListenerImpl2;
                }
                launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = onClickListenerImpl2.setValue(launcherViewModel);
                OnClickListenerImpl3 onClickListenerImpl3 = this.f179xf249ca93;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.f179xf249ca93 = onClickListenerImpl3;
                }
                launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener = onClickListenerImpl3.setValue(launcherViewModel);
                launcherViewModelRightOnCheckedChangeListener2 = launcherViewModel.rightOnCheckedChangeListener;
            }
            if (launcherViewModel == null) {
                launcherViewModelControlBean = null;
            } else {
                launcherViewModelControlBean = launcherViewModel.controlBean;
            }
            if ((dirtyFlags & 193) == 0) {
                launcherViewModelControlBeanPassairbar = null;
                launcherViewModelControlBeanRightBrightnessAdjus2 = launcherViewModelControlBeanRightBrightnessAdjus;
            } else {
                launcherViewModelControlBeanRightBrightnessAdjus2 = launcherViewModelControlBean != null ? launcherViewModelControlBean.rightBrightnessAdjus : launcherViewModelControlBeanRightBrightnessAdjus;
                launcherViewModelControlBeanPassairbar = null;
                updateRegistration(0, launcherViewModelControlBeanRightBrightnessAdjus2);
                if (launcherViewModelControlBeanRightBrightnessAdjus2 != null) {
                    launcherViewModelControlBeanRightBrightnessAdjusGet2 = launcherViewModelControlBeanRightBrightnessAdjus2.get();
                }
            }
            if ((dirtyFlags & 194) != 0) {
                if (launcherViewModelControlBean != null) {
                    launcherViewModelControlBeanSport = launcherViewModelControlBean.sport;
                }
                updateRegistration(1, launcherViewModelControlBeanSport);
                if (launcherViewModelControlBeanSport != null) {
                    launcherViewModelControlBeanSportGet = launcherViewModelControlBeanSport.get();
                }
                if ((dirtyFlags & 194) != 0) {
                    if (launcherViewModelControlBeanSportGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff2 = launcherViewModelControlBeanSportGet ? AppCompatResources.getDrawable(this.controlBtn2.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_btn_on) : AppCompatResources.getDrawable(this.controlBtn2.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_btn_off);
            }
            if ((dirtyFlags & 196) != 0) {
                if (launcherViewModelControlBean != null) {
                    launcherViewModelControlBeanRdarAssistance = launcherViewModelControlBean.rdarAssistance;
                }
                updateRegistration(2, launcherViewModelControlBeanRdarAssistance);
                if (launcherViewModelControlBeanRdarAssistance != null) {
                    launcherViewModelControlBeanRdarAssistanceGet = launcherViewModelControlBeanRdarAssistance.get();
                }
                if ((dirtyFlags & 196) != 0) {
                    if (launcherViewModelControlBeanRdarAssistanceGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2 = AppCompatResources.getDrawable(this.controlBtn3.getContext(), launcherViewModelControlBeanRdarAssistanceGet ? C0899R.C0900drawable.ntg55_ctrlpanel_btn_on : C0899R.C0900drawable.ntg55_ctrlpanel_btn_off);
            }
            if ((dirtyFlags & 200) != 0) {
                if (launcherViewModelControlBean == null) {
                    launcherViewModelControlBeanLeftBrightnessAdjus = null;
                } else {
                    launcherViewModelControlBeanLeftBrightnessAdjus = launcherViewModelControlBean.leftBrightnessAdjus;
                }
                updateRegistration(3, launcherViewModelControlBeanLeftBrightnessAdjus);
                if (launcherViewModelControlBeanLeftBrightnessAdjus != null) {
                    launcherViewModelControlBeanLeftBrightnessAdjusGet = launcherViewModelControlBeanLeftBrightnessAdjus.get();
                }
            }
            if ((dirtyFlags & 208) == 0) {
                launcherViewModelControlBeanPassairbar2 = launcherViewModelControlBeanPassairbar;
            } else {
                if (launcherViewModelControlBean == null) {
                    launcherViewModelControlBeanPassairbar2 = launcherViewModelControlBeanPassairbar;
                } else {
                    launcherViewModelControlBeanPassairbar2 = launcherViewModelControlBean.passairbar;
                }
                updateRegistration(4, launcherViewModelControlBeanPassairbar2);
                if (launcherViewModelControlBeanPassairbar2 != null) {
                    launcherViewModelControlBeanPassairbarGet = launcherViewModelControlBeanPassairbar2.get();
                }
                if ((dirtyFlags & 208) != 0) {
                    if (launcherViewModelControlBeanPassairbarGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (launcherViewModelControlBeanPassairbarGet) {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.imageView.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_airbag_on);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.imageView.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_airbag_off);
                }
                launcherViewModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = drawable;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 224) != 0) {
                if (launcherViewModelControlBean == null) {
                    launcherViewModelControlBeanChassis = null;
                } else {
                    launcherViewModelControlBeanChassis = launcherViewModelControlBean.chassis;
                }
                updateRegistration(5, launcherViewModelControlBeanChassis);
                if (launcherViewModelControlBeanChassis != null) {
                    launcherViewModelControlBeanChassisGet = launcherViewModelControlBeanChassis.get();
                }
                if ((dirtyFlags & 224) != 0) {
                    if (launcherViewModelControlBeanChassisGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                if (launcherViewModelControlBeanChassisGet) {
                    context = this.controlBtn1.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.C0900drawable.ntg55_ctrlpanel_btn_on;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.controlBtn1.getContext();
                    i = C0899R.C0900drawable.ntg55_ctrlpanel_btn_off;
                }
                launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff = AppCompatResources.getDrawable(context, i);
                launcherViewModelControlBeanRightBrightnessAdjusGet = launcherViewModelControlBeanRightBrightnessAdjusGet2;
                launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2;
                dirtyFlags = dirtyFlags2;
                launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff2;
                launcherViewModelRightOnCheckedChangeListener = launcherViewModelRightOnCheckedChangeListener2;
            } else {
                launcherViewModelControlBeanRightBrightnessAdjusGet = launcherViewModelControlBeanRightBrightnessAdjusGet2;
                launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff = launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff2;
                launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff = launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff2;
                launcherViewModelRightOnCheckedChangeListener = launcherViewModelRightOnCheckedChangeListener2;
            }
        }
        if ((dirtyFlags & 192) != 0) {
            this.brightnessBtnLeft.setOnClickListener(launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.brightnessBtnRight.setOnClickListener(launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener);
            this.checkBox.setOnCheckedChangeListener(launcherViewModelLeftOnCheckedChangeListener);
            this.checkBox2.setOnCheckedChangeListener(launcherViewModelRightOnCheckedChangeListener);
            this.controlBtn1.setOnClickListener(launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.controlBtn2.setOnClickListener(launcherViewModelOnSportClickAndroidViewViewOnClickListener);
            this.controlBtn3.setOnClickListener(launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 200) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox, launcherViewModelControlBeanLeftBrightnessAdjusGet);
        }
        if ((dirtyFlags & 193) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkBox2, launcherViewModelControlBeanRightBrightnessAdjusGet);
        }
        if ((dirtyFlags & 224) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelBtnOnControlBtn1AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 194) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn2, launcherViewModelControlBeanSportControlBtn2AndroidDrawableNtg55CtrlpanelBtnOnControlBtn2AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 196) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelBtnOnControlBtn3AndroidDrawableNtg55CtrlpanelBtnOff);
        }
        if ((dirtyFlags & 208) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imageView, launcherViewModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff);
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
            this.value.onSportClick(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl1 setValue(LauncherViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.showBrightnessDialog(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl2 setValue(LauncherViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onAuxiliaryRadarClick(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl3 setValue(LauncherViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onHighChasssisClick(arg0);
        }
    }
}

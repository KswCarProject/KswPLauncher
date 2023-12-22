package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.ControlBean;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class Ntg630ControlPopupBindingImpl extends Ntg630ControlPopupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* renamed from: mLauncherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener */
    private OnClickListenerImpl3 f184x91270436;
    private OnClickListenerImpl mLauncherViewModelOnEspClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener;

    /* renamed from: mLauncherViewModelOnHighChasssisClickAndroidViewViewOnClickListener */
    private OnClickListenerImpl4 f185xf249ca93;

    /* renamed from: mLauncherViewModelShowBrightnessDialogAndroidViewViewOnClickListener */
    private OnClickListenerImpl1 f186xc17a85f1;

    public Ntg630ControlPopupBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private Ntg630ControlPopupBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[2], (ImageView) bindings[3], (ImageView) bindings[4], (ImageView) bindings[5], (ImageView) bindings[6], (ImageView) bindings[1], (RelativeLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.brightnessBtnLeft.setTag(null);
        this.brightnessBtnRight.setTag(null);
        this.controlBtn1.setTag(null);
        this.controlBtn2.setTag(null);
        this.controlBtn3.setTag(null);
        this.foldLeftBtn.setTag(null);
        this.foldRightBtn.setTag(null);
        this.imageView.setTag(null);
        this.linearLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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

    @Override // com.wits.ksw.databinding.Ntg630ControlPopupBinding
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelControlBeanRdarAssistance((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeLauncherViewModelControlBeanPassairbar((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeLauncherViewModelControlBeanChassis((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelControlBeanRdarAssistance(ObservableBoolean LauncherViewModelControlBeanRdarAssistance, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanPassairbar(ObservableBoolean LauncherViewModelControlBeanPassairbar, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelControlBeanChassis(ObservableBoolean LauncherViewModelControlBeanChassis, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01aa  */
    @Override // android.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void executeBindings() {
        long dirtyFlags;
        View.OnClickListener launcherViewModelOnEspClickAndroidViewViewOnClickListener;
        View.OnClickListener launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener;
        View.OnClickListener launcherViewModelOnEspClickAndroidViewViewOnClickListener2;
        ControlBean launcherViewModelControlBean;
        ObservableBoolean launcherViewModelControlBeanChassis;
        long dirtyFlags2;
        Context context;
        int i;
        long dirtyFlags3;
        Drawable drawable;
        long dirtyFlags4;
        Drawable drawable2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnClickListener launcherViewModelOnEspClickAndroidViewViewOnClickListener3 = null;
        View.OnClickListener launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener = null;
        View.OnClickListener launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = null;
        Drawable launcherViewModelControlBeanPassairbarImageViewAndroidDrawableNtg55CtrlpanelAirbagOnImageViewAndroidDrawableNtg55CtrlpanelAirbagOff = null;
        View.OnClickListener launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener = null;
        Drawable launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        ObservableBoolean launcherViewModelControlBeanRdarAssistance = null;
        Drawable launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = null;
        View.OnClickListener launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener2 = null;
        View.OnClickListener launcherViewModelOnFoldLeftClickAndroidViewViewOnClickListener = null;
        boolean launcherViewModelControlBeanRdarAssistanceGet = false;
        boolean launcherViewModelControlBeanPassairbarGet = false;
        boolean launcherViewModelControlBeanChassisGet = false;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        ObservableBoolean launcherViewModelControlBeanPassairbar = null;
        if ((dirtyFlags & 31) != 0) {
            if ((dirtyFlags & 24) == 0) {
                launcherViewModelOnEspClickAndroidViewViewOnClickListener = null;
            } else if (launcherViewModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mLauncherViewModelOnEspClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mLauncherViewModelOnEspClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener launcherViewModelOnEspClickAndroidViewViewOnClickListener4 = onClickListenerImpl.setValue(launcherViewModel);
                OnClickListenerImpl1 onClickListenerImpl1 = this.f186xc17a85f1;
                if (onClickListenerImpl1 == null) {
                    onClickListenerImpl1 = new OnClickListenerImpl1();
                    this.f186xc17a85f1 = onClickListenerImpl1;
                }
                View.OnClickListener launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener2 = onClickListenerImpl1.setValue(launcherViewModel);
                OnClickListenerImpl2 onClickListenerImpl2 = this.mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl2();
                    this.mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                View.OnClickListener launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener2 = onClickListenerImpl2.setValue(launcherViewModel);
                OnClickListenerImpl3 onClickListenerImpl3 = this.f184x91270436;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl3();
                    this.f184x91270436 = onClickListenerImpl3;
                }
                View.OnClickListener launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2 = onClickListenerImpl3.setValue(launcherViewModel);
                OnClickListenerImpl4 onClickListenerImpl4 = this.f185xf249ca93;
                if (onClickListenerImpl4 == null) {
                    onClickListenerImpl4 = new OnClickListenerImpl4();
                    this.f185xf249ca93 = onClickListenerImpl4;
                }
                View.OnClickListener launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener3 = onClickListenerImpl4.setValue(launcherViewModel);
                OnClickListenerImpl5 onClickListenerImpl5 = this.mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl5 == null) {
                    onClickListenerImpl5 = new OnClickListenerImpl5();
                    this.mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener = onClickListenerImpl5;
                }
                launcherViewModelOnFoldLeftClickAndroidViewViewOnClickListener = onClickListenerImpl5.setValue(launcherViewModel);
                launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener2 = launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener3;
                launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener = launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener2;
                launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener2;
                launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener = launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener2;
                launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener = launcherViewModelOnEspClickAndroidViewViewOnClickListener4;
                if (launcherViewModel != null) {
                    launcherViewModelOnEspClickAndroidViewViewOnClickListener2 = launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener;
                    launcherViewModelControlBean = null;
                } else {
                    launcherViewModelOnEspClickAndroidViewViewOnClickListener2 = launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener;
                    launcherViewModelControlBean = launcherViewModel.controlBean;
                }
                if ((dirtyFlags & 25) != 0) {
                    if (launcherViewModelControlBean != null) {
                        launcherViewModelControlBeanRdarAssistance = launcherViewModelControlBean.rdarAssistance;
                    }
                    updateRegistration(0, launcherViewModelControlBeanRdarAssistance);
                    if (launcherViewModelControlBeanRdarAssistance != null) {
                        launcherViewModelControlBeanRdarAssistanceGet = launcherViewModelControlBeanRdarAssistance.get();
                    }
                    if ((dirtyFlags & 25) != 0) {
                        if (launcherViewModelControlBeanRdarAssistanceGet) {
                            dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        } else {
                            dirtyFlags |= 512;
                        }
                    }
                    if (launcherViewModelControlBeanRdarAssistanceGet) {
                        dirtyFlags4 = dirtyFlags;
                        drawable2 = AppCompatResources.getDrawable(this.controlBtn3.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_ntg30_btn_on);
                    } else {
                        dirtyFlags4 = dirtyFlags;
                        drawable2 = AppCompatResources.getDrawable(this.controlBtn3.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_ntg30_btn_off_n);
                    }
                    launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = drawable2;
                    dirtyFlags = dirtyFlags4;
                }
                if ((dirtyFlags & 26) != 0) {
                    if (launcherViewModelControlBean != null) {
                        launcherViewModelControlBeanPassairbar = launcherViewModelControlBean.passairbar;
                    }
                    updateRegistration(1, launcherViewModelControlBeanPassairbar);
                    if (launcherViewModelControlBeanPassairbar != null) {
                        launcherViewModelControlBeanPassairbarGet = launcherViewModelControlBeanPassairbar.get();
                    }
                    if ((dirtyFlags & 26) != 0) {
                        if (launcherViewModelControlBeanPassairbarGet) {
                            dirtyFlags |= 64;
                        } else {
                            dirtyFlags |= 32;
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
                if ((dirtyFlags & 28) == 0) {
                    if (launcherViewModelControlBean == null) {
                        launcherViewModelControlBeanChassis = null;
                    } else {
                        launcherViewModelControlBeanChassis = launcherViewModelControlBean.chassis;
                    }
                    updateRegistration(2, launcherViewModelControlBeanChassis);
                    if (launcherViewModelControlBeanChassis != null) {
                        launcherViewModelControlBeanChassisGet = launcherViewModelControlBeanChassis.get();
                    }
                    if ((dirtyFlags & 28) != 0) {
                        if (launcherViewModelControlBeanChassisGet) {
                            dirtyFlags |= 256;
                        } else {
                            dirtyFlags |= 128;
                        }
                    }
                    if (launcherViewModelControlBeanChassisGet) {
                        context = this.controlBtn1.getContext();
                        dirtyFlags2 = dirtyFlags;
                        i = C0899R.C0900drawable.ntg55_ctrlpanel_ntg30_btn_on;
                    } else {
                        dirtyFlags2 = dirtyFlags;
                        context = this.controlBtn1.getContext();
                        i = C0899R.C0900drawable.ntg55_ctrlpanel_ntg30_btn_off_n;
                    }
                    launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN = AppCompatResources.getDrawable(context, i);
                    launcherViewModelOnEspClickAndroidViewViewOnClickListener3 = launcherViewModelOnEspClickAndroidViewViewOnClickListener2;
                    dirtyFlags = dirtyFlags2;
                } else {
                    launcherViewModelOnEspClickAndroidViewViewOnClickListener3 = launcherViewModelOnEspClickAndroidViewViewOnClickListener2;
                }
            } else {
                launcherViewModelOnEspClickAndroidViewViewOnClickListener = null;
            }
            launcherViewModelShowBrightnessDialogAndroidViewViewOnClickListener = launcherViewModelOnEspClickAndroidViewViewOnClickListener;
            if (launcherViewModel != null) {
            }
            if ((dirtyFlags & 25) != 0) {
            }
            if ((dirtyFlags & 26) != 0) {
            }
            if ((dirtyFlags & 28) == 0) {
            }
        }
        if ((dirtyFlags & 24) != 0) {
            this.brightnessBtnLeft.setOnClickListener(launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener);
            this.brightnessBtnRight.setOnClickListener(launcherViewModelOnFoldRigtClickAndroidViewViewOnClickListener);
            this.controlBtn1.setOnClickListener(launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener2);
            this.controlBtn2.setOnClickListener(launcherViewModelOnEspClickAndroidViewViewOnClickListener3);
            this.controlBtn3.setOnClickListener(launcherViewModelOnHighChasssisClickAndroidViewViewOnClickListener);
            this.foldLeftBtn.setOnClickListener(launcherViewModelOnFoldLeftClickAndroidViewViewOnClickListener);
            this.foldRightBtn.setOnClickListener(launcherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 28) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn1, launcherViewModelControlBeanChassisControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn1AndroidDrawableNtg55CtrlpanelNtg30BtnOffN);
        }
        if ((dirtyFlags & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn3, launcherViewModelControlBeanRdarAssistanceControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOnControlBtn3AndroidDrawableNtg55CtrlpanelNtg30BtnOffN);
        }
        if ((dirtyFlags & 26) != 0) {
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
            this.value.onEspClick(arg0);
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
            this.value.onFoldRigtClick(arg0);
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
            this.value.onAuxiliaryRadarClick(arg0);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl4 setValue(LauncherViewModel value) {
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

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl5 setValue(LauncherViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onFoldLeftClick(arg0);
        }
    }
}

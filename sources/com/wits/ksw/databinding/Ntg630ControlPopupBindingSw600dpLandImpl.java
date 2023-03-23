package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Ntg630ControlPopupBindingSw600dpLandImpl extends Ntg630ControlPopupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl3 mLauncherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mLauncherViewModelOnEspClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mLauncherViewModelOnHighChasssisClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mLauncherViewModelShowBrightnessDialogAndroidViewViewOnClickListener;

    public Ntg630ControlPopupBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Ntg630ControlPopupBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        if (19 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelControlBeanPassairbar(ObservableBoolean LauncherViewModelControlBeanPassairbar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelControlBeanChassis(ObservableBoolean LauncherViewModelControlBeanChassis, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r36 = this;
            r1 = r36
            r2 = 0
            monitor-enter(r36)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0204 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0204 }
            monitor-exit(r36)     // Catch:{ all -> 0x0204 }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            com.wits.ksw.launcher.model.LauncherViewModel r4 = r1.mLauncherViewModel
            r5 = 0
            r21 = 0
            r22 = 31
            long r22 = r2 & r22
            r19 = 0
            int r22 = (r22 > r19 ? 1 : (r22 == r19 ? 0 : -1))
            r23 = 24
            r25 = 26
            r27 = 25
            r29 = 28
            if (r22 == 0) goto L_0x01b1
            long r31 = r2 & r23
            int r22 = (r31 > r19 ? 1 : (r31 == r19 ? 0 : -1))
            if (r22 == 0) goto L_0x00a6
            if (r4 == 0) goto L_0x00a3
            r22 = r0
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl r0 = r1.mLauncherViewModelOnEspClickAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x004a
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl r0 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl
            r0.<init>()
            r1.mLauncherViewModelOnEspClickAndroidViewViewOnClickListener = r0
        L_0x004a:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl r0 = r0.setValue(r4)
            r22 = r0
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl1 r0 = r1.mLauncherViewModelShowBrightnessDialogAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x005b
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl1 r0 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl1
            r0.<init>()
            r1.mLauncherViewModelShowBrightnessDialogAndroidViewViewOnClickListener = r0
        L_0x005b:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl1 r0 = r0.setValue(r4)
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl2 r6 = r1.mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener
            if (r6 != 0) goto L_0x006a
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl2 r6 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl2
            r6.<init>()
            r1.mLauncherViewModelOnFoldRigtClickAndroidViewViewOnClickListener = r6
        L_0x006a:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl2 r6 = r6.setValue(r4)
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl3 r7 = r1.mLauncherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener
            if (r7 != 0) goto L_0x0079
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl3 r7 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl3
            r7.<init>()
            r1.mLauncherViewModelOnAuxiliaryRadarClickAndroidViewViewOnClickListener = r7
        L_0x0079:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl3 r7 = r7.setValue(r4)
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl4 r9 = r1.mLauncherViewModelOnHighChasssisClickAndroidViewViewOnClickListener
            if (r9 != 0) goto L_0x0088
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl4 r9 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl4
            r9.<init>()
            r1.mLauncherViewModelOnHighChasssisClickAndroidViewViewOnClickListener = r9
        L_0x0088:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl4 r9 = r9.setValue(r4)
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl5 r13 = r1.mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener
            if (r13 != 0) goto L_0x0097
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl5 r13 = new com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl5
            r13.<init>()
            r1.mLauncherViewModelOnFoldLeftClickAndroidViewViewOnClickListener = r13
        L_0x0097:
            com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl$OnClickListenerImpl5 r13 = r13.setValue(r4)
            r14 = r13
            r13 = r9
            r9 = r7
            r7 = r6
            r6 = r0
            r0 = r22
            goto L_0x00aa
        L_0x00a3:
            r22 = r0
            goto L_0x00a8
        L_0x00a6:
            r22 = r0
        L_0x00a8:
            r0 = r22
        L_0x00aa:
            if (r4 == 0) goto L_0x00b1
            r22 = r0
            com.wits.ksw.launcher.model.ControlBean r0 = r4.controlBean
            goto L_0x00b5
        L_0x00b1:
            r22 = r0
            r0 = r16
        L_0x00b5:
            long r31 = r2 & r27
            r19 = 0
            int r16 = (r31 > r19 ? 1 : (r31 == r19 ? 0 : -1))
            r31 = r4
            if (r16 == 0) goto L_0x0106
            if (r0 == 0) goto L_0x00c3
            android.databinding.ObservableBoolean r11 = r0.rdarAssistance
        L_0x00c3:
            r4 = 0
            r1.updateRegistration((int) r4, (android.databinding.Observable) r11)
            if (r11 == 0) goto L_0x00cd
            boolean r15 = r11.get()
        L_0x00cd:
            long r33 = r2 & r27
            r19 = 0
            int r4 = (r33 > r19 ? 1 : (r33 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x00e0
            if (r15 == 0) goto L_0x00dc
            r33 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r33
            goto L_0x00e0
        L_0x00dc:
            r33 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r33
        L_0x00e0:
            if (r15 == 0) goto L_0x00f2
            android.widget.ImageView r4 = r1.controlBtn3
            android.content.Context r4 = r4.getContext()
            r33 = r2
            r2 = 2131234637(0x7f080f4d, float:1.8085445E38)
            android.graphics.drawable.Drawable r3 = android.support.v7.content.res.AppCompatResources.getDrawable(r4, r2)
            goto L_0x0102
        L_0x00f2:
            r33 = r2
            android.widget.ImageView r2 = r1.controlBtn3
            android.content.Context r2 = r2.getContext()
            r3 = 2131234636(0x7f080f4c, float:1.8085443E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r3)
            r3 = r2
        L_0x0102:
            r2 = r3
            r12 = r2
            r2 = r33
        L_0x0106:
            long r33 = r2 & r25
            r19 = 0
            int r4 = (r33 > r19 ? 1 : (r33 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0153
            if (r0 == 0) goto L_0x0112
            android.databinding.ObservableBoolean r5 = r0.passairbar
        L_0x0112:
            r4 = 1
            r1.updateRegistration((int) r4, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x011c
            boolean r17 = r5.get()
        L_0x011c:
            long r33 = r2 & r25
            r19 = 0
            int r4 = (r33 > r19 ? 1 : (r33 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x012f
            if (r17 == 0) goto L_0x012b
            r33 = 64
            long r2 = r2 | r33
            goto L_0x012f
        L_0x012b:
            r33 = 32
            long r2 = r2 | r33
        L_0x012f:
            if (r17 == 0) goto L_0x0141
            android.widget.ImageView r4 = r1.imageView
            android.content.Context r4 = r4.getContext()
            r33 = r2
            r2 = 2131234606(0x7f080f2e, float:1.8085382E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r4, r2)
            goto L_0x0150
        L_0x0141:
            r33 = r2
            android.widget.ImageView r2 = r1.imageView
            android.content.Context r2 = r2.getContext()
            r3 = 2131234605(0x7f080f2d, float:1.808538E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x0150:
            r8 = r2
            r2 = r33
        L_0x0153:
            long r33 = r2 & r29
            r19 = 0
            int r4 = (r33 > r19 ? 1 : (r33 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01aa
            if (r0 == 0) goto L_0x0160
            android.databinding.ObservableBoolean r4 = r0.chassis
            goto L_0x0162
        L_0x0160:
            r4 = r21
        L_0x0162:
            r33 = r0
            r0 = 2
            r1.updateRegistration((int) r0, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0170
            boolean r0 = r4.get()
            r18 = r0
        L_0x0170:
            long r34 = r2 & r29
            r19 = 0
            int r0 = (r34 > r19 ? 1 : (r34 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0183
            if (r18 == 0) goto L_0x017f
            r34 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r34
            goto L_0x0183
        L_0x017f:
            r34 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r34
        L_0x0183:
            if (r18 == 0) goto L_0x0191
            android.widget.ImageView r0 = r1.controlBtn1
            android.content.Context r0 = r0.getContext()
            r34 = r2
            r2 = 2131234637(0x7f080f4d, float:1.8085445E38)
            goto L_0x019c
        L_0x0191:
            r34 = r2
            android.widget.ImageView r0 = r1.controlBtn1
            android.content.Context r0 = r0.getContext()
            r2 = 2131234636(0x7f080f4c, float:1.8085443E38)
        L_0x019c:
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r0, r2)
            r10 = r0
            r21 = r4
            r0 = r22
            r16 = r33
            r2 = r34
            goto L_0x01b5
        L_0x01aa:
            r33 = r0
            r0 = r22
            r16 = r33
            goto L_0x01b5
        L_0x01b1:
            r22 = r0
            r31 = r4
        L_0x01b5:
            long r22 = r2 & r23
            r19 = 0
            int r4 = (r22 > r19 ? 1 : (r22 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01e0
            android.widget.ImageView r4 = r1.brightnessBtnLeft
            r4.setOnClickListener(r6)
            android.widget.ImageView r4 = r1.brightnessBtnRight
            r4.setOnClickListener(r6)
            android.widget.ImageView r4 = r1.controlBtn1
            r4.setOnClickListener(r13)
            android.widget.ImageView r4 = r1.controlBtn2
            r4.setOnClickListener(r0)
            android.widget.ImageView r4 = r1.controlBtn3
            r4.setOnClickListener(r9)
            android.widget.ImageView r4 = r1.foldLeftBtn
            r4.setOnClickListener(r14)
            android.widget.ImageView r4 = r1.foldRightBtn
            r4.setOnClickListener(r7)
        L_0x01e0:
            long r22 = r2 & r29
            r19 = 0
            int r4 = (r22 > r19 ? 1 : (r22 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01ed
            android.widget.ImageView r4 = r1.controlBtn1
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r10)
        L_0x01ed:
            long r22 = r2 & r27
            int r4 = (r22 > r19 ? 1 : (r22 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01f8
            android.widget.ImageView r4 = r1.controlBtn3
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r12)
        L_0x01f8:
            long r22 = r2 & r25
            int r4 = (r22 > r19 ? 1 : (r22 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0203
            android.widget.ImageView r4 = r1.imageView
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r8)
        L_0x0203:
            return
        L_0x0204:
            r0 = move-exception
            monitor-exit(r36)     // Catch:{ all -> 0x0204 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl setValue(LauncherViewModel value2) {
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

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl1 setValue(LauncherViewModel value2) {
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

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl2 setValue(LauncherViewModel value2) {
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

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl3 setValue(LauncherViewModel value2) {
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

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private LauncherViewModel value;

        public OnClickListenerImpl4 setValue(LauncherViewModel value2) {
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
        private LauncherViewModel value;

        public OnClickListenerImpl5 setValue(LauncherViewModel value2) {
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
}

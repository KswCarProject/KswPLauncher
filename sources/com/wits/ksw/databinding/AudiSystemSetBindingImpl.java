package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiSystemSetBindingImpl extends AudiSystemSetBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ScrollView mboundView0;

    static {
        sViewsWithIds.put(R.id.audiSystemSetParentPanel, 7);
        sViewsWithIds.put(R.id.audi_system_speed_unit, 8);
        sViewsWithIds.put(R.id.audi_system_temp_unit, 9);
        sViewsWithIds.put(R.id.audi_system_rever_camera, 10);
        sViewsWithIds.put(R.id.audi_system_brightness, 11);
    }

    public AudiSystemSetBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiSystemSetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, bindings[6], bindings[11], bindings[2], bindings[10], bindings[1], bindings[5], bindings[4], bindings[3], bindings[7], bindings[8], bindings[9]);
        this.mDirtyFlags = -1;
        this.audiSystemAuxPostion.setTag((Object) null);
        this.audiSystemDrivingProhibitedVideo.setTag((Object) null);
        this.audiSystemReverCameraImg.setTag((Object) null);
        this.audiSystemReverMute.setTag((Object) null);
        this.audiSystemReverRadar.setTag((Object) null);
        this.audiSystemReverTrack.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
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
        if (17 != variableId) {
            return false;
        }
        setVm((AudiSystemViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiSystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmReverMute((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVmShowAuxPostion((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeVmDrivingVideo((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeVmReverTrack((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeVmReverView((ObservableBoolean) object, fieldId);
            case 5:
                return onChangeVmReverRadar((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmReverMute(ObservableBoolean VmReverMute, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmShowAuxPostion(ObservableBoolean VmShowAuxPostion, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmDrivingVideo(ObservableBoolean VmDrivingVideo, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmReverTrack(ObservableBoolean VmReverTrack, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmReverView(ObservableBoolean VmReverView, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmReverRadar(ObservableBoolean VmReverRadar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r44 = this;
            r1 = r44
            r2 = 0
            monitor-enter(r44)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0189 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0189 }
            monitor-exit(r44)     // Catch:{ all -> 0x0189 }
            r0 = 0
            com.wits.ksw.settings.audi.vm.AudiSystemViewModel r6 = r1.mVm
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
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 255(0xff, double:1.26E-321)
            long r24 = r2 & r24
            int r24 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            r25 = 224(0xe0, double:1.107E-321)
            r27 = 200(0xc8, double:9.9E-322)
            r29 = 208(0xd0, double:1.03E-321)
            r31 = 196(0xc4, double:9.7E-322)
            r33 = 193(0xc1, double:9.54E-322)
            r35 = 192(0xc0, double:9.5E-322)
            r37 = 194(0xc2, double:9.6E-322)
            if (r24 == 0) goto L_0x010c
            long r39 = r2 & r35
            int r24 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x0055
            if (r6 == 0) goto L_0x0055
            android.widget.CompoundButton$OnCheckedChangeListener r0 = r6.ondrivingVideoChangeListener
            android.widget.CompoundButton$OnCheckedChangeListener r7 = r6.onReverViewChangeListener
            android.widget.CompoundButton$OnCheckedChangeListener r14 = r6.onReverMuteChangeListener
            android.widget.CompoundButton$OnCheckedChangeListener r4 = r6.onReverTrackChangeListener
            android.widget.CompoundButton$OnCheckedChangeListener r5 = r6.onReverRadarkChangeListener
            r17 = r4
            r23 = r5
        L_0x0055:
            long r4 = r2 & r33
            r39 = 0
            int r4 = (r4 > r39 ? 1 : (r4 == r39 ? 0 : -1))
            r5 = 0
            if (r4 == 0) goto L_0x006c
            if (r6 == 0) goto L_0x0062
            android.databinding.ObservableBoolean r8 = r6.reverMute
        L_0x0062:
            r1.updateRegistration((int) r5, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x006c
            boolean r4 = r8.get()
            r13 = r4
        L_0x006c:
            long r39 = r2 & r37
            r41 = 0
            int r4 = (r39 > r41 ? 1 : (r39 == r41 ? 0 : -1))
            if (r4 == 0) goto L_0x009c
            if (r6 == 0) goto L_0x0078
            android.databinding.ObservableBoolean r10 = r6.showAuxPostion
        L_0x0078:
            r4 = 1
            r1.updateRegistration((int) r4, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0082
            boolean r21 = r10.get()
        L_0x0082:
            long r39 = r2 & r37
            r41 = 0
            int r4 = (r39 > r41 ? 1 : (r39 == r41 ? 0 : -1))
            if (r4 == 0) goto L_0x0095
            if (r21 == 0) goto L_0x0091
            r39 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r39
            goto L_0x0095
        L_0x0091:
            r39 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r39
        L_0x0095:
            if (r21 == 0) goto L_0x0098
            goto L_0x009a
        L_0x0098:
            r5 = 8
        L_0x009a:
            r4 = r5
            r9 = r4
        L_0x009c:
            long r4 = r2 & r31
            r39 = 0
            int r4 = (r4 > r39 ? 1 : (r4 == r39 ? 0 : -1))
            if (r4 == 0) goto L_0x00b4
            if (r6 == 0) goto L_0x00a8
            android.databinding.ObservableBoolean r12 = r6.drivingVideo
        L_0x00a8:
            r4 = 2
            r1.updateRegistration((int) r4, (android.databinding.Observable) r12)
            if (r12 == 0) goto L_0x00b4
            boolean r4 = r12.get()
            r16 = r4
        L_0x00b4:
            long r4 = r2 & r27
            r39 = 0
            int r4 = (r4 > r39 ? 1 : (r4 == r39 ? 0 : -1))
            if (r4 == 0) goto L_0x00cc
            if (r6 == 0) goto L_0x00c0
            android.databinding.ObservableBoolean r15 = r6.reverTrack
        L_0x00c0:
            r4 = 3
            r1.updateRegistration((int) r4, (android.databinding.Observable) r15)
            if (r15 == 0) goto L_0x00cc
            boolean r4 = r15.get()
            r22 = r4
        L_0x00cc:
            long r4 = r2 & r29
            r39 = 0
            int r4 = (r4 > r39 ? 1 : (r4 == r39 ? 0 : -1))
            if (r4 == 0) goto L_0x00eb
            if (r6 == 0) goto L_0x00d9
            android.databinding.ObservableBoolean r4 = r6.reverView
            goto L_0x00db
        L_0x00d9:
            r4 = r19
        L_0x00db:
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x00e9
            boolean r5 = r4.get()
            r19 = r4
            r11 = r5
            goto L_0x00eb
        L_0x00e9:
            r19 = r4
        L_0x00eb:
            long r4 = r2 & r25
            r39 = 0
            int r4 = (r4 > r39 ? 1 : (r4 == r39 ? 0 : -1))
            if (r4 == 0) goto L_0x010c
            if (r6 == 0) goto L_0x00f8
            android.databinding.ObservableBoolean r4 = r6.reverRadar
            goto L_0x00fa
        L_0x00f8:
            r4 = r20
        L_0x00fa:
            r5 = 5
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0104
            boolean r18 = r4.get()
        L_0x0104:
            r20 = r4
            r5 = r23
            r4 = r0
            r0 = r16
            goto L_0x0111
        L_0x010c:
            r4 = r0
            r0 = r16
            r5 = r23
        L_0x0111:
            r16 = r10
            r10 = r18
            r18 = r15
            r15 = r8
            r8 = r17
            r17 = r12
            r12 = r22
            long r22 = r2 & r37
            r37 = 0
            int r22 = (r22 > r37 ? 1 : (r22 == r37 ? 0 : -1))
            if (r22 == 0) goto L_0x012e
            r43 = r6
            android.widget.TextView r6 = r1.audiSystemAuxPostion
            r6.setVisibility(r9)
            goto L_0x0130
        L_0x012e:
            r43 = r6
        L_0x0130:
            long r22 = r2 & r31
            int r6 = (r22 > r37 ? 1 : (r22 == r37 ? 0 : -1))
            if (r6 == 0) goto L_0x013b
            android.widget.CheckBox r6 = r1.audiSystemDrivingProhibitedVideo
            android.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r6, r0)
        L_0x013b:
            long r22 = r2 & r35
            int r6 = (r22 > r37 ? 1 : (r22 == r37 ? 0 : -1))
            if (r6 == 0) goto L_0x015a
            android.widget.CheckBox r6 = r1.audiSystemDrivingProhibitedVideo
            r6.setOnCheckedChangeListener(r4)
            android.widget.CheckBox r6 = r1.audiSystemReverCameraImg
            r6.setOnCheckedChangeListener(r7)
            android.widget.CheckBox r6 = r1.audiSystemReverMute
            r6.setOnCheckedChangeListener(r14)
            android.widget.CheckBox r6 = r1.audiSystemReverRadar
            r6.setOnCheckedChangeListener(r5)
            android.widget.CheckBox r6 = r1.audiSystemReverTrack
            r6.setOnCheckedChangeListener(r8)
        L_0x015a:
            long r22 = r2 & r29
            r29 = 0
            int r6 = (r22 > r29 ? 1 : (r22 == r29 ? 0 : -1))
            if (r6 == 0) goto L_0x0167
            android.widget.CheckBox r6 = r1.audiSystemReverCameraImg
            android.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r6, r11)
        L_0x0167:
            long r22 = r2 & r33
            int r6 = (r22 > r29 ? 1 : (r22 == r29 ? 0 : -1))
            if (r6 == 0) goto L_0x0172
            android.widget.CheckBox r6 = r1.audiSystemReverMute
            android.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r6, r13)
        L_0x0172:
            long r22 = r2 & r25
            int r6 = (r22 > r29 ? 1 : (r22 == r29 ? 0 : -1))
            if (r6 == 0) goto L_0x017d
            android.widget.CheckBox r6 = r1.audiSystemReverRadar
            android.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r6, r10)
        L_0x017d:
            long r22 = r2 & r27
            int r6 = (r22 > r29 ? 1 : (r22 == r29 ? 0 : -1))
            if (r6 == 0) goto L_0x0188
            android.widget.CheckBox r6 = r1.audiSystemReverTrack
            android.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r6, r12)
        L_0x0188:
            return
        L_0x0189:
            r0 = move-exception
            monitor-exit(r44)     // Catch:{ all -> 0x0189 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AudiSystemSetBindingImpl.executeBindings():void");
    }
}

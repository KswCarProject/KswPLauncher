package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubDashboardViewBindingImpl extends AlsId7SubDashboardViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback40;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 7);
    }

    public AlsId7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AlsId7SubDashboardViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[4], bindings[0], bindings[1], bindings[2], bindings[3], bindings[5], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.brakeTextView.setTag((Object) null);
        this.dashboardConstraintLayout.setTag((Object) null);
        this.dashboardImageView.setTag((Object) null);
        this.oilTextView.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        this.seatBeltTextView.setTag((Object) null);
        this.speedTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback40 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        if (3 != variableId) {
            return false;
        }
        setDashVideoViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel) {
        this.mDashVideoViewModel = DashVideoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDashVideoViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 1:
                return onChangeDashVideoViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 2:
                return onChangeDashVideoViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 3:
                return onChangeDashVideoViewModelCarInfoUnit((ObservableInt) object, fieldId);
            case 4:
                return onChangeDashVideoViewModelCarInfoSpeed((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashVideoViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoUnit(ObservableInt DashVideoViewModelCarInfoUnit, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoSpeed(ObservableInt DashVideoViewModelCarInfoSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0201  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r43 = this;
            r1 = r43
            r2 = 0
            monitor-enter(r43)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0238 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0238 }
            monitor-exit(r43)     // Catch:{ all -> 0x0238 }
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
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r4 = r1.mDashVideoViewModel
            r5 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 95
            long r25 = r2 & r25
            r19 = 0
            int r25 = (r25 > r19 ? 1 : (r25 == r19 ? 0 : -1))
            r26 = 80
            r28 = 66
            r30 = 68
            r32 = 72
            r34 = 65
            if (r25 == 0) goto L_0x01ac
            com.wits.ksw.launcher.bean.CarInfo r8 = com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.carInfo
            long r36 = r2 & r34
            int r25 = (r36 > r19 ? 1 : (r36 == r19 ? 0 : -1))
            r36 = r0
            r0 = 0
            if (r25 == 0) goto L_0x0091
            if (r8 == 0) goto L_0x004c
            android.databinding.ObservableField<java.lang.Boolean> r7 = r8.brakeValue
        L_0x004c:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0059
            java.lang.Object r25 = r7.get()
            r22 = r25
            java.lang.Boolean r22 = (java.lang.Boolean) r22
        L_0x0059:
            boolean r10 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r22)
            long r37 = r2 & r34
            r19 = 0
            int r25 = (r37 > r19 ? 1 : (r37 == r19 ? 0 : -1))
            if (r25 == 0) goto L_0x0070
            if (r10 == 0) goto L_0x006c
            r37 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r37
            goto L_0x0070
        L_0x006c:
            r37 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r37
        L_0x0070:
            if (r10 == 0) goto L_0x007e
            android.widget.TextView r0 = r1.brakeTextView
            android.content.res.Resources r0 = r0.getResources()
            r37 = r2
            r2 = 2131558729(0x7f0d0149, float:1.8742782E38)
            goto L_0x0089
        L_0x007e:
            r37 = r2
            android.widget.TextView r0 = r1.brakeTextView
            android.content.res.Resources r0 = r0.getResources()
            r2 = 2131558728(0x7f0d0148, float:1.874278E38)
        L_0x0089:
            java.lang.String r0 = r0.getString(r2)
            r16 = r0
            r2 = r37
        L_0x0091:
            long r37 = r2 & r28
            r19 = 0
            int r0 = (r37 > r19 ? 1 : (r37 == r19 ? 0 : -1))
            r37 = r5
            r5 = 1
            if (r0 == 0) goto L_0x00af
            if (r8 == 0) goto L_0x00a0
            android.databinding.ObservableField<java.lang.String> r15 = r8.oilValue
        L_0x00a0:
            r1.updateRegistration((int) r5, (android.databinding.Observable) r15)
            if (r15 == 0) goto L_0x00ac
            java.lang.Object r0 = r15.get()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x00b1
        L_0x00ac:
            r0 = r36
            goto L_0x00b1
        L_0x00af:
            r0 = r36
        L_0x00b1:
            long r38 = r2 & r30
            r19 = 0
            int r36 = (r38 > r19 ? 1 : (r38 == r19 ? 0 : -1))
            if (r36 == 0) goto L_0x010e
            if (r8 == 0) goto L_0x00be
            android.databinding.ObservableField<java.lang.Boolean> r5 = r8.seatBeltpValue
            goto L_0x00c0
        L_0x00be:
            r5 = r17
        L_0x00c0:
            r36 = r0
            r0 = 2
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x00d0
            java.lang.Object r0 = r5.get()
            r23 = r0
            java.lang.Boolean r23 = (java.lang.Boolean) r23
        L_0x00d0:
            boolean r0 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r23)
            long r39 = r2 & r30
            r19 = 0
            int r14 = (r39 > r19 ? 1 : (r39 == r19 ? 0 : -1))
            if (r14 == 0) goto L_0x00e7
            if (r0 == 0) goto L_0x00e3
            r39 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r39
            goto L_0x00e7
        L_0x00e3:
            r39 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r39
        L_0x00e7:
            if (r0 == 0) goto L_0x00f9
            android.widget.TextView r14 = r1.seatBeltTextView
            android.content.res.Resources r14 = r14.getResources()
            r17 = r0
            r0 = 2131558746(0x7f0d015a, float:1.8742817E38)
            java.lang.String r0 = r14.getString(r0)
            goto L_0x0108
        L_0x00f9:
            r17 = r0
            android.widget.TextView r0 = r1.seatBeltTextView
            android.content.res.Resources r0 = r0.getResources()
            r14 = 2131558745(0x7f0d0159, float:1.8742814E38)
            java.lang.String r0 = r0.getString(r14)
        L_0x0108:
            r12 = r0
            r14 = r17
            r17 = r5
            goto L_0x0110
        L_0x010e:
            r36 = r0
        L_0x0110:
            long r39 = r2 & r32
            r19 = 0
            int r0 = (r39 > r19 ? 1 : (r39 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0166
            if (r8 == 0) goto L_0x011d
            android.databinding.ObservableInt r5 = r8.unit
            goto L_0x011f
        L_0x011d:
            r5 = r37
        L_0x011f:
            r0 = 3
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0129
            int r13 = r5.get()
        L_0x0129:
            r0 = 1
            if (r13 != r0) goto L_0x012d
            goto L_0x012e
        L_0x012d:
            r0 = 0
        L_0x012e:
            long r37 = r2 & r32
            r19 = 0
            int r9 = (r37 > r19 ? 1 : (r37 == r19 ? 0 : -1))
            if (r9 == 0) goto L_0x0141
            if (r0 == 0) goto L_0x013d
            r37 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r37
            goto L_0x0141
        L_0x013d:
            r37 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r37
        L_0x0141:
            if (r0 == 0) goto L_0x0153
            com.wits.ksw.launcher.view.CustomBmwImageView r9 = r1.dashboardImageView
            android.content.Context r9 = r9.getContext()
            r25 = r0
            r0 = 2131230966(0x7f0800f6, float:1.8078E38)
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r9, r0)
            goto L_0x0162
        L_0x0153:
            r25 = r0
            com.wits.ksw.launcher.view.CustomBmwImageView r0 = r1.dashboardImageView
            android.content.Context r0 = r0.getContext()
            r9 = 2131230965(0x7f0800f5, float:1.8077998E38)
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r0, r9)
        L_0x0162:
            r6 = r0
            r9 = r25
            goto L_0x0168
        L_0x0166:
            r5 = r37
        L_0x0168:
            long r37 = r2 & r26
            r19 = 0
            int r0 = (r37 > r19 ? 1 : (r37 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x019b
            if (r8 == 0) goto L_0x0175
            android.databinding.ObservableInt r0 = r8.speed
            goto L_0x0177
        L_0x0175:
            r0 = r24
        L_0x0177:
            r37 = r2
            r2 = 4
            r1.updateRegistration((int) r2, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0184
            int r2 = r0.get()
            r11 = r2
        L_0x0184:
            java.lang.String r18 = java.lang.String.valueOf(r11)
            r24 = r0
            r0 = r36
            r2 = r37
            r41 = r16
            r16 = r5
            r5 = r41
            r42 = r18
            r18 = r7
            r7 = r42
            goto L_0x01ba
        L_0x019b:
            r37 = r2
            r0 = r36
            r41 = r16
            r16 = r5
            r5 = r41
            r42 = r18
            r18 = r7
            r7 = r42
            goto L_0x01ba
        L_0x01ac:
            r36 = r0
            r37 = r5
            r5 = r16
            r16 = r37
            r41 = r18
            r18 = r7
            r7 = r41
        L_0x01ba:
            r36 = 96
            long r38 = r2 & r36
            r19 = 0
            int r25 = (r38 > r19 ? 1 : (r38 == r19 ? 0 : -1))
            if (r25 == 0) goto L_0x01ce
            if (r4 == 0) goto L_0x01cb
            r25 = r8
            android.view.View$OnFocusChangeListener r8 = r4.dashViewFocusChangeListener
            goto L_0x01d2
        L_0x01cb:
            r25 = r8
            goto L_0x01d0
        L_0x01ce:
            r25 = r8
        L_0x01d0:
            r8 = r21
        L_0x01d2:
            long r34 = r2 & r34
            r19 = 0
            int r21 = (r34 > r19 ? 1 : (r34 == r19 ? 0 : -1))
            if (r21 == 0) goto L_0x01e2
            r21 = r4
            android.widget.TextView r4 = r1.brakeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r5)
            goto L_0x01e4
        L_0x01e2:
            r21 = r4
        L_0x01e4:
            long r32 = r2 & r32
            int r4 = (r32 > r19 ? 1 : (r32 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x01ef
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.dashboardImageView
            android.databinding.adapters.ViewBindingAdapter.setBackground(r4, r6)
        L_0x01ef:
            r32 = 64
            long r32 = r2 & r32
            int r4 = (r32 > r19 ? 1 : (r32 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0201
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.dashboardImageView
            r32 = r5
            android.view.View$OnClickListener r5 = r1.mCallback40
            r4.setOnClickListener(r5)
            goto L_0x0203
        L_0x0201:
            r32 = r5
        L_0x0203:
            long r4 = r2 & r36
            int r4 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x020e
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.dashboardImageView
            r4.setOnFocusChangeListener(r8)
        L_0x020e:
            long r4 = r2 & r28
            int r4 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0219
            android.widget.TextView r4 = r1.oilTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r0)
        L_0x0219:
            long r4 = r2 & r26
            int r4 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x022a
            android.widget.ImageView r4 = r1.pointerImageView
            float r5 = (float) r11
            com.wits.ksw.launcher.model.LauncherViewModel.setRotationBySpeed(r4, r5)
            android.widget.TextView r4 = r1.speedTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r7)
        L_0x022a:
            long r4 = r2 & r30
            r19 = 0
            int r4 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x0237
            android.widget.TextView r4 = r1.seatBeltTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r12)
        L_0x0237:
            return
        L_0x0238:
            r0 = move-exception
            monitor-exit(r43)     // Catch:{ all -> 0x0238 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AlsId7SubDashboardViewBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel dashVideoViewModel = this.mDashVideoViewModel;
        if (dashVideoViewModel != null) {
            dashVideoViewModel.openDashboard(callbackArg_0);
        }
    }
}

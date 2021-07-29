package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
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
    private final View.OnClickListener mCallback37;
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
        this.mCallback37 = new OnClickListener(this, 1);
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
                return onChangeDashVideoViewModelCarInfoTurnSpeed((ObservableInt) object, fieldId);
            case 1:
                return onChangeDashVideoViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            case 2:
                return onChangeDashVideoViewModelCarInfoOilValue((ObservableField) object, fieldId);
            case 3:
                return onChangeDashVideoViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeDashVideoViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeed(ObservableInt DashVideoViewModelCarInfoTurnSpeed, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeDashVideoViewModelCarInfoTurnSpeedAnge(ObservableFloat DashVideoViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r39 = this;
            r1 = r39
            r2 = 0
            monitor-enter(r39)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x01e4 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x01e4 }
            monitor-exit(r39)     // Catch:{ all -> 0x01e4 }
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
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r4 = r1.mDashVideoViewModel
            r5 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 95
            long r23 = r2 & r23
            r16 = 0
            int r23 = (r23 > r16 ? 1 : (r23 == r16 ? 0 : -1))
            r24 = 80
            r26 = 68
            r28 = 65
            r30 = 72
            r32 = 66
            if (r23 == 0) goto L_0x0166
            com.wits.ksw.launcher.bean.CarInfo r8 = com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.carInfo
            long r34 = r2 & r28
            int r23 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x0057
            if (r8 == 0) goto L_0x0045
            android.databinding.ObservableInt r6 = r8.turnSpeed
        L_0x0045:
            r23 = r0
            r0 = 0
            r1.updateRegistration((int) r0, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0051
            int r5 = r6.get()
        L_0x0051:
            java.lang.String r0 = java.lang.String.valueOf(r5)
            r11 = r0
            goto L_0x0059
        L_0x0057:
            r23 = r0
        L_0x0059:
            long r34 = r2 & r32
            r16 = 0
            int r0 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x00ae
            if (r8 == 0) goto L_0x0065
            android.databinding.ObservableField<java.lang.Boolean> r7 = r8.brakeValue
        L_0x0065:
            r0 = 1
            r1.updateRegistration((int) r0, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0073
            java.lang.Object r0 = r7.get()
            r20 = r0
            java.lang.Boolean r20 = (java.lang.Boolean) r20
        L_0x0073:
            boolean r0 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r20)
            long r34 = r2 & r32
            r16 = 0
            int r9 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x0086
            r34 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r34
            goto L_0x008a
        L_0x0086:
            r34 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r34
        L_0x008a:
            if (r0 == 0) goto L_0x009c
            android.widget.TextView r9 = r1.brakeTextView
            android.content.res.Resources r9 = r9.getResources()
            r34 = r0
            r0 = 2131558725(0x7f0d0145, float:1.8742774E38)
            java.lang.String r0 = r9.getString(r0)
            goto L_0x00ab
        L_0x009c:
            r34 = r0
            android.widget.TextView r0 = r1.brakeTextView
            android.content.res.Resources r0 = r0.getResources()
            r9 = 2131558724(0x7f0d0144, float:1.8742772E38)
            java.lang.String r0 = r0.getString(r9)
        L_0x00ab:
            r14 = r0
            r9 = r34
        L_0x00ae:
            long r34 = r2 & r26
            r16 = 0
            int r0 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x00ca
            if (r8 == 0) goto L_0x00ba
            android.databinding.ObservableField<java.lang.String> r13 = r8.oilValue
        L_0x00ba:
            r0 = 2
            r1.updateRegistration((int) r0, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x00c7
            java.lang.Object r0 = r13.get()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x00cc
        L_0x00c7:
            r0 = r23
            goto L_0x00cc
        L_0x00ca:
            r0 = r23
        L_0x00cc:
            long r34 = r2 & r30
            r16 = 0
            int r23 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x0124
            if (r8 == 0) goto L_0x00d8
            android.databinding.ObservableField<java.lang.Boolean> r15 = r8.seatBeltpValue
        L_0x00d8:
            r23 = r0
            r0 = 3
            r1.updateRegistration((int) r0, (android.databinding.Observable) r15)
            if (r15 == 0) goto L_0x00e8
            java.lang.Object r0 = r15.get()
            r21 = r0
            java.lang.Boolean r21 = (java.lang.Boolean) r21
        L_0x00e8:
            boolean r0 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r21)
            long r34 = r2 & r30
            r16 = 0
            int r12 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x00ff
            if (r0 == 0) goto L_0x00fb
            r34 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r34
            goto L_0x00ff
        L_0x00fb:
            r34 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r34
        L_0x00ff:
            if (r0 == 0) goto L_0x0111
            android.widget.TextView r12 = r1.seatBeltTextView
            android.content.res.Resources r12 = r12.getResources()
            r34 = r0
            r0 = 2131558742(0x7f0d0156, float:1.8742808E38)
            java.lang.String r0 = r12.getString(r0)
            goto L_0x0120
        L_0x0111:
            r34 = r0
            android.widget.TextView r0 = r1.seatBeltTextView
            android.content.res.Resources r0 = r0.getResources()
            r12 = 2131558741(0x7f0d0155, float:1.8742806E38)
            java.lang.String r0 = r0.getString(r12)
        L_0x0120:
            r10 = r0
            r12 = r34
            goto L_0x0126
        L_0x0124:
            r23 = r0
        L_0x0126:
            long r34 = r2 & r24
            r16 = 0
            int r0 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x015b
            if (r8 == 0) goto L_0x0133
            android.databinding.ObservableFloat r0 = r8.turnSpeedAnge
            goto L_0x0135
        L_0x0133:
            r0 = r19
        L_0x0135:
            r34 = r2
            r2 = 4
            r1.updateRegistration((int) r2, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x014e
            float r22 = r0.get()
            r19 = r0
            r0 = r23
            r2 = r34
            r38 = r22
            r22 = r5
            r5 = r38
            goto L_0x016e
        L_0x014e:
            r19 = r0
            r0 = r23
            r2 = r34
            r38 = r22
            r22 = r5
            r5 = r38
            goto L_0x016e
        L_0x015b:
            r34 = r2
            r0 = r23
            r38 = r22
            r22 = r5
            r5 = r38
            goto L_0x016e
        L_0x0166:
            r23 = r0
            r38 = r22
            r22 = r5
            r5 = r38
        L_0x016e:
            r34 = 96
            long r36 = r2 & r34
            r16 = 0
            int r23 = (r36 > r16 ? 1 : (r36 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x0182
            if (r4 == 0) goto L_0x017f
            r23 = r6
            android.view.View$OnFocusChangeListener r6 = r4.dashViewFocusChangeListener
            goto L_0x0186
        L_0x017f:
            r23 = r6
            goto L_0x0184
        L_0x0182:
            r23 = r6
        L_0x0184:
            r6 = r18
        L_0x0186:
            long r32 = r2 & r32
            r16 = 0
            int r18 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r18 == 0) goto L_0x0196
            r18 = r4
            android.widget.TextView r4 = r1.brakeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r14)
            goto L_0x0198
        L_0x0196:
            r18 = r4
        L_0x0198:
            r32 = 64
            long r32 = r2 & r32
            int r4 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01aa
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.dashboardImageView
            r32 = r7
            android.view.View$OnClickListener r7 = r1.mCallback37
            r4.setOnClickListener(r7)
            goto L_0x01ac
        L_0x01aa:
            r32 = r7
        L_0x01ac:
            long r33 = r2 & r34
            int r4 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01b7
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.dashboardImageView
            r4.setOnFocusChangeListener(r6)
        L_0x01b7:
            long r26 = r2 & r26
            int r4 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01c2
            android.widget.TextView r4 = r1.oilTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r0)
        L_0x01c2:
            long r24 = r2 & r24
            int r4 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01cd
            android.widget.ImageView r4 = r1.pointerImageView
            com.wits.ksw.launcher.model.DashboardViewModel.setRotation(r4, r5)
        L_0x01cd:
            long r24 = r2 & r30
            int r4 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01d8
            android.widget.TextView r4 = r1.seatBeltTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r10)
        L_0x01d8:
            long r24 = r2 & r28
            int r4 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x01e3
            android.widget.TextView r4 = r1.speedTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r11)
        L_0x01e3:
            return
        L_0x01e4:
            r0 = move-exception
            monitor-exit(r39)     // Catch:{ all -> 0x01e4 }
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

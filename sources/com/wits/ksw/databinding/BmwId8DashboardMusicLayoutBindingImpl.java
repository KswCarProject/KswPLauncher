package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.launcher.model.DashboardViewModel;

public class BmwId8DashboardMusicLayoutBindingImpl extends BmwId8DashboardMusicLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    public BmwId8DashboardMusicLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardMusicLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, bindings[4], bindings[8]);
        this.mDirtyFlags = -1;
        this.bmwId8DashboardMusicName.setTag((Object) null);
        this.bmwId8DashboardMusicSeekbar.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = bindings[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
        ImageView imageView = bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView2 = bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoHightLrcRow((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBThirdMusic((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMediaInfoProgressPercent((ObservableInt) object, fieldId);
            case 5:
                return onChangeViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoHightLrcRow(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelBThirdMusic(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoProgressPercent(ObservableInt ViewModelMediaInfoProgressPercent, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: java.lang.Boolean} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r51 = this;
            r1 = r51
            r2 = 0
            monitor-enter(r51)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x025c }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x025c }
            monitor-exit(r51)     // Catch:{ all -> 0x025c }
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
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 765(0x2fd, double:3.78E-321)
            long r29 = r2 & r29
            int r29 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            r30 = 640(0x280, double:3.16E-321)
            r32 = 576(0x240, double:2.846E-321)
            r34 = 544(0x220, double:2.69E-321)
            r36 = 528(0x210, double:2.61E-321)
            r38 = 513(0x201, double:2.535E-321)
            r40 = 520(0x208, double:2.57E-321)
            r42 = 516(0x204, double:2.55E-321)
            r4 = 0
            if (r29 == 0) goto L_0x0167
            com.wits.ksw.launcher.bean.MediaInfo r8 = com.wits.ksw.launcher.model.DashboardViewModel.mediaInfo
            long r46 = r2 & r38
            r44 = 0
            int r29 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r29 == 0) goto L_0x0064
            if (r8 == 0) goto L_0x0057
            android.databinding.ObservableField<java.lang.String> r6 = r8.hightLrcRow
        L_0x0057:
            r1.updateRegistration((int) r4, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0064
            java.lang.Object r29 = r6.get()
            r11 = r29
            java.lang.String r11 = (java.lang.String) r11
        L_0x0064:
            long r46 = r2 & r42
            r44 = 0
            int r29 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r29 == 0) goto L_0x009d
            if (r8 == 0) goto L_0x0070
            android.databinding.ObservableField<java.lang.String> r10 = r8.musicName
        L_0x0070:
            r4 = 2
            r1.updateRegistration((int) r4, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x007e
            java.lang.Object r4 = r10.get()
            r17 = r4
            java.lang.String r17 = (java.lang.String) r17
        L_0x007e:
            if (r17 != 0) goto L_0x0082
            r4 = 1
            goto L_0x0083
        L_0x0082:
            r4 = 0
        L_0x0083:
            long r46 = r2 & r42
            r44 = 0
            int r18 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r18 == 0) goto L_0x009b
            if (r4 == 0) goto L_0x0094
            r46 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r46
            r18 = r4
            goto L_0x009d
        L_0x0094:
            r46 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r46
            r18 = r4
            goto L_0x009d
        L_0x009b:
            r18 = r4
        L_0x009d:
            long r46 = r2 & r40
            r44 = 0
            int r4 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r4 == 0) goto L_0x00d5
            if (r8 == 0) goto L_0x00a9
            android.databinding.ObservableField<java.lang.String> r14 = r8.musicAtist
        L_0x00a9:
            r4 = 3
            r1.updateRegistration((int) r4, (android.databinding.Observable) r14)
            if (r14 == 0) goto L_0x00b6
            java.lang.Object r4 = r14.get()
            r0 = r4
            java.lang.String r0 = (java.lang.String) r0
        L_0x00b6:
            if (r0 != 0) goto L_0x00ba
            r4 = 1
            goto L_0x00bb
        L_0x00ba:
            r4 = 0
        L_0x00bb:
            long r46 = r2 & r40
            r44 = 0
            int r19 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r19 == 0) goto L_0x00d3
            if (r4 == 0) goto L_0x00cc
            r46 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r46
            r19 = r4
            goto L_0x00d5
        L_0x00cc:
            r46 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r46
            r19 = r4
            goto L_0x00d5
        L_0x00d3:
            r19 = r4
        L_0x00d5:
            long r46 = r2 & r36
            r44 = 0
            int r4 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r4 == 0) goto L_0x00f4
            if (r8 == 0) goto L_0x00e2
            android.databinding.ObservableInt r4 = r8.progressPercent
            goto L_0x00e4
        L_0x00e2:
            r4 = r20
        L_0x00e4:
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x00f2
            int r5 = r4.get()
            r20 = r4
            r15 = r5
            goto L_0x00f4
        L_0x00f2:
            r20 = r4
        L_0x00f4:
            long r4 = r2 & r34
            r44 = 0
            int r4 = (r4 > r44 ? 1 : (r4 == r44 ? 0 : -1))
            if (r4 == 0) goto L_0x0115
            if (r8 == 0) goto L_0x0101
            android.databinding.ObservableField<java.lang.String> r4 = r8.currentTime
            goto L_0x0103
        L_0x0101:
            r4 = r22
        L_0x0103:
            r5 = 5
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0113
            java.lang.Object r5 = r4.get()
            r7 = r5
            java.lang.String r7 = (java.lang.String) r7
            r22 = r4
            goto L_0x0115
        L_0x0113:
            r22 = r4
        L_0x0115:
            long r4 = r2 & r32
            r44 = 0
            int r4 = (r4 > r44 ? 1 : (r4 == r44 ? 0 : -1))
            if (r4 == 0) goto L_0x0137
            if (r8 == 0) goto L_0x0122
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r4 = r8.pic
            goto L_0x0124
        L_0x0122:
            r4 = r26
        L_0x0124:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0135
            java.lang.Object r5 = r4.get()
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            r26 = r4
            r23 = r5
            goto L_0x0137
        L_0x0135:
            r26 = r4
        L_0x0137:
            long r4 = r2 & r30
            r44 = 0
            int r4 = (r4 > r44 ? 1 : (r4 == r44 ? 0 : -1))
            if (r4 == 0) goto L_0x0162
            if (r8 == 0) goto L_0x0144
            android.databinding.ObservableField<java.lang.String> r4 = r8.totalTime
            goto L_0x0146
        L_0x0144:
            r4 = r28
        L_0x0146:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r4)
            if (r4 == 0) goto L_0x015b
            java.lang.Object r5 = r4.get()
            r25 = r5
            java.lang.String r25 = (java.lang.String) r25
            r28 = r4
            r4 = r23
            r5 = r25
            goto L_0x016b
        L_0x015b:
            r28 = r4
            r4 = r23
            r5 = r25
            goto L_0x016b
        L_0x0162:
            r4 = r23
            r5 = r25
            goto L_0x016b
        L_0x0167:
            r4 = r23
            r5 = r25
        L_0x016b:
            r47 = 514(0x202, double:2.54E-321)
            long r49 = r2 & r47
            r44 = 0
            int r23 = (r49 > r44 ? 1 : (r49 == r44 ? 0 : -1))
            if (r23 == 0) goto L_0x01af
            android.databinding.ObservableField r9 = com.wits.ksw.launcher.model.DashboardViewModel.bThirdMusic
            r23 = r0
            r0 = 1
            r1.updateRegistration((int) r0, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0187
            java.lang.Object r0 = r9.get()
            r21 = r0
            java.lang.Boolean r21 = (java.lang.Boolean) r21
        L_0x0187:
            boolean r13 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r21)
            r0 = 1
            if (r13 != r0) goto L_0x018f
            goto L_0x0190
        L_0x018f:
            r0 = 0
        L_0x0190:
            r24 = r0
            long r49 = r2 & r47
            r44 = 0
            int r0 = (r49 > r44 ? 1 : (r49 == r44 ? 0 : -1))
            if (r0 == 0) goto L_0x01a6
            if (r24 == 0) goto L_0x01a2
            r49 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r49
            goto L_0x01a6
        L_0x01a2:
            r49 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r49
        L_0x01a6:
            if (r24 == 0) goto L_0x01ab
            r0 = 8
            goto L_0x01ac
        L_0x01ab:
            r0 = 0
        L_0x01ac:
            r27 = r0
            goto L_0x01b3
        L_0x01af:
            r23 = r0
            r0 = r27
        L_0x01b3:
            long r49 = r2 & r42
            r44 = 0
            int r25 = (r49 > r44 ? 1 : (r49 == r44 ? 0 : -1))
            if (r25 == 0) goto L_0x01d7
            if (r18 == 0) goto L_0x01cf
            r25 = r6
            android.widget.TextView r6 = r1.bmwId8DashboardMusicName
            android.content.res.Resources r6 = r6.getResources()
            r27 = r8
            r8 = 2131558825(0x7f0d01a9, float:1.8742977E38)
            java.lang.String r6 = r6.getString(r8)
            goto L_0x01d5
        L_0x01cf:
            r25 = r6
            r27 = r8
            r6 = r17
        L_0x01d5:
            r12 = r6
            goto L_0x01db
        L_0x01d7:
            r25 = r6
            r27 = r8
        L_0x01db:
            long r49 = r2 & r40
            r44 = 0
            int r6 = (r49 > r44 ? 1 : (r49 == r44 ? 0 : -1))
            if (r6 == 0) goto L_0x01f8
            if (r19 == 0) goto L_0x01f3
            android.widget.TextView r6 = r1.mboundView5
            android.content.res.Resources r6 = r6.getResources()
            r8 = 2131558823(0x7f0d01a7, float:1.8742973E38)
            java.lang.String r6 = r6.getString(r8)
            goto L_0x01f5
        L_0x01f3:
            r6 = r23
        L_0x01f5:
            r16 = r6
            goto L_0x01fa
        L_0x01f8:
            r6 = r16
        L_0x01fa:
            long r42 = r2 & r42
            r44 = 0
            int r8 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r8 == 0) goto L_0x0207
            android.widget.TextView r8 = r1.bmwId8DashboardMusicName
            android.databinding.adapters.TextViewBindingAdapter.setText(r8, r12)
        L_0x0207:
            long r36 = r2 & r36
            int r8 = (r36 > r44 ? 1 : (r36 == r44 ? 0 : -1))
            if (r8 == 0) goto L_0x0212
            com.wits.ksw.launcher.view.CustomizeSeekBar r8 = r1.bmwId8DashboardMusicSeekbar
            com.wits.ksw.launcher.view.CustomizeSeekBar.setProgress(r8, r15)
        L_0x0212:
            long r36 = r2 & r38
            int r8 = (r36 > r44 ? 1 : (r36 == r44 ? 0 : -1))
            if (r8 == 0) goto L_0x021d
            android.widget.TextView r8 = r1.mboundView1
            android.databinding.adapters.TextViewBindingAdapter.setText(r8, r11)
        L_0x021d:
            long r36 = r2 & r47
            int r8 = (r36 > r44 ? 1 : (r36 == r44 ? 0 : -1))
            if (r8 == 0) goto L_0x022d
            android.widget.ImageView r8 = r1.mboundView2
            r8.setVisibility(r0)
            android.widget.ImageView r8 = r1.mboundView3
            r8.setVisibility(r0)
        L_0x022d:
            long r32 = r2 & r32
            r36 = 0
            int r8 = (r32 > r36 ? 1 : (r32 == r36 ? 0 : -1))
            if (r8 == 0) goto L_0x023a
            android.widget.ImageView r8 = r1.mboundView2
            com.wits.ksw.launcher.base.BaseBindingModel.setID8AlbumIcon(r8, r4)
        L_0x023a:
            long r32 = r2 & r40
            int r8 = (r32 > r36 ? 1 : (r32 == r36 ? 0 : -1))
            if (r8 == 0) goto L_0x0245
            android.widget.TextView r8 = r1.mboundView5
            android.databinding.adapters.TextViewBindingAdapter.setText(r8, r6)
        L_0x0245:
            long r32 = r2 & r34
            int r8 = (r32 > r36 ? 1 : (r32 == r36 ? 0 : -1))
            if (r8 == 0) goto L_0x0250
            android.widget.TextView r8 = r1.mboundView6
            android.databinding.adapters.TextViewBindingAdapter.setText(r8, r7)
        L_0x0250:
            long r29 = r2 & r30
            int r8 = (r29 > r36 ? 1 : (r29 == r36 ? 0 : -1))
            if (r8 == 0) goto L_0x025b
            android.widget.TextView r8 = r1.mboundView7
            android.databinding.adapters.TextViewBindingAdapter.setText(r8, r5)
        L_0x025b:
            return
        L_0x025c:
            r0 = move-exception
            monitor-exit(r51)     // Catch:{ all -> 0x025c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBindingImpl.executeBindings():void");
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubMusicViewBindingImpl extends Id7SubMusicViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback240;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.nameImageView, 10);
        sparseIntArray.put(R.id.artistImageView, 11);
        sparseIntArray.put(R.id.albumImageView, 12);
    }

    public Id7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private Id7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, bindings[12], bindings[7], bindings[11], bindings[6], bindings[8], bindings[1], bindings[3], bindings[0], bindings[2], bindings[10], bindings[5], bindings[4], bindings[9]);
        this.mDirtyFlags = -1;
        this.albumTextView.setTag((Object) null);
        this.artistTextView.setTag((Object) null);
        this.currentTimeTextView.setTag((Object) null);
        this.imageFrameLayout.setTag((Object) null);
        this.imageView6.setTag((Object) null);
        this.musicConstraintLayout.setTag((Object) null);
        this.musicImageView.setTag((Object) null);
        this.nameTextView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.totalTimeTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback240 = new OnClickListener(this, 1);
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
        if (6 != variableId) {
            return false;
        }
        setMediaViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 3:
                return onChangeMediaViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 6:
                return onChangeMediaViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 7:
                return onChangeMediaViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoProgress(ObservableInt MediaViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMaxProgress(ObservableInt MediaViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02cf  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02f0  */
    /* JADX WARNING: Removed duplicated region for block: B:167:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r55 = this;
            r1 = r55
            r2 = 0
            monitor-enter(r55)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x02f6 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02f6 }
            monitor-exit(r55)     // Catch:{ all -> 0x02f6 }
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
            com.wits.ksw.launcher.model.LauncherViewModel r4 = r1.mMediaViewModel
            r5 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 767(0x2ff, double:3.79E-321)
            long r30 = r2 & r30
            r25 = 0
            int r30 = (r30 > r25 ? 1 : (r30 == r25 ? 0 : -1))
            r31 = 640(0x280, double:3.16E-321)
            r33 = 576(0x240, double:2.846E-321)
            r35 = 544(0x220, double:2.69E-321)
            r37 = 528(0x210, double:2.61E-321)
            r39 = 513(0x201, double:2.535E-321)
            r41 = 516(0x204, double:2.55E-321)
            r43 = 514(0x202, double:2.54E-321)
            r45 = 520(0x208, double:2.57E-321)
            if (r30 == 0) goto L_0x01de
            com.wits.ksw.launcher.bean.MediaInfo r10 = com.wits.ksw.launcher.model.LauncherViewModel.mediaInfo
            long r47 = r2 & r39
            r25 = 0
            int r30 = (r47 > r25 ? 1 : (r47 == r25 ? 0 : -1))
            r47 = r0
            r0 = 0
            if (r30 == 0) goto L_0x006b
            if (r10 == 0) goto L_0x005e
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r6 = r10.pic
        L_0x005e:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x006b
            java.lang.Object r30 = r6.get()
            r16 = r30
            android.graphics.drawable.BitmapDrawable r16 = (android.graphics.drawable.BitmapDrawable) r16
        L_0x006b:
            long r48 = r2 & r43
            r25 = 0
            int r30 = (r48 > r25 ? 1 : (r48 == r25 ? 0 : -1))
            r0 = 1
            if (r30 == 0) goto L_0x00a1
            if (r10 == 0) goto L_0x0078
            android.databinding.ObservableField<java.lang.String> r9 = r10.musicAlbum
        L_0x0078:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0085
            java.lang.Object r30 = r9.get()
            r8 = r30
            java.lang.String r8 = (java.lang.String) r8
        L_0x0085:
            if (r8 != 0) goto L_0x008a
            r30 = r0
            goto L_0x008c
        L_0x008a:
            r30 = 0
        L_0x008c:
            r18 = r30
            long r49 = r2 & r43
            r25 = 0
            int r30 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r30 == 0) goto L_0x00a1
            if (r18 == 0) goto L_0x009d
            r49 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r49
            goto L_0x00a1
        L_0x009d:
            r49 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r49
        L_0x00a1:
            long r49 = r2 & r41
            r25 = 0
            int r30 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r30 == 0) goto L_0x00da
            if (r10 == 0) goto L_0x00ad
            android.databinding.ObservableField<java.lang.String> r11 = r10.musicName
        L_0x00ad:
            r0 = 2
            r1.updateRegistration((int) r0, (android.databinding.Observable) r11)
            if (r11 == 0) goto L_0x00ba
            java.lang.Object r0 = r11.get()
            r14 = r0
            java.lang.String r14 = (java.lang.String) r14
        L_0x00ba:
            if (r14 != 0) goto L_0x00be
            r0 = 1
            goto L_0x00bf
        L_0x00be:
            r0 = 0
        L_0x00bf:
            long r49 = r2 & r41
            r25 = 0
            int r23 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r23 == 0) goto L_0x00d8
            if (r0 == 0) goto L_0x00d1
            r49 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r49
            r23 = r0
            goto L_0x00da
        L_0x00d1:
            r49 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r49
            r23 = r0
            goto L_0x00da
        L_0x00d8:
            r23 = r0
        L_0x00da:
            long r49 = r2 & r45
            r25 = 0
            int r0 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0110
            if (r10 == 0) goto L_0x00e6
            android.databinding.ObservableField<java.lang.String> r13 = r10.musicAtist
        L_0x00e6:
            r0 = 3
            r1.updateRegistration((int) r0, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x00f4
            java.lang.Object r0 = r13.get()
            r22 = r0
            java.lang.String r22 = (java.lang.String) r22
        L_0x00f4:
            if (r22 != 0) goto L_0x00f8
            r0 = 1
            goto L_0x00f9
        L_0x00f8:
            r0 = 0
        L_0x00f9:
            long r48 = r2 & r45
            r25 = 0
            int r15 = (r48 > r25 ? 1 : (r48 == r25 ? 0 : -1))
            if (r15 == 0) goto L_0x010f
            if (r0 == 0) goto L_0x0109
            r48 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r48
            r15 = r0
            goto L_0x0110
        L_0x0109:
            r48 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r48
            r15 = r0
            goto L_0x0110
        L_0x010f:
            r15 = r0
        L_0x0110:
            long r48 = r2 & r37
            r25 = 0
            int r0 = (r48 > r25 ? 1 : (r48 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0135
            if (r10 == 0) goto L_0x011d
            android.databinding.ObservableField<java.lang.String> r0 = r10.totalTime
            goto L_0x011f
        L_0x011d:
            r0 = r19
        L_0x011f:
            r30 = r5
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0132
            java.lang.Object r5 = r0.get()
            r27 = r5
            java.lang.String r27 = (java.lang.String) r27
            r19 = r0
            goto L_0x0137
        L_0x0132:
            r19 = r0
            goto L_0x0137
        L_0x0135:
            r30 = r5
        L_0x0137:
            long r48 = r2 & r35
            r25 = 0
            int r0 = (r48 > r25 ? 1 : (r48 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0155
            if (r10 == 0) goto L_0x0144
            android.databinding.ObservableInt r0 = r10.progress
            goto L_0x0146
        L_0x0144:
            r0 = r20
        L_0x0146:
            r5 = 5
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0153
            int r17 = r0.get()
            r20 = r0
            goto L_0x0155
        L_0x0153:
            r20 = r0
        L_0x0155:
            long r48 = r2 & r33
            r25 = 0
            int r0 = (r48 > r25 ? 1 : (r48 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0179
            if (r10 == 0) goto L_0x0162
            android.databinding.ObservableField<java.lang.String> r0 = r10.currentTime
            goto L_0x0164
        L_0x0162:
            r0 = r24
        L_0x0164:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0174
            java.lang.Object r5 = r0.get()
            java.lang.String r5 = (java.lang.String) r5
            r24 = r0
            r0 = r5
            goto L_0x017b
        L_0x0174:
            r24 = r0
            r0 = r47
            goto L_0x017b
        L_0x0179:
            r0 = r47
        L_0x017b:
            long r47 = r2 & r31
            r25 = 0
            int r5 = (r47 > r25 ? 1 : (r47 == r25 ? 0 : -1))
            if (r5 == 0) goto L_0x01c7
            if (r10 == 0) goto L_0x0188
            android.databinding.ObservableInt r5 = r10.maxProgress
            goto L_0x018a
        L_0x0188:
            r5 = r28
        L_0x018a:
            r47 = r0
            r0 = 7
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x01ae
            int r0 = r5.get()
            r28 = r5
            r5 = r0
            r0 = r47
            r52 = r16
            r16 = r6
            r6 = r52
            r53 = r17
            r17 = r7
            r7 = r53
            r54 = r27
            r27 = r8
            r8 = r54
            goto L_0x01f4
        L_0x01ae:
            r28 = r5
            r5 = r30
            r0 = r47
            r52 = r16
            r16 = r6
            r6 = r52
            r53 = r17
            r17 = r7
            r7 = r53
            r54 = r27
            r27 = r8
            r8 = r54
            goto L_0x01f4
        L_0x01c7:
            r47 = r0
            r5 = r30
            r52 = r16
            r16 = r6
            r6 = r52
            r53 = r17
            r17 = r7
            r7 = r53
            r54 = r27
            r27 = r8
            r8 = r54
            goto L_0x01f4
        L_0x01de:
            r47 = r0
            r30 = r5
            r52 = r16
            r16 = r6
            r6 = r52
            r53 = r17
            r17 = r7
            r7 = r53
            r54 = r27
            r27 = r8
            r8 = r54
        L_0x01f4:
            r47 = 768(0x300, double:3.794E-321)
            long r49 = r2 & r47
            r25 = 0
            int r30 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r30 == 0) goto L_0x0208
            if (r4 == 0) goto L_0x0205
            r30 = r9
            android.view.View$OnFocusChangeListener r9 = r4.musicViewFocusChangeListener
            goto L_0x020c
        L_0x0205:
            r30 = r9
            goto L_0x020a
        L_0x0208:
            r30 = r9
        L_0x020a:
            r9 = r29
        L_0x020c:
            long r49 = r2 & r45
            r25 = 0
            int r29 = (r49 > r25 ? 1 : (r49 == r25 ? 0 : -1))
            if (r29 == 0) goto L_0x022f
            if (r15 == 0) goto L_0x0228
            r29 = r4
            android.widget.TextView r4 = r1.artistTextView
            android.content.res.Resources r4 = r4.getResources()
            r49 = r10
            r10 = 2131558746(0x7f0d015a, float:1.8742817E38)
            java.lang.String r4 = r4.getString(r10)
            goto L_0x022e
        L_0x0228:
            r29 = r4
            r49 = r10
            r4 = r22
        L_0x022e:
            goto L_0x0235
        L_0x022f:
            r29 = r4
            r49 = r10
            r4 = r17
        L_0x0235:
            long r50 = r2 & r43
            r25 = 0
            int r10 = (r50 > r25 ? 1 : (r50 == r25 ? 0 : -1))
            if (r10 == 0) goto L_0x0255
            if (r18 == 0) goto L_0x024f
            android.widget.TextView r10 = r1.albumTextView
            android.content.res.Resources r10 = r10.getResources()
            r17 = r11
            r11 = 2131558747(0x7f0d015b, float:1.8742819E38)
            java.lang.String r10 = r10.getString(r11)
            goto L_0x0253
        L_0x024f:
            r17 = r11
            r10 = r27
        L_0x0253:
            r12 = r10
            goto L_0x0257
        L_0x0255:
            r17 = r11
        L_0x0257:
            long r10 = r2 & r41
            r25 = 0
            int r10 = (r10 > r25 ? 1 : (r10 == r25 ? 0 : -1))
            if (r10 == 0) goto L_0x0273
            if (r23 == 0) goto L_0x026f
            android.widget.TextView r10 = r1.nameTextView
            android.content.res.Resources r10 = r10.getResources()
            r11 = 2131558748(0x7f0d015c, float:1.874282E38)
            java.lang.String r10 = r10.getString(r11)
            goto L_0x0270
        L_0x026f:
            r10 = r14
        L_0x0270:
            r21 = r10
            goto L_0x0275
        L_0x0273:
            r10 = r21
        L_0x0275:
            long r43 = r2 & r43
            r25 = 0
            int r11 = (r43 > r25 ? 1 : (r43 == r25 ? 0 : -1))
            if (r11 == 0) goto L_0x0282
            android.widget.TextView r11 = r1.albumTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r12)
        L_0x0282:
            long r43 = r2 & r45
            int r11 = (r43 > r25 ? 1 : (r43 == r25 ? 0 : -1))
            if (r11 == 0) goto L_0x028d
            android.widget.TextView r11 = r1.artistTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r4)
        L_0x028d:
            long r33 = r2 & r33
            int r11 = (r33 > r25 ? 1 : (r33 == r25 ? 0 : -1))
            if (r11 == 0) goto L_0x0298
            android.widget.TextView r11 = r1.currentTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r0)
        L_0x0298:
            r33 = 512(0x200, double:2.53E-321)
            long r33 = r2 & r33
            int r11 = (r33 > r25 ? 1 : (r33 == r25 ? 0 : -1))
            if (r11 == 0) goto L_0x02aa
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r11 = r1.imageFrameLayout
            r21 = r0
            android.view.View$OnClickListener r0 = r1.mCallback240
            r11.setOnClickListener(r0)
            goto L_0x02ac
        L_0x02aa:
            r21 = r0
        L_0x02ac:
            long r33 = r2 & r47
            int r0 = (r33 > r25 ? 1 : (r33 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02b7
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r0 = r1.imageFrameLayout
            r0.setOnFocusChangeListener(r9)
        L_0x02b7:
            long r33 = r2 & r39
            int r0 = (r33 > r25 ? 1 : (r33 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02c7
            android.widget.ImageView r0 = r1.imageView6
            com.wits.ksw.launcher.base.BaseBindingModel.setAlbumIcon(r0, r6)
            android.widget.ImageView r0 = r1.musicImageView
            com.wits.ksw.launcher.base.BaseBindingModel.setMusicAlbumIcon(r0, r6)
        L_0x02c7:
            long r33 = r2 & r41
            r25 = 0
            int r0 = (r33 > r25 ? 1 : (r33 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02d4
            android.widget.TextView r0 = r1.nameTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x02d4:
            long r31 = r2 & r31
            int r0 = (r31 > r25 ? 1 : (r31 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02df
            android.widget.SeekBar r0 = r1.seekBar
            r0.setMax(r5)
        L_0x02df:
            long r31 = r2 & r35
            int r0 = (r31 > r25 ? 1 : (r31 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02ea
            android.widget.SeekBar r0 = r1.seekBar
            android.databinding.adapters.SeekBarBindingAdapter.setProgress(r0, r7)
        L_0x02ea:
            long r31 = r2 & r37
            int r0 = (r31 > r25 ? 1 : (r31 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x02f5
            android.widget.TextView r0 = r1.totalTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x02f5:
            return
        L_0x02f6:
            r0 = move-exception
            monitor-exit(r55)     // Catch:{ all -> 0x02f6 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.Id7SubMusicViewBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if (mediaViewModel != null) {
            mediaViewModel.openMusic(callbackArg_0);
        }
    }
}

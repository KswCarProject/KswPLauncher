package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubMusicViewBindingSw600dpLandImpl extends Id7SubMusicViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback82;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.nameImageView, 10);
        sViewsWithIds.put(R.id.artistImageView, 11);
        sViewsWithIds.put(R.id.albumImageView, 12);
    }

    public Id7SubMusicViewBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private Id7SubMusicViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        this.mCallback82 = new OnClickListener(this, 1);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (22 != variableId) {
            return false;
        }
        setMediaViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setMediaViewModel(@Nullable LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(22);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 3:
                return onChangeMediaViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 6:
                return onChangeMediaViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 7:
                return onChangeMediaViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
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

    private boolean onChangeMediaViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoProgress(ObservableInt MediaViewModelMediaInfoProgress, int fieldId) {
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
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r60 = this;
            r1 = r60
            r2 = 0
            monitor-enter(r60)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x02b4 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02b4 }
            monitor-exit(r60)     // Catch:{ all -> 0x02b4 }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            com.wits.ksw.launcher.model.LauncherViewModel r11 = r1.mMediaViewModel
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
            r29 = 0
            r30 = 768(0x300, double:3.794E-321)
            long r32 = r2 & r30
            int r32 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r32 == 0) goto L_0x0042
            if (r11 == 0) goto L_0x0042
            android.view.View$OnFocusChangeListener r4 = r11.musicViewFocusChangeListener
            goto L_0x0044
        L_0x0042:
            r4 = r29
        L_0x0044:
            r32 = 767(0x2ff, double:3.79E-321)
            long r32 = r2 & r32
            r34 = 0
            int r5 = (r32 > r34 ? 1 : (r32 == r34 ? 0 : -1))
            r32 = 640(0x280, double:3.16E-321)
            r36 = 576(0x240, double:2.846E-321)
            r38 = 544(0x220, double:2.69E-321)
            r40 = 528(0x210, double:2.61E-321)
            r42 = 520(0x208, double:2.57E-321)
            r44 = 514(0x202, double:2.54E-321)
            r46 = 516(0x204, double:2.55E-321)
            r48 = 513(0x201, double:2.535E-321)
            if (r5 == 0) goto L_0x01bc
            com.wits.ksw.launcher.bean.MediaInfo r14 = com.wits.ksw.launcher.model.LauncherViewModel.mediaInfo
            long r50 = r2 & r48
            r34 = 0
            int r5 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            r52 = r0
            r0 = 0
            if (r5 == 0) goto L_0x0096
            if (r14 == 0) goto L_0x006f
            android.databinding.ObservableField<java.lang.String> r7 = r14.musicName
        L_0x006f:
            r1.updateRegistration((int) r0, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x007c
            java.lang.Object r5 = r7.get()
            r26 = r5
            java.lang.String r26 = (java.lang.String) r26
        L_0x007c:
            if (r26 != 0) goto L_0x0080
            r5 = 1
            goto L_0x0081
        L_0x0080:
            r5 = r0
        L_0x0081:
            long r50 = r2 & r48
            r34 = 0
            int r10 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r10 == 0) goto L_0x0095
            if (r5 == 0) goto L_0x0091
            r50 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r50
            goto L_0x0095
        L_0x0091:
            r50 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r50
        L_0x0095:
            r10 = r5
        L_0x0096:
            long r50 = r2 & r44
            r34 = 0
            int r5 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r5 == 0) goto L_0x00cd
            if (r14 == 0) goto L_0x00a2
            android.databinding.ObservableField<java.lang.String> r13 = r14.musicAlbum
        L_0x00a2:
            r5 = 1
            r1.updateRegistration((int) r5, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x00b0
            java.lang.Object r29 = r13.get()
            r25 = r29
            java.lang.String r25 = (java.lang.String) r25
        L_0x00b0:
            if (r25 != 0) goto L_0x00b5
            r29 = r5
            goto L_0x00b7
        L_0x00b5:
            r29 = r0
        L_0x00b7:
            r21 = r29
            long r50 = r2 & r44
            r34 = 0
            int r29 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r29 == 0) goto L_0x00ce
            if (r21 == 0) goto L_0x00c8
            r50 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r50
            goto L_0x00ce
        L_0x00c8:
            r50 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r50
            goto L_0x00ce
        L_0x00cd:
            r5 = 1
        L_0x00ce:
            long r50 = r2 & r46
            r34 = 0
            int r29 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r29 == 0) goto L_0x010a
            if (r14 == 0) goto L_0x00db
            android.databinding.ObservableField<java.lang.String> r0 = r14.musicAtist
            goto L_0x00dd
        L_0x00db:
            r0 = r16
        L_0x00dd:
            r5 = 2
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x00eb
            java.lang.Object r5 = r0.get()
            r23 = r5
            java.lang.String r23 = (java.lang.String) r23
        L_0x00eb:
            if (r23 != 0) goto L_0x00f0
            r53 = 1
            goto L_0x00f2
        L_0x00f0:
            r53 = 0
        L_0x00f2:
            r5 = r53
            long r50 = r2 & r46
            r34 = 0
            int r8 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r8 == 0) goto L_0x0107
            if (r5 == 0) goto L_0x0103
            r50 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r50
            goto L_0x0107
        L_0x0103:
            r50 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r50
        L_0x0107:
            r16 = r0
            r8 = r5
        L_0x010a:
            long r50 = r2 & r42
            r34 = 0
            int r0 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x012b
            if (r14 == 0) goto L_0x0117
            android.databinding.ObservableField<java.lang.String> r0 = r14.totalTime
            goto L_0x0119
        L_0x0117:
            r0 = r18
        L_0x0119:
            r5 = 3
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0129
            java.lang.Object r5 = r0.get()
            java.lang.String r5 = (java.lang.String) r5
            r18 = r0
            r12 = r5
            goto L_0x012b
        L_0x0129:
            r18 = r0
        L_0x012b:
            long r50 = r2 & r40
            r34 = 0
            int r0 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x014c
            if (r14 == 0) goto L_0x0138
            android.databinding.ObservableField<java.lang.String> r0 = r14.currentTime
            goto L_0x013a
        L_0x0138:
            r0 = r19
        L_0x013a:
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x014a
            java.lang.Object r5 = r0.get()
            java.lang.String r5 = (java.lang.String) r5
            r19 = r0
            r0 = r5
            goto L_0x014e
        L_0x014a:
            r19 = r0
        L_0x014c:
            r0 = r52
        L_0x014e:
            long r50 = r2 & r38
            r34 = 0
            int r5 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r5 == 0) goto L_0x016f
            if (r14 == 0) goto L_0x015b
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r5 = r14.pic
            goto L_0x015d
        L_0x015b:
            r5 = r24
        L_0x015d:
            r54 = r0
            r0 = 5
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x016c
            java.lang.Object r0 = r5.get()
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0
            r9 = r0
        L_0x016c:
            r24 = r5
            goto L_0x0171
        L_0x016f:
            r54 = r0
        L_0x0171:
            long r50 = r2 & r36
            r34 = 0
            int r0 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x0191
            if (r14 == 0) goto L_0x017e
            android.databinding.ObservableInt r0 = r14.progress
            goto L_0x0180
        L_0x017e:
            r0 = r27
        L_0x0180:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x018f
            int r5 = r0.get()
            r27 = r0
            r17 = r5
            goto L_0x0191
        L_0x018f:
            r27 = r0
        L_0x0191:
            long r50 = r2 & r32
            r34 = 0
            int r0 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x01ac
            if (r14 == 0) goto L_0x019e
            android.databinding.ObservableInt r0 = r14.maxProgress
            goto L_0x01a0
        L_0x019e:
            r0 = r28
        L_0x01a0:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01aa
            int r20 = r0.get()
        L_0x01aa:
            r28 = r0
        L_0x01ac:
            r5 = r20
            r0 = r54
            r59 = r13
            r13 = r7
            r7 = r17
            r17 = r16
            r16 = r14
            r14 = r59
            goto L_0x01cb
        L_0x01bc:
            r52 = r0
            r5 = r20
            r59 = r13
            r13 = r7
            r7 = r17
            r17 = r16
            r16 = r14
            r14 = r59
        L_0x01cb:
            long r50 = r2 & r46
            r34 = 0
            int r20 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r20 == 0) goto L_0x01ee
            if (r8 == 0) goto L_0x01e7
            r55 = r6
            android.widget.TextView r6 = r1.artistTextView
            android.content.res.Resources r6 = r6.getResources()
            r56 = r8
            r8 = 2131493185(0x7f0c0141, float:1.8609843E38)
            java.lang.String r6 = r6.getString(r8)
            goto L_0x01ed
        L_0x01e7:
            r55 = r6
            r56 = r8
            r6 = r23
        L_0x01ed:
            goto L_0x01f2
        L_0x01ee:
            r55 = r6
            r56 = r8
        L_0x01f2:
            long r50 = r2 & r44
            r34 = 0
            int r8 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r8 == 0) goto L_0x0212
            if (r21 == 0) goto L_0x020c
            android.widget.TextView r8 = r1.albumTextView
            android.content.res.Resources r8 = r8.getResources()
            r57 = r11
            r11 = 2131493186(0x7f0c0142, float:1.8609845E38)
            java.lang.String r8 = r8.getString(r11)
            goto L_0x0210
        L_0x020c:
            r57 = r11
            r8 = r25
        L_0x0210:
            r15 = r8
            goto L_0x0214
        L_0x0212:
            r57 = r11
        L_0x0214:
            long r50 = r2 & r48
            r34 = 0
            int r8 = (r50 > r34 ? 1 : (r50 == r34 ? 0 : -1))
            if (r8 == 0) goto L_0x0231
            if (r10 == 0) goto L_0x022c
            android.widget.TextView r8 = r1.nameTextView
            android.content.res.Resources r8 = r8.getResources()
            r11 = 2131493187(0x7f0c0143, float:1.8609847E38)
            java.lang.String r8 = r8.getString(r11)
            goto L_0x022e
        L_0x022c:
            r8 = r26
        L_0x022e:
            r22 = r8
            goto L_0x0233
        L_0x0231:
            r8 = r22
        L_0x0233:
            long r44 = r2 & r44
            r34 = 0
            int r11 = (r44 > r34 ? 1 : (r44 == r34 ? 0 : -1))
            if (r11 == 0) goto L_0x0240
            android.widget.TextView r11 = r1.albumTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r15)
        L_0x0240:
            long r44 = r2 & r46
            int r11 = (r44 > r34 ? 1 : (r44 == r34 ? 0 : -1))
            if (r11 == 0) goto L_0x024b
            android.widget.TextView r11 = r1.artistTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r6)
        L_0x024b:
            long r40 = r2 & r40
            int r11 = (r40 > r34 ? 1 : (r40 == r34 ? 0 : -1))
            if (r11 == 0) goto L_0x0256
            android.widget.TextView r11 = r1.currentTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r0)
        L_0x0256:
            r40 = 512(0x200, double:2.53E-321)
            long r40 = r2 & r40
            int r11 = (r40 > r34 ? 1 : (r40 == r34 ? 0 : -1))
            if (r11 == 0) goto L_0x0268
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r11 = r1.imageFrameLayout
            r58 = r0
            android.view.View$OnClickListener r0 = r1.mCallback82
            r11.setOnClickListener(r0)
            goto L_0x026a
        L_0x0268:
            r58 = r0
        L_0x026a:
            long r29 = r2 & r30
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x0275
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r0 = r1.imageFrameLayout
            r0.setOnFocusChangeListener(r4)
        L_0x0275:
            long r29 = r2 & r38
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x0285
            android.widget.ImageView r0 = r1.imageView6
            com.wits.ksw.launcher.base.BaseBindingModel.setAlbumIcon(r0, r9)
            android.widget.ImageView r0 = r1.musicImageView
            com.wits.ksw.launcher.base.BaseBindingModel.setMusicAlbumIcon(r0, r9)
        L_0x0285:
            long r29 = r2 & r48
            r34 = 0
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x0292
            android.widget.TextView r0 = r1.nameTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x0292:
            long r29 = r2 & r32
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x029d
            android.widget.SeekBar r0 = r1.seekBar
            r0.setMax(r5)
        L_0x029d:
            long r29 = r2 & r36
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x02a8
            android.widget.SeekBar r0 = r1.seekBar
            android.databinding.adapters.SeekBarBindingAdapter.setProgress(r0, r7)
        L_0x02a8:
            long r29 = r2 & r42
            int r0 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x02b3
            android.widget.TextView r0 = r1.totalTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x02b3:
            return
        L_0x02b4:
            r0 = move-exception
            monitor-exit(r60)     // Catch:{ all -> 0x02b4 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.Id7SubMusicViewBindingSw600dpLandImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if (mediaViewModel != null) {
            mediaViewModel.openMusic(callbackArg_0);
        }
    }
}

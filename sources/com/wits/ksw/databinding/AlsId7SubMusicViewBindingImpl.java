package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubMusicViewBindingImpl extends AlsId7SubMusicViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback192;
    private final View.OnClickListener mCallback193;
    private final View.OnClickListener mCallback194;
    private final View.OnClickListener mCallback195;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.music_title, 12);
        sparseIntArray.put(R.id.music_sub_title, 13);
    }

    public AlsId7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private AlsId7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, bindings[9], bindings[8], bindings[6], bindings[5], bindings[4], bindings[10], bindings[1], bindings[2], bindings[0], bindings[13], bindings[12], bindings[7], bindings[3], bindings[11]);
        this.mDirtyFlags = -1;
        this.albumTextView.setTag((Object) null);
        this.artistTextView.setTag((Object) null);
        this.btnMusicNext.setTag((Object) null);
        this.btnMusicPause.setTag((Object) null);
        this.btnMusicPrev.setTag((Object) null);
        this.currentTimeTextView.setTag((Object) null);
        this.imageFrameLayout.setTag((Object) null);
        this.musicAlbumBg.setTag((Object) null);
        this.musicConstraintLayout.setTag((Object) null);
        this.nameTextView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.totalTimeTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback192 = new OnClickListener(this, 1);
        this.mCallback193 = new OnClickListener(this, 2);
        this.mCallback194 = new OnClickListener(this, 3);
        this.mCallback195 = new OnClickListener(this, 4);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        if (7 != variableId) {
            return false;
        }
        setMusicPhoneViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMusicPhoneViewModelMediaInfoMusicPlay((ObservableField) object, fieldId);
            case 1:
                return onChangeMusicPhoneViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 2:
                return onChangeMusicPhoneViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 3:
                return onChangeMusicPhoneViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 4:
                return onChangeMusicPhoneViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 5:
                return onChangeMusicPhoneViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 6:
                return onChangeMusicPhoneViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            case 7:
                return onChangeMusicPhoneViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 8:
                return onChangeMusicPhoneViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 9:
                return onChangeMusicPhoneViewModelBThirdMusic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoProgress(ObservableInt MusicPhoneViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMaxProgress(ObservableInt MusicPhoneViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelBThirdMusic(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: java.lang.Boolean} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r69 = this;
            r1 = r69
            r2 = 0
            monitor-enter(r69)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0441 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0441 }
            monitor-exit(r69)     // Catch:{ all -> 0x0441 }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r9 = r1.mMusicPhoneViewModel
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
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 3072(0xc00, double:1.518E-320)
            long r42 = r2 & r40
            int r42 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r42 == 0) goto L_0x0055
            if (r9 == 0) goto L_0x0055
            android.view.View$OnFocusChangeListener r6 = r9.musicViewFocusChangeListener
        L_0x0055:
            r42 = 2559(0x9ff, double:1.2643E-320)
            long r42 = r2 & r42
            int r42 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            r45 = 2176(0x880, double:1.075E-320)
            r47 = 2112(0x840, double:1.0435E-320)
            r49 = 2080(0x820, double:1.0277E-320)
            r51 = 2064(0x810, double:1.0198E-320)
            r53 = 2052(0x804, double:1.014E-320)
            r55 = 2049(0x801, double:1.0123E-320)
            r57 = 2056(0x808, double:1.016E-320)
            r59 = 2050(0x802, double:1.013E-320)
            r61 = 2304(0x900, double:1.1383E-320)
            r5 = 0
            if (r42 == 0) goto L_0x025e
            com.wits.ksw.launcher.bean.MediaInfo r4 = com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.mediaInfo
            long r64 = r2 & r55
            r43 = 0
            int r20 = (r64 > r43 ? 1 : (r64 == r43 ? 0 : -1))
            if (r20 == 0) goto L_0x00c7
            if (r4 == 0) goto L_0x007e
            android.databinding.ObservableField<java.lang.Boolean> r12 = r4.musicPlay
        L_0x007e:
            r1.updateRegistration((int) r5, (android.databinding.Observable) r12)
            if (r12 == 0) goto L_0x008b
            java.lang.Object r20 = r12.get()
            r39 = r20
            java.lang.Boolean r39 = (java.lang.Boolean) r39
        L_0x008b:
            boolean r38 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r39)
            long r64 = r2 & r55
            r43 = 0
            int r20 = (r64 > r43 ? 1 : (r64 == r43 ? 0 : -1))
            if (r20 == 0) goto L_0x00a3
            if (r38 == 0) goto L_0x009f
            r64 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r64
            goto L_0x00a3
        L_0x009f:
            r64 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r64
        L_0x00a3:
            if (r38 == 0) goto L_0x00b5
            android.widget.ImageView r5 = r1.btnMusicPause
            android.content.Context r5 = r5.getContext()
            r65 = r0
            r0 = 2131230980(0x7f080104, float:1.8078028E38)
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r5, r0)
            goto L_0x00c4
        L_0x00b5:
            r65 = r0
            android.widget.ImageView r0 = r1.btnMusicPause
            android.content.Context r0 = r0.getContext()
            r5 = 2131230981(0x7f080105, float:1.807803E38)
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r0, r5)
        L_0x00c4:
            r27 = r0
            goto L_0x00c9
        L_0x00c7:
            r65 = r0
        L_0x00c9:
            long r66 = r2 & r59
            r43 = 0
            int r0 = (r66 > r43 ? 1 : (r66 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x00ff
            if (r4 == 0) goto L_0x00d5
            android.databinding.ObservableField<java.lang.String> r15 = r4.musicAtist
        L_0x00d5:
            r0 = 1
            r1.updateRegistration((int) r0, (android.databinding.Observable) r15)
            if (r15 == 0) goto L_0x00e3
            java.lang.Object r0 = r15.get()
            r26 = r0
            java.lang.String r26 = (java.lang.String) r26
        L_0x00e3:
            if (r26 != 0) goto L_0x00e7
            r0 = 1
            goto L_0x00e8
        L_0x00e7:
            r0 = 0
        L_0x00e8:
            r23 = r0
            long r66 = r2 & r59
            r43 = 0
            int r0 = (r66 > r43 ? 1 : (r66 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x00ff
            if (r23 == 0) goto L_0x00fa
            r66 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r66
            goto L_0x00ff
        L_0x00fa:
            r66 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r66
        L_0x00ff:
            long r66 = r2 & r53
            r43 = 0
            int r0 = (r66 > r43 ? 1 : (r66 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x0121
            if (r4 == 0) goto L_0x010c
            android.databinding.ObservableInt r0 = r4.progress
            goto L_0x010e
        L_0x010c:
            r0 = r18
        L_0x010e:
            r5 = 2
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x011c
            int r5 = r0.get()
            r18 = r0
            r0 = r5
            goto L_0x0123
        L_0x011c:
            r18 = r0
            r0 = r65
            goto L_0x0123
        L_0x0121:
            r0 = r65
        L_0x0123:
            long r65 = r2 & r57
            r43 = 0
            int r5 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r5 == 0) goto L_0x0165
            if (r4 == 0) goto L_0x0130
            android.databinding.ObservableField<java.lang.String> r5 = r4.musicName
            goto L_0x0132
        L_0x0130:
            r5 = r19
        L_0x0132:
            r20 = r0
            r0 = 3
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0142
            java.lang.Object r0 = r5.get()
            r22 = r0
            java.lang.String r22 = (java.lang.String) r22
        L_0x0142:
            if (r22 != 0) goto L_0x0146
            r0 = 1
            goto L_0x0147
        L_0x0146:
            r0 = 0
        L_0x0147:
            r14 = r0
            long r65 = r2 & r57
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x0162
            if (r14 == 0) goto L_0x015a
            r65 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r65
            r19 = r5
            goto L_0x0167
        L_0x015a:
            r65 = 262144(0x40000, double:1.295163E-318)
            long r2 = r2 | r65
            r19 = r5
            goto L_0x0167
        L_0x0162:
            r19 = r5
            goto L_0x0167
        L_0x0165:
            r20 = r0
        L_0x0167:
            long r65 = r2 & r51
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x0188
            if (r4 == 0) goto L_0x0174
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r0 = r4.pic
            goto L_0x0176
        L_0x0174:
            r0 = r21
        L_0x0176:
            r5 = 4
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0186
            java.lang.Object r5 = r0.get()
            r7 = r5
            android.graphics.drawable.BitmapDrawable r7 = (android.graphics.drawable.BitmapDrawable) r7
            r21 = r0
            goto L_0x0188
        L_0x0186:
            r21 = r0
        L_0x0188:
            long r65 = r2 & r49
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x01a9
            if (r4 == 0) goto L_0x0195
            android.databinding.ObservableField<java.lang.String> r0 = r4.currentTime
            goto L_0x0197
        L_0x0195:
            r0 = r24
        L_0x0197:
            r5 = 5
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01a7
            java.lang.Object r5 = r0.get()
            r8 = r5
            java.lang.String r8 = (java.lang.String) r8
            r24 = r0
            goto L_0x01a9
        L_0x01a7:
            r24 = r0
        L_0x01a9:
            long r65 = r2 & r47
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x01c7
            if (r4 == 0) goto L_0x01b6
            android.databinding.ObservableInt r0 = r4.maxProgress
            goto L_0x01b8
        L_0x01b6:
            r0 = r30
        L_0x01b8:
            r5 = 6
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01c5
            int r29 = r0.get()
            r30 = r0
            goto L_0x01c7
        L_0x01c5:
            r30 = r0
        L_0x01c7:
            long r65 = r2 & r45
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x01e9
            if (r4 == 0) goto L_0x01d4
            android.databinding.ObservableField<java.lang.String> r0 = r4.totalTime
            goto L_0x01d6
        L_0x01d4:
            r0 = r31
        L_0x01d6:
            r5 = 7
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x01e7
            java.lang.Object r5 = r0.get()
            r17 = r5
            java.lang.String r17 = (java.lang.String) r17
            r31 = r0
            goto L_0x01e9
        L_0x01e7:
            r31 = r0
        L_0x01e9:
            long r65 = r2 & r61
            r43 = 0
            int r0 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r0 == 0) goto L_0x024f
            if (r4 == 0) goto L_0x01f6
            android.databinding.ObservableField<java.lang.String> r0 = r4.musicAlbum
            goto L_0x01f8
        L_0x01f6:
            r0 = r34
        L_0x01f8:
            r5 = 8
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0207
            java.lang.Object r34 = r0.get()
            r11 = r34
            java.lang.String r11 = (java.lang.String) r11
        L_0x0207:
            if (r11 != 0) goto L_0x020c
            r34 = 1
            goto L_0x020e
        L_0x020c:
            r34 = 0
        L_0x020e:
            r10 = r34
            long r65 = r2 & r61
            r43 = 0
            int r34 = (r65 > r43 ? 1 : (r65 == r43 ? 0 : -1))
            if (r34 == 0) goto L_0x0240
            if (r10 == 0) goto L_0x022d
            r65 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r65
            r34 = r0
            r0 = r20
            r5 = r27
            r20 = r4
            r4 = r17
            r17 = r9
            r9 = r29
            goto L_0x026a
        L_0x022d:
            r65 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r65
            r34 = r0
            r0 = r20
            r5 = r27
            r20 = r4
            r4 = r17
            r17 = r9
            r9 = r29
            goto L_0x026a
        L_0x0240:
            r34 = r0
            r0 = r20
            r5 = r27
            r20 = r4
            r4 = r17
            r17 = r9
            r9 = r29
            goto L_0x026a
        L_0x024f:
            r5 = 8
            r0 = r20
            r5 = r27
            r20 = r4
            r4 = r17
            r17 = r9
            r9 = r29
            goto L_0x026a
        L_0x025e:
            r65 = r0
            r5 = 8
            r4 = r17
            r5 = r27
            r17 = r9
            r9 = r29
        L_0x026a:
            r65 = 2560(0xa00, double:1.265E-320)
            long r67 = r2 & r65
            r43 = 0
            int r27 = (r67 > r43 ? 1 : (r67 == r43 ? 0 : -1))
            if (r27 == 0) goto L_0x02e9
            r27 = r11
            android.databinding.ObservableField r11 = com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.bThirdMusic
            r29 = r12
            r12 = 9
            r1.updateRegistration((int) r12, (android.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0288
            java.lang.Object r12 = r11.get()
            r13 = r12
            java.lang.Boolean r13 = (java.lang.Boolean) r13
        L_0x0288:
            boolean r12 = android.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            r37 = r11
            r11 = 1
            if (r12 != r11) goto L_0x0292
            goto L_0x0293
        L_0x0292:
            r11 = 0
        L_0x0293:
            r25 = r11
            long r67 = r2 & r65
            r42 = 0
            int r11 = (r67 > r42 ? 1 : (r67 == r42 ? 0 : -1))
            if (r11 == 0) goto L_0x02b4
            if (r25 == 0) goto L_0x02aa
            r67 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r67
            r67 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r67
            goto L_0x02b4
        L_0x02aa:
            r67 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r67
            r67 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r67
        L_0x02b4:
            if (r25 == 0) goto L_0x02c6
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r11 = r1.imageFrameLayout
            android.content.Context r11 = r11.getContext()
            r67 = r2
            r2 = 2131230987(0x7f08010b, float:1.8078042E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r11, r2)
            goto L_0x02d5
        L_0x02c6:
            r67 = r2
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r2 = r1.imageFrameLayout
            android.content.Context r2 = r2.getContext()
            r3 = 2131230986(0x7f08010a, float:1.807804E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x02d5:
            r33 = r2
            if (r25 == 0) goto L_0x02dc
            r63 = 8
            goto L_0x02de
        L_0x02dc:
            r63 = 0
        L_0x02de:
            r35 = r63
            r36 = r12
            r11 = r33
            r12 = r35
            r2 = r67
            goto L_0x02f1
        L_0x02e9:
            r27 = r11
            r29 = r12
            r11 = r33
            r12 = r35
        L_0x02f1:
            long r63 = r2 & r61
            r42 = 0
            int r33 = (r63 > r42 ? 1 : (r63 == r42 ? 0 : -1))
            if (r33 == 0) goto L_0x0316
            if (r10 == 0) goto L_0x030d
            r33 = r10
            android.widget.TextView r10 = r1.albumTextView
            android.content.res.Resources r10 = r10.getResources()
            r35 = r13
            r13 = 2131558751(0x7f0d015f, float:1.8742827E38)
            java.lang.String r10 = r10.getString(r13)
            goto L_0x0313
        L_0x030d:
            r33 = r10
            r35 = r13
            r10 = r27
        L_0x0313:
            r16 = r10
            goto L_0x031c
        L_0x0316:
            r33 = r10
            r35 = r13
            r10 = r16
        L_0x031c:
            long r63 = r2 & r59
            r42 = 0
            int r13 = (r63 > r42 ? 1 : (r63 == r42 ? 0 : -1))
            if (r13 == 0) goto L_0x033d
            if (r23 == 0) goto L_0x0336
            android.widget.TextView r13 = r1.artistTextView
            android.content.res.Resources r13 = r13.getResources()
            r16 = r15
            r15 = 2131558750(0x7f0d015e, float:1.8742825E38)
            java.lang.String r13 = r13.getString(r15)
            goto L_0x033a
        L_0x0336:
            r16 = r15
            r13 = r26
        L_0x033a:
            r28 = r13
            goto L_0x0341
        L_0x033d:
            r16 = r15
            r13 = r28
        L_0x0341:
            long r63 = r2 & r57
            r42 = 0
            int r15 = (r63 > r42 ? 1 : (r63 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x0362
            if (r14 == 0) goto L_0x035b
            android.widget.TextView r15 = r1.nameTextView
            android.content.res.Resources r15 = r15.getResources()
            r28 = r14
            r14 = 2131558752(0x7f0d0160, float:1.8742829E38)
            java.lang.String r14 = r15.getString(r14)
            goto L_0x035f
        L_0x035b:
            r28 = r14
            r14 = r22
        L_0x035f:
            r32 = r14
            goto L_0x0366
        L_0x0362:
            r28 = r14
            r14 = r32
        L_0x0366:
            long r61 = r2 & r61
            r42 = 0
            int r15 = (r61 > r42 ? 1 : (r61 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x0373
            android.widget.TextView r15 = r1.albumTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r15, r10)
        L_0x0373:
            long r61 = r2 & r65
            int r15 = (r61 > r42 ? 1 : (r61 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x03b0
            android.widget.TextView r15 = r1.albumTextView
            r15.setVisibility(r12)
            android.widget.TextView r15 = r1.artistTextView
            r15.setVisibility(r12)
            android.widget.ImageView r15 = r1.btnMusicNext
            r15.setVisibility(r12)
            android.widget.ImageView r15 = r1.btnMusicPause
            r15.setVisibility(r12)
            android.widget.ImageView r15 = r1.btnMusicPrev
            r15.setVisibility(r12)
            android.widget.TextView r15 = r1.currentTimeTextView
            r15.setVisibility(r12)
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r15 = r1.imageFrameLayout
            android.databinding.adapters.ViewBindingAdapter.setBackground(r15, r11)
            android.widget.ImageView r15 = r1.musicAlbumBg
            r15.setVisibility(r12)
            android.widget.TextView r15 = r1.nameTextView
            r15.setVisibility(r12)
            com.wits.ksw.launcher.als_id7.view.MusicSeekBar r15 = r1.seekBar
            r15.setVisibility(r12)
            android.widget.TextView r15 = r1.totalTimeTextView
            r15.setVisibility(r12)
        L_0x03b0:
            long r59 = r2 & r59
            r42 = 0
            int r15 = (r59 > r42 ? 1 : (r59 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x03bd
            android.widget.TextView r15 = r1.artistTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r15, r13)
        L_0x03bd:
            r59 = 2048(0x800, double:1.0118E-320)
            long r59 = r2 & r59
            int r15 = (r59 > r42 ? 1 : (r59 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x03e4
            android.widget.ImageView r15 = r1.btnMusicNext
            r32 = r10
            android.view.View$OnClickListener r10 = r1.mCallback195
            r15.setOnClickListener(r10)
            android.widget.ImageView r10 = r1.btnMusicPause
            android.view.View$OnClickListener r15 = r1.mCallback194
            r10.setOnClickListener(r15)
            android.widget.ImageView r10 = r1.btnMusicPrev
            android.view.View$OnClickListener r15 = r1.mCallback193
            r10.setOnClickListener(r15)
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r10 = r1.imageFrameLayout
            android.view.View$OnClickListener r15 = r1.mCallback192
            r10.setOnClickListener(r15)
            goto L_0x03e6
        L_0x03e4:
            r32 = r10
        L_0x03e6:
            long r55 = r2 & r55
            r42 = 0
            int r10 = (r55 > r42 ? 1 : (r55 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x03f3
            android.widget.ImageView r10 = r1.btnMusicPause
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r10, r5)
        L_0x03f3:
            long r49 = r2 & r49
            int r10 = (r49 > r42 ? 1 : (r49 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x03fe
            android.widget.TextView r10 = r1.currentTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r10, r8)
        L_0x03fe:
            long r40 = r2 & r40
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x0409
            com.wits.ksw.launcher.view.CustomBmwMusicLayout r10 = r1.imageFrameLayout
            r10.setOnFocusChangeListener(r6)
        L_0x0409:
            long r40 = r2 & r51
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x0414
            android.widget.ImageView r10 = r1.musicAlbumBg
            com.wits.ksw.launcher.base.BaseBindingModel.setAlsID7AlbumIcon(r10, r7)
        L_0x0414:
            long r40 = r2 & r57
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x041f
            android.widget.TextView r10 = r1.nameTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r10, r14)
        L_0x041f:
            long r40 = r2 & r47
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x042a
            com.wits.ksw.launcher.als_id7.view.MusicSeekBar r10 = r1.seekBar
            r10.setMax(r9)
        L_0x042a:
            long r40 = r2 & r53
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x0435
            com.wits.ksw.launcher.als_id7.view.MusicSeekBar r10 = r1.seekBar
            android.databinding.adapters.SeekBarBindingAdapter.setProgress(r10, r0)
        L_0x0435:
            long r40 = r2 & r45
            int r10 = (r40 > r42 ? 1 : (r40 == r42 ? 0 : -1))
            if (r10 == 0) goto L_0x0440
            android.widget.TextView r10 = r1.totalTimeTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r10, r4)
        L_0x0440:
            return
        L_0x0441:
            r0 = move-exception
            monitor-exit(r69)     // Catch:{ all -> 0x0441 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AlsId7SubMusicViewBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean musicPhoneViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AlsID7ViewModel musicPhoneViewModel2 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel2 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel2.btnMusicPrev(callbackArg_0);
                    return;
                }
                return;
            case 3:
                AlsID7ViewModel musicPhoneViewModel3 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel3 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel3.btnMusicPlayPause(callbackArg_0);
                    return;
                }
                return;
            case 4:
                AlsID7ViewModel musicPhoneViewModel4 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel4 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel4.btnMusicNext(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

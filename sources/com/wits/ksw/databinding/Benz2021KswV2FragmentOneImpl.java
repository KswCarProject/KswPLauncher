package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class Benz2021KswV2FragmentOneImpl extends Benz2021KswV2FragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final BenzMbuxItemView mboundView3;
    private final BenzMbuxItemView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navi_rl, 10);
        sparseIntArray.put(R.id.navi_tv, 11);
        sparseIntArray.put(R.id.navi_tip, 12);
        sparseIntArray.put(R.id.iv_navi1, 13);
        sparseIntArray.put(R.id.space1, 14);
        sparseIntArray.put(R.id.iv_navi2, 15);
        sparseIntArray.put(R.id.music_rl, 16);
        sparseIntArray.put(R.id.music_tv, 17);
        sparseIntArray.put(R.id.iv_music1, 18);
        sparseIntArray.put(R.id.space2, 19);
        sparseIntArray.put(R.id.iv_music2, 20);
        sparseIntArray.put(R.id.bt_rl, 21);
        sparseIntArray.put(R.id.bt_tv, 22);
        sparseIntArray.put(R.id.iv_bt1, 23);
        sparseIntArray.put(R.id.space3, 24);
        sparseIntArray.put(R.id.iv_bt2, 25);
        sparseIntArray.put(R.id.weather_rl, 26);
        sparseIntArray.put(R.id.weather_tv, 27);
        sparseIntArray.put(R.id.weather_tip, 28);
        sparseIntArray.put(R.id.iv_weather1, 29);
        sparseIntArray.put(R.id.space4, 30);
        sparseIntArray.put(R.id.iv_weather2, 31);
        sparseIntArray.put(R.id.setting_rl, 32);
        sparseIntArray.put(R.id.set_tv, 33);
        sparseIntArray.put(R.id.set_tip, 34);
        sparseIntArray.put(R.id.iv_set1, 35);
        sparseIntArray.put(R.id.space5, 36);
        sparseIntArray.put(R.id.iv_set2, 37);
    }

    public Benz2021KswV2FragmentOneImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }

    private Benz2021KswV2FragmentOneImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[6], bindings[21], bindings[7], bindings[22], bindings[0], bindings[23], bindings[25], bindings[18], bindings[20], bindings[13], bindings[15], bindings[35], bindings[37], bindings[29], bindings[31], bindings[2], bindings[16], bindings[5], bindings[17], bindings[1], bindings[10], bindings[12], bindings[11], bindings[9], bindings[34], bindings[33], bindings[32], bindings[14], bindings[19], bindings[24], bindings[30], bindings[36], bindings[8], bindings[26], bindings[28], bindings[27]);
        this.mDirtyFlags = -1;
        this.btItemview.setTag((Object) null);
        this.btTip.setTag((Object) null);
        this.fragmentOneLl.setTag((Object) null);
        BenzMbuxItemView benzMbuxItemView = bindings[3];
        this.mboundView3 = benzMbuxItemView;
        benzMbuxItemView.setTag((Object) null);
        BenzMbuxItemView benzMbuxItemView2 = bindings[4];
        this.mboundView4 = benzMbuxItemView2;
        benzMbuxItemView2.setTag((Object) null);
        this.musicItemview.setTag((Object) null);
        this.musicTip.setTag((Object) null);
        this.naviItemview.setTag((Object) null);
        this.setItemview.setTag((Object) null);
        this.weatherItemview.setTag((Object) null);
        setRootTag(root);
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoPicZoom((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoSongInfo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPicZoom(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r76 = this;
            r1 = r76
            r2 = 0
            monitor-enter(r76)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x043e }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x043e }
            monitor-exit(r76)     // Catch:{ all -> 0x043e }
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
            com.wits.ksw.launcher.model.BcVieModel r4 = r1.mViewModel
            r5 = 0
            r31 = 123(0x7b, double:6.1E-322)
            long r31 = r2 & r31
            r29 = 0
            int r31 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            r32 = 80
            r34 = 1024(0x400, double:5.06E-321)
            r36 = 16384(0x4000, double:8.0948E-320)
            r38 = r0
            r39 = 122(0x7a, double:6.03E-322)
            r41 = 65
            if (r31 == 0) goto L_0x0151
            com.wits.ksw.launcher.bean.MediaInfo r0 = com.wits.ksw.launcher.model.BcVieModel.mediaInfo
            long r43 = r2 & r41
            r29 = 0
            int r21 = (r43 > r29 ? 1 : (r43 == r29 ? 0 : -1))
            r43 = r5
            r5 = 0
            if (r21 == 0) goto L_0x0089
            if (r0 == 0) goto L_0x005c
            android.databinding.ObservableField<java.lang.String> r7 = r0.songInfo
        L_0x005c:
            r1.updateRegistration((int) r5, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0068
            java.lang.Object r21 = r7.get()
            java.lang.String r21 = (java.lang.String) r21
            goto L_0x006a
        L_0x0068:
            r21 = r43
        L_0x006a:
            if (r21 != 0) goto L_0x006f
            r43 = 1
            goto L_0x0071
        L_0x006f:
            r43 = r5
        L_0x0071:
            r25 = r43
            long r43 = r2 & r41
            r29 = 0
            int r43 = (r43 > r29 ? 1 : (r43 == r29 ? 0 : -1))
            if (r43 == 0) goto L_0x008b
            if (r25 == 0) goto L_0x0083
            r43 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r43
            goto L_0x008b
        L_0x0083:
            r43 = 33554432(0x2000000, double:1.6578092E-316)
            long r2 = r2 | r43
            goto L_0x008b
        L_0x0089:
            r21 = r43
        L_0x008b:
            long r43 = r2 & r39
            r29 = 0
            int r43 = (r43 > r29 ? 1 : (r43 == r29 ? 0 : -1))
            if (r43 == 0) goto L_0x0144
            if (r0 == 0) goto L_0x0098
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r5 = r0.pic
            goto L_0x009a
        L_0x0098:
            r5 = r28
        L_0x009a:
            r45 = r0
            r0 = 4
            r1.updateRegistration((int) r0, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x00a9
            java.lang.Object r0 = r5.get()
            r15 = r0
            android.graphics.drawable.BitmapDrawable r15 = (android.graphics.drawable.BitmapDrawable) r15
        L_0x00a9:
            if (r15 != 0) goto L_0x00ad
            r0 = 1
            goto L_0x00ae
        L_0x00ad:
            r0 = 0
        L_0x00ae:
            r19 = r0
            long r46 = r2 & r39
            r28 = 0
            int r0 = (r46 > r28 ? 1 : (r46 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x00c7
            if (r19 == 0) goto L_0x00bf
            long r2 = r2 | r34
            long r2 = r2 | r36
            goto L_0x00c7
        L_0x00bf:
            r46 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r46
            r46 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r46
        L_0x00c7:
            long r46 = r2 & r32
            r28 = 0
            int r0 = (r46 > r28 ? 1 : (r46 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x00f0
            if (r19 == 0) goto L_0x00e1
            r46 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r46
            r46 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r46
            r46 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r46
            goto L_0x00f0
        L_0x00e1:
            r46 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r46
            r46 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r46
            r46 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r46
        L_0x00f0:
            long r46 = r2 & r32
            r28 = 0
            int r0 = (r46 > r28 ? 1 : (r46 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0135
            if (r19 == 0) goto L_0x00fd
            r0 = 8
            goto L_0x00fe
        L_0x00fd:
            r0 = 0
        L_0x00fe:
            r10 = r0
            if (r19 == 0) goto L_0x010d
            android.widget.FrameLayout r0 = r1.musicItemview
            android.content.Context r0 = r0.getContext()
            r46 = r2
            r2 = 2131234973(0x7f08109d, float:1.8086127E38)
            goto L_0x0118
        L_0x010d:
            r46 = r2
            android.widget.FrameLayout r0 = r1.musicItemview
            android.content.Context r0 = r0.getContext()
            r2 = 2131231997(0x7f0804fd, float:1.808009E38)
        L_0x0118:
            android.graphics.drawable.Drawable r0 = android.support.v7.content.res.AppCompatResources.getDrawable(r0, r2)
            r17 = r0
            if (r19 == 0) goto L_0x0122
            r0 = 0
            goto L_0x0124
        L_0x0122:
            r0 = 8
        L_0x0124:
            r27 = r0
            r28 = r5
            r0 = r17
            r17 = r21
            r5 = r45
            r2 = r46
            r21 = r6
            r6 = r27
            goto L_0x015d
        L_0x0135:
            r46 = r2
            r28 = r5
            r0 = r17
            r17 = r21
            r5 = r45
            r21 = r6
            r6 = r27
            goto L_0x015d
        L_0x0144:
            r45 = r0
            r0 = r17
            r17 = r21
            r5 = r45
            r21 = r6
            r6 = r27
            goto L_0x015d
        L_0x0151:
            r43 = r5
            r0 = r17
            r5 = r21
            r17 = r43
            r21 = r6
            r6 = r27
        L_0x015d:
            r43 = 102(0x66, double:5.04E-322)
            long r43 = r2 & r43
            r29 = 0
            int r27 = (r43 > r29 ? 1 : (r43 == r29 ? 0 : -1))
            r43 = 1048576(0x100000, double:5.180654E-318)
            r45 = 524288(0x80000, double:2.590327E-318)
            r47 = 4096(0x1000, double:2.0237E-320)
            r49 = 2048(0x800, double:1.0118E-320)
            r51 = 268435456(0x10000000, double:1.32624737E-315)
            r53 = 16777216(0x1000000, double:8.289046E-317)
            r55 = 262144(0x40000, double:1.295163E-318)
            r57 = 256(0x100, double:1.265E-321)
            r59 = 134217728(0x8000000, double:6.63123685E-316)
            r61 = 8388608(0x800000, double:4.144523E-317)
            r63 = 131072(0x20000, double:6.47582E-319)
            r65 = 128(0x80, double:6.32E-322)
            r67 = 100
            r69 = 98
            if (r27 == 0) goto L_0x02a5
            long r71 = r2 & r69
            r29 = 0
            int r27 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r27 == 0) goto L_0x024c
            if (r4 == 0) goto L_0x0197
            android.databinding.ObservableBoolean r13 = r4.itemIconClassical
        L_0x0197:
            r27 = r7
            r7 = 1
            r1.updateRegistration((int) r7, (android.databinding.Observable) r13)
            if (r13 == 0) goto L_0x01a3
            boolean r26 = r13.get()
        L_0x01a3:
            long r71 = r2 & r69
            r29 = 0
            int r7 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r7 == 0) goto L_0x01be
            if (r26 == 0) goto L_0x01b6
            long r2 = r2 | r57
            long r2 = r2 | r55
            long r2 = r2 | r53
            long r2 = r2 | r51
            goto L_0x01be
        L_0x01b6:
            long r2 = r2 | r65
            long r2 = r2 | r63
            long r2 = r2 | r61
            long r2 = r2 | r59
        L_0x01be:
            long r71 = r2 & r36
            r29 = 0
            int r7 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r7 == 0) goto L_0x01cd
            if (r26 == 0) goto L_0x01cb
            long r2 = r2 | r47
            goto L_0x01cd
        L_0x01cb:
            long r2 = r2 | r49
        L_0x01cd:
            long r71 = r2 & r34
            r29 = 0
            int r7 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r7 == 0) goto L_0x01dc
            if (r26 == 0) goto L_0x01da
            long r2 = r2 | r43
            goto L_0x01dc
        L_0x01da:
            long r2 = r2 | r45
        L_0x01dc:
            if (r26 == 0) goto L_0x01ee
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r7 = r1.weatherItemview
            android.content.Context r7 = r7.getContext()
            r71 = r2
            r2 = 2131232095(0x7f08055f, float:1.808029E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r7, r2)
            goto L_0x01fd
        L_0x01ee:
            r71 = r2
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r2 = r1.weatherItemview
            android.content.Context r2 = r2.getContext()
            r3 = 2131232098(0x7f080562, float:1.8080296E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x01fd:
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r3 = r1.btItemview
            android.content.Context r3 = r3.getContext()
            if (r26 == 0) goto L_0x0209
            r7 = 2131232063(0x7f08053f, float:1.8080225E38)
            goto L_0x020c
        L_0x0209:
            r7 = 2131232064(0x7f080540, float:1.8080227E38)
        L_0x020c:
            android.graphics.drawable.Drawable r3 = android.support.v7.content.res.AppCompatResources.getDrawable(r3, r7)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r7 = r1.naviItemview
            android.content.Context r7 = r7.getContext()
            if (r26 == 0) goto L_0x021c
            r11 = 2131232075(0x7f08054b, float:1.808025E38)
            goto L_0x021f
        L_0x021c:
            r11 = 2131232076(0x7f08054c, float:1.8080251E38)
        L_0x021f:
            android.graphics.drawable.Drawable r7 = android.support.v7.content.res.AppCompatResources.getDrawable(r7, r11)
            if (r26 == 0) goto L_0x0235
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r11 = r1.setItemview
            android.content.Context r11 = r11.getContext()
            r38 = r2
            r2 = 2131232077(0x7f08054d, float:1.8080253E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r11, r2)
            goto L_0x0244
        L_0x0235:
            r38 = r2
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r2 = r1.setItemview
            android.content.Context r2 = r2.getContext()
            r11 = 2131232078(0x7f08054e, float:1.8080255E38)
            android.graphics.drawable.Drawable r2 = android.support.v7.content.res.AppCompatResources.getDrawable(r2, r11)
        L_0x0244:
            r24 = r2
            r11 = r3
            r18 = r7
            r2 = r71
            goto L_0x024e
        L_0x024c:
            r27 = r7
        L_0x024e:
            long r71 = r2 & r67
            r29 = 0
            int r7 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r7 == 0) goto L_0x0291
            if (r4 == 0) goto L_0x025b
            android.databinding.ObservableField r7 = r4.btState
            r14 = r7
        L_0x025b:
            r7 = 2
            r1.updateRegistration((int) r7, (android.databinding.Observable) r14)
            if (r14 == 0) goto L_0x027d
            java.lang.Object r7 = r14.get()
            r20 = r7
            java.lang.String r20 = (java.lang.String) r20
            r7 = r20
            r20 = r14
            r74 = r24
            r24 = r8
            r8 = r74
            r75 = r18
            r18 = r13
            r13 = r2
            r3 = r75
            r2 = r38
            goto L_0x02ba
        L_0x027d:
            r7 = r20
            r20 = r14
            r74 = r24
            r24 = r8
            r8 = r74
            r75 = r18
            r18 = r13
            r13 = r2
            r3 = r75
            r2 = r38
            goto L_0x02ba
        L_0x0291:
            r7 = r20
            r20 = r14
            r74 = r24
            r24 = r8
            r8 = r74
            r75 = r18
            r18 = r13
            r13 = r2
            r3 = r75
            r2 = r38
            goto L_0x02ba
        L_0x02a5:
            r27 = r7
            r7 = r20
            r20 = r14
            r74 = r24
            r24 = r8
            r8 = r74
            r75 = r18
            r18 = r13
            r13 = r2
            r3 = r75
            r2 = r38
        L_0x02ba:
            long r71 = r13 & r41
            r29 = 0
            int r38 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r38 == 0) goto L_0x02df
            if (r25 == 0) goto L_0x02d6
            r38 = r9
            android.widget.TextView r9 = r1.musicTip
            android.content.res.Resources r9 = r9.getResources()
            r71 = r12
            r12 = 2131558825(0x7f0d01a9, float:1.8742977E38)
            java.lang.String r9 = r9.getString(r12)
            goto L_0x02dc
        L_0x02d6:
            r38 = r9
            r71 = r12
            r9 = r17
        L_0x02dc:
            r22 = r9
            goto L_0x02e5
        L_0x02df:
            r38 = r9
            r71 = r12
            r9 = r22
        L_0x02e5:
            r72 = 8704(0x2200, double:4.3003E-320)
            long r72 = r13 & r72
            r29 = 0
            int r12 = (r72 > r29 ? 1 : (r72 == r29 ? 0 : -1))
            if (r12 == 0) goto L_0x030d
            if (r5 == 0) goto L_0x02f4
            android.databinding.ObservableField<android.graphics.drawable.BitmapDrawable> r12 = r5.picZoom
            goto L_0x02f6
        L_0x02f4:
            r12 = r23
        L_0x02f6:
            r22 = r5
            r5 = 3
            r1.updateRegistration((int) r5, (android.databinding.Observable) r12)
            if (r12 == 0) goto L_0x0308
            java.lang.Object r5 = r12.get()
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            r23 = r12
            r12 = r5
            goto L_0x0311
        L_0x0308:
            r23 = r12
            r12 = r71
            goto L_0x0311
        L_0x030d:
            r22 = r5
            r12 = r71
        L_0x0311:
            r71 = 17408(0x4400, double:8.6007E-320)
            long r71 = r13 & r71
            r29 = 0
            int r5 = (r71 > r29 ? 1 : (r71 == r29 ? 0 : -1))
            if (r5 == 0) goto L_0x03c0
            if (r4 == 0) goto L_0x0320
            android.databinding.ObservableBoolean r5 = r4.itemIconClassical
            goto L_0x0322
        L_0x0320:
            r5 = r18
        L_0x0322:
            r71 = r4
            r4 = 1
            r1.updateRegistration((int) r4, (android.databinding.Observable) r5)
            if (r5 == 0) goto L_0x032e
            boolean r26 = r5.get()
        L_0x032e:
            long r72 = r13 & r69
            r29 = 0
            int r4 = (r72 > r29 ? 1 : (r72 == r29 ? 0 : -1))
            if (r4 == 0) goto L_0x0349
            if (r26 == 0) goto L_0x0341
            long r13 = r13 | r57
            long r13 = r13 | r55
            long r13 = r13 | r53
            long r13 = r13 | r51
            goto L_0x0349
        L_0x0341:
            long r13 = r13 | r65
            long r13 = r13 | r63
            long r13 = r13 | r61
            long r13 = r13 | r59
        L_0x0349:
            long r51 = r13 & r36
            r29 = 0
            int r4 = (r51 > r29 ? 1 : (r51 == r29 ? 0 : -1))
            if (r4 == 0) goto L_0x0358
            if (r26 == 0) goto L_0x0356
            long r13 = r13 | r47
            goto L_0x0358
        L_0x0356:
            long r13 = r13 | r49
        L_0x0358:
            long r47 = r13 & r34
            r29 = 0
            int r4 = (r47 > r29 ? 1 : (r47 == r29 ? 0 : -1))
            if (r4 == 0) goto L_0x0367
            if (r26 == 0) goto L_0x0365
            long r13 = r13 | r43
            goto L_0x0367
        L_0x0365:
            long r13 = r13 | r45
        L_0x0367:
            long r36 = r13 & r36
            r29 = 0
            int r4 = (r36 > r29 ? 1 : (r36 == r29 ? 0 : -1))
            if (r4 == 0) goto L_0x038d
            if (r26 == 0) goto L_0x037d
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.mboundView4
            android.content.Context r4 = r4.getContext()
            r18 = r5
            r5 = 2131232073(0x7f080549, float:1.8080245E38)
            goto L_0x0388
        L_0x037d:
            r18 = r5
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.mboundView4
            android.content.Context r4 = r4.getContext()
            r5 = 2131232074(0x7f08054a, float:1.8080247E38)
        L_0x0388:
            android.graphics.drawable.Drawable r4 = android.support.v7.content.res.AppCompatResources.getDrawable(r4, r5)
            goto L_0x0391
        L_0x038d:
            r18 = r5
            r4 = r24
        L_0x0391:
            long r34 = r13 & r34
            r29 = 0
            int r5 = (r34 > r29 ? 1 : (r34 == r29 ? 0 : -1))
            if (r5 == 0) goto L_0x03bd
            if (r26 == 0) goto L_0x03ab
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r5 = r1.mboundView3
            android.content.Context r5 = r5.getContext()
            r24 = r4
            r4 = 2131232073(0x7f080549, float:1.8080245E38)
            android.graphics.drawable.Drawable r4 = android.support.v7.content.res.AppCompatResources.getDrawable(r5, r4)
            goto L_0x03ba
        L_0x03ab:
            r24 = r4
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.mboundView3
            android.content.Context r4 = r4.getContext()
            r5 = 2131232074(0x7f08054a, float:1.8080247E38)
            android.graphics.drawable.Drawable r4 = android.support.v7.content.res.AppCompatResources.getDrawable(r4, r5)
        L_0x03ba:
            r16 = r4
            goto L_0x03c2
        L_0x03bd:
            r24 = r4
            goto L_0x03c2
        L_0x03c0:
            r71 = r4
        L_0x03c2:
            long r4 = r13 & r39
            r29 = 0
            int r4 = (r4 > r29 ? 1 : (r4 == r29 ? 0 : -1))
            if (r4 == 0) goto L_0x03d7
            if (r19 == 0) goto L_0x03cf
            r4 = r16
            goto L_0x03d0
        L_0x03cf:
            r4 = r12
        L_0x03d0:
            if (r19 == 0) goto L_0x03d5
            r5 = r24
            goto L_0x03d6
        L_0x03d5:
            r5 = r12
        L_0x03d6:
            goto L_0x03db
        L_0x03d7:
            r4 = r21
            r5 = r38
        L_0x03db:
            long r34 = r13 & r69
            r29 = 0
            int r21 = (r34 > r29 ? 1 : (r34 == r29 ? 0 : -1))
            if (r21 == 0) goto L_0x03fa
            r21 = r12
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.btItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r11)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.naviItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r3)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.setItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r8)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.weatherItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r2)
            goto L_0x03fc
        L_0x03fa:
            r21 = r12
        L_0x03fc:
            long r34 = r13 & r67
            r29 = 0
            int r12 = (r34 > r29 ? 1 : (r34 == r29 ? 0 : -1))
            if (r12 == 0) goto L_0x0409
            android.widget.TextView r12 = r1.btTip
            android.databinding.adapters.TextViewBindingAdapter.setText(r12, r7)
        L_0x0409:
            long r34 = r13 & r39
            int r12 = (r34 > r29 ? 1 : (r34 == r29 ? 0 : -1))
            if (r12 == 0) goto L_0x0419
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.mboundView3
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r4)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.mboundView4
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r12, r5)
        L_0x0419:
            long r31 = r13 & r32
            r29 = 0
            int r12 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r12 == 0) goto L_0x0430
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.mboundView3
            r12.setVisibility(r10)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r12 = r1.mboundView4
            r12.setVisibility(r6)
            android.widget.FrameLayout r12 = r1.musicItemview
            android.databinding.adapters.ViewBindingAdapter.setBackground(r12, r0)
        L_0x0430:
            long r31 = r13 & r41
            r29 = 0
            int r12 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r12 == 0) goto L_0x043d
            android.widget.TextView r12 = r1.musicTip
            android.databinding.adapters.TextViewBindingAdapter.setText(r12, r9)
        L_0x043d:
            return
        L_0x043e:
            r0 = move-exception
            monitor-exit(r76)     // Catch:{ all -> 0x043e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.Benz2021KswV2FragmentOneImpl.executeBindings():void");
    }
}

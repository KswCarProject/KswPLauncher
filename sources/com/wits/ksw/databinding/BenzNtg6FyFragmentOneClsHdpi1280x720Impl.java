package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class BenzNtg6FyFragmentOneClsHdpi1280x720Impl extends BenzNtg6FyFragmentOneCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navi_rl, 3);
        sparseIntArray.put(R.id.navi_itemview, 4);
        sparseIntArray.put(R.id.navi_tv, 5);
        sparseIntArray.put(R.id.navi_tip, 6);
        sparseIntArray.put(R.id.navi_iv1, 7);
        sparseIntArray.put(R.id.space1, 8);
        sparseIntArray.put(R.id.navi_iv2, 9);
        sparseIntArray.put(R.id.music_rl, 10);
        sparseIntArray.put(R.id.music_itemview, 11);
        sparseIntArray.put(R.id.music_tv, 12);
        sparseIntArray.put(R.id.music_iv2, 13);
        sparseIntArray.put(R.id.space2, 14);
        sparseIntArray.put(R.id.music_iv1, 15);
        sparseIntArray.put(R.id.bt_rl, 16);
        sparseIntArray.put(R.id.bt_itemview, 17);
        sparseIntArray.put(R.id.bt_tv, 18);
        sparseIntArray.put(R.id.bt_iv1, 19);
        sparseIntArray.put(R.id.space, 20);
        sparseIntArray.put(R.id.bt_iv2, 21);
        sparseIntArray.put(R.id.car_rl, 22);
        sparseIntArray.put(R.id.car_itemview, 23);
        sparseIntArray.put(R.id.car_tv, 24);
        sparseIntArray.put(R.id.car_tip, 25);
        sparseIntArray.put(R.id.car_iv1, 26);
        sparseIntArray.put(R.id.space4, 27);
        sparseIntArray.put(R.id.car_iv2, 28);
        sparseIntArray.put(R.id.set_rl, 29);
        sparseIntArray.put(R.id.set_itemview, 30);
        sparseIntArray.put(R.id.set_tv, 31);
        sparseIntArray.put(R.id.set_tip, 32);
        sparseIntArray.put(R.id.set_iv1, 33);
        sparseIntArray.put(R.id.space5, 34);
        sparseIntArray.put(R.id.set_iv2, 35);
    }

    public BenzNtg6FyFragmentOneClsHdpi1280x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private BenzNtg6FyFragmentOneClsHdpi1280x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[17], bindings[19], bindings[21], bindings[16], bindings[2], bindings[18], bindings[23], bindings[26], bindings[28], bindings[22], bindings[25], bindings[24], bindings[0], bindings[11], bindings[15], bindings[13], bindings[10], bindings[1], bindings[12], bindings[4], bindings[7], bindings[9], bindings[3], bindings[6], bindings[5], bindings[30], bindings[33], bindings[35], bindings[29], bindings[32], bindings[31], bindings[20], bindings[8], bindings[14], bindings[27], bindings[34]);
        this.mDirtyFlags = -1;
        this.btTip.setTag((Object) null);
        this.fragmentOneLl.setTag((Object) null);
        this.musicTip.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r21 = this;
            r1 = r21
            r2 = 0
            monitor-enter(r21)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x0096 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0096 }
            monitor-exit(r21)     // Catch:{ all -> 0x0096 }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            com.wits.ksw.launcher.model.BcVieModel r11 = r1.mViewModel
            r12 = 0
            r13 = 9
            long r15 = r2 & r13
            int r15 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            r4 = 1
            if (r15 == 0) goto L_0x0049
            com.wits.ksw.launcher.bean.MediaInfo r8 = com.wits.ksw.launcher.model.BcVieModel.mediaInfo
            if (r8 == 0) goto L_0x0025
            android.databinding.ObservableField<java.lang.String> r0 = r8.songInfo
        L_0x0025:
            r5 = 0
            r1.updateRegistration((int) r5, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0032
            java.lang.Object r15 = r0.get()
            r12 = r15
            java.lang.String r12 = (java.lang.String) r12
        L_0x0032:
            if (r12 != 0) goto L_0x0035
            r5 = r4
        L_0x0035:
            r10 = r5
            long r17 = r2 & r13
            r15 = 0
            int r5 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x0049
            if (r10 == 0) goto L_0x0045
            r17 = 32
            long r2 = r2 | r17
            goto L_0x0049
        L_0x0045:
            r17 = 16
            long r2 = r2 | r17
        L_0x0049:
            r17 = 14
            long r19 = r2 & r17
            r15 = 0
            int r5 = (r19 > r15 ? 1 : (r19 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x0063
            if (r11 == 0) goto L_0x0057
            android.databinding.ObservableField r6 = r11.btState
        L_0x0057:
            r1.updateRegistration((int) r4, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0063
            java.lang.Object r4 = r6.get()
            r7 = r4
            java.lang.String r7 = (java.lang.String) r7
        L_0x0063:
            long r4 = r2 & r13
            r15 = 0
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x007d
            if (r10 == 0) goto L_0x007b
            android.widget.TextView r4 = r1.musicTip
            android.content.res.Resources r4 = r4.getResources()
            r5 = 2131558752(0x7f0d0160, float:1.8742829E38)
            java.lang.String r4 = r4.getString(r5)
            goto L_0x007c
        L_0x007b:
            r4 = r12
        L_0x007c:
            r9 = r4
        L_0x007d:
            long r4 = r2 & r17
            r15 = 0
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x008a
            android.widget.TextView r4 = r1.btTip
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r7)
        L_0x008a:
            long r4 = r2 & r13
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0095
            android.widget.TextView r4 = r1.musicTip
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r9)
        L_0x0095:
            return
        L_0x0096:
            r0 = move-exception
            monitor-exit(r21)     // Catch:{ all -> 0x0096 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsHdpi1280x720Impl.executeBindings():void");
    }
}

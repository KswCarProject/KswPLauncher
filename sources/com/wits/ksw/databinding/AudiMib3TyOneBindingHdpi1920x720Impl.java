package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class AudiMib3TyOneBindingHdpi1920x720Impl extends AudiMib3TyOneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.video_itemview, 3);
        sparseIntArray.put(R.id.video_content, 4);
        sparseIntArray.put(R.id.video_content_ll, 5);
        sparseIntArray.put(R.id.video_iv, 6);
        sparseIntArray.put(R.id.video_tv, 7);
        sparseIntArray.put(R.id.music_itemview, 8);
        sparseIntArray.put(R.id.music_content_ll, 9);
        sparseIntArray.put(R.id.music_iv, 10);
        sparseIntArray.put(R.id.music_tv, 11);
        sparseIntArray.put(R.id.navi_itemview, 12);
        sparseIntArray.put(R.id.navi_content, 13);
        sparseIntArray.put(R.id.navi_content_ll, 14);
        sparseIntArray.put(R.id.navi_iv, 15);
        sparseIntArray.put(R.id.navi_tv, 16);
        sparseIntArray.put(R.id.bt_itemview, 17);
        sparseIntArray.put(R.id.bt_content_ll, 18);
        sparseIntArray.put(R.id.bt_iv, 19);
        sparseIntArray.put(R.id.bt_tv, 20);
        sparseIntArray.put(R.id.phonelink_itemview, 21);
        sparseIntArray.put(R.id.phonelink_content, 22);
        sparseIntArray.put(R.id.phonelink_content_ll, 23);
        sparseIntArray.put(R.id.phonelink_iv, 24);
        sparseIntArray.put(R.id.phonelink_tv, 25);
    }

    public AudiMib3TyOneBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }

    private AudiMib3TyOneBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[2], bindings[18], bindings[17], bindings[19], bindings[20], bindings[0], bindings[1], bindings[9], bindings[8], bindings[10], bindings[11], bindings[13], bindings[14], bindings[12], bindings[15], bindings[16], bindings[22], bindings[23], bindings[21], bindings[24], bindings[25], bindings[4], bindings[5], bindings[3], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.btContent.setTag((Object) null);
        this.fragmentOneLl.setTag((Object) null);
        this.musicContent.setTag((Object) null);
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
        if (25 != variableId) {
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
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r17 = this;
            r1 = r17
            r2 = 0
            monitor-enter(r17)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x005d }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x005d }
            monitor-exit(r17)     // Catch:{ all -> 0x005d }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.wits.ksw.launcher.model.BcVieModel r9 = r1.mViewModel
            r10 = 0
            r11 = 9
            long r13 = r2 & r11
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x002f
            com.wits.ksw.launcher.bean.MediaInfo r0 = com.wits.ksw.launcher.model.BcVieModel.mediaInfo
            if (r0 == 0) goto L_0x0022
            android.databinding.ObservableField<java.lang.String> r6 = r0.musicName
        L_0x0022:
            r13 = 0
            r1.updateRegistration((int) r13, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x002f
            java.lang.Object r13 = r6.get()
            r7 = r13
            java.lang.String r7 = (java.lang.String) r7
        L_0x002f:
            r13 = 14
            long r15 = r2 & r13
            int r15 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0048
            if (r9 == 0) goto L_0x003b
            android.databinding.ObservableField r8 = r9.btState
        L_0x003b:
            r15 = 1
            r1.updateRegistration((int) r15, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x0048
            java.lang.Object r15 = r8.get()
            r10 = r15
            java.lang.String r10 = (java.lang.String) r10
        L_0x0048:
            long r13 = r13 & r2
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0052
            android.widget.TextView r13 = r1.btContent
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r10)
        L_0x0052:
            long r11 = r11 & r2
            int r4 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x005c
            android.widget.TextView r4 = r1.musicContent
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r7)
        L_0x005c:
            return
        L_0x005d:
            r0 = move-exception
            monitor-exit(r17)     // Catch:{ all -> 0x005d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AudiMib3TyOneBindingHdpi1920x720Impl.executeBindings():void");
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class Benz2021FragmentOne1920x720Impl extends Benz2021FragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bt_rl, 5);
        sparseIntArray.put(R.id.bt_tv, 6);
        sparseIntArray.put(R.id.iv_bt1, 7);
        sparseIntArray.put(R.id.space1, 8);
        sparseIntArray.put(R.id.iv_bt2, 9);
        sparseIntArray.put(R.id.navi_rl, 10);
        sparseIntArray.put(R.id.navi_tv, 11);
        sparseIntArray.put(R.id.navi_tip, 12);
        sparseIntArray.put(R.id.iv_navi1, 13);
        sparseIntArray.put(R.id.space2, 14);
        sparseIntArray.put(R.id.iv_navi2, 15);
        sparseIntArray.put(R.id.car_rl, 16);
        sparseIntArray.put(R.id.car_tv, 17);
        sparseIntArray.put(R.id.car_tip, 18);
        sparseIntArray.put(R.id.iv_car1, 19);
        sparseIntArray.put(R.id.space, 20);
        sparseIntArray.put(R.id.iv_car2, 21);
    }

    public Benz2021FragmentOne1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentOne1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[5], bindings[2], bindings[6], bindings[4], bindings[16], bindings[18], bindings[17], bindings[0], bindings[7], bindings[9], bindings[19], bindings[21], bindings[13], bindings[15], bindings[3], bindings[10], bindings[12], bindings[11], bindings[20], bindings[8], bindings[14]);
        this.mDirtyFlags = -1;
        this.btItemview.setTag((Object) null);
        this.btTip.setTag((Object) null);
        this.carItemview.setTag((Object) null);
        this.fragmentOneLl.setTag((Object) null);
        this.naviItemview.setTag((Object) null);
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
                return onChangeViewModelItemIconClass((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelItemIconClass(ObservableBoolean ViewModelItemIconClass, int fieldId) {
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            r2 = 0
            monitor-enter(r20)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x00db }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00db }
            monitor-exit(r20)     // Catch:{ all -> 0x00db }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            com.wits.ksw.launcher.model.BcVieModel r11 = r1.mViewModel
            r12 = 0
            r13 = 15
            long r13 = r13 & r2
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            r14 = 14
            r16 = 13
            if (r13 == 0) goto L_0x00b6
            long r18 = r2 & r16
            int r13 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x009c
            if (r11 == 0) goto L_0x002b
            android.databinding.ObservableBoolean r0 = r11.itemIconClass
        L_0x002b:
            r13 = 0
            r1.updateRegistration((int) r13, (android.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0035
            boolean r6 = r0.get()
        L_0x0035:
            long r18 = r2 & r16
            int r13 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0056
            if (r6 == 0) goto L_0x004a
            r18 = 32
            long r2 = r2 | r18
            r18 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r18
            r18 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r18
            goto L_0x0056
        L_0x004a:
            r18 = 16
            long r2 = r2 | r18
            r18 = 64
            long r2 = r2 | r18
            r18 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r18
        L_0x0056:
            if (r6 == 0) goto L_0x0066
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r13 = r1.naviItemview
            android.content.Context r13 = r13.getContext()
            r4 = 2131231677(0x7f0803bd, float:1.8079442E38)
            android.graphics.drawable.Drawable r4 = android.support.v7.content.res.AppCompatResources.getDrawable(r13, r4)
            goto L_0x0073
        L_0x0066:
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.naviItemview
            android.content.Context r4 = r4.getContext()
            r5 = 2131231678(0x7f0803be, float:1.8079444E38)
            android.graphics.drawable.Drawable r4 = android.support.v7.content.res.AppCompatResources.getDrawable(r4, r5)
        L_0x0073:
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r5 = r1.btItemview
            android.content.Context r5 = r5.getContext()
            if (r6 == 0) goto L_0x007f
            r8 = 2131231667(0x7f0803b3, float:1.8079421E38)
            goto L_0x0082
        L_0x007f:
            r8 = 2131231668(0x7f0803b4, float:1.8079424E38)
        L_0x0082:
            android.graphics.drawable.Drawable r5 = android.support.v7.content.res.AppCompatResources.getDrawable(r5, r8)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r8 = r1.carItemview
            android.content.Context r8 = r8.getContext()
            if (r6 == 0) goto L_0x0092
            r9 = 2131231669(0x7f0803b5, float:1.8079426E38)
            goto L_0x0095
        L_0x0092:
            r9 = 2131231670(0x7f0803b6, float:1.8079428E38)
        L_0x0095:
            android.graphics.drawable.Drawable r8 = android.support.v7.content.res.AppCompatResources.getDrawable(r8, r9)
            r9 = r5
            r10 = r8
            r8 = r4
        L_0x009c:
            long r4 = r2 & r14
            r18 = 0
            int r4 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r4 == 0) goto L_0x00b6
            if (r11 == 0) goto L_0x00a9
            android.databinding.ObservableField r4 = r11.btState
            r7 = r4
        L_0x00a9:
            r4 = 1
            r1.updateRegistration((int) r4, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x00b6
            java.lang.Object r4 = r7.get()
            r12 = r4
            java.lang.String r12 = (java.lang.String) r12
        L_0x00b6:
            long r4 = r2 & r16
            r16 = 0
            int r4 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x00cd
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.btItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r9)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.carItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r10)
            com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView r4 = r1.naviItemview
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r8)
        L_0x00cd:
            long r4 = r2 & r14
            r13 = 0
            int r4 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x00da
            android.widget.TextView r4 = r1.btTip
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r12)
        L_0x00da:
            return
        L_0x00db:
            r0 = move-exception
            monitor-exit(r20)     // Catch:{ all -> 0x00db }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.Benz2021FragmentOne1920x720Impl.executeBindings():void");
    }
}

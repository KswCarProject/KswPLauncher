package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubCarViewBindingImpl extends AlsId7SubCarViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback193;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView2, 4);
        sparseIntArray.put(R.id.textView3, 5);
    }

    public AlsId7SubCarViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private AlsId7SubCarViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[0], bindings[1], bindings[3], bindings[2], bindings[4], bindings[5]);
        this.mDirtyFlags = -1;
        this.carConstraintLayout.setTag((Object) null);
        this.carImageView.setTag((Object) null);
        this.dayTextView.setTag((Object) null);
        this.monthTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback193 = new OnClickListener(this, 1);
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
        if (8 != variableId) {
            return false;
        }
        setNaviCarViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel) {
        this.mNaviCarViewModel = NaviCarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviCarViewModelDay((ObservableField) object, fieldId);
            case 1:
                return onChangeNaviCarViewModelMonth((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviCarViewModelDay(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeNaviCarViewModelMonth(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            r2 = 0
            monitor-enter(r20)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x008b }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x008b }
            monitor-exit(r20)     // Catch:{ all -> 0x008b }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r10 = r1.mNaviCarViewModel
            r11 = 15
            long r11 = r11 & r2
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            r12 = 14
            r14 = 13
            r16 = 12
            if (r11 == 0) goto L_0x0059
            long r18 = r2 & r16
            int r11 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x002b
            if (r10 == 0) goto L_0x002b
            android.view.View$OnFocusChangeListener r0 = r10.carinfoViewFocusChangeListener
        L_0x002b:
            long r18 = r2 & r14
            int r11 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0042
            if (r10 == 0) goto L_0x0035
            android.databinding.ObservableField r6 = r10.day
        L_0x0035:
            r11 = 0
            r1.updateRegistration((int) r11, (android.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0042
            java.lang.Object r11 = r6.get()
            r7 = r11
            java.lang.String r7 = (java.lang.String) r7
        L_0x0042:
            long r18 = r2 & r12
            int r11 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0059
            if (r10 == 0) goto L_0x004c
            android.databinding.ObservableField r8 = r10.month
        L_0x004c:
            r11 = 1
            r1.updateRegistration((int) r11, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x0059
            java.lang.Object r11 = r8.get()
            r9 = r11
            java.lang.String r9 = (java.lang.String) r9
        L_0x0059:
            r18 = 8
            long r18 = r2 & r18
            int r11 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0068
            com.wits.ksw.launcher.view.CustomBmwImageView r11 = r1.carImageView
            android.view.View$OnClickListener r12 = r1.mCallback193
            r11.setOnClickListener(r12)
        L_0x0068:
            long r11 = r2 & r16
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0073
            com.wits.ksw.launcher.view.CustomBmwImageView r11 = r1.carImageView
            r11.setOnFocusChangeListener(r0)
        L_0x0073:
            long r11 = r2 & r14
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x007e
            android.widget.TextView r11 = r1.dayTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r11, r7)
        L_0x007e:
            r11 = 14
            long r11 = r11 & r2
            int r4 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x008a
            android.widget.TextView r4 = r1.monthTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r4, r9)
        L_0x008a:
            return
        L_0x008b:
            r0 = move-exception
            monitor-exit(r20)     // Catch:{ all -> 0x008b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AlsId7SubCarViewBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel naviCarViewModel = this.mNaviCarViewModel;
        if (naviCarViewModel != null) {
            naviCarViewModel.openCar(callbackArg_0);
        }
    }
}

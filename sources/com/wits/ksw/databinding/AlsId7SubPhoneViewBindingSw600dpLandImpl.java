package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubPhoneViewBindingSw600dpLandImpl extends AlsId7SubPhoneViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback319;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView2, 5);
    }

    public AlsId7SubPhoneViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private AlsId7SubPhoneViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[4], bindings[3], bindings[2], bindings[0], bindings[1], bindings[5]);
        this.mDirtyFlags = -1;
        this.dayTextView.setTag((Object) null);
        this.monthTextView.setTag((Object) null);
        this.phoneConnectionTextView.setTag((Object) null);
        this.phoneConstraintLayout.setTag((Object) null);
        this.phoneImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback319 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (12 != variableId) {
            return false;
        }
        setMusicPhoneViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMusicPhoneViewModelMonth((ObservableField) object, fieldId);
            case 1:
                return onChangeMusicPhoneViewModelBtState((ObservableField) object, fieldId);
            case 2:
                return onChangeMusicPhoneViewModelDay((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicPhoneViewModelMonth(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelDay(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r24 = this;
            r1 = r24
            r2 = 0
            monitor-enter(r24)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x00af }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00af }
            monitor-exit(r24)     // Catch:{ all -> 0x00af }
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r9 = r1.mMusicPhoneViewModel
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 31
            long r13 = r13 & r2
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            r14 = 26
            r16 = 25
            r18 = 24
            r20 = 28
            if (r13 == 0) goto L_0x0074
            long r22 = r2 & r18
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x002f
            if (r9 == 0) goto L_0x002f
            android.view.View$OnFocusChangeListener r6 = r9.btPhoneViewFocusChangeListener
        L_0x002f:
            long r22 = r2 & r16
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0046
            if (r9 == 0) goto L_0x0039
            android.databinding.ObservableField r7 = r9.month
        L_0x0039:
            r13 = 0
            r1.updateRegistration((int) r13, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x0046
            java.lang.Object r13 = r7.get()
            r12 = r13
            java.lang.String r12 = (java.lang.String) r12
        L_0x0046:
            long r22 = r2 & r14
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x005d
            if (r9 == 0) goto L_0x0050
            android.databinding.ObservableField r8 = r9.btState
        L_0x0050:
            r13 = 1
            r1.updateRegistration((int) r13, (android.databinding.Observable) r8)
            if (r8 == 0) goto L_0x005d
            java.lang.Object r13 = r8.get()
            r11 = r13
            java.lang.String r11 = (java.lang.String) r11
        L_0x005d:
            long r22 = r2 & r20
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0074
            if (r9 == 0) goto L_0x0067
            android.databinding.ObservableField r10 = r9.day
        L_0x0067:
            r13 = 2
            r1.updateRegistration((int) r13, (android.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0074
            java.lang.Object r13 = r10.get()
            r0 = r13
            java.lang.String r0 = (java.lang.String) r0
        L_0x0074:
            long r20 = r2 & r20
            int r13 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x007f
            android.widget.TextView r13 = r1.dayTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r0)
        L_0x007f:
            long r16 = r2 & r16
            int r13 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x008a
            android.widget.TextView r13 = r1.monthTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r12)
        L_0x008a:
            long r13 = r2 & r14
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0095
            android.widget.TextView r13 = r1.phoneConnectionTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r11)
        L_0x0095:
            r13 = 16
            long r13 = r13 & r2
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00a3
            com.wits.ksw.launcher.view.CustomBmwImageView r13 = r1.phoneImageView
            android.view.View$OnClickListener r14 = r1.mCallback319
            r13.setOnClickListener(r14)
        L_0x00a3:
            long r13 = r2 & r18
            int r4 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x00ae
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.phoneImageView
            r4.setOnFocusChangeListener(r6)
        L_0x00ae:
            return
        L_0x00af:
            r0 = move-exception
            monitor-exit(r24)     // Catch:{ all -> 0x00af }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.AlsId7SubPhoneViewBindingSw600dpLandImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        if (musicPhoneViewModel != null) {
            musicPhoneViewModel.openBtApp(callbackArg_0);
        }
    }
}

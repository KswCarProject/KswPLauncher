package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubPhoneViewBindingSw600dpLandImpl extends AlsId7SubPhoneViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback120;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.textView2, 5);
    }

    public AlsId7SubPhoneViewBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
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
        this.mCallback120 = new OnClickListener(this, 1);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (24 != variableId) {
            return false;
        }
        setMusicPhoneViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setMusicPhoneViewModel(@Nullable AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(24);
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r24 = this;
            r1 = r24
            r2 = 0
            monitor-enter(r24)
            long r4 = r1.mDirtyFlags     // Catch:{ all -> 0x00b2 }
            r2 = r4
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00b2 }
            monitor-exit(r24)     // Catch:{ all -> 0x00b2 }
            r0 = 0
            com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel r6 = r1.mMusicPhoneViewModel
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 31
            long r13 = r13 & r2
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            r14 = 24
            r16 = 26
            r18 = 28
            r20 = 25
            if (r13 == 0) goto L_0x0074
            long r22 = r2 & r20
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x003c
            if (r6 == 0) goto L_0x002f
            android.databinding.ObservableField r7 = r6.month
        L_0x002f:
            r13 = 0
            r1.updateRegistration((int) r13, (android.databinding.Observable) r7)
            if (r7 == 0) goto L_0x003c
            java.lang.Object r13 = r7.get()
            r10 = r13
            java.lang.String r10 = (java.lang.String) r10
        L_0x003c:
            long r22 = r2 & r16
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0053
            if (r6 == 0) goto L_0x0046
            android.databinding.ObservableField r9 = r6.btState
        L_0x0046:
            r13 = 1
            r1.updateRegistration((int) r13, (android.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0053
            java.lang.Object r13 = r9.get()
            r8 = r13
            java.lang.String r8 = (java.lang.String) r8
        L_0x0053:
            long r22 = r2 & r18
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x006a
            if (r6 == 0) goto L_0x005d
            android.databinding.ObservableField r11 = r6.day
        L_0x005d:
            r13 = 2
            r1.updateRegistration((int) r13, (android.databinding.Observable) r11)
            if (r11 == 0) goto L_0x006a
            java.lang.Object r13 = r11.get()
            r0 = r13
            java.lang.String r0 = (java.lang.String) r0
        L_0x006a:
            long r22 = r2 & r14
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0074
            if (r6 == 0) goto L_0x0074
            android.view.View$OnFocusChangeListener r12 = r6.btPhoneViewFocusChangeListener
        L_0x0074:
            long r18 = r2 & r18
            int r13 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x007f
            android.widget.TextView r13 = r1.dayTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r0)
        L_0x007f:
            long r18 = r2 & r20
            int r13 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x008a
            android.widget.TextView r13 = r1.monthTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r10)
        L_0x008a:
            long r16 = r2 & r16
            int r13 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0095
            android.widget.TextView r13 = r1.phoneConnectionTextView
            android.databinding.adapters.TextViewBindingAdapter.setText(r13, r8)
        L_0x0095:
            r16 = 16
            long r16 = r2 & r16
            int r13 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00a4
            com.wits.ksw.launcher.view.CustomBmwImageView r13 = r1.phoneImageView
            android.view.View$OnClickListener r4 = r1.mCallback120
            r13.setOnClickListener(r4)
        L_0x00a4:
            long r4 = r2 & r14
            r13 = 0
            int r4 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x00b1
            com.wits.ksw.launcher.view.CustomBmwImageView r4 = r1.phoneImageView
            r4.setOnFocusChangeListener(r12)
        L_0x00b1:
            return
        L_0x00b2:
            r0 = move-exception
            monitor-exit(r24)     // Catch:{ all -> 0x00b2 }
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

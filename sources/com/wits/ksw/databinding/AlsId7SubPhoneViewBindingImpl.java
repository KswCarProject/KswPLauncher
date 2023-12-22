package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class AlsId7SubPhoneViewBindingImpl extends AlsId7SubPhoneViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback314;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.textView2, 5);
    }

    public AlsId7SubPhoneViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private AlsId7SubPhoneViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (TextView) bindings[4], (TextView) bindings[3], (TextView) bindings[2], (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.dayTextView.setTag(null);
        this.monthTextView.setTag(null);
        this.phoneConnectionTextView.setTag(null);
        this.phoneConstraintLayout.setTag(null);
        this.phoneImageView.setTag(null);
        setRootTag(root);
        this.mCallback314 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (12 == variableId) {
            setMusicPhoneViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubPhoneViewBinding
    public void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeMusicPhoneViewModelMonth(ObservableField<String> MusicPhoneViewModelMonth, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelBtState(ObservableField<String> MusicPhoneViewModelBtState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelDay(ObservableField<String> MusicPhoneViewModelDay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String musicPhoneViewModelDayGet = null;
        View.OnFocusChangeListener musicPhoneViewModelBtPhoneViewFocusChangeListener = null;
        ObservableField<String> musicPhoneViewModelMonth = null;
        ObservableField<String> musicPhoneViewModelBtState = null;
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        ObservableField<String> musicPhoneViewModelDay = null;
        String musicPhoneViewModelBtStateGet = null;
        String musicPhoneViewModelMonthGet = null;
        if ((31 & dirtyFlags) != 0) {
            if ((dirtyFlags & 24) != 0 && musicPhoneViewModel != null) {
                musicPhoneViewModelBtPhoneViewFocusChangeListener = musicPhoneViewModel.btPhoneViewFocusChangeListener;
            }
            if ((dirtyFlags & 25) != 0) {
                if (musicPhoneViewModel != null) {
                    musicPhoneViewModelMonth = musicPhoneViewModel.month;
                }
                updateRegistration(0, musicPhoneViewModelMonth);
                if (musicPhoneViewModelMonth != null) {
                    musicPhoneViewModelMonthGet = musicPhoneViewModelMonth.get();
                }
            }
            if ((dirtyFlags & 26) != 0) {
                if (musicPhoneViewModel != null) {
                    musicPhoneViewModelBtState = musicPhoneViewModel.btState;
                }
                updateRegistration(1, musicPhoneViewModelBtState);
                if (musicPhoneViewModelBtState != null) {
                    musicPhoneViewModelBtStateGet = musicPhoneViewModelBtState.get();
                }
            }
            if ((dirtyFlags & 28) != 0) {
                if (musicPhoneViewModel != null) {
                    musicPhoneViewModelDay = musicPhoneViewModel.day;
                }
                updateRegistration(2, musicPhoneViewModelDay);
                if (musicPhoneViewModelDay != null) {
                    musicPhoneViewModelDayGet = musicPhoneViewModelDay.get();
                }
            }
        }
        if ((dirtyFlags & 28) != 0) {
            TextViewBindingAdapter.setText(this.dayTextView, musicPhoneViewModelDayGet);
        }
        if ((dirtyFlags & 25) != 0) {
            TextViewBindingAdapter.setText(this.monthTextView, musicPhoneViewModelMonthGet);
        }
        if ((dirtyFlags & 26) != 0) {
            TextViewBindingAdapter.setText(this.phoneConnectionTextView, musicPhoneViewModelBtStateGet);
        }
        if ((16 & dirtyFlags) != 0) {
            this.phoneImageView.setOnClickListener(this.mCallback314);
        }
        if ((dirtyFlags & 24) != 0) {
            this.phoneImageView.setOnFocusChangeListener(musicPhoneViewModelBtPhoneViewFocusChangeListener);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        boolean musicPhoneViewModelJavaLangObjectNull = musicPhoneViewModel != null;
        if (musicPhoneViewModelJavaLangObjectNull) {
            musicPhoneViewModel.openBtApp(callbackArg_0);
        }
    }
}

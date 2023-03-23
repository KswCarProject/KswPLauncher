package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7V2SubPhoneViewBindingImpl extends Id7V2SubPhoneViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback343;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView2, 5);
    }

    public Id7V2SubPhoneViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private Id7V2SubPhoneViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[4], bindings[3], bindings[2], bindings[0], bindings[1], bindings[5]);
        this.mDirtyFlags = -1;
        this.dayTextView.setTag((Object) null);
        this.monthTextView.setTag((Object) null);
        this.phoneConnectionTextView.setTag((Object) null);
        this.phoneConstraintLayout.setTag((Object) null);
        this.phoneImageView1.setTag((Object) null);
        setRootTag(root);
        this.mCallback343 = new OnClickListener(this, 1);
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
        if (14 != variableId) {
            return false;
        }
        setNaviViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setNaviViewModel(LauncherViewModel NaviViewModel) {
        this.mNaviViewModel = NaviViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviViewModelBtState((ObservableField) object, fieldId);
            case 1:
                return onChangeNaviViewModelMonth((ObservableField) object, fieldId);
            case 2:
                return onChangeNaviViewModelDay((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeNaviViewModelMonth(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeNaviViewModelDay(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<String> naviViewModelBtState = null;
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        String naviViewModelDayGet = null;
        String naviViewModelMonthGet = null;
        View.OnFocusChangeListener naviViewModelPhoneViewFocusChangeListenerV2 = null;
        ObservableField<String> naviViewModelMonth = null;
        ObservableField<String> naviViewModelDay = null;
        String naviViewModelBtStateGet = null;
        if ((31 & dirtyFlags) != 0) {
            if ((dirtyFlags & 25) != 0) {
                if (naviViewModel != null) {
                    naviViewModelBtState = naviViewModel.btState;
                }
                updateRegistration(0, (Observable) naviViewModelBtState);
                if (naviViewModelBtState != null) {
                    naviViewModelBtStateGet = naviViewModelBtState.get();
                }
            }
            if (!((dirtyFlags & 24) == 0 || naviViewModel == null)) {
                naviViewModelPhoneViewFocusChangeListenerV2 = naviViewModel.phoneViewFocusChangeListenerV2;
            }
            if ((dirtyFlags & 26) != 0) {
                if (naviViewModel != null) {
                    naviViewModelMonth = naviViewModel.month;
                }
                updateRegistration(1, (Observable) naviViewModelMonth);
                if (naviViewModelMonth != null) {
                    naviViewModelMonthGet = naviViewModelMonth.get();
                }
            }
            if ((dirtyFlags & 28) != 0) {
                if (naviViewModel != null) {
                    naviViewModelDay = naviViewModel.day;
                }
                updateRegistration(2, (Observable) naviViewModelDay);
                if (naviViewModelDay != null) {
                    naviViewModelDayGet = naviViewModelDay.get();
                }
            }
        }
        if ((dirtyFlags & 28) != 0) {
            TextViewBindingAdapter.setText(this.dayTextView, naviViewModelDayGet);
        }
        if ((dirtyFlags & 26) != 0) {
            TextViewBindingAdapter.setText(this.monthTextView, naviViewModelMonthGet);
        }
        if ((dirtyFlags & 25) != 0) {
            TextViewBindingAdapter.setText(this.phoneConnectionTextView, naviViewModelBtStateGet);
        }
        if ((dirtyFlags & 16) != 0) {
            this.phoneImageView1.setOnClickListener(this.mCallback343);
        }
        if ((dirtyFlags & 24) != 0) {
            this.phoneImageView1.setOnFocusChangeListener(naviViewModelPhoneViewFocusChangeListenerV2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel naviViewModel = this.mNaviViewModel;
        if (naviViewModel != null) {
            naviViewModel.openBtApp(callbackArg_0);
        }
    }
}

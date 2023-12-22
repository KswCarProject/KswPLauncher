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
public class AlsId7SubCarViewBindingSw600dpLandImpl extends AlsId7SubCarViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback126;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.textView2, 4);
        sparseIntArray.put(C0899R.C0901id.textView3, 5);
    }

    public AlsId7SubCarViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private AlsId7SubCarViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1], (TextView) bindings[3], (TextView) bindings[2], (TextView) bindings[4], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.carConstraintLayout.setTag(null);
        this.carImageView.setTag(null);
        this.dayTextView.setTag(null);
        this.monthTextView.setTag(null);
        setRootTag(root);
        this.mCallback126 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (13 == variableId) {
            setNaviCarViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubCarViewBinding
    public void setNaviCarViewModel(AlsID7ViewModel NaviCarViewModel) {
        this.mNaviCarViewModel = NaviCarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNaviCarViewModelDay((ObservableField) object, fieldId);
            case 1:
                return onChangeNaviCarViewModelMonth((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNaviCarViewModelDay(ObservableField<String> NaviCarViewModelDay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeNaviCarViewModelMonth(ObservableField<String> NaviCarViewModelMonth, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        View.OnFocusChangeListener naviCarViewModelCarinfoViewFocusChangeListener = null;
        ObservableField<String> naviCarViewModelDay = null;
        String naviCarViewModelDayGet = null;
        ObservableField<String> naviCarViewModelMonth = null;
        String naviCarViewModelMonthGet = null;
        AlsID7ViewModel naviCarViewModel = this.mNaviCarViewModel;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 12) != 0 && naviCarViewModel != null) {
                naviCarViewModelCarinfoViewFocusChangeListener = naviCarViewModel.carinfoViewFocusChangeListener;
            }
            if ((dirtyFlags & 13) != 0) {
                if (naviCarViewModel != null) {
                    naviCarViewModelDay = naviCarViewModel.day;
                }
                updateRegistration(0, naviCarViewModelDay);
                if (naviCarViewModelDay != null) {
                    naviCarViewModelDayGet = naviCarViewModelDay.get();
                }
            }
            if ((dirtyFlags & 14) != 0) {
                if (naviCarViewModel != null) {
                    naviCarViewModelMonth = naviCarViewModel.month;
                }
                updateRegistration(1, naviCarViewModelMonth);
                if (naviCarViewModelMonth != null) {
                    naviCarViewModelMonthGet = naviCarViewModelMonth.get();
                }
            }
        }
        if ((dirtyFlags & 8) != 0) {
            this.carImageView.setOnClickListener(this.mCallback126);
        }
        if ((dirtyFlags & 12) != 0) {
            this.carImageView.setOnFocusChangeListener(naviCarViewModelCarinfoViewFocusChangeListener);
        }
        if ((dirtyFlags & 13) != 0) {
            TextViewBindingAdapter.setText(this.dayTextView, naviCarViewModelDayGet);
        }
        if ((14 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.monthTextView, naviCarViewModelMonthGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel naviCarViewModel = this.mNaviCarViewModel;
        boolean naviCarViewModelJavaLangObjectNull = naviCarViewModel != null;
        if (naviCarViewModelJavaLangObjectNull) {
            naviCarViewModel.openCar(callbackArg_0);
        }
    }
}

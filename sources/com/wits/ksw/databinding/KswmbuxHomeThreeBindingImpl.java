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
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgHomeImageView;

/* loaded from: classes7.dex */
public class KswmbuxHomeThreeBindingImpl extends KswmbuxHomeThreeBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback231;
    private final View.OnClickListener mCallback232;
    private final View.OnClickListener mCallback233;
    private long mDirtyFlags;
    private final TextView mboundView2;

    public KswmbuxHomeThreeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private KswmbuxHomeThreeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ConstraintLayout) bindings[0], (UgHomeImageView) bindings[3], (UgHomeImageView) bindings[1], (UgHomeImageView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.carConstraintLayout.setTag(null);
        this.kswmbuxHomeCarVaiw.setTag(null);
        this.kswmbuxHomeDashboradVaiw.setTag(null);
        this.kswmbuxHomeEasyVaiw.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        setRootTag(root);
        this.mCallback232 = new OnClickListener(this, 2);
        this.mCallback233 = new OnClickListener(this, 3);
        this.mCallback231 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (25 == variableId) {
            setViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.KswmbuxHomeThreeBinding
    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> ViewModelCarInfoOilValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
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
        ObservableField<String> viewModelCarInfoOilValue = null;
        String mboundView2AndroidStringOilSizeViewModelCarInfoOilValue = null;
        LauncherViewModel launcherViewModel = this.mViewModel;
        String viewModelCarInfoOilValueGet = null;
        if ((dirtyFlags & 5) != 0) {
            com.wits.ksw.launcher.bean.CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if (viewModelCarInfo != null) {
                viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
            }
            updateRegistration(0, viewModelCarInfoOilValue);
            if (viewModelCarInfoOilValue != null) {
                String viewModelCarInfoOilValueGet2 = viewModelCarInfoOilValue.get();
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValueGet2;
            }
            mboundView2AndroidStringOilSizeViewModelCarInfoOilValue = this.mboundView2.getResources().getString(C0899R.string.oil_size, viewModelCarInfoOilValueGet);
        }
        if ((4 & dirtyFlags) != 0) {
            this.kswmbuxHomeCarVaiw.setOnClickListener(this.mCallback232);
            this.kswmbuxHomeDashboradVaiw.setOnClickListener(this.mCallback231);
            this.kswmbuxHomeEasyVaiw.setOnClickListener(this.mCallback233);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, mboundView2AndroidStringOilSizeViewModelCarInfoOilValue);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openCar(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

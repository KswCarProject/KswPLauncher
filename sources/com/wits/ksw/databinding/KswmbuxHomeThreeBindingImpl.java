package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class KswmbuxHomeThreeBindingImpl extends KswmbuxHomeThreeBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback148;
    private final View.OnClickListener mCallback149;
    private final View.OnClickListener mCallback150;
    private long mDirtyFlags;
    private final TextView mboundView2;

    public KswmbuxHomeThreeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private KswmbuxHomeThreeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[0], bindings[3], bindings[1], bindings[4]);
        this.mDirtyFlags = -1;
        this.carConstraintLayout.setTag((Object) null);
        this.kswmbuxHomeCarVaiw.setTag((Object) null);
        this.kswmbuxHomeDashboradVaiw.setTag((Object) null);
        this.kswmbuxHomeEasyVaiw.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        setRootTag(root);
        this.mCallback148 = new OnClickListener(this, 1);
        this.mCallback149 = new OnClickListener(this, 2);
        this.mCallback150 = new OnClickListener(this, 3);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelCarInfoOilValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
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
        ObservableField<String> viewModelCarInfoOilValue = null;
        String mboundView2AndroidStringOilSizeViewModelCarInfoOilValue = null;
        LauncherViewModel launcherViewModel = this.mViewModel;
        String viewModelCarInfoOilValueGet = null;
        if ((dirtyFlags & 5) != 0) {
            CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if (viewModelCarInfo != null) {
                viewModelCarInfoOilValue = viewModelCarInfo.oilValue;
            }
            updateRegistration(0, (Observable) viewModelCarInfoOilValue);
            if (viewModelCarInfoOilValue != null) {
                viewModelCarInfoOilValueGet = viewModelCarInfoOilValue.get();
            }
            mboundView2AndroidStringOilSizeViewModelCarInfoOilValue = this.mboundView2.getResources().getString(R.string.oil_size, new Object[]{viewModelCarInfoOilValueGet});
        }
        if ((4 & dirtyFlags) != 0) {
            this.kswmbuxHomeCarVaiw.setOnClickListener(this.mCallback149);
            this.kswmbuxHomeDashboradVaiw.setOnClickListener(this.mCallback148);
            this.kswmbuxHomeEasyVaiw.setOnClickListener(this.mCallback150);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, mboundView2AndroidStringOilSizeViewModelCarInfoOilValue);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openCar(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
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

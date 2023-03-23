package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class KswId7MainPage2FragmentImpl extends KswId7MainPage2Fragment implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback283;
    private final View.OnClickListener mCallback284;
    private final View.OnClickListener mCallback285;
    private final View.OnClickListener mCallback286;
    private final View.OnClickListener mCallback287;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_arrow, 7);
        sparseIntArray.put(R.id.tv_video, 8);
    }

    public KswId7MainPage2FragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private KswId7MainPage2FragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[7], bindings[4], bindings[2], bindings[5], bindings[6], bindings[1], bindings[8]);
        this.mDirtyFlags = -1;
        this.llCarCard.setTag((Object) null);
        this.llComputerCard.setTag((Object) null);
        this.llFileCard.setTag((Object) null);
        this.llLinkCard.setTag((Object) null);
        this.llVideoCard.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = bindings[3];
        this.mboundView3 = textView;
        textView.setTag((Object) null);
        setRootTag(root);
        this.mCallback287 = new OnClickListener(this, 5);
        this.mCallback285 = new OnClickListener(this, 3);
        this.mCallback286 = new OnClickListener(this, 4);
        this.mCallback283 = new OnClickListener(this, 1);
        this.mCallback284 = new OnClickListener(this, 2);
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
        if (3 != variableId) {
            return false;
        }
        setCarViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setCarViewModel(LauncherViewModel CarViewModel) {
        this.mCarViewModel = CarViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCarViewModelCarInfoOilValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCarViewModelCarInfoOilValue(ObservableField<String> observableField, int fieldId) {
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
        String carViewModelCarInfoOilValueGet = null;
        String mboundView3AndroidStringOilSizeCarViewModelCarInfoOilValue = null;
        ObservableField<String> carViewModelCarInfoOilValue = null;
        LauncherViewModel carViewModel = this.mCarViewModel;
        View.OnFocusChangeListener carViewModelKswId7VideoCardFocusChangeListener = null;
        if ((dirtyFlags & 5) != 0) {
            CarInfo carViewModelCarInfo = LauncherViewModel.carInfo;
            if (carViewModelCarInfo != null) {
                carViewModelCarInfoOilValue = carViewModelCarInfo.oilValue;
            }
            updateRegistration(0, (Observable) carViewModelCarInfoOilValue);
            if (carViewModelCarInfoOilValue != null) {
                carViewModelCarInfoOilValueGet = carViewModelCarInfoOilValue.get();
            }
            mboundView3AndroidStringOilSizeCarViewModelCarInfoOilValue = this.mboundView3.getResources().getString(R.string.oil_size, new Object[]{carViewModelCarInfoOilValueGet});
        }
        if (!((dirtyFlags & 6) == 0 || carViewModel == null)) {
            carViewModelKswId7VideoCardFocusChangeListener = carViewModel.kswId7VideoCardFocusChangeListener;
        }
        if ((4 & dirtyFlags) != 0) {
            this.llCarCard.setOnClickListener(this.mCallback285);
            this.llComputerCard.setOnClickListener(this.mCallback284);
            this.llFileCard.setOnClickListener(this.mCallback286);
            this.llLinkCard.setOnClickListener(this.mCallback287);
            this.llVideoCard.setOnClickListener(this.mCallback283);
        }
        if ((6 & dirtyFlags) != 0) {
            this.llVideoCard.setOnFocusChangeListener(carViewModelKswId7VideoCardFocusChangeListener);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, mboundView3AndroidStringOilSizeCarViewModelCarInfoOilValue);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean carViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel carViewModel = this.mCarViewModel;
                if (carViewModel == null) {
                    carViewModelJavaLangObjectNull = false;
                }
                if (carViewModelJavaLangObjectNull) {
                    carViewModel.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel carViewModel2 = this.mCarViewModel;
                if (carViewModel2 == null) {
                    carViewModelJavaLangObjectNull = false;
                }
                if (carViewModelJavaLangObjectNull) {
                    carViewModel2.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel carViewModel3 = this.mCarViewModel;
                if (carViewModel3 == null) {
                    carViewModelJavaLangObjectNull = false;
                }
                if (carViewModelJavaLangObjectNull) {
                    carViewModel3.openCar(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel carViewModel4 = this.mCarViewModel;
                if (carViewModel4 == null) {
                    carViewModelJavaLangObjectNull = false;
                }
                if (carViewModelJavaLangObjectNull) {
                    carViewModel4.openFileManager(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel carViewModel5 = this.mCarViewModel;
                if (carViewModel5 == null) {
                    carViewModelJavaLangObjectNull = false;
                }
                if (carViewModelJavaLangObjectNull) {
                    carViewModel5.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

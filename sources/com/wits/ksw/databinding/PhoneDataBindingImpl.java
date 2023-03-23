package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class PhoneDataBindingImpl extends PhoneDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback322;
    private long mDirtyFlags;
    private final ImageView mboundView2;

    public PhoneDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private PhoneDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[0], bindings[3]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.llContainer.setTag((Object) null);
        ImageView imageView = bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        this.tvDesc.setTag((Object) null);
        setRootTag(root);
        this.mCallback322 = new OnClickListener(this, 1);
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
        if (2 != variableId) {
            return false;
        }
        setBtViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setBtViewModel(LauncherViewModel BtViewModel) {
        this.mBtViewModel = BtViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeBtViewModelBtState((ObservableField) object, fieldId);
            case 1:
                return onChangeBtViewModelPhoneConState((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeBtViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeBtViewModelPhoneConState(ObservableInt BtViewModelPhoneConState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        String btViewModelBtStateGet = null;
        int btViewModelPhoneConStateGet = 0;
        LauncherViewModel btViewModel = this.mBtViewModel;
        ObservableField<String> btViewModelBtState = null;
        ObservableInt btViewModelPhoneConState = null;
        View.OnFocusChangeListener btViewModelPhoneViewFocusChangeListener = null;
        Drawable btViewModelPhoneConStateInt0MboundView2AndroidDrawableId8MainIconPhoneUnconnectedMboundView2AndroidDrawableId8MainIconPhoneConnected = null;
        if ((15 & dirtyFlags) != 0) {
            boolean z = false;
            if ((dirtyFlags & 13) != 0) {
                if (btViewModel != null) {
                    btViewModelBtState = btViewModel.btState;
                }
                updateRegistration(0, (Observable) btViewModelBtState);
                if (btViewModelBtState != null) {
                    btViewModelBtStateGet = btViewModelBtState.get();
                }
            }
            if ((dirtyFlags & 14) != 0) {
                if (btViewModel != null) {
                    btViewModelPhoneConState = btViewModel.phoneConState;
                }
                updateRegistration(1, (Observable) btViewModelPhoneConState);
                if (btViewModelPhoneConState != null) {
                    btViewModelPhoneConStateGet = btViewModelPhoneConState.get();
                }
                if (btViewModelPhoneConStateGet == 0) {
                    z = true;
                }
                boolean btViewModelPhoneConStateInt0 = z;
                if ((dirtyFlags & 14) != 0) {
                    if (btViewModelPhoneConStateInt0) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                btViewModelPhoneConStateInt0MboundView2AndroidDrawableId8MainIconPhoneUnconnectedMboundView2AndroidDrawableId8MainIconPhoneConnected = AppCompatResources.getDrawable(this.mboundView2.getContext(), btViewModelPhoneConStateInt0 ? R.drawable.id8_main_icon_phone_unconnected : R.drawable.id8_main_icon_phone_connected);
            }
            if (!((dirtyFlags & 12) == 0 || btViewModel == null)) {
                btViewModelPhoneViewFocusChangeListener = btViewModel.phoneViewFocusChangeListener;
            }
        }
        if ((8 & dirtyFlags) != 0) {
            this.ivMask.setOnClickListener(this.mCallback322);
        }
        if ((12 & dirtyFlags) != 0) {
            this.ivMask.setOnFocusChangeListener(btViewModelPhoneViewFocusChangeListener);
        }
        if ((dirtyFlags & 14) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView2, btViewModelPhoneConStateInt0MboundView2AndroidDrawableId8MainIconPhoneUnconnectedMboundView2AndroidDrawableId8MainIconPhoneConnected);
        }
        if ((dirtyFlags & 13) != 0) {
            TextViewBindingAdapter.setText(this.tvDesc, btViewModelBtStateGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel btViewModel = this.mBtViewModel;
        if (btViewModel != null) {
            btViewModel.openBtApp(callbackArg_0);
        }
    }
}

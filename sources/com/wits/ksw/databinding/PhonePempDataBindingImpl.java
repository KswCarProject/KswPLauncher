package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class PhonePempDataBindingImpl extends PhonePempDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback237;
    private long mDirtyFlags;
    private final ImageView mboundView1;
    private final TextView mboundView4;
    private final TextView mboundView5;

    public PhonePempDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private PhonePempDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ImageView) bindings[2], (RelativeLayout) bindings[0], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.ivMask.setTag(null);
        this.llContainer.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        this.tvDesc.setTag(null);
        setRootTag(root);
        this.mCallback237 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (2 == variableId) {
            setBtViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.PhonePempDataBinding
    public void setBtViewModel(LauncherViewModel BtViewModel) {
        this.mBtViewModel = BtViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeBtViewModelBtState((ObservableField) object, fieldId);
            case 1:
                return onChangeBtViewModelMonth((ObservableField) object, fieldId);
            case 2:
                return onChangeBtViewModelPhoneConState((ObservableInt) object, fieldId);
            case 3:
                return onChangeBtViewModelDay((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeBtViewModelBtState(ObservableField<String> BtViewModelBtState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBtViewModelMonth(ObservableField<String> BtViewModelMonth, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBtViewModelPhoneConState(ObservableInt BtViewModelPhoneConState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeBtViewModelDay(ObservableField<String> BtViewModelDay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        ObservableField<String> btViewModelDay;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String btViewModelMonthGet = null;
        String btViewModelBtStateGet = null;
        String btViewModelDayGet = null;
        int btViewModelPhoneConStateGet = 0;
        LauncherViewModel btViewModel = this.mBtViewModel;
        Drawable btViewModelPhoneConStateInt0MboundView1AndroidDrawablePempId8MainIconPhoneUnconnectedMboundView1AndroidDrawablePempId8MainIconPhoneConnected = null;
        ObservableField<String> btViewModelBtState = null;
        ObservableField<String> btViewModelMonth = null;
        ObservableInt btViewModelPhoneConState = null;
        View.OnFocusChangeListener btViewModelPhoneViewFocusChangeListener = null;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (btViewModel != null) {
                    btViewModelBtState = btViewModel.btState;
                }
                updateRegistration(0, btViewModelBtState);
                if (btViewModelBtState != null) {
                    btViewModelBtStateGet = btViewModelBtState.get();
                }
            }
            if ((dirtyFlags & 50) != 0) {
                if (btViewModel != null) {
                    btViewModelMonth = btViewModel.month;
                }
                updateRegistration(1, btViewModelMonth);
                if (btViewModelMonth != null) {
                    btViewModelMonthGet = btViewModelMonth.get();
                }
            }
            if ((dirtyFlags & 52) != 0) {
                if (btViewModel != null) {
                    btViewModelPhoneConState = btViewModel.phoneConState;
                }
                updateRegistration(2, btViewModelPhoneConState);
                if (btViewModelPhoneConState != null) {
                    btViewModelPhoneConStateGet = btViewModelPhoneConState.get();
                }
                boolean btViewModelPhoneConStateInt0 = btViewModelPhoneConStateGet == 0;
                if ((dirtyFlags & 52) != 0) {
                    if (btViewModelPhoneConStateInt0) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                btViewModelPhoneConStateInt0MboundView1AndroidDrawablePempId8MainIconPhoneUnconnectedMboundView1AndroidDrawablePempId8MainIconPhoneConnected = AppCompatResources.getDrawable(this.mboundView1.getContext(), btViewModelPhoneConStateInt0 ? C0899R.C0900drawable.pemp_id8_main_icon_phone_unconnected : C0899R.C0900drawable.pemp_id8_main_icon_phone_connected);
            }
            if ((dirtyFlags & 48) != 0 && btViewModel != null) {
                btViewModelPhoneViewFocusChangeListener = btViewModel.phoneViewFocusChangeListener;
            }
            if ((dirtyFlags & 56) != 0) {
                if (btViewModel == null) {
                    btViewModelDay = null;
                } else {
                    btViewModelDay = btViewModel.day;
                }
                updateRegistration(3, btViewModelDay);
                if (btViewModelDay != null) {
                    btViewModelDayGet = btViewModelDay.get();
                }
            }
        }
        if ((32 & dirtyFlags) != 0) {
            this.ivMask.setOnClickListener(this.mCallback237);
        }
        if ((dirtyFlags & 48) != 0) {
            this.ivMask.setOnFocusChangeListener(btViewModelPhoneViewFocusChangeListener);
        }
        if ((dirtyFlags & 52) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, btViewModelPhoneConStateInt0MboundView1AndroidDrawablePempId8MainIconPhoneUnconnectedMboundView1AndroidDrawablePempId8MainIconPhoneConnected);
        }
        if ((dirtyFlags & 56) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, btViewModelDayGet);
        }
        if ((dirtyFlags & 50) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, btViewModelMonthGet);
        }
        if ((dirtyFlags & 49) != 0) {
            TextViewBindingAdapter.setText(this.tvDesc, btViewModelBtStateGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel btViewModel = this.mBtViewModel;
        boolean btViewModelJavaLangObjectNull = btViewModel != null;
        if (btViewModelJavaLangObjectNull) {
            btViewModel.openBtApp(callbackArg_0);
        }
    }
}

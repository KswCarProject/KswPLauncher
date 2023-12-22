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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class PhoneEditorPempDataBindingImpl extends PhoneEditorPempDataBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView3;
    private final TextView mboundView4;

    public PhoneEditorPempDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private PhoneEditorPempDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (LinearLayout) bindings[1], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.layout.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        this.tvDesc.setTag(null);
        setRootTag(root);
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

    @Override // com.wits.ksw.databinding.PhoneEditorPempDataBinding
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String btViewModelMonthGet = null;
        String btViewModelBtStateGet = null;
        String btViewModelDayGet = null;
        int btViewModelPhoneConStateGet = 0;
        LauncherViewModel btViewModel = this.mBtViewModel;
        ObservableField<String> btViewModelBtState = null;
        ObservableField<String> btViewModelMonth = null;
        ObservableInt btViewModelPhoneConState = null;
        ObservableField<String> btViewModelDay = null;
        Drawable btViewModelPhoneConStateInt0LayoutAndroidDrawablePempId8MainEditIconPhoneUnconnectedLayoutAndroidDrawablePempId8MainEditIconPhoneConnected = null;
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
                btViewModelPhoneConStateInt0LayoutAndroidDrawablePempId8MainEditIconPhoneUnconnectedLayoutAndroidDrawablePempId8MainEditIconPhoneConnected = AppCompatResources.getDrawable(this.layout.getContext(), btViewModelPhoneConStateInt0 ? C0899R.C0900drawable.pemp_id8_main_edit_icon_phone_unconnected : C0899R.C0900drawable.pemp_id8_main_edit_icon_phone_connected);
            }
            if ((dirtyFlags & 56) != 0) {
                if (btViewModel != null) {
                    btViewModelDay = btViewModel.day;
                }
                updateRegistration(3, btViewModelDay);
                if (btViewModelDay != null) {
                    btViewModelDayGet = btViewModelDay.get();
                }
            }
        }
        if ((dirtyFlags & 52) != 0) {
            ViewBindingAdapter.setBackground(this.layout, btViewModelPhoneConStateInt0LayoutAndroidDrawablePempId8MainEditIconPhoneUnconnectedLayoutAndroidDrawablePempId8MainEditIconPhoneConnected);
        }
        if ((dirtyFlags & 56) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, btViewModelDayGet);
        }
        if ((dirtyFlags & 50) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, btViewModelMonthGet);
        }
        if ((dirtyFlags & 49) != 0) {
            TextViewBindingAdapter.setText(this.tvDesc, btViewModelBtStateGet);
        }
    }
}

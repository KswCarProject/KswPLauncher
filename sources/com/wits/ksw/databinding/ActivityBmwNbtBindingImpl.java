package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.model.BwmNbtModel;

public class ActivityBmwNbtBindingImpl extends ActivityBmwNbtBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback131;
    @Nullable
    private final View.OnClickListener mCallback132;
    @Nullable
    private final View.OnClickListener mCallback133;
    @Nullable
    private final View.OnClickListener mCallback134;
    @Nullable
    private final View.OnClickListener mCallback135;
    @Nullable
    private final View.OnClickListener mCallback136;
    @Nullable
    private final View.OnClickListener mCallback137;
    @Nullable
    private final View.OnClickListener mCallback138;
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    private final ImageView mboundView1;
    @NonNull
    private final ImageView mboundView2;
    @NonNull
    private final ImageView mboundView3;
    @NonNull
    private final ImageView mboundView4;

    public ActivityBmwNbtBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityBmwNbtBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[5], bindings[11], bindings[7], bindings[6], bindings[9], bindings[10], bindings[12], bindings[8]);
        this.mDirtyFlags = -1;
        this.appLl.setTag((Object) null);
        this.dashbroadLl.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.musicLl.setTag((Object) null);
        this.naviLl.setTag((Object) null);
        this.phoneLl.setTag((Object) null);
        this.rlCar.setTag((Object) null);
        this.rlSettings.setTag((Object) null);
        this.videoLl.setTag((Object) null);
        setRootTag(root);
        this.mCallback136 = new OnClickListener(this, 6);
        this.mCallback137 = new OnClickListener(this, 7);
        this.mCallback134 = new OnClickListener(this, 4);
        this.mCallback135 = new OnClickListener(this, 5);
        this.mCallback132 = new OnClickListener(this, 2);
        this.mCallback133 = new OnClickListener(this, 3);
        this.mCallback138 = new OnClickListener(this, 8);
        this.mCallback131 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (7 != variableId) {
            return false;
        }
        setNbtModel((BwmNbtModel) variable);
        return true;
    }

    public void setNbtModel(@Nullable BwmNbtModel NbtModel) {
        this.mNbtModel = NbtModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNbtModelCircleBgId((ObservableInt) object, fieldId);
            case 1:
                return onChangeNbtModelIndicatorBgId((ObservableInt) object, fieldId);
            case 2:
                return onChangeNbtModelItemLogoId((ObservableInt) object, fieldId);
            case 3:
                return onChangeNbtModelLeftBGId((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeNbtModelCircleBgId(ObservableInt NbtModelCircleBgId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeNbtModelIndicatorBgId(ObservableInt NbtModelIndicatorBgId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeNbtModelItemLogoId(ObservableInt NbtModelItemLogoId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeNbtModelLeftBGId(ObservableInt NbtModelLeftBGId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
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
        int nbtModelIndicatorBgIdGet = 0;
        int nbtModelCircleBgIdGet = 0;
        ObservableInt nbtModelCircleBgId = null;
        ObservableInt nbtModelIndicatorBgId = null;
        int nbtModelLeftBGIdGet = 0;
        ObservableInt nbtModelItemLogoId = null;
        int nbtModelItemLogoIdGet = 0;
        BwmNbtModel nbtModel = this.mNbtModel;
        ObservableInt nbtModelLeftBGId = null;
        if ((63 & dirtyFlags) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (nbtModel != null) {
                    nbtModelCircleBgId = nbtModel.circleBgId;
                }
                updateRegistration(0, (Observable) nbtModelCircleBgId);
                if (nbtModelCircleBgId != null) {
                    nbtModelCircleBgIdGet = nbtModelCircleBgId.get();
                }
            }
            if ((dirtyFlags & 50) != 0) {
                if (nbtModel != null) {
                    nbtModelIndicatorBgId = nbtModel.indicatorBgId;
                }
                updateRegistration(1, (Observable) nbtModelIndicatorBgId);
                if (nbtModelIndicatorBgId != null) {
                    nbtModelIndicatorBgIdGet = nbtModelIndicatorBgId.get();
                }
            }
            if ((dirtyFlags & 52) != 0) {
                if (nbtModel != null) {
                    nbtModelItemLogoId = nbtModel.itemLogoId;
                }
                updateRegistration(2, (Observable) nbtModelItemLogoId);
                if (nbtModelItemLogoId != null) {
                    nbtModelItemLogoIdGet = nbtModelItemLogoId.get();
                }
            }
            if ((dirtyFlags & 56) != 0) {
                if (nbtModel != null) {
                    nbtModelLeftBGId = nbtModel.leftBGId;
                }
                updateRegistration(3, (Observable) nbtModelLeftBGId);
                if (nbtModelLeftBGId != null) {
                    nbtModelLeftBGIdGet = nbtModelLeftBGId.get();
                }
            }
        }
        if ((dirtyFlags & 32) != 0) {
            this.appLl.setOnClickListener(this.mCallback131);
            this.dashbroadLl.setOnClickListener(this.mCallback137);
            this.musicLl.setOnClickListener(this.mCallback133);
            this.naviLl.setOnClickListener(this.mCallback132);
            this.phoneLl.setOnClickListener(this.mCallback135);
            this.rlCar.setOnClickListener(this.mCallback136);
            this.rlSettings.setOnClickListener(this.mCallback138);
            this.videoLl.setOnClickListener(this.mCallback134);
        }
        if ((dirtyFlags & 56) != 0) {
            BaseBindingModel.srcImage(this.mboundView1, nbtModelLeftBGIdGet);
        }
        if ((dirtyFlags & 50) != 0) {
            BaseBindingModel.srcImage(this.mboundView2, nbtModelIndicatorBgIdGet);
        }
        if ((dirtyFlags & 49) != 0) {
            BaseBindingModel.srcImage(this.mboundView3, nbtModelCircleBgIdGet);
        }
        if ((52 & dirtyFlags) != 0) {
            BaseBindingModel.srcImage(this.mboundView4, nbtModelItemLogoIdGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean nbtModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                BwmNbtModel nbtModel = this.mNbtModel;
                if (nbtModel != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 2:
                BwmNbtModel nbtModel2 = this.mNbtModel;
                if (nbtModel2 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel2.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 3:
                BwmNbtModel nbtModel3 = this.mNbtModel;
                if (nbtModel3 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel3.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 4:
                BwmNbtModel nbtModel4 = this.mNbtModel;
                if (nbtModel4 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel4.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 5:
                BwmNbtModel nbtModel5 = this.mNbtModel;
                if (nbtModel5 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel5.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 6:
                BwmNbtModel nbtModel6 = this.mNbtModel;
                if (nbtModel6 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel6.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 7:
                BwmNbtModel nbtModel7 = this.mNbtModel;
                if (nbtModel7 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel7.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 8:
                BwmNbtModel nbtModel8 = this.mNbtModel;
                if (nbtModel8 != null) {
                    nbtModelJavaLangObjectNull = true;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel8.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

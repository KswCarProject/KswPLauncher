package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.model.BwmNbtModel;

public class ActivityBmwNbtBindingImpl extends ActivityBmwNbtBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback291;
    private final View.OnClickListener mCallback292;
    private final View.OnClickListener mCallback293;
    private final View.OnClickListener mCallback294;
    private final View.OnClickListener mCallback295;
    private final View.OnClickListener mCallback296;
    private final View.OnClickListener mCallback297;
    private final View.OnClickListener mCallback298;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;

    public ActivityBmwNbtBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityBmwNbtBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[5], bindings[11], bindings[7], bindings[6], bindings[9], bindings[10], bindings[12], bindings[8]);
        this.mDirtyFlags = -1;
        this.appLl.setTag((Object) null);
        this.dashbroadLl.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag((Object) null);
        this.musicLl.setTag((Object) null);
        this.naviLl.setTag((Object) null);
        this.phoneLl.setTag((Object) null);
        this.rlCar.setTag((Object) null);
        this.rlSettings.setTag((Object) null);
        this.videoLl.setTag((Object) null);
        setRootTag(root);
        this.mCallback297 = new OnClickListener(this, 7);
        this.mCallback295 = new OnClickListener(this, 5);
        this.mCallback293 = new OnClickListener(this, 3);
        this.mCallback291 = new OnClickListener(this, 1);
        this.mCallback298 = new OnClickListener(this, 8);
        this.mCallback296 = new OnClickListener(this, 6);
        this.mCallback294 = new OnClickListener(this, 4);
        this.mCallback292 = new OnClickListener(this, 2);
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

    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setNbtModel((BwmNbtModel) variable);
        return true;
    }

    public void setNbtModel(BwmNbtModel NbtModel) {
        this.mNbtModel = NbtModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeNbtModelCircleBgId((ObservableInt) object, fieldId);
            case 1:
                return onChangeNbtModelLeftBGId((ObservableInt) object, fieldId);
            case 2:
                return onChangeNbtModelItemLogoId((ObservableInt) object, fieldId);
            case 3:
                return onChangeNbtModelIndicatorBgId((ObservableInt) object, fieldId);
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

    private boolean onChangeNbtModelLeftBGId(ObservableInt NbtModelLeftBGId, int fieldId) {
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

    private boolean onChangeNbtModelIndicatorBgId(ObservableInt NbtModelIndicatorBgId, int fieldId) {
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
        BwmNbtModel nbtModel = this.mNbtModel;
        int nbtModelLeftBGIdGet = 0;
        ObservableInt nbtModelCircleBgId = null;
        ObservableInt nbtModelLeftBGId = null;
        ObservableInt nbtModelItemLogoId = null;
        int nbtModelIndicatorBgIdGet = 0;
        ObservableInt nbtModelIndicatorBgId = null;
        int nbtModelCircleBgIdGet = 0;
        int nbtModelItemLogoIdGet = 0;
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
                    nbtModelLeftBGId = nbtModel.leftBGId;
                }
                updateRegistration(1, (Observable) nbtModelLeftBGId);
                if (nbtModelLeftBGId != null) {
                    nbtModelLeftBGIdGet = nbtModelLeftBGId.get();
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
                    nbtModelIndicatorBgId = nbtModel.indicatorBgId;
                }
                updateRegistration(3, (Observable) nbtModelIndicatorBgId);
                if (nbtModelIndicatorBgId != null) {
                    nbtModelIndicatorBgIdGet = nbtModelIndicatorBgId.get();
                }
            }
        }
        if ((dirtyFlags & 32) != 0) {
            this.appLl.setOnClickListener(this.mCallback291);
            this.dashbroadLl.setOnClickListener(this.mCallback297);
            this.musicLl.setOnClickListener(this.mCallback293);
            this.naviLl.setOnClickListener(this.mCallback292);
            this.phoneLl.setOnClickListener(this.mCallback295);
            this.rlCar.setOnClickListener(this.mCallback296);
            this.rlSettings.setOnClickListener(this.mCallback298);
            this.videoLl.setOnClickListener(this.mCallback294);
        }
        if ((dirtyFlags & 50) != 0) {
            BaseBindingModel.srcImage(this.mboundView1, nbtModelLeftBGIdGet);
        }
        if ((dirtyFlags & 56) != 0) {
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
        boolean nbtModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                BwmNbtModel nbtModel = this.mNbtModel;
                if (nbtModel == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 2:
                BwmNbtModel nbtModel2 = this.mNbtModel;
                if (nbtModel2 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel2.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 3:
                BwmNbtModel nbtModel3 = this.mNbtModel;
                if (nbtModel3 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel3.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 4:
                BwmNbtModel nbtModel4 = this.mNbtModel;
                if (nbtModel4 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel4.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 5:
                BwmNbtModel nbtModel5 = this.mNbtModel;
                if (nbtModel5 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel5.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 6:
                BwmNbtModel nbtModel6 = this.mNbtModel;
                if (nbtModel6 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel6.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 7:
                BwmNbtModel nbtModel7 = this.mNbtModel;
                if (nbtModel7 == null) {
                    nbtModelJavaLangObjectNull = false;
                }
                if (nbtModelJavaLangObjectNull) {
                    nbtModel7.onNbtClick(callbackArg_0);
                    return;
                }
                return;
            case 8:
                BwmNbtModel nbtModel8 = this.mNbtModel;
                if (nbtModel8 == null) {
                    nbtModelJavaLangObjectNull = false;
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

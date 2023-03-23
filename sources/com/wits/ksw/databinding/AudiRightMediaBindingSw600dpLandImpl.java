package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.AudiViewModel;

public class AudiRightMediaBindingSw600dpLandImpl extends AudiRightMediaBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback121;
    private final View.OnClickListener mCallback122;
    private final View.OnClickListener mCallback123;
    private final View.OnClickListener mCallback124;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.info_bg, 6);
        sparseIntArray.put(R.id.linearLayout7, 7);
    }

    public AudiRightMediaBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiRightMediaBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[0], bindings[5], bindings[4], bindings[3], bindings[6], bindings[7], bindings[2]);
        this.mDirtyFlags = -1;
        this.IvRightMusicIcon.setTag((Object) null);
        this.KSWA4LRightShowMedia.setTag((Object) null);
        this.btnMusicNext.setTag((Object) null);
        this.btnMusicPlayPause.setTag((Object) null);
        this.btnMusicPrev.setTag((Object) null);
        this.tvMusicTitleInfor.setTag((Object) null);
        setRootTag(root);
        this.mCallback124 = new OnClickListener(this, 4);
        this.mCallback122 = new OnClickListener(this, 2);
        this.mCallback123 = new OnClickListener(this, 3);
        this.mCallback121 = new OnClickListener(this, 1);
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
        if (26 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmMediaView((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmMediaInfoMusicName((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmMediaView(ObservableInt VmMediaView, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
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
        String vmMediaInfoMusicNameGet = null;
        AudiViewModel vm = this.mVm;
        ObservableInt vmMediaView = null;
        int vmMediaViewGet = 0;
        ObservableField<String> vmMediaInfoMusicName = null;
        if ((dirtyFlags & 10) != 0) {
            MediaInfo vmMediaInfo = AudiViewModel.mediaInfo;
            if (vmMediaInfo != null) {
                vmMediaInfoMusicName = vmMediaInfo.musicName;
            }
            updateRegistration(1, (Observable) vmMediaInfoMusicName);
            if (vmMediaInfoMusicName != null) {
                vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
            }
        }
        if ((dirtyFlags & 13) != 0) {
            if (vm != null) {
                vmMediaView = vm.mediaView;
            }
            updateRegistration(0, (Observable) vmMediaView);
            if (vmMediaView != null) {
                vmMediaViewGet = vmMediaView.get();
            }
        }
        if ((8 & dirtyFlags) != 0) {
            this.IvRightMusicIcon.setOnClickListener(this.mCallback121);
            this.btnMusicNext.setOnClickListener(this.mCallback124);
            this.btnMusicPlayPause.setOnClickListener(this.mCallback123);
            this.btnMusicPrev.setOnClickListener(this.mCallback122);
        }
        if ((dirtyFlags & 13) != 0) {
            ((ConstraintLayout) this.KSWA4LRightShowMedia).setVisibility(vmMediaViewGet);
        }
        if ((10 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.tvMusicTitleInfor, vmMediaInfoMusicNameGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                AudiViewModel vm = this.mVm;
                if (vm == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AudiViewModel vm2 = this.mVm;
                if (vm2 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm2.btnMusicPrev();
                    return;
                }
                return;
            case 3:
                AudiViewModel vm3 = this.mVm;
                if (vm3 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm3.btnMusicPlayPause();
                    return;
                }
                return;
            case 4:
                AudiViewModel vm4 = this.mVm;
                if (vm4 == null) {
                    vmJavaLangObjectNull = false;
                }
                if (vmJavaLangObjectNull) {
                    vm4.btnMusicNext();
                    return;
                }
                return;
            default:
                return;
        }
    }
}

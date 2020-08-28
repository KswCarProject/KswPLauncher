package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.AudiViewModel;

public class AudiRightMediaBindingImpl extends AudiRightMediaBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback50;
    @Nullable
    private final View.OnClickListener mCallback51;
    @Nullable
    private final View.OnClickListener mCallback52;
    @Nullable
    private final View.OnClickListener mCallback53;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.info_bg, 6);
    }

    public AudiRightMediaBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiRightMediaBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[1], bindings[0], bindings[5], bindings[4], bindings[3], bindings[6], (LinearLayout) null, bindings[2]);
        this.mDirtyFlags = -1;
        this.IvRightMusicIcon.setTag((Object) null);
        this.KSWA4LRightShowMedia.setTag((Object) null);
        this.btnMusicNext.setTag((Object) null);
        this.btnMusicPlayPause.setTag((Object) null);
        this.btnMusicPrev.setTag((Object) null);
        this.tvMusicTitleInfor.setTag((Object) null);
        setRootTag(root);
        this.mCallback52 = new OnClickListener(this, 3);
        this.mCallback53 = new OnClickListener(this, 4);
        this.mCallback50 = new OnClickListener(this, 1);
        this.mCallback51 = new OnClickListener(this, 2);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeVmMediaView((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmMediaView(ObservableInt VmMediaView, int fieldId) {
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
        AudiViewModel vm = this.mVm;
        ObservableField<String> vmMediaInfoMusicName = null;
        String vmMediaInfoMusicNameGet = null;
        int vmMediaViewGet = 0;
        ObservableInt vmMediaView = null;
        if ((dirtyFlags & 14) != 0) {
            if (vm != null) {
                vmMediaView = vm.mediaView;
            }
            updateRegistration(1, (Observable) vmMediaView);
            if (vmMediaView != null) {
                vmMediaViewGet = vmMediaView.get();
            }
        }
        if ((dirtyFlags & 9) != 0) {
            MediaInfo vmMediaInfo = AudiViewModel.mediaInfo;
            if (vmMediaInfo != null) {
                vmMediaInfoMusicName = vmMediaInfo.musicName;
            }
            updateRegistration(0, (Observable) vmMediaInfoMusicName);
            if (vmMediaInfoMusicName != null) {
                vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
            }
        }
        if ((8 & dirtyFlags) != 0) {
            this.IvRightMusicIcon.setOnClickListener(this.mCallback50);
            this.btnMusicNext.setOnClickListener(this.mCallback53);
            this.btnMusicPlayPause.setOnClickListener(this.mCallback52);
            this.btnMusicPrev.setOnClickListener(this.mCallback51);
        }
        if ((14 & dirtyFlags) != 0) {
            ((RelativeLayout) this.KSWA4LRightShowMedia).setVisibility(vmMediaViewGet);
        }
        if ((9 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.tvMusicTitleInfor, vmMediaInfoMusicNameGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                AudiViewModel vm = this.mVm;
                if (vm != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AudiViewModel vm2 = this.mVm;
                if (vm2 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm2.btnMusicPrev();
                    return;
                }
                return;
            case 3:
                AudiViewModel vm3 = this.mVm;
                if (vm3 != null) {
                    vmJavaLangObjectNull = true;
                }
                if (vmJavaLangObjectNull) {
                    vm3.btnMusicPlayPause();
                    return;
                }
                return;
            case 4:
                AudiViewModel vm4 = this.mVm;
                if (vm4 != null) {
                    vmJavaLangObjectNull = true;
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

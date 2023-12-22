package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
public class AudiRightMediaBindingSw600dpLandImpl extends AudiRightMediaBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback195;
    private final View.OnClickListener mCallback196;
    private final View.OnClickListener mCallback197;
    private final View.OnClickListener mCallback198;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.info_bg, 6);
        sparseIntArray.put(C0899R.C0901id.linearLayout7, 7);
    }

    public AudiRightMediaBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private AudiRightMediaBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[1], (View) bindings[0], (Button) bindings[5], (Button) bindings[4], (Button) bindings[3], (ImageView) bindings[6], (LinearLayout) bindings[7], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.IvRightMusicIcon.setTag(null);
        this.KSWA4LRightShowMedia.setTag(null);
        this.btnMusicNext.setTag(null);
        this.btnMusicPlayPause.setTag(null);
        this.btnMusicPrev.setTag(null);
        this.tvMusicTitleInfor.setTag(null);
        setRootTag(root);
        this.mCallback197 = new OnClickListener(this, 3);
        this.mCallback198 = new OnClickListener(this, 4);
        this.mCallback195 = new OnClickListener(this, 1);
        this.mCallback196 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (26 == variableId) {
            setVm((AudiViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiRightMediaBinding
    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> VmMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
            updateRegistration(1, vmMediaInfoMusicName);
            if (vmMediaInfoMusicName != null) {
                vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
            }
        }
        if ((dirtyFlags & 13) != 0) {
            if (vm != null) {
                vmMediaView = vm.mediaView;
            }
            updateRegistration(0, vmMediaView);
            if (vmMediaView != null) {
                vmMediaViewGet = vmMediaView.get();
            }
        }
        if ((8 & dirtyFlags) != 0) {
            this.IvRightMusicIcon.setOnClickListener(this.mCallback195);
            this.btnMusicNext.setOnClickListener(this.mCallback198);
            this.btnMusicPlayPause.setOnClickListener(this.mCallback197);
            this.btnMusicPrev.setOnClickListener(this.mCallback196);
        }
        if ((dirtyFlags & 13) != 0) {
            ((ConstraintLayout) this.KSWA4LRightShowMedia).setVisibility(vmMediaViewGet);
        }
        if ((10 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.tvMusicTitleInfor, vmMediaInfoMusicNameGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean vmJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                AudiViewModel vm = this.mVm;
                vmJavaLangObjectNull = vm != null;
                if (vmJavaLangObjectNull) {
                    vm.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AudiViewModel vm2 = this.mVm;
                vmJavaLangObjectNull = vm2 != null;
                if (vmJavaLangObjectNull) {
                    vm2.btnMusicPrev();
                    return;
                }
                return;
            case 3:
                AudiViewModel vm3 = this.mVm;
                vmJavaLangObjectNull = vm3 != null;
                if (vmJavaLangObjectNull) {
                    vm3.btnMusicPlayPause();
                    return;
                }
                return;
            case 4:
                AudiViewModel vm4 = this.mVm;
                vmJavaLangObjectNull = vm4 != null;
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

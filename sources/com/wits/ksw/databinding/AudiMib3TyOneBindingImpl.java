package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class AudiMib3TyOneBindingImpl extends AudiMib3TyOneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.video_itemview, 3);
        sparseIntArray.put(C0899R.C0901id.video_content, 4);
        sparseIntArray.put(C0899R.C0901id.video_content_ll, 5);
        sparseIntArray.put(C0899R.C0901id.video_iv, 6);
        sparseIntArray.put(C0899R.C0901id.video_tv, 7);
        sparseIntArray.put(C0899R.C0901id.music_itemview, 8);
        sparseIntArray.put(C0899R.C0901id.music_content_ll, 9);
        sparseIntArray.put(C0899R.C0901id.music_iv, 10);
        sparseIntArray.put(C0899R.C0901id.music_tv, 11);
        sparseIntArray.put(C0899R.C0901id.navi_itemview, 12);
        sparseIntArray.put(C0899R.C0901id.navi_content, 13);
        sparseIntArray.put(C0899R.C0901id.navi_content_ll, 14);
        sparseIntArray.put(C0899R.C0901id.navi_iv, 15);
        sparseIntArray.put(C0899R.C0901id.navi_tv, 16);
        sparseIntArray.put(C0899R.C0901id.bt_itemview, 17);
        sparseIntArray.put(C0899R.C0901id.bt_content_ll, 18);
        sparseIntArray.put(C0899R.C0901id.bt_iv, 19);
        sparseIntArray.put(C0899R.C0901id.bt_tv, 20);
        sparseIntArray.put(C0899R.C0901id.phonelink_itemview, 21);
        sparseIntArray.put(C0899R.C0901id.phonelink_content, 22);
        sparseIntArray.put(C0899R.C0901id.phonelink_content_ll, 23);
        sparseIntArray.put(C0899R.C0901id.phonelink_iv, 24);
        sparseIntArray.put(C0899R.C0901id.phonelink_tv, 25);
    }

    public AudiMib3TyOneBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }

    private AudiMib3TyOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (TextView) bindings[2], (LinearLayout) bindings[18], (BenzMbuxItemView) bindings[17], (ImageView) bindings[19], (TextView) bindings[20], (LinearLayout) bindings[0], (TextView) bindings[1], (LinearLayout) bindings[9], (BenzMbuxItemView) bindings[8], (ImageView) bindings[10], (TextView) bindings[11], (TextView) bindings[13], (LinearLayout) bindings[14], (BenzMbuxItemView) bindings[12], (ImageView) bindings[15], (TextView) bindings[16], (TextView) bindings[22], (LinearLayout) bindings[23], (BenzMbuxItemView) bindings[21], (ImageView) bindings[24], (TextView) bindings[25], (TextView) bindings[4], (LinearLayout) bindings[5], (BenzMbuxItemView) bindings[3], (ImageView) bindings[6], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.btContent.setTag(null);
        this.fragmentOneLl.setTag(null);
        this.musicContent.setTag(null);
        setRootTag(root);
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
        if (25 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3TyOneBinding
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> ViewModelMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelBtState(ObservableField<String> ViewModelBtState, int fieldId) {
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
        ObservableField<String> viewModelMediaInfoMusicName = null;
        String viewModelMediaInfoMusicNameGet = null;
        ObservableField<String> viewModelBtState = null;
        BcVieModel viewModel = this.mViewModel;
        String viewModelBtStateGet = null;
        if ((dirtyFlags & 9) != 0) {
            MediaInfo viewModelMediaInfo = BcVieModel.mediaInfo;
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoMusicName = viewModelMediaInfo.musicName;
            }
            updateRegistration(0, viewModelMediaInfoMusicName);
            if (viewModelMediaInfoMusicName != null) {
                viewModelMediaInfoMusicNameGet = viewModelMediaInfoMusicName.get();
            }
        }
        if ((dirtyFlags & 14) != 0) {
            if (viewModel != null) {
                viewModelBtState = viewModel.btState;
            }
            updateRegistration(1, viewModelBtState);
            if (viewModelBtState != null) {
                viewModelBtStateGet = viewModelBtState.get();
            }
        }
        if ((14 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.btContent, viewModelBtStateGet);
        }
        if ((9 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.musicContent, viewModelMediaInfoMusicNameGet);
        }
    }
}

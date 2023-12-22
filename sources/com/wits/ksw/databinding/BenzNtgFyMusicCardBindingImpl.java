package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.AppCompatImageView;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public class BenzNtgFyMusicCardBindingImpl extends BenzNtgFyMusicCardBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback307;
    private final View.OnClickListener mCallback308;
    private final View.OnClickListener mCallback309;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final AppCompatImageView mboundView3;
    private final AppCompatImageView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.benz_mbux_local_card_title, 5);
    }

    public BenzNtgFyMusicCardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private BenzNtgFyMusicCardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (AppCompatImageView) bindings[1], (AppCompatTextView) bindings[2], (AppCompatTextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.benzMbuxLocalCardBg.setTag(null);
        this.benzMbuxLocalCardContent.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        AppCompatImageView appCompatImageView = (AppCompatImageView) bindings[3];
        this.mboundView3 = appCompatImageView;
        appCompatImageView.setTag(null);
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) bindings[4];
        this.mboundView4 = appCompatImageView2;
        appCompatImageView2.setTag(null);
        setRootTag(root);
        this.mCallback309 = new OnClickListener(this, 3);
        this.mCallback307 = new OnClickListener(this, 1);
        this.mCallback308 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (16 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BenzNtgFyMusicCardBinding
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoSongInfo(ObservableField<String> ViewModelMediaInfoSongInfo, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
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
        ObservableField<String> viewModelMediaInfoSongInfo = null;
        BcVieModel bcVieModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet = null;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        String viewModelMediaInfoSongInfoJavaLangObjectNullBenzMbuxLocalCardContentAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        if ((dirtyFlags & 5) != 0) {
            MediaInfo viewModelMediaInfo = BcVieModel.mediaInfo;
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoSongInfo = viewModelMediaInfo.songInfo;
            }
            updateRegistration(0, viewModelMediaInfoSongInfo);
            if (viewModelMediaInfoSongInfo != null) {
                viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfo.get();
            }
            viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet == null;
            if ((dirtyFlags & 5) != 0) {
                dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | 16 : dirtyFlags | 8;
            }
        }
        if ((dirtyFlags & 5) != 0) {
            viewModelMediaInfoSongInfoJavaLangObjectNullBenzMbuxLocalCardContentAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = viewModelMediaInfoSongInfoJavaLangObjectNull ? this.benzMbuxLocalCardContent.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoSongInfoGet;
        }
        if ((4 & dirtyFlags) != 0) {
            this.benzMbuxLocalCardBg.setOnClickListener(this.mCallback307);
            this.mboundView3.setOnClickListener(this.mCallback308);
            this.mboundView4.setOnClickListener(this.mCallback309);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.benzMbuxLocalCardContent, viewModelMediaInfoSongInfoJavaLangObjectNullBenzMbuxLocalCardContentAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                BcVieModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                BcVieModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.btnMusicPrev();
                    return;
                }
                return;
            case 3:
                BcVieModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.btnMusicNext();
                    return;
                }
                return;
            default:
                return;
        }
    }
}

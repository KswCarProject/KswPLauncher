package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgHomeImageView;

/* loaded from: classes7.dex */
public class KswmbuxHomeOneBindingImpl extends KswmbuxHomeOneBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback295;
    private final View.OnClickListener mCallback296;
    private final View.OnClickListener mCallback297;
    private long mDirtyFlags;
    private final TextView mboundView5;

    public KswmbuxHomeOneBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private KswmbuxHomeOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ConstraintLayout) bindings[0], (UgHomeImageView) bindings[4], (UgHomeImageView) bindings[1], (UgHomeImageView) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.carConstraintLayout.setTag(null);
        this.kswmbuxHomeBtVaiw.setTag(null);
        this.kswmbuxHomeMusicVaiw.setTag(null);
        this.kswmbuxHomeNaviVaiw.setTag(null);
        TextView textView = (TextView) bindings[5];
        this.mboundView5 = textView;
        textView.setTag(null);
        this.tvMusic.setTag(null);
        setRootTag(root);
        this.mCallback296 = new OnClickListener(this, 2);
        this.mCallback297 = new OnClickListener(this, 3);
        this.mCallback295 = new OnClickListener(this, 1);
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
            setViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.KswmbuxHomeOneBinding
    public void setViewModel(LauncherViewModel ViewModel) {
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
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelBtState(ObservableField<String> ViewModelBtState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> ViewModelMediaInfoMusicName, int fieldId) {
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
        ObservableField<String> viewModelBtState = null;
        ObservableField<String> viewModelMediaInfoMusicName = null;
        String viewModelMediaInfoMusicNameGet = null;
        LauncherViewModel viewModel = this.mViewModel;
        boolean viewModelMediaInfoMusicNameJavaLangObjectNull = false;
        String viewModelBtStateGet = null;
        String viewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = null;
        if ((dirtyFlags & 10) != 0) {
            MediaInfo viewModelMediaInfo = LauncherViewModel.mediaInfo;
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoMusicName = viewModelMediaInfo.musicName;
            }
            updateRegistration(1, viewModelMediaInfoMusicName);
            if (viewModelMediaInfoMusicName != null) {
                viewModelMediaInfoMusicNameGet = viewModelMediaInfoMusicName.get();
            }
            viewModelMediaInfoMusicNameJavaLangObjectNull = viewModelMediaInfoMusicNameGet == null;
            if ((dirtyFlags & 10) != 0) {
                dirtyFlags = viewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 32 : dirtyFlags | 16;
            }
        }
        if ((dirtyFlags & 13) != 0) {
            if (viewModel != null) {
                viewModelBtState = viewModel.btState;
            }
            updateRegistration(0, viewModelBtState);
            if (viewModelBtState != null) {
                viewModelBtStateGet = viewModelBtState.get();
            }
        }
        if ((dirtyFlags & 10) != 0) {
            viewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = viewModelMediaInfoMusicNameJavaLangObjectNull ? this.tvMusic.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoMusicNameGet;
        }
        if ((8 & dirtyFlags) != 0) {
            this.kswmbuxHomeBtVaiw.setOnClickListener(this.mCallback297);
            this.kswmbuxHomeMusicVaiw.setOnClickListener(this.mCallback295);
            this.kswmbuxHomeNaviVaiw.setOnClickListener(this.mCallback296);
        }
        if ((dirtyFlags & 13) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, viewModelBtStateGet);
        }
        if ((dirtyFlags & 10) != 0) {
            TextViewBindingAdapter.setText(this.tvMusic, viewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openBtApp(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

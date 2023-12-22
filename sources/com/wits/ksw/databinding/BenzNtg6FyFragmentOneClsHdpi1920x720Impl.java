package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class BenzNtg6FyFragmentOneClsHdpi1920x720Impl extends BenzNtg6FyFragmentOneCls {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.navi_rl, 3);
        sparseIntArray.put(C0899R.C0901id.navi_itemview, 4);
        sparseIntArray.put(C0899R.C0901id.navi_tv, 5);
        sparseIntArray.put(C0899R.C0901id.navi_tip, 6);
        sparseIntArray.put(C0899R.C0901id.navi_iv1, 7);
        sparseIntArray.put(C0899R.C0901id.space1, 8);
        sparseIntArray.put(C0899R.C0901id.navi_iv2, 9);
        sparseIntArray.put(C0899R.C0901id.music_rl, 10);
        sparseIntArray.put(C0899R.C0901id.music_itemview, 11);
        sparseIntArray.put(C0899R.C0901id.music_tv, 12);
        sparseIntArray.put(C0899R.C0901id.music_iv2, 13);
        sparseIntArray.put(C0899R.C0901id.space2, 14);
        sparseIntArray.put(C0899R.C0901id.music_iv1, 15);
        sparseIntArray.put(C0899R.C0901id.bt_rl, 16);
        sparseIntArray.put(C0899R.C0901id.bt_itemview, 17);
        sparseIntArray.put(C0899R.C0901id.bt_tv, 18);
        sparseIntArray.put(C0899R.C0901id.bt_iv1, 19);
        sparseIntArray.put(C0899R.C0901id.space, 20);
        sparseIntArray.put(C0899R.C0901id.bt_iv2, 21);
        sparseIntArray.put(C0899R.C0901id.car_rl, 22);
        sparseIntArray.put(C0899R.C0901id.car_itemview, 23);
        sparseIntArray.put(C0899R.C0901id.car_tv, 24);
        sparseIntArray.put(C0899R.C0901id.car_tip, 25);
        sparseIntArray.put(C0899R.C0901id.car_iv1, 26);
        sparseIntArray.put(C0899R.C0901id.space4, 27);
        sparseIntArray.put(C0899R.C0901id.car_iv2, 28);
        sparseIntArray.put(C0899R.C0901id.set_rl, 29);
        sparseIntArray.put(C0899R.C0901id.set_itemview, 30);
        sparseIntArray.put(C0899R.C0901id.set_tv, 31);
        sparseIntArray.put(C0899R.C0901id.set_tip, 32);
        sparseIntArray.put(C0899R.C0901id.set_iv1, 33);
        sparseIntArray.put(C0899R.C0901id.space5, 34);
        sparseIntArray.put(C0899R.C0901id.set_iv2, 35);
    }

    public BenzNtg6FyFragmentOneClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private BenzNtg6FyFragmentOneClsHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (BenzMbuxItemView) bindings[17], (ImageView) bindings[19], (ImageView) bindings[21], (RelativeLayout) bindings[16], (TextView) bindings[2], (TextView) bindings[18], (BenzMbuxItemView) bindings[23], (ImageView) bindings[26], (ImageView) bindings[28], (RelativeLayout) bindings[22], (TextView) bindings[25], (TextView) bindings[24], (LinearLayout) bindings[0], (BenzMbuxItemView) bindings[11], (ImageView) bindings[15], (ImageView) bindings[13], (RelativeLayout) bindings[10], (TextView) bindings[1], (TextView) bindings[12], (BenzMbuxItemView) bindings[4], (ImageView) bindings[7], (ImageView) bindings[9], (RelativeLayout) bindings[3], (TextView) bindings[6], (TextView) bindings[5], (BenzMbuxItemView) bindings[30], (ImageView) bindings[33], (ImageView) bindings[35], (RelativeLayout) bindings[29], (TextView) bindings[32], (TextView) bindings[31], (View) bindings[20], (View) bindings[8], (View) bindings[14], (View) bindings[27], (View) bindings[34]);
        this.mDirtyFlags = -1L;
        this.btTip.setTag(null);
        this.fragmentOneLl.setTag(null);
        this.musicTip.setTag(null);
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

    @Override // com.wits.ksw.databinding.BenzNtg6FyFragmentOneCls
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
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
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
        ObservableField<String> viewModelMediaInfoSongInfo = null;
        ObservableField<String> viewModelBtState = null;
        String viewModelBtStateGet = null;
        String viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        BcVieModel viewModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet = null;
        if ((dirtyFlags & 9) != 0) {
            MediaInfo viewModelMediaInfo = BcVieModel.mediaInfo;
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoSongInfo = viewModelMediaInfo.songInfo;
            }
            updateRegistration(0, viewModelMediaInfoSongInfo);
            if (viewModelMediaInfoSongInfo != null) {
                viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfo.get();
            }
            viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet == null;
            if ((dirtyFlags & 9) != 0) {
                dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | 32 : dirtyFlags | 16;
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
        if ((dirtyFlags & 9) != 0) {
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = viewModelMediaInfoSongInfoJavaLangObjectNull ? this.musicTip.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoSongInfoGet;
        }
        if ((dirtyFlags & 14) != 0) {
            TextViewBindingAdapter.setText(this.btTip, viewModelBtStateGet);
        }
        if ((dirtyFlags & 9) != 0) {
            TextViewBindingAdapter.setText(this.musicTip, viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
    }
}

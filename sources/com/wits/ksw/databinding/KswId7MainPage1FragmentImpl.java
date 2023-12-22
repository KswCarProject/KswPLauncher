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
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class KswId7MainPage1FragmentImpl extends KswId7MainPage1Fragment implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback442;
    private final View.OnClickListener mCallback443;
    private final View.OnClickListener mCallback444;
    private final View.OnClickListener mCallback445;
    private final View.OnClickListener mCallback446;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.iv_arrow, 8);
    }

    public KswId7MainPage1FragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private KswId7MainPage1FragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[8], (LinearLayout) bindings[4], (LinearLayout) bindings[1], (LinearLayout) bindings[3], (LinearLayout) bindings[5], (LinearLayout) bindings[7], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.llAppCard.setTag(null);
        this.llMusicCard.setTag(null);
        this.llNaviCard.setTag(null);
        this.llPhoneCard.setTag(null);
        this.llSetCard.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[6];
        this.mboundView6 = textView;
        textView.setTag(null);
        this.tvMusic.setTag(null);
        setRootTag(root);
        this.mCallback445 = new OnClickListener(this, 4);
        this.mCallback443 = new OnClickListener(this, 2);
        this.mCallback446 = new OnClickListener(this, 5);
        this.mCallback442 = new OnClickListener(this, 1);
        this.mCallback444 = new OnClickListener(this, 3);
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
        if (10 == variableId) {
            setMediaViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.KswId7MainPage1Fragment
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelBtState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> MediaViewModelMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelBtState(ObservableField<String> MediaViewModelBtState, int fieldId) {
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
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        String mediaViewModelBtStateGet = null;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
        ObservableField<String> mediaViewModelBtState = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        View.OnFocusChangeListener mediaViewModelKswId7SetCardFocusChangeListener = null;
        if ((dirtyFlags & 9) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if (mediaViewModelMediaInfo != null) {
                mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
            }
            updateRegistration(0, mediaViewModelMediaInfoMusicName);
            if (mediaViewModelMediaInfoMusicName != null) {
                mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
            }
            mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameGet == null;
            if ((dirtyFlags & 9) != 0) {
                dirtyFlags = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 32 : dirtyFlags | 16;
            }
        }
        if ((dirtyFlags & 14) != 0) {
            if (mediaViewModel != null) {
                mediaViewModelBtState = mediaViewModel.btState;
            }
            updateRegistration(1, mediaViewModelBtState);
            if (mediaViewModelBtState != null) {
                mediaViewModelBtStateGet = mediaViewModelBtState.get();
            }
            if ((dirtyFlags & 12) != 0 && mediaViewModel != null) {
                mediaViewModelKswId7SetCardFocusChangeListener = mediaViewModel.kswId7SetCardFocusChangeListener;
            }
        }
        if ((dirtyFlags & 9) != 0) {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.tvMusic.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
        }
        if ((8 & dirtyFlags) != 0) {
            this.llAppCard.setOnClickListener(this.mCallback444);
            this.llMusicCard.setOnClickListener(this.mCallback442);
            this.llNaviCard.setOnClickListener(this.mCallback443);
            this.llPhoneCard.setOnClickListener(this.mCallback445);
            this.llSetCard.setOnClickListener(this.mCallback446);
        }
        if ((dirtyFlags & 12) != 0) {
            this.llSetCard.setOnFocusChangeListener(mediaViewModelKswId7SetCardFocusChangeListener);
        }
        if ((dirtyFlags & 14) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, mediaViewModelBtStateGet);
        }
        if ((dirtyFlags & 9) != 0) {
            TextViewBindingAdapter.setText(this.tvMusic, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean mediaViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel mediaViewModel = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel mediaViewModel2 = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel2 != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel mediaViewModel3 = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel3 != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel3.openApps(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel mediaViewModel4 = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel4 != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel4.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel mediaViewModel5 = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel5 != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel5.openSettings(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

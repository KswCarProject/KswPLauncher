package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class KswId7MainPage1FragmentImpl extends KswId7MainPage1Fragment implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback308;
    private final View.OnClickListener mCallback309;
    private final View.OnClickListener mCallback310;
    private final View.OnClickListener mCallback311;
    private final View.OnClickListener mCallback312;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_arrow, 8);
    }

    public KswId7MainPage1FragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private KswId7MainPage1FragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[8], bindings[4], bindings[1], bindings[3], bindings[5], bindings[7], bindings[2]);
        this.mDirtyFlags = -1;
        this.llAppCard.setTag((Object) null);
        this.llMusicCard.setTag((Object) null);
        this.llNaviCard.setTag((Object) null);
        this.llPhoneCard.setTag((Object) null);
        this.llSetCard.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = bindings[6];
        this.mboundView6 = textView;
        textView.setTag((Object) null);
        this.tvMusic.setTag((Object) null);
        setRootTag(root);
        this.mCallback310 = new OnClickListener(this, 3);
        this.mCallback312 = new OnClickListener(this, 5);
        this.mCallback308 = new OnClickListener(this, 1);
        this.mCallback311 = new OnClickListener(this, 4);
        this.mCallback309 = new OnClickListener(this, 2);
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
        if (10 != variableId) {
            return false;
        }
        setMediaViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
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
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelBtState((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMediaViewModelBtState(ObservableField<String> observableField, int fieldId) {
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
            boolean z = false;
            updateRegistration(0, (Observable) mediaViewModelMediaInfoMusicName);
            if (mediaViewModelMediaInfoMusicName != null) {
                mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
            }
            if (mediaViewModelMediaInfoMusicNameGet == null) {
                z = true;
            }
            mediaViewModelMediaInfoMusicNameJavaLangObjectNull = z;
            if ((dirtyFlags & 9) != 0) {
                dirtyFlags = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 32 : dirtyFlags | 16;
            }
        }
        if ((dirtyFlags & 14) != 0) {
            if (mediaViewModel != null) {
                mediaViewModelBtState = mediaViewModel.btState;
            }
            updateRegistration(1, (Observable) mediaViewModelBtState);
            if (mediaViewModelBtState != null) {
                mediaViewModelBtStateGet = mediaViewModelBtState.get();
            }
            if (!((dirtyFlags & 12) == 0 || mediaViewModel == null)) {
                mediaViewModelKswId7SetCardFocusChangeListener = mediaViewModel.kswId7SetCardFocusChangeListener;
            }
        }
        if ((dirtyFlags & 9) != 0) {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvMusicAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.tvMusic.getResources().getString(R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
        }
        if ((8 & dirtyFlags) != 0) {
            this.llAppCard.setOnClickListener(this.mCallback310);
            this.llMusicCard.setOnClickListener(this.mCallback308);
            this.llNaviCard.setOnClickListener(this.mCallback309);
            this.llPhoneCard.setOnClickListener(this.mCallback311);
            this.llSetCard.setOnClickListener(this.mCallback312);
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

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean mediaViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel mediaViewModel = this.mMediaViewModel;
                if (mediaViewModel == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel mediaViewModel2 = this.mMediaViewModel;
                if (mediaViewModel2 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel2.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel mediaViewModel3 = this.mMediaViewModel;
                if (mediaViewModel3 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel3.openApps(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel mediaViewModel4 = this.mMediaViewModel;
                if (mediaViewModel4 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel4.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel mediaViewModel5 = this.mMediaViewModel;
                if (mediaViewModel5 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
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

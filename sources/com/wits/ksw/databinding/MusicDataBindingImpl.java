package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomizeSeekBar;

public class MusicDataBindingImpl extends MusicDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback64;
    private long mDirtyFlags;
    private final ImageView mboundView5;
    private final CustomizeSeekBar mboundView6;

    public MusicDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private MusicDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, bindings[1], bindings[4], bindings[0], bindings[2], bindings[3]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.ivMusicAlbum.setTag((Object) null);
        this.llContainer.setTag((Object) null);
        ImageView imageView = bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag((Object) null);
        CustomizeSeekBar customizeSeekBar = bindings[6];
        this.mboundView6 = customizeSeekBar;
        customizeSeekBar.setTag((Object) null);
        this.tvSinger.setTag((Object) null);
        this.tvSongTitle.setTag((Object) null);
        setRootTag(root);
        this.mCallback64 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoProgressPercent((ObservableInt) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 3:
                return onChangeMediaViewModelBThirdMusic((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoProgressPercent(ObservableInt MediaViewModelMediaInfoProgressPercent, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelBThirdMusic(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener;
        String mediaViewModelMediaInfoMusicAtistGet;
        BitmapDrawable mediaViewModelMediaInfoPicGet;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener2;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2;
        String str;
        Boolean mediaViewModelBThirdMusicGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic = null;
        ObservableInt mediaViewModelMediaInfoProgressPercent = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        ObservableField<String> mediaViewModelMediaInfoMusicAtist = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        int mediaViewModelMediaInfoProgressPercentGet = 0;
        int mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        BitmapDrawable mediaViewModelMediaInfoPicGet2 = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if ((dirtyFlags & 87) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 65) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                }
                updateRegistration(0, (Observable) mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet2 = mediaViewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 66) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoProgressPercent = mediaViewModelMediaInfo.progressPercent;
                }
                updateRegistration(1, (Observable) mediaViewModelMediaInfoProgressPercent);
                if (mediaViewModelMediaInfoProgressPercent != null) {
                    mediaViewModelMediaInfoProgressPercentGet = mediaViewModelMediaInfoProgressPercent.get();
                }
            }
            if ((dirtyFlags & 68) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(2, (Observable) mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull2 = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 68) == 0) {
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull2) {
                    dirtyFlags |= 256;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else {
                    dirtyFlags |= 128;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 80) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicAtist = mediaViewModelMediaInfo.musicAtist;
                }
                updateRegistration(4, (Observable) mediaViewModelMediaInfoMusicAtist);
                if (mediaViewModelMediaInfoMusicAtist != null) {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                    mediaViewModelMusicViewFocusChangeListener = null;
                    mediaViewModelMediaInfoMusicAtistGet = mediaViewModelMediaInfoMusicAtist.get();
                } else {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                    mediaViewModelMusicViewFocusChangeListener = null;
                    mediaViewModelMediaInfoMusicAtistGet = null;
                }
            } else {
                mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                mediaViewModelMusicViewFocusChangeListener = null;
                mediaViewModelMediaInfoMusicAtistGet = null;
            }
        } else {
            mediaViewModelMediaInfoPicGet = null;
            mediaViewModelMusicViewFocusChangeListener = null;
            mediaViewModelMediaInfoMusicAtistGet = null;
        }
        if ((dirtyFlags & 72) != 0) {
            ObservableField<Boolean> mediaViewModelBThirdMusic = LauncherViewModel.bThirdMusic;
            ObservableField<BitmapDrawable> observableField = mediaViewModelMediaInfoPic;
            updateRegistration(3, (Observable) mediaViewModelBThirdMusic);
            if (mediaViewModelBThirdMusic != null) {
                mediaViewModelBThirdMusicGet = mediaViewModelBThirdMusic.get();
            } else {
                mediaViewModelBThirdMusicGet = null;
            }
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
            boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet = ViewDataBinding.safeUnbox(mediaViewModelBThirdMusicGet);
            Boolean bool = mediaViewModelBThirdMusicGet;
            boolean z = true;
            if (!androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet) {
                z = false;
            }
            boolean mediaViewModelBThirdMusicBooleanTrue = z;
            if ((dirtyFlags & 72) != 0) {
                if (mediaViewModelBThirdMusicBooleanTrue) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                } else {
                    dirtyFlags |= 512;
                }
            }
            mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdMusicBooleanTrue ? 8 : 0;
            boolean z2 = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet;
        } else {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
        }
        if ((dirtyFlags & 96) == 0 || mediaViewModel == null) {
            mediaViewModelMusicViewFocusChangeListener2 = mediaViewModelMusicViewFocusChangeListener;
        } else {
            mediaViewModelMusicViewFocusChangeListener2 = mediaViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 68) != 0) {
            if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull) {
                LauncherViewModel launcherViewModel = mediaViewModel;
                str = this.tvSongTitle.getResources().getString(R.string.ksw_idf7_unkonw_soung);
            } else {
                str = mediaViewModelMediaInfoMusicNameGet;
            }
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = str;
        } else {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName;
        }
        if ((dirtyFlags & 64) != 0) {
            ObservableInt observableInt = mediaViewModelMediaInfoProgressPercent;
            this.ivMask.setOnClickListener(this.mCallback64);
        }
        if ((dirtyFlags & 96) != 0) {
            this.ivMask.setOnFocusChangeListener(mediaViewModelMusicViewFocusChangeListener2);
        }
        if ((dirtyFlags & 72) != 0) {
            this.ivMusicAlbum.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView5.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView6.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSinger.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSongTitle.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 65) != 0) {
            BaseBindingModel.setID8AlbumIcon(this.ivMusicAlbum, mediaViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 66) != 0) {
            CustomizeSeekBar.setProgress(this.mboundView6, mediaViewModelMediaInfoProgressPercentGet);
        }
        if ((dirtyFlags & 80) != 0) {
            TextViewBindingAdapter.setText(this.tvSinger, mediaViewModelMediaInfoMusicAtistGet);
        }
        if ((dirtyFlags & 68) != 0) {
            TextViewBindingAdapter.setText(this.tvSongTitle, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if (mediaViewModel != null) {
            mediaViewModel.openMusicMulti(callbackArg_0);
        }
    }
}

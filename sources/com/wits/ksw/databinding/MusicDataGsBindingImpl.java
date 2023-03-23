package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class MusicDataGsBindingImpl extends MusicDataGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback31;
    private final View.OnClickListener mCallback32;
    private final View.OnClickListener mCallback33;
    private final View.OnClickListener mCallback34;
    private long mDirtyFlags;
    private final ImageView mboundView2;
    private final ProgressBar mboundView8;
    private final LinearLayout mboundView9;

    public MusicDataGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private MusicDataGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, bindings[1], bindings[7], bindings[0], bindings[5], bindings[4], bindings[3], bindings[6], bindings[10], bindings[11]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.ivMusicAlbum.setTag((Object) null);
        this.llContainerGs.setTag((Object) null);
        ImageView imageView = bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        ProgressBar progressBar = bindings[8];
        this.mboundView8 = progressBar;
        progressBar.setTag((Object) null);
        LinearLayout linearLayout = bindings[9];
        this.mboundView9 = linearLayout;
        linearLayout.setTag((Object) null);
        this.musicId8GsNext.setTag((Object) null);
        this.musicId8GsPlayPause.setTag((Object) null);
        this.musicId8GsPrev.setTag((Object) null);
        this.tvSongTitle.setTag((Object) null);
        this.tvStrTime.setTag((Object) null);
        this.tvTotalTime.setTag((Object) null);
        setRootTag(root);
        this.mCallback32 = new OnClickListener(this, 2);
        this.mCallback31 = new OnClickListener(this, 1);
        this.mCallback33 = new OnClickListener(this, 3);
        this.mCallback34 = new OnClickListener(this, 4);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
            this.mDirtyFlags |= 128;
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
                return onChangeMediaViewModelBThirdMusic((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicPlay((ObservableField) object, fieldId);
            case 3:
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoProgressPercent((ObservableInt) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 6:
                return onChangeMediaViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
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

    private boolean onChangeMediaViewModelBThirdMusic(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoProgressPercent(ObservableInt MediaViewModelMediaInfoProgressPercent, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        int mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName;
        Drawable mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2;
        BitmapDrawable mediaViewModelMediaInfoPicGet;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName3;
        Drawable mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic;
        ObservableField<String> mediaViewModelMediaInfoMusicName;
        ObservableField<String> mediaViewModelMediaInfoCurrentTime;
        ObservableField<String> mediaViewModelMediaInfoTotalTime;
        ObservableInt mediaViewModelMediaInfoProgressPercent;
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic;
        long dirtyFlags2;
        Drawable drawable;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String mediaViewModelMediaInfoCurrentTimeGet = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName2 = null;
        ObservableField<Boolean> mediaViewModelBThirdMusic = null;
        int mediaViewModelMediaInfoProgressPercentGet = 0;
        BitmapDrawable mediaViewModelMediaInfoPicGet2 = null;
        boolean mediaViewModelMediaInfoPicJavaLangObjectNull = false;
        boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet = false;
        ObservableField<Boolean> mediaViewModelMediaInfoMusicPlay = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        String mediaViewModelMediaInfoTotalTimeGet = null;
        Boolean mediaViewModelBThirdMusicGet = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        Drawable mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = null;
        Boolean mediaViewModelMediaInfoMusicPlayGet = null;
        if ((dirtyFlags & 258) != 0) {
            mediaViewModelBThirdMusic = LauncherViewModel.bThirdMusic;
            updateRegistration(1, (Observable) mediaViewModelBThirdMusic);
            if (mediaViewModelBThirdMusic != null) {
                mediaViewModelBThirdMusicGet = mediaViewModelBThirdMusic.get();
            }
            androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet = ViewDataBinding.safeUnbox(mediaViewModelBThirdMusicGet);
            boolean mediaViewModelBThirdMusicBooleanTrue = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet;
            if ((dirtyFlags & 258) != 0) {
                if (mediaViewModelBThirdMusicBooleanTrue) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
            }
            mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdMusicBooleanTrue ? 8 : 0;
        } else {
            mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        }
        if ((dirtyFlags & 384) == 0 || mediaViewModel == null) {
            mediaViewModelMusicViewFocusChangeListener = null;
        } else {
            mediaViewModelMusicViewFocusChangeListener = mediaViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 381) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 257) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName2 = mediaViewModelMediaInfo.musicName;
                }
                mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
                updateRegistration(0, (Observable) mediaViewModelMediaInfoMusicName2);
                if (mediaViewModelMediaInfoMusicName2 != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName2.get();
                }
                mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 257) != 0) {
                    if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
            } else {
                mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
            }
            if ((dirtyFlags & 260) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicPlay = mediaViewModelMediaInfo.musicPlay;
                }
                updateRegistration(2, (Observable) mediaViewModelMediaInfoMusicPlay);
                if (mediaViewModelMediaInfoMusicPlay != null) {
                    mediaViewModelMediaInfoMusicPlayGet = mediaViewModelMediaInfoMusicPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet = ViewDataBinding.safeUnbox(mediaViewModelMediaInfoMusicPlayGet);
                if ((dirtyFlags & 260) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet) {
                    dirtyFlags2 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.musicId8GsPlayPause.getContext(), R.drawable.gs_id8_main_icon_music_btn_pause_n);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    drawable = AppCompatResources.getDrawable(this.musicId8GsPlayPause.getContext(), R.drawable.gs_id8_main_icon_music_btn_play_n);
                }
                mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = drawable;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 264) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                } else {
                    mediaViewModelMediaInfoPic = null;
                }
                mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicName2;
                updateRegistration(3, (Observable) mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet2 = mediaViewModelMediaInfoPic.get();
                }
                boolean mediaViewModelMediaInfoPicJavaLangObjectNull2 = mediaViewModelMediaInfoPicGet2 == null;
                if ((dirtyFlags & 264) == 0) {
                    ObservableField<BitmapDrawable> observableField = mediaViewModelMediaInfoPic;
                    mediaViewModelMediaInfoPicJavaLangObjectNull = mediaViewModelMediaInfoPicJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoPicJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    ObservableField<BitmapDrawable> observableField2 = mediaViewModelMediaInfoPic;
                    mediaViewModelMediaInfoPicJavaLangObjectNull = mediaViewModelMediaInfoPicJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    ObservableField<BitmapDrawable> observableField3 = mediaViewModelMediaInfoPic;
                    mediaViewModelMediaInfoPicJavaLangObjectNull = mediaViewModelMediaInfoPicJavaLangObjectNull2;
                }
            } else {
                mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicName2;
            }
            if ((dirtyFlags & 272) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoProgressPercent = mediaViewModelMediaInfo.progressPercent;
                } else {
                    mediaViewModelMediaInfoProgressPercent = null;
                }
                updateRegistration(4, (Observable) mediaViewModelMediaInfoProgressPercent);
                if (mediaViewModelMediaInfoProgressPercent != null) {
                    ObservableInt observableInt = mediaViewModelMediaInfoProgressPercent;
                    mediaViewModelMediaInfoProgressPercentGet = mediaViewModelMediaInfoProgressPercent.get();
                } else {
                    ObservableInt observableInt2 = mediaViewModelMediaInfoProgressPercent;
                }
            }
            if ((dirtyFlags & 288) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoTotalTime = mediaViewModelMediaInfo.totalTime;
                } else {
                    mediaViewModelMediaInfoTotalTime = null;
                }
                updateRegistration(5, (Observable) mediaViewModelMediaInfoTotalTime);
                if (mediaViewModelMediaInfoTotalTime != null) {
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTime.get();
                    ObservableField<String> observableField4 = mediaViewModelMediaInfoTotalTime;
                } else {
                    ObservableField<String> observableField5 = mediaViewModelMediaInfoTotalTime;
                }
            }
            if ((dirtyFlags & 320) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoCurrentTime = mediaViewModelMediaInfo.currentTime;
                } else {
                    mediaViewModelMediaInfoCurrentTime = null;
                }
                updateRegistration(6, (Observable) mediaViewModelMediaInfoCurrentTime);
                if (mediaViewModelMediaInfoCurrentTime != null) {
                    ObservableField<String> observableField6 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = mediaViewModelMediaInfoTotalTimeGet;
                    ObservableField<String> observableField7 = mediaViewModelMediaInfoMusicName;
                    MediaInfo mediaInfo = mediaViewModelMediaInfo;
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTime.get();
                    mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                } else {
                    ObservableField<String> observableField8 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = mediaViewModelMediaInfoTotalTimeGet;
                    ObservableField<String> observableField9 = mediaViewModelMediaInfoMusicName;
                    mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    MediaInfo mediaInfo2 = mediaViewModelMediaInfo;
                    mediaViewModelMediaInfoCurrentTimeGet = null;
                }
            } else {
                mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = mediaViewModelMediaInfoTotalTimeGet;
                ObservableField<String> observableField10 = mediaViewModelMediaInfoMusicName;
                mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                MediaInfo mediaInfo3 = mediaViewModelMediaInfo;
                mediaViewModelMediaInfoCurrentTimeGet = null;
            }
        } else {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2 = null;
            mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = null;
        }
        if ((dirtyFlags & 257) == 0) {
            mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName3 = mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName;
        } else if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull) {
            ObservableField<Boolean> observableField11 = mediaViewModelBThirdMusic;
            mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName3 = this.tvSongTitle.getResources().getString(R.string.ksw_idf7_unkonw_soung);
        } else {
            mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName3 = mediaViewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 264) != 0) {
            if (mediaViewModelMediaInfoPicJavaLangObjectNull) {
                boolean z = mediaViewModelMediaInfoPicJavaLangObjectNull;
                mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic = AppCompatResources.getDrawable(this.ivMusicAlbum.getContext(), R.drawable.id8_gs_main_icon_music_album);
            } else {
                mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic = mediaViewModelMediaInfoPicGet;
            }
        } else {
            mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic = null;
        }
        if ((dirtyFlags & 256) != 0) {
            boolean z2 = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet;
            this.ivMask.setOnClickListener(this.mCallback31);
            this.musicId8GsNext.setOnClickListener(this.mCallback34);
            this.musicId8GsPlayPause.setOnClickListener(this.mCallback33);
            this.musicId8GsPrev.setOnClickListener(this.mCallback32);
        }
        if ((dirtyFlags & 384) != 0) {
            this.ivMask.setOnFocusChangeListener(mediaViewModelMusicViewFocusChangeListener);
        }
        if ((dirtyFlags & 264) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivMusicAlbum, mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic);
        }
        if ((dirtyFlags & 258) != 0) {
            this.ivMusicAlbum.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView2.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView8.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView9.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsNext.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsPlayPause.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsPrev.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSongTitle.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 272) != 0) {
            this.mboundView8.setProgress(mediaViewModelMediaInfoProgressPercentGet);
        }
        if ((dirtyFlags & 260) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.musicId8GsPlayPause, mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN);
        }
        if ((dirtyFlags & 257) != 0) {
            TextViewBindingAdapter.setText(this.tvSongTitle, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName3);
        }
        if ((dirtyFlags & 320) != 0) {
            TextViewBindingAdapter.setText(this.tvStrTime, mediaViewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 288) != 0) {
            TextViewBindingAdapter.setText(this.tvTotalTime, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName2);
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
                    mediaViewModel2.id8GsPreMusic(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel mediaViewModel3 = this.mMediaViewModel;
                if (mediaViewModel3 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel3.id8GsOpenPauseMusic(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel mediaViewModel4 = this.mMediaViewModel;
                if (mediaViewModel4 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel4.id8GsNextMusic(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

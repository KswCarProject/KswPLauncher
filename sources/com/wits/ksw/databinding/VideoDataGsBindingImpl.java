package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
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

public class VideoDataGsBindingImpl extends VideoDataGsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback265;
    private final View.OnClickListener mCallback266;
    private final View.OnClickListener mCallback267;
    private final View.OnClickListener mCallback268;
    private long mDirtyFlags;
    private final ImageView mboundView2;
    private final ProgressBar mboundView7;
    private final LinearLayout mboundView8;

    public VideoDataGsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private VideoDataGsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, bindings[1], bindings[0], bindings[6], bindings[9], bindings[10], bindings[5], bindings[4], bindings[3]);
        this.mDirtyFlags = -1;
        this.ivMask.setTag((Object) null);
        this.llContainerGs.setTag((Object) null);
        ImageView imageView = bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        ProgressBar progressBar = bindings[7];
        this.mboundView7 = progressBar;
        progressBar.setTag((Object) null);
        LinearLayout linearLayout = bindings[8];
        this.mboundView8 = linearLayout;
        linearLayout.setTag((Object) null);
        this.tvSongTitle.setTag((Object) null);
        this.tvStrTime.setTag((Object) null);
        this.tvTotalTime.setTag((Object) null);
        this.videoId8GsNext.setTag((Object) null);
        this.videoId8GsPlayPause.setTag((Object) null);
        this.videoId8GsPrev.setTag((Object) null);
        setRootTag(root);
        this.mCallback267 = new OnClickListener(this, 3);
        this.mCallback265 = new OnClickListener(this, 1);
        this.mCallback268 = new OnClickListener(this, 4);
        this.mCallback266 = new OnClickListener(this, 2);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelBThirdVideo((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoVideoPlay((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoProgressPercentVideo((ObservableInt) object, fieldId);
            case 3:
                return onChangeMediaViewModelMediaInfoCurrentTimeVideo((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoTotalTimeVideo((ObservableField) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoVideoName((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelBThirdVideo(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoVideoPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoProgressPercentVideo(ObservableInt MediaViewModelMediaInfoProgressPercentVideo, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoCurrentTimeVideo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoTotalTimeVideo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoVideoName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        boolean mediaViewModelBThirdVideoBooleanTrue;
        int mediaViewModelMediaInfoProgressPercentVideoGet;
        Drawable mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN;
        String mediaViewModelMediaInfoVideoNameJavaLangObjectNullTvSongTitleAndroidStringUnkonwMediaViewModelMediaInfoVideoName;
        ObservableField<String> mediaViewModelMediaInfoVideoName;
        ObservableField<String> mediaViewModelMediaInfoTotalTimeVideo;
        ObservableField<String> mediaViewModelMediaInfoCurrentTimeVideo;
        long dirtyFlags2;
        int i;
        Context context;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean mediaViewModelMediaInfoVideoNameJavaLangObjectNull = false;
        String mediaViewModelMediaInfoTotalTimeVideoGet = null;
        View.OnFocusChangeListener mediaViewModelVideoViewFocusChangeListener = null;
        ObservableField<Boolean> mediaViewModelBThirdVideo = null;
        Boolean mediaViewModelBThirdVideoGet = null;
        String mediaViewModelMediaInfoCurrentTimeVideoGet = null;
        int mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE = 0;
        ObservableField<Boolean> mediaViewModelMediaInfoVideoPlay = null;
        ObservableInt mediaViewModelMediaInfoProgressPercentVideo = null;
        String mediaViewModelMediaInfoVideoNameGet = null;
        Drawable mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = null;
        Boolean mediaViewModelMediaInfoVideoPlayGet = null;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet = false;
        int mediaViewModelMediaInfoProgressPercentVideoGet2 = 0;
        if ((dirtyFlags & 129) != 0) {
            mediaViewModelBThirdVideo = LauncherViewModel.bThirdVideo;
            updateRegistration(0, (Observable) mediaViewModelBThirdVideo);
            if (mediaViewModelBThirdVideo != null) {
                mediaViewModelBThirdVideoGet = mediaViewModelBThirdVideo.get();
            }
            androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet = ViewDataBinding.safeUnbox(mediaViewModelBThirdVideoGet);
            mediaViewModelBThirdVideoBooleanTrue = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet;
            if ((dirtyFlags & 129) != 0) {
                if (mediaViewModelBThirdVideoBooleanTrue) {
                    dirtyFlags |= 512;
                } else {
                    dirtyFlags |= 256;
                }
            }
            mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdVideoBooleanTrue ? 8 : 0;
        } else {
            mediaViewModelBThirdVideoBooleanTrue = false;
        }
        if ((dirtyFlags & 190) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 130) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoVideoPlay = mediaViewModelMediaInfo.videoPlay;
                }
                boolean z = mediaViewModelBThirdVideoBooleanTrue;
                updateRegistration(1, (Observable) mediaViewModelMediaInfoVideoPlay);
                if (mediaViewModelMediaInfoVideoPlay != null) {
                    mediaViewModelMediaInfoVideoPlayGet = mediaViewModelMediaInfoVideoPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet = ViewDataBinding.safeUnbox(mediaViewModelMediaInfoVideoPlayGet);
                if ((dirtyFlags & 130) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet) {
                    context = this.videoId8GsPlayPause.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = R.drawable.gs_id8_main_icon_music_btn_pause_n;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.videoId8GsPlayPause.getContext();
                    i = R.drawable.gs_id8_main_icon_music_btn_play_n;
                }
                mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = AppCompatResources.getDrawable(context, i);
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 132) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoProgressPercentVideo = mediaViewModelMediaInfo.progressPercentVideo;
                }
                updateRegistration(2, (Observable) mediaViewModelMediaInfoProgressPercentVideo);
                if (mediaViewModelMediaInfoProgressPercentVideo != null) {
                    mediaViewModelMediaInfoProgressPercentVideoGet2 = mediaViewModelMediaInfoProgressPercentVideo.get();
                }
            }
            if ((dirtyFlags & 136) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoCurrentTimeVideo = mediaViewModelMediaInfo.currentTimeVideo;
                } else {
                    mediaViewModelMediaInfoCurrentTimeVideo = null;
                }
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet;
                updateRegistration(3, (Observable) mediaViewModelMediaInfoCurrentTimeVideo);
                if (mediaViewModelMediaInfoCurrentTimeVideo != null) {
                    mediaViewModelMediaInfoCurrentTimeVideoGet = mediaViewModelMediaInfoCurrentTimeVideo.get();
                    ObservableField<String> observableField = mediaViewModelMediaInfoCurrentTimeVideo;
                } else {
                    ObservableField<String> observableField2 = mediaViewModelMediaInfoCurrentTimeVideo;
                }
            }
            if ((dirtyFlags & 144) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoTotalTimeVideo = mediaViewModelMediaInfo.totalTimeVideo;
                } else {
                    mediaViewModelMediaInfoTotalTimeVideo = null;
                }
                updateRegistration(4, (Observable) mediaViewModelMediaInfoTotalTimeVideo);
                if (mediaViewModelMediaInfoTotalTimeVideo != null) {
                    mediaViewModelMediaInfoTotalTimeVideoGet = mediaViewModelMediaInfoTotalTimeVideo.get();
                    ObservableField<String> observableField3 = mediaViewModelMediaInfoTotalTimeVideo;
                } else {
                    ObservableField<String> observableField4 = mediaViewModelMediaInfoTotalTimeVideo;
                }
            }
            if ((dirtyFlags & 160) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoVideoName = mediaViewModelMediaInfo.videoName;
                } else {
                    mediaViewModelMediaInfoVideoName = null;
                }
                updateRegistration(5, (Observable) mediaViewModelMediaInfoVideoName);
                if (mediaViewModelMediaInfoVideoName != null) {
                    mediaViewModelMediaInfoVideoNameGet = mediaViewModelMediaInfoVideoName.get();
                }
                mediaViewModelMediaInfoVideoNameJavaLangObjectNull = mediaViewModelMediaInfoVideoNameGet == null;
                if ((dirtyFlags & 160) == 0) {
                    ObservableField<String> observableField5 = mediaViewModelMediaInfoVideoName;
                    mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    mediaViewModelMediaInfoProgressPercentVideoGet = mediaViewModelMediaInfoProgressPercentVideoGet2;
                } else if (mediaViewModelMediaInfoVideoNameJavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    ObservableField<String> observableField6 = mediaViewModelMediaInfoVideoName;
                    mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    mediaViewModelMediaInfoProgressPercentVideoGet = mediaViewModelMediaInfoProgressPercentVideoGet2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    ObservableField<String> observableField7 = mediaViewModelMediaInfoVideoName;
                    mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    mediaViewModelMediaInfoProgressPercentVideoGet = mediaViewModelMediaInfoProgressPercentVideoGet2;
                }
            } else {
                mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                mediaViewModelMediaInfoProgressPercentVideoGet = mediaViewModelMediaInfoProgressPercentVideoGet2;
            }
        } else {
            boolean z3 = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet;
            mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = null;
            mediaViewModelMediaInfoProgressPercentVideoGet = 0;
        }
        if (!((dirtyFlags & 192) == 0 || mediaViewModel == null)) {
            mediaViewModelVideoViewFocusChangeListener = mediaViewModel.videoViewFocusChangeListener;
        }
        if ((dirtyFlags & 160) != 0) {
            if (mediaViewModelMediaInfoVideoNameJavaLangObjectNull) {
                LauncherViewModel launcherViewModel = mediaViewModel;
                boolean z4 = mediaViewModelMediaInfoVideoNameJavaLangObjectNull;
                mediaViewModelMediaInfoVideoNameJavaLangObjectNullTvSongTitleAndroidStringUnkonwMediaViewModelMediaInfoVideoName = this.tvSongTitle.getResources().getString(R.string.unkonw);
            } else {
                boolean z5 = mediaViewModelMediaInfoVideoNameJavaLangObjectNull;
                mediaViewModelMediaInfoVideoNameJavaLangObjectNullTvSongTitleAndroidStringUnkonwMediaViewModelMediaInfoVideoName = mediaViewModelMediaInfoVideoNameGet;
            }
        } else {
            boolean z6 = mediaViewModelMediaInfoVideoNameJavaLangObjectNull;
            mediaViewModelMediaInfoVideoNameJavaLangObjectNullTvSongTitleAndroidStringUnkonwMediaViewModelMediaInfoVideoName = null;
        }
        if ((dirtyFlags & 128) != 0) {
            ObservableField<Boolean> observableField8 = mediaViewModelBThirdVideo;
            this.ivMask.setOnClickListener(this.mCallback265);
            this.videoId8GsNext.setOnClickListener(this.mCallback268);
            this.videoId8GsPlayPause.setOnClickListener(this.mCallback267);
            this.videoId8GsPrev.setOnClickListener(this.mCallback266);
        }
        if ((dirtyFlags & 192) != 0) {
            this.ivMask.setOnFocusChangeListener(mediaViewModelVideoViewFocusChangeListener);
        }
        if ((dirtyFlags & 129) != 0) {
            this.mboundView2.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.mboundView7.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.mboundView8.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.tvSongTitle.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.videoId8GsNext.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.videoId8GsPlayPause.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
            this.videoId8GsPrev.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 132) != 0) {
            this.mboundView7.setProgress(mediaViewModelMediaInfoProgressPercentVideoGet);
        }
        if ((dirtyFlags & 160) != 0) {
            TextViewBindingAdapter.setText(this.tvSongTitle, mediaViewModelMediaInfoVideoNameJavaLangObjectNullTvSongTitleAndroidStringUnkonwMediaViewModelMediaInfoVideoName);
        }
        if ((dirtyFlags & 136) != 0) {
            TextViewBindingAdapter.setText(this.tvStrTime, mediaViewModelMediaInfoCurrentTimeVideoGet);
        }
        if ((dirtyFlags & 144) != 0) {
            TextViewBindingAdapter.setText(this.tvTotalTime, mediaViewModelMediaInfoTotalTimeVideoGet);
        }
        if ((dirtyFlags & 130) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.videoId8GsPlayPause, mediaViewModelMediaInfoVideoPlayVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNVideoId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN);
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
                    mediaViewModel.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel mediaViewModel2 = this.mMediaViewModel;
                if (mediaViewModel2 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel2.id8GsPreVideo(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel mediaViewModel3 = this.mMediaViewModel;
                if (mediaViewModel3 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel3.id8GsOpenPauseVideo(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel mediaViewModel4 = this.mMediaViewModel;
                if (mediaViewModel4 == null) {
                    mediaViewModelJavaLangObjectNull = false;
                }
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel4.id8GsNextVideo(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

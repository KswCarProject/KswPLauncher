package com.wits.ksw.databinding;

import android.content.Context;
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
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class MusicGsEditorDataBindingImpl extends MusicGsEditorDataBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final ProgressBar mboundView7;
    private final LinearLayout mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.id8_gs_music_edit, 11);
        sparseIntArray.put(R.id.gs_id8_icon_edit_bg, 12);
    }

    public MusicGsEditorDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private MusicGsEditorDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, bindings[12], bindings[11], bindings[6], bindings[4], bindings[3], bindings[2], bindings[5], bindings[9], bindings[10]);
        this.mDirtyFlags = -1;
        this.ivMusicAlbum.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        ProgressBar progressBar = bindings[7];
        this.mboundView7 = progressBar;
        progressBar.setTag((Object) null);
        LinearLayout linearLayout = bindings[8];
        this.mboundView8 = linearLayout;
        linearLayout.setTag((Object) null);
        this.musicId8GsNext.setTag((Object) null);
        this.musicId8GsPlayPause.setTag((Object) null);
        this.musicId8GsPrev.setTag((Object) null);
        this.tvSongTitle.setTag((Object) null);
        this.tvStrTime.setTag((Object) null);
        this.tvTotalTime.setTag((Object) null);
        setRootTag(root);
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
                return onChangeMediaViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoMusicPlay((ObservableField) object, fieldId);
            case 6:
                return onChangeMediaViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
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

    private boolean onChangeMediaViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicPlay(ObservableField<Boolean> observableField, int fieldId) {
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
        Boolean mediaViewModelBThirdMusicGet;
        String mediaViewModelMediaInfoTotalTimeGet;
        Drawable mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN;
        Drawable mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic;
        Boolean mediaViewModelBThirdMusicGet2;
        ObservableField<String> mediaViewModelMediaInfoCurrentTime;
        ObservableField<Boolean> mediaViewModelMediaInfoMusicPlay;
        long dirtyFlags2;
        int i;
        Context context;
        ObservableField<String> mediaViewModelMediaInfoTotalTime;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String mediaViewModelMediaInfoCurrentTimeGet = null;
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic = null;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
        ObservableInt mediaViewModelMediaInfoProgressPercent = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        int mediaViewModelMediaInfoProgressPercentGet = 0;
        int mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        BitmapDrawable mediaViewModelMediaInfoPicGet = null;
        boolean mediaViewModelMediaInfoPicJavaLangObjectNull = false;
        Drawable mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = null;
        Boolean mediaViewModelMediaInfoMusicPlayGet = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        String mediaViewModelMediaInfoTotalTimeGet2 = null;
        if ((dirtyFlags & 375) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 257) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                }
                updateRegistration(0, (Observable) mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPic.get();
                }
                mediaViewModelMediaInfoPicJavaLangObjectNull = mediaViewModelMediaInfoPicGet == null;
                if ((dirtyFlags & 257) != 0) {
                    dirtyFlags = mediaViewModelMediaInfoPicJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PREPARE : dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
            }
            if ((dirtyFlags & 258) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoProgressPercent = mediaViewModelMediaInfo.progressPercent;
                }
                updateRegistration(1, (Observable) mediaViewModelMediaInfoProgressPercent);
                if (mediaViewModelMediaInfoProgressPercent != null) {
                    mediaViewModelMediaInfoProgressPercentGet = mediaViewModelMediaInfoProgressPercent.get();
                }
            }
            if ((dirtyFlags & 260) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(2, (Observable) mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull2 = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 260) == 0) {
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else {
                    dirtyFlags |= 512;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 272) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoTotalTime = mediaViewModelMediaInfo.totalTime;
                } else {
                    mediaViewModelMediaInfoTotalTime = null;
                }
                updateRegistration(4, (Observable) mediaViewModelMediaInfoTotalTime);
                if (mediaViewModelMediaInfoTotalTime != null) {
                    ObservableField<String> observableField = mediaViewModelMediaInfoTotalTime;
                    mediaViewModelMediaInfoTotalTimeGet2 = mediaViewModelMediaInfoTotalTime.get();
                } else {
                    ObservableField<String> observableField2 = mediaViewModelMediaInfoTotalTime;
                }
            }
            if ((dirtyFlags & 288) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicPlay = mediaViewModelMediaInfo.musicPlay;
                } else {
                    mediaViewModelMediaInfoMusicPlay = null;
                }
                updateRegistration(5, (Observable) mediaViewModelMediaInfoMusicPlay);
                if (mediaViewModelMediaInfoMusicPlay != null) {
                    mediaViewModelMediaInfoMusicPlayGet = mediaViewModelMediaInfoMusicPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet = ViewDataBinding.safeUnbox(mediaViewModelMediaInfoMusicPlayGet);
                if ((dirtyFlags & 288) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet) {
                    mediaViewModelBThirdMusicGet = null;
                    context = this.musicId8GsPlayPause.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = R.drawable.gs_id8_main_icon_music_btn_pause_n;
                } else {
                    mediaViewModelBThirdMusicGet = null;
                    dirtyFlags2 = dirtyFlags;
                    context = this.musicId8GsPlayPause.getContext();
                    i = R.drawable.gs_id8_main_icon_music_btn_play_n;
                }
                mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2 = AppCompatResources.getDrawable(context, i);
                ObservableField<Boolean> observableField3 = mediaViewModelMediaInfoMusicPlay;
                boolean z = androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoMusicPlayGet;
                dirtyFlags = dirtyFlags2;
            } else {
                mediaViewModelBThirdMusicGet = null;
            }
            if ((dirtyFlags & 320) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoCurrentTime = mediaViewModelMediaInfo.currentTime;
                } else {
                    mediaViewModelMediaInfoCurrentTime = null;
                }
                updateRegistration(6, (Observable) mediaViewModelMediaInfoCurrentTime);
                if (mediaViewModelMediaInfoCurrentTime != null) {
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTime.get();
                    ObservableField<String> observableField4 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTimeGet2;
                } else {
                    ObservableField<String> observableField5 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTimeGet2;
                }
            } else {
                mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN2;
                mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTimeGet2;
            }
        } else {
            mediaViewModelBThirdMusicGet = null;
            mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN = null;
            mediaViewModelMediaInfoTotalTimeGet = null;
        }
        if ((dirtyFlags & 264) != 0) {
            ObservableField<Boolean> mediaViewModelBThirdMusic = LauncherViewModel.bThirdMusic;
            updateRegistration(3, (Observable) mediaViewModelBThirdMusic);
            if (mediaViewModelBThirdMusic != null) {
                mediaViewModelBThirdMusicGet2 = mediaViewModelBThirdMusic.get();
            } else {
                mediaViewModelBThirdMusicGet2 = mediaViewModelBThirdMusicGet;
            }
            ObservableField<BitmapDrawable> observableField6 = mediaViewModelMediaInfoPic;
            boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet = ViewDataBinding.safeUnbox(mediaViewModelBThirdMusicGet2);
            Boolean bool = mediaViewModelBThirdMusicGet2;
            boolean z2 = true;
            if (!androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet) {
                z2 = false;
            }
            boolean mediaViewModelBThirdMusicBooleanTrue = z2;
            if ((dirtyFlags & 264) != 0) {
                if (mediaViewModelBThirdMusicBooleanTrue) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
            }
            mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdMusicBooleanTrue ? 8 : 0;
            boolean z3 = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdMusicGet;
        } else {
            Boolean bool2 = mediaViewModelBThirdMusicGet;
        }
        if ((dirtyFlags & 260) != 0) {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.tvSongTitle.getResources().getString(R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 257) != 0) {
            mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic = mediaViewModelMediaInfoPicJavaLangObjectNull ? AppCompatResources.getDrawable(this.ivMusicAlbum.getContext(), R.drawable.id8_gs_main_icon_music_album) : mediaViewModelMediaInfoPicGet;
        } else {
            mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic = null;
        }
        if ((dirtyFlags & 257) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivMusicAlbum, mediaViewModelMediaInfoPicJavaLangObjectNullIvMusicAlbumAndroidDrawableId8GsMainIconMusicAlbumMediaViewModelMediaInfoPic);
        }
        if ((dirtyFlags & 264) != 0) {
            this.ivMusicAlbum.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView1.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView7.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView8.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsNext.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsPlayPause.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.musicId8GsPrev.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSongTitle.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 258) != 0) {
            this.mboundView7.setProgress(mediaViewModelMediaInfoProgressPercentGet);
        }
        if ((dirtyFlags & 288) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.musicId8GsPlayPause, mediaViewModelMediaInfoMusicPlayMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPauseNMusicId8GsPlayPauseAndroidDrawableGsId8MainIconMusicBtnPlayN);
        }
        if ((dirtyFlags & 260) != 0) {
            TextViewBindingAdapter.setText(this.tvSongTitle, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 320) != 0) {
            TextViewBindingAdapter.setText(this.tvStrTime, mediaViewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 272) != 0) {
            TextViewBindingAdapter.setText(this.tvTotalTime, mediaViewModelMediaInfoTotalTimeGet);
        }
    }
}

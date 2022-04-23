package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class AlsId7UiMusicSubViewBindingImpl extends AlsId7UiMusicSubViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback56;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.musicImageView, 11);
        sparseIntArray.put(R.id.als_middle_point, 12);
        sparseIntArray.put(R.id.nameImageView, 13);
        sparseIntArray.put(R.id.artistImageView, 14);
        sparseIntArray.put(R.id.albumImageView, 15);
        sparseIntArray.put(R.id.in_nameTextView, 16);
        sparseIntArray.put(R.id.in_artistTextView, 17);
        sparseIntArray.put(R.id.in_albumTextView, 18);
        sparseIntArray.put(R.id.in_currentTimeTextView, 19);
        sparseIntArray.put(R.id.time_middle_tv, 20);
        sparseIntArray.put(R.id.in_time_middle_tv, 21);
        sparseIntArray.put(R.id.in_totalTimeTextView, 22);
    }

    public AlsId7UiMusicSubViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private AlsId7UiMusicSubViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, bindings[15], bindings[8], bindings[2], bindings[12], bindings[3], bindings[14], bindings[7], bindings[9], bindings[1], bindings[4], bindings[18], bindings[17], bindings[19], bindings[16], bindings[21], bindings[22], bindings[0], bindings[11], bindings[13], bindings[6], bindings[5], bindings[20], bindings[10]);
        this.mDirtyFlags = -1;
        this.albumTextView.setTag((Object) null);
        this.alsImage.setTag((Object) null);
        this.alsProcess.setTag((Object) null);
        this.artistTextView.setTag((Object) null);
        this.currentTimeTextView.setTag((Object) null);
        this.imageFrameLayout.setTag((Object) null);
        this.imageView6.setTag((Object) null);
        this.musicConstraintLayout.setTag((Object) null);
        this.nameTextView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.totalTimeTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback56 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
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
        if (6 != variableId) {
            return false;
        }
        setMediaViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 3:
                return onChangeMediaViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 4:
                return onChangeMediaViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 5:
                return onChangeMediaViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 6:
                return onChangeMediaViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 7:
                return onChangeMediaViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
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

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
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

    private boolean onChangeMediaViewModelMediaInfoProgress(ObservableInt MediaViewModelMediaInfoProgress, int fieldId) {
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

    private boolean onChangeMediaViewModelMediaInfoMaxProgress(ObservableInt MediaViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener;
        String mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
        int mediaViewModelMediaInfoMaxProgressGet;
        int mediaViewModelMediaInfoProgressGet;
        String mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2;
        String mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum;
        ObservableInt mediaViewModelMediaInfoMaxProgress;
        ObservableField<String> mediaViewModelMediaInfoCurrentTime;
        ObservableInt mediaViewModelMediaInfoProgress;
        ObservableField<String> mediaViewModelMediaInfoTotalTime;
        ObservableField<String> mediaViewModelMediaInfoMusicAtist;
        ObservableField<String> mediaViewModelMediaInfoMusicAlbum;
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String mediaViewModelMediaInfoCurrentTimeGet = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        boolean mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        BitmapDrawable mediaViewModelMediaInfoPicGet = null;
        boolean mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
        String mediaViewModelMediaInfoMusicAtistGet = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        String mediaViewModelMediaInfoTotalTimeGet = null;
        String mediaViewModelMediaInfoMusicAlbumGet = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        int mediaViewModelMediaInfoProgressGet2 = 0;
        if ((dirtyFlags & 768) == 0 || mediaViewModel == null) {
            mediaViewModelMusicViewFocusChangeListener = null;
        } else {
            mediaViewModelMusicViewFocusChangeListener = mediaViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 767) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 513) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(0, (Observable) mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 513) != 0) {
                    dirtyFlags = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI : dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 514) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                } else {
                    mediaViewModelMediaInfoPic = null;
                }
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
                updateRegistration(1, (Observable) mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPic.get();
                    ObservableField<BitmapDrawable> observableField = mediaViewModelMediaInfoPic;
                } else {
                    ObservableField<BitmapDrawable> observableField2 = mediaViewModelMediaInfoPic;
                }
            } else {
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
            }
            if ((dirtyFlags & 516) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfo.musicAlbum;
                } else {
                    mediaViewModelMediaInfoMusicAlbum = null;
                }
                updateRegistration(2, (Observable) mediaViewModelMediaInfoMusicAlbum);
                if (mediaViewModelMediaInfoMusicAlbum != null) {
                    mediaViewModelMediaInfoMusicAlbumGet = mediaViewModelMediaInfoMusicAlbum.get();
                }
                boolean mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2 = mediaViewModelMediaInfoMusicAlbumGet == null;
                if ((dirtyFlags & 516) == 0) {
                    ObservableField<String> observableField3 = mediaViewModelMediaInfoMusicAlbum;
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    ObservableField<String> observableField4 = mediaViewModelMediaInfoMusicAlbum;
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    ObservableField<String> observableField5 = mediaViewModelMediaInfoMusicAlbum;
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 520) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicAtist = mediaViewModelMediaInfo.musicAtist;
                } else {
                    mediaViewModelMediaInfoMusicAtist = null;
                }
                updateRegistration(3, (Observable) mediaViewModelMediaInfoMusicAtist);
                if (mediaViewModelMediaInfoMusicAtist != null) {
                    mediaViewModelMediaInfoMusicAtistGet = mediaViewModelMediaInfoMusicAtist.get();
                }
                boolean mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2 = mediaViewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 520) == 0) {
                    ObservableField<String> observableField6 = mediaViewModelMediaInfoMusicAtist;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    ObservableField<String> observableField7 = mediaViewModelMediaInfoMusicAtist;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    ObservableField<String> observableField8 = mediaViewModelMediaInfoMusicAtist;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 528) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoTotalTime = mediaViewModelMediaInfo.totalTime;
                } else {
                    mediaViewModelMediaInfoTotalTime = null;
                }
                updateRegistration(4, (Observable) mediaViewModelMediaInfoTotalTime);
                if (mediaViewModelMediaInfoTotalTime != null) {
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTime.get();
                    ObservableField<String> observableField9 = mediaViewModelMediaInfoTotalTime;
                } else {
                    ObservableField<String> observableField10 = mediaViewModelMediaInfoTotalTime;
                }
            }
            if ((dirtyFlags & 544) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoProgress = mediaViewModelMediaInfo.progress;
                } else {
                    mediaViewModelMediaInfoProgress = null;
                }
                updateRegistration(5, (Observable) mediaViewModelMediaInfoProgress);
                if (mediaViewModelMediaInfoProgress != null) {
                    ObservableInt observableInt = mediaViewModelMediaInfoProgress;
                    mediaViewModelMediaInfoProgressGet2 = mediaViewModelMediaInfoProgress.get();
                } else {
                    ObservableInt observableInt2 = mediaViewModelMediaInfoProgress;
                }
            }
            if ((dirtyFlags & 576) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoCurrentTime = mediaViewModelMediaInfo.currentTime;
                } else {
                    mediaViewModelMediaInfoCurrentTime = null;
                }
                updateRegistration(6, (Observable) mediaViewModelMediaInfoCurrentTime);
                if (mediaViewModelMediaInfoCurrentTime != null) {
                    ObservableField<String> observableField11 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTime.get();
                } else {
                    ObservableField<String> observableField12 = mediaViewModelMediaInfoCurrentTime;
                    mediaViewModelMediaInfoCurrentTimeGet = null;
                }
            } else {
                mediaViewModelMediaInfoCurrentTimeGet = null;
            }
            if ((dirtyFlags & 640) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMaxProgress = mediaViewModelMediaInfo.maxProgress;
                } else {
                    mediaViewModelMediaInfoMaxProgress = null;
                }
                String mediaViewModelMediaInfoCurrentTimeGet2 = mediaViewModelMediaInfoCurrentTimeGet;
                updateRegistration(7, (Observable) mediaViewModelMediaInfoMaxProgress);
                if (mediaViewModelMediaInfoMaxProgress != null) {
                    ObservableInt observableInt3 = mediaViewModelMediaInfoMaxProgress;
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoMaxProgressGet = mediaViewModelMediaInfoMaxProgress.get();
                    MediaInfo mediaInfo = mediaViewModelMediaInfo;
                    mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
                } else {
                    ObservableInt observableInt4 = mediaViewModelMediaInfoMaxProgress;
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoMaxProgressGet = 0;
                    MediaInfo mediaInfo2 = mediaViewModelMediaInfo;
                    mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
                }
            } else {
                mediaViewModelMediaInfoMaxProgressGet = 0;
                MediaInfo mediaInfo3 = mediaViewModelMediaInfo;
                mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
            }
        } else {
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
            mediaViewModelMediaInfoProgressGet = 0;
            mediaViewModelMediaInfoMaxProgressGet = 0;
        }
        if ((dirtyFlags & 520) == 0) {
            boolean z = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull;
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
        } else if (mediaViewModelMediaInfoMusicAtistJavaLangObjectNull) {
            ObservableField<String> observableField13 = mediaViewModelMediaInfoMusicName;
            boolean z2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull;
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = this.artistTextView.getResources().getString(R.string.ksw_idf7_unknow_artis);
        } else {
            boolean z3 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull;
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistGet;
        }
        if ((dirtyFlags & 513) != 0) {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.nameTextView.getResources().getString(R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 516) != 0) {
            if (mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                boolean z4 = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull;
                mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = this.albumTextView.getResources().getString(R.string.ksw_idf7_unkonw_album);
            } else {
                mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfoMusicAlbumGet;
            }
        } else {
            mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = null;
        }
        if ((dirtyFlags & 516) != 0) {
            TextViewBindingAdapter.setText(this.albumTextView, mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags & 514) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.alsImage, mediaViewModelMediaInfoPicGet);
            BaseBindingModel.setAlbumIcon(this.imageView6, mediaViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 544) != 0) {
            this.alsProcess.setProgress(mediaViewModelMediaInfoProgressGet);
            SeekBarBindingAdapter.setProgress(this.seekBar, mediaViewModelMediaInfoProgressGet);
        }
        if ((dirtyFlags & 640) != 0) {
            this.alsProcess.setMax(mediaViewModelMediaInfoMaxProgressGet);
            this.seekBar.setMax(mediaViewModelMediaInfoMaxProgressGet);
        }
        if ((dirtyFlags & 520) != 0) {
            TextViewBindingAdapter.setText(this.artistTextView, mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2);
        }
        if ((dirtyFlags & 576) != 0) {
            TextViewBindingAdapter.setText(this.currentTimeTextView, mediaViewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 512) != 0) {
            String str = mediaViewModelMediaInfoCurrentTimeGet;
            this.imageFrameLayout.setOnClickListener(this.mCallback56);
        }
        if ((dirtyFlags & 768) != 0) {
            this.imageFrameLayout.setOnFocusChangeListener(mediaViewModelMusicViewFocusChangeListener);
        }
        if ((dirtyFlags & 513) != 0) {
            TextViewBindingAdapter.setText(this.nameTextView, mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 528) != 0) {
            TextViewBindingAdapter.setText(this.totalTimeTextView, mediaViewModelMediaInfoTotalTimeGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if (mediaViewModel != null) {
            mediaViewModel.openMusicMulti(callbackArg_0);
        }
    }
}

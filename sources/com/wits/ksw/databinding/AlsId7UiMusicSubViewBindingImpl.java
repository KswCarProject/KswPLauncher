package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7_ui.view.CustomCircleProgressView;
import com.wits.ksw.launcher.als_id7_ui.view.CustomRadiusImageView;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinMusicLayout;

/* loaded from: classes7.dex */
public class AlsId7UiMusicSubViewBindingImpl extends AlsId7UiMusicSubViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback60;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.musicImageView, 11);
        sparseIntArray.put(C0899R.C0901id.als_middle_point, 12);
        sparseIntArray.put(C0899R.C0901id.nameImageView, 13);
        sparseIntArray.put(C0899R.C0901id.artistImageView, 14);
        sparseIntArray.put(C0899R.C0901id.albumImageView, 15);
        sparseIntArray.put(C0899R.C0901id.in_nameTextView, 16);
        sparseIntArray.put(C0899R.C0901id.in_artistTextView, 17);
        sparseIntArray.put(C0899R.C0901id.in_albumTextView, 18);
        sparseIntArray.put(C0899R.C0901id.in_currentTimeTextView, 19);
        sparseIntArray.put(C0899R.C0901id.time_middle_tv, 20);
        sparseIntArray.put(C0899R.C0901id.in_time_middle_tv, 21);
        sparseIntArray.put(C0899R.C0901id.in_totalTimeTextView, 22);
    }

    public AlsId7UiMusicSubViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private AlsId7UiMusicSubViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, (ImageView) bindings[15], (TextView) bindings[8], (CustomRadiusImageView) bindings[2], (ImageView) bindings[12], (CustomCircleProgressView) bindings[3], (ImageView) bindings[14], (TextView) bindings[7], (TextView) bindings[9], (CustomSkinMusicLayout) bindings[1], (ImageView) bindings[4], (TextView) bindings[18], (TextView) bindings[17], (TextView) bindings[19], (TextView) bindings[16], (TextView) bindings[21], (TextView) bindings[22], (ConstraintLayout) bindings[0], (ImageView) bindings[11], (ImageView) bindings[13], (TextView) bindings[6], (SeekBar) bindings[5], (TextView) bindings[20], (TextView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.albumTextView.setTag(null);
        this.alsImage.setTag(null);
        this.alsProcess.setTag(null);
        this.artistTextView.setTag(null);
        this.currentTimeTextView.setTag(null);
        this.imageFrameLayout.setTag(null);
        this.imageView6.setTag(null);
        this.musicConstraintLayout.setTag(null);
        this.nameTextView.setTag(null);
        this.seekBar.setTag(null);
        this.totalTimeTextView.setTag(null);
        setRootTag(root);
        this.mCallback60 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512L;
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

    @Override // com.wits.ksw.databinding.AlsId7UiMusicSubViewBinding
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
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

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> MediaViewModelMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> MediaViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAlbum(ObservableField<String> MediaViewModelMediaInfoMusicAlbum, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAtist(ObservableField<String> MediaViewModelMediaInfoMusicAtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoTotalTime(ObservableField<String> MediaViewModelMediaInfoTotalTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoProgress(ObservableInt MediaViewModelMediaInfoProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoCurrentTime(ObservableField<String> MediaViewModelMediaInfoCurrentTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoMaxProgress(ObservableInt MediaViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener;
        String mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
        int mediaViewModelMediaInfoProgressGet;
        int mediaViewModelMediaInfoMaxProgressGet;
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
            this.mDirtyFlags = 0L;
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
        if ((dirtyFlags & 768) != 0 && mediaViewModel != null) {
            mediaViewModelMusicViewFocusChangeListener = mediaViewModel.musicViewFocusChangeListener;
        } else {
            mediaViewModelMusicViewFocusChangeListener = null;
        }
        if ((dirtyFlags & 767) == 0) {
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
            mediaViewModelMediaInfoProgressGet = 0;
            mediaViewModelMediaInfoMaxProgressGet = 0;
        } else {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 513) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(0, mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 513) != 0) {
                    dirtyFlags = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI : dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 514) == 0) {
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
            } else {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoPic = null;
                } else {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                }
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
                updateRegistration(1, mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 516) != 0) {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoMusicAlbum = null;
                } else {
                    mediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfo.musicAlbum;
                }
                updateRegistration(2, mediaViewModelMediaInfoMusicAlbum);
                if (mediaViewModelMediaInfoMusicAlbum != null) {
                    mediaViewModelMediaInfoMusicAlbumGet = mediaViewModelMediaInfoMusicAlbum.get();
                }
                boolean mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2 = mediaViewModelMediaInfoMusicAlbumGet == null;
                if ((dirtyFlags & 516) == 0) {
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 520) != 0) {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoMusicAtist = null;
                } else {
                    mediaViewModelMediaInfoMusicAtist = mediaViewModelMediaInfo.musicAtist;
                }
                updateRegistration(3, mediaViewModelMediaInfoMusicAtist);
                if (mediaViewModelMediaInfoMusicAtist != null) {
                    mediaViewModelMediaInfoMusicAtistGet = mediaViewModelMediaInfoMusicAtist.get();
                }
                boolean mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2 = mediaViewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 520) == 0) {
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 528) != 0) {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoTotalTime = null;
                } else {
                    mediaViewModelMediaInfoTotalTime = mediaViewModelMediaInfo.totalTime;
                }
                updateRegistration(4, mediaViewModelMediaInfoTotalTime);
                if (mediaViewModelMediaInfoTotalTime != null) {
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoTotalTime.get();
                }
            }
            if ((dirtyFlags & 544) != 0) {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoProgress = null;
                } else {
                    mediaViewModelMediaInfoProgress = mediaViewModelMediaInfo.progress;
                }
                updateRegistration(5, mediaViewModelMediaInfoProgress);
                if (mediaViewModelMediaInfoProgress != null) {
                    mediaViewModelMediaInfoProgressGet2 = mediaViewModelMediaInfoProgress.get();
                }
            }
            if ((dirtyFlags & 576) == 0) {
                mediaViewModelMediaInfoCurrentTimeGet = null;
            } else {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoCurrentTime = null;
                } else {
                    mediaViewModelMediaInfoCurrentTime = mediaViewModelMediaInfo.currentTime;
                }
                updateRegistration(6, mediaViewModelMediaInfoCurrentTime);
                if (mediaViewModelMediaInfoCurrentTime == null) {
                    mediaViewModelMediaInfoCurrentTimeGet = null;
                } else {
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTime.get();
                }
            }
            if ((dirtyFlags & 640) == 0) {
                mediaViewModelMediaInfoMaxProgressGet = 0;
                mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
            } else {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoMaxProgress = null;
                } else {
                    mediaViewModelMediaInfoMaxProgress = mediaViewModelMediaInfo.maxProgress;
                }
                String mediaViewModelMediaInfoCurrentTimeGet2 = mediaViewModelMediaInfoCurrentTimeGet;
                updateRegistration(7, mediaViewModelMediaInfoMaxProgress);
                if (mediaViewModelMediaInfoMaxProgress == null) {
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoMaxProgressGet = 0;
                    mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
                } else {
                    int mediaViewModelMediaInfoMaxProgressGet2 = mediaViewModelMediaInfoMaxProgress.get();
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoMaxProgressGet = mediaViewModelMediaInfoMaxProgressGet2;
                    mediaViewModelMediaInfoProgressGet = mediaViewModelMediaInfoProgressGet2;
                }
            }
        }
        if ((dirtyFlags & 520) != 0) {
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull ? this.artistTextView.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : mediaViewModelMediaInfoMusicAtistGet;
        } else {
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
        }
        if ((dirtyFlags & 513) != 0) {
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.nameTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 516) == 0) {
            mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = null;
        } else {
            mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull ? this.albumTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_album) : mediaViewModelMediaInfoMusicAlbumGet;
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
            this.imageFrameLayout.setOnClickListener(this.mCallback60);
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

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        boolean mediaViewModelJavaLangObjectNull = mediaViewModel != null;
        if (mediaViewModelJavaLangObjectNull) {
            mediaViewModel.openMusicMulti(callbackArg_0);
        }
    }
}

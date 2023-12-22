package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
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
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

/* loaded from: classes7.dex */
public class Id7V2SubMusicViewBindingSw600dpLandImpl extends Id7V2SubMusicViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback92;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.nameImageView, 10);
        sparseIntArray.put(C0899R.C0901id.artistImageView, 11);
        sparseIntArray.put(C0899R.C0901id.albumImageView, 12);
    }

    public Id7V2SubMusicViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private Id7V2SubMusicViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, (ImageView) bindings[12], (TextView) bindings[7], (ImageView) bindings[11], (TextView) bindings[6], (TextView) bindings[8], (CustomBmwMusicLayout) bindings[1], (ImageView) bindings[3], (ConstraintLayout) bindings[0], (ImageView) bindings[2], (ImageView) bindings[10], (TextView) bindings[5], (SeekBar) bindings[4], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.albumTextView.setTag(null);
        this.artistTextView.setTag(null);
        this.currentTimeTextView.setTag(null);
        this.imageFrameLayout.setTag(null);
        this.imageView6.setTag(null);
        this.musicConstraintLayout.setTag(null);
        this.musicImageView.setTag(null);
        this.nameTextView.setTag(null);
        this.seekBar.setTag(null);
        this.totalTimeTextView.setTag(null);
        setRootTag(root);
        this.mCallback92 = new OnClickListener(this, 1);
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

    @Override // com.wits.ksw.databinding.Id7V2SubMusicViewBinding
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
                return onChangeMediaViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
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

    private boolean onChangeMediaViewModelMediaInfoPic(ObservableField<BitmapDrawable> MediaViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicAlbum(ObservableField<String> MediaViewModelMediaInfoMusicAlbum, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> MediaViewModelMediaInfoMusicName, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:111:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:167:? A[RETURN, SYNTHETIC] */
    @Override // android.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void executeBindings() {
        long dirtyFlags;
        BitmapDrawable mediaViewModelMediaInfoPicGet;
        String mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
        int mediaViewModelMediaInfoProgressGet;
        String mediaViewModelMediaInfoTotalTimeGet;
        String mediaViewModelMediaInfoMusicAlbumGet;
        View.OnFocusChangeListener mediaViewModelMusicViewFocusChangeListener;
        String mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName;
        int mediaViewModelMediaInfoMaxProgressGet;
        ObservableInt mediaViewModelMediaInfoMaxProgress;
        ObservableField<String> mediaViewModelMediaInfoCurrentTime;
        ObservableInt mediaViewModelMediaInfoProgress;
        ObservableField<String> mediaViewModelMediaInfoTotalTime;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String mediaViewModelMediaInfoCurrentTimeGet = null;
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic = null;
        String mediaViewModelMediaInfoMusicAlbumGet2 = null;
        ObservableField<String> mediaViewModelMediaInfoMusicAlbum = null;
        MediaInfo mediaViewModelMediaInfo = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        String mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = null;
        ObservableField<String> mediaViewModelMediaInfoMusicAtist = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        boolean mediaViewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        BitmapDrawable mediaViewModelMediaInfoPicGet2 = null;
        int mediaViewModelMediaInfoProgressGet2 = 0;
        boolean mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        String mediaViewModelMediaInfoMusicAtistGet = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        int mediaViewModelMediaInfoMaxProgressGet2 = 0;
        String mediaViewModelMediaInfoTotalTimeGet2 = null;
        if ((dirtyFlags & 767) == 0) {
            mediaViewModelMediaInfoPicGet = null;
            mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
            mediaViewModelMediaInfoProgressGet = 0;
            mediaViewModelMediaInfoTotalTimeGet = null;
            mediaViewModelMediaInfoMusicAlbumGet = null;
        } else {
            mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 513) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                }
                updateRegistration(0, mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet2 = mediaViewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 514) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfo.musicAlbum;
                }
                updateRegistration(1, mediaViewModelMediaInfoMusicAlbum);
                if (mediaViewModelMediaInfoMusicAlbum != null) {
                    mediaViewModelMediaInfoMusicAlbumGet2 = mediaViewModelMediaInfoMusicAlbum.get();
                }
                mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull = mediaViewModelMediaInfoMusicAlbumGet2 == null;
                if ((dirtyFlags & 514) != 0) {
                    dirtyFlags = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI : dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 516) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(2, mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull2 = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 516) == 0) {
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 520) != 0) {
                if (mediaViewModelMediaInfo != null) {
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
            if ((dirtyFlags & 528) == 0) {
                mediaViewModelMediaInfoMaxProgressGet = 0;
            } else {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoTotalTime = null;
                } else {
                    mediaViewModelMediaInfoTotalTime = mediaViewModelMediaInfo.totalTime;
                }
                mediaViewModelMediaInfoMaxProgressGet = 0;
                updateRegistration(4, mediaViewModelMediaInfoTotalTime);
                if (mediaViewModelMediaInfoTotalTime != null) {
                    mediaViewModelMediaInfoTotalTimeGet2 = mediaViewModelMediaInfoTotalTime.get();
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
                mediaViewModelMediaInfoMaxProgressGet2 = mediaViewModelMediaInfoMaxProgressGet;
                mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                int i = mediaViewModelMediaInfoProgressGet2;
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
                mediaViewModelMediaInfoProgressGet = i;
                String str = mediaViewModelMediaInfoTotalTimeGet2;
                mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoMusicAlbumGet2;
                mediaViewModelMediaInfoMusicAlbumGet = str;
            } else {
                if (mediaViewModelMediaInfo == null) {
                    mediaViewModelMediaInfoMaxProgress = null;
                } else {
                    mediaViewModelMediaInfoMaxProgress = mediaViewModelMediaInfo.maxProgress;
                }
                String mediaViewModelMediaInfoCurrentTimeGet2 = mediaViewModelMediaInfoCurrentTimeGet;
                updateRegistration(7, mediaViewModelMediaInfoMaxProgress);
                if (mediaViewModelMediaInfoMaxProgress == null) {
                    mediaViewModelMediaInfoMaxProgressGet2 = mediaViewModelMediaInfoMaxProgressGet;
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                    int i2 = mediaViewModelMediaInfoProgressGet2;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
                    mediaViewModelMediaInfoProgressGet = i2;
                    String str2 = mediaViewModelMediaInfoTotalTimeGet2;
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoMusicAlbumGet2;
                    mediaViewModelMediaInfoMusicAlbumGet = str2;
                } else {
                    mediaViewModelMediaInfoMaxProgressGet2 = mediaViewModelMediaInfoMaxProgress.get();
                    mediaViewModelMediaInfoCurrentTimeGet = mediaViewModelMediaInfoCurrentTimeGet2;
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPicGet2;
                    int i3 = mediaViewModelMediaInfoProgressGet2;
                    mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist = null;
                    mediaViewModelMediaInfoProgressGet = i3;
                    String str3 = mediaViewModelMediaInfoTotalTimeGet2;
                    mediaViewModelMediaInfoTotalTimeGet = mediaViewModelMediaInfoMusicAlbumGet2;
                    mediaViewModelMediaInfoMusicAlbumGet = str3;
                }
            }
        }
        if ((dirtyFlags & 768) != 0 && mediaViewModel != null) {
            mediaViewModelMusicViewFocusChangeListener = mediaViewModel.musicViewFocusChangeListener;
            if ((dirtyFlags & 520) == 0) {
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNull ? this.artistTextView.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : mediaViewModelMediaInfoMusicAtistGet;
            } else {
                mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2 = mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist;
            }
            if ((dirtyFlags & 514) != 0) {
                mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum = mediaViewModelMediaInfoMusicAlbumJavaLangObjectNull ? this.albumTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_album) : mediaViewModelMediaInfoTotalTimeGet;
            }
            if ((dirtyFlags & 516) != 0) {
                mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
            } else {
                mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? this.nameTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : mediaViewModelMediaInfoMusicNameGet;
            }
            if ((dirtyFlags & 514) != 0) {
                TextViewBindingAdapter.setText(this.albumTextView, mediaViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMediaViewModelMediaInfoMusicAlbum);
            }
            if ((dirtyFlags & 520) != 0) {
                TextViewBindingAdapter.setText(this.artistTextView, mediaViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMediaViewModelMediaInfoMusicAtist2);
            }
            if ((dirtyFlags & 576) != 0) {
                TextViewBindingAdapter.setText(this.currentTimeTextView, mediaViewModelMediaInfoCurrentTimeGet);
            }
            if ((dirtyFlags & 512) == 0) {
                this.imageFrameLayout.setOnClickListener(this.mCallback92);
            }
            if ((dirtyFlags & 768) != 0) {
                this.imageFrameLayout.setOnFocusChangeListener(mediaViewModelMusicViewFocusChangeListener);
            }
            if ((dirtyFlags & 513) != 0) {
                BaseBindingModel.setAlbumIcon(this.imageView6, mediaViewModelMediaInfoPicGet);
                BaseBindingModel.setMusicAlbumIcon(this.musicImageView, mediaViewModelMediaInfoPicGet);
            }
            if ((dirtyFlags & 516) != 0) {
                TextViewBindingAdapter.setText(this.nameTextView, mediaViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName);
            }
            if ((dirtyFlags & 640) != 0) {
                this.seekBar.setMax(mediaViewModelMediaInfoMaxProgressGet2);
            }
            if ((dirtyFlags & 544) != 0) {
                SeekBarBindingAdapter.setProgress(this.seekBar, mediaViewModelMediaInfoProgressGet);
            }
            if ((dirtyFlags & 528) == 0) {
                TextViewBindingAdapter.setText(this.totalTimeTextView, mediaViewModelMediaInfoMusicAlbumGet);
                return;
            }
            return;
        }
        mediaViewModelMusicViewFocusChangeListener = null;
        if ((dirtyFlags & 520) == 0) {
        }
        if ((dirtyFlags & 514) != 0) {
        }
        if ((dirtyFlags & 516) != 0) {
        }
        if ((dirtyFlags & 514) != 0) {
        }
        if ((dirtyFlags & 520) != 0) {
        }
        if ((dirtyFlags & 576) != 0) {
        }
        if ((dirtyFlags & 512) == 0) {
        }
        if ((dirtyFlags & 768) != 0) {
        }
        if ((dirtyFlags & 513) != 0) {
        }
        if ((dirtyFlags & 516) != 0) {
        }
        if ((dirtyFlags & 640) != 0) {
        }
        if ((dirtyFlags & 544) != 0) {
        }
        if ((dirtyFlags & 528) == 0) {
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

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
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;

public class AlsId7SubMusicViewBindingSw600dpLandImpl extends AlsId7SubMusicViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback142;
    @Nullable
    private final View.OnClickListener mCallback143;
    @Nullable
    private final View.OnClickListener mCallback144;
    @Nullable
    private final View.OnClickListener mCallback145;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.music_title, 12);
        sViewsWithIds.put(R.id.music_sub_title, 13);
    }

    public AlsId7SubMusicViewBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private AlsId7SubMusicViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, bindings[9], bindings[8], bindings[6], bindings[5], bindings[4], bindings[10], bindings[1], bindings[2], bindings[0], bindings[13], bindings[12], bindings[7], bindings[3], bindings[11]);
        this.mDirtyFlags = -1;
        this.albumTextView.setTag((Object) null);
        this.artistTextView.setTag((Object) null);
        this.btnMusicNext.setTag((Object) null);
        this.btnMusicPause.setTag((Object) null);
        this.btnMusicPrev.setTag((Object) null);
        this.currentTimeTextView.setTag((Object) null);
        this.imageFrameLayout.setTag((Object) null);
        this.musicAlbumBg.setTag((Object) null);
        this.musicConstraintLayout.setTag((Object) null);
        this.nameTextView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.totalTimeTextView.setTag((Object) null);
        setRootTag(root);
        this.mCallback144 = new OnClickListener(this, 3);
        this.mCallback145 = new OnClickListener(this, 4);
        this.mCallback142 = new OnClickListener(this, 1);
        this.mCallback143 = new OnClickListener(this, 2);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (24 != variableId) {
            return false;
        }
        setMusicPhoneViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setMusicPhoneViewModel(@Nullable AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(24);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMusicPhoneViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 1:
                return onChangeMusicPhoneViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 2:
                return onChangeMusicPhoneViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 3:
                return onChangeMusicPhoneViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 4:
                return onChangeMusicPhoneViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 5:
                return onChangeMusicPhoneViewModelMediaInfoMusicPlay((ObservableField) object, fieldId);
            case 6:
                return onChangeMusicPhoneViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 7:
                return onChangeMusicPhoneViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            case 8:
                return onChangeMusicPhoneViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoProgress(ObservableInt MusicPhoneViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMaxProgress(ObservableInt MusicPhoneViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName;
        String musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist;
        ObservableField<String> musicPhoneViewModelMediaInfoTotalTime;
        ObservableInt musicPhoneViewModelMediaInfoMaxProgress;
        ObservableField<BitmapDrawable> musicPhoneViewModelMediaInfoPic;
        ObservableField<Boolean> musicPhoneViewModelMediaInfoMusicPlay;
        long dirtyFlags2;
        ImageView imageView;
        int i;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAlbum;
        ObservableField<String> musicPhoneViewModelMediaInfoCurrentTime;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        View.OnFocusChangeListener musicPhoneViewModelMusicViewFocusChangeListener = null;
        BitmapDrawable musicPhoneViewModelMediaInfoPicGet = null;
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        String musicPhoneViewModelMediaInfoMusicAlbumGet = null;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAtist = null;
        String musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = null;
        String musicPhoneViewModelMediaInfoTotalTimeGet = null;
        ObservableInt musicPhoneViewModelMediaInfoProgress = null;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicName = null;
        MediaInfo musicPhoneViewModelMediaInfo = null;
        String musicPhoneViewModelMediaInfoMusicNameGet = null;
        ObservableField<String> musicPhoneViewModelMediaInfoCurrentTime2 = null;
        String musicPhoneViewModelMediaInfoMusicAtistGet = null;
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = null;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAlbum2 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet = false;
        int musicPhoneViewModelMediaInfoProgressGet = 0;
        String musicPhoneViewModelMediaInfoCurrentTimeGet = null;
        boolean musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        boolean musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        boolean musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        int musicPhoneViewModelMediaInfoMaxProgressGet = 0;
        Boolean musicPhoneViewModelMediaInfoMusicPlayGet = null;
        if (!((dirtyFlags & 1536) == 0 || musicPhoneViewModel == null)) {
            musicPhoneViewModelMusicViewFocusChangeListener = musicPhoneViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 1535) != 0) {
            musicPhoneViewModelMediaInfo = AlsID7ViewModel.mediaInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfo.musicAtist;
                }
                updateRegistration(0, (Observable) musicPhoneViewModelMediaInfoMusicAtist);
                if (musicPhoneViewModelMediaInfoMusicAtist != null) {
                    musicPhoneViewModelMediaInfoMusicAtistGet = musicPhoneViewModelMediaInfoMusicAtist.get();
                }
                musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 1025) != 0) {
                    dirtyFlags = musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE : dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 1026) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoProgress = musicPhoneViewModelMediaInfo.progress;
                }
                updateRegistration(1, (Observable) musicPhoneViewModelMediaInfoProgress);
                if (musicPhoneViewModelMediaInfoProgress != null) {
                    musicPhoneViewModelMediaInfoProgressGet = musicPhoneViewModelMediaInfoProgress.get();
                }
            }
            if ((dirtyFlags & 1028) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfo.musicName;
                }
                updateRegistration(2, (Observable) musicPhoneViewModelMediaInfoMusicName);
                if (musicPhoneViewModelMediaInfoMusicName != null) {
                    musicPhoneViewModelMediaInfoMusicNameGet = musicPhoneViewModelMediaInfoMusicName.get();
                }
                boolean musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull2 = musicPhoneViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 1028) != 0) {
                    if (musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull2;
            }
            if ((dirtyFlags & 1032) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoCurrentTime = musicPhoneViewModelMediaInfo.currentTime;
                } else {
                    musicPhoneViewModelMediaInfoCurrentTime = null;
                }
                updateRegistration(3, (Observable) musicPhoneViewModelMediaInfoCurrentTime);
                if (musicPhoneViewModelMediaInfoCurrentTime != null) {
                    musicPhoneViewModelMediaInfoCurrentTime2 = musicPhoneViewModelMediaInfoCurrentTime;
                    musicPhoneViewModelMediaInfoCurrentTimeGet = musicPhoneViewModelMediaInfoCurrentTime.get();
                } else {
                    musicPhoneViewModelMediaInfoCurrentTime2 = musicPhoneViewModelMediaInfoCurrentTime;
                }
            }
            if ((dirtyFlags & 1040) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicAlbum = musicPhoneViewModelMediaInfo.musicAlbum;
                } else {
                    musicPhoneViewModelMediaInfoMusicAlbum = null;
                }
                updateRegistration(4, (Observable) musicPhoneViewModelMediaInfoMusicAlbum);
                if (musicPhoneViewModelMediaInfoMusicAlbum != null) {
                    musicPhoneViewModelMediaInfoMusicAlbumGet = musicPhoneViewModelMediaInfoMusicAlbum.get();
                }
                boolean musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull2 = musicPhoneViewModelMediaInfoMusicAlbumGet == null;
                if ((dirtyFlags & 1040) != 0) {
                    if (musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                musicPhoneViewModelMediaInfoMusicAlbum2 = musicPhoneViewModelMediaInfoMusicAlbum;
                musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull2;
            }
            if ((dirtyFlags & 1056) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicPlay = musicPhoneViewModelMediaInfo.musicPlay;
                } else {
                    musicPhoneViewModelMediaInfoMusicPlay = null;
                }
                updateRegistration(5, (Observable) musicPhoneViewModelMediaInfoMusicPlay);
                if (musicPhoneViewModelMediaInfoMusicPlay != null) {
                    musicPhoneViewModelMediaInfoMusicPlayGet = musicPhoneViewModelMediaInfoMusicPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet2 = ViewDataBinding.safeUnbox(musicPhoneViewModelMediaInfoMusicPlayGet);
                if ((dirtyFlags & 1056) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet2) {
                    dirtyFlags2 = dirtyFlags;
                    imageView = this.btnMusicPause;
                    i = R.drawable.als_id7_main_music_btn_pause;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    imageView = this.btnMusicPause;
                    i = R.drawable.als_id7_main_music_btn_play;
                }
                musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = getDrawableFromResource(imageView, i);
                ObservableField<Boolean> observableField = musicPhoneViewModelMediaInfoMusicPlay;
                androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet = androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet2;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 1088) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoPic = musicPhoneViewModelMediaInfo.pic;
                } else {
                    musicPhoneViewModelMediaInfoPic = null;
                }
                updateRegistration(6, (Observable) musicPhoneViewModelMediaInfoPic);
                if (musicPhoneViewModelMediaInfoPic != null) {
                    ObservableField<BitmapDrawable> observableField2 = musicPhoneViewModelMediaInfoPic;
                    musicPhoneViewModelMediaInfoPicGet = musicPhoneViewModelMediaInfoPic.get();
                } else {
                    ObservableField<BitmapDrawable> observableField3 = musicPhoneViewModelMediaInfoPic;
                }
            }
            if ((dirtyFlags & 1152) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMaxProgress = musicPhoneViewModelMediaInfo.maxProgress;
                } else {
                    musicPhoneViewModelMediaInfoMaxProgress = null;
                }
                updateRegistration(7, (Observable) musicPhoneViewModelMediaInfoMaxProgress);
                if (musicPhoneViewModelMediaInfoMaxProgress != null) {
                    ObservableInt observableInt = musicPhoneViewModelMediaInfoMaxProgress;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgress.get();
                } else {
                    ObservableInt observableInt2 = musicPhoneViewModelMediaInfoMaxProgress;
                }
            }
            if ((dirtyFlags & 1280) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoTotalTime = musicPhoneViewModelMediaInfo.totalTime;
                } else {
                    musicPhoneViewModelMediaInfoTotalTime = null;
                }
                updateRegistration(8, (Observable) musicPhoneViewModelMediaInfoTotalTime);
                if (musicPhoneViewModelMediaInfoTotalTime != null) {
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTime.get();
                }
            }
        }
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2 = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay;
        String musicPhoneViewModelMediaInfoCurrentTimeGet2 = musicPhoneViewModelMediaInfoCurrentTimeGet;
        String musicPhoneViewModelMediaInfoTotalTimeGet2 = musicPhoneViewModelMediaInfoTotalTimeGet;
        int musicPhoneViewModelMediaInfoProgressGet2 = musicPhoneViewModelMediaInfoProgressGet;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAtist2 = musicPhoneViewModelMediaInfoMusicAtist;
        int musicPhoneViewModelMediaInfoMaxProgressGet2 = musicPhoneViewModelMediaInfoMaxProgressGet;
        if ((dirtyFlags & 1040) != 0) {
            if (musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                AlsID7ViewModel alsID7ViewModel = musicPhoneViewModel;
                String str = musicPhoneViewModelMediaInfoMusicAlbumGet;
                musicPhoneViewModelMediaInfoMusicAlbumGet = this.albumTextView.getResources().getString(R.string.ksw_idf7_unkonw_album);
            } else {
                String str2 = musicPhoneViewModelMediaInfoMusicAlbumGet;
            }
            musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = musicPhoneViewModelMediaInfoMusicAlbumGet;
        } else {
            String str3 = musicPhoneViewModelMediaInfoMusicAlbumGet;
        }
        if ((dirtyFlags & 1028) != 0) {
            musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull ? this.nameTextView.getResources().getString(R.string.ksw_idf7_unkonw_soung) : musicPhoneViewModelMediaInfoMusicNameGet;
        } else {
            musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = null;
        }
        if ((dirtyFlags & 1025) != 0) {
            if (musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull) {
                ObservableField<String> observableField4 = musicPhoneViewModelMediaInfoMusicAtist2;
                musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = this.artistTextView.getResources().getString(R.string.ksw_idf7_unknow_artis);
            } else {
                musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfoMusicAtistGet;
            }
        } else {
            musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = null;
        }
        if ((dirtyFlags & 1040) != 0) {
            TextViewBindingAdapter.setText(this.albumTextView, musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags & 1025) != 0) {
            TextViewBindingAdapter.setText(this.artistTextView, musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            String str4 = musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist;
            this.btnMusicNext.setOnClickListener(this.mCallback145);
            this.btnMusicPause.setOnClickListener(this.mCallback144);
            this.btnMusicPrev.setOnClickListener(this.mCallback143);
            this.imageFrameLayout.setOnClickListener(this.mCallback142);
        }
        if ((dirtyFlags & 1056) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btnMusicPause, musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2);
        }
        if ((dirtyFlags & 1032) != 0) {
            TextViewBindingAdapter.setText(this.currentTimeTextView, musicPhoneViewModelMediaInfoCurrentTimeGet2);
        }
        if ((dirtyFlags & 1536) != 0) {
            this.imageFrameLayout.setOnFocusChangeListener(musicPhoneViewModelMusicViewFocusChangeListener);
        }
        if ((dirtyFlags & 1088) != 0) {
            BaseBindingModel.setAlsID7AlbumIcon(this.musicAlbumBg, musicPhoneViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 1028) != 0) {
            TextViewBindingAdapter.setText(this.nameTextView, musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 1152) != 0) {
            this.seekBar.setMax(musicPhoneViewModelMediaInfoMaxProgressGet2);
        }
        if ((dirtyFlags & 1026) != 0) {
            SeekBarBindingAdapter.setProgress(this.seekBar, musicPhoneViewModelMediaInfoProgressGet2);
        }
        if ((dirtyFlags & 1280) != 0) {
            TextViewBindingAdapter.setText(this.totalTimeTextView, musicPhoneViewModelMediaInfoTotalTimeGet2);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean musicPhoneViewModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel != null) {
                    musicPhoneViewModelJavaLangObjectNull = true;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AlsID7ViewModel musicPhoneViewModel2 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel2 != null) {
                    musicPhoneViewModelJavaLangObjectNull = true;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel2.btnMusicPrev(callbackArg_0);
                    return;
                }
                return;
            case 3:
                AlsID7ViewModel musicPhoneViewModel3 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel3 != null) {
                    musicPhoneViewModelJavaLangObjectNull = true;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel3.btnMusicPlayPause(callbackArg_0);
                    return;
                }
                return;
            case 4:
                AlsID7ViewModel musicPhoneViewModel4 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel4 != null) {
                    musicPhoneViewModelJavaLangObjectNull = true;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel4.btnMusicNext(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

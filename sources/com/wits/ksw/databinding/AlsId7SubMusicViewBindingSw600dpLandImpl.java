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
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;

public class AlsId7SubMusicViewBindingSw600dpLandImpl extends AlsId7SubMusicViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback44;
    private final View.OnClickListener mCallback45;
    private final View.OnClickListener mCallback46;
    private final View.OnClickListener mCallback47;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.music_title, 12);
        sparseIntArray.put(R.id.music_sub_title, 13);
    }

    public AlsId7SubMusicViewBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
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
        this.mCallback47 = new OnClickListener(this, 4);
        this.mCallback44 = new OnClickListener(this, 1);
        this.mCallback45 = new OnClickListener(this, 2);
        this.mCallback46 = new OnClickListener(this, 3);
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

    public boolean setVariable(int variableId, Object variable) {
        if (7 != variableId) {
            return false;
        }
        setMusicPhoneViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMusicPhoneViewModelMediaInfoMusicPlay((ObservableField) object, fieldId);
            case 1:
                return onChangeMusicPhoneViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 2:
                return onChangeMusicPhoneViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 3:
                return onChangeMusicPhoneViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 4:
                return onChangeMusicPhoneViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 5:
                return onChangeMusicPhoneViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 6:
                return onChangeMusicPhoneViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            case 7:
                return onChangeMusicPhoneViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            case 8:
                return onChangeMusicPhoneViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicPlay(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoProgress(ObservableInt MusicPhoneViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoCurrentTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMaxProgress(ObservableInt MusicPhoneViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoTotalTime(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
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
        int musicPhoneViewModelMediaInfoMaxProgressGet;
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay;
        String musicPhoneViewModelMediaInfoTotalTimeGet;
        String musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist;
        String musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName;
        String str;
        int musicPhoneViewModelMediaInfoProgressGet;
        int musicPhoneViewModelMediaInfoProgressGet2;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAlbum;
        ObservableField<String> musicPhoneViewModelMediaInfoTotalTime;
        ObservableInt musicPhoneViewModelMediaInfoMaxProgress;
        ObservableField<String> musicPhoneViewModelMediaInfoCurrentTime;
        ObservableField<BitmapDrawable> musicPhoneViewModelMediaInfoPic;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicName;
        ObservableInt musicPhoneViewModelMediaInfoProgress;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int musicPhoneViewModelMediaInfoProgressGet3 = 0;
        View.OnFocusChangeListener musicPhoneViewModelMusicViewFocusChangeListener = null;
        BitmapDrawable musicPhoneViewModelMediaInfoPicGet = null;
        String musicPhoneViewModelMediaInfoCurrentTimeGet = null;
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        boolean musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        String musicPhoneViewModelMediaInfoMusicAlbumGet = null;
        ObservableField<Boolean> musicPhoneViewModelMediaInfoMusicPlay = null;
        boolean musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAtist = null;
        String musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = null;
        String musicPhoneViewModelMediaInfoTotalTimeGet2 = null;
        String musicPhoneViewModelMediaInfoMusicNameGet = null;
        boolean musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        String musicPhoneViewModelMediaInfoMusicAtistGet = null;
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2 = null;
        int musicPhoneViewModelMediaInfoMaxProgressGet2 = 0;
        Boolean musicPhoneViewModelMediaInfoMusicPlayGet = null;
        if (!((dirtyFlags & 1536) == 0 || musicPhoneViewModel == null)) {
            musicPhoneViewModelMusicViewFocusChangeListener = musicPhoneViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 1535) != 0) {
            MediaInfo musicPhoneViewModelMediaInfo = AlsID7ViewModel.mediaInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicPlay = musicPhoneViewModelMediaInfo.musicPlay;
                }
                updateRegistration(0, (Observable) musicPhoneViewModelMediaInfoMusicPlay);
                if (musicPhoneViewModelMediaInfoMusicPlay != null) {
                    musicPhoneViewModelMediaInfoMusicPlayGet = musicPhoneViewModelMediaInfoMusicPlay.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet = ViewDataBinding.safeUnbox(musicPhoneViewModelMediaInfoMusicPlayGet);
                if ((dirtyFlags & 1025) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2 = androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet ? AppCompatResources.getDrawable(this.btnMusicPause.getContext(), R.drawable.als_id7_main_music_btn_pause) : AppCompatResources.getDrawable(this.btnMusicPause.getContext(), R.drawable.als_id7_main_music_btn_play);
            }
            if ((dirtyFlags & 1026) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfo.musicAtist;
                }
                updateRegistration(1, (Observable) musicPhoneViewModelMediaInfoMusicAtist);
                if (musicPhoneViewModelMediaInfoMusicAtist != null) {
                    musicPhoneViewModelMediaInfoMusicAtistGet = musicPhoneViewModelMediaInfoMusicAtist.get();
                }
                musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 1026) != 0) {
                    if (musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
            }
            if ((dirtyFlags & 1028) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoProgress = musicPhoneViewModelMediaInfo.progress;
                } else {
                    musicPhoneViewModelMediaInfoProgress = null;
                }
                updateRegistration(2, (Observable) musicPhoneViewModelMediaInfoProgress);
                if (musicPhoneViewModelMediaInfoProgress != null) {
                    ObservableInt observableInt = musicPhoneViewModelMediaInfoProgress;
                    musicPhoneViewModelMediaInfoProgressGet = musicPhoneViewModelMediaInfoProgress.get();
                } else {
                    ObservableInt observableInt2 = musicPhoneViewModelMediaInfoProgress;
                    musicPhoneViewModelMediaInfoProgressGet = 0;
                }
            } else {
                musicPhoneViewModelMediaInfoProgressGet = 0;
            }
            if ((dirtyFlags & 1032) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfo.musicName;
                } else {
                    musicPhoneViewModelMediaInfoMusicName = null;
                }
                musicPhoneViewModelMediaInfoProgressGet2 = musicPhoneViewModelMediaInfoProgressGet;
                updateRegistration(3, (Observable) musicPhoneViewModelMediaInfoMusicName);
                if (musicPhoneViewModelMediaInfoMusicName != null) {
                    musicPhoneViewModelMediaInfoMusicNameGet = musicPhoneViewModelMediaInfoMusicName.get();
                }
                musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 1032) == 0) {
                    ObservableField<String> observableField = musicPhoneViewModelMediaInfoMusicName;
                } else if (musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    ObservableField<String> observableField2 = musicPhoneViewModelMediaInfoMusicName;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    ObservableField<String> observableField3 = musicPhoneViewModelMediaInfoMusicName;
                }
            } else {
                musicPhoneViewModelMediaInfoProgressGet2 = musicPhoneViewModelMediaInfoProgressGet;
            }
            if ((dirtyFlags & 1040) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoPic = musicPhoneViewModelMediaInfo.pic;
                } else {
                    musicPhoneViewModelMediaInfoPic = null;
                }
                updateRegistration(4, (Observable) musicPhoneViewModelMediaInfoPic);
                if (musicPhoneViewModelMediaInfoPic != null) {
                    musicPhoneViewModelMediaInfoPicGet = musicPhoneViewModelMediaInfoPic.get();
                    ObservableField<BitmapDrawable> observableField4 = musicPhoneViewModelMediaInfoPic;
                } else {
                    ObservableField<BitmapDrawable> observableField5 = musicPhoneViewModelMediaInfoPic;
                }
            }
            if ((dirtyFlags & 1056) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoCurrentTime = musicPhoneViewModelMediaInfo.currentTime;
                } else {
                    musicPhoneViewModelMediaInfoCurrentTime = null;
                }
                updateRegistration(5, (Observable) musicPhoneViewModelMediaInfoCurrentTime);
                if (musicPhoneViewModelMediaInfoCurrentTime != null) {
                    musicPhoneViewModelMediaInfoCurrentTimeGet = musicPhoneViewModelMediaInfoCurrentTime.get();
                    ObservableField<String> observableField6 = musicPhoneViewModelMediaInfoCurrentTime;
                } else {
                    ObservableField<String> observableField7 = musicPhoneViewModelMediaInfoCurrentTime;
                }
            }
            if ((dirtyFlags & 1088) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMaxProgress = musicPhoneViewModelMediaInfo.maxProgress;
                } else {
                    musicPhoneViewModelMediaInfoMaxProgress = null;
                }
                updateRegistration(6, (Observable) musicPhoneViewModelMediaInfoMaxProgress);
                if (musicPhoneViewModelMediaInfoMaxProgress != null) {
                    musicPhoneViewModelMediaInfoMaxProgressGet2 = musicPhoneViewModelMediaInfoMaxProgress.get();
                    ObservableInt observableInt3 = musicPhoneViewModelMediaInfoMaxProgress;
                } else {
                    ObservableInt observableInt4 = musicPhoneViewModelMediaInfoMaxProgress;
                }
            }
            if ((dirtyFlags & 1152) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoTotalTime = musicPhoneViewModelMediaInfo.totalTime;
                } else {
                    musicPhoneViewModelMediaInfoTotalTime = null;
                }
                updateRegistration(7, (Observable) musicPhoneViewModelMediaInfoTotalTime);
                if (musicPhoneViewModelMediaInfoTotalTime != null) {
                    musicPhoneViewModelMediaInfoTotalTimeGet2 = musicPhoneViewModelMediaInfoTotalTime.get();
                    ObservableField<String> observableField8 = musicPhoneViewModelMediaInfoTotalTime;
                } else {
                    ObservableField<String> observableField9 = musicPhoneViewModelMediaInfoTotalTime;
                }
            }
            if ((dirtyFlags & 1280) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicAlbum = musicPhoneViewModelMediaInfo.musicAlbum;
                } else {
                    musicPhoneViewModelMediaInfoMusicAlbum = null;
                }
                updateRegistration(8, (Observable) musicPhoneViewModelMediaInfoMusicAlbum);
                if (musicPhoneViewModelMediaInfoMusicAlbum != null) {
                    musicPhoneViewModelMediaInfoMusicAlbumGet = musicPhoneViewModelMediaInfoMusicAlbum.get();
                }
                musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAlbumGet == null;
                if ((dirtyFlags & 1280) == 0) {
                    ObservableField<String> observableField10 = musicPhoneViewModelMediaInfoMusicAlbum;
                    MediaInfo mediaInfo = musicPhoneViewModelMediaInfo;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
                    AlsID7ViewModel alsID7ViewModel = musicPhoneViewModel;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                } else if (musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    ObservableField<String> observableField11 = musicPhoneViewModelMediaInfoMusicAlbum;
                    MediaInfo mediaInfo2 = musicPhoneViewModelMediaInfo;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
                    AlsID7ViewModel alsID7ViewModel2 = musicPhoneViewModel;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    ObservableField<String> observableField12 = musicPhoneViewModelMediaInfoMusicAlbum;
                    MediaInfo mediaInfo3 = musicPhoneViewModelMediaInfo;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
                    AlsID7ViewModel alsID7ViewModel3 = musicPhoneViewModel;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                }
            } else {
                MediaInfo mediaInfo4 = musicPhoneViewModelMediaInfo;
                musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
                AlsID7ViewModel alsID7ViewModel4 = musicPhoneViewModel;
                musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
            }
        } else {
            musicPhoneViewModelMediaInfoTotalTimeGet = null;
            musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = null;
            AlsID7ViewModel alsID7ViewModel5 = musicPhoneViewModel;
            musicPhoneViewModelMediaInfoMaxProgressGet = 0;
        }
        if ((dirtyFlags & 1280) != 0) {
            if (musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                boolean z = musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull;
                String str2 = musicPhoneViewModelMediaInfoMusicAlbumGet;
                str = this.albumTextView.getResources().getString(R.string.ksw_idf7_unkonw_album);
            } else {
                str = musicPhoneViewModelMediaInfoMusicAlbumGet;
            }
            musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = str;
        } else {
            String str3 = musicPhoneViewModelMediaInfoMusicAlbumGet;
        }
        if ((dirtyFlags & 1026) != 0) {
            musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull ? this.artistTextView.getResources().getString(R.string.ksw_idf7_unknow_artis) : musicPhoneViewModelMediaInfoMusicAtistGet;
        } else {
            musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = null;
        }
        if ((dirtyFlags & 1032) != 0) {
            if (musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull) {
                ObservableField<Boolean> observableField13 = musicPhoneViewModelMediaInfoMusicPlay;
                musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = this.nameTextView.getResources().getString(R.string.ksw_idf7_unkonw_soung);
            } else {
                musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfoMusicNameGet;
            }
        } else {
            musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = null;
        }
        if ((dirtyFlags & 1280) != 0) {
            TextViewBindingAdapter.setText(this.albumTextView, musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags & 1026) != 0) {
            TextViewBindingAdapter.setText(this.artistTextView, musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            String str4 = musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist;
            this.btnMusicNext.setOnClickListener(this.mCallback47);
            this.btnMusicPause.setOnClickListener(this.mCallback46);
            this.btnMusicPrev.setOnClickListener(this.mCallback45);
            this.imageFrameLayout.setOnClickListener(this.mCallback44);
        }
        if ((dirtyFlags & 1025) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btnMusicPause, musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay);
        }
        if ((dirtyFlags & 1056) != 0) {
            TextViewBindingAdapter.setText(this.currentTimeTextView, musicPhoneViewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 1536) != 0) {
            this.imageFrameLayout.setOnFocusChangeListener(musicPhoneViewModelMusicViewFocusChangeListener);
        }
        if ((dirtyFlags & 1040) != 0) {
            BaseBindingModel.setAlsID7AlbumIcon(this.musicAlbumBg, musicPhoneViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 1032) != 0) {
            TextViewBindingAdapter.setText(this.nameTextView, musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 1088) != 0) {
            this.seekBar.setMax(musicPhoneViewModelMediaInfoMaxProgressGet);
        }
        if ((dirtyFlags & 1028) != 0) {
            SeekBarBindingAdapter.setProgress(this.seekBar, musicPhoneViewModelMediaInfoProgressGet3);
        }
        if ((dirtyFlags & 1152) != 0) {
            TextViewBindingAdapter.setText(this.totalTimeTextView, musicPhoneViewModelMediaInfoTotalTimeGet);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean musicPhoneViewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel.openMusic(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AlsID7ViewModel musicPhoneViewModel2 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel2 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel2.btnMusicPrev(callbackArg_0);
                    return;
                }
                return;
            case 3:
                AlsID7ViewModel musicPhoneViewModel3 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel3 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
                }
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel3.btnMusicPlayPause(callbackArg_0);
                    return;
                }
                return;
            case 4:
                AlsID7ViewModel musicPhoneViewModel4 = this.mMusicPhoneViewModel;
                if (musicPhoneViewModel4 == null) {
                    musicPhoneViewModelJavaLangObjectNull = false;
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

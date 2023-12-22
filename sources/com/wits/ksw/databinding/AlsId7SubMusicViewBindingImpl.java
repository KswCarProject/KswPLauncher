package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.als_id7.view.MusicSeekBar;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

/* loaded from: classes7.dex */
public class AlsId7SubMusicViewBindingImpl extends AlsId7SubMusicViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback174;
    private final View.OnClickListener mCallback175;
    private final View.OnClickListener mCallback176;
    private final View.OnClickListener mCallback177;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.music_title, 12);
        sparseIntArray.put(C0899R.C0901id.music_sub_title, 13);
    }

    public AlsId7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private AlsId7SubMusicViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (TextView) bindings[9], (TextView) bindings[8], (ImageView) bindings[6], (ImageView) bindings[5], (ImageView) bindings[4], (TextView) bindings[10], (CustomBmwMusicLayout) bindings[1], (ImageView) bindings[2], (ConstraintLayout) bindings[0], (TextView) bindings[13], (TextView) bindings[12], (TextView) bindings[7], (MusicSeekBar) bindings[3], (TextView) bindings[11]);
        this.mDirtyFlags = -1L;
        this.albumTextView.setTag(null);
        this.artistTextView.setTag(null);
        this.btnMusicNext.setTag(null);
        this.btnMusicPause.setTag(null);
        this.btnMusicPrev.setTag(null);
        this.currentTimeTextView.setTag(null);
        this.imageFrameLayout.setTag(null);
        this.musicAlbumBg.setTag(null);
        this.musicConstraintLayout.setTag(null);
        this.nameTextView.setTag(null);
        this.seekBar.setTag(null);
        this.totalTimeTextView.setTag(null);
        setRootTag(root);
        this.mCallback176 = new OnClickListener(this, 3);
        this.mCallback177 = new OnClickListener(this, 4);
        this.mCallback174 = new OnClickListener(this, 1);
        this.mCallback175 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        if (12 == variableId) {
            setMusicPhoneViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubMusicViewBinding
    public void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel) {
        this.mMusicPhoneViewModel = MusicPhoneViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
            case 9:
                return onChangeMusicPhoneViewModelBThirdMusic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicPlay(ObservableField<Boolean> MusicPhoneViewModelMediaInfoMusicPlay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAtist(ObservableField<String> MusicPhoneViewModelMediaInfoMusicAtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoProgress(ObservableInt MusicPhoneViewModelMediaInfoProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicName(ObservableField<String> MusicPhoneViewModelMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoPic(ObservableField<BitmapDrawable> MusicPhoneViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoCurrentTime(ObservableField<String> MusicPhoneViewModelMediaInfoCurrentTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMaxProgress(ObservableInt MusicPhoneViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoTotalTime(ObservableField<String> MusicPhoneViewModelMediaInfoTotalTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelMediaInfoMusicAlbum(ObservableField<String> MusicPhoneViewModelMediaInfoMusicAlbum, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMusicPhoneViewModelBThirdMusic(ObservableField<Boolean> MusicPhoneViewModelBThirdMusic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String musicPhoneViewModelMediaInfoTotalTimeGet;
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay;
        int musicPhoneViewModelMediaInfoMaxProgressGet;
        String musicPhoneViewModelMediaInfoMusicAlbumGet;
        Drawable musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector;
        int musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE;
        String musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum;
        String musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist;
        String musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName;
        long dirtyFlags2;
        Drawable drawable;
        int musicPhoneViewModelMediaInfoProgressGet;
        int musicPhoneViewModelMediaInfoProgressGet2;
        int musicPhoneViewModelMediaInfoProgressGet3;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAlbum;
        ObservableField<String> musicPhoneViewModelMediaInfoTotalTime;
        ObservableInt musicPhoneViewModelMediaInfoMaxProgress;
        ObservableField<String> musicPhoneViewModelMediaInfoCurrentTime;
        ObservableField<BitmapDrawable> musicPhoneViewModelMediaInfoPic;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicName;
        ObservableInt musicPhoneViewModelMediaInfoProgress;
        Drawable drawable2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int musicPhoneViewModelMediaInfoProgressGet4 = 0;
        View.OnFocusChangeListener musicPhoneViewModelMusicViewFocusChangeListener = null;
        BitmapDrawable musicPhoneViewModelMediaInfoPicGet = null;
        String musicPhoneViewModelMediaInfoCurrentTimeGet = null;
        AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
        boolean musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        String musicPhoneViewModelMediaInfoMusicAlbumGet2 = null;
        ObservableField<Boolean> musicPhoneViewModelMediaInfoMusicPlay = null;
        Boolean musicPhoneViewModelBThirdMusicGet = null;
        boolean musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        ObservableField<String> musicPhoneViewModelMediaInfoMusicAtist = null;
        String musicPhoneViewModelMediaInfoTotalTimeGet2 = null;
        String musicPhoneViewModelMediaInfoMusicNameGet = null;
        boolean musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        String musicPhoneViewModelMediaInfoMusicAtistGet = null;
        Drawable musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2 = null;
        int musicPhoneViewModelMediaInfoMaxProgressGet2 = 0;
        Boolean musicPhoneViewModelMediaInfoMusicPlayGet = null;
        if ((dirtyFlags & 3072) != 0 && musicPhoneViewModel != null) {
            musicPhoneViewModelMusicViewFocusChangeListener = musicPhoneViewModel.musicViewFocusChangeListener;
        }
        if ((dirtyFlags & 2559) == 0) {
            musicPhoneViewModelMediaInfoTotalTimeGet = null;
            musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = null;
            musicPhoneViewModelMediaInfoMaxProgressGet = 0;
        } else {
            MediaInfo musicPhoneViewModelMediaInfo = AlsID7ViewModel.mediaInfo;
            if ((dirtyFlags & 2049) == 0) {
                musicPhoneViewModelMediaInfoProgressGet = 0;
            } else {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicPlay = musicPhoneViewModelMediaInfo.musicPlay;
                }
                updateRegistration(0, musicPhoneViewModelMediaInfoMusicPlay);
                if (musicPhoneViewModelMediaInfoMusicPlay != null) {
                    Boolean musicPhoneViewModelMediaInfoMusicPlayGet2 = musicPhoneViewModelMediaInfoMusicPlay.get();
                    musicPhoneViewModelMediaInfoMusicPlayGet = musicPhoneViewModelMediaInfoMusicPlayGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet = ViewDataBinding.safeUnbox(musicPhoneViewModelMediaInfoMusicPlayGet);
                if ((dirtyFlags & 2049) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelMediaInfoMusicPlayGet) {
                    musicPhoneViewModelMediaInfoProgressGet = 0;
                    drawable2 = AppCompatResources.getDrawable(this.btnMusicPause.getContext(), C0899R.C0900drawable.als_id7_main_music_btn_pause);
                } else {
                    musicPhoneViewModelMediaInfoProgressGet = 0;
                    drawable2 = AppCompatResources.getDrawable(this.btnMusicPause.getContext(), C0899R.C0900drawable.als_id7_main_music_btn_play);
                }
                musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2 = drawable2;
            }
            if ((dirtyFlags & 2050) != 0) {
                if (musicPhoneViewModelMediaInfo != null) {
                    musicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfo.musicAtist;
                }
                updateRegistration(1, musicPhoneViewModelMediaInfoMusicAtist);
                if (musicPhoneViewModelMediaInfoMusicAtist != null) {
                    musicPhoneViewModelMediaInfoMusicAtistGet = musicPhoneViewModelMediaInfoMusicAtist.get();
                }
                musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 2050) != 0) {
                    if (musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    }
                }
            }
            if ((dirtyFlags & 2052) == 0) {
                musicPhoneViewModelMediaInfoProgressGet2 = musicPhoneViewModelMediaInfoProgressGet;
            } else {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoProgress = null;
                } else {
                    musicPhoneViewModelMediaInfoProgress = musicPhoneViewModelMediaInfo.progress;
                }
                updateRegistration(2, musicPhoneViewModelMediaInfoProgress);
                musicPhoneViewModelMediaInfoProgressGet2 = musicPhoneViewModelMediaInfoProgress != null ? musicPhoneViewModelMediaInfoProgress.get() : musicPhoneViewModelMediaInfoProgressGet;
            }
            if ((dirtyFlags & 2056) == 0) {
                musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
            } else {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoMusicName = null;
                } else {
                    musicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfo.musicName;
                }
                musicPhoneViewModelMediaInfoProgressGet3 = musicPhoneViewModelMediaInfoProgressGet2;
                updateRegistration(3, musicPhoneViewModelMediaInfoMusicName);
                if (musicPhoneViewModelMediaInfoMusicName != null) {
                    musicPhoneViewModelMediaInfoMusicNameGet = musicPhoneViewModelMediaInfoMusicName.get();
                }
                musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 2056) != 0) {
                    if (musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    }
                }
            }
            if ((dirtyFlags & 2064) != 0) {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoPic = null;
                } else {
                    musicPhoneViewModelMediaInfoPic = musicPhoneViewModelMediaInfo.pic;
                }
                updateRegistration(4, musicPhoneViewModelMediaInfoPic);
                if (musicPhoneViewModelMediaInfoPic != null) {
                    musicPhoneViewModelMediaInfoPicGet = musicPhoneViewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 2080) != 0) {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoCurrentTime = null;
                } else {
                    musicPhoneViewModelMediaInfoCurrentTime = musicPhoneViewModelMediaInfo.currentTime;
                }
                updateRegistration(5, musicPhoneViewModelMediaInfoCurrentTime);
                if (musicPhoneViewModelMediaInfoCurrentTime != null) {
                    musicPhoneViewModelMediaInfoCurrentTimeGet = musicPhoneViewModelMediaInfoCurrentTime.get();
                }
            }
            if ((dirtyFlags & 2112) != 0) {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoMaxProgress = null;
                } else {
                    musicPhoneViewModelMediaInfoMaxProgress = musicPhoneViewModelMediaInfo.maxProgress;
                }
                updateRegistration(6, musicPhoneViewModelMediaInfoMaxProgress);
                if (musicPhoneViewModelMediaInfoMaxProgress != null) {
                    musicPhoneViewModelMediaInfoMaxProgressGet2 = musicPhoneViewModelMediaInfoMaxProgress.get();
                }
            }
            if ((dirtyFlags & 2176) != 0) {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoTotalTime = null;
                } else {
                    musicPhoneViewModelMediaInfoTotalTime = musicPhoneViewModelMediaInfo.totalTime;
                }
                updateRegistration(7, musicPhoneViewModelMediaInfoTotalTime);
                if (musicPhoneViewModelMediaInfoTotalTime != null) {
                    musicPhoneViewModelMediaInfoTotalTimeGet2 = musicPhoneViewModelMediaInfoTotalTime.get();
                }
            }
            if ((dirtyFlags & 2304) != 0) {
                if (musicPhoneViewModelMediaInfo == null) {
                    musicPhoneViewModelMediaInfoMusicAlbum = null;
                } else {
                    musicPhoneViewModelMediaInfoMusicAlbum = musicPhoneViewModelMediaInfo.musicAlbum;
                }
                updateRegistration(8, musicPhoneViewModelMediaInfoMusicAlbum);
                if (musicPhoneViewModelMediaInfoMusicAlbum != null) {
                    musicPhoneViewModelMediaInfoMusicAlbumGet2 = musicPhoneViewModelMediaInfoMusicAlbum.get();
                }
                musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull = musicPhoneViewModelMediaInfoMusicAlbumGet2 == null;
                if ((dirtyFlags & 2304) == 0) {
                    musicPhoneViewModelMediaInfoProgressGet4 = musicPhoneViewModelMediaInfoProgressGet3;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                } else if (musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    musicPhoneViewModelMediaInfoProgressGet4 = musicPhoneViewModelMediaInfoProgressGet3;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    musicPhoneViewModelMediaInfoProgressGet4 = musicPhoneViewModelMediaInfoProgressGet3;
                    musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                    musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                    musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
                }
            } else {
                musicPhoneViewModelMediaInfoProgressGet4 = musicPhoneViewModelMediaInfoProgressGet3;
                musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay = musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay2;
                musicPhoneViewModelMediaInfoTotalTimeGet = musicPhoneViewModelMediaInfoTotalTimeGet2;
                musicPhoneViewModelMediaInfoMaxProgressGet = musicPhoneViewModelMediaInfoMaxProgressGet2;
            }
        }
        if ((dirtyFlags & 2560) == 0) {
            musicPhoneViewModelMediaInfoMusicAlbumGet = musicPhoneViewModelMediaInfoMusicAlbumGet2;
            musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector = null;
            musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        } else {
            musicPhoneViewModelMediaInfoMusicAlbumGet = musicPhoneViewModelMediaInfoMusicAlbumGet2;
            ObservableField<Boolean> musicPhoneViewModelBThirdMusic = AlsID7ViewModel.bThirdMusic;
            updateRegistration(9, musicPhoneViewModelBThirdMusic);
            if (musicPhoneViewModelBThirdMusic != null) {
                musicPhoneViewModelBThirdMusicGet = musicPhoneViewModelBThirdMusic.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelBThirdMusicGet = ViewDataBinding.safeUnbox(musicPhoneViewModelBThirdMusicGet);
            boolean musicPhoneViewModelBThirdMusicBooleanTrue = androidDatabindingViewDataBindingSafeUnboxMusicPhoneViewModelBThirdMusicGet;
            if ((dirtyFlags & 2560) != 0) {
                if (musicPhoneViewModelBThirdMusicBooleanTrue) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE | 8388608;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED | 4194304;
                }
            }
            if (musicPhoneViewModelBThirdMusicBooleanTrue) {
                dirtyFlags2 = dirtyFlags;
                drawable = AppCompatResources.getDrawable(this.imageFrameLayout.getContext(), C0899R.C0900drawable.als_id7_main_music_item_selector2);
            } else {
                dirtyFlags2 = dirtyFlags;
                drawable = AppCompatResources.getDrawable(this.imageFrameLayout.getContext(), C0899R.C0900drawable.als_id7_main_music_item_selector);
            }
            Drawable musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2 = drawable;
            int musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE2 = musicPhoneViewModelBThirdMusicBooleanTrue ? 8 : 0;
            musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector = musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2;
            musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE2;
            dirtyFlags = dirtyFlags2;
        }
        if ((dirtyFlags & 2304) == 0) {
            musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = null;
        } else {
            musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum = musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNull ? this.albumTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_album) : musicPhoneViewModelMediaInfoMusicAlbumGet;
        }
        if ((dirtyFlags & 2050) == 0) {
            musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = null;
        } else {
            musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist = musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNull ? this.artistTextView.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : musicPhoneViewModelMediaInfoMusicAtistGet;
        }
        if ((dirtyFlags & 2056) == 0) {
            musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = null;
        } else {
            musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName = musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNull ? this.nameTextView.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : musicPhoneViewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 2304) != 0) {
            TextViewBindingAdapter.setText(this.albumTextView, musicPhoneViewModelMediaInfoMusicAlbumJavaLangObjectNullAlbumTextViewAndroidStringKswIdf7UnkonwAlbumMusicPhoneViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags & 2560) != 0) {
            this.albumTextView.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.artistTextView.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.btnMusicNext.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.btnMusicPause.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.btnMusicPrev.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.currentTimeTextView.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            ViewBindingAdapter.setBackground(this.imageFrameLayout, musicPhoneViewModelBThirdMusicBooleanTrueImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector2ImageFrameLayoutAndroidDrawableAlsId7MainMusicItemSelector);
            this.musicAlbumBg.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.nameTextView.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.seekBar.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.totalTimeTextView.setVisibility(musicPhoneViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 2050) != 0) {
            TextViewBindingAdapter.setText(this.artistTextView, musicPhoneViewModelMediaInfoMusicAtistJavaLangObjectNullArtistTextViewAndroidStringKswIdf7UnknowArtisMusicPhoneViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0) {
            this.btnMusicNext.setOnClickListener(this.mCallback177);
            this.btnMusicPause.setOnClickListener(this.mCallback176);
            this.btnMusicPrev.setOnClickListener(this.mCallback175);
            this.imageFrameLayout.setOnClickListener(this.mCallback174);
        }
        if ((dirtyFlags & 2049) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btnMusicPause, musicPhoneViewModelMediaInfoMusicPlayBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPauseBtnMusicPauseAndroidDrawableAlsId7MainMusicBtnPlay);
        }
        if ((dirtyFlags & 2080) != 0) {
            TextViewBindingAdapter.setText(this.currentTimeTextView, musicPhoneViewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 3072) != 0) {
            this.imageFrameLayout.setOnFocusChangeListener(musicPhoneViewModelMusicViewFocusChangeListener);
        }
        if ((dirtyFlags & 2064) != 0) {
            BaseBindingModel.setAlsID7AlbumIcon(this.musicAlbumBg, musicPhoneViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 2056) != 0) {
            TextViewBindingAdapter.setText(this.nameTextView, musicPhoneViewModelMediaInfoMusicNameJavaLangObjectNullNameTextViewAndroidStringKswIdf7UnkonwSoungMusicPhoneViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 2112) != 0) {
            this.seekBar.setMax(musicPhoneViewModelMediaInfoMaxProgressGet);
        }
        if ((dirtyFlags & 2052) != 0) {
            SeekBarBindingAdapter.setProgress(this.seekBar, musicPhoneViewModelMediaInfoProgressGet4);
        }
        if ((dirtyFlags & 2176) != 0) {
            TextViewBindingAdapter.setText(this.totalTimeTextView, musicPhoneViewModelMediaInfoTotalTimeGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean musicPhoneViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                AlsID7ViewModel musicPhoneViewModel = this.mMusicPhoneViewModel;
                musicPhoneViewModelJavaLangObjectNull = musicPhoneViewModel != null;
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                AlsID7ViewModel musicPhoneViewModel2 = this.mMusicPhoneViewModel;
                musicPhoneViewModelJavaLangObjectNull = musicPhoneViewModel2 != null;
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel2.btnMusicPrev(callbackArg_0);
                    return;
                }
                return;
            case 3:
                AlsID7ViewModel musicPhoneViewModel3 = this.mMusicPhoneViewModel;
                musicPhoneViewModelJavaLangObjectNull = musicPhoneViewModel3 != null;
                if (musicPhoneViewModelJavaLangObjectNull) {
                    musicPhoneViewModel3.btnMusicPlayPause(callbackArg_0);
                    return;
                }
                return;
            case 4:
                AlsID7ViewModel musicPhoneViewModel4 = this.mMusicPhoneViewModel;
                musicPhoneViewModelJavaLangObjectNull = musicPhoneViewModel4 != null;
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

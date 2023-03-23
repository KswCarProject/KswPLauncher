package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class MusicEditorDataBindingImpl extends MusicEditorDataBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout, 5);
    }

    public MusicEditorDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private MusicEditorDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[3], bindings[5], bindings[1], bindings[2]);
        this.mDirtyFlags = -1;
        this.ivMainIconMusic.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag((Object) null);
        this.tvSinger.setTag((Object) null);
        this.tvSongTitle.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
                return onChangeMediaViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 2:
                return onChangeMediaViewModelBThirdMusic((ObservableField) object, fieldId);
            case 3:
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

    private boolean onChangeMediaViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMediaViewModelBThirdMusic(ObservableField<Boolean> observableField, int fieldId) {
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String mediaViewModelMediaInfoMusicAtistGet;
        String str;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Boolean mediaViewModelBThirdMusicGet = null;
        ObservableField<BitmapDrawable> mediaViewModelMediaInfoPic = null;
        String mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = null;
        ObservableField<String> mediaViewModelMediaInfoMusicName = null;
        ObservableField<String> mediaViewModelMediaInfoMusicAtist = null;
        String mediaViewModelMediaInfoMusicNameGet = null;
        int mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        BitmapDrawable mediaViewModelMediaInfoPicGet = null;
        boolean mediaViewModelMediaInfoMusicNameJavaLangObjectNull = false;
        if ((dirtyFlags & 43) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 33) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoPic = mediaViewModelMediaInfo.pic;
                }
                updateRegistration(0, (Observable) mediaViewModelMediaInfoPic);
                if (mediaViewModelMediaInfoPic != null) {
                    mediaViewModelMediaInfoPicGet = mediaViewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 34) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicName = mediaViewModelMediaInfo.musicName;
                }
                updateRegistration(1, (Observable) mediaViewModelMediaInfoMusicName);
                if (mediaViewModelMediaInfoMusicName != null) {
                    mediaViewModelMediaInfoMusicNameGet = mediaViewModelMediaInfoMusicName.get();
                }
                mediaViewModelMediaInfoMusicNameJavaLangObjectNull = mediaViewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 34) != 0) {
                    dirtyFlags = mediaViewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 128 : dirtyFlags | 64;
                }
            }
            if ((dirtyFlags & 40) != 0) {
                if (mediaViewModelMediaInfo != null) {
                    mediaViewModelMediaInfoMusicAtist = mediaViewModelMediaInfo.musicAtist;
                }
                updateRegistration(3, (Observable) mediaViewModelMediaInfoMusicAtist);
                if (mediaViewModelMediaInfoMusicAtist != null) {
                    mediaViewModelMediaInfoMusicAtistGet = mediaViewModelMediaInfoMusicAtist.get();
                } else {
                    mediaViewModelMediaInfoMusicAtistGet = null;
                }
            } else {
                mediaViewModelMediaInfoMusicAtistGet = null;
            }
        } else {
            mediaViewModelMediaInfoMusicAtistGet = null;
        }
        if ((dirtyFlags & 36) != 0) {
            ObservableField<Boolean> mediaViewModelBThirdMusic = LauncherViewModel.bThirdMusic;
            updateRegistration(2, (Observable) mediaViewModelBThirdMusic);
            if (mediaViewModelBThirdMusic != null) {
                mediaViewModelBThirdMusicGet = mediaViewModelBThirdMusic.get();
            }
            boolean z = true;
            if (!ViewDataBinding.safeUnbox(mediaViewModelBThirdMusicGet)) {
                z = false;
            }
            boolean mediaViewModelBThirdMusicBooleanTrue = z;
            if ((dirtyFlags & 36) != 0) {
                if (mediaViewModelBThirdMusicBooleanTrue) {
                    dirtyFlags |= 512;
                } else {
                    dirtyFlags |= 256;
                }
            }
            mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdMusicBooleanTrue ? 8 : 0;
        }
        if ((dirtyFlags & 34) != 0) {
            if (mediaViewModelMediaInfoMusicNameJavaLangObjectNull) {
                Boolean bool = mediaViewModelBThirdMusicGet;
                str = this.tvSongTitle.getResources().getString(R.string.ksw_idf7_unkonw_soung);
            } else {
                str = mediaViewModelMediaInfoMusicNameGet;
            }
            mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName = str;
        }
        if ((dirtyFlags & 36) != 0) {
            this.ivMainIconMusic.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView4.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSinger.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.tvSongTitle.setVisibility(mediaViewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 33) != 0) {
            BaseBindingModel.setID8AlbumIcon(this.ivMainIconMusic, mediaViewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 40) != 0) {
            TextViewBindingAdapter.setText(this.tvSinger, mediaViewModelMediaInfoMusicAtistGet);
        }
        if ((dirtyFlags & 34) != 0) {
            TextViewBindingAdapter.setText(this.tvSongTitle, mediaViewModelMediaInfoMusicNameJavaLangObjectNullTvSongTitleAndroidStringKswIdf7UnkonwSoungMediaViewModelMediaInfoMusicName);
        }
    }
}

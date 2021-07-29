package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class Benz2021FragmentTwo1920x720Impl extends Benz2021FragmentTwo {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final BenzMbuxItemView mboundView3;
    private final BenzMbuxItemView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.video_rl, 7);
        sparseIntArray.put(R.id.video_tv, 8);
        sparseIntArray.put(R.id.bt_tip, 9);
        sparseIntArray.put(R.id.iv_video1, 10);
        sparseIntArray.put(R.id.space1, 11);
        sparseIntArray.put(R.id.iv_video2, 12);
        sparseIntArray.put(R.id.music_rl, 13);
        sparseIntArray.put(R.id.music_tv, 14);
        sparseIntArray.put(R.id.iv_music1, 15);
        sparseIntArray.put(R.id.space2, 16);
        sparseIntArray.put(R.id.iv_music2, 17);
        sparseIntArray.put(R.id.car_rl, 18);
        sparseIntArray.put(R.id.set_tv, 19);
        sparseIntArray.put(R.id.set_tip, 20);
        sparseIntArray.put(R.id.iv_set1, 21);
        sparseIntArray.put(R.id.space, 22);
        sparseIntArray.put(R.id.iv_set2, 23);
    }

    public Benz2021FragmentTwo1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentTwo1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[9], bindings[18], bindings[0], bindings[15], bindings[17], bindings[21], bindings[23], bindings[10], bindings[12], bindings[2], bindings[13], bindings[5], bindings[14], bindings[6], bindings[20], bindings[19], bindings[22], bindings[11], bindings[16], bindings[1], bindings[7], bindings[8]);
        this.mDirtyFlags = -1;
        this.fragmentTwoLl.setTag((Object) null);
        BenzMbuxItemView benzMbuxItemView = bindings[3];
        this.mboundView3 = benzMbuxItemView;
        benzMbuxItemView.setTag((Object) null);
        BenzMbuxItemView benzMbuxItemView2 = bindings[4];
        this.mboundView4 = benzMbuxItemView2;
        benzMbuxItemView2.setTag((Object) null);
        this.musicItemview.setTag((Object) null);
        this.musicTip.setTag((Object) null);
        this.setItemview.setTag((Object) null);
        this.videoItemview.setTag((Object) null);
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelItemIconClass((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoPicZoom((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelItemIconClass(ObservableBoolean ViewModelItemIconClass, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoSongInfo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPicZoom(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
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
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        int viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE;
        MediaInfo viewModelMediaInfo;
        long dirtyFlags2;
        Drawable viewModelItemIconClassSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2;
        String viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo;
        BitmapDrawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2;
        ObservableField<BitmapDrawable> viewModelMediaInfoPicZoom;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        long dirtyFlags3;
        int i;
        Context context;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableBoolean viewModelItemIconClass = null;
        ObservableField<String> viewModelMediaInfoSongInfo = null;
        int viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE = 0;
        BitmapDrawable viewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        BitmapDrawable viewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        BitmapDrawable viewModelMediaInfoPicZoomGet = null;
        Drawable viewModelItemIconClassVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = null;
        BitmapDrawable viewModelMediaInfoPicGet = null;
        Drawable viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = null;
        boolean viewModelMediaInfoPicJavaLangObjectNull = false;
        boolean viewModelItemIconClassGet = false;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        BcVieModel viewModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet = null;
        if ((dirtyFlags & 63) != 0) {
            viewModelMediaInfo = BcVieModel.mediaInfo;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            if ((dirtyFlags & 34) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoSongInfo = viewModelMediaInfo.songInfo;
                }
                updateRegistration(1, (Observable) viewModelMediaInfoSongInfo);
                if (viewModelMediaInfoSongInfo != null) {
                    viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfo.get();
                }
                viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet == null;
                if ((dirtyFlags & 34) != 0) {
                    dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE : dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                }
            }
            if ((dirtyFlags & 61) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                } else {
                    viewModelMediaInfoPic = null;
                }
                MediaInfo viewModelMediaInfo2 = viewModelMediaInfo;
                updateRegistration(3, (Observable) viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    viewModelMediaInfoPicGet = viewModelMediaInfoPic.get();
                }
                viewModelMediaInfoPicJavaLangObjectNull = viewModelMediaInfoPicGet == null;
                if ((dirtyFlags & 40) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | 512 | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | 8388608;
                    } else {
                        dirtyFlags = dirtyFlags | 256 | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH | 4194304;
                    }
                }
                if ((dirtyFlags & 61) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED | 33554432;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE | 16777216;
                    }
                }
                if ((dirtyFlags & 40) != 0) {
                    viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE = viewModelMediaInfoPicJavaLangObjectNull ? 8 : 0;
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        context = this.musicItemview.getContext();
                        dirtyFlags3 = dirtyFlags;
                        i = R.drawable.transp;
                    } else {
                        dirtyFlags3 = dirtyFlags;
                        context = this.musicItemview.getContext();
                        i = R.drawable.benz_mbux_2021_album_selector;
                    }
                    viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = AppCompatResources.getDrawable(context, i);
                    ObservableField<BitmapDrawable> observableField = viewModelMediaInfoPic;
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = viewModelMediaInfoPicJavaLangObjectNull ? 0 : 8;
                    viewModelMediaInfo = viewModelMediaInfo2;
                    dirtyFlags = dirtyFlags3;
                } else {
                    ObservableField<BitmapDrawable> observableField2 = viewModelMediaInfoPic;
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
                    viewModelMediaInfo = viewModelMediaInfo2;
                }
            } else {
                viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
            }
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfo = null;
            viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 49) != 0) {
            if (viewModel != null) {
                viewModelItemIconClass = viewModel.itemIconClass;
            }
            ObservableField<String> observableField3 = viewModelMediaInfoSongInfo;
            updateRegistration(0, (Observable) viewModelItemIconClass);
            if (viewModelItemIconClass != null) {
                viewModelItemIconClassGet = viewModelItemIconClass.get();
            }
            if ((dirtyFlags & 49) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags = dirtyFlags | 128 | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    dirtyFlags = dirtyFlags | 64 | PlaybackStateCompat.ACTION_PREPARE;
                }
            }
            if ((dirtyFlags & 33554432) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if (viewModelItemIconClassGet) {
                dirtyFlags2 = dirtyFlags;
                viewModelItemIconClassSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = AppCompatResources.getDrawable(this.setItemview.getContext(), R.drawable.benz_mbux_2021_home_setting_selector1);
            } else {
                dirtyFlags2 = dirtyFlags;
                viewModelItemIconClassSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = AppCompatResources.getDrawable(this.setItemview.getContext(), R.drawable.benz_mbux_2021_home_setting_selector2);
            }
            viewModelItemIconClassVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = AppCompatResources.getDrawable(this.videoItemview.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_video_selector1 : R.drawable.benz_mbux_2021_home_video_selector2);
        } else {
            dirtyFlags2 = dirtyFlags;
            viewModelItemIconClassSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = null;
        }
        if ((dirtyFlags2 & 34) != 0) {
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = viewModelMediaInfoSongInfoJavaLangObjectNull ? this.musicTip.getResources().getString(R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoSongInfoGet;
        } else {
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        }
        if ((dirtyFlags2 & 17039360) != 0) {
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoPicZoom = viewModelMediaInfo.picZoom;
            } else {
                viewModelMediaInfoPicZoom = null;
            }
            MediaInfo mediaInfo = viewModelMediaInfo;
            updateRegistration(2, (Observable) viewModelMediaInfoPicZoom);
            if (viewModelMediaInfoPicZoom != null) {
                viewModelMediaInfoPicZoomGet = viewModelMediaInfoPicZoom.get();
                ObservableField<BitmapDrawable> observableField4 = viewModelMediaInfoPicZoom;
            } else {
                ObservableField<BitmapDrawable> observableField5 = viewModelMediaInfoPicZoom;
            }
        }
        if ((dirtyFlags2 & 34078720) != 0) {
            if (viewModel != null) {
                viewModelItemIconClass = viewModel.itemIconClass;
            }
            updateRegistration(0, (Observable) viewModelItemIconClass);
            if (viewModelItemIconClass != null) {
                viewModelItemIconClassGet = viewModelItemIconClass.get();
            }
            if ((dirtyFlags2 & 49) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags2 = dirtyFlags2 | 128 | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    dirtyFlags2 = dirtyFlags2 | 64 | PlaybackStateCompat.ACTION_PREPARE;
                }
            }
            if ((dirtyFlags2 & 33554432) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                } else {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags2 & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) != 0) {
                if (viewModelItemIconClassGet) {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                } else {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags2 & 33554432) != 0) {
                viewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = AppCompatResources.getDrawable(this.mboundView3.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_music_selector1 : R.drawable.benz_mbux_2021_home_music_selector2);
            }
            if ((dirtyFlags2 & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) != 0) {
                viewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = AppCompatResources.getDrawable(this.mboundView4.getContext(), viewModelItemIconClassGet ? R.drawable.benz_mbux_2021_home_music_selector1 : R.drawable.benz_mbux_2021_home_music_selector2);
            }
        }
        if ((dirtyFlags2 & 61) != 0) {
            BitmapDrawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2;
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        }
        if ((dirtyFlags2 & 61) != 0) {
            BcVieModel bcVieModel = viewModel;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom);
        }
        if ((dirtyFlags2 & 40) != 0) {
            this.mboundView3.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE);
            this.mboundView4.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE);
            ViewBindingAdapter.setBackground(this.musicItemview, viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector);
        }
        if ((dirtyFlags2 & 34) != 0) {
            TextViewBindingAdapter.setText(this.musicTip, viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
        if ((dirtyFlags2 & 49) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.setItemview, viewModelItemIconClassSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.videoItemview, viewModelItemIconClassVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2);
        }
    }
}

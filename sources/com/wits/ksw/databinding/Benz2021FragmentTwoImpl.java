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

public class Benz2021FragmentTwoImpl extends Benz2021FragmentTwo {
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

    public Benz2021FragmentTwoImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentTwoImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoPicZoom((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoSongInfo(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
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
        int viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE;
        String viewModelMediaInfoSongInfoGet;
        Drawable viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2;
        BitmapDrawable viewModelMediaInfoPicZoomGet;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        BitmapDrawable viewModelMediaInfoPicZoomGet2;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        ObservableField<BitmapDrawable> viewModelMediaInfoPicZoom;
        String str;
        long dirtyFlags2;
        Drawable drawable;
        String viewModelMediaInfoSongInfoGet2;
        ObservableField<String> viewModelMediaInfoSongInfo;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        long dirtyFlags3;
        int i;
        Context context;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE = 0;
        ObservableBoolean viewModelItemIconClassical = null;
        BitmapDrawable viewModelMediaInfoPicGet = null;
        Drawable viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = null;
        boolean viewModelMediaInfoPicJavaLangObjectNull = false;
        MediaInfo viewModelMediaInfo = null;
        Drawable viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = null;
        String viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        Drawable viewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        Drawable viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        boolean viewModelItemIconClassicalGet = false;
        BcVieModel viewModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet3 = null;
        if ((dirtyFlags & 63) != 0) {
            viewModelMediaInfo = BcVieModel.mediaInfo;
            if ((dirtyFlags & 33) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoSongInfo = viewModelMediaInfo.songInfo;
                } else {
                    viewModelMediaInfoSongInfo = null;
                }
                viewModelMediaInfoSongInfoGet2 = null;
                updateRegistration(0, (Observable) viewModelMediaInfoSongInfo);
                if (viewModelMediaInfoSongInfo != null) {
                    viewModelMediaInfoSongInfoGet2 = viewModelMediaInfoSongInfo.get();
                }
                viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet2 == null;
                if ((dirtyFlags & 33) != 0) {
                    dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID : dirtyFlags | PlaybackStateCompat.ACTION_PREPARE;
                }
            } else {
                viewModelMediaInfoSongInfoGet2 = null;
                viewModelMediaInfoSongInfo = null;
            }
            if ((dirtyFlags & 62) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                } else {
                    viewModelMediaInfoPic = null;
                }
                ObservableField<String> viewModelMediaInfoSongInfo2 = viewModelMediaInfoSongInfo;
                updateRegistration(3, (Observable) viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    viewModelMediaInfoPicGet = viewModelMediaInfoPic.get();
                }
                viewModelMediaInfoPicJavaLangObjectNull = viewModelMediaInfoPicGet == null;
                if ((dirtyFlags & 40) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | 128 | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | 33554432;
                    } else {
                        dirtyFlags = dirtyFlags | 64 | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | 16777216;
                    }
                }
                if ((dirtyFlags & 62) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | 512 | 8388608;
                    } else {
                        dirtyFlags = dirtyFlags | 256 | 4194304;
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
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = viewModelMediaInfoPicJavaLangObjectNull ? 0 : 8;
                    ObservableField<BitmapDrawable> observableField = viewModelMediaInfoPic;
                    ObservableField<String> observableField2 = viewModelMediaInfoSongInfo2;
                    viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
                    dirtyFlags = dirtyFlags3;
                } else {
                    ObservableField<BitmapDrawable> observableField3 = viewModelMediaInfoPic;
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
                    ObservableField<String> observableField4 = viewModelMediaInfoSongInfo2;
                    viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
                }
            } else {
                ObservableField<String> viewModelMediaInfoSongInfo3 = viewModelMediaInfoSongInfo;
                viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
                ObservableField<String> observableField5 = viewModelMediaInfoSongInfo3;
                viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
            }
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
        }
        if ((dirtyFlags & 50) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet3;
            updateRegistration(1, (Observable) viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 50) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                }
            }
            if ((dirtyFlags & 8388608) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
            }
            if ((dirtyFlags & 512) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                }
            }
            if (viewModelItemIconClassicalGet) {
                dirtyFlags2 = dirtyFlags;
                drawable = AppCompatResources.getDrawable(this.setItemview.getContext(), R.drawable.benz_mbux_2021_home_setting_selector1);
            } else {
                dirtyFlags2 = dirtyFlags;
                drawable = AppCompatResources.getDrawable(this.setItemview.getContext(), R.drawable.benz_mbux_2021_home_setting_selector2);
            }
            viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = drawable;
            viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = AppCompatResources.getDrawable(this.videoItemview.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_home_video_selector1 : R.drawable.benz_mbux_2021_home_video_selector2);
            dirtyFlags = dirtyFlags2;
        } else {
            viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet3;
            viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = null;
        }
        if ((dirtyFlags & 33) != 0) {
            if (viewModelMediaInfoSongInfoJavaLangObjectNull) {
                viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
                viewModelMediaInfoPicZoomGet = null;
                str = this.musicTip.getResources().getString(R.string.ksw_idf7_unkonw_soung);
            } else {
                viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
                viewModelMediaInfoPicZoomGet = null;
                str = viewModelMediaInfoSongInfoGet;
            }
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = str;
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfoPicZoomGet = null;
        }
        if ((4194560 & dirtyFlags) != 0) {
            if (viewModelMediaInfo != null) {
                viewModelMediaInfoPicZoom = viewModelMediaInfo.picZoom;
            } else {
                viewModelMediaInfoPicZoom = null;
            }
            updateRegistration(2, (Observable) viewModelMediaInfoPicZoom);
            if (viewModelMediaInfoPicZoom != null) {
                viewModelMediaInfoPicZoomGet2 = viewModelMediaInfoPicZoom.get();
                ObservableField<BitmapDrawable> observableField6 = viewModelMediaInfoPicZoom;
            } else {
                ObservableField<BitmapDrawable> observableField7 = viewModelMediaInfoPicZoom;
                viewModelMediaInfoPicZoomGet2 = viewModelMediaInfoPicZoomGet;
            }
        } else {
            viewModelMediaInfoPicZoomGet2 = viewModelMediaInfoPicZoomGet;
        }
        if ((dirtyFlags & 8389120) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            updateRegistration(1, (Observable) viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 50) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                }
            }
            if ((dirtyFlags & 8388608) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
            }
            if ((dirtyFlags & 512) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                }
            }
            BcVieModel bcVieModel = viewModel;
            if ((dirtyFlags & 8388608) != 0) {
                viewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = viewModelItemIconClassicalGet ? AppCompatResources.getDrawable(this.mboundView3.getContext(), R.drawable.benz_mbux_2021_home_music_selector1) : AppCompatResources.getDrawable(this.mboundView3.getContext(), R.drawable.benz_mbux_2021_home_music_selector2);
            }
            if ((dirtyFlags & 512) != 0) {
                viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = AppCompatResources.getDrawable(this.mboundView4.getContext(), viewModelItemIconClassicalGet ? R.drawable.benz_mbux_2021_home_music_selector1 : R.drawable.benz_mbux_2021_home_music_selector2);
            }
        }
        if ((dirtyFlags & 62) != 0) {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet2;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet2;
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
        }
        if ((dirtyFlags & 62) != 0) {
            Drawable drawable2 = viewModelMediaInfoPicZoomGet2;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2);
        }
        if ((dirtyFlags & 40) != 0) {
            this.mboundView3.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE);
            this.mboundView4.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE);
            ViewBindingAdapter.setBackground(this.musicItemview, viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector);
        }
        if ((dirtyFlags & 33) != 0) {
            TextViewBindingAdapter.setText(this.musicTip, viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
        if ((dirtyFlags & 50) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.setItemview, viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.videoItemview, viewModelItemIconClassicalVideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2);
        }
    }
}

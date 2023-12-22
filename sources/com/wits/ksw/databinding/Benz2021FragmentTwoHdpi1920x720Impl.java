package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public class Benz2021FragmentTwoHdpi1920x720Impl extends Benz2021FragmentTwo {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final BenzMbuxItemView mboundView3;
    private final BenzMbuxItemView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.video_rl, 7);
        sparseIntArray.put(C0899R.C0901id.video_tv, 8);
        sparseIntArray.put(C0899R.C0901id.bt_tip, 9);
        sparseIntArray.put(C0899R.C0901id.iv_video1, 10);
        sparseIntArray.put(C0899R.C0901id.space1, 11);
        sparseIntArray.put(C0899R.C0901id.iv_video2, 12);
        sparseIntArray.put(C0899R.C0901id.music_rl, 13);
        sparseIntArray.put(C0899R.C0901id.music_tv, 14);
        sparseIntArray.put(C0899R.C0901id.iv_music1, 15);
        sparseIntArray.put(C0899R.C0901id.space2, 16);
        sparseIntArray.put(C0899R.C0901id.iv_music2, 17);
        sparseIntArray.put(C0899R.C0901id.car_rl, 18);
        sparseIntArray.put(C0899R.C0901id.set_tv, 19);
        sparseIntArray.put(C0899R.C0901id.set_tip, 20);
        sparseIntArray.put(C0899R.C0901id.iv_set1, 21);
        sparseIntArray.put(C0899R.C0901id.space, 22);
        sparseIntArray.put(C0899R.C0901id.iv_set2, 23);
    }

    public Benz2021FragmentTwoHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private Benz2021FragmentTwoHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (TextView) bindings[9], (RelativeLayout) bindings[18], (LinearLayout) bindings[0], (ImageView) bindings[15], (ImageView) bindings[17], (ImageView) bindings[21], (ImageView) bindings[23], (ImageView) bindings[10], (ImageView) bindings[12], (FrameLayout) bindings[2], (RelativeLayout) bindings[13], (TextView) bindings[5], (TextView) bindings[14], (BenzMbuxItemView) bindings[6], (TextView) bindings[20], (TextView) bindings[19], (View) bindings[22], (View) bindings[11], (View) bindings[16], (BenzMbuxItemView) bindings[1], (RelativeLayout) bindings[7], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.fragmentTwoLl.setTag(null);
        BenzMbuxItemView benzMbuxItemView = (BenzMbuxItemView) bindings[3];
        this.mboundView3 = benzMbuxItemView;
        benzMbuxItemView.setTag(null);
        BenzMbuxItemView benzMbuxItemView2 = (BenzMbuxItemView) bindings[4];
        this.mboundView4 = benzMbuxItemView2;
        benzMbuxItemView2.setTag(null);
        this.musicItemview.setTag(null);
        this.musicTip.setTag(null);
        this.setItemview.setTag(null);
        this.videoItemview.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
        if (25 == variableId) {
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Benz2021FragmentTwo
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoSongInfo((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelItemIconClassical((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelIsYO((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoPicZoom((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoSongInfo(ObservableField<String> ViewModelMediaInfoSongInfo, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelItemIconClassical(ObservableBoolean ViewModelItemIconClassical, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsYO(ObservableBoolean ViewModelIsYO, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoPicZoom(ObservableField<BitmapDrawable> ViewModelMediaInfoPicZoom, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> ViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r69v0, types: [com.wits.ksw.databinding.Benz2021FragmentTwoHdpi1920x720Impl] */
    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        Drawable viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1;
        MediaInfo viewModelMediaInfo;
        int viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE;
        String viewModelMediaInfoSongInfoGet;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        String viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo;
        Drawable viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2;
        Drawable viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector12;
        Drawable viewModelItemIconClassicalViewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2;
        Drawable viewModelMediaInfoPicZoomGet;
        Drawable drawable;
        Drawable drawable2;
        ObservableBoolean viewModelIsYO;
        Drawable viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13;
        Drawable viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14;
        Context context;
        int i;
        Context context2;
        int i2;
        ObservableField<BitmapDrawable> viewModelMediaInfoPicZoom;
        ObservableField<String> viewModelMediaInfoSongInfo;
        String viewModelMediaInfoSongInfoGet2;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        long dirtyFlags2;
        Context context3;
        int i3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE = 0;
        Drawable viewModelItemIconClassicalViewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = null;
        BitmapDrawable viewModelMediaInfoPicZoomGet2 = null;
        ObservableBoolean viewModelItemIconClassical = null;
        BitmapDrawable viewModelMediaInfoPicGet = null;
        boolean viewModelIsYOGet = false;
        Drawable viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = null;
        boolean viewModelMediaInfoPicJavaLangObjectNull = false;
        BitmapDrawable viewModelItemIconClassicalViewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        Drawable viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = null;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        boolean viewModelItemIconClassicalGet = false;
        Drawable viewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1 = null;
        Drawable viewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1 = null;
        BcVieModel viewModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet3 = null;
        if ((dirtyFlags & 127) == 0) {
            viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1 = null;
            viewModelMediaInfo = null;
            viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
        } else {
            MediaInfo viewModelMediaInfo2 = BcVieModel.mediaInfo;
            if ((dirtyFlags & 65) == 0) {
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1 = null;
                viewModelMediaInfoSongInfo = null;
                viewModelMediaInfoSongInfoGet2 = null;
            } else {
                if (viewModelMediaInfo2 == null) {
                    viewModelMediaInfoSongInfo = null;
                } else {
                    viewModelMediaInfoSongInfo = viewModelMediaInfo2.songInfo;
                }
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1 = null;
                updateRegistration(0, viewModelMediaInfoSongInfo);
                if (viewModelMediaInfoSongInfo == null) {
                    viewModelMediaInfoSongInfoGet2 = null;
                } else {
                    viewModelMediaInfoSongInfoGet2 = viewModelMediaInfoSongInfo.get();
                }
                viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet2 == null;
                if ((dirtyFlags & 65) != 0) {
                    dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED : dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            if ((dirtyFlags & 126) == 0) {
                viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
                viewModelMediaInfo = viewModelMediaInfo2;
                viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
            } else {
                if (viewModelMediaInfo2 == null) {
                    viewModelMediaInfoPic = null;
                } else {
                    viewModelMediaInfoPic = viewModelMediaInfo2.pic;
                }
                updateRegistration(4, viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    BitmapDrawable viewModelMediaInfoPicGet2 = viewModelMediaInfoPic.get();
                    viewModelMediaInfoPicGet = viewModelMediaInfoPicGet2;
                }
                viewModelMediaInfoPicJavaLangObjectNull = viewModelMediaInfoPicGet == null;
                if ((dirtyFlags & 80) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH | 1073741824;
                    } else {
                        dirtyFlags = dirtyFlags | 512 | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID | 536870912;
                    }
                }
                if ((dirtyFlags & 122) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if ((dirtyFlags & 126) != 0) {
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                if ((dirtyFlags & 80) == 0) {
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = 0;
                    viewModelMediaInfo = viewModelMediaInfo2;
                    viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
                } else {
                    viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE = viewModelMediaInfoPicJavaLangObjectNull ? 8 : 0;
                    if (viewModelMediaInfoPicJavaLangObjectNull) {
                        context3 = this.musicItemview.getContext();
                        dirtyFlags2 = dirtyFlags;
                        i3 = C0899R.C0900drawable.transp;
                    } else {
                        dirtyFlags2 = dirtyFlags;
                        context3 = this.musicItemview.getContext();
                        i3 = C0899R.C0900drawable.benz_mbux_2021_album_selector;
                    }
                    viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = AppCompatResources.getDrawable(context3, i3);
                    int viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE2 = viewModelMediaInfoPicJavaLangObjectNull ? 0 : 8;
                    viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE = viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE2;
                    viewModelMediaInfo = viewModelMediaInfo2;
                    dirtyFlags = dirtyFlags2;
                    viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
                }
            }
        }
        if ((dirtyFlags & 102) == 0) {
            viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet3;
        } else {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet3;
            updateRegistration(1, viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 102) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE | 268435456;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI | 134217728;
                }
            }
            if ((dirtyFlags & 67108864) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= 4194304;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                }
            }
        }
        if ((dirtyFlags & 65) == 0) {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        } else if (viewModelMediaInfoSongInfoJavaLangObjectNull) {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = this.musicTip.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung);
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom = null;
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = viewModelMediaInfoSongInfoGet;
        }
        if ((dirtyFlags & 33556480) != 0) {
            if (viewModelMediaInfo == null) {
                viewModelMediaInfoPicZoom = null;
            } else {
                viewModelMediaInfoPicZoom = viewModelMediaInfo.picZoom;
            }
            updateRegistration(3, viewModelMediaInfoPicZoom);
            if (viewModelMediaInfoPicZoom != null) {
                viewModelMediaInfoPicZoomGet2 = viewModelMediaInfoPicZoom.get();
            }
        }
        if ((dirtyFlags & 67112960) != 0) {
            if (viewModel != null) {
                viewModelItemIconClassical = viewModel.itemIconClassical;
            }
            updateRegistration(1, viewModelItemIconClassical);
            if (viewModelItemIconClassical != null) {
                viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
            }
            if ((dirtyFlags & 102) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE | 268435456;
                } else {
                    dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI | 134217728;
                }
            }
            if ((dirtyFlags & 67108864) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0) {
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags |= 4194304;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0) {
                viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 = AppCompatResources.getDrawable(this.mboundView4.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_home_music_selector1 : C0899R.C0900drawable.benz_mbux_2021_home_music_selector2);
            }
        }
        if ((dirtyFlags & 122) == 0) {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom;
        } else {
            viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2 = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet2;
        }
        if ((dirtyFlags & 268713984) != 0) {
            if (viewModel == null) {
                viewModelIsYO = null;
            } else {
                viewModelIsYO = viewModel.isYO;
            }
            updateRegistration(2, viewModelIsYO);
            if (viewModelIsYO != null) {
                viewModelIsYOGet = viewModelIsYO.get();
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= 256;
                } else {
                    dirtyFlags |= 128;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= 16777216;
                } else {
                    dirtyFlags |= 8388608;
                }
            }
            if ((dirtyFlags & 268435456) != 0) {
                if (viewModelIsYOGet) {
                    dirtyFlags |= 4294967296L;
                } else {
                    dirtyFlags |= 2147483648L;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE) != 0) {
                if (viewModelIsYOGet) {
                    context2 = this.setItemview.getContext();
                    i2 = C0899R.C0900drawable.benz_mbux_yo_home_setting_selector1;
                } else {
                    context2 = this.setItemview.getContext();
                    i2 = C0899R.C0900drawable.benz_mbux_2021_home_setting_selector1;
                }
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13 = AppCompatResources.getDrawable(context2, i2);
            } else {
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1;
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) == 0) {
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13;
            } else {
                if (viewModelIsYOGet) {
                    context = this.mboundView3.getContext();
                    viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13;
                    i = C0899R.C0900drawable.benz_mbux_yo_home_music_selector1;
                } else {
                    viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector13;
                    context = this.mboundView3.getContext();
                    i = C0899R.C0900drawable.benz_mbux_2021_home_music_selector1;
                }
                viewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1 = AppCompatResources.getDrawable(context, i);
            }
            if ((dirtyFlags & 268435456) == 0) {
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector12 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14;
            } else {
                viewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1 = AppCompatResources.getDrawable(this.videoItemview.getContext(), viewModelIsYOGet ? C0899R.C0900drawable.benz_mbux_yo_home_video_selector1 : C0899R.C0900drawable.benz_mbux_2021_home_video_selector1);
                viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector12 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector14;
            }
        } else {
            viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector12 = viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1;
        }
        if ((dirtyFlags & 102) == 0) {
            viewModelItemIconClassicalViewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = null;
        } else {
            viewModelItemIconClassicalViewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2 = viewModelItemIconClassicalGet ? viewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector12 : AppCompatResources.getDrawable(this.setItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_setting_selector2);
            viewModelItemIconClassicalViewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2 = viewModelItemIconClassicalGet ? viewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1 : AppCompatResources.getDrawable(this.videoItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_video_selector2);
        }
        if ((dirtyFlags & 67108864) == 0) {
            viewModelMediaInfoPicZoomGet = viewModelMediaInfoPicZoomGet2;
        } else {
            if (viewModelItemIconClassicalGet) {
                viewModelMediaInfoPicZoomGet = viewModelMediaInfoPicZoomGet2;
                drawable2 = viewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1;
            } else {
                viewModelMediaInfoPicZoomGet = viewModelMediaInfoPicZoomGet2;
                drawable2 = AppCompatResources.getDrawable(this.mboundView3.getContext(), C0899R.C0900drawable.benz_mbux_2021_home_music_selector2);
            }
            viewModelItemIconClassicalViewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 = drawable2;
        }
        if ((dirtyFlags & 126) == 0) {
            drawable = null;
        } else {
            drawable = viewModelMediaInfoPicJavaLangObjectNull ? viewModelItemIconClassicalViewModelIsYOMboundView3AndroidDrawableBenzMbuxYoHomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView3AndroidDrawableBenzMbux2021HomeMusicSelector2 : viewModelMediaInfoPicZoomGet;
        }
        if ((dirtyFlags & 126) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, drawable);
        }
        if ((dirtyFlags & 80) != 0) {
            this.mboundView3.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewGONEViewVISIBLE);
            this.mboundView4.setVisibility(viewModelMediaInfoPicJavaLangObjectNullViewVISIBLEViewGONE);
            ViewBindingAdapter.setBackground(this.musicItemview, viewModelMediaInfoPicJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector);
        }
        if ((dirtyFlags & 122) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelMediaInfoPicJavaLangObjectNullViewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021HomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021HomeMusicSelector2ViewModelMediaInfoPicZoom2);
        }
        if ((dirtyFlags & 65) != 0) {
            TextViewBindingAdapter.setText(this.musicTip, viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
        if ((dirtyFlags & 102) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.setItemview, viewModelItemIconClassicalViewModelIsYOSetItemviewAndroidDrawableBenzMbuxYoHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021HomeSettingSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.videoItemview, viewModelItemIconClassicalViewModelIsYOVideoItemviewAndroidDrawableBenzMbuxYoHomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector1VideoItemviewAndroidDrawableBenzMbux2021HomeVideoSelector2);
        }
    }
}

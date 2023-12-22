package com.wits.ksw.databinding;

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
public class Benz2021KswV2FragmentOneImpl extends Benz2021KswV2FragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final BenzMbuxItemView mboundView3;
    private final BenzMbuxItemView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.navi_rl, 10);
        sparseIntArray.put(C0899R.C0901id.navi_tv, 11);
        sparseIntArray.put(C0899R.C0901id.navi_tip, 12);
        sparseIntArray.put(C0899R.C0901id.iv_navi1, 13);
        sparseIntArray.put(C0899R.C0901id.space1, 14);
        sparseIntArray.put(C0899R.C0901id.iv_navi2, 15);
        sparseIntArray.put(C0899R.C0901id.music_rl, 16);
        sparseIntArray.put(C0899R.C0901id.music_tv, 17);
        sparseIntArray.put(C0899R.C0901id.iv_music1, 18);
        sparseIntArray.put(C0899R.C0901id.space2, 19);
        sparseIntArray.put(C0899R.C0901id.iv_music2, 20);
        sparseIntArray.put(C0899R.C0901id.bt_rl, 21);
        sparseIntArray.put(C0899R.C0901id.bt_tv, 22);
        sparseIntArray.put(C0899R.C0901id.iv_bt1, 23);
        sparseIntArray.put(C0899R.C0901id.space3, 24);
        sparseIntArray.put(C0899R.C0901id.iv_bt2, 25);
        sparseIntArray.put(C0899R.C0901id.weather_rl, 26);
        sparseIntArray.put(C0899R.C0901id.weather_tv, 27);
        sparseIntArray.put(C0899R.C0901id.weather_tip, 28);
        sparseIntArray.put(C0899R.C0901id.iv_weather1, 29);
        sparseIntArray.put(C0899R.C0901id.space4, 30);
        sparseIntArray.put(C0899R.C0901id.iv_weather2, 31);
        sparseIntArray.put(C0899R.C0901id.setting_rl, 32);
        sparseIntArray.put(C0899R.C0901id.set_tv, 33);
        sparseIntArray.put(C0899R.C0901id.set_tip, 34);
        sparseIntArray.put(C0899R.C0901id.iv_set1, 35);
        sparseIntArray.put(C0899R.C0901id.space5, 36);
        sparseIntArray.put(C0899R.C0901id.iv_set2, 37);
    }

    public Benz2021KswV2FragmentOneImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }

    private Benz2021KswV2FragmentOneImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (BenzMbuxItemView) bindings[6], (RelativeLayout) bindings[21], (TextView) bindings[7], (TextView) bindings[22], (LinearLayout) bindings[0], (ImageView) bindings[23], (ImageView) bindings[25], (ImageView) bindings[18], (ImageView) bindings[20], (ImageView) bindings[13], (ImageView) bindings[15], (ImageView) bindings[35], (ImageView) bindings[37], (ImageView) bindings[29], (ImageView) bindings[31], (FrameLayout) bindings[2], (RelativeLayout) bindings[16], (TextView) bindings[5], (TextView) bindings[17], (BenzMbuxItemView) bindings[1], (RelativeLayout) bindings[10], (TextView) bindings[12], (TextView) bindings[11], (BenzMbuxItemView) bindings[9], (TextView) bindings[34], (TextView) bindings[33], (RelativeLayout) bindings[32], (View) bindings[14], (View) bindings[19], (View) bindings[24], (View) bindings[30], (View) bindings[36], (BenzMbuxItemView) bindings[8], (RelativeLayout) bindings[26], (TextView) bindings[28], (TextView) bindings[27]);
        this.mDirtyFlags = -1L;
        this.btItemview.setTag(null);
        this.btTip.setTag(null);
        this.fragmentOneLl.setTag(null);
        BenzMbuxItemView benzMbuxItemView = (BenzMbuxItemView) bindings[3];
        this.mboundView3 = benzMbuxItemView;
        benzMbuxItemView.setTag(null);
        BenzMbuxItemView benzMbuxItemView2 = (BenzMbuxItemView) bindings[4];
        this.mboundView4 = benzMbuxItemView2;
        benzMbuxItemView2.setTag(null);
        this.musicItemview.setTag(null);
        this.musicTip.setTag(null);
        this.naviItemview.setTag(null);
        this.setItemview.setTag(null);
        this.weatherItemview.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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

    @Override // com.wits.ksw.databinding.Benz2021KswV2FragmentOne
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
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
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoPicZoom((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelBtState(ObservableField<String> ViewModelBtState, int fieldId) {
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

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE;
        String viewModelMediaInfoSongInfoGet;
        Drawable viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector;
        long dirtyFlags2;
        Drawable viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2;
        Drawable viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector;
        String viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo;
        ObservableField<BitmapDrawable> viewModelMediaInfoPicZoom;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableField<String> viewModelMediaInfoSongInfo = null;
        Drawable viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector2 = null;
        Drawable viewModelItemIconClassicalBtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector1BtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector2 = null;
        BitmapDrawable viewModelMediaInfoPicZoomGet = null;
        ObservableBoolean viewModelItemIconClassical = null;
        ObservableField<String> viewModelBtState = null;
        Drawable viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector22 = null;
        int viewModelMediaInfoPicZoomJavaLangObjectNullViewVISIBLEViewGONE = 0;
        String viewModelBtStateGet = null;
        Drawable viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector22 = null;
        boolean viewModelMediaInfoSongInfoJavaLangObjectNull = false;
        boolean viewModelItemIconClassicalGet = false;
        BcVieModel viewModel = this.mViewModel;
        String viewModelMediaInfoSongInfoGet2 = null;
        if ((dirtyFlags & 41) == 0) {
            viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE = 0;
            viewModelMediaInfoSongInfoGet = null;
            viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = null;
        } else {
            MediaInfo viewModelMediaInfo = BcVieModel.mediaInfo;
            if ((dirtyFlags & 33) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoSongInfo = viewModelMediaInfo.songInfo;
                }
                updateRegistration(0, viewModelMediaInfoSongInfo);
                if (viewModelMediaInfoSongInfo != null) {
                    viewModelMediaInfoSongInfoGet2 = viewModelMediaInfoSongInfo.get();
                }
                viewModelMediaInfoSongInfoJavaLangObjectNull = viewModelMediaInfoSongInfoGet2 == null;
                if ((dirtyFlags & 33) != 0) {
                    dirtyFlags = viewModelMediaInfoSongInfoJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED : dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
            }
            if ((dirtyFlags & 40) == 0) {
                String viewModelMediaInfoSongInfoGet3 = viewModelMediaInfoSongInfoGet2;
                viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE = 0;
                viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = null;
                viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet3;
            } else {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoPicZoom = null;
                } else {
                    viewModelMediaInfoPicZoom = viewModelMediaInfo.picZoom;
                }
                String viewModelMediaInfoSongInfoGet4 = viewModelMediaInfoSongInfoGet2;
                updateRegistration(3, viewModelMediaInfoPicZoom);
                if (viewModelMediaInfoPicZoom != null) {
                    viewModelMediaInfoPicZoomGet = viewModelMediaInfoPicZoom.get();
                }
                boolean viewModelMediaInfoPicZoomJavaLangObjectNull = viewModelMediaInfoPicZoomGet == null;
                if ((dirtyFlags & 40) != 0) {
                    if (viewModelMediaInfoPicZoomJavaLangObjectNull) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | 8388608;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH | 4194304;
                    }
                }
                viewModelMediaInfoPicZoomJavaLangObjectNullViewVISIBLEViewGONE = viewModelMediaInfoPicZoomJavaLangObjectNull ? 0 : 8;
                int viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE2 = viewModelMediaInfoPicZoomJavaLangObjectNull ? 8 : 0;
                Drawable viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector2 = viewModelMediaInfoPicZoomJavaLangObjectNull ? AppCompatResources.getDrawable(this.musicItemview.getContext(), C0899R.C0900drawable.transp) : AppCompatResources.getDrawable(this.musicItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_album_selector);
                viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE = viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE2;
                viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector = viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector2;
                viewModelMediaInfoSongInfoGet = viewModelMediaInfoSongInfoGet4;
            }
        }
        if ((dirtyFlags & 54) == 0) {
            dirtyFlags2 = dirtyFlags;
            viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = null;
            viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = null;
        } else {
            if ((dirtyFlags & 50) == 0) {
                dirtyFlags2 = dirtyFlags;
                viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = null;
            } else {
                if (viewModel != null) {
                    viewModelItemIconClassical = viewModel.itemIconClassical;
                }
                updateRegistration(1, viewModelItemIconClassical);
                if (viewModelItemIconClassical != null) {
                    viewModelItemIconClassicalGet = viewModelItemIconClassical.get();
                }
                if ((dirtyFlags & 50) != 0) {
                    if (viewModelItemIconClassicalGet) {
                        dirtyFlags = dirtyFlags | 128 | 512 | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    } else {
                        dirtyFlags = dirtyFlags | 64 | 256 | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    }
                }
                if (viewModelItemIconClassicalGet) {
                    dirtyFlags2 = dirtyFlags;
                    viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = AppCompatResources.getDrawable(this.weatherItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_ksw_v2_weather_icon1_selector);
                } else {
                    dirtyFlags2 = dirtyFlags;
                    viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector = AppCompatResources.getDrawable(this.weatherItemview.getContext(), C0899R.C0900drawable.benz_mbux_2021_ksw_v2_weather_icon2_selector);
                }
                Drawable viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021KswHomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021KswHomeMusicSelector2 = AppCompatResources.getDrawable(this.mboundView4.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_music_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_music_selector2);
                Drawable viewModelItemIconClassicalBtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector1BtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector22 = AppCompatResources.getDrawable(this.btItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_bt_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_bt_selector2);
                viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector22 = AppCompatResources.getDrawable(this.naviItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_navi_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_navi_selector2);
                viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector22 = AppCompatResources.getDrawable(this.setItemview.getContext(), viewModelItemIconClassicalGet ? C0899R.C0900drawable.benz_mbux_2021_ksw_home_setting_selector1 : C0899R.C0900drawable.benz_mbux_2021_ksw_home_setting_selector2);
                viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector2 = viewModelItemIconClassicalMboundView4AndroidDrawableBenzMbux2021KswHomeMusicSelector1MboundView4AndroidDrawableBenzMbux2021KswHomeMusicSelector2;
                viewModelItemIconClassicalBtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector1BtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector2 = viewModelItemIconClassicalBtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector1BtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector22;
            }
            if ((dirtyFlags2 & 52) == 0) {
                viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector22;
            } else {
                if (viewModel != null) {
                    viewModelBtState = viewModel.btState;
                }
                updateRegistration(2, viewModelBtState);
                if (viewModelBtState == null) {
                    viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector22;
                } else {
                    viewModelBtStateGet = viewModelBtState.get();
                    viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2 = viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector22;
                }
            }
        }
        if ((dirtyFlags2 & 33) == 0) {
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = null;
        } else {
            viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo = viewModelMediaInfoSongInfoJavaLangObjectNull ? this.musicTip.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoSongInfoGet;
        }
        if ((dirtyFlags2 & 50) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btItemview, viewModelItemIconClassicalBtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector1BtItemviewAndroidDrawableBenzMbux2021KswHomeBtSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.naviItemview, viewModelItemIconClassicalNaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector1NaviItemviewAndroidDrawableBenzMbux2021KswHomeNaviSelector22);
            ImageViewBindingAdapter.setImageDrawable(this.setItemview, viewModelItemIconClassicalSetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector1SetItemviewAndroidDrawableBenzMbux2021KswHomeSettingSelector2);
            ImageViewBindingAdapter.setImageDrawable(this.weatherItemview, viewModelItemIconClassicalWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon1SelectorWeatherItemviewAndroidDrawableBenzMbux2021KswV2WeatherIcon2Selector);
        }
        if ((dirtyFlags2 & 52) != 0) {
            TextViewBindingAdapter.setText(this.btTip, viewModelBtStateGet);
        }
        if ((dirtyFlags2 & 40) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelMediaInfoPicZoomGet);
            this.mboundView3.setVisibility(viewModelMediaInfoPicZoomJavaLangObjectNullViewGONEViewVISIBLE);
            this.mboundView4.setVisibility(viewModelMediaInfoPicZoomJavaLangObjectNullViewVISIBLEViewGONE);
            ViewBindingAdapter.setBackground(this.musicItemview, viewModelMediaInfoPicZoomJavaLangObjectNullMusicItemviewAndroidDrawableTranspMusicItemviewAndroidDrawableBenzMbux2021AlbumSelector);
        }
        if ((dirtyFlags2 & 33) != 0) {
            TextViewBindingAdapter.setText(this.musicTip, viewModelMediaInfoSongInfoJavaLangObjectNullMusicTipAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoSongInfo);
        }
    }
}

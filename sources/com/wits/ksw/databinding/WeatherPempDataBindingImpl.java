package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class WeatherPempDataBindingImpl extends WeatherPempDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback52;
    private long mDirtyFlags;
    private final ImageView mboundView1;
    private final TextView mboundView13;
    private final RelativeLayout mboundView3;
    private final IncludeNearWeatherBinding mboundView31;
    private final ProgressBar mboundView5;
    private final LinearLayout mboundView6;
    private final TextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(21);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(3, new String[]{"include_near_weather"}, new int[]{16}, new int[]{C0899R.C0902layout.include_near_weather});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.tv_title, 17);
        sparseIntArray.put(C0899R.C0901id.iv_divider, 18);
        sparseIntArray.put(C0899R.C0901id.temperature, 19);
        sparseIntArray.put(C0899R.C0901id.temperature_range, 20);
    }

    public WeatherPempDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }

    private WeatherPempDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, (TextView) bindings[8], (TextView) bindings[9], (TextView) bindings[10], (ImageView) bindings[18], (ImageView) bindings[15], (ImageView) bindings[2], (RelativeLayout) bindings[0], (RelativeLayout) bindings[19], (FrameLayout) bindings[20], (TextView) bindings[11], (TextView) bindings[4], (TextView) bindings[17], (TextView) bindings[12], (TextView) bindings[14]);
        this.mDirtyFlags = -1L;
        this.btA.setTag(null);
        this.btB.setTag(null);
        this.btC.setTag(null);
        this.ivIcon.setTag(null);
        this.ivMask.setTag(null);
        this.llContainer.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[13];
        this.mboundView13 = textView;
        textView.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[3];
        this.mboundView3 = relativeLayout;
        relativeLayout.setTag(null);
        IncludeNearWeatherBinding includeNearWeatherBinding = (IncludeNearWeatherBinding) bindings[16];
        this.mboundView31 = includeNearWeatherBinding;
        setContainedBinding(includeNearWeatherBinding);
        ProgressBar progressBar = (ProgressBar) bindings[5];
        this.mboundView5 = progressBar;
        progressBar.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout;
        linearLayout.setTag(null);
        TextView textView2 = (TextView) bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag(null);
        this.temperatureTv.setTag(null);
        this.tvCity.setTag(null);
        this.unitWeather.setTag(null);
        this.weatherTv.setTag(null);
        setRootTag(root);
        this.mCallback52 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        this.mboundView31.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView31.hasPendingBindings();
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (17 == variableId) {
            setWeatherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.WeatherPempDataBinding
    public void setWeatherViewModel(LauncherViewModel WeatherViewModel) {
        this.mWeatherViewModel = WeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView31.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWeatherViewModelWeatherInfoCity((ObservableField) object, fieldId);
            case 1:
                return onChangeWeatherViewModelWeatherInfoCardDetailText1((ObservableField) object, fieldId);
            case 2:
                return onChangeWeatherViewModelWeatherInfoCardDetailText3((ObservableField) object, fieldId);
            case 3:
                return onChangeWeatherViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 4:
                return onChangeWeatherViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 5:
                return onChangeWeatherViewModelWeatherInfoId8CardDetailImg3((ObservableField) object, fieldId);
            case 6:
                return onChangeWeatherViewModelWeatherInfoId8CardDetailImg1((ObservableField) object, fieldId);
            case 7:
                return onChangeWeatherViewModelWeatherInfoId8ImageIcon((ObservableField) object, fieldId);
            case 8:
                return onChangeWeatherViewModelWeatherInfoPhrase((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoPempId8Image((ObservableField) object, fieldId);
            case 10:
                return onChangeWeatherViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 11:
                return onChangeWeatherViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 12:
                return onChangeWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 13:
                return onChangeWeatherViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 14:
                return onChangeWeatherViewModelWeatherInfoId8CardDetailImg2((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeWeatherViewModelWeatherInfoCity(ObservableField<String> WeatherViewModelWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText1(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText3(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> WeatherViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> WeatherViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg3(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardDetailImg3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg1(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardDetailImg1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8ImageIcon(ObservableField<Drawable> WeatherViewModelWeatherInfoId8ImageIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoPhrase(ObservableField<String> WeatherViewModelWeatherInfoPhrase, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoPempId8Image(ObservableField<Drawable> WeatherViewModelWeatherInfoPempId8Image, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoErrorMessage(ObservableField<String> WeatherViewModelWeatherInfoErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText2(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> WeatherViewModelWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg2(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardDetailImg2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        String weatherViewModelWeatherInfoPhraseGet;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg1Get;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
        WeatherInfo weatherViewModelWeatherInfo;
        String weatherViewModelWeatherInfoTemperatureRangeGet;
        Drawable weatherViewModelWeatherInfoId8ImageIconGet;
        Drawable weatherViewModelWeatherInfoId8ImageIconGet2;
        Drawable weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather;
        String weatherViewModelWeatherInfoTemperatureGet;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        Drawable weatherViewModelWeatherInfoPempId8ImageGet;
        String weatherViewModelWeatherInfoCardDetailText3Get;
        String weatherViewModelWeatherInfoErrorMessageGet;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg2Get;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
        String weatherViewModelWeatherInfoErrorMessageGet2;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        LauncherViewModel weatherViewModel;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather;
        Drawable weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather2;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4;
        String weatherViewModelWeatherInfoErrorMessageGet3;
        String weatherViewModelWeatherInfoErrorMessageGet4;
        ObservableField<Drawable> weatherViewModelWeatherInfoPempId8Image;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess1;
        String weatherViewModelWeatherInfoTemperatureRangeGet2;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg2;
        ObservableField<String> weatherViewModelWeatherInfoTemperatureRange;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess12;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText2;
        ObservableField<String> weatherViewModelWeatherInfoErrorMessage;
        ObservableField<String> weatherViewModelWeatherInfoPhrase;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8ImageIcon;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg1;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg3;
        ObservableField<String> weatherViewModelWeatherInfoTemperature;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String weatherViewModelWeatherInfoTemperatureRangeGet3 = null;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet3 = null;
        LauncherViewModel weatherViewModel2 = this.mWeatherViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText1 = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText3 = null;
        String weatherViewModelWeatherInfoCityGet = null;
        String weatherViewModelWeatherInfoCardDetailText1Get2 = null;
        boolean weatherViewModelWeatherInfoIsLoadSuccess = false;
        String weatherViewModelWeatherInfoTemperatureGet2 = null;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5 = 0;
        String weatherViewModelWeatherInfoCardDetailText2Get2 = null;
        String weatherViewModelWeatherInfoCardDetailText3Get2 = null;
        Drawable weatherViewModelWeatherInfoId8ImageIconGet3 = null;
        String weatherViewModelWeatherInfoErrorMessageGet5 = null;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg3Get = null;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess13 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        String weatherViewModelWeatherInfoPhraseGet2 = null;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg1Get2 = null;
        if ((dirtyFlags & 98303) == 0) {
            weatherViewModelWeatherInfoCardDetailText1Get = null;
            weatherViewModelWeatherInfoPhraseGet = null;
            weatherViewModelWeatherInfoId8CardDetailImg1Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessGet = null;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoTemperatureRangeGet = null;
            weatherViewModelWeatherInfoId8ImageIconGet = null;
            weatherViewModelWeatherInfoId8ImageIconGet2 = null;
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather = null;
            weatherViewModelWeatherInfoTemperatureGet = null;
            weatherViewModelWeatherInfoCity = null;
            weatherViewModelWeatherInfoCardDetailText2Get = null;
            weatherViewModelWeatherInfoPempId8ImageGet = null;
            weatherViewModelWeatherInfoCardDetailText3Get = null;
            weatherViewModelWeatherInfoErrorMessageGet = null;
            weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
        } else {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 65538) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(1, weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 65540) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(2, weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get2 = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 69640) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet2 = null;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet2 = null;
                updateRegistration(3, weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    Boolean weatherViewModelWeatherInfoIsInitFinishedGet2 = weatherViewModelWeatherInfoIsInitFinished.get();
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinishedGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 69640) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags = dirtyFlags | 16777216 | 67108864;
                    } else {
                        dirtyFlags = dirtyFlags | 8388608 | 33554432;
                    }
                }
                if ((dirtyFlags & 65544) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                if ((dirtyFlags & 65544) == 0) {
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                }
            }
            if ((dirtyFlags & 65552) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperature = null;
                } else {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                }
                updateRegistration(4, weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperature.get();
                }
            }
            if ((dirtyFlags & 65568) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardDetailImg3 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg3 = weatherViewModelWeatherInfo2.id8CardDetailImg3;
                }
                updateRegistration(5, weatherViewModelWeatherInfoId8CardDetailImg3);
                if (weatherViewModelWeatherInfoId8CardDetailImg3 != null) {
                    weatherViewModelWeatherInfoId8CardDetailImg3Get = weatherViewModelWeatherInfoId8CardDetailImg3.get();
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardDetailImg1 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg1 = weatherViewModelWeatherInfo2.id8CardDetailImg1;
                }
                updateRegistration(6, weatherViewModelWeatherInfoId8CardDetailImg1);
                if (weatherViewModelWeatherInfoId8CardDetailImg1 != null) {
                    weatherViewModelWeatherInfoId8CardDetailImg1Get2 = weatherViewModelWeatherInfoId8CardDetailImg1.get();
                }
            }
            if ((dirtyFlags & 65664) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8ImageIcon = null;
                } else {
                    weatherViewModelWeatherInfoId8ImageIcon = weatherViewModelWeatherInfo2.id8ImageIcon;
                }
                updateRegistration(7, weatherViewModelWeatherInfoId8ImageIcon);
                if (weatherViewModelWeatherInfoId8ImageIcon != null) {
                    weatherViewModelWeatherInfoId8ImageIconGet3 = weatherViewModelWeatherInfoId8ImageIcon.get();
                }
            }
            if ((dirtyFlags & 65792) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoPhrase = null;
                } else {
                    weatherViewModelWeatherInfoPhrase = weatherViewModelWeatherInfo2.phrase;
                }
                updateRegistration(8, weatherViewModelWeatherInfoPhrase);
                if (weatherViewModelWeatherInfoPhrase != null) {
                    weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhrase.get();
                }
            }
            if ((dirtyFlags & 66560) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoErrorMessage = null;
                } else {
                    weatherViewModelWeatherInfoErrorMessage = weatherViewModelWeatherInfo2.errorMessage;
                }
                updateRegistration(10, weatherViewModelWeatherInfoErrorMessage);
                if (weatherViewModelWeatherInfoErrorMessage != null) {
                    weatherViewModelWeatherInfoErrorMessageGet5 = weatherViewModelWeatherInfoErrorMessage.get();
                }
            }
            if ((dirtyFlags & 67584) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoCardDetailText2 = null;
                } else {
                    weatherViewModelWeatherInfoCardDetailText2 = weatherViewModelWeatherInfo2.cardDetailText2;
                }
                updateRegistration(11, weatherViewModelWeatherInfoCardDetailText2);
                if (weatherViewModelWeatherInfoCardDetailText2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2.get();
                }
            }
            if ((dirtyFlags & 70145) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = null;
                } else {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = weatherViewModelWeatherInfo2.isLoadSuccess;
                }
                updateRegistration(12, WeatherViewModelWeatherInfoIsLoadSuccess12);
                if (WeatherViewModelWeatherInfoIsLoadSuccess12 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccessGet3 = WeatherViewModelWeatherInfoIsLoadSuccess12.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet3);
                if ((dirtyFlags & 69633) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                if ((dirtyFlags & 70144) == 0) {
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 4194304;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 73728) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                }
                updateRegistration(13, weatherViewModelWeatherInfoTemperatureRange);
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRange != null ? weatherViewModelWeatherInfoTemperatureRange.get() : weatherViewModelWeatherInfoTemperatureRangeGet2;
            }
            if ((dirtyFlags & 81920) == 0) {
                weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet2;
                weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet3;
                weatherViewModelWeatherInfoId8ImageIconGet2 = null;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather = weatherViewModelWeatherInfoId8CardDetailImg3Get;
                weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                weatherViewModelWeatherInfoPempId8ImageGet = null;
                weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardDetailImg2 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg2 = weatherViewModelWeatherInfo2.id8CardDetailImg2;
                }
                String weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                updateRegistration(14, weatherViewModelWeatherInfoId8CardDetailImg2);
                if (weatherViewModelWeatherInfoId8CardDetailImg2 == null) {
                    weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet4;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet2;
                    weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet = null;
                    weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet3;
                    weatherViewModelWeatherInfoId8ImageIconGet2 = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather = weatherViewModelWeatherInfoId8CardDetailImg3Get;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    weatherViewModelWeatherInfoPempId8ImageGet = null;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
                } else {
                    Drawable weatherViewModelWeatherInfoId8CardDetailImg2Get2 = weatherViewModelWeatherInfoId8CardDetailImg2.get();
                    Drawable weatherViewModelWeatherInfoId8CardDetailImg2Get3 = weatherViewModelWeatherInfoId8CardDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet4;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet2;
                    weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet = null;
                    weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet3;
                    weatherViewModelWeatherInfoId8ImageIconGet2 = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather = weatherViewModelWeatherInfoId8CardDetailImg3Get;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    weatherViewModelWeatherInfoPempId8ImageGet = null;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoId8CardDetailImg2Get = weatherViewModelWeatherInfoId8CardDetailImg2Get3;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
                }
            }
        }
        if ((dirtyFlags & 83886080) == 0) {
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
            weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet;
            weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
        } else {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = WeatherViewModelWeatherInfoIsLoadSuccess13;
            } else {
                weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = weatherViewModelWeatherInfo.isLoadSuccess;
            }
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
            updateRegistration(12, WeatherViewModelWeatherInfoIsLoadSuccess1);
            if (WeatherViewModelWeatherInfoIsLoadSuccess1 == null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
            } else {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = WeatherViewModelWeatherInfoIsLoadSuccess1.get();
            }
            androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet2);
            if ((dirtyFlags & 69633) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            if ((dirtyFlags & 70144) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 4194304;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                }
            }
            if ((dirtyFlags & 67108864) != 0) {
                weatherViewModelWeatherInfoIsLoadSuccess = !androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet;
            }
        }
        if ((dirtyFlags & 69640) == 0) {
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
        } else {
            boolean weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            boolean WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? weatherViewModelWeatherInfoIsLoadSuccess : false;
            if ((dirtyFlags & 69640) != 0) {
                if (weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 69640) != 0) {
                if (WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= 1073741824;
                } else {
                    dirtyFlags |= 536870912;
                }
            }
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) == 0) {
            weatherViewModel = weatherViewModel2;
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        } else {
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo != null ? weatherViewModelWeatherInfo.city : weatherViewModelWeatherInfoCity;
            weatherViewModel = weatherViewModel2;
            updateRegistration(0, weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        }
        if ((dirtyFlags & 4194304) != 0) {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoPempId8Image = null;
            } else {
                weatherViewModelWeatherInfoPempId8Image = weatherViewModelWeatherInfo.PempId8Image;
            }
            updateRegistration(9, weatherViewModelWeatherInfoPempId8Image);
            if (weatherViewModelWeatherInfoPempId8Image != null) {
                weatherViewModelWeatherInfoPempId8ImageGet = weatherViewModelWeatherInfoPempId8Image.get();
            }
        }
        if ((dirtyFlags & 69633) == 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoTemperatureRangeGet;
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoCityGet : this.tvCity.getResources().getString(C0899R.string.ksw_id8_weather);
        }
        if ((dirtyFlags & 70144) != 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoPempId8ImageGet : AppCompatResources.getDrawable(this.mboundView1.getContext(), C0899R.C0900drawable.pemp_id8_main_icon_weather);
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather2 = weatherViewModelWeatherInfoId8ImageIconGet2;
        }
        if ((dirtyFlags & 65600) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btA, weatherViewModelWeatherInfoId8CardDetailImg1Get);
        }
        if ((dirtyFlags & 65538) != 0) {
            TextViewBindingAdapter.setText(this.btA, weatherViewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 81920) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btB, weatherViewModelWeatherInfoId8CardDetailImg2Get);
        }
        if ((dirtyFlags & 67584) != 0) {
            TextViewBindingAdapter.setText(this.btB, weatherViewModelWeatherInfoCardDetailText2Get);
        }
        if ((dirtyFlags & 65568) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btC, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather);
        }
        if ((dirtyFlags & 65540) != 0) {
            TextViewBindingAdapter.setText(this.btC, weatherViewModelWeatherInfoCardDetailText3Get);
        }
        if ((dirtyFlags & 65664) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, weatherViewModelWeatherInfoId8ImageIconGet);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            this.ivMask.setOnClickListener(this.mCallback52);
        }
        if ((dirtyFlags & 70144) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8ImageMboundView1AndroidDrawablePempId8MainIconWeather2);
        }
        if ((dirtyFlags & 73728) != 0) {
            TextViewBindingAdapter.setText(this.mboundView13, weatherViewModelWeatherInfoTemperatureRangeGet3);
        }
        if ((dirtyFlags & 69640) != 0) {
            this.mboundView13.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView6.setVisibility(WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.temperatureTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.tvCity.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.unitWeather.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.weatherTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 98304) != 0) {
            this.mboundView31.setWeatherViewModel(weatherViewModel);
        }
        if ((dirtyFlags & 65544) != 0) {
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
            this.mboundView5.setVisibility(weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4);
        } else {
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
        }
        if ((dirtyFlags & 66560) != 0) {
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
            TextViewBindingAdapter.setText(this.mboundView7, weatherViewModelWeatherInfoErrorMessageGet3);
        } else {
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
        }
        if ((dirtyFlags & 65552) != 0) {
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoTemperatureGet;
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoErrorMessageGet4);
        } else {
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoTemperatureGet;
        }
        if ((dirtyFlags & 69633) != 0) {
            TextViewBindingAdapter.setText(this.tvCity, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 65792) != 0) {
            TextViewBindingAdapter.setText(this.weatherTv, weatherViewModelWeatherInfoPhraseGet);
        }
        executeBindingsOn(this.mboundView31);
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        boolean weatherViewModelJavaLangObjectNull = weatherViewModel != null;
        if (weatherViewModelJavaLangObjectNull) {
            weatherViewModel.openWeatherApp(callbackArg_0);
        }
    }
}

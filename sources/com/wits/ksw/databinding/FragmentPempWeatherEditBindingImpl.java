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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class FragmentPempWeatherEditBindingImpl extends FragmentPempWeatherEditBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final IncludeNearWeatherEditBinding mboundView1;
    private final TextView mboundView8;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(15);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"include_near_weather_edit"}, new int[]{10}, new int[]{C0899R.C0902layout.include_near_weather_edit});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.tv_title, 11);
        sparseIntArray.put(C0899R.C0901id.iv_divider, 12);
        sparseIntArray.put(C0899R.C0901id.temperature, 13);
        sparseIntArray.put(C0899R.C0901id.remove, 14);
    }

    public FragmentPempWeatherEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private FragmentPempWeatherEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 13, (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[5], (ImageView) bindings[12], (RelativeLayout) bindings[1], (ImageView) bindings[14], (RelativeLayout) bindings[13], (TextView) bindings[6], (TextView) bindings[2], (TextView) bindings[11], (TextView) bindings[7], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.btA.setTag(null);
        this.btB.setTag(null);
        this.btC.setTag(null);
        this.layout.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        IncludeNearWeatherEditBinding includeNearWeatherEditBinding = (IncludeNearWeatherEditBinding) bindings[10];
        this.mboundView1 = includeNearWeatherEditBinding;
        setContainedBinding(includeNearWeatherEditBinding);
        TextView textView = (TextView) bindings[8];
        this.mboundView8 = textView;
        textView.setTag(null);
        this.temperatureTv.setTag(null);
        this.tvCity.setTag(null);
        this.unitWeather.setTag(null);
        this.weatherTv.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE;
        }
        this.mboundView1.invalidateAll();
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView1.hasPendingBindings();
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

    @Override // com.wits.ksw.databinding.FragmentPempWeatherEditBinding
    public void setWeatherViewModel(LauncherViewModel WeatherViewModel) {
        this.mWeatherViewModel = WeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView1.setLifecycleOwner(lifecycleOwner);
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWeatherViewModelWeatherInfoCity((ObservableField) object, fieldId);
            case 1:
                return onChangeWeatherViewModelWeatherInfoPempId8EditImage((ObservableField) object, fieldId);
            case 2:
                return onChangeWeatherViewModelWeatherInfoCardDetailText1((ObservableField) object, fieldId);
            case 3:
                return onChangeWeatherViewModelWeatherInfoCardDetailText3((ObservableField) object, fieldId);
            case 4:
                return onChangeWeatherViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 5:
                return onChangeWeatherViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 6:
                return onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg2((ObservableField) object, fieldId);
            case 7:
                return onChangeWeatherViewModelWeatherInfoPhrase((ObservableField) object, fieldId);
            case 8:
                return onChangeWeatherViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 10:
                return onChangeWeatherViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 11:
                return onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg1((ObservableField) object, fieldId);
            case 12:
                return onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg3((ObservableField) object, fieldId);
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

    private boolean onChangeWeatherViewModelWeatherInfoPempId8EditImage(ObservableField<Drawable> WeatherViewModelWeatherInfoPempId8EditImage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText1(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText3(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> WeatherViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> WeatherViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg2(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardEditDetailImg2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoPhrase(ObservableField<String> WeatherViewModelWeatherInfoPhrase, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText2(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> WeatherViewModelWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg1(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardEditDetailImg1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg3(ObservableField<Drawable> WeatherViewModelWeatherInfoId8CardEditDetailImg3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg2Get;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg1Get;
        String weatherViewModelWeatherInfoTemperatureGet;
        ObservableField<Drawable> weatherViewModelWeatherInfoPempId8EditImage;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        WeatherInfo weatherViewModelWeatherInfo;
        String weatherViewModelWeatherInfoCardDetailText2Get2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg3Get;
        String weatherViewModelWeatherInfoPhraseGet;
        String weatherViewModelWeatherInfoPhraseGet2;
        String weatherViewModelWeatherInfoCityGet;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        ObservableField<Drawable> weatherViewModelWeatherInfoPempId8EditImage2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2;
        Drawable weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8EditImageLayoutAndroidDrawablePempId8MainEditIconWeather;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess;
        String weatherViewModelWeatherInfoTemperatureRangeGet;
        String weatherViewModelWeatherInfoTemperatureRangeGet2;
        String weatherViewModelWeatherInfoTemperatureRangeGet3;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg3;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg1;
        ObservableField<String> weatherViewModelWeatherInfoTemperatureRange;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess2;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText2;
        ObservableField<String> weatherViewModelWeatherInfoPhrase;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg2;
        ObservableField<String> weatherViewModelWeatherInfoTemperature;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String weatherViewModelWeatherInfoTemperatureRangeGet4 = null;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2 = null;
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText1 = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText3 = null;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg2Get2 = null;
        String weatherViewModelWeatherInfoCardDetailText1Get2 = null;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg1Get2 = null;
        String weatherViewModelWeatherInfoTemperatureGet2 = null;
        Drawable weatherViewModelWeatherInfoPempId8EditImageGet = null;
        String weatherViewModelWeatherInfoCardDetailText2Get3 = null;
        String weatherViewModelWeatherInfoCardDetailText3Get = null;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess3 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        String weatherViewModelWeatherInfoPhraseGet3 = null;
        if ((dirtyFlags & 24575) == 0) {
            weatherViewModelWeatherInfoId8CardEditDetailImg2Get = null;
            weatherViewModelWeatherInfoCardDetailText1Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessGet = null;
            weatherViewModelWeatherInfoCity = null;
            weatherViewModelWeatherInfoId8CardEditDetailImg1Get = null;
            weatherViewModelWeatherInfoTemperatureGet = null;
            weatherViewModelWeatherInfoPempId8EditImage = null;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            weatherViewModelWeatherInfoCardDetailText2Get = null;
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoCardDetailText2Get2 = null;
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = null;
            weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
            weatherViewModelWeatherInfoPhraseGet = null;
        } else {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 16388) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(2, weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 16392) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(3, weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 16912) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                updateRegistration(4, weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    Boolean weatherViewModelWeatherInfoIsInitFinishedGet2 = weatherViewModelWeatherInfoIsInitFinished.get();
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinishedGet2;
                }
                androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 16912) != 0) {
                    dirtyFlags = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED : dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            if ((dirtyFlags & 16416) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperature = null;
                } else {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                }
                updateRegistration(5, weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperature.get();
                }
            }
            if ((dirtyFlags & 16448) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2 = weatherViewModelWeatherInfo2.id8CardEditDetailImg2;
                }
                updateRegistration(6, weatherViewModelWeatherInfoId8CardEditDetailImg2);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg2 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get2 = weatherViewModelWeatherInfoId8CardEditDetailImg2.get();
                }
            }
            if ((dirtyFlags & 16512) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoPhrase = null;
                } else {
                    weatherViewModelWeatherInfoPhrase = weatherViewModelWeatherInfo2.phrase;
                }
                updateRegistration(7, weatherViewModelWeatherInfoPhrase);
                if (weatherViewModelWeatherInfoPhrase != null) {
                    weatherViewModelWeatherInfoPhraseGet3 = weatherViewModelWeatherInfoPhrase.get();
                }
            }
            if ((dirtyFlags & 16640) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoCardDetailText2 = null;
                } else {
                    weatherViewModelWeatherInfoCardDetailText2 = weatherViewModelWeatherInfo2.cardDetailText2;
                }
                updateRegistration(8, weatherViewModelWeatherInfoCardDetailText2);
                if (weatherViewModelWeatherInfoCardDetailText2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2Get3 = weatherViewModelWeatherInfoCardDetailText2.get();
                }
            }
            if ((dirtyFlags & 16899) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoIsLoadSuccess2 = null;
                } else {
                    weatherViewModelWeatherInfoIsLoadSuccess2 = weatherViewModelWeatherInfo2.isLoadSuccess;
                }
                updateRegistration(9, weatherViewModelWeatherInfoIsLoadSuccess2);
                if (weatherViewModelWeatherInfoIsLoadSuccess2 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccess2.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet2);
                if ((dirtyFlags & 16897) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if ((dirtyFlags & 16898) == 0) {
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 4194304;
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 17408) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                }
                updateRegistration(10, weatherViewModelWeatherInfoTemperatureRange);
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRange != null ? weatherViewModelWeatherInfoTemperatureRange.get() : weatherViewModelWeatherInfoTemperatureRangeGet;
            }
            if ((dirtyFlags & 18432) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1 = weatherViewModelWeatherInfo2.id8CardEditDetailImg1;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
                updateRegistration(11, weatherViewModelWeatherInfoId8CardEditDetailImg1);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg1 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get2 = weatherViewModelWeatherInfoId8CardEditDetailImg1.get();
                }
            }
            if ((dirtyFlags & 20480) == 0) {
                weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet2;
                weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
                weatherViewModelWeatherInfoPempId8EditImage = null;
                weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get3;
                weatherViewModelWeatherInfoCardDetailText2Get2 = null;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg3 = null;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg3 = weatherViewModelWeatherInfo2.id8CardEditDetailImg3;
                }
                updateRegistration(12, weatherViewModelWeatherInfoId8CardEditDetailImg3);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg3 == null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
                    weatherViewModelWeatherInfoPempId8EditImage = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get3;
                    weatherViewModelWeatherInfoCardDetailText2Get2 = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                } else {
                    Drawable weatherViewModelWeatherInfoId8CardEditDetailImg3Get2 = weatherViewModelWeatherInfoId8CardEditDetailImg3.get();
                    Drawable weatherViewModelWeatherInfoId8CardEditDetailImg3Get3 = weatherViewModelWeatherInfoId8CardEditDetailImg3Get2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg3Get = weatherViewModelWeatherInfoId8CardEditDetailImg3Get3;
                    weatherViewModelWeatherInfoPempId8EditImage = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get3;
                    weatherViewModelWeatherInfoCardDetailText2Get2 = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                }
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) == 0) {
            weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
            weatherViewModelWeatherInfoCityGet = null;
        } else {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoCityGet = null;
                weatherViewModelWeatherInfoIsLoadSuccess = weatherViewModelWeatherInfoIsLoadSuccess3;
            } else {
                weatherViewModelWeatherInfoCityGet = null;
                weatherViewModelWeatherInfoIsLoadSuccess = weatherViewModelWeatherInfo.isLoadSuccess;
            }
            weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
            updateRegistration(9, weatherViewModelWeatherInfoIsLoadSuccess);
            if (weatherViewModelWeatherInfoIsLoadSuccess != null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccess.get();
            }
            androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet);
            if ((dirtyFlags & 16897) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 16898) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 4194304;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                }
            }
        }
        if ((dirtyFlags & 16912) == 0) {
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        } else {
            boolean weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            if ((dirtyFlags & 16912) != 0) {
                if (weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
            }
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
            } else {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo.city;
            }
            updateRegistration(0, weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        } else {
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        }
        if ((dirtyFlags & 4194304) != 0) {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoPempId8EditImage2 = weatherViewModelWeatherInfoPempId8EditImage;
            } else {
                weatherViewModelWeatherInfoPempId8EditImage2 = weatherViewModelWeatherInfo.PempId8EditImage;
            }
            updateRegistration(1, weatherViewModelWeatherInfoPempId8EditImage2);
            if (weatherViewModelWeatherInfoPempId8EditImage2 != null) {
                weatherViewModelWeatherInfoPempId8EditImageGet = weatherViewModelWeatherInfoPempId8EditImage2.get();
            }
        } else {
            weatherViewModelWeatherInfoPempId8EditImage2 = weatherViewModelWeatherInfoPempId8EditImage;
        }
        if ((dirtyFlags & 16897) != 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoCityGet : this.tvCity.getResources().getString(C0899R.string.ksw_id8_weather);
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2 = weatherViewModelWeatherInfoCardDetailText2Get2;
        }
        if ((dirtyFlags & 16898) == 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8EditImageLayoutAndroidDrawablePempId8MainEditIconWeather = null;
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8EditImageLayoutAndroidDrawablePempId8MainEditIconWeather = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoPempId8EditImageGet : AppCompatResources.getDrawable(this.layout.getContext(), C0899R.C0900drawable.pemp_id8_main_edit_icon_weather);
        }
        if ((dirtyFlags & 18432) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btA, weatherViewModelWeatherInfoId8CardEditDetailImg1Get);
        }
        if ((dirtyFlags & 16388) != 0) {
            TextViewBindingAdapter.setText(this.btA, weatherViewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 16448) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btB, weatherViewModelWeatherInfoId8CardEditDetailImg2Get);
        }
        if ((dirtyFlags & 16640) != 0) {
            TextViewBindingAdapter.setText(this.btB, weatherViewModelWeatherInfoCardDetailText2Get);
        }
        if ((dirtyFlags & 20480) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btC, weatherViewModelWeatherInfoId8CardEditDetailImg3Get);
        }
        if ((dirtyFlags & 16392) != 0) {
            TextViewBindingAdapter.setText(this.btC, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 16898) != 0) {
            ViewBindingAdapter.setBackground(this.layout, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoPempId8EditImageLayoutAndroidDrawablePempId8MainEditIconWeather);
        }
        if ((dirtyFlags & 24576) != 0) {
            this.mboundView1.setWeatherViewModel(weatherViewModel);
        }
        if ((dirtyFlags & 17408) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, weatherViewModelWeatherInfoTemperatureRangeGet4);
        }
        if ((dirtyFlags & 16912) != 0) {
            this.mboundView8.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.temperatureTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.tvCity.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.unitWeather.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.weatherTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
        }
        if ((dirtyFlags & 16416) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoTemperatureGet);
        }
        if ((dirtyFlags & 16897) != 0) {
            TextViewBindingAdapter.setText(this.tvCity, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2);
        }
        if ((dirtyFlags & 16512) != 0) {
            String weatherViewModelWeatherInfoTemperatureRangeGet5 = weatherViewModelWeatherInfoPhraseGet2;
            TextViewBindingAdapter.setText(this.weatherTv, weatherViewModelWeatherInfoTemperatureRangeGet5);
        }
        executeBindingsOn(this.mboundView1);
    }
}

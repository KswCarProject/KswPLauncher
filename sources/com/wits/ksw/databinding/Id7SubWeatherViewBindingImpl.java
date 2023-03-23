package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class Id7SubWeatherViewBindingImpl extends Id7SubWeatherViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback112;
    private long mDirtyFlags;
    private final TextView mboundView10;
    private final ProgressBar mboundView2;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_a, 14);
        sparseIntArray.put(R.id.ll_b, 15);
        sparseIntArray.put(R.id.ll_c, 16);
    }

    public Id7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private Id7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, bindings[11], bindings[12], bindings[13], bindings[1], bindings[14], bindings[15], bindings[16], bindings[7], bindings[5], bindings[9], bindings[6], bindings[8], bindings[0]);
        this.mDirtyFlags = -1;
        this.btA.setTag((Object) null);
        this.btB.setTag((Object) null);
        this.btC.setTag((Object) null);
        this.icon.setTag((Object) null);
        TextView textView = bindings[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        ProgressBar progressBar = bindings[2];
        this.mboundView2 = progressBar;
        progressBar.setTag((Object) null);
        LinearLayout linearLayout = bindings[3];
        this.mboundView3 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView2 = bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        this.phoneConnectionTextView.setTag((Object) null);
        this.phoneImageView.setTag((Object) null);
        this.temperatureTv.setTag((Object) null);
        this.textView2.setTag((Object) null);
        this.unitWeather.setTag((Object) null);
        this.weatherConstraintLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback112 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
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
        if (17 != variableId) {
            return false;
        }
        setWeatherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setWeatherViewModel(LauncherViewModel WeatherViewModel) {
        this.mWeatherViewModel = WeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWeatherViewModelWeatherInfoId7CardDetailImg3((ObservableField) object, fieldId);
            case 1:
                return onChangeWeatherViewModelWeatherInfoId7CardDetailImg1((ObservableField) object, fieldId);
            case 2:
                return onChangeWeatherViewModelWeatherInfoCity((ObservableField) object, fieldId);
            case 3:
                return onChangeWeatherViewModelWeatherInfoCardDetailText1((ObservableField) object, fieldId);
            case 4:
                return onChangeWeatherViewModelWeatherInfoCardDetailText3((ObservableField) object, fieldId);
            case 5:
                return onChangeWeatherViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 6:
                return onChangeWeatherViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 7:
                return onChangeWeatherViewModelWeatherInfoId7CardDetailImg2((ObservableField) object, fieldId);
            case 8:
                return onChangeWeatherViewModelWeatherInfoPhrase((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoImage((ObservableField) object, fieldId);
            case 10:
                return onChangeWeatherViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 11:
                return onChangeWeatherViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 12:
                return onChangeWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 13:
                return onChangeWeatherViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 14:
                return onChangeWeatherViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg3(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCity(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText1(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText3(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg2(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoPhrase(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoImage(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoErrorMessage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        View.OnFocusChangeListener weatherViewModelWeatherViewFocusChangeListener;
        View.OnFocusChangeListener weatherViewModelWeatherViewFocusChangeListener2;
        String weatherViewModelWeatherInfoTemperatureGet;
        String weatherViewModelWeatherInfoErrorMessageGet;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        String weatherViewModelWeatherInfoErrorMessageGet2;
        String weatherViewModelWeatherInfoCardDetailText3Get;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg1Get;
        WeatherInfo weatherViewModelWeatherInfo;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        Drawable weatherViewModelWeatherInfoImageGet;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg2Get;
        String weatherViewModelWeatherInfoPhraseGet;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        String weatherViewModelWeatherInfoErrorMessageGet3;
        String weatherViewModelWeatherInfoPhraseGet2;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2;
        int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather;
        String weatherViewModelWeatherInfoCardDetailText1Get2;
        String weatherViewModelWeatherInfoPhraseGet3;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess1;
        String weatherViewModelWeatherInfoTemperatureRangeGet;
        ObservableField<Drawable> weatherViewModelWeatherInfoId7CardDetailImg3;
        ObservableField<Drawable> weatherViewModelWeatherInfoId7CardDetailImg32;
        ObservableField<String> weatherViewModelWeatherInfoTemperatureRange;
        ObservableField<String> weatherViewModelWeatherInfoTemperUnit;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess12;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText2;
        ObservableField<String> weatherViewModelWeatherInfoErrorMessage;
        ObservableField<Drawable> weatherViewModelWeatherInfoImage;
        ObservableField<String> weatherViewModelWeatherInfoPhrase;
        ObservableField<Drawable> weatherViewModelWeatherInfoId7CardDetailImg2;
        ObservableField<String> weatherViewModelWeatherInfoTemperature;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String weatherViewModelWeatherInfoTemperatureRangeGet2 = null;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet3 = null;
        ObservableField<Drawable> weatherViewModelWeatherInfoId7CardDetailImg1 = null;
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg3Get = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText1 = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText3 = null;
        String weatherViewModelWeatherInfoCityGet = null;
        String weatherViewModelWeatherInfoTemperUnitGet = null;
        String weatherViewModelWeatherInfoCardDetailText1Get3 = null;
        boolean weatherViewModelWeatherInfoIsLoadSuccess = false;
        String weatherViewModelWeatherInfoTemperatureGet2 = null;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
        String weatherViewModelWeatherInfoCardDetailText2Get2 = null;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg1Get2 = null;
        String weatherViewModelWeatherInfoCardDetailText3Get2 = null;
        String weatherViewModelWeatherInfoErrorMessageGet4 = null;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess13 = null;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg2Get2 = null;
        Drawable weatherViewModelWeatherInfoImageGet2 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        String weatherViewModelWeatherInfoPhraseGet4 = null;
        if ((dirtyFlags & 98304) == 0 || weatherViewModel == null) {
            weatherViewModelWeatherViewFocusChangeListener = null;
        } else {
            weatherViewModelWeatherViewFocusChangeListener = weatherViewModel.weatherViewFocusChangeListener;
        }
        if ((dirtyFlags & 98303) != 0) {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 65537) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg3 = weatherViewModelWeatherInfo2.id7CardDetailImg3;
                } else {
                    weatherViewModelWeatherInfoId7CardDetailImg3 = null;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                updateRegistration(0, (Observable) weatherViewModelWeatherInfoId7CardDetailImg3);
                if (weatherViewModelWeatherInfoId7CardDetailImg3 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg3Get = weatherViewModelWeatherInfoId7CardDetailImg3.get();
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                weatherViewModelWeatherInfoId7CardDetailImg3 = null;
            }
            if ((dirtyFlags & 65538) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg1 = weatherViewModelWeatherInfo2.id7CardDetailImg1;
                }
                updateRegistration(1, (Observable) weatherViewModelWeatherInfoId7CardDetailImg1);
                if (weatherViewModelWeatherInfoId7CardDetailImg1 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg1Get2 = weatherViewModelWeatherInfoId7CardDetailImg1.get();
                }
            }
            if ((dirtyFlags & 65544) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(3, (Observable) weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get3 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 65552) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(4, (Observable) weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get2 = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 69664) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                }
                weatherViewModelWeatherInfoId7CardDetailImg32 = weatherViewModelWeatherInfoId7CardDetailImg3;
                updateRegistration(5, (Observable) weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinished.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 69664) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED | 4194304;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                if ((dirtyFlags & 65568) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                if ((dirtyFlags & 65568) != 0) {
                    ObservableField<Boolean> observableField = weatherViewModelWeatherInfoIsInitFinished;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                    ObservableField<Boolean> observableField2 = weatherViewModelWeatherInfoIsInitFinished;
                }
            } else {
                weatherViewModelWeatherInfoId7CardDetailImg32 = weatherViewModelWeatherInfoId7CardDetailImg3;
            }
            if ((dirtyFlags & 65600) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                } else {
                    weatherViewModelWeatherInfoTemperature = null;
                }
                updateRegistration(6, (Observable) weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperature.get();
                    ObservableField<String> observableField3 = weatherViewModelWeatherInfoTemperature;
                } else {
                    ObservableField<String> observableField4 = weatherViewModelWeatherInfoTemperature;
                }
            }
            if ((dirtyFlags & 65664) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg2 = weatherViewModelWeatherInfo2.id7CardDetailImg2;
                } else {
                    weatherViewModelWeatherInfoId7CardDetailImg2 = null;
                }
                updateRegistration(7, (Observable) weatherViewModelWeatherInfoId7CardDetailImg2);
                if (weatherViewModelWeatherInfoId7CardDetailImg2 != null) {
                    ObservableField<Drawable> observableField5 = weatherViewModelWeatherInfoId7CardDetailImg2;
                    weatherViewModelWeatherInfoId7CardDetailImg2Get2 = weatherViewModelWeatherInfoId7CardDetailImg2.get();
                } else {
                    ObservableField<Drawable> observableField6 = weatherViewModelWeatherInfoId7CardDetailImg2;
                }
            }
            if ((dirtyFlags & 65792) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoPhrase = weatherViewModelWeatherInfo2.phrase;
                } else {
                    weatherViewModelWeatherInfoPhrase = null;
                }
                updateRegistration(8, (Observable) weatherViewModelWeatherInfoPhrase);
                if (weatherViewModelWeatherInfoPhrase != null) {
                    ObservableField<String> observableField7 = weatherViewModelWeatherInfoPhrase;
                    weatherViewModelWeatherInfoPhraseGet4 = weatherViewModelWeatherInfoPhrase.get();
                } else {
                    ObservableField<String> observableField8 = weatherViewModelWeatherInfoPhrase;
                }
            }
            if ((dirtyFlags & 66048) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoImage = weatherViewModelWeatherInfo2.image;
                } else {
                    weatherViewModelWeatherInfoImage = null;
                }
                updateRegistration(9, (Observable) weatherViewModelWeatherInfoImage);
                if (weatherViewModelWeatherInfoImage != null) {
                    ObservableField<Drawable> observableField9 = weatherViewModelWeatherInfoImage;
                    weatherViewModelWeatherInfoImageGet2 = weatherViewModelWeatherInfoImage.get();
                } else {
                    ObservableField<Drawable> observableField10 = weatherViewModelWeatherInfoImage;
                }
            }
            if ((dirtyFlags & 66560) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoErrorMessage = weatherViewModelWeatherInfo2.errorMessage;
                } else {
                    weatherViewModelWeatherInfoErrorMessage = null;
                }
                updateRegistration(10, (Observable) weatherViewModelWeatherInfoErrorMessage);
                if (weatherViewModelWeatherInfoErrorMessage != null) {
                    ObservableField<String> observableField11 = weatherViewModelWeatherInfoErrorMessage;
                    weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoErrorMessage.get();
                } else {
                    ObservableField<String> observableField12 = weatherViewModelWeatherInfoErrorMessage;
                }
            }
            if ((dirtyFlags & 67584) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2 = weatherViewModelWeatherInfo2.cardDetailText2;
                } else {
                    weatherViewModelWeatherInfoCardDetailText2 = null;
                }
                updateRegistration(11, (Observable) weatherViewModelWeatherInfoCardDetailText2);
                if (weatherViewModelWeatherInfoCardDetailText2 != null) {
                    ObservableField<String> observableField13 = weatherViewModelWeatherInfoCardDetailText2;
                    weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2.get();
                } else {
                    ObservableField<String> observableField14 = weatherViewModelWeatherInfoCardDetailText2;
                }
            }
            if ((dirtyFlags & 69636) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = weatherViewModelWeatherInfo2.isLoadSuccess;
                } else {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = null;
                }
                updateRegistration(12, (Observable) WeatherViewModelWeatherInfoIsLoadSuccess12);
                if (WeatherViewModelWeatherInfoIsLoadSuccess12 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccessGet3 = WeatherViewModelWeatherInfoIsLoadSuccess12.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet3);
                if ((dirtyFlags & 69636) == 0) {
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 268435456;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= 134217728;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 73728) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperUnit = weatherViewModelWeatherInfo2.temperUnit;
                } else {
                    weatherViewModelWeatherInfoTemperUnit = null;
                }
                updateRegistration(13, (Observable) weatherViewModelWeatherInfoTemperUnit);
                if (weatherViewModelWeatherInfoTemperUnit != null) {
                    weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnit.get();
                    ObservableField<String> observableField15 = weatherViewModelWeatherInfoTemperUnit;
                } else {
                    ObservableField<String> observableField16 = weatherViewModelWeatherInfoTemperUnit;
                }
            }
            if ((dirtyFlags & 81920) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                }
                updateRegistration(14, (Observable) weatherViewModelWeatherInfoTemperatureRange);
                if (weatherViewModelWeatherInfoTemperatureRange != null) {
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRange.get();
                    ObservableField<String> observableField17 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                    weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet4;
                    LauncherViewModel launcherViewModel = weatherViewModel;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    ObservableField<Drawable> observableField18 = weatherViewModelWeatherInfoId7CardDetailImg1;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    LauncherViewModel launcherViewModel2 = launcherViewModel;
                    ObservableField<Drawable> observableField19 = weatherViewModelWeatherInfoId7CardDetailImg32;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                    ObservableField<String> observableField20 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    ObservableField<String> observableField21 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet4;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                    ObservableField<Drawable> observableField22 = observableField19;
                } else {
                    ObservableField<String> observableField23 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                    weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet4;
                    LauncherViewModel launcherViewModel3 = weatherViewModel;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    ObservableField<Drawable> observableField24 = weatherViewModelWeatherInfoId7CardDetailImg1;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    LauncherViewModel launcherViewModel4 = launcherViewModel3;
                    ObservableField<Drawable> observableField25 = weatherViewModelWeatherInfoId7CardDetailImg32;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                    ObservableField<String> observableField26 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    ObservableField<String> observableField27 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet4;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                    ObservableField<Drawable> observableField28 = observableField25;
                }
            } else {
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet4;
                LauncherViewModel launcherViewModel5 = weatherViewModel;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                ObservableField<Drawable> observableField29 = weatherViewModelWeatherInfoId7CardDetailImg1;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                LauncherViewModel launcherViewModel6 = launcherViewModel5;
                ObservableField<Drawable> observableField30 = weatherViewModelWeatherInfoId7CardDetailImg32;
                weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                ObservableField<String> observableField31 = weatherViewModelWeatherInfoCardDetailText1;
                weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                ObservableField<String> observableField32 = weatherViewModelWeatherInfoCardDetailText3;
                weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet4;
                weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                ObservableField<Drawable> observableField33 = observableField30;
            }
        } else {
            weatherViewModelWeatherInfoCardDetailText1Get = null;
            weatherViewModelWeatherInfoId7CardDetailImg2Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessGet = null;
            weatherViewModelWeatherInfoTemperatureGet = null;
            weatherViewModelWeatherInfoImageGet = null;
            weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            weatherViewModelWeatherInfoPhraseGet = null;
            weatherViewModelWeatherInfoCity = null;
            weatherViewModelWeatherInfoCardDetailText2Get = null;
            LauncherViewModel launcherViewModel7 = weatherViewModel;
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            weatherViewModelWeatherInfoId7CardDetailImg1Get = null;
            weatherViewModelWeatherInfoCardDetailText3Get = null;
            weatherViewModelWeatherInfoErrorMessageGet2 = null;
            weatherViewModelWeatherInfoErrorMessageGet = null;
        }
        if ((dirtyFlags & 5242880) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = weatherViewModelWeatherInfo.isLoadSuccess;
            } else {
                weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = WeatherViewModelWeatherInfoIsLoadSuccess13;
            }
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
            updateRegistration(12, (Observable) WeatherViewModelWeatherInfoIsLoadSuccess1);
            if (WeatherViewModelWeatherInfoIsLoadSuccess1 != null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = WeatherViewModelWeatherInfoIsLoadSuccess1.get();
            } else {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
            }
            androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet2);
            if ((dirtyFlags & 69636) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 268435456;
                } else {
                    dirtyFlags |= 134217728;
                }
            }
            if ((dirtyFlags & 4194304) != 0) {
                weatherViewModelWeatherInfoIsLoadSuccess = !androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet;
                ObservableField<Boolean> observableField34 = WeatherViewModelWeatherInfoIsLoadSuccess1;
            } else {
                ObservableField<Boolean> observableField35 = WeatherViewModelWeatherInfoIsLoadSuccess1;
            }
        } else {
            weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
            weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
        }
        if ((dirtyFlags & 69664) != 0) {
            boolean weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            boolean WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? weatherViewModelWeatherInfoIsLoadSuccess : false;
            if ((dirtyFlags & 69664) != 0) {
                if (weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 69664) != 0) {
                if (WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= 67108864;
                } else {
                    dirtyFlags |= 33554432;
                }
            }
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            boolean z = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
        } else {
            boolean z2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & 268435456) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                Boolean bool = weatherViewModelWeatherInfoIsLoadSuccessGet2;
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo.city;
            } else {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
            }
            WeatherInfo weatherInfo = weatherViewModelWeatherInfo;
            updateRegistration(2, (Observable) weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        } else {
            Boolean bool2 = weatherViewModelWeatherInfoIsLoadSuccessGet2;
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        }
        if ((dirtyFlags & 69636) != 0) {
            if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                ObservableField<String> observableField36 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather = weatherViewModelWeatherInfoCityGet;
            } else {
                ObservableField<String> observableField37 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather = this.textView2.getResources().getString(R.string.ksw_id8_weather);
            }
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather = null;
        }
        if ((dirtyFlags & 65538) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btA, weatherViewModelWeatherInfoId7CardDetailImg1Get);
        }
        if ((dirtyFlags & 65544) != 0) {
            TextViewBindingAdapter.setText(this.btA, weatherViewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 69664) != 0) {
            this.btA.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.btB.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.btC.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.icon.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.mboundView10.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.mboundView3.setVisibility(WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.phoneConnectionTextView.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.temperatureTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.unitWeather.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
        }
        if ((dirtyFlags & 65664) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btB, weatherViewModelWeatherInfoId7CardDetailImg2Get);
        }
        if ((dirtyFlags & 67584) != 0) {
            TextViewBindingAdapter.setText(this.btB, weatherViewModelWeatherInfoCardDetailText2Get);
        }
        if ((dirtyFlags & 65537) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btC, weatherViewModelWeatherInfoId7CardDetailImg3Get);
        }
        if ((dirtyFlags & 65552) != 0) {
            TextViewBindingAdapter.setText(this.btC, weatherViewModelWeatherInfoCardDetailText3Get);
        }
        if ((dirtyFlags & 66048) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.icon, weatherViewModelWeatherInfoImageGet);
        }
        if ((dirtyFlags & 81920) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, weatherViewModelWeatherInfoTemperatureRangeGet2);
        }
        if ((dirtyFlags & 65568) != 0) {
            this.mboundView2.setVisibility(weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 66560) != 0) {
            String str = weatherViewModelWeatherInfoCardDetailText1Get;
            weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoErrorMessageGet3;
            TextViewBindingAdapter.setText(this.mboundView4, weatherViewModelWeatherInfoCardDetailText1Get2);
        } else {
            weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoErrorMessageGet3;
        }
        if ((dirtyFlags & 65792) != 0) {
            String str2 = weatherViewModelWeatherInfoCardDetailText1Get2;
            weatherViewModelWeatherInfoPhraseGet3 = weatherViewModelWeatherInfoPhraseGet2;
            TextViewBindingAdapter.setText(this.phoneConnectionTextView, weatherViewModelWeatherInfoPhraseGet3);
        } else {
            String weatherViewModelWeatherInfoErrorMessageGet5 = weatherViewModelWeatherInfoCardDetailText1Get2;
            weatherViewModelWeatherInfoPhraseGet3 = weatherViewModelWeatherInfoPhraseGet2;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            String str3 = weatherViewModelWeatherInfoPhraseGet3;
            this.phoneImageView.setOnClickListener(this.mCallback112);
        }
        if ((dirtyFlags & 98304) != 0) {
            this.phoneImageView.setOnFocusChangeListener(weatherViewModelWeatherViewFocusChangeListener2);
        }
        if ((dirtyFlags & 65600) != 0) {
            int i = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoTemperatureGet);
        } else {
            String str4 = weatherViewModelWeatherInfoTemperatureGet;
        }
        if ((dirtyFlags & 69636) != 0) {
            TextViewBindingAdapter.setText(this.textView2, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 73728) != 0) {
            long j = dirtyFlags;
            TextViewBindingAdapter.setText(this.unitWeather, weatherViewModelWeatherInfoErrorMessageGet);
            return;
        }
        String str5 = weatherViewModelWeatherInfoErrorMessageGet;
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        if (weatherViewModel != null) {
            weatherViewModel.openWeatherApp(callbackArg_0);
        }
    }
}

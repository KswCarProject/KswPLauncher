package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;
import com.wits.ksw.launcher.view.RollTextView;

/* loaded from: classes7.dex */
public class Id7SubWeatherViewBindingImpl extends Id7SubWeatherViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback173;
    private long mDirtyFlags;
    private final TextView mboundView10;
    private final ProgressBar mboundView2;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.ll_a, 14);
        sparseIntArray.put(C0899R.C0901id.ll_b, 15);
        sparseIntArray.put(C0899R.C0901id.ll_c, 16);
    }

    public Id7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private Id7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, (TextView) bindings[11], (TextView) bindings[12], (TextView) bindings[13], (ImageView) bindings[1], (RelativeLayout) bindings[14], (RelativeLayout) bindings[15], (RelativeLayout) bindings[16], (TextView) bindings[7], (CustomBmwImageView) bindings[5], (TextView) bindings[9], (RollTextView) bindings[6], (TextView) bindings[8], (ConstraintLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.btA.setTag(null);
        this.btB.setTag(null);
        this.btC.setTag(null);
        this.icon.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[2];
        this.mboundView2 = progressBar;
        progressBar.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[3];
        this.mboundView3 = linearLayout;
        linearLayout.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        this.phoneConnectionTextView.setTag(null);
        this.phoneImageView.setTag(null);
        this.temperatureTv.setTag(null);
        this.textView2.setTag(null);
        this.unitWeather.setTag(null);
        this.weatherConstraintLayout.setTag(null);
        setRootTag(root);
        this.mCallback173 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
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
        if (17 == variableId) {
            setWeatherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.Id7SubWeatherViewBinding
    public void setWeatherViewModel(LauncherViewModel WeatherViewModel) {
        this.mWeatherViewModel = WeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg3(ObservableField<Drawable> WeatherViewModelWeatherInfoId7CardDetailImg3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg1(ObservableField<Drawable> WeatherViewModelWeatherInfoId7CardDetailImg1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCity(ObservableField<String> WeatherViewModelWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText1(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText3(ObservableField<String> WeatherViewModelWeatherInfoCardDetailText3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> WeatherViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> WeatherViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId7CardDetailImg2(ObservableField<Drawable> WeatherViewModelWeatherInfoId7CardDetailImg2, int fieldId) {
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

    private boolean onChangeWeatherViewModelWeatherInfoImage(ObservableField<Drawable> WeatherViewModelWeatherInfoImage, int fieldId) {
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

    private boolean onChangeWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> WeatherViewModelWeatherInfoTemperUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> WeatherViewModelWeatherInfoTemperatureRange, int fieldId) {
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
        View.OnFocusChangeListener weatherViewModelWeatherViewFocusChangeListener;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg2Get;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        String weatherViewModelWeatherInfoTemperatureGet;
        Drawable weatherViewModelWeatherInfoImageGet;
        View.OnFocusChangeListener weatherViewModelWeatherViewFocusChangeListener2;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        String weatherViewModelWeatherInfoPhraseGet;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        WeatherInfo weatherViewModelWeatherInfo;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg1Get;
        String weatherViewModelWeatherInfoCardDetailText3Get;
        String weatherViewModelWeatherInfoErrorMessageGet;
        String weatherViewModelWeatherInfoErrorMessageGet2;
        String weatherViewModelWeatherInfoPhraseGet2;
        String weatherViewModelWeatherInfoErrorMessageGet3;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2;
        int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather;
        String weatherViewModelWeatherInfoCardDetailText1Get2;
        String weatherViewModelWeatherInfoErrorMessageGet4;
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
            this.mDirtyFlags = 0L;
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
        String weatherViewModelWeatherInfoErrorMessageGet5 = null;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess13 = null;
        Drawable weatherViewModelWeatherInfoId7CardDetailImg2Get2 = null;
        Drawable weatherViewModelWeatherInfoImageGet2 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        String weatherViewModelWeatherInfoPhraseGet3 = null;
        if ((dirtyFlags & 98304) != 0 && weatherViewModel != null) {
            weatherViewModelWeatherViewFocusChangeListener = weatherViewModel.weatherViewFocusChangeListener;
        } else {
            weatherViewModelWeatherViewFocusChangeListener = null;
        }
        if ((dirtyFlags & 98303) == 0) {
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
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            weatherViewModelWeatherInfoId7CardDetailImg1Get = null;
            weatherViewModelWeatherInfoCardDetailText3Get = null;
            weatherViewModelWeatherInfoErrorMessageGet = null;
            weatherViewModelWeatherInfoErrorMessageGet2 = null;
        } else {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 65537) == 0) {
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                weatherViewModelWeatherInfoId7CardDetailImg3 = null;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId7CardDetailImg3 = null;
                } else {
                    weatherViewModelWeatherInfoId7CardDetailImg3 = weatherViewModelWeatherInfo2.id7CardDetailImg3;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                updateRegistration(0, weatherViewModelWeatherInfoId7CardDetailImg3);
                if (weatherViewModelWeatherInfoId7CardDetailImg3 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg3Get = weatherViewModelWeatherInfoId7CardDetailImg3.get();
                }
            }
            if ((dirtyFlags & 65538) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg1 = weatherViewModelWeatherInfo2.id7CardDetailImg1;
                }
                updateRegistration(1, weatherViewModelWeatherInfoId7CardDetailImg1);
                if (weatherViewModelWeatherInfoId7CardDetailImg1 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg1Get2 = weatherViewModelWeatherInfoId7CardDetailImg1.get();
                }
            }
            if ((dirtyFlags & 65544) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(3, weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get3 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 65552) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(4, weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get2 = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 69664) == 0) {
                weatherViewModelWeatherInfoId7CardDetailImg32 = weatherViewModelWeatherInfoId7CardDetailImg3;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                }
                weatherViewModelWeatherInfoId7CardDetailImg32 = weatherViewModelWeatherInfoId7CardDetailImg3;
                updateRegistration(5, weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    Boolean weatherViewModelWeatherInfoIsInitFinishedGet2 = weatherViewModelWeatherInfoIsInitFinished.get();
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinishedGet2;
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
                if ((dirtyFlags & 65568) == 0) {
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperature = null;
                } else {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                }
                updateRegistration(6, weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperature.get();
                }
            }
            if ((dirtyFlags & 65664) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoId7CardDetailImg2 = null;
                } else {
                    weatherViewModelWeatherInfoId7CardDetailImg2 = weatherViewModelWeatherInfo2.id7CardDetailImg2;
                }
                updateRegistration(7, weatherViewModelWeatherInfoId7CardDetailImg2);
                if (weatherViewModelWeatherInfoId7CardDetailImg2 != null) {
                    weatherViewModelWeatherInfoId7CardDetailImg2Get2 = weatherViewModelWeatherInfoId7CardDetailImg2.get();
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
                    weatherViewModelWeatherInfoPhraseGet3 = weatherViewModelWeatherInfoPhrase.get();
                }
            }
            if ((dirtyFlags & 66048) != 0) {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoImage = null;
                } else {
                    weatherViewModelWeatherInfoImage = weatherViewModelWeatherInfo2.image;
                }
                updateRegistration(9, weatherViewModelWeatherInfoImage);
                if (weatherViewModelWeatherInfoImage != null) {
                    weatherViewModelWeatherInfoImageGet2 = weatherViewModelWeatherInfoImage.get();
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
            if ((dirtyFlags & 69636) != 0) {
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
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperUnit = null;
                } else {
                    weatherViewModelWeatherInfoTemperUnit = weatherViewModelWeatherInfo2.temperUnit;
                }
                updateRegistration(13, weatherViewModelWeatherInfoTemperUnit);
                if (weatherViewModelWeatherInfoTemperUnit != null) {
                    weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnit.get();
                }
            }
            if ((dirtyFlags & 81920) == 0) {
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoTemperUnitGet;
            } else {
                if (weatherViewModelWeatherInfo2 == null) {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                }
                updateRegistration(14, weatherViewModelWeatherInfoTemperatureRange);
                if (weatherViewModelWeatherInfoTemperatureRange == null) {
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                    weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoTemperUnitGet;
                } else {
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRange.get();
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoImageGet = weatherViewModelWeatherInfoImageGet2;
                    weatherViewModelWeatherViewFocusChangeListener2 = weatherViewModelWeatherViewFocusChangeListener;
                    weatherViewModelWeatherInfoPhraseGet = weatherViewModelWeatherInfoPhraseGet3;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoId7CardDetailImg2Get = weatherViewModelWeatherInfoId7CardDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet2;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoId7CardDetailImg1Get = weatherViewModelWeatherInfoId7CardDetailImg1Get2;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoTemperUnitGet;
                }
            }
        }
        if ((dirtyFlags & 5242880) == 0) {
            weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet;
            weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
        } else {
            if (weatherViewModelWeatherInfo == null) {
                weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = WeatherViewModelWeatherInfoIsLoadSuccess13;
            } else {
                weatherViewModelWeatherInfoPhraseGet2 = weatherViewModelWeatherInfoPhraseGet;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = weatherViewModelWeatherInfo.isLoadSuccess;
            }
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet;
            updateRegistration(12, WeatherViewModelWeatherInfoIsLoadSuccess1);
            if (WeatherViewModelWeatherInfoIsLoadSuccess1 == null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
            } else {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = WeatherViewModelWeatherInfoIsLoadSuccess1.get();
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
            }
        }
        if ((dirtyFlags & 69664) == 0) {
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        } else {
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
            int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        }
        if ((dirtyFlags & 268435456) != 0) {
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo != null ? weatherViewModelWeatherInfo.city : weatherViewModelWeatherInfoCity;
            updateRegistration(2, weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        } else {
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        }
        if ((dirtyFlags & 69636) == 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather = null;
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoCityGet : this.textView2.getResources().getString(C0899R.string.ksw_id8_weather);
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
            weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoErrorMessageGet3;
            TextViewBindingAdapter.setText(this.mboundView4, weatherViewModelWeatherInfoCardDetailText1Get2);
        } else {
            weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoErrorMessageGet3;
        }
        if ((dirtyFlags & 65792) != 0) {
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoPhraseGet2;
            TextViewBindingAdapter.setText(this.phoneConnectionTextView, weatherViewModelWeatherInfoErrorMessageGet4);
        } else {
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoPhraseGet2;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            this.phoneImageView.setOnClickListener(this.mCallback173);
        }
        if ((dirtyFlags & 98304) != 0) {
            this.phoneImageView.setOnFocusChangeListener(weatherViewModelWeatherViewFocusChangeListener2);
        }
        if ((dirtyFlags & 65600) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoTemperatureGet);
        }
        if ((dirtyFlags & 69636) != 0) {
            TextViewBindingAdapter.setText(this.textView2, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTextView2AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 73728) != 0) {
            TextViewBindingAdapter.setText(this.unitWeather, weatherViewModelWeatherInfoErrorMessageGet2);
        }
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

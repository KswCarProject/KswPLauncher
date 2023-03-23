package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.view.MarqueeTextView;

public class AlsId7SubWeatherViewBindingImpl extends AlsId7SubWeatherViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback254;
    private long mDirtyFlags;
    private final TextView mboundView10;
    private final ProgressBar mboundView2;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;
    private final MarqueeTextView mboundView5;
    private final MarqueeTextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView2, 11);
        sparseIntArray.put(R.id.textView3, 12);
    }

    public AlsId7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private AlsId7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, bindings[7], bindings[8], bindings[11], bindings[12], bindings[9], bindings[0], bindings[1]);
        this.mDirtyFlags = -1;
        this.ivIcon.setTag((Object) null);
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
        MarqueeTextView marqueeTextView = bindings[5];
        this.mboundView5 = marqueeTextView;
        marqueeTextView.setTag((Object) null);
        MarqueeTextView marqueeTextView2 = bindings[6];
        this.mboundView6 = marqueeTextView2;
        marqueeTextView2.setTag((Object) null);
        this.temperatureTv.setTag((Object) null);
        this.unitWeather.setTag((Object) null);
        this.videoConstraintLayout.setTag((Object) null);
        this.weatherImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback254 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
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
        if (18 != variableId) {
            return false;
        }
        setZlinkWeatherViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel) {
        this.mZlinkWeatherViewModel = ZlinkWeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(18);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeZlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon((ObservableField) object, fieldId);
            case 1:
                return onChangeZlinkWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 2:
                return onChangeZlinkWeatherViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 3:
                return onChangeZlinkWeatherViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 4:
                return onChangeZlinkWeatherViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 5:
                return onChangeZlinkWeatherViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 6:
                return onChangeZlinkWeatherViewModelWeatherInfoCity((ObservableField) object, fieldId);
            case 7:
                return onChangeZlinkWeatherViewModelWeatherInfoPhrase((ObservableField) object, fieldId);
            case 8:
                return onChangeZlinkWeatherViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoErrorMessage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoCity(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoPhrase(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperature(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather;
        int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        String zlinkWeatherViewModelWeatherInfoTemperatureGet;
        String zlinkWeatherViewModelWeatherInfoErrorMessageGet;
        WeatherInfo zlinkWeatherViewModelWeatherInfo;
        String zlinkWeatherViewModelWeatherInfoTemperatureRangeGet;
        int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        int zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoPhrase;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoCity;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoTemperature;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoTemperatureRange;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoErrorMessage;
        ObservableField<Boolean> zlinkWeatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int zlinkWeatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
        ObservableField<Drawable> zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon = null;
        String zlinkWeatherViewModelWeatherInfoTemperUnitGet = null;
        Drawable zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIconGet = null;
        ObservableField<Boolean> zlinkWeatherViewModelWeatherInfoIsLoadSuccess = null;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoTemperUnit = null;
        Boolean zlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = null;
        String zlinkWeatherViewModelWeatherInfoCityGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        String zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2 = null;
        Boolean zlinkWeatherViewModelWeatherInfoIsInitFinishedGet = null;
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        String zlinkWeatherViewModelWeatherInfoErrorMessageGet2 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        boolean ZlinkWeatherViewModelWeatherInfoIsLoadSuccess1 = false;
        String zlinkWeatherViewModelWeatherInfoPhraseGet = null;
        if ((dirtyFlags & 1535) != 0) {
            WeatherInfo zlinkWeatherViewModelWeatherInfo2 = AlsID7ViewModel.weatherInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon = zlinkWeatherViewModelWeatherInfo2.id7AlsV2ImageIcon;
                }
                AlsID7ViewModel alsID7ViewModel = zlinkWeatherViewModel;
                updateRegistration(0, (Observable) zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon);
                if (zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon != null) {
                    zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIconGet = zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon.get();
                }
            }
            if ((dirtyFlags & 1218) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoIsLoadSuccess = zlinkWeatherViewModelWeatherInfo2.isLoadSuccess;
                }
                updateRegistration(1, (Observable) zlinkWeatherViewModelWeatherInfoIsLoadSuccess);
                if (zlinkWeatherViewModelWeatherInfoIsLoadSuccess != null) {
                    zlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = zlinkWeatherViewModelWeatherInfoIsLoadSuccess.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(zlinkWeatherViewModelWeatherInfoIsLoadSuccessGet);
                if ((dirtyFlags & 1154) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                if ((dirtyFlags & 1090) == 0) {
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 16777216;
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= 8388608;
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 1028) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoTemperUnit = zlinkWeatherViewModelWeatherInfo2.temperUnit;
                }
                updateRegistration(2, (Observable) zlinkWeatherViewModelWeatherInfoTemperUnit);
                if (zlinkWeatherViewModelWeatherInfoTemperUnit != null) {
                    zlinkWeatherViewModelWeatherInfoTemperUnitGet = zlinkWeatherViewModelWeatherInfoTemperUnit.get();
                }
            }
            if ((dirtyFlags & 1034) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoIsInitFinished = zlinkWeatherViewModelWeatherInfo2.isInitFinished;
                } else {
                    zlinkWeatherViewModelWeatherInfoIsInitFinished = null;
                }
                zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
                updateRegistration(3, (Observable) zlinkWeatherViewModelWeatherInfoIsInitFinished);
                if (zlinkWeatherViewModelWeatherInfoIsInitFinished != null) {
                    zlinkWeatherViewModelWeatherInfoIsInitFinishedGet = zlinkWeatherViewModelWeatherInfoIsInitFinished.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2 = ViewDataBinding.safeUnbox(zlinkWeatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 1032) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                if ((dirtyFlags & 1034) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE | 4194304;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                if ((dirtyFlags & 1032) != 0) {
                    zlinkWeatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    ObservableField<Boolean> observableField = zlinkWeatherViewModelWeatherInfoIsInitFinished;
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    ObservableField<Boolean> observableField2 = zlinkWeatherViewModelWeatherInfoIsInitFinished;
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2;
                }
            } else {
                zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
            }
            if ((dirtyFlags & 1040) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoErrorMessage = zlinkWeatherViewModelWeatherInfo2.errorMessage;
                } else {
                    zlinkWeatherViewModelWeatherInfoErrorMessage = null;
                }
                updateRegistration(4, (Observable) zlinkWeatherViewModelWeatherInfoErrorMessage);
                if (zlinkWeatherViewModelWeatherInfoErrorMessage != null) {
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet2 = zlinkWeatherViewModelWeatherInfoErrorMessage.get();
                    ObservableField<String> observableField3 = zlinkWeatherViewModelWeatherInfoErrorMessage;
                } else {
                    ObservableField<String> observableField4 = zlinkWeatherViewModelWeatherInfoErrorMessage;
                }
            }
            if ((dirtyFlags & 1056) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoTemperatureRange = zlinkWeatherViewModelWeatherInfo2.temperatureRange;
                } else {
                    zlinkWeatherViewModelWeatherInfoTemperatureRange = null;
                }
                updateRegistration(5, (Observable) zlinkWeatherViewModelWeatherInfoTemperatureRange);
                if (zlinkWeatherViewModelWeatherInfoTemperatureRange != null) {
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2 = zlinkWeatherViewModelWeatherInfoTemperatureRange.get();
                    ObservableField<String> observableField5 = zlinkWeatherViewModelWeatherInfoTemperatureRange;
                } else {
                    ObservableField<String> observableField6 = zlinkWeatherViewModelWeatherInfoTemperatureRange;
                }
            }
            if ((dirtyFlags & 1280) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoTemperature = zlinkWeatherViewModelWeatherInfo2.temperature;
                } else {
                    zlinkWeatherViewModelWeatherInfoTemperature = null;
                }
                updateRegistration(8, (Observable) zlinkWeatherViewModelWeatherInfoTemperature);
                if (zlinkWeatherViewModelWeatherInfoTemperature != null) {
                    ObservableField<String> observableField7 = zlinkWeatherViewModelWeatherInfoTemperature;
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                    zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                    ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    zlinkWeatherViewModelWeatherInfoTemperatureGet = zlinkWeatherViewModelWeatherInfoTemperature.get();
                } else {
                    ObservableField<String> observableField8 = zlinkWeatherViewModelWeatherInfoTemperature;
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                    zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                    ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
                }
            } else {
                zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
            }
        } else {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
            zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = null;
            zlinkWeatherViewModelWeatherInfo = null;
            zlinkWeatherViewModelWeatherInfoErrorMessageGet = null;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
            zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
        }
        if ((dirtyFlags & 4456448) != 0) {
            if (zlinkWeatherViewModelWeatherInfo != null) {
                zlinkWeatherViewModelWeatherInfoIsLoadSuccess = zlinkWeatherViewModelWeatherInfo.isLoadSuccess;
            }
            ObservableField<Drawable> observableField9 = zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon;
            updateRegistration(1, (Observable) zlinkWeatherViewModelWeatherInfoIsLoadSuccess);
            if (zlinkWeatherViewModelWeatherInfoIsLoadSuccess != null) {
                zlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = zlinkWeatherViewModelWeatherInfoIsLoadSuccess.get();
            }
            androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(zlinkWeatherViewModelWeatherInfoIsLoadSuccessGet);
            if ((dirtyFlags & 1154) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            if ((dirtyFlags & 1090) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 16777216;
                } else {
                    dirtyFlags |= 8388608;
                }
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
                ZlinkWeatherViewModelWeatherInfoIsLoadSuccess1 = !androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet;
            }
        }
        if ((dirtyFlags & 1034) != 0) {
            int zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet ? ZlinkWeatherViewModelWeatherInfoIsLoadSuccess1 : false;
            boolean ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            if ((dirtyFlags & 1034) != 0) {
                if (zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse == true) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
            }
            if ((dirtyFlags & 1034) != 0) {
                if (ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
            }
            zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse != 0 ? 0 : 4;
            int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE13 = zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
        } else {
            zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        }
        if ((dirtyFlags & 16777216) != 0) {
            if (zlinkWeatherViewModelWeatherInfo != null) {
                ObservableField<Boolean> observableField10 = zlinkWeatherViewModelWeatherInfoIsLoadSuccess;
                zlinkWeatherViewModelWeatherInfoCity = zlinkWeatherViewModelWeatherInfo.city;
            } else {
                zlinkWeatherViewModelWeatherInfoCity = null;
            }
            ObservableField<String> observableField11 = zlinkWeatherViewModelWeatherInfoTemperUnit;
            updateRegistration(6, (Observable) zlinkWeatherViewModelWeatherInfoCity);
            if (zlinkWeatherViewModelWeatherInfoCity != null) {
                zlinkWeatherViewModelWeatherInfoCityGet = zlinkWeatherViewModelWeatherInfoCity.get();
                ObservableField<String> observableField12 = zlinkWeatherViewModelWeatherInfoCity;
            } else {
                ObservableField<String> observableField13 = zlinkWeatherViewModelWeatherInfoCity;
            }
        } else {
            ObservableField<String> observableField14 = zlinkWeatherViewModelWeatherInfoTemperUnit;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
            if (zlinkWeatherViewModelWeatherInfo != null) {
                zlinkWeatherViewModelWeatherInfoPhrase = zlinkWeatherViewModelWeatherInfo.phrase;
            } else {
                zlinkWeatherViewModelWeatherInfoPhrase = null;
            }
            updateRegistration(7, (Observable) zlinkWeatherViewModelWeatherInfoPhrase);
            if (zlinkWeatherViewModelWeatherInfoPhrase != null) {
                zlinkWeatherViewModelWeatherInfoPhraseGet = zlinkWeatherViewModelWeatherInfoPhrase.get();
                ObservableField<String> observableField15 = zlinkWeatherViewModelWeatherInfoPhrase;
            } else {
                ObservableField<String> observableField16 = zlinkWeatherViewModelWeatherInfoPhrase;
            }
        }
        if ((dirtyFlags & 1154) != 0) {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet ? zlinkWeatherViewModelWeatherInfoPhraseGet : this.mboundView6.getResources().getString(R.string.ksw_id8_weather);
        } else {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather = null;
        }
        if ((dirtyFlags & 1090) != 0) {
            WeatherInfo weatherInfo = zlinkWeatherViewModelWeatherInfo;
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet ? zlinkWeatherViewModelWeatherInfoCityGet : this.mboundView5.getResources().getString(R.string.ksw_id8_weather);
        } else {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2 = zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather;
        }
        if ((dirtyFlags & 1025) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIconGet);
        }
        if ((dirtyFlags & 1034) != 0) {
            this.ivIcon.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView10.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView3.setVisibility(zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView5.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView6.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.temperatureTv.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.unitWeather.setVisibility(ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
        }
        if ((dirtyFlags & 1056) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, zlinkWeatherViewModelWeatherInfoTemperatureRangeGet);
        }
        if ((dirtyFlags & 1032) != 0) {
            this.mboundView2.setVisibility(zlinkWeatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 1040) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, zlinkWeatherViewModelWeatherInfoErrorMessageGet);
        }
        if ((dirtyFlags & 1090) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2);
        }
        if ((dirtyFlags & 1154) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 1280) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, zlinkWeatherViewModelWeatherInfoTemperatureGet);
        }
        if ((dirtyFlags & 1028) != 0) {
            TextViewBindingAdapter.setText(this.unitWeather, zlinkWeatherViewModelWeatherInfoTemperUnitGet);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            String str = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet;
            this.weatherImageView.setOnClickListener(this.mCallback254);
            return;
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        if (zlinkWeatherViewModel != null) {
            zlinkWeatherViewModel.openWeatherApp(callbackArg_0);
        }
    }
}

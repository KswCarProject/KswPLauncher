package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.CapitalizeTextView;
import com.wits.ksw.launcher.view.CustomBmwImageView;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public class AlsId7SubWeatherViewBindingImpl extends AlsId7SubWeatherViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback374;
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
        sparseIntArray.put(C0899R.C0901id.textView2, 11);
        sparseIntArray.put(C0899R.C0901id.textView3, 12);
    }

    public AlsId7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private AlsId7SubWeatherViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, (ImageView) bindings[7], (CustomFontTextView) bindings[8], (CapitalizeTextView) bindings[11], (TextView) bindings[12], (TextView) bindings[9], (ConstraintLayout) bindings[0], (CustomBmwImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.ivIcon.setTag(null);
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
        MarqueeTextView marqueeTextView = (MarqueeTextView) bindings[5];
        this.mboundView5 = marqueeTextView;
        marqueeTextView.setTag(null);
        MarqueeTextView marqueeTextView2 = (MarqueeTextView) bindings[6];
        this.mboundView6 = marqueeTextView2;
        marqueeTextView2.setTag(null);
        this.temperatureTv.setTag(null);
        this.unitWeather.setTag(null);
        this.videoConstraintLayout.setTag(null);
        this.weatherImageView.setTag(null);
        setRootTag(root);
        this.mCallback374 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
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
        if (18 == variableId) {
            setZlinkWeatherViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubWeatherViewBinding
    public void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel) {
        this.mZlinkWeatherViewModel = ZlinkWeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(18);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeZlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon(ObservableField<Drawable> ZlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> ZlinkWeatherViewModelWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> ZlinkWeatherViewModelWeatherInfoTemperUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> ZlinkWeatherViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoErrorMessage(ObservableField<String> ZlinkWeatherViewModelWeatherInfoErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> ZlinkWeatherViewModelWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoCity(ObservableField<String> ZlinkWeatherViewModelWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoPhrase(ObservableField<String> ZlinkWeatherViewModelWeatherInfoPhrase, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeZlinkWeatherViewModelWeatherInfoTemperature(ObservableField<String> ZlinkWeatherViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather;
        String zlinkWeatherViewModelWeatherInfoTemperatureRangeGet;
        WeatherInfo zlinkWeatherViewModelWeatherInfo;
        String zlinkWeatherViewModelWeatherInfoErrorMessageGet;
        int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        String zlinkWeatherViewModelWeatherInfoTemperatureGet;
        int zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather;
        String zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoPhrase;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoTemperature;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoTemperatureRange;
        ObservableField<String> zlinkWeatherViewModelWeatherInfoErrorMessage;
        ObservableField<Boolean> zlinkWeatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
        AlsID7ViewModel alsID7ViewModel = this.mZlinkWeatherViewModel;
        String zlinkWeatherViewModelWeatherInfoErrorMessageGet2 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        boolean ZlinkWeatherViewModelWeatherInfoIsLoadSuccess1 = false;
        String zlinkWeatherViewModelWeatherInfoPhraseGet = null;
        if ((dirtyFlags & 1535) == 0) {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
            zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = null;
            zlinkWeatherViewModelWeatherInfo = null;
            zlinkWeatherViewModelWeatherInfoErrorMessageGet = null;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
            zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
        } else {
            WeatherInfo zlinkWeatherViewModelWeatherInfo2 = AlsID7ViewModel.weatherInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon = zlinkWeatherViewModelWeatherInfo2.id7AlsV2ImageIcon;
                }
                updateRegistration(0, zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon);
                if (zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon != null) {
                    zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIconGet = zlinkWeatherViewModelWeatherInfoId7AlsV2ImageIcon.get();
                }
            }
            if ((dirtyFlags & 1218) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 != null) {
                    zlinkWeatherViewModelWeatherInfoIsLoadSuccess = zlinkWeatherViewModelWeatherInfo2.isLoadSuccess;
                }
                updateRegistration(1, zlinkWeatherViewModelWeatherInfoIsLoadSuccess);
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
                updateRegistration(2, zlinkWeatherViewModelWeatherInfoTemperUnit);
                if (zlinkWeatherViewModelWeatherInfoTemperUnit != null) {
                    zlinkWeatherViewModelWeatherInfoTemperUnitGet = zlinkWeatherViewModelWeatherInfoTemperUnit.get();
                }
            }
            if ((dirtyFlags & 1034) == 0) {
                zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
            } else {
                if (zlinkWeatherViewModelWeatherInfo2 == null) {
                    zlinkWeatherViewModelWeatherInfoIsInitFinished = null;
                } else {
                    zlinkWeatherViewModelWeatherInfoIsInitFinished = zlinkWeatherViewModelWeatherInfo2.isInitFinished;
                }
                zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
                updateRegistration(3, zlinkWeatherViewModelWeatherInfoIsInitFinished);
                if (zlinkWeatherViewModelWeatherInfoIsInitFinished != null) {
                    Boolean zlinkWeatherViewModelWeatherInfoIsInitFinishedGet2 = zlinkWeatherViewModelWeatherInfoIsInitFinished.get();
                    zlinkWeatherViewModelWeatherInfoIsInitFinishedGet = zlinkWeatherViewModelWeatherInfoIsInitFinishedGet2;
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
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet2;
                }
            }
            if ((dirtyFlags & 1040) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 == null) {
                    zlinkWeatherViewModelWeatherInfoErrorMessage = null;
                } else {
                    zlinkWeatherViewModelWeatherInfoErrorMessage = zlinkWeatherViewModelWeatherInfo2.errorMessage;
                }
                updateRegistration(4, zlinkWeatherViewModelWeatherInfoErrorMessage);
                if (zlinkWeatherViewModelWeatherInfoErrorMessage != null) {
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet2 = zlinkWeatherViewModelWeatherInfoErrorMessage.get();
                }
            }
            if ((dirtyFlags & 1056) != 0) {
                if (zlinkWeatherViewModelWeatherInfo2 == null) {
                    zlinkWeatherViewModelWeatherInfoTemperatureRange = null;
                } else {
                    zlinkWeatherViewModelWeatherInfoTemperatureRange = zlinkWeatherViewModelWeatherInfo2.temperatureRange;
                }
                updateRegistration(5, zlinkWeatherViewModelWeatherInfoTemperatureRange);
                if (zlinkWeatherViewModelWeatherInfoTemperatureRange != null) {
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2 = zlinkWeatherViewModelWeatherInfoTemperatureRange.get();
                }
            }
            if ((dirtyFlags & 1280) == 0) {
                zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
            } else {
                if (zlinkWeatherViewModelWeatherInfo2 == null) {
                    zlinkWeatherViewModelWeatherInfoTemperature = null;
                } else {
                    zlinkWeatherViewModelWeatherInfoTemperature = zlinkWeatherViewModelWeatherInfo2.temperature;
                }
                updateRegistration(8, zlinkWeatherViewModelWeatherInfoTemperature);
                if (zlinkWeatherViewModelWeatherInfoTemperature == null) {
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                    zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                    ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    zlinkWeatherViewModelWeatherInfoTemperatureGet = null;
                } else {
                    String zlinkWeatherViewModelWeatherInfoTemperatureGet2 = zlinkWeatherViewModelWeatherInfoTemperature.get();
                    String zlinkWeatherViewModelWeatherInfoTemperatureGet3 = zlinkWeatherViewModelWeatherInfoTemperatureGet2;
                    zlinkWeatherViewModelWeatherInfoErrorMessageGet = zlinkWeatherViewModelWeatherInfoErrorMessageGet2;
                    zlinkWeatherViewModelWeatherInfo = zlinkWeatherViewModelWeatherInfo2;
                    zlinkWeatherViewModelWeatherInfoTemperatureRangeGet = zlinkWeatherViewModelWeatherInfoTemperatureRangeGet2;
                    ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    zlinkWeatherViewModelWeatherInfoTemperatureGet = zlinkWeatherViewModelWeatherInfoTemperatureGet3;
                }
            }
        }
        if ((dirtyFlags & 4456448) != 0) {
            if (zlinkWeatherViewModelWeatherInfo != null) {
                zlinkWeatherViewModelWeatherInfoIsLoadSuccess = zlinkWeatherViewModelWeatherInfo.isLoadSuccess;
            }
            updateRegistration(1, zlinkWeatherViewModelWeatherInfoIsLoadSuccess);
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
        if ((dirtyFlags & 1034) == 0) {
            zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        } else {
            boolean zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet ? ZlinkWeatherViewModelWeatherInfoIsLoadSuccess1 : false;
            boolean ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            if ((dirtyFlags & 1034) != 0) {
                if (zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
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
            zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = zlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            int ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE13 = ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = ZlinkWeatherViewModelWeatherInfoIsInitFinishedZlinkWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE13;
        }
        if ((dirtyFlags & 16777216) != 0) {
            ObservableField<String> zlinkWeatherViewModelWeatherInfoCity = zlinkWeatherViewModelWeatherInfo != null ? zlinkWeatherViewModelWeatherInfo.city : null;
            updateRegistration(6, zlinkWeatherViewModelWeatherInfoCity);
            if (zlinkWeatherViewModelWeatherInfoCity != null) {
                zlinkWeatherViewModelWeatherInfoCityGet = zlinkWeatherViewModelWeatherInfoCity.get();
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
            if (zlinkWeatherViewModelWeatherInfo == null) {
                zlinkWeatherViewModelWeatherInfoPhrase = null;
            } else {
                zlinkWeatherViewModelWeatherInfoPhrase = zlinkWeatherViewModelWeatherInfo.phrase;
            }
            updateRegistration(7, zlinkWeatherViewModelWeatherInfoPhrase);
            if (zlinkWeatherViewModelWeatherInfoPhrase != null) {
                zlinkWeatherViewModelWeatherInfoPhraseGet = zlinkWeatherViewModelWeatherInfoPhrase.get();
            }
        }
        if ((dirtyFlags & 1154) == 0) {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather = null;
        } else {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoPhraseMboundView6AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet ? zlinkWeatherViewModelWeatherInfoPhraseGet : this.mboundView6.getResources().getString(C0899R.string.ksw_id8_weather);
        }
        if ((dirtyFlags & 1090) != 0) {
            zlinkWeatherViewModelWeatherInfoIsLoadSuccessZlinkWeatherViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxZlinkWeatherViewModelWeatherInfoIsLoadSuccessGet ? zlinkWeatherViewModelWeatherInfoCityGet : this.mboundView5.getResources().getString(C0899R.string.ksw_id8_weather);
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
            this.weatherImageView.setOnClickListener(this.mCallback374);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        boolean zlinkWeatherViewModelJavaLangObjectNull = zlinkWeatherViewModel != null;
        if (zlinkWeatherViewModelJavaLangObjectNull) {
            zlinkWeatherViewModel.openWeatherApp(callbackArg_0);
        }
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.RomeoViewModelV2;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public class ActivityRomeoV2BindingImpl extends ActivityRomeoV2Binding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback298;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView11;
    private final ProgressBar mboundView2;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;
    private final MarqueeTextView mboundView5;
    private final MarqueeTextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.romeo_navi, 12);
        sparseIntArray.put(C0899R.C0901id.romeo_music, 13);
        sparseIntArray.put(C0899R.C0901id.romeo_video, 14);
        sparseIntArray.put(C0899R.C0901id.romeo_phone, 15);
        sparseIntArray.put(C0899R.C0901id.romeo_app, 16);
        sparseIntArray.put(C0899R.C0901id.romeo_setting, 17);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_1, 18);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_2, 19);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_3, 20);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_4, 21);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_5, 22);
        sparseIntArray.put(C0899R.C0901id.romeo_indicator_6, 23);
        sparseIntArray.put(C0899R.C0901id.page_indicator1, 24);
        sparseIntArray.put(C0899R.C0901id.page_indicator2, 25);
        sparseIntArray.put(C0899R.C0901id.romeo_main_rv, 26);
        sparseIntArray.put(C0899R.C0901id.videoConstraintLayout, 27);
    }

    public ActivityRomeoV2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }

    private ActivityRomeoV2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (TextView) bindings[6], (ImageView) bindings[8], (ImageView) bindings[24], (ImageView) bindings[25], (ImageView) bindings[16], (ImageView) bindings[18], (ImageView) bindings[19], (ImageView) bindings[20], (ImageView) bindings[21], (ImageView) bindings[22], (ImageView) bindings[23], (RecyclerView) bindings[26], (ImageView) bindings[13], (ImageView) bindings[12], (ImageView) bindings[15], (ImageView) bindings[17], (ImageView) bindings[14], (TextView) bindings[9], (TextView) bindings[10], (ConstraintLayout) bindings[27], (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.dateTv.setTag(null);
        this.ivIcon.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
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
        MarqueeTextView marqueeTextView2 = (MarqueeTextView) bindings[7];
        this.mboundView7 = marqueeTextView2;
        marqueeTextView2.setTag(null);
        this.temperatureTv.setTag(null);
        this.unitWeather.setTag(null);
        this.weatherImageView.setTag(null);
        setRootTag(root);
        this.mCallback298 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
            setViewModel((RomeoViewModelV2) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityRomeoV2Binding
    public void setViewModel(RomeoViewModelV2 ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelWeatherInfoPhrase((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelWeatherInfoDate((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelWeatherInfoRomeoV2ImageIcon((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelWeatherInfoCity((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelWeatherInfoTemperatureRange(ObservableField<String> ViewModelWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoErrorMessage(ObservableField<String> ViewModelWeatherInfoErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoTemperature(ObservableField<String> ViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoPhrase(ObservableField<String> ViewModelWeatherInfoPhrase, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoDate(ObservableField<String> ViewModelWeatherInfoDate, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoRomeoV2ImageIcon(ObservableField<Drawable> ViewModelWeatherInfoRomeoV2ImageIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> ViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoTemperUnit(ObservableField<String> ViewModelWeatherInfoTemperUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoCity(ObservableField<String> ViewModelWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String viewModelWeatherInfoTemperatureGet;
        int viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        ObservableField<String> viewModelWeatherInfoCity;
        String viewModelWeatherInfoTemperatureRangeGet;
        String viewModelWeatherInfoTemperatureRangeGet2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather;
        Boolean viewModelWeatherInfoIsLoadSuccessGet;
        Drawable viewModelWeatherInfoRomeoV2ImageIconGet;
        int viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        int ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather;
        ObservableField<String> viewModelWeatherInfoCity2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoPhraseMboundView7AndroidStringKswId8Weather;
        ObservableField<String> viewModelWeatherInfoDate;
        ObservableField<String> viewModelWeatherInfoTemperatureRange;
        ObservableField<String> viewModelWeatherInfoTemperatureRange2;
        ObservableField<String> viewModelWeatherInfoTemperUnit;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess1;
        ObservableField<Boolean> viewModelWeatherInfoIsInitFinished;
        ObservableField<Drawable> viewModelWeatherInfoRomeoV2ImageIcon;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Boolean viewModelWeatherInfoIsLoadSuccessGet2 = null;
        WeatherInfo viewModelWeatherInfo = null;
        String viewModelWeatherInfoTemperUnitGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = false;
        ObservableField<String> viewModelWeatherInfoErrorMessage = null;
        ObservableField<String> viewModelWeatherInfoTemperature = null;
        ObservableField<String> viewModelWeatherInfoPhrase = null;
        String viewModelWeatherInfoDateGet = null;
        String viewModelWeatherInfoTemperatureGet2 = null;
        int viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
        String viewModelWeatherInfoCityGet = null;
        boolean viewModelWeatherInfoIsLoadSuccess = false;
        String viewModelWeatherInfoTemperatureRangeGet3 = null;
        String viewModelWeatherInfoPhraseGet = null;
        String viewModelWeatherInfoErrorMessageGet = null;
        Drawable viewModelWeatherInfoRomeoV2ImageIconGet2 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess12 = null;
        Boolean viewModelWeatherInfoIsInitFinishedGet = null;
        RomeoViewModelV2 romeoViewModelV2 = this.mViewModel;
        if ((dirtyFlags & 3071) == 0) {
            viewModelWeatherInfoTemperatureGet = null;
            viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            viewModelWeatherInfoCity = null;
            viewModelWeatherInfoTemperatureRangeGet = null;
            viewModelWeatherInfoTemperatureRangeGet2 = null;
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather = null;
            viewModelWeatherInfoIsLoadSuccessGet = null;
            viewModelWeatherInfoRomeoV2ImageIconGet = null;
        } else {
            viewModelWeatherInfo = RomeoViewModelV2.weatherInfo;
            if ((dirtyFlags & 2049) == 0) {
                viewModelWeatherInfoTemperatureRange = null;
            } else {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoTemperatureRange = null;
                } else {
                    viewModelWeatherInfoTemperatureRange = viewModelWeatherInfo.temperatureRange;
                }
                updateRegistration(0, viewModelWeatherInfoTemperatureRange);
                if (viewModelWeatherInfoTemperatureRange != null) {
                    viewModelWeatherInfoTemperatureRangeGet3 = viewModelWeatherInfoTemperatureRange.get();
                }
            }
            if ((dirtyFlags & 2050) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoErrorMessage = viewModelWeatherInfo.errorMessage;
                }
                updateRegistration(1, viewModelWeatherInfoErrorMessage);
                if (viewModelWeatherInfoErrorMessage != null) {
                    viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessage.get();
                }
            }
            if ((dirtyFlags & 2052) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoTemperature = viewModelWeatherInfo.temperature;
                }
                updateRegistration(2, viewModelWeatherInfoTemperature);
                if (viewModelWeatherInfoTemperature != null) {
                    viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoTemperature.get();
                }
            }
            if ((dirtyFlags & 2080) == 0) {
                viewModelWeatherInfoTemperatureRange2 = viewModelWeatherInfoTemperatureRange;
            } else {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoRomeoV2ImageIcon = null;
                } else {
                    viewModelWeatherInfoRomeoV2ImageIcon = viewModelWeatherInfo.romeoV2ImageIcon;
                }
                viewModelWeatherInfoTemperatureRange2 = viewModelWeatherInfoTemperatureRange;
                updateRegistration(5, viewModelWeatherInfoRomeoV2ImageIcon);
                if (viewModelWeatherInfoRomeoV2ImageIcon != null) {
                    viewModelWeatherInfoRomeoV2ImageIconGet2 = viewModelWeatherInfoRomeoV2ImageIcon.get();
                }
            }
            if ((dirtyFlags & 2240) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoIsInitFinished = null;
                } else {
                    viewModelWeatherInfoIsInitFinished = viewModelWeatherInfo.isInitFinished;
                }
                updateRegistration(6, viewModelWeatherInfoIsInitFinished);
                if (viewModelWeatherInfoIsInitFinished != null) {
                    Boolean viewModelWeatherInfoIsInitFinishedGet2 = viewModelWeatherInfoIsInitFinished.get();
                    viewModelWeatherInfoIsInitFinishedGet = viewModelWeatherInfoIsInitFinishedGet2;
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2 = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 2112) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    }
                }
                if ((dirtyFlags & 2240) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE | 8388608;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED | 4194304;
                    }
                }
                if ((dirtyFlags & 2112) != 0) {
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2;
                } else {
                    androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet2;
                }
            }
            if ((dirtyFlags & 2712) != 0) {
                if (viewModelWeatherInfo == null) {
                    ViewModelWeatherInfoIsLoadSuccess1 = null;
                } else {
                    ViewModelWeatherInfoIsLoadSuccess1 = viewModelWeatherInfo.isLoadSuccess;
                }
                updateRegistration(7, ViewModelWeatherInfoIsLoadSuccess1);
                if (ViewModelWeatherInfoIsLoadSuccess1 != null) {
                    viewModelWeatherInfoIsLoadSuccessGet2 = ViewModelWeatherInfoIsLoadSuccess1.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsLoadSuccessGet2);
                if ((dirtyFlags & 2192) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                if ((dirtyFlags & 2688) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                if ((dirtyFlags & 2184) == 0) {
                    ViewModelWeatherInfoIsLoadSuccess12 = ViewModelWeatherInfoIsLoadSuccess1;
                    androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 134217728;
                    ViewModelWeatherInfoIsLoadSuccess12 = ViewModelWeatherInfoIsLoadSuccess1;
                    androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= 67108864;
                    ViewModelWeatherInfoIsLoadSuccess12 = ViewModelWeatherInfoIsLoadSuccess1;
                    androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 2304) == 0) {
                viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet2;
                viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                viewModelWeatherInfoCity = null;
                viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                viewModelWeatherInfoTemperatureRangeGet2 = null;
                viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather = viewModelWeatherInfoErrorMessageGet;
                viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet2;
                viewModelWeatherInfoRomeoV2ImageIconGet = viewModelWeatherInfoRomeoV2ImageIconGet2;
            } else {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoTemperUnit = null;
                } else {
                    viewModelWeatherInfoTemperUnit = viewModelWeatherInfo.temperUnit;
                }
                updateRegistration(8, viewModelWeatherInfoTemperUnit);
                if (viewModelWeatherInfoTemperUnit == null) {
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoCity = null;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                    viewModelWeatherInfoTemperatureRangeGet2 = null;
                    viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather = viewModelWeatherInfoErrorMessageGet;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet2;
                    viewModelWeatherInfoRomeoV2ImageIconGet = viewModelWeatherInfoRomeoV2ImageIconGet2;
                } else {
                    viewModelWeatherInfoTemperUnitGet = viewModelWeatherInfoTemperUnit.get();
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoCity = null;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                    viewModelWeatherInfoTemperatureRangeGet2 = null;
                    viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather = viewModelWeatherInfoErrorMessageGet;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet2;
                    viewModelWeatherInfoRomeoV2ImageIconGet = viewModelWeatherInfoRomeoV2ImageIconGet2;
                }
            }
        }
        if ((dirtyFlags & 10485760) != 0) {
            ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess13 = viewModelWeatherInfo != null ? viewModelWeatherInfo.isLoadSuccess : ViewModelWeatherInfoIsLoadSuccess12;
            updateRegistration(7, ViewModelWeatherInfoIsLoadSuccess13);
            if (ViewModelWeatherInfoIsLoadSuccess13 != null) {
                viewModelWeatherInfoIsLoadSuccessGet = ViewModelWeatherInfoIsLoadSuccess13.get();
            }
            boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3 = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsLoadSuccessGet);
            if ((dirtyFlags & 2192) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 2688) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                }
            }
            if ((dirtyFlags & 2184) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3) {
                    dirtyFlags |= 134217728;
                } else {
                    dirtyFlags |= 67108864;
                }
            }
            if ((dirtyFlags & 8388608) != 0) {
                viewModelWeatherInfoIsLoadSuccess = !androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3;
                androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3;
            } else {
                androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet3;
            }
        }
        if ((dirtyFlags & 2240) == 0) {
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
        } else {
            boolean viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet : false;
            boolean ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet ? viewModelWeatherInfoIsLoadSuccess : false;
            if ((dirtyFlags & 2240) != 0) {
                if (viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= 33554432;
                } else {
                    dirtyFlags |= 16777216;
                }
            }
            if ((dirtyFlags & 2240) != 0) {
                if (ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
            }
            int ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            int viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        }
        if ((dirtyFlags & 134217728) == 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
        } else {
            if (viewModelWeatherInfo != null) {
                viewModelWeatherInfoPhrase = viewModelWeatherInfo.phrase;
            }
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather = null;
            updateRegistration(3, viewModelWeatherInfoPhrase);
            if (viewModelWeatherInfoPhrase != null) {
                viewModelWeatherInfoPhraseGet = viewModelWeatherInfoPhrase.get();
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            if (viewModelWeatherInfo == null) {
                viewModelWeatherInfoDate = null;
            } else {
                viewModelWeatherInfoDate = viewModelWeatherInfo.date;
            }
            updateRegistration(4, viewModelWeatherInfoDate);
            if (viewModelWeatherInfoDate != null) {
                viewModelWeatherInfoDateGet = viewModelWeatherInfoDate.get();
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) == 0) {
            viewModelWeatherInfoCity2 = viewModelWeatherInfoCity;
        } else {
            if (viewModelWeatherInfo == null) {
                viewModelWeatherInfoCity2 = viewModelWeatherInfoCity;
            } else {
                viewModelWeatherInfoCity2 = viewModelWeatherInfo.city;
            }
            updateRegistration(9, viewModelWeatherInfoCity2);
            if (viewModelWeatherInfoCity2 != null) {
                viewModelWeatherInfoCityGet = viewModelWeatherInfoCity2.get();
            }
        }
        if ((dirtyFlags & 2192) == 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather2 = viewModelWeatherInfoTemperatureRangeGet2;
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet ? viewModelWeatherInfoDateGet : this.dateTv.getResources().getString(C0899R.string.ksw_id8_weather);
        }
        if ((dirtyFlags & 2688) != 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet ? viewModelWeatherInfoCityGet : this.mboundView5.getResources().getString(C0899R.string.ksw_id8_weather);
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2 = viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather;
        }
        if ((dirtyFlags & 2184) != 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoPhraseMboundView7AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet ? viewModelWeatherInfoPhraseGet : this.mboundView7.getResources().getString(C0899R.string.ksw_id8_weather);
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoPhraseMboundView7AndroidStringKswId8Weather = null;
        }
        if ((dirtyFlags & 2192) != 0) {
            TextViewBindingAdapter.setText(this.dateTv, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather2);
        }
        if ((dirtyFlags & 2240) != 0) {
            this.dateTv.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.ivIcon.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView11.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView3.setVisibility(viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView5.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView7.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.temperatureTv.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.unitWeather.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
        }
        if ((dirtyFlags & 2080) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, viewModelWeatherInfoRomeoV2ImageIconGet);
        }
        if ((dirtyFlags & 2049) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelWeatherInfoTemperatureRangeGet);
        }
        if ((dirtyFlags & 2112) != 0) {
            this.mboundView2.setVisibility(viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 2050) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoDateDateTvAndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 2688) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView5AndroidStringKswId8Weather2);
        }
        if ((dirtyFlags & 2184) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoPhraseMboundView7AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 2052) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, viewModelWeatherInfoTemperatureGet);
        }
        if ((dirtyFlags & 2304) != 0) {
            TextViewBindingAdapter.setText(this.unitWeather, viewModelWeatherInfoTemperUnitGet);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0) {
            this.weatherImageView.setOnClickListener(this.mCallback298);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        RomeoViewModelV2 viewModel = this.mViewModel;
        boolean viewModelJavaLangObjectNull = viewModel != null;
        if (viewModelJavaLangObjectNull) {
            viewModel.openWeatherApp(callbackArg_0);
        }
    }
}

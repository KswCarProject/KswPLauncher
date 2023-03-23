package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class FragmentWeatherEditBindingImpl extends FragmentWeatherEditBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final IncludeNearWeatherEditBinding mboundView1;
    private final TextView mboundView8;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(13);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"include_near_weather_edit"}, new int[]{9}, new int[]{R.layout.include_near_weather_edit});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_title, 10);
        sparseIntArray.put(R.id.iv_divider, 11);
        sparseIntArray.put(R.id.remove, 12);
    }

    public FragmentWeatherEditBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private FragmentWeatherEditBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 13, bindings[3], bindings[4], bindings[5], bindings[11], bindings[1], bindings[12], bindings[6], bindings[2], bindings[10], bindings[7]);
        this.mDirtyFlags = -1;
        this.btA.setTag((Object) null);
        this.btB.setTag((Object) null);
        this.btC.setTag((Object) null);
        this.layout.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        IncludeNearWeatherEditBinding includeNearWeatherEditBinding = bindings[9];
        this.mboundView1 = includeNearWeatherEditBinding;
        setContainedBinding(includeNearWeatherEditBinding);
        TextView textView = bindings[8];
        this.mboundView8 = textView;
        textView.setTag((Object) null);
        this.temperatureTv.setTag((Object) null);
        this.tvCity.setTag((Object) null);
        this.unitWeather.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE;
        }
        this.mboundView1.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.mboundView1.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.wits.ksw.databinding.IncludeNearWeatherEditBinding r0 = r4.mboundView1
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.FragmentWeatherEditBindingImpl.hasPendingBindings():boolean");
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
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView1.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWeatherViewModelWeatherInfoCity((ObservableField) object, fieldId);
            case 1:
                return onChangeWeatherViewModelWeatherInfoCardDetailText1((ObservableField) object, fieldId);
            case 2:
                return onChangeWeatherViewModelWeatherInfoCardDetailText3((ObservableField) object, fieldId);
            case 3:
                return onChangeWeatherViewModelWeatherInfoId8EditImage((ObservableField) object, fieldId);
            case 4:
                return onChangeWeatherViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 5:
                return onChangeWeatherViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 6:
                return onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg2((ObservableField) object, fieldId);
            case 7:
                return onChangeWeatherViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 8:
                return onChangeWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
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

    private boolean onChangeWeatherViewModelWeatherInfoCity(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText1(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText3(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8EditImage(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg2(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardEditDetailImg3(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String weatherViewModelWeatherInfoTemperatureGet;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg3Get;
        String weatherViewModelWeatherInfoCardDetailText3Get;
        WeatherInfo weatherViewModelWeatherInfo;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        String weatherViewModelWeatherInfoTemperatureGet2;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg1Get;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg2Get;
        String weatherViewModelWeatherInfoTemperUnitGet;
        String weatherViewModelWeatherInfoCityGet;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather;
        Drawable weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8EditImageLayoutAndroidDrawableId8MainEditIconWeather;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8EditImage;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess;
        String weatherViewModelWeatherInfoTemperatureRangeGet;
        String weatherViewModelWeatherInfoTemperatureRangeGet2;
        String weatherViewModelWeatherInfoTemperatureRangeGet3;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg3;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg1;
        ObservableField<String> weatherViewModelWeatherInfoTemperatureRange;
        ObservableField<String> weatherViewModelWeatherInfoTemperUnit;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess2;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText2;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardEditDetailImg2;
        ObservableField<String> weatherViewModelWeatherInfoTemperature;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String weatherViewModelWeatherInfoTemperatureRangeGet4 = null;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet3 = null;
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText1 = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText3 = null;
        String weatherViewModelWeatherInfoTemperUnitGet2 = null;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg2Get2 = null;
        String weatherViewModelWeatherInfoCardDetailText1Get2 = null;
        Drawable weatherViewModelWeatherInfoId8CardEditDetailImg1Get2 = null;
        String weatherViewModelWeatherInfoTemperatureGet3 = null;
        String weatherViewModelWeatherInfoCardDetailText2Get2 = null;
        String weatherViewModelWeatherInfoCardDetailText3Get2 = null;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsLoadSuccess3 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        Drawable weatherViewModelWeatherInfoId8EditImageGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        if ((dirtyFlags & 24575) != 0) {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 16386) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(1, (Observable) weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 16388) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(2, (Observable) weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get2 = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 16656) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                updateRegistration(4, (Observable) weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinished.get();
                }
                androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 16656) == 0) {
                    ObservableField<Boolean> observableField = weatherViewModelWeatherInfoIsInitFinished;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    ObservableField<Boolean> observableField2 = weatherViewModelWeatherInfoIsInitFinished;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    ObservableField<Boolean> observableField3 = weatherViewModelWeatherInfoIsInitFinished;
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
            }
            if ((dirtyFlags & 16416) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                } else {
                    weatherViewModelWeatherInfoTemperature = null;
                }
                updateRegistration(5, (Observable) weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet3 = weatherViewModelWeatherInfoTemperature.get();
                    ObservableField<String> observableField4 = weatherViewModelWeatherInfoTemperature;
                } else {
                    ObservableField<String> observableField5 = weatherViewModelWeatherInfoTemperature;
                }
            }
            if ((dirtyFlags & 16448) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2 = weatherViewModelWeatherInfo2.id8CardEditDetailImg2;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2 = null;
                }
                updateRegistration(6, (Observable) weatherViewModelWeatherInfoId8CardEditDetailImg2);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg2 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get2 = weatherViewModelWeatherInfoId8CardEditDetailImg2.get();
                    ObservableField<Drawable> observableField6 = weatherViewModelWeatherInfoId8CardEditDetailImg2;
                } else {
                    ObservableField<Drawable> observableField7 = weatherViewModelWeatherInfoId8CardEditDetailImg2;
                }
            }
            if ((dirtyFlags & 16512) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2 = weatherViewModelWeatherInfo2.cardDetailText2;
                } else {
                    weatherViewModelWeatherInfoCardDetailText2 = null;
                }
                updateRegistration(7, (Observable) weatherViewModelWeatherInfoCardDetailText2);
                if (weatherViewModelWeatherInfoCardDetailText2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2.get();
                    ObservableField<String> observableField8 = weatherViewModelWeatherInfoCardDetailText2;
                } else {
                    ObservableField<String> observableField9 = weatherViewModelWeatherInfoCardDetailText2;
                }
            }
            if ((dirtyFlags & 16649) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccess2 = weatherViewModelWeatherInfo2.isLoadSuccess;
                } else {
                    weatherViewModelWeatherInfoIsLoadSuccess2 = null;
                }
                updateRegistration(8, (Observable) weatherViewModelWeatherInfoIsLoadSuccess2);
                if (weatherViewModelWeatherInfoIsLoadSuccess2 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccessGet3 = weatherViewModelWeatherInfoIsLoadSuccess2.get();
                }
                androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet3);
                if ((dirtyFlags & 16641) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if ((dirtyFlags & 16648) == 0) {
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 4194304;
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    weatherViewModelWeatherInfoIsLoadSuccess3 = weatherViewModelWeatherInfoIsLoadSuccess2;
                }
            }
            if ((dirtyFlags & 16896) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperUnit = weatherViewModelWeatherInfo2.temperUnit;
                } else {
                    weatherViewModelWeatherInfoTemperUnit = null;
                }
                updateRegistration(9, (Observable) weatherViewModelWeatherInfoTemperUnit);
                if (weatherViewModelWeatherInfoTemperUnit != null) {
                    weatherViewModelWeatherInfoTemperUnitGet2 = weatherViewModelWeatherInfoTemperUnit.get();
                    ObservableField<String> observableField10 = weatherViewModelWeatherInfoTemperUnit;
                } else {
                    ObservableField<String> observableField11 = weatherViewModelWeatherInfoTemperUnit;
                }
            }
            if ((dirtyFlags & 17408) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                }
                updateRegistration(10, (Observable) weatherViewModelWeatherInfoTemperatureRange);
                if (weatherViewModelWeatherInfoTemperatureRange != null) {
                    ObservableField<String> observableField12 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRange.get();
                } else {
                    ObservableField<String> observableField13 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
            }
            if ((dirtyFlags & 18432) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1 = weatherViewModelWeatherInfo2.id8CardEditDetailImg1;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1 = null;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
                updateRegistration(11, (Observable) weatherViewModelWeatherInfoId8CardEditDetailImg1);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg1 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get2 = weatherViewModelWeatherInfoId8CardEditDetailImg1.get();
                    ObservableField<Drawable> observableField14 = weatherViewModelWeatherInfoId8CardEditDetailImg1;
                } else {
                    ObservableField<Drawable> observableField15 = weatherViewModelWeatherInfoId8CardEditDetailImg1;
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
            }
            if ((dirtyFlags & 20480) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardEditDetailImg3 = weatherViewModelWeatherInfo2.id8CardEditDetailImg3;
                } else {
                    weatherViewModelWeatherInfoId8CardEditDetailImg3 = null;
                }
                updateRegistration(12, (Observable) weatherViewModelWeatherInfoId8CardEditDetailImg3);
                if (weatherViewModelWeatherInfoId8CardEditDetailImg3 != null) {
                    ObservableField<Drawable> observableField16 = weatherViewModelWeatherInfoId8CardEditDetailImg3;
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    ObservableField<String> observableField17 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg3Get = weatherViewModelWeatherInfoId8CardEditDetailImg3.get();
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    ObservableField<String> observableField18 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperatureGet3;
                    weatherViewModelWeatherInfoTemperatureGet = null;
                } else {
                    ObservableField<Drawable> observableField19 = weatherViewModelWeatherInfoId8CardEditDetailImg3;
                    weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                    weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    ObservableField<String> observableField20 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                    weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
                    weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                    ObservableField<String> observableField21 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperatureGet3;
                    weatherViewModelWeatherInfoTemperatureGet = null;
                }
            } else {
                weatherViewModelWeatherInfoId8CardEditDetailImg2Get = weatherViewModelWeatherInfoId8CardEditDetailImg2Get2;
                weatherViewModelWeatherInfoTemperatureRangeGet4 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                ObservableField<String> observableField22 = weatherViewModelWeatherInfoCardDetailText3;
                weatherViewModelWeatherInfoId8CardEditDetailImg1Get = weatherViewModelWeatherInfoId8CardEditDetailImg1Get2;
                weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
                weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                weatherViewModelWeatherInfoCardDetailText2Get = weatherViewModelWeatherInfoCardDetailText2Get2;
                ObservableField<String> observableField23 = weatherViewModelWeatherInfoCardDetailText1;
                weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3Get2;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperatureGet3;
                weatherViewModelWeatherInfoTemperatureGet = null;
            }
        } else {
            weatherViewModelWeatherInfoId8CardEditDetailImg2Get = null;
            weatherViewModelWeatherInfoCardDetailText1Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessGet = null;
            weatherViewModelWeatherInfoCity = null;
            weatherViewModelWeatherInfoId8CardEditDetailImg1Get = null;
            weatherViewModelWeatherInfoTemperatureGet2 = null;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            weatherViewModelWeatherInfoTemperatureGet = null;
            weatherViewModelWeatherInfoCardDetailText2Get = null;
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoCardDetailText3Get = null;
            weatherViewModelWeatherInfoId8CardEditDetailImg3Get = null;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoCityGet = null;
                weatherViewModelWeatherInfoIsLoadSuccess = weatherViewModelWeatherInfo.isLoadSuccess;
            } else {
                weatherViewModelWeatherInfoCityGet = null;
                weatherViewModelWeatherInfoIsLoadSuccess = weatherViewModelWeatherInfoIsLoadSuccess3;
            }
            weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnitGet2;
            updateRegistration(8, (Observable) weatherViewModelWeatherInfoIsLoadSuccess);
            if (weatherViewModelWeatherInfoIsLoadSuccess != null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccess.get();
            } else {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
            }
            androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet2);
            if ((dirtyFlags & 16641) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 16648) == 0) {
                ObservableField<Boolean> observableField24 = weatherViewModelWeatherInfoIsLoadSuccess;
            } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                dirtyFlags |= 4194304;
                ObservableField<Boolean> observableField25 = weatherViewModelWeatherInfoIsLoadSuccess;
            } else {
                dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                ObservableField<Boolean> observableField26 = weatherViewModelWeatherInfoIsLoadSuccess;
            }
        } else {
            weatherViewModelWeatherInfoCityGet = null;
            weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnitGet2;
            weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
        }
        Boolean bool = weatherViewModelWeatherInfoIsLoadSuccessGet2;
        if ((dirtyFlags & 16656) != 0) {
            boolean weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            if ((dirtyFlags & 16656) != 0) {
                if (weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
            }
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
        } else {
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo.city;
            } else {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
            }
            boolean z = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet;
            updateRegistration(0, (Observable) weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        } else {
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        }
        if ((dirtyFlags & 4194304) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoId8EditImage = weatherViewModelWeatherInfo.id8EditImage;
            } else {
                weatherViewModelWeatherInfoId8EditImage = null;
            }
            WeatherInfo weatherInfo = weatherViewModelWeatherInfo;
            updateRegistration(3, (Observable) weatherViewModelWeatherInfoId8EditImage);
            if (weatherViewModelWeatherInfoId8EditImage != null) {
                weatherViewModelWeatherInfoId8EditImageGet = weatherViewModelWeatherInfoId8EditImage.get();
                ObservableField<Drawable> observableField27 = weatherViewModelWeatherInfoId8EditImage;
            } else {
                ObservableField<Drawable> observableField28 = weatherViewModelWeatherInfoId8EditImage;
            }
        }
        if ((dirtyFlags & 16641) != 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoCityGet : this.tvCity.getResources().getString(R.string.ksw_id8_weather);
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoTemperatureGet;
        }
        if ((dirtyFlags & 16648) != 0) {
            if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                ObservableField<String> observableField29 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8EditImageLayoutAndroidDrawableId8MainEditIconWeather = weatherViewModelWeatherInfoId8EditImageGet;
            } else {
                ObservableField<String> observableField30 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8EditImageLayoutAndroidDrawableId8MainEditIconWeather = AppCompatResources.getDrawable(this.layout.getContext(), R.drawable.id8_main_edit_icon_weather);
            }
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8EditImageLayoutAndroidDrawableId8MainEditIconWeather = null;
        }
        if ((dirtyFlags & 16386) != 0) {
            TextViewBindingAdapter.setText(this.btA, weatherViewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 18432) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btA, weatherViewModelWeatherInfoId8CardEditDetailImg1Get);
        }
        if ((dirtyFlags & 16656) != 0) {
            this.btA.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.btB.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.btC.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.mboundView1.getRoot().setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.mboundView8.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.temperatureTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.tvCity.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.unitWeather.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
        }
        if ((dirtyFlags & 16512) != 0) {
            TextViewBindingAdapter.setText(this.btB, weatherViewModelWeatherInfoCardDetailText2Get);
        }
        if ((dirtyFlags & 16448) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btB, weatherViewModelWeatherInfoId8CardEditDetailImg2Get);
        }
        if ((dirtyFlags & 16388) != 0) {
            TextViewBindingAdapter.setText(this.btC, weatherViewModelWeatherInfoCardDetailText3Get);
        }
        if ((dirtyFlags & 20480) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btC, weatherViewModelWeatherInfoId8CardEditDetailImg3Get);
        }
        if ((dirtyFlags & 16648) != 0) {
            ViewBindingAdapter.setBackground(this.layout, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8EditImageLayoutAndroidDrawableId8MainEditIconWeather);
        }
        if ((dirtyFlags & 24576) != 0) {
            this.mboundView1.setWeatherViewModel(weatherViewModel);
        }
        if ((dirtyFlags & 17408) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, weatherViewModelWeatherInfoTemperatureRangeGet4);
        }
        if ((dirtyFlags & 16416) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoTemperatureGet2);
        }
        if ((dirtyFlags & 16641) != 0) {
            TextViewBindingAdapter.setText(this.tvCity, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 16896) != 0) {
            String str = weatherViewModelWeatherInfoTemperatureRangeGet4;
            TextViewBindingAdapter.setText(this.unitWeather, weatherViewModelWeatherInfoTemperUnitGet);
        } else {
            String weatherViewModelWeatherInfoTemperatureRangeGet5 = weatherViewModelWeatherInfoTemperUnitGet;
        }
        executeBindingsOn(this.mboundView1);
    }
}

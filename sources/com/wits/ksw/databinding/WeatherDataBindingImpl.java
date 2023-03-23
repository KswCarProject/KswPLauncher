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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class WeatherDataBindingImpl extends WeatherDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback290;
    private long mDirtyFlags;
    private final ImageView mboundView1;
    private final TextView mboundView14;
    private final RelativeLayout mboundView3;
    private final IncludeNearWeatherBinding mboundView31;
    private final ProgressBar mboundView5;
    private final LinearLayout mboundView6;
    private final TextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(18);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(3, new String[]{"include_near_weather"}, new int[]{15}, new int[]{R.layout.include_near_weather});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_title, 16);
        sparseIntArray.put(R.id.iv_divider, 17);
    }

    public WeatherDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private WeatherDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, bindings[8], bindings[9], bindings[10], bindings[17], bindings[11], bindings[2], bindings[0], bindings[12], bindings[4], bindings[16], bindings[13]);
        this.mDirtyFlags = -1;
        this.btA.setTag((Object) null);
        this.btB.setTag((Object) null);
        this.btC.setTag((Object) null);
        this.ivIcon.setTag((Object) null);
        this.ivMask.setTag((Object) null);
        this.llContainer.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        TextView textView = bindings[14];
        this.mboundView14 = textView;
        textView.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[3];
        this.mboundView3 = relativeLayout;
        relativeLayout.setTag((Object) null);
        IncludeNearWeatherBinding includeNearWeatherBinding = bindings[15];
        this.mboundView31 = includeNearWeatherBinding;
        setContainedBinding(includeNearWeatherBinding);
        ProgressBar progressBar = bindings[5];
        this.mboundView5 = progressBar;
        progressBar.setTag((Object) null);
        LinearLayout linearLayout = bindings[6];
        this.mboundView6 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView2 = bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag((Object) null);
        this.temperatureTv.setTag((Object) null);
        this.tvCity.setTag((Object) null);
        this.unitWeather.setTag((Object) null);
        setRootTag(root);
        this.mCallback290 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        this.mboundView31.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.mboundView31.hasPendingBindings() == false) goto L_0x0016;
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
            com.wits.ksw.databinding.IncludeNearWeatherBinding r0 = r4.mboundView31
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
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.WeatherDataBindingImpl.hasPendingBindings():boolean");
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

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView31.setLifecycleOwner(lifecycleOwner);
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
                return onChangeWeatherViewModelWeatherInfoId8ImageBg((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 10:
                return onChangeWeatherViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 11:
                return onChangeWeatherViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 12:
                return onChangeWeatherViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 13:
                return onChangeWeatherViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 14:
                return onChangeWeatherViewModelWeatherInfoId8CardDetailImg2((ObservableField) object, fieldId);
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

    private boolean onChangeWeatherViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperature(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg3(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8ImageIcon(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8ImageBg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoErrorMessage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoCardDetailText2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperatureRange(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoId8CardDetailImg2(ObservableField<Drawable> observableField, int fieldId) {
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
        String weatherViewModelWeatherInfoTemperatureGet;
        String weatherViewModelWeatherInfoErrorMessageGet;
        String weatherViewModelWeatherInfoCityGet;
        String weatherViewModelWeatherInfoCardDetailText2Get;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        ObservableField<String> weatherViewModelWeatherInfoCity;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg2Get;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg3Get;
        String weatherViewModelWeatherInfoErrorMessageGet2;
        Drawable weatherViewModelWeatherInfoId8ImageIconGet;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather;
        WeatherInfo weatherViewModelWeatherInfo;
        String weatherViewModelWeatherInfoCardDetailText2Get2;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg1Get;
        String weatherViewModelWeatherInfoCardDetailText1Get;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
        String weatherViewModelWeatherInfoErrorMessageGet3;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet2;
        int weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        int WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        LauncherViewModel weatherViewModel;
        ObservableField<String> weatherViewModelWeatherInfoCity2;
        String weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2;
        Drawable weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8ImageBgMboundView1AndroidDrawableId8MainIconWeather;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4;
        String weatherViewModelWeatherInfoErrorMessageGet4;
        String weatherViewModelWeatherInfoTemperatureGet2;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8ImageBg;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess1;
        String weatherViewModelWeatherInfoTemperatureRangeGet;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg2;
        ObservableField<String> weatherViewModelWeatherInfoTemperatureRange;
        ObservableField<String> weatherViewModelWeatherInfoTemperUnit;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess12;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText2;
        ObservableField<String> weatherViewModelWeatherInfoErrorMessage;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8ImageIcon;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg1;
        ObservableField<Drawable> weatherViewModelWeatherInfoId8CardDetailImg3;
        ObservableField<String> weatherViewModelWeatherInfoTemperature;
        ObservableField<Boolean> weatherViewModelWeatherInfoIsInitFinished;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String weatherViewModelWeatherInfoTemperatureRangeGet2 = null;
        Boolean weatherViewModelWeatherInfoIsLoadSuccessGet3 = null;
        LauncherViewModel weatherViewModel2 = this.mWeatherViewModel;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText1 = null;
        ObservableField<String> weatherViewModelWeatherInfoCardDetailText3 = null;
        String weatherViewModelWeatherInfoTemperUnitGet = null;
        String weatherViewModelWeatherInfoCardDetailText1Get2 = null;
        boolean weatherViewModelWeatherInfoIsLoadSuccess = false;
        String weatherViewModelWeatherInfoTemperatureGet3 = null;
        int weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5 = 0;
        String weatherViewModelWeatherInfoCardDetailText2Get3 = null;
        String weatherViewModelWeatherInfoCardDetailText3Get = null;
        Drawable weatherViewModelWeatherInfoId8ImageIconGet2 = null;
        String weatherViewModelWeatherInfoErrorMessageGet5 = null;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg3Get2 = null;
        ObservableField<Boolean> WeatherViewModelWeatherInfoIsLoadSuccess13 = null;
        Boolean weatherViewModelWeatherInfoIsInitFinishedGet = null;
        Drawable weatherViewModelWeatherInfoId8ImageBgGet = null;
        boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = false;
        Drawable weatherViewModelWeatherInfoId8CardDetailImg1Get2 = null;
        if ((dirtyFlags & 98303) != 0) {
            WeatherInfo weatherViewModelWeatherInfo2 = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 65538) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText1 = weatherViewModelWeatherInfo2.cardDetailText1;
                }
                updateRegistration(1, (Observable) weatherViewModelWeatherInfoCardDetailText1);
                if (weatherViewModelWeatherInfoCardDetailText1 != null) {
                    weatherViewModelWeatherInfoCardDetailText1Get2 = weatherViewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 65540) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText3 = weatherViewModelWeatherInfo2.cardDetailText3;
                }
                updateRegistration(2, (Observable) weatherViewModelWeatherInfoCardDetailText3);
                if (weatherViewModelWeatherInfoCardDetailText3 != null) {
                    weatherViewModelWeatherInfoCardDetailText3Get = weatherViewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 67592) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoIsInitFinished = weatherViewModelWeatherInfo2.isInitFinished;
                } else {
                    weatherViewModelWeatherInfoIsInitFinished = null;
                }
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
                updateRegistration(3, (Observable) weatherViewModelWeatherInfoIsInitFinished);
                if (weatherViewModelWeatherInfoIsInitFinished != null) {
                    weatherViewModelWeatherInfoIsInitFinishedGet = weatherViewModelWeatherInfoIsInitFinished.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 67592) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags = dirtyFlags | 4194304 | 67108864;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE | 33554432;
                    }
                }
                if ((dirtyFlags & 65544) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                if ((dirtyFlags & 65544) != 0) {
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2 ? 4 : 0;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                    ObservableField<Boolean> observableField = weatherViewModelWeatherInfoIsInitFinished;
                } else {
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet2;
                    ObservableField<Boolean> observableField2 = weatherViewModelWeatherInfoIsInitFinished;
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet = null;
            }
            if ((dirtyFlags & 65552) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperature = weatherViewModelWeatherInfo2.temperature;
                } else {
                    weatherViewModelWeatherInfoTemperature = null;
                }
                updateRegistration(4, (Observable) weatherViewModelWeatherInfoTemperature);
                if (weatherViewModelWeatherInfoTemperature != null) {
                    weatherViewModelWeatherInfoTemperatureGet3 = weatherViewModelWeatherInfoTemperature.get();
                    ObservableField<String> observableField3 = weatherViewModelWeatherInfoTemperature;
                } else {
                    ObservableField<String> observableField4 = weatherViewModelWeatherInfoTemperature;
                }
            }
            if ((dirtyFlags & 65568) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardDetailImg3 = weatherViewModelWeatherInfo2.id8CardDetailImg3;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg3 = null;
                }
                updateRegistration(5, (Observable) weatherViewModelWeatherInfoId8CardDetailImg3);
                if (weatherViewModelWeatherInfoId8CardDetailImg3 != null) {
                    ObservableField<Drawable> observableField5 = weatherViewModelWeatherInfoId8CardDetailImg3;
                    weatherViewModelWeatherInfoId8CardDetailImg3Get2 = weatherViewModelWeatherInfoId8CardDetailImg3.get();
                } else {
                    ObservableField<Drawable> observableField6 = weatherViewModelWeatherInfoId8CardDetailImg3;
                }
            }
            if ((dirtyFlags & 65600) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardDetailImg1 = weatherViewModelWeatherInfo2.id8CardDetailImg1;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg1 = null;
                }
                updateRegistration(6, (Observable) weatherViewModelWeatherInfoId8CardDetailImg1);
                if (weatherViewModelWeatherInfoId8CardDetailImg1 != null) {
                    ObservableField<Drawable> observableField7 = weatherViewModelWeatherInfoId8CardDetailImg1;
                    weatherViewModelWeatherInfoId8CardDetailImg1Get2 = weatherViewModelWeatherInfoId8CardDetailImg1.get();
                } else {
                    ObservableField<Drawable> observableField8 = weatherViewModelWeatherInfoId8CardDetailImg1;
                }
            }
            if ((dirtyFlags & 65664) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8ImageIcon = weatherViewModelWeatherInfo2.id8ImageIcon;
                } else {
                    weatherViewModelWeatherInfoId8ImageIcon = null;
                }
                updateRegistration(7, (Observable) weatherViewModelWeatherInfoId8ImageIcon);
                if (weatherViewModelWeatherInfoId8ImageIcon != null) {
                    ObservableField<Drawable> observableField9 = weatherViewModelWeatherInfoId8ImageIcon;
                    weatherViewModelWeatherInfoId8ImageIconGet2 = weatherViewModelWeatherInfoId8ImageIcon.get();
                } else {
                    ObservableField<Drawable> observableField10 = weatherViewModelWeatherInfoId8ImageIcon;
                }
            }
            if ((dirtyFlags & 66048) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoErrorMessage = weatherViewModelWeatherInfo2.errorMessage;
                } else {
                    weatherViewModelWeatherInfoErrorMessage = null;
                }
                updateRegistration(9, (Observable) weatherViewModelWeatherInfoErrorMessage);
                if (weatherViewModelWeatherInfoErrorMessage != null) {
                    weatherViewModelWeatherInfoErrorMessageGet5 = weatherViewModelWeatherInfoErrorMessage.get();
                    ObservableField<String> observableField11 = weatherViewModelWeatherInfoErrorMessage;
                } else {
                    ObservableField<String> observableField12 = weatherViewModelWeatherInfoErrorMessage;
                }
            }
            if ((dirtyFlags & 66560) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoCardDetailText2 = weatherViewModelWeatherInfo2.cardDetailText2;
                } else {
                    weatherViewModelWeatherInfoCardDetailText2 = null;
                }
                updateRegistration(10, (Observable) weatherViewModelWeatherInfoCardDetailText2);
                if (weatherViewModelWeatherInfoCardDetailText2 != null) {
                    ObservableField<String> observableField13 = weatherViewModelWeatherInfoCardDetailText2;
                    weatherViewModelWeatherInfoCardDetailText2Get3 = weatherViewModelWeatherInfoCardDetailText2.get();
                } else {
                    ObservableField<String> observableField14 = weatherViewModelWeatherInfoCardDetailText2;
                }
            }
            if ((dirtyFlags & 67841) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = weatherViewModelWeatherInfo2.isLoadSuccess;
                } else {
                    WeatherViewModelWeatherInfoIsLoadSuccess12 = null;
                }
                updateRegistration(11, (Observable) WeatherViewModelWeatherInfoIsLoadSuccess12);
                if (WeatherViewModelWeatherInfoIsLoadSuccess12 != null) {
                    weatherViewModelWeatherInfoIsLoadSuccessGet3 = WeatherViewModelWeatherInfoIsLoadSuccess12.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2 = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet3);
                if ((dirtyFlags & 67585) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                if ((dirtyFlags & 67840) == 0) {
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2) {
                    dirtyFlags |= 16777216;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                } else {
                    dirtyFlags |= 8388608;
                    WeatherViewModelWeatherInfoIsLoadSuccess13 = WeatherViewModelWeatherInfoIsLoadSuccess12;
                    androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet2;
                }
            }
            if ((dirtyFlags & 69632) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperUnit = weatherViewModelWeatherInfo2.temperUnit;
                } else {
                    weatherViewModelWeatherInfoTemperUnit = null;
                }
                updateRegistration(12, (Observable) weatherViewModelWeatherInfoTemperUnit);
                if (weatherViewModelWeatherInfoTemperUnit != null) {
                    weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnit.get();
                    ObservableField<String> observableField15 = weatherViewModelWeatherInfoTemperUnit;
                } else {
                    ObservableField<String> observableField16 = weatherViewModelWeatherInfoTemperUnit;
                }
            }
            if ((dirtyFlags & 73728) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoTemperatureRange = weatherViewModelWeatherInfo2.temperatureRange;
                } else {
                    weatherViewModelWeatherInfoTemperatureRange = null;
                }
                updateRegistration(13, (Observable) weatherViewModelWeatherInfoTemperatureRange);
                if (weatherViewModelWeatherInfoTemperatureRange != null) {
                    ObservableField<String> observableField17 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRange.get();
                } else {
                    ObservableField<String> observableField18 = weatherViewModelWeatherInfoTemperatureRange;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
                }
            } else {
                weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet;
            }
            if ((dirtyFlags & 81920) != 0) {
                if (weatherViewModelWeatherInfo2 != null) {
                    weatherViewModelWeatherInfoId8CardDetailImg2 = weatherViewModelWeatherInfo2.id8CardDetailImg2;
                } else {
                    weatherViewModelWeatherInfoId8CardDetailImg2 = null;
                }
                String weatherViewModelWeatherInfoTemperatureRangeGet3 = weatherViewModelWeatherInfoTemperatureRangeGet2;
                updateRegistration(14, (Observable) weatherViewModelWeatherInfoId8CardDetailImg2);
                if (weatherViewModelWeatherInfoId8CardDetailImg2 != null) {
                    ObservableField<Drawable> observableField19 = weatherViewModelWeatherInfoId8CardDetailImg2;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                    ObservableField<String> observableField20 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet2;
                    weatherViewModelWeatherInfoCityGet = null;
                    weatherViewModelWeatherInfoId8CardDetailImg3Get = weatherViewModelWeatherInfoId8CardDetailImg3Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet3;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2Get3;
                    weatherViewModelWeatherInfoCardDetailText2Get = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                    ObservableField<String> observableField21 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                    weatherViewModelWeatherInfoId8CardDetailImg2Get = weatherViewModelWeatherInfoId8CardDetailImg2.get();
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
                } else {
                    ObservableField<Drawable> observableField22 = weatherViewModelWeatherInfoId8CardDetailImg2;
                    weatherViewModelWeatherInfoTemperatureRangeGet2 = weatherViewModelWeatherInfoTemperatureRangeGet3;
                    weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                    ObservableField<String> observableField23 = weatherViewModelWeatherInfoCardDetailText1;
                    weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet2;
                    weatherViewModelWeatherInfoCityGet = null;
                    weatherViewModelWeatherInfoId8CardDetailImg3Get = weatherViewModelWeatherInfoId8CardDetailImg3Get2;
                    weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet3;
                    weatherViewModelWeatherInfoCity = null;
                    weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2Get3;
                    weatherViewModelWeatherInfoCardDetailText2Get = null;
                    weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                    ObservableField<String> observableField24 = weatherViewModelWeatherInfoCardDetailText3;
                    weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet5;
                    weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                    weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
                    weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                    weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                    weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                    weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
                }
            } else {
                weatherViewModelWeatherInfoId8CardDetailImg1Get = weatherViewModelWeatherInfoId8CardDetailImg1Get2;
                ObservableField<String> observableField25 = weatherViewModelWeatherInfoCardDetailText1;
                weatherViewModelWeatherInfoId8ImageIconGet = weatherViewModelWeatherInfoId8ImageIconGet2;
                weatherViewModelWeatherInfoCityGet = null;
                weatherViewModelWeatherInfoId8CardDetailImg3Get = weatherViewModelWeatherInfoId8CardDetailImg3Get2;
                weatherViewModelWeatherInfoTemperatureGet = weatherViewModelWeatherInfoTemperatureGet3;
                weatherViewModelWeatherInfoCity = null;
                weatherViewModelWeatherInfoCardDetailText2Get2 = weatherViewModelWeatherInfoCardDetailText2Get3;
                weatherViewModelWeatherInfoCardDetailText2Get = null;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = weatherViewModelWeatherInfoCardDetailText3Get;
                ObservableField<String> observableField26 = weatherViewModelWeatherInfoCardDetailText3;
                weatherViewModelWeatherInfoErrorMessageGet2 = weatherViewModelWeatherInfoErrorMessageGet5;
                weatherViewModelWeatherInfoErrorMessageGet = weatherViewModelWeatherInfoTemperUnitGet;
                weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
                weatherViewModelWeatherInfo = weatherViewModelWeatherInfo2;
                weatherViewModelWeatherInfoCardDetailText1Get = weatherViewModelWeatherInfoCardDetailText1Get2;
                weatherViewModelWeatherInfoIsLoadSuccessGet = weatherViewModelWeatherInfoIsLoadSuccessGet3;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE5;
                weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            }
        } else {
            weatherViewModelWeatherInfoCardDetailText1Get = null;
            weatherViewModelWeatherInfoId8CardDetailImg1Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessGet = null;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            weatherViewModelWeatherInfo = null;
            weatherViewModelWeatherInfoId8ImageIconGet = null;
            weatherViewModelWeatherInfoCityGet = null;
            weatherViewModelWeatherInfoId8CardDetailImg3Get = null;
            weatherViewModelWeatherInfoTemperatureGet = null;
            weatherViewModelWeatherInfoCity = null;
            weatherViewModelWeatherInfoCardDetailText2Get2 = null;
            weatherViewModelWeatherInfoCardDetailText2Get = null;
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather = null;
            weatherViewModelWeatherInfoErrorMessageGet2 = null;
            weatherViewModelWeatherInfoErrorMessageGet = null;
            weatherViewModelWeatherInfoId8CardDetailImg2Get = null;
        }
        if ((dirtyFlags & 71303168) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = weatherViewModelWeatherInfo.isLoadSuccess;
            } else {
                weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
                WeatherViewModelWeatherInfoIsLoadSuccess1 = WeatherViewModelWeatherInfoIsLoadSuccess13;
            }
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
            updateRegistration(11, (Observable) WeatherViewModelWeatherInfoIsLoadSuccess1);
            if (WeatherViewModelWeatherInfoIsLoadSuccess1 != null) {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = WeatherViewModelWeatherInfoIsLoadSuccess1.get();
            } else {
                weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
            }
            androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(weatherViewModelWeatherInfoIsLoadSuccessGet2);
            if ((dirtyFlags & 67585) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
            }
            if ((dirtyFlags & 67840) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 16777216;
                } else {
                    dirtyFlags |= 8388608;
                }
            }
            if ((dirtyFlags & 67108864) != 0) {
                weatherViewModelWeatherInfoIsLoadSuccess = !androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet;
                ObservableField<Boolean> observableField27 = WeatherViewModelWeatherInfoIsLoadSuccess1;
            } else {
                ObservableField<Boolean> observableField28 = WeatherViewModelWeatherInfoIsLoadSuccess1;
            }
        } else {
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
            weatherViewModelWeatherInfoErrorMessageGet3 = weatherViewModelWeatherInfoErrorMessageGet2;
            weatherViewModelWeatherInfoIsLoadSuccessGet2 = weatherViewModelWeatherInfoIsLoadSuccessGet;
        }
        if ((dirtyFlags & 67592) != 0) {
            boolean weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet : false;
            boolean WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet ? weatherViewModelWeatherInfoIsLoadSuccess : false;
            if ((dirtyFlags & 67592) != 0) {
                if (weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
            }
            if ((dirtyFlags & 67592) != 0) {
                if (WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= 1073741824;
                } else {
                    dirtyFlags |= 536870912;
                }
            }
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            Boolean bool = weatherViewModelWeatherInfoIsLoadSuccessGet2;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
        } else {
            Boolean bool2 = weatherViewModelWeatherInfoIsLoadSuccessGet2;
            weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
            WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                boolean z = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet;
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfo.city;
            } else {
                weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
            }
            weatherViewModel = weatherViewModel2;
            updateRegistration(0, (Observable) weatherViewModelWeatherInfoCity2);
            if (weatherViewModelWeatherInfoCity2 != null) {
                weatherViewModelWeatherInfoCityGet = weatherViewModelWeatherInfoCity2.get();
            }
        } else {
            weatherViewModel = weatherViewModel2;
            boolean z2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsInitFinishedGet;
            weatherViewModelWeatherInfoCity2 = weatherViewModelWeatherInfoCity;
        }
        if ((dirtyFlags & 16777216) != 0) {
            if (weatherViewModelWeatherInfo != null) {
                weatherViewModelWeatherInfoId8ImageBg = weatherViewModelWeatherInfo.id8ImageBg;
            } else {
                weatherViewModelWeatherInfoId8ImageBg = null;
            }
            WeatherInfo weatherInfo = weatherViewModelWeatherInfo;
            updateRegistration(8, (Observable) weatherViewModelWeatherInfoId8ImageBg);
            if (weatherViewModelWeatherInfoId8ImageBg != null) {
                weatherViewModelWeatherInfoId8ImageBgGet = weatherViewModelWeatherInfoId8ImageBg.get();
                ObservableField<Drawable> observableField29 = weatherViewModelWeatherInfoId8ImageBg;
            } else {
                ObservableField<Drawable> observableField30 = weatherViewModelWeatherInfoId8ImageBg;
            }
        }
        if ((dirtyFlags & 67585) != 0) {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2 = androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet ? weatherViewModelWeatherInfoCityGet : this.tvCity.getResources().getString(R.string.ksw_id8_weather);
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2 = weatherViewModelWeatherInfoCardDetailText2Get;
        }
        if ((dirtyFlags & 67840) != 0) {
            if (androidDatabindingViewDataBindingSafeUnboxWeatherViewModelWeatherInfoIsLoadSuccessGet) {
                ObservableField<String> observableField31 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8ImageBgMboundView1AndroidDrawableId8MainIconWeather = weatherViewModelWeatherInfoId8ImageBgGet;
            } else {
                ObservableField<String> observableField32 = weatherViewModelWeatherInfoCity2;
                weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8ImageBgMboundView1AndroidDrawableId8MainIconWeather = AppCompatResources.getDrawable(this.mboundView1.getContext(), R.drawable.id8_main_icon_weather);
            }
        } else {
            weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8ImageBgMboundView1AndroidDrawableId8MainIconWeather = null;
        }
        if ((dirtyFlags & 65600) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btA, weatherViewModelWeatherInfoId8CardDetailImg1Get);
        }
        if ((dirtyFlags & 65538) != 0) {
            TextViewBindingAdapter.setText(this.btA, weatherViewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 67592) != 0) {
            this.btA.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.btB.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.btC.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.ivIcon.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView14.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView31.getRoot().setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView6.setVisibility(WeatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.temperatureTv.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.tvCity.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.unitWeather.setVisibility(weatherViewModelWeatherInfoIsInitFinishedWeatherViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 81920) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btB, weatherViewModelWeatherInfoId8CardDetailImg2Get);
        }
        if ((dirtyFlags & 66560) != 0) {
            TextViewBindingAdapter.setText(this.btB, weatherViewModelWeatherInfoCardDetailText2Get2);
        }
        if ((dirtyFlags & 65540) != 0) {
            TextViewBindingAdapter.setText(this.btC, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 65568) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.btC, weatherViewModelWeatherInfoId8CardDetailImg3Get);
        }
        if ((dirtyFlags & 65664) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, weatherViewModelWeatherInfoId8ImageIconGet);
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            String str = weatherViewModelWeatherInfoCardDetailText1Get;
            this.ivMask.setOnClickListener(this.mCallback290);
        }
        if ((dirtyFlags & 67840) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoId8ImageBgMboundView1AndroidDrawableId8MainIconWeather);
        }
        if ((dirtyFlags & 73728) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, weatherViewModelWeatherInfoTemperatureRangeGet2);
        }
        if ((dirtyFlags & 98304) != 0) {
            this.mboundView31.setWeatherViewModel(weatherViewModel);
        }
        if ((dirtyFlags & 65544) != 0) {
            String str2 = weatherViewModelWeatherInfoTemperatureRangeGet2;
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
            this.mboundView5.setVisibility(weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4);
        } else {
            weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4 = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE3;
        }
        if ((dirtyFlags & 66048) != 0) {
            int i = weatherViewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE4;
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoErrorMessageGet3;
            TextViewBindingAdapter.setText(this.mboundView7, weatherViewModelWeatherInfoErrorMessageGet4);
        } else {
            weatherViewModelWeatherInfoErrorMessageGet4 = weatherViewModelWeatherInfoErrorMessageGet3;
        }
        if ((dirtyFlags & 65552) != 0) {
            String str3 = weatherViewModelWeatherInfoErrorMessageGet4;
            weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperatureGet;
            TextViewBindingAdapter.setText(this.temperatureTv, weatherViewModelWeatherInfoTemperatureGet2);
        } else {
            weatherViewModelWeatherInfoTemperatureGet2 = weatherViewModelWeatherInfoTemperatureGet;
        }
        if ((dirtyFlags & 67585) != 0) {
            TextViewBindingAdapter.setText(this.tvCity, weatherViewModelWeatherInfoIsLoadSuccessWeatherViewModelWeatherInfoCityTvCityAndroidStringKswId8Weather2);
        }
        if ((dirtyFlags & 69632) != 0) {
            String str4 = weatherViewModelWeatherInfoTemperatureGet2;
            TextViewBindingAdapter.setText(this.unitWeather, weatherViewModelWeatherInfoErrorMessageGet);
        } else {
            String weatherViewModelWeatherInfoTemperatureGet4 = weatherViewModelWeatherInfoErrorMessageGet;
        }
        executeBindingsOn(this.mboundView31);
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LauncherViewModel weatherViewModel = this.mWeatherViewModel;
        if (weatherViewModel != null) {
            weatherViewModel.openWeatherApp(callbackArg_0);
        }
    }
}

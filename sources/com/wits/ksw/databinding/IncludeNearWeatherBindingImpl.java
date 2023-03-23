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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class IncludeNearWeatherBindingImpl extends IncludeNearWeatherBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ImageView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final TextView mboundView17;
    private final ImageView mboundView18;
    private final TextView mboundView19;
    private final TextView mboundView2;
    private final TextView mboundView20;
    private final TextView mboundView21;
    private final TextView mboundView22;
    private final ImageView mboundView23;
    private final TextView mboundView24;
    private final TextView mboundView25;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView8;
    private final TextView mboundView9;

    public IncludeNearWeatherBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }

    private IncludeNearWeatherBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 21);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = bindings[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[10];
        this.mboundView10 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[11];
        this.mboundView11 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[12];
        this.mboundView12 = textView4;
        textView4.setTag((Object) null);
        ImageView imageView = bindings[13];
        this.mboundView13 = imageView;
        imageView.setTag((Object) null);
        TextView textView5 = bindings[14];
        this.mboundView14 = textView5;
        textView5.setTag((Object) null);
        TextView textView6 = bindings[15];
        this.mboundView15 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = bindings[16];
        this.mboundView16 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = bindings[17];
        this.mboundView17 = textView8;
        textView8.setTag((Object) null);
        ImageView imageView2 = bindings[18];
        this.mboundView18 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView9 = bindings[19];
        this.mboundView19 = textView9;
        textView9.setTag((Object) null);
        TextView textView10 = bindings[2];
        this.mboundView2 = textView10;
        textView10.setTag((Object) null);
        TextView textView11 = bindings[20];
        this.mboundView20 = textView11;
        textView11.setTag((Object) null);
        TextView textView12 = bindings[21];
        this.mboundView21 = textView12;
        textView12.setTag((Object) null);
        TextView textView13 = bindings[22];
        this.mboundView22 = textView13;
        textView13.setTag((Object) null);
        ImageView imageView3 = bindings[23];
        this.mboundView23 = imageView3;
        imageView3.setTag((Object) null);
        TextView textView14 = bindings[24];
        this.mboundView24 = textView14;
        textView14.setTag((Object) null);
        TextView textView15 = bindings[25];
        this.mboundView25 = textView15;
        textView15.setTag((Object) null);
        ImageView imageView4 = bindings[3];
        this.mboundView3 = imageView4;
        imageView4.setTag((Object) null);
        TextView textView16 = bindings[4];
        this.mboundView4 = textView16;
        textView16.setTag((Object) null);
        TextView textView17 = bindings[5];
        this.mboundView5 = textView17;
        textView17.setTag((Object) null);
        TextView textView18 = bindings[6];
        this.mboundView6 = textView18;
        textView18.setTag((Object) null);
        TextView textView19 = bindings[7];
        this.mboundView7 = textView19;
        textView19.setTag((Object) null);
        ImageView imageView5 = bindings[8];
        this.mboundView8 = imageView5;
        imageView5.setTag((Object) null);
        TextView textView20 = bindings[9];
        this.mboundView9 = textView20;
        textView20.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4194304;
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
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWeatherViewModelWeatherInfoNear4HourClock((ObservableField) object, fieldId);
            case 1:
                return onChangeWeatherViewModelWeatherInfoNear1HourImg((ObservableField) object, fieldId);
            case 2:
                return onChangeWeatherViewModelWeatherInfoNear4HourImg((ObservableField) object, fieldId);
            case 3:
                return onChangeWeatherViewModelWeatherInfoNear4HourClockUnit((ObservableField) object, fieldId);
            case 4:
                return onChangeWeatherViewModelWeatherInfoNear4HourTemp((ObservableField) object, fieldId);
            case 5:
                return onChangeWeatherViewModelWeatherInfoNear1HourClockUnit((ObservableField) object, fieldId);
            case 6:
                return onChangeWeatherViewModelWeatherInfoNear3HourImg((ObservableField) object, fieldId);
            case 7:
                return onChangeWeatherViewModelWeatherInfoNear3HourClock((ObservableField) object, fieldId);
            case 8:
                return onChangeWeatherViewModelWeatherInfoNear5HourClockUnit((ObservableField) object, fieldId);
            case 9:
                return onChangeWeatherViewModelWeatherInfoNear1HourTemp((ObservableField) object, fieldId);
            case 10:
                return onChangeWeatherViewModelWeatherInfoNear2HourImg((ObservableField) object, fieldId);
            case 11:
                return onChangeWeatherViewModelWeatherInfoNear5HourImg((ObservableField) object, fieldId);
            case 12:
                return onChangeWeatherViewModelWeatherInfoNear3HourTemp((ObservableField) object, fieldId);
            case 13:
                return onChangeWeatherViewModelWeatherInfoNear2HourClockUnit((ObservableField) object, fieldId);
            case 14:
                return onChangeWeatherViewModelWeatherInfoNear2HourClock((ObservableField) object, fieldId);
            case 15:
                return onChangeWeatherViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 16:
                return onChangeWeatherViewModelWeatherInfoNear1HourClock((ObservableField) object, fieldId);
            case 17:
                return onChangeWeatherViewModelWeatherInfoNear5HourTemp((ObservableField) object, fieldId);
            case 18:
                return onChangeWeatherViewModelWeatherInfoNear5HourClock((ObservableField) object, fieldId);
            case 19:
                return onChangeWeatherViewModelWeatherInfoNear2HourTemp((ObservableField) object, fieldId);
            case 20:
                return onChangeWeatherViewModelWeatherInfoNear3HourClockUnit((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear4HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear1HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear4HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear4HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear4HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear1HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear3HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear3HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear5HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear1HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear2HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear5HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear3HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear2HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear2HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear1HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear5HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear5HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear2HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeWeatherViewModelWeatherInfoNear3HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String weatherViewModelWeatherInfoNear5HourTempGet;
        Drawable weatherViewModelWeatherInfoNear2HourImgGet;
        String weatherViewModelWeatherInfoNear1HourTempGet;
        String weatherViewModelWeatherInfoNear2HourTempGet;
        Drawable weatherViewModelWeatherInfoNear5HourImgGet;
        Drawable weatherViewModelWeatherInfoNear1HourImgGet;
        String weatherViewModelWeatherInfoNear4HourTempGet;
        String weatherViewModelWeatherInfoNear3HourClockUnitGet;
        String weatherViewModelWeatherInfoNear2HourClockGet;
        String weatherViewModelWeatherInfoNear3HourTempGet;
        String weatherViewModelWeatherInfoNear4HourTempGet2;
        String weatherViewModelWeatherInfoNear3HourClockUnitGet2;
        String weatherViewModelWeatherInfoNear3HourClockGet;
        String weatherViewModelWeatherInfoNear4HourClockUnitGet;
        String weatherViewModelWeatherInfoNear5HourClockUnitGet;
        String weatherViewModelWeatherInfoTemperUnitGet;
        String weatherViewModelWeatherInfoNear5HourClockGet;
        String weatherViewModelWeatherInfoNear5HourClockUnitGet2;
        String weatherViewModelWeatherInfoNear5HourClockUnitGet3;
        Drawable weatherViewModelWeatherInfoNear5HourImgGet2;
        String weatherViewModelWeatherInfoNear5HourTempGet2;
        Drawable weatherViewModelWeatherInfoNear1HourImgGet2;
        String weatherViewModelWeatherInfoNear1HourTempGet2;
        String weatherViewModelWeatherInfoNear1HourTempGet3;
        String weatherViewModelWeatherInfoNear2HourClockUnitGet;
        Drawable weatherViewModelWeatherInfoNear2HourImgGet2;
        String weatherViewModelWeatherInfoNear4HourClockGet;
        ObservableField<String> weatherViewModelWeatherInfoNear3HourClockUnit;
        ObservableField<String> weatherViewModelWeatherInfoNear2HourTemp;
        ObservableField<String> weatherViewModelWeatherInfoNear5HourClock;
        ObservableField<String> weatherViewModelWeatherInfoNear5HourTemp;
        ObservableField<String> weatherViewModelWeatherInfoNear1HourClock;
        ObservableField<String> weatherViewModelWeatherInfoTemperUnit;
        ObservableField<String> weatherViewModelWeatherInfoNear2HourClock;
        ObservableField<String> weatherViewModelWeatherInfoNear2HourClockUnit;
        ObservableField<String> weatherViewModelWeatherInfoNear3HourTemp;
        ObservableField<Drawable> weatherViewModelWeatherInfoNear5HourImg;
        ObservableField<Drawable> weatherViewModelWeatherInfoNear2HourImg;
        ObservableField<String> weatherViewModelWeatherInfoNear1HourTemp;
        ObservableField<String> weatherViewModelWeatherInfoNear5HourClockUnit;
        ObservableField<String> weatherViewModelWeatherInfoNear3HourClock;
        ObservableField<Drawable> weatherViewModelWeatherInfoNear3HourImg;
        ObservableField<String> weatherViewModelWeatherInfoNear1HourClockUnit;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String weatherViewModelWeatherInfoNear4HourClockGet2 = null;
        ObservableField<String> weatherViewModelWeatherInfoNear4HourClock = null;
        ObservableField<Drawable> weatherViewModelWeatherInfoNear1HourImg = null;
        ObservableField<Drawable> weatherViewModelWeatherInfoNear4HourImg = null;
        Drawable weatherViewModelWeatherInfoNear4HourImgGet = null;
        ObservableField<String> weatherViewModelWeatherInfoNear4HourClockUnit = null;
        ObservableField<String> weatherViewModelWeatherInfoNear4HourTemp = null;
        String weatherViewModelWeatherInfoNear1HourClockUnitGet = null;
        Drawable weatherViewModelWeatherInfoNear1HourImgGet3 = null;
        String weatherViewModelWeatherInfoNear2HourClockGet2 = null;
        Drawable weatherViewModelWeatherInfoNear3HourImgGet = null;
        String weatherViewModelWeatherInfoNear5HourClockGet2 = null;
        String weatherViewModelWeatherInfoTemperUnitGet2 = null;
        String weatherViewModelWeatherInfoNear2HourClockUnitGet2 = null;
        Drawable weatherViewModelWeatherInfoNear5HourImgGet3 = null;
        String weatherViewModelWeatherInfoNear3HourClockGet2 = null;
        String weatherViewModelWeatherInfoNear4HourTempGet3 = null;
        String weatherViewModelWeatherInfoNear5HourClockUnitGet4 = null;
        String weatherViewModelWeatherInfoNear1HourClockGet = null;
        String weatherViewModelWeatherInfoNear2HourTempGet2 = null;
        String weatherViewModelWeatherInfoNear1HourTempGet4 = null;
        Drawable weatherViewModelWeatherInfoNear2HourImgGet3 = null;
        String weatherViewModelWeatherInfoNear3HourTempGet2 = null;
        String weatherViewModelWeatherInfoNear4HourClockUnitGet2 = null;
        String weatherViewModelWeatherInfoNear5HourTempGet3 = null;
        if ((dirtyFlags & 6291455) != 0) {
            WeatherInfo weatherViewModelWeatherInfo = LauncherViewModel.weatherInfo;
            if ((dirtyFlags & 4194305) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear4HourClock = weatherViewModelWeatherInfo.near4HourClock;
                }
                updateRegistration(0, (Observable) weatherViewModelWeatherInfoNear4HourClock);
                if (weatherViewModelWeatherInfoNear4HourClock != null) {
                    weatherViewModelWeatherInfoNear4HourClockGet2 = weatherViewModelWeatherInfoNear4HourClock.get();
                }
            }
            if ((dirtyFlags & 4194306) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear1HourImg = weatherViewModelWeatherInfo.near1HourImg;
                }
                updateRegistration(1, (Observable) weatherViewModelWeatherInfoNear1HourImg);
                if (weatherViewModelWeatherInfoNear1HourImg != null) {
                    weatherViewModelWeatherInfoNear1HourImgGet3 = weatherViewModelWeatherInfoNear1HourImg.get();
                }
            }
            if ((dirtyFlags & 4194308) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear4HourImg = weatherViewModelWeatherInfo.near4HourImg;
                }
                updateRegistration(2, (Observable) weatherViewModelWeatherInfoNear4HourImg);
                if (weatherViewModelWeatherInfoNear4HourImg != null) {
                    weatherViewModelWeatherInfoNear4HourImgGet = weatherViewModelWeatherInfoNear4HourImg.get();
                }
            }
            if ((dirtyFlags & 4194312) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear4HourClockUnit = weatherViewModelWeatherInfo.near4HourClockUnit;
                }
                updateRegistration(3, (Observable) weatherViewModelWeatherInfoNear4HourClockUnit);
                if (weatherViewModelWeatherInfoNear4HourClockUnit != null) {
                    weatherViewModelWeatherInfoNear4HourClockUnitGet2 = weatherViewModelWeatherInfoNear4HourClockUnit.get();
                }
            }
            if ((dirtyFlags & 4194320) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear4HourTemp = weatherViewModelWeatherInfo.near4HourTemp;
                }
                updateRegistration(4, (Observable) weatherViewModelWeatherInfoNear4HourTemp);
                if (weatherViewModelWeatherInfoNear4HourTemp != null) {
                    weatherViewModelWeatherInfoNear4HourTempGet3 = weatherViewModelWeatherInfoNear4HourTemp.get();
                }
            }
            if ((dirtyFlags & 4194336) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear1HourClockUnit = weatherViewModelWeatherInfo.near1HourClockUnit;
                } else {
                    weatherViewModelWeatherInfoNear1HourClockUnit = null;
                }
                weatherViewModelWeatherInfoNear4HourClockGet = weatherViewModelWeatherInfoNear4HourClockGet2;
                updateRegistration(5, (Observable) weatherViewModelWeatherInfoNear1HourClockUnit);
                if (weatherViewModelWeatherInfoNear1HourClockUnit != null) {
                    weatherViewModelWeatherInfoNear1HourClockUnitGet = weatherViewModelWeatherInfoNear1HourClockUnit.get();
                    ObservableField<String> observableField = weatherViewModelWeatherInfoNear1HourClockUnit;
                } else {
                    ObservableField<String> observableField2 = weatherViewModelWeatherInfoNear1HourClockUnit;
                }
            } else {
                weatherViewModelWeatherInfoNear4HourClockGet = weatherViewModelWeatherInfoNear4HourClockGet2;
            }
            if ((dirtyFlags & 4194368) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear3HourImg = weatherViewModelWeatherInfo.near3HourImg;
                } else {
                    weatherViewModelWeatherInfoNear3HourImg = null;
                }
                updateRegistration(6, (Observable) weatherViewModelWeatherInfoNear3HourImg);
                if (weatherViewModelWeatherInfoNear3HourImg != null) {
                    weatherViewModelWeatherInfoNear3HourImgGet = weatherViewModelWeatherInfoNear3HourImg.get();
                    ObservableField<Drawable> observableField3 = weatherViewModelWeatherInfoNear3HourImg;
                } else {
                    ObservableField<Drawable> observableField4 = weatherViewModelWeatherInfoNear3HourImg;
                }
            }
            if ((dirtyFlags & 4194432) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear3HourClock = weatherViewModelWeatherInfo.near3HourClock;
                } else {
                    weatherViewModelWeatherInfoNear3HourClock = null;
                }
                updateRegistration(7, (Observable) weatherViewModelWeatherInfoNear3HourClock);
                if (weatherViewModelWeatherInfoNear3HourClock != null) {
                    weatherViewModelWeatherInfoNear3HourClockGet2 = weatherViewModelWeatherInfoNear3HourClock.get();
                    ObservableField<String> observableField5 = weatherViewModelWeatherInfoNear3HourClock;
                } else {
                    ObservableField<String> observableField6 = weatherViewModelWeatherInfoNear3HourClock;
                }
            }
            if ((dirtyFlags & 4194560) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear5HourClockUnit = weatherViewModelWeatherInfo.near5HourClockUnit;
                } else {
                    weatherViewModelWeatherInfoNear5HourClockUnit = null;
                }
                updateRegistration(8, (Observable) weatherViewModelWeatherInfoNear5HourClockUnit);
                if (weatherViewModelWeatherInfoNear5HourClockUnit != null) {
                    weatherViewModelWeatherInfoNear5HourClockUnitGet4 = weatherViewModelWeatherInfoNear5HourClockUnit.get();
                    ObservableField<String> observableField7 = weatherViewModelWeatherInfoNear5HourClockUnit;
                } else {
                    ObservableField<String> observableField8 = weatherViewModelWeatherInfoNear5HourClockUnit;
                }
            }
            if ((dirtyFlags & 4194816) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear1HourTemp = weatherViewModelWeatherInfo.near1HourTemp;
                } else {
                    weatherViewModelWeatherInfoNear1HourTemp = null;
                }
                updateRegistration(9, (Observable) weatherViewModelWeatherInfoNear1HourTemp);
                if (weatherViewModelWeatherInfoNear1HourTemp != null) {
                    weatherViewModelWeatherInfoNear1HourTempGet4 = weatherViewModelWeatherInfoNear1HourTemp.get();
                    ObservableField<String> observableField9 = weatherViewModelWeatherInfoNear1HourTemp;
                } else {
                    ObservableField<String> observableField10 = weatherViewModelWeatherInfoNear1HourTemp;
                }
            }
            if ((dirtyFlags & 4195328) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear2HourImg = weatherViewModelWeatherInfo.near2HourImg;
                } else {
                    weatherViewModelWeatherInfoNear2HourImg = null;
                }
                updateRegistration(10, (Observable) weatherViewModelWeatherInfoNear2HourImg);
                if (weatherViewModelWeatherInfoNear2HourImg != null) {
                    weatherViewModelWeatherInfoNear2HourImgGet3 = weatherViewModelWeatherInfoNear2HourImg.get();
                    ObservableField<Drawable> observableField11 = weatherViewModelWeatherInfoNear2HourImg;
                } else {
                    ObservableField<Drawable> observableField12 = weatherViewModelWeatherInfoNear2HourImg;
                }
            }
            if ((dirtyFlags & 4196352) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear5HourImg = weatherViewModelWeatherInfo.near5HourImg;
                } else {
                    weatherViewModelWeatherInfoNear5HourImg = null;
                }
                updateRegistration(11, (Observable) weatherViewModelWeatherInfoNear5HourImg);
                if (weatherViewModelWeatherInfoNear5HourImg != null) {
                    weatherViewModelWeatherInfoNear5HourImgGet3 = weatherViewModelWeatherInfoNear5HourImg.get();
                    ObservableField<Drawable> observableField13 = weatherViewModelWeatherInfoNear5HourImg;
                } else {
                    ObservableField<Drawable> observableField14 = weatherViewModelWeatherInfoNear5HourImg;
                }
            }
            if ((dirtyFlags & 4198400) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear3HourTemp = weatherViewModelWeatherInfo.near3HourTemp;
                } else {
                    weatherViewModelWeatherInfoNear3HourTemp = null;
                }
                updateRegistration(12, (Observable) weatherViewModelWeatherInfoNear3HourTemp);
                if (weatherViewModelWeatherInfoNear3HourTemp != null) {
                    weatherViewModelWeatherInfoNear3HourTempGet2 = weatherViewModelWeatherInfoNear3HourTemp.get();
                    ObservableField<String> observableField15 = weatherViewModelWeatherInfoNear3HourTemp;
                } else {
                    ObservableField<String> observableField16 = weatherViewModelWeatherInfoNear3HourTemp;
                }
            }
            if ((dirtyFlags & 4202496) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear2HourClockUnit = weatherViewModelWeatherInfo.near2HourClockUnit;
                } else {
                    weatherViewModelWeatherInfoNear2HourClockUnit = null;
                }
                updateRegistration(13, (Observable) weatherViewModelWeatherInfoNear2HourClockUnit);
                if (weatherViewModelWeatherInfoNear2HourClockUnit != null) {
                    weatherViewModelWeatherInfoNear2HourClockUnitGet2 = weatherViewModelWeatherInfoNear2HourClockUnit.get();
                    ObservableField<String> observableField17 = weatherViewModelWeatherInfoNear2HourClockUnit;
                } else {
                    ObservableField<String> observableField18 = weatherViewModelWeatherInfoNear2HourClockUnit;
                }
            }
            if ((dirtyFlags & 4210688) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear2HourClock = weatherViewModelWeatherInfo.near2HourClock;
                } else {
                    weatherViewModelWeatherInfoNear2HourClock = null;
                }
                updateRegistration(14, (Observable) weatherViewModelWeatherInfoNear2HourClock);
                if (weatherViewModelWeatherInfoNear2HourClock != null) {
                    weatherViewModelWeatherInfoNear2HourClockGet2 = weatherViewModelWeatherInfoNear2HourClock.get();
                    ObservableField<String> observableField19 = weatherViewModelWeatherInfoNear2HourClock;
                } else {
                    ObservableField<String> observableField20 = weatherViewModelWeatherInfoNear2HourClock;
                }
            }
            if ((dirtyFlags & 4227072) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoTemperUnit = weatherViewModelWeatherInfo.temperUnit;
                } else {
                    weatherViewModelWeatherInfoTemperUnit = null;
                }
                updateRegistration(15, (Observable) weatherViewModelWeatherInfoTemperUnit);
                if (weatherViewModelWeatherInfoTemperUnit != null) {
                    weatherViewModelWeatherInfoTemperUnitGet2 = weatherViewModelWeatherInfoTemperUnit.get();
                    ObservableField<String> observableField21 = weatherViewModelWeatherInfoTemperUnit;
                } else {
                    ObservableField<String> observableField22 = weatherViewModelWeatherInfoTemperUnit;
                }
            }
            if ((dirtyFlags & 4259840) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear1HourClock = weatherViewModelWeatherInfo.near1HourClock;
                } else {
                    weatherViewModelWeatherInfoNear1HourClock = null;
                }
                updateRegistration(16, (Observable) weatherViewModelWeatherInfoNear1HourClock);
                if (weatherViewModelWeatherInfoNear1HourClock != null) {
                    weatherViewModelWeatherInfoNear1HourClockGet = weatherViewModelWeatherInfoNear1HourClock.get();
                    ObservableField<String> observableField23 = weatherViewModelWeatherInfoNear1HourClock;
                } else {
                    ObservableField<String> observableField24 = weatherViewModelWeatherInfoNear1HourClock;
                }
            }
            if ((dirtyFlags & 4325376) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear5HourTemp = weatherViewModelWeatherInfo.near5HourTemp;
                } else {
                    weatherViewModelWeatherInfoNear5HourTemp = null;
                }
                updateRegistration(17, (Observable) weatherViewModelWeatherInfoNear5HourTemp);
                if (weatherViewModelWeatherInfoNear5HourTemp != null) {
                    weatherViewModelWeatherInfoNear5HourTempGet3 = weatherViewModelWeatherInfoNear5HourTemp.get();
                    ObservableField<String> observableField25 = weatherViewModelWeatherInfoNear5HourTemp;
                } else {
                    ObservableField<String> observableField26 = weatherViewModelWeatherInfoNear5HourTemp;
                }
            }
            if ((dirtyFlags & 4456448) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear5HourClock = weatherViewModelWeatherInfo.near5HourClock;
                } else {
                    weatherViewModelWeatherInfoNear5HourClock = null;
                }
                updateRegistration(18, (Observable) weatherViewModelWeatherInfoNear5HourClock);
                if (weatherViewModelWeatherInfoNear5HourClock != null) {
                    weatherViewModelWeatherInfoNear5HourClockGet2 = weatherViewModelWeatherInfoNear5HourClock.get();
                    ObservableField<String> observableField27 = weatherViewModelWeatherInfoNear5HourClock;
                } else {
                    ObservableField<String> observableField28 = weatherViewModelWeatherInfoNear5HourClock;
                }
            }
            if ((dirtyFlags & 4718592) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear2HourTemp = weatherViewModelWeatherInfo.near2HourTemp;
                } else {
                    weatherViewModelWeatherInfoNear2HourTemp = null;
                }
                updateRegistration(19, (Observable) weatherViewModelWeatherInfoNear2HourTemp);
                if (weatherViewModelWeatherInfoNear2HourTemp != null) {
                    weatherViewModelWeatherInfoNear2HourTempGet2 = weatherViewModelWeatherInfoNear2HourTemp.get();
                    ObservableField<String> observableField29 = weatherViewModelWeatherInfoNear2HourTemp;
                } else {
                    ObservableField<String> observableField30 = weatherViewModelWeatherInfoNear2HourTemp;
                }
            }
            if ((dirtyFlags & 5242880) != 0) {
                if (weatherViewModelWeatherInfo != null) {
                    weatherViewModelWeatherInfoNear3HourClockUnit = weatherViewModelWeatherInfo.near3HourClockUnit;
                } else {
                    weatherViewModelWeatherInfoNear3HourClockUnit = null;
                }
                updateRegistration(20, (Observable) weatherViewModelWeatherInfoNear3HourClockUnit);
                if (weatherViewModelWeatherInfoNear3HourClockUnit != null) {
                    ObservableField<String> observableField31 = weatherViewModelWeatherInfoNear3HourClockUnit;
                    weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnitGet2;
                    weatherViewModelWeatherInfoNear2HourTempGet = weatherViewModelWeatherInfoNear2HourTempGet2;
                    weatherViewModelWeatherInfoNear1HourTempGet = weatherViewModelWeatherInfoNear1HourTempGet4;
                    weatherViewModelWeatherInfoNear4HourClockGet2 = weatherViewModelWeatherInfoNear4HourClockGet;
                    weatherViewModelWeatherInfoNear2HourImgGet = weatherViewModelWeatherInfoNear2HourImgGet3;
                    weatherViewModelWeatherInfoNear5HourTempGet = weatherViewModelWeatherInfoNear5HourTempGet3;
                    WeatherInfo weatherInfo = weatherViewModelWeatherInfo;
                    ObservableField<Drawable> observableField32 = weatherViewModelWeatherInfoNear1HourImg;
                    weatherViewModelWeatherInfoNear5HourClockGet = weatherViewModelWeatherInfoNear5HourClockGet2;
                    weatherViewModelWeatherInfoNear4HourClockUnitGet = weatherViewModelWeatherInfoNear4HourClockUnitGet2;
                    ObservableField<String> observableField33 = weatherViewModelWeatherInfoNear4HourClock;
                    weatherViewModelWeatherInfoNear5HourClockUnitGet = weatherViewModelWeatherInfoNear5HourClockUnitGet4;
                    weatherViewModelWeatherInfoNear1HourImgGet = weatherViewModelWeatherInfoNear1HourImgGet3;
                    weatherViewModelWeatherInfoNear3HourTempGet = weatherViewModelWeatherInfoNear3HourTempGet2;
                    String str = weatherViewModelWeatherInfoNear2HourClockUnitGet2;
                    ObservableField<Drawable> observableField34 = weatherViewModelWeatherInfoNear4HourImg;
                    weatherViewModelWeatherInfoNear3HourClockGet = weatherViewModelWeatherInfoNear3HourClockGet2;
                    ObservableField<String> observableField35 = weatherViewModelWeatherInfoNear4HourTemp;
                    weatherViewModelWeatherInfoNear4HourTempGet2 = weatherViewModelWeatherInfoNear4HourTempGet3;
                    weatherViewModelWeatherInfoNear4HourTempGet = weatherViewModelWeatherInfoNear2HourClockGet2;
                    weatherViewModelWeatherInfoNear2HourClockGet = weatherViewModelWeatherInfoNear1HourClockGet;
                    weatherViewModelWeatherInfoNear5HourImgGet = weatherViewModelWeatherInfoNear5HourImgGet3;
                    ObservableField<String> observableField36 = weatherViewModelWeatherInfoNear4HourClockUnit;
                    weatherViewModelWeatherInfoNear3HourClockUnitGet2 = weatherViewModelWeatherInfoNear3HourClockUnit.get();
                    weatherViewModelWeatherInfoNear3HourClockUnitGet = str;
                } else {
                    ObservableField<String> observableField37 = weatherViewModelWeatherInfoNear3HourClockUnit;
                    weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnitGet2;
                    weatherViewModelWeatherInfoNear2HourTempGet = weatherViewModelWeatherInfoNear2HourTempGet2;
                    weatherViewModelWeatherInfoNear1HourTempGet = weatherViewModelWeatherInfoNear1HourTempGet4;
                    weatherViewModelWeatherInfoNear4HourClockGet2 = weatherViewModelWeatherInfoNear4HourClockGet;
                    weatherViewModelWeatherInfoNear2HourImgGet = weatherViewModelWeatherInfoNear2HourImgGet3;
                    weatherViewModelWeatherInfoNear5HourTempGet = weatherViewModelWeatherInfoNear5HourTempGet3;
                    WeatherInfo weatherInfo2 = weatherViewModelWeatherInfo;
                    ObservableField<Drawable> observableField38 = weatherViewModelWeatherInfoNear1HourImg;
                    weatherViewModelWeatherInfoNear5HourClockGet = weatherViewModelWeatherInfoNear5HourClockGet2;
                    weatherViewModelWeatherInfoNear4HourClockUnitGet = weatherViewModelWeatherInfoNear4HourClockUnitGet2;
                    ObservableField<String> observableField39 = weatherViewModelWeatherInfoNear4HourClock;
                    weatherViewModelWeatherInfoNear5HourClockUnitGet = weatherViewModelWeatherInfoNear5HourClockUnitGet4;
                    weatherViewModelWeatherInfoNear1HourImgGet = weatherViewModelWeatherInfoNear1HourImgGet3;
                    weatherViewModelWeatherInfoNear3HourTempGet = weatherViewModelWeatherInfoNear3HourTempGet2;
                    String str2 = weatherViewModelWeatherInfoNear2HourClockUnitGet2;
                    ObservableField<Drawable> observableField40 = weatherViewModelWeatherInfoNear4HourImg;
                    weatherViewModelWeatherInfoNear3HourClockGet = weatherViewModelWeatherInfoNear3HourClockGet2;
                    ObservableField<String> observableField41 = weatherViewModelWeatherInfoNear4HourTemp;
                    weatherViewModelWeatherInfoNear4HourTempGet2 = weatherViewModelWeatherInfoNear4HourTempGet3;
                    weatherViewModelWeatherInfoNear4HourTempGet = weatherViewModelWeatherInfoNear2HourClockGet2;
                    weatherViewModelWeatherInfoNear2HourClockGet = weatherViewModelWeatherInfoNear1HourClockGet;
                    weatherViewModelWeatherInfoNear5HourImgGet = weatherViewModelWeatherInfoNear5HourImgGet3;
                    ObservableField<String> observableField42 = weatherViewModelWeatherInfoNear4HourClockUnit;
                    weatherViewModelWeatherInfoNear3HourClockUnitGet2 = null;
                    weatherViewModelWeatherInfoNear3HourClockUnitGet = str2;
                }
            } else {
                weatherViewModelWeatherInfoTemperUnitGet = weatherViewModelWeatherInfoTemperUnitGet2;
                weatherViewModelWeatherInfoNear2HourTempGet = weatherViewModelWeatherInfoNear2HourTempGet2;
                weatherViewModelWeatherInfoNear1HourTempGet = weatherViewModelWeatherInfoNear1HourTempGet4;
                weatherViewModelWeatherInfoNear4HourClockGet2 = weatherViewModelWeatherInfoNear4HourClockGet;
                weatherViewModelWeatherInfoNear2HourImgGet = weatherViewModelWeatherInfoNear2HourImgGet3;
                weatherViewModelWeatherInfoNear5HourTempGet = weatherViewModelWeatherInfoNear5HourTempGet3;
                WeatherInfo weatherInfo3 = weatherViewModelWeatherInfo;
                ObservableField<Drawable> observableField43 = weatherViewModelWeatherInfoNear1HourImg;
                weatherViewModelWeatherInfoNear5HourClockGet = weatherViewModelWeatherInfoNear5HourClockGet2;
                weatherViewModelWeatherInfoNear4HourClockUnitGet = weatherViewModelWeatherInfoNear4HourClockUnitGet2;
                ObservableField<String> observableField44 = weatherViewModelWeatherInfoNear4HourClock;
                weatherViewModelWeatherInfoNear5HourClockUnitGet = weatherViewModelWeatherInfoNear5HourClockUnitGet4;
                weatherViewModelWeatherInfoNear1HourImgGet = weatherViewModelWeatherInfoNear1HourImgGet3;
                weatherViewModelWeatherInfoNear3HourTempGet = weatherViewModelWeatherInfoNear3HourTempGet2;
                String str3 = weatherViewModelWeatherInfoNear2HourClockUnitGet2;
                ObservableField<Drawable> observableField45 = weatherViewModelWeatherInfoNear4HourImg;
                weatherViewModelWeatherInfoNear3HourClockGet = weatherViewModelWeatherInfoNear3HourClockGet2;
                ObservableField<String> observableField46 = weatherViewModelWeatherInfoNear4HourTemp;
                weatherViewModelWeatherInfoNear4HourTempGet2 = weatherViewModelWeatherInfoNear4HourTempGet3;
                weatherViewModelWeatherInfoNear4HourTempGet = weatherViewModelWeatherInfoNear2HourClockGet2;
                weatherViewModelWeatherInfoNear2HourClockGet = weatherViewModelWeatherInfoNear1HourClockGet;
                weatherViewModelWeatherInfoNear5HourImgGet = weatherViewModelWeatherInfoNear5HourImgGet3;
                ObservableField<String> observableField47 = weatherViewModelWeatherInfoNear4HourClockUnit;
                weatherViewModelWeatherInfoNear3HourClockUnitGet2 = null;
                weatherViewModelWeatherInfoNear3HourClockUnitGet = str3;
            }
        } else {
            weatherViewModelWeatherInfoNear5HourClockGet = null;
            weatherViewModelWeatherInfoTemperUnitGet = null;
            weatherViewModelWeatherInfoNear2HourTempGet = null;
            weatherViewModelWeatherInfoNear1HourTempGet = null;
            weatherViewModelWeatherInfoNear2HourImgGet = null;
            weatherViewModelWeatherInfoNear5HourTempGet = null;
            weatherViewModelWeatherInfoNear5HourClockUnitGet = null;
            weatherViewModelWeatherInfoNear4HourClockUnitGet = null;
            weatherViewModelWeatherInfoNear1HourImgGet = null;
            weatherViewModelWeatherInfoNear3HourTempGet = null;
            weatherViewModelWeatherInfoNear3HourClockGet = null;
            weatherViewModelWeatherInfoNear4HourTempGet2 = null;
            weatherViewModelWeatherInfoNear4HourTempGet = null;
            weatherViewModelWeatherInfoNear2HourClockGet = null;
            weatherViewModelWeatherInfoNear5HourImgGet = null;
            weatherViewModelWeatherInfoNear3HourClockUnitGet2 = null;
            weatherViewModelWeatherInfoNear3HourClockUnitGet = null;
        }
        if ((dirtyFlags & 4259840) != 0) {
            weatherViewModelWeatherInfoNear5HourClockUnitGet2 = weatherViewModelWeatherInfoNear5HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView1, weatherViewModelWeatherInfoNear2HourClockGet);
        } else {
            weatherViewModelWeatherInfoNear5HourClockUnitGet2 = weatherViewModelWeatherInfoNear5HourClockUnitGet;
        }
        if ((dirtyFlags & 4227072) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, weatherViewModelWeatherInfoTemperUnitGet);
            TextViewBindingAdapter.setText(this.mboundView15, weatherViewModelWeatherInfoTemperUnitGet);
            TextViewBindingAdapter.setText(this.mboundView20, weatherViewModelWeatherInfoTemperUnitGet);
            TextViewBindingAdapter.setText(this.mboundView25, weatherViewModelWeatherInfoTemperUnitGet);
            TextViewBindingAdapter.setText(this.mboundView5, weatherViewModelWeatherInfoTemperUnitGet);
        }
        if ((dirtyFlags & 4194432) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, weatherViewModelWeatherInfoNear3HourClockGet);
        }
        if ((dirtyFlags & 5242880) != 0) {
            TextViewBindingAdapter.setText(this.mboundView12, weatherViewModelWeatherInfoNear3HourClockUnitGet2);
        }
        if ((dirtyFlags & 4194368) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView13, weatherViewModelWeatherInfoNear3HourImgGet);
        }
        if ((dirtyFlags & 4198400) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, weatherViewModelWeatherInfoNear3HourTempGet);
        }
        if ((dirtyFlags & 4194305) != 0) {
            TextViewBindingAdapter.setText(this.mboundView16, weatherViewModelWeatherInfoNear4HourClockGet2);
        }
        if ((dirtyFlags & 4194312) != 0) {
            TextViewBindingAdapter.setText(this.mboundView17, weatherViewModelWeatherInfoNear4HourClockUnitGet);
        }
        if ((dirtyFlags & 4194308) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView18, weatherViewModelWeatherInfoNear4HourImgGet);
        }
        if ((dirtyFlags & 4194320) != 0) {
            TextViewBindingAdapter.setText(this.mboundView19, weatherViewModelWeatherInfoNear4HourTempGet2);
        }
        if ((dirtyFlags & 4194336) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, weatherViewModelWeatherInfoNear1HourClockUnitGet);
        }
        if ((dirtyFlags & 4456448) != 0) {
            TextViewBindingAdapter.setText(this.mboundView21, weatherViewModelWeatherInfoNear5HourClockGet);
        }
        if ((dirtyFlags & 4194560) != 0) {
            String str4 = weatherViewModelWeatherInfoNear4HourClockGet2;
            weatherViewModelWeatherInfoNear5HourClockUnitGet3 = weatherViewModelWeatherInfoNear5HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView22, weatherViewModelWeatherInfoNear5HourClockUnitGet3);
        } else {
            weatherViewModelWeatherInfoNear5HourClockUnitGet3 = weatherViewModelWeatherInfoNear5HourClockUnitGet2;
        }
        if ((dirtyFlags & 4196352) != 0) {
            String str5 = weatherViewModelWeatherInfoNear5HourClockUnitGet3;
            weatherViewModelWeatherInfoNear5HourImgGet2 = weatherViewModelWeatherInfoNear5HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView23, weatherViewModelWeatherInfoNear5HourImgGet2);
        } else {
            weatherViewModelWeatherInfoNear5HourImgGet2 = weatherViewModelWeatherInfoNear5HourImgGet;
        }
        if ((dirtyFlags & 4325376) != 0) {
            Drawable drawable = weatherViewModelWeatherInfoNear5HourImgGet2;
            weatherViewModelWeatherInfoNear5HourTempGet2 = weatherViewModelWeatherInfoNear5HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView24, weatherViewModelWeatherInfoNear5HourTempGet2);
        } else {
            weatherViewModelWeatherInfoNear5HourTempGet2 = weatherViewModelWeatherInfoNear5HourTempGet;
        }
        if ((dirtyFlags & 4194306) != 0) {
            String str6 = weatherViewModelWeatherInfoNear5HourTempGet2;
            weatherViewModelWeatherInfoNear1HourImgGet2 = weatherViewModelWeatherInfoNear1HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, weatherViewModelWeatherInfoNear1HourImgGet2);
        } else {
            weatherViewModelWeatherInfoNear1HourImgGet2 = weatherViewModelWeatherInfoNear1HourImgGet;
        }
        if ((dirtyFlags & 4194816) != 0) {
            Drawable drawable2 = weatherViewModelWeatherInfoNear1HourImgGet2;
            weatherViewModelWeatherInfoNear1HourTempGet2 = weatherViewModelWeatherInfoNear1HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView4, weatherViewModelWeatherInfoNear1HourTempGet2);
        } else {
            weatherViewModelWeatherInfoNear1HourTempGet2 = weatherViewModelWeatherInfoNear1HourTempGet;
        }
        if ((dirtyFlags & 4210688) != 0) {
            String str7 = weatherViewModelWeatherInfoNear1HourTempGet2;
            weatherViewModelWeatherInfoNear1HourTempGet3 = weatherViewModelWeatherInfoNear4HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView6, weatherViewModelWeatherInfoNear1HourTempGet3);
        } else {
            weatherViewModelWeatherInfoNear1HourTempGet3 = weatherViewModelWeatherInfoNear4HourTempGet;
        }
        if ((dirtyFlags & 4202496) != 0) {
            String str8 = weatherViewModelWeatherInfoNear1HourTempGet3;
            weatherViewModelWeatherInfoNear2HourClockUnitGet = weatherViewModelWeatherInfoNear3HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView7, weatherViewModelWeatherInfoNear2HourClockUnitGet);
        } else {
            String weatherViewModelWeatherInfoNear2HourClockGet3 = weatherViewModelWeatherInfoNear1HourTempGet3;
            weatherViewModelWeatherInfoNear2HourClockUnitGet = weatherViewModelWeatherInfoNear3HourClockUnitGet;
        }
        if ((dirtyFlags & 4195328) != 0) {
            String str9 = weatherViewModelWeatherInfoNear2HourClockUnitGet;
            weatherViewModelWeatherInfoNear2HourImgGet2 = weatherViewModelWeatherInfoNear2HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, weatherViewModelWeatherInfoNear2HourImgGet2);
        } else {
            weatherViewModelWeatherInfoNear2HourImgGet2 = weatherViewModelWeatherInfoNear2HourImgGet;
        }
        if ((dirtyFlags & 4718592) != 0) {
            Drawable drawable3 = weatherViewModelWeatherInfoNear2HourImgGet2;
            TextViewBindingAdapter.setText(this.mboundView9, weatherViewModelWeatherInfoNear2HourTempGet);
            return;
        }
        String str10 = weatherViewModelWeatherInfoNear2HourTempGet;
    }
}

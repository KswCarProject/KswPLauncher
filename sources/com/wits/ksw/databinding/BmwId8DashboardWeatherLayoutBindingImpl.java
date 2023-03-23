package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

public class BmwId8DashboardWeatherLayoutBindingImpl extends BmwId8DashboardWeatherLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ProgressBar mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final LinearLayout mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final ImageView mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final LinearLayout mboundView2;
    private final TextView mboundView20;
    private final ImageView mboundView21;
    private final TextView mboundView22;
    private final TextView mboundView23;
    private final TextView mboundView24;
    private final TextView mboundView25;
    private final ImageView mboundView26;
    private final TextView mboundView27;
    private final TextView mboundView28;
    private final TextView mboundView29;
    private final TextView mboundView3;
    private final TextView mboundView30;
    private final ImageView mboundView31;
    private final TextView mboundView32;
    private final TextView mboundView33;
    private final TextView mboundView34;
    private final TextView mboundView35;
    private final ImageView mboundView36;
    private final TextView mboundView37;
    private final TextView mboundView38;
    private final MarqueeTextView mboundView4;
    private final TextView mboundView8;
    private final LinearLayout mboundView9;

    public BmwId8DashboardWeatherLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 39, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardWeatherLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 34, bindings[5], bindings[6], bindings[7]);
        this.mDirtyFlags = -1;
        this.ivIcon.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ProgressBar progressBar = bindings[1];
        this.mboundView1 = progressBar;
        progressBar.setTag((Object) null);
        TextView textView = bindings[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag((Object) null);
        LinearLayout linearLayout = bindings[13];
        this.mboundView13 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView4 = bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = bindings[15];
        this.mboundView15 = textView5;
        textView5.setTag((Object) null);
        ImageView imageView = bindings[16];
        this.mboundView16 = imageView;
        imageView.setTag((Object) null);
        TextView textView6 = bindings[17];
        this.mboundView17 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = bindings[18];
        this.mboundView18 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = bindings[19];
        this.mboundView19 = textView8;
        textView8.setTag((Object) null);
        LinearLayout linearLayout2 = bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag((Object) null);
        TextView textView9 = bindings[20];
        this.mboundView20 = textView9;
        textView9.setTag((Object) null);
        ImageView imageView2 = bindings[21];
        this.mboundView21 = imageView2;
        imageView2.setTag((Object) null);
        TextView textView10 = bindings[22];
        this.mboundView22 = textView10;
        textView10.setTag((Object) null);
        TextView textView11 = bindings[23];
        this.mboundView23 = textView11;
        textView11.setTag((Object) null);
        TextView textView12 = bindings[24];
        this.mboundView24 = textView12;
        textView12.setTag((Object) null);
        TextView textView13 = bindings[25];
        this.mboundView25 = textView13;
        textView13.setTag((Object) null);
        ImageView imageView3 = bindings[26];
        this.mboundView26 = imageView3;
        imageView3.setTag((Object) null);
        TextView textView14 = bindings[27];
        this.mboundView27 = textView14;
        textView14.setTag((Object) null);
        TextView textView15 = bindings[28];
        this.mboundView28 = textView15;
        textView15.setTag((Object) null);
        TextView textView16 = bindings[29];
        this.mboundView29 = textView16;
        textView16.setTag((Object) null);
        TextView textView17 = bindings[3];
        this.mboundView3 = textView17;
        textView17.setTag((Object) null);
        TextView textView18 = bindings[30];
        this.mboundView30 = textView18;
        textView18.setTag((Object) null);
        ImageView imageView4 = bindings[31];
        this.mboundView31 = imageView4;
        imageView4.setTag((Object) null);
        TextView textView19 = bindings[32];
        this.mboundView32 = textView19;
        textView19.setTag((Object) null);
        TextView textView20 = bindings[33];
        this.mboundView33 = textView20;
        textView20.setTag((Object) null);
        TextView textView21 = bindings[34];
        this.mboundView34 = textView21;
        textView21.setTag((Object) null);
        TextView textView22 = bindings[35];
        this.mboundView35 = textView22;
        textView22.setTag((Object) null);
        ImageView imageView5 = bindings[36];
        this.mboundView36 = imageView5;
        imageView5.setTag((Object) null);
        TextView textView23 = bindings[37];
        this.mboundView37 = textView23;
        textView23.setTag((Object) null);
        TextView textView24 = bindings[38];
        this.mboundView38 = textView24;
        textView24.setTag((Object) null);
        MarqueeTextView marqueeTextView = bindings[4];
        this.mboundView4 = marqueeTextView;
        marqueeTextView.setTag((Object) null);
        TextView textView25 = bindings[8];
        this.mboundView8 = textView25;
        textView25.setTag((Object) null);
        LinearLayout linearLayout3 = bindings[9];
        this.mboundView9 = linearLayout3;
        linearLayout3.setTag((Object) null);
        this.temperatureTv.setTag((Object) null);
        this.unitWeather.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 34359738368L;
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((DashboardViewModel) variable);
        return true;
    }

    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelWeatherInfoNear2HourClock((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelWeatherInfoCardDetailText2((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelWeatherInfoNear5HourClockUnit((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelWeatherInfoNear5HourClock((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelWeatherInfoNear1HourTemp((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelWeatherInfoNear2HourImg((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelWeatherInfoNear5HourTemp((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelWeatherInfoNear4HourImg((ObservableField) object, fieldId);
            case 9:
                return onChangeViewModelWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 10:
                return onChangeViewModelWeatherInfoTemperature((ObservableField) object, fieldId);
            case 11:
                return onChangeViewModelWeatherInfoNear1HourImg((ObservableField) object, fieldId);
            case 12:
                return onChangeViewModelWeatherInfoNear3HourClock((ObservableField) object, fieldId);
            case 13:
                return onChangeViewModelWeatherInfoNear1HourClockUnit((ObservableField) object, fieldId);
            case 14:
                return onChangeViewModelWeatherInfoNear3HourClockUnit((ObservableField) object, fieldId);
            case 15:
                return onChangeViewModelWeatherInfoId8CardDetailImg3((ObservableField) object, fieldId);
            case 16:
                return onChangeViewModelWeatherInfoId8CardDetailImg1((ObservableField) object, fieldId);
            case 17:
                return onChangeViewModelWeatherInfoNear5HourImg((ObservableField) object, fieldId);
            case 18:
                return onChangeViewModelWeatherInfoNear4HourClock((ObservableField) object, fieldId);
            case 19:
                return onChangeViewModelWeatherInfoNear2HourClockUnit((ObservableField) object, fieldId);
            case 20:
                return onChangeViewModelWeatherInfoNear4HourTemp((ObservableField) object, fieldId);
            case 21:
                return onChangeViewModelWeatherInfoNear3HourTemp((ObservableField) object, fieldId);
            case 22:
                return onChangeViewModelWeatherInfoCardDetailText3((ObservableField) object, fieldId);
            case 23:
                return onChangeViewModelWeatherInfoNear3HourImg((ObservableField) object, fieldId);
            case 24:
                return onChangeViewModelWeatherInfoCardDetailText1((ObservableField) object, fieldId);
            case 25:
                return onChangeViewModelWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 26:
                return onChangeViewModelWeatherInfoNear4HourClockUnit((ObservableField) object, fieldId);
            case 27:
                return onChangeViewModelWeatherInfoNear2HourTemp((ObservableField) object, fieldId);
            case 28:
                return onChangeViewModelWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 29:
                return onChangeViewModelWeatherInfoId8ImageIcon((ObservableField) object, fieldId);
            case 30:
                return onChangeViewModelWeatherInfoId8CardDetailImg2((ObservableField) object, fieldId);
            case 31:
                return onChangeViewModelWeatherInfoNear1HourClock((ObservableField) object, fieldId);
            case 32:
                return onChangeViewModelWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 33:
                return onChangeViewModelWeatherInfoCity((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelWeatherInfoNear2HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoTemperatureRange(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoErrorMessage(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoTemperature(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg3(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText3(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourImg(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText1(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourClockUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourTemp(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoId8ImageIcon(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg2(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourClock(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoTemperUnit(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    private boolean onChangeViewModelWeatherInfoCity(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        Drawable viewModelWeatherInfoNear2HourImgGet;
        Drawable viewModelWeatherInfoNear3HourImgGet;
        String viewModelWeatherInfoErrorMessageGet;
        String viewModelWeatherInfoTemperatureRangeGet;
        String viewModelWeatherInfoNear5HourTempGet;
        String viewModelWeatherInfoNear4HourTempGet;
        Drawable viewModelWeatherInfoNear5HourImgGet;
        String viewModelWeatherInfoTemperatureGet;
        String viewModelWeatherInfoNear2HourClockGet;
        String viewModelWeatherInfoNear3HourClockUnitGet;
        String viewModelWeatherInfoNear2HourClockUnitGet;
        String viewModelWeatherInfoNear5HourTempGet2;
        String viewModelWeatherInfoNear4HourTempGet2;
        String viewModelWeatherInfoNear3HourClockUnitGet2;
        String viewModelWeatherInfoTemperatureGet2;
        String viewModelWeatherInfoNear2HourClockUnitGet2;
        String viewModelWeatherInfoNear4HourClockUnitGet;
        Drawable viewModelWeatherInfoNear4HourImgGet;
        String viewModelWeatherInfoNear1HourTempGet;
        String viewModelWeatherInfoNear3HourTempGet;
        Boolean viewModelWeatherInfoIsLoadSuccessGet;
        int viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        String viewModelWeatherInfoNear1HourTempGet2;
        String viewModelWeatherInfoCardDetailText3Get;
        Drawable viewModelWeatherInfoId8CardDetailImg2Get;
        Drawable viewModelWeatherInfoNear1HourImgGet;
        String viewModelWeatherInfoCardDetailText2Get;
        Drawable viewModelWeatherInfoId8CardDetailImg3Get;
        String viewModelWeatherInfoNear1HourClockGet;
        Drawable viewModelWeatherInfoId8ImageIconGet;
        Drawable viewModelWeatherInfoId8CardDetailImg1Get;
        String viewModelWeatherInfoNear1HourClockUnitGet;
        Drawable viewModelWeatherInfoNear1HourImgGet2;
        String viewModelWeatherInfoNear1HourTempGet3;
        Boolean viewModelWeatherInfoIsLoadSuccessGet2;
        int ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        int viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        String viewModelWeatherInfoNear1HourClockUnitGet2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather;
        String viewModelWeatherInfoNear1HourClockUnitGet3;
        Drawable viewModelWeatherInfoNear1HourImgGet3;
        String viewModelWeatherInfoNear1HourTempGet4;
        String viewModelWeatherInfoNear1HourTempGet5;
        String viewModelWeatherInfoNear2HourClockGet2;
        String viewModelWeatherInfoNear2HourClockGet3;
        Drawable viewModelWeatherInfoNear2HourImgGet2;
        String viewModelWeatherInfoNear2HourTempGet;
        String viewModelWeatherInfoNear3HourClockGet;
        String viewModelWeatherInfoNear3HourClockGet2;
        Drawable viewModelWeatherInfoNear3HourImgGet2;
        String viewModelWeatherInfoNear3HourTempGet2;
        String viewModelWeatherInfoNear4HourClockGet;
        String viewModelWeatherInfoErrorMessageGet2;
        String viewModelWeatherInfoErrorMessageGet3;
        Drawable viewModelWeatherInfoNear4HourImgGet2;
        String viewModelWeatherInfoNear4HourTempGet3;
        String viewModelWeatherInfoNear4HourTempGet4;
        String viewModelWeatherInfoNear5HourClockUnitGet;
        Drawable viewModelWeatherInfoNear5HourImgGet2;
        String viewModelWeatherInfoTemperatureRangeGet2;
        ObservableField<String> viewModelWeatherInfoCity;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess1;
        Boolean viewModelWeatherInfoIsLoadSuccessGet3;
        ObservableField<String> viewModelWeatherInfoTemperUnit;
        ObservableField<String> viewModelWeatherInfoNear1HourClock;
        ObservableField<Drawable> viewModelWeatherInfoId8CardDetailImg2;
        ObservableField<Drawable> viewModelWeatherInfoId8ImageIcon;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess12;
        ObservableField<String> viewModelWeatherInfoNear2HourTemp;
        ObservableField<String> viewModelWeatherInfoNear4HourClockUnit;
        ObservableField<Boolean> viewModelWeatherInfoIsInitFinished;
        ObservableField<String> viewModelWeatherInfoCardDetailText1;
        ObservableField<Drawable> viewModelWeatherInfoNear3HourImg;
        ObservableField<String> viewModelWeatherInfoCardDetailText3;
        ObservableField<String> viewModelWeatherInfoNear3HourTemp;
        ObservableField<String> viewModelWeatherInfoNear4HourTemp;
        ObservableField<String> viewModelWeatherInfoNear2HourClockUnit;
        ObservableField<String> viewModelWeatherInfoNear4HourClock;
        ObservableField<Drawable> viewModelWeatherInfoNear5HourImg;
        ObservableField<Drawable> viewModelWeatherInfoId8CardDetailImg1;
        ObservableField<Drawable> viewModelWeatherInfoId8CardDetailImg3;
        ObservableField<String> viewModelWeatherInfoNear3HourClockUnit;
        ObservableField<String> viewModelWeatherInfoNear1HourClockUnit;
        ObservableField<String> viewModelWeatherInfoNear3HourClock;
        ObservableField<Drawable> viewModelWeatherInfoNear1HourImg;
        ObservableField<String> viewModelWeatherInfoTemperature;
        ObservableField<String> viewModelWeatherInfoErrorMessage;
        ObservableField<Drawable> viewModelWeatherInfoNear4HourImg;
        ObservableField<String> viewModelWeatherInfoNear5HourTemp;
        ObservableField<Drawable> viewModelWeatherInfoNear2HourImg;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableField<String> viewModelWeatherInfoNear2HourClock = null;
        ObservableField<String> viewModelWeatherInfoTemperatureRange = null;
        String viewModelWeatherInfoNear5HourClockGet = null;
        ObservableField<String> viewModelWeatherInfoCardDetailText2 = null;
        ObservableField<String> viewModelWeatherInfoNear5HourClockUnit = null;
        String viewModelWeatherInfoCardDetailText1Get = null;
        ObservableField<String> viewModelWeatherInfoNear5HourClock = null;
        ObservableField<String> viewModelWeatherInfoNear1HourTemp = null;
        Boolean viewModelWeatherInfoIsLoadSuccessGet4 = null;
        String viewModelWeatherInfoNear4HourClockUnitGet2 = null;
        WeatherInfo viewModelWeatherInfo = null;
        String viewModelWeatherInfoNear4HourClockGet2 = null;
        Drawable viewModelWeatherInfoId8ImageIconGet2 = null;
        Drawable viewModelWeatherInfoNear4HourImgGet3 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = false;
        String viewModelWeatherInfoNear5HourClockUnitGet2 = null;
        String viewModelWeatherInfoNear2HourTempGet2 = null;
        Drawable viewModelWeatherInfoId8CardDetailImg2Get2 = null;
        String viewModelWeatherInfoNear3HourTempGet3 = null;
        String viewModelWeatherInfoNear1HourTempGet6 = null;
        String viewModelWeatherInfoNear3HourClockGet3 = null;
        Drawable viewModelWeatherInfoId8CardDetailImg3Get2 = null;
        String viewModelWeatherInfoNear2HourClockUnitGet3 = null;
        String viewModelWeatherInfoTemperatureGet3 = null;
        Drawable viewModelWeatherInfoNear5HourImgGet3 = null;
        String viewModelWeatherInfoNear4HourTempGet5 = null;
        String viewModelWeatherInfoNear5HourTempGet3 = null;
        int viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = 0;
        String viewModelWeatherInfoCityGet = null;
        String viewModelWeatherInfoNear3HourClockUnitGet3 = null;
        boolean viewModelWeatherInfoIsLoadSuccess = false;
        String viewModelWeatherInfoNear2HourClockGet4 = null;
        Drawable viewModelWeatherInfoId8CardDetailImg1Get2 = null;
        String viewModelWeatherInfoTemperatureRangeGet3 = null;
        String viewModelWeatherInfoCardDetailText2Get2 = null;
        Drawable viewModelWeatherInfoNear1HourImgGet4 = null;
        String viewModelWeatherInfoErrorMessageGet4 = null;
        String viewModelWeatherInfoNear1HourClockGet2 = null;
        String viewModelWeatherInfoCardDetailText3Get2 = null;
        Drawable viewModelWeatherInfoNear3HourImgGet3 = null;
        String viewModelWeatherInfoNear1HourClockUnitGet4 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess13 = null;
        Drawable viewModelWeatherInfoNear2HourImgGet3 = null;
        Boolean viewModelWeatherInfoIsInitFinishedGet = null;
        if ((dirtyFlags & 51539607551L) != 0) {
            viewModelWeatherInfo = DashboardViewModel.weatherInfo;
            if ((dirtyFlags & 34359738369L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear2HourClock = viewModelWeatherInfo.near2HourClock;
                }
                updateRegistration(0, (Observable) viewModelWeatherInfoNear2HourClock);
                if (viewModelWeatherInfoNear2HourClock != null) {
                    viewModelWeatherInfoNear2HourClockGet4 = viewModelWeatherInfoNear2HourClock.get();
                }
            }
            if ((dirtyFlags & 34359738370L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoTemperatureRange = viewModelWeatherInfo.temperatureRange;
                }
                updateRegistration(1, (Observable) viewModelWeatherInfoTemperatureRange);
                if (viewModelWeatherInfoTemperatureRange != null) {
                    viewModelWeatherInfoTemperatureRangeGet3 = viewModelWeatherInfoTemperatureRange.get();
                }
            }
            if ((dirtyFlags & 34359738372L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoCardDetailText2 = viewModelWeatherInfo.cardDetailText2;
                }
                updateRegistration(2, (Observable) viewModelWeatherInfoCardDetailText2);
                if (viewModelWeatherInfoCardDetailText2 != null) {
                    viewModelWeatherInfoCardDetailText2Get2 = viewModelWeatherInfoCardDetailText2.get();
                }
            }
            if ((dirtyFlags & 34359738376L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourClockUnit = viewModelWeatherInfo.near5HourClockUnit;
                }
                updateRegistration(3, (Observable) viewModelWeatherInfoNear5HourClockUnit);
                if (viewModelWeatherInfoNear5HourClockUnit != null) {
                    viewModelWeatherInfoNear5HourClockUnitGet2 = viewModelWeatherInfoNear5HourClockUnit.get();
                }
            }
            if ((dirtyFlags & 34359738384L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourClock = viewModelWeatherInfo.near5HourClock;
                }
                updateRegistration(4, (Observable) viewModelWeatherInfoNear5HourClock);
                if (viewModelWeatherInfoNear5HourClock != null) {
                    viewModelWeatherInfoNear5HourClockGet = viewModelWeatherInfoNear5HourClock.get();
                }
            }
            if ((dirtyFlags & 34359738400L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear1HourTemp = viewModelWeatherInfo.near1HourTemp;
                }
                updateRegistration(5, (Observable) viewModelWeatherInfoNear1HourTemp);
                if (viewModelWeatherInfoNear1HourTemp != null) {
                    viewModelWeatherInfoNear1HourTempGet6 = viewModelWeatherInfoNear1HourTemp.get();
                }
            }
            if ((dirtyFlags & 34359738432L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear2HourImg = viewModelWeatherInfo.near2HourImg;
                } else {
                    viewModelWeatherInfoNear2HourImg = null;
                }
                updateRegistration(6, (Observable) viewModelWeatherInfoNear2HourImg);
                if (viewModelWeatherInfoNear2HourImg != null) {
                    viewModelWeatherInfoNear2HourImgGet3 = viewModelWeatherInfoNear2HourImg.get();
                    ObservableField<Drawable> observableField = viewModelWeatherInfoNear2HourImg;
                } else {
                    ObservableField<Drawable> observableField2 = viewModelWeatherInfoNear2HourImg;
                }
            }
            if ((dirtyFlags & 34359738496L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourTemp = viewModelWeatherInfo.near5HourTemp;
                } else {
                    viewModelWeatherInfoNear5HourTemp = null;
                }
                updateRegistration(7, (Observable) viewModelWeatherInfoNear5HourTemp);
                if (viewModelWeatherInfoNear5HourTemp != null) {
                    viewModelWeatherInfoNear5HourTempGet3 = viewModelWeatherInfoNear5HourTemp.get();
                    ObservableField<String> observableField3 = viewModelWeatherInfoNear5HourTemp;
                } else {
                    ObservableField<String> observableField4 = viewModelWeatherInfoNear5HourTemp;
                }
            }
            if ((34359738624L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear4HourImg = viewModelWeatherInfo.near4HourImg;
                } else {
                    viewModelWeatherInfoNear4HourImg = null;
                }
                updateRegistration(8, (Observable) viewModelWeatherInfoNear4HourImg);
                if (viewModelWeatherInfoNear4HourImg != null) {
                    viewModelWeatherInfoNear4HourImgGet3 = viewModelWeatherInfoNear4HourImg.get();
                    ObservableField<Drawable> observableField5 = viewModelWeatherInfoNear4HourImg;
                } else {
                    ObservableField<Drawable> observableField6 = viewModelWeatherInfoNear4HourImg;
                }
            }
            if ((34359738880L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoErrorMessage = viewModelWeatherInfo.errorMessage;
                } else {
                    viewModelWeatherInfoErrorMessage = null;
                }
                updateRegistration(9, (Observable) viewModelWeatherInfoErrorMessage);
                if (viewModelWeatherInfoErrorMessage != null) {
                    viewModelWeatherInfoErrorMessageGet4 = viewModelWeatherInfoErrorMessage.get();
                    ObservableField<String> observableField7 = viewModelWeatherInfoErrorMessage;
                } else {
                    ObservableField<String> observableField8 = viewModelWeatherInfoErrorMessage;
                }
            }
            if ((34359739392L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoTemperature = viewModelWeatherInfo.temperature;
                } else {
                    viewModelWeatherInfoTemperature = null;
                }
                updateRegistration(10, (Observable) viewModelWeatherInfoTemperature);
                if (viewModelWeatherInfoTemperature != null) {
                    viewModelWeatherInfoTemperatureGet3 = viewModelWeatherInfoTemperature.get();
                    ObservableField<String> observableField9 = viewModelWeatherInfoTemperature;
                } else {
                    ObservableField<String> observableField10 = viewModelWeatherInfoTemperature;
                }
            }
            if ((34359740416L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear1HourImg = viewModelWeatherInfo.near1HourImg;
                } else {
                    viewModelWeatherInfoNear1HourImg = null;
                }
                updateRegistration(11, (Observable) viewModelWeatherInfoNear1HourImg);
                if (viewModelWeatherInfoNear1HourImg != null) {
                    viewModelWeatherInfoNear1HourImgGet4 = viewModelWeatherInfoNear1HourImg.get();
                    ObservableField<Drawable> observableField11 = viewModelWeatherInfoNear1HourImg;
                } else {
                    ObservableField<Drawable> observableField12 = viewModelWeatherInfoNear1HourImg;
                }
            }
            if ((34359742464L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear3HourClock = viewModelWeatherInfo.near3HourClock;
                } else {
                    viewModelWeatherInfoNear3HourClock = null;
                }
                updateRegistration(12, (Observable) viewModelWeatherInfoNear3HourClock);
                if (viewModelWeatherInfoNear3HourClock != null) {
                    viewModelWeatherInfoNear3HourClockGet3 = viewModelWeatherInfoNear3HourClock.get();
                    ObservableField<String> observableField13 = viewModelWeatherInfoNear3HourClock;
                } else {
                    ObservableField<String> observableField14 = viewModelWeatherInfoNear3HourClock;
                }
            }
            if ((34359746560L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear1HourClockUnit = viewModelWeatherInfo.near1HourClockUnit;
                } else {
                    viewModelWeatherInfoNear1HourClockUnit = null;
                }
                updateRegistration(13, (Observable) viewModelWeatherInfoNear1HourClockUnit);
                if (viewModelWeatherInfoNear1HourClockUnit != null) {
                    viewModelWeatherInfoNear1HourClockUnitGet4 = viewModelWeatherInfoNear1HourClockUnit.get();
                    ObservableField<String> observableField15 = viewModelWeatherInfoNear1HourClockUnit;
                } else {
                    ObservableField<String> observableField16 = viewModelWeatherInfoNear1HourClockUnit;
                }
            }
            if ((34359754752L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear3HourClockUnit = viewModelWeatherInfo.near3HourClockUnit;
                } else {
                    viewModelWeatherInfoNear3HourClockUnit = null;
                }
                updateRegistration(14, (Observable) viewModelWeatherInfoNear3HourClockUnit);
                if (viewModelWeatherInfoNear3HourClockUnit != null) {
                    viewModelWeatherInfoNear3HourClockUnitGet3 = viewModelWeatherInfoNear3HourClockUnit.get();
                    ObservableField<String> observableField17 = viewModelWeatherInfoNear3HourClockUnit;
                } else {
                    ObservableField<String> observableField18 = viewModelWeatherInfoNear3HourClockUnit;
                }
            }
            if ((34359771136L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoId8CardDetailImg3 = viewModelWeatherInfo.id8CardDetailImg3;
                } else {
                    viewModelWeatherInfoId8CardDetailImg3 = null;
                }
                updateRegistration(15, (Observable) viewModelWeatherInfoId8CardDetailImg3);
                if (viewModelWeatherInfoId8CardDetailImg3 != null) {
                    viewModelWeatherInfoId8CardDetailImg3Get2 = viewModelWeatherInfoId8CardDetailImg3.get();
                    ObservableField<Drawable> observableField19 = viewModelWeatherInfoId8CardDetailImg3;
                } else {
                    ObservableField<Drawable> observableField20 = viewModelWeatherInfoId8CardDetailImg3;
                }
            }
            if ((dirtyFlags & 34359803904L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoId8CardDetailImg1 = viewModelWeatherInfo.id8CardDetailImg1;
                } else {
                    viewModelWeatherInfoId8CardDetailImg1 = null;
                }
                updateRegistration(16, (Observable) viewModelWeatherInfoId8CardDetailImg1);
                if (viewModelWeatherInfoId8CardDetailImg1 != null) {
                    viewModelWeatherInfoId8CardDetailImg1Get2 = viewModelWeatherInfoId8CardDetailImg1.get();
                    ObservableField<Drawable> observableField21 = viewModelWeatherInfoId8CardDetailImg1;
                } else {
                    ObservableField<Drawable> observableField22 = viewModelWeatherInfoId8CardDetailImg1;
                }
            }
            if ((34359869440L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourImg = viewModelWeatherInfo.near5HourImg;
                } else {
                    viewModelWeatherInfoNear5HourImg = null;
                }
                updateRegistration(17, (Observable) viewModelWeatherInfoNear5HourImg);
                if (viewModelWeatherInfoNear5HourImg != null) {
                    viewModelWeatherInfoNear5HourImgGet3 = viewModelWeatherInfoNear5HourImg.get();
                    ObservableField<Drawable> observableField23 = viewModelWeatherInfoNear5HourImg;
                } else {
                    ObservableField<Drawable> observableField24 = viewModelWeatherInfoNear5HourImg;
                }
            }
            if ((34360000512L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear4HourClock = viewModelWeatherInfo.near4HourClock;
                } else {
                    viewModelWeatherInfoNear4HourClock = null;
                }
                updateRegistration(18, (Observable) viewModelWeatherInfoNear4HourClock);
                if (viewModelWeatherInfoNear4HourClock != null) {
                    viewModelWeatherInfoNear4HourClockGet2 = viewModelWeatherInfoNear4HourClock.get();
                    ObservableField<String> observableField25 = viewModelWeatherInfoNear4HourClock;
                } else {
                    ObservableField<String> observableField26 = viewModelWeatherInfoNear4HourClock;
                }
            }
            if ((34360262656L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear2HourClockUnit = viewModelWeatherInfo.near2HourClockUnit;
                } else {
                    viewModelWeatherInfoNear2HourClockUnit = null;
                }
                updateRegistration(19, (Observable) viewModelWeatherInfoNear2HourClockUnit);
                if (viewModelWeatherInfoNear2HourClockUnit != null) {
                    viewModelWeatherInfoNear2HourClockUnitGet3 = viewModelWeatherInfoNear2HourClockUnit.get();
                    ObservableField<String> observableField27 = viewModelWeatherInfoNear2HourClockUnit;
                } else {
                    ObservableField<String> observableField28 = viewModelWeatherInfoNear2HourClockUnit;
                }
            }
            if ((34360786944L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear4HourTemp = viewModelWeatherInfo.near4HourTemp;
                } else {
                    viewModelWeatherInfoNear4HourTemp = null;
                }
                updateRegistration(20, (Observable) viewModelWeatherInfoNear4HourTemp);
                if (viewModelWeatherInfoNear4HourTemp != null) {
                    viewModelWeatherInfoNear4HourTempGet5 = viewModelWeatherInfoNear4HourTemp.get();
                    ObservableField<String> observableField29 = viewModelWeatherInfoNear4HourTemp;
                } else {
                    ObservableField<String> observableField30 = viewModelWeatherInfoNear4HourTemp;
                }
            }
            if ((34361835520L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear3HourTemp = viewModelWeatherInfo.near3HourTemp;
                } else {
                    viewModelWeatherInfoNear3HourTemp = null;
                }
                updateRegistration(21, (Observable) viewModelWeatherInfoNear3HourTemp);
                if (viewModelWeatherInfoNear3HourTemp != null) {
                    viewModelWeatherInfoNear3HourTempGet3 = viewModelWeatherInfoNear3HourTemp.get();
                    ObservableField<String> observableField31 = viewModelWeatherInfoNear3HourTemp;
                } else {
                    ObservableField<String> observableField32 = viewModelWeatherInfoNear3HourTemp;
                }
            }
            if ((34363932672L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoCardDetailText3 = viewModelWeatherInfo.cardDetailText3;
                } else {
                    viewModelWeatherInfoCardDetailText3 = null;
                }
                updateRegistration(22, (Observable) viewModelWeatherInfoCardDetailText3);
                if (viewModelWeatherInfoCardDetailText3 != null) {
                    viewModelWeatherInfoCardDetailText3Get2 = viewModelWeatherInfoCardDetailText3.get();
                    ObservableField<String> observableField33 = viewModelWeatherInfoCardDetailText3;
                } else {
                    ObservableField<String> observableField34 = viewModelWeatherInfoCardDetailText3;
                }
            }
            if ((34368126976L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear3HourImg = viewModelWeatherInfo.near3HourImg;
                } else {
                    viewModelWeatherInfoNear3HourImg = null;
                }
                updateRegistration(23, (Observable) viewModelWeatherInfoNear3HourImg);
                if (viewModelWeatherInfoNear3HourImg != null) {
                    viewModelWeatherInfoNear3HourImgGet3 = viewModelWeatherInfoNear3HourImg.get();
                    ObservableField<Drawable> observableField35 = viewModelWeatherInfoNear3HourImg;
                } else {
                    ObservableField<Drawable> observableField36 = viewModelWeatherInfoNear3HourImg;
                }
            }
            if ((dirtyFlags & 34376515584L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoCardDetailText1 = viewModelWeatherInfo.cardDetailText1;
                } else {
                    viewModelWeatherInfoCardDetailText1 = null;
                }
                updateRegistration(24, (Observable) viewModelWeatherInfoCardDetailText1);
                if (viewModelWeatherInfoCardDetailText1 != null) {
                    viewModelWeatherInfoCardDetailText1Get = viewModelWeatherInfoCardDetailText1.get();
                    ObservableField<String> observableField37 = viewModelWeatherInfoCardDetailText1;
                } else {
                    ObservableField<String> observableField38 = viewModelWeatherInfoCardDetailText1;
                }
            }
            if ((dirtyFlags & 34661728256L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoIsInitFinished = viewModelWeatherInfo.isInitFinished;
                } else {
                    viewModelWeatherInfoIsInitFinished = null;
                }
                updateRegistration(25, (Observable) viewModelWeatherInfoIsInitFinished);
                if (viewModelWeatherInfoIsInitFinished != null) {
                    viewModelWeatherInfoIsInitFinishedGet = viewModelWeatherInfoIsInitFinished.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 34393292800L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet) {
                        dirtyFlags |= 549755813888L;
                    } else {
                        dirtyFlags |= 274877906944L;
                    }
                }
                if ((dirtyFlags & 34661728256L) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet) {
                        dirtyFlags = dirtyFlags | 8796093022208L | 35184372088832L;
                    } else {
                        dirtyFlags = dirtyFlags | 4398046511104L | 17592186044416L;
                    }
                }
                if ((dirtyFlags & 34393292800L) != 0) {
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet ? 4 : 0;
                    ObservableField<Boolean> observableField39 = viewModelWeatherInfoIsInitFinished;
                } else {
                    ObservableField<Boolean> observableField40 = viewModelWeatherInfoIsInitFinished;
                }
            }
            if ((34426847232L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear4HourClockUnit = viewModelWeatherInfo.near4HourClockUnit;
                } else {
                    viewModelWeatherInfoNear4HourClockUnit = null;
                }
                updateRegistration(26, (Observable) viewModelWeatherInfoNear4HourClockUnit);
                if (viewModelWeatherInfoNear4HourClockUnit != null) {
                    viewModelWeatherInfoNear4HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockUnit.get();
                    ObservableField<String> observableField41 = viewModelWeatherInfoNear4HourClockUnit;
                } else {
                    ObservableField<String> observableField42 = viewModelWeatherInfoNear4HourClockUnit;
                }
            }
            if ((34493956096L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear2HourTemp = viewModelWeatherInfo.near2HourTemp;
                } else {
                    viewModelWeatherInfoNear2HourTemp = null;
                }
                updateRegistration(27, (Observable) viewModelWeatherInfoNear2HourTemp);
                if (viewModelWeatherInfoNear2HourTemp != null) {
                    viewModelWeatherInfoNear2HourTempGet2 = viewModelWeatherInfoNear2HourTemp.get();
                    ObservableField<String> observableField43 = viewModelWeatherInfoNear2HourTemp;
                } else {
                    ObservableField<String> observableField44 = viewModelWeatherInfoNear2HourTemp;
                }
            }
            if ((dirtyFlags & 43218108416L) != 0) {
                if (viewModelWeatherInfo != null) {
                    ViewModelWeatherInfoIsLoadSuccess12 = viewModelWeatherInfo.isLoadSuccess;
                } else {
                    ViewModelWeatherInfoIsLoadSuccess12 = null;
                }
                updateRegistration(28, (Observable) ViewModelWeatherInfoIsLoadSuccess12);
                if (ViewModelWeatherInfoIsLoadSuccess12 != null) {
                    viewModelWeatherInfoIsLoadSuccessGet4 = ViewModelWeatherInfoIsLoadSuccess12.get();
                }
                androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsLoadSuccessGet4);
                if ((dirtyFlags & 43218108416L) == 0) {
                    ViewModelWeatherInfoIsLoadSuccess13 = ViewModelWeatherInfoIsLoadSuccess12;
                } else if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 137438953472L;
                    ViewModelWeatherInfoIsLoadSuccess13 = ViewModelWeatherInfoIsLoadSuccess12;
                } else {
                    dirtyFlags |= 68719476736L;
                    ViewModelWeatherInfoIsLoadSuccess13 = ViewModelWeatherInfoIsLoadSuccess12;
                }
            }
            if ((dirtyFlags & 34896609280L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoId8ImageIcon = viewModelWeatherInfo.id8ImageIcon;
                } else {
                    viewModelWeatherInfoId8ImageIcon = null;
                }
                updateRegistration(29, (Observable) viewModelWeatherInfoId8ImageIcon);
                if (viewModelWeatherInfoId8ImageIcon != null) {
                    viewModelWeatherInfoId8ImageIconGet2 = viewModelWeatherInfoId8ImageIcon.get();
                    ObservableField<Drawable> observableField45 = viewModelWeatherInfoId8ImageIcon;
                } else {
                    ObservableField<Drawable> observableField46 = viewModelWeatherInfoId8ImageIcon;
                }
            }
            if ((35433480192L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoId8CardDetailImg2 = viewModelWeatherInfo.id8CardDetailImg2;
                } else {
                    viewModelWeatherInfoId8CardDetailImg2 = null;
                }
                updateRegistration(30, (Observable) viewModelWeatherInfoId8CardDetailImg2);
                if (viewModelWeatherInfoId8CardDetailImg2 != null) {
                    viewModelWeatherInfoId8CardDetailImg2Get2 = viewModelWeatherInfoId8CardDetailImg2.get();
                    ObservableField<Drawable> observableField47 = viewModelWeatherInfoId8CardDetailImg2;
                } else {
                    ObservableField<Drawable> observableField48 = viewModelWeatherInfoId8CardDetailImg2;
                }
            }
            if ((36507222016L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear1HourClock = viewModelWeatherInfo.near1HourClock;
                } else {
                    viewModelWeatherInfoNear1HourClock = null;
                }
                updateRegistration(31, (Observable) viewModelWeatherInfoNear1HourClock);
                if (viewModelWeatherInfoNear1HourClock != null) {
                    viewModelWeatherInfoNear1HourClockGet2 = viewModelWeatherInfoNear1HourClock.get();
                    ObservableField<String> observableField49 = viewModelWeatherInfoNear1HourClock;
                } else {
                    ObservableField<String> observableField50 = viewModelWeatherInfoNear1HourClock;
                }
            }
            if ((38654705664L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoTemperUnit = viewModelWeatherInfo.temperUnit;
                } else {
                    viewModelWeatherInfoTemperUnit = null;
                }
                updateRegistration(32, (Observable) viewModelWeatherInfoTemperUnit);
                if (viewModelWeatherInfoTemperUnit != null) {
                    ObservableField<String> observableField51 = viewModelWeatherInfoTemperUnit;
                    viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                    viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                    viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                    viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear3HourClockUnitGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear2HourClockGet4;
                    viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                    viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet4;
                    viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                    viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                    ObservableField<String> observableField52 = viewModelWeatherInfoTemperatureRange;
                    viewModelWeatherInfoNear2HourClockGet = viewModelWeatherInfoTemperUnit.get();
                    viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet2;
                    viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet3;
                    viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet3;
                    viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                    ObservableField<String> observableField53 = viewModelWeatherInfoCardDetailText2;
                    ObservableField<String> observableField54 = viewModelWeatherInfoNear1HourTemp;
                    viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                    viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet2;
                    viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                    viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                    ObservableField<String> observableField55 = viewModelWeatherInfoNear5HourClockUnit;
                    ObservableField<String> observableField56 = viewModelWeatherInfoNear5HourClock;
                    viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                    viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                    viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear1HourTempGet6;
                    viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear5HourClockGet;
                    viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                    viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                    viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                    ObservableField<String> observableField57 = viewModelWeatherInfoNear2HourClock;
                    viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
                } else {
                    ObservableField<String> observableField58 = viewModelWeatherInfoTemperUnit;
                    viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                    viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                    viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                    viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear3HourClockUnitGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear2HourClockGet4;
                    viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                    viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet4;
                    viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                    viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                    ObservableField<String> observableField59 = viewModelWeatherInfoTemperatureRange;
                    viewModelWeatherInfoNear2HourClockGet = null;
                    viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet2;
                    viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet3;
                    viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet3;
                    viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                    ObservableField<String> observableField60 = viewModelWeatherInfoCardDetailText2;
                    ObservableField<String> observableField61 = viewModelWeatherInfoNear1HourTemp;
                    viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                    viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet2;
                    viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                    viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                    ObservableField<String> observableField62 = viewModelWeatherInfoNear5HourClockUnit;
                    ObservableField<String> observableField63 = viewModelWeatherInfoNear5HourClock;
                    viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                    viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                    viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear1HourTempGet6;
                    viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear5HourClockGet;
                    viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                    viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                    viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                    ObservableField<String> observableField64 = viewModelWeatherInfoNear2HourClock;
                    viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
                }
            } else {
                viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear3HourClockUnitGet3;
                viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear2HourClockGet4;
                viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet3;
                viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet4;
                viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                ObservableField<String> observableField65 = viewModelWeatherInfoTemperatureRange;
                viewModelWeatherInfoNear2HourClockGet = null;
                viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet2;
                viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet3;
                viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet3;
                viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                ObservableField<String> observableField66 = viewModelWeatherInfoCardDetailText2;
                ObservableField<String> observableField67 = viewModelWeatherInfoNear1HourTemp;
                viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet2;
                viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                ObservableField<String> observableField68 = viewModelWeatherInfoNear5HourClockUnit;
                ObservableField<String> observableField69 = viewModelWeatherInfoNear5HourClock;
                viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear1HourTempGet6;
                viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear5HourClockGet;
                viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                ObservableField<String> observableField70 = viewModelWeatherInfoNear2HourClock;
                viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
            }
        } else {
            viewModelWeatherInfoId8ImageIconGet = null;
            viewModelWeatherInfoTemperatureGet = null;
            viewModelWeatherInfoNear5HourImgGet = null;
            viewModelWeatherInfoNear4HourTempGet = null;
            viewModelWeatherInfoNear5HourTempGet = null;
            viewModelWeatherInfoNear3HourClockUnitGet2 = null;
            viewModelWeatherInfoNear3HourClockUnitGet = null;
            viewModelWeatherInfoId8CardDetailImg1Get = null;
            viewModelWeatherInfoTemperatureRangeGet = null;
            viewModelWeatherInfoErrorMessageGet = null;
            viewModelWeatherInfoNear3HourImgGet = null;
            viewModelWeatherInfoNear2HourImgGet = null;
            viewModelWeatherInfoNear2HourClockGet = null;
            viewModelWeatherInfoNear5HourTempGet2 = null;
            viewModelWeatherInfoTemperatureGet2 = null;
            viewModelWeatherInfoNear4HourTempGet2 = null;
            viewModelWeatherInfoNear1HourClockGet = null;
            viewModelWeatherInfoNear4HourImgGet = null;
            viewModelWeatherInfoNear3HourTempGet = null;
            viewModelWeatherInfoCardDetailText2Get = null;
            viewModelWeatherInfoCardDetailText3Get = null;
            viewModelWeatherInfoId8CardDetailImg2Get = null;
            viewModelWeatherInfoNear1HourImgGet = null;
            viewModelWeatherInfoIsLoadSuccessGet = null;
            viewModelWeatherInfoNear1HourTempGet2 = null;
            viewModelWeatherInfoNear1HourTempGet = null;
            viewModelWeatherInfoId8CardDetailImg3Get = null;
            viewModelWeatherInfoNear4HourClockUnitGet = null;
            viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            viewModelWeatherInfoNear2HourClockUnitGet = null;
            viewModelWeatherInfoNear2HourClockUnitGet2 = null;
            viewModelWeatherInfoNear1HourClockUnitGet = null;
        }
        if ((dirtyFlags & 43980465111040L) != 0) {
            if (viewModelWeatherInfo != null) {
                viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet2;
                ViewModelWeatherInfoIsLoadSuccess1 = viewModelWeatherInfo.isLoadSuccess;
            } else {
                viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet2;
                ViewModelWeatherInfoIsLoadSuccess1 = ViewModelWeatherInfoIsLoadSuccess13;
            }
            viewModelWeatherInfoNear1HourImgGet2 = viewModelWeatherInfoNear1HourImgGet;
            updateRegistration(28, (Observable) ViewModelWeatherInfoIsLoadSuccess1);
            if (ViewModelWeatherInfoIsLoadSuccess1 != null) {
                viewModelWeatherInfoIsLoadSuccessGet3 = ViewModelWeatherInfoIsLoadSuccess1.get();
            } else {
                viewModelWeatherInfoIsLoadSuccessGet3 = viewModelWeatherInfoIsLoadSuccessGet;
            }
            androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(viewModelWeatherInfoIsLoadSuccessGet3);
            if ((dirtyFlags & 43218108416L) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 137438953472L;
                } else {
                    dirtyFlags |= 68719476736L;
                }
            }
            if ((dirtyFlags & 35184372088832L) != 0) {
                viewModelWeatherInfoIsLoadSuccess = !androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet;
                ObservableField<Boolean> observableField71 = ViewModelWeatherInfoIsLoadSuccess1;
                viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet3;
            } else {
                ObservableField<Boolean> observableField72 = ViewModelWeatherInfoIsLoadSuccess1;
                viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet3;
            }
        } else {
            viewModelWeatherInfoNear1HourImgGet2 = viewModelWeatherInfoNear1HourImgGet;
            viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet2;
            viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet;
        }
        if ((dirtyFlags & 34661728256L) != 0) {
            boolean viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet : false;
            boolean ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet ? viewModelWeatherInfoIsLoadSuccess : false;
            if ((dirtyFlags & 34661728256L) != 0) {
                if (viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags |= 140737488355328L;
                } else {
                    dirtyFlags |= 70368744177664L;
                }
            }
            if ((dirtyFlags & 34661728256L) != 0) {
                if (ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags |= 2199023255552L;
                } else {
                    dirtyFlags |= 1099511627776L;
                }
            }
            int ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            Boolean bool = viewModelWeatherInfoIsLoadSuccessGet2;
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        } else {
            Boolean bool2 = viewModelWeatherInfoIsLoadSuccessGet2;
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
        }
        if ((dirtyFlags & 137438953472L) != 0) {
            if (viewModelWeatherInfo != null) {
                viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
                viewModelWeatherInfoCity = viewModelWeatherInfo.city;
            } else {
                viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
                viewModelWeatherInfoCity = null;
            }
            WeatherInfo weatherInfo = viewModelWeatherInfo;
            updateRegistration(33, (Observable) viewModelWeatherInfoCity);
            if (viewModelWeatherInfoCity != null) {
                viewModelWeatherInfoCityGet = viewModelWeatherInfoCity.get();
                ObservableField<String> observableField73 = viewModelWeatherInfoCity;
            } else {
                ObservableField<String> observableField74 = viewModelWeatherInfoCity;
            }
        } else {
            viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
            WeatherInfo weatherInfo2 = viewModelWeatherInfo;
        }
        if ((dirtyFlags & 43218108416L) != 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet ? viewModelWeatherInfoCityGet : this.mboundView4.getResources().getString(R.string.ksw_id8_weather);
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather = null;
        }
        if ((dirtyFlags & 34896609280L) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, viewModelWeatherInfoId8ImageIconGet);
        }
        if ((dirtyFlags & 34661728256L) != 0) {
            this.ivIcon.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView10.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView11.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView12.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView13.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView2.setVisibility(viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE);
            this.mboundView4.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView8.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.mboundView9.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.temperatureTv.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
            this.unitWeather.setVisibility(ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1);
        }
        if ((dirtyFlags & 34393292800L) != 0) {
            this.mboundView1.setVisibility(viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 34359803904L) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.mboundView10, viewModelWeatherInfoId8CardDetailImg1Get);
        }
        if ((dirtyFlags & 34376515584L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, viewModelWeatherInfoCardDetailText1Get);
        }
        if ((dirtyFlags & 35433480192L) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.mboundView11, viewModelWeatherInfoId8CardDetailImg2Get);
        }
        if ((dirtyFlags & 34359738372L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelWeatherInfoCardDetailText2Get);
        }
        if ((dirtyFlags & 34359771136L) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.mboundView12, viewModelWeatherInfoId8CardDetailImg3Get);
        }
        if ((dirtyFlags & 34363932672L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView12, viewModelWeatherInfoCardDetailText3Get);
        }
        if ((dirtyFlags & 36507222016L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, viewModelWeatherInfoNear1HourClockGet);
        }
        if ((dirtyFlags & 34359746560L) != 0) {
            Drawable drawable = viewModelWeatherInfoId8CardDetailImg1Get;
            viewModelWeatherInfoNear1HourClockUnitGet3 = viewModelWeatherInfoNear1HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView15, viewModelWeatherInfoNear1HourClockUnitGet3);
        } else {
            viewModelWeatherInfoNear1HourClockUnitGet3 = viewModelWeatherInfoNear1HourClockUnitGet2;
        }
        if ((dirtyFlags & 34359740416L) != 0) {
            String str = viewModelWeatherInfoNear1HourClockUnitGet3;
            viewModelWeatherInfoNear1HourImgGet3 = viewModelWeatherInfoNear1HourImgGet2;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView16, viewModelWeatherInfoNear1HourImgGet3);
        } else {
            viewModelWeatherInfoNear1HourImgGet3 = viewModelWeatherInfoNear1HourImgGet2;
        }
        if ((dirtyFlags & 34359738400L) != 0) {
            Drawable drawable2 = viewModelWeatherInfoNear1HourImgGet3;
            viewModelWeatherInfoNear1HourTempGet4 = viewModelWeatherInfoNear1HourTempGet3;
            TextViewBindingAdapter.setText(this.mboundView17, viewModelWeatherInfoNear1HourTempGet4);
        } else {
            viewModelWeatherInfoNear1HourTempGet4 = viewModelWeatherInfoNear1HourTempGet3;
        }
        if ((dirtyFlags & 38654705664L) != 0) {
            String str2 = viewModelWeatherInfoNear1HourTempGet4;
            viewModelWeatherInfoNear1HourTempGet5 = viewModelWeatherInfoNear2HourClockGet;
            TextViewBindingAdapter.setText(this.mboundView18, viewModelWeatherInfoNear1HourTempGet5);
            TextViewBindingAdapter.setText(this.mboundView23, viewModelWeatherInfoNear1HourTempGet5);
            TextViewBindingAdapter.setText(this.mboundView28, viewModelWeatherInfoNear1HourTempGet5);
            TextViewBindingAdapter.setText(this.mboundView33, viewModelWeatherInfoNear1HourTempGet5);
            TextViewBindingAdapter.setText(this.mboundView38, viewModelWeatherInfoNear1HourTempGet5);
            TextViewBindingAdapter.setText(this.unitWeather, viewModelWeatherInfoNear1HourTempGet5);
        } else {
            viewModelWeatherInfoNear1HourTempGet5 = viewModelWeatherInfoNear2HourClockGet;
        }
        if ((dirtyFlags & 34359738369L) != 0) {
            String str3 = viewModelWeatherInfoNear1HourTempGet5;
            viewModelWeatherInfoNear2HourClockGet2 = viewModelWeatherInfoNear3HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView19, viewModelWeatherInfoNear2HourClockGet2);
        } else {
            String viewModelWeatherInfoTemperUnitGet = viewModelWeatherInfoNear1HourTempGet5;
            viewModelWeatherInfoNear2HourClockGet2 = viewModelWeatherInfoNear3HourClockUnitGet;
        }
        if ((dirtyFlags & 34360262656L) != 0) {
            String str4 = viewModelWeatherInfoNear2HourClockGet2;
            viewModelWeatherInfoNear2HourClockGet3 = viewModelWeatherInfoNear2HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView20, viewModelWeatherInfoNear2HourClockGet3);
        } else {
            viewModelWeatherInfoNear2HourClockGet3 = viewModelWeatherInfoNear2HourClockUnitGet;
        }
        if ((dirtyFlags & 34359738432L) != 0) {
            String str5 = viewModelWeatherInfoNear2HourClockGet3;
            viewModelWeatherInfoNear2HourImgGet2 = viewModelWeatherInfoNear2HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView21, viewModelWeatherInfoNear2HourImgGet2);
        } else {
            String viewModelWeatherInfoNear2HourClockUnitGet4 = viewModelWeatherInfoNear2HourClockGet3;
            viewModelWeatherInfoNear2HourImgGet2 = viewModelWeatherInfoNear2HourImgGet;
        }
        if ((dirtyFlags & 34493956096L) != 0) {
            Drawable drawable3 = viewModelWeatherInfoNear2HourImgGet2;
            viewModelWeatherInfoNear2HourTempGet = viewModelWeatherInfoNear5HourTempGet2;
            TextViewBindingAdapter.setText(this.mboundView22, viewModelWeatherInfoNear2HourTempGet);
        } else {
            viewModelWeatherInfoNear2HourTempGet = viewModelWeatherInfoNear5HourTempGet2;
        }
        if ((dirtyFlags & 34359742464L) != 0) {
            String str6 = viewModelWeatherInfoNear2HourTempGet;
            viewModelWeatherInfoNear3HourClockGet = viewModelWeatherInfoNear4HourTempGet2;
            TextViewBindingAdapter.setText(this.mboundView24, viewModelWeatherInfoNear3HourClockGet);
        } else {
            viewModelWeatherInfoNear3HourClockGet = viewModelWeatherInfoNear4HourTempGet2;
        }
        if ((dirtyFlags & 34359754752L) != 0) {
            String str7 = viewModelWeatherInfoNear3HourClockGet;
            viewModelWeatherInfoNear3HourClockGet2 = viewModelWeatherInfoNear3HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView25, viewModelWeatherInfoNear3HourClockGet2);
        } else {
            viewModelWeatherInfoNear3HourClockGet2 = viewModelWeatherInfoNear3HourClockUnitGet2;
        }
        if ((dirtyFlags & 34368126976L) != 0) {
            String str8 = viewModelWeatherInfoNear3HourClockGet2;
            viewModelWeatherInfoNear3HourImgGet2 = viewModelWeatherInfoNear3HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView26, viewModelWeatherInfoNear3HourImgGet2);
        } else {
            String viewModelWeatherInfoNear3HourClockUnitGet4 = viewModelWeatherInfoNear3HourClockGet2;
            viewModelWeatherInfoNear3HourImgGet2 = viewModelWeatherInfoNear3HourImgGet;
        }
        if ((dirtyFlags & 34361835520L) != 0) {
            Drawable drawable4 = viewModelWeatherInfoNear3HourImgGet2;
            viewModelWeatherInfoNear3HourTempGet2 = viewModelWeatherInfoTemperatureGet2;
            TextViewBindingAdapter.setText(this.mboundView27, viewModelWeatherInfoNear3HourTempGet2);
        } else {
            viewModelWeatherInfoNear3HourTempGet2 = viewModelWeatherInfoTemperatureGet2;
        }
        if ((dirtyFlags & 34360000512L) != 0) {
            String str9 = viewModelWeatherInfoNear3HourTempGet2;
            viewModelWeatherInfoNear4HourClockGet = viewModelWeatherInfoNear2HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView29, viewModelWeatherInfoNear4HourClockGet);
        } else {
            viewModelWeatherInfoNear4HourClockGet = viewModelWeatherInfoNear2HourClockUnitGet2;
        }
        if ((dirtyFlags & 34359738880L) != 0) {
            String str10 = viewModelWeatherInfoNear4HourClockGet;
            viewModelWeatherInfoErrorMessageGet2 = viewModelWeatherInfoErrorMessageGet;
            TextViewBindingAdapter.setText(this.mboundView3, viewModelWeatherInfoErrorMessageGet2);
        } else {
            viewModelWeatherInfoErrorMessageGet2 = viewModelWeatherInfoErrorMessageGet;
        }
        if ((dirtyFlags & 34426847232L) != 0) {
            String str11 = viewModelWeatherInfoErrorMessageGet2;
            viewModelWeatherInfoErrorMessageGet3 = viewModelWeatherInfoNear4HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView30, viewModelWeatherInfoErrorMessageGet3);
        } else {
            viewModelWeatherInfoErrorMessageGet3 = viewModelWeatherInfoNear4HourClockUnitGet;
        }
        if ((dirtyFlags & 34359738624L) != 0) {
            String str12 = viewModelWeatherInfoErrorMessageGet3;
            viewModelWeatherInfoNear4HourImgGet2 = viewModelWeatherInfoNear4HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView31, viewModelWeatherInfoNear4HourImgGet2);
        } else {
            String viewModelWeatherInfoNear4HourClockUnitGet3 = viewModelWeatherInfoErrorMessageGet3;
            viewModelWeatherInfoNear4HourImgGet2 = viewModelWeatherInfoNear4HourImgGet;
        }
        if ((dirtyFlags & 34360786944L) != 0) {
            Drawable drawable5 = viewModelWeatherInfoNear4HourImgGet2;
            viewModelWeatherInfoNear4HourTempGet3 = viewModelWeatherInfoNear4HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView32, viewModelWeatherInfoNear4HourTempGet3);
        } else {
            viewModelWeatherInfoNear4HourTempGet3 = viewModelWeatherInfoNear4HourTempGet;
        }
        if ((dirtyFlags & 34359738384L) != 0) {
            String str13 = viewModelWeatherInfoNear4HourTempGet3;
            viewModelWeatherInfoNear4HourTempGet4 = viewModelWeatherInfoNear1HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView34, viewModelWeatherInfoNear4HourTempGet4);
        } else {
            viewModelWeatherInfoNear4HourTempGet4 = viewModelWeatherInfoNear1HourTempGet;
        }
        if ((dirtyFlags & 34359738376L) != 0) {
            String str14 = viewModelWeatherInfoNear4HourTempGet4;
            viewModelWeatherInfoNear5HourClockUnitGet = viewModelWeatherInfoNear3HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView35, viewModelWeatherInfoNear5HourClockUnitGet);
        } else {
            String viewModelWeatherInfoNear5HourClockGet2 = viewModelWeatherInfoNear4HourTempGet4;
            viewModelWeatherInfoNear5HourClockUnitGet = viewModelWeatherInfoNear3HourTempGet;
        }
        if ((dirtyFlags & 34359869440L) != 0) {
            String str15 = viewModelWeatherInfoNear5HourClockUnitGet;
            viewModelWeatherInfoNear5HourImgGet2 = viewModelWeatherInfoNear5HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView36, viewModelWeatherInfoNear5HourImgGet2);
        } else {
            viewModelWeatherInfoNear5HourImgGet2 = viewModelWeatherInfoNear5HourImgGet;
        }
        if ((dirtyFlags & 34359738496L) != 0) {
            Drawable drawable6 = viewModelWeatherInfoNear5HourImgGet2;
            TextViewBindingAdapter.setText(this.mboundView37, viewModelWeatherInfoNear5HourTempGet);
        } else {
            String str16 = viewModelWeatherInfoNear5HourTempGet;
        }
        if ((dirtyFlags & 43218108416L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 34359738370L) != 0) {
            String str17 = viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather;
            viewModelWeatherInfoTemperatureRangeGet2 = viewModelWeatherInfoTemperatureRangeGet;
            TextViewBindingAdapter.setText(this.mboundView8, viewModelWeatherInfoTemperatureRangeGet2);
        } else {
            viewModelWeatherInfoTemperatureRangeGet2 = viewModelWeatherInfoTemperatureRangeGet;
        }
        if ((dirtyFlags & 34359739392L) != 0) {
            String str18 = viewModelWeatherInfoTemperatureRangeGet2;
            TextViewBindingAdapter.setText(this.temperatureTv, viewModelWeatherInfoTemperatureGet);
            return;
        }
        String viewModelWeatherInfoTemperatureRangeGet4 = viewModelWeatherInfoTemperatureGet;
    }
}

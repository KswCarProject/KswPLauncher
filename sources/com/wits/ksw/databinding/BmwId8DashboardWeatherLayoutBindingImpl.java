package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
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
        super(bindingComponent, root, 34, (ImageView) bindings[5], (TextView) bindings[6], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.ivIcon.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[1];
        this.mboundView1 = progressBar;
        progressBar.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[13];
        this.mboundView13 = linearLayout;
        linearLayout.setTag(null);
        TextView textView4 = (TextView) bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[15];
        this.mboundView15 = textView5;
        textView5.setTag(null);
        ImageView imageView = (ImageView) bindings[16];
        this.mboundView16 = imageView;
        imageView.setTag(null);
        TextView textView6 = (TextView) bindings[17];
        this.mboundView17 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[18];
        this.mboundView18 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[19];
        this.mboundView19 = textView8;
        textView8.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView9 = (TextView) bindings[20];
        this.mboundView20 = textView9;
        textView9.setTag(null);
        ImageView imageView2 = (ImageView) bindings[21];
        this.mboundView21 = imageView2;
        imageView2.setTag(null);
        TextView textView10 = (TextView) bindings[22];
        this.mboundView22 = textView10;
        textView10.setTag(null);
        TextView textView11 = (TextView) bindings[23];
        this.mboundView23 = textView11;
        textView11.setTag(null);
        TextView textView12 = (TextView) bindings[24];
        this.mboundView24 = textView12;
        textView12.setTag(null);
        TextView textView13 = (TextView) bindings[25];
        this.mboundView25 = textView13;
        textView13.setTag(null);
        ImageView imageView3 = (ImageView) bindings[26];
        this.mboundView26 = imageView3;
        imageView3.setTag(null);
        TextView textView14 = (TextView) bindings[27];
        this.mboundView27 = textView14;
        textView14.setTag(null);
        TextView textView15 = (TextView) bindings[28];
        this.mboundView28 = textView15;
        textView15.setTag(null);
        TextView textView16 = (TextView) bindings[29];
        this.mboundView29 = textView16;
        textView16.setTag(null);
        TextView textView17 = (TextView) bindings[3];
        this.mboundView3 = textView17;
        textView17.setTag(null);
        TextView textView18 = (TextView) bindings[30];
        this.mboundView30 = textView18;
        textView18.setTag(null);
        ImageView imageView4 = (ImageView) bindings[31];
        this.mboundView31 = imageView4;
        imageView4.setTag(null);
        TextView textView19 = (TextView) bindings[32];
        this.mboundView32 = textView19;
        textView19.setTag(null);
        TextView textView20 = (TextView) bindings[33];
        this.mboundView33 = textView20;
        textView20.setTag(null);
        TextView textView21 = (TextView) bindings[34];
        this.mboundView34 = textView21;
        textView21.setTag(null);
        TextView textView22 = (TextView) bindings[35];
        this.mboundView35 = textView22;
        textView22.setTag(null);
        ImageView imageView5 = (ImageView) bindings[36];
        this.mboundView36 = imageView5;
        imageView5.setTag(null);
        TextView textView23 = (TextView) bindings[37];
        this.mboundView37 = textView23;
        textView23.setTag(null);
        TextView textView24 = (TextView) bindings[38];
        this.mboundView38 = textView24;
        textView24.setTag(null);
        MarqueeTextView marqueeTextView = (MarqueeTextView) bindings[4];
        this.mboundView4 = marqueeTextView;
        marqueeTextView.setTag(null);
        TextView textView25 = (TextView) bindings[8];
        this.mboundView8 = textView25;
        textView25.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[9];
        this.mboundView9 = linearLayout3;
        linearLayout3.setTag(null);
        this.temperatureTv.setTag(null);
        this.unitWeather.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 34359738368L;
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
            setViewModel((DashboardViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBinding
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeViewModelWeatherInfoNear2HourClock(ObservableField<String> ViewModelWeatherInfoNear2HourClock, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoTemperatureRange(ObservableField<String> ViewModelWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText2(ObservableField<String> ViewModelWeatherInfoCardDetailText2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourClockUnit(ObservableField<String> ViewModelWeatherInfoNear5HourClockUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourClock(ObservableField<String> ViewModelWeatherInfoNear5HourClock, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourTemp(ObservableField<String> ViewModelWeatherInfoNear1HourTemp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourImg(ObservableField<Drawable> ViewModelWeatherInfoNear2HourImg, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourTemp(ObservableField<String> ViewModelWeatherInfoNear5HourTemp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourImg(ObservableField<Drawable> ViewModelWeatherInfoNear4HourImg, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoErrorMessage(ObservableField<String> ViewModelWeatherInfoErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoTemperature(ObservableField<String> ViewModelWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourImg(ObservableField<Drawable> ViewModelWeatherInfoNear1HourImg, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourClock(ObservableField<String> ViewModelWeatherInfoNear3HourClock, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourClockUnit(ObservableField<String> ViewModelWeatherInfoNear1HourClockUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourClockUnit(ObservableField<String> ViewModelWeatherInfoNear3HourClockUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg3(ObservableField<Drawable> ViewModelWeatherInfoId8CardDetailImg3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg1(ObservableField<Drawable> ViewModelWeatherInfoId8CardDetailImg1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear5HourImg(ObservableField<Drawable> ViewModelWeatherInfoNear5HourImg, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourClock(ObservableField<String> ViewModelWeatherInfoNear4HourClock, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourClockUnit(ObservableField<String> ViewModelWeatherInfoNear2HourClockUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourTemp(ObservableField<String> ViewModelWeatherInfoNear4HourTemp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourTemp(ObservableField<String> ViewModelWeatherInfoNear3HourTemp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText3(ObservableField<String> ViewModelWeatherInfoCardDetailText3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4194304;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear3HourImg(ObservableField<Drawable> ViewModelWeatherInfoNear3HourImg, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8388608;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoCardDetailText1(ObservableField<String> ViewModelWeatherInfoCardDetailText1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16777216;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoIsInitFinished(ObservableField<Boolean> ViewModelWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 33554432;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear4HourClockUnit(ObservableField<String> ViewModelWeatherInfoNear4HourClockUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 67108864;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear2HourTemp(ObservableField<String> ViewModelWeatherInfoNear2HourTemp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 134217728;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoIsLoadSuccess(ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 268435456;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoId8ImageIcon(ObservableField<Drawable> ViewModelWeatherInfoId8ImageIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 536870912;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoId8CardDetailImg2(ObservableField<Drawable> ViewModelWeatherInfoId8CardDetailImg2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1073741824;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoNear1HourClock(ObservableField<String> ViewModelWeatherInfoNear1HourClock, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2147483648L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoTemperUnit(ObservableField<String> ViewModelWeatherInfoTemperUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4294967296L;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWeatherInfoCity(ObservableField<String> ViewModelWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8589934592L;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        Drawable viewModelWeatherInfoId8ImageIconGet;
        String viewModelWeatherInfoTemperatureGet;
        Drawable viewModelWeatherInfoNear5HourImgGet;
        String viewModelWeatherInfoNear4HourTempGet;
        String viewModelWeatherInfoNear5HourTempGet;
        String viewModelWeatherInfoNear3HourClockUnitGet;
        String viewModelWeatherInfoNear3HourClockUnitGet2;
        Drawable viewModelWeatherInfoId8CardDetailImg1Get;
        String viewModelWeatherInfoTemperatureRangeGet;
        String viewModelWeatherInfoErrorMessageGet;
        Drawable viewModelWeatherInfoNear3HourImgGet;
        Drawable viewModelWeatherInfoNear2HourImgGet;
        String viewModelWeatherInfoNear2HourClockGet;
        String viewModelWeatherInfoNear5HourTempGet2;
        String viewModelWeatherInfoTemperatureGet2;
        String viewModelWeatherInfoNear4HourTempGet2;
        String viewModelWeatherInfoNear1HourClockGet;
        Drawable viewModelWeatherInfoNear4HourImgGet;
        String viewModelWeatherInfoNear3HourTempGet;
        String viewModelWeatherInfoCardDetailText2Get;
        String viewModelWeatherInfoCardDetailText3Get;
        Drawable viewModelWeatherInfoId8CardDetailImg2Get;
        Drawable viewModelWeatherInfoNear1HourImgGet;
        Boolean viewModelWeatherInfoIsLoadSuccessGet;
        String viewModelWeatherInfoNear1HourTempGet;
        String viewModelWeatherInfoNear1HourTempGet2;
        Drawable viewModelWeatherInfoId8CardDetailImg3Get;
        String viewModelWeatherInfoNear4HourClockUnitGet;
        int viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE;
        String viewModelWeatherInfoNear2HourClockUnitGet;
        String viewModelWeatherInfoNear2HourClockUnitGet2;
        String viewModelWeatherInfoNear1HourClockUnitGet;
        Drawable viewModelWeatherInfoNear1HourImgGet2;
        String viewModelWeatherInfoNear1HourTempGet3;
        Boolean viewModelWeatherInfoIsLoadSuccessGet2;
        int viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        int ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        String viewModelWeatherInfoNear1HourClockUnitGet2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather;
        String viewModelWeatherInfoNear1HourClockUnitGet3;
        Drawable viewModelWeatherInfoNear1HourImgGet3;
        String viewModelWeatherInfoNear1HourTempGet4;
        String viewModelWeatherInfoNear1HourTempGet5;
        String viewModelWeatherInfoTemperUnitGet;
        String viewModelWeatherInfoNear2HourClockGet2;
        Drawable viewModelWeatherInfoNear2HourImgGet2;
        String viewModelWeatherInfoNear2HourTempGet;
        String viewModelWeatherInfoNear2HourTempGet2;
        String viewModelWeatherInfoNear3HourClockGet;
        Drawable viewModelWeatherInfoNear3HourImgGet2;
        String viewModelWeatherInfoNear3HourTempGet2;
        String viewModelWeatherInfoNear3HourTempGet3;
        String viewModelWeatherInfoNear4HourClockGet;
        String viewModelWeatherInfoErrorMessageGet2;
        Drawable viewModelWeatherInfoNear4HourImgGet2;
        String viewModelWeatherInfoNear4HourTempGet3;
        String viewModelWeatherInfoNear4HourTempGet4;
        String viewModelWeatherInfoNear5HourClockGet;
        Drawable viewModelWeatherInfoNear5HourImgGet2;
        String viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather2;
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
            this.mDirtyFlags = 0L;
        }
        ObservableField<String> viewModelWeatherInfoNear2HourClock = null;
        ObservableField<String> viewModelWeatherInfoTemperatureRange = null;
        String viewModelWeatherInfoNear5HourClockGet2 = null;
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
        String viewModelWeatherInfoNear5HourClockUnitGet = null;
        String viewModelWeatherInfoNear2HourTempGet3 = null;
        Drawable viewModelWeatherInfoId8CardDetailImg2Get2 = null;
        String viewModelWeatherInfoNear3HourTempGet4 = null;
        String viewModelWeatherInfoNear1HourTempGet6 = null;
        String viewModelWeatherInfoNear3HourClockGet2 = null;
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
        String viewModelWeatherInfoNear2HourClockGet3 = null;
        Drawable viewModelWeatherInfoId8CardDetailImg1Get2 = null;
        String viewModelWeatherInfoTemperatureRangeGet2 = null;
        String viewModelWeatherInfoCardDetailText2Get2 = null;
        Drawable viewModelWeatherInfoNear1HourImgGet4 = null;
        String viewModelWeatherInfoErrorMessageGet3 = null;
        String viewModelWeatherInfoNear1HourClockGet2 = null;
        String viewModelWeatherInfoCardDetailText3Get2 = null;
        Drawable viewModelWeatherInfoNear3HourImgGet3 = null;
        String viewModelWeatherInfoNear1HourClockUnitGet4 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsInitFinishedGet = false;
        ObservableField<Boolean> ViewModelWeatherInfoIsLoadSuccess13 = null;
        Drawable viewModelWeatherInfoNear2HourImgGet3 = null;
        Boolean viewModelWeatherInfoIsInitFinishedGet = null;
        if ((dirtyFlags & 51539607551L) == 0) {
            viewModelWeatherInfoId8ImageIconGet = null;
            viewModelWeatherInfoTemperatureGet = null;
            viewModelWeatherInfoNear5HourImgGet = null;
            viewModelWeatherInfoNear4HourTempGet = null;
            viewModelWeatherInfoNear5HourTempGet = null;
            viewModelWeatherInfoNear3HourClockUnitGet = null;
            viewModelWeatherInfoNear3HourClockUnitGet2 = null;
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
            viewModelWeatherInfoNear1HourTempGet = null;
            viewModelWeatherInfoNear1HourTempGet2 = null;
            viewModelWeatherInfoId8CardDetailImg3Get = null;
            viewModelWeatherInfoNear4HourClockUnitGet = null;
            viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = 0;
            viewModelWeatherInfoNear2HourClockUnitGet = null;
            viewModelWeatherInfoNear2HourClockUnitGet2 = null;
            viewModelWeatherInfoNear1HourClockUnitGet = null;
        } else {
            viewModelWeatherInfo = DashboardViewModel.weatherInfo;
            if ((dirtyFlags & 34359738369L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear2HourClock = viewModelWeatherInfo.near2HourClock;
                }
                updateRegistration(0, viewModelWeatherInfoNear2HourClock);
                if (viewModelWeatherInfoNear2HourClock != null) {
                    viewModelWeatherInfoNear2HourClockGet3 = viewModelWeatherInfoNear2HourClock.get();
                }
            }
            if ((dirtyFlags & 34359738370L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoTemperatureRange = viewModelWeatherInfo.temperatureRange;
                }
                updateRegistration(1, viewModelWeatherInfoTemperatureRange);
                if (viewModelWeatherInfoTemperatureRange != null) {
                    viewModelWeatherInfoTemperatureRangeGet2 = viewModelWeatherInfoTemperatureRange.get();
                }
            }
            if ((dirtyFlags & 34359738372L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoCardDetailText2 = viewModelWeatherInfo.cardDetailText2;
                }
                updateRegistration(2, viewModelWeatherInfoCardDetailText2);
                if (viewModelWeatherInfoCardDetailText2 != null) {
                    viewModelWeatherInfoCardDetailText2Get2 = viewModelWeatherInfoCardDetailText2.get();
                }
            }
            if ((dirtyFlags & 34359738376L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourClockUnit = viewModelWeatherInfo.near5HourClockUnit;
                }
                updateRegistration(3, viewModelWeatherInfoNear5HourClockUnit);
                if (viewModelWeatherInfoNear5HourClockUnit != null) {
                    viewModelWeatherInfoNear5HourClockUnitGet = viewModelWeatherInfoNear5HourClockUnit.get();
                }
            }
            if ((dirtyFlags & 34359738384L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear5HourClock = viewModelWeatherInfo.near5HourClock;
                }
                updateRegistration(4, viewModelWeatherInfoNear5HourClock);
                if (viewModelWeatherInfoNear5HourClock != null) {
                    viewModelWeatherInfoNear5HourClockGet2 = viewModelWeatherInfoNear5HourClock.get();
                }
            }
            if ((dirtyFlags & 34359738400L) != 0) {
                if (viewModelWeatherInfo != null) {
                    viewModelWeatherInfoNear1HourTemp = viewModelWeatherInfo.near1HourTemp;
                }
                updateRegistration(5, viewModelWeatherInfoNear1HourTemp);
                if (viewModelWeatherInfoNear1HourTemp != null) {
                    viewModelWeatherInfoNear1HourTempGet6 = viewModelWeatherInfoNear1HourTemp.get();
                }
            }
            if ((dirtyFlags & 34359738432L) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear2HourImg = null;
                } else {
                    viewModelWeatherInfoNear2HourImg = viewModelWeatherInfo.near2HourImg;
                }
                updateRegistration(6, viewModelWeatherInfoNear2HourImg);
                if (viewModelWeatherInfoNear2HourImg != null) {
                    viewModelWeatherInfoNear2HourImgGet3 = viewModelWeatherInfoNear2HourImg.get();
                }
            }
            if ((dirtyFlags & 34359738496L) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear5HourTemp = null;
                } else {
                    viewModelWeatherInfoNear5HourTemp = viewModelWeatherInfo.near5HourTemp;
                }
                updateRegistration(7, viewModelWeatherInfoNear5HourTemp);
                if (viewModelWeatherInfoNear5HourTemp != null) {
                    viewModelWeatherInfoNear5HourTempGet3 = viewModelWeatherInfoNear5HourTemp.get();
                }
            }
            if ((34359738624L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear4HourImg = null;
                } else {
                    viewModelWeatherInfoNear4HourImg = viewModelWeatherInfo.near4HourImg;
                }
                updateRegistration(8, viewModelWeatherInfoNear4HourImg);
                if (viewModelWeatherInfoNear4HourImg != null) {
                    viewModelWeatherInfoNear4HourImgGet3 = viewModelWeatherInfoNear4HourImg.get();
                }
            }
            if ((34359738880L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoErrorMessage = null;
                } else {
                    viewModelWeatherInfoErrorMessage = viewModelWeatherInfo.errorMessage;
                }
                updateRegistration(9, viewModelWeatherInfoErrorMessage);
                if (viewModelWeatherInfoErrorMessage != null) {
                    viewModelWeatherInfoErrorMessageGet3 = viewModelWeatherInfoErrorMessage.get();
                }
            }
            if ((34359739392L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoTemperature = null;
                } else {
                    viewModelWeatherInfoTemperature = viewModelWeatherInfo.temperature;
                }
                updateRegistration(10, viewModelWeatherInfoTemperature);
                if (viewModelWeatherInfoTemperature != null) {
                    viewModelWeatherInfoTemperatureGet3 = viewModelWeatherInfoTemperature.get();
                }
            }
            if ((34359740416L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear1HourImg = null;
                } else {
                    viewModelWeatherInfoNear1HourImg = viewModelWeatherInfo.near1HourImg;
                }
                updateRegistration(11, viewModelWeatherInfoNear1HourImg);
                if (viewModelWeatherInfoNear1HourImg != null) {
                    viewModelWeatherInfoNear1HourImgGet4 = viewModelWeatherInfoNear1HourImg.get();
                }
            }
            if ((34359742464L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear3HourClock = null;
                } else {
                    viewModelWeatherInfoNear3HourClock = viewModelWeatherInfo.near3HourClock;
                }
                updateRegistration(12, viewModelWeatherInfoNear3HourClock);
                if (viewModelWeatherInfoNear3HourClock != null) {
                    viewModelWeatherInfoNear3HourClockGet2 = viewModelWeatherInfoNear3HourClock.get();
                }
            }
            if ((34359746560L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear1HourClockUnit = null;
                } else {
                    viewModelWeatherInfoNear1HourClockUnit = viewModelWeatherInfo.near1HourClockUnit;
                }
                updateRegistration(13, viewModelWeatherInfoNear1HourClockUnit);
                if (viewModelWeatherInfoNear1HourClockUnit != null) {
                    viewModelWeatherInfoNear1HourClockUnitGet4 = viewModelWeatherInfoNear1HourClockUnit.get();
                }
            }
            if ((34359754752L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear3HourClockUnit = null;
                } else {
                    viewModelWeatherInfoNear3HourClockUnit = viewModelWeatherInfo.near3HourClockUnit;
                }
                updateRegistration(14, viewModelWeatherInfoNear3HourClockUnit);
                if (viewModelWeatherInfoNear3HourClockUnit != null) {
                    viewModelWeatherInfoNear3HourClockUnitGet3 = viewModelWeatherInfoNear3HourClockUnit.get();
                }
            }
            if ((34359771136L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoId8CardDetailImg3 = null;
                } else {
                    viewModelWeatherInfoId8CardDetailImg3 = viewModelWeatherInfo.id8CardDetailImg3;
                }
                updateRegistration(15, viewModelWeatherInfoId8CardDetailImg3);
                if (viewModelWeatherInfoId8CardDetailImg3 != null) {
                    viewModelWeatherInfoId8CardDetailImg3Get2 = viewModelWeatherInfoId8CardDetailImg3.get();
                }
            }
            if ((dirtyFlags & 34359803904L) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoId8CardDetailImg1 = null;
                } else {
                    viewModelWeatherInfoId8CardDetailImg1 = viewModelWeatherInfo.id8CardDetailImg1;
                }
                updateRegistration(16, viewModelWeatherInfoId8CardDetailImg1);
                if (viewModelWeatherInfoId8CardDetailImg1 != null) {
                    viewModelWeatherInfoId8CardDetailImg1Get2 = viewModelWeatherInfoId8CardDetailImg1.get();
                }
            }
            if ((34359869440L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear5HourImg = null;
                } else {
                    viewModelWeatherInfoNear5HourImg = viewModelWeatherInfo.near5HourImg;
                }
                updateRegistration(17, viewModelWeatherInfoNear5HourImg);
                if (viewModelWeatherInfoNear5HourImg != null) {
                    viewModelWeatherInfoNear5HourImgGet3 = viewModelWeatherInfoNear5HourImg.get();
                }
            }
            if ((34360000512L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear4HourClock = null;
                } else {
                    viewModelWeatherInfoNear4HourClock = viewModelWeatherInfo.near4HourClock;
                }
                updateRegistration(18, viewModelWeatherInfoNear4HourClock);
                if (viewModelWeatherInfoNear4HourClock != null) {
                    viewModelWeatherInfoNear4HourClockGet2 = viewModelWeatherInfoNear4HourClock.get();
                }
            }
            if ((34360262656L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear2HourClockUnit = null;
                } else {
                    viewModelWeatherInfoNear2HourClockUnit = viewModelWeatherInfo.near2HourClockUnit;
                }
                updateRegistration(19, viewModelWeatherInfoNear2HourClockUnit);
                if (viewModelWeatherInfoNear2HourClockUnit != null) {
                    viewModelWeatherInfoNear2HourClockUnitGet3 = viewModelWeatherInfoNear2HourClockUnit.get();
                }
            }
            if ((34360786944L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear4HourTemp = null;
                } else {
                    viewModelWeatherInfoNear4HourTemp = viewModelWeatherInfo.near4HourTemp;
                }
                updateRegistration(20, viewModelWeatherInfoNear4HourTemp);
                if (viewModelWeatherInfoNear4HourTemp != null) {
                    viewModelWeatherInfoNear4HourTempGet5 = viewModelWeatherInfoNear4HourTemp.get();
                }
            }
            if ((34361835520L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear3HourTemp = null;
                } else {
                    viewModelWeatherInfoNear3HourTemp = viewModelWeatherInfo.near3HourTemp;
                }
                updateRegistration(21, viewModelWeatherInfoNear3HourTemp);
                if (viewModelWeatherInfoNear3HourTemp != null) {
                    viewModelWeatherInfoNear3HourTempGet4 = viewModelWeatherInfoNear3HourTemp.get();
                }
            }
            if ((34363932672L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoCardDetailText3 = null;
                } else {
                    viewModelWeatherInfoCardDetailText3 = viewModelWeatherInfo.cardDetailText3;
                }
                updateRegistration(22, viewModelWeatherInfoCardDetailText3);
                if (viewModelWeatherInfoCardDetailText3 != null) {
                    viewModelWeatherInfoCardDetailText3Get2 = viewModelWeatherInfoCardDetailText3.get();
                }
            }
            if ((34368126976L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear3HourImg = null;
                } else {
                    viewModelWeatherInfoNear3HourImg = viewModelWeatherInfo.near3HourImg;
                }
                updateRegistration(23, viewModelWeatherInfoNear3HourImg);
                if (viewModelWeatherInfoNear3HourImg != null) {
                    viewModelWeatherInfoNear3HourImgGet3 = viewModelWeatherInfoNear3HourImg.get();
                }
            }
            if ((dirtyFlags & 34376515584L) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoCardDetailText1 = null;
                } else {
                    viewModelWeatherInfoCardDetailText1 = viewModelWeatherInfo.cardDetailText1;
                }
                updateRegistration(24, viewModelWeatherInfoCardDetailText1);
                if (viewModelWeatherInfoCardDetailText1 != null) {
                    viewModelWeatherInfoCardDetailText1Get = viewModelWeatherInfoCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 34661728256L) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoIsInitFinished = null;
                } else {
                    viewModelWeatherInfoIsInitFinished = viewModelWeatherInfo.isInitFinished;
                }
                updateRegistration(25, viewModelWeatherInfoIsInitFinished);
                if (viewModelWeatherInfoIsInitFinished != null) {
                    Boolean viewModelWeatherInfoIsInitFinishedGet2 = viewModelWeatherInfoIsInitFinished.get();
                    viewModelWeatherInfoIsInitFinishedGet = viewModelWeatherInfoIsInitFinishedGet2;
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
                }
            }
            if ((34426847232L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear4HourClockUnit = null;
                } else {
                    viewModelWeatherInfoNear4HourClockUnit = viewModelWeatherInfo.near4HourClockUnit;
                }
                updateRegistration(26, viewModelWeatherInfoNear4HourClockUnit);
                if (viewModelWeatherInfoNear4HourClockUnit != null) {
                    viewModelWeatherInfoNear4HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockUnit.get();
                }
            }
            if ((34493956096L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear2HourTemp = null;
                } else {
                    viewModelWeatherInfoNear2HourTemp = viewModelWeatherInfo.near2HourTemp;
                }
                updateRegistration(27, viewModelWeatherInfoNear2HourTemp);
                if (viewModelWeatherInfoNear2HourTemp != null) {
                    viewModelWeatherInfoNear2HourTempGet3 = viewModelWeatherInfoNear2HourTemp.get();
                }
            }
            if ((dirtyFlags & 43218108416L) != 0) {
                if (viewModelWeatherInfo == null) {
                    ViewModelWeatherInfoIsLoadSuccess12 = null;
                } else {
                    ViewModelWeatherInfoIsLoadSuccess12 = viewModelWeatherInfo.isLoadSuccess;
                }
                updateRegistration(28, ViewModelWeatherInfoIsLoadSuccess12);
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
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoId8ImageIcon = null;
                } else {
                    viewModelWeatherInfoId8ImageIcon = viewModelWeatherInfo.id8ImageIcon;
                }
                updateRegistration(29, viewModelWeatherInfoId8ImageIcon);
                if (viewModelWeatherInfoId8ImageIcon != null) {
                    viewModelWeatherInfoId8ImageIconGet2 = viewModelWeatherInfoId8ImageIcon.get();
                }
            }
            if ((35433480192L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoId8CardDetailImg2 = null;
                } else {
                    viewModelWeatherInfoId8CardDetailImg2 = viewModelWeatherInfo.id8CardDetailImg2;
                }
                updateRegistration(30, viewModelWeatherInfoId8CardDetailImg2);
                if (viewModelWeatherInfoId8CardDetailImg2 != null) {
                    viewModelWeatherInfoId8CardDetailImg2Get2 = viewModelWeatherInfoId8CardDetailImg2.get();
                }
            }
            if ((36507222016L & dirtyFlags) != 0) {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoNear1HourClock = null;
                } else {
                    viewModelWeatherInfoNear1HourClock = viewModelWeatherInfo.near1HourClock;
                }
                updateRegistration(31, viewModelWeatherInfoNear1HourClock);
                if (viewModelWeatherInfoNear1HourClock != null) {
                    viewModelWeatherInfoNear1HourClockGet2 = viewModelWeatherInfoNear1HourClock.get();
                }
            }
            if ((38654705664L & dirtyFlags) == 0) {
                viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear3HourClockUnitGet3;
                viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear2HourClockGet3;
                viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet2;
                viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet3;
                viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                viewModelWeatherInfoNear2HourClockGet = null;
                viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet3;
                viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet4;
                viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet2;
                viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet;
                viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear1HourTempGet6;
                viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear5HourClockGet2;
                viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
            } else {
                if (viewModelWeatherInfo == null) {
                    viewModelWeatherInfoTemperUnit = null;
                } else {
                    viewModelWeatherInfoTemperUnit = viewModelWeatherInfo.temperUnit;
                }
                updateRegistration(32, viewModelWeatherInfoTemperUnit);
                if (viewModelWeatherInfoTemperUnit == null) {
                    viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                    viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                    viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                    viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear3HourClockUnitGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear2HourClockGet3;
                    viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet2;
                    viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet3;
                    viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                    viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                    viewModelWeatherInfoNear2HourClockGet = null;
                    viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet3;
                    viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet4;
                    viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet2;
                    viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                    viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                    viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet;
                    viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                    viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                    viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                    viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                    viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear1HourTempGet6;
                    viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear5HourClockGet2;
                    viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                    viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                    viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                    viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
                } else {
                    String viewModelWeatherInfoTemperUnitGet2 = viewModelWeatherInfoTemperUnit.get();
                    String viewModelWeatherInfoTemperUnitGet3 = viewModelWeatherInfoTemperUnitGet2;
                    viewModelWeatherInfoId8ImageIconGet = viewModelWeatherInfoId8ImageIconGet2;
                    viewModelWeatherInfoTemperatureGet = viewModelWeatherInfoTemperatureGet3;
                    viewModelWeatherInfoNear5HourImgGet = viewModelWeatherInfoNear5HourImgGet3;
                    viewModelWeatherInfoNear4HourTempGet = viewModelWeatherInfoNear4HourTempGet5;
                    viewModelWeatherInfoNear5HourTempGet = viewModelWeatherInfoNear5HourTempGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet = viewModelWeatherInfoNear3HourClockUnitGet3;
                    viewModelWeatherInfoNear3HourClockUnitGet2 = viewModelWeatherInfoNear2HourClockGet3;
                    viewModelWeatherInfoId8CardDetailImg1Get = viewModelWeatherInfoId8CardDetailImg1Get2;
                    viewModelWeatherInfoTemperatureRangeGet = viewModelWeatherInfoTemperatureRangeGet2;
                    viewModelWeatherInfoErrorMessageGet = viewModelWeatherInfoErrorMessageGet3;
                    viewModelWeatherInfoNear3HourImgGet = viewModelWeatherInfoNear3HourImgGet3;
                    viewModelWeatherInfoNear2HourImgGet = viewModelWeatherInfoNear2HourImgGet3;
                    viewModelWeatherInfoNear2HourClockGet = viewModelWeatherInfoTemperUnitGet3;
                    viewModelWeatherInfoNear5HourTempGet2 = viewModelWeatherInfoNear2HourTempGet3;
                    viewModelWeatherInfoTemperatureGet2 = viewModelWeatherInfoNear3HourTempGet4;
                    viewModelWeatherInfoNear4HourTempGet2 = viewModelWeatherInfoNear3HourClockGet2;
                    viewModelWeatherInfoNear1HourClockGet = viewModelWeatherInfoNear1HourClockGet2;
                    viewModelWeatherInfoNear4HourImgGet = viewModelWeatherInfoNear4HourImgGet3;
                    viewModelWeatherInfoNear3HourTempGet = viewModelWeatherInfoNear5HourClockUnitGet;
                    viewModelWeatherInfoCardDetailText2Get = viewModelWeatherInfoCardDetailText2Get2;
                    viewModelWeatherInfoCardDetailText3Get = viewModelWeatherInfoCardDetailText3Get2;
                    viewModelWeatherInfoId8CardDetailImg2Get = viewModelWeatherInfoId8CardDetailImg2Get2;
                    viewModelWeatherInfoNear1HourImgGet = viewModelWeatherInfoNear1HourImgGet4;
                    viewModelWeatherInfoIsLoadSuccessGet = viewModelWeatherInfoIsLoadSuccessGet4;
                    viewModelWeatherInfoNear1HourTempGet = viewModelWeatherInfoNear1HourTempGet6;
                    viewModelWeatherInfoNear1HourTempGet2 = viewModelWeatherInfoNear5HourClockGet2;
                    viewModelWeatherInfoId8CardDetailImg3Get = viewModelWeatherInfoId8CardDetailImg3Get2;
                    viewModelWeatherInfoNear4HourClockUnitGet = viewModelWeatherInfoNear4HourClockUnitGet2;
                    viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE = viewModelWeatherInfoIsInitFinishedViewINVISIBLEViewVISIBLE2;
                    viewModelWeatherInfoNear2HourClockUnitGet = viewModelWeatherInfoNear2HourClockUnitGet3;
                    viewModelWeatherInfoNear2HourClockUnitGet2 = viewModelWeatherInfoNear4HourClockGet2;
                    viewModelWeatherInfoNear1HourClockUnitGet = viewModelWeatherInfoNear1HourClockUnitGet4;
                }
            }
        }
        if ((dirtyFlags & 43980465111040L) == 0) {
            viewModelWeatherInfoNear1HourImgGet2 = viewModelWeatherInfoNear1HourImgGet;
            viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet;
            viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet;
        } else {
            if (viewModelWeatherInfo == null) {
                viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet;
                ViewModelWeatherInfoIsLoadSuccess1 = ViewModelWeatherInfoIsLoadSuccess13;
            } else {
                viewModelWeatherInfoNear1HourTempGet3 = viewModelWeatherInfoNear1HourTempGet;
                ViewModelWeatherInfoIsLoadSuccess1 = viewModelWeatherInfo.isLoadSuccess;
            }
            viewModelWeatherInfoNear1HourImgGet2 = viewModelWeatherInfoNear1HourImgGet;
            updateRegistration(28, ViewModelWeatherInfoIsLoadSuccess1);
            if (ViewModelWeatherInfoIsLoadSuccess1 == null) {
                viewModelWeatherInfoIsLoadSuccessGet3 = viewModelWeatherInfoIsLoadSuccessGet;
            } else {
                viewModelWeatherInfoIsLoadSuccessGet3 = ViewModelWeatherInfoIsLoadSuccess1.get();
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
                viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet3;
            } else {
                viewModelWeatherInfoIsLoadSuccessGet2 = viewModelWeatherInfoIsLoadSuccessGet3;
            }
        }
        if ((dirtyFlags & 34661728256L) == 0) {
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
        } else {
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
            int viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = viewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
            ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = ViewModelWeatherInfoIsInitFinishedViewModelWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        }
        if ((dirtyFlags & 137438953472L) == 0) {
            viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
        } else {
            if (viewModelWeatherInfo == null) {
                viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
                viewModelWeatherInfoCity = null;
            } else {
                viewModelWeatherInfoNear1HourClockUnitGet2 = viewModelWeatherInfoNear1HourClockUnitGet;
                viewModelWeatherInfoCity = viewModelWeatherInfo.city;
            }
            updateRegistration(33, viewModelWeatherInfoCity);
            if (viewModelWeatherInfoCity != null) {
                viewModelWeatherInfoCityGet = viewModelWeatherInfoCity.get();
            }
        }
        if ((dirtyFlags & 43218108416L) == 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather = null;
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxViewModelWeatherInfoIsLoadSuccessGet ? viewModelWeatherInfoCityGet : this.mboundView4.getResources().getString(C0899R.string.ksw_id8_weather);
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
            viewModelWeatherInfoNear1HourClockUnitGet3 = viewModelWeatherInfoNear1HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView15, viewModelWeatherInfoNear1HourClockUnitGet3);
        } else {
            viewModelWeatherInfoNear1HourClockUnitGet3 = viewModelWeatherInfoNear1HourClockUnitGet2;
        }
        if ((dirtyFlags & 34359740416L) != 0) {
            viewModelWeatherInfoNear1HourImgGet3 = viewModelWeatherInfoNear1HourImgGet2;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView16, viewModelWeatherInfoNear1HourImgGet3);
        } else {
            viewModelWeatherInfoNear1HourImgGet3 = viewModelWeatherInfoNear1HourImgGet2;
        }
        if ((dirtyFlags & 34359738400L) != 0) {
            viewModelWeatherInfoNear1HourTempGet4 = viewModelWeatherInfoNear1HourTempGet3;
            TextViewBindingAdapter.setText(this.mboundView17, viewModelWeatherInfoNear1HourTempGet4);
        } else {
            viewModelWeatherInfoNear1HourTempGet4 = viewModelWeatherInfoNear1HourTempGet3;
        }
        if ((dirtyFlags & 38654705664L) != 0) {
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
            viewModelWeatherInfoTemperUnitGet = viewModelWeatherInfoNear3HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView19, viewModelWeatherInfoTemperUnitGet);
        } else {
            viewModelWeatherInfoTemperUnitGet = viewModelWeatherInfoNear3HourClockUnitGet2;
        }
        if ((dirtyFlags & 34360262656L) != 0) {
            viewModelWeatherInfoNear2HourClockGet2 = viewModelWeatherInfoNear2HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView20, viewModelWeatherInfoNear2HourClockGet2);
        } else {
            viewModelWeatherInfoNear2HourClockGet2 = viewModelWeatherInfoNear2HourClockUnitGet;
        }
        if ((dirtyFlags & 34359738432L) != 0) {
            viewModelWeatherInfoNear2HourImgGet2 = viewModelWeatherInfoNear2HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView21, viewModelWeatherInfoNear2HourImgGet2);
        } else {
            viewModelWeatherInfoNear2HourImgGet2 = viewModelWeatherInfoNear2HourImgGet;
        }
        if ((dirtyFlags & 34493956096L) != 0) {
            viewModelWeatherInfoNear2HourTempGet = viewModelWeatherInfoNear5HourTempGet2;
            TextViewBindingAdapter.setText(this.mboundView22, viewModelWeatherInfoNear2HourTempGet);
        } else {
            viewModelWeatherInfoNear2HourTempGet = viewModelWeatherInfoNear5HourTempGet2;
        }
        if ((dirtyFlags & 34359742464L) != 0) {
            viewModelWeatherInfoNear2HourTempGet2 = viewModelWeatherInfoNear4HourTempGet2;
            TextViewBindingAdapter.setText(this.mboundView24, viewModelWeatherInfoNear2HourTempGet2);
        } else {
            viewModelWeatherInfoNear2HourTempGet2 = viewModelWeatherInfoNear4HourTempGet2;
        }
        if ((dirtyFlags & 34359754752L) != 0) {
            viewModelWeatherInfoNear3HourClockGet = viewModelWeatherInfoNear3HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView25, viewModelWeatherInfoNear3HourClockGet);
        } else {
            viewModelWeatherInfoNear3HourClockGet = viewModelWeatherInfoNear3HourClockUnitGet;
        }
        if ((dirtyFlags & 34368126976L) != 0) {
            viewModelWeatherInfoNear3HourImgGet2 = viewModelWeatherInfoNear3HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView26, viewModelWeatherInfoNear3HourImgGet2);
        } else {
            viewModelWeatherInfoNear3HourImgGet2 = viewModelWeatherInfoNear3HourImgGet;
        }
        if ((dirtyFlags & 34361835520L) != 0) {
            viewModelWeatherInfoNear3HourTempGet2 = viewModelWeatherInfoTemperatureGet2;
            TextViewBindingAdapter.setText(this.mboundView27, viewModelWeatherInfoNear3HourTempGet2);
        } else {
            viewModelWeatherInfoNear3HourTempGet2 = viewModelWeatherInfoTemperatureGet2;
        }
        if ((dirtyFlags & 34360000512L) != 0) {
            viewModelWeatherInfoNear3HourTempGet3 = viewModelWeatherInfoNear2HourClockUnitGet2;
            TextViewBindingAdapter.setText(this.mboundView29, viewModelWeatherInfoNear3HourTempGet3);
        } else {
            viewModelWeatherInfoNear3HourTempGet3 = viewModelWeatherInfoNear2HourClockUnitGet2;
        }
        if ((dirtyFlags & 34359738880L) != 0) {
            viewModelWeatherInfoNear4HourClockGet = viewModelWeatherInfoErrorMessageGet;
            TextViewBindingAdapter.setText(this.mboundView3, viewModelWeatherInfoNear4HourClockGet);
        } else {
            viewModelWeatherInfoNear4HourClockGet = viewModelWeatherInfoErrorMessageGet;
        }
        if ((dirtyFlags & 34426847232L) != 0) {
            viewModelWeatherInfoErrorMessageGet2 = viewModelWeatherInfoNear4HourClockUnitGet;
            TextViewBindingAdapter.setText(this.mboundView30, viewModelWeatherInfoErrorMessageGet2);
        } else {
            viewModelWeatherInfoErrorMessageGet2 = viewModelWeatherInfoNear4HourClockUnitGet;
        }
        if ((dirtyFlags & 34359738624L) != 0) {
            viewModelWeatherInfoNear4HourImgGet2 = viewModelWeatherInfoNear4HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView31, viewModelWeatherInfoNear4HourImgGet2);
        } else {
            viewModelWeatherInfoNear4HourImgGet2 = viewModelWeatherInfoNear4HourImgGet;
        }
        if ((dirtyFlags & 34360786944L) != 0) {
            viewModelWeatherInfoNear4HourTempGet3 = viewModelWeatherInfoNear4HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView32, viewModelWeatherInfoNear4HourTempGet3);
        } else {
            viewModelWeatherInfoNear4HourTempGet3 = viewModelWeatherInfoNear4HourTempGet;
        }
        if ((dirtyFlags & 34359738384L) != 0) {
            viewModelWeatherInfoNear4HourTempGet4 = viewModelWeatherInfoNear1HourTempGet2;
            TextViewBindingAdapter.setText(this.mboundView34, viewModelWeatherInfoNear4HourTempGet4);
        } else {
            viewModelWeatherInfoNear4HourTempGet4 = viewModelWeatherInfoNear1HourTempGet2;
        }
        if ((dirtyFlags & 34359738376L) != 0) {
            viewModelWeatherInfoNear5HourClockGet = viewModelWeatherInfoNear3HourTempGet;
            TextViewBindingAdapter.setText(this.mboundView35, viewModelWeatherInfoNear5HourClockGet);
        } else {
            viewModelWeatherInfoNear5HourClockGet = viewModelWeatherInfoNear3HourTempGet;
        }
        if ((dirtyFlags & 34359869440L) != 0) {
            viewModelWeatherInfoNear5HourImgGet2 = viewModelWeatherInfoNear5HourImgGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView36, viewModelWeatherInfoNear5HourImgGet2);
        } else {
            viewModelWeatherInfoNear5HourImgGet2 = viewModelWeatherInfoNear5HourImgGet;
        }
        if ((dirtyFlags & 34359738496L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView37, viewModelWeatherInfoNear5HourTempGet);
        }
        if ((dirtyFlags & 43218108416L) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather);
        }
        if ((dirtyFlags & 34359738370L) != 0) {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather2 = viewModelWeatherInfoTemperatureRangeGet;
            TextViewBindingAdapter.setText(this.mboundView8, viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather2);
        } else {
            viewModelWeatherInfoIsLoadSuccessViewModelWeatherInfoCityMboundView4AndroidStringKswId8Weather2 = viewModelWeatherInfoTemperatureRangeGet;
        }
        if ((dirtyFlags & 34359739392L) != 0) {
            TextViewBindingAdapter.setText(this.temperatureTv, viewModelWeatherInfoTemperatureGet);
        }
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public class AudiRightWeatherBindingImpl extends AudiRightWeatherBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback359;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView10;
    private final ImageView mboundView11;
    private final TextView mboundView12;
    private final ImageView mboundView13;
    private final TextView mboundView14;
    private final MarqueeTextView mboundView15;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final ImageView mboundView9;

    public AudiRightWeatherBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private AudiRightWeatherBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 16, (ConstraintLayout) bindings[1], (ImageView) bindings[2], (TextView) bindings[5], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.clWeather.setTag(null);
        this.ivIcon.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[11];
        this.mboundView11 = imageView;
        imageView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        ImageView imageView2 = (ImageView) bindings[13];
        this.mboundView13 = imageView2;
        imageView2.setTag(null);
        TextView textView3 = (TextView) bindings[14];
        this.mboundView14 = textView3;
        textView3.setTag(null);
        MarqueeTextView marqueeTextView = (MarqueeTextView) bindings[15];
        this.mboundView15 = marqueeTextView;
        marqueeTextView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[3];
        this.mboundView3 = linearLayout;
        linearLayout.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[8];
        this.mboundView8 = textView6;
        textView6.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        this.temperatureTv.setTag(null);
        this.unitWeather.setTag(null);
        setRootTag(root);
        this.mCallback359 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
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
        if (26 == variableId) {
            setVm((AudiViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiRightWeatherBinding
    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmWeatherInfoTemperature((ObservableField) object, fieldId);
            case 1:
                return onChangeVmWeatherInfoPhrase((ObservableField) object, fieldId);
            case 2:
                return onChangeVmWeatherInfoAudiCardDetailImg2((ObservableField) object, fieldId);
            case 3:
                return onChangeVmWeatherInfoAudiCardDetailText1((ObservableField) object, fieldId);
            case 4:
                return onChangeVmWeatherInfoAudiCardDetailText3((ObservableField) object, fieldId);
            case 5:
                return onChangeVmWeatherInfoCity((ObservableField) object, fieldId);
            case 6:
                return onChangeVmWeatherInfoIsLoadSuccess((ObservableField) object, fieldId);
            case 7:
                return onChangeVmWeatherInfoIsInitFinished((ObservableField) object, fieldId);
            case 8:
                return onChangeVmLogoView((ObservableInt) object, fieldId);
            case 9:
                return onChangeVmWeatherInfoAudiImageIcon((ObservableField) object, fieldId);
            case 10:
                return onChangeVmWeatherInfoTemperatureRange((ObservableField) object, fieldId);
            case 11:
                return onChangeVmWeatherInfoTemperUnit((ObservableField) object, fieldId);
            case 12:
                return onChangeVmWeatherInfoAudiCardDetailImg3((ObservableField) object, fieldId);
            case 13:
                return onChangeVmWeatherInfoAudiCardDetailImg1((ObservableField) object, fieldId);
            case 14:
                return onChangeVmWeatherInfoErrorMessage((ObservableField) object, fieldId);
            case 15:
                return onChangeVmWeatherInfoAudiCardDetailText2((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmWeatherInfoTemperature(ObservableField<String> VmWeatherInfoTemperature, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoPhrase(ObservableField<String> VmWeatherInfoPhrase, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailImg2(ObservableField<Drawable> VmWeatherInfoAudiCardDetailImg2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailText1(ObservableField<String> VmWeatherInfoAudiCardDetailText1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailText3(ObservableField<String> VmWeatherInfoAudiCardDetailText3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoCity(ObservableField<String> VmWeatherInfoCity, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoIsLoadSuccess(ObservableField<Boolean> VmWeatherInfoIsLoadSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoIsInitFinished(ObservableField<Boolean> VmWeatherInfoIsInitFinished, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmLogoView(ObservableInt VmLogoView, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiImageIcon(ObservableField<Drawable> VmWeatherInfoAudiImageIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoTemperatureRange(ObservableField<String> VmWeatherInfoTemperatureRange, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoTemperUnit(ObservableField<String> VmWeatherInfoTemperUnit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailImg3(ObservableField<Drawable> VmWeatherInfoAudiCardDetailImg3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailImg1(ObservableField<Drawable> VmWeatherInfoAudiCardDetailImg1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoErrorMessage(ObservableField<String> VmWeatherInfoErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmWeatherInfoAudiCardDetailText2(ObservableField<String> VmWeatherInfoAudiCardDetailText2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int vmLogoViewGet;
        Drawable vmWeatherInfoAudiCardDetailImg2Get;
        long dirtyFlags2;
        String vmWeatherInfoAudiCardDetailText3Get;
        String vmWeatherInfoPhraseGet;
        int vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
        String vmWeatherInfoAudiCardDetailText2Get;
        String vmWeatherInfoTemperatureGet;
        Drawable vmWeatherInfoAudiCardDetailImg3Get;
        String vmWeatherInfoAudiCardDetailText1Get;
        String vmWeatherInfoTemperUnitGet;
        int VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        String vmWeatherInfoTemperatureRangeGet;
        WeatherInfo vmWeatherInfo;
        Drawable vmWeatherInfoAudiCardDetailImg1Get;
        String vmWeatherInfoTemperatureGet2;
        int vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2;
        int VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12;
        String vmWeatherInfoPhraseGet2;
        String vmWeatherInfoIsLoadSuccessVmWeatherInfoCityMboundView15AndroidStringKswId8Weather;
        String vmWeatherInfoTemperatureRangeGet2;
        String vmWeatherInfoPhraseGet3;
        Drawable vmWeatherInfoAudiCardDetailImg1Get2;
        String vmWeatherInfoTemperatureGet3;
        ObservableField<String> vmWeatherInfoCity;
        ObservableField<Boolean> vmWeatherInfoIsLoadSuccess;
        String vmWeatherInfoPhraseGet4;
        String vmWeatherInfoPhraseGet5;
        ObservableField<String> vmWeatherInfoAudiCardDetailText2;
        ObservableField<String> vmWeatherInfoErrorMessage;
        ObservableField<Drawable> vmWeatherInfoAudiCardDetailImg1;
        ObservableField<Drawable> vmWeatherInfoAudiCardDetailImg3;
        ObservableField<String> vmWeatherInfoTemperUnit;
        ObservableField<String> vmWeatherInfoTemperatureRange;
        ObservableField<Drawable> vmWeatherInfoAudiImageIcon;
        ObservableField<Boolean> vmWeatherInfoIsInitFinished;
        ObservableField<Boolean> vmWeatherInfoIsLoadSuccess2;
        ObservableField<String> vmWeatherInfoAudiCardDetailText3;
        ObservableField<String> vmWeatherInfoAudiCardDetailText1;
        ObservableInt vmLogoView;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableField<String> vmWeatherInfoTemperature = null;
        Drawable vmWeatherInfoAudiCardDetailImg1Get3 = null;
        ObservableField<String> vmWeatherInfoPhrase = null;
        AudiViewModel vm = this.mVm;
        ObservableField<Drawable> vmWeatherInfoAudiCardDetailImg2 = null;
        Drawable vmWeatherInfoAudiImageIconGet = null;
        String vmWeatherInfoErrorMessageGet = null;
        String vmWeatherInfoAudiCardDetailText3Get2 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsInitFinishedGet = false;
        String vmWeatherInfoTemperUnitGet2 = null;
        ObservableField<Boolean> vmWeatherInfoIsLoadSuccess3 = null;
        boolean androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet = false;
        Boolean vmWeatherInfoIsLoadSuccessGet = null;
        String vmWeatherInfoTemperatureRangeGet3 = null;
        Boolean vmWeatherInfoIsInitFinishedGet = null;
        String vmWeatherInfoTemperatureGet4 = null;
        boolean VmWeatherInfoIsLoadSuccess1 = false;
        Drawable vmWeatherInfoAudiCardDetailImg3Get2 = null;
        String vmWeatherInfoCityGet = null;
        String vmWeatherInfoAudiCardDetailText1Get2 = null;
        Drawable vmWeatherInfoAudiCardDetailImg2Get2 = null;
        if ((dirtyFlags & 196864) == 0) {
            vmLogoViewGet = 0;
        } else {
            if (vm == null) {
                vmLogoView = null;
            } else {
                vmLogoView = vm.logoView;
            }
            updateRegistration(8, vmLogoView);
            if (vmLogoView == null) {
                vmLogoViewGet = 0;
            } else {
                int vmLogoViewGet2 = vmLogoView.get();
                vmLogoViewGet = vmLogoViewGet2;
            }
        }
        if ((dirtyFlags & 196351) == 0) {
            vmWeatherInfoAudiCardDetailImg2Get = null;
            dirtyFlags2 = dirtyFlags;
            vmWeatherInfoAudiCardDetailText3Get = null;
            vmWeatherInfoPhraseGet = null;
            vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
            vmWeatherInfoAudiCardDetailText2Get = null;
            vmWeatherInfoTemperatureGet = null;
            vmWeatherInfoAudiCardDetailImg3Get = null;
            vmWeatherInfoAudiCardDetailText1Get = null;
            vmWeatherInfoTemperUnitGet = null;
            VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
            vmWeatherInfoTemperatureRangeGet = null;
            vmWeatherInfo = null;
        } else {
            WeatherInfo vmWeatherInfo2 = AudiViewModel.weatherInfo;
            if ((dirtyFlags & 131073) != 0) {
                if (vmWeatherInfo2 != null) {
                    vmWeatherInfoTemperature = vmWeatherInfo2.temperature;
                }
                updateRegistration(0, vmWeatherInfoTemperature);
                if (vmWeatherInfoTemperature != null) {
                    vmWeatherInfoTemperatureGet4 = vmWeatherInfoTemperature.get();
                }
            }
            if ((dirtyFlags & 131074) == 0) {
                vmWeatherInfoPhraseGet4 = null;
            } else {
                if (vmWeatherInfo2 != null) {
                    vmWeatherInfoPhrase = vmWeatherInfo2.phrase;
                }
                updateRegistration(1, vmWeatherInfoPhrase);
                if (vmWeatherInfoPhrase == null) {
                    vmWeatherInfoPhraseGet4 = null;
                } else {
                    vmWeatherInfoPhraseGet4 = vmWeatherInfoPhrase.get();
                }
            }
            if ((dirtyFlags & 131076) == 0) {
                vmWeatherInfoPhraseGet5 = vmWeatherInfoPhraseGet4;
            } else {
                if (vmWeatherInfo2 != null) {
                    vmWeatherInfoAudiCardDetailImg2 = vmWeatherInfo2.audiCardDetailImg2;
                }
                vmWeatherInfoPhraseGet5 = vmWeatherInfoPhraseGet4;
                updateRegistration(2, vmWeatherInfoAudiCardDetailImg2);
                if (vmWeatherInfoAudiCardDetailImg2 != null) {
                    vmWeatherInfoAudiCardDetailImg2Get2 = vmWeatherInfoAudiCardDetailImg2.get();
                }
            }
            if ((dirtyFlags & 131080) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiCardDetailText1 = null;
                } else {
                    vmWeatherInfoAudiCardDetailText1 = vmWeatherInfo2.audiCardDetailText1;
                }
                updateRegistration(3, vmWeatherInfoAudiCardDetailText1);
                if (vmWeatherInfoAudiCardDetailText1 != null) {
                    vmWeatherInfoAudiCardDetailText1Get2 = vmWeatherInfoAudiCardDetailText1.get();
                }
            }
            if ((dirtyFlags & 131088) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiCardDetailText3 = null;
                } else {
                    vmWeatherInfoAudiCardDetailText3 = vmWeatherInfo2.audiCardDetailText3;
                }
                updateRegistration(4, vmWeatherInfoAudiCardDetailText3);
                if (vmWeatherInfoAudiCardDetailText3 != null) {
                    vmWeatherInfoAudiCardDetailText3Get2 = vmWeatherInfoAudiCardDetailText3.get();
                }
            }
            if ((dirtyFlags & 131168) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoIsLoadSuccess2 = null;
                } else {
                    vmWeatherInfoIsLoadSuccess2 = vmWeatherInfo2.isLoadSuccess;
                }
                updateRegistration(6, vmWeatherInfoIsLoadSuccess2);
                if (vmWeatherInfoIsLoadSuccess2 != null) {
                    vmWeatherInfoIsLoadSuccessGet = vmWeatherInfoIsLoadSuccess2.get();
                }
                androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(vmWeatherInfoIsLoadSuccessGet);
                if ((dirtyFlags & 131168) == 0) {
                    vmWeatherInfoIsLoadSuccess3 = vmWeatherInfoIsLoadSuccess2;
                } else if (androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags |= 134217728;
                    vmWeatherInfoIsLoadSuccess3 = vmWeatherInfoIsLoadSuccess2;
                } else {
                    dirtyFlags |= 67108864;
                    vmWeatherInfoIsLoadSuccess3 = vmWeatherInfoIsLoadSuccess2;
                }
            }
            if ((dirtyFlags & 131264) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoIsInitFinished = null;
                } else {
                    vmWeatherInfoIsInitFinished = vmWeatherInfo2.isInitFinished;
                }
                updateRegistration(7, vmWeatherInfoIsInitFinished);
                if (vmWeatherInfoIsInitFinished != null) {
                    Boolean vmWeatherInfoIsInitFinishedGet2 = vmWeatherInfoIsInitFinished.get();
                    vmWeatherInfoIsInitFinishedGet = vmWeatherInfoIsInitFinishedGet2;
                }
                androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsInitFinishedGet = ViewDataBinding.safeUnbox(vmWeatherInfoIsInitFinishedGet);
                if ((dirtyFlags & 131264) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsInitFinishedGet) {
                        dirtyFlags = dirtyFlags | 8388608 | 33554432;
                    } else {
                        dirtyFlags = dirtyFlags | 4194304 | 16777216;
                    }
                }
            }
            if ((dirtyFlags & 131584) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiImageIcon = null;
                } else {
                    vmWeatherInfoAudiImageIcon = vmWeatherInfo2.audiImageIcon;
                }
                updateRegistration(9, vmWeatherInfoAudiImageIcon);
                if (vmWeatherInfoAudiImageIcon != null) {
                    vmWeatherInfoAudiImageIconGet = vmWeatherInfoAudiImageIcon.get();
                }
            }
            if ((dirtyFlags & 132096) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoTemperatureRange = null;
                } else {
                    vmWeatherInfoTemperatureRange = vmWeatherInfo2.temperatureRange;
                }
                updateRegistration(10, vmWeatherInfoTemperatureRange);
                if (vmWeatherInfoTemperatureRange != null) {
                    vmWeatherInfoTemperatureRangeGet3 = vmWeatherInfoTemperatureRange.get();
                }
            }
            if ((dirtyFlags & 133120) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoTemperUnit = null;
                } else {
                    vmWeatherInfoTemperUnit = vmWeatherInfo2.temperUnit;
                }
                updateRegistration(11, vmWeatherInfoTemperUnit);
                if (vmWeatherInfoTemperUnit != null) {
                    vmWeatherInfoTemperUnitGet2 = vmWeatherInfoTemperUnit.get();
                }
            }
            if ((dirtyFlags & 135168) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiCardDetailImg3 = null;
                } else {
                    vmWeatherInfoAudiCardDetailImg3 = vmWeatherInfo2.audiCardDetailImg3;
                }
                updateRegistration(12, vmWeatherInfoAudiCardDetailImg3);
                if (vmWeatherInfoAudiCardDetailImg3 != null) {
                    vmWeatherInfoAudiCardDetailImg3Get2 = vmWeatherInfoAudiCardDetailImg3.get();
                }
            }
            if ((dirtyFlags & 139264) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiCardDetailImg1 = null;
                } else {
                    vmWeatherInfoAudiCardDetailImg1 = vmWeatherInfo2.audiCardDetailImg1;
                }
                updateRegistration(13, vmWeatherInfoAudiCardDetailImg1);
                if (vmWeatherInfoAudiCardDetailImg1 != null) {
                    vmWeatherInfoAudiCardDetailImg1Get3 = vmWeatherInfoAudiCardDetailImg1.get();
                }
            }
            if ((dirtyFlags & 147456) != 0) {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoErrorMessage = null;
                } else {
                    vmWeatherInfoErrorMessage = vmWeatherInfo2.errorMessage;
                }
                updateRegistration(14, vmWeatherInfoErrorMessage);
                if (vmWeatherInfoErrorMessage != null) {
                    vmWeatherInfoErrorMessageGet = vmWeatherInfoErrorMessage.get();
                }
            }
            if ((dirtyFlags & 163840) == 0) {
                vmWeatherInfoAudiCardDetailImg2Get = vmWeatherInfoAudiCardDetailImg2Get2;
                vmWeatherInfo = vmWeatherInfo2;
                dirtyFlags2 = dirtyFlags;
                vmWeatherInfoAudiCardDetailText3Get = vmWeatherInfoAudiCardDetailText3Get2;
                vmWeatherInfoPhraseGet = vmWeatherInfoPhraseGet5;
                vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                vmWeatherInfoAudiCardDetailText2Get = null;
                vmWeatherInfoTemperatureGet = vmWeatherInfoTemperatureGet4;
                vmWeatherInfoAudiCardDetailImg3Get = vmWeatherInfoAudiCardDetailImg3Get2;
                vmWeatherInfoAudiCardDetailText1Get = vmWeatherInfoAudiCardDetailText1Get2;
                vmWeatherInfoTemperUnitGet = vmWeatherInfoTemperUnitGet2;
                VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                vmWeatherInfoTemperatureRangeGet = vmWeatherInfoTemperatureRangeGet3;
            } else {
                if (vmWeatherInfo2 == null) {
                    vmWeatherInfoAudiCardDetailText2 = null;
                } else {
                    vmWeatherInfoAudiCardDetailText2 = vmWeatherInfo2.audiCardDetailText2;
                }
                updateRegistration(15, vmWeatherInfoAudiCardDetailText2);
                if (vmWeatherInfoAudiCardDetailText2 == null) {
                    vmWeatherInfoAudiCardDetailImg2Get = vmWeatherInfoAudiCardDetailImg2Get2;
                    vmWeatherInfo = vmWeatherInfo2;
                    dirtyFlags2 = dirtyFlags;
                    vmWeatherInfoAudiCardDetailText3Get = vmWeatherInfoAudiCardDetailText3Get2;
                    vmWeatherInfoPhraseGet = vmWeatherInfoPhraseGet5;
                    vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    vmWeatherInfoAudiCardDetailText2Get = null;
                    vmWeatherInfoTemperatureGet = vmWeatherInfoTemperatureGet4;
                    vmWeatherInfoAudiCardDetailImg3Get = vmWeatherInfoAudiCardDetailImg3Get2;
                    vmWeatherInfoAudiCardDetailText1Get = vmWeatherInfoAudiCardDetailText1Get2;
                    vmWeatherInfoTemperUnitGet = vmWeatherInfoTemperUnitGet2;
                    VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    vmWeatherInfoTemperatureRangeGet = vmWeatherInfoTemperatureRangeGet3;
                } else {
                    String vmWeatherInfoAudiCardDetailText2Get2 = vmWeatherInfoAudiCardDetailText2.get();
                    vmWeatherInfoAudiCardDetailImg2Get = vmWeatherInfoAudiCardDetailImg2Get2;
                    vmWeatherInfo = vmWeatherInfo2;
                    dirtyFlags2 = dirtyFlags;
                    vmWeatherInfoAudiCardDetailText3Get = vmWeatherInfoAudiCardDetailText3Get2;
                    vmWeatherInfoPhraseGet = vmWeatherInfoPhraseGet5;
                    vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE = 0;
                    vmWeatherInfoAudiCardDetailText2Get = vmWeatherInfoAudiCardDetailText2Get2;
                    vmWeatherInfoTemperatureGet = vmWeatherInfoTemperatureGet4;
                    vmWeatherInfoAudiCardDetailImg3Get = vmWeatherInfoAudiCardDetailImg3Get2;
                    vmWeatherInfoAudiCardDetailText1Get = vmWeatherInfoAudiCardDetailText1Get2;
                    vmWeatherInfoTemperUnitGet = vmWeatherInfoTemperUnitGet2;
                    VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1 = 0;
                    vmWeatherInfoTemperatureRangeGet = vmWeatherInfoTemperatureRangeGet3;
                }
            }
        }
        if ((dirtyFlags2 & 41943040) == 0) {
            vmWeatherInfoAudiCardDetailImg1Get = vmWeatherInfoAudiCardDetailImg1Get3;
            vmWeatherInfoTemperatureGet2 = vmWeatherInfoTemperatureGet;
        } else {
            if (vmWeatherInfo == null) {
                vmWeatherInfoTemperatureGet2 = vmWeatherInfoTemperatureGet;
                vmWeatherInfoIsLoadSuccess = vmWeatherInfoIsLoadSuccess3;
            } else {
                vmWeatherInfoTemperatureGet2 = vmWeatherInfoTemperatureGet;
                vmWeatherInfoIsLoadSuccess = vmWeatherInfo.isLoadSuccess;
            }
            vmWeatherInfoAudiCardDetailImg1Get = vmWeatherInfoAudiCardDetailImg1Get3;
            updateRegistration(6, vmWeatherInfoIsLoadSuccess);
            if (vmWeatherInfoIsLoadSuccess != null) {
                vmWeatherInfoIsLoadSuccessGet = vmWeatherInfoIsLoadSuccess.get();
            }
            androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet = ViewDataBinding.safeUnbox(vmWeatherInfoIsLoadSuccessGet);
            if ((dirtyFlags2 & 131168) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet) {
                    dirtyFlags2 |= 134217728;
                } else {
                    dirtyFlags2 |= 67108864;
                }
            }
            if ((dirtyFlags2 & 33554432) != 0) {
                VmWeatherInfoIsLoadSuccess1 = !androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet;
            }
        }
        if ((dirtyFlags2 & 131264) == 0) {
            vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE;
            VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE1;
        } else {
            boolean vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse = androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsInitFinishedGet ? androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet : false;
            boolean VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse1 = androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsInitFinishedGet ? VmWeatherInfoIsLoadSuccess1 : false;
            if ((dirtyFlags2 & 131264) != 0) {
                if (vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse) {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                } else {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                }
            }
            if ((dirtyFlags2 & 131264) != 0) {
                if (VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse1) {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                } else {
                    dirtyFlags2 |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
            }
            VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12 = vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse ? 0 : 4;
            int vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE3 = VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalse1 ? 0 : 4;
            vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2 = vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE3;
        }
        if ((dirtyFlags2 & 134217728) == 0) {
            vmWeatherInfoPhraseGet2 = vmWeatherInfoPhraseGet;
        } else {
            if (vmWeatherInfo == null) {
                vmWeatherInfoPhraseGet2 = vmWeatherInfoPhraseGet;
                vmWeatherInfoCity = null;
            } else {
                vmWeatherInfoPhraseGet2 = vmWeatherInfoPhraseGet;
                vmWeatherInfoCity = vmWeatherInfo.city;
            }
            updateRegistration(5, vmWeatherInfoCity);
            if (vmWeatherInfoCity != null) {
                vmWeatherInfoCityGet = vmWeatherInfoCity.get();
            }
        }
        if ((dirtyFlags2 & 131168) == 0) {
            vmWeatherInfoIsLoadSuccessVmWeatherInfoCityMboundView15AndroidStringKswId8Weather = null;
        } else {
            vmWeatherInfoIsLoadSuccessVmWeatherInfoCityMboundView15AndroidStringKswId8Weather = androidDatabindingViewDataBindingSafeUnboxVmWeatherInfoIsLoadSuccessGet ? vmWeatherInfoCityGet : this.mboundView15.getResources().getString(C0899R.string.ksw_id8_weather);
        }
        if ((dirtyFlags2 & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            vmWeatherInfoTemperatureRangeGet2 = vmWeatherInfoTemperatureRangeGet;
            this.clWeather.setOnClickListener(this.mCallback359);
        } else {
            vmWeatherInfoTemperatureRangeGet2 = vmWeatherInfoTemperatureRangeGet;
        }
        if ((dirtyFlags2 & 131584) != 0) {
            ViewBindingAdapter.setBackground(this.ivIcon, vmWeatherInfoAudiImageIconGet);
        }
        if ((dirtyFlags2 & 131264) != 0) {
            this.ivIcon.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView15.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView3.setVisibility(vmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE2);
            this.mboundView7.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.mboundView8.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.temperatureTv.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
            this.unitWeather.setVisibility(VmWeatherInfoIsInitFinishedVmWeatherInfoIsLoadSuccessBooleanFalseViewVISIBLEViewINVISIBLE12);
        }
        if ((dirtyFlags2 & 196864) != 0) {
            this.mboundView0.setVisibility(vmLogoViewGet);
        }
        if ((dirtyFlags2 & 131080) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, vmWeatherInfoAudiCardDetailText1Get);
        }
        if ((dirtyFlags2 & 131076) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView11, vmWeatherInfoAudiCardDetailImg2Get);
        }
        if ((dirtyFlags2 & 163840) != 0) {
            TextViewBindingAdapter.setText(this.mboundView12, vmWeatherInfoAudiCardDetailText2Get);
        }
        if ((dirtyFlags2 & 135168) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView13, vmWeatherInfoAudiCardDetailImg3Get);
        }
        if ((dirtyFlags2 & 131088) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, vmWeatherInfoAudiCardDetailText3Get);
        }
        if ((dirtyFlags2 & 131168) != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, vmWeatherInfoIsLoadSuccessVmWeatherInfoCityMboundView15AndroidStringKswId8Weather);
        }
        if ((dirtyFlags2 & 147456) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, vmWeatherInfoErrorMessageGet);
        }
        if ((dirtyFlags2 & 132096) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, vmWeatherInfoTemperatureRangeGet2);
        }
        if ((dirtyFlags2 & 131074) != 0) {
            vmWeatherInfoPhraseGet3 = vmWeatherInfoPhraseGet2;
            TextViewBindingAdapter.setText(this.mboundView8, vmWeatherInfoPhraseGet3);
        } else {
            vmWeatherInfoPhraseGet3 = vmWeatherInfoPhraseGet2;
        }
        if ((dirtyFlags2 & 139264) != 0) {
            vmWeatherInfoAudiCardDetailImg1Get2 = vmWeatherInfoAudiCardDetailImg1Get;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView9, vmWeatherInfoAudiCardDetailImg1Get2);
        } else {
            vmWeatherInfoAudiCardDetailImg1Get2 = vmWeatherInfoAudiCardDetailImg1Get;
        }
        if ((dirtyFlags2 & 131073) != 0) {
            vmWeatherInfoTemperatureGet3 = vmWeatherInfoTemperatureGet2;
            TextViewBindingAdapter.setText(this.temperatureTv, vmWeatherInfoTemperatureGet3);
        } else {
            vmWeatherInfoTemperatureGet3 = vmWeatherInfoTemperatureGet2;
        }
        if ((dirtyFlags2 & 133120) != 0) {
            TextViewBindingAdapter.setText(this.unitWeather, vmWeatherInfoTemperUnitGet);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AudiViewModel vm = this.mVm;
        boolean vmJavaLangObjectNull = vm != null;
        if (vmJavaLangObjectNull) {
            vm.openWeatherApp(callbackArg_0);
        }
    }
}

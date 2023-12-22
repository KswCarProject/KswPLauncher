package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsSystemLayoutBindingImpl extends BmwId8SettingsSystemLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView10;
    private final TextView mboundView11;
    private final ImageView mboundView12;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final ImageView mboundView4;
    private final TextView mboundView5;
    private final ImageView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_scroll, 15);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_mirror, 16);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_motion, 17);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_lines, 18);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_radar, 19);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_mute, 20);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_camera, 21);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_brightness, 22);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_temp, 23);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_fuel, 24);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_music, 25);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_video, 26);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_system_framelay, 27);
    }

    public BmwId8SettingsSystemLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsSystemLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, (RelativeLayout) bindings[22], (RelativeLayout) bindings[21], (FrameLayout) bindings[27], (RelativeLayout) bindings[24], (ImageView) bindings[14], (ImageView) bindings[1], (RelativeLayout) bindings[18], (RelativeLayout) bindings[16], (RelativeLayout) bindings[17], (RelativeLayout) bindings[25], (RelativeLayout) bindings[20], (RelativeLayout) bindings[19], (ImageView) bindings[13], (ScrollView) bindings[15], (RelativeLayout) bindings[23], (RelativeLayout) bindings[26]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsSystemImg.setTag(null);
        this.bmwId8SettingsSystemLeftArrow.setTag(null);
        this.bmwId8SettingsSystemRightArrow.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[12];
        this.mboundView12 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[2];
        this.mboundView2 = imageView3;
        imageView3.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        ImageView imageView4 = (ImageView) bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        ImageView imageView5 = (ImageView) bindings[6];
        this.mboundView6 = imageView5;
        imageView5.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        ImageView imageView6 = (ImageView) bindings[8];
        this.mboundView8 = imageView6;
        imageView6.setTag(null);
        TextView textView5 = (TextView) bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
            setViewModel((BmwId8SettingsViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BmwId8SettingsSystemLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelParkMute((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelParkLines((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelParkRadar((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeViewModelSystemIconShow((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeViewModelSystemBgShow((ObservableBoolean) object, fieldId);
            case 5:
                return onChangeViewModelDisableVideo((ObservableBoolean) object, fieldId);
            case 6:
                return onChangeViewModelRearMirror((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelParkMute(ObservableBoolean ViewModelParkMute, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelParkLines(ObservableBoolean ViewModelParkLines, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelParkRadar(ObservableBoolean ViewModelParkRadar, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSystemIconShow(ObservableBoolean ViewModelSystemIconShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSystemBgShow(ObservableBoolean ViewModelSystemBgShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelDisableVideo(ObservableBoolean ViewModelDisableVideo, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelRearMirror(ObservableBoolean ViewModelRearMirror, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        Drawable viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff;
        String viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff;
        Drawable viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff;
        Drawable viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff;
        int viewModelSystemBgShowViewGONEViewVISIBLE;
        String viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff;
        Drawable viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff;
        int viewModelSystemIconShowViewVISIBLEViewGONE;
        int viewModelSystemIconShowViewVISIBLEViewGONE2;
        int viewModelSystemIconShowViewVISIBLEViewGONE3;
        ObservableBoolean viewModelRearMirror;
        String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff;
        Drawable drawable;
        ObservableBoolean viewModelDisableVideo;
        ObservableBoolean viewModelSystemBgShow;
        ObservableBoolean viewModelSystemIconShow;
        ObservableBoolean viewModelParkRadar;
        ObservableBoolean viewModelParkLines;
        long dirtyFlags2;
        Context context;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2 = null;
        int viewModelSystemBgShowViewVISIBLEViewGONE = 0;
        boolean viewModelRearMirrorGet = false;
        Drawable viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2 = null;
        boolean viewModelSystemIconShowGet = false;
        boolean viewModelParkMuteGet = false;
        ObservableBoolean viewModelParkMute = null;
        String viewModelParkRadarMboundView9AndroidStringOnMboundView9AndroidStringOff = null;
        Drawable viewModelParkMuteMboundView12AndroidDrawableId8SettingsSystemOnMboundView12AndroidDrawableId8SettingsSystemOff = null;
        boolean viewModelDisableVideoGet = false;
        String viewModelParkLinesMboundView7AndroidStringOnMboundView7AndroidStringOff = null;
        Drawable viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2 = null;
        String viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2 = null;
        boolean viewModelParkLinesGet = false;
        boolean viewModelSystemBgShowGet = false;
        Drawable viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2 = null;
        int viewModelSystemBgShowViewGONEViewVISIBLE2 = 0;
        String viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2 = null;
        boolean viewModelParkRadarGet = false;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        int viewModelSystemIconShowViewVISIBLEViewGONE4 = 0;
        if ((dirtyFlags & 511) == 0) {
            viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = null;
            viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = null;
            viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = null;
            viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = null;
            viewModelSystemBgShowViewGONEViewVISIBLE = 0;
            viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = null;
        } else {
            if ((dirtyFlags & 385) != 0) {
                if (viewModel != null) {
                    viewModelParkMute = viewModel.parkMute;
                }
                updateRegistration(0, viewModelParkMute);
                if (viewModelParkMute != null) {
                    viewModelParkMuteGet = viewModelParkMute.get();
                }
                if ((dirtyFlags & 385) != 0) {
                    if (viewModelParkMuteGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE | 4294967296L;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | 2147483648L;
                    }
                }
                if (viewModelParkMuteGet) {
                    context = this.mboundView12.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.C0900drawable.id8_settings_system_on;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView12.getContext();
                    i = C0899R.C0900drawable.id8_settings_system_off;
                }
                viewModelParkMuteMboundView12AndroidDrawableId8SettingsSystemOnMboundView12AndroidDrawableId8SettingsSystemOff = AppCompatResources.getDrawable(context, i);
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2 = this.mboundView11.getResources().getString(viewModelParkMuteGet ? C0899R.string.on : C0899R.string.off);
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 386) == 0) {
                viewModelSystemIconShowViewVISIBLEViewGONE = 0;
            } else {
                if (viewModel == null) {
                    viewModelParkLines = null;
                } else {
                    viewModelParkLines = viewModel.parkLines;
                }
                viewModelSystemIconShowViewVISIBLEViewGONE = 0;
                updateRegistration(1, viewModelParkLines);
                if (viewModelParkLines != null) {
                    viewModelParkLinesGet = viewModelParkLines.get();
                }
                if ((dirtyFlags & 386) != 0) {
                    if (viewModelParkLinesGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2 = viewModelParkLinesGet ? AppCompatResources.getDrawable(this.mboundView8.getContext(), C0899R.C0900drawable.id8_settings_system_on) : AppCompatResources.getDrawable(this.mboundView8.getContext(), C0899R.C0900drawable.id8_settings_system_off);
                viewModelParkLinesMboundView7AndroidStringOnMboundView7AndroidStringOff = this.mboundView7.getResources().getString(viewModelParkLinesGet ? C0899R.string.on : C0899R.string.off);
            }
            if ((dirtyFlags & 388) != 0) {
                if (viewModel == null) {
                    viewModelParkRadar = null;
                } else {
                    viewModelParkRadar = viewModel.parkRadar;
                }
                updateRegistration(2, viewModelParkRadar);
                if (viewModelParkRadar != null) {
                    viewModelParkRadarGet = viewModelParkRadar.get();
                }
                if ((dirtyFlags & 388) != 0) {
                    if (viewModelParkRadarGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH | 268435456;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID | 134217728;
                    }
                }
                viewModelParkRadarMboundView9AndroidStringOnMboundView9AndroidStringOff = viewModelParkRadarGet ? this.mboundView9.getResources().getString(C0899R.string.on) : this.mboundView9.getResources().getString(C0899R.string.off);
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2 = AppCompatResources.getDrawable(this.mboundView10.getContext(), viewModelParkRadarGet ? C0899R.C0900drawable.id8_settings_system_on : C0899R.C0900drawable.id8_settings_system_off);
            }
            if ((dirtyFlags & 392) == 0) {
                viewModelSystemIconShowViewVISIBLEViewGONE2 = viewModelSystemIconShowViewVISIBLEViewGONE;
            } else {
                if (viewModel == null) {
                    viewModelSystemIconShow = null;
                } else {
                    viewModelSystemIconShow = viewModel.systemIconShow;
                }
                updateRegistration(3, viewModelSystemIconShow);
                if (viewModelSystemIconShow != null) {
                    viewModelSystemIconShowGet = viewModelSystemIconShow.get();
                }
                if ((dirtyFlags & 392) != 0) {
                    if (viewModelSystemIconShowGet) {
                        dirtyFlags |= 17179869184L;
                    } else {
                        dirtyFlags |= 8589934592L;
                    }
                }
                viewModelSystemIconShowViewVISIBLEViewGONE2 = viewModelSystemIconShowGet ? 0 : 8;
            }
            if ((dirtyFlags & 400) == 0) {
                viewModelSystemIconShowViewVISIBLEViewGONE3 = viewModelSystemIconShowViewVISIBLEViewGONE2;
            } else {
                if (viewModel == null) {
                    viewModelSystemBgShow = null;
                } else {
                    viewModelSystemBgShow = viewModel.systemBgShow;
                }
                viewModelSystemIconShowViewVISIBLEViewGONE3 = viewModelSystemIconShowViewVISIBLEViewGONE2;
                updateRegistration(4, viewModelSystemBgShow);
                if (viewModelSystemBgShow != null) {
                    viewModelSystemBgShowGet = viewModelSystemBgShow.get();
                }
                if ((dirtyFlags & 400) != 0) {
                    if (viewModelSystemBgShowGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | 1073741824;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | 536870912;
                    }
                }
                viewModelSystemBgShowViewVISIBLEViewGONE = viewModelSystemBgShowGet ? 0 : 8;
                viewModelSystemBgShowViewGONEViewVISIBLE2 = viewModelSystemBgShowGet ? 8 : 0;
            }
            if ((dirtyFlags & 416) != 0) {
                if (viewModel == null) {
                    viewModelDisableVideo = null;
                } else {
                    viewModelDisableVideo = viewModel.disableVideo;
                }
                updateRegistration(5, viewModelDisableVideo);
                if (viewModelDisableVideo != null) {
                    viewModelDisableVideoGet = viewModelDisableVideo.get();
                }
                if ((dirtyFlags & 416) != 0) {
                    if (viewModelDisableVideoGet) {
                        dirtyFlags = dirtyFlags | 4194304 | 16777216;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE | 8388608;
                    }
                }
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2 = viewModelDisableVideoGet ? AppCompatResources.getDrawable(this.mboundView6.getContext(), C0899R.C0900drawable.id8_settings_system_on) : AppCompatResources.getDrawable(this.mboundView6.getContext(), C0899R.C0900drawable.id8_settings_system_off);
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2 = this.mboundView5.getResources().getString(viewModelDisableVideoGet ? C0899R.string.on : C0899R.string.off);
            }
            if ((dirtyFlags & 448) == 0) {
                viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2 = null;
                viewModelSystemIconShowViewVISIBLEViewGONE4 = viewModelSystemIconShowViewVISIBLEViewGONE3;
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2;
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2;
                viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = null;
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2;
                viewModelSystemBgShowViewGONEViewVISIBLE = viewModelSystemBgShowViewGONEViewVISIBLE2;
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2;
            } else {
                if (viewModel == null) {
                    viewModelRearMirror = null;
                } else {
                    viewModelRearMirror = viewModel.rearMirror;
                }
                updateRegistration(6, viewModelRearMirror);
                if (viewModelRearMirror != null) {
                    viewModelRearMirrorGet = viewModelRearMirror.get();
                }
                if ((dirtyFlags & 448) != 0) {
                    if (viewModelRearMirrorGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | 67108864;
                    } else {
                        dirtyFlags = dirtyFlags | 512 | 33554432;
                    }
                }
                String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3 = viewModelRearMirrorGet ? this.mboundView3.getResources().getString(C0899R.string.on) : this.mboundView3.getResources().getString(C0899R.string.off);
                if (viewModelRearMirrorGet) {
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), C0899R.C0900drawable.id8_settings_system_on);
                } else {
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), C0899R.C0900drawable.id8_settings_system_off);
                }
                Drawable viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff2 = drawable;
                viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2 = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff;
                viewModelSystemIconShowViewVISIBLEViewGONE4 = viewModelSystemIconShowViewVISIBLEViewGONE3;
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2;
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2;
                viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff2;
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2;
                viewModelSystemBgShowViewGONEViewVISIBLE = viewModelSystemBgShowViewGONEViewVISIBLE2;
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2;
            }
        }
        if ((dirtyFlags & 392) == 0) {
            viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff = viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2;
        } else {
            viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff = viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2;
            this.bmwId8SettingsSystemImg.setVisibility(viewModelSystemIconShowViewVISIBLEViewGONE4);
        }
        if ((dirtyFlags & 400) != 0) {
            this.bmwId8SettingsSystemLeftArrow.setVisibility(viewModelSystemBgShowViewVISIBLEViewGONE);
            this.bmwId8SettingsSystemRightArrow.setVisibility(viewModelSystemBgShowViewGONEViewVISIBLE);
            this.mboundView2.setVisibility(viewModelSystemBgShowViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 388) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView10, viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff);
            TextViewBindingAdapter.setText(this.mboundView9, viewModelParkRadarMboundView9AndroidStringOnMboundView9AndroidStringOff);
        }
        if ((dirtyFlags & 385) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView12, viewModelParkMuteMboundView12AndroidDrawableId8SettingsSystemOnMboundView12AndroidDrawableId8SettingsSystemOff);
        }
        if ((dirtyFlags & 448) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff);
        }
        if ((dirtyFlags & 416) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView6, viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff);
        }
        if ((dirtyFlags & 386) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, viewModelParkLinesMboundView7AndroidStringOnMboundView7AndroidStringOff);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff);
        }
    }
}

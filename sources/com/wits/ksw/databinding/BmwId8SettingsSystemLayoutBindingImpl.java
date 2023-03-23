package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

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
        sparseIntArray.put(R.id.bmw_id8_settings_system_scroll, 15);
        sparseIntArray.put(R.id.bmw_id8_settings_system_mirror, 16);
        sparseIntArray.put(R.id.bmw_id8_settings_system_motion, 17);
        sparseIntArray.put(R.id.bmw_id8_settings_system_lines, 18);
        sparseIntArray.put(R.id.bmw_id8_settings_system_radar, 19);
        sparseIntArray.put(R.id.bmw_id8_settings_system_mute, 20);
        sparseIntArray.put(R.id.bmw_id8_settings_system_camera, 21);
        sparseIntArray.put(R.id.bmw_id8_settings_system_brightness, 22);
        sparseIntArray.put(R.id.bmw_id8_settings_system_temp, 23);
        sparseIntArray.put(R.id.bmw_id8_settings_system_fuel, 24);
        sparseIntArray.put(R.id.bmw_id8_settings_system_music, 25);
        sparseIntArray.put(R.id.bmw_id8_settings_system_video, 26);
        sparseIntArray.put(R.id.bmw_id8_settings_system_framelay, 27);
    }

    public BmwId8SettingsSystemLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsSystemLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, bindings[22], bindings[21], bindings[27], bindings[24], bindings[14], bindings[1], bindings[18], bindings[16], bindings[17], bindings[25], bindings[20], bindings[19], bindings[13], bindings[15], bindings[23], bindings[26]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsSystemImg.setTag((Object) null);
        this.bmwId8SettingsSystemLeftArrow.setTag((Object) null);
        this.bmwId8SettingsSystemRightArrow.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag((Object) null);
        TextView textView = bindings[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        ImageView imageView2 = bindings[12];
        this.mboundView12 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[2];
        this.mboundView2 = imageView3;
        imageView3.setTag((Object) null);
        TextView textView2 = bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag((Object) null);
        ImageView imageView4 = bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag((Object) null);
        TextView textView3 = bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag((Object) null);
        ImageView imageView5 = bindings[6];
        this.mboundView6 = imageView5;
        imageView5.setTag((Object) null);
        TextView textView4 = bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag((Object) null);
        ImageView imageView6 = bindings[8];
        this.mboundView8 = imageView6;
        imageView6.setTag((Object) null);
        TextView textView5 = bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
        setViewModel((BmwId8SettingsViewModel) variable);
        return true;
    }

    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelParkLines(ObservableBoolean ViewModelParkLines, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelParkRadar(ObservableBoolean ViewModelParkRadar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelSystemIconShow(ObservableBoolean ViewModelSystemIconShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelSystemBgShow(ObservableBoolean ViewModelSystemBgShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelDisableVideo(ObservableBoolean ViewModelDisableVideo, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelRearMirror(ObservableBoolean ViewModelRearMirror, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff;
        int viewModelSystemBgShowViewGONEViewVISIBLE;
        Drawable viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff;
        Drawable viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff;
        String viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff;
        Drawable viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff;
        Drawable viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff;
        int viewModelSystemIconShowViewVISIBLEViewGONE;
        int viewModelSystemIconShowViewVISIBLEViewGONE2;
        int viewModelSystemIconShowViewVISIBLEViewGONE3;
        ObservableBoolean viewModelRearMirror;
        String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff;
        String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2;
        Drawable drawable;
        ObservableBoolean viewModelDisableVideo;
        Drawable drawable2;
        ObservableBoolean viewModelSystemBgShow;
        ObservableBoolean viewModelSystemIconShow;
        ObservableBoolean viewModelParkRadar;
        String str;
        ObservableBoolean viewModelParkLines;
        Drawable drawable3;
        long dirtyFlags2;
        int i;
        Context context;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3 = null;
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
        if ((dirtyFlags & 511) != 0) {
            if ((dirtyFlags & 385) != 0) {
                if (viewModel != null) {
                    viewModelParkMute = viewModel.parkMute;
                }
                updateRegistration(0, (Observable) viewModelParkMute);
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
                    i = R.drawable.id8_settings_system_on;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView12.getContext();
                    i = R.drawable.id8_settings_system_off;
                }
                viewModelParkMuteMboundView12AndroidDrawableId8SettingsSystemOnMboundView12AndroidDrawableId8SettingsSystemOff = AppCompatResources.getDrawable(context, i);
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2 = this.mboundView11.getResources().getString(viewModelParkMuteGet ? R.string.on : R.string.off);
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 386) != 0) {
                if (viewModel != null) {
                    viewModelParkLines = viewModel.parkLines;
                } else {
                    viewModelParkLines = null;
                }
                viewModelSystemIconShowViewVISIBLEViewGONE = 0;
                updateRegistration(1, (Observable) viewModelParkLines);
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
                if (viewModelParkLinesGet) {
                    ObservableBoolean observableBoolean = viewModelParkLines;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView8.getContext(), R.drawable.id8_settings_system_on);
                } else {
                    drawable3 = AppCompatResources.getDrawable(this.mboundView8.getContext(), R.drawable.id8_settings_system_off);
                }
                viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2 = drawable3;
                viewModelParkLinesMboundView7AndroidStringOnMboundView7AndroidStringOff = this.mboundView7.getResources().getString(viewModelParkLinesGet ? R.string.on : R.string.off);
            } else {
                viewModelSystemIconShowViewVISIBLEViewGONE = 0;
            }
            if ((dirtyFlags & 388) != 0) {
                if (viewModel != null) {
                    viewModelParkRadar = viewModel.parkRadar;
                } else {
                    viewModelParkRadar = null;
                }
                updateRegistration(2, (Observable) viewModelParkRadar);
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
                if (viewModelParkRadarGet) {
                    ObservableBoolean observableBoolean2 = viewModelParkRadar;
                    str = this.mboundView9.getResources().getString(R.string.on);
                } else {
                    str = this.mboundView9.getResources().getString(R.string.off);
                }
                viewModelParkRadarMboundView9AndroidStringOnMboundView9AndroidStringOff = str;
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2 = AppCompatResources.getDrawable(this.mboundView10.getContext(), viewModelParkRadarGet ? R.drawable.id8_settings_system_on : R.drawable.id8_settings_system_off);
            }
            if ((dirtyFlags & 392) != 0) {
                if (viewModel != null) {
                    viewModelSystemIconShow = viewModel.systemIconShow;
                } else {
                    viewModelSystemIconShow = null;
                }
                updateRegistration(3, (Observable) viewModelSystemIconShow);
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
                ObservableBoolean observableBoolean3 = viewModelSystemIconShow;
            } else {
                viewModelSystemIconShowViewVISIBLEViewGONE2 = viewModelSystemIconShowViewVISIBLEViewGONE;
            }
            if ((dirtyFlags & 400) != 0) {
                if (viewModel != null) {
                    viewModelSystemBgShow = viewModel.systemBgShow;
                } else {
                    viewModelSystemBgShow = null;
                }
                viewModelSystemIconShowViewVISIBLEViewGONE3 = viewModelSystemIconShowViewVISIBLEViewGONE2;
                updateRegistration(4, (Observable) viewModelSystemBgShow);
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
                ObservableBoolean observableBoolean4 = viewModelSystemBgShow;
            } else {
                viewModelSystemIconShowViewVISIBLEViewGONE3 = viewModelSystemIconShowViewVISIBLEViewGONE2;
            }
            if ((dirtyFlags & 416) != 0) {
                if (viewModel != null) {
                    viewModelDisableVideo = viewModel.disableVideo;
                } else {
                    viewModelDisableVideo = null;
                }
                updateRegistration(5, (Observable) viewModelDisableVideo);
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
                if (viewModelDisableVideoGet) {
                    ObservableBoolean observableBoolean5 = viewModelDisableVideo;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView6.getContext(), R.drawable.id8_settings_system_on);
                } else {
                    drawable2 = AppCompatResources.getDrawable(this.mboundView6.getContext(), R.drawable.id8_settings_system_off);
                }
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2 = drawable2;
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2 = this.mboundView5.getResources().getString(viewModelDisableVideoGet ? R.string.on : R.string.off);
            }
            if ((dirtyFlags & 448) != 0) {
                if (viewModel != null) {
                    viewModelRearMirror = viewModel.rearMirror;
                } else {
                    viewModelRearMirror = null;
                }
                updateRegistration(6, (Observable) viewModelRearMirror);
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
                if (viewModelRearMirrorGet) {
                    ObservableBoolean observableBoolean6 = viewModelRearMirror;
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff = this.mboundView3.getResources().getString(R.string.on);
                } else {
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff = this.mboundView3.getResources().getString(R.string.off);
                }
                if (viewModelRearMirrorGet) {
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2 = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), R.drawable.id8_settings_system_on);
                } else {
                    viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2 = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff;
                    drawable = AppCompatResources.getDrawable(this.mboundView4.getContext(), R.drawable.id8_settings_system_off);
                }
                Drawable viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff2 = drawable;
                viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3 = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff2;
                viewModelSystemIconShowViewVISIBLEViewGONE4 = viewModelSystemIconShowViewVISIBLEViewGONE3;
                Drawable drawable4 = viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2;
                BmwId8SettingsViewModel bmwId8SettingsViewModel = viewModel;
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = drawable4;
                String str2 = viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2;
                boolean z = viewModelRearMirrorGet;
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = str2;
                Drawable drawable5 = viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff2;
                boolean z2 = viewModelSystemIconShowGet;
                viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = drawable5;
                Drawable drawable6 = viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2;
                boolean z3 = viewModelParkMuteGet;
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = drawable6;
                int i2 = viewModelSystemBgShowViewGONEViewVISIBLE2;
                ObservableBoolean observableBoolean7 = viewModelParkMute;
                viewModelSystemBgShowViewGONEViewVISIBLE = i2;
                String str3 = viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2;
                boolean z4 = viewModelDisableVideoGet;
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = str3;
            } else {
                viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3 = null;
                viewModelSystemIconShowViewVISIBLEViewGONE4 = viewModelSystemIconShowViewVISIBLEViewGONE3;
                Drawable drawable7 = viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff2;
                BmwId8SettingsViewModel bmwId8SettingsViewModel2 = viewModel;
                viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = drawable7;
                viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff2;
                boolean z5 = viewModelSystemIconShowGet;
                viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = null;
                Drawable drawable8 = viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff2;
                boolean z6 = viewModelParkMuteGet;
                viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = drawable8;
                int i3 = viewModelSystemBgShowViewGONEViewVISIBLE2;
                ObservableBoolean observableBoolean8 = viewModelParkMute;
                viewModelSystemBgShowViewGONEViewVISIBLE = i3;
                String str4 = viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff2;
                boolean z7 = viewModelDisableVideoGet;
                viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = str4;
            }
        } else {
            BmwId8SettingsViewModel bmwId8SettingsViewModel3 = viewModel;
            viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff = null;
            viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff = null;
            viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff = null;
            viewModelParkRadarMboundView10AndroidDrawableId8SettingsSystemOnMboundView10AndroidDrawableId8SettingsSystemOff = null;
            viewModelSystemBgShowViewGONEViewVISIBLE = 0;
            viewModelParkMuteMboundView11AndroidStringOnMboundView11AndroidStringOff = null;
        }
        if ((dirtyFlags & 392) != 0) {
            viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff = viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2;
            this.bmwId8SettingsSystemImg.setVisibility(viewModelSystemIconShowViewVISIBLEViewGONE4);
        } else {
            viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff = viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff2;
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
            TextViewBindingAdapter.setText(this.mboundView3, viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView4, viewModelRearMirrorMboundView4AndroidDrawableId8SettingsSystemOnMboundView4AndroidDrawableId8SettingsSystemOff);
        }
        if ((dirtyFlags & 416) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, viewModelDisableVideoMboundView5AndroidStringOnMboundView5AndroidStringOff);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView6, viewModelDisableVideoMboundView6AndroidDrawableId8SettingsSystemOnMboundView6AndroidDrawableId8SettingsSystemOff);
        }
        if ((dirtyFlags & 386) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, viewModelParkLinesMboundView7AndroidStringOnMboundView7AndroidStringOff);
            String str5 = viewModelRearMirrorMboundView3AndroidStringOnMboundView3AndroidStringOff3;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff);
            return;
        }
        Drawable drawable9 = viewModelParkLinesMboundView8AndroidDrawableId8SettingsSystemOnMboundView8AndroidDrawableId8SettingsSystemOff;
    }
}

package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsAudioSoundLayoutBindingImpl extends BmwId8SettingsAudioSoundLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final LinearLayout mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView13;
    private final TextView mboundView15;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ScrollView mboundView4;
    private final ImageView mboundView5;
    private final ImageView mboundView6;
    private final ImageView mboundView7;
    private final ImageView mboundView8;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_lay, 17);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_user, 18);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_pop, 19);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_class, 20);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_rock, 21);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_jazz, 22);
        sparseIntArray.put(R.id.bmw_id8_settings_audio_sound_dance, 23);
        sparseIntArray.put(R.id.bmw_id8_settings_bass_sub_btn, 24);
        sparseIntArray.put(R.id.bmw_id8_settings_bass_add_btn, 25);
        sparseIntArray.put(R.id.bmw_id8_settings_mid_sub_btn, 26);
        sparseIntArray.put(R.id.bmw_id8_settings_mid_add_btn, 27);
        sparseIntArray.put(R.id.bmw_id8_settings_tre_sub_btn, 28);
        sparseIntArray.put(R.id.bmw_id8_settings_tre_add_btn, 29);
    }

    public BmwId8SettingsAudioSoundLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsAudioSoundLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, bindings[12], bindings[14], bindings[20], bindings[23], bindings[22], bindings[17], bindings[19], bindings[21], bindings[18], bindings[16], bindings[25], bindings[24], bindings[27], bindings[26], bindings[29], bindings[28]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsAudioLow.setTag((Object) null);
        this.bmwId8SettingsAudioMid.setTag((Object) null);
        this.bmwId8SettingsAudioTre.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        LinearLayout linearLayout = bindings[10];
        this.mboundView10 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView = bindings[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag((Object) null);
        ImageView imageView2 = bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag((Object) null);
        ScrollView scrollView = bindings[4];
        this.mboundView4 = scrollView;
        scrollView.setTag((Object) null);
        ImageView imageView4 = bindings[5];
        this.mboundView5 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = bindings[6];
        this.mboundView6 = imageView5;
        imageView5.setTag((Object) null);
        ImageView imageView6 = bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag((Object) null);
        ImageView imageView7 = bindings[8];
        this.mboundView8 = imageView7;
        imageView7.setTag((Object) null);
        ImageView imageView8 = bindings[9];
        this.mboundView9 = imageView8;
        imageView8.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
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
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelBassVolume((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelUserTypeShow((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelMidVolume((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelMidVolumeStr((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelTreVolumeStr((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelTreVolume((ObservableInt) object, fieldId);
            case 6:
                return onChangeViewModelEqType((ObservableInt) object, fieldId);
            case 7:
                return onChangeViewModelBassVolumeStr((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelAudioBgShow((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelBassVolume(ObservableInt ViewModelBassVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelUserTypeShow(ObservableBoolean ViewModelUserTypeShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMidVolume(ObservableInt ViewModelMidVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMidVolumeStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelTreVolumeStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelTreVolume(ObservableInt ViewModelTreVolume, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelEqType(ObservableInt ViewModelEqType, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelBassVolumeStr(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelAudioBgShow(ObservableBoolean ViewModelAudioBgShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
        String viewModelBassVolumeStrGet;
        int viewModelMidVolumeGet;
        Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN;
        int viewModelTreVolumeGet;
        int viewModelAudioBgShowViewGONEViewVISIBLE;
        int viewModelBassVolumeGet;
        int viewModelUserTypeShowViewVISIBLEViewGONE;
        Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
        String viewModelTreVolumeStrGet;
        Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2;
        ObservableBoolean viewModelAudioBgShow;
        ObservableInt viewModelBassVolume;
        ObservableInt viewModelBassVolume2;
        int viewModelUserTypeShowViewGONEViewVISIBLE;
        ObservableBoolean viewModelUserTypeShow;
        ObservableBoolean viewModelAudioBgShow2;
        ObservableField<String> viewModelBassVolumeStr;
        ObservableInt viewModelEqType;
        int viewModelEqTypeGet;
        boolean viewModelEqTypeInt2;
        Drawable drawable;
        Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2;
        Drawable drawable2;
        Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2;
        Drawable drawable3;
        Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2;
        ObservableInt viewModelTreVolume;
        ObservableField<String> viewModelTreVolumeStr;
        Drawable viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableBoolean viewModelUserTypeShow2 = null;
        int viewModelEqTypeInt3 = false;
        int viewModelUserTypeShowViewGONEViewVISIBLE2 = 0;
        ObservableInt viewModelMidVolume = null;
        Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3 = null;
        Drawable viewModelEqTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
        Drawable viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = null;
        ObservableField<String> viewModelMidVolumeStr = null;
        boolean viewModelEqTypeInt4 = false;
        String viewModelMidVolumeStrGet = null;
        String viewModelTreVolumeStrGet2 = null;
        Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3 = null;
        int viewModelUserTypeShowViewVISIBLEViewGONE2 = 0;
        int viewModelBassVolumeGet2 = 0;
        boolean viewModelUserTypeShowGet = false;
        boolean viewModelAudioBgShowGet = false;
        int viewModelTreVolumeGet2 = 0;
        int viewModelMidVolumeGet2 = 0;
        String viewModelBassVolumeStrGet2 = null;
        Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3 = null;
        Drawable viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2 = null;
        Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4 = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 2047) != 0) {
            if ((dirtyFlags & 1537) != 0) {
                if (viewModel != null) {
                    viewModelBassVolume = viewModel.bassVolume;
                } else {
                    viewModelBassVolume = null;
                }
                viewModelAudioBgShow = null;
                updateRegistration(0, (Observable) viewModelBassVolume);
                if (viewModelBassVolume != null) {
                    viewModelBassVolumeGet2 = viewModelBassVolume.get();
                }
            } else {
                viewModelAudioBgShow = null;
                viewModelBassVolume = null;
            }
            if ((dirtyFlags & 1538) != 0) {
                if (viewModel != null) {
                    viewModelUserTypeShow2 = viewModel.userTypeShow;
                }
                updateRegistration(1, (Observable) viewModelUserTypeShow2);
                if (viewModelUserTypeShow2 != null) {
                    viewModelUserTypeShowGet = viewModelUserTypeShow2.get();
                }
                if ((dirtyFlags & 1538) != 0) {
                    if (viewModelUserTypeShowGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM | PlaybackStateCompat.ACTION_SET_REPEAT_MODE | 4194304;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    }
                }
                viewModelUserTypeShowViewGONEViewVISIBLE2 = viewModelUserTypeShowGet ? 8 : 0;
                if (viewModelUserTypeShowGet) {
                    viewModelBassVolume2 = viewModelBassVolume;
                    viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = AppCompatResources.getDrawable(this.mboundView3.getContext(), R.drawable.id8_settings_time_up);
                } else {
                    viewModelBassVolume2 = viewModelBassVolume;
                    viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = AppCompatResources.getDrawable(this.mboundView3.getContext(), R.drawable.id8_settings_time_down);
                }
                viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
                viewModelUserTypeShowViewVISIBLEViewGONE2 = viewModelUserTypeShowGet ? 0 : 8;
            } else {
                viewModelBassVolume2 = viewModelBassVolume;
            }
            if ((dirtyFlags & 1540) != 0) {
                if (viewModel != null) {
                    viewModelMidVolume = viewModel.midVolume;
                }
                updateRegistration(2, (Observable) viewModelMidVolume);
                if (viewModelMidVolume != null) {
                    viewModelMidVolumeGet2 = viewModelMidVolume.get();
                }
            }
            if ((dirtyFlags & 1544) != 0) {
                if (viewModel != null) {
                    viewModelMidVolumeStr = viewModel.midVolumeStr;
                }
                updateRegistration(3, (Observable) viewModelMidVolumeStr);
                if (viewModelMidVolumeStr != null) {
                    viewModelMidVolumeStrGet = viewModelMidVolumeStr.get();
                }
            }
            if ((dirtyFlags & 1552) != 0) {
                if (viewModel != null) {
                    viewModelTreVolumeStr = viewModel.treVolumeStr;
                } else {
                    viewModelTreVolumeStr = null;
                }
                updateRegistration(4, (Observable) viewModelTreVolumeStr);
                if (viewModelTreVolumeStr != null) {
                    viewModelTreVolumeStrGet2 = viewModelTreVolumeStr.get();
                    ObservableField<String> observableField = viewModelTreVolumeStr;
                } else {
                    ObservableField<String> observableField2 = viewModelTreVolumeStr;
                }
            }
            if ((dirtyFlags & 1568) != 0) {
                if (viewModel != null) {
                    viewModelTreVolume = viewModel.treVolume;
                } else {
                    viewModelTreVolume = null;
                }
                updateRegistration(5, (Observable) viewModelTreVolume);
                if (viewModelTreVolume != null) {
                    viewModelTreVolumeGet2 = viewModelTreVolume.get();
                    ObservableInt observableInt = viewModelTreVolume;
                } else {
                    ObservableInt observableInt2 = viewModelTreVolume;
                }
            }
            if ((dirtyFlags & 1600) != 0) {
                if (viewModel != null) {
                    viewModelEqType = viewModel.eqType;
                } else {
                    viewModelEqType = null;
                }
                updateRegistration(6, (Observable) viewModelEqType);
                if (viewModelEqType != null) {
                    viewModelEqTypeGet = viewModelEqType.get();
                } else {
                    viewModelEqTypeGet = 0;
                }
                ObservableInt observableInt3 = viewModelEqType;
                boolean viewModelEqTypeInt22 = viewModelEqTypeGet == 2;
                boolean viewModelEqTypeInt32 = viewModelEqTypeGet == 3;
                boolean viewModelEqTypeInt42 = viewModelEqTypeGet == 4;
                boolean viewModelEqTypeInt0 = viewModelEqTypeGet == 0;
                viewModelUserTypeShow = viewModelUserTypeShow2;
                boolean viewModelEqTypeInt5 = viewModelEqTypeGet == 5;
                viewModelUserTypeShowViewGONEViewVISIBLE = viewModelUserTypeShowViewGONEViewVISIBLE2;
                boolean viewModelEqTypeInt1 = true;
                if (viewModelEqTypeGet != 1) {
                    viewModelEqTypeInt1 = false;
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt22) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    }
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt32) {
                        dirtyFlags |= 67108864;
                    } else {
                        dirtyFlags |= 33554432;
                    }
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt42) {
                        dirtyFlags |= 1073741824;
                    } else {
                        dirtyFlags |= 536870912;
                    }
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt0) {
                        dirtyFlags |= 268435456;
                    } else {
                        dirtyFlags |= 134217728;
                    }
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt5) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                if ((dirtyFlags & 1600) != 0) {
                    if (viewModelEqTypeInt1) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                long dirtyFlags2 = dirtyFlags;
                int i = R.drawable.id8_settings_system_select_sel;
                Context context = this.mboundView6.getContext();
                if (!viewModelEqTypeInt22) {
                    i = R.drawable.id8_settings_system_select_n;
                }
                Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN3 = AppCompatResources.getDrawable(context, i);
                if (viewModelEqTypeInt32) {
                    viewModelEqTypeInt2 = viewModelEqTypeInt22;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), R.drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt2 = viewModelEqTypeInt22;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), R.drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4 = drawable;
                if (viewModelEqTypeInt42) {
                    viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView8.getContext(), R.drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView8.getContext(), R.drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5 = drawable2;
                if (viewModelEqTypeInt0) {
                    viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView2.getContext(), R.drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView2.getContext(), R.drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3 = drawable3;
                if (viewModelEqTypeInt5) {
                    viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2 = AppCompatResources.getDrawable(this.mboundView9.getContext(), R.drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2 = AppCompatResources.getDrawable(this.mboundView9.getContext(), R.drawable.id8_settings_system_select_n);
                }
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3 = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView5.getContext(), viewModelEqTypeInt1 ? R.drawable.id8_settings_system_select_sel : R.drawable.id8_settings_system_select_n);
                boolean z = viewModelEqTypeInt5;
                boolean z2 = viewModelEqTypeInt1;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN3;
                dirtyFlags = dirtyFlags2;
                boolean z3 = viewModelEqTypeInt42;
                boolean z4 = viewModelEqTypeInt0;
                int i2 = viewModelEqTypeGet;
                viewModelEqTypeInt4 = viewModelEqTypeInt32;
                viewModelEqTypeInt3 = viewModelEqTypeInt2;
            } else {
                viewModelUserTypeShow = viewModelUserTypeShow2;
                viewModelUserTypeShowViewGONEViewVISIBLE = viewModelUserTypeShowViewGONEViewVISIBLE2;
            }
            if ((dirtyFlags & 1664) != 0) {
                if (viewModel != null) {
                    viewModelBassVolumeStr = viewModel.bassVolumeStr;
                } else {
                    viewModelBassVolumeStr = null;
                }
                updateRegistration(7, (Observable) viewModelBassVolumeStr);
                if (viewModelBassVolumeStr != null) {
                    ObservableField<String> observableField3 = viewModelBassVolumeStr;
                    viewModelBassVolumeStrGet2 = viewModelBassVolumeStr.get();
                } else {
                    ObservableField<String> observableField4 = viewModelBassVolumeStr;
                }
            }
            if ((dirtyFlags & 1792) != 0) {
                if (viewModel != null) {
                    viewModelAudioBgShow2 = viewModel.audioBgShow;
                } else {
                    viewModelAudioBgShow2 = viewModelAudioBgShow;
                }
                int i3 = 8;
                updateRegistration(8, (Observable) viewModelAudioBgShow2);
                if (viewModelAudioBgShow2 != null) {
                    viewModelAudioBgShowGet = viewModelAudioBgShow2.get();
                }
                if ((dirtyFlags & 1792) != 0) {
                    if (viewModelAudioBgShowGet) {
                        dirtyFlags |= 16777216;
                    } else {
                        dirtyFlags |= 8388608;
                    }
                }
                if (!viewModelAudioBgShowGet) {
                    i3 = 0;
                }
                int viewModelAudioBgShowViewGONEViewVISIBLE2 = i3;
                viewModelTreVolumeStrGet = viewModelTreVolumeStrGet2;
                viewModelBassVolumeGet = viewModelBassVolumeGet2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4;
                ObservableInt observableInt4 = viewModelBassVolume2;
                ObservableBoolean observableBoolean = viewModelUserTypeShow;
                viewModelUserTypeShowViewGONEViewVISIBLE2 = viewModelUserTypeShowViewGONEViewVISIBLE;
                Drawable drawable4 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3;
                BmwId8SettingsViewModel bmwId8SettingsViewModel = viewModel;
                viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = drawable4;
                int i4 = viewModelUserTypeShowViewVISIBLEViewGONE2;
                ObservableBoolean observableBoolean2 = viewModelAudioBgShow2;
                viewModelUserTypeShowViewVISIBLEViewGONE = i4;
                int i5 = viewModelAudioBgShowViewGONEViewVISIBLE2;
                int viewModelAudioBgShowViewGONEViewVISIBLE3 = viewModelEqTypeInt3;
                viewModelAudioBgShowViewGONEViewVISIBLE = i5;
                int i6 = viewModelTreVolumeGet2;
                ObservableInt observableInt5 = viewModelMidVolume;
                viewModelTreVolumeGet = i6;
                Drawable drawable5 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = drawable5;
                int i7 = viewModelMidVolumeGet2;
                ObservableField<String> observableField5 = viewModelMidVolumeStr;
                viewModelMidVolumeGet = i7;
                String str = viewModelBassVolumeStrGet2;
                boolean z5 = viewModelEqTypeInt4;
                viewModelBassVolumeStrGet = str;
            } else {
                viewModelTreVolumeStrGet = viewModelTreVolumeStrGet2;
                viewModelUserTypeShowViewVISIBLEViewGONE = viewModelUserTypeShowViewVISIBLEViewGONE2;
                viewModelBassVolumeGet = viewModelBassVolumeGet2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4;
                ObservableBoolean observableBoolean3 = viewModelAudioBgShow;
                ObservableInt observableInt6 = viewModelBassVolume2;
                ObservableBoolean observableBoolean4 = viewModelUserTypeShow;
                viewModelUserTypeShowViewGONEViewVISIBLE2 = viewModelUserTypeShowViewGONEViewVISIBLE;
                Drawable drawable6 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3;
                BmwId8SettingsViewModel bmwId8SettingsViewModel2 = viewModel;
                viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = drawable6;
                int viewModelAudioBgShowViewGONEViewVISIBLE4 = viewModelEqTypeInt3;
                viewModelAudioBgShowViewGONEViewVISIBLE = 0;
                int i8 = viewModelTreVolumeGet2;
                ObservableInt observableInt7 = viewModelMidVolume;
                viewModelTreVolumeGet = i8;
                Drawable drawable7 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = drawable7;
                int i9 = viewModelMidVolumeGet2;
                ObservableField<String> observableField6 = viewModelMidVolumeStr;
                viewModelMidVolumeGet = i9;
                String str2 = viewModelBassVolumeStrGet2;
                boolean z6 = viewModelEqTypeInt4;
                viewModelBassVolumeStrGet = str2;
            }
        } else {
            viewModelTreVolumeStrGet = null;
            viewModelUserTypeShowViewVISIBLEViewGONE = 0;
            viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = null;
            BmwId8SettingsViewModel bmwId8SettingsViewModel3 = viewModel;
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelBassVolumeGet = 0;
            viewModelAudioBgShowViewGONEViewVISIBLE = 0;
            viewModelTreVolumeGet = 0;
            viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelMidVolumeGet = 0;
            viewModelBassVolumeStrGet = null;
        }
        if ((dirtyFlags & 1537) != 0) {
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
            ID8AudioProgressBar.setId8AudioProgressBarValue(this.bmwId8SettingsAudioLow, viewModelBassVolumeGet);
        } else {
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
        }
        if ((dirtyFlags & 1540) != 0) {
            ID8AudioProgressBar.setId8AudioProgressBarValue(this.bmwId8SettingsAudioMid, viewModelMidVolumeGet);
        }
        if ((dirtyFlags & 1568) != 0) {
            ID8AudioProgressBar.setId8AudioProgressBarValue(this.bmwId8SettingsAudioTre, viewModelTreVolumeGet);
        }
        if ((dirtyFlags & 1792) != 0) {
            this.mboundView1.setVisibility(viewModelAudioBgShowViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 1538) != 0) {
            this.mboundView10.setVisibility(viewModelUserTypeShowViewVISIBLEViewGONE);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2);
            this.mboundView4.setVisibility(viewModelUserTypeShowViewGONEViewVISIBLE2);
        }
        if ((dirtyFlags & 1664) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelBassVolumeStrGet);
        }
        if ((dirtyFlags & 1544) != 0) {
            TextViewBindingAdapter.setText(this.mboundView13, viewModelMidVolumeStrGet);
        }
        if ((dirtyFlags & 1552) != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, viewModelTreVolumeStrGet);
        }
        if ((dirtyFlags & 1600) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView2, viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, viewModelEqTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN);
            String str3 = viewModelTreVolumeStrGet;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView6, viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN);
            Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN6 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN6);
            Drawable drawable8 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN6;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView9, viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN);
            return;
        }
        Drawable drawable9 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
        Drawable drawable10 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN;
    }
}

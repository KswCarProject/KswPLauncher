package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_lay, 17);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_user, 18);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_pop, 19);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_class, 20);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_rock, 21);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_jazz, 22);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_audio_sound_dance, 23);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_bass_sub_btn, 24);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_bass_add_btn, 25);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_mid_sub_btn, 26);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_mid_add_btn, 27);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_tre_sub_btn, 28);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_tre_add_btn, 29);
    }

    public BmwId8SettingsAudioSoundLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsAudioSoundLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, (ID8AudioProgressBar) bindings[12], (ID8AudioProgressBar) bindings[14], (RelativeLayout) bindings[20], (RelativeLayout) bindings[23], (RelativeLayout) bindings[22], (RelativeLayout) bindings[17], (RelativeLayout) bindings[19], (RelativeLayout) bindings[21], (RelativeLayout) bindings[18], (ID8AudioProgressBar) bindings[16], (ImageButton) bindings[25], (ImageButton) bindings[24], (ImageButton) bindings[27], (ImageButton) bindings[26], (ImageButton) bindings[29], (ImageButton) bindings[28]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsAudioLow.setTag(null);
        this.bmwId8SettingsAudioMid.setTag(null);
        this.bmwId8SettingsAudioTre.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[10];
        this.mboundView10 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[4];
        this.mboundView4 = scrollView;
        scrollView.setTag(null);
        ImageView imageView4 = (ImageView) bindings[5];
        this.mboundView5 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[6];
        this.mboundView6 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag(null);
        ImageView imageView7 = (ImageView) bindings[8];
        this.mboundView8 = imageView7;
        imageView7.setTag(null);
        ImageView imageView8 = (ImageView) bindings[9];
        this.mboundView9 = imageView8;
        imageView8.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
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

    @Override // com.wits.ksw.databinding.BmwId8SettingsAudioSoundLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelUserTypeShow(ObservableBoolean ViewModelUserTypeShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMidVolume(ObservableInt ViewModelMidVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMidVolumeStr(ObservableField<String> ViewModelMidVolumeStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTreVolumeStr(ObservableField<String> ViewModelTreVolumeStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTreVolume(ObservableInt ViewModelTreVolume, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelEqType(ObservableInt ViewModelEqType, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelBassVolumeStr(ObservableField<String> ViewModelBassVolumeStr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAudioBgShow(ObservableBoolean ViewModelAudioBgShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String viewModelTreVolumeStrGet;
        int viewModelUserTypeShowViewVISIBLEViewGONE;
        Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
        int viewModelBassVolumeGet;
        int viewModelAudioBgShowViewGONEViewVISIBLE;
        int viewModelTreVolumeGet;
        Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN;
        int viewModelMidVolumeGet;
        String viewModelBassVolumeStrGet;
        Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2;
        ObservableBoolean viewModelAudioBgShow;
        ObservableInt viewModelBassVolume;
        ObservableInt viewModelBassVolume2;
        ObservableBoolean viewModelUserTypeShow;
        int viewModelUserTypeShowViewGONEViewVISIBLE;
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
        Drawable drawable4;
        ObservableInt viewModelTreVolume;
        ObservableField<String> viewModelTreVolumeStr;
        Drawable viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableBoolean viewModelUserTypeShow2 = null;
        boolean viewModelEqTypeInt3 = false;
        int viewModelUserTypeShowViewGONEViewVISIBLE2 = 0;
        ObservableInt viewModelMidVolume = null;
        Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2 = null;
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
        if ((dirtyFlags & 2047) == 0) {
            viewModelTreVolumeStrGet = null;
            viewModelUserTypeShowViewVISIBLEViewGONE = 0;
            viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelBassVolumeGet = 0;
            viewModelAudioBgShowViewGONEViewVISIBLE = 0;
            viewModelTreVolumeGet = 0;
            viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelMidVolumeGet = 0;
            viewModelBassVolumeStrGet = null;
        } else {
            if ((dirtyFlags & 1537) == 0) {
                viewModelAudioBgShow = null;
                viewModelBassVolume = null;
            } else {
                if (viewModel == null) {
                    viewModelBassVolume = null;
                } else {
                    viewModelBassVolume = viewModel.bassVolume;
                }
                viewModelAudioBgShow = null;
                updateRegistration(0, viewModelBassVolume);
                if (viewModelBassVolume != null) {
                    viewModelBassVolumeGet2 = viewModelBassVolume.get();
                }
            }
            if ((dirtyFlags & 1538) == 0) {
                viewModelBassVolume2 = viewModelBassVolume;
            } else {
                if (viewModel != null) {
                    viewModelUserTypeShow2 = viewModel.userTypeShow;
                }
                updateRegistration(1, viewModelUserTypeShow2);
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
                    viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = AppCompatResources.getDrawable(this.mboundView3.getContext(), C0899R.C0900drawable.id8_settings_time_up);
                } else {
                    viewModelBassVolume2 = viewModelBassVolume;
                    viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = AppCompatResources.getDrawable(this.mboundView3.getContext(), C0899R.C0900drawable.id8_settings_time_down);
                }
                viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = viewModelUserTypeShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
                viewModelUserTypeShowViewVISIBLEViewGONE2 = viewModelUserTypeShowGet ? 0 : 8;
            }
            if ((dirtyFlags & 1540) != 0) {
                if (viewModel != null) {
                    viewModelMidVolume = viewModel.midVolume;
                }
                updateRegistration(2, viewModelMidVolume);
                if (viewModelMidVolume != null) {
                    viewModelMidVolumeGet2 = viewModelMidVolume.get();
                }
            }
            if ((dirtyFlags & 1544) != 0) {
                if (viewModel != null) {
                    viewModelMidVolumeStr = viewModel.midVolumeStr;
                }
                updateRegistration(3, viewModelMidVolumeStr);
                if (viewModelMidVolumeStr != null) {
                    viewModelMidVolumeStrGet = viewModelMidVolumeStr.get();
                }
            }
            if ((dirtyFlags & 1552) != 0) {
                if (viewModel == null) {
                    viewModelTreVolumeStr = null;
                } else {
                    viewModelTreVolumeStr = viewModel.treVolumeStr;
                }
                updateRegistration(4, viewModelTreVolumeStr);
                if (viewModelTreVolumeStr != null) {
                    viewModelTreVolumeStrGet2 = viewModelTreVolumeStr.get();
                }
            }
            if ((dirtyFlags & 1568) != 0) {
                if (viewModel == null) {
                    viewModelTreVolume = null;
                } else {
                    viewModelTreVolume = viewModel.treVolume;
                }
                updateRegistration(5, viewModelTreVolume);
                if (viewModelTreVolume != null) {
                    viewModelTreVolumeGet2 = viewModelTreVolume.get();
                }
            }
            if ((dirtyFlags & 1600) == 0) {
                viewModelUserTypeShow = viewModelUserTypeShow2;
                viewModelUserTypeShowViewGONEViewVISIBLE = viewModelUserTypeShowViewGONEViewVISIBLE2;
            } else {
                if (viewModel == null) {
                    viewModelEqType = null;
                } else {
                    viewModelEqType = viewModel.eqType;
                }
                updateRegistration(6, viewModelEqType);
                if (viewModelEqType == null) {
                    viewModelEqTypeGet = 0;
                } else {
                    int viewModelEqTypeGet2 = viewModelEqType.get();
                    viewModelEqTypeGet = viewModelEqTypeGet2;
                }
                boolean viewModelEqTypeInt22 = viewModelEqTypeGet == 2;
                boolean viewModelEqTypeInt32 = viewModelEqTypeGet == 3;
                boolean viewModelEqTypeInt42 = viewModelEqTypeGet == 4;
                boolean viewModelEqTypeInt0 = viewModelEqTypeGet == 0;
                viewModelUserTypeShow = viewModelUserTypeShow2;
                boolean viewModelEqTypeInt5 = viewModelEqTypeGet == 5;
                viewModelUserTypeShowViewGONEViewVISIBLE = viewModelUserTypeShowViewGONEViewVISIBLE2;
                boolean viewModelEqTypeInt1 = viewModelEqTypeGet == 1;
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
                int i = C0899R.C0900drawable.id8_settings_system_select_sel;
                Context context = this.mboundView6.getContext();
                if (!viewModelEqTypeInt22) {
                    i = C0899R.C0900drawable.id8_settings_system_select_n;
                }
                Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN3 = AppCompatResources.getDrawable(context, i);
                if (viewModelEqTypeInt32) {
                    viewModelEqTypeInt2 = viewModelEqTypeInt22;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt2 = viewModelEqTypeInt22;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4 = drawable;
                if (viewModelEqTypeInt42) {
                    viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView8.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN4;
                    drawable2 = AppCompatResources.getDrawable(this.mboundView8.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5 = drawable2;
                if (viewModelEqTypeInt0) {
                    viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView2.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN5;
                    drawable3 = AppCompatResources.getDrawable(this.mboundView2.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3 = drawable3;
                if (viewModelEqTypeInt5) {
                    viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView9.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel);
                } else {
                    viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN3;
                    drawable4 = AppCompatResources.getDrawable(this.mboundView9.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                }
                Drawable viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3 = drawable4;
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(this.mboundView5.getContext(), viewModelEqTypeInt1 ? C0899R.C0900drawable.id8_settings_system_select_sel : C0899R.C0900drawable.id8_settings_system_select_n);
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN3;
                dirtyFlags = dirtyFlags2;
                viewModelEqTypeInt4 = viewModelEqTypeInt32;
                viewModelEqTypeInt3 = viewModelEqTypeInt2;
            }
            if ((dirtyFlags & 1664) != 0) {
                if (viewModel == null) {
                    viewModelBassVolumeStr = null;
                } else {
                    viewModelBassVolumeStr = viewModel.bassVolumeStr;
                }
                updateRegistration(7, viewModelBassVolumeStr);
                if (viewModelBassVolumeStr != null) {
                    viewModelBassVolumeStrGet2 = viewModelBassVolumeStr.get();
                }
            }
            if ((dirtyFlags & 1792) == 0) {
                viewModelTreVolumeStrGet = viewModelTreVolumeStrGet2;
                viewModelUserTypeShowViewVISIBLEViewGONE = viewModelUserTypeShowViewVISIBLEViewGONE2;
                viewModelBassVolumeGet = viewModelBassVolumeGet2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4;
                viewModelUserTypeShowViewGONEViewVISIBLE2 = viewModelUserTypeShowViewGONEViewVISIBLE;
                viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3;
                viewModelAudioBgShowViewGONEViewVISIBLE = 0;
                viewModelTreVolumeGet = viewModelTreVolumeGet2;
                Drawable drawable5 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = drawable5;
                viewModelMidVolumeGet = viewModelMidVolumeGet2;
                viewModelBassVolumeStrGet = viewModelBassVolumeStrGet2;
            } else {
                if (viewModel == null) {
                    viewModelAudioBgShow2 = viewModelAudioBgShow;
                } else {
                    viewModelAudioBgShow2 = viewModel.audioBgShow;
                }
                updateRegistration(8, viewModelAudioBgShow2);
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
                int viewModelAudioBgShowViewGONEViewVISIBLE2 = viewModelAudioBgShowGet ? 8 : 0;
                viewModelTreVolumeStrGet = viewModelTreVolumeStrGet2;
                viewModelBassVolumeGet = viewModelBassVolumeGet2;
                viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN4;
                viewModelUserTypeShowViewGONEViewVISIBLE2 = viewModelUserTypeShowViewGONEViewVISIBLE;
                viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN3;
                viewModelUserTypeShowViewVISIBLEViewGONE = viewModelUserTypeShowViewVISIBLEViewGONE2;
                viewModelAudioBgShowViewGONEViewVISIBLE = viewModelAudioBgShowViewGONEViewVISIBLE2;
                viewModelTreVolumeGet = viewModelTreVolumeGet2;
                Drawable drawable6 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN3;
                viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt5MboundView9AndroidDrawableId8SettingsSystemSelectSelMboundView9AndroidDrawableId8SettingsSystemSelectN = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN2;
                viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN = drawable6;
                viewModelMidVolumeGet = viewModelMidVolumeGet2;
                viewModelBassVolumeStrGet = viewModelBassVolumeStrGet2;
            }
        }
        if ((dirtyFlags & 1537) == 0) {
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
        } else {
            viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2 = viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN;
            ID8AudioProgressBar.setId8AudioProgressBarValue(this.bmwId8SettingsAudioLow, viewModelBassVolumeGet);
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
            ImageViewBindingAdapter.setImageDrawable(this.mboundView6, viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN2);
            Drawable viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN4 = viewModelEqTypeInt0MboundView2AndroidDrawableId8SettingsSystemSelectSelMboundView2AndroidDrawableId8SettingsSystemSelectN;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelEqTypeInt2MboundView6AndroidDrawableId8SettingsSystemSelectSelMboundView6AndroidDrawableId8SettingsSystemSelectN4);
            Drawable viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN5 = viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView8, viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN5);
            Drawable viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN6 = viewModelEqTypeInt3MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN;
            ImageViewBindingAdapter.setImageDrawable(this.mboundView9, viewModelEqTypeInt4MboundView8AndroidDrawableId8SettingsSystemSelectSelMboundView8AndroidDrawableId8SettingsSystemSelectN6);
        }
    }
}

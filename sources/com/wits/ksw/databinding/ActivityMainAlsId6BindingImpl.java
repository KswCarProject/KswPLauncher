package com.wits.ksw.databinding;

import android.content.res.Resources;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ActivityMainAlsId6BindingImpl extends ActivityMainAlsId6Binding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback39;
    @Nullable
    private final View.OnClickListener mCallback40;
    @Nullable
    private final View.OnClickListener mCallback41;
    @Nullable
    private final View.OnClickListener mCallback42;
    @Nullable
    private final View.OnClickListener mCallback43;
    @Nullable
    private final View.OnClickListener mCallback44;
    @Nullable
    private final View.OnClickListener mCallback45;
    @Nullable
    private final View.OnClickListener mCallback46;
    @Nullable
    private final View.OnClickListener mCallback47;
    @Nullable
    private final View.OnClickListener mCallback48;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final LinearLayout mboundView1;
    @NonNull
    private final TextView mboundView10;
    @NonNull
    private final TextView mboundView11;
    @NonNull
    private final TextView mboundView14;
    @NonNull
    private final TextView mboundView15;
    @NonNull
    private final ImageView mboundView17;
    @NonNull
    private final LinearLayout mboundView2;
    @NonNull
    private final LinearLayout mboundView3;
    @NonNull
    private final LinearLayout mboundView4;
    @NonNull
    private final TextView mboundView8;

    static {
        sViewsWithIds.put(R.id.seat_ll, 19);
        sViewsWithIds.put(R.id.seat_belt_btn, 20);
        sViewsWithIds.put(R.id.seat_btn, 21);
        sViewsWithIds.put(R.id.s_view, 22);
    }

    public ActivityMainAlsId6BindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityMainAlsId6BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, bindings[18], bindings[9], bindings[13], bindings[5], bindings[7], bindings[12], bindings[22], bindings[20], bindings[21], bindings[19], bindings[16], bindings[6]);
        this.mDirtyFlags = -1;
        this.appLl.setTag((Object) null);
        this.dashbroadLl.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView1 = bindings[1];
        this.mboundView1.setTag((Object) null);
        this.mboundView10 = bindings[10];
        this.mboundView10.setTag((Object) null);
        this.mboundView11 = bindings[11];
        this.mboundView11.setTag((Object) null);
        this.mboundView14 = bindings[14];
        this.mboundView14.setTag((Object) null);
        this.mboundView15 = bindings[15];
        this.mboundView15.setTag((Object) null);
        this.mboundView17 = bindings[17];
        this.mboundView17.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView3 = bindings[3];
        this.mboundView3.setTag((Object) null);
        this.mboundView4 = bindings[4];
        this.mboundView4.setTag((Object) null);
        this.mboundView8 = bindings[8];
        this.mboundView8.setTag((Object) null);
        this.musicLl.setTag((Object) null);
        this.naviLl.setTag((Object) null);
        this.phoneLl.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.videoLl.setTag((Object) null);
        setRootTag(root);
        this.mCallback39 = new OnClickListener(this, 1);
        this.mCallback43 = new OnClickListener(this, 5);
        this.mCallback42 = new OnClickListener(this, 4);
        this.mCallback44 = new OnClickListener(this, 6);
        this.mCallback40 = new OnClickListener(this, 2);
        this.mCallback41 = new OnClickListener(this, 3);
        this.mCallback47 = new OnClickListener(this, 9);
        this.mCallback48 = new OnClickListener(this, 10);
        this.mCallback45 = new OnClickListener(this, 7);
        this.mCallback46 = new OnClickListener(this, 8);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (19 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(@Nullable LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 6:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMaxProgress(ObservableInt ViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoProgress(ObservableInt ViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> observableField, int fieldId) {
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
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        float viewModelCarInfoTurnSpeedAngeGet;
        ObservableInt viewModelMediaInfoProgress;
        int viewModelMediaInfoMaxProgressGet;
        float viewModelCarInfoTurnSpeedAngeGet2;
        String viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1;
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
        ObservableField<String> viewModelMediaInfoMusicAtist;
        String viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum;
        String str;
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
        ObservableField<Boolean> viewModelCarInfoBrakeValue2;
        long dirtyFlags2;
        Resources resources;
        int i;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        long dirtyFlags3;
        Resources resources2;
        int i2;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt13 = null;
        ObservableField<String> viewModelMediaInfoMusicAtist2 = null;
        ObservableField<String> viewModelMediaInfoMusicAlbum = null;
        ObservableInt viewModelMediaInfoMaxProgress = null;
        int viewModelMediaInfoProgressGet = 0;
        View.OnFocusChangeListener viewModelVideoViewFocusChangeListener = null;
        String viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = null;
        BitmapDrawable viewModelMediaInfoPicGet = null;
        String viewModelBtStateGet = null;
        int viewModelMediaInfoMaxProgressGet2 = 0;
        boolean viewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        String viewModelMediaInfoMusicAlbumGet = null;
        boolean viewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        String viewModelMediaInfoMusicAtistGet = null;
        Boolean viewModelCarInfoBrakeValueGet = null;
        LauncherViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 1079) != 0) {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            MediaInfo viewModelMediaInfo = LauncherViewModel.mediaInfo;
            viewModelCarInfoBrakeValue = null;
            if ((dirtyFlags & 1025) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAtist2 = viewModelMediaInfo.musicAtist;
                }
                updateRegistration(0, (Observable) viewModelMediaInfoMusicAtist2);
                if (viewModelMediaInfoMusicAtist2 != null) {
                    viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtist2.get();
                }
                boolean viewModelMediaInfoMusicAtistJavaLangObjectNull2 = viewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 1025) != 0) {
                    if (viewModelMediaInfoMusicAtistJavaLangObjectNull2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
            }
            if ((dirtyFlags & 1026) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAlbum = viewModelMediaInfo.musicAlbum;
                }
                updateRegistration(1, (Observable) viewModelMediaInfoMusicAlbum);
                if (viewModelMediaInfoMusicAlbum != null) {
                    viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbum.get();
                }
                boolean viewModelMediaInfoMusicAlbumJavaLangObjectNull2 = viewModelMediaInfoMusicAlbumGet == null;
                if ((dirtyFlags & 1026) != 0) {
                    if (viewModelMediaInfoMusicAlbumJavaLangObjectNull2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                viewModelMediaInfoMusicAlbumJavaLangObjectNull = viewModelMediaInfoMusicAlbumJavaLangObjectNull2;
            }
            if ((dirtyFlags & 1028) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMaxProgress = viewModelMediaInfo.maxProgress;
                }
                updateRegistration(2, (Observable) viewModelMediaInfoMaxProgress);
                if (viewModelMediaInfoMaxProgress != null) {
                    viewModelMediaInfoMaxProgressGet2 = viewModelMediaInfoMaxProgress.get();
                }
            }
            if ((dirtyFlags & 1040) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                } else {
                    viewModelMediaInfoPic = null;
                }
                updateRegistration(4, (Observable) viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    ObservableField<BitmapDrawable> observableField = viewModelMediaInfoPic;
                    viewModelMediaInfoPicGet = viewModelMediaInfoPic.get();
                } else {
                    ObservableField<BitmapDrawable> observableField2 = viewModelMediaInfoPic;
                }
            }
            if ((dirtyFlags & 1056) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoProgress = viewModelMediaInfo.progress;
                } else {
                    viewModelMediaInfoProgress = null;
                }
                updateRegistration(5, (Observable) viewModelMediaInfoProgress);
                if (viewModelMediaInfoProgress != null) {
                    viewModelMediaInfoProgressGet = viewModelMediaInfoProgress.get();
                }
                viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
            } else {
                viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                viewModelMediaInfoProgress = null;
            }
        } else {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelCarInfoBrakeValue = null;
            viewModelMediaInfoMaxProgressGet = 0;
            viewModelMediaInfoProgress = null;
        }
        if ((dirtyFlags & 1416) != 0) {
            CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 1032) != 0) {
                if (viewModelCarInfo != null) {
                    ObservableInt observableInt = viewModelMediaInfoProgress;
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                updateRegistration(3, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 1032) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                    dirtyFlags3 = dirtyFlags;
                    resources2 = this.mboundView10.getResources();
                    i2 = R.string.ksw_id7_seatbelt2;
                } else {
                    dirtyFlags3 = dirtyFlags;
                    resources2 = this.mboundView10.getResources();
                    i2 = R.string.ksw_id7_seatbelt1;
                }
                ObservableField<Boolean> observableField3 = viewModelCarInfoSeatBeltpValue;
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt13 = resources2.getString(i2);
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 1152) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt13;
                updateRegistration(7, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                    viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAnge.get();
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                }
            } else {
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt13;
            }
            if ((dirtyFlags & 1280) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue2 = viewModelCarInfoBrakeValue;
                }
                updateRegistration(8, (Observable) viewModelCarInfoBrakeValue2);
                if (viewModelCarInfoBrakeValue2 != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue2.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 1280) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet) {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.mboundView11.getResources();
                    i = R.string.ksw_id7_brake2;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.mboundView11.getResources();
                    i = R.string.ksw_id7_brake1;
                }
                ObservableField<Boolean> observableField4 = viewModelCarInfoBrakeValue2;
                CarInfo carInfo = viewModelCarInfo;
                boolean z2 = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = resources.getString(i);
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
                dirtyFlags = dirtyFlags2;
            } else {
                CarInfo carInfo2 = viewModelCarInfo;
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
            }
        } else {
            viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
            viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
            viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = null;
        }
        if ((dirtyFlags & 1600) != 0) {
            if (!((dirtyFlags & 1536) == 0 || viewModel == null)) {
                viewModelVideoViewFocusChangeListener = viewModel.videoViewFocusChangeListener;
            }
            if (viewModel != null) {
                ObservableField<String> observableField5 = viewModelMediaInfoMusicAtist2;
                viewModelMediaInfoMusicAtist = viewModel.btState;
            } else {
                viewModelMediaInfoMusicAtist = null;
            }
            LauncherViewModel launcherViewModel = viewModel;
            updateRegistration(6, (Observable) viewModelMediaInfoMusicAtist);
            if (viewModelMediaInfoMusicAtist != null) {
                viewModelBtStateGet = viewModelMediaInfoMusicAtist.get();
            }
        } else {
            ObservableField<String> observableField6 = viewModelMediaInfoMusicAtist2;
            viewModelMediaInfoMusicAtist = null;
        }
        if ((dirtyFlags & 1025) != 0) {
            if (viewModelMediaInfoMusicAtistJavaLangObjectNull) {
                ObservableField<String> observableField7 = viewModelMediaInfoMusicAtist;
                str = this.mboundView15.getResources().getString(R.string.ksw_idf7_unknow_artis);
            } else {
                ObservableField<String> viewModelBtState = viewModelMediaInfoMusicAtist;
                str = viewModelMediaInfoMusicAtistGet;
            }
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = str;
        } else {
            ObservableField<String> viewModelBtState2 = viewModelMediaInfoMusicAtist;
        }
        if ((dirtyFlags & 1026) != 0) {
            viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum = viewModelMediaInfoMusicAlbumJavaLangObjectNull ? this.mboundView14.getResources().getString(R.string.ksw_idf7_unkonw_album) : viewModelMediaInfoMusicAlbumGet;
        } else {
            viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum = null;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            ObservableField<String> observableField8 = viewModelMediaInfoMusicAlbum;
            this.appLl.setOnClickListener(this.mCallback48);
            this.dashbroadLl.setOnClickListener(this.mCallback46);
            this.mboundView1.setOnClickListener(this.mCallback39);
            this.mboundView2.setOnClickListener(this.mCallback40);
            this.mboundView3.setOnClickListener(this.mCallback41);
            this.mboundView4.setOnClickListener(this.mCallback42);
            this.musicLl.setOnClickListener(this.mCallback47);
            this.naviLl.setOnClickListener(this.mCallback43);
            this.phoneLl.setOnClickListener(this.mCallback45);
            this.videoLl.setOnClickListener(this.mCallback44);
        }
        if ((dirtyFlags & 1032) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1);
        }
        if ((dirtyFlags & 1280) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1);
        }
        if ((dirtyFlags & 1026) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags & 1025) != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & 1040) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView17, viewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 1600) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, viewModelBtStateGet);
        }
        if ((dirtyFlags & 1152) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, viewModelCarInfoTurnSpeedAngeGet2);
        }
        if ((dirtyFlags & 1028) != 0) {
            this.seekBar.setMax(viewModelMediaInfoMaxProgressGet);
        }
        if ((dirtyFlags & 1056) != 0) {
            SeekBarBindingAdapter.setProgress(this.seekBar, viewModelMediaInfoProgressGet);
        }
        if ((dirtyFlags & 1536) != 0) {
            this.videoLl.setOnFocusChangeListener(viewModelVideoViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = false;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                if (viewModel5 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                if (viewModel6 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openVideo(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                if (viewModel7 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                if (viewModel8 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                if (viewModel9 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openChoseMusic(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                if (viewModel10 != null) {
                    viewModelJavaLangObjectNull = true;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel10.openApps(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

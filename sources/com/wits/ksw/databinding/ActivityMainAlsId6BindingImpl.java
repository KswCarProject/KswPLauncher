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
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback228;
    private final View.OnClickListener mCallback229;
    private final View.OnClickListener mCallback230;
    private final View.OnClickListener mCallback231;
    private final View.OnClickListener mCallback232;
    private final View.OnClickListener mCallback233;
    private final View.OnClickListener mCallback234;
    private final View.OnClickListener mCallback235;
    private final View.OnClickListener mCallback236;
    private final View.OnClickListener mCallback237;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final ImageView mboundView17;
    private final LinearLayout mboundView2;
    private final LinearLayout mboundView3;
    private final LinearLayout mboundView4;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.seat_ll, 19);
        sparseIntArray.put(R.id.seat_belt_btn, 20);
        sparseIntArray.put(R.id.seat_btn, 21);
        sparseIntArray.put(R.id.s_view, 22);
    }

    public ActivityMainAlsId6BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityMainAlsId6BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, bindings[18], bindings[9], bindings[13], bindings[5], bindings[7], bindings[12], bindings[22], bindings[20], bindings[21], bindings[19], bindings[16], bindings[6]);
        this.mDirtyFlags = -1;
        this.appLl.setTag((Object) null);
        this.dashbroadLl.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        LinearLayout linearLayout2 = bindings[1];
        this.mboundView1 = linearLayout2;
        linearLayout2.setTag((Object) null);
        TextView textView = bindings[10];
        this.mboundView10 = textView;
        textView.setTag((Object) null);
        TextView textView2 = bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = bindings[14];
        this.mboundView14 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = bindings[15];
        this.mboundView15 = textView4;
        textView4.setTag((Object) null);
        ImageView imageView = bindings[17];
        this.mboundView17 = imageView;
        imageView.setTag((Object) null);
        LinearLayout linearLayout3 = bindings[2];
        this.mboundView2 = linearLayout3;
        linearLayout3.setTag((Object) null);
        LinearLayout linearLayout4 = bindings[3];
        this.mboundView3 = linearLayout4;
        linearLayout4.setTag((Object) null);
        LinearLayout linearLayout5 = bindings[4];
        this.mboundView4 = linearLayout5;
        linearLayout5.setTag((Object) null);
        TextView textView5 = bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag((Object) null);
        this.musicLl.setTag((Object) null);
        this.naviLl.setTag((Object) null);
        this.phoneLl.setTag((Object) null);
        this.pointerImageView.setTag((Object) null);
        this.seekBar.setTag((Object) null);
        this.videoLl.setTag((Object) null);
        setRootTag(root);
        this.mCallback237 = new OnClickListener(this, 10);
        this.mCallback233 = new OnClickListener(this, 6);
        this.mCallback229 = new OnClickListener(this, 2);
        this.mCallback234 = new OnClickListener(this, 7);
        this.mCallback230 = new OnClickListener(this, 3);
        this.mCallback235 = new OnClickListener(this, 8);
        this.mCallback231 = new OnClickListener(this, 4);
        this.mCallback228 = new OnClickListener(this, 1);
        this.mCallback236 = new OnClickListener(this, 9);
        this.mCallback232 = new OnClickListener(this, 5);
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoProgress((ObservableInt) object, fieldId);
            case 1:
                return onChangeViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoMusicAlbum((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoMaxProgress((ObservableInt) object, fieldId);
            case 4:
                return onChangeViewModelBtState((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelCarInfoSeatBeltpValue((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelCarInfoTurnSpeedAnge((ObservableFloat) object, fieldId);
            case 7:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 8:
                return onChangeViewModelCarInfoBrakeValue((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoProgress(ObservableInt ViewModelMediaInfoProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicAlbum(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMaxProgress(ObservableInt ViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelBtState(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
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
        float viewModelCarInfoTurnSpeedAngeGet;
        String viewModelMediaInfoMusicAlbumGet;
        int viewModelMediaInfoMaxProgressGet;
        BitmapDrawable viewModelMediaInfoPicGet;
        long dirtyFlags2;
        String viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1;
        float viewModelCarInfoTurnSpeedAngeGet2;
        String viewModelBtStateGet;
        String viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist;
        String str;
        ObservableField<String> viewModelBtState;
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        int i;
        Resources resources;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        ObservableField<Boolean> viewModelCarInfoSeatBeltpValue;
        long dirtyFlags3;
        String str2;
        String viewModelMediaInfoMusicAlbumGet2;
        String viewModelMediaInfoMusicAlbumGet3;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = null;
        ObservableInt viewModelMediaInfoProgress = null;
        ObservableField<String> viewModelMediaInfoMusicAtist = null;
        ObservableField<String> viewModelMediaInfoMusicAlbum = null;
        ObservableInt viewModelMediaInfoMaxProgress = null;
        String viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum = null;
        int viewModelMediaInfoProgressGet = 0;
        View.OnFocusChangeListener viewModelVideoViewFocusChangeListener = null;
        boolean viewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        Boolean viewModelCarInfoSeatBeltpValueGet = null;
        String viewModelMediaInfoMusicAtistGet = null;
        int viewModelMediaInfoMaxProgressGet2 = 0;
        Boolean viewModelCarInfoBrakeValueGet = null;
        boolean viewModelMediaInfoMusicAlbumJavaLangObjectNull = false;
        LauncherViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 1167) != 0) {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            MediaInfo viewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoProgress = viewModelMediaInfo.progress;
                }
                updateRegistration(0, (Observable) viewModelMediaInfoProgress);
                if (viewModelMediaInfoProgress != null) {
                    viewModelMediaInfoProgressGet = viewModelMediaInfoProgress.get();
                }
            }
            if ((dirtyFlags & 1026) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAtist = viewModelMediaInfo.musicAtist;
                }
                updateRegistration(1, (Observable) viewModelMediaInfoMusicAtist);
                if (viewModelMediaInfoMusicAtist != null) {
                    viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtist.get();
                }
                viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 1026) != 0) {
                    dirtyFlags = viewModelMediaInfoMusicAtistJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH : dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags & 1028) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAlbum = viewModelMediaInfo.musicAlbum;
                }
                updateRegistration(2, (Observable) viewModelMediaInfoMusicAlbum);
                if (viewModelMediaInfoMusicAlbum != null) {
                    viewModelMediaInfoMusicAlbumGet2 = viewModelMediaInfoMusicAlbum.get();
                } else {
                    viewModelMediaInfoMusicAlbumGet2 = null;
                }
                boolean viewModelMediaInfoMusicAlbumJavaLangObjectNull2 = viewModelMediaInfoMusicAlbumGet2 == null;
                if ((dirtyFlags & 1028) == 0) {
                    viewModelMediaInfoMusicAlbumJavaLangObjectNull = viewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else if (viewModelMediaInfoMusicAlbumJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    viewModelMediaInfoMusicAlbumJavaLangObjectNull = viewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    viewModelMediaInfoMusicAlbumJavaLangObjectNull = viewModelMediaInfoMusicAlbumJavaLangObjectNull2;
                }
            } else {
                viewModelMediaInfoMusicAlbumGet2 = null;
            }
            if ((dirtyFlags & 1032) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMaxProgress = viewModelMediaInfo.maxProgress;
                }
                viewModelMediaInfoMusicAlbumGet3 = viewModelMediaInfoMusicAlbumGet2;
                updateRegistration(3, (Observable) viewModelMediaInfoMaxProgress);
                if (viewModelMediaInfoMaxProgress != null) {
                    viewModelMediaInfoMaxProgressGet2 = viewModelMediaInfoMaxProgress.get();
                }
            } else {
                viewModelMediaInfoMusicAlbumGet3 = viewModelMediaInfoMusicAlbumGet2;
            }
            if ((dirtyFlags & 1152) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                } else {
                    viewModelMediaInfoPic = null;
                }
                MediaInfo viewModelMediaInfo2 = viewModelMediaInfo;
                updateRegistration(7, (Observable) viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    ObservableField<BitmapDrawable> observableField = viewModelMediaInfoPic;
                    viewModelMediaInfoPicGet = viewModelMediaInfoPic.get();
                    viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                    viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                    MediaInfo mediaInfo = viewModelMediaInfo2;
                } else {
                    ObservableField<BitmapDrawable> observableField2 = viewModelMediaInfoPic;
                    viewModelMediaInfoPicGet = null;
                    viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                    viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                    MediaInfo mediaInfo2 = viewModelMediaInfo2;
                }
            } else {
                MediaInfo viewModelMediaInfo3 = viewModelMediaInfo;
                viewModelMediaInfoPicGet = null;
                viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                MediaInfo mediaInfo3 = viewModelMediaInfo3;
            }
        } else {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelMediaInfoPicGet = null;
            viewModelMediaInfoMaxProgressGet = 0;
            viewModelMediaInfoMusicAlbumGet = null;
        }
        if ((dirtyFlags & 1376) != 0) {
            CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 1056) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoSeatBeltpValue = viewModelCarInfo.seatBeltpValue;
                } else {
                    viewModelCarInfoSeatBeltpValue = null;
                }
                ObservableInt observableInt = viewModelMediaInfoProgress;
                updateRegistration(5, (Observable) viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet = ViewDataBinding.safeUnbox(viewModelCarInfoSeatBeltpValueGet);
                if ((dirtyFlags & 1056) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoSeatBeltpValueGet) {
                    dirtyFlags3 = dirtyFlags;
                    str2 = this.mboundView10.getResources().getString(R.string.ksw_id7_seatbelt2);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    str2 = this.mboundView10.getResources().getString(R.string.ksw_id7_seatbelt1);
                }
                ObservableField<Boolean> observableField3 = viewModelCarInfoSeatBeltpValue;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = str2;
                dirtyFlags = dirtyFlags3;
            } else {
                ObservableInt observableInt2 = viewModelMediaInfoProgress;
            }
            if ((dirtyFlags & 1088) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                } else {
                    viewModelCarInfoTurnSpeedAnge = null;
                }
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
                updateRegistration(6, (Observable) viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAnge.get();
                    ObservableFloat observableFloat = viewModelCarInfoTurnSpeedAnge;
                } else {
                    ObservableFloat observableFloat2 = viewModelCarInfoTurnSpeedAnge;
                }
            } else {
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
            }
            if ((dirtyFlags & 1280) != 0) {
                if (viewModelCarInfo != null) {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                } else {
                    viewModelCarInfoBrakeValue = null;
                }
                updateRegistration(8, (Observable) viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValue.get();
                }
                boolean androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2 = ViewDataBinding.safeUnbox(viewModelCarInfoBrakeValueGet);
                if ((dirtyFlags & 1280) != 0) {
                    if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    }
                }
                if (androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2) {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.mboundView11.getResources();
                    i = R.string.ksw_id7_brake2;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.mboundView11.getResources();
                    i = R.string.ksw_id7_brake1;
                }
                ObservableField<Boolean> observableField4 = viewModelCarInfoBrakeValue;
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = resources.getString(i);
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                CarInfo carInfo = viewModelCarInfo;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2;
            } else {
                dirtyFlags2 = dirtyFlags;
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                CarInfo carInfo2 = viewModelCarInfo;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
            }
        } else {
            ObservableInt observableInt3 = viewModelMediaInfoProgress;
            dirtyFlags2 = dirtyFlags;
            viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
            viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
        }
        if ((dirtyFlags2 & 1552) != 0) {
            if (!((dirtyFlags2 & 1536) == 0 || viewModel == null)) {
                viewModelVideoViewFocusChangeListener = viewModel.videoViewFocusChangeListener;
            }
            if (viewModel != null) {
                viewModelBtState = viewModel.btState;
            } else {
                viewModelBtState = null;
            }
            LauncherViewModel launcherViewModel = viewModel;
            updateRegistration(4, (Observable) viewModelBtState);
            if (viewModelBtState != null) {
                ObservableField<String> observableField5 = viewModelBtState;
                viewModelBtStateGet = viewModelBtState.get();
            } else {
                ObservableField<String> observableField6 = viewModelBtState;
                viewModelBtStateGet = null;
            }
        } else {
            viewModelBtStateGet = null;
        }
        if ((dirtyFlags2 & 1028) != 0) {
            if (viewModelMediaInfoMusicAlbumJavaLangObjectNull) {
                boolean z = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet;
                str = this.mboundView14.getResources().getString(R.string.ksw_idf7_unkonw_album);
            } else {
                str = viewModelMediaInfoMusicAlbumGet;
            }
            viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum = str;
        }
        if ((dirtyFlags2 & 1026) != 0) {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = viewModelMediaInfoMusicAtistJavaLangObjectNull ? this.mboundView15.getResources().getString(R.string.ksw_idf7_unknow_artis) : viewModelMediaInfoMusicAtistGet;
        } else {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = null;
        }
        if ((dirtyFlags2 & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            ObservableField<String> observableField7 = viewModelMediaInfoMusicAtist;
            this.appLl.setOnClickListener(this.mCallback237);
            this.dashbroadLl.setOnClickListener(this.mCallback235);
            this.mboundView1.setOnClickListener(this.mCallback228);
            this.mboundView2.setOnClickListener(this.mCallback229);
            this.mboundView3.setOnClickListener(this.mCallback230);
            this.mboundView4.setOnClickListener(this.mCallback231);
            this.musicLl.setOnClickListener(this.mCallback236);
            this.naviLl.setOnClickListener(this.mCallback232);
            this.phoneLl.setOnClickListener(this.mCallback234);
            this.videoLl.setOnClickListener(this.mCallback233);
        }
        if ((dirtyFlags2 & 1056) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12);
        }
        if ((dirtyFlags2 & 1280) != 0) {
            TextViewBindingAdapter.setText(this.mboundView11, viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1);
        }
        if ((dirtyFlags2 & 1028) != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum);
        }
        if ((dirtyFlags2 & 1026) != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags2 & 1152) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView17, viewModelMediaInfoPicGet);
        }
        if ((dirtyFlags2 & 1552) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, viewModelBtStateGet);
        }
        if ((dirtyFlags2 & 1088) != 0) {
            DashboardViewModel.setRotation(this.pointerImageView, viewModelCarInfoTurnSpeedAngeGet2);
        }
        if ((dirtyFlags2 & 1032) != 0) {
            this.seekBar.setMax(viewModelMediaInfoMaxProgressGet);
        }
        if ((dirtyFlags2 & 1025) != 0) {
            SeekBarBindingAdapter.setProgress(this.seekBar, viewModelMediaInfoProgressGet);
        }
        if ((dirtyFlags2 & 1536) != 0) {
            this.videoLl.setOnFocusChangeListener(viewModelVideoViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                if (viewModel2 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                if (viewModel4 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                if (viewModel5 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                if (viewModel6 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                if (viewModel7 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                if (viewModel8 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                if (viewModel9 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                if (viewModel10 == null) {
                    viewModelJavaLangObjectNull = false;
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

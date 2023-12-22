package com.wits.ksw.databinding;

import android.content.res.Resources;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class ActivityMainAlsId6BindingImpl extends ActivityMainAlsId6Binding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback217;
    private final View.OnClickListener mCallback218;
    private final View.OnClickListener mCallback219;
    private final View.OnClickListener mCallback220;
    private final View.OnClickListener mCallback221;
    private final View.OnClickListener mCallback222;
    private final View.OnClickListener mCallback223;
    private final View.OnClickListener mCallback224;
    private final View.OnClickListener mCallback225;
    private final View.OnClickListener mCallback226;
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
        sparseIntArray.put(C0899R.C0901id.seat_ll, 19);
        sparseIntArray.put(C0899R.C0901id.seat_belt_btn, 20);
        sparseIntArray.put(C0899R.C0901id.seat_btn, 21);
        sparseIntArray.put(C0899R.C0901id.s_view, 22);
    }

    public ActivityMainAlsId6BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityMainAlsId6BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9, (LinearLayout) bindings[18], (LinearLayout) bindings[9], (LinearLayout) bindings[13], (LinearLayout) bindings[5], (LinearLayout) bindings[7], (ImageView) bindings[12], (View) bindings[22], (ImageView) bindings[20], (ImageView) bindings[21], (LinearLayout) bindings[19], (SeekBar) bindings[16], (LinearLayout) bindings[6]);
        this.mDirtyFlags = -1L;
        this.appLl.setTag(null);
        this.dashbroadLl.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[14];
        this.mboundView14 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[15];
        this.mboundView15 = textView4;
        textView4.setTag(null);
        ImageView imageView = (ImageView) bindings[17];
        this.mboundView17 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout3;
        linearLayout3.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[3];
        this.mboundView3 = linearLayout4;
        linearLayout4.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout5;
        linearLayout5.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        this.musicLl.setTag(null);
        this.naviLl.setTag(null);
        this.phoneLl.setTag(null);
        this.pointerImageView.setTag(null);
        this.seekBar.setTag(null);
        this.videoLl.setTag(null);
        setRootTag(root);
        this.mCallback217 = new OnClickListener(this, 1);
        this.mCallback225 = new OnClickListener(this, 9);
        this.mCallback221 = new OnClickListener(this, 5);
        this.mCallback226 = new OnClickListener(this, 10);
        this.mCallback222 = new OnClickListener(this, 6);
        this.mCallback218 = new OnClickListener(this, 2);
        this.mCallback223 = new OnClickListener(this, 7);
        this.mCallback219 = new OnClickListener(this, 3);
        this.mCallback224 = new OnClickListener(this, 8);
        this.mCallback220 = new OnClickListener(this, 4);
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
            setViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityMainAlsId6Binding
    public void setViewModel(LauncherViewModel ViewModel) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> ViewModelMediaInfoMusicAtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMusicAlbum(ObservableField<String> ViewModelMediaInfoMusicAlbum, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMaxProgress(ObservableInt ViewModelMediaInfoMaxProgress, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelBtState(ObservableField<String> ViewModelBtState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoSeatBeltpValue(ObservableField<Boolean> ViewModelCarInfoSeatBeltpValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoTurnSpeedAnge(ObservableFloat ViewModelCarInfoTurnSpeedAnge, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> ViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCarInfoBrakeValue(ObservableField<Boolean> ViewModelCarInfoBrakeValue, int fieldId) {
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
        float viewModelCarInfoTurnSpeedAngeGet;
        BitmapDrawable viewModelMediaInfoPicGet;
        int viewModelMediaInfoMaxProgressGet;
        String viewModelMediaInfoMusicAlbumGet;
        long dirtyFlags2;
        String viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1;
        float viewModelCarInfoTurnSpeedAngeGet2;
        String viewModelBtStateGet;
        String viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist;
        ObservableField<String> viewModelBtState;
        String viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
        ObservableField<Boolean> viewModelCarInfoBrakeValue;
        Resources resources;
        int i;
        ObservableFloat viewModelCarInfoTurnSpeedAnge;
        long dirtyFlags3;
        String string;
        String viewModelMediaInfoMusicAlbumGet2;
        String viewModelMediaInfoMusicAlbumGet3;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
        if ((dirtyFlags & 1167) == 0) {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            viewModelMediaInfoPicGet = null;
            viewModelMediaInfoMaxProgressGet = 0;
            viewModelMediaInfoMusicAlbumGet = null;
        } else {
            viewModelCarInfoTurnSpeedAngeGet = 0.0f;
            MediaInfo viewModelMediaInfo = LauncherViewModel.mediaInfo;
            if ((dirtyFlags & 1025) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoProgress = viewModelMediaInfo.progress;
                }
                updateRegistration(0, viewModelMediaInfoProgress);
                if (viewModelMediaInfoProgress != null) {
                    viewModelMediaInfoProgressGet = viewModelMediaInfoProgress.get();
                }
            }
            if ((dirtyFlags & 1026) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAtist = viewModelMediaInfo.musicAtist;
                }
                updateRegistration(1, viewModelMediaInfoMusicAtist);
                if (viewModelMediaInfoMusicAtist != null) {
                    viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtist.get();
                }
                viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 1026) != 0) {
                    dirtyFlags = viewModelMediaInfoMusicAtistJavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH : dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags & 1028) == 0) {
                viewModelMediaInfoMusicAlbumGet2 = null;
            } else {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAlbum = viewModelMediaInfo.musicAlbum;
                }
                updateRegistration(2, viewModelMediaInfoMusicAlbum);
                if (viewModelMediaInfoMusicAlbum == null) {
                    viewModelMediaInfoMusicAlbumGet2 = null;
                } else {
                    viewModelMediaInfoMusicAlbumGet2 = viewModelMediaInfoMusicAlbum.get();
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
            }
            if ((dirtyFlags & 1032) == 0) {
                viewModelMediaInfoMusicAlbumGet3 = viewModelMediaInfoMusicAlbumGet2;
            } else {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMaxProgress = viewModelMediaInfo.maxProgress;
                }
                viewModelMediaInfoMusicAlbumGet3 = viewModelMediaInfoMusicAlbumGet2;
                updateRegistration(3, viewModelMediaInfoMaxProgress);
                if (viewModelMediaInfoMaxProgress != null) {
                    viewModelMediaInfoMaxProgressGet2 = viewModelMediaInfoMaxProgress.get();
                }
            }
            if ((dirtyFlags & 1152) == 0) {
                viewModelMediaInfoPicGet = null;
                viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
            } else {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoPic = null;
                } else {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                }
                updateRegistration(7, viewModelMediaInfoPic);
                if (viewModelMediaInfoPic == null) {
                    viewModelMediaInfoPicGet = null;
                    viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                    viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                } else {
                    BitmapDrawable viewModelMediaInfoPicGet2 = viewModelMediaInfoPic.get();
                    viewModelMediaInfoPicGet = viewModelMediaInfoPicGet2;
                    viewModelMediaInfoMusicAlbumGet = viewModelMediaInfoMusicAlbumGet3;
                    viewModelMediaInfoMaxProgressGet = viewModelMediaInfoMaxProgressGet2;
                }
            }
        }
        if ((dirtyFlags & 1376) == 0) {
            dirtyFlags2 = dirtyFlags;
            viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
            viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
        } else {
            com.wits.ksw.launcher.bean.CarInfo viewModelCarInfo = LauncherViewModel.carInfo;
            if ((dirtyFlags & 1056) != 0) {
                ObservableField<Boolean> viewModelCarInfoSeatBeltpValue = viewModelCarInfo != null ? viewModelCarInfo.seatBeltpValue : null;
                updateRegistration(5, viewModelCarInfoSeatBeltpValue);
                if (viewModelCarInfoSeatBeltpValue != null) {
                    Boolean viewModelCarInfoSeatBeltpValueGet2 = viewModelCarInfoSeatBeltpValue.get();
                    viewModelCarInfoSeatBeltpValueGet = viewModelCarInfoSeatBeltpValueGet2;
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
                    string = this.mboundView10.getResources().getString(C0899R.string.ksw_id7_seatbelt2);
                } else {
                    dirtyFlags3 = dirtyFlags;
                    string = this.mboundView10.getResources().getString(C0899R.string.ksw_id7_seatbelt1);
                }
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = string;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 1088) == 0) {
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoTurnSpeedAnge = null;
                } else {
                    viewModelCarInfoTurnSpeedAnge = viewModelCarInfo.turnSpeedAnge;
                }
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12;
                updateRegistration(6, viewModelCarInfoTurnSpeedAnge);
                if (viewModelCarInfoTurnSpeedAnge != null) {
                    viewModelCarInfoTurnSpeedAngeGet = viewModelCarInfoTurnSpeedAnge.get();
                }
            }
            if ((dirtyFlags & 1280) == 0) {
                dirtyFlags2 = dirtyFlags;
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = null;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = false;
            } else {
                if (viewModelCarInfo == null) {
                    viewModelCarInfoBrakeValue = null;
                } else {
                    viewModelCarInfoBrakeValue = viewModelCarInfo.brakeValue;
                }
                updateRegistration(8, viewModelCarInfoBrakeValue);
                if (viewModelCarInfoBrakeValue != null) {
                    Boolean viewModelCarInfoBrakeValueGet2 = viewModelCarInfoBrakeValue.get();
                    viewModelCarInfoBrakeValueGet = viewModelCarInfoBrakeValueGet2;
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
                    i = C0899R.string.ksw_id7_brake2;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    resources = this.mboundView11.getResources();
                    i = C0899R.string.ksw_id7_brake1;
                }
                String viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake12 = resources.getString(i);
                viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake1 = viewModelCarInfoBrakeValueMboundView11AndroidStringKswId7Brake2MboundView11AndroidStringKswId7Brake12;
                viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt12 = viewModelCarInfoSeatBeltpValueMboundView10AndroidStringKswId7Seatbelt2MboundView10AndroidStringKswId7Seatbelt1;
                viewModelCarInfoTurnSpeedAngeGet2 = viewModelCarInfoTurnSpeedAngeGet;
                androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet = androidDatabindingViewDataBindingSafeUnboxViewModelCarInfoBrakeValueGet2;
            }
        }
        if ((dirtyFlags2 & 1552) == 0) {
            viewModelBtStateGet = null;
        } else {
            if ((dirtyFlags2 & 1536) != 0 && viewModel != null) {
                viewModelVideoViewFocusChangeListener = viewModel.videoViewFocusChangeListener;
            }
            if (viewModel == null) {
                viewModelBtState = null;
            } else {
                viewModelBtState = viewModel.btState;
            }
            updateRegistration(4, viewModelBtState);
            if (viewModelBtState == null) {
                viewModelBtStateGet = null;
            } else {
                String viewModelBtStateGet2 = viewModelBtState.get();
                viewModelBtStateGet = viewModelBtStateGet2;
            }
        }
        if ((dirtyFlags2 & 1028) != 0) {
            viewModelMediaInfoMusicAlbumJavaLangObjectNullMboundView14AndroidStringKswIdf7UnkonwAlbumViewModelMediaInfoMusicAlbum = viewModelMediaInfoMusicAlbumJavaLangObjectNull ? this.mboundView14.getResources().getString(C0899R.string.ksw_idf7_unkonw_album) : viewModelMediaInfoMusicAlbumGet;
        }
        if ((dirtyFlags2 & 1026) == 0) {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = null;
        } else {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView15AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = viewModelMediaInfoMusicAtistJavaLangObjectNull ? this.mboundView15.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : viewModelMediaInfoMusicAtistGet;
        }
        if ((dirtyFlags2 & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
            this.appLl.setOnClickListener(this.mCallback226);
            this.dashbroadLl.setOnClickListener(this.mCallback224);
            this.mboundView1.setOnClickListener(this.mCallback217);
            this.mboundView2.setOnClickListener(this.mCallback218);
            this.mboundView3.setOnClickListener(this.mCallback219);
            this.mboundView4.setOnClickListener(this.mCallback220);
            this.musicLl.setOnClickListener(this.mCallback225);
            this.naviLl.setOnClickListener(this.mCallback221);
            this.phoneLl.setOnClickListener(this.mCallback223);
            this.videoLl.setOnClickListener(this.mCallback222);
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

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LauncherViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openSettings(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LauncherViewModel viewModel4 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel4 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LauncherViewModel viewModel5 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel5 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LauncherViewModel viewModel6 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel6 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 7:
                LauncherViewModel viewModel7 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel7 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel7.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 8:
                LauncherViewModel viewModel8 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel8 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel8.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 9:
                LauncherViewModel viewModel9 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel9 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel9.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 10:
                LauncherViewModel viewModel10 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel10 != null;
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

package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsTimeLayoutBindingImpl extends BmwId8SettingsTimeLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView10;
    private final ImageView mboundView12;
    private final ImageView mboundView14;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView5;
    private final ImageView mboundView7;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_time_back, 15);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_time_lay, 16);
    }

    public BmwId8SettingsTimeLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsTimeLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (RelativeLayout) bindings[11], (RelativeLayout) bindings[13], (RelativeLayout) bindings[6], (ImageView) bindings[15], (RelativeLayout) bindings[4], (RelativeLayout) bindings[8], (RelativeLayout) bindings[16], (RelativeLayout) bindings[1]);
        this.mDirtyFlags = -1L;
        this.bmwId8SettingsTime12.setTag(null);
        this.bmwId8SettingsTime24.setTag(null);
        this.bmwId8SettingsTimeAndroid.setTag(null);
        this.bmwId8SettingsTimeCar.setTag(null);
        this.bmwId8SettingsTimeFormat.setTag(null);
        this.bmwId8SettingsTimeSync.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[12];
        this.mboundView12 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[14];
        this.mboundView14 = imageView3;
        imageView3.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView4 = (ImageView) bindings[3];
        this.mboundView3 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) bindings[5];
        this.mboundView5 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag(null);
        TextView textView2 = (TextView) bindings[9];
        this.mboundView9 = textView2;
        textView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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

    @Override // com.wits.ksw.databinding.BmwId8SettingsTimeLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelTimeSyncShow((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelTimeFormatShow((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelTimeFormatState((ObservableInt) object, fieldId);
            case 3:
                return onChangeViewModelTimeSyncState((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelTimeSyncShow(ObservableBoolean ViewModelTimeSyncShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTimeFormatShow(ObservableBoolean ViewModelTimeFormatShow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTimeFormatState(ObservableInt ViewModelTimeFormatState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelTimeSyncState(ObservableInt ViewModelTimeSyncState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        Drawable viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
        Drawable viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN;
        String viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener;
        boolean viewModelTimeSyncShowGet;
        ObservableInt viewModelTimeSyncState;
        int viewModelTimeSyncStateGet;
        long dirtyFlags2;
        Context context;
        int i;
        String viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52;
        int viewModelTimeFormatStateGet;
        int viewModelTimeFormatStateGet2;
        long dirtyFlags3;
        Context context2;
        int i2;
        boolean viewModelTimeSyncShowGet2;
        long dirtyFlags4;
        Context context3;
        int i3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Drawable viewModelTimeFormatStateInt0MboundView14AndroidDrawableId8SettingsSystemSelectSelMboundView14AndroidDrawableId8SettingsSystemSelectN = null;
        ObservableBoolean viewModelTimeSyncShow = null;
        ObservableBoolean viewModelTimeFormatShow = null;
        int viewModelTimeSyncShowViewVISIBLEViewGONE = 0;
        ObservableInt viewModelTimeFormatState = null;
        Drawable viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown = null;
        Drawable viewModelTimeSyncStateInt0MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = null;
        int viewModelTimeFormatShowViewVISIBLEViewGONE = 0;
        String viewModelTimeFormatStateInt1MboundView9AndroidStringText6MboundView9AndroidStringText7 = null;
        Drawable viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2 = null;
        Drawable viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = null;
        Drawable viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2 = null;
        boolean viewModelTimeFormatShowGet = false;
        String viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53 = null;
        BmwId8SettingsViewModel viewModel = this.mViewModel;
        if ((dirtyFlags & 63) == 0) {
            viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = null;
            viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = null;
            viewModelOnClickAndroidViewViewOnClickListener = null;
        } else {
            if ((dirtyFlags & 49) == 0) {
                viewModelTimeSyncShowGet = false;
            } else {
                if (viewModel != null) {
                    viewModelTimeSyncShow = viewModel.timeSyncShow;
                }
                updateRegistration(0, viewModelTimeSyncShow);
                if (viewModelTimeSyncShow == null) {
                    viewModelTimeSyncShowGet2 = false;
                } else {
                    viewModelTimeSyncShowGet2 = viewModelTimeSyncShow.get();
                }
                if ((dirtyFlags & 49) != 0) {
                    if (viewModelTimeSyncShowGet2) {
                        dirtyFlags = dirtyFlags | 512 | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    } else {
                        dirtyFlags = dirtyFlags | 256 | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    }
                }
                viewModelTimeSyncShowViewVISIBLEViewGONE = viewModelTimeSyncShowGet2 ? 0 : 8;
                if (viewModelTimeSyncShowGet2) {
                    context3 = this.mboundView3.getContext();
                    dirtyFlags4 = dirtyFlags;
                    i3 = C0899R.C0900drawable.id8_settings_time_up;
                } else {
                    dirtyFlags4 = dirtyFlags;
                    context3 = this.mboundView3.getContext();
                    i3 = C0899R.C0900drawable.id8_settings_time_down;
                }
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = AppCompatResources.getDrawable(context3, i3);
                viewModelTimeSyncShowGet = viewModelTimeSyncShowGet2;
                dirtyFlags = dirtyFlags4;
            }
            if ((dirtyFlags & 50) != 0) {
                if (viewModel != null) {
                    viewModelTimeFormatShow = viewModel.timeFormatShow;
                }
                updateRegistration(1, viewModelTimeFormatShow);
                if (viewModelTimeFormatShow != null) {
                    viewModelTimeFormatShowGet = viewModelTimeFormatShow.get();
                }
                if ((dirtyFlags & 50) != 0) {
                    if (viewModelTimeFormatShowGet) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
                viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown = viewModelTimeFormatShowGet ? AppCompatResources.getDrawable(this.mboundView10.getContext(), C0899R.C0900drawable.id8_settings_time_up) : AppCompatResources.getDrawable(this.mboundView10.getContext(), C0899R.C0900drawable.id8_settings_time_down);
                viewModelTimeFormatShowViewVISIBLEViewGONE = viewModelTimeFormatShowGet ? 0 : 8;
            }
            if ((dirtyFlags & 52) != 0) {
                if (viewModel != null) {
                    viewModelTimeFormatState = viewModel.timeFormatState;
                }
                updateRegistration(2, viewModelTimeFormatState);
                if (viewModelTimeFormatState == null) {
                    viewModelTimeFormatStateGet = 0;
                } else {
                    int viewModelTimeFormatStateGet3 = viewModelTimeFormatState.get();
                    viewModelTimeFormatStateGet = viewModelTimeFormatStateGet3;
                }
                boolean viewModelTimeFormatStateInt0 = viewModelTimeFormatStateGet == 0;
                boolean viewModelTimeFormatStateInt1 = viewModelTimeFormatStateGet == 1;
                if ((dirtyFlags & 52) != 0) {
                    if (viewModelTimeFormatStateInt0) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
                if ((dirtyFlags & 52) != 0) {
                    if (viewModelTimeFormatStateInt1) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_URI | 8388608;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH | 4194304;
                    }
                }
                if (viewModelTimeFormatStateInt0) {
                    viewModelTimeFormatStateGet2 = viewModelTimeFormatStateGet;
                    context2 = this.mboundView14.getContext();
                    dirtyFlags3 = dirtyFlags;
                    i2 = C0899R.C0900drawable.id8_settings_system_select_sel;
                } else {
                    viewModelTimeFormatStateGet2 = viewModelTimeFormatStateGet;
                    dirtyFlags3 = dirtyFlags;
                    context2 = this.mboundView14.getContext();
                    i2 = C0899R.C0900drawable.id8_settings_system_select_n;
                }
                viewModelTimeFormatStateInt0MboundView14AndroidDrawableId8SettingsSystemSelectSelMboundView14AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(context2, i2);
                viewModelTimeFormatStateInt1MboundView9AndroidStringText6MboundView9AndroidStringText7 = this.mboundView9.getResources().getString(viewModelTimeFormatStateInt1 ? C0899R.string.text_6 : C0899R.string.text_7);
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2 = AppCompatResources.getDrawable(this.mboundView12.getContext(), viewModelTimeFormatStateInt1 ? C0899R.C0900drawable.id8_settings_system_select_sel : C0899R.C0900drawable.id8_settings_system_select_n);
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 56) != 0) {
                if (viewModel == null) {
                    viewModelTimeSyncState = null;
                } else {
                    viewModelTimeSyncState = viewModel.timeSyncState;
                }
                updateRegistration(3, viewModelTimeSyncState);
                if (viewModelTimeSyncState == null) {
                    viewModelTimeSyncStateGet = 0;
                } else {
                    int viewModelTimeSyncStateGet2 = viewModelTimeSyncState.get();
                    viewModelTimeSyncStateGet = viewModelTimeSyncStateGet2;
                }
                boolean viewModelTimeSyncStateInt1 = viewModelTimeSyncStateGet == 1;
                boolean viewModelTimeSyncStateInt0 = viewModelTimeSyncStateGet == 0;
                if ((dirtyFlags & 56) != 0) {
                    if (viewModelTimeSyncStateInt1) {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED | 33554432;
                    } else {
                        dirtyFlags = dirtyFlags | PlaybackStateCompat.ACTION_SET_REPEAT_MODE | 16777216;
                    }
                }
                if ((dirtyFlags & 56) != 0) {
                    if (viewModelTimeSyncStateInt0) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
                if (viewModelTimeSyncStateInt1) {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView5.getContext();
                    i = C0899R.C0900drawable.id8_settings_system_select_sel;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView5.getContext();
                    i = C0899R.C0900drawable.id8_settings_system_select_n;
                }
                Drawable viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3 = AppCompatResources.getDrawable(context, i);
                if (viewModelTimeSyncStateInt1) {
                    viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2 = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52 = this.mboundView2.getResources().getString(C0899R.string.text_4);
                } else {
                    viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2 = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52 = this.mboundView2.getResources().getString(C0899R.string.text_5);
                }
                viewModelTimeSyncStateInt0MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = viewModelTimeSyncStateInt0 ? AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_settings_system_select_sel) : AppCompatResources.getDrawable(this.mboundView7.getContext(), C0899R.C0900drawable.id8_settings_system_select_n);
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 48) != 0 && viewModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener2 = onClickListenerImpl.setValue(viewModel);
                viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2;
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53;
                viewModelOnClickAndroidViewViewOnClickListener = viewModelOnClickAndroidViewViewOnClickListener2;
            } else {
                viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2;
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53;
                viewModelOnClickAndroidViewViewOnClickListener = null;
            }
        }
        if ((dirtyFlags & 48) != 0) {
            this.bmwId8SettingsTime12.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsTime24.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsTimeAndroid.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsTimeCar.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsTimeFormat.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.bmwId8SettingsTimeSync.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 50) != 0) {
            this.bmwId8SettingsTime12.setVisibility(viewModelTimeFormatShowViewVISIBLEViewGONE);
            this.bmwId8SettingsTime24.setVisibility(viewModelTimeFormatShowViewVISIBLEViewGONE);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView10, viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown);
        }
        if ((dirtyFlags & 49) != 0) {
            this.bmwId8SettingsTimeAndroid.setVisibility(viewModelTimeSyncShowViewVISIBLEViewGONE);
            this.bmwId8SettingsTimeCar.setVisibility(viewModelTimeSyncShowViewVISIBLEViewGONE);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown);
        }
        if ((dirtyFlags & 52) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView12, viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView14, viewModelTimeFormatStateInt0MboundView14AndroidDrawableId8SettingsSystemSelectSelMboundView14AndroidDrawableId8SettingsSystemSelectN);
            TextViewBindingAdapter.setText(this.mboundView9, viewModelTimeFormatStateInt1MboundView9AndroidStringText6MboundView9AndroidStringText7);
        }
        if ((dirtyFlags & 56) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN);
            ImageViewBindingAdapter.setImageDrawable(this.mboundView7, viewModelTimeSyncStateInt0MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private BmwId8SettingsViewModel value;

        public OnClickListenerImpl setValue(BmwId8SettingsViewModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}

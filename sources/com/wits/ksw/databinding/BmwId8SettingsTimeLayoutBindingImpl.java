package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

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
        sparseIntArray.put(R.id.bmw_id8_settings_time_back, 15);
        sparseIntArray.put(R.id.bmw_id8_settings_time_lay, 16);
    }

    public BmwId8SettingsTimeLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BmwId8SettingsTimeLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[11], bindings[13], bindings[6], bindings[15], bindings[4], bindings[8], bindings[16], bindings[1]);
        this.mDirtyFlags = -1;
        this.bmwId8SettingsTime12.setTag((Object) null);
        this.bmwId8SettingsTime24.setTag((Object) null);
        this.bmwId8SettingsTimeAndroid.setTag((Object) null);
        this.bmwId8SettingsTimeCar.setTag((Object) null);
        this.bmwId8SettingsTimeFormat.setTag((Object) null);
        this.bmwId8SettingsTimeSync.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = bindings[12];
        this.mboundView12 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = bindings[14];
        this.mboundView14 = imageView3;
        imageView3.setTag((Object) null);
        TextView textView = bindings[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        ImageView imageView4 = bindings[3];
        this.mboundView3 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = bindings[5];
        this.mboundView5 = imageView5;
        imageView5.setTag((Object) null);
        ImageView imageView6 = bindings[7];
        this.mboundView7 = imageView6;
        imageView6.setTag((Object) null);
        TextView textView2 = bindings[9];
        this.mboundView9 = textView2;
        textView2.setTag((Object) null);
        View view = root;
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelTimeFormatShow(ObservableBoolean ViewModelTimeFormatShow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelTimeFormatState(ObservableInt ViewModelTimeFormatState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelTimeSyncState(ObservableInt ViewModelTimeSyncState, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener;
        String viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5;
        Drawable viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN;
        Drawable viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown;
        boolean viewModelTimeSyncShowGet;
        ObservableInt viewModelTimeSyncState;
        int viewModelTimeSyncStateGet;
        long dirtyFlags2;
        int i;
        Context context;
        String viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52;
        Drawable drawable;
        int viewModelTimeFormatStateGet;
        long dirtyFlags3;
        int viewModelTimeFormatStateGet2;
        int i2;
        Context context2;
        boolean viewModelTimeSyncShowGet2;
        long dirtyFlags4;
        int i3;
        Context context3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
        if ((dirtyFlags & 63) != 0) {
            int i4 = 8;
            if ((dirtyFlags & 49) != 0) {
                if (viewModel != null) {
                    viewModelTimeSyncShow = viewModel.timeSyncShow;
                }
                updateRegistration(0, (Observable) viewModelTimeSyncShow);
                if (viewModelTimeSyncShow != null) {
                    viewModelTimeSyncShowGet2 = viewModelTimeSyncShow.get();
                } else {
                    viewModelTimeSyncShowGet2 = false;
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
                    i3 = R.drawable.id8_settings_time_up;
                } else {
                    dirtyFlags4 = dirtyFlags;
                    context3 = this.mboundView3.getContext();
                    i3 = R.drawable.id8_settings_time_down;
                }
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2 = AppCompatResources.getDrawable(context3, i3);
                viewModelTimeSyncShowGet = viewModelTimeSyncShowGet2;
                dirtyFlags = dirtyFlags4;
            } else {
                viewModelTimeSyncShowGet = false;
            }
            boolean viewModelTimeSyncShowGet3 = viewModelTimeSyncShowGet;
            if ((dirtyFlags & 50) != 0) {
                if (viewModel != null) {
                    viewModelTimeFormatShow = viewModel.timeFormatShow;
                }
                updateRegistration(1, (Observable) viewModelTimeFormatShow);
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
                Drawable viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown2 = viewModelTimeFormatShowGet ? AppCompatResources.getDrawable(this.mboundView10.getContext(), R.drawable.id8_settings_time_up) : AppCompatResources.getDrawable(this.mboundView10.getContext(), R.drawable.id8_settings_time_down);
                if (viewModelTimeFormatShowGet) {
                    i4 = 0;
                }
                viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown = viewModelTimeFormatShowMboundView10AndroidDrawableId8SettingsTimeUpMboundView10AndroidDrawableId8SettingsTimeDown2;
                viewModelTimeFormatShowViewVISIBLEViewGONE = i4;
            }
            if ((dirtyFlags & 52) != 0) {
                if (viewModel != null) {
                    viewModelTimeFormatState = viewModel.timeFormatState;
                }
                updateRegistration(2, (Observable) viewModelTimeFormatState);
                if (viewModelTimeFormatState != null) {
                    viewModelTimeFormatStateGet = viewModelTimeFormatState.get();
                } else {
                    viewModelTimeFormatStateGet = 0;
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
                    i2 = R.drawable.id8_settings_system_select_sel;
                } else {
                    viewModelTimeFormatStateGet2 = viewModelTimeFormatStateGet;
                    dirtyFlags3 = dirtyFlags;
                    context2 = this.mboundView14.getContext();
                    i2 = R.drawable.id8_settings_system_select_n;
                }
                viewModelTimeFormatStateInt0MboundView14AndroidDrawableId8SettingsSystemSelectSelMboundView14AndroidDrawableId8SettingsSystemSelectN = AppCompatResources.getDrawable(context2, i2);
                viewModelTimeFormatStateInt1MboundView9AndroidStringText6MboundView9AndroidStringText7 = this.mboundView9.getResources().getString(viewModelTimeFormatStateInt1 ? R.string.text_6 : R.string.text_7);
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2 = AppCompatResources.getDrawable(this.mboundView12.getContext(), viewModelTimeFormatStateInt1 ? R.drawable.id8_settings_system_select_sel : R.drawable.id8_settings_system_select_n);
                boolean z = viewModelTimeFormatStateInt1;
                boolean z2 = viewModelTimeFormatStateInt0;
                int i5 = viewModelTimeFormatStateGet2;
                dirtyFlags = dirtyFlags3;
            }
            if ((dirtyFlags & 56) != 0) {
                if (viewModel != null) {
                    viewModelTimeSyncState = viewModel.timeSyncState;
                } else {
                    viewModelTimeSyncState = null;
                }
                updateRegistration(3, (Observable) viewModelTimeSyncState);
                if (viewModelTimeSyncState != null) {
                    viewModelTimeSyncStateGet = viewModelTimeSyncState.get();
                } else {
                    viewModelTimeSyncStateGet = 0;
                }
                ObservableInt observableInt = viewModelTimeSyncState;
                boolean viewModelTimeSyncStateInt0 = true;
                boolean viewModelTimeSyncStateInt1 = viewModelTimeSyncStateGet == 1;
                if (viewModelTimeSyncStateGet != 0) {
                    viewModelTimeSyncStateInt0 = false;
                }
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
                    i = R.drawable.id8_settings_system_select_sel;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.mboundView5.getContext();
                    i = R.drawable.id8_settings_system_select_n;
                }
                Drawable viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3 = AppCompatResources.getDrawable(context, i);
                if (viewModelTimeSyncStateInt1) {
                    viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2 = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52 = this.mboundView2.getResources().getString(R.string.text_4);
                } else {
                    viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2 = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN3;
                    viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52 = this.mboundView2.getResources().getString(R.string.text_5);
                }
                if (viewModelTimeSyncStateInt0) {
                    boolean z3 = viewModelTimeSyncStateInt0;
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), R.drawable.id8_settings_system_select_sel);
                } else {
                    drawable = AppCompatResources.getDrawable(this.mboundView7.getContext(), R.drawable.id8_settings_system_select_n);
                }
                viewModelTimeSyncStateInt0MboundView7AndroidDrawableId8SettingsSystemSelectSelMboundView7AndroidDrawableId8SettingsSystemSelectN = drawable;
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText52;
                int i6 = viewModelTimeSyncStateGet;
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 48) == 0 || viewModel == null) {
                viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2;
                boolean z4 = viewModelTimeSyncShowGet3;
                BmwId8SettingsViewModel bmwId8SettingsViewModel = viewModel;
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2;
                ObservableBoolean observableBoolean = viewModelTimeSyncShow;
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53;
                ObservableBoolean observableBoolean2 = viewModelTimeFormatShow;
                viewModelOnClickAndroidViewViewOnClickListener = null;
            } else {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener2 = onClickListenerImpl.setValue(viewModel);
                viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN2;
                viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown2;
                boolean z5 = viewModelTimeSyncShowGet3;
                BmwId8SettingsViewModel bmwId8SettingsViewModel2 = viewModel;
                viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN2;
                ObservableBoolean observableBoolean3 = viewModelTimeSyncShow;
                viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText53;
                ObservableBoolean observableBoolean4 = viewModelTimeFormatShow;
                viewModelOnClickAndroidViewViewOnClickListener = viewModelOnClickAndroidViewViewOnClickListener2;
            }
        } else {
            viewModelTimeSyncStateInt1MboundView5AndroidDrawableId8SettingsSystemSelectSelMboundView5AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelTimeSyncShowMboundView3AndroidDrawableId8SettingsTimeUpMboundView3AndroidDrawableId8SettingsTimeDown = null;
            BmwId8SettingsViewModel bmwId8SettingsViewModel3 = viewModel;
            viewModelTimeFormatStateInt1MboundView12AndroidDrawableId8SettingsSystemSelectSelMboundView12AndroidDrawableId8SettingsSystemSelectN = null;
            viewModelTimeSyncStateInt1MboundView2AndroidStringText4MboundView2AndroidStringText5 = null;
            viewModelOnClickAndroidViewViewOnClickListener = null;
        }
        if ((dirtyFlags & 48) != 0) {
            ObservableInt observableInt2 = viewModelTimeFormatState;
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

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BmwId8SettingsViewModel value;

        public OnClickListenerImpl setValue(BmwId8SettingsViewModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}

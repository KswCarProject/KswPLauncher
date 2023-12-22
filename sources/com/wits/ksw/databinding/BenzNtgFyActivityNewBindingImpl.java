package com.wits.ksw.databinding;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.content.res.AppCompatResources;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

/* loaded from: classes7.dex */
public class BenzNtgFyActivityNewBindingImpl extends BenzNtgFyActivityNewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mViewModelOnControlClickAndroidViewViewOnClickListener;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.layout_main_ntg_fy, 6);
        sparseIntArray.put(C0899R.C0901id.iv_coat_fy, 7);
        sparseIntArray.put(C0899R.C0901id.tv_coat_fy_tip, 8);
    }

    public BenzNtgFyActivityNewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private BenzNtgFyActivityNewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (RecyclerView) bindings[2], (ViewPager) bindings[1], (ImageView) bindings[5], (LinearLayout) bindings[3], (ImageView) bindings[7], (LinearLayout) bindings[4], (LinearLayout) bindings[6], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.benzMbux2021KswNewRecycle.setTag(null);
        this.benzMbux2021Viewpager.setTag(null);
        this.controlBtn.setTag(null);
        this.indicatorBenzNtg6Fy.setTag(null);
        this.layoutCoatBenzFy.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setViewModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BenzNtgFyActivityNewBinding
    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeViewModelIsEditState((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeViewModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelControlBeanBenzControlPanelState(ObservableBoolean ViewModelControlBeanBenzControlPanelState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEditState(ObservableBoolean ViewModelIsEditState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelControlBeanControlPanelClose(ObservableBoolean ViewModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        View.OnClickListener viewModelOnControlClickAndroidViewViewOnClickListener;
        int viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE;
        long dirtyFlags2;
        Context context;
        int i;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean viewModelControlBeanBenzControlPanelStateGet = false;
        int viewModelIsEditStateViewINVISIBLEViewVISIBLE = 0;
        boolean viewModelControlBeanControlPanelCloseGet = false;
        ObservableBoolean viewModelControlBeanBenzControlPanelState = null;
        Drawable viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        int viewModelIsEditStateViewGONEViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        int viewModelIsEditStateViewVISIBLEViewINVISIBLE = 0;
        ObservableBoolean viewModelIsEditState = null;
        ObservableBoolean viewModelControlBeanControlPanelClose = null;
        ControlBean viewModelControlBean = null;
        BcVieModel viewModel = this.mViewModel;
        boolean viewModelIsEditStateGet = false;
        if ((dirtyFlags & 31) == 0) {
            viewModelOnControlClickAndroidViewViewOnClickListener = null;
        } else {
            if ((dirtyFlags & 30) != 0) {
                if (viewModel != null) {
                    viewModelIsEditState = viewModel.isEditState;
                }
                updateRegistration(1, viewModelIsEditState);
                if (viewModelIsEditState != null) {
                    viewModelIsEditStateGet = viewModelIsEditState.get();
                }
                if ((dirtyFlags & 26) != 0) {
                    if (viewModelIsEditStateGet) {
                        dirtyFlags = dirtyFlags | 256 | PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags = dirtyFlags | 128 | PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                if ((dirtyFlags & 30) != 0) {
                    if (viewModelIsEditStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if ((dirtyFlags & 26) != 0) {
                    viewModelIsEditStateViewINVISIBLEViewVISIBLE = viewModelIsEditStateGet ? 4 : 0;
                    viewModelIsEditStateViewVISIBLEViewINVISIBLE = viewModelIsEditStateGet ? 0 : 4;
                }
            }
            if ((dirtyFlags & 25) != 0) {
                if (viewModel != null) {
                    viewModelControlBean = viewModel.controlBean;
                }
                if (viewModelControlBean != null) {
                    viewModelControlBeanBenzControlPanelState = viewModelControlBean.benzControlPanelState;
                }
                updateRegistration(0, viewModelControlBeanBenzControlPanelState);
                if (viewModelControlBeanBenzControlPanelState != null) {
                    viewModelControlBeanBenzControlPanelStateGet = viewModelControlBeanBenzControlPanelState.get();
                }
                if ((dirtyFlags & 25) != 0) {
                    if (viewModelControlBeanBenzControlPanelStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= 512;
                    }
                }
                if (viewModelControlBeanBenzControlPanelStateGet) {
                    context = this.controlBtn.getContext();
                    dirtyFlags2 = dirtyFlags;
                    i = C0899R.C0900drawable.ntg55_ctrlpanel_down_selector;
                } else {
                    dirtyFlags2 = dirtyFlags;
                    context = this.controlBtn.getContext();
                    i = C0899R.C0900drawable.ntg55_ctrlpanel_up_selector;
                }
                viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = AppCompatResources.getDrawable(context, i);
                dirtyFlags = dirtyFlags2;
            }
            if ((dirtyFlags & 24) != 0 && viewModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mViewModelOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                View.OnClickListener viewModelOnControlClickAndroidViewViewOnClickListener2 = onClickListenerImpl.setValue(viewModel);
                viewModelOnControlClickAndroidViewViewOnClickListener = viewModelOnControlClickAndroidViewViewOnClickListener2;
            } else {
                viewModelOnControlClickAndroidViewViewOnClickListener = null;
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) == 0) {
            viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        } else {
            if (viewModel != null) {
                viewModelControlBean = viewModel.controlBean;
            }
            if (viewModelControlBean != null) {
                viewModelControlBeanControlPanelClose = viewModelControlBean.controlPanelClose;
            }
            updateRegistration(2, viewModelControlBeanControlPanelClose);
            if (viewModelControlBeanControlPanelClose != null) {
                viewModelControlBeanControlPanelCloseGet = viewModelControlBeanControlPanelClose.get();
            }
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0) {
                if (viewModelControlBeanControlPanelCloseGet) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
            viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = viewModelControlBeanControlPanelCloseGet ? 8 : 0;
        }
        if ((dirtyFlags & 30) != 0) {
            viewModelIsEditStateViewGONEViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE = viewModelIsEditStateGet ? 8 : viewModelControlBeanControlPanelCloseViewGONEViewVISIBLE;
        }
        if ((dirtyFlags & 26) != 0) {
            this.benzMbux2021KswNewRecycle.setVisibility(viewModelIsEditStateViewVISIBLEViewINVISIBLE);
            this.benzMbux2021Viewpager.setVisibility(viewModelIsEditStateViewINVISIBLEViewVISIBLE);
            this.indicatorBenzNtg6Fy.setVisibility(viewModelIsEditStateViewINVISIBLEViewVISIBLE);
            this.layoutCoatBenzFy.setVisibility(viewModelIsEditStateViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 24) != 0) {
            this.controlBtn.setOnClickListener(viewModelOnControlClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 25) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, viewModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 30) != 0) {
            this.controlBtn.setVisibility(viewModelIsEditStateViewGONEViewModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
    }

    /* loaded from: classes7.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl setValue(BcVieModel value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onControlClick(arg0);
        }
    }
}

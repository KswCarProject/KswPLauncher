package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

/* loaded from: classes7.dex */
public class BenzMbux2021KswActivityBindingImpl extends BenzMbux2021KswActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mVieModelOnControlClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.layout_main_2021_2, 3);
        sparseIntArray.put(C0899R.C0901id.benzMbux2021Viewpager, 4);
        sparseIntArray.put(C0899R.C0901id.indicator_benz_mbux_2021, 5);
        sparseIntArray.put(C0899R.C0901id.layout_coat_benz_mbux_2021, 6);
        sparseIntArray.put(C0899R.C0901id.iv_coat_2021, 7);
        sparseIntArray.put(C0899R.C0901id.tv_coat_2021_tip, 8);
    }

    public BenzMbux2021KswActivityBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private BenzMbux2021KswActivityBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ViewPager) bindings[4], (ImageView) bindings[2], (LinearLayout) bindings[5], (ImageView) bindings[7], (LinearLayout) bindings[6], (ImageView) bindings[1], (RelativeLayout) bindings[3], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.controlBtn.setTag(null);
        this.layoutMain2021.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
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
        if (24 == variableId) {
            setVieModel((BcVieModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BenzMbux2021KswActivityBinding
    public void setVieModel(BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(24);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVieModelControlBeanControlPanelClose((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVieModelMediaInfoPageIndex((ObservableInt) object, fieldId);
            case 2:
                return onChangeVieModelMediaInfoPicOther((ObservableField) object, fieldId);
            case 3:
                return onChangeVieModelControlBeanBenzControlPanelState((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVieModelControlBeanControlPanelClose(ObservableBoolean VieModelControlBeanControlPanelClose, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVieModelMediaInfoPageIndex(ObservableInt VieModelMediaInfoPageIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVieModelMediaInfoPicOther(ObservableField<BitmapDrawable> VieModelMediaInfoPicOther, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVieModelControlBeanBenzControlPanelState(ObservableBoolean VieModelControlBeanBenzControlPanelState, int fieldId) {
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
        BitmapDrawable vieModelMediaInfoPicOtherGet;
        Drawable vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp;
        int vieModelMediaInfoPageIndexGet;
        ObservableBoolean vieModelControlBeanBenzControlPanelState;
        ObservableBoolean vieModelControlBeanControlPanelClose;
        Drawable drawable;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ObservableBoolean vieModelControlBeanControlPanelClose2 = null;
        View.OnClickListener vieModelOnControlClickAndroidViewViewOnClickListener = null;
        BcVieModel vieModel = this.mVieModel;
        Drawable vieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTransp = null;
        boolean vieModelMediaInfoPageIndexInt1 = false;
        boolean vieModelControlBeanControlPanelCloseGet = false;
        ObservableInt vieModelMediaInfoPageIndex = null;
        ControlBean vieModelControlBean = null;
        ObservableField<BitmapDrawable> vieModelMediaInfoPicOther = null;
        int vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = 0;
        Drawable vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = null;
        boolean vieModelMediaInfoPicOtherJavaLangObjectNull = false;
        boolean vieModelControlBeanBenzControlPanelStateGet = false;
        BitmapDrawable vieModelMediaInfoPicOtherGet2 = null;
        if ((dirtyFlags & 57) != 0) {
            if ((dirtyFlags & 48) != 0 && vieModel != null) {
                OnClickListenerImpl onClickListenerImpl = this.mVieModelOnControlClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mVieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                vieModelOnControlClickAndroidViewViewOnClickListener = onClickListenerImpl.setValue(vieModel);
            }
            if (vieModel != null) {
                vieModelControlBean = vieModel.controlBean;
            }
            if ((dirtyFlags & 49) != 0) {
                if (vieModelControlBean != null) {
                    vieModelControlBeanControlPanelClose2 = vieModelControlBean.controlPanelClose;
                }
                updateRegistration(0, vieModelControlBeanControlPanelClose2);
                if (vieModelControlBeanControlPanelClose2 != null) {
                    vieModelControlBeanControlPanelCloseGet = vieModelControlBeanControlPanelClose2.get();
                }
                if ((dirtyFlags & 49) != 0) {
                    if (vieModelControlBeanControlPanelCloseGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE = vieModelControlBeanControlPanelCloseGet ? 8 : 0;
            }
            if ((dirtyFlags & 56) != 0) {
                if (vieModelControlBean == null) {
                    vieModelControlBeanBenzControlPanelState = null;
                } else {
                    vieModelControlBeanBenzControlPanelState = vieModelControlBean.benzControlPanelState;
                }
                updateRegistration(3, vieModelControlBeanBenzControlPanelState);
                if (vieModelControlBeanBenzControlPanelState != null) {
                    vieModelControlBeanBenzControlPanelStateGet = vieModelControlBeanBenzControlPanelState.get();
                }
                if ((dirtyFlags & 56) != 0) {
                    if (vieModelControlBeanBenzControlPanelStateGet) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
                if (vieModelControlBeanBenzControlPanelStateGet) {
                    vieModelControlBeanControlPanelClose = vieModelControlBeanControlPanelClose2;
                    drawable = AppCompatResources.getDrawable(this.controlBtn.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_down_selector);
                } else {
                    vieModelControlBeanControlPanelClose = vieModelControlBeanControlPanelClose2;
                    drawable = AppCompatResources.getDrawable(this.controlBtn.getContext(), C0899R.C0900drawable.ntg55_ctrlpanel_up_selector);
                }
                vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = drawable;
                vieModelControlBeanControlPanelClose2 = vieModelControlBeanControlPanelClose;
            }
        }
        if ((dirtyFlags & 38) != 0) {
            MediaInfo vieModelMediaInfo = BcVieModel.mediaInfo;
            if (vieModelMediaInfo != null) {
                vieModelMediaInfoPageIndex = vieModelMediaInfo.pageIndex;
            }
            updateRegistration(1, vieModelMediaInfoPageIndex);
            if (vieModelMediaInfoPageIndex == null) {
                vieModelMediaInfoPageIndexGet = 0;
            } else {
                int vieModelMediaInfoPageIndexGet2 = vieModelMediaInfoPageIndex.get();
                vieModelMediaInfoPageIndexGet = vieModelMediaInfoPageIndexGet2;
            }
            vieModelMediaInfoPageIndexInt1 = vieModelMediaInfoPageIndexGet == 1;
            if ((dirtyFlags & 38) != 0) {
                if (vieModelMediaInfoPageIndexInt1) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 36) != 0) {
                if (vieModelMediaInfo != null) {
                    vieModelMediaInfoPicOther = vieModelMediaInfo.picOther;
                }
                updateRegistration(2, vieModelMediaInfoPicOther);
                if (vieModelMediaInfoPicOther != null) {
                    vieModelMediaInfoPicOtherGet2 = vieModelMediaInfoPicOther.get();
                }
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) == 0) {
            vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
        } else {
            MediaInfo vieModelMediaInfo2 = BcVieModel.mediaInfo;
            if (vieModelMediaInfo2 != null) {
                vieModelMediaInfoPicOther = vieModelMediaInfo2.picOther;
            }
            updateRegistration(2, vieModelMediaInfoPicOther);
            if (vieModelMediaInfoPicOther != null) {
                vieModelMediaInfoPicOtherGet2 = vieModelMediaInfoPicOther.get();
            }
            vieModelMediaInfoPicOtherJavaLangObjectNull = vieModelMediaInfoPicOtherGet2 != null;
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) == 0) {
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            } else if (vieModelMediaInfoPicOtherJavaLangObjectNull) {
                dirtyFlags |= 128;
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            } else {
                dirtyFlags |= 64;
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            vieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTransp = vieModelMediaInfoPicOtherJavaLangObjectNull ? vieModelMediaInfoPicOtherGet : AppCompatResources.getDrawable(this.layoutMain2021.getContext(), C0899R.C0900drawable.transp);
        }
        if ((dirtyFlags & 38) == 0) {
            vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp = null;
        } else {
            vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp = vieModelMediaInfoPageIndexInt1 ? vieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTransp : AppCompatResources.getDrawable(this.layoutMain2021.getContext(), C0899R.C0900drawable.transp);
        }
        if ((dirtyFlags & 48) != 0) {
            this.controlBtn.setOnClickListener(vieModelOnControlClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 56) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.controlBtn, vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector);
        }
        if ((dirtyFlags & 49) != 0) {
            this.controlBtn.setVisibility(vieModelControlBeanControlPanelCloseViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 36) != 0) {
            ViewBindingAdapter.setBackground(this.layoutMain2021, vieModelMediaInfoPicOtherGet);
        }
        if ((dirtyFlags & 38) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.layoutMain2021, vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp);
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

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.ControlBean;

public class BenzMbux2021KswActivityBindingImpl extends BenzMbux2021KswActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mVieModelOnControlClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layout_main_2021_2, 3);
        sparseIntArray.put(R.id.benzMbux2021Viewpager, 4);
        sparseIntArray.put(R.id.indicator_benz_mbux_2021, 5);
        sparseIntArray.put(R.id.layout_coat_benz_mbux_2021, 6);
        sparseIntArray.put(R.id.iv_coat_2021, 7);
        sparseIntArray.put(R.id.tv_coat_2021_tip, 8);
    }

    public BenzMbux2021KswActivityBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BenzMbux2021KswActivityBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[4], bindings[2], bindings[5], bindings[7], bindings[6], bindings[1], bindings[3], bindings[8]);
        this.mDirtyFlags = -1;
        this.controlBtn.setTag((Object) null);
        this.layoutMain2021.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
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
        if (24 != variableId) {
            return false;
        }
        setVieModel((BcVieModel) variable);
        return true;
    }

    public void setVieModel(BcVieModel VieModel) {
        this.mVieModel = VieModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(24);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVieModelMediaInfoPageIndex(ObservableInt VieModelMediaInfoPageIndex, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVieModelMediaInfoPicOther(ObservableField<BitmapDrawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVieModelControlBeanBenzControlPanelState(ObservableBoolean VieModelControlBeanBenzControlPanelState, int fieldId) {
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
        BitmapDrawable vieModelMediaInfoPicOtherGet;
        Drawable vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp;
        int vieModelMediaInfoPageIndexGet;
        ObservableBoolean vieModelControlBeanBenzControlPanelState;
        ObservableBoolean vieModelControlBeanControlPanelClose;
        Drawable drawable;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
            if (!((dirtyFlags & 48) == 0 || vieModel == null)) {
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
                updateRegistration(0, (Observable) vieModelControlBeanControlPanelClose2);
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
                if (vieModelControlBean != null) {
                    vieModelControlBeanBenzControlPanelState = vieModelControlBean.benzControlPanelState;
                } else {
                    vieModelControlBeanBenzControlPanelState = null;
                }
                updateRegistration(3, (Observable) vieModelControlBeanBenzControlPanelState);
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
                    drawable = AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_down_selector);
                } else {
                    vieModelControlBeanControlPanelClose = vieModelControlBeanControlPanelClose2;
                    drawable = AppCompatResources.getDrawable(this.controlBtn.getContext(), R.drawable.ntg55_ctrlpanel_up_selector);
                }
                vieModelControlBeanBenzControlPanelStateControlBtnAndroidDrawableNtg55CtrlpanelDownSelectorControlBtnAndroidDrawableNtg55CtrlpanelUpSelector = drawable;
                ObservableBoolean observableBoolean = vieModelControlBeanBenzControlPanelState;
                vieModelControlBeanControlPanelClose2 = vieModelControlBeanControlPanelClose;
            }
        }
        if ((dirtyFlags & 38) != 0) {
            MediaInfo vieModelMediaInfo = BcVieModel.mediaInfo;
            if (vieModelMediaInfo != null) {
                vieModelMediaInfoPageIndex = vieModelMediaInfo.pageIndex;
            }
            updateRegistration(1, (Observable) vieModelMediaInfoPageIndex);
            if (vieModelMediaInfoPageIndex != null) {
                ObservableBoolean observableBoolean2 = vieModelControlBeanControlPanelClose2;
                vieModelMediaInfoPageIndexGet = vieModelMediaInfoPageIndex.get();
            } else {
                ObservableBoolean observableBoolean3 = vieModelControlBeanControlPanelClose2;
                vieModelMediaInfoPageIndexGet = 0;
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
                updateRegistration(2, (Observable) vieModelMediaInfoPicOther);
                if (vieModelMediaInfoPicOther != null) {
                    vieModelMediaInfoPicOtherGet2 = vieModelMediaInfoPicOther.get();
                    int i = vieModelMediaInfoPageIndexGet;
                    MediaInfo mediaInfo = vieModelMediaInfo;
                } else {
                    int i2 = vieModelMediaInfoPageIndexGet;
                    MediaInfo mediaInfo2 = vieModelMediaInfo;
                }
            } else {
                int i3 = vieModelMediaInfoPageIndexGet;
                MediaInfo mediaInfo3 = vieModelMediaInfo;
            }
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            MediaInfo vieModelMediaInfo2 = BcVieModel.mediaInfo;
            if (vieModelMediaInfo2 != null) {
                vieModelMediaInfoPicOther = vieModelMediaInfo2.picOther;
            }
            updateRegistration(2, (Observable) vieModelMediaInfoPicOther);
            if (vieModelMediaInfoPicOther != null) {
                vieModelMediaInfoPicOtherGet2 = vieModelMediaInfoPicOther.get();
            }
            vieModelMediaInfoPicOtherJavaLangObjectNull = vieModelMediaInfoPicOtherGet2 != null;
            if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) == 0) {
                MediaInfo mediaInfo4 = vieModelMediaInfo2;
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            } else if (vieModelMediaInfoPicOtherJavaLangObjectNull) {
                dirtyFlags |= 128;
                MediaInfo mediaInfo5 = vieModelMediaInfo2;
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            } else {
                dirtyFlags |= 64;
                MediaInfo mediaInfo6 = vieModelMediaInfo2;
                vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
            }
        } else {
            vieModelMediaInfoPicOtherGet = vieModelMediaInfoPicOtherGet2;
        }
        if ((dirtyFlags & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            vieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTransp = vieModelMediaInfoPicOtherJavaLangObjectNull ? vieModelMediaInfoPicOtherGet : AppCompatResources.getDrawable(this.layoutMain2021.getContext(), R.drawable.transp);
        }
        if ((dirtyFlags & 38) != 0) {
            vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp = vieModelMediaInfoPageIndexInt1 ? vieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTransp : AppCompatResources.getDrawable(this.layoutMain2021.getContext(), R.drawable.transp);
        } else {
            vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp = null;
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
        if ((dirtyFlags & 38) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.layoutMain2021, vieModelMediaInfoPageIndexInt1VieModelMediaInfoPicOtherJavaLangObjectNullVieModelMediaInfoPicOtherLayoutMain2021AndroidDrawableTranspLayoutMain2021AndroidDrawableTransp);
        }
        if ((dirtyFlags & 36) != 0) {
            ViewBindingAdapter.setBackground(this.layoutMain2021, vieModelMediaInfoPicOtherGet);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BcVieModel value;

        public OnClickListenerImpl setValue(BcVieModel value2) {
            this.value = value2;
            if (value2 == null) {
                return null;
            }
            return this;
        }

        public void onClick(View arg0) {
            this.value.onControlClick(arg0);
        }
    }
}

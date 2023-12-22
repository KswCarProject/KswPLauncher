package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public class VideoPempDataBindingImpl extends VideoPempDataBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback518;
    private final View.OnClickListener mCallback519;
    private long mDirtyFlags;
    private final ImageView mboundView2;

    public VideoPempDataBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private VideoPempDataBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[1], (RelativeLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.ivMask.setTag(null);
        this.llContainer.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        setRootTag(root);
        this.mCallback518 = new OnClickListener(this, 1);
        this.mCallback519 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (10 == variableId) {
            setMediaViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.VideoPempDataBinding
    public void setMediaViewModel(LauncherViewModel MediaViewModel) {
        this.mMediaViewModel = MediaViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMediaViewModelBThirdVideo((ObservableField) object, fieldId);
            case 1:
                return onChangeMediaViewModelMediaInfoVideoPlay((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMediaViewModelBThirdVideo(ObservableField<Boolean> MediaViewModelBThirdVideo, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMediaViewModelMediaInfoVideoPlay(ObservableField<Boolean> MediaViewModelMediaInfoVideoPlay, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        View.OnFocusChangeListener mediaViewModelVideoViewFocusChangeListener = null;
        Boolean mediaViewModelBThirdVideoGet = null;
        Drawable mediaViewModelMediaInfoVideoPlayMboundView2AndroidDrawablePempId8MainIconVideoPauseMboundView2AndroidDrawablePempId8MainIconVideoPlay = null;
        ObservableField<Boolean> mediaViewModelMediaInfoVideoPlay = null;
        int mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE = 0;
        Boolean mediaViewModelMediaInfoVideoPlayGet = null;
        LauncherViewModel mediaViewModel = this.mMediaViewModel;
        if ((dirtyFlags & 9) != 0) {
            ObservableField<Boolean> mediaViewModelBThirdVideo = LauncherViewModel.bThirdVideo;
            updateRegistration(0, mediaViewModelBThirdVideo);
            if (mediaViewModelBThirdVideo != null) {
                Boolean mediaViewModelBThirdVideoGet2 = mediaViewModelBThirdVideo.get();
                mediaViewModelBThirdVideoGet = mediaViewModelBThirdVideoGet2;
            }
            boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet = ViewDataBinding.safeUnbox(mediaViewModelBThirdVideoGet);
            boolean mediaViewModelBThirdVideoBooleanTrue = androidDatabindingViewDataBindingSafeUnboxMediaViewModelBThirdVideoGet;
            if ((dirtyFlags & 9) != 0) {
                if (mediaViewModelBThirdVideoBooleanTrue) {
                    dirtyFlags |= 128;
                } else {
                    dirtyFlags |= 64;
                }
            }
            mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE = mediaViewModelBThirdVideoBooleanTrue ? 8 : 0;
        }
        if ((dirtyFlags & 10) != 0) {
            MediaInfo mediaViewModelMediaInfo = LauncherViewModel.mediaInfo;
            if (mediaViewModelMediaInfo != null) {
                mediaViewModelMediaInfoVideoPlay = mediaViewModelMediaInfo.videoPlay;
            }
            updateRegistration(1, mediaViewModelMediaInfoVideoPlay);
            if (mediaViewModelMediaInfoVideoPlay != null) {
                Boolean mediaViewModelMediaInfoVideoPlayGet2 = mediaViewModelMediaInfoVideoPlay.get();
                mediaViewModelMediaInfoVideoPlayGet = mediaViewModelMediaInfoVideoPlayGet2;
            }
            boolean androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet = ViewDataBinding.safeUnbox(mediaViewModelMediaInfoVideoPlayGet);
            if ((dirtyFlags & 10) != 0) {
                if (androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet) {
                    dirtyFlags |= 32;
                } else {
                    dirtyFlags |= 16;
                }
            }
            mediaViewModelMediaInfoVideoPlayMboundView2AndroidDrawablePempId8MainIconVideoPauseMboundView2AndroidDrawablePempId8MainIconVideoPlay = AppCompatResources.getDrawable(this.mboundView2.getContext(), androidDatabindingViewDataBindingSafeUnboxMediaViewModelMediaInfoVideoPlayGet ? C0899R.C0900drawable.pemp_id8_main_icon_video_pause : C0899R.C0900drawable.pemp_id8_main_icon_video_play);
        }
        if ((dirtyFlags & 12) != 0 && mediaViewModel != null) {
            mediaViewModelVideoViewFocusChangeListener = mediaViewModel.videoViewFocusChangeListener;
        }
        if ((dirtyFlags & 8) != 0) {
            this.ivMask.setOnClickListener(this.mCallback518);
            this.mboundView2.setOnClickListener(this.mCallback519);
        }
        if ((12 & dirtyFlags) != 0) {
            this.ivMask.setOnFocusChangeListener(mediaViewModelVideoViewFocusChangeListener);
        }
        if ((dirtyFlags & 10) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView2, mediaViewModelMediaInfoVideoPlayMboundView2AndroidDrawablePempId8MainIconVideoPauseMboundView2AndroidDrawablePempId8MainIconVideoPlay);
        }
        if ((dirtyFlags & 9) != 0) {
            this.mboundView2.setVisibility(mediaViewModelBThirdVideoBooleanTrueViewGONEViewVISIBLE);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean mediaViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LauncherViewModel mediaViewModel = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LauncherViewModel mediaViewModel2 = this.mMediaViewModel;
                mediaViewModelJavaLangObjectNull = mediaViewModel2 != null;
                if (mediaViewModelJavaLangObjectNull) {
                    mediaViewModel2.id8GsOpenPauseVideo(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

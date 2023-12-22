package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3RightMediaBinding extends ViewDataBinding {
    public final ImageView IvRightMusicIcon;
    public final RelativeLayout KSWA4LRightShowMedia;
    public final Button btnMusicNext;
    public final Button btnMusicPlayPause;
    public final Button btnMusicPrev;
    public final ImageView infoBg;
    @Bindable
    protected AudiViewModel mVm;
    public final TextView tvMusicTitleInfor;

    public abstract void setVm(AudiViewModel vm);

    protected AudiMib3RightMediaBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView IvRightMusicIcon, RelativeLayout KSWA4LRightShowMedia, Button btnMusicNext, Button btnMusicPlayPause, Button btnMusicPrev, ImageView infoBg, TextView tvMusicTitleInfor) {
        super(_bindingComponent, _root, _localFieldCount);
        this.IvRightMusicIcon = IvRightMusicIcon;
        this.KSWA4LRightShowMedia = KSWA4LRightShowMedia;
        this.btnMusicNext = btnMusicNext;
        this.btnMusicPlayPause = btnMusicPlayPause;
        this.btnMusicPrev = btnMusicPrev;
        this.infoBg = infoBg;
        this.tvMusicTitleInfor = tvMusicTitleInfor;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3RightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3RightMediaBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_media, root, attachToRoot, component);
    }

    public static AudiMib3RightMediaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightMediaBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3RightMediaBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_media, null, false, component);
    }

    public static AudiMib3RightMediaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightMediaBinding bind(View view, Object component) {
        return (AudiMib3RightMediaBinding) bind(component, view, C0899R.C0902layout.audi_mib3_right_media);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
public abstract class AudiRightMediaBinding extends ViewDataBinding {
    public final ImageView IvRightMusicIcon;
    public final View KSWA4LRightShowMedia;
    public final Button btnMusicNext;
    public final Button btnMusicPlayPause;
    public final Button btnMusicPrev;
    public final ImageView infoBg;
    public final LinearLayout linearLayout7;
    @Bindable
    protected AudiViewModel mVm;
    public final TextView tvMusicTitleInfor;

    public abstract void setVm(AudiViewModel vm);

    protected AudiRightMediaBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView IvRightMusicIcon, View KSWA4LRightShowMedia, Button btnMusicNext, Button btnMusicPlayPause, Button btnMusicPrev, ImageView infoBg, LinearLayout linearLayout7, TextView tvMusicTitleInfor) {
        super(_bindingComponent, _root, _localFieldCount);
        this.IvRightMusicIcon = IvRightMusicIcon;
        this.KSWA4LRightShowMedia = KSWA4LRightShowMedia;
        this.btnMusicNext = btnMusicNext;
        this.btnMusicPlayPause = btnMusicPlayPause;
        this.btnMusicPrev = btnMusicPrev;
        this.infoBg = infoBg;
        this.linearLayout7 = linearLayout7;
        this.tvMusicTitleInfor = tvMusicTitleInfor;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightMediaBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_media, root, attachToRoot, component);
    }

    public static AudiRightMediaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightMediaBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_media, null, false, component);
    }

    public static AudiRightMediaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding bind(View view, Object component) {
        return (AudiRightMediaBinding) bind(component, view, C0899R.C0902layout.audi_right_media);
    }
}

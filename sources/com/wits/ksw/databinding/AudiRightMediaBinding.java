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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

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

    public abstract void setVm(AudiViewModel audiViewModel);

    protected AudiRightMediaBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView IvRightMusicIcon2, View KSWA4LRightShowMedia2, Button btnMusicNext2, Button btnMusicPlayPause2, Button btnMusicPrev2, ImageView infoBg2, LinearLayout linearLayout72, TextView tvMusicTitleInfor2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.IvRightMusicIcon = IvRightMusicIcon2;
        this.KSWA4LRightShowMedia = KSWA4LRightShowMedia2;
        this.btnMusicNext = btnMusicNext2;
        this.btnMusicPlayPause = btnMusicPlayPause2;
        this.btnMusicPrev = btnMusicPrev2;
        this.infoBg = infoBg2;
        this.linearLayout7 = linearLayout72;
        this.tvMusicTitleInfor = tvMusicTitleInfor2;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightMediaBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_media, root, attachToRoot, component);
    }

    public static AudiRightMediaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightMediaBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_media, (ViewGroup) null, false, component);
    }

    public static AudiRightMediaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightMediaBinding bind(View view, Object component) {
        return (AudiRightMediaBinding) bind(component, view, R.layout.audi_right_media);
    }
}

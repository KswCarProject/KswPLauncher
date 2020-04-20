package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiRightMediaBinding extends ViewDataBinding {
    @NonNull
    public final ImageView IvRightMusicIcon;
    @NonNull
    public final RelativeLayout KSWA4LRightShowMedia;
    @NonNull
    public final Button btnMusicNext;
    @NonNull
    public final Button btnMusicPlayPause;
    @NonNull
    public final Button btnMusicPrev;
    @NonNull
    public final ImageView infoBg;
    @Bindable
    protected AudiViewModel mVm;
    @NonNull
    public final TextView tvMusicTitleInfor;

    public abstract void setVm(@Nullable AudiViewModel audiViewModel);

    protected AudiRightMediaBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView IvRightMusicIcon2, RelativeLayout KSWA4LRightShowMedia2, Button btnMusicNext2, Button btnMusicPlayPause2, Button btnMusicPrev2, ImageView infoBg2, TextView tvMusicTitleInfor2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.IvRightMusicIcon = IvRightMusicIcon2;
        this.KSWA4LRightShowMedia = KSWA4LRightShowMedia2;
        this.btnMusicNext = btnMusicNext2;
        this.btnMusicPlayPause = btnMusicPlayPause2;
        this.btnMusicPrev = btnMusicPrev2;
        this.infoBg = infoBg2;
        this.tvMusicTitleInfor = tvMusicTitleInfor2;
    }

    @Nullable
    public AudiViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiRightMediaBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightMediaBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiRightMediaBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_media, root, attachToRoot, component);
    }

    @NonNull
    public static AudiRightMediaBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightMediaBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiRightMediaBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_media, (ViewGroup) null, false, component);
    }

    public static AudiRightMediaBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiRightMediaBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiRightMediaBinding) bind(component, view, R.layout.audi_right_media);
    }
}

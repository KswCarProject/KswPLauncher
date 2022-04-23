package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class AlsId7UiMediaBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final AlsId7UiMusicSubViewBinding musicInclude;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected AlsId7UiMediaBinding(Object _bindingComponent, View _root, int _localFieldCount, AlsId7UiMusicSubViewBinding musicInclude2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.musicInclude = musicInclude2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static AlsId7UiMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMediaBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiMediaBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_als_id7_ui_media, root, attachToRoot, component);
    }

    public static AlsId7UiMediaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMediaBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiMediaBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_als_id7_ui_media, (ViewGroup) null, false, component);
    }

    public static AlsId7UiMediaBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMediaBinding bind(View view, Object component) {
        return (AlsId7UiMediaBinding) bind(component, view, R.layout.fragment_als_id7_ui_media);
    }
}

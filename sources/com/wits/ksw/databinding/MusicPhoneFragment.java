package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public abstract class MusicPhoneFragment extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mMusicPhoneViewModel;

    public abstract void setMusicPhoneViewModel(AlsID7ViewModel alsID7ViewModel);

    protected MusicPhoneFragment(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AlsID7ViewModel getMusicPhoneViewModel() {
        return this.mMusicPhoneViewModel;
    }

    public static MusicPhoneFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPhoneFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicPhoneFragment) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_fragment_music, root, attachToRoot, component);
    }

    public static MusicPhoneFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPhoneFragment inflate(LayoutInflater inflater, Object component) {
        return (MusicPhoneFragment) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_fragment_music, (ViewGroup) null, false, component);
    }

    public static MusicPhoneFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPhoneFragment bind(View view, Object component) {
        return (MusicPhoneFragment) bind(component, view, R.layout.als_id7_fragment_music);
    }
}

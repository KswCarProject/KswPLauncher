package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class AlsId7SubPhoneViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView dayTextView;
    @Bindable
    protected AlsID7ViewModel mMusicPhoneViewModel;
    @NonNull
    public final TextView monthTextView;
    @NonNull
    public final TextView phoneConnectionTextView;
    @NonNull
    public final ConstraintLayout phoneConstraintLayout;
    @NonNull
    public final CustomBmwImageView phoneImageView;
    @NonNull
    public final TextView textView2;

    public abstract void setMusicPhoneViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubPhoneViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView dayTextView2, TextView monthTextView2, TextView phoneConnectionTextView2, ConstraintLayout phoneConstraintLayout2, CustomBmwImageView phoneImageView2, TextView textView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dayTextView = dayTextView2;
        this.monthTextView = monthTextView2;
        this.phoneConnectionTextView = phoneConnectionTextView2;
        this.phoneConstraintLayout = phoneConstraintLayout2;
        this.phoneImageView = phoneImageView2;
        this.textView2 = textView22;
    }

    @Nullable
    public AlsID7ViewModel getMusicPhoneViewModel() {
        return this.mMusicPhoneViewModel;
    }

    @NonNull
    public static AlsId7SubPhoneViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubPhoneViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AlsId7SubPhoneViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_phone_view, root, attachToRoot, component);
    }

    @NonNull
    public static AlsId7SubPhoneViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubPhoneViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AlsId7SubPhoneViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_phone_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubPhoneViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AlsId7SubPhoneViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AlsId7SubPhoneViewBinding) bind(component, view, R.layout.als_id7_sub_phone_view);
    }
}

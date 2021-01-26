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

public abstract class AlsId7SubVideoViewBinding extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;
    @NonNull
    public final TextView textView2;
    @NonNull
    public final TextView textView3;
    @NonNull
    public final ConstraintLayout videoConstraintLayout;
    @NonNull
    public final CustomBmwImageView videoImageView;

    public abstract void setDashVideoViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubVideoViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView textView22, TextView textView32, ConstraintLayout videoConstraintLayout2, CustomBmwImageView videoImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.videoConstraintLayout = videoConstraintLayout2;
        this.videoImageView = videoImageView2;
    }

    @Nullable
    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    @NonNull
    public static AlsId7SubVideoViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubVideoViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AlsId7SubVideoViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_video_view, root, attachToRoot, component);
    }

    @NonNull
    public static AlsId7SubVideoViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubVideoViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AlsId7SubVideoViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_video_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubVideoViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AlsId7SubVideoViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AlsId7SubVideoViewBinding) bind(component, view, R.layout.als_id7_sub_video_view);
    }
}

package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
    public final TextView textView2;
    public final TextView textView3;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView videoImageView;

    public abstract void setDashVideoViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubVideoViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView textView22, TextView textView32, ConstraintLayout videoConstraintLayout2, CustomBmwImageView videoImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.videoConstraintLayout = videoConstraintLayout2;
        this.videoImageView = videoImageView2;
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static AlsId7SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_video_view, root, attachToRoot, component);
    }

    public static AlsId7SubVideoViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_video_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubVideoViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewBinding bind(View view, Object component) {
        return (AlsId7SubVideoViewBinding) bind(component, view, R.layout.als_id7_sub_video_view);
    }
}

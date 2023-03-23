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

public abstract class AlsId7SubVideoViewV2Binding extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView videoImageView;

    public abstract void setDashVideoViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubVideoViewV2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView textView22, TextView textView32, ConstraintLayout videoConstraintLayout2, CustomBmwImageView videoImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.videoConstraintLayout = videoConstraintLayout2;
        this.videoImageView = videoImageView2;
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubVideoViewV2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_video_view_v2, root, attachToRoot, component);
    }

    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubVideoViewV2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_video_view_v2, (ViewGroup) null, false, component);
    }

    public static AlsId7SubVideoViewV2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding bind(View view, Object component) {
        return (AlsId7SubVideoViewV2Binding) bind(component, view, R.layout.als_id7_sub_video_view_v2);
    }
}

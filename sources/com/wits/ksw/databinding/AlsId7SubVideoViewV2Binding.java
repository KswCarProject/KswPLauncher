package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7SubVideoViewV2Binding extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView videoImageView;

    public abstract void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel);

    protected AlsId7SubVideoViewV2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView textView2, TextView textView3, ConstraintLayout videoConstraintLayout, CustomBmwImageView videoImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.videoConstraintLayout = videoConstraintLayout;
        this.videoImageView = videoImageView;
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubVideoViewV2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_video_view_v2, root, attachToRoot, component);
    }

    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubVideoViewV2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_video_view_v2, null, false, component);
    }

    public static AlsId7SubVideoViewV2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubVideoViewV2Binding bind(View view, Object component) {
        return (AlsId7SubVideoViewV2Binding) bind(component, view, C0899R.C0902layout.als_id7_sub_video_view_v2);
    }
}

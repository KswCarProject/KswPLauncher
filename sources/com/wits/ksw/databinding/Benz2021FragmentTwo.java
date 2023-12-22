package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class Benz2021FragmentTwo extends ViewDataBinding {
    public final TextView btTip;
    public final RelativeLayout carRl;
    public final LinearLayout fragmentTwoLl;
    public final ImageView ivMusic1;
    public final ImageView ivMusic2;
    public final ImageView ivSet1;
    public final ImageView ivSet2;
    public final ImageView ivVideo1;
    public final ImageView ivVideo2;
    @Bindable
    protected BcVieModel mViewModel;
    public final FrameLayout musicItemview;
    public final RelativeLayout musicRl;
    public final TextView musicTip;
    public final TextView musicTv;
    public final BenzMbuxItemView setItemview;
    public final TextView setTip;
    public final TextView setTv;
    public final View space;
    public final View space1;
    public final View space2;
    public final BenzMbuxItemView videoItemview;
    public final RelativeLayout videoRl;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel viewModel);

    protected Benz2021FragmentTwo(Object _bindingComponent, View _root, int _localFieldCount, TextView btTip, RelativeLayout carRl, LinearLayout fragmentTwoLl, ImageView ivMusic1, ImageView ivMusic2, ImageView ivSet1, ImageView ivSet2, ImageView ivVideo1, ImageView ivVideo2, FrameLayout musicItemview, RelativeLayout musicRl, TextView musicTip, TextView musicTv, BenzMbuxItemView setItemview, TextView setTip, TextView setTv, View space, View space1, View space2, BenzMbuxItemView videoItemview, RelativeLayout videoRl, TextView videoTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btTip = btTip;
        this.carRl = carRl;
        this.fragmentTwoLl = fragmentTwoLl;
        this.ivMusic1 = ivMusic1;
        this.ivMusic2 = ivMusic2;
        this.ivSet1 = ivSet1;
        this.ivSet2 = ivSet2;
        this.ivVideo1 = ivVideo1;
        this.ivVideo2 = ivVideo2;
        this.musicItemview = musicItemview;
        this.musicRl = musicRl;
        this.musicTip = musicTip;
        this.musicTv = musicTv;
        this.setItemview = setItemview;
        this.setTip = setTip;
        this.setTv = setTv;
        this.space = space;
        this.space1 = space1;
        this.space2 = space2;
        this.videoItemview = videoItemview;
        this.videoRl = videoRl;
        this.videoTv = videoTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021FragmentTwo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_two, root, attachToRoot, component);
    }

    public static Benz2021FragmentTwo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, Object component) {
        return (Benz2021FragmentTwo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_two, null, false, component);
    }

    public static Benz2021FragmentTwo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo bind(View view, Object component) {
        return (Benz2021FragmentTwo) bind(component, view, C0899R.C0902layout.benz_mbux_2021_fragment_two);
    }
}

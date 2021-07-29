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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

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

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Benz2021FragmentTwo(Object _bindingComponent, View _root, int _localFieldCount, TextView btTip2, RelativeLayout carRl2, LinearLayout fragmentTwoLl2, ImageView ivMusic12, ImageView ivMusic22, ImageView ivSet12, ImageView ivSet22, ImageView ivVideo12, ImageView ivVideo22, FrameLayout musicItemview2, RelativeLayout musicRl2, TextView musicTip2, TextView musicTv2, BenzMbuxItemView setItemview2, TextView setTip2, TextView setTv2, View space3, View space12, View space22, BenzMbuxItemView videoItemview2, RelativeLayout videoRl2, TextView videoTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btTip = btTip2;
        this.carRl = carRl2;
        this.fragmentTwoLl = fragmentTwoLl2;
        this.ivMusic1 = ivMusic12;
        this.ivMusic2 = ivMusic22;
        this.ivSet1 = ivSet12;
        this.ivSet2 = ivSet22;
        this.ivVideo1 = ivVideo12;
        this.ivVideo2 = ivVideo22;
        this.musicItemview = musicItemview2;
        this.musicRl = musicRl2;
        this.musicTip = musicTip2;
        this.musicTv = musicTv2;
        this.setItemview = setItemview2;
        this.setTip = setTip2;
        this.setTv = setTv2;
        this.space = space3;
        this.space1 = space12;
        this.space2 = space22;
        this.videoItemview = videoItemview2;
        this.videoRl = videoRl2;
        this.videoTv = videoTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021FragmentTwo) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_2021_fragment_two, root, attachToRoot, component);
    }

    public static Benz2021FragmentTwo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo inflate(LayoutInflater inflater, Object component) {
        return (Benz2021FragmentTwo) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_2021_fragment_two, (ViewGroup) null, false, component);
    }

    public static Benz2021FragmentTwo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentTwo bind(View view, Object component) {
        return (Benz2021FragmentTwo) bind(component, view, R.layout.benz_mbux_2021_fragment_two);
    }
}

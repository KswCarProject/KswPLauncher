package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public abstract class AudiMib3TyOneBinding extends ViewDataBinding {
    public final TextView btContent;
    public final LinearLayout btContentLl;
    public final BenzMbuxItemView btItemview;
    public final ImageView btIv;
    public final TextView btTv;
    public final LinearLayout fragmentOneLl;
    @Bindable
    protected BcVieModel mViewModel;
    public final TextView musicContent;
    public final LinearLayout musicContentLl;
    public final BenzMbuxItemView musicItemview;
    public final ImageView musicIv;
    public final TextView musicTv;
    public final TextView naviContent;
    public final LinearLayout naviContentLl;
    public final BenzMbuxItemView naviItemview;
    public final ImageView naviIv;
    public final TextView naviTv;
    public final TextView phonelinkContent;
    public final LinearLayout phonelinkContentLl;
    public final BenzMbuxItemView phonelinkItemview;
    public final ImageView phonelinkIv;
    public final TextView phonelinkTv;
    public final TextView videoContent;
    public final LinearLayout videoContentLl;
    public final BenzMbuxItemView videoItemview;
    public final ImageView videoIv;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3TyOneBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btContent2, LinearLayout btContentLl2, BenzMbuxItemView btItemview2, ImageView btIv2, TextView btTv2, LinearLayout fragmentOneLl2, TextView musicContent2, LinearLayout musicContentLl2, BenzMbuxItemView musicItemview2, ImageView musicIv2, TextView musicTv2, TextView naviContent2, LinearLayout naviContentLl2, BenzMbuxItemView naviItemview2, ImageView naviIv2, TextView naviTv2, TextView phonelinkContent2, LinearLayout phonelinkContentLl2, BenzMbuxItemView phonelinkItemview2, ImageView phonelinkIv2, TextView phonelinkTv2, TextView videoContent2, LinearLayout videoContentLl2, BenzMbuxItemView videoItemview2, ImageView videoIv2, TextView videoTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btContent = btContent2;
        this.btContentLl = btContentLl2;
        this.btItemview = btItemview2;
        this.btIv = btIv2;
        this.btTv = btTv2;
        this.fragmentOneLl = fragmentOneLl2;
        this.musicContent = musicContent2;
        this.musicContentLl = musicContentLl2;
        this.musicItemview = musicItemview2;
        this.musicIv = musicIv2;
        this.musicTv = musicTv2;
        this.naviContent = naviContent2;
        this.naviContentLl = naviContentLl2;
        this.naviItemview = naviItemview2;
        this.naviIv = naviIv2;
        this.naviTv = naviTv2;
        this.phonelinkContent = phonelinkContent2;
        this.phonelinkContentLl = phonelinkContentLl2;
        this.phonelinkItemview = phonelinkItemview2;
        this.phonelinkIv = phonelinkIv2;
        this.phonelinkTv = phonelinkTv2;
        this.videoContent = videoContent2;
        this.videoContentLl = videoContentLl2;
        this.videoItemview = videoItemview2;
        this.videoIv = videoIv2;
        this.videoTv = videoTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_ty_one, root, attachToRoot, component);
    }

    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_ty_one, (ViewGroup) null, false, component);
    }

    public static AudiMib3TyOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding bind(View view, Object component) {
        return (AudiMib3TyOneBinding) bind(component, view, R.layout.fragment_audi_mib3_ty_one);
    }
}

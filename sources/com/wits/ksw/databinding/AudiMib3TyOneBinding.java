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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3TyOneBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btContent, LinearLayout btContentLl, BenzMbuxItemView btItemview, ImageView btIv, TextView btTv, LinearLayout fragmentOneLl, TextView musicContent, LinearLayout musicContentLl, BenzMbuxItemView musicItemview, ImageView musicIv, TextView musicTv, TextView naviContent, LinearLayout naviContentLl, BenzMbuxItemView naviItemview, ImageView naviIv, TextView naviTv, TextView phonelinkContent, LinearLayout phonelinkContentLl, BenzMbuxItemView phonelinkItemview, ImageView phonelinkIv, TextView phonelinkTv, TextView videoContent, LinearLayout videoContentLl, BenzMbuxItemView videoItemview, ImageView videoIv, TextView videoTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btContent = btContent;
        this.btContentLl = btContentLl;
        this.btItemview = btItemview;
        this.btIv = btIv;
        this.btTv = btTv;
        this.fragmentOneLl = fragmentOneLl;
        this.musicContent = musicContent;
        this.musicContentLl = musicContentLl;
        this.musicItemview = musicItemview;
        this.musicIv = musicIv;
        this.musicTv = musicTv;
        this.naviContent = naviContent;
        this.naviContentLl = naviContentLl;
        this.naviItemview = naviItemview;
        this.naviIv = naviIv;
        this.naviTv = naviTv;
        this.phonelinkContent = phonelinkContent;
        this.phonelinkContentLl = phonelinkContentLl;
        this.phonelinkItemview = phonelinkItemview;
        this.phonelinkIv = phonelinkIv;
        this.phonelinkTv = phonelinkTv;
        this.videoContent = videoContent;
        this.videoContentLl = videoContentLl;
        this.videoItemview = videoItemview;
        this.videoIv = videoIv;
        this.videoTv = videoTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_ty_one, root, attachToRoot, component);
    }

    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_ty_one, null, false, component);
    }

    public static AudiMib3TyOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyOneBinding bind(View view, Object component) {
        return (AudiMib3TyOneBinding) bind(component, view, C0899R.C0902layout.fragment_audi_mib3_ty_one);
    }
}

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class ActivityAudiBinding extends ViewDataBinding {
    @NonNull
    public final TextView auSetEqItem;
    @NonNull
    public final TextView auSetFacItem;
    @NonNull
    public final TextView auSetLagItem;
    @NonNull
    public final TextView auSetMoreItem;
    @NonNull
    public final TextView auSetNaviItem;
    @NonNull
    public final TextView auSetSoundItem;
    @NonNull
    public final TextView auSetSysItem;
    @NonNull
    public final TextView auSetSysinfoItem;
    @NonNull
    public final TextView auSetTimeItem;
    @NonNull
    public final AudiConstraintLayout audiHomeParentPanel;

    protected ActivityAudiBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView auSetEqItem2, TextView auSetFacItem2, TextView auSetLagItem2, TextView auSetMoreItem2, TextView auSetNaviItem2, TextView auSetSoundItem2, TextView auSetSysItem2, TextView auSetSysinfoItem2, TextView auSetTimeItem2, AudiConstraintLayout audiHomeParentPanel2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.auSetEqItem = auSetEqItem2;
        this.auSetFacItem = auSetFacItem2;
        this.auSetLagItem = auSetLagItem2;
        this.auSetMoreItem = auSetMoreItem2;
        this.auSetNaviItem = auSetNaviItem2;
        this.auSetSoundItem = auSetSoundItem2;
        this.auSetSysItem = auSetSysItem2;
        this.auSetSysinfoItem = auSetSysinfoItem2;
        this.auSetTimeItem = auSetTimeItem2;
        this.audiHomeParentPanel = audiHomeParentPanel2;
    }

    @NonNull
    public static ActivityAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityAudiBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityAudiBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityAudiBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi, (ViewGroup) null, false, component);
    }

    public static ActivityAudiBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityAudiBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityAudiBinding) bind(component, view, R.layout.activity_audi);
    }
}

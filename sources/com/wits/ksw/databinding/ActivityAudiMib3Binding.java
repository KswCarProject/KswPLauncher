package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public abstract class ActivityAudiMib3Binding extends ViewDataBinding {
    public final TextView auSetEqItem;
    public final TextView auSetFacItem;
    public final TextView auSetLagItem;
    public final TextView auSetMoreItem;
    public final TextView auSetNaviItem;
    public final TextView auSetSoundItem;
    public final TextView auSetSysItem;
    public final TextView auSetSysinfoItem;
    public final TextView auSetTimeItem;
    public final AudiConstraintLayout audiHomeParentPanel;

    protected ActivityAudiMib3Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView auSetEqItem, TextView auSetFacItem, TextView auSetLagItem, TextView auSetMoreItem, TextView auSetNaviItem, TextView auSetSoundItem, TextView auSetSysItem, TextView auSetSysinfoItem, TextView auSetTimeItem, AudiConstraintLayout audiHomeParentPanel) {
        super(_bindingComponent, _root, _localFieldCount);
        this.auSetEqItem = auSetEqItem;
        this.auSetFacItem = auSetFacItem;
        this.auSetLagItem = auSetLagItem;
        this.auSetMoreItem = auSetMoreItem;
        this.auSetNaviItem = auSetNaviItem;
        this.auSetSoundItem = auSetSoundItem;
        this.auSetSysItem = auSetSysItem;
        this.auSetSysinfoItem = auSetSysinfoItem;
        this.auSetTimeItem = auSetTimeItem;
        this.audiHomeParentPanel = audiHomeParentPanel;
    }

    public static ActivityAudiMib3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3, root, attachToRoot, component);
    }

    public static ActivityAudiMib3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3, null, false, component);
    }

    public static ActivityAudiMib3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3Binding bind(View view, Object component) {
        return (ActivityAudiMib3Binding) bind(component, view, C0899R.C0902layout.activity_audi_mib3);
    }
}

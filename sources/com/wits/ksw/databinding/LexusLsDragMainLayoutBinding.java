package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.lexusls.drag.DeleteZone;
import com.wits.ksw.launcher.view.lexusls.drag.DragLayer;

/* loaded from: classes7.dex */
public abstract class LexusLsDragMainLayoutBinding extends ViewDataBinding {
    public final ImageView deleteImg;
    public final DeleteZone demoDelZone;
    public final DragLayer demoDraglayer;
    public final ImageView ivLexusLsDragDesktop;
    public final ImageView ivLexusLsDragLeftBtn;
    public final ImageView ivLexusLsDragMb;
    public final ImageView ivLexusLsDragRightBtn;
    public final RelativeLayout lexusLsDragMainLl;
    public final RelativeLayout llLexusLsDragBottom;
    public final TextView mInstallTxt;
    @Bindable
    protected LauncherViewModel mLexusLsViewModel;
    public final RecyclerView recyclerviewDrag;

    public abstract void setLexusLsViewModel(LauncherViewModel LexusLsViewModel);

    protected LexusLsDragMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView deleteImg, DeleteZone demoDelZone, DragLayer demoDraglayer, ImageView ivLexusLsDragDesktop, ImageView ivLexusLsDragLeftBtn, ImageView ivLexusLsDragMb, ImageView ivLexusLsDragRightBtn, RelativeLayout lexusLsDragMainLl, RelativeLayout llLexusLsDragBottom, TextView mInstallTxt, RecyclerView recyclerviewDrag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deleteImg = deleteImg;
        this.demoDelZone = demoDelZone;
        this.demoDraglayer = demoDraglayer;
        this.ivLexusLsDragDesktop = ivLexusLsDragDesktop;
        this.ivLexusLsDragLeftBtn = ivLexusLsDragLeftBtn;
        this.ivLexusLsDragMb = ivLexusLsDragMb;
        this.ivLexusLsDragRightBtn = ivLexusLsDragRightBtn;
        this.lexusLsDragMainLl = lexusLsDragMainLl;
        this.llLexusLsDragBottom = llLexusLsDragBottom;
        this.mInstallTxt = mInstallTxt;
        this.recyclerviewDrag = recyclerviewDrag;
    }

    public LauncherViewModel getLexusLsViewModel() {
        return this.mLexusLsViewModel;
    }

    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsDragMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_drag_main_layout, root, attachToRoot, component);
    }

    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (LexusLsDragMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_drag_main_layout, null, false, component);
    }

    public static LexusLsDragMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding bind(View view, Object component) {
        return (LexusLsDragMainLayoutBinding) bind(component, view, C0899R.C0902layout.lexus_ls_drag_main_layout);
    }
}

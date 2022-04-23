package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.lexusls.drag.DeleteZone;
import com.wits.ksw.launcher.view.lexusls.drag.DragLayer;

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

    public abstract void setLexusLsViewModel(LauncherViewModel launcherViewModel);

    protected LexusLsDragMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView deleteImg2, DeleteZone demoDelZone2, DragLayer demoDraglayer2, ImageView ivLexusLsDragDesktop2, ImageView ivLexusLsDragLeftBtn2, ImageView ivLexusLsDragMb2, ImageView ivLexusLsDragRightBtn2, RelativeLayout lexusLsDragMainLl2, RelativeLayout llLexusLsDragBottom2, TextView mInstallTxt2, RecyclerView recyclerviewDrag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deleteImg = deleteImg2;
        this.demoDelZone = demoDelZone2;
        this.demoDraglayer = demoDraglayer2;
        this.ivLexusLsDragDesktop = ivLexusLsDragDesktop2;
        this.ivLexusLsDragLeftBtn = ivLexusLsDragLeftBtn2;
        this.ivLexusLsDragMb = ivLexusLsDragMb2;
        this.ivLexusLsDragRightBtn = ivLexusLsDragRightBtn2;
        this.lexusLsDragMainLl = lexusLsDragMainLl2;
        this.llLexusLsDragBottom = llLexusLsDragBottom2;
        this.mInstallTxt = mInstallTxt2;
        this.recyclerviewDrag = recyclerviewDrag2;
    }

    public LauncherViewModel getLexusLsViewModel() {
        return this.mLexusLsViewModel;
    }

    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsDragMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_drag_main_layout, root, attachToRoot, component);
    }

    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (LexusLsDragMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_drag_main_layout, (ViewGroup) null, false, component);
    }

    public static LexusLsDragMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsDragMainLayoutBinding bind(View view, Object component) {
        return (LexusLsDragMainLayoutBinding) bind(component, view, R.layout.lexus_ls_drag_main_layout);
    }
}

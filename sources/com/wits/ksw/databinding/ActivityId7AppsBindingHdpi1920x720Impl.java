package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.view.DragGridView;

public class ActivityId7AppsBindingHdpi1920x720Impl extends ActivityId7AppsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ActivityId7AppsBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private ActivityId7AppsBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1]);
        this.mDirtyFlags = -1;
        this.appGridView.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        if (1 != variableId) {
            return false;
        }
        setAppViewModel((AppViewModel) variable);
        return true;
    }

    public void setAppViewModel(AppViewModel AppViewModel) {
        this.mAppViewModel = AppViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeAppViewModelListAdpater((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeAppViewModelListAdpater(ObservableField<BaseListAdpater<AppInfo>> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        DragGridView.onItemChangerListener appViewModelOnItemChangerListener = null;
        AdapterView.OnItemClickListener appViewModelOnItemClickListener = null;
        BaseListAdpater<AppInfo> appViewModelListAdpaterGet = null;
        AppViewModel appViewModel = this.mAppViewModel;
        AdapterView.OnItemLongClickListener appViewModelOnItemLongClickListener = null;
        ObservableField<BaseListAdpater<AppInfo>> appViewModelListAdpater = null;
        if ((dirtyFlags & 7) != 0) {
            if (!((dirtyFlags & 6) == 0 || appViewModel == null)) {
                appViewModelOnItemChangerListener = appViewModel.onItemChangerListener;
                appViewModelOnItemClickListener = appViewModel.onItemClickListener;
                appViewModelOnItemLongClickListener = appViewModel.onItemLongClickListener;
            }
            if (appViewModel != null) {
                appViewModelListAdpater = appViewModel.listAdpater;
            }
            updateRegistration(0, (Observable) appViewModelListAdpater);
            if (appViewModelListAdpater != null) {
                appViewModelListAdpaterGet = appViewModelListAdpater.get();
            }
        }
        if ((7 & dirtyFlags) != 0) {
            BaseBindingModel.setAdpater(this.appGridView, appViewModelListAdpaterGet);
        }
        if ((dirtyFlags & 6) != 0) {
            this.appGridView.setOnItemClickListener(appViewModelOnItemClickListener);
            this.appGridView.setOnItemLongClickListener(appViewModelOnItemLongClickListener);
            BaseBindingModel.setOnItemChangerListener(this.appGridView, appViewModelOnItemChangerListener);
        }
    }
}

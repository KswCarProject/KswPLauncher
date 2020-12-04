package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.model.AppViewModel;

public class ActivityId7AppsBindingSw600dpLandImpl extends ActivityId7AppsBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    public ActivityId7AppsBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private ActivityId7AppsBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, bindings[1]);
        this.mDirtyFlags = -1;
        this.appGridView.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (14 != variableId) {
            return false;
        }
        setAppViewModel((AppViewModel) variable);
        return true;
    }

    public void setAppViewModel(@Nullable AppViewModel AppViewModel) {
        this.mAppViewModel = AppViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeAppViewModelListAdpater((ObservableField) object, fieldId);
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
        AppViewModel appViewModel = this.mAppViewModel;
        ObservableField<BaseListAdpater<AppInfo>> appViewModelListAdpater = null;
        BaseListAdpater<AppInfo> appViewModelListAdpaterGet = null;
        AdapterView.OnItemClickListener appViewModelOnItemClickListener = null;
        AdapterView.OnItemLongClickListener appViewModelOnItemLongClickListener = null;
        if ((dirtyFlags & 7) != 0) {
            if (appViewModel != null) {
                appViewModelListAdpater = appViewModel.listAdpater;
            }
            updateRegistration(0, (Observable) appViewModelListAdpater);
            if (appViewModelListAdpater != null) {
                appViewModelListAdpaterGet = appViewModelListAdpater.get();
            }
            if (!((dirtyFlags & 6) == 0 || appViewModel == null)) {
                appViewModelOnItemClickListener = appViewModel.onItemClickListener;
                appViewModelOnItemLongClickListener = appViewModel.onItemLongClickListener;
            }
        }
        if ((7 & dirtyFlags) != 0) {
            BaseBindingModel.setAdpater(this.appGridView, appViewModelListAdpaterGet);
        }
        if ((dirtyFlags & 6) != 0) {
            this.appGridView.setOnItemClickListener(appViewModelOnItemClickListener);
            this.appGridView.setOnItemLongClickListener(appViewModelOnItemLongClickListener);
        }
    }
}

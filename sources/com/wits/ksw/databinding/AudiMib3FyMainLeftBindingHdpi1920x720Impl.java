package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class AudiMib3FyMainLeftBindingHdpi1920x720Impl extends AudiMib3FyMainLeftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_music_audimib3, 1);
        sparseIntArray.put(R.id.iv_navi_audimib3, 2);
        sparseIntArray.put(R.id.iv_bt_audimib3, 3);
        sparseIntArray.put(R.id.iv_set_audimib3, 4);
        sparseIntArray.put(R.id.iv_car_audimib3, 5);
    }

    public AudiMib3FyMainLeftBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private AudiMib3FyMainLeftBindingHdpi1920x720Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[3], bindings[5], bindings[1], bindings[2], bindings[4]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((BcVieModel) variable);
        return true;
    }

    public void setViewModel(BcVieModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}

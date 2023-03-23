package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public class BmwId8SettingsFactoryLayoutBindingImpl extends BmwId8SettingsFactoryLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bmw_id8_settings_factory_psw, 1);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_1_btn, 2);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_2_btn, 3);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_3_btn, 4);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_del_btn, 5);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_4_btn, 6);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_5_btn, 7);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_6_btn, 8);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_0_btn, 9);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_7_btn, 10);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_8_btn, 11);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_9_btn, 12);
        sparseIntArray.put(R.id.bmw_id8_settings_factory_enter_btn, 13);
    }

    public BmwId8SettingsFactoryLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsFactoryLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[9], bindings[2], bindings[3], bindings[4], bindings[6], bindings[7], bindings[8], bindings[10], bindings[11], bindings[12], bindings[5], bindings[13], bindings[1]);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
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
        if (25 != variableId) {
            return false;
        }
        setViewModel((BmwId8SettingsViewModel) variable);
        return true;
    }

    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
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

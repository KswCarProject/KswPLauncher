package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public class BmwId8SettingsFactoryLayoutBindingImpl extends BmwId8SettingsFactoryLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_psw, 1);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_1_btn, 2);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_2_btn, 3);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_3_btn, 4);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_del_btn, 5);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_4_btn, 6);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_5_btn, 7);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_6_btn, 8);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_0_btn, 9);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_7_btn, 10);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_8_btn, 11);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_9_btn, 12);
        sparseIntArray.put(C0899R.C0901id.bmw_id8_settings_factory_enter_btn, 13);
    }

    public BmwId8SettingsFactoryLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private BmwId8SettingsFactoryLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[9], (ImageView) bindings[2], (ImageView) bindings[3], (ImageView) bindings[4], (ImageView) bindings[6], (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[10], (ImageView) bindings[11], (ImageView) bindings[12], (ImageView) bindings[5], (ImageView) bindings[13], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // android.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (25 == variableId) {
            setViewModel((BmwId8SettingsViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BmwId8SettingsFactoryLayoutBinding
    public void setViewModel(BmwId8SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
    }
}

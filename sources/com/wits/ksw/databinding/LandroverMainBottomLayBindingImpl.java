package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;

public class LandroverMainBottomLayBindingImpl extends LandroverMainBottomLayBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.landrover_main_bottom_return_btn, 1);
        sViewsWithIds.put(R.id.landrover_main_bottom_menu_btn, 2);
        sViewsWithIds.put(R.id.landrover_main_bottom_setup_btn, 3);
        sViewsWithIds.put(R.id.landrover_main_bottom_dvr_btn, 4);
        sViewsWithIds.put(R.id.landrover_main_bottom_gps_btn, 5);
        sViewsWithIds.put(R.id.landrover_main_bottom_bt_btn, 6);
        sViewsWithIds.put(R.id.landrover_main_bottom_video_btn, 7);
        sViewsWithIds.put(R.id.landrover_main_bottom_air_btn, 8);
        sViewsWithIds.put(R.id.landrover_main_bottom_radar_btn, 9);
        sViewsWithIds.put(R.id.landrover_main_bottom_parkassist_btn, 10);
        sViewsWithIds.put(R.id.landrover_main_bottom_off_btn, 11);
    }

    public LandroverMainBottomLayBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LandroverMainBottomLayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[8], bindings[6], bindings[4], bindings[5], bindings[2], bindings[11], bindings[10], bindings[9], bindings[1], bindings[3], bindings[7]);
        this.mDirtyFlags = -1;
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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
        return true;
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

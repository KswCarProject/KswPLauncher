package com.wits.ksw.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.view.LoopRotarySwitchView;

public class ActivityMainAudiBindingSw600dpLandImpl extends ActivityMainAudiBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(38);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;
    @Nullable
    private final AudiRightLogoBinding mboundView01;
    @Nullable
    private final AudiRightCarinfoBinding mboundView02;
    @Nullable
    private final AudiRightNaviBinding mboundView03;
    @Nullable
    private final AudiRightMediaBinding mboundView04;

    static {
        sIncludes.setIncludes(0, new String[]{"audi_right_logo", "audi_right_carinfo", "audi_right_navi", "audi_right_media"}, new int[]{4, 5, 6, 7}, new int[]{R.layout.audi_right_logo, R.layout.audi_right_carinfo, R.layout.audi_right_navi, R.layout.audi_right_media});
        sViewsWithIds.put(R.id.rl_navi, 8);
        sViewsWithIds.put(R.id.iv_navi, 9);
        sViewsWithIds.put(R.id.tv_navi, 10);
        sViewsWithIds.put(R.id.rl_bt, 11);
        sViewsWithIds.put(R.id.iv_bt, 12);
        sViewsWithIds.put(R.id.tv_bt, 13);
        sViewsWithIds.put(R.id.rl_music, 14);
        sViewsWithIds.put(R.id.iv_music, 15);
        sViewsWithIds.put(R.id.tv_music, 16);
        sViewsWithIds.put(R.id.rl_settings, 17);
        sViewsWithIds.put(R.id.iv_settings, 18);
        sViewsWithIds.put(R.id.tv_settings, 19);
        sViewsWithIds.put(R.id.rl_video, 20);
        sViewsWithIds.put(R.id.iv_video, 21);
        sViewsWithIds.put(R.id.tv_video, 22);
        sViewsWithIds.put(R.id.rl_dashboard, 23);
        sViewsWithIds.put(R.id.iv_dashboard, 24);
        sViewsWithIds.put(R.id.rl_easyconnection, 25);
        sViewsWithIds.put(R.id.iv_easyconnection, 26);
        sViewsWithIds.put(R.id.tv_easyconnection, 27);
        sViewsWithIds.put(R.id.rl_dvr, 28);
        sViewsWithIds.put(R.id.iv_dvr, 29);
        sViewsWithIds.put(R.id.tv_dvr, 30);
        sViewsWithIds.put(R.id.rl_car, 31);
        sViewsWithIds.put(R.id.iv_car, 32);
        sViewsWithIds.put(R.id.tv_car, 33);
        sViewsWithIds.put(R.id.rl_apps, 34);
        sViewsWithIds.put(R.id.iv_apps, 35);
        sViewsWithIds.put(R.id.tv_apps, 36);
        sViewsWithIds.put(R.id.date, 37);
    }

    public ActivityMainAudiBindingSw600dpLandImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }

    private ActivityMainAudiBindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, bindings[2], bindings[37], bindings[35], bindings[12], bindings[32], bindings[24], bindings[29], bindings[26], bindings[1], bindings[15], bindings[9], bindings[18], bindings[21], bindings[3], bindings[34], bindings[11], bindings[31], bindings[23], bindings[28], bindings[25], bindings[14], bindings[8], bindings[17], bindings[20], bindings[36], bindings[13], bindings[33], bindings[30], bindings[27], bindings[16], bindings[10], bindings[19], bindings[22]);
        this.mDirtyFlags = -1;
        this.MLoopRotarySwitchView.setTag((Object) null);
        this.ivGuang.setTag((Object) null);
        this.kswA4LAudiChe.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView01 = bindings[4];
        setContainedBinding(this.mboundView01);
        this.mboundView02 = bindings[5];
        setContainedBinding(this.mboundView02);
        this.mboundView03 = bindings[6];
        setContainedBinding(this.mboundView03);
        this.mboundView04 = bindings[7];
        setContainedBinding(this.mboundView04);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.mboundView01.invalidateAll();
        this.mboundView02.invalidateAll();
        this.mboundView03.invalidateAll();
        this.mboundView04.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.mboundView02.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.mboundView03.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.mboundView04.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.mboundView01.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0033 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            com.wits.ksw.databinding.AudiRightLogoBinding r0 = r4.mboundView01
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.wits.ksw.databinding.AudiRightCarinfoBinding r0 = r4.mboundView02
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.wits.ksw.databinding.AudiRightNaviBinding r0 = r4.mboundView03
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.wits.ksw.databinding.AudiRightMediaBinding r0 = r4.mboundView04
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r1
        L_0x0031:
            r0 = 0
            return r0
        L_0x0033:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.databinding.ActivityMainAudiBindingSw600dpLandImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setVm((AudiViewModel) variable);
        return true;
    }

    public void setVm(@Nullable AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView01.setLifecycleOwner(lifecycleOwner);
        this.mboundView02.setLifecycleOwner(lifecycleOwner);
        this.mboundView03.setLifecycleOwner(lifecycleOwner);
        this.mboundView04.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmCarBgPicId((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmCarPicId((ObservableInt) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmCarBgPicId(ObservableInt VmCarBgPicId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmCarPicId(ObservableInt VmCarPicId, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        AudiViewModel vm = this.mVm;
        int vmCarBgPicIdGet = 0;
        int vmCarPicIdGet = 0;
        LoopRotarySwitchView.OnItemSelectedListener vmOnItemSelectedListener = null;
        LoopRotarySwitchView.OnItemClickListener vmOnItemClickListener = null;
        ObservableInt vmCarBgPicId = null;
        ObservableInt vmCarPicId = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((dirtyFlags & 12) == 0 || vm == null)) {
                vmOnItemSelectedListener = vm.OnItemSelectedListener;
                vmOnItemClickListener = vm.onItemClickListener;
            }
            if ((dirtyFlags & 13) != 0) {
                if (vm != null) {
                    vmCarBgPicId = vm.carBgPicId;
                }
                updateRegistration(0, (Observable) vmCarBgPicId);
                if (vmCarBgPicId != null) {
                    vmCarBgPicIdGet = vmCarBgPicId.get();
                }
            }
            if ((dirtyFlags & 14) != 0) {
                if (vm != null) {
                    vmCarPicId = vm.carPicId;
                }
                updateRegistration(1, (Observable) vmCarPicId);
                if (vmCarPicId != null) {
                    vmCarPicIdGet = vmCarPicId.get();
                }
            }
        }
        if ((dirtyFlags & 12) != 0) {
            this.MLoopRotarySwitchView.setOnItemClickListener(vmOnItemClickListener);
            this.MLoopRotarySwitchView.setOnItemSelectedListener(vmOnItemSelectedListener);
            this.mboundView01.setVm(vm);
            this.mboundView02.setVm(vm);
            this.mboundView03.setVm(vm);
            this.mboundView04.setVm(vm);
        }
        if ((13 & dirtyFlags) != 0) {
            BaseBindingModel.srcImage(this.ivGuang, vmCarBgPicIdGet);
        }
        if ((dirtyFlags & 14) != 0) {
            BaseBindingModel.srcImage(this.kswA4LAudiChe, vmCarPicIdGet);
        }
        executeBindingsOn(this.mboundView01);
        executeBindingsOn(this.mboundView02);
        executeBindingsOn(this.mboundView03);
        executeBindingsOn(this.mboundView04);
    }
}

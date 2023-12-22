package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.utils.NaviInfo;

/* loaded from: classes7.dex */
public class AudiMib3RightNaviBindingImpl extends AudiMib3RightNaviBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.NaviAmapautoInfo, 6);
        sparseIntArray.put(C0899R.C0901id.bg1, 7);
        sparseIntArray.put(C0899R.C0901id.bg2, 8);
        sparseIntArray.put(C0899R.C0901id.bg3, 9);
        sparseIntArray.put(C0899R.C0901id.ImgRemainDis, 10);
        sparseIntArray.put(C0899R.C0901id.ImgRemainTime, 11);
    }

    public AudiMib3RightNaviBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private AudiMib3RightNaviBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (ImageView) bindings[1], (ImageView) bindings[10], (ImageView) bindings[11], (RelativeLayout) bindings[0], (RelativeLayout) bindings[6], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[2], (ImageView) bindings[7], (ImageView) bindings[8], (ImageView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.AmapautoIcon.setTag(null);
        this.KSWA4LRightShowNavi.setTag(null);
        this.TvNextRouadName.setTag(null);
        this.TvRouteRemainDis.setTag(null);
        this.TvRouteRemainTime.setTag(null);
        this.TvSegRemainDisInfor.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        if (26 == variableId) {
            setVm((AudiViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiMib3RightNaviBinding
    public void setVm(AudiViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmNaviInfoShowGuideView((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmNaviInfoRouteRemainDis((ObservableField) object, fieldId);
            case 2:
                return onChangeVmNaviInfoIcon((ObservableField) object, fieldId);
            case 3:
                return onChangeVmNaviInfoSegRemainDis((ObservableField) object, fieldId);
            case 4:
                return onChangeVmNaviInfoRouteRemainTime((ObservableField) object, fieldId);
            case 5:
                return onChangeVmNaviInfoNextRoadName((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmNaviInfoShowGuideView(ObservableInt VmNaviInfoShowGuideView, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNaviInfoRouteRemainDis(ObservableField<String> VmNaviInfoRouteRemainDis, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNaviInfoIcon(ObservableField<Integer> VmNaviInfoIcon, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNaviInfoSegRemainDis(ObservableField<String> VmNaviInfoSegRemainDis, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNaviInfoRouteRemainTime(ObservableField<String> VmNaviInfoRouteRemainTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmNaviInfoNextRoadName(ObservableField<String> VmNaviInfoNextRoadName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        int androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet;
        String vmNaviInfoRouteRemainTimeGet;
        NaviInfo vmNaviInfo;
        ObservableField<String> vmNaviInfoNextRoadName;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String vmNaviInfoSegRemainDisGet = null;
        AudiViewModel vm = this.mVm;
        String vmNaviInfoRouteRemainDisGet = null;
        ObservableInt vmNaviInfoShowGuideView = null;
        int vmNaviInfoShowGuideViewGet = 0;
        Integer vmNaviInfoIconGet = null;
        ObservableField<String> vmNaviInfoRouteRemainDis = null;
        ObservableField<Integer> vmNaviInfoIcon = null;
        ObservableField<String> vmNaviInfoSegRemainDis = null;
        ObservableField<String> vmNaviInfoRouteRemainTime = null;
        String vmNaviInfoNextRoadNameGet = null;
        int androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet2 = 0;
        String vmNaviInfoRouteRemainTimeGet2 = null;
        if ((dirtyFlags & 255) == 0) {
            androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet = 0;
            vmNaviInfoRouteRemainTimeGet = null;
        } else {
            if (vm == null) {
                vmNaviInfo = null;
            } else {
                vmNaviInfo = vm.naviInfo;
            }
            if ((dirtyFlags & 193) != 0) {
                if (vmNaviInfo != null) {
                    vmNaviInfoShowGuideView = vmNaviInfo.showGuideView;
                }
                updateRegistration(0, vmNaviInfoShowGuideView);
                if (vmNaviInfoShowGuideView != null) {
                    vmNaviInfoShowGuideViewGet = vmNaviInfoShowGuideView.get();
                }
            }
            if ((dirtyFlags & 194) != 0) {
                if (vmNaviInfo != null) {
                    vmNaviInfoRouteRemainDis = vmNaviInfo.route_remain_dis;
                }
                updateRegistration(1, vmNaviInfoRouteRemainDis);
                if (vmNaviInfoRouteRemainDis != null) {
                    vmNaviInfoRouteRemainDisGet = vmNaviInfoRouteRemainDis.get();
                }
            }
            if ((dirtyFlags & 196) != 0) {
                if (vmNaviInfo != null) {
                    vmNaviInfoIcon = vmNaviInfo.icon;
                }
                updateRegistration(2, vmNaviInfoIcon);
                if (vmNaviInfoIcon != null) {
                    Integer vmNaviInfoIconGet2 = vmNaviInfoIcon.get();
                    vmNaviInfoIconGet = vmNaviInfoIconGet2;
                }
                androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet2 = ViewDataBinding.safeUnbox(vmNaviInfoIconGet);
            }
            if ((dirtyFlags & 200) != 0) {
                if (vmNaviInfo != null) {
                    vmNaviInfoSegRemainDis = vmNaviInfo.seg_remain_dis;
                }
                updateRegistration(3, vmNaviInfoSegRemainDis);
                if (vmNaviInfoSegRemainDis != null) {
                    vmNaviInfoSegRemainDisGet = vmNaviInfoSegRemainDis.get();
                }
            }
            if ((dirtyFlags & 208) != 0) {
                if (vmNaviInfo != null) {
                    vmNaviInfoRouteRemainTime = vmNaviInfo.route_remain_time;
                }
                updateRegistration(4, vmNaviInfoRouteRemainTime);
                if (vmNaviInfoRouteRemainTime != null) {
                    vmNaviInfoRouteRemainTimeGet2 = vmNaviInfoRouteRemainTime.get();
                }
            }
            if ((dirtyFlags & 224) != 0) {
                if (vmNaviInfo == null) {
                    vmNaviInfoNextRoadName = null;
                } else {
                    vmNaviInfoNextRoadName = vmNaviInfo.next_road_name;
                }
                String vmNaviInfoSegRemainDisGet2 = vmNaviInfoSegRemainDisGet;
                updateRegistration(5, vmNaviInfoNextRoadName);
                if (vmNaviInfoNextRoadName != null) {
                    vmNaviInfoNextRoadNameGet = vmNaviInfoNextRoadName.get();
                    vmNaviInfoRouteRemainTimeGet = vmNaviInfoRouteRemainTimeGet2;
                    vmNaviInfoSegRemainDisGet = vmNaviInfoSegRemainDisGet2;
                    androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet = androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet2;
                } else {
                    vmNaviInfoRouteRemainTimeGet = vmNaviInfoRouteRemainTimeGet2;
                    vmNaviInfoSegRemainDisGet = vmNaviInfoSegRemainDisGet2;
                    androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet = androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet2;
                }
            } else {
                vmNaviInfoRouteRemainTimeGet = vmNaviInfoRouteRemainTimeGet2;
                androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet = androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet2;
            }
        }
        if ((dirtyFlags & 196) != 0) {
            BaseBindingModel.srcImage(this.AmapautoIcon, androidDatabindingViewDataBindingSafeUnboxVmNaviInfoIconGet);
        }
        if ((dirtyFlags & 193) != 0) {
            this.KSWA4LRightShowNavi.setVisibility(vmNaviInfoShowGuideViewGet);
        }
        if ((dirtyFlags & 224) != 0) {
            TextViewBindingAdapter.setText(this.TvNextRouadName, vmNaviInfoNextRoadNameGet);
        }
        if ((dirtyFlags & 194) != 0) {
            TextViewBindingAdapter.setText(this.TvRouteRemainDis, vmNaviInfoRouteRemainDisGet);
        }
        if ((dirtyFlags & 208) != 0) {
            TextViewBindingAdapter.setText(this.TvRouteRemainTime, vmNaviInfoRouteRemainTimeGet);
        }
        if ((dirtyFlags & 200) != 0) {
            TextViewBindingAdapter.setText(this.TvSegRemainDisInfor, vmNaviInfoSegRemainDisGet);
        }
    }
}

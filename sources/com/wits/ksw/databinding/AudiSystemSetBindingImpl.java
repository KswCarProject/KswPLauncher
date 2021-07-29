package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public class AudiSystemSetBindingImpl extends AudiSystemSetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.audiSystemSetParentPanel, 7);
        sparseIntArray.put(R.id.audi_system_speed_unit, 8);
        sparseIntArray.put(R.id.audi_system_temp_unit, 9);
        sparseIntArray.put(R.id.audi_system_rever_camera, 10);
        sparseIntArray.put(R.id.audi_system_brightness, 11);
    }

    public AudiSystemSetBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudiSystemSetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, bindings[6], bindings[11], bindings[2], bindings[10], bindings[1], bindings[5], bindings[4], bindings[3], bindings[7], bindings[8], bindings[9]);
        this.mDirtyFlags = -1;
        this.audiSystemAuxPostion.setTag((Object) null);
        this.audiSystemDrivingProhibitedVideo.setTag((Object) null);
        this.audiSystemReverCameraImg.setTag((Object) null);
        this.audiSystemReverMute.setTag((Object) null);
        this.audiSystemReverRadar.setTag((Object) null);
        this.audiSystemReverTrack.setTag((Object) null);
        ScrollView scrollView = bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (17 != variableId) {
            return false;
        }
        setVm((AudiSystemViewModel) variable);
        return true;
    }

    public void setVm(AudiSystemViewModel Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmReverTrack((ObservableBoolean) object, fieldId);
            case 1:
                return onChangeVmReverMute((ObservableBoolean) object, fieldId);
            case 2:
                return onChangeVmReverView((ObservableBoolean) object, fieldId);
            case 3:
                return onChangeVmShowAuxPostion((ObservableBoolean) object, fieldId);
            case 4:
                return onChangeVmReverRadar((ObservableBoolean) object, fieldId);
            case 5:
                return onChangeVmDrivingVideo((ObservableBoolean) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmReverTrack(ObservableBoolean VmReverTrack, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmReverMute(ObservableBoolean VmReverMute, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmReverView(ObservableBoolean VmReverView, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVmShowAuxPostion(ObservableBoolean VmShowAuxPostion, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVmReverRadar(ObservableBoolean VmReverRadar, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVmDrivingVideo(ObservableBoolean VmDrivingVideo, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        CompoundButton.OnCheckedChangeListener vmOnReverRadarkChangeListener;
        CompoundButton.OnCheckedChangeListener vmOnReverTrackChangeListener;
        CompoundButton.OnCheckedChangeListener vmOnReverMuteChangeListener;
        boolean vmReverTrackGet;
        boolean vmReverViewGet;
        boolean vmReverMuteGet;
        ObservableBoolean vmDrivingVideo;
        ObservableBoolean vmReverRadar;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean vmReverMuteGet2 = false;
        ObservableBoolean vmReverTrack = null;
        CompoundButton.OnCheckedChangeListener vmOnReverViewChangeListener = null;
        AudiSystemViewModel vm = this.mVm;
        boolean vmDrivingVideoGet = false;
        CompoundButton.OnCheckedChangeListener vmOnReverMuteChangeListener2 = null;
        ObservableBoolean vmReverMute = null;
        int vmShowAuxPostionViewVISIBLEViewGONE = 0;
        boolean vmReverRadarGet = false;
        ObservableBoolean vmReverView = null;
        ObservableBoolean vmShowAuxPostion = null;
        boolean vmShowAuxPostionGet = false;
        boolean vmReverViewGet2 = false;
        boolean vmReverTrackGet2 = false;
        CompoundButton.OnCheckedChangeListener vmOnReverMuteChangeListener3 = null;
        CompoundButton.OnCheckedChangeListener vmOnReverTrackChangeListener2 = null;
        CompoundButton.OnCheckedChangeListener vmOnReverRadarkChangeListener2 = null;
        if ((dirtyFlags & 255) != 0) {
            if ((dirtyFlags & 193) != 0) {
                if (vm != null) {
                    vmReverTrack = vm.reverTrack;
                }
                updateRegistration(0, (Observable) vmReverTrack);
                if (vmReverTrack != null) {
                    vmReverTrackGet2 = vmReverTrack.get();
                }
            }
            if ((dirtyFlags & 192) == 0) {
                vmReverMuteGet = false;
            } else if (vm != null) {
                CompoundButton.OnCheckedChangeListener vmOndrivingVideoChangeListener = vm.ondrivingVideoChangeListener;
                CompoundButton.OnCheckedChangeListener vmOnReverViewChangeListener2 = vm.onReverViewChangeListener;
                CompoundButton.OnCheckedChangeListener vmOnReverMuteChangeListener4 = vm.onReverMuteChangeListener;
                CompoundButton.OnCheckedChangeListener vmOnReverTrackChangeListener3 = vm.onReverTrackChangeListener;
                vmReverMuteGet = false;
                vmOnReverRadarkChangeListener2 = vm.onReverRadarkChangeListener;
                vmOnReverTrackChangeListener2 = vmOnReverTrackChangeListener3;
                vmOnReverMuteChangeListener3 = vmOnReverMuteChangeListener4;
                vmOnReverMuteChangeListener2 = vmOnReverViewChangeListener2;
                vmOnReverViewChangeListener = vmOndrivingVideoChangeListener;
            } else {
                vmReverMuteGet = false;
            }
            if ((dirtyFlags & 194) != 0) {
                if (vm != null) {
                    vmReverMute = vm.reverMute;
                }
                updateRegistration(1, (Observable) vmReverMute);
                if (vmReverMute != null) {
                    vmReverMuteGet2 = vmReverMute.get();
                } else {
                    vmReverMuteGet2 = vmReverMuteGet;
                }
            } else {
                vmReverMuteGet2 = vmReverMuteGet;
            }
            if ((dirtyFlags & 196) != 0) {
                if (vm != null) {
                    vmReverView = vm.reverView;
                }
                updateRegistration(2, (Observable) vmReverView);
                if (vmReverView != null) {
                    vmReverViewGet2 = vmReverView.get();
                }
            }
            if ((dirtyFlags & 200) != 0) {
                if (vm != null) {
                    vmShowAuxPostion = vm.showAuxPostion;
                }
                updateRegistration(3, (Observable) vmShowAuxPostion);
                if (vmShowAuxPostion != null) {
                    vmShowAuxPostionGet = vmShowAuxPostion.get();
                }
                if ((dirtyFlags & 200) != 0) {
                    if (vmShowAuxPostionGet) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
                vmShowAuxPostionViewVISIBLEViewGONE = vmShowAuxPostionGet ? 0 : 8;
            }
            if ((dirtyFlags & 208) != 0) {
                if (vm != null) {
                    vmReverRadar = vm.reverRadar;
                } else {
                    vmReverRadar = null;
                }
                updateRegistration(4, (Observable) vmReverRadar);
                if (vmReverRadar != null) {
                    ObservableBoolean observableBoolean = vmReverRadar;
                    vmReverRadarGet = vmReverRadar.get();
                } else {
                    ObservableBoolean observableBoolean2 = vmReverRadar;
                }
            }
            if ((dirtyFlags & 224) != 0) {
                if (vm != null) {
                    vmDrivingVideo = vm.drivingVideo;
                } else {
                    vmDrivingVideo = null;
                }
                updateRegistration(5, (Observable) vmDrivingVideo);
                if (vmDrivingVideo != null) {
                    vmDrivingVideoGet = vmDrivingVideo.get();
                    ObservableBoolean observableBoolean3 = vmDrivingVideo;
                    vmReverViewGet = vmReverViewGet2;
                    vmReverTrackGet = vmReverTrackGet2;
                    ObservableBoolean observableBoolean4 = vmReverTrack;
                    AudiSystemViewModel audiSystemViewModel = vm;
                    vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                    vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                    ObservableBoolean observableBoolean5 = vmReverMute;
                    vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
                } else {
                    ObservableBoolean observableBoolean6 = vmDrivingVideo;
                    vmReverViewGet = vmReverViewGet2;
                    vmReverTrackGet = vmReverTrackGet2;
                    ObservableBoolean observableBoolean7 = vmReverTrack;
                    AudiSystemViewModel audiSystemViewModel2 = vm;
                    vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                    vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                    ObservableBoolean observableBoolean8 = vmReverMute;
                    vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
                }
            } else {
                vmReverViewGet = vmReverViewGet2;
                vmReverTrackGet = vmReverTrackGet2;
                ObservableBoolean observableBoolean9 = vmReverTrack;
                AudiSystemViewModel audiSystemViewModel3 = vm;
                vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                ObservableBoolean observableBoolean10 = vmReverMute;
                vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
            }
        } else {
            vmReverViewGet = false;
            vmReverTrackGet = false;
            AudiSystemViewModel audiSystemViewModel4 = vm;
            vmOnReverMuteChangeListener = null;
            vmOnReverTrackChangeListener = null;
            vmOnReverRadarkChangeListener = null;
        }
        if ((dirtyFlags & 200) != 0) {
            ObservableBoolean observableBoolean11 = vmReverView;
            this.audiSystemAuxPostion.setVisibility(vmShowAuxPostionViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 224) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemDrivingProhibitedVideo, vmDrivingVideoGet);
        }
        if ((dirtyFlags & 192) != 0) {
            this.audiSystemDrivingProhibitedVideo.setOnCheckedChangeListener(vmOnReverViewChangeListener);
            this.audiSystemReverCameraImg.setOnCheckedChangeListener(vmOnReverMuteChangeListener2);
            this.audiSystemReverMute.setOnCheckedChangeListener(vmOnReverMuteChangeListener);
            this.audiSystemReverRadar.setOnCheckedChangeListener(vmOnReverRadarkChangeListener);
            this.audiSystemReverTrack.setOnCheckedChangeListener(vmOnReverTrackChangeListener);
        }
        if ((dirtyFlags & 196) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemReverCameraImg, vmReverViewGet);
        }
        if ((dirtyFlags & 194) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemReverMute, vmReverMuteGet2);
        }
        if ((dirtyFlags & 208) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemReverRadar, vmReverRadarGet);
        }
        if ((dirtyFlags & 193) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.audiSystemReverTrack, vmReverTrackGet);
        }
    }
}

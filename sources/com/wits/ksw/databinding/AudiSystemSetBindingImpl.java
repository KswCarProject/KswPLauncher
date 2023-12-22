package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public class AudiSystemSetBindingImpl extends AudiSystemSetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.audiSystemSetParentPanel, 7);
        sparseIntArray.put(C0899R.C0901id.audi_system_speed_unit, 8);
        sparseIntArray.put(C0899R.C0901id.audi_system_temp_unit, 9);
        sparseIntArray.put(C0899R.C0901id.audi_system_rever_camera, 10);
        sparseIntArray.put(C0899R.C0901id.audi_system_brightness, 11);
        sparseIntArray.put(C0899R.C0901id.tv_music_app, 12);
        sparseIntArray.put(C0899R.C0901id.tv_video_app, 13);
    }

    public AudiSystemSetBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private AudiSystemSetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (TextView) bindings[6], (TextView) bindings[11], (CheckBox) bindings[2], (TextView) bindings[10], (CheckBox) bindings[1], (CheckBox) bindings[5], (CheckBox) bindings[4], (CheckBox) bindings[3], (AudiConstraintLayout) bindings[7], (TextView) bindings[8], (TextView) bindings[9], (TextView) bindings[12], (TextView) bindings[13]);
        this.mDirtyFlags = -1L;
        this.audiSystemAuxPostion.setTag(null);
        this.audiSystemDrivingProhibitedVideo.setTag(null);
        this.audiSystemReverCameraImg.setTag(null);
        this.audiSystemReverMute.setTag(null);
        this.audiSystemReverRadar.setTag(null);
        this.audiSystemReverTrack.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
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
            setVm((AudiSystemViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AudiSystemSetBinding
    public void setVm(AudiSystemViewModel Vm) {
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
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmReverMute(ObservableBoolean VmReverMute, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmReverView(ObservableBoolean VmReverView, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmShowAuxPostion(ObservableBoolean VmShowAuxPostion, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmReverRadar(ObservableBoolean VmReverRadar, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmDrivingVideo(ObservableBoolean VmDrivingVideo, int fieldId) {
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
        boolean vmReverViewGet;
        boolean vmReverTrackGet;
        CompoundButton.OnCheckedChangeListener vmOnReverMuteChangeListener;
        CompoundButton.OnCheckedChangeListener vmOnReverTrackChangeListener;
        CompoundButton.OnCheckedChangeListener vmOnReverRadarkChangeListener;
        boolean vmReverMuteGet;
        ObservableBoolean vmDrivingVideo;
        ObservableBoolean vmReverRadar;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
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
                updateRegistration(0, vmReverTrack);
                if (vmReverTrack != null) {
                    vmReverTrackGet2 = vmReverTrack.get();
                }
            }
            if ((dirtyFlags & 192) == 0) {
                vmReverMuteGet = false;
            } else if (vm == null) {
                vmReverMuteGet = false;
            } else {
                CompoundButton.OnCheckedChangeListener vmOndrivingVideoChangeListener = vm.ondrivingVideoChangeListener;
                CompoundButton.OnCheckedChangeListener vmOndrivingVideoChangeListener2 = vm.onReverViewChangeListener;
                CompoundButton.OnCheckedChangeListener vmOnReverViewChangeListener2 = vm.onReverMuteChangeListener;
                CompoundButton.OnCheckedChangeListener vmOnReverTrackChangeListener3 = vm.onReverTrackChangeListener;
                vmReverMuteGet = false;
                vmOnReverRadarkChangeListener2 = vm.onReverRadarkChangeListener;
                vmOnReverTrackChangeListener2 = vmOnReverTrackChangeListener3;
                vmOnReverMuteChangeListener3 = vmOnReverViewChangeListener2;
                vmOnReverMuteChangeListener2 = vmOndrivingVideoChangeListener2;
                vmOnReverViewChangeListener = vmOndrivingVideoChangeListener;
            }
            if ((dirtyFlags & 194) == 0) {
                vmReverMuteGet2 = vmReverMuteGet;
            } else {
                if (vm != null) {
                    vmReverMute = vm.reverMute;
                }
                updateRegistration(1, vmReverMute);
                if (vmReverMute == null) {
                    vmReverMuteGet2 = vmReverMuteGet;
                } else {
                    vmReverMuteGet2 = vmReverMute.get();
                }
            }
            if ((dirtyFlags & 196) != 0) {
                if (vm != null) {
                    vmReverView = vm.reverView;
                }
                updateRegistration(2, vmReverView);
                if (vmReverView != null) {
                    vmReverViewGet2 = vmReverView.get();
                }
            }
            if ((dirtyFlags & 200) != 0) {
                if (vm != null) {
                    vmShowAuxPostion = vm.showAuxPostion;
                }
                updateRegistration(3, vmShowAuxPostion);
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
                if (vm == null) {
                    vmReverRadar = null;
                } else {
                    vmReverRadar = vm.reverRadar;
                }
                updateRegistration(4, vmReverRadar);
                if (vmReverRadar != null) {
                    vmReverRadarGet = vmReverRadar.get();
                }
            }
            if ((dirtyFlags & 224) == 0) {
                vmReverViewGet = vmReverViewGet2;
                vmReverTrackGet = vmReverTrackGet2;
                vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
            } else {
                if (vm == null) {
                    vmDrivingVideo = null;
                } else {
                    vmDrivingVideo = vm.drivingVideo;
                }
                updateRegistration(5, vmDrivingVideo);
                if (vmDrivingVideo != null) {
                    vmDrivingVideoGet = vmDrivingVideo.get();
                    vmReverViewGet = vmReverViewGet2;
                    vmReverTrackGet = vmReverTrackGet2;
                    vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                    vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                    vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
                } else {
                    vmReverViewGet = vmReverViewGet2;
                    vmReverTrackGet = vmReverTrackGet2;
                    vmOnReverMuteChangeListener = vmOnReverMuteChangeListener3;
                    vmOnReverTrackChangeListener = vmOnReverTrackChangeListener2;
                    vmOnReverRadarkChangeListener = vmOnReverRadarkChangeListener2;
                }
            }
        } else {
            vmReverViewGet = false;
            vmReverTrackGet = false;
            vmOnReverMuteChangeListener = null;
            vmOnReverTrackChangeListener = null;
            vmOnReverRadarkChangeListener = null;
        }
        if ((dirtyFlags & 200) != 0) {
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

package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

/* loaded from: classes7.dex */
public class FraBmwEvoId6GsOneBindingImpl extends FraBmwEvoId6GsOneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ConstraintLayout mboundView2;
    private final ConstraintLayout mboundView5;
    private final ConstraintLayout mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_navi_hint_textview, 7);
        sparseIntArray.put(C0899R.C0901id.textView17, 8);
        sparseIntArray.put(C0899R.C0901id.bmw_evo_id6_gs_hmoe_video_hint_textview, 9);
    }

    public FraBmwEvoId6GsOneBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (TextView) bindings[3], (TextView) bindings[4], (ConstraintLayout) bindings[1], (TextView) bindings[7], (TextView) bindings[9], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.bmwEvoId6GsHmoeMusicHintTextview.setTag(null);
        this.bmwEvoId6GsHmoeMusicNameTextview.setTag(null);
        this.bmwEvoId6GsHmoeNaviBtn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[5];
        this.mboundView5 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[6];
        this.mboundView6 = constraintLayout3;
        constraintLayout3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setVm((BmwId6gsViewMode) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.FraBmwEvoId6GsOneBinding
    public void setVm(BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeVmIndex((ObservableInt) object, fieldId);
            case 1:
                return onChangeVmMediaInfoMusicName((ObservableField) object, fieldId);
            case 2:
                return onChangeVmMediaInfoMusicAtist((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeVmIndex(ObservableInt VmIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> VmMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeVmMediaInfoMusicAtist(ObservableField<String> VmMediaInfoMusicAtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        boolean vmIndexInt1;
        String vmMediaInfoMusicAtistGet;
        ObservableField<String> vmMediaInfoMusicAtist;
        ObservableField<String> vmMediaInfoMusicName;
        int vmIndexGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean vmIndexInt2 = false;
        String vmMediaInfoMusicAtistGet2 = null;
        ObservableInt vmIndex = null;
        String vmMediaInfoMusicNameGet = null;
        boolean vmMediaInfoMusicAtistJavaLangObjectNull = false;
        BmwId6gsViewMode vm = this.mVm;
        String vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist = null;
        String vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName = null;
        boolean vmIndexInt0 = false;
        boolean vmIndexInt3 = false;
        boolean vmMediaInfoMusicNameJavaLangObjectNull = false;
        if ((dirtyFlags & 25) == 0) {
            vmIndexInt1 = false;
        } else {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, vmIndex);
            if (vmIndex == null) {
                vmIndexGet = 0;
            } else {
                int vmIndexGet2 = vmIndex.get();
                vmIndexGet = vmIndexGet2;
            }
            vmIndexInt2 = vmIndexGet == 2;
            vmIndexInt0 = vmIndexGet == 0;
            vmIndexInt3 = vmIndexGet == 3;
            vmIndexInt1 = vmIndexGet == 1;
        }
        if ((dirtyFlags & 22) != 0) {
            MediaInfo vmMediaInfo = BmwId6gsViewMode.mediaInfo;
            if ((dirtyFlags & 18) == 0) {
                vmMediaInfoMusicAtistGet = null;
            } else {
                if (vmMediaInfo == null) {
                    vmMediaInfoMusicName = null;
                } else {
                    vmMediaInfoMusicName = vmMediaInfo.musicName;
                }
                vmMediaInfoMusicAtistGet = null;
                updateRegistration(1, vmMediaInfoMusicName);
                if (vmMediaInfoMusicName != null) {
                    vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
                }
                vmMediaInfoMusicNameJavaLangObjectNull = vmMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 18) != 0) {
                    dirtyFlags = vmMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 256 : dirtyFlags | 128;
                }
            }
            if ((dirtyFlags & 20) == 0) {
                vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtistGet;
            } else {
                if (vmMediaInfo == null) {
                    vmMediaInfoMusicAtist = null;
                } else {
                    vmMediaInfoMusicAtist = vmMediaInfo.musicAtist;
                }
                updateRegistration(2, vmMediaInfoMusicAtist);
                if (vmMediaInfoMusicAtist == null) {
                    vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtistGet;
                } else {
                    vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtist.get();
                }
                vmMediaInfoMusicAtistJavaLangObjectNull = vmMediaInfoMusicAtistGet2 == null;
                if ((dirtyFlags & 20) != 0) {
                    if (vmMediaInfoMusicAtistJavaLangObjectNull) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
            }
        }
        if ((dirtyFlags & 20) != 0) {
            vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist = vmMediaInfoMusicAtistJavaLangObjectNull ? this.bmwEvoId6GsHmoeMusicHintTextview.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : vmMediaInfoMusicAtistGet2;
        }
        if ((dirtyFlags & 18) != 0) {
            vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName = vmMediaInfoMusicNameJavaLangObjectNull ? this.bmwEvoId6GsHmoeMusicNameTextview.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : vmMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeMusicHintTextview, vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist);
        }
        if ((dirtyFlags & 18) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeMusicNameTextview, vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName);
        }
        if ((dirtyFlags & 25) != 0) {
            this.bmwEvoId6GsHmoeNaviBtn.setSelected(vmIndexInt0);
            this.mboundView2.setSelected(vmIndexInt1);
            this.mboundView5.setSelected(vmIndexInt2);
            this.mboundView6.setSelected(vmIndexInt3);
        }
    }
}

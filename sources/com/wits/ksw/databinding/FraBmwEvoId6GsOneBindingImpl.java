package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

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
        sparseIntArray.put(R.id.bmw_evo_id6_gs_hmoe_navi_hint_textview, 7);
        sparseIntArray.put(R.id.textView17, 8);
        sparseIntArray.put(R.id.bmw_evo_id6_gs_hmoe_video_hint_textview, 9);
    }

    public FraBmwEvoId6GsOneBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[3], bindings[4], bindings[1], bindings[7], bindings[9], bindings[8]);
        this.mDirtyFlags = -1;
        this.bmwEvoId6GsHmoeMusicHintTextview.setTag((Object) null);
        this.bmwEvoId6GsHmoeMusicNameTextview.setTag((Object) null);
        this.bmwEvoId6GsHmoeNaviBtn.setTag((Object) null);
        LinearLayout linearLayout = bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[2];
        this.mboundView2 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ConstraintLayout constraintLayout2 = bindings[5];
        this.mboundView5 = constraintLayout2;
        constraintLayout2.setTag((Object) null);
        ConstraintLayout constraintLayout3 = bindings[6];
        this.mboundView6 = constraintLayout3;
        constraintLayout3.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (26 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVmMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVmMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        boolean vmIndexInt1;
        String str;
        String vmMediaInfoMusicAtistGet;
        ObservableField<String> vmMediaInfoMusicAtist;
        ObservableField<String> vmMediaInfoMusicName;
        int vmIndexGet;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
        if ((dirtyFlags & 25) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            } else {
                vmIndexGet = 0;
            }
            vmIndexInt2 = vmIndexGet == 2;
            vmIndexInt0 = vmIndexGet == 0;
            vmIndexInt3 = vmIndexGet == 3;
            vmIndexInt1 = vmIndexGet == 1;
            int i = vmIndexGet;
        } else {
            vmIndexInt1 = false;
        }
        if ((dirtyFlags & 22) != 0) {
            MediaInfo vmMediaInfo = BmwId6gsViewMode.mediaInfo;
            if ((dirtyFlags & 18) != 0) {
                if (vmMediaInfo != null) {
                    vmMediaInfoMusicName = vmMediaInfo.musicName;
                } else {
                    vmMediaInfoMusicName = null;
                }
                vmMediaInfoMusicAtistGet = null;
                updateRegistration(1, (Observable) vmMediaInfoMusicName);
                if (vmMediaInfoMusicName != null) {
                    vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
                }
                vmMediaInfoMusicNameJavaLangObjectNull = vmMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 18) == 0) {
                    ObservableField<String> observableField = vmMediaInfoMusicName;
                } else if (vmMediaInfoMusicNameJavaLangObjectNull) {
                    dirtyFlags |= 256;
                    ObservableField<String> observableField2 = vmMediaInfoMusicName;
                } else {
                    dirtyFlags |= 128;
                    ObservableField<String> observableField3 = vmMediaInfoMusicName;
                }
            } else {
                vmMediaInfoMusicAtistGet = null;
            }
            if ((dirtyFlags & 20) != 0) {
                if (vmMediaInfo != null) {
                    vmMediaInfoMusicAtist = vmMediaInfo.musicAtist;
                } else {
                    vmMediaInfoMusicAtist = null;
                }
                updateRegistration(2, (Observable) vmMediaInfoMusicAtist);
                if (vmMediaInfoMusicAtist != null) {
                    vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtist.get();
                } else {
                    vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtistGet;
                }
                vmMediaInfoMusicAtistJavaLangObjectNull = vmMediaInfoMusicAtistGet2 == null;
                if ((dirtyFlags & 20) == 0) {
                    ObservableField<String> observableField4 = vmMediaInfoMusicAtist;
                } else if (vmMediaInfoMusicAtistJavaLangObjectNull) {
                    dirtyFlags |= 64;
                    ObservableField<String> observableField5 = vmMediaInfoMusicAtist;
                } else {
                    dirtyFlags |= 32;
                    ObservableField<String> observableField6 = vmMediaInfoMusicAtist;
                }
            } else {
                vmMediaInfoMusicAtistGet2 = vmMediaInfoMusicAtistGet;
            }
        }
        if ((dirtyFlags & 20) != 0) {
            if (vmMediaInfoMusicAtistJavaLangObjectNull) {
                String str2 = vmMediaInfoMusicAtistGet2;
                str = this.bmwEvoId6GsHmoeMusicHintTextview.getResources().getString(R.string.ksw_idf7_unknow_artis);
            } else {
                str = vmMediaInfoMusicAtistGet2;
            }
            vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist = str;
        }
        if ((dirtyFlags & 18) != 0) {
            vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName = vmMediaInfoMusicNameJavaLangObjectNull ? this.bmwEvoId6GsHmoeMusicNameTextview.getResources().getString(R.string.ksw_idf7_unkonw_soung) : vmMediaInfoMusicNameGet;
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

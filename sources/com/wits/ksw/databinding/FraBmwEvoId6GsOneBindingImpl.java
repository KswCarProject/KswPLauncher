package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;

public class FraBmwEvoId6GsOneBindingImpl extends FraBmwEvoId6GsOneBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final ConstraintLayout mboundView2;
    @NonNull
    private final ConstraintLayout mboundView5;
    @NonNull
    private final ConstraintLayout mboundView6;

    static {
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_navi_hint_textview, 7);
        sViewsWithIds.put(R.id.textView17, 8);
        sViewsWithIds.put(R.id.bmw_evo_id6_gs_hmoe_video_hint_textview, 9);
    }

    public FraBmwEvoId6GsOneBindingImpl(@Nullable DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FraBmwEvoId6GsOneBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[3], bindings[4], bindings[1], bindings[7], bindings[9], bindings[8]);
        this.mDirtyFlags = -1;
        this.bmwEvoId6GsHmoeMusicHintTextview.setTag((Object) null);
        this.bmwEvoId6GsHmoeMusicNameTextview.setTag((Object) null);
        this.bmwEvoId6GsHmoeNaviBtn.setTag((Object) null);
        this.mboundView0 = bindings[0];
        this.mboundView0.setTag((Object) null);
        this.mboundView2 = bindings[2];
        this.mboundView2.setTag((Object) null);
        this.mboundView5 = bindings[5];
        this.mboundView5.setTag((Object) null);
        this.mboundView6 = bindings[6];
        this.mboundView6.setTag((Object) null);
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

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (10 != variableId) {
            return false;
        }
        setVm((BmwId6gsViewMode) variable);
        return true;
    }

    public void setVm(@Nullable BmwId6gsViewMode Vm) {
        this.mVm = Vm;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(10);
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
        boolean vmIndexInt2;
        MediaInfo vmMediaInfo;
        String vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName;
        String str;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ObservableInt vmIndex = null;
        BmwId6gsViewMode vm = this.mVm;
        ObservableField<String> vmMediaInfoMusicName = null;
        ObservableField<String> vmMediaInfoMusicAtist = null;
        String vmMediaInfoMusicNameGet = null;
        String vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist = null;
        boolean vmIndexInt0 = false;
        boolean vmIndexInt3 = false;
        String vmMediaInfoMusicAtistGet = null;
        boolean vmMediaInfoMusicAtistJavaLangObjectNull = false;
        int vmIndexGet = 0;
        boolean vmIndexInt1 = false;
        boolean vmMediaInfoMusicNameJavaLangObjectNull = false;
        if ((dirtyFlags & 25) != 0) {
            if (vm != null) {
                vmIndex = vm.index;
            }
            updateRegistration(0, (Observable) vmIndex);
            if (vmIndex != null) {
                vmIndexGet = vmIndex.get();
            }
            int vmIndexGet2 = vmIndexGet;
            boolean vmIndexInt22 = vmIndexGet2 == 2;
            vmIndexInt0 = vmIndexGet2 == 0;
            vmIndexInt3 = vmIndexGet2 == 3;
            vmIndexInt1 = vmIndexGet2 == 1;
            vmIndexInt2 = vmIndexInt22;
            int i = vmIndexGet2;
        } else {
            vmIndexInt2 = false;
        }
        boolean vmIndexInt12 = vmIndexInt1;
        if ((dirtyFlags & 22) != 0) {
            vmMediaInfo = BmwId6gsViewMode.mediaInfo;
            if ((dirtyFlags & 18) != 0) {
                if (vmMediaInfo != null) {
                    vmMediaInfoMusicName = vmMediaInfo.musicName;
                }
                ObservableInt observableInt = vmIndex;
                updateRegistration(1, (Observable) vmMediaInfoMusicName);
                if (vmMediaInfoMusicName != null) {
                    vmMediaInfoMusicNameGet = vmMediaInfoMusicName.get();
                }
                boolean vmMediaInfoMusicNameJavaLangObjectNull2 = vmMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 18) != 0) {
                    if (vmMediaInfoMusicNameJavaLangObjectNull2) {
                        dirtyFlags |= 256;
                    } else {
                        dirtyFlags |= 128;
                    }
                }
                vmMediaInfoMusicNameJavaLangObjectNull = vmMediaInfoMusicNameJavaLangObjectNull2;
            }
            if ((dirtyFlags & 20) != 0) {
                if (vmMediaInfo != null) {
                    vmMediaInfoMusicAtist = vmMediaInfo.musicAtist;
                }
                updateRegistration(2, (Observable) vmMediaInfoMusicAtist);
                if (vmMediaInfoMusicAtist != null) {
                    vmMediaInfoMusicAtistGet = vmMediaInfoMusicAtist.get();
                }
                vmMediaInfoMusicAtistJavaLangObjectNull = vmMediaInfoMusicAtistGet == null;
                if ((dirtyFlags & 20) != 0) {
                    if (vmMediaInfoMusicAtistJavaLangObjectNull) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
            }
        } else {
            vmMediaInfo = null;
        }
        if ((dirtyFlags & 20) != 0) {
            if (vmMediaInfoMusicAtistJavaLangObjectNull) {
                MediaInfo mediaInfo = vmMediaInfo;
                str = this.bmwEvoId6GsHmoeMusicHintTextview.getResources().getString(R.string.ksw_idf7_unknow_artis);
            } else {
                str = vmMediaInfoMusicAtistGet;
            }
            vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist = str;
        }
        if ((dirtyFlags & 18) != 0) {
            vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName = vmMediaInfoMusicNameJavaLangObjectNull ? this.bmwEvoId6GsHmoeMusicNameTextview.getResources().getString(R.string.ksw_idf7_unkonw_soung) : vmMediaInfoMusicNameGet;
        } else {
            vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName = null;
        }
        if ((dirtyFlags & 20) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeMusicHintTextview, vmMediaInfoMusicAtistJavaLangObjectNullBmwEvoId6GsHmoeMusicHintTextviewAndroidStringKswIdf7UnknowArtisVmMediaInfoMusicAtist);
        }
        if ((dirtyFlags & 18) != 0) {
            TextViewBindingAdapter.setText(this.bmwEvoId6GsHmoeMusicNameTextview, vmMediaInfoMusicNameJavaLangObjectNullBmwEvoId6GsHmoeMusicNameTextviewAndroidStringKswIdf7UnkonwSoungVmMediaInfoMusicName);
        }
        if ((dirtyFlags & 25) != 0) {
            this.bmwEvoId6GsHmoeNaviBtn.setSelected(vmIndexInt0);
            this.mboundView2.setSelected(vmIndexInt12);
            this.mboundView5.setSelected(vmIndexInt2);
            this.mboundView6.setSelected(vmIndexInt3);
        }
    }
}

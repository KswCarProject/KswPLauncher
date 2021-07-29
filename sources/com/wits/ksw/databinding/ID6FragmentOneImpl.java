package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID6FragmentOneImpl extends ID6FragmentOne {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.id6_music_iamge_view, 4);
        sparseIntArray.put(R.id.id6_music_text_view, 5);
        sparseIntArray.put(R.id.id6_nav_image_view, 6);
        sparseIntArray.put(R.id.id6_navi_text_view, 7);
        sparseIntArray.put(R.id.id6_navi_mess, 8);
        sparseIntArray.put(R.id.id6_bt_image_view, 9);
        sparseIntArray.put(R.id.id6_bt_text_view, 10);
        sparseIntArray.put(R.id.id6_bt_mess, 11);
    }

    public ID6FragmentOneImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ID6FragmentOneImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, bindings[9], bindings[11], bindings[10], bindings[1], bindings[4], bindings[3], bindings[2], bindings[5], bindings[6], bindings[8], bindings[7]);
        this.mDirtyFlags = -1;
        this.id6MusicArtisTextView.setTag((Object) null);
        this.id6MusicIcon.setTag((Object) null);
        this.id6MusicNameTextView.setTag((Object) null);
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
        if (16 != variableId) {
            return false;
        }
        setViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setViewModel(LauncherViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> observableField, int fieldId) {
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
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String viewModelMediaInfoMusicAtistGet = null;
        ObservableField<String> viewModelMediaInfoMusicName = null;
        String viewModelMediaInfoMusicNameJavaLangObjectNullId6MusicNameTextViewAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = null;
        ObservableField<String> viewModelMediaInfoMusicAtist = null;
        String viewModelMediaInfoMusicNameGet = null;
        boolean viewModelMediaInfoMusicNameJavaLangObjectNull = false;
        boolean viewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        BitmapDrawable viewModelMediaInfoPicGet = null;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic = null;
        String viewModelMediaInfoMusicAtistJavaLangObjectNullId6MusicArtisTextViewAndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = null;
        if ((dirtyFlags & 23) != 0) {
            MediaInfo viewModelMediaInfo = LauncherViewModel.mediaInfo;
            boolean viewModelMediaInfoMusicAtistJavaLangObjectNull2 = true;
            if ((dirtyFlags & 17) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicName = viewModelMediaInfo.musicName;
                }
                updateRegistration(0, (Observable) viewModelMediaInfoMusicName);
                if (viewModelMediaInfoMusicName != null) {
                    viewModelMediaInfoMusicNameGet = viewModelMediaInfoMusicName.get();
                }
                viewModelMediaInfoMusicNameJavaLangObjectNull = viewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 17) != 0) {
                    dirtyFlags = viewModelMediaInfoMusicNameJavaLangObjectNull ? dirtyFlags | 64 : dirtyFlags | 32;
                }
            }
            if ((dirtyFlags & 18) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAtist = viewModelMediaInfo.musicAtist;
                }
                updateRegistration(1, (Observable) viewModelMediaInfoMusicAtist);
                if (viewModelMediaInfoMusicAtist != null) {
                    viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtist.get();
                }
                if (viewModelMediaInfoMusicAtistGet != null) {
                    viewModelMediaInfoMusicAtistJavaLangObjectNull2 = false;
                }
                if ((dirtyFlags & 18) == 0) {
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else if (viewModelMediaInfoMusicAtistJavaLangObjectNull2) {
                    dirtyFlags |= 256;
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else {
                    dirtyFlags |= 128;
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 20) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                }
                updateRegistration(2, (Observable) viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    viewModelMediaInfoPicGet = viewModelMediaInfoPic.get();
                }
            }
        }
        if ((dirtyFlags & 17) != 0) {
            viewModelMediaInfoMusicNameJavaLangObjectNullId6MusicNameTextViewAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = viewModelMediaInfoMusicNameJavaLangObjectNull ? this.id6MusicNameTextView.getResources().getString(R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 18) != 0) {
            viewModelMediaInfoMusicAtistJavaLangObjectNullId6MusicArtisTextViewAndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = viewModelMediaInfoMusicAtistJavaLangObjectNull ? this.id6MusicArtisTextView.getResources().getString(R.string.ksw_idf7_unknow_artis) : viewModelMediaInfoMusicAtistGet;
        }
        if ((dirtyFlags & 18) != 0) {
            TextViewBindingAdapter.setText(this.id6MusicArtisTextView, viewModelMediaInfoMusicAtistJavaLangObjectNullId6MusicArtisTextViewAndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & 20) != 0) {
            BaseBindingModel.setID6MusicAlbumIcon(this.id6MusicIcon, viewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 17) != 0) {
            TextViewBindingAdapter.setText(this.id6MusicNameTextView, viewModelMediaInfoMusicNameJavaLangObjectNullId6MusicNameTextViewAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName);
        }
    }
}

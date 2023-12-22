package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.base.BaseBindingModel;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.CustomizeSeekBar;

/* loaded from: classes7.dex */
public class BmwId8DashboardMusicLayoutBindingImpl extends BmwId8DashboardMusicLayoutBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView8;

    public BmwId8DashboardMusicLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private BmwId8DashboardMusicLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8, (TextView) bindings[4], (CustomizeSeekBar) bindings[7]);
        this.mDirtyFlags = -1L;
        this.bmwId8DashboardMusicName.setTag(null);
        this.bmwId8DashboardMusicSeekbar.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[8];
        this.mboundView8 = textView4;
        textView4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512L;
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
            setViewModel((DashboardViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBinding
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMediaInfoHightLrcRow((ObservableField) object, fieldId);
            case 1:
                return onChangeViewModelBThirdMusic((ObservableField) object, fieldId);
            case 2:
                return onChangeViewModelMediaInfoMusicName((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelMediaInfoMusicAtist((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMediaInfoProgressPercent((ObservableInt) object, fieldId);
            case 5:
                return onChangeViewModelMediaInfoCurrentTime((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelMediaInfoPic((ObservableField) object, fieldId);
            case 7:
                return onChangeViewModelMediaInfoTotalTime((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMediaInfoHightLrcRow(ObservableField<String> ViewModelMediaInfoHightLrcRow, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelBThirdMusic(ObservableField<Boolean> ViewModelBThirdMusic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMusicName(ObservableField<String> ViewModelMediaInfoMusicName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoMusicAtist(ObservableField<String> ViewModelMediaInfoMusicAtist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoProgressPercent(ObservableInt ViewModelMediaInfoProgressPercent, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoCurrentTime(ObservableField<String> ViewModelMediaInfoCurrentTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoPic(ObservableField<BitmapDrawable> ViewModelMediaInfoPic, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelMediaInfoTotalTime(ObservableField<String> ViewModelMediaInfoTotalTime, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        BitmapDrawable viewModelMediaInfoPicGet;
        String viewModelMediaInfoTotalTimeGet;
        String viewModelMediaInfoMusicAtistGet;
        int viewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE;
        String viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView5AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist;
        ObservableField<String> viewModelMediaInfoTotalTime;
        ObservableField<BitmapDrawable> viewModelMediaInfoPic;
        ObservableField<String> viewModelMediaInfoCurrentTime;
        ObservableInt viewModelMediaInfoProgressPercent;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String viewModelMediaInfoMusicAtistGet2 = null;
        ObservableField<String> viewModelMediaInfoHightLrcRow = null;
        String viewModelMediaInfoCurrentTimeGet = null;
        MediaInfo viewModelMediaInfo = null;
        ObservableField<String> viewModelMediaInfoMusicName = null;
        String viewModelMediaInfoHightLrcRowGet = null;
        String viewModelMediaInfoMusicNameJavaLangObjectNullBmwId8DashboardMusicNameAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = null;
        ObservableField<String> viewModelMediaInfoMusicAtist = null;
        int viewModelMediaInfoProgressPercentGet = 0;
        String viewModelMediaInfoMusicNameGet = null;
        boolean viewModelMediaInfoMusicNameJavaLangObjectNull = false;
        boolean viewModelMediaInfoMusicAtistJavaLangObjectNull = false;
        Boolean viewModelBThirdMusicGet = null;
        BitmapDrawable viewModelMediaInfoPicGet2 = null;
        if ((dirtyFlags & 765) == 0) {
            viewModelMediaInfoPicGet = null;
            viewModelMediaInfoTotalTimeGet = null;
        } else {
            viewModelMediaInfo = DashboardViewModel.mediaInfo;
            if ((dirtyFlags & 513) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoHightLrcRow = viewModelMediaInfo.hightLrcRow;
                }
                updateRegistration(0, viewModelMediaInfoHightLrcRow);
                if (viewModelMediaInfoHightLrcRow != null) {
                    viewModelMediaInfoHightLrcRowGet = viewModelMediaInfoHightLrcRow.get();
                }
            }
            if ((dirtyFlags & 516) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicName = viewModelMediaInfo.musicName;
                }
                updateRegistration(2, viewModelMediaInfoMusicName);
                if (viewModelMediaInfoMusicName != null) {
                    viewModelMediaInfoMusicNameGet = viewModelMediaInfoMusicName.get();
                }
                boolean viewModelMediaInfoMusicNameJavaLangObjectNull2 = viewModelMediaInfoMusicNameGet == null;
                if ((dirtyFlags & 516) == 0) {
                    viewModelMediaInfoMusicNameJavaLangObjectNull = viewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else if (viewModelMediaInfoMusicNameJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    viewModelMediaInfoMusicNameJavaLangObjectNull = viewModelMediaInfoMusicNameJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    viewModelMediaInfoMusicNameJavaLangObjectNull = viewModelMediaInfoMusicNameJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 520) != 0) {
                if (viewModelMediaInfo != null) {
                    viewModelMediaInfoMusicAtist = viewModelMediaInfo.musicAtist;
                }
                updateRegistration(3, viewModelMediaInfoMusicAtist);
                if (viewModelMediaInfoMusicAtist != null) {
                    viewModelMediaInfoMusicAtistGet2 = viewModelMediaInfoMusicAtist.get();
                }
                boolean viewModelMediaInfoMusicAtistJavaLangObjectNull2 = viewModelMediaInfoMusicAtistGet2 == null;
                if ((dirtyFlags & 520) == 0) {
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else if (viewModelMediaInfoMusicAtistJavaLangObjectNull2) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    viewModelMediaInfoMusicAtistJavaLangObjectNull = viewModelMediaInfoMusicAtistJavaLangObjectNull2;
                }
            }
            if ((dirtyFlags & 528) != 0) {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoProgressPercent = null;
                } else {
                    viewModelMediaInfoProgressPercent = viewModelMediaInfo.progressPercent;
                }
                updateRegistration(4, viewModelMediaInfoProgressPercent);
                if (viewModelMediaInfoProgressPercent != null) {
                    viewModelMediaInfoProgressPercentGet = viewModelMediaInfoProgressPercent.get();
                }
            }
            if ((dirtyFlags & 544) != 0) {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoCurrentTime = null;
                } else {
                    viewModelMediaInfoCurrentTime = viewModelMediaInfo.currentTime;
                }
                updateRegistration(5, viewModelMediaInfoCurrentTime);
                if (viewModelMediaInfoCurrentTime != null) {
                    viewModelMediaInfoCurrentTimeGet = viewModelMediaInfoCurrentTime.get();
                }
            }
            if ((dirtyFlags & 576) != 0) {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoPic = null;
                } else {
                    viewModelMediaInfoPic = viewModelMediaInfo.pic;
                }
                updateRegistration(6, viewModelMediaInfoPic);
                if (viewModelMediaInfoPic != null) {
                    viewModelMediaInfoPicGet2 = viewModelMediaInfoPic.get();
                }
            }
            if ((dirtyFlags & 640) == 0) {
                viewModelMediaInfoPicGet = viewModelMediaInfoPicGet2;
                viewModelMediaInfoTotalTimeGet = null;
            } else {
                if (viewModelMediaInfo == null) {
                    viewModelMediaInfoTotalTime = null;
                } else {
                    viewModelMediaInfoTotalTime = viewModelMediaInfo.totalTime;
                }
                updateRegistration(7, viewModelMediaInfoTotalTime);
                if (viewModelMediaInfoTotalTime == null) {
                    viewModelMediaInfoPicGet = viewModelMediaInfoPicGet2;
                    viewModelMediaInfoTotalTimeGet = null;
                } else {
                    String viewModelMediaInfoTotalTimeGet2 = viewModelMediaInfoTotalTime.get();
                    viewModelMediaInfoPicGet = viewModelMediaInfoPicGet2;
                    viewModelMediaInfoTotalTimeGet = viewModelMediaInfoTotalTimeGet2;
                }
            }
        }
        if ((dirtyFlags & 514) == 0) {
            viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtistGet2;
            viewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = 0;
        } else {
            ObservableField<Boolean> viewModelBThirdMusic = DashboardViewModel.bThirdMusic;
            viewModelMediaInfoMusicAtistGet = viewModelMediaInfoMusicAtistGet2;
            updateRegistration(1, viewModelBThirdMusic);
            if (viewModelBThirdMusic != null) {
                Boolean viewModelBThirdMusicGet2 = viewModelBThirdMusic.get();
                viewModelBThirdMusicGet = viewModelBThirdMusicGet2;
            }
            boolean androidDatabindingViewDataBindingSafeUnboxViewModelBThirdMusicGet = ViewDataBinding.safeUnbox(viewModelBThirdMusicGet);
            boolean viewModelBThirdMusicBooleanTrue = androidDatabindingViewDataBindingSafeUnboxViewModelBThirdMusicGet;
            if ((dirtyFlags & 514) != 0) {
                if (viewModelBThirdMusicBooleanTrue) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                }
            }
            viewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE = viewModelBThirdMusicBooleanTrue ? 8 : 0;
        }
        if ((dirtyFlags & 516) != 0) {
            viewModelMediaInfoMusicNameJavaLangObjectNullBmwId8DashboardMusicNameAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName = viewModelMediaInfoMusicNameJavaLangObjectNull ? this.bmwId8DashboardMusicName.getResources().getString(C0899R.string.ksw_idf7_unkonw_soung) : viewModelMediaInfoMusicNameGet;
        }
        if ((dirtyFlags & 520) == 0) {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView5AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = null;
        } else {
            viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView5AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist = viewModelMediaInfoMusicAtistJavaLangObjectNull ? this.mboundView5.getResources().getString(C0899R.string.ksw_idf7_unknow_artis) : viewModelMediaInfoMusicAtistGet;
        }
        if ((dirtyFlags & 516) != 0) {
            TextViewBindingAdapter.setText(this.bmwId8DashboardMusicName, viewModelMediaInfoMusicNameJavaLangObjectNullBmwId8DashboardMusicNameAndroidStringKswIdf7UnkonwSoungViewModelMediaInfoMusicName);
        }
        if ((dirtyFlags & 528) != 0) {
            CustomizeSeekBar.setProgress(this.bmwId8DashboardMusicSeekbar, viewModelMediaInfoProgressPercentGet);
        }
        if ((dirtyFlags & 513) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, viewModelMediaInfoHightLrcRowGet);
        }
        if ((dirtyFlags & 514) != 0) {
            this.mboundView2.setVisibility(viewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
            this.mboundView3.setVisibility(viewModelBThirdMusicBooleanTrueViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 576) != 0) {
            BaseBindingModel.setID8AlbumIcon(this.mboundView2, viewModelMediaInfoPicGet);
        }
        if ((dirtyFlags & 520) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, viewModelMediaInfoMusicAtistJavaLangObjectNullMboundView5AndroidStringKswIdf7UnknowArtisViewModelMediaInfoMusicAtist);
        }
        if ((dirtyFlags & 544) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, viewModelMediaInfoCurrentTimeGet);
        }
        if ((dirtyFlags & 640) != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, viewModelMediaInfoTotalTimeGet);
        }
    }
}

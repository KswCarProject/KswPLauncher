package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7_ui.view.CustomViewpager;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public class AlsId7UiMainBindingImpl extends AlsId7UiMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MarqueeTextView mboundView2;
    private final MarqueeTextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.guideline, 5);
        sparseIntArray.put(C0899R.C0901id.imageView1, 6);
        sparseIntArray.put(C0899R.C0901id.itemViewSkin_default, 7);
        sparseIntArray.put(C0899R.C0901id.skin_ll, 8);
        sparseIntArray.put(C0899R.C0901id.blue_iv, 9);
        sparseIntArray.put(C0899R.C0901id.yellow_iv, 10);
        sparseIntArray.put(C0899R.C0901id.red_iv, 11);
        sparseIntArray.put(C0899R.C0901id.imageView4, 12);
        sparseIntArray.put(C0899R.C0901id.imageView3, 13);
        sparseIntArray.put(C0899R.C0901id.viewPage, 14);
        sparseIntArray.put(C0899R.C0901id.menuConstraintLayout, 15);
        sparseIntArray.put(C0899R.C0901id.menu_button1, 16);
        sparseIntArray.put(C0899R.C0901id.menu_button2, 17);
        sparseIntArray.put(C0899R.C0901id.menu_button3, 18);
        sparseIntArray.put(C0899R.C0901id.btn_3_iv, 19);
        sparseIntArray.put(C0899R.C0901id.menu_button4, 20);
        sparseIntArray.put(C0899R.C0901id.btn_4_iv, 21);
        sparseIntArray.put(C0899R.C0901id.menu_button5, 22);
        sparseIntArray.put(C0899R.C0901id.btn_5_iv, 23);
    }

    public AlsId7UiMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private AlsId7UiMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ConstraintLayout) bindings[0], (ImageView) bindings[9], (ImageView) bindings[1], (ImageView) bindings[3], (ImageView) bindings[19], (ImageView) bindings[21], (ImageView) bindings[23], (Guideline) bindings[5], (ImageView) bindings[6], (ImageView) bindings[13], (ImageView) bindings[12], (ImageView) bindings[7], (RelativeLayout) bindings[16], (RelativeLayout) bindings[17], (RelativeLayout) bindings[18], (RelativeLayout) bindings[20], (RelativeLayout) bindings[22], (LinearLayout) bindings[15], (ImageView) bindings[11], (RelativeLayout) bindings[8], (CustomViewpager) bindings[14], (ImageView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.alsRoot.setTag(null);
        this.btn1Iv.setTag(null);
        this.btn2Iv.setTag(null);
        MarqueeTextView marqueeTextView = (MarqueeTextView) bindings[2];
        this.mboundView2 = marqueeTextView;
        marqueeTextView.setTag(null);
        MarqueeTextView marqueeTextView2 = (MarqueeTextView) bindings[4];
        this.mboundView4 = marqueeTextView2;
        marqueeTextView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (7 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7UiMainBinding
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelShortCutIcon2((ObservableField) object, fieldId);
            case 1:
                return onChangeLauncherViewModelShortCutIcon1((ObservableField) object, fieldId);
            case 2:
                return onChangeLauncherViewModelShortCutName1((ObservableField) object, fieldId);
            case 3:
                return onChangeLauncherViewModelShortCutName2((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelShortCutIcon2(ObservableField<Drawable> LauncherViewModelShortCutIcon2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutIcon1(ObservableField<Drawable> LauncherViewModelShortCutIcon1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutName1(ObservableField<String> LauncherViewModelShortCutName1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutName2(ObservableField<String> LauncherViewModelShortCutName2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Drawable launcherViewModelShortCutIcon2Get = null;
        ObservableField<Drawable> launcherViewModelShortCutIcon2 = null;
        ObservableField<Drawable> launcherViewModelShortCutIcon1 = null;
        Drawable launcherViewModelShortCutIcon1JavaLangObjectNullBtn1IvAndroidDrawableAlsSpId7MainLeftIconMusicLauncherViewModelShortCutIcon1 = null;
        boolean launcherViewModelShortCutIcon2JavaLangObjectNull = false;
        boolean launcherViewModelShortCutName1JavaLangObjectNull = false;
        ObservableField<String> launcherViewModelShortCutName1 = null;
        ObservableField<String> launcherViewModelShortCutName2 = null;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        String launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringKswId7MusicLauncherViewModelShortCutName1 = null;
        String launcherViewModelShortCutName2Get = null;
        boolean launcherViewModelShortCutName2JavaLangObjectNull = false;
        String launcherViewModelShortCutName1Get = null;
        Drawable launcherViewModelShortCutIcon1Get = null;
        boolean launcherViewModelShortCutIcon1JavaLangObjectNull = false;
        if ((dirtyFlags & 63) != 0) {
            if ((dirtyFlags & 49) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutIcon2 = launcherViewModel.shortCutIcon2;
                }
                updateRegistration(0, launcherViewModelShortCutIcon2);
                if (launcherViewModelShortCutIcon2 != null) {
                    launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2.get();
                }
                launcherViewModelShortCutIcon2JavaLangObjectNull = launcherViewModelShortCutIcon2Get == null;
                if ((dirtyFlags & 49) != 0) {
                    dirtyFlags = launcherViewModelShortCutIcon2JavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_URI : dirtyFlags | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
            }
            if ((dirtyFlags & 50) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutIcon1 = launcherViewModel.shortCutIcon1;
                }
                updateRegistration(1, launcherViewModelShortCutIcon1);
                if (launcherViewModelShortCutIcon1 != null) {
                    launcherViewModelShortCutIcon1Get = launcherViewModelShortCutIcon1.get();
                }
                launcherViewModelShortCutIcon1JavaLangObjectNull = launcherViewModelShortCutIcon1Get == null;
                if ((dirtyFlags & 50) != 0) {
                    if (launcherViewModelShortCutIcon1JavaLangObjectNull) {
                        dirtyFlags |= 128;
                    } else {
                        dirtyFlags |= 64;
                    }
                }
            }
            if ((dirtyFlags & 52) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutName1 = launcherViewModel.shortCutName1;
                }
                updateRegistration(2, launcherViewModelShortCutName1);
                if (launcherViewModelShortCutName1 != null) {
                    launcherViewModelShortCutName1Get = launcherViewModelShortCutName1.get();
                }
                launcherViewModelShortCutName1JavaLangObjectNull = launcherViewModelShortCutName1Get == null;
                if ((dirtyFlags & 52) != 0) {
                    if (launcherViewModelShortCutName1JavaLangObjectNull) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
            }
            if ((dirtyFlags & 56) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutName2 = launcherViewModel.shortCutName2;
                }
                updateRegistration(3, launcherViewModelShortCutName2);
                if (launcherViewModelShortCutName2 != null) {
                    launcherViewModelShortCutName2Get = launcherViewModelShortCutName2.get();
                }
                launcherViewModelShortCutName2JavaLangObjectNull = launcherViewModelShortCutName2Get == null;
                if ((dirtyFlags & 56) != 0) {
                    if (launcherViewModelShortCutName2JavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    }
                }
            }
        }
        if ((dirtyFlags & 50) != 0) {
            launcherViewModelShortCutIcon1JavaLangObjectNullBtn1IvAndroidDrawableAlsSpId7MainLeftIconMusicLauncherViewModelShortCutIcon1 = launcherViewModelShortCutIcon1JavaLangObjectNull ? AppCompatResources.getDrawable(this.btn1Iv.getContext(), C0899R.C0900drawable.als_sp_id7_main_left_icon_music) : launcherViewModelShortCutIcon1Get;
        }
        if ((dirtyFlags & 52) != 0) {
            launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringKswId7MusicLauncherViewModelShortCutName1 = launcherViewModelShortCutName1JavaLangObjectNull ? this.mboundView2.getResources().getString(C0899R.string.ksw_id7_music) : launcherViewModelShortCutName1Get;
        }
        if ((dirtyFlags & 56) == 0) {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2 = null;
        } else {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2 = launcherViewModelShortCutName2JavaLangObjectNull ? this.mboundView4.getResources().getString(C0899R.string.ksw_id7_navi) : launcherViewModelShortCutName2Get;
        }
        if ((dirtyFlags & 49) == 0) {
            launcherViewModelShortCutIcon2Get = null;
        } else if (launcherViewModelShortCutIcon2JavaLangObjectNull) {
            launcherViewModelShortCutIcon2Get = AppCompatResources.getDrawable(this.btn2Iv.getContext(), C0899R.C0900drawable.als_sp_id7_main_left_icon_navi);
        }
        if ((dirtyFlags & 50) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btn1Iv, launcherViewModelShortCutIcon1JavaLangObjectNullBtn1IvAndroidDrawableAlsSpId7MainLeftIconMusicLauncherViewModelShortCutIcon1);
        }
        if ((dirtyFlags & 49) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.btn2Iv, launcherViewModelShortCutIcon2Get);
        }
        if ((dirtyFlags & 52) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringKswId7MusicLauncherViewModelShortCutName1);
        }
        if ((dirtyFlags & 56) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2);
        }
    }
}

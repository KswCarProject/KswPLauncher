package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

public class AlsId7UiMainBindingImpl extends AlsId7UiMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MarqueeTextView mboundView2;
    private final MarqueeTextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline, 5);
        sparseIntArray.put(R.id.imageView1, 6);
        sparseIntArray.put(R.id.itemViewSkin_default, 7);
        sparseIntArray.put(R.id.skin_ll, 8);
        sparseIntArray.put(R.id.blue_iv, 9);
        sparseIntArray.put(R.id.yellow_iv, 10);
        sparseIntArray.put(R.id.red_iv, 11);
        sparseIntArray.put(R.id.imageView4, 12);
        sparseIntArray.put(R.id.imageView3, 13);
        sparseIntArray.put(R.id.viewPage, 14);
        sparseIntArray.put(R.id.menuConstraintLayout, 15);
        sparseIntArray.put(R.id.menu_button1, 16);
        sparseIntArray.put(R.id.menu_button2, 17);
        sparseIntArray.put(R.id.menu_button3, 18);
        sparseIntArray.put(R.id.btn_3_iv, 19);
        sparseIntArray.put(R.id.menu_button4, 20);
        sparseIntArray.put(R.id.btn_4_iv, 21);
        sparseIntArray.put(R.id.menu_button5, 22);
        sparseIntArray.put(R.id.btn_5_iv, 23);
    }

    public AlsId7UiMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }

    private AlsId7UiMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, bindings[0], bindings[9], bindings[1], bindings[3], bindings[19], bindings[21], bindings[23], bindings[5], bindings[6], bindings[13], bindings[12], bindings[7], bindings[16], bindings[17], bindings[18], bindings[20], bindings[22], bindings[15], bindings[11], bindings[8], bindings[14], bindings[10]);
        this.mDirtyFlags = -1;
        this.alsRoot.setTag((Object) null);
        this.btn1Iv.setTag((Object) null);
        this.btn2Iv.setTag((Object) null);
        MarqueeTextView marqueeTextView = bindings[2];
        this.mboundView2 = marqueeTextView;
        marqueeTextView.setTag((Object) null);
        MarqueeTextView marqueeTextView2 = bindings[4];
        this.mboundView4 = marqueeTextView2;
        marqueeTextView2.setTag((Object) null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (4 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeLauncherViewModelShortCutIcon2(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutIcon1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutName1(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutName2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        String launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
                updateRegistration(0, (Observable) launcherViewModelShortCutIcon2);
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
                updateRegistration(1, (Observable) launcherViewModelShortCutIcon1);
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
                updateRegistration(2, (Observable) launcherViewModelShortCutName1);
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
                updateRegistration(3, (Observable) launcherViewModelShortCutName2);
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
            launcherViewModelShortCutIcon1JavaLangObjectNullBtn1IvAndroidDrawableAlsSpId7MainLeftIconMusicLauncherViewModelShortCutIcon1 = launcherViewModelShortCutIcon1JavaLangObjectNull ? AppCompatResources.getDrawable(this.btn1Iv.getContext(), R.drawable.als_sp_id7_main_left_icon_music) : launcherViewModelShortCutIcon1Get;
        }
        if ((dirtyFlags & 52) != 0) {
            launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringKswId7MusicLauncherViewModelShortCutName1 = launcherViewModelShortCutName1JavaLangObjectNull ? this.mboundView2.getResources().getString(R.string.ksw_id7_music) : launcherViewModelShortCutName1Get;
        }
        if ((dirtyFlags & 56) != 0) {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2 = launcherViewModelShortCutName2JavaLangObjectNull ? this.mboundView4.getResources().getString(R.string.ksw_id7_navi) : launcherViewModelShortCutName2Get;
        } else {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7NaviLauncherViewModelShortCutName2 = null;
        }
        if ((dirtyFlags & 49) != 0) {
            if (launcherViewModelShortCutIcon2JavaLangObjectNull) {
                Drawable drawable = launcherViewModelShortCutIcon2Get;
                launcherViewModelShortCutIcon2Get = AppCompatResources.getDrawable(this.btn2Iv.getContext(), R.drawable.als_sp_id7_main_left_icon_navi);
            }
            Drawable launcherViewModelShortCutIcon2JavaLangObjectNullBtn2IvAndroidDrawableAlsSpId7MainLeftIconNaviLauncherViewModelShortCutIcon2 = launcherViewModelShortCutIcon2Get;
        } else {
            launcherViewModelShortCutIcon2Get = null;
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

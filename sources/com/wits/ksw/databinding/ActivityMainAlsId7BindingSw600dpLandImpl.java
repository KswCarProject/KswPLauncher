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
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public class ActivityMainAlsId7BindingSw600dpLandImpl extends ActivityMainAlsId7Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;
    private final MarqueeTextView mboundView2;
    private final ImageView mboundView3;
    private final MarqueeTextView mboundView4;
    private final ImageView mboundView5;
    private final MarqueeTextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.guideline, 7);
        sparseIntArray.put(C0899R.C0901id.imageView1, 8);
        sparseIntArray.put(C0899R.C0901id.imageView4, 9);
        sparseIntArray.put(C0899R.C0901id.imageView3, 10);
        sparseIntArray.put(C0899R.C0901id.viewPage, 11);
        sparseIntArray.put(C0899R.C0901id.ll_left, 12);
        sparseIntArray.put(C0899R.C0901id.btn_apps, 13);
        sparseIntArray.put(C0899R.C0901id.btn_settings, 14);
        sparseIntArray.put(C0899R.C0901id.btn_shut_1, 15);
        sparseIntArray.put(C0899R.C0901id.btn_shut_2, 16);
        sparseIntArray.put(C0899R.C0901id.btn_shut_3, 17);
    }

    public ActivityMainAlsId7BindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityMainAlsId7BindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (LinearLayout) bindings[13], (LinearLayout) bindings[14], (LinearLayout) bindings[15], (LinearLayout) bindings[16], (LinearLayout) bindings[17], (Guideline) bindings[7], (ImageView) bindings[8], (ImageView) bindings[10], (ImageView) bindings[9], (LinearLayout) bindings[12], (ViewPager) bindings[11]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        MarqueeTextView marqueeTextView = (MarqueeTextView) bindings[2];
        this.mboundView2 = marqueeTextView;
        marqueeTextView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        MarqueeTextView marqueeTextView2 = (MarqueeTextView) bindings[4];
        this.mboundView4 = marqueeTextView2;
        marqueeTextView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        MarqueeTextView marqueeTextView3 = (MarqueeTextView) bindings[6];
        this.mboundView6 = marqueeTextView3;
        marqueeTextView3.setTag(null);
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
        if (7 == variableId) {
            setLauncherViewModel((LauncherViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.ActivityMainAlsId7Binding
    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLauncherViewModelShortCutIcon3((ObservableField) object, fieldId);
            case 1:
                return onChangeLauncherViewModelShortCutIcon2((ObservableField) object, fieldId);
            case 2:
                return onChangeLauncherViewModelShortCutIcon1((ObservableField) object, fieldId);
            case 3:
                return onChangeLauncherViewModelShortCutName1((ObservableField) object, fieldId);
            case 4:
                return onChangeLauncherViewModelShortCutName2((ObservableField) object, fieldId);
            case 5:
                return onChangeLauncherViewModelShortCutName3((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLauncherViewModelShortCutIcon3(ObservableField<Drawable> LauncherViewModelShortCutIcon3, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutIcon2(ObservableField<Drawable> LauncherViewModelShortCutIcon2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutIcon1(ObservableField<Drawable> LauncherViewModelShortCutIcon1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutName1(ObservableField<String> LauncherViewModelShortCutName1, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutName2(ObservableField<String> LauncherViewModelShortCutName2, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLauncherViewModelShortCutName3(ObservableField<String> LauncherViewModelShortCutName3, int fieldId) {
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
        Drawable launcherViewModelShortCutIcon2Get;
        String launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2;
        String launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3;
        Drawable launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2;
        Drawable launcherViewModelShortCutIcon2Get2;
        ObservableField<String> launcherViewModelShortCutName3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Drawable launcherViewModelShortCutIcon2Get3 = null;
        Drawable launcherViewModelShortCutIcon1JavaLangObjectNullMboundView1AndroidDrawableId7MainBrowserNLauncherViewModelShortCutIcon1 = null;
        ObservableField<Drawable> launcherViewModelShortCutIcon3 = null;
        ObservableField<Drawable> launcherViewModelShortCutIcon2 = null;
        ObservableField<Drawable> launcherViewModelShortCutIcon1 = null;
        Drawable launcherViewModelShortCutIcon3JavaLangObjectNullMboundView5AndroidDrawableId7MainVideoNLauncherViewModelShortCutIcon3 = null;
        boolean launcherViewModelShortCutIcon2JavaLangObjectNull = false;
        boolean launcherViewModelShortCutName1JavaLangObjectNull = false;
        String launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringId7BrowserLauncherViewModelShortCutName1 = null;
        ObservableField<String> launcherViewModelShortCutName1 = null;
        ObservableField<String> launcherViewModelShortCutName2 = null;
        LauncherViewModel launcherViewModel = this.mLauncherViewModel;
        boolean launcherViewModelShortCutName3JavaLangObjectNull = false;
        Drawable launcherViewModelShortCutIcon3Get = null;
        String launcherViewModelShortCutName2Get = null;
        boolean launcherViewModelShortCutIcon3JavaLangObjectNull = false;
        boolean launcherViewModelShortCutName2JavaLangObjectNull = false;
        String launcherViewModelShortCutName1Get = null;
        Drawable launcherViewModelShortCutIcon1Get = null;
        String launcherViewModelShortCutName3Get = null;
        boolean launcherViewModelShortCutIcon1JavaLangObjectNull = false;
        if ((dirtyFlags & 255) != 0) {
            Drawable launcherViewModelShortCutIcon2Get4 = null;
            if ((dirtyFlags & 193) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutIcon3 = launcherViewModel.shortCutIcon3;
                }
                updateRegistration(0, launcherViewModelShortCutIcon3);
                if (launcherViewModelShortCutIcon3 != null) {
                    launcherViewModelShortCutIcon3Get = launcherViewModelShortCutIcon3.get();
                }
                launcherViewModelShortCutIcon3JavaLangObjectNull = launcherViewModelShortCutIcon3Get == null;
                if ((dirtyFlags & 193) != 0) {
                    dirtyFlags = launcherViewModelShortCutIcon3JavaLangObjectNull ? dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : dirtyFlags | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
            }
            if ((dirtyFlags & 194) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutIcon2 = launcherViewModel.shortCutIcon2;
                }
                updateRegistration(1, launcherViewModelShortCutIcon2);
                if (launcherViewModelShortCutIcon2 != null) {
                    Drawable launcherViewModelShortCutIcon2Get5 = launcherViewModelShortCutIcon2.get();
                    launcherViewModelShortCutIcon2Get4 = launcherViewModelShortCutIcon2Get5;
                }
                launcherViewModelShortCutIcon2JavaLangObjectNull = launcherViewModelShortCutIcon2Get4 == null;
                if ((dirtyFlags & 194) != 0) {
                    if (launcherViewModelShortCutIcon2JavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    }
                }
            }
            if ((dirtyFlags & 196) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutIcon1 = launcherViewModel.shortCutIcon1;
                }
                updateRegistration(2, launcherViewModelShortCutIcon1);
                if (launcherViewModelShortCutIcon1 != null) {
                    launcherViewModelShortCutIcon1Get = launcherViewModelShortCutIcon1.get();
                }
                launcherViewModelShortCutIcon1JavaLangObjectNull = launcherViewModelShortCutIcon1Get == null;
                if ((dirtyFlags & 196) != 0) {
                    if (launcherViewModelShortCutIcon1JavaLangObjectNull) {
                        dirtyFlags |= 512;
                    } else {
                        dirtyFlags |= 256;
                    }
                }
            }
            if ((dirtyFlags & 200) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutName1 = launcherViewModel.shortCutName1;
                }
                updateRegistration(3, launcherViewModelShortCutName1);
                if (launcherViewModelShortCutName1 != null) {
                    launcherViewModelShortCutName1Get = launcherViewModelShortCutName1.get();
                }
                launcherViewModelShortCutName1JavaLangObjectNull = launcherViewModelShortCutName1Get == null;
                if ((dirtyFlags & 200) != 0) {
                    if (launcherViewModelShortCutName1JavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                }
            }
            if ((dirtyFlags & 208) != 0) {
                if (launcherViewModel != null) {
                    launcherViewModelShortCutName2 = launcherViewModel.shortCutName2;
                }
                updateRegistration(4, launcherViewModelShortCutName2);
                if (launcherViewModelShortCutName2 != null) {
                    launcherViewModelShortCutName2Get = launcherViewModelShortCutName2.get();
                }
                launcherViewModelShortCutName2JavaLangObjectNull = launcherViewModelShortCutName2Get == null;
                if ((dirtyFlags & 208) != 0) {
                    if (launcherViewModelShortCutName2JavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    }
                }
            }
            if ((dirtyFlags & 224) != 0) {
                if (launcherViewModel == null) {
                    launcherViewModelShortCutName3 = null;
                } else {
                    launcherViewModelShortCutName3 = launcherViewModel.shortCutName3;
                }
                updateRegistration(5, launcherViewModelShortCutName3);
                if (launcherViewModelShortCutName3 != null) {
                    launcherViewModelShortCutName3Get = launcherViewModelShortCutName3.get();
                }
                launcherViewModelShortCutName3JavaLangObjectNull = launcherViewModelShortCutName3Get == null;
                if ((dirtyFlags & 224) == 0) {
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                } else if (launcherViewModelShortCutName3JavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                }
            } else {
                launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
            }
        }
        if ((dirtyFlags & 196) == 0) {
            launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
        } else {
            if (launcherViewModelShortCutIcon1JavaLangObjectNull) {
                launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
                launcherViewModelShortCutIcon2Get2 = AppCompatResources.getDrawable(this.mboundView1.getContext(), C0899R.C0900drawable.id7_main_browser_n);
            } else {
                launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
                launcherViewModelShortCutIcon2Get2 = launcherViewModelShortCutIcon1Get;
            }
            launcherViewModelShortCutIcon1JavaLangObjectNullMboundView1AndroidDrawableId7MainBrowserNLauncherViewModelShortCutIcon1 = launcherViewModelShortCutIcon2Get2;
        }
        if ((dirtyFlags & 193) != 0) {
            launcherViewModelShortCutIcon3JavaLangObjectNullMboundView5AndroidDrawableId7MainVideoNLauncherViewModelShortCutIcon3 = launcherViewModelShortCutIcon3JavaLangObjectNull ? AppCompatResources.getDrawable(this.mboundView5.getContext(), C0899R.C0900drawable.id7_main_video_n) : launcherViewModelShortCutIcon3Get;
        }
        if ((dirtyFlags & 200) != 0) {
            launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringId7BrowserLauncherViewModelShortCutName1 = launcherViewModelShortCutName1JavaLangObjectNull ? this.mboundView2.getResources().getString(C0899R.string.id7_browser) : launcherViewModelShortCutName1Get;
        }
        if ((dirtyFlags & 208) == 0) {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2 = null;
        } else {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2 = launcherViewModelShortCutName2JavaLangObjectNull ? this.mboundView4.getResources().getString(C0899R.string.ksw_id7_music) : launcherViewModelShortCutName2Get;
        }
        if ((dirtyFlags & 224) == 0) {
            launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3 = null;
        } else {
            launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3 = launcherViewModelShortCutName3JavaLangObjectNull ? this.mboundView6.getResources().getString(C0899R.string.ksw_id7_hd_video) : launcherViewModelShortCutName3Get;
        }
        if ((dirtyFlags & 194) == 0) {
            launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2 = null;
        } else {
            launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2 = launcherViewModelShortCutIcon2JavaLangObjectNull ? AppCompatResources.getDrawable(this.mboundView3.getContext(), C0899R.C0900drawable.id7_main_music_n) : launcherViewModelShortCutIcon2Get;
        }
        if ((dirtyFlags & 196) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView1, launcherViewModelShortCutIcon1JavaLangObjectNullMboundView1AndroidDrawableId7MainBrowserNLauncherViewModelShortCutIcon1);
        }
        if ((dirtyFlags & 200) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringId7BrowserLauncherViewModelShortCutName1);
        }
        if ((dirtyFlags & 194) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView3, launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2);
        }
        if ((dirtyFlags & 208) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2);
        }
        if ((dirtyFlags & 193) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.mboundView5, launcherViewModelShortCutIcon3JavaLangObjectNullMboundView5AndroidDrawableId7MainVideoNLauncherViewModelShortCutIcon3);
        }
        if ((dirtyFlags & 224) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3);
        }
    }
}

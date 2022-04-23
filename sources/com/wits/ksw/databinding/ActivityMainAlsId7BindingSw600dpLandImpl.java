package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.MarqueeTextView;

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
        sparseIntArray.put(R.id.guideline, 7);
        sparseIntArray.put(R.id.imageView1, 8);
        sparseIntArray.put(R.id.imageView4, 9);
        sparseIntArray.put(R.id.imageView3, 10);
        sparseIntArray.put(R.id.viewPage, 11);
        sparseIntArray.put(R.id.ll_left, 12);
        sparseIntArray.put(R.id.btn_apps, 13);
        sparseIntArray.put(R.id.btn_settings, 14);
        sparseIntArray.put(R.id.btn_shut_1, 15);
        sparseIntArray.put(R.id.btn_shut_2, 16);
        sparseIntArray.put(R.id.btn_shut_3, 17);
    }

    public ActivityMainAlsId7BindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainAlsId7BindingSw600dpLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, bindings[13], bindings[14], bindings[15], bindings[16], bindings[17], bindings[7], bindings[8], bindings[10], bindings[9], bindings[12], bindings[11]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ImageView imageView = bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag((Object) null);
        MarqueeTextView marqueeTextView = bindings[2];
        this.mboundView2 = marqueeTextView;
        marqueeTextView.setTag((Object) null);
        ImageView imageView2 = bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag((Object) null);
        MarqueeTextView marqueeTextView2 = bindings[4];
        this.mboundView4 = marqueeTextView2;
        marqueeTextView2.setTag((Object) null);
        ImageView imageView3 = bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag((Object) null);
        MarqueeTextView marqueeTextView3 = bindings[6];
        this.mboundView6 = marqueeTextView3;
        marqueeTextView3.setTag((Object) null);
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
        if (4 != variableId) {
            return false;
        }
        setLauncherViewModel((LauncherViewModel) variable);
        return true;
    }

    public void setLauncherViewModel(LauncherViewModel LauncherViewModel) {
        this.mLauncherViewModel = LauncherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
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

    private boolean onChangeLauncherViewModelShortCutIcon3(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutIcon2(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutIcon1(ObservableField<Drawable> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutName1(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutName2(ObservableField<String> observableField, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeLauncherViewModelShortCutName3(ObservableField<String> observableField, int fieldId) {
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
        Drawable launcherViewModelShortCutIcon2Get;
        String launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2;
        String launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3;
        Drawable launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2;
        Drawable launcherViewModelShortCutIcon2Get2;
        ObservableField<String> launcherViewModelShortCutName3;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
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
                updateRegistration(0, (Observable) launcherViewModelShortCutIcon3);
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
                updateRegistration(1, (Observable) launcherViewModelShortCutIcon2);
                if (launcherViewModelShortCutIcon2 != null) {
                    launcherViewModelShortCutIcon2Get4 = launcherViewModelShortCutIcon2.get();
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
                updateRegistration(2, (Observable) launcherViewModelShortCutIcon1);
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
                updateRegistration(3, (Observable) launcherViewModelShortCutName1);
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
                updateRegistration(4, (Observable) launcherViewModelShortCutName2);
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
                if (launcherViewModel != null) {
                    launcherViewModelShortCutName3 = launcherViewModel.shortCutName3;
                } else {
                    launcherViewModelShortCutName3 = null;
                }
                LauncherViewModel launcherViewModel2 = launcherViewModel;
                updateRegistration(5, (Observable) launcherViewModelShortCutName3);
                if (launcherViewModelShortCutName3 != null) {
                    launcherViewModelShortCutName3Get = launcherViewModelShortCutName3.get();
                }
                launcherViewModelShortCutName3JavaLangObjectNull = launcherViewModelShortCutName3Get == null;
                if ((dirtyFlags & 224) == 0) {
                    ObservableField<String> observableField = launcherViewModelShortCutName3;
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                } else if (launcherViewModelShortCutName3JavaLangObjectNull) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    ObservableField<String> observableField2 = launcherViewModelShortCutName3;
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    ObservableField<String> observableField3 = launcherViewModelShortCutName3;
                    launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
                }
            } else {
                launcherViewModelShortCutIcon2Get3 = launcherViewModelShortCutIcon2Get4;
            }
        } else {
            LauncherViewModel launcherViewModel3 = launcherViewModel;
        }
        if ((dirtyFlags & 196) != 0) {
            if (launcherViewModelShortCutIcon1JavaLangObjectNull) {
                launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
                launcherViewModelShortCutIcon2Get2 = AppCompatResources.getDrawable(this.mboundView1.getContext(), R.drawable.id7_main_browser_n);
            } else {
                launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
                launcherViewModelShortCutIcon2Get2 = launcherViewModelShortCutIcon1Get;
            }
            launcherViewModelShortCutIcon1JavaLangObjectNullMboundView1AndroidDrawableId7MainBrowserNLauncherViewModelShortCutIcon1 = launcherViewModelShortCutIcon2Get2;
        } else {
            launcherViewModelShortCutIcon2Get = launcherViewModelShortCutIcon2Get3;
        }
        if ((dirtyFlags & 193) != 0) {
            launcherViewModelShortCutIcon3JavaLangObjectNullMboundView5AndroidDrawableId7MainVideoNLauncherViewModelShortCutIcon3 = launcherViewModelShortCutIcon3JavaLangObjectNull ? AppCompatResources.getDrawable(this.mboundView5.getContext(), R.drawable.id7_main_video_n) : launcherViewModelShortCutIcon3Get;
        }
        if ((dirtyFlags & 200) != 0) {
            launcherViewModelShortCutName1JavaLangObjectNullMboundView2AndroidStringId7BrowserLauncherViewModelShortCutName1 = launcherViewModelShortCutName1JavaLangObjectNull ? this.mboundView2.getResources().getString(R.string.id7_browser) : launcherViewModelShortCutName1Get;
        }
        if ((dirtyFlags & 208) != 0) {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2 = launcherViewModelShortCutName2JavaLangObjectNull ? this.mboundView4.getResources().getString(R.string.ksw_id7_music) : launcherViewModelShortCutName2Get;
        } else {
            launcherViewModelShortCutName2JavaLangObjectNullMboundView4AndroidStringKswId7MusicLauncherViewModelShortCutName2 = null;
        }
        if ((dirtyFlags & 224) != 0) {
            if (launcherViewModelShortCutName3JavaLangObjectNull) {
                boolean z = launcherViewModelShortCutName3JavaLangObjectNull;
                launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3 = this.mboundView6.getResources().getString(R.string.ksw_id7_hd_video);
            } else {
                launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3 = launcherViewModelShortCutName3Get;
            }
        } else {
            launcherViewModelShortCutName3JavaLangObjectNullMboundView6AndroidStringKswId7HdVideoLauncherViewModelShortCutName3 = null;
        }
        if ((dirtyFlags & 194) != 0) {
            if (launcherViewModelShortCutIcon2JavaLangObjectNull) {
                ObservableField<Drawable> observableField4 = launcherViewModelShortCutIcon3;
                launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2 = AppCompatResources.getDrawable(this.mboundView3.getContext(), R.drawable.id7_main_music_n);
            } else {
                launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2 = launcherViewModelShortCutIcon2Get;
            }
        } else {
            launcherViewModelShortCutIcon2JavaLangObjectNullMboundView3AndroidDrawableId7MainMusicNLauncherViewModelShortCutIcon2 = null;
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

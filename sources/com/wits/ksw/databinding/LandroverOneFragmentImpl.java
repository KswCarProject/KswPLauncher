package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public class LandroverOneFragmentImpl extends LandroverOneFragment implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback174;
    private final View.OnClickListener mCallback175;
    private final View.OnClickListener mCallback176;
    private final View.OnClickListener mCallback177;
    private final View.OnClickListener mCallback178;
    private final View.OnClickListener mCallback179;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverOneFragmentImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LandroverOneFragmentImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[4], bindings[5], bindings[1], bindings[3], bindings[6], bindings[2]);
        this.mDirtyFlags = -1;
        this.landroverMainIconCar.setTag((Object) null);
        this.landroverMainIconDvr.setTag((Object) null);
        this.landroverMainIconGps.setTag((Object) null);
        this.landroverMainIconMusic.setTag((Object) null);
        this.landroverMainIconSettings.setTag((Object) null);
        this.landroverMainIconVideo.setTag((Object) null);
        RelativeLayout relativeLayout = bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        setRootTag(root);
        this.mCallback179 = new OnClickListener(this, 6);
        this.mCallback177 = new OnClickListener(this, 4);
        this.mCallback178 = new OnClickListener(this, 5);
        this.mCallback175 = new OnClickListener(this, 2);
        this.mCallback176 = new OnClickListener(this, 3);
        this.mCallback174 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        setViewModel((LandroverViewModel) variable);
        return true;
    }

    public void setViewModel(LandroverViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LandroverViewModel viewModel = this.mViewModel;
        View.OnFocusChangeListener viewModelSettingsViewFocusChangeListener = null;
        if (!((dirtyFlags & 3) == 0 || viewModel == null)) {
            viewModelSettingsViewFocusChangeListener = viewModel.settingsViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.landroverMainIconCar.setOnClickListener(this.mCallback177);
            this.landroverMainIconDvr.setOnClickListener(this.mCallback178);
            this.landroverMainIconGps.setOnClickListener(this.mCallback174);
            this.landroverMainIconMusic.setOnClickListener(this.mCallback176);
            this.landroverMainIconSettings.setOnClickListener(this.mCallback179);
            this.landroverMainIconVideo.setOnClickListener(this.mCallback175);
        }
        if ((3 & dirtyFlags) != 0) {
            this.landroverMainIconSettings.setOnFocusChangeListener(viewModelSettingsViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull = true;
        switch (sourceId) {
            case 1:
                LandroverViewModel viewModel = this.mViewModel;
                if (viewModel == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel.openNaviApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LandroverViewModel viewModel2 = this.mViewModel;
                if (viewModel2 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openVideoMulti(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LandroverViewModel viewModel3 = this.mViewModel;
                if (viewModel3 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openMusicMulti(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LandroverViewModel viewModel4 = this.mViewModel;
                if (viewModel4 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openCar(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LandroverViewModel viewModel5 = this.mViewModel;
                if (viewModel5 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openDvr(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LandroverViewModel viewModel6 = this.mViewModel;
                if (viewModel6 == null) {
                    viewModelJavaLangObjectNull = false;
                }
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openSettings(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

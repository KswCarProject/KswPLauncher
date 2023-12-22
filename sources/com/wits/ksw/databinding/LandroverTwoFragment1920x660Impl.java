package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
public class LandroverTwoFragment1920x660Impl extends LandroverTwoFragment implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback105;
    private final View.OnClickListener mCallback106;
    private final View.OnClickListener mCallback107;
    private final View.OnClickListener mCallback108;
    private final View.OnClickListener mCallback109;
    private final View.OnClickListener mCallback110;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    public LandroverTwoFragment1920x660Impl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private LandroverTwoFragment1920x660Impl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (ImageView) bindings[6], (ImageView) bindings[1], (ImageView) bindings[3], (ImageView) bindings[2], (ImageView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.landroverMainIconApp.setTag(null);
        this.landroverMainIconBrowser.setTag(null);
        this.landroverMainIconBt.setTag(null);
        this.landroverMainIconDashboard.setTag(null);
        this.landroverMainIconFile.setTag(null);
        this.landroverMainIconPhonelink.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        setRootTag(root);
        this.mCallback106 = new OnClickListener(this, 2);
        this.mCallback107 = new OnClickListener(this, 3);
        this.mCallback105 = new OnClickListener(this, 1);
        this.mCallback110 = new OnClickListener(this, 6);
        this.mCallback108 = new OnClickListener(this, 4);
        this.mCallback109 = new OnClickListener(this, 5);
        invalidateAll();
    }

    @Override // android.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (16 == variableId) {
            setViewModel((LandroverViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.LandroverTwoFragment
    public void setViewModel(LandroverViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // android.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // android.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LandroverViewModel viewModel = this.mViewModel;
        View.OnFocusChangeListener viewModelBtViewFocusChangeListener = null;
        if ((dirtyFlags & 3) != 0 && viewModel != null) {
            viewModelBtViewFocusChangeListener = viewModel.btViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.landroverMainIconApp.setOnClickListener(this.mCallback109);
            this.landroverMainIconBrowser.setOnClickListener(this.mCallback110);
            this.landroverMainIconBt.setOnClickListener(this.mCallback105);
            this.landroverMainIconDashboard.setOnClickListener(this.mCallback107);
            this.landroverMainIconFile.setOnClickListener(this.mCallback106);
            this.landroverMainIconPhonelink.setOnClickListener(this.mCallback108);
        }
        if ((3 & dirtyFlags) != 0) {
            this.landroverMainIconBt.setOnFocusChangeListener(viewModelBtViewFocusChangeListener);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean viewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                LandroverViewModel viewModel = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel.openBtApp(callbackArg_0);
                    return;
                }
                return;
            case 2:
                LandroverViewModel viewModel2 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel2 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel2.openFileManager(callbackArg_0);
                    return;
                }
                return;
            case 3:
                LandroverViewModel viewModel3 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel3 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel3.openDashboard(callbackArg_0);
                    return;
                }
                return;
            case 4:
                LandroverViewModel viewModel4 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel4 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel4.openShouJiHuLian(callbackArg_0);
                    return;
                }
                return;
            case 5:
                LandroverViewModel viewModel5 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel5 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel5.openAllApp(callbackArg_0);
                    return;
                }
                return;
            case 6:
                LandroverViewModel viewModel6 = this.mViewModel;
                viewModelJavaLangObjectNull = viewModel6 != null;
                if (viewModelJavaLangObjectNull) {
                    viewModel6.openBrowser(callbackArg_0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

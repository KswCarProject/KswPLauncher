package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class AlsId7SubZlinkViewBindingImpl extends AlsId7SubZlinkViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback307;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView2, 2);
        sparseIntArray.put(R.id.textView3, 3);
    }

    public AlsId7SubZlinkViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private AlsId7SubZlinkViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, bindings[0], bindings[2], bindings[3], bindings[1]);
        this.mDirtyFlags = -1;
        this.dashboardConstraintLayout.setTag((Object) null);
        this.zlinkImageView.setTag((Object) null);
        setRootTag(root);
        this.mCallback307 = new OnClickListener(this, 1);
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
        if (18 != variableId) {
            return false;
        }
        setZlinkWeatherViewModel((AlsID7ViewModel) variable);
        return true;
    }

    public void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel) {
        this.mZlinkWeatherViewModel = ZlinkWeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(18);
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
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        View.OnFocusChangeListener zlinkWeatherViewModelZlinkViewFocusChangeListener = null;
        if (!((dirtyFlags & 3) == 0 || zlinkWeatherViewModel == null)) {
            zlinkWeatherViewModelZlinkViewFocusChangeListener = zlinkWeatherViewModel.zlinkViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.zlinkImageView.setOnClickListener(this.mCallback307);
        }
        if ((3 & dirtyFlags) != 0) {
            this.zlinkImageView.setOnFocusChangeListener(zlinkWeatherViewModelZlinkViewFocusChangeListener);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        if (zlinkWeatherViewModel != null) {
            zlinkWeatherViewModel.openShouJiHuLian(callbackArg_0);
        }
    }
}

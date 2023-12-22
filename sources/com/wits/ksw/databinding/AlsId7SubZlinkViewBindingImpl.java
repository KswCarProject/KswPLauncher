package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.generated.callback.OnClickListener;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public class AlsId7SubZlinkViewBindingImpl extends AlsId7SubZlinkViewBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback441;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0899R.C0901id.textView2, 2);
        sparseIntArray.put(C0899R.C0901id.textView3, 3);
    }

    public AlsId7SubZlinkViewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private AlsId7SubZlinkViewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[0], (TextView) bindings[2], (TextView) bindings[3], (CustomBmwImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.dashboardConstraintLayout.setTag(null);
        this.zlinkImageView.setTag(null);
        setRootTag(root);
        this.mCallback441 = new OnClickListener(this, 1);
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
        if (18 == variableId) {
            setZlinkWeatherViewModel((AlsID7ViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.databinding.AlsId7SubZlinkViewBinding
    public void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel) {
        this.mZlinkWeatherViewModel = ZlinkWeatherViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(18);
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
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        View.OnFocusChangeListener zlinkWeatherViewModelZlinkViewFocusChangeListener = null;
        if ((dirtyFlags & 3) != 0 && zlinkWeatherViewModel != null) {
            zlinkWeatherViewModelZlinkViewFocusChangeListener = zlinkWeatherViewModel.zlinkViewFocusChangeListener;
        }
        if ((2 & dirtyFlags) != 0) {
            this.zlinkImageView.setOnClickListener(this.mCallback441);
        }
        if ((3 & dirtyFlags) != 0) {
            this.zlinkImageView.setOnFocusChangeListener(zlinkWeatherViewModelZlinkViewFocusChangeListener);
        }
    }

    @Override // com.wits.ksw.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        AlsID7ViewModel zlinkWeatherViewModel = this.mZlinkWeatherViewModel;
        boolean zlinkWeatherViewModelJavaLangObjectNull = zlinkWeatherViewModel != null;
        if (zlinkWeatherViewModelJavaLangObjectNull) {
            zlinkWeatherViewModel.openShouJiHuLian(callbackArg_0);
        }
    }
}

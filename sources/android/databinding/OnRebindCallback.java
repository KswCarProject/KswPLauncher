package android.databinding;

import android.databinding.ViewDataBinding;

/* loaded from: classes.dex */
public abstract class OnRebindCallback<T extends ViewDataBinding> {
    public boolean onPreBind(T binding) {
        return true;
    }

    public void onCanceled(T binding) {
    }

    public void onBound(T binding) {
    }
}

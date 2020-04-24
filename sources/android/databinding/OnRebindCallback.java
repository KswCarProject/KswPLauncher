package android.databinding;

import android.databinding.ViewDataBinding;

public abstract class OnRebindCallback<T extends ViewDataBinding> {
    public boolean onPreBind(T t) {
        return true;
    }

    public void onCanceled(T t) {
    }

    public void onBound(T t) {
    }
}

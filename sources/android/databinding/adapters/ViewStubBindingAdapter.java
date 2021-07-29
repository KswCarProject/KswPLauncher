package android.databinding.adapters;

import android.databinding.ViewStubProxy;
import android.view.ViewStub;

public class ViewStubBindingAdapter {
    public static void setOnInflateListener(ViewStubProxy viewStubProxy, ViewStub.OnInflateListener listener) {
        viewStubProxy.setOnInflateListener(listener);
    }
}

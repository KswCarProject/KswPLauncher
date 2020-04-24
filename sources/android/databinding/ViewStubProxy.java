package android.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

public class ViewStubProxy {
    /* access modifiers changed from: private */
    public ViewDataBinding mContainingBinding;
    /* access modifiers changed from: private */
    public ViewStub.OnInflateListener mOnInflateListener;
    private ViewStub.OnInflateListener mProxyListener = new ViewStub.OnInflateListener() {
        public void onInflate(ViewStub stub, View inflated) {
            View unused = ViewStubProxy.this.mRoot = inflated;
            ViewDataBinding unused2 = ViewStubProxy.this.mViewDataBinding = DataBindingUtil.bind(ViewStubProxy.this.mContainingBinding.mBindingComponent, inflated, stub.getLayoutResource());
            ViewStub unused3 = ViewStubProxy.this.mViewStub = null;
            if (ViewStubProxy.this.mOnInflateListener != null) {
                ViewStubProxy.this.mOnInflateListener.onInflate(stub, inflated);
                ViewStub.OnInflateListener unused4 = ViewStubProxy.this.mOnInflateListener = null;
            }
            ViewStubProxy.this.mContainingBinding.invalidateAll();
            ViewStubProxy.this.mContainingBinding.forceExecuteBindings();
        }
    };
    /* access modifiers changed from: private */
    public View mRoot;
    /* access modifiers changed from: private */
    public ViewDataBinding mViewDataBinding;
    /* access modifiers changed from: private */
    public ViewStub mViewStub;

    public ViewStubProxy(@NonNull ViewStub viewStub) {
        this.mViewStub = viewStub;
        this.mViewStub.setOnInflateListener(this.mProxyListener);
    }

    public void setContainingBinding(@NonNull ViewDataBinding containingBinding) {
        this.mContainingBinding = containingBinding;
    }

    public boolean isInflated() {
        return this.mRoot != null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    @Nullable
    public ViewDataBinding getBinding() {
        return this.mViewDataBinding;
    }

    @Nullable
    public ViewStub getViewStub() {
        return this.mViewStub;
    }

    public void setOnInflateListener(@Nullable ViewStub.OnInflateListener listener) {
        if (this.mViewStub != null) {
            this.mOnInflateListener = listener;
        }
    }
}

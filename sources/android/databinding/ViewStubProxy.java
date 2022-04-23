package android.databinding;

import android.view.View;
import android.view.ViewStub;

public class ViewStubProxy {
    /* access modifiers changed from: private */
    public ViewDataBinding mContainingBinding;
    /* access modifiers changed from: private */
    public ViewStub.OnInflateListener mOnInflateListener;
    private ViewStub.OnInflateListener mProxyListener;
    /* access modifiers changed from: private */
    public View mRoot;
    /* access modifiers changed from: private */
    public ViewDataBinding mViewDataBinding;
    /* access modifiers changed from: private */
    public ViewStub mViewStub;

    public ViewStubProxy(ViewStub viewStub) {
        AnonymousClass1 r0 = new ViewStub.OnInflateListener() {
            public void onInflate(ViewStub stub, View inflated) {
                View unused = ViewStubProxy.this.mRoot = inflated;
                ViewStubProxy viewStubProxy = ViewStubProxy.this;
                ViewDataBinding unused2 = viewStubProxy.mViewDataBinding = DataBindingUtil.bind(viewStubProxy.mContainingBinding.mBindingComponent, inflated, stub.getLayoutResource());
                ViewStub unused3 = ViewStubProxy.this.mViewStub = null;
                if (ViewStubProxy.this.mOnInflateListener != null) {
                    ViewStubProxy.this.mOnInflateListener.onInflate(stub, inflated);
                    ViewStub.OnInflateListener unused4 = ViewStubProxy.this.mOnInflateListener = null;
                }
                ViewStubProxy.this.mContainingBinding.invalidateAll();
                ViewStubProxy.this.mContainingBinding.forceExecuteBindings();
            }
        };
        this.mProxyListener = r0;
        this.mViewStub = viewStub;
        viewStub.setOnInflateListener(r0);
    }

    public void setContainingBinding(ViewDataBinding containingBinding) {
        this.mContainingBinding = containingBinding;
    }

    public boolean isInflated() {
        return this.mRoot != null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    public ViewDataBinding getBinding() {
        return this.mViewDataBinding;
    }

    public ViewStub getViewStub() {
        return this.mViewStub;
    }

    public void setOnInflateListener(ViewStub.OnInflateListener listener) {
        if (this.mViewStub != null) {
            this.mOnInflateListener = listener;
        }
    }
}

package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ComputableLiveData<T> {
    /* access modifiers changed from: private */
    public AtomicBoolean mComputing;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    /* access modifiers changed from: private */
    public AtomicBoolean mInvalid;
    final Runnable mInvalidationRunnable;
    /* access modifiers changed from: private */
    public final LiveData<T> mLiveData;
    final Runnable mRefreshRunnable;

    /* access modifiers changed from: protected */
    public abstract T compute();

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(Executor executor) {
        this.mInvalid = new AtomicBoolean(true);
        this.mComputing = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                L_0x0000:
                    r0 = 0
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.mComputing
                    r2 = 0
                    r3 = 1
                    boolean r1 = r1.compareAndSet(r2, r3)
                    if (r1 == 0) goto L_0x0045
                    r1 = 0
                L_0x0010:
                    android.arch.lifecycle.ComputableLiveData r4 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid     // Catch:{ all -> 0x003a }
                    boolean r4 = r4.compareAndSet(r3, r2)     // Catch:{ all -> 0x003a }
                    if (r4 == 0) goto L_0x0025
                    r0 = 1
                    android.arch.lifecycle.ComputableLiveData r4 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.lang.Object r4 = r4.compute()     // Catch:{ all -> 0x003a }
                    r1 = r4
                    goto L_0x0010
                L_0x0025:
                    if (r0 == 0) goto L_0x0030
                    android.arch.lifecycle.ComputableLiveData r3 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    android.arch.lifecycle.LiveData r3 = r3.mLiveData     // Catch:{ all -> 0x003a }
                    r3.postValue(r1)     // Catch:{ all -> 0x003a }
                L_0x0030:
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.mComputing
                    r1.set(r2)
                    goto L_0x0045
                L_0x003a:
                    r1 = move-exception
                    android.arch.lifecycle.ComputableLiveData r3 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                    r3.set(r2)
                    throw r1
                L_0x0045:
                    if (r0 == 0) goto L_0x0053
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.mInvalid
                    boolean r1 = r1.get()
                    if (r1 != 0) goto L_0x0000
                L_0x0053:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.ComputableLiveData.AnonymousClass2.run():void");
            }
        };
        this.mInvalidationRunnable = new Runnable() {
            public void run() {
                boolean isActive = ComputableLiveData.this.mLiveData.hasActiveObservers();
                if (ComputableLiveData.this.mInvalid.compareAndSet(false, true) && isActive) {
                    ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
                }
            }
        };
        this.mExecutor = executor;
        this.mLiveData = new LiveData<T>() {
            /* access modifiers changed from: protected */
            public void onActive() {
                ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
            }
        };
    }

    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}

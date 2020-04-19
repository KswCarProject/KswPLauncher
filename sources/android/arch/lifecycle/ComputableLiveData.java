package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
    /* access modifiers changed from: private */
    public AtomicBoolean mComputing;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    /* access modifiers changed from: private */
    public AtomicBoolean mInvalid;
    @VisibleForTesting
    final Runnable mInvalidationRunnable;
    /* access modifiers changed from: private */
    public final LiveData<T> mLiveData;
    @VisibleForTesting
    final Runnable mRefreshRunnable;

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract T compute();

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.mInvalid = new AtomicBoolean(true);
        this.mComputing = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
            @android.support.annotation.WorkerThread
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                L_0x0000:
                    r0 = 0
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.mComputing
                    r2 = 1
                    r3 = 0
                    boolean r1 = r1.compareAndSet(r3, r2)
                    if (r1 == 0) goto L_0x0045
                    r1 = 0
                L_0x0010:
                    android.arch.lifecycle.ComputableLiveData r4 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid     // Catch:{ all -> 0x003a }
                    boolean r4 = r4.compareAndSet(r2, r3)     // Catch:{ all -> 0x003a }
                    if (r4 == 0) goto L_0x0025
                    r0 = 1
                    android.arch.lifecycle.ComputableLiveData r4 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    java.lang.Object r4 = r4.compute()     // Catch:{ all -> 0x003a }
                    r1 = r4
                    goto L_0x0010
                L_0x0025:
                    if (r0 == 0) goto L_0x0030
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x003a }
                    android.arch.lifecycle.LiveData r2 = r2.mLiveData     // Catch:{ all -> 0x003a }
                    r2.postValue(r1)     // Catch:{ all -> 0x003a }
                L_0x0030:
                    android.arch.lifecycle.ComputableLiveData r1 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r1 = r1.mComputing
                    r1.set(r3)
                    goto L_0x0045
                L_0x003a:
                    r1 = move-exception
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mComputing
                    r2.set(r3)
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
            @MainThread
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

    @NonNull
    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}

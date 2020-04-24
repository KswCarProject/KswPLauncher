package android.support.v4.os;

import android.os.Build;

public final class CancellationSignal {
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    public interface OnCancelListener {
        void onCancel();
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this) {
            z = this.mIsCanceled;
        }
        return z;
    }

    public void throwIfCanceled() {
        if (isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.onCancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
        if (r0 == null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0022, code lost:
        if (android.os.Build.VERSION.SDK_INT < 16) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
        ((android.os.CancellationSignal) r0).cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002b, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r6.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0032, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0036, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r6.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x003c, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            boolean r1 = r6.mIsCanceled     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0008
            monitor-exit(r6)     // Catch:{ all -> 0x0048 }
            return
        L_0x0008:
            r1 = 1
            r6.mIsCanceled = r1     // Catch:{ all -> 0x0048 }
            r6.mCancelInProgress = r1     // Catch:{ all -> 0x0048 }
            android.support.v4.os.CancellationSignal$OnCancelListener r1 = r6.mOnCancelListener     // Catch:{ all -> 0x0048 }
            java.lang.Object r2 = r6.mCancellationSignalObj     // Catch:{ all -> 0x0042 }
            r0 = r2
            monitor-exit(r6)     // Catch:{ all -> 0x0042 }
            r2 = 0
            if (r1 == 0) goto L_0x001c
            r1.onCancel()     // Catch:{ all -> 0x001a }
            goto L_0x001c
        L_0x001a:
            r3 = move-exception
            goto L_0x002b
        L_0x001c:
            if (r0 == 0) goto L_0x0036
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x001a }
            r4 = 16
            if (r3 < r4) goto L_0x0036
            r3 = r0
            android.os.CancellationSignal r3 = (android.os.CancellationSignal) r3     // Catch:{ all -> 0x001a }
            r3.cancel()     // Catch:{ all -> 0x001a }
            goto L_0x0036
        L_0x002b:
            monitor-enter(r6)
            r6.mCancelInProgress = r2     // Catch:{ all -> 0x0033 }
            r6.notifyAll()     // Catch:{ all -> 0x0033 }
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            throw r3
        L_0x0033:
            r2 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            throw r2
        L_0x0036:
            monitor-enter(r6)
            r6.mCancelInProgress = r2     // Catch:{ all -> 0x003f }
            r6.notifyAll()     // Catch:{ all -> 0x003f }
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r2 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            throw r2
        L_0x0042:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r1
            r1 = r5
            goto L_0x004a
        L_0x0048:
            r1 = move-exception
            r2 = r0
        L_0x004a:
            monitor-exit(r6)     // Catch:{ all -> 0x004c }
            throw r1
        L_0x004c:
            r1 = move-exception
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.CancellationSignal.cancel():void");
    }

    public void setOnCancelListener(OnCancelListener listener) {
        synchronized (this) {
            waitForCancelFinishedLocked();
            if (this.mOnCancelListener != listener) {
                this.mOnCancelListener = listener;
                if (this.mIsCanceled) {
                    if (listener != null) {
                        listener.onCancel();
                    }
                }
            }
        }
    }

    public Object getCancellationSignalObject() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = new android.os.CancellationSignal();
                if (this.mIsCanceled) {
                    ((android.os.CancellationSignal) this.mCancellationSignalObj).cancel();
                }
            }
            obj = this.mCancellationSignalObj;
        }
        return obj;
    }

    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }
}

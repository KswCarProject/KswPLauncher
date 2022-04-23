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

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0014, code lost:
        if (r1 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.onCancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001c, code lost:
        if (r2 == null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0022, code lost:
        if (android.os.Build.VERSION.SDK_INT < 16) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0024, code lost:
        ((android.os.CancellationSignal) r2).cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r6.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0032, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0036, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r6.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x003c, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            boolean r1 = r6.mIsCanceled     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0008
            monitor-exit(r6)     // Catch:{ all -> 0x004d }
            return
        L_0x0008:
            r1 = 1
            r6.mIsCanceled = r1     // Catch:{ all -> 0x004d }
            r6.mCancelInProgress = r1     // Catch:{ all -> 0x004d }
            android.support.v4.os.CancellationSignal$OnCancelListener r1 = r6.mOnCancelListener     // Catch:{ all -> 0x004d }
            java.lang.Object r0 = r6.mCancellationSignalObj     // Catch:{ all -> 0x0047 }
            r2 = r0
            monitor-exit(r6)     // Catch:{ all -> 0x0042 }
            r0 = 0
            if (r1 == 0) goto L_0x001c
            r1.onCancel()     // Catch:{ all -> 0x001a }
            goto L_0x001c
        L_0x001a:
            r3 = move-exception
            goto L_0x002b
        L_0x001c:
            if (r2 == 0) goto L_0x0036
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x001a }
            r4 = 16
            if (r3 < r4) goto L_0x0036
            r3 = r2
            android.os.CancellationSignal r3 = (android.os.CancellationSignal) r3     // Catch:{ all -> 0x001a }
            r3.cancel()     // Catch:{ all -> 0x001a }
            goto L_0x0036
        L_0x002b:
            monitor-enter(r6)
            r6.mCancelInProgress = r0     // Catch:{ all -> 0x0033 }
            r6.notifyAll()     // Catch:{ all -> 0x0033 }
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            throw r3
        L_0x0033:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0033 }
            throw r0
        L_0x0036:
            monitor-enter(r6)
            r6.mCancelInProgress = r0     // Catch:{ all -> 0x003f }
            r6.notifyAll()     // Catch:{ all -> 0x003f }
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            throw r0
        L_0x0042:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x004f
        L_0x0047:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r1
            r1 = r5
            goto L_0x004f
        L_0x004d:
            r1 = move-exception
            r2 = r0
        L_0x004f:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            throw r1
        L_0x0051:
            r1 = move-exception
            goto L_0x004f
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
                android.os.CancellationSignal cancellationSignal = new android.os.CancellationSignal();
                this.mCancellationSignalObj = cancellationSignal;
                if (this.mIsCanceled) {
                    cancellationSignal.cancel();
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

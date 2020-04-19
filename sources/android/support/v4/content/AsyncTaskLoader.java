package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.os.OperationCanceledException;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader<D> extends Loader<D> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile AsyncTaskLoader<D>.LoadTask mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile AsyncTaskLoader<D>.LoadTask mTask;
    long mUpdateThrottle;

    @Nullable
    public abstract D loadInBackground();

    final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable {
        private final CountDownLatch mDone = new CountDownLatch(1);
        boolean waiting;

        LoadTask() {
        }

        /* access modifiers changed from: protected */
        public D doInBackground(Void... params) {
            try {
                return AsyncTaskLoader.this.onLoadInBackground();
            } catch (OperationCanceledException ex) {
                if (isCancelled()) {
                    return null;
                }
                throw ex;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(D data) {
            try {
                AsyncTaskLoader.this.dispatchOnLoadComplete(this, data);
            } finally {
                this.mDone.countDown();
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(D data) {
            try {
                AsyncTaskLoader.this.dispatchOnCancelled(this, data);
            } finally {
                this.mDone.countDown();
            }
        }

        public void run() {
            this.waiting = false;
            AsyncTaskLoader.this.executePendingTask();
        }

        public void waitForLoader() {
            try {
                this.mDone.await();
            } catch (InterruptedException e) {
            }
        }
    }

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000;
        this.mExecutor = executor;
    }

    public void setUpdateThrottle(long delayMS) {
        this.mUpdateThrottle = delayMS;
        if (delayMS != 0) {
            this.mHandler = new Handler();
        }
    }

    /* access modifiers changed from: protected */
    public void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new LoadTask();
        executePendingTask();
    }

    /* access modifiers changed from: protected */
    public boolean onCancelLoad() {
        if (this.mTask == null) {
            return false;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return false;
        } else if (this.mTask.waiting) {
            this.mTask.waiting = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
        } else {
            boolean cancelled = this.mTask.cancel(false);
            if (cancelled) {
                this.mCancellingTask = this.mTask;
                cancelLoadInBackground();
            }
            this.mTask = null;
            return cancelled;
        }
    }

    public void onCanceled(@Nullable D d) {
    }

    /* access modifiers changed from: package-private */
    public void executePendingTask() {
        if (this.mCancellingTask == null && this.mTask != null) {
            if (this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            if (this.mUpdateThrottle <= 0 || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
                this.mTask.executeOnExecutor(this.mExecutor, (Params[]) null);
                return;
            }
            this.mTask.waiting = true;
            this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnCancelled(AsyncTaskLoader<D>.LoadTask task, D data) {
        onCanceled(data);
        if (this.mCancellingTask == task) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnLoadComplete(AsyncTaskLoader<D>.LoadTask task, D data) {
        if (this.mTask != task) {
            dispatchOnCancelled(task, data);
        } else if (isAbandoned()) {
            onCanceled(data);
        } else {
            commitContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mTask = null;
            deliverResult(data);
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public D onLoadInBackground() {
        return loadInBackground();
    }

    public void cancelLoadInBackground() {
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        AsyncTaskLoader<D>.LoadTask task = this.mTask;
        if (task != null) {
            task.waitForLoader();
        }
    }

    @Deprecated
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        if (this.mTask != null) {
            writer.print(prefix);
            writer.print("mTask=");
            writer.print(this.mTask);
            writer.print(" waiting=");
            writer.println(this.mTask.waiting);
        }
        if (this.mCancellingTask != null) {
            writer.print(prefix);
            writer.print("mCancellingTask=");
            writer.print(this.mCancellingTask);
            writer.print(" waiting=");
            writer.println(this.mCancellingTask.waiting);
        }
        if (this.mUpdateThrottle != 0) {
            writer.print(prefix);
            writer.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.mUpdateThrottle, writer);
            writer.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), writer);
            writer.println();
        }
    }
}

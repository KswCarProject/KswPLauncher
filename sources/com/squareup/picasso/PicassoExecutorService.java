package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Utils;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    PicassoExecutorService() {
        super(3, 3, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new Utils.PicassoThreadFactory());
    }

    /* access modifiers changed from: package-private */
    public void adjustThreadCount(NetworkInfo info) {
        if (info == null || !info.isConnectedOrConnecting()) {
            setThreadCount(3);
            return;
        }
        switch (info.getType()) {
            case 0:
                switch (info.getSubtype()) {
                    case 1:
                    case 2:
                        setThreadCount(1);
                        return;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 12:
                        setThreadCount(2);
                        return;
                    case 13:
                    case 14:
                    case 15:
                        setThreadCount(3);
                        return;
                    default:
                        setThreadCount(3);
                        return;
                }
            case 1:
            case 6:
            case 9:
                setThreadCount(4);
                return;
            default:
                setThreadCount(3);
                return;
        }
    }

    private void setThreadCount(int threadCount) {
        setCorePoolSize(threadCount);
        setMaximumPoolSize(threadCount);
    }

    public Future<?> submit(Runnable task) {
        PicassoFutureTask ftask = new PicassoFutureTask((BitmapHunter) task);
        execute(ftask);
        return ftask;
    }

    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        public PicassoFutureTask(BitmapHunter hunter2) {
            super(hunter2, (Object) null);
            this.hunter = hunter2;
        }

        public int compareTo(PicassoFutureTask other) {
            int i;
            int i2;
            Picasso.Priority p1 = this.hunter.getPriority();
            Picasso.Priority p2 = other.hunter.getPriority();
            if (p1 == p2) {
                i2 = this.hunter.sequence;
                i = other.hunter.sequence;
            } else {
                i2 = p2.ordinal();
                i = p1.ordinal();
            }
            return i2 - i;
        }
    }
}

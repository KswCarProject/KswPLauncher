package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;

/* loaded from: classes.dex */
public interface SchedulerMultiWorkerSupport {

    /* loaded from: classes.dex */
    public interface WorkerCallback {
        void onWorker(int i, Scheduler.Worker worker);
    }

    void createWorkers(int i, WorkerCallback workerCallback);
}

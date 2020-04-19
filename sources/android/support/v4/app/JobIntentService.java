package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    static final HashMap<ComponentName, WorkEnqueuer> sClassWorkEnqueuer = new HashMap<>();
    static final Object sLock = new Object();
    final ArrayList<CompatWorkItem> mCompatQueue;
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed = false;
    boolean mInterruptIfStopped = false;
    CompatJobEngine mJobImpl;
    boolean mStopped = false;

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    /* access modifiers changed from: protected */
    public abstract void onHandleWork(@NonNull Intent intent);

    static abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        /* access modifiers changed from: package-private */
        public abstract void enqueueWork(Intent intent);

        WorkEnqueuer(Context context, ComponentName cn) {
            this.mComponentName = cn;
        }

        /* access modifiers changed from: package-private */
        public void ensureJobId(int jobId) {
            if (!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = jobId;
            } else if (this.mJobId != jobId) {
                throw new IllegalArgumentException("Given job ID " + jobId + " is different than previous " + this.mJobId);
            }
        }

        public void serviceStartReceived() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceProcessingFinished() {
        }
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final PowerManager.WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context context, ComponentName cn) {
            super(context, cn);
            this.mContext = context.getApplicationContext();
            PowerManager pm = (PowerManager) context.getSystemService("power");
            this.mLaunchWakeLock = pm.newWakeLock(1, cn.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = pm.newWakeLock(1, cn.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        /* access modifiers changed from: package-private */
        public void enqueueWork(Intent work) {
            Intent intent = new Intent(work);
            intent.setComponent(this.mComponentName);
            if (this.mContext.startService(intent) != null) {
                synchronized (this) {
                    if (!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if (!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000);
                        }
                    }
                }
            }
        }

        public void serviceStartReceived() {
            synchronized (this) {
                this.mLaunchingService = false;
            }
        }

        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(600000);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.mServiceProcessing) {
                    if (this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000);
                    }
                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }
            }
        }
    }

    @RequiresApi(26)
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock = new Object();
        JobParameters mParams;
        final JobIntentService mService;

        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;

            WrapperWorkItem(JobWorkItem jobWork) {
                this.mJobWork = jobWork;
            }

            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }

            public void complete() {
                synchronized (JobServiceEngineImpl.this.mLock) {
                    if (JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
                    }
                }
            }
        }

        JobServiceEngineImpl(JobIntentService service) {
            super(service);
            this.mService = service;
        }

        public IBinder compatGetBinder() {
            return getBinder();
        }

        public boolean onStartJob(JobParameters params) {
            this.mParams = params;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        public boolean onStopJob(JobParameters params) {
            boolean result = this.mService.doStopCurrentWork();
            synchronized (this.mLock) {
                this.mParams = null;
            }
            return result;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
            if (r2 == null) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r2.getIntent().setExtrasClassLoader(r4.mService.getClassLoader());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
            return new android.support.v4.app.JobIntentService.JobServiceEngineImpl.WrapperWorkItem(r4, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.v4.app.JobIntentService.GenericWorkItem dequeueWork() {
            /*
                r4 = this;
                java.lang.Object r0 = r4.mLock
                monitor-enter(r0)
                r1 = 0
                android.app.job.JobParameters r2 = r4.mParams     // Catch:{ all -> 0x002c }
                if (r2 != 0) goto L_0x000a
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return r1
            L_0x000a:
                android.app.job.JobParameters r2 = r4.mParams     // Catch:{ all -> 0x002c }
                android.app.job.JobWorkItem r2 = r2.dequeueWork()     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                if (r2 == 0) goto L_0x0026
                android.content.Intent r0 = r2.getIntent()
                android.support.v4.app.JobIntentService r1 = r4.mService
                java.lang.ClassLoader r1 = r1.getClassLoader()
                r0.setExtrasClassLoader(r1)
                android.support.v4.app.JobIntentService$JobServiceEngineImpl$WrapperWorkItem r0 = new android.support.v4.app.JobIntentService$JobServiceEngineImpl$WrapperWorkItem
                r0.<init>(r2)
                return r0
            L_0x0026:
                return r1
            L_0x0027:
                r1 = move-exception
                r3 = r2
                r2 = r1
                r1 = r3
                goto L_0x002d
            L_0x002c:
                r2 = move-exception
            L_0x002d:
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.JobIntentService.JobServiceEngineImpl.dequeueWork():android.support.v4.app.JobIntentService$GenericWorkItem");
        }
    }

    @RequiresApi(26)
    static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName cn, int jobId) {
            super(context, cn);
            ensureJobId(jobId);
            this.mJobInfo = new JobInfo.Builder(jobId, this.mComponentName).setOverrideDeadline(0).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        /* access modifiers changed from: package-private */
        public void enqueueWork(Intent work) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(work));
        }
    }

    final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;

        CompatWorkItem(Intent intent, int startId) {
            this.mIntent = intent;
            this.mStartId = startId;
        }

        public Intent getIntent() {
            return this.mIntent;
        }

        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }
    }

    final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            while (true) {
                GenericWorkItem dequeueWork = JobIntentService.this.dequeueWork();
                GenericWorkItem work = dequeueWork;
                if (dequeueWork == null) {
                    return null;
                }
                JobIntentService.this.onHandleWork(work.getIntent());
                work.complete();
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(Void aVoid) {
            JobIntentService.this.processorFinished();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void aVoid) {
            JobIntentService.this.processorFinished();
        }
    }

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mCompatQueue = null;
        } else {
            this.mCompatQueue = new ArrayList<>();
        }
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mJobImpl = new JobServiceEngineImpl(this);
            this.mCompatWorkEnqueuer = null;
            return;
        }
        this.mJobImpl = null;
        this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0);
    }

    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (this.mCompatQueue == null) {
            return 2;
        }
        this.mCompatWorkEnqueuer.serviceStartReceived();
        synchronized (this.mCompatQueue) {
            this.mCompatQueue.add(new CompatWorkItem(intent != null ? intent : new Intent(), startId));
            ensureProcessorRunningLocked(true);
        }
        return 3;
    }

    public IBinder onBind(@NonNull Intent intent) {
        if (this.mJobImpl != null) {
            return this.mJobImpl.compatGetBinder();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCompatQueue != null) {
            synchronized (this.mCompatQueue) {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.serviceProcessingFinished();
            }
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class cls, int jobId, @NonNull Intent work) {
        enqueueWork(context, new ComponentName(context, cls), jobId, work);
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName component, int jobId, @NonNull Intent work) {
        if (work != null) {
            synchronized (sLock) {
                WorkEnqueuer we = getWorkEnqueuer(context, component, true, jobId);
                we.ensureJobId(jobId);
                we.enqueueWork(work);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName cn, boolean hasJobId, int jobId) {
        WorkEnqueuer we = sClassWorkEnqueuer.get(cn);
        if (we == null) {
            if (Build.VERSION.SDK_INT < 26) {
                we = new CompatWorkEnqueuer(context, cn);
            } else if (hasJobId) {
                we = new JobWorkEnqueuer(context, cn, jobId);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            sClassWorkEnqueuer.put(cn, we);
        }
        return we;
    }

    public void setInterruptIfStopped(boolean interruptIfStopped) {
        this.mInterruptIfStopped = interruptIfStopped;
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean doStopCurrentWork() {
        if (this.mCurProcessor != null) {
            this.mCurProcessor.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return onStopCurrentWork();
    }

    /* access modifiers changed from: package-private */
    public void ensureProcessorRunningLocked(boolean reportStarted) {
        if (this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor();
            if (this.mCompatWorkEnqueuer != null && reportStarted) {
                this.mCompatWorkEnqueuer.serviceProcessingStarted();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void processorFinished() {
        if (this.mCompatQueue != null) {
            synchronized (this.mCompatQueue) {
                this.mCurProcessor = null;
                if (this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
                    ensureProcessorRunningLocked(false);
                } else if (!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public GenericWorkItem dequeueWork() {
        if (this.mJobImpl != null) {
            return this.mJobImpl.dequeueWork();
        }
        synchronized (this.mCompatQueue) {
            if (this.mCompatQueue.size() <= 0) {
                return null;
            }
            GenericWorkItem remove = this.mCompatQueue.remove(0);
            return remove;
        }
    }
}

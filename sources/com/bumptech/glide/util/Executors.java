package com.bumptech.glide.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class Executors {
    private static final Executor DIRECT_EXECUTOR = new Executor() {
        public void execute(Runnable command) {
            command.run();
        }
    };
    private static final Executor MAIN_THREAD_EXECUTOR = new Executor() {
        private final Handler handler = new Handler(Looper.getMainLooper());

        public void execute(Runnable command) {
            this.handler.post(command);
        }
    };

    private Executors() {
    }

    public static Executor mainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    public static Executor directExecutor() {
        return DIRECT_EXECUTOR;
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdownNow();
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                    throw new RuntimeException("Failed to shutdown");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }
}

package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class ExceptionCatchingInputStream extends InputStream {
    private static final Queue<ExceptionCatchingInputStream> QUEUE = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    @NonNull
    public static ExceptionCatchingInputStream obtain(@NonNull InputStream toWrap) {
        ExceptionCatchingInputStream result;
        synchronized (QUEUE) {
            result = QUEUE.poll();
        }
        if (result == null) {
            result = new ExceptionCatchingInputStream();
        }
        result.setInputStream(toWrap);
        return result;
    }

    static void clearQueue() {
        while (!QUEUE.isEmpty()) {
            QUEUE.remove();
        }
    }

    ExceptionCatchingInputStream() {
    }

    /* access modifiers changed from: package-private */
    public void setInputStream(@NonNull InputStream toWrap) {
        this.wrapped = toWrap;
    }

    public int available() throws IOException {
        return this.wrapped.available();
    }

    public void close() throws IOException {
        this.wrapped.close();
    }

    public void mark(int readLimit) {
        this.wrapped.mark(readLimit);
    }

    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    public int read(byte[] buffer) {
        try {
            return this.wrapped.read(buffer);
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) {
        try {
            return this.wrapped.read(buffer, byteOffset, byteCount);
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    public synchronized void reset() throws IOException {
        this.wrapped.reset();
    }

    public long skip(long byteCount) {
        try {
            return this.wrapped.skip(byteCount);
        } catch (IOException e) {
            this.exception = e;
            return 0;
        }
    }

    public int read() {
        try {
            return this.wrapped.read();
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    @Nullable
    public IOException getException() {
        return this.exception;
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        synchronized (QUEUE) {
            QUEUE.offer(this);
        }
    }
}

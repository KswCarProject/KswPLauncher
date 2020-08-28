package com.bumptech.glide.load.resource.bitmap;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public RecyclableBufferedInputStream(@NonNull InputStream in, @NonNull ArrayPool byteArrayPool2) {
        this(in, byteArrayPool2, 65536);
    }

    @VisibleForTesting
    RecyclableBufferedInputStream(@NonNull InputStream in, @NonNull ArrayPool byteArrayPool2, int bufferSize) {
        super(in);
        this.markpos = -1;
        this.byteArrayPool = byteArrayPool2;
        this.buf = (byte[]) byteArrayPool2.get(bufferSize, byte[].class);
    }

    public synchronized int available() throws IOException {
        InputStream localIn;
        localIn = this.in;
        if (this.buf == null || localIn == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + localIn.available();
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    public synchronized void release() {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
    }

    public void close() throws IOException {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
        InputStream localIn = this.in;
        this.in = null;
        if (localIn != null) {
            localIn.close();
        }
    }

    private int fillbuf(InputStream localIn, byte[] localBuf) throws IOException {
        if (this.markpos == -1 || this.pos - this.markpos >= this.marklimit) {
            int bytesread = localIn.read(localBuf);
            if (bytesread > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = bytesread;
            }
            return bytesread;
        }
        if (this.markpos == 0 && this.marklimit > localBuf.length && this.count == localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > this.marklimit) {
                newLength = this.marklimit;
            }
            byte[] newbuf = (byte[]) this.byteArrayPool.get(newLength, byte[].class);
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            byte[] oldbuf = localBuf;
            this.buf = newbuf;
            localBuf = newbuf;
            this.byteArrayPool.put(oldbuf);
        } else if (this.markpos > 0) {
            System.arraycopy(localBuf, this.markpos, localBuf, 0, localBuf.length - this.markpos);
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int bytesread2 = localIn.read(localBuf, this.pos, localBuf.length - this.pos);
        this.count = bytesread2 <= 0 ? this.pos : this.pos + bytesread2;
        return bytesread2;
    }

    public synchronized void mark(int readlimit) {
        this.marklimit = Math.max(this.marklimit, readlimit);
        this.markpos = this.pos;
    }

    public boolean markSupported() {
        return true;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0018=Splitter:B:11:0x0018, B:27:0x003c=Splitter:B:27:0x003c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.buf     // Catch:{ all -> 0x0041 }
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x003c
            if (r1 == 0) goto L_0x003c
            int r2 = r5.pos     // Catch:{ all -> 0x0041 }
            int r3 = r5.count     // Catch:{ all -> 0x0041 }
            r4 = -1
            if (r2 < r3) goto L_0x0018
            int r2 = r5.fillbuf(r1, r0)     // Catch:{ all -> 0x0041 }
            if (r2 != r4) goto L_0x0018
            monitor-exit(r5)
            return r4
        L_0x0018:
            byte[] r2 = r5.buf     // Catch:{ all -> 0x0041 }
            if (r0 == r2) goto L_0x0027
            byte[] r2 = r5.buf     // Catch:{ all -> 0x0041 }
            r0 = r2
            if (r0 == 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x0041 }
            throw r2     // Catch:{ all -> 0x0041 }
        L_0x0027:
            int r2 = r5.count     // Catch:{ all -> 0x0041 }
            int r3 = r5.pos     // Catch:{ all -> 0x0041 }
            int r2 = r2 - r3
            if (r2 <= 0) goto L_0x003a
            int r2 = r5.pos     // Catch:{ all -> 0x0041 }
            int r3 = r2 + 1
            r5.pos = r3     // Catch:{ all -> 0x0041 }
            byte r2 = r0[r2]     // Catch:{ all -> 0x0041 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            monitor-exit(r5)
            return r2
        L_0x003a:
            monitor-exit(r5)
            return r4
        L_0x003c:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x0041 }
            throw r2     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0050, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005d, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(@android.support.annotation.NonNull byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.buf     // Catch:{ all -> 0x00a0 }
            if (r0 == 0) goto L_0x009b
            if (r8 != 0) goto L_0x000a
            r1 = 0
            monitor-exit(r5)
            return r1
        L_0x000a:
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x00a0 }
            if (r1 == 0) goto L_0x0096
            int r2 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r3 = r5.count     // Catch:{ all -> 0x00a0 }
            if (r2 >= r3) goto L_0x003b
            int r2 = r5.count     // Catch:{ all -> 0x00a0 }
            int r3 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r2 = r2 - r3
            if (r2 < r8) goto L_0x001d
            r2 = r8
            goto L_0x0022
        L_0x001d:
            int r2 = r5.count     // Catch:{ all -> 0x00a0 }
            int r3 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r2 = r2 - r3
        L_0x0022:
            int r3 = r5.pos     // Catch:{ all -> 0x00a0 }
            java.lang.System.arraycopy(r0, r3, r6, r7, r2)     // Catch:{ all -> 0x00a0 }
            int r3 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r3 = r3 + r2
            r5.pos = r3     // Catch:{ all -> 0x00a0 }
            if (r2 == r8) goto L_0x0039
            int r3 = r1.available()     // Catch:{ all -> 0x00a0 }
            if (r3 != 0) goto L_0x0035
            goto L_0x0039
        L_0x0035:
            int r7 = r7 + r2
            int r2 = r8 - r2
            goto L_0x003c
        L_0x0039:
            monitor-exit(r5)
            return r2
        L_0x003b:
            r2 = r8
        L_0x003c:
            int r3 = r5.markpos     // Catch:{ all -> 0x00a0 }
            r4 = -1
            if (r3 != r4) goto L_0x0051
            int r3 = r0.length     // Catch:{ all -> 0x00a0 }
            if (r2 < r3) goto L_0x0051
            int r3 = r1.read(r6, r7, r2)     // Catch:{ all -> 0x00a0 }
            if (r3 != r4) goto L_0x0085
            if (r2 != r8) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            int r4 = r8 - r2
        L_0x004f:
            monitor-exit(r5)
            return r4
        L_0x0051:
            int r3 = r5.fillbuf(r1, r0)     // Catch:{ all -> 0x00a0 }
            if (r3 != r4) goto L_0x005e
            if (r2 != r8) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            int r4 = r8 - r2
        L_0x005c:
            monitor-exit(r5)
            return r4
        L_0x005e:
            byte[] r3 = r5.buf     // Catch:{ all -> 0x00a0 }
            if (r0 == r3) goto L_0x006d
            byte[] r3 = r5.buf     // Catch:{ all -> 0x00a0 }
            r0 = r3
            if (r0 == 0) goto L_0x0068
            goto L_0x006d
        L_0x0068:
            java.io.IOException r3 = streamClosed()     // Catch:{ all -> 0x00a0 }
            throw r3     // Catch:{ all -> 0x00a0 }
        L_0x006d:
            int r3 = r5.count     // Catch:{ all -> 0x00a0 }
            int r4 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r3 = r3 - r4
            if (r3 < r2) goto L_0x0076
            r3 = r2
            goto L_0x007b
        L_0x0076:
            int r3 = r5.count     // Catch:{ all -> 0x00a0 }
            int r4 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r3 = r3 - r4
        L_0x007b:
            int r4 = r5.pos     // Catch:{ all -> 0x00a0 }
            java.lang.System.arraycopy(r0, r4, r6, r7, r3)     // Catch:{ all -> 0x00a0 }
            int r4 = r5.pos     // Catch:{ all -> 0x00a0 }
            int r4 = r4 + r3
            r5.pos = r4     // Catch:{ all -> 0x00a0 }
        L_0x0085:
            int r2 = r2 - r3
            if (r2 != 0) goto L_0x008a
            monitor-exit(r5)
            return r8
        L_0x008a:
            int r4 = r1.available()     // Catch:{ all -> 0x00a0 }
            if (r4 != 0) goto L_0x0094
            int r4 = r8 - r2
            monitor-exit(r5)
            return r4
        L_0x0094:
            int r7 = r7 + r3
            goto L_0x003c
        L_0x0096:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x00a0 }
            throw r2     // Catch:{ all -> 0x00a0 }
        L_0x009b:
            java.io.IOException r1 = streamClosed()     // Catch:{ all -> 0x00a0 }
            throw r1     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }

    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        } else if (-1 != this.markpos) {
            this.pos = this.markpos;
        } else {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
        }
    }

    public synchronized long skip(long byteCount) throws IOException {
        if (byteCount < 1) {
            return 0;
        }
        byte[] localBuf = this.buf;
        if (localBuf != null) {
            InputStream localIn = this.in;
            if (localIn == null) {
                throw streamClosed();
            } else if (((long) (this.count - this.pos)) >= byteCount) {
                this.pos = (int) (((long) this.pos) + byteCount);
                return byteCount;
            } else {
                long read = ((long) this.count) - ((long) this.pos);
                this.pos = this.count;
                if (this.markpos == -1 || byteCount > ((long) this.marklimit)) {
                    return localIn.skip(byteCount - read) + read;
                } else if (fillbuf(localIn, localBuf) == -1) {
                    return read;
                } else {
                    if (((long) (this.count - this.pos)) >= byteCount - read) {
                        this.pos = (int) ((((long) this.pos) + byteCount) - read);
                        return byteCount;
                    }
                    long read2 = (((long) this.count) + read) - ((long) this.pos);
                    this.pos = this.count;
                    return read2;
                }
            }
        } else {
            throw streamClosed();
        }
    }

    static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        InvalidMarkException(String detailMessage) {
            super(detailMessage);
        }
    }
}

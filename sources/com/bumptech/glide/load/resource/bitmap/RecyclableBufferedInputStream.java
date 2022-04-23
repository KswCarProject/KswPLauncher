package com.bumptech.glide.load.resource.bitmap;

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

    public RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool2) {
        this(in, byteArrayPool2, 65536);
    }

    RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool2, int bufferSize) {
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
        int i;
        int i2 = this.markpos;
        if (i2 == -1 || this.pos - i2 >= (i = this.marklimit)) {
            int result = localIn.read(localBuf);
            if (result > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = result;
            }
            return result;
        }
        if (i2 == 0 && i > localBuf.length && this.count == localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > i) {
                newLength = this.marklimit;
            }
            byte[] newbuf = (byte[]) this.byteArrayPool.get(newLength, byte[].class);
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            byte[] oldbuf = localBuf;
            this.buf = newbuf;
            localBuf = newbuf;
            this.byteArrayPool.put(oldbuf);
        } else if (i2 > 0) {
            System.arraycopy(localBuf, i2, localBuf, 0, localBuf.length - i2);
        }
        int i3 = this.pos - this.markpos;
        this.pos = i3;
        this.markpos = 0;
        this.count = 0;
        int bytesread = localIn.read(localBuf, i3, localBuf.length - i3);
        int i4 = this.pos;
        if (bytesread > 0) {
            i4 += bytesread;
        }
        this.count = i4;
        return bytesread;
    }

    public synchronized void mark(int readlimit) {
        this.marklimit = Math.max(this.marklimit, readlimit);
        this.markpos = this.pos;
    }

    public boolean markSupported() {
        return true;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0018=Splitter:B:11:0x0018, B:27:0x003a=Splitter:B:27:0x003a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.buf     // Catch:{ all -> 0x003f }
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x003a
            if (r1 == 0) goto L_0x003a
            int r2 = r5.pos     // Catch:{ all -> 0x003f }
            int r3 = r5.count     // Catch:{ all -> 0x003f }
            r4 = -1
            if (r2 < r3) goto L_0x0018
            int r2 = r5.fillbuf(r1, r0)     // Catch:{ all -> 0x003f }
            if (r2 != r4) goto L_0x0018
            monitor-exit(r5)
            return r4
        L_0x0018:
            byte[] r2 = r5.buf     // Catch:{ all -> 0x003f }
            if (r0 == r2) goto L_0x0027
            byte[] r2 = r5.buf     // Catch:{ all -> 0x003f }
            r0 = r2
            if (r0 == 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x003f }
            throw r2     // Catch:{ all -> 0x003f }
        L_0x0027:
            int r2 = r5.count     // Catch:{ all -> 0x003f }
            int r3 = r5.pos     // Catch:{ all -> 0x003f }
            int r2 = r2 - r3
            if (r2 <= 0) goto L_0x0038
            int r2 = r3 + 1
            r5.pos = r2     // Catch:{ all -> 0x003f }
            byte r2 = r0[r3]     // Catch:{ all -> 0x003f }
            r2 = r2 & 255(0xff, float:3.57E-43)
            monitor-exit(r5)
            return r2
        L_0x0038:
            monitor-exit(r5)
            return r4
        L_0x003a:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x003f }
            throw r2     // Catch:{ all -> 0x003f }
        L_0x003f:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0031, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0047, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0054, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            byte[] r0 = r6.buf     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x008d
            if (r9 != 0) goto L_0x000a
            r1 = 0
            monitor-exit(r6)
            return r1
        L_0x000a:
            java.io.InputStream r1 = r6.in     // Catch:{ all -> 0x0092 }
            if (r1 == 0) goto L_0x0088
            int r2 = r6.pos     // Catch:{ all -> 0x0092 }
            int r3 = r6.count     // Catch:{ all -> 0x0092 }
            if (r2 >= r3) goto L_0x0032
            int r4 = r3 - r2
            if (r4 < r9) goto L_0x001a
            r3 = r9
            goto L_0x001b
        L_0x001a:
            int r3 = r3 - r2
        L_0x001b:
            java.lang.System.arraycopy(r0, r2, r7, r8, r3)     // Catch:{ all -> 0x0092 }
            int r2 = r6.pos     // Catch:{ all -> 0x0092 }
            int r2 = r2 + r3
            r6.pos = r2     // Catch:{ all -> 0x0092 }
            if (r3 == r9) goto L_0x0030
            int r2 = r1.available()     // Catch:{ all -> 0x0092 }
            if (r2 != 0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            int r8 = r8 + r3
            int r2 = r9 - r3
            goto L_0x0033
        L_0x0030:
            monitor-exit(r6)
            return r3
        L_0x0032:
            r2 = r9
        L_0x0033:
            int r3 = r6.markpos     // Catch:{ all -> 0x0092 }
            r4 = -1
            if (r3 != r4) goto L_0x0048
            int r3 = r0.length     // Catch:{ all -> 0x0092 }
            if (r2 < r3) goto L_0x0048
            int r3 = r1.read(r7, r8, r2)     // Catch:{ all -> 0x0092 }
            if (r3 != r4) goto L_0x0077
            if (r2 != r9) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            int r4 = r9 - r2
        L_0x0046:
            monitor-exit(r6)
            return r4
        L_0x0048:
            int r3 = r6.fillbuf(r1, r0)     // Catch:{ all -> 0x0092 }
            if (r3 != r4) goto L_0x0055
            if (r2 != r9) goto L_0x0051
            goto L_0x0053
        L_0x0051:
            int r4 = r9 - r2
        L_0x0053:
            monitor-exit(r6)
            return r4
        L_0x0055:
            byte[] r3 = r6.buf     // Catch:{ all -> 0x0092 }
            if (r0 == r3) goto L_0x0064
            byte[] r3 = r6.buf     // Catch:{ all -> 0x0092 }
            r0 = r3
            if (r0 == 0) goto L_0x005f
            goto L_0x0064
        L_0x005f:
            java.io.IOException r3 = streamClosed()     // Catch:{ all -> 0x0092 }
            throw r3     // Catch:{ all -> 0x0092 }
        L_0x0064:
            int r3 = r6.count     // Catch:{ all -> 0x0092 }
            int r4 = r6.pos     // Catch:{ all -> 0x0092 }
            int r5 = r3 - r4
            if (r5 < r2) goto L_0x006e
            r3 = r2
            goto L_0x006f
        L_0x006e:
            int r3 = r3 - r4
        L_0x006f:
            java.lang.System.arraycopy(r0, r4, r7, r8, r3)     // Catch:{ all -> 0x0092 }
            int r4 = r6.pos     // Catch:{ all -> 0x0092 }
            int r4 = r4 + r3
            r6.pos = r4     // Catch:{ all -> 0x0092 }
        L_0x0077:
            int r2 = r2 - r3
            if (r2 != 0) goto L_0x007c
            monitor-exit(r6)
            return r9
        L_0x007c:
            int r4 = r1.available()     // Catch:{ all -> 0x0092 }
            if (r4 != 0) goto L_0x0086
            int r4 = r9 - r2
            monitor-exit(r6)
            return r4
        L_0x0086:
            int r8 = r8 + r3
            goto L_0x0033
        L_0x0088:
            java.io.IOException r2 = streamClosed()     // Catch:{ all -> 0x0092 }
            throw r2     // Catch:{ all -> 0x0092 }
        L_0x008d:
            java.io.IOException r1 = streamClosed()     // Catch:{ all -> 0x0092 }
            throw r1     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }

    public synchronized void reset() throws IOException {
        if (this.buf != null) {
            int i = this.markpos;
            if (-1 != i) {
                this.pos = i;
            } else {
                throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
            }
        } else {
            throw new IOException("Stream is closed");
        }
    }

    public synchronized long skip(long byteCount) throws IOException {
        if (byteCount < 1) {
            return 0;
        }
        byte[] localBuf = this.buf;
        if (localBuf != null) {
            InputStream localIn = this.in;
            if (localIn != null) {
                int i = this.count;
                int i2 = this.pos;
                if (((long) (i - i2)) >= byteCount) {
                    this.pos = (int) (((long) i2) + byteCount);
                    return byteCount;
                }
                long read = ((long) i) - ((long) i2);
                this.pos = i;
                if (this.markpos == -1 || byteCount > ((long) this.marklimit)) {
                    return localIn.skip(byteCount - read) + read;
                } else if (fillbuf(localIn, localBuf) == -1) {
                    return read;
                } else {
                    int i3 = this.count;
                    int i4 = this.pos;
                    if (((long) (i3 - i4)) >= byteCount - read) {
                        this.pos = (int) ((((long) i4) + byteCount) - read);
                        return byteCount;
                    }
                    long read2 = (((long) i3) + read) - ((long) i4);
                    this.pos = i3;
                    return read2;
                }
            } else {
                throw streamClosed();
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

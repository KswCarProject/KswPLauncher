package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool) {
        this(in, byteArrayPool, 65536);
    }

    RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool, int bufferSize) {
        super(in);
        this.markpos = -1;
        this.byteArrayPool = byteArrayPool;
        this.buf = (byte[]) byteArrayPool.get(bufferSize, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
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

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
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
        int i = this.markpos;
        if (i != -1) {
            int i2 = this.pos - i;
            int i3 = this.marklimit;
            if (i2 < i3) {
                if (i == 0 && i3 > localBuf.length && this.count == localBuf.length) {
                    int newLength = localBuf.length * 2;
                    if (newLength > i3) {
                        newLength = this.marklimit;
                    }
                    byte[] newbuf = (byte[]) this.byteArrayPool.get(newLength, byte[].class);
                    System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
                    this.buf = newbuf;
                    localBuf = newbuf;
                    this.byteArrayPool.put(localBuf);
                } else if (i > 0) {
                    System.arraycopy(localBuf, i, localBuf, 0, localBuf.length - i);
                }
                int i4 = this.pos - this.markpos;
                this.pos = i4;
                this.markpos = 0;
                this.count = 0;
                int bytesread = localIn.read(localBuf, i4, localBuf.length - i4);
                int i5 = this.pos;
                if (bytesread > 0) {
                    i5 += bytesread;
                }
                this.count = i5;
                return bytesread;
            }
        }
        int result = localIn.read(localBuf);
        if (result > 0) {
            this.markpos = -1;
            this.pos = 0;
            this.count = result;
        }
        return result;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
        this.marklimit = Math.max(this.marklimit, readlimit);
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] localBuf = this.buf;
        InputStream localIn = this.in;
        if (localBuf == null || localIn == null) {
            throw streamClosed();
        }
        if (this.pos < this.count || fillbuf(localIn, localBuf) != -1) {
            if (localBuf != this.buf && (localBuf = this.buf) == null) {
                throw streamClosed();
            }
            int i = this.count;
            int i2 = this.pos;
            if (i - i2 > 0) {
                this.pos = i2 + 1;
                return localBuf[i2] & UByte.MAX_VALUE;
            }
            return -1;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] buffer, int offset, int byteCount) throws IOException {
        int required;
        int read;
        byte[] localBuf = this.buf;
        if (localBuf == null) {
            throw streamClosed();
        }
        if (byteCount == 0) {
            return 0;
        }
        InputStream localIn = this.in;
        if (localIn == null) {
            throw streamClosed();
        }
        int i = this.pos;
        int i2 = this.count;
        if (i < i2) {
            int copylength = i2 - i >= byteCount ? byteCount : i2 - i;
            System.arraycopy(localBuf, i, buffer, offset, copylength);
            this.pos += copylength;
            if (copylength == byteCount || localIn.available() == 0) {
                return copylength;
            }
            offset += copylength;
            required = byteCount - copylength;
        } else {
            required = byteCount;
        }
        while (true) {
            if (this.markpos == -1 && required >= localBuf.length) {
                read = localIn.read(buffer, offset, required);
                if (read == -1) {
                    return required != byteCount ? byteCount - required : -1;
                }
            } else {
                if (fillbuf(localIn, localBuf) == -1) {
                    return required != byteCount ? byteCount - required : -1;
                }
                if (localBuf != this.buf && (localBuf = this.buf) == null) {
                    throw streamClosed();
                }
                int i3 = this.count;
                int i4 = this.pos;
                read = i3 - i4 >= required ? required : i3 - i4;
                System.arraycopy(localBuf, i4, buffer, offset, read);
                this.pos += read;
            }
            required -= read;
            if (required == 0) {
                return byteCount;
            }
            if (localIn.available() == 0) {
                return byteCount - required;
            }
            offset += read;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        }
        int i = this.markpos;
        if (-1 == i) {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
        }
        this.pos = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long byteCount) throws IOException {
        if (byteCount < 1) {
            return 0L;
        }
        byte[] localBuf = this.buf;
        if (localBuf == null) {
            throw streamClosed();
        }
        InputStream localIn = this.in;
        if (localIn == null) {
            throw streamClosed();
        }
        int i = this.count;
        int i2 = this.pos;
        if (i - i2 >= byteCount) {
            this.pos = (int) (i2 + byteCount);
            return byteCount;
        }
        long read = i - i2;
        this.pos = i;
        if (this.markpos != -1 && byteCount <= this.marklimit) {
            if (fillbuf(localIn, localBuf) == -1) {
                return read;
            }
            int i3 = this.count;
            int i4 = this.pos;
            if (i3 - i4 >= byteCount - read) {
                this.pos = (int) ((i4 + byteCount) - read);
                return byteCount;
            }
            long read2 = (i3 + read) - i4;
            this.pos = i3;
            return read2;
        }
        return localIn.skip(byteCount - read) + read;
    }

    /* loaded from: classes.dex */
    static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        InvalidMarkException(String detailMessage) {
            super(detailMessage);
        }
    }
}

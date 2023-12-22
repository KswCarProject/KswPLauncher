package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
final class MarkableInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private long defaultMark;

    /* renamed from: in */
    private final InputStream f171in;
    private long limit;
    private long offset;
    private long reset;

    public MarkableInputStream(InputStream in) {
        this(in, 4096);
    }

    public MarkableInputStream(InputStream in, int size) {
        this.defaultMark = -1L;
        this.f171in = in.markSupported() ? in : new BufferedInputStream(in, size);
    }

    @Override // java.io.InputStream
    public void mark(int readLimit) {
        this.defaultMark = savePosition(readLimit);
    }

    public long savePosition(int readLimit) {
        long offsetLimit = this.offset + readLimit;
        if (this.limit < offsetLimit) {
            setLimit(offsetLimit);
        }
        return this.offset;
    }

    private void setLimit(long limit) {
        try {
            long j = this.reset;
            long j2 = this.offset;
            if (j < j2 && j2 <= this.limit) {
                this.f171in.reset();
                this.f171in.mark((int) (limit - this.reset));
                skip(this.reset, this.offset);
            } else {
                this.reset = j2;
                this.f171in.mark((int) (limit - j2));
            }
            this.limit = limit;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to mark: " + e);
        }
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        reset(this.defaultMark);
    }

    public void reset(long token) throws IOException {
        if (this.offset > this.limit || token < this.reset) {
            throw new IOException("Cannot reset");
        }
        this.f171in.reset();
        skip(this.reset, token);
        this.offset = token;
    }

    private void skip(long current, long target) throws IOException {
        while (current < target) {
            long skipped = this.f171in.skip(target - current);
            if (skipped == 0) {
                if (read() != -1) {
                    skipped = 1;
                } else {
                    return;
                }
            }
            current += skipped;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int result = this.f171in.read();
        if (result != -1) {
            this.offset++;
        }
        return result;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        int count = this.f171in.read(buffer);
        if (count != -1) {
            this.offset += count;
        }
        return count;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int count = this.f171in.read(buffer, offset, length);
        if (count != -1) {
            this.offset += count;
        }
        return count;
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        long skipped = this.f171in.skip(byteCount);
        this.offset += skipped;
        return skipped;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f171in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f171in.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f171in.markSupported();
    }
}

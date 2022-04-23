package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

final class MarkableInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private long defaultMark;
    private final InputStream in;
    private long limit;
    private long offset;
    private long reset;

    public MarkableInputStream(InputStream in2) {
        this(in2, 4096);
    }

    public MarkableInputStream(InputStream in2, int size) {
        this.defaultMark = -1;
        this.in = !in2.markSupported() ? new BufferedInputStream(in2, size) : in2;
    }

    public void mark(int readLimit) {
        this.defaultMark = savePosition(readLimit);
    }

    public long savePosition(int readLimit) {
        long offsetLimit = this.offset + ((long) readLimit);
        if (this.limit < offsetLimit) {
            setLimit(offsetLimit);
        }
        return this.offset;
    }

    private void setLimit(long limit2) {
        try {
            long j = this.reset;
            long j2 = this.offset;
            if (j >= j2 || j2 > this.limit) {
                this.reset = j2;
                this.in.mark((int) (limit2 - j2));
            } else {
                this.in.reset();
                this.in.mark((int) (limit2 - this.reset));
                skip(this.reset, this.offset);
            }
            this.limit = limit2;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to mark: " + e);
        }
    }

    public void reset() throws IOException {
        reset(this.defaultMark);
    }

    public void reset(long token) throws IOException {
        if (this.offset > this.limit || token < this.reset) {
            throw new IOException("Cannot reset");
        }
        this.in.reset();
        skip(this.reset, token);
        this.offset = token;
    }

    private void skip(long current, long target) throws IOException {
        while (current < target) {
            long skipped = this.in.skip(target - current);
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

    public int read() throws IOException {
        int result = this.in.read();
        if (result != -1) {
            this.offset++;
        }
        return result;
    }

    public int read(byte[] buffer) throws IOException {
        int count = this.in.read(buffer);
        if (count != -1) {
            this.offset += (long) count;
        }
        return count;
    }

    public int read(byte[] buffer, int offset2, int length) throws IOException {
        int count = this.in.read(buffer, offset2, length);
        if (count != -1) {
            this.offset += (long) count;
        }
        return count;
    }

    public long skip(long byteCount) throws IOException {
        long skipped = this.in.skip(byteCount);
        this.offset += skipped;
        return skipped;
    }

    public int available() throws IOException {
        return this.in.available();
    }

    public void close() throws IOException {
        this.in.close();
    }

    public boolean markSupported() {
        return this.in.markSupported();
    }
}

package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes = Integer.MIN_VALUE;

    public MarkEnforcingInputStream(@NonNull InputStream in) {
        super(in);
    }

    public synchronized void mark(int readLimit) {
        super.mark(readLimit);
        this.availableBytes = readLimit;
    }

    public int read() throws IOException {
        if (getBytesToRead(1) == -1) {
            return -1;
        }
        int result = super.read();
        updateAvailableBytesAfterRead(1);
        return result;
    }

    public int read(@NonNull byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int toRead = (int) getBytesToRead((long) byteCount);
        if (toRead == -1) {
            return -1;
        }
        int read = super.read(buffer, byteOffset, toRead);
        updateAvailableBytesAfterRead((long) read);
        return read;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.availableBytes = Integer.MIN_VALUE;
    }

    public long skip(long byteCount) throws IOException {
        long toSkip = getBytesToRead(byteCount);
        if (toSkip == -1) {
            return 0;
        }
        long read = super.skip(toSkip);
        updateAvailableBytesAfterRead(read);
        return read;
    }

    public int available() throws IOException {
        if (this.availableBytes == Integer.MIN_VALUE) {
            return super.available();
        }
        return Math.min(this.availableBytes, super.available());
    }

    private long getBytesToRead(long targetByteCount) {
        if (this.availableBytes == 0) {
            return -1;
        }
        if (this.availableBytes == Integer.MIN_VALUE || targetByteCount <= ((long) this.availableBytes)) {
            return targetByteCount;
        }
        return (long) this.availableBytes;
    }

    private void updateAvailableBytesAfterRead(long bytesRead) {
        if (this.availableBytes != Integer.MIN_VALUE && bytesRead != -1) {
            this.availableBytes = (int) (((long) this.availableBytes) - bytesRead);
        }
    }
}

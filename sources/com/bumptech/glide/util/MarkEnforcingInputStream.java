package com.bumptech.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes = Integer.MIN_VALUE;

    public MarkEnforcingInputStream(InputStream in) {
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

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
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
        int i = this.availableBytes;
        if (i == Integer.MIN_VALUE) {
            return super.available();
        }
        return Math.min(i, super.available());
    }

    private long getBytesToRead(long targetByteCount) {
        int i = this.availableBytes;
        if (i == 0) {
            return -1;
        }
        if (i == Integer.MIN_VALUE || targetByteCount <= ((long) i)) {
            return targetByteCount;
        }
        return (long) i;
    }

    private void updateAvailableBytesAfterRead(long bytesRead) {
        int i = this.availableBytes;
        if (i != Integer.MIN_VALUE && bytesRead != -1) {
            this.availableBytes = (int) (((long) i) - bytesRead);
        }
    }
}

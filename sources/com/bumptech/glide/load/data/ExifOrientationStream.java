package com.bumptech.glide.load.data;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

public final class ExifOrientationStream extends FilterInputStream {
    private static final byte[] EXIF_SEGMENT;
    private static final int ORIENTATION_POSITION;
    private static final int SEGMENT_LENGTH;
    private static final int SEGMENT_START_POSITION = 2;
    private final byte orientation;
    private int position;

    static {
        byte[] bArr = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
        EXIF_SEGMENT = bArr;
        int length = bArr.length;
        SEGMENT_LENGTH = length;
        ORIENTATION_POSITION = length + 2;
    }

    public ExifOrientationStream(InputStream in, int orientation2) {
        super(in);
        if (orientation2 < -1 || orientation2 > 8) {
            throw new IllegalArgumentException("Cannot add invalid orientation: " + orientation2);
        }
        this.orientation = (byte) orientation2;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int readLimit) {
        throw new UnsupportedOperationException();
    }

    public int read() throws IOException {
        int result;
        int i;
        int i2 = this.position;
        if (i2 < 2 || i2 > (i = ORIENTATION_POSITION)) {
            result = super.read();
        } else if (i2 == i) {
            result = this.orientation;
        } else {
            result = EXIF_SEGMENT[i2 - 2] & UByte.MAX_VALUE;
        }
        if (result != -1) {
            this.position++;
        }
        return result;
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int read;
        int read2 = this.position;
        int i = ORIENTATION_POSITION;
        if (read2 > i) {
            read = super.read(buffer, byteOffset, byteCount);
        } else if (read2 == i) {
            buffer[byteOffset] = this.orientation;
            read = 1;
        } else if (read2 < 2) {
            read = super.read(buffer, byteOffset, 2 - read2);
        } else {
            read = Math.min(i - read2, byteCount);
            System.arraycopy(EXIF_SEGMENT, this.position - 2, buffer, byteOffset, read);
        }
        if (read > 0) {
            this.position += read;
        }
        return read;
    }

    public long skip(long byteCount) throws IOException {
        long skipped = super.skip(byteCount);
        if (skipped > 0) {
            this.position = (int) (((long) this.position) + skipped);
        }
        return skipped;
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }
}

package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ExifOrientationStream extends FilterInputStream {
    private static final byte[] EXIF_SEGMENT = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    private static final int ORIENTATION_POSITION = (SEGMENT_LENGTH + 2);
    private static final int SEGMENT_LENGTH = EXIF_SEGMENT.length;
    private static final int SEGMENT_START_POSITION = 2;
    private final byte orientation;
    private int position;

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
        if (this.position < 2 || this.position > ORIENTATION_POSITION) {
            result = super.read();
        } else if (this.position == ORIENTATION_POSITION) {
            result = this.orientation;
        } else {
            result = EXIF_SEGMENT[this.position - 2] & 255;
        }
        if (result != -1) {
            this.position++;
        }
        return result;
    }

    public int read(@NonNull byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int read;
        if (this.position > ORIENTATION_POSITION) {
            read = super.read(buffer, byteOffset, byteCount);
        } else if (this.position == ORIENTATION_POSITION) {
            buffer[byteOffset] = this.orientation;
            read = 1;
        } else if (this.position < 2) {
            read = super.read(buffer, byteOffset, 2 - this.position);
        } else {
            read = Math.min(ORIENTATION_POSITION - this.position, byteCount);
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

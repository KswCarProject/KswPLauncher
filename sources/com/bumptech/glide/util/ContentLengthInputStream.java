package com.bumptech.glide.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ContentLengthInputStream extends FilterInputStream {
    private static final String TAG = "ContentLengthStream";
    private static final int UNKNOWN = -1;
    private final long contentLength;
    private int readSoFar;

    public static InputStream obtain(InputStream other, String contentLengthHeader) {
        return obtain(other, (long) parseContentLength(contentLengthHeader));
    }

    public static InputStream obtain(InputStream other, long contentLength2) {
        return new ContentLengthInputStream(other, contentLength2);
    }

    private static int parseContentLength(String contentLengthHeader) {
        if (TextUtils.isEmpty(contentLengthHeader)) {
            return -1;
        }
        try {
            return Integer.parseInt(contentLengthHeader);
        } catch (NumberFormatException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "failed to parse content length header: " + contentLengthHeader, e);
            return -1;
        }
    }

    private ContentLengthInputStream(InputStream in, long contentLength2) {
        super(in);
        this.contentLength = contentLength2;
    }

    public synchronized int available() throws IOException {
        return (int) Math.max(this.contentLength - ((long) this.readSoFar), (long) this.in.available());
    }

    public synchronized int read() throws IOException {
        int value;
        value = super.read();
        checkReadSoFarOrThrow(value >= 0 ? 1 : -1);
        return value;
    }

    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public synchronized int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        return checkReadSoFarOrThrow(super.read(buffer, byteOffset, byteCount));
    }

    private int checkReadSoFarOrThrow(int read) throws IOException {
        if (read >= 0) {
            this.readSoFar += read;
        } else if (this.contentLength - ((long) this.readSoFar) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.contentLength + ", but read: " + this.readSoFar);
        }
        return read;
    }
}

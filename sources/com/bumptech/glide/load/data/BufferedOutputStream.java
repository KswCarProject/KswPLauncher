package com.bumptech.glide.load.data;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;

public final class BufferedOutputStream extends OutputStream {
    private ArrayPool arrayPool;
    private byte[] buffer;
    private int index;
    private final OutputStream out;

    public BufferedOutputStream(OutputStream out2, ArrayPool arrayPool2) {
        this(out2, arrayPool2, 65536);
    }

    BufferedOutputStream(OutputStream out2, ArrayPool arrayPool2, int bufferSize) {
        this.out = out2;
        this.arrayPool = arrayPool2;
        this.buffer = (byte[]) arrayPool2.get(bufferSize, byte[].class);
    }

    public void write(int b) throws IOException {
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        bArr[i] = (byte) b;
        maybeFlushBuffer();
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int initialOffset, int length) throws IOException {
        int writtenSoFar = 0;
        do {
            int remainingToWrite = length - writtenSoFar;
            int currentOffset = initialOffset + writtenSoFar;
            int i = this.index;
            if (i != 0 || remainingToWrite < this.buffer.length) {
                int totalBytesToWriteToBuffer = Math.min(remainingToWrite, this.buffer.length - i);
                System.arraycopy(b, currentOffset, this.buffer, this.index, totalBytesToWriteToBuffer);
                this.index += totalBytesToWriteToBuffer;
                writtenSoFar += totalBytesToWriteToBuffer;
                maybeFlushBuffer();
            } else {
                this.out.write(b, currentOffset, remainingToWrite);
                return;
            }
        } while (writtenSoFar < length);
    }

    public void flush() throws IOException {
        flushBuffer();
        this.out.flush();
    }

    private void flushBuffer() throws IOException {
        int i = this.index;
        if (i > 0) {
            this.out.write(this.buffer, 0, i);
            this.index = 0;
        }
    }

    private void maybeFlushBuffer() throws IOException {
        if (this.index == this.buffer.length) {
            flushBuffer();
        }
    }

    /* JADX INFO: finally extract failed */
    public void close() throws IOException {
        try {
            flush();
            this.out.close();
            release();
        } catch (Throwable th) {
            this.out.close();
            throw th;
        }
    }

    private void release() {
        byte[] bArr = this.buffer;
        if (bArr != null) {
            this.arrayPool.put(bArr);
            this.buffer = null;
        }
    }
}

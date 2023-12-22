package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
class StrictLineReader implements Closeable {

    /* renamed from: CR */
    private static final byte f69CR = 13;

    /* renamed from: LF */
    private static final byte f70LF = 10;
    private byte[] buf;
    private final Charset charset;
    private int end;

    /* renamed from: in */
    private final InputStream f71in;
    private int pos;

    public StrictLineReader(InputStream in, Charset charset) {
        this(in, 8192, charset);
    }

    public StrictLineReader(InputStream in, int capacity, Charset charset) {
        if (in == null || charset == null) {
            throw new NullPointerException();
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(Util.US_ASCII)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f71in = in;
        this.charset = charset;
        this.buf = new byte[capacity];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f71in) {
            if (this.buf != null) {
                this.buf = null;
                this.f71in.close();
            }
        }
    }

    public String readLine() throws IOException {
        int i;
        byte[] bArr;
        synchronized (this.f71in) {
            if (this.buf == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.pos >= this.end) {
                fillBuf();
            }
            int i2 = this.pos;
            while (i2 != this.end) {
                byte[] bArr2 = this.buf;
                if (bArr2[i2] != 10) {
                    i2++;
                } else {
                    int lineEnd = (i2 == this.pos || bArr2[i2 + (-1)] != 13) ? i2 : i2 - 1;
                    byte[] bArr3 = this.buf;
                    int i3 = this.pos;
                    String res = new String(bArr3, i3, lineEnd - i3, this.charset.name());
                    this.pos = i2 + 1;
                    return res;
                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream((this.end - this.pos) + 80) { // from class: com.bumptech.glide.disklrucache.StrictLineReader.1
                @Override // java.io.ByteArrayOutputStream
                public String toString() {
                    int length = (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1;
                    try {
                        return new String(this.buf, 0, length, StrictLineReader.this.charset.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byte[] bArr4 = this.buf;
                int i4 = this.pos;
                out.write(bArr4, i4, this.end - i4);
                this.end = -1;
                fillBuf();
                i = this.pos;
                while (i != this.end) {
                    bArr = this.buf;
                    if (bArr[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            int i5 = this.pos;
            if (i != i5) {
                out.write(bArr, i5, i - i5);
            }
            this.pos = i + 1;
            return out.toString();
        }
    }

    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }

    private void fillBuf() throws IOException {
        InputStream inputStream = this.f71in;
        byte[] bArr = this.buf;
        int result = inputStream.read(bArr, 0, bArr.length);
        if (result == -1) {
            throw new EOFException();
        }
        this.pos = 0;
        this.end = result;
    }
}

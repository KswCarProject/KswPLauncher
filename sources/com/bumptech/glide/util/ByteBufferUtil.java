package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteBufferUtil {
    private static final AtomicReference<byte[]> BUFFER_REF = new AtomicReference<>();
    private static final int BUFFER_SIZE = 16384;

    private ByteBufferUtil() {
    }

    @NonNull
    public static ByteBuffer fromFile(@NonNull File file) throws IOException {
        RandomAccessFile raf = null;
        FileChannel channel = null;
        try {
            long fileLength = file.length();
            if (fileLength > 2147483647L) {
                throw new IOException("File too large to map into memory");
            } else if (fileLength != 0) {
                RandomAccessFile raf2 = new RandomAccessFile(file, "r");
                FileChannel channel2 = raf2.getChannel();
                MappedByteBuffer load = channel2.map(FileChannel.MapMode.READ_ONLY, 0, fileLength).load();
                if (channel2 != null) {
                    try {
                        channel2.close();
                    } catch (IOException e) {
                    }
                }
                try {
                    raf2.close();
                } catch (IOException e2) {
                }
                return load;
            } else {
                throw new IOException("File unsuitable for memory mapping");
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e3) {
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    public static void toFile(@NonNull ByteBuffer buffer, @NonNull File file) throws IOException {
        buffer.position(0);
        RandomAccessFile raf = null;
        FileChannel channel = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            channel = raf.getChannel();
            channel.write(buffer);
            channel.force(false);
            channel.close();
            raf.close();
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
            try {
                raf.close();
            } catch (IOException e2) {
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e3) {
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    public static void toStream(@NonNull ByteBuffer byteBuffer, @NonNull OutputStream os) throws IOException {
        SafeArray safeArray = getSafeArray(byteBuffer);
        if (safeArray != null) {
            os.write(safeArray.data, safeArray.offset, safeArray.offset + safeArray.limit);
            return;
        }
        byte[] buffer = BUFFER_REF.getAndSet((Object) null);
        if (buffer == null) {
            buffer = new byte[16384];
        }
        while (byteBuffer.remaining() > 0) {
            int toRead = Math.min(byteBuffer.remaining(), buffer.length);
            byteBuffer.get(buffer, 0, toRead);
            os.write(buffer, 0, toRead);
        }
        BUFFER_REF.set(buffer);
    }

    @NonNull
    public static byte[] toBytes(@NonNull ByteBuffer byteBuffer) {
        SafeArray safeArray = getSafeArray(byteBuffer);
        if (safeArray != null && safeArray.offset == 0 && safeArray.limit == safeArray.data.length) {
            return byteBuffer.array();
        }
        ByteBuffer toCopy = byteBuffer.asReadOnlyBuffer();
        byte[] result = new byte[toCopy.limit()];
        toCopy.position(0);
        toCopy.get(result);
        return result;
    }

    @NonNull
    public static InputStream toStream(@NonNull ByteBuffer buffer) {
        return new ByteBufferStream(buffer);
    }

    @NonNull
    public static ByteBuffer fromStream(@NonNull InputStream stream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(16384);
        byte[] buffer = BUFFER_REF.getAndSet((Object) null);
        if (buffer == null) {
            buffer = new byte[16384];
        }
        while (true) {
            int read = stream.read(buffer);
            int n = read;
            if (read >= 0) {
                outStream.write(buffer, 0, n);
            } else {
                BUFFER_REF.set(buffer);
                byte[] bytes = outStream.toByteArray();
                return (ByteBuffer) ByteBuffer.allocateDirect(bytes.length).put(bytes).position(0);
            }
        }
    }

    @Nullable
    private static SafeArray getSafeArray(@NonNull ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly() || !byteBuffer.hasArray()) {
            return null;
        }
        return new SafeArray(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
    }

    static final class SafeArray {
        final byte[] data;
        final int limit;
        final int offset;

        SafeArray(@NonNull byte[] data2, int offset2, int limit2) {
            this.data = data2;
            this.offset = offset2;
            this.limit = limit2;
        }
    }

    private static class ByteBufferStream extends InputStream {
        private static final int UNSET = -1;
        @NonNull
        private final ByteBuffer byteBuffer;
        private int markPos = -1;

        ByteBufferStream(@NonNull ByteBuffer byteBuffer2) {
            this.byteBuffer = byteBuffer2;
        }

        public int available() {
            return this.byteBuffer.remaining();
        }

        public int read() {
            if (!this.byteBuffer.hasRemaining()) {
                return -1;
            }
            return this.byteBuffer.get();
        }

        public synchronized void mark(int readLimit) {
            this.markPos = this.byteBuffer.position();
        }

        public boolean markSupported() {
            return true;
        }

        public int read(@NonNull byte[] buffer, int byteOffset, int byteCount) throws IOException {
            if (!this.byteBuffer.hasRemaining()) {
                return -1;
            }
            int toRead = Math.min(byteCount, available());
            this.byteBuffer.get(buffer, byteOffset, toRead);
            return toRead;
        }

        public synchronized void reset() throws IOException {
            if (this.markPos != -1) {
                this.byteBuffer.position(this.markPos);
            } else {
                throw new IOException("Cannot reset to unset mark position");
            }
        }

        public long skip(long byteCount) throws IOException {
            if (!this.byteBuffer.hasRemaining()) {
                return -1;
            }
            long toSkip = Math.min(byteCount, (long) available());
            this.byteBuffer.position((int) (((long) this.byteBuffer.position()) + toSkip));
            return toSkip;
        }
    }
}

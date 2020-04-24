package com.bumptech.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import java.nio.ByteBuffer;

public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
    private final ByteBuffer buffer;

    public ByteBufferRewinder(ByteBuffer buffer2) {
        this.buffer = buffer2;
    }

    @NonNull
    public ByteBuffer rewindAndGet() {
        this.buffer.position(0);
        return this.buffer;
    }

    public void cleanup() {
    }

    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        @NonNull
        public DataRewinder<ByteBuffer> build(ByteBuffer data) {
            return new ByteBufferRewinder(data);
        }

        @NonNull
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }
    }
}

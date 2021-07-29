package com.bumptech.glide.load.model;

import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamEncoder implements Encoder<InputStream> {
    private static final String TAG = "StreamEncoder";
    private final ArrayPool byteArrayPool;

    public StreamEncoder(ArrayPool byteArrayPool2) {
        this.byteArrayPool = byteArrayPool2;
    }

    public boolean encode(InputStream data, File file, Options options) {
        byte[] buffer = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        boolean success = false;
        OutputStream os = null;
        try {
            OutputStream os2 = new FileOutputStream(file);
            while (true) {
                int read = data.read(buffer);
                int read2 = read;
                if (read == -1) {
                    break;
                }
                os2.write(buffer, 0, read2);
            }
            os2.close();
            success = true;
            try {
                os2.close();
            } catch (IOException e) {
            }
        } catch (IOException e2) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to encode data onto the OutputStream", e2);
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e4) {
                }
            }
            this.byteArrayPool.put(buffer);
            throw th;
        }
        this.byteArrayPool.put(buffer);
        return success;
    }
}

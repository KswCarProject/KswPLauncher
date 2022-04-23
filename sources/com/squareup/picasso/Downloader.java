package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

public interface Downloader {
    Response load(Uri uri, int i) throws IOException;

    void shutdown();

    public static class ResponseException extends IOException {
        final boolean localCacheOnly;
        final int responseCode;

        public ResponseException(String message, int networkPolicy, int responseCode2) {
            super(message);
            this.localCacheOnly = NetworkPolicy.isOfflineOnly(networkPolicy);
            this.responseCode = responseCode2;
        }
    }

    public static class Response {
        final Bitmap bitmap;
        final boolean cached;
        final long contentLength;
        final InputStream stream;

        @Deprecated
        public Response(Bitmap bitmap2, boolean loadedFromCache) {
            if (bitmap2 != null) {
                this.stream = null;
                this.bitmap = bitmap2;
                this.cached = loadedFromCache;
                this.contentLength = -1;
                return;
            }
            throw new IllegalArgumentException("Bitmap may not be null.");
        }

        @Deprecated
        public Response(InputStream stream2, boolean loadedFromCache) {
            this(stream2, loadedFromCache, -1);
        }

        @Deprecated
        public Response(Bitmap bitmap2, boolean loadedFromCache, long contentLength2) {
            this(bitmap2, loadedFromCache);
        }

        public Response(InputStream stream2, boolean loadedFromCache, long contentLength2) {
            if (stream2 != null) {
                this.stream = stream2;
                this.bitmap = null;
                this.cached = loadedFromCache;
                this.contentLength = contentLength2;
                return;
            }
            throw new IllegalArgumentException("Stream may not be null.");
        }

        public InputStream getInputStream() {
            return this.stream;
        }

        @Deprecated
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public long getContentLength() {
            return this.contentLength;
        }
    }
}

package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.NetworkInfo;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.io.InputStream;

class NetworkRequestHandler extends RequestHandler {
    static final int RETRY_COUNT = 2;
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";
    private final Downloader downloader;
    private final Stats stats;

    public NetworkRequestHandler(Downloader downloader2, Stats stats2) {
        this.downloader = downloader2;
        this.stats = stats2;
    }

    public boolean canHandleRequest(Request data) {
        String scheme = data.uri.getScheme();
        return SCHEME_HTTP.equals(scheme) || SCHEME_HTTPS.equals(scheme);
    }

    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        Downloader.Response response = this.downloader.load(request.uri, request.networkPolicy);
        if (response == null) {
            return null;
        }
        Picasso.LoadedFrom loadedFrom = response.cached ? Picasso.LoadedFrom.DISK : Picasso.LoadedFrom.NETWORK;
        Bitmap bitmap = response.getBitmap();
        if (bitmap != null) {
            return new RequestHandler.Result(bitmap, loadedFrom);
        }
        InputStream is = response.getInputStream();
        if (is == null) {
            return null;
        }
        if (loadedFrom == Picasso.LoadedFrom.DISK && response.getContentLength() == 0) {
            Utils.closeQuietly(is);
            throw new ContentLengthException("Received response with 0 content-length header.");
        }
        if (loadedFrom == Picasso.LoadedFrom.NETWORK && response.getContentLength() > 0) {
            this.stats.dispatchDownloadFinished(response.getContentLength());
        }
        return new RequestHandler.Result(is, loadedFrom);
    }

    /* access modifiers changed from: package-private */
    public int getRetryCount() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRetry(boolean airplaneMode, NetworkInfo info) {
        return info == null || info.isConnected();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsReplay() {
        return true;
    }

    static class ContentLengthException extends IOException {
        public ContentLengthException(String message) {
            super(message);
        }
    }
}

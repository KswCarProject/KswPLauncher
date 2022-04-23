package com.bumptech.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpUrlFetcher implements DataFetcher<InputStream> {
    static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultHttpUrlConnectionFactory();
    private static final int INVALID_STATUS_CODE = -1;
    private static final int MAXIMUM_REDIRECTS = 5;
    private static final String TAG = "HttpUrlFetcher";
    private final HttpUrlConnectionFactory connectionFactory;
    private final GlideUrl glideUrl;
    private volatile boolean isCancelled;
    private InputStream stream;
    private final int timeout;
    private HttpURLConnection urlConnection;

    interface HttpUrlConnectionFactory {
        HttpURLConnection build(URL url) throws IOException;
    }

    public HttpUrlFetcher(GlideUrl glideUrl2, int timeout2) {
        this(glideUrl2, timeout2, DEFAULT_CONNECTION_FACTORY);
    }

    HttpUrlFetcher(GlideUrl glideUrl2, int timeout2, HttpUrlConnectionFactory connectionFactory2) {
        this.glideUrl = glideUrl2;
        this.timeout = timeout2;
        this.connectionFactory = connectionFactory2;
    }

    public void loadData(Priority priority, DataFetcher.DataCallback<? super InputStream> callback) {
        StringBuilder sb;
        long startTime = LogTime.getLogTime();
        try {
            callback.onDataReady(loadDataWithRedirects(this.glideUrl.toURL(), 0, (URL) null, this.glideUrl.getHeaders()));
            if (Log.isLoggable(TAG, 2)) {
                sb = new StringBuilder();
                Log.v(TAG, sb.append("Finished http url fetcher fetch in ").append(LogTime.getElapsedMillis(startTime)).toString());
            }
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to load data for url", e);
            }
            callback.onLoadFailed(e);
            if (Log.isLoggable(TAG, 2)) {
                sb = new StringBuilder();
            }
        } catch (Throwable th) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Finished http url fetcher fetch in " + LogTime.getElapsedMillis(startTime));
            }
            throw th;
        }
    }

    private InputStream loadDataWithRedirects(URL url, int redirects, URL lastUrl, Map<String, String> headers) throws IOException {
        if (redirects < 5) {
            if (lastUrl != null) {
                try {
                    if (url.toURI().equals(lastUrl.toURI())) {
                        throw new HttpException("In re-direct loop");
                    }
                } catch (URISyntaxException e) {
                }
            }
            this.urlConnection = this.connectionFactory.build(url);
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                this.urlConnection.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }
            this.urlConnection.setConnectTimeout(this.timeout);
            this.urlConnection.setReadTimeout(this.timeout);
            this.urlConnection.setUseCaches(false);
            this.urlConnection.setDoInput(true);
            this.urlConnection.setInstanceFollowRedirects(false);
            this.urlConnection.connect();
            this.stream = this.urlConnection.getInputStream();
            if (this.isCancelled) {
                return null;
            }
            int statusCode = this.urlConnection.getResponseCode();
            if (isHttpOk(statusCode)) {
                return getStreamForSuccessfulRequest(this.urlConnection);
            }
            if (isHttpRedirect(statusCode)) {
                String redirectUrlString = this.urlConnection.getHeaderField("Location");
                if (!TextUtils.isEmpty(redirectUrlString)) {
                    URL redirectUrl = new URL(url, redirectUrlString);
                    cleanup();
                    return loadDataWithRedirects(redirectUrl, redirects + 1, url, headers);
                }
                throw new HttpException("Received empty or null redirect url");
            } else if (statusCode == -1) {
                throw new HttpException(statusCode);
            } else {
                throw new HttpException(this.urlConnection.getResponseMessage(), statusCode);
            }
        } else {
            throw new HttpException("Too many (> 5) redirects!");
        }
    }

    private static boolean isHttpOk(int statusCode) {
        return statusCode / 100 == 2;
    }

    private static boolean isHttpRedirect(int statusCode) {
        return statusCode / 100 == 3;
    }

    private InputStream getStreamForSuccessfulRequest(HttpURLConnection urlConnection2) throws IOException {
        if (TextUtils.isEmpty(urlConnection2.getContentEncoding())) {
            this.stream = ContentLengthInputStream.obtain(urlConnection2.getInputStream(), (long) urlConnection2.getContentLength());
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Got non empty content encoding: " + urlConnection2.getContentEncoding());
            }
            this.stream = urlConnection2.getInputStream();
        }
        return this.stream;
    }

    public void cleanup() {
        InputStream inputStream = this.stream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
        HttpURLConnection httpURLConnection = this.urlConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.urlConnection = null;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        DefaultHttpUrlConnectionFactory() {
        }

        public HttpURLConnection build(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
}

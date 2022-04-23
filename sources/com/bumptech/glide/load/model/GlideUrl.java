package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

public class GlideUrl implements Key {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%;$";
    private volatile byte[] cacheKeyBytes;
    private int hashCode;
    private final Headers headers;
    private String safeStringUrl;
    private URL safeUrl;
    private final String stringUrl;
    private final URL url;

    public GlideUrl(URL url2) {
        this(url2, Headers.DEFAULT);
    }

    public GlideUrl(String url2) {
        this(url2, Headers.DEFAULT);
    }

    public GlideUrl(URL url2, Headers headers2) {
        this.url = (URL) Preconditions.checkNotNull(url2);
        this.stringUrl = null;
        this.headers = (Headers) Preconditions.checkNotNull(headers2);
    }

    public GlideUrl(String url2, Headers headers2) {
        this.url = null;
        this.stringUrl = Preconditions.checkNotEmpty(url2);
        this.headers = (Headers) Preconditions.checkNotNull(headers2);
    }

    public URL toURL() throws MalformedURLException {
        return getSafeUrl();
    }

    private URL getSafeUrl() throws MalformedURLException {
        if (this.safeUrl == null) {
            this.safeUrl = new URL(getSafeStringUrl());
        }
        return this.safeUrl;
    }

    public String toStringUrl() {
        return getSafeStringUrl();
    }

    private String getSafeStringUrl() {
        if (TextUtils.isEmpty(this.safeStringUrl)) {
            String unsafeStringUrl = this.stringUrl;
            if (TextUtils.isEmpty(unsafeStringUrl)) {
                unsafeStringUrl = ((URL) Preconditions.checkNotNull(this.url)).toString();
            }
            this.safeStringUrl = Uri.encode(unsafeStringUrl, ALLOWED_URI_CHARS);
        }
        return this.safeStringUrl;
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public String getCacheKey() {
        String str = this.stringUrl;
        return str != null ? str : ((URL) Preconditions.checkNotNull(this.url)).toString();
    }

    public String toString() {
        return getCacheKey();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(getCacheKeyBytes());
    }

    private byte[] getCacheKeyBytes() {
        if (this.cacheKeyBytes == null) {
            this.cacheKeyBytes = getCacheKey().getBytes(CHARSET);
        }
        return this.cacheKeyBytes;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GlideUrl)) {
            return false;
        }
        GlideUrl other = (GlideUrl) o;
        if (!getCacheKey().equals(other.getCacheKey()) || !this.headers.equals(other.headers)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode2 = getCacheKey().hashCode();
            this.hashCode = hashCode2;
            this.hashCode = (hashCode2 * 31) + this.headers.hashCode();
        }
        return this.hashCode;
    }
}

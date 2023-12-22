package com.bumptech.glide.load;

import java.io.IOException;

/* loaded from: classes.dex */
public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public HttpException(int statusCode) {
        this("Http request failed with status code: " + statusCode, statusCode);
    }

    public HttpException(String message) {
        this(message, -1);
    }

    public HttpException(String message, int statusCode) {
        this(message, statusCode, null);
    }

    public HttpException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}

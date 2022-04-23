package com.bumptech.glide.load;

import java.io.IOException;

public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public HttpException(int statusCode2) {
        this("Http request failed with status code: " + statusCode2, statusCode2);
    }

    public HttpException(String message) {
        this(message, -1);
    }

    public HttpException(String message, int statusCode2) {
        this(message, statusCode2, (Throwable) null);
    }

    public HttpException(String message, int statusCode2, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
